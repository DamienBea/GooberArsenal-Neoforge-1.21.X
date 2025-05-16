package net.teamluxron.gooberarsenal.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.network.ModMessages;
import net.teamluxron.gooberarsenal.network.packet.PlayRadioSoundPacket;
import net.teamluxron.gooberarsenal.network.packet.StopRadioSoundPacket;
import org.jetbrains.annotations.Nullable;

public class RadioBlockEntity extends BlockEntity {
    private static final int SOUND_INTERVAL = 440; // ~22 seconds
    private boolean isPlaying = false;
    private long nextPlayTick = 0;

    public RadioBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RadioBlockEntity blockEntity) {
        if (!level.isClientSide && blockEntity.isPlaying && level.getGameTime() >= blockEntity.nextPlayTick) {
            blockEntity.playSound();
            blockEntity.nextPlayTick = level.getGameTime() + SOUND_INTERVAL;
        }
    }

    public void toggle() {
        this.isPlaying = !this.isPlaying;
        this.setChanged();

        if (level != null && !level.isClientSide) {
            if (this.isPlaying) {
                this.nextPlayTick = level.getGameTime() + SOUND_INTERVAL;
                playSound(); // Play immediately on toggle
            } else {
                stopSound();
            }

            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    private void playSound() {
        if (level == null || !isPlaying || level.isClientSide) return;

        double x = worldPosition.getX() + 0.5;
        double y = worldPosition.getY() + 0.5;
        double z = worldPosition.getZ() + 0.5;

        for (ServerPlayer player : ((ServerLevel) level).players()) {
            if (player.distanceToSqr(x, y, z) < 256) {
                ModMessages.sendToPlayer(player, new PlayRadioSoundPacket(worldPosition));
            }
        }
    }

    private void stopSound() {
        if (level == null || level.isClientSide) return;

        for (ServerPlayer player : ((ServerLevel) level).players()) {
            if (player.distanceToSqr(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ()) < 256) {
                ModMessages.sendToPlayer(player, new StopRadioSoundPacket(worldPosition));
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, net.minecraft.core.HolderLookup.Provider provider) {
        tag.putBoolean("Playing", isPlaying);
        tag.putLong("NextPlayTick", nextPlayTick);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, net.minecraft.core.HolderLookup.Provider provider) {
        this.isPlaying = tag.getBoolean("Playing");
        this.nextPlayTick = tag.getLong("NextPlayTick");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(net.minecraft.core.HolderLookup.Provider provider) {
        return saveWithoutMetadata(provider);
    }
}