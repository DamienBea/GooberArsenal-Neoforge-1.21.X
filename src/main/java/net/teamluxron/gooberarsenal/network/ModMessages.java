package net.teamluxron.gooberarsenal.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.teamluxron.gooberarsenal.network.packet.RadioTogglePacket;


public class ModMessages {

    @SubscribeEvent
    public static void registerClientReceivers(RegisterPayloadHandlersEvent event) {
        IPayloadHandler registry = event.registrar();

        registry.register(
                RadioTogglePacket.TYPE,
                RadioTogglePacket::decode,
                RadioTogglePacket::handle
        );
    }

    public static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        // Register packet handlers for both client and server
        event.registrar().register(RadioTogglePacket.TYPE, RadioTogglePacket::decode, RadioTogglePacket::handle);
    }
}