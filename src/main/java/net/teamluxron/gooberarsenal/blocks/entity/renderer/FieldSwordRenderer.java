package net.teamluxron.gooberarsenal.blocks.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.blocks.entity.FieldSword;
import net.teamluxron.gooberarsenal.blocks.entity.model.FieldSwordModel;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import java.util.List;


public class FieldSwordRenderer extends GeoBlockRenderer<FieldSword> {

    private static final List<ResourceLocation> SWORD_TEXTURES = List.of(
            ResourceLocation.fromNamespaceAndPath("gooberarsenal", "textures/item/rose_quartz_sword.png"),
            ResourceLocation.fromNamespaceAndPath("gooberarsenal", "textures/item/thorn_of_the_dead_gods.png"),
            ResourceLocation.fromNamespaceAndPath("gooberarsenal", "textures/item/tungsten_sword.png"),
            ResourceLocation.fromNamespaceAndPath("gooberarsenal", "textures/item/soulphyre_sword.png"),
            ResourceLocation.fromNamespaceAndPath("gooberarsenal", "textures/item/bee_bunny_basher.png")
    );

    public FieldSwordRenderer(BlockEntityRendererProvider.Context context) {
        super(new FieldSwordModel());
    }

    @Override
    public RenderType getRenderType(FieldSword animatable, ResourceLocation texture,
                                    MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }

    @Override
    public ResourceLocation getTextureLocation(FieldSword animatable) {
        return getSwordTextureFor(animatable);
    }

    @Override
    public void renderRecursively(PoseStack poseStack,
                                  FieldSword animatable,
                                  GeoBone bone,
                                  RenderType renderType,
                                  MultiBufferSource bufferSource,
                                  VertexConsumer buffer,
                                  boolean isReRender,
                                  float partialTick,
                                  int packedLight,
                                  int packedOverlay,
                                  int colour) {

        RenderType override = renderType;

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

            override = RenderType.entityTranslucent(getSwordTextureFor(animatable));
        }

        if ("sword".equals(bone.getName())
//                || "bone".equals(bone.getName())
//                || "bone2".equals(bone.getName())
        ) {
            bone.setScaleX(bone.getScaleX() * 1.0F);
            bone.setScaleY(bone.getScaleY() * 1.0F);
            bone.setScaleZ(bone.getScaleZ() * 1.0F);
        }

        if (!isReRender && "base".equals(bone.getName())) {
            ResourceLocation blockTexture = getTextureOfBlockBelow(animatable);
            if (blockTexture != null) {
                override = RenderType.entityTranslucent(blockTexture);
            } else {
                return;
            }
        }

        super.renderRecursively(poseStack, animatable, bone, override, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, colour);
    }

    private ResourceLocation getSwordTextureFor(FieldSword animatable) {
        // Simple hash based on block position (you can make this persistent if needed)
        long hash = Math.abs(animatable.getBlockPos().asLong());
        int index = (int)(hash % SWORD_TEXTURES.size());
        return SWORD_TEXTURES.get(index);
    }

    private @Nullable ResourceLocation getTextureOfBlockBelow(FieldSword animatable) {
        BlockPos posBelow = animatable.getBlockPos().below();
        BlockState state = animatable.getLevel().getBlockState(posBelow);

        if (state.isAir()) return null;

        TextureAtlasSprite sprite = Minecraft.getInstance()
                .getBlockRenderer()
                .getBlockModel(state)
                .getParticleIcon();

        if (sprite == null) return null;

        return sprite.contents().name();
    }
}