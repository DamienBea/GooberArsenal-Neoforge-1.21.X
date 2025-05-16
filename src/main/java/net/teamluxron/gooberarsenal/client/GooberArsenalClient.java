package net.teamluxron.gooberarsenal.client;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.network.ModMessages;

public class GooberArsenalClient {

    public static void onClientSetup(FMLClientSetupEvent event) {
        // Client-only setup if needed
    }

    public static void registerClientReceivers(RegisterPayloadHandlersEvent event) {
        if (event.side().isClient()) {
            ModMessages.registerClientReceivers(event);
        }
    }
}