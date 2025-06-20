package net.teamluxron.gooberarsenal.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.ServerPayloadContext;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.entity.function.EchoFlowerBlockEntity;

public class EchoFlowerUpdateMessage implements CustomPacketPayload {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "echo_flower_update");

    public static final Type<EchoFlowerUpdateMessage> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, EchoFlowerUpdateMessage> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public EchoFlowerUpdateMessage decode(RegistryFriendlyByteBuf buf) {
            BlockPos pos = buf.readBlockPos();
            String message = buf.readUtf(64);
            return new EchoFlowerUpdateMessage(pos, message);
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, EchoFlowerUpdateMessage msg) {
            buf.writeBlockPos(msg.pos);
            buf.writeUtf(msg.message);
        }
    };

    private final BlockPos pos;
    private final String message;

    public EchoFlowerUpdateMessage(BlockPos pos, String message) {
        this.pos = pos;
        this.message = message;
    }

    public BlockPos getPos() {
        return pos;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(EchoFlowerUpdateMessage msg, IPayloadContext context) {
        if (!(context instanceof ServerPayloadContext serverCtx)) return;

        serverCtx.enqueueWork(() -> {
            ServerPlayer player = serverCtx.player();
            Level level = player.level();
            BlockEntity be = level.getBlockEntity(msg.getPos());
            if (be instanceof EchoFlowerBlockEntity flower) {
                flower.setCustomMessage(msg.getMessage());
                flower.setPlacedByPlayer(true);
                flower.setChanged();
            }
        });
    }
}
