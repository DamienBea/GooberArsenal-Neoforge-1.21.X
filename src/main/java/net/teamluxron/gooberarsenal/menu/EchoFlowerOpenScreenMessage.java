package net.teamluxron.gooberarsenal.menu;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.client.screen.EchoFlowerEditScreen;

public class EchoFlowerOpenScreenMessage implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "echo_flower_open");
    public static final Type<EchoFlowerOpenScreenMessage> TYPE = new Type<>(ID);

    private final BlockPos pos;

    public EchoFlowerOpenScreenMessage(BlockPos pos) {
        this.pos = pos;
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, EchoFlowerOpenScreenMessage> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    EchoFlowerOpenScreenMessage::getPos,
                    EchoFlowerOpenScreenMessage::new
            );

    public BlockPos getPos() {
        return pos;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void encode(RegistryFriendlyByteBuf buf, EchoFlowerOpenScreenMessage msg) {
        buf.writeBlockPos(msg.pos);
    }

    public static EchoFlowerOpenScreenMessage decode(RegistryFriendlyByteBuf buf) {
        return new EchoFlowerOpenScreenMessage(buf.readBlockPos());
    }

    public static void handle(EchoFlowerOpenScreenMessage msg, IPayloadContext context) {
        if (!(context instanceof ClientPayloadContext clientCtx)) return;

        clientCtx.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            if (player == null) return;

            EchoFlowerEditMenu menu = new EchoFlowerEditMenu(0, player.getInventory(), msg.getPos());
            mc.setScreen(new EchoFlowerEditScreen(menu));
        });
    }
}
