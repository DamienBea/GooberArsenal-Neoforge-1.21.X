package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;



public class HammerItem extends AreaMiningItem {
    public HammerItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }
}