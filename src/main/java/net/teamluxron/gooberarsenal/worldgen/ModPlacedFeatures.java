package net.teamluxron.gooberarsenal.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.teamluxron.gooberarsenal.GooberArsenal;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> KEVIN_ORE_PLACED_KEY = registerKey("kevin_ore_placed_key");
    public static final ResourceKey<PlacedFeature> SOULPHYRE_PLACED_KEY = registerKey("soulphyre_placed_key");
    public static final ResourceKey<PlacedFeature> ANCIENT_CAGITE_PLACED_KEY = registerKey("ancient_cagite_placed_key");
    public static final ResourceKey<PlacedFeature> SCALED_ENDSTONE_PLACED_KEY = registerKey("scaled_endstone_placed_key");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, KEVIN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_KEVIN_ORE_PLACED_KEY),
                ModOrePlacement.commonOrePlacement(7,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-100), VerticalAnchor.absolute(80))));
        register(context, SOULPHYRE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_SOULPHYRE_PLACED_KEY),
                ModOrePlacement.rareOrePlacement(2,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(20))));
        register(context, ANCIENT_CAGITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_ANCIENT_CAGITE_PLACED_KEY),
                ModOrePlacement.rareOrePlacement(2,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(20))));
        register(context, SCALED_ENDSTONE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_SCALED_ENDSTONE_PLACED_KEY),
                ModOrePlacement.rareOrePlacement(2,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
