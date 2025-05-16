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

public record ServerboundRadioTogglePacket(BlockPos pos, boolean isOn) implements CustomPacketPayload {
    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "serverbound_radio_toggle");

    public static final Type<ServerboundRadioTogglePacket> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundRadioTogglePacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    ServerboundRadioTogglePacket::pos,
                    StreamCodec.of(
                            RegistryFriendlyByteBuf::writeBoolean,
                            RegistryFriendlyByteBuf::readBoolean
                    ),
                    ServerboundRadioTogglePacket::isOn,
                    ServerboundRadioTogglePacket::new
            );

    public static void handle(ServerboundRadioTogglePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                var level = player.serverLevel();
                var blockEntity = level.getBlockEntity(packet.pos());
                if (blockEntity instanceof RadioBlockEntity radio) {
                    radio.setEnabled(packet.isOn());
                    radio.syncToClients();
                }
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return null;
    }
}