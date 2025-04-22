package net.teamluxron.gooberarsenal.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.blocks.custom.RadioBlock;
import net.teamluxron.gooberarsenal.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class RadioBlockEntity extends BlockEntity {
    private static final int SOUND_INTERVAL = 22 * 20; // 22 seconds in ticks
    private int tickCount = 0;
    private boolean isPlaying = false;

    public RadioBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.RADIO_BE.get(), pos, blockState);
    }

    public void tick() {
        if (!isPlaying || level == null || level.isClientSide) return;

        if (++tickCount >= SOUND_INTERVAL) {
            tickCount = 0;
            level.playSound(null, worldPosition,
                    ModSounds.RADIO.get(),
                    SoundSource.RECORDS,
                    1.0f, 1.0f);

            // Visual feedback
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.NOTE,
                        worldPosition.getX() + 0.5,
                        worldPosition.getY() + 1.2,
                        worldPosition.getZ() + 0.5,
                        3, 0.2, 0.2, 0.2, 0.0);
            }
        }
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
        this.tickCount = 0; // Reset counter on state change
        this.setChanged();

        // Update block state if needed
        if (level != null && !level.isClientSide) {
            BlockState state = level.getBlockState(worldPosition);
            if (state.getValue(RadioBlock.POWERED) != playing) {
                level.setBlock(worldPosition, state.setValue(RadioBlock.POWERED, playing), Block.UPDATE_ALL);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putBoolean(PLAYING_TAG, isPlaying);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        this.isPlaying = tag.getBoolean(PLAYING_TAG);
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