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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SLAPSTICK_SWORD.get()))
                    .title(Component.translatable("creativetab.gooberarsenal.gooberarsenal_combat"))
                    .displayItems((itemDisplayParameters, output) -> {

                        //Weapons
                        //Bats
                        output.accept(ModItems.WOODEN_BAT.get());
                        output.accept(ModItems.IRON_BAT.get());
                        output.accept(ModItems.GOLDEN_BAT.get());
                        output.accept(ModItems.DIAMOND_BAT.get());
                        output.accept(ModItems.NETHERITE_BAT.get());
                        output.accept(ModItems.BEE_BUNNY_BASHER.get());

                        //Scythes
                        output.accept(ModItems.WOODEN_SCYTHE.get());
                        output.accept(ModItems.STONE_SCYTHE.get());
                        output.accept(ModItems.IRON_SCYTHE.get());
                        output.accept(ModItems.GOLDEN_SCYTHE.get());
                        output.accept(ModItems.DIAMOND_SCYTHE.get());
                        output.accept(ModItems.NETHERITE_SCYTHE.get());
                        output.accept(ModItems.RED_EYES_DREAM.get());

                        //Polearms
                        output.accept(ModItems.WOODEN_POLEARM.get());
                        output.accept(ModItems.STONE_POLEARM.get());
                        output.accept(ModItems.IRON_POLEARM.get());
                        output.accept(ModItems.GOLDEN_POLEARM.get());
                        output.accept(ModItems.DIAMOND_POLEARM.get());
                        output.accept(ModItems.NETHERITE_POLEARM.get());
                        output.accept(ModItems.LYNNS_DESOLATION.get());

                        //Greatswords
                        output.accept(ModItems.OBSIDIAN_SWORD.get());


                        //Misc
                        output.accept(ModItems.KENDO_STICK.get());
                        output.accept(ModItems.SPOON.get());
                        output.accept(ModItems.FRYING_PAN.get());
                        output.accept(ModItems.STEEL_PIPE.get());
                        output.accept(ModItems.CHAIR.get());
                        output.accept(ModItems.FESTIVE_AXE.get());
                        output.accept(ModItems.LIFE_SABER.get());
                        output.accept(ModItems.SLAPSTICK_SWORD.get());
                        output.accept(ModItems.STAHP_SIGN.get());
                        output.accept(ModItems.SWITCH_BLADE.get());

                        //Armor
                        output.accept(ModItems.CAGITE_HELMET.get());
                        output.accept(ModItems.CAGITE_CHESTPLATE.get());
                        output.accept(ModItems.CAGITE_LEGGINGS.get());
                        output.accept(ModItems.CAGITE_BOOTS.get());


                    }).build());

    public static final Supplier<CreativeModeTab> GOOBER_BLOCKS = CREATIVE_MODE_TAB.register("goober_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.KEVIN_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "gooberarsenal_combat"))
                    .title(Component.translatable("creativetab.gooberarsenal.goober_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.KEVIN_BLOCK.get());
                        output.accept(ModBlocks.KEVIN_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_KEVIN_ORE.get());
                        output.accept(ModBlocks.ANCIENT_CAGITE.get());
                        output.accept(ModBlocks.CAGITE_BLOCK.get());

                    }).build());

    public static final Supplier<CreativeModeTab> GOOBER_ITEMS = CREATIVE_MODE_TAB.register("goober_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GOOBER_UPGRADE_TEMPLATE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "goober_blocks"))
                    .title(Component.translatable("creativetab.gooberarsenal.goober_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.OBSIDIAN_HILT.get());
                        output.accept(ModItems.OBSIDIAN_HANDGUARD.get());
                        output.accept(ModItems.GOOBER_UPGRADE_TEMPLATE.get());
                        output.accept(ModItems.CAGITE_SCRAP.get());
                        output.accept(ModItems.CAGITE_INGOT.get());
                        output.accept(ModItems.KEVIN_SHARDS.get());
                        output.accept(ModItems.IRON_PLATE.get());
                        output.accept(ModItems.LIFE_SAVER.get());
                        output.accept(ModItems.RUBBER_CHICKEN.get());
                        output.accept(ModItems.PLASTIC_BAG.get());
                        output.accept(ModItems.PLASTIC.get());
                        output.accept(ModItems.SWITCH_CARTRIDGE.get());
                        output.accept(ModItems.OBSIDIAN_ROSE.get());
                        output.accept(ModItems.GLEAMING_RED_EYE.get());

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
