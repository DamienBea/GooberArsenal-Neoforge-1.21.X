package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class TungstenBatItem extends BatItem{
    public TungstenBatItem(Tier tier, int attackDamageMod, float attackSpeedMod, float horizontalKnockback, float verticalKnockback, Properties properties) {
        super(tier, attackDamageMod, attackSpeedMod, horizontalKnockback, verticalKnockback, properties);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

    public float getShieldDisableModifier(ItemStack stack, ItemStack shield) {
        return 0.5f; // Same value as vanilla axes
    }
}
