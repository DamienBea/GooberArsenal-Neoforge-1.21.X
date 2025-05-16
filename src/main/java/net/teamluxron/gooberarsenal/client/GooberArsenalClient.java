package net.teamluxron.gooberarsenal.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

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
}