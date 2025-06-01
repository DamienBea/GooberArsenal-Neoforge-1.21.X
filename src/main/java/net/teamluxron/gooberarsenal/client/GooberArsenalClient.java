package net.teamluxron.gooberarsenal.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.blocks.entity.renderer.FieldSwordRenderer;

@OnlyIn(Dist.CLIENT)
public class GooberArsenalClient {

    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(GooberArsenalClient::onClientSetup);
        modEventBus.addListener(GooberArsenalClient::registerClientReceivers);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        // Client-only setup if needed
    }

    public static void registerClientReceivers(RegisterPayloadHandlersEvent event) {
    }

    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.FIELD_SWORD_BE.get(), (BlockEntityRendererProvider) FieldSwordRenderer::new);
    }

}