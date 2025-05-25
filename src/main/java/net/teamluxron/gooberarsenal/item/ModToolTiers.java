package net.teamluxron.gooberarsenal.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;
import net.teamluxron.gooberarsenal.util.ModTags;

public class ModToolTiers {
    public static final Tier CAGITE_TIER = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_CAGITE_TOOL,
            2000, 4f, 3f, 28, () -> Ingredient.of(ModItems.CAGITE_INGOT));

    public static final Tier SOULPHYRE_TIER = new SimpleTier(Tags.Blocks.NEEDS_GOLD_TOOL,
            1561, 8.0f, 3.0f, 10, () -> Ingredient.of(ModItems.SOULPHYRE));

    public static final Tier TUNGSTEN_TIER = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            3046, 12.0f, 5.5f, 20, () -> Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN));

}
