package net.teamluxron.gooberarsenal.blocks.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.teamluxron.gooberarsenal.blocks.entity.FieldSword;
import net.teamluxron.gooberarsenal.blocks.entity.model.FieldSwordModel;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;


public class FieldSwordRenderer extends GeoBlockRenderer<FieldSword> {

    public FieldSwordRenderer(BlockEntityRendererProvider.Context context) {
        super(new FieldSwordModel());
    }

    @Override
    public void renderRecursively(
            PoseStack poseStack,
            FieldSword animatable,
            GeoBone bone,
            RenderType renderType,
            MultiBufferSource bufferSource,
            VertexConsumer buffer,
            boolean isReRender,
            float partialTick,
            int packedLight,
            int packedOverlay,
            int colour
    ) {
        if (!isReRender && "sword".equals(bone.getName())) {
            float rotX = (float) Math.toRadians(animatable.getRandomXRotation());
            float rotY = (float) Math.toRadians(animatable.getRandomYRotation());
            float rotZ = (float) Math.toRadians(animatable.getRandomZRotation());
            float scale = animatable.getRandomUniformScale();

            bone.setRotX(rotX);
            bone.setRotY(rotY);
            bone.setRotZ(rotZ);

            bone.setScaleX(scale);
            bone.setScaleY(scale);
            bone.setScaleZ(scale);

            packedLight = LightTexture.pack(15, 15);
        }

        // Apply 2x scale to "sword" and its children ("bone", "bone2", etc.)
        if ("sword".equals(bone.getName())
                || "bone".equals(bone.getName())
                || "bone2".equals(bone.getName())) {
            bone.setScaleX(bone.getScaleX() * 2.0F);
            bone.setScaleY(bone.getScaleY() * 2.0F);
            bone.setScaleZ(bone.getScaleZ() * 2.0F);
        }

        super.renderRecursively(
                poseStack, animatable, bone, renderType, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, colour
        );
    }



    @Override
    public RenderType getRenderType(FieldSword animatable, ResourceLocation texture,
                                    MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}