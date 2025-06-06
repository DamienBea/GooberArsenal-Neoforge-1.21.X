package net.teamluxron.gooberarsenal.item.material;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.teamluxron.gooberarsenal.item.ModItems;

public class TungstenMaterial implements Tier {
    public static final TungstenMaterial INSTANCE = new TungstenMaterial();

    @Override
    public int getUses() {
        return 2000; // durability
    }

    @Override
    public float getSpeed() {
        return 8.0f; // mining speed
    }

    @Override
    public float getAttackDamageBonus() {
        return 8.0f; // attack damage bonus
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {

        return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
    }

    @Override
    public int getEnchantmentValue() {
        return 20; // enchantability
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get());
    }
}

