package net.teamluxron.gooberarsenal.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.entity.RadioBlockEntity;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;

public class StopRadioSoundPacket implements CustomPacketPayload {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "stop_radio_sound");

    public static final Type<StopRadioSoundPacket> TYPE = new Type<>(ID);

    public final BlockPos pos;

    public StopRadioSoundPacket(BlockPos pos) {
        this.pos = pos;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, StopRadioSoundPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    StopRadioSoundPacket::getPos,
                    StopRadioSoundPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleClient(StopRadioSoundPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> ClientSoundManager.stopRadioSound(packet.getPos()));
    }

    public static void handleServer(StopRadioSoundPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) return;

            var level = player.serverLevel();
            var blockEntity = level.getBlockEntity(packet.getPos());
            if (blockEntity instanceof RadioBlockEntity radio) {
                radio.setEnabled(false);
                radio.syncToClients();
                radio.stopRadio();
            }
        });
    }

}