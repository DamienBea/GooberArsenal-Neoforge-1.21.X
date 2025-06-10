package net.teamluxron.gooberarsenal;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.common.NeoForge;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.client.GooberArsenalClient;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantmentEffects;
import net.teamluxron.gooberarsenal.entity.ModEntities;
import net.teamluxron.gooberarsenal.events.ModEvents;
import net.teamluxron.gooberarsenal.events.PortalConversionHandler;
import net.teamluxron.gooberarsenal.handler.ShieldBlockHandler;
import net.teamluxron.gooberarsenal.item.ModCreativeModeTabs;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.loot.ModLootModifiers;
import net.teamluxron.gooberarsenal.network.ModMessages;
import net.teamluxron.gooberarsenal.recipe.ForgingRecipe;
import net.teamluxron.gooberarsenal.recipe.ModRecipeSerializers;
import net.teamluxron.gooberarsenal.recipe.ModRecipes;
import net.teamluxron.gooberarsenal.registry.LootModifierRegistry;
import net.teamluxron.gooberarsenal.registry.ModDamageTypes;
import net.teamluxron.gooberarsenal.sound.ModSounds;
import net.teamluxron.gooberarsenal.util.PvPBlockHandler;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(GooberArsenal.MOD_ID)
public class GooberArsenal {
    public static final String MOD_ID = "gooberarsenal";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation res(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public GooberArsenal(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::onRegisterPayloadHandlers);
        modEventBus.addListener(ModMessages::register);

        // Register mod-specific stuff
        ModDamageTypes.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModEnchantmentEffects.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModRecipes.TYPES.register(modEventBus);
        ModRecipeSerializers.register(modEventBus);
        ModEvents.register();
        ModEntities.register(modEventBus);
        NeoForge.EVENT_BUS.register(PvPBlockHandler.class);
        NeoForge.EVENT_BUS.register(ShieldBlockHandler.class);
        LootModifierRegistry.register(modEventBus);

        //Portal bread teleportation

        PortalConversionHandler handler = new PortalConversionHandler();
        NeoForge.EVENT_BUS.addListener(LevelTickEvent.Post.class, handler::onLevelTick);

        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Register server-side listeners
        NeoForge.EVENT_BUS.register(this);

        // Register client-only setup
        if (FMLEnvironment.dist == Dist.CLIENT) {
            GooberArsenalClient.init(modEventBus);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Common setup logic
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // Add items to creative tabs
    }

    private void onRegisterPayloadHandlers(RegisterPayloadHandlersEvent event) {
        // Register server-side packets
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Server-side logic
    }

    @SubscribeEvent
    public void onWorldUnload(LevelEvent.Unload event) {
        if (event.getLevel().isClientSide()) {
            ClientSoundManager.stopAllRadioSounds();
        }
    }

    // Recipe Type registration
    public static class ModRecipeTypes {
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