package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class BeeBunnyBasherItem extends BatItem {
    public BeeBunnyBasherItem(Tier tier, int attackDamageMod, float attackSpeedMod,
                              float horizontalKnockback, float verticalKnockback,
                              Properties properties) {
        super(tier, attackDamageMod, attackSpeedMod, horizontalKnockback, verticalKnockback, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide()) {
            target.addEffect(new MobEffectInstance(MobEffects.POISON, 40));
            target.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide() && entity instanceof Player player && selected) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 1, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 0, true, false));
        }
        super.inventoryTick(stack, level, entity, slot, selected);
    }
}