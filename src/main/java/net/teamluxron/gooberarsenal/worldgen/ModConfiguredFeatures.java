package net.teamluxron.gooberarsenal.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_KEVIN_ORE_PLACED_KEY = registerKey("kevin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SOULPHYRE_PLACED_KEY = registerKey("soulphyre");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_ROSE_QUARTZ_PLACED_KEY = registerKey("rose_quartz");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_ANCIENT_CAGITE_PLACED_KEY = registerKey("ancient_cagite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_SCALED_ENDSTONE_PLACED_KEY = registerKey("scaled_endstone");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest blackstoneReplaceables = new BlockMatchTest(Blocks.BLACKSTONE);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);


        List<OreConfiguration.TargetBlockState> overworldKevinOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.KEVIN_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_KEVIN_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_KEVIN_ORE_PLACED_KEY, Feature.ORE, new OreConfiguration(overworldKevinOres, 5));
        register(context, NETHER_SOULPHYRE_PLACED_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.SOULPHYRE_ORE.get().defaultBlockState(), 8));
        register(context, NETHER_ROSE_QUARTZ_PLACED_KEY, Feature.ORE, new OreConfiguration(blackstoneReplaceables,
                ModBlocks.ROSE_QUARTZ_ORE.get().defaultBlockState(), 4));
        register(context, NETHER_ANCIENT_CAGITE_PLACED_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.ANCIENT_CAGITE.get().defaultBlockState(), 4));
        register(context, END_SCALED_ENDSTONE_PLACED_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.SCALED_ENDSTONE.get().defaultBlockState() ,4));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
