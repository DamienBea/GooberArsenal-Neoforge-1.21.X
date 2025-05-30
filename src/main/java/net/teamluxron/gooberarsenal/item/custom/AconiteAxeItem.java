package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class AconiteAxeItem extends PolearmItem {
    public AconiteAxeItem(Tier tier, int damage, float speed, Properties props) {
        super(tier, damage, speed, props);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide()) {
            target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40));

            if (attacker instanceof Player player) {
                if (player.getAttackStrengthScale(0.5F) >= 1.0F) {
                    MobEffectInstance existingWither = target.getEffect(MobEffects.WITHER);
                    int amplifier = (existingWither != null)
                            ? Math.min(existingWither.getAmplifier() + 1, 2)
                            : 0;

                    target.addEffect(new MobEffectInstance(
                            MobEffects.WITHER,
                            80,
                            amplifier,
                            false,
                            true
                    ));
                }
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}