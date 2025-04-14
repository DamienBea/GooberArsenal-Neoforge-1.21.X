package net.teamluxron.gooberarsenal.item.custom;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;


import java.util.UUID;

public class EndersScrutinyItem extends SwordItem {

    public EndersScrutinyItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Check if it was a critical hit
        if (attacker instanceof Player player && player.fallDistance > 0.0F && !player.onGround()
                && !player.onClimbable() && !player.isInWater() && !player.hasEffect(MobEffects.BLINDNESS)
                && !player.isPassenger()) {

            // If the target is blocking with a shield
            if (target instanceof Player targetPlayer && targetPlayer.isBlocking()) {
                // Disable the shield for 5 seconds (100 ticks) like axes do
                targetPlayer.getCooldowns().addCooldown(targetPlayer.getUseItem().getItem(), 100);
                targetPlayer.stopUsingItem();  // Force stop blocking
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }
}