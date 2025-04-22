package net.teamluxron.gooberarsenal.blocks.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.teamluxron.gooberarsenal.blocks.entity.ForgingAnvilBlockEntity;

public class ForgingAnvilRenderer implements BlockEntityRenderer<ForgingAnvilBlockEntity> {
    public ForgingAnvilRenderer(BlockEntityRendererProvider.Context context) {
    }
    @Override
    public void render(ForgingAnvilBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = blockEntity.getStoredItem();

        if (!stack.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.5f, 0.8f, 0.5f);
            poseStack.scale(0.5f, 0.5f, 0.5f);
            poseStack.mulPose(Axis.XP.rotationDegrees(90f));

            // Use the same lighting calculation as pedestal
            int lightLevel = getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos());

            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED,
                    lightLevel, OverlayTexture.NO_OVERLAY,
                    poseStack, bufferSource, blockEntity.getLevel(), 1);
            poseStack.popPose();
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}