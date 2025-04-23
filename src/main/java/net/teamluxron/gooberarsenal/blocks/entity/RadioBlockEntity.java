package net.teamluxron.gooberarsenal.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class RadioBlockEntity extends BlockEntity {
    private static final int SOUND_INTERVAL = 440; // 22 seconds
    private boolean isPlaying = false;
    private long nextPlayTick = 0;
    private boolean wasJustPlaced = true;

    private boolean needsToggleSound = false;

    public RadioBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void toggle() {
        this.isPlaying = !this.isPlaying;
        this.needsToggleSound = true;
        this.setChanged();

        if (this.isPlaying && level != null) {
            this.nextPlayTick = level.getGameTime() + SOUND_INTERVAL;
            scheduleSound();
        }
    }

    private void scheduleSound() {
        if (level == null || level.isClientSide) return;

        // Play toggle sound if needed
        if (needsToggleSound) {
            level.playSound(null, worldPosition,
                    isPlaying ? ModSounds.RADIO.get() : ModSounds.BUTTON.get(),
                    SoundSource.RECORDS,
                    isPlaying ? 1.0f : 0.5f,
                    1.0f);
            needsToggleSound = false;
        }

        // Schedule recurring sound
        if (isPlaying) {
            long currentTime = level.getGameTime();
            if (currentTime >= nextPlayTick) {
                level.playSound(null, worldPosition,
                        ModSounds.RADIO.get(),
                        SoundSource.RECORDS,
                        1.0f, 1.0f);

                nextPlayTick = currentTime + SOUND_INTERVAL;
            }
            level.scheduleTick(worldPosition, getBlockState().getBlock(),
                    (int)(nextPlayTick - currentTime));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putBoolean("Playing", isPlaying);
        tag.putLong("NextPlayTick", nextPlayTick);
        tag.putBoolean("WasJustPlaced", wasJustPlaced);
    }



    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        this.isPlaying = tag.getBoolean("Playing");
        this.nextPlayTick = tag.getLong("NextPlayTick");
        this.wasJustPlaced = tag.getBoolean("WasJustPlaced");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}