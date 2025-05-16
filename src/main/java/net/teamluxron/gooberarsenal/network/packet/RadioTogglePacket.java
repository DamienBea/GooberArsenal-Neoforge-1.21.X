package net.teamluxron.gooberarsenal.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.teamluxron.gooberarsenal.GooberArsenal;

public class RadioTogglePacket extends CustomPacketPayload {
    public static final ResourceLocation TYPE = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "radio_toggle");

    private final boolean isOn;

    public RadioTogglePacket(boolean isOn) {
        this.isOn = isOn;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this.isOn);
    }

    public static RadioTogglePacket decode(FriendlyByteBuf buffer) {
        return new RadioTogglePacket(buffer.readBoolean());
    }

    public static void handle(RadioTogglePacket packet, IPayloadContext context) {
        // Handle packet on the client side: Play or stop sound based on `isOn`
        if (context.flow() == PacketFlow.CLIENTBOUND) {
            // Logic for playing or stopping sound
            if (packet.isOn) {
                // Play sound
            } else {
                // Stop sound
            }
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return null;
    }
}