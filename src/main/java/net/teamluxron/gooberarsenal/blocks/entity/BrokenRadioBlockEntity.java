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
import net.teamluxron.gooberarsenal.network.packet.ClientboundRadioTogglePacket;
import net.teamluxron.gooberarsenal.network.packet.PlayRadioSoundPacket;
import net.teamluxron.gooberarsenal.network.packet.StopRadioSoundPacket;
import org.jetbrains.annotations.Nullable;

public class BrokenRadioBlockEntity extends BlockEntity {
    private static final int SOUND_INTERVAL = 880;
    public boolean isPlaying = false;
    private long nextPlayTick = 0;

    public BrokenRadioBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BrokenRadioBlockEntity blockEntity) {
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
                playSound();
            } else {
                stopSound();
            }
            syncToClients();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    public void setEnabled(boolean enabled) {
        if (isPlaying != enabled) {
            isPlaying = enabled;
            setChanged();

            if (level != null && !level.isClientSide) {
                if (enabled) {
                    nextPlayTick = level.getGameTime() + SOUND_INTERVAL;
                    playSound();
                } else {
                    stopSound();
                }
                syncToClients();
            }
        }
    }

    private void playSound() {
        if (level == null || level.isClientSide) return;

        for (ServerPlayer player : ((ServerLevel) level).getPlayers(player ->
                player.distanceToSqr(worldPosition.getCenter()) < (65.0 * 65.0))) {
            ModMessages.sendToPlayer(player, new PlayRadioSoundPacket(worldPosition, true));
        }

    }

    public void stopSound() {
        if (level == null || level.isClientSide) return;

        for (ServerPlayer player : ((ServerLevel) level).players()) {
            if (player.distanceToSqr(worldPosition.getCenter()) < 256) {
                ModMessages.sendToPlayer(player, new StopRadioSoundPacket(worldPosition));
            }
        }
    }

    public void stopRadio() {
        if (isPlaying) {
            isPlaying = false;
            stopSound();
            setChanged();
            syncToClients();
        }
    }

    public void onBlockDestroyed() {
        if (level != null && !level.isClientSide) {
            // Send stop sound to all nearby players
            for (ServerPlayer player : ((ServerLevel) level).getPlayers(player ->
                    player.distanceToSqr(worldPosition.getCenter()) < 256)) {
                ModMessages.sendToPlayer(player, new StopRadioSoundPacket(worldPosition));
            }
            isPlaying = false;
            setChanged();
        }
    }

    public void syncToClients() {
        if (level instanceof ServerLevel serverLevel) {
            ModMessages.sendToAllTracking(this, new ClientboundRadioTogglePacket(getBlockPos(), this.isPlaying));
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level != null && !level.isClientSide && isPlaying) {
            syncToClients();
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