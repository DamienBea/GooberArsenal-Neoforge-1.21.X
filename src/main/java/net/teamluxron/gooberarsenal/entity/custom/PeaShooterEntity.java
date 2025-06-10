package net.teamluxron.gooberarsenal.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.teamluxron.gooberarsenal.item.ModItems;
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
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                this,
                Mob.class,
                10,
                true,
                true,
                target -> {
                    return target instanceof Enemy &&
                            !(target instanceof PeaShooterEntity) &&
                            target.isAlive();
                }
        ));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new PeashooterAttackGoal(this));
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
            this.setupAnimationStates();
        }

        if (this.attackCooldown > 0) {
            this.attackCooldown--;
        }

        if (this.isSheared()) {
            BlockPos belowPos = this.blockPosition().below();
            if (this.level().getBlockState(belowPos).is(Blocks.GRASS_BLOCK)) {
                if (++this.regrowTime >= 6000) { // 5 minutes (6000 ticks)
                    this.setSheared(false);
                    this.regrowTime = 0;
                }
            } else {
                this.regrowTime = 0;
            }
        }
    }

    private void setupAnimationStates() {
        // Always try to play idle animation
        if (!this.attackAnimationState.isStarted()) {
            this.idleAnimationState.startIfStopped(this.tickCount);
        }

        // Stop attack animation after it completes
        if (this.attackAnimationState.isStarted() &&
                this.attackAnimationState.getAccumulatedTime() >= 20) { // 20 ticks = 1 second
            this.attackAnimationState.stop();
        }
    }

     public void shootBean() {
        if (this.level().isClientSide()) return;

        LivingEntity target = this.getTarget();
        if (target == null) return;

        BeanProjectile bean = new BeanProjectile(this.level(), this);

        Vec3 lookAngle = this.getLookAngle();
        double spawnDistance = 0.5;
        Vec3 spawnPos = this.position()
                .add(0, this.getBbHeight() * 0.75, 0)
                .add(lookAngle.scale(spawnDistance));

        bean.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

        Vec3 targetPos = target.position().add(0, target.getBbHeight() / 2, 0);
        Vec3 toTarget = targetPos.subtract(spawnPos);
        double distance = toTarget.length();
        double flightTime = distance / 1.5;

        Vec3 targetVelocity = target.getDeltaMovement();
        Vec3 predictedPos = targetPos.add(targetVelocity.scale(flightTime));

        Vec3 direction = predictedPos.subtract(spawnPos).normalize();

        float inaccuracy = 0.01f;
        direction = direction.add(
                (this.random.nextFloat() - 0.5f) * inaccuracy,
                (this.random.nextFloat() - 0.5f) * inaccuracy,
                (this.random.nextFloat() - 0.5f) * inaccuracy
        ).normalize();

        float velocity = 1.5f;
        bean.setDeltaMovement(direction.scale(velocity));

        bean.shoot(direction.x, direction.y, direction.z, velocity, inaccuracy);

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

    public static class PeashooterAttackGoal extends Goal {
        private final PeaShooterEntity peashooter;
        private int seeTime;

        public PeashooterAttackGoal(PeaShooterEntity peashooter) {
            this.peashooter = peashooter;
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.peashooter.getTarget();
            return target != null && target.isAlive() && this.peashooter.hasLineOfSight(target);
        }

        @Override
        public void start() {
            this.seeTime = 0;
        }

        @Override
        public void tick() {
            LivingEntity target = this.peashooter.getTarget();
            if (target == null) return;

            double distanceSq = this.peashooter.distanceToSqr(target);
            boolean canSee = this.peashooter.getSensing().hasLineOfSight(target);

            if (canSee) {
                ++this.seeTime;
            } else {
                this.seeTime = 0;
            }
            if (distanceSq <= 64.0 && this.seeTime >= 5 && this.peashooter.attackCooldown <= 0) {
                this.peashooter.level().broadcastEntityEvent(this.peashooter, (byte) 4);
                this.peashooter.attackCooldown = 20;
            }
            this.peashooter.getLookControl().setLookAt(target, 30.0F, 30.0F);
        }
    }
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.attackAnimationState.start(this.tickCount);

            if (!this.level().isClientSide() && this.isAlive()) {
                this.shootBean();
            }
        } else {
            super.handleEntityEvent(id);
        }
    }

}