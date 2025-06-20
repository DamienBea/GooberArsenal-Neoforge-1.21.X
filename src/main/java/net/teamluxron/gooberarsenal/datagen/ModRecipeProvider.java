package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.recipe.ForgingRecipe;

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
                        ModItems.PLASTIC_BAG,
                        ModItems.SWITCH_CARTRIDGE
                );
        List<ItemLike> CAGITE_SMELTING =
                List.of(
                        ModBlocks.ANCIENT_CAGITE
                );
        List<ItemLike> TUNGSTEN_SMELTING =
                List.of(
                        ModBlocks.SCALED_ENDSTONE
                );
        List<ItemLike> SOULPHYRE_SMELTING =
                List.of(
                        ModBlocks.SOULPHYRE_ORE
                );
        List<ItemLike> ROSE_QUARTZ_SMELTING =
                List.of(
                        ModBlocks.ROSE_QUARTZ_ORE
                );

        Ingredient mossIngredient = Ingredient.of(
                Items.MOSS_BLOCK,
                Items.FLOWERING_AZALEA,
                Items.AZALEA
        );

        Ingredient beeBunnyIngredient = Ingredient.of(
                Items.BEEHIVE,
                Items.BEE_NEST,
                Items.RABBIT_FOOT
        );


    //Shaped Recipes
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_SPIKED_BAT.get())
                .pattern(" D ")
                .pattern("DLD")
                .pattern(" D ")
                .define('L', ModItems.WOODEN_BAT)
                .define('D', Items.COBBLESTONE)
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_BAT.get())
                .pattern("  L")
                .pattern(" L ")
                .pattern("D  ")
                .define('L', Items.IRON_INGOT)
                .define('D', Items.DRIED_KELP)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput, "iron_bat_recipe");

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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOOBER_UPGRADE_TEMPLATE.get(), 2)
                .pattern("KKK")
                .pattern("KGK")
                .pattern("KNK")
                .define('K', ModItems.KEVIN_SHARDS.get())
                .define('G', ModItems.GOOBER_UPGRADE_TEMPLATE.get())
                .define('N', Items.NETHERRACK)
                .unlockedBy("has_goober_upgrade_template", has(ModItems.GOOBER_UPGRADE_TEMPLATE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TRANSFORMATION_TEMPLATE.get(), 2)
                .pattern("CCC")
                .pattern("SAS")
                .pattern("CCC")
                .define('S', ItemTags.TERRACOTTA)
                .define('A', Items.AMETHYST_SHARD)
                .define('C', Items.COPPER_INGOT)
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_SPOON.get())
                .pattern(" MM")
                .pattern(" MM")
                .pattern("M  ")
                .define('M', Items.OAK_PLANKS)
                .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_SPOON.get())
                .pattern(" MM")
                .pattern(" MM")
                .pattern("M  ")
                .define('M', Items.COBBLESTONE)
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_SPOON.get())
                .pattern(" MM")
                .pattern(" MM")
                .pattern("M  ")
                .define('M', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_SPOON.get())
                .pattern(" MM")
                .pattern(" MM")
                .pattern("M  ")
                .define('M', Items.GOLD_INGOT)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_SPOON.get())
                .pattern(" MM")
                .pattern(" MM")
                .pattern("M  ")
                .define('M', Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LIFE_SABER.get())
                .pattern("BKB")
                .pattern("KLK")
                .pattern("BKB")
                .define('L', ModItems.LIFE_SAVER.get())
                .define('K', ModBlocks.KEVIN_BLOCK.get())
                .define('B', ModItems.PLASTIC.get())
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

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_POLEARM.get())
                .pattern(" AA")
                .pattern("AAC")
                .pattern("A C")
                .define('A', ItemTags.PLANKS)
                .define('C', ModItems.POLE)
                .unlockedBy("has_planks", has(ModItems.POLE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_POLEARM.get())
                .pattern(" AA")
                .pattern("AAC")
                .pattern("A C")
                .define('A', Items.COBBLESTONE)
                .define('C', ModItems.POLE)
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_POLEARM.get())
                .pattern(" AA")
                .pattern("AAC")
                .pattern("A C")
                .define('A', Items.IRON_INGOT)
                .define('C', ModItems.POLE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_POLEARM.get())
                .pattern(" AA")
                .pattern("AAC")
                .pattern("A C")
                .define('A', Items.GOLD_INGOT)
                .define('C', ModItems.POLE)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_POLEARM.get())
                .pattern(" AA")
                .pattern("AAC")
                .pattern("A C")
                .define('A', Items.DIAMOND)
                .define('C', ModItems.POLE)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_SCYTHE.get())
                .pattern("AAA")
                .pattern("A C")
                .pattern("  C")
                .define('A', ItemTags.PLANKS)
                .define('C', ModItems.POLE)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_SCYTHE.get())
                .pattern("AAA")
                .pattern("A C")
                .pattern("  C")
                .define('A', Items.COBBLESTONE)
                .define('C', ModItems.POLE)
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_SCYTHE.get())
                .pattern("AAA")
                .pattern("A C")
                .pattern("  C")
                .define('A', Items.IRON_INGOT)
                .define('C', ModItems.POLE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_SCYTHE.get())
                .pattern("AAA")
                .pattern("A C")
                .pattern("  C")
                .define('A', Items.GOLD_INGOT)
                .define('C', ModItems.POLE)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_SCYTHE.get())
                .pattern("AAA")
                .pattern("A C")
                .pattern("  C")
                .define('A', Items.DIAMOND)
                .define('C', ModItems.POLE)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_DAGGER.get())
                .pattern("   ")
                .pattern(" A ")
                .pattern("C  ")
                .define('A', ItemTags.PLANKS)
                .define('C', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_DAGGER.get())
                .pattern("   ")
                .pattern(" A ")
                .pattern("C  ")
                .define('A', Items.COBBLESTONE)
                .define('C', Items.STICK)
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_DAGGER.get())
                .pattern("   ")
                .pattern(" A ")
                .pattern("C  ")
                .define('A', Items.IRON_INGOT)
                .define('C', Items.STICK)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_DAGGER.get())
                .pattern("   ")
                .pattern(" A ")
                .pattern("C  ")
                .define('A', Items.GOLD_INGOT)
                .define('C', Items.STICK)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_DAGGER.get())
                .pattern("   ")
                .pattern(" A ")
                .pattern("C  ")
                .define('A', Items.DIAMOND)
                .define('C', Items.STICK)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_HAMMER.get())
                .pattern("WWW")
                .pattern("WSW")
                .pattern(" S ")
                .define('W', ItemTags.PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_wood", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_HAMMER.get())
                .pattern("WWW")
                .pattern("WSW")
                .pattern(" S ")
                .define('W', Items.COBBLESTONE)
                .define('S', Items.STICK)
                .unlockedBy("has_cobble", has(Items.COBBLESTONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_HAMMER.get())
                .pattern("WWW")
                .pattern("WSW")
                .pattern(" S ")
                .define('W', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_HAMMER.get())
                .pattern("WWW")
                .pattern("WSW")
                .pattern(" S ")
                .define('W', Items.GOLD_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_HAMMER.get())
                .pattern("WWW")
                .pattern("WSW")
                .pattern(" S ")
                .define('W', Items.DIAMOND)
                .define('S', Items.STICK)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRAGON_SCALED_TUNGSTEN.get())
                .pattern("WWW")
                .pattern("WSW")
                .pattern("WWW")
                .define('S', Items.DIAMOND_BLOCK)
                .define('W', ModItems.DRAGON_SCALE_SHARD.get())
                .unlockedBy("has_diamond_block", has(Items.DIAMOND_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MOSSY_GEM.get())
                .pattern("AWA")
                .pattern("WSW")
                .pattern("AWA")
                .define('S', Items.SLIME_BALL)
                .define('A', Items.EMERALD)
                .define('W', mossIngredient)
                .unlockedBy("has_diamond_block", has(Items.DIAMOND_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FORGING_ANVIL.get())
                .pattern("III")
                .pattern("IAI")
                .pattern("LLL")
                .define('I', Items.IRON_INGOT)
                .define('A', Items.ANVIL)
                .define('L', ItemTags.LOGS)
                .unlockedBy("has_anvil", has(Items.ANVIL))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RADIO.get())
                .pattern("SPS")
                .pattern("SJS")
                .pattern("SRS")
                .define('S', ModItems.IRON_PLATE)
                .define('J', Items.JUKEBOX)
                .define('P', ModItems.PLASTIC)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_iron_plate", has(ModItems.IRON_PLATE))
                .save(recipeOutput, "radio_from_crafting");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POLE.get())
                .pattern("  I")
                .pattern(" I ")
                .pattern("I  ")
                .define('I', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEVENS_JACKET.get())
                .pattern("PXP")
                .pattern("RYR")
                .pattern("PRP")
                .define('X', Items.DIAMOND_CHESTPLATE)
                .define('P', Items.PINK_WOOL)
                .define('Y', Items.YELLOW_WOOL)
                .define('R', ModItems.ROSE_QUARTZ)
                .unlockedBy("has_rose_quartz", has(ModItems.ROSE_QUARTZ))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOULPHYRE_BAT.get())
                .pattern("  S")
                .pattern(" S ")
                .pattern("D  ")
                .define('D', Items.DRIED_KELP)
                .define('S', ModItems.SOULPHYRE)
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOULPHYRE_HAMMER.get())
                .pattern("SSS")
                .pattern("SDS")
                .pattern(" D ")
                .define('D', Items.STICK)
                .define('S', ModItems.SOULPHYRE)
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOULPHYRE_SCYTHE.get())
                .pattern("SSS")
                .pattern("S D")
                .pattern("  D")
                .define('D', ModItems.POLE)
                .define('S', ModItems.SOULPHYRE)
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOULPHYRE_BOOTS.get())
                .pattern("S S")
                .pattern("S S")
                .define('S', ModItems.SOULPHYRE)
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOULPHYRE_LEGGINGS.get())
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', ModItems.SOULPHYRE)
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOULPHYRE_CHESTPLATE.get())
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SOULPHYRE)
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOULPHYRE_HELMET.get())
                .pattern("SSS")
                .pattern("S S")
                .define('S', ModItems.SOULPHYRE)
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOULPHYRE_POLEARM.get())
                .pattern(" SS")
                .pattern("SSD")
                .pattern("S D")
                .define('D', ModItems.POLE)
                .define('S', ModItems.SOULPHYRE)
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOULPHYRE_DAGGER.get())
                .pattern("   ")
                .pattern(" S ")
                .pattern("D  ")
                .define('D', Items.STICK)
                .define('S', ModItems.SOULPHYRE)
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ROSE_QUARTZ_SWORD.get())
                .pattern(" S ")
                .pattern(" S ")
                .pattern(" D ")
                .define('D', ModItems.POLE)
                .define('S', ModItems.ROSE_QUARTZ.get())
                .unlockedBy("has_rose_quarty", has(ModItems.ROSE_QUARTZ.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SOULPHYRE_SWORD.get())
                .pattern(" S ")
                .pattern(" S ")
                .pattern(" D ")
                .define('D', Items.STICK)
                .define('S', ModItems.SOULPHYRE.get())
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SOULPHYRE_PICKAXE.get())
                .pattern("SSS")
                .pattern(" D ")
                .pattern(" D ")
                .define('D', Items.STICK)
                .define('S', ModItems.SOULPHYRE.get())
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SOULPHYRE_SHOVEL.get())
                .pattern(" S ")
                .pattern(" D ")
                .pattern(" D ")
                .define('D', Items.STICK)
                .define('S', ModItems.SOULPHYRE.get())
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SOULPHYRE_AXE.get())
                .pattern("SS ")
                .pattern("SD ")
                .pattern(" D ")
                .define('D', Items.STICK)
                .define('S', ModItems.SOULPHYRE.get())
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SOULPHYRE_HOE.get())
                .pattern("SS ")
                .pattern(" D ")
                .pattern(" D ")
                .define('D', Items.STICK)
                .define('S', ModItems.SOULPHYRE.get())
                .unlockedBy("has_soulphyre", has(ModItems.SOULPHYRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ROSE_QUARTZ_SHIELD.get())
                .pattern("SSS")
                .pattern("SAS")
                .pattern("SSS")
                .define('S', ModItems.ROSE_QUARTZ.get())
                .define('A', Items.SHIELD)
                .unlockedBy("has_rose_quartz", has(ModItems.ROSE_QUARTZ.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.FIELD_SWORD.asItem(), 4)
                .pattern(" S ")
                .pattern("ATA")
                .pattern("CCC")
                .define('C', Items.CHISELED_STONE_BRICKS)
                .define('A', Items.AMETHYST_SHARD)
                .define('S', ModItems.STONE_GREATSWORD.get())
                .define('T', ModItems.TRANSFORMATION_TEMPLATE.get())
                .unlockedBy("has_chiseled_stone", has(Items.CHISELED_STONE_BRICKS))
                .save(recipeOutput, "field_sword_block_from_crafting");



        //Shapeless

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.KEVIN_BLOCK.get(), 1)
                .requires(ModItems.KEVIN_SHARDS.get(), 9)
                .unlockedBy(getHasName(ModItems.KEVIN_SHARDS.get()), has(ModItems.KEVIN_SHARDS.get()))
                .save(recipeOutput, "kevin_block_from_shard");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.KEVIN_SHARDS.get(), 9)
                .requires(ModBlocks.KEVIN_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.KEVIN_BLOCK.get()), has(ModBlocks.KEVIN_BLOCK.get()))
                .save(recipeOutput, "kevin_shards_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.SOULPHYRE_BLOCK.get(), 1)
                .requires(ModItems.SOULPHYRE.get(), 9)
                .unlockedBy(getHasName(ModItems.SOULPHYRE.get()), has(ModItems.SOULPHYRE.get()))
                .save(recipeOutput, "soulphyre_block_from_souphyre");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SOULPHYRE.get(), 9)
                .requires(ModBlocks.SOULPHYRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SOULPHYRE_BLOCK.get()), has(ModBlocks.SOULPHYRE_BLOCK.get()))
                .save(recipeOutput, "soulphyre_from_soulphyre_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ROSE_QUARTZ_BLOCK.get(), 1)
                .requires(ModItems.ROSE_QUARTZ.get(), 9)
                .unlockedBy(getHasName(ModItems.ROSE_QUARTZ.get()), has(ModItems.ROSE_QUARTZ.get()))
                .save(recipeOutput, "rose_quartz_block_from_rose_quart");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ROSE_QUARTZ.get(), 9)
                .requires(ModBlocks.ROSE_QUARTZ_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROSE_QUARTZ_BLOCK.get()), has(ModBlocks.ROSE_QUARTZ_BLOCK.get()))
                .save(recipeOutput, "rose_quartz_from_rose_quartz_block");

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.DRAGON_SCALED_TUNGSTEN_BLOCK.get(), 1)
                .requires(ModItems.DRAGON_SCALED_TUNGSTEN.get(), 9)
                .unlockedBy(getHasName(ModItems.DRAGON_SCALED_TUNGSTEN.get()), has(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "dragon_scaled_tungsten_block_from_ingot");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DRAGON_SCALED_TUNGSTEN.get(), 9)
                .requires(ModBlocks.DRAGON_SCALED_TUNGSTEN_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.DRAGON_SCALED_TUNGSTEN_BLOCK.get()), has(ModBlocks.DRAGON_SCALED_TUNGSTEN_BLOCK.get()))
                .save(recipeOutput, "dragon_scaled_tungsten_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.OBSIDIAN_HILT.get(), 1)
                .requires(ModItems.OBSIDIAN_HANDGUARD.get(), 1)
                .requires(Items.BLAZE_ROD, 1)
                .unlockedBy(getHasName(ModItems.OBSIDIAN_HANDGUARD.get()), has(ModItems.OBSIDIAN_HANDGUARD.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.RADIO.get(), 1)
                .requires(ModItems.IRON_PLATE.get(), 1)
                .requires(ModBlocks.BROKEN_RADIO.get(), 1)
                .unlockedBy(getHasName(ModBlocks.BROKEN_RADIO.get()), has(ModItems.IRON_PLATE.get()))
                .save(recipeOutput, "radio_from_repair");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.THORN_OF_THE_DEAD_GODS.get(), 1)
                .requires(ModItems.THORN_OF_ZAZIKEL.get(), 1)
                .requires(ModItems.THORN_OF_ANDORAL.get(), 1)
                .requires(ModItems.THORN_OF_TOTH.get(), 1)
                .requires(ModItems.NETHERITE_DAGGER.get(), 1)
                .unlockedBy(getHasName(ModItems.NETHERITE_DAGGER.get()), has(ModItems.NETHERITE_DAGGER.get()))
                .save(recipeOutput);






        //Smelting
        oreSmelting(recipeOutput, PLASTIC_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIC.get(), 0.25f, 200, "plastic");
        oreSmelting(recipeOutput, CAGITE_SMELTING, RecipeCategory.MISC, ModItems.CAGITE_SCRAP.get(), 0.25f, 200, "cagite_scrap_smelting");
        oreBlasting(recipeOutput, CAGITE_SMELTING, RecipeCategory.MISC, ModItems.CAGITE_SCRAP.get(), 0.25f, 200, "cagite_scrap_blasting");
        oreSmelting(recipeOutput, TUNGSTEN_SMELTING, RecipeCategory.MISC, ModItems.DRAGON_SCALE_SHARD.get(), 0.25f, 200, "scale_smelting");
        oreBlasting(recipeOutput, TUNGSTEN_SMELTING, RecipeCategory.MISC, ModItems.DRAGON_SCALE_SHARD.get(), 0.25f, 200, "scale_scrap_blasting");
        oreSmelting(recipeOutput, KEVIN_SMELTABLES, RecipeCategory.MISC, ModItems.KEVIN_SHARDS.get(), 0.25f, 200, "kevin_smelting");
        oreBlasting(recipeOutput, KEVIN_SMELTABLES, RecipeCategory.MISC, ModItems.KEVIN_SHARDS.get(), 0.25f, 100, "kevin_blasting");
        oreSmelting(recipeOutput, SOULPHYRE_SMELTING, RecipeCategory.MISC, ModItems.SOULPHYRE.get(), 0.25f, 200, "soulphyre_smelting");
        oreBlasting(recipeOutput, SOULPHYRE_SMELTING, RecipeCategory.MISC, ModItems.SOULPHYRE.get(), 0.25f, 200, "soulphyre_blasting");
        oreSmelting(recipeOutput, ROSE_QUARTZ_SMELTING, RecipeCategory.MISC, ModItems.ROSE_QUARTZ.get(), 0.25f, 200, "rose_quartz_smelting");
        oreBlasting(recipeOutput, ROSE_QUARTZ_SMELTING, RecipeCategory.MISC, ModItems.ROSE_QUARTZ.get(), 0.25f, 200, "rose_quartz_blasting");




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
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_HELMET.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_helmet");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_CHESTPLATE.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_chesplate");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_LEGGINGS.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_leggings");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_BOOTS.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_boots");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_BAT),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_BAT.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_bat");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_POLEARM),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_POLEARM.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_polearm");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_SCYTHE),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_SCYTHE.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_scythe");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_DAGGER),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_DAGGER.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_dagger");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_HAMMER),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_HAMMER.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_hammer");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_SWORD),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_SWORD.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_sword");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_PICKAXE),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_PICKAXE.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_pickaxe");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_SHOVEL),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_SHOVEL.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_shovel");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_AXE),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_AXE.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_axe");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_HOE),
                        Ingredient.of(ModItems.DRAGON_SCALED_TUNGSTEN.get()),
                        RecipeCategory.COMBAT, ModItems.TUNGSTEN_HOE.get())
                .unlocks("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_SCALED_TUNGSTEN.get()))
                .save(recipeOutput, "tungsten_hoe");

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                Ingredient.of(ModItems.NETHERITE_BAT.get()),
                beeBunnyIngredient,
                RecipeCategory.COMBAT,
                ModItems.BEE_BUNNY_BASHER.get())
                .unlocks("has_netherite_bat", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_BAT.get()))
                .save(recipeOutput, "bee_bunny_basher");

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

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.DIAMOND_AXE),
                        Ingredient.of(Items.CAKE),
                        RecipeCategory.COMBAT, ModItems.FESTIVE_AXE.get())
                .unlocks("has_diamond_axe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_AXE))
                .save(recipeOutput, "festive_axe");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_SWORD),
                        Ingredient.of(ModItems.RUBBER_CHICKEN.get()),
                        RecipeCategory.COMBAT, ModItems.SLAPSTICK_SWORD.get())
                .unlocks("has_rubber_chicken", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RUBBER_CHICKEN.get()))
                .save(recipeOutput, "slapstick_sword");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.TRANSFORMATION_TEMPLATE.get()),
                        Ingredient.of(ModItems.IRON_DAGGER.get()),
                        Ingredient.of(ModItems.SWITCH_CARTRIDGE.get()),
                        RecipeCategory.COMBAT, ModItems.SWITCH_BLADE.get())
                .unlocks("has_switch_cart", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SWITCH_CARTRIDGE.get()))
                .save(recipeOutput, "switch_blade");


        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.SOULPHYRE_DAGGER.get()),
                        Ingredient.of(ModItems.CHAIN_OF_FATE.get()),
                        RecipeCategory.COMBAT, ModItems.REBELS_KNIFE.get())
                .unlocks("has_chain_of_fate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CHAIN_OF_FATE.get()))
                .save(recipeOutput, "rebels_knife");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_DAGGER.get()),
                        Ingredient.of(ModItems.VENOMOUS_FANG.get()),
                        RecipeCategory.COMBAT, ModItems.POISONERS_SIDEARM.get())
                .unlocks("has_venomuos_fang", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.VENOMOUS_FANG.get()))
                .save(recipeOutput, "poisoners_sidearm");


        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.DIAMOND_SCYTHE.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT, ModItems.NETHERITE_SCYTHE.get())
                .unlocks("has_diamond_scythe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_SCYTHE.get()))
                .save(recipeOutput, "netherite_scythe");


        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.DIAMOND_SPOON.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT, ModItems.NETHERITE_SPOON.get())
                .unlocks("has_diamond_spoon", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_SPOON.get()))
                .save(recipeOutput, "netherite_spoon");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.DIAMOND_POLEARM.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT, ModItems.NETHERITE_POLEARM.get())
                .unlocks("has_diamond_polearm", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_POLEARM.get()))
                .save(recipeOutput, "netherite_polearm");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_SCYTHE.get()),
                        Ingredient.of(ModItems.GLEAMING_RED_EYE.get()),
                        RecipeCategory.COMBAT, ModItems.RED_EYES_DREAM.get())
                .unlocks("has_gleaming_red_eye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GLEAMING_RED_EYE.get()))
                .save(recipeOutput, "red_eyes_dream_smithing");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_POLEARM.get()),
                        Ingredient.of(ModItems.OBSIDIAN_ROSE.get()),
                        RecipeCategory.COMBAT, ModItems.LYNNS_DESOLATION.get())
                .unlocks("has_obsidian_rose", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_ROSE.get()))
                .save(recipeOutput, "lynns_desolation_smithing");


        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_POLEARM.get()),
                        Ingredient.of(ModItems.ACONITE.get()),
                        RecipeCategory.COMBAT, ModItems.ACONITE_AXE.get())
                .unlocks("has_aconite", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ACONITE.get()))
                .save(recipeOutput, "aconite_axe_smithing");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.DIAMOND_DAGGER.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT, ModItems.NETHERITE_DAGGER.get())
                .unlocks("has_diamond_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_DAGGER.get()))
                .save(recipeOutput, "netherite_dagger_smithing");


        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.DIAMOND_HAMMER.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT, ModItems.NETHERITE_HAMMER.get())
                .unlocks("has_diamond_hammer", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_HAMMER.get()))
                .save(recipeOutput, "netherite_hammer");

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(ModItems.GOOBER_UPGRADE_TEMPLATE),
                        Ingredient.of(ModItems.NETHERITE_HAMMER.get()),
                        Ingredient.of(ModItems.MOSSY_GEM.get()),
                RecipeCategory.COMBAT,
                ModItems.MOSSY_MASHER.get())
                .unlocks("has_netherite_hammer",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_HAMMER.get()))
                .save(recipeOutput, "mossy_masher");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.TRANSFORMATION_TEMPLATE.get()),
                        Ingredient.of(Items.SHIELD),
                        Ingredient.of(Items.GOLD_INGOT),
                        RecipeCategory.COMBAT,
                        ModItems.ALLOY_SHIELD.get())
                .unlocks("has_shield",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.SHIELD))
                .save(recipeOutput, "alloy_shield_smithing");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.TRANSFORMATION_TEMPLATE.get()),
                        Ingredient.of(Items.SHIELD),
                        Ingredient.of(Items.IRON_DOOR),
                        RecipeCategory.COMBAT,
                        ModItems.DOOR_SHIELD.get())
                .unlocks("has_shield",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.SHIELD))
                .save(recipeOutput, "door_shield_smithing");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.TRANSFORMATION_TEMPLATE.get()),
                        Ingredient.of(Items.SHIELD),
                        Ingredient.of(ItemTags.PLANKS),
                        RecipeCategory.COMBAT,
                        ModItems.PALETTE.get())
                .unlocks("has_shield",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.SHIELD))
                .save(recipeOutput, "pallete_shield_smithing");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.TRANSFORMATION_TEMPLATE.get()),
                        Ingredient.of(Items.SHIELD),
                        Ingredient.of(Items.MOSSY_COBBLESTONE),
                        RecipeCategory.COMBAT,
                        ModItems.GRASS_CREST_SHIELD.get())
                .unlocks("has_shield",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.SHIELD))
                .save(recipeOutput, "grass_crest_shield_smithing");


        //Forging

        registerForgingRecipe(recipeOutput, Items.IRON_INGOT, ModItems.IRON_PLATE.get(), 1);
        registerForgingRecipe(recipeOutput, ModBlocks.RADIO, ModBlocks.BROKEN_RADIO.get(), 1);
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
    private void registerForgingRecipe(RecipeOutput output, ItemLike input, ItemLike result, int count) {
        output.accept(
                ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID,
                        "forging/" + BuiltInRegistries.ITEM.getKey(result.asItem()).getPath()),
                new ForgingRecipe(
                        Ingredient.of(input),  // Input item
                        new ItemStack(result, count)  // Output stack
                ),
                null  // No advancement
        );
    }
}
