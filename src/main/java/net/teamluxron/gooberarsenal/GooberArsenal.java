package net.teamluxron.gooberarsenal;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.common.NeoForge;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.blocks.entity.RadioBlockEntity;
import net.teamluxron.gooberarsenal.blocks.entity.renderer.ForgingAnvilRenderer;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantmentEffects;
import net.teamluxron.gooberarsenal.item.ModCreativeModeTabs;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.loot.ModLootModifiers;
import net.teamluxron.gooberarsenal.recipe.ForgingRecipe;
import net.teamluxron.gooberarsenal.recipe.ModRecipeSerializers;
import net.teamluxron.gooberarsenal.recipe.ModRecipes;
import net.teamluxron.gooberarsenal.registry.ModDamageTypes;
import net.teamluxron.gooberarsenal.sound.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(GooberArsenal.MOD_ID)
public class GooberArsenal {
    public static final String MOD_ID = "gooberarsenal";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static ResourceLocation res(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
    public GooberArsenal(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModDamageTypes.register(modEventBus);


        modEventBus.addListener(this::addCreative);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);


        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        ModSounds.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);


        ModBlockEntities.register(modEventBus);
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModRecipes.TYPES.register(modEventBus);
        ModRecipeSerializers.register(modEventBus);

    }

        private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    


    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.FORGING_ANVIL_BE.get(), ForgingAnvilRenderer::new);


        }
    }


    public class ModRecipeTypes {
        public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
                DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, GooberArsenal.MOD_ID);

        public static final DeferredHolder<RecipeType<?>, RecipeType<ForgingRecipe>> FORGING =
                RECIPE_TYPES.register("forging", () -> new RecipeType<>() {
                    @Override
                    public String toString() {
                        return GooberArsenal.MOD_ID + ":forging";
                    }
                });
    }
}