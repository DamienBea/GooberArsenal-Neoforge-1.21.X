package net.teamluxron.gooberarsenal.item.custom.special;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.teamluxron.gooberarsenal.item.custom.weapon.GooberShield;

import java.util.List;

public class RoseQuartzShield extends GooberShield {

    public RoseQuartzShield(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlock(Level level, LivingEntity entity, ItemStack shieldStack) {
        if (!level.isClientSide()) {
            doKnockbackBlast(entity, level, 2.0F);
            level.playSound(null, entity.blockPosition(), SoundEvents.WIND_CHARGE_BURST.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @Override
    public void onShieldDisabled(Level level, LivingEntity entity, ItemStack shieldStack) {
        if (!level.isClientSide()) {
            doKnockbackBlast(entity, level, 4.0F);
            level.playSound(null, entity.blockPosition(), SoundEvents.WIND_CHARGE_BURST.value(), SoundSource.PLAYERS, 1.0F, 0.8F);
        }
    }

    private void doKnockbackBlast(LivingEntity user, Level level, float strength) {
        double radius = 4.0D;
        AABB area = new AABB(user.blockPosition()).inflate(radius);
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area, e -> e != user && e.isAlive());

        for (LivingEntity target : entities) {
            double dx = target.getX() - user.getX();
            double dz = target.getZ() - user.getZ();
            double dist = Math.sqrt(dx * dx + dz * dz);

            if (dist != 0.0D) {
                double scale = strength / dist;
                target.push(dx * scale, 0.25D * strength, dz * scale);
            }
        }
    }
}