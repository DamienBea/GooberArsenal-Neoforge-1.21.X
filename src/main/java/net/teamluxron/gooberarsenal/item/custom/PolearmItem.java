package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class PolearmItem extends AxeItem {

    public PolearmItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player && player.fallDistance > 0.0F && !player.onGround()
                && !player.onClimbable() && !player.isInWater() && !player.hasEffect(MobEffects.BLINDNESS)
                && !player.isPassenger()) {

            if (target instanceof Player targetPlayer && targetPlayer.isBlocking()) {
                targetPlayer.getCooldowns().addCooldown(targetPlayer.getUseItem().getItem(), 100);
                targetPlayer.stopUsingItem();
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
