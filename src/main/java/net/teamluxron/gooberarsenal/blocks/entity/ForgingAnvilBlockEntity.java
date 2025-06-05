package net.teamluxron.gooberarsenal.blocks.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.teamluxron.gooberarsenal.recipe.ForgingRecipe;
import net.teamluxron.gooberarsenal.recipe.ForgingRecipeInput;
import net.teamluxron.gooberarsenal.recipe.ModRecipeTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public class ForgingAnvilBlockEntity extends BlockEntity {
    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    // Client-side rendering fields
    @Nullable
    private static ItemRenderer itemRenderer;
    private float rotation = 0;

    public ForgingAnvilBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FORGING_ANVIL_BE.get(), pos, blockState);
    }

    public void clearContents() {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public Optional<RecipeHolder<ForgingRecipe>> getCurrentRecipe() {
        if (level == null) return Optional.empty();
        return level.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.FORGING.get(),
                        new ForgingRecipeInput(inventory.getStackInSlot(0)),
                        level);
    }

    public ItemStack getStoredItem() {
        return inventory.getStackInSlot(0);
    }

    public void setStoredItem(ItemStack stack) {
        inventory.setStackInSlot(0, stack);
    }

    // Rendering methods
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        ItemStack stack = inventory.getStackInSlot(0);
        if (!stack.isEmpty()) {
            if (itemRenderer == null && level != null && level.isClientSide()) {
                itemRenderer = Minecraft.getInstance().getItemRenderer();
            }

            if (itemRenderer != null) {
                poseStack.pushPose();
                poseStack.translate(0.5, 1.1, 0.5);
                poseStack.scale(0.5f, 0.5f, 0.5f);

                itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED,
                        packedLight, OverlayTexture.NO_OVERLAY,
                        poseStack, buffer, level, 0);

                poseStack.popPose();
            }
        }
    }

    public void tick() {
        if (level != null && level.isClientSide()) {
            rotation += 0.5f;
            if (rotation >= 360) {
                rotation = 0;
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
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