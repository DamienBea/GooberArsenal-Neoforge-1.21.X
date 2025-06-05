package net.teamluxron.gooberarsenal.item.custom.special;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.teamluxron.gooberarsenal.item.custom.weapon.PolearmItem;

public class LynnsDesolationItem extends PolearmItem {
    public LynnsDesolationItem(Tier tier, int damage, float speed, Properties props) {
        super(tier, damage, speed, props);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide()) {
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40));
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}