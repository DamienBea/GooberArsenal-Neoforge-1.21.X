package net.teamluxron.gooberarsenal.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;

public record ClientboundRadioTogglePacket(BlockPos pos, boolean isOn) implements CustomPacketPayload {
    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "clientbound_radio_toggle");

    public static final Type<ClientboundRadioTogglePacket> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundRadioTogglePacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    ClientboundRadioTogglePacket::pos,
                    StreamCodec.of(
                            RegistryFriendlyByteBuf::writeBoolean,
                            RegistryFriendlyByteBuf::readBoolean
                    ),
                    ClientboundRadioTogglePacket::isOn,
                    ClientboundRadioTogglePacket::new
            );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;  // Must return TYPE, not null!
    }

    public static void handle(ClientboundRadioTogglePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (packet.isOn()) {
                ClientSoundManager.playRadioSound(packet.pos());
            } else {
                ClientSoundManager.stopRadioSound(packet.pos());
            }
        });
    }
}