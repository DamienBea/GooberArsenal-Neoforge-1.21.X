package net.teamluxron.gooberarsenal.network;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;
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
                (payload, context) -> handlePlayRadioSoundPacket(context, payload)
        );

        registrar.playToClient(
                StopRadioSoundPacket.TYPE,
                StopRadioSoundPacket.STREAM_CODEC,
                (payload, context) -> handleStopRadioSoundPacket(context, payload)
        );
    }

    private static void handlePlayRadioSoundPacket(IPayloadContext context, PlayRadioSoundPacket packet) {
        context.enqueueWork(() -> {
            ClientSoundManager.playRadioSound(packet.pos());
        }).exceptionally(e -> {
            context.disconnect(Component.literal("Error processing PlayRadioSoundPacket."));
            return null;
        });
    }

    private static void handleStopRadioSoundPacket(IPayloadContext context, StopRadioSoundPacket packet) {
        context.enqueueWork(() -> {
            ClientSoundManager.stopRadioSound(packet.pos());
        }).exceptionally(e -> {
            context.disconnect(Component.literal("Error processing StopRadioSoundPacket."));
            return null;
        });
    }
}