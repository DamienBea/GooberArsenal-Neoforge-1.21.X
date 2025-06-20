package net.teamluxron.gooberarsenal.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.menu.EchoFlowerOpenScreenMessage;
import net.teamluxron.gooberarsenal.network.packet.EchoFlowerUpdateMessage;
import net.teamluxron.gooberarsenal.network.packet.FieldSwordSyncS2CPacket;

public class ModNetwork {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(GooberArsenal.MOD_ID);

        registrar.playToClient(
                FieldSwordSyncS2CPacket.TYPE,
                FieldSwordSyncS2CPacket.STREAM_CODEC,
                FieldSwordSyncS2CPacket::handle
        );

        registrar.playToClient(
                EchoFlowerOpenScreenMessage.TYPE,
                EchoFlowerOpenScreenMessage.STREAM_CODEC,
                EchoFlowerOpenScreenMessage::handle
        );

        registrar.playToServer(
                EchoFlowerUpdateMessage.TYPE,
                EchoFlowerUpdateMessage.STREAM_CODEC,
                EchoFlowerUpdateMessage::handle
        );
    }

    // ✅ Generic version for ANY client-bound packet
    public static void sendToPlayer(CustomPacketPayload packet, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    // ✅ Generic version for broadcasting to ALL players
    public static void sendToClients(CustomPacketPayload packet) {
        PacketDistributor.sendToAllPlayers(packet);
    }

    // ✅ Generic version for server-bound messages
    public static void sendToServer(CustomPacketPayload message) {
        PacketDistributor.sendToServer(message);
    }
}
