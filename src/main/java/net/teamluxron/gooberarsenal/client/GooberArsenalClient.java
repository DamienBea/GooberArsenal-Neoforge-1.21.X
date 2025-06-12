package net.teamluxron.gooberarsenal.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.entity.ModEntities;
import net.teamluxron.gooberarsenal.entity.client.model.PeaShooterModel;
import net.teamluxron.gooberarsenal.entity.client.renderer.FieldSwordRenderer;
import net.teamluxron.gooberarsenal.entity.client.renderer.PeaShooterRenderer;
import net.teamluxron.gooberarsenal.entity.client.renderer.ZombifiedBreadRenderer;


@OnlyIn(Dist.CLIENT)
public class GooberArsenalClient {

    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(GooberArsenalClient::onClientSetup);
        modEventBus.addListener(GooberArsenalClient::registerClientReceivers);
        modEventBus.addListener(GooberArsenalClient::onRegisterLayerDefinitions);
        modEventBus.addListener(GooberArsenalClient::onRegisterEntityRenderers);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.ZOMBIFIED_BREAD.get(), ZombifiedBreadRenderer::new);
        EntityRenderers.register(ModEntities.PEASHOOTER.get(), PeaShooterRenderer::new);
        EntityRenderers.register(ModEntities.BEAN_PROJECTILE.get(),
                context -> new ThrownItemRenderer<>(context)
        );
    }

    public static void registerClientReceivers(RegisterPayloadHandlersEvent event) {
    }

    private static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                PeaShooterModel.LAYER_LOCATION,
                PeaShooterModel::createBodyLayer
        );
    }

    private static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.ZOMBIFIED_BREAD.get(),
                ZombifiedBreadRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.PEASHOOTER.get(),
                PeaShooterRenderer::new
        );
        event.registerBlockEntityRenderer(
                ModBlockEntities.FIELD_SWORD_BE.get(),
                FieldSwordRenderer::new
        );
    }

}