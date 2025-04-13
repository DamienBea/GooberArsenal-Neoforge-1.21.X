package net.teamluxron.gooberarsenal.item.material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class KendoStickMaterial implements Tier {
    public static final KendoStickMaterial INSTANCE = new KendoStickMaterial();

    @Override
    public int getUses() {
        return 3; // Extremely fragile
    }

    @Override
    public float getSpeed() {
        return 2.0f; // Mining speed
    }

    @Override
    public float getAttackDamageBonus() {
        return 8.0f; // High damage for a joke weapon
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
        return Ingredient.of(Items.BAMBOO); // Uses vanilla bamboo for repair
    }
}
