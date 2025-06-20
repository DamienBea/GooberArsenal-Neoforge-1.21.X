package net.teamluxron.gooberarsenal.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.entity.function.FieldSwordBlockEntity;

public record FieldSwordSyncS2CPacket(BlockPos pos, CompoundTag data) implements CustomPacketPayload {
    public static final Type<FieldSwordSyncS2CPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "fieldsword_sync"));

    public static final StreamCodec<FriendlyByteBuf, FieldSwordSyncS2CPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            FieldSwordSyncS2CPacket::pos,
            ByteBufCodecs.COMPOUND_TAG,
            FieldSwordSyncS2CPacket::data,
            FieldSwordSyncS2CPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            var level = context.player().level();
            if (level != null && level.getBlockEntity(pos) instanceof FieldSwordBlockEntity be) {
                be.loadFromUpdateTag(data, level.registryAccess());
            }
        });
    }
}