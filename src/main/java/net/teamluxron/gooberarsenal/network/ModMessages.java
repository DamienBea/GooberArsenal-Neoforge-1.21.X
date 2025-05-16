package net.teamluxron.gooberarsenal.network;


import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.network.packet.ClientboundRadioTogglePacket;
import net.teamluxron.gooberarsenal.network.packet.PlayRadioSoundPacket;
import net.teamluxron.gooberarsenal.network.packet.ServerboundRadioTogglePacket;
import net.teamluxron.gooberarsenal.network.packet.StopRadioSoundPacket;


public class ModMessages {
    public static void register(final RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(GooberArsenal.MOD_ID)
                .versioned("1.0")
                .optional();

        // Client-bound packets
        registrar.playToClient(
                ClientboundRadioTogglePacket.TYPE,
                ClientboundRadioTogglePacket.STREAM_CODEC,
                ClientboundRadioTogglePacket::handle
        );

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

        // Server-bound packets
        registrar.playToServer(
                ServerboundRadioTogglePacket.TYPE,
                ServerboundRadioTogglePacket.STREAM_CODEC,
                ServerboundRadioTogglePacket::handle
        );
    }

    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {
        player.connection.send(payload);
    }

    public static void sendToAllTracking(BlockEntity be, CustomPacketPayload packet) {
        if (be.getLevel() instanceof ServerLevel serverLevel) {
            double x = be.getBlockPos().getX() + 0.5;
            double y = be.getBlockPos().getY() + 0.5;
            double z = be.getBlockPos().getZ() + 0.5;

            for (ServerPlayer player : serverLevel.players()) {
                if (player.distanceToSqr(x, y, z) < 256) {
                    player.connection.send(packet);
                }
            }
        }
    }
}