package net.teamluxron.gooberarsenal.network;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;
import net.teamluxron.gooberarsenal.network.packet.ClientboundRadioTogglePacket;
import net.teamluxron.gooberarsenal.network.packet.PlayRadioSoundPacket;
import net.teamluxron.gooberarsenal.network.packet.StopRadioSoundPacket;

public class NetworkHandler {
    public static void registerPackets(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(GooberArsenal.MOD_ID)
                .versioned("1.0")
                .optional();

        registrar.playToClient(
                PlayRadioSoundPacket.TYPE,
                PlayRadioSoundPacket.STREAM_CODEC,
                NetworkHandler::handlePlayRadioSoundPacket
        );

        registrar.playToClient(
                StopRadioSoundPacket.TYPE,
                StopRadioSoundPacket.STREAM_CODEC,
                NetworkHandler::handleStopRadioSoundPacket
        );

        registrar.playToClient(
                ClientboundRadioTogglePacket.TYPE,
                ClientboundRadioTogglePacket.STREAM_CODEC,
                ClientboundRadioTogglePacket::handle
        );
    }

    private static void handlePlayRadioSoundPacket(PlayRadioSoundPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientSoundManager.playRadioSound(payload.pos());
        }).exceptionally(e -> {
            context.disconnect(Component.literal("Error processing PlayRadioSoundPacket."));
            return null;
        });
    }

    private static void handleStopRadioSoundPacket(StopRadioSoundPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientSoundManager.stopRadioSound(payload.pos);
        }).exceptionally(e -> {
            context.disconnect(Component.literal("Error processing StopRadioSoundPacket."));
            return null;
        });
    }
}