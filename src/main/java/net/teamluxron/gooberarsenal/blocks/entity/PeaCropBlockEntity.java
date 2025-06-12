package net.teamluxron.gooberarsenal.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PeaCropBlockEntity extends BlockEntity {
    private static final String PLANTER_KEY = "Planter";
    @Nullable
    private UUID planter;

    public PeaCropBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MAGICAL_BEAN_CROP_BE.get(), pos, state);
    }

    public void setPlanter(UUID uuid) {
        this.planter = uuid;
        setChanged();
    }

    @Nullable
    public UUID getPlanter() {
        return planter;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (planter != null) {
            tag.putUUID(PLANTER_KEY, planter);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.hasUUID(PLANTER_KEY)) {
            this.planter = tag.getUUID(PLANTER_KEY);
        }
    }

}