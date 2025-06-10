package net.teamluxron.gooberarsenal.entity.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.teamluxron.gooberarsenal.entity.ModEntities;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.particles.ModParticles;

public class BeanProjectile extends ThrowableItemProjectile {
    private static final float DAMAGE = 3.0f;

    public BeanProjectile(EntityType<? extends BeanProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.03, 0.0));
        }
        this.setYRot(getYRot() + 10);

        if (this.level().isClientSide) {
            for (int i = 0; i < 2; i++) {
                double offsetX = (random.nextDouble() - 0.5) * 0.1;
                double offsetY = (random.nextDouble() - 0.5) * 0.1;
                double offsetZ = (random.nextDouble() - 0.5) * 0.1;

                this.level().addParticle(ModParticles.BEAN_PARTICLE.get(),
                        this.getX() + offsetX,
                        this.getY() + offsetY,
                        this.getZ() + offsetZ,
                        0, 0, 0);
            }
        }
        if (this.tickCount > 200) {
            this.discard();
        }
    }

    public BeanProjectile(Level level, LivingEntity shooter) {
        super(ModEntities.BEAN_PROJECTILE.get(), shooter, level);
    }

    public BeanProjectile(Level level, double x, double y, double z) {
        super(ModEntities.BEAN_PROJECTILE.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.MAGICAL_BEAN_SEED.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        if (!(entity instanceof LivingEntity living)) return;

        Entity owner = this.getOwner();
        DamageSource source = this.damageSources().thrown(this, owner);

        living.hurt(source, DAMAGE);

        if (owner instanceof LivingEntity) {
            living.knockback(0.5f,
                    owner.getX() - living.getX(),
                    owner.getZ() - living.getZ());
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(ModParticles.BEAN_PARTICLE.get(),
                        this.getX(), this.getY(), this.getZ(),
                        0.0, 0.0, 0.0);
            }
        } else {
            super.handleEntityEvent(id);
        }
    }
}