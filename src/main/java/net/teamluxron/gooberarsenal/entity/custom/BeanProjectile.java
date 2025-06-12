package net.teamluxron.gooberarsenal.entity.custom;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.teamluxron.gooberarsenal.entity.ModEntities;
import net.teamluxron.gooberarsenal.item.ModItems;

public class BeanProjectile extends ThrowableItemProjectile implements ItemSupplier {
    private static final float DAMAGE = 3.0f;

    public BeanProjectile(EntityType<? extends BeanProjectile> type, Level world) {
        super(type, world);
    }

    public BeanProjectile(Level world, LivingEntity shooter) {
        super(ModEntities.BEAN_PROJECTILE.get(), shooter, world);
    }

    public BeanProjectile(Level world, double x, double y, double z) {
        super(ModEntities.BEAN_PROJECTILE.get(), x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        // This is what the projectile will look like in‐flight
        return ModItems.MAGICAL_BEAN_SEED.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (result.getEntity() instanceof LivingEntity living) {
            DamageSource src = this.damageSources().thrown(this, this.getOwner());
            living.hurt(src, DAMAGE);
        }
        // discard after hitting
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this, 0, this.blockPosition());
    }


    @Override
    public ItemStack getItem() {
        return new ItemStack(ModItems.MAGICAL_BEAN_SEED.get());
    }
}
