package net.teamluxron.gooberarsenal.item.material;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ObsidianSwordMaterial implements Tier {
    public static final ObsidianSwordMaterial INSTANCE = new ObsidianSwordMaterial();

    @Override
    public int getUses() {
        return 2000;
    }

    @Override
    public float getSpeed() {
        return 8.0f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 8.0f;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return null;
    }

    @Override
    public int getEnchantmentValue() {
        return 20;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Blocks.OBSIDIAN);
    }
}

