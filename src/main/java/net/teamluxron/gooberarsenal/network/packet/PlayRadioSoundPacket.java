package net.teamluxron.gooberarsenal.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;

public record PlayRadioSoundPacket(BlockPos pos) implements CustomPacketPayload {
    public static final Type<PlayRadioSoundPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "start_radio_sound")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, PlayRadioSoundPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            PlayRadioSoundPacket::pos,
            PlayRadioSoundPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(PlayRadioSoundPacket packet) {
        ClientSoundManager.playRadioSound(packet.pos());
    }
}