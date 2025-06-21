package net.teamluxron.gooberarsenal.menu;

import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.client.screen.EchoFlowerEditScreen;

public class EchoFlowerOpenScreenMessage implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "echo_flower_open");
    public static final Type<EchoFlowerOpenScreenMessage> TYPE = new Type<>(ID);

    private final BlockPos pos;
    private final String message;

    public EchoFlowerOpenScreenMessage(BlockPos pos, String message) {
        this.pos = pos;
        this.message = message;
    }

    public BlockPos getPos() {
        return pos;
    }

    public String getMessage() {
        return message;
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, EchoFlowerOpenScreenMessage> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    EchoFlowerOpenScreenMessage::getPos,
                    StreamCodec.fromCodec(Codec.STRING),
                    EchoFlowerOpenScreenMessage::getMessage,
                    EchoFlowerOpenScreenMessage::new
            );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(EchoFlowerOpenScreenMessage msg, IPayloadContext context) {
        if (!(context instanceof ClientPayloadContext clientCtx)) return;

        clientCtx.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            mc.setScreen(new EchoFlowerEditScreen(msg.getPos(), msg.getMessage()));
        });
    }
}
