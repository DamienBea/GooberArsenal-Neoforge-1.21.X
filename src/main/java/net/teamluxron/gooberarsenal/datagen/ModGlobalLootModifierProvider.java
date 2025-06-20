package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, GooberArsenal.MOD_ID);
    }

    @Override
    protected void start() {

        //Loot Chests

        this.add("goober_upgrade_from_bastion_treasure",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_treasure")).build(),
                        LootItemRandomChanceCondition.randomChance(0.4f).build()
                }, ModItems.GOOBER_UPGRADE_TEMPLATE.get()));

        this.add("rubber_chicken_from_hoglin_stable",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_hoglin_stable")).build(),
                        LootItemRandomChanceCondition.randomChance(0.4f).build()
                }, ModItems.RUBBER_CHICKEN.get()));

        this.add("goober_upgrade_from_bastion_bridge",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_bridge")).build(),
                        LootItemRandomChanceCondition.randomChance(0.4f).build()
                }, ModItems.GOOBER_UPGRADE_TEMPLATE.get()));

        this.add("goober_upgrade_from_bastion_hoglin_stable",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_hoglin_stable")).build(),
                        LootItemRandomChanceCondition.randomChance(1f).build()
                }, ModItems.GOOBER_UPGRADE_TEMPLATE.get()));

        this.add("cagite_from_bastion_treasure",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_treasure")).build(),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ModItems.CAGITE_INGOT.get()));

        this.add("cagite_from_bastion_bridge",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_bridge")).build(),
                        LootItemRandomChanceCondition.randomChance(0.1f).build()
                }, ModItems.CAGITE_INGOT.get()));

        this.add("cagite_from_bastion_hoglin_stable",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_hoglin_stable")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ModItems.CAGITE_INGOT.get()));


        this.add("cagite_scrap_from_bastion_treasure",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_treasure")).build(),
                        LootItemRandomChanceCondition.randomChance(0.4f).build()
                }, ModItems.CAGITE_SCRAP.get()));

        this.add("cagite_from_bastion_bridge",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_bridge")).build(),
                        LootItemRandomChanceCondition.randomChance(0.1f).build()
                }, ModItems.CAGITE_SCRAP.get()));

        this.add("cagite_from_bastion_hoglin_stable",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_hoglin_stable")).build(),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ModItems.CAGITE_SCRAP.get()));

        this.add("life_saver_from_shipwreck_treasure",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/shipwreck_treasure")).build(),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ModItems.LIFE_SAVER.get()));

        this.add("life_saver_from_buried_treasure",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/buried_treasure")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ModItems.LIFE_SAVER.get()));

        this.add("switch_cartridge_from_mineshaft",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/abandoned_mineshaft")).build(),
                        LootItemRandomChanceCondition.randomChance(0.3f).build()
                }, ModItems.SWITCH_CARTRIDGE.get()));

        this.add("copper_apple_from_mineshaft",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/abandoned_mineshaft")).build(),
                        LootItemRandomChanceCondition.randomChance(0.6f).build()
                }, ModItems.COPPER_APPLE.get()));


        this.add("energy_bar_from_mineshaft",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/abandoned_mineshaft")).build(),
                        LootItemRandomChanceCondition.randomChance(0.3f).build()
                }, ModItems.ENERGY_BAR.get()));


        this.add("switch_cartridge_from_dungeon",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/simple_dungeon")).build(),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                }, ModItems.SWITCH_CARTRIDGE.get()));

        this.add("copper_apple_from_dungeon",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/simple_dungeon")).build(),
                        LootItemRandomChanceCondition.randomChance(0.6f).build()
                }, ModItems.COPPER_APPLE.get()));


        this.add("energy_bar_from_dungeon",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/simple_dungeon")).build(),
                        LootItemRandomChanceCondition.randomChance(0.3f).build()
                }, ModItems.ENERGY_BAR.get()));

        this.add("obsidian_rose_from_end_city",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/end_city_treasure")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ModItems.OBSIDIAN_ROSE.get()));

        this.add("gleaming_red_eye_from_end_city",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/end_city_treasure")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ModItems.GLEAMING_RED_EYE.get()));


        this.add("thorn_of_toth_from_hoglin_stable",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_hoglin_stable")).build(),
                        LootItemRandomChanceCondition.randomChance(0.4f).build()
                }, ModItems.THORN_OF_TOTH.get()));

        this.add("thorn_of_andoral_from_end_city",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/end_city_treasure")).build(),
                        LootItemRandomChanceCondition.randomChance(0.4f).build()
                }, ModItems.THORN_OF_ANDORAL.get()));

        this.add("thorn_of_zazikel_from_strongholds",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/stronghold_library")).build(),
                        LootItemRandomChanceCondition.randomChance(0.4f).build()
                }, ModItems.THORN_OF_ZAZIKEL.get()));

        this.add("aconite_from_ancient_city",
                new AddItemModifier(
                        new LootItemCondition[] {
                                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/ancient_city"))
                                        .build(),
                                LootItemRandomChanceCondition.randomChance(0.1f).build()
                        },
                        ModItems.ACONITE.get()
                )
        );

        //Sus Sand

//        this.add("life_saver_from_desert_sand",
//                new AddItemModifier(new LootItemCondition[] {
//                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("archaeology/desert_pyramid"))
//                                .and(LootItemRandomChanceCondition.randomChance(0.1f)).build(),
//                }, ModItems.LIFE_SAVER.get()));

//        this.add("switch_cartridge_from_desert_sand",
//                new AddItemModifier(new LootItemCondition[] {
//                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("archaeology/desert_pyramid"))
//                                .and(LootItemRandomChanceCondition.randomChance(0.1f)).build(),
//                }, ModItems.SWITCH_CARTRIDGE.get()));

        //Mob drops

        this.add("rubber_chicken_from_chicken",
                new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/chicken"))
                        .and(LootItemRandomChanceCondition.randomChance(0.05f)).build() },
                ModItems.RUBBER_CHICKEN.get()));

        this.add("chain_of_fate_from_wither_skeleton",
                new AddItemModifier(
                        new LootItemCondition[] {
                                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/wither_skeleton"))
                                        .and(LootItemRandomChanceCondition.randomChance(0.05f))
                                        .build()
                        },
                        ModItems.CHAIN_OF_FATE.get()
                ));

        this.add("venomous_fang_from_cave_spider",
                new AddItemModifier(
                        new LootItemCondition[] {
                                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/cave_spider"))
                                        .and(LootItemRandomChanceCondition.randomChance(0.05f))
                                        .build()
                        },
                        ModItems.VENOMOUS_FANG.get()
                ));

        this.add("australium_from_zombified_bread",
                new AddItemModifier(
                        new LootItemCondition[] {
                                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/zombified_bread"))
                                        .and(LootItemRandomChanceCondition.randomChance(0.05f))
                                        .build()
                        },
                        ModItems.AUSTRALIUM_NUGGET.get()
                ));

        this.add("rotten_bread_from_zombified_bread",
                new AddItemModifier(
                        new LootItemCondition[] {
                                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/zombified_bread"))
                                        .and(LootItemRandomChanceCondition.randomChance(0.8f))
                                        .build()
                        },
                        ModItems.ROTTEN_BREAD.get()
                ));

        //Fishing


        this.add("plastic_bag_from_fishing",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("gameplay/fishing/junk"))
                                .and(LootItemRandomChanceCondition.randomChance(0.4f)).build() },
                        ModItems.PLASTIC_BAG.get()));


    }
}
//Danke Nico