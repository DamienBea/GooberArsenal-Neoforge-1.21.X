package net.teamluxron.gooberarsenal.blocks.entity.function;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.network.ModMessages;
import net.teamluxron.gooberarsenal.network.packet.ClientboundRadioTogglePacket;
import net.teamluxron.gooberarsenal.network.packet.PlayRadioSoundPacket;
import net.teamluxron.gooberarsenal.network.packet.StopRadioSoundPacket;
import org.jetbrains.annotations.Nullable;

public class BrokenRadioBlockEntity extends BlockEntity {
    private static final int SOUND_INTERVAL = 880;
    public boolean isPlaying = false;
    private long nextPlayTick = 0L;

    public BrokenRadioBlockEntity(BlockPos pos, BlockState state) {
        super((BlockEntityType) ModBlockEntities.BROKEN_RADIO_BE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BrokenRadioBlockEntity blockEntity) {
        if (!level.isClientSide && blockEntity.isPlaying && level.getGameTime() >= blockEntity.nextPlayTick) {
            blockEntity.playSound();
            blockEntity.nextPlayTick = level.getGameTime() + 880L;
        }

    }

    public void toggle() {
        this.isPlaying = !this.isPlaying;
        this.setChanged();
        if (this.level != null && !this.level.isClientSide) {
            if (this.isPlaying) {
                this.nextPlayTick = this.level.getGameTime() + 880L;
                this.playSound();
            } else {
                this.stopSound();
            }

            this.syncToClients();
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }

    }

    public void setEnabled(boolean enabled) {
        if (this.isPlaying != enabled) {
            this.isPlaying = enabled;
            this.setChanged();
            if (this.level != null && !this.level.isClientSide) {
                if (enabled) {
                    this.nextPlayTick = this.level.getGameTime() + 880L;
                    this.playSound();
                } else {
                    this.stopSound();
                }

                this.syncToClients();
            }
        }

    }

    private void playSound() {
        if (this.level != null && !this.level.isClientSide) {
            for(ServerPlayer player : ((ServerLevel)this.level).getPlayers((playerx) -> playerx.distanceToSqr(this.worldPosition.getCenter()) < (double)4225.0F)) {
                ModMessages.sendToPlayer(player, new PlayRadioSoundPacket(this.worldPosition, true));
            }

        }
    }

    public void stopSound() {
        if (this.level != null && !this.level.isClientSide) {
            for(ServerPlayer player : ((ServerLevel)this.level).players()) {
                if (player.distanceToSqr(this.worldPosition.getCenter()) < (double)256.0F) {
                    ModMessages.sendToPlayer(player, new StopRadioSoundPacket(this.worldPosition));
                }
            }

        }
    }

    public void stopRadio() {
        if (this.isPlaying) {
            this.isPlaying = false;
            this.stopSound();
            this.setChanged();
            this.syncToClients();
        }

    }

    public void onBlockDestroyed() {
        if (this.level != null && !this.level.isClientSide) {
            for(ServerPlayer player : ((ServerLevel)this.level).getPlayers((playerx) -> playerx.distanceToSqr(this.worldPosition.getCenter()) < (double)256.0F)) {
                ModMessages.sendToPlayer(player, new StopRadioSoundPacket(this.worldPosition));
            }

            this.isPlaying = false;
            this.setChanged();
        }

    }

    public void syncToClients() {
        Level var2 = this.level;
        if (var2 instanceof ServerLevel serverLevel) {
            ModMessages.sendToAllTracking(this, new ClientboundRadioTogglePacket(this.getBlockPos(), this.isPlaying, false));
        }

    }

    public void onLoad() {
        super.onLoad();
        if (this.level != null && !this.level.isClientSide && this.isPlaying) {
            this.syncToClients();
        }

    }

    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putBoolean("Playing", this.isPlaying);
        tag.putLong("NextPlayTick", this.nextPlayTick);
    }

    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.isPlaying = tag.getBoolean("Playing");
        this.nextPlayTick = tag.getLong("NextPlayTick");
    }

    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return this.saveWithoutMetadata(provider);
    }
}
