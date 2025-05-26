package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.item.ModItems;

import java.util.LinkedHashMap;


public class ModItemModelProvider extends ItemModelProvider {
        private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

        static {
            trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
            trimMaterials.put(TrimMaterials.IRON, 0.2F);
            trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
            trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
            trimMaterials.put(TrimMaterials.COPPER, 0.5F);
            trimMaterials.put(TrimMaterials.GOLD, 0.6F);
            trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
            trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
            trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
            trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
        }

        public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
            super(output, GooberArsenal.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {

            //Basic Item Model
            basicItem(ModItems.OBSIDIAN_HILT.get());
            basicItem(ModItems.OBSIDIAN_HANDGUARD.get());
            basicItem(ModItems.GOOBER_UPGRADE_TEMPLATE.get());
            basicItem(ModItems.TRANSFORMATION_TEMPLATE.get());
            basicItem(ModItems.CAGITE_SCRAP.get());
            basicItem(ModItems.CAGITE_INGOT.get());
            basicItem(ModItems.DRAGON_SCALE_SHARD.get());
            basicItem(ModItems.DRAGON_SCALED_TUNGSTEN.get());
            basicItem(ModItems.KEVIN_SHARDS.get());
            basicItem(ModItems.IRON_PLATE.get());
            basicItem(ModItems.LIFE_SAVER.get());
            basicItem(ModItems.RUBBER_CHICKEN.get());
            basicItem(ModItems.ENERGY_BAR.get());
            basicItem(ModItems.SANDVICH.get());
            basicItem(ModItems.CHOCOLATE_CHIP_PANCAKES.get());
            basicItem(ModItems.COPPER_APPLE.get());
            basicItem(ModItems.SWITCH_CARTRIDGE.get());
            basicItem(ModItems.PLASTIC.get());
            basicItem(ModItems.PLASTIC_BAG.get());
            basicItem(ModItems.OBSIDIAN_ROSE.get());
            basicItem(ModItems.GLEAMING_RED_EYE.get());
            basicItem(ModItems.THORN_OF_ZAZIKEL.get());
            basicItem(ModItems.THORN_OF_TOTH.get());
            basicItem(ModItems.THORN_OF_ANDORAL.get());
            basicItem(ModItems.MOSSY_GEM.get());
            basicItem(ModItems.CHAIN_OF_FATE.get());
            basicItem(ModItems.VENOMOUS_FANG.get());
            basicItem(ModItems.ACONITE.get());
            basicItem(ModItems.SOULPHYRE.get());
            basicItem(ModItems.ROSE_QUARTZ.get());


            //Armor

            trimmedArmorItem(ModItems.SOULPHYRE_HELMET);
            trimmedArmorItem(ModItems.SOULPHYRE_CHESTPLATE);
            trimmedArmorItem(ModItems.SOULPHYRE_LEGGINGS);
            trimmedArmorItem(ModItems.SOULPHYRE_BOOTS);

            trimmedArmorItem(ModItems.CAGITE_HELMET);
            trimmedArmorItem(ModItems.CAGITE_CHESTPLATE);
            trimmedArmorItem(ModItems.CAGITE_LEGGINGS);
            trimmedArmorItem(ModItems.CAGITE_BOOTS);

            trimmedArmorItem(ModItems.TUNGSTEN_HELMET);
            trimmedArmorItem(ModItems.TUNGSTEN_CHESTPLATE);
            trimmedArmorItem(ModItems.TUNGSTEN_LEGGINGS);
            trimmedArmorItem(ModItems.TUNGSTEN_BOOTS);

            trimmedArmorItem(ModItems.STEVENS_JACKET);

            //Weapons

            handheldItem(ModItems.FRYING_PAN);
            handheldItem(ModItems.STEEL_PIPE);
            handheldItem(ModItems.CHAIR);
            handheldItem(ModItems.BEE_BUNNY_BASHER);
            handheldScaledItem(ModItems.STAHP_SIGN);
            handheldItem(ModItems.WOODEN_BAT);
            handheldItem(ModItems.STONE_SPIKED_BAT);
            handheldItem(ModItems.IRON_BAT);
            handheldItem(ModItems.GOLDEN_BAT);
            handheldItem(ModItems.DIAMOND_BAT);
            handheldItem(ModItems.SOULPHYRE_BAT);
            handheldItem(ModItems.NETHERITE_BAT);
            handheldItem(ModItems.TUNGSTEN_BAT);
            handheldBroadswordItem(ModItems.OBSIDIAN_SWORD);
            handheldScaledItem(ModItems.SPOON);
            handheldItem(ModItems.FESTIVE_AXE);
            handheldItem(ModItems.LIFE_SABER);
            handheldItem(ModItems.KENDO_STICK);
            handheldItem(ModItems.SLAPSTICK_SWORD);
            handheldItem(ModItems.WOODEN_DAGGER);
            handheldItem(ModItems.STONE_DAGGER);
            handheldItem(ModItems.IRON_DAGGER);
            handheldItem(ModItems.GOLDEN_DAGGER);
            handheldItem(ModItems.DIAMOND_DAGGER);
            handheldItem(ModItems.SOULPHYRE_DAGGER);
            handheldItem(ModItems.NETHERITE_DAGGER);
            handheldItem(ModItems.TUNGSTEN_DAGGER);
            handheldItem(ModItems.SWITCH_BLADE);
            handheldItem(ModItems.REBELS_KNIFE);
            handheldItem(ModItems.THORN_OF_THE_DEAD_GODS);
            handheldItem(ModItems.POISONERS_SIDEARM);
            handheldItem(ModItems.SOULPHYRE_AXE);
            handheldItem(ModItems.SOULPHYRE_HOE);
            handheldItem(ModItems.SOULPHYRE_PICKAXE);
            handheldItem(ModItems.SOULPHYRE_SWORD);
            handheldItem(ModItems.SOULPHYRE_SHOVEL);
            handheldItem(ModItems.TUNGSTEN_SWORD);
            handheldItem(ModItems.TUNGSTEN_PICKAXE);
            handheldItem(ModItems.TUNGSTEN_SHOVEL);
            handheldItem(ModItems.TUNGSTEN_AXE);
            handheldItem(ModItems.TUNGSTEN_HOE);
            handheldMediumItem(ModItems.ROSE_QUARTZ_SWORD);
//            handheldItem(ModItems.IRON_RAPIER);


            handheldScaledItem(ModItems.RED_EYES_DREAM);
            handheldScaledItem(ModItems.LYNNS_DESOLATION);
            handheldScaledItem(ModItems.ACONITE_AXE);
            handheldScaledItem(ModItems.WOODEN_POLEARM);
            handheldScaledItem(ModItems.STONE_POLEARM);
            handheldScaledItem(ModItems.IRON_POLEARM);
            handheldScaledItem(ModItems.GOLDEN_POLEARM);
            handheldScaledItem(ModItems.DIAMOND_POLEARM);
            handheldScaledItem(ModItems.SOULPHYRE_POLEARM);
            handheldScaledItem(ModItems.NETHERITE_POLEARM);
            handheldScaledItem(ModItems.TUNGSTEN_POLEARM);
            handheldScaledItem(ModItems.WOODEN_SCYTHE);
            handheldScaledItem(ModItems.STONE_SCYTHE);
            handheldScaledItem(ModItems.IRON_SCYTHE);
            handheldScaledItem(ModItems.GOLDEN_SCYTHE);
            handheldScaledItem(ModItems.DIAMOND_SCYTHE);
            handheldScaledItem(ModItems.SOULPHYRE_SCYTHE);
            handheldScaledItem(ModItems.NETHERITE_SCYTHE);
            handheldScaledItem(ModItems.TUNGSTEN_SCYTHE);
            handheldScaledItem(ModItems.WOODEN_HAMMER);
            handheldScaledItem(ModItems.STONE_HAMMER);
            handheldScaledItem(ModItems.IRON_HAMMER);
            handheldScaledItem(ModItems.GOLDEN_HAMMER);
            handheldScaledItem(ModItems.DIAMOND_HAMMER);
            handheldScaledItem(ModItems.SOULPHYRE_HAMMER);
            handheldScaledItem(ModItems.NETHERITE_HAMMER);
            handheldScaledItem(ModItems.TUNGSTEN_HAMMER);
            handheldScaledItem(ModItems.MOSSY_MASHER);
            handheldScaledItem(ModItems.POLE);



        }

        // Shoutout to El_Redstoniano for making this
        private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
            final String MOD_ID = GooberArsenal.MOD_ID; // Change this to your mod id

            if (itemDeferredItem.get() instanceof ArmorItem armorItem) {
                trimMaterials.forEach((trimMaterial, value) -> {
                    float trimValue = value;

                    String armorType = switch (armorItem.getEquipmentSlot()) {
                        case HEAD -> "helmet";
                        case CHEST -> "chestplate";
                        case LEGS -> "leggings";
                        case FEET -> "boots";
                        default -> "";
                    };

                    String armorItemPath = armorItem.toString();
                    String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                    String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                    ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                    ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                    ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                    // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                    // avoid an IllegalArgumentException
                    existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                    // Trimmed armorItem files
                    getBuilder(currentTrimName)
                            .parent(new ModelFile.UncheckedModelFile("item/generated"))
                            .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                            .texture("layer1", trimResLoc);

                    // Non-trimmed armorItem file (normal variant)
                    this.withExistingParent(itemDeferredItem.getId().getPath(),
                                    mcLoc("item/generated"))
                            .override()
                            .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath()))
                            .predicate(mcLoc("trim_type"), trimValue).end()
                            .texture("layer0",
                                    ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                            "item/" + itemDeferredItem.getId().getPath()));
                });
            }
        }

        public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
            this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                    .texture("texture", ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID,
                            "block/" + baseBlock.getId().getPath()));
        }

        public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
            this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                    .texture("texture", ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID,
                            "block/" + baseBlock.getId().getPath()));
        }

        public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
            this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                    .texture("wall", ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID,
                            "block/" + baseBlock.getId().getPath()));
        }

        private ItemModelBuilder handheldItem(DeferredItem<?> item) {
            return withExistingParent(item.getId().getPath(),
                    ResourceLocation.parse("item/handheld")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "item/" + item.getId().getPath()));
        }

    private ItemModelBuilder handheldScaledItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                modLoc("item/templates/handheld_scaled"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldBroadswordItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                modLoc("item/templates/handheld_broadsword"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldMediumItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                modLoc("item/templates/handheld_medium"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    //Leck eier this dont work yet and it doesnt have a usage for me to actually have a reason to fix this
//    private ItemModelBuilder switchBladeDyedItem(DeferredItem<?> item) {
//        return withExistingParent(item.getId().getPath(),
//                modLoc("item/templates/switchblade_base"))
//                .texture("layer0", modLoc("item/" + item.getId().getPath() + "_base"))
//                .texture("layer1", modLoc("item/" + item.getId().getPath() + "_overlay"))
//                .texture("layer2", modLoc("item/" + item.getId().getPath() + "_dyed"));
//    }
    }

