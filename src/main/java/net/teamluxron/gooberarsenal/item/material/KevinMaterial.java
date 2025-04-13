package net.teamluxron.gooberarsenal.item.material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.teamluxron.gooberarsenal.item.ModItems;

public class KevinMaterial implements Tier {
    public static final KevinMaterial INSTANCE = new KevinMaterial();

    @Override
    public int getUses() {
        return 1650;
    }

    @Override
    public float getSpeed() {
        return 9.0f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 6.0f;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return null;
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.KEVIN_SHARDS.get());
    }
}

