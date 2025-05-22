package net.teamluxron.gooberarsenal.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;
import net.teamluxron.gooberarsenal.sound.ModSounds;

public record PlayRadioSoundPacket(BlockPos pos, boolean isBroken) implements CustomPacketPayload {
    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "play_radio_sound");

    public static final Type<PlayRadioSoundPacket> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, PlayRadioSoundPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    PlayRadioSoundPacket::pos,
                    StreamCodec.of(
                            RegistryFriendlyByteBuf::writeBoolean,
                            RegistryFriendlyByteBuf::readBoolean
                    ),
                    PlayRadioSoundPacket::isBroken,
                    PlayRadioSoundPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(PlayRadioSoundPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            SoundEvent sound = packet.isBroken() ?
                    ModSounds.BROKEN_RADIO.get() :
                    ModSounds.RADIO.get();
            ClientSoundManager.playRadioSound(packet.pos(), sound);
        });
    }
}