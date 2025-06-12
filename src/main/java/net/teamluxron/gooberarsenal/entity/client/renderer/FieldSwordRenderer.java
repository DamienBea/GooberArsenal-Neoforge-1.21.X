package net.teamluxron.gooberarsenal.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.blocks.entity.FieldSwordBlockEntity;

public class FieldSwordRenderer implements BlockEntityRenderer<FieldSwordBlockEntity> {
    private final ItemRenderer itemRenderer;

    public FieldSwordRenderer(BlockEntityRendererProvider.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(FieldSwordBlockEntity be, float partialTicks,
                       PoseStack ms, MultiBufferSource buffer, int light, int overlay) {

        BlockState below = be.getLevel().getBlockState(be.getBlockPos().below());

        ms.pushPose();
        ms.scale(1f, 1f / 16f, 1f); // Flatten Y to 1px
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                below, ms, buffer, light, overlay
        );
        ms.popPose();

        ms.pushPose();

        ms.translate(1.0, 1.5625, 0.5);
        ms.translate(0, 0, -0.5);

        ms.mulPose(Axis.YP.rotationDegrees(be.secondaryYRotDeg));

        ms.mulPose(Axis.XP.rotationDegrees(90f + be.rotationXDeg));
        ms.mulPose(Axis.YP.rotationDegrees(90f + be.rotationYDeg));
        ms.mulPose(Axis.ZP.rotationDegrees(135f + be.rotationZDeg));

        ms.translate(0.0, 0.0, 0.0);

        float scale = be.lengthScale / 4f;
        ms.scale(scale, scale, scale);

        VertexConsumer glow = buffer.getBuffer(RenderType.eyes(
                Minecraft.getInstance().getItemRenderer().getItemModelShaper()
                        .getItemModel(be.swordItem).getParticleIcon().atlasLocation()
        ));
        itemRenderer.renderStatic(
                new ItemStack(be.swordItem),
                ItemDisplayContext.NONE,
                0xF000F0, overlay, ms, buffer, be.getLevel(), 0
        );

        ms.popPose();
    }
}
