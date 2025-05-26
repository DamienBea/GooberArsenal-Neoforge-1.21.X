package net.teamluxron.gooberarsenal.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GooberArsenal.MOD_ID);

    public static final Supplier<CreativeModeTab> GOOBERARSENAL_COMBAT = CREATIVE_MODE_TAB.register("gooberarsenal_combat",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RED_EYES_DREAM.get()))
                    .title(Component.translatable("creativetab.gooberarsenal.gooberarsenal_combat"))
                    .displayItems((itemDisplayParameters, output) -> {

                        //Weapons
                        //Bats
                        output.accept(ModItems.WOODEN_BAT.get());
                        output.accept(ModItems.STONE_SPIKED_BAT.get());
                        output.accept(ModItems.IRON_BAT.get());
                        output.accept(ModItems.GOLDEN_BAT.get());
                        output.accept(ModItems.DIAMOND_BAT.get());
                        output.accept(ModItems.SOULPHYRE_BAT.get());
                        output.accept(ModItems.NETHERITE_BAT.get());
                        output.accept(ModItems.TUNGSTEN_BAT.get());
                        output.accept(ModItems.BEE_BUNNY_BASHER.get());

                        //Scythes
                        output.accept(ModItems.WOODEN_SCYTHE.get());
                        output.accept(ModItems.STONE_SCYTHE.get());
                        output.accept(ModItems.IRON_SCYTHE.get());
                        output.accept(ModItems.GOLDEN_SCYTHE.get());
                        output.accept(ModItems.DIAMOND_SCYTHE.get());
                        output.accept(ModItems.SOULPHYRE_SCYTHE.get());
                        output.accept(ModItems.NETHERITE_SCYTHE.get());
                        output.accept(ModItems.TUNGSTEN_SCYTHE.get());
                        output.accept(ModItems.RED_EYES_DREAM.get());

                        //Polearms
                        output.accept(ModItems.WOODEN_POLEARM.get());
                        output.accept(ModItems.STONE_POLEARM.get());
                        output.accept(ModItems.IRON_POLEARM.get());
                        output.accept(ModItems.GOLDEN_POLEARM.get());
                        output.accept(ModItems.DIAMOND_POLEARM.get());
                        output.accept(ModItems.SOULPHYRE_POLEARM.get());
                        output.accept(ModItems.NETHERITE_POLEARM.get());
                        output.accept(ModItems.TUNGSTEN_POLEARM.get());
                        output.accept(ModItems.LYNNS_DESOLATION.get());
                        output.accept(ModItems.ACONITE_AXE.get());

                        //Daggers
                        output.accept(ModItems.WOODEN_DAGGER.get());
                        output.accept(ModItems.STONE_DAGGER.get());
                        output.accept(ModItems.IRON_DAGGER.get());
                        output.accept(ModItems.SWITCH_BLADE.get());
                        output.accept(ModItems.GOLDEN_DAGGER.get());
                        output.accept(ModItems.DIAMOND_DAGGER.get());
                        output.accept(ModItems.SOULPHYRE_DAGGER.get());
                        output.accept(ModItems.LIFE_SABER.get());
                        output.accept(ModItems.NETHERITE_DAGGER.get());
                        output.accept(ModItems.REBELS_KNIFE.get());
                        output.accept(ModItems.THORN_OF_THE_DEAD_GODS.get());
                        output.accept(ModItems.POISONERS_SIDEARM.get());

                        //Rapiers
//                        output.accept(ModItems.IRON_RAPIER.get());

                        //Hammers
                        output.accept(ModItems.WOODEN_HAMMER.get());
                        output.accept(ModItems.STONE_HAMMER.get());
                        output.accept(ModItems.IRON_HAMMER.get());
                        output.accept(ModItems.GOLDEN_HAMMER.get());
                        output.accept(ModItems.DIAMOND_HAMMER.get());
                        output.accept(ModItems.SOULPHYRE_HAMMER.get());
                        output.accept(ModItems.NETHERITE_HAMMER.get());
                        output.accept(ModItems.TUNGSTEN_HAMMER.get());
                        output.accept(ModItems.MOSSY_MASHER.get());

                        //Swords
                        output.accept(ModItems.OBSIDIAN_SWORD.get());
                        output.accept(ModItems.ROSE_QUARTZ_SWORD.get());



                        //Armor

                        output.accept(ModItems.SOULPHYRE_HELMET.get());
                        output.accept(ModItems.SOULPHYRE_CHESTPLATE.get());
                        output.accept(ModItems.SOULPHYRE_LEGGINGS.get());
                        output.accept(ModItems.SOULPHYRE_BOOTS.get());

                        output.accept(ModItems.CAGITE_HELMET.get());
                        output.accept(ModItems.CAGITE_CHESTPLATE.get());
                        output.accept(ModItems.CAGITE_LEGGINGS.get());
                        output.accept(ModItems.CAGITE_BOOTS.get());

                        output.accept(ModItems.TUNGSTEN_HELMET.get());
                        output.accept(ModItems.TUNGSTEN_CHESTPLATE.get());
                        output.accept(ModItems.TUNGSTEN_LEGGINGS.get());
                        output.accept(ModItems.TUNGSTEN_BOOTS.get());

                        output.accept(ModItems.STEVENS_JACKET.get());

                        //Tools
                        output.accept(ModItems.SOULPHYRE_SWORD.get());
                        output.accept(ModItems.SOULPHYRE_PICKAXE.get());
                        output.accept(ModItems.SOULPHYRE_SHOVEL.get());
                        output.accept(ModItems.SOULPHYRE_AXE.get());
                        output.accept(ModItems.SOULPHYRE_HOE.get());

                        output.accept(ModItems.TUNGSTEN_SWORD.get());
                        output.accept(ModItems.TUNGSTEN_PICKAXE.get());
                        output.accept(ModItems.TUNGSTEN_SHOVEL.get());
                        output.accept(ModItems.TUNGSTEN_AXE.get());
                        output.accept(ModItems.TUNGSTEN_HOE.get());


                        output.accept(ModItems.ROSE_QUARTZ_SHIELD.get());
                        output.accept(ModItems.ALLOY_SHIELD.get());
                        output.accept(ModItems.DOOR_SHIELD.get());
                        output.accept(ModItems.GRASS_CREST_SHIELD.get());
                        output.accept(ModItems.KNIGHTS_SHIELD.get());
                        output.accept(ModItems.PALETTE.get());
                        output.accept(ModItems.TRASH_CAN.get());



                    }).build());

    public static final Supplier<CreativeModeTab> GOOBERARSENAL_COMBAT_SPECIAL = CREATIVE_MODE_TAB.register("gooberarsenal_combat_special",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SLAPSTICK_SWORD.get()))
                    .title(Component.translatable("creativetab.gooberarsenal.gooberarsenal_combat_special"))
                    .displayItems((itemDisplayParameters, output) -> {

                        //Misc
                        output.accept(ModItems.SLAPSTICK_SWORD.get());
                        output.accept(ModItems.STAHP_SIGN.get());
                        output.accept(ModItems.FESTIVE_AXE.get());
                        output.accept(ModItems.KENDO_STICK.get());
                        output.accept(ModItems.CHAIR.get());
                        output.accept(ModItems.FRYING_PAN.get());
                        output.accept(ModItems.STEEL_PIPE.get());
                        output.accept(ModItems.SPOON.get());

                    }).build());

    public static final Supplier<CreativeModeTab> GOOBER_BLOCKS = CREATIVE_MODE_TAB.register("goober_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.KEVIN_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "gooberarsenal_combat"))
                    .title(Component.translatable("creativetab.gooberarsenal.goober_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.KEVIN_ORE.get());
                        output.accept(ModBlocks.KEVIN_BLOCK.get());
                        output.accept(ModBlocks.DEEPSLATE_KEVIN_ORE.get());
                        output.accept(ModBlocks.SOULPHYRE_ORE.get());
                        output.accept(ModBlocks.SOULPHYRE_BLOCK.get());
                        output.accept(ModBlocks.ROSE_QUARTZ_ORE.get());
                        output.accept(ModBlocks.ROSE_QUARTZ_BLOCK.get());
                        output.accept(ModBlocks.ANCIENT_CAGITE.get());
                        output.accept(ModBlocks.CAGITE_BLOCK.get());
                        output.accept(ModBlocks.SCALED_ENDSTONE.get());
                        output.accept(ModBlocks.DRAGON_SCALED_TUNGSTEN_BLOCK.get());
                        output.accept(ModBlocks.FORGING_ANVIL.get());
                        output.accept(ModBlocks.RADIO.get());
                        output.accept(ModBlocks.BROKEN_RADIO.get());

                    }).build());

    public static final Supplier<CreativeModeTab> GOOBER_ITEMS = CREATIVE_MODE_TAB.register("goober_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GOOBER_UPGRADE_TEMPLATE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "goober_blocks"))
                    .title(Component.translatable("creativetab.gooberarsenal.goober_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.OBSIDIAN_HILT.get());
                        output.accept(ModItems.OBSIDIAN_HANDGUARD.get());
                        output.accept(ModItems.GOOBER_UPGRADE_TEMPLATE.get());
                        output.accept(ModItems.TRANSFORMATION_TEMPLATE.get());
                        output.accept(ModItems.KEVIN_SHARDS.get());
                        output.accept(ModItems.SOULPHYRE.get());
                        output.accept(ModItems.CAGITE_SCRAP.get());
                        output.accept(ModItems.CAGITE_INGOT.get());
                        output.accept(ModItems.DRAGON_SCALE_SHARD.get());
                        output.accept(ModItems.IRON_PLATE.get());
                        output.accept(ModItems.LIFE_SAVER.get());
                        output.accept(ModItems.RUBBER_CHICKEN.get());
                        output.accept(ModItems.PLASTIC_BAG.get());
                        output.accept(ModItems.PLASTIC.get());
                        output.accept(ModItems.SWITCH_CARTRIDGE.get());
                        output.accept(ModItems.OBSIDIAN_ROSE.get());
                        output.accept(ModItems.GLEAMING_RED_EYE.get());
                        output.accept(ModItems.THORN_OF_ZAZIKEL.get());
                        output.accept(ModItems.THORN_OF_TOTH.get());
                        output.accept(ModItems.THORN_OF_ANDORAL.get());
                        output.accept(ModItems.MOSSY_GEM.get());
                        output.accept(ModItems.CHAIN_OF_FATE.get());
                        output.accept(ModItems.VENOMOUS_FANG.get());
                        output.accept(ModItems.ACONITE.get());
                        output.accept(ModItems.POLE.get());

                    }).build());

    public static final Supplier<CreativeModeTab> GOOBER_CONSUMABLES = CREATIVE_MODE_TAB.register("goober_consumables",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COPPER_APPLE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "goober_items"))
                    .title(Component.translatable("creativetab.gooberarsenal.goober_consumables"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ENERGY_BAR.get());
                        output.accept(ModItems.SANDVICH.get());
                        output.accept(ModItems.CHOCOLATE_CHIP_PANCAKES.get());
                        output.accept(ModItems.COPPER_APPLE.get());

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
