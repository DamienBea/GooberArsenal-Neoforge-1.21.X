package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> KEVIN_SMELTABLES =
                List.of(
            ModBlocks.KEVIN_ORE,
            ModBlocks.DEEPSLATE_KEVIN_ORE
    );
        List<ItemLike> PLASTIC_SMELTABLES =
                List.of(
                        ModItems.PLASTIC_BAG
                );


    //Shaped Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_PLATE.get())
                .pattern("AAA")
                .define('A', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OBSIDIAN_HANDGUARD.get())
                .pattern("OCO")
                .define('O', Items.OBSIDIAN)
                .define('C', ModItems.CAGITE_INGOT.get())
                .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_PIPE.get())
                .pattern("II")
                .pattern("I ")
                .pattern("D ")
                .define('I', Items.IRON_INGOT)
                .define('D', Items.DRIED_KELP)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHAIR.get())
                .pattern("PPP")
                .pattern("PIP")
                .pattern("P P")
                .define('I', Items.IRON_INGOT)
                .define('P', ModItems.IRON_PLATE.get())
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FRYING_PAN.get())
                .pattern(" I ")
                .pattern("IPI")
                .pattern("DI ")
                .define('I', Items.IRON_INGOT)
                .define('P', ModItems.IRON_PLATE.get())
                .define('D', Items.DRIED_KELP)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_BAT.get())
                .pattern("  L")
                .pattern(" L ")
                .pattern("D  ")
                .define('L', ItemTags.LOGS)
                .define('D', Items.DRIED_KELP)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_BAT.get())
                .pattern("  L")
                .pattern(" L ")
                .pattern("D  ")
                .define('L', Items.IRON_INGOT)
                .define('D', Items.DRIED_KELP)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_BAT.get())
                .pattern("  L")
                .pattern(" L ")
                .pattern("D  ")
                .define('L', Items.GOLD_INGOT)
                .define('D', Items.DRIED_KELP)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_BAT.get())
                .pattern("  L")
                .pattern(" L ")
                .pattern("D  ")
                .define('L', Items.DIAMOND)
                .define('D', Items.DRIED_KELP)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOOBER_UPGRADE_TEMPLATE.get())
                .pattern("KKK")
                .pattern("KGK")
                .pattern("KNK")
                .define('K', ModItems.KEVIN_SHARDS.get())
                .define('G', ModItems.GOOBER_UPGRADE_TEMPLATE.get())
                .define('N', Items.NETHERRACK)
                .unlockedBy("has_goober_upgrade_template", has(ModItems.GOOBER_UPGRADE_TEMPLATE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPOON.get())
                .pattern("P")
                .pattern("I")
                .pattern("I")
                .define('I', Items.IRON_INGOT)
                .define('P', ModItems.IRON_PLATE.get())
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LIFE_SABER.get())
                .pattern("GKB")
                .pattern("KLK")
                .pattern("BKG")
                .define('L', ModItems.LIFE_SAVER.get())
                .define('K', ModBlocks.KEVIN_BLOCK.get())
                .define('B', Items.BLUE_DYE)
                .define('G', Items.GREEN_DYE)
                .unlockedBy("has_life_saver", has(ModItems.LIFE_SAVER.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KENDO_STICK.get())
                .pattern("P")
                .pattern("P")
                .pattern("I")
                .define('I', Items.STRING)
                .define('P', Items.BAMBOO)
                .unlockedBy("has_bamboo", has(Items.BAMBOO))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENERGY_BAR.get())
                .pattern("KCK")
                .pattern("SWS")
                .pattern("KEK")
                .define('K', ModBlocks.KEVIN_BLOCK.get())
                .define('C', Items.COOKIE)
                .define('S', Items.SUGAR)
                .define('E', Items.EGG)
                .define('W', Items.WHEAT)
                .unlockedBy("has_kevin_block", has(ModBlocks.KEVIN_BLOCK.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SANDVICH.get())
                .pattern(" B ")
                .pattern("CPS")
                .pattern(" B ")
                .define('B', Items.BREAD)
                .define('C', Items.COOKED_PORKCHOP)
                .define('P', Items.BAKED_POTATO)
                .define('S', Items.COOKED_BEEF)
                .unlockedBy("has_bread", has(Items.BREAD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHOCOLATE_CHIP_PANCAKES.get())
                .pattern("SCS")
                .pattern("WWW")
                .define('W', Items.WHEAT)
                .define('C', Items.COCOA_BEANS)
                .define('S', Items.SUGAR)
                .unlockedBy("has_wheat", has(Items.WHEAT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_APPLE.get())
                .pattern("CCC")
                .pattern("CAC")
                .pattern("CCC")
                .define('A', Items.APPLE)
                .define('C', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput);

        //Shapeless

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.KEVIN_BLOCK.get(), 1)
                .requires(ModItems.KEVIN_SHARDS.get(), 9)
                .unlockedBy(getHasName(ModItems.KEVIN_SHARDS.get()), has(ModItems.KEVIN_SHARDS.get()))
                .save(recipeOutput, "kevin_block_from_shard");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.KEVIN_SHARDS.get(), 9)
                .requires(ModBlocks.KEVIN_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.KEVIN_BLOCK.get()), has(ModBlocks.KEVIN_BLOCK.get()))
                .save(recipeOutput, "kevin_shards_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CAGITE_INGOT.get(), 1)
                .requires(ModItems.CAGITE_SCRAP.get(), 4)
                .requires(Items.GOLD_INGOT, 4)
                .unlockedBy(getHasName(ModItems.CAGITE_SCRAP.get()), has(ModItems.CAGITE_SCRAP.get()))
                .save(recipeOutput, "cagite_ingot_from_scrap");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CAGITE_BLOCK.get(), 1)
                .requires(ModItems.CAGITE_INGOT.get(), 9)
                .unlockedBy(getHasName(ModItems.CAGITE_INGOT.get()), has(ModItems.CAGITE_INGOT.get()))
                .save(recipeOutput, "cagite_block_from_ingots");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CAGITE_INGOT.get(), 9)
                .requires(ModBlocks.CAGITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CAGITE_BLOCK.get()), has(ModBlocks.CAGITE_BLOCK.get()))
                .save(recipeOutput, "cagite_ingot_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.OBSIDIAN_HILT.get(), 1)
                .requires(ModItems.OBSIDIAN_HANDGUARD.get(), 1)
                .requires(Items.BLAZE_ROD, 1)
                .unlockedBy(getHasName(ModItems.OBSIDIAN_HANDGUARD.get()), has(ModItems.OBSIDIAN_HANDGUARD.get()))
                .save(recipeOutput);



        //Smelting
        oreSmelting(recipeOutput, PLASTIC_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIC.get(), 0.25f, 200, "plastic");
        oreSmelting(recipeOutput, KEVIN_SMELTABLES, RecipeCategory.MISC, ModItems.KEVIN_SHARDS.get(), 0.25f, 200, "kevin");
        oreBlasting(recipeOutput, KEVIN_SMELTABLES, RecipeCategory.MISC, ModItems.KEVIN_SHARDS.get(), 0.25f, 100, "kevin");

        //Smithing
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.DIAMOND_HELMET),
                        Ingredient.of(ModItems.CAGITE_INGOT.get()),
                        RecipeCategory.COMBAT, ModItems.CAGITE_HELMET.get())
                .unlocks("has_cagite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAGITE_INGOT.get()))
                .save(recipeOutput, "cagite_helmet");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.DIAMOND_CHESTPLATE),
                        Ingredient.of(ModItems.CAGITE_INGOT.get()),
                        RecipeCategory.COMBAT, ModItems.CAGITE_CHESTPLATE.get())
                .unlocks("has_cagite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAGITE_INGOT.get()))
                .save(recipeOutput, "cagite_chesplate");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.DIAMOND_LEGGINGS),
                        Ingredient.of(ModItems.CAGITE_INGOT.get()),
                        RecipeCategory.COMBAT, ModItems.CAGITE_LEGGINGS.get())
                .unlocks("has_cagite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAGITE_INGOT.get()))
                .save(recipeOutput, "cagite_leggings");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.DIAMOND_BOOTS),
                        Ingredient.of(ModItems.CAGITE_INGOT.get()),
                        RecipeCategory.COMBAT, ModItems.CAGITE_BOOTS.get())
                .unlocks("has_cagite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAGITE_INGOT.get()))
                .save(recipeOutput, "cagite_boots");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_BAT.get()),
                        Ingredient.of(Items.BEEHIVE),
                        RecipeCategory.COMBAT, ModItems.BEE_BUNNY_BASHER.get())
                .unlocks("has_netherite_bat", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_BAT.get()))
                .save(recipeOutput, "bbb_from_hive");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_BAT.get()),
                        Ingredient.of(Items.BEE_NEST),
                        RecipeCategory.COMBAT, ModItems.BEE_BUNNY_BASHER.get())
                .unlocks("has_netherite_bat", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_BAT.get()))
                .save(recipeOutput, "bbb_from_nest");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_BAT.get()),
                        Ingredient.of(Items.RABBIT_FOOT),
                        RecipeCategory.COMBAT, ModItems.BEE_BUNNY_BASHER.get())
                .unlocks("has_netherite_bat", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_BAT.get()))
                .save(recipeOutput, "bbb_from_foot");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.OBSIDIAN_HILT.get()),
                        Ingredient.of(Items.LAVA_BUCKET),
                        RecipeCategory.COMBAT, ModItems.OBSIDIAN_SWORD.get())
                .unlocks("has_obsidian_hilt", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_HILT.get()))
                .save(recipeOutput, "obsidian_sword");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.DIAMOND_BAT.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT, ModItems.NETHERITE_BAT.get())
                .unlocks("has_diamond_bat", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_BAT.get()))
                .save(recipeOutput, "netherite_bat");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.STEEL_PIPE.get()),
                        Ingredient.of(ModItems.CAGITE_INGOT.get()),
                        RecipeCategory.COMBAT, ModItems.STAHP_SIGN.get())
                .unlocks("has_cagite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAGITE_INGOT.get()))
                .save(recipeOutput, "stahp_sign");

    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, GooberArsenal.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
