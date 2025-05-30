package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class LynnsDesolationItem extends PolearmItem {
    public LynnsDesolationItem(Tier tier, int baseDamage, float attackSpeed,
                               double rangeBonus, double blockRangeBonus,
                               Item.Properties properties) {
        super(tier, baseDamage, attackSpeed, rangeBonus, blockRangeBonus, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide()) {
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40));
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}