package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class DaggerItem extends DaggerBaseItem{
    public DaggerItem(Tier tier, int baseDamage, float attackSpeed, Properties properties) {
        super(tier, baseDamage, attackSpeed, properties);
    }


    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility ability) {
        if (ability == ItemAbilities.SWORD_SWEEP
        ) {
            return false;
        }
        return super.canPerformAction(stack, ability);
    }
}
