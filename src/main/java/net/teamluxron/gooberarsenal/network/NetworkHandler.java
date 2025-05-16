package net.teamluxron.gooberarsenal.network;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;
import net.teamluxron.gooberarsenal.network.packet.PlayRadioSoundPacket;
import net.teamluxron.gooberarsenal.network.packet.StopRadioSoundPacket;

public class NetworkHandler {
    public static void handlePlayRadioSoundPacket(PlayRadioSoundPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientSoundManager.playRadioSound(payload.pos());
        }).exceptionally(e -> {
            context.disconnect(Component.literal("Error processing PlayRadioSoundPacket."));
            return null;
        });
    }

    public static void handleStopRadioSoundPacket(StopRadioSoundPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientSoundManager.stopRadioSound(payload.pos());
        }).exceptionally(e -> {
            context.disconnect(Component.literal("Error processing StopRadioSoundPacket."));
            return null;
        });
    }
}