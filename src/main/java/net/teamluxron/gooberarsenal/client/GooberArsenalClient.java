package net.teamluxron.gooberarsenal.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.blocks.entity.renderer.FieldSwordRenderer;
import net.teamluxron.gooberarsenal.entity.ModEntities;
import net.teamluxron.gooberarsenal.entity.client.model.PeaShooterModel;
import net.teamluxron.gooberarsenal.entity.client.renderer.BeanProjectileRenderer;
import net.teamluxron.gooberarsenal.entity.client.renderer.PeaShooterRenderer;
import net.teamluxron.gooberarsenal.entity.client.renderer.ZombifiedBreadRenderer;
import net.teamluxron.gooberarsenal.particles.ModParticles;
import net.teamluxron.gooberarsenal.particles.custom.BeanParticleProvider;


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
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.FIELD_SWORD_BE.get(), (BlockEntityRendererProvider) FieldSwordRenderer::new);
        event.registerEntityRenderer(ModEntities.BEAN_PROJECTILE.get(), BeanProjectileRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(
                ModParticles.BEAN_PARTICLE.get(),
                BeanParticleProvider::new
        );
    }
}