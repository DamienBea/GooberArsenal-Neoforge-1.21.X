package net.teamluxron.gooberarsenal.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.world.entity.ai.goal.PeashooterAttackGoal;
import net.teamluxron.gooberarsenal.world.entity.ai.goal.RevengeHelpTargetGoal;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class PeaShooterEntity extends Mob {
    private static final EntityDataAccessor<Boolean> DATA_SHEARED = SynchedEntityData.defineId(PeaShooterEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Optional<UUID>> DATA_OWNER_UUID = SynchedEntityData.defineId(PeaShooterEntity.class, EntityDataSerializers.OPTIONAL_UUID);

    private int regrowTime;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int attackAnimationTimeout = 0;
    private int attackCooldown = 0;

    public PeaShooterEntity(EntityType<? extends Mob> type, Level level) {
        super(type, level);
        this.noCulling = true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_SHEARED, false);
        builder.define(DATA_OWNER_UUID, Optional.empty());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        UUID owner = getOwnerUUID();
        if (owner != null) {
            tag.putUUID("Owner", owner);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID("Owner")) {
            setOwnerUUID(tag.getUUID("Owner"));
        }
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new RevengeHelpTargetGoal(this));
        this.targetSelector.addGoal(1,
                new NearestAttackableTargetGoal<>(
                        this,
                        Monster.class,
                        28,
                        true,
                        true,
                        mob -> !(mob instanceof PeaShooterEntity)
                )
        );
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8f));
        this.goalSelector.addGoal(2, new PeashooterAttackGoal(this));
    }



    public class OwnerHurtByTargetGoal extends TargetGoal {
        private final PeaShooterEntity peaShooter;
        private LivingEntity ownerAttacker;
        private int timestamp;
        private final TargetingConditions targetConditions;

        public OwnerHurtByTargetGoal(PeaShooterEntity peaShooter) {
            super(peaShooter, false);
            this.peaShooter = peaShooter;
            this.targetConditions = TargetingConditions.forCombat().range(16.0);
        }

        @Override
        public boolean canUse() {
            Player owner = peaShooter.getOwner();
            if (owner == null) {
                return false;
            }
            this.ownerAttacker = owner.getLastHurtByMob();
            int lastAttackedTime = owner.getLastHurtByMobTimestamp();
            return lastAttackedTime != this.timestamp &&
                    this.ownerAttacker != null &&
                    this.canAttack(this.ownerAttacker, this.targetConditions);
        }


        @Override
        public void start() {
            this.mob.setTarget(this.ownerAttacker);
            Player owner = peaShooter.getOwner();
            if (owner != null) {
                this.timestamp = owner.getLastHurtByMobTimestamp();
            }
            super.start();
        }
    }

    public class OwnerHurtTargetGoal extends TargetGoal {
        private final PeaShooterEntity peaShooter;
        private LivingEntity ownerHurt;
        private int timestamp;

        private final TargetingConditions targetConditions;

        public OwnerHurtTargetGoal(PeaShooterEntity peaShooter) {
            super(peaShooter, false);
            this.peaShooter = peaShooter;
            this.targetConditions = TargetingConditions.forCombat().range(16.0);
        }

        @Override
        public boolean canUse() {
            Player owner = peaShooter.getOwner();
            if (owner == null) {
                return false;
            }
            this.ownerHurt = owner.getLastHurtMob();
            int lastHurtTime = owner.getLastHurtMobTimestamp();
            return lastHurtTime != this.timestamp &&
                    this.ownerHurt != null &&
                    this.canAttack(this.ownerHurt, this.targetConditions);
        }

        @Override
        public void start() {
            this.mob.setTarget(this.ownerHurt);
            Player owner = peaShooter.getOwner();
            if (owner != null) {
                this.timestamp = owner.getLastHurtMobTimestamp();
            }
            super.start();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.updateAnimationStates();
        }
        if (this.attackCooldown > 0) {
            this.attackCooldown--;
        }

        if (this.isSheared()) {
            BlockPos belowPos = this.blockPosition().below();
            if (this.level().getBlockState(belowPos).is(Blocks.GRASS_BLOCK)) {
                if (++this.regrowTime >= 6000) {
                    this.setSheared(false);
                    this.regrowTime = 0;
                }
            } else {
                this.regrowTime = 0;
            }
        }
    }

    private void updateAnimationStates() {
        if (attackAnimationState.isStarted()) {
            if (attackAnimationState.getAccumulatedTime() >= 20) {
                attackAnimationState.stop();
            }
        } else {
            idleAnimationState.startIfStopped(this.tickCount);
        }
    }



    public void shootBean() {
        LivingEntity target = this.getTarget();
        if (target == null) return;

        BeanProjectile bean = new BeanProjectile(this.level(), this);

        double eyeHeight = 9.0 / 16.0;
        Vec3 start = this.position().add(0, eyeHeight, 0);
        bean.setPos(start.x, start.y, start.z);

        Vec3 aim = target.position()
                .add(0, target.getBbHeight() * 0.5, 0)
                .subtract(start)
                .normalize();

        bean.shoot(aim.x, aim.y, aim.z, 1.5f, 0.01f);
        this.level().addFreshEntity(bean);
    }


    public boolean isSheared() {
        return this.entityData.get(DATA_SHEARED);
    }

    public void setSheared(boolean sheared) {
        this.entityData.set(DATA_SHEARED, sheared);
    }

    @Nullable
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNER_UUID).orElse(null);
    }

    public void setOwnerUUID(@Nullable UUID uuid) {
        this.entityData.set(DATA_OWNER_UUID, Optional.ofNullable(uuid));
    }

    @Nullable
    public Player getOwner() {
        UUID uuid = this.getOwnerUUID();
        if (uuid == null) return null;
        return this.level().getPlayerByUUID(uuid);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(Items.SHEARS) && !this.isSheared()) {
            if (!this.level().isClientSide) {
                this.setSheared(true);
                itemstack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND
                );

                int count = 1 + this.random.nextInt(3);
                this.spawnAtLocation(new ItemStack(ModItems.MAGICAL_BEAN_SEED.get(), count));
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }


    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            // trigger the shooting animation on client
            attackAnimationState.startIfStopped(this.tickCount);
        } else {
            super.handleEntityEvent(id);
        }
    }
}