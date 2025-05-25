package net.teamluxron.gooberarsenal.item.material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.teamluxron.gooberarsenal.item.ModItems;

import java.util.List;

public class SoulphyreMaterial implements Tier {
    public static final SoulphyreMaterial INSTANCE = new SoulphyreMaterial();

    @Override
    public int getUses() {
        return 1561; // durability
    }

    @Override
    public float getSpeed() {
        return 8.0f; // mining speed
    }

    @Override
    public float getAttackDamageBonus() {
        return 3.0f; // attack damage bonus
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.SOULPHYRE.get());
    }
}

