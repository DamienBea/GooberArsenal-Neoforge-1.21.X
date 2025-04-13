package net.teamluxron.gooberarsenal.item.material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.teamluxron.gooberarsenal.item.ModItems;

public class ChairMaterial implements Tier {
    public static final ChairMaterial INSTANCE = new ChairMaterial();

    @Override
    public int getUses() {
        return 10; // Very low durability
    }

    @Override
    public float getSpeed() {
        return 2.0f; // Mining speed
    }

    @Override
    public float getAttackDamageBonus() {
        return 8.0f; // High attack damage
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return null;
    }

    @Override
    public int getEnchantmentValue() {
        return 5; // Low enchantability
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.IRON_PLATE.get()); // Use your registered item here
    }
}

