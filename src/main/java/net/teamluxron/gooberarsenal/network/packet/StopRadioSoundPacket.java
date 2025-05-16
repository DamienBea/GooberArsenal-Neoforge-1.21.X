package net.teamluxron.gooberarsenal.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;

public record StopRadioSoundPacket(BlockPos pos) implements CustomPacketPayload {
    public static final Type<StopRadioSoundPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "stop_radio_sound")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, StopRadioSoundPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            StopRadioSoundPacket::pos,
            StopRadioSoundPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(StopRadioSoundPacket packet) {
        ClientSoundManager.stopRadioSound(packet.pos());
    }
}