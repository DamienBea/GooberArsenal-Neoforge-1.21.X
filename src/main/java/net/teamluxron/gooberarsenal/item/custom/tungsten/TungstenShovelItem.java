package net.teamluxron.gooberarsenal.item.custom.tungsten;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.teamluxron.gooberarsenal.item.custom.coreitem.AreaMiningItem;

public class TungstenShovelItem extends AreaMiningItem {
    public TungstenShovelItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_SHOVEL, properties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility ability) {

        if    (
                ability == ItemAbilities.SHOVEL_DOUSE||
                ability == ItemAbilities.SHOVEL_FLATTEN) {
            return true;
        }

        return super.canPerformAction(stack, ability);
    }
}