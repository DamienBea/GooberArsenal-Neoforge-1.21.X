package net.teamluxron.gooberarsenal.entity.client.renderer;

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
import net.teamluxron.gooberarsenal.blocks.entity.FieldSwordBlockEntity;

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
        poseStack.translate(0.5f, 0.01f, 0.5f);

        poseStack.mulPose(Axis.YP.rotationDegrees(be.getSecondaryYRot()));

        poseStack.mulPose(Axis.XP.rotationDegrees(be.getTotalRotationX()));
        poseStack.mulPose(Axis.YP.rotationDegrees(be.getTotalRotationY()));
        poseStack.mulPose(Axis.ZP.rotationDegrees(be.getTotalRotationZ()));

        float scale = be.getTotalScale();
        poseStack.scale(scale, scale, scale);

        poseStack.translate(0f, 0.5f, 0f);

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
        return true; // Ensures large swords render properly
    }
}