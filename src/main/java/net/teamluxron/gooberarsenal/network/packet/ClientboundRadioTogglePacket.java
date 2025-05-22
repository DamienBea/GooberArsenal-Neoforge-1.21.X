package net.teamluxron.gooberarsenal.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.blocks.custom.RadioBlock;
import net.teamluxron.gooberarsenal.blocks.entity.RadioBlockEntity;
import net.teamluxron.gooberarsenal.client.sound.ClientSoundManager;
import net.teamluxron.gooberarsenal.sound.ModSounds;

public record ClientboundRadioTogglePacket(BlockPos pos, boolean isOn, boolean isBroken) implements CustomPacketPayload {
    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "clientbound_radio_toggle");

    public static final Type<ClientboundRadioTogglePacket> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundRadioTogglePacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    ClientboundRadioTogglePacket::pos,
                    StreamCodec.of( // For isOn (boolean)
                            (buf, value) -> buf.writeBoolean(value),
                            buf -> buf.readBoolean()
                    ),
                    ClientboundRadioTogglePacket::isOn,
                    StreamCodec.of( // For isBroken (boolean)
                            (buf, value) -> buf.writeBoolean(value),
                            buf -> buf.readBoolean()
                    ),
                    ClientboundRadioTogglePacket::isBroken,
                    ClientboundRadioTogglePacket::new
            );

    public static void handle(ClientboundRadioTogglePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            SoundEvent sound = packet.isBroken()
                    ? ModSounds.BROKEN_RADIO.get()
                    : ModSounds.RADIO.get();

            if (packet.isOn()) {
                ClientSoundManager.playRadioSound(packet.pos(), sound);
            } else {
                ClientSoundManager.stopRadioSound(packet.pos());
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return null;
    }
}