package net.teamluxron.gooberarsenal.entity.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.event.EventHooks;
import net.teamluxron.gooberarsenal.world.entity.ai.goal.SeekNetherPortalGoal;

import java.util.function.Predicate;

public class ZombifiedBreadEntity extends Monster {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState chasingAnimationState = new AnimationState();


    private int idleAnimationTimeout = 0;
    private static final float BREAK_DOOR_CHANCE = 0.1F;
    private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE;
    private final BreakDoorGoal breakDoorGoal;
    private boolean canBreakDoors;


    public ZombifiedBreadEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
    }

    public static boolean checkZombifiedBreadSpawnRules(EntityType<ZombifiedBreadEntity> entityType, ServerLevelAccessor level,
                                                        MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        if (!level.getLevel().dimension().location().equals(Level.NETHER.location())) {
            return false;
        }
        if (!level.getBiome(pos).is(Biomes.NETHER_WASTES)) {
            return false;
        }
        if (random.nextInt(100) != 0) {
            return false;
        }
        return Monster.checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this,  1.2, true));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Cod.class, 6.0F, (double)1.0F, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Salmon.class, 6.0F, (double)1.0F, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Pufferfish.class, 6.0F, (double)1.0F, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, TropicalFish.class, 6.0F, (double)1.0F, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Fox.class, 6.0F, (double)1.0F, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Horse.class, 6.0F, (double)1.0F, 1.2));
        this.goalSelector.addGoal(4, new SeekNetherPortalGoal(this, 1.2));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[]{ZombifiedBreadEntity.class}));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractIllager.class, true));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.MOVEMENT_SPEED, 0.35f);
    }

    public boolean canBreakDoors() {
        return this.canBreakDoors;
    }

    public void setCanBreakDoors(boolean canBreakDoors) {
        if (this.supportsBreakDoorGoal() && GoalUtils.hasGroundPathNavigation(this)) {
            if (this.canBreakDoors != canBreakDoors) {
                this.canBreakDoors = canBreakDoors;
                ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(canBreakDoors);
                if (canBreakDoors) {
                    this.goalSelector.addGoal(1, this.breakDoorGoal);
                } else {
                    this.goalSelector.removeGoal(this.breakDoorGoal);
                }
            }
        } else if (this.canBreakDoors) {
            this.goalSelector.removeGoal(this.breakDoorGoal);
            this.canBreakDoors = false;
        }
    }


    protected int getBaseExperienceReward() {
        if (this.isBaby()) {
            this.xpReward = (int)((double)this.xpReward * (double)2.5F);
        }
        return super.getBaseExperienceReward();
    }

    protected ItemStack getSkull() {
        return new ItemStack(Items.ZOMBIE_HEAD);
    }

    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        Item item = itemstack.getItem();
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.onEquippedItemBroken(item, EquipmentSlot.HEAD);
                            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }
                    flag = false;
                }
                if (flag) {
                    this.igniteForSeconds(8.0F);
                }
            }
        }
        super.aiStep();
    }

    protected boolean isSunSensitive() {
        return true;
    }

    protected boolean supportsBreakDoorGoal() {
        return true;
    }

    public boolean killedEntity(ServerLevel level, LivingEntity entity) {
        boolean flag = super.killedEntity(level, entity);
        if ((level.getDifficulty() == Difficulty.NORMAL || level.getDifficulty() == Difficulty.HARD) && entity instanceof Villager villager) {
            if (EventHooks.canLivingConvert(entity, EntityType.ZOMBIE_VILLAGER, (timer) -> {
            })) {
                if (level.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
                    return flag;
                }
                ZombieVillager zombievillager = (ZombieVillager)villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
                if (zombievillager != null) {
                    zombievillager.finalizeSpawn(level, level.getCurrentDifficultyAt(zombievillager.blockPosition()), MobSpawnType.CONVERSION, new Zombie.ZombieGroupData(false, true));
                    zombievillager.setVillagerData(villager.getVillagerData());
                    zombievillager.setGossips((Tag)villager.getGossips().store(NbtOps.INSTANCE));
                    zombievillager.setTradeOffers(villager.getOffers().copy());
                    zombievillager.setVillagerXp(villager.getVillagerXp());
                    EventHooks.onLivingConvert(entity, zombievillager);
                    if (!this.isSilent()) {
                        level.levelEvent((Player)null, 1026, this.blockPosition(), 0);
                    }
                    flag = false;
                }
            }
        }
        return flag;
    }

    static {
        DOOR_BREAKING_PREDICATE = (p_34284_) -> p_34284_ == Difficulty.HARD;
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.updateAnimationStates();
        }
    }

    private void updateAnimationStates() {
        if (this.getTarget() == null && !this.swinging) {
            if (this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = 40;
                this.idleAnimationState.start(this.tickCount);
            } else {
                --this.idleAnimationTimeout;
            }
        } else {
            this.idleAnimationState.stop();
        }
        if (this.swinging) {
            this.attackAnimationState.startIfStopped(this.tickCount);
        } else {
            this.attackAnimationState.stop();
        }
        if (this.getTarget() != null && !this.swinging) {
            this.chasingAnimationState.startIfStopped(this.tickCount);
        } else {
            this.chasingAnimationState.stop();
        }
    }

    @Override
    public boolean isInvertedHealAndHarm() {
        return true;
    }
}