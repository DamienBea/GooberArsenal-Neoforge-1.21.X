package net.teamluxron.gooberarsenal.blocks.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.teamluxron.gooberarsenal.blocks.entity.function.FieldSwordBlockEntity;

public class FieldSwordRenderer implements BlockEntityRenderer<FieldSwordBlockEntity> {
    private final ItemRenderer itemRenderer;

    public FieldSwordRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(FieldSwordBlockEntity be, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (be.swordItem == null) return;

        ItemStack swordStack = new ItemStack(be.swordItem);

        poseStack.pushPose();

        // Base position
        poseStack.translate(0.5f, 0.0f, 0.5f);

        // Pivot adjustments
        poseStack.translate(-0.5f, -0.5f, 0f);

        // Apply rotations
        poseStack.mulPose(Axis.YP.rotationDegrees(be.getSecondaryYRot()));
        poseStack.mulPose(Axis.ZP.rotationDegrees(be.getTotalRotationZ()));
        poseStack.mulPose(Axis.XP.rotationDegrees(be.getTotalRotationX()));
        poseStack.mulPose(Axis.YP.rotationDegrees(be.getTotalRotationY()));

        // Apply scale
        float scale = be.getTotalScale();
        poseStack.scale(scale, scale, scale);

        // Final pivot adjustment
        poseStack.translate(0f, -0.5f, 0f);

        // Render with glow effect
        itemRenderer.renderStatic(
                swordStack,
                ItemDisplayContext.FIXED,
                LightTexture.FULL_BRIGHT,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                bufferSource,
                be.getLevel(),
                0
        );

        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(FieldSwordBlockEntity be) {
        return true;
    }
}