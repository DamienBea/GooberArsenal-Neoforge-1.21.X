package net.teamluxron.gooberarsenal.item.material;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.teamluxron.gooberarsenal.item.ModItems;

public class SoulphyreMaterial implements Tier {
    public static final SoulphyreMaterial INSTANCE = new SoulphyreMaterial();

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
        return null;
    }

    @Override
    public int getEnchantmentValue() {
        return 20; // enchantability
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.CAGITE_INGOT.get());
    }
}

