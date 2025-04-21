package net.teamluxron.gooberarsenal.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.teamluxron.gooberarsenal.blocks.entity.ForgingAnvilBlockEntity;

public class ForgingAnvilRenderer implements BlockEntityRenderer<ForgingAnvilBlockEntity> {
    private final ItemRenderer itemRenderer;

    public ForgingAnvilRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ForgingAnvilBlockEntity be, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        // Use getStoredItem() instead of getItem()
        ItemStack stack = be.getStoredItem();

        if (!stack.isEmpty()) {
            poseStack.pushPose();
            // Position above the anvil
            poseStack.translate(0.5, 1.1, 0.5);
            // Scale down the item
            poseStack.scale(0.5f, 0.5f, 0.5f);
            // Render the item
            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED,
                    packedLight, packedOverlay, poseStack, buffer, be.getLevel(), 0);
            poseStack.popPose();
        }
    }
}
