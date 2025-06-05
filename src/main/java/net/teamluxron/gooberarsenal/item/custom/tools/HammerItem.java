package net.teamluxron.gooberarsenal.item.custom.tools;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.teamluxron.gooberarsenal.item.custom.coreitem.AreaMiningItem;


public class HammerItem extends AreaMiningItem {
    public HammerItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }
}