package net.teamluxron.gooberarsenal.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.teamluxron.gooberarsenal.util.ModTags;

public class ModToolTiers {
    public static final Tier CAGITE_TIER = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_CAGITE_TOOL,
            1400, 4f, 3f, 28, () -> Ingredient.of(ModItems.CAGITE_INGOT));

}
