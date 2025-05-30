package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;

public class TungstenShovelItem extends AreaMiningItem {
    public TungstenShovelItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_SHOVEL, properties);
    }
}