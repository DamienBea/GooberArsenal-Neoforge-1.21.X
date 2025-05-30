package net.teamluxron.gooberarsenal.item.material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.teamluxron.gooberarsenal.item.ModItems;

public class RoseQuartzMaterial implements Tier {

    public static final RoseQuartzMaterial INSTANCE = new RoseQuartzMaterial();

    @Override
    public int getUses() {
        return 1561;
    }

    @Override
    public float getSpeed() {
        return 8.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 3.0F;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return null;
    }

    public int getLevel() {
        return 3;
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.ROSE_QUARTZ.get());
    }
}