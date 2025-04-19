package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.teamluxron.gooberarsenal.registry.ArmorBypass;

public class RapierItem extends SwordItem {
    public RapierItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Get base damage before armor reduction
        float damage = (float) attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);

        // Directly reduce health, bypassing armor calculations
        float remainingHealth = target.getHealth() - damage;
        if (remainingHealth <= 0) {
            target.die(RapierDamageSource.rapierDamage(attacker));
        } else {
            target.setHealth(remainingHealth);
        }

        // Still trigger weapon effects
        stack.hurtEnemy(target, (Player) attacker);
        return true;
    }


}