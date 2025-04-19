package net.teamluxron.gooberarsenal.registry;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.teamluxron.gooberarsenal.item.custom.RapierDamageSource;

public class ArmorBypass {
    public static void applyArmorBypassDamage(LivingEntity target, LivingEntity attacker, ItemStack stack, float amount) {
        // Create damage source that definitely bypasses armor
        DamageSource damageSource = RapierDamageSource.rapierDamage(attacker);

        // Apply damage directly to health
        if (target.isInvulnerableTo(damageSource)) {
            return;
        }

        float remainingHealth = target.getHealth() - amount;
        if (remainingHealth <= 0) {
            target.die(damageSource);
        } else {
            target.setHealth(remainingHealth);
        }
    }
}