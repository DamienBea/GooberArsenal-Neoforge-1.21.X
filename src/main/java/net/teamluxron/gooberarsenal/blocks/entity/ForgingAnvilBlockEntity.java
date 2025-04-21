package net.teamluxron.gooberarsenal.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.teamluxron.gooberarsenal.recipe.ForgingRecipe;
import net.teamluxron.gooberarsenal.recipe.ForgingRecipeInput;
import net.teamluxron.gooberarsenal.recipe.ModRecipes;

import java.util.Optional;

public class ForgingAnvilBlockEntity extends BlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    };

    public ForgingAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORGING_ANVIL_BE.get(), pos, state);
    }

    public ItemStack getStoredItem() {
        return itemHandler.getStackInSlot(0);
    }

    public void setStoredItem(ItemStack stack) {
        itemHandler.setStackInSlot(0, stack);
    }

    public Optional<RecipeHolder<ForgingRecipe>> getCurrentRecipe() {
        if (level == null) return Optional.empty();

        return level.getRecipeManager()
                .getRecipeFor(ModRecipes.FORGING_TYPE.get(),
                        new ForgingRecipeInput(getStoredItem()),
                        level);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.put("inventory", itemHandler.serializeNBT(provider));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        itemHandler.deserializeNBT(provider, tag.getCompound("inventory"));
    }
}