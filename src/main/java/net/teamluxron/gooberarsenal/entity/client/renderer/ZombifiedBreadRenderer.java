package net.teamluxron.gooberarsenal.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.entity.client.model.ZombifiedBreadModel;
import net.teamluxron.gooberarsenal.entity.custom.ZombifiedBreadEntity;

public class ZombifiedBreadRenderer extends MobRenderer<ZombifiedBreadEntity, ZombifiedBreadModel<ZombifiedBreadEntity>> {

    public ZombifiedBreadRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombifiedBreadModel<>(context.bakeLayer(ZombifiedBreadModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ZombifiedBreadEntity zombifiedBreadEntity) {
        return ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "textures/entity/zombified_bread.png");
    }

    @Override
    public void render(ZombifiedBreadEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
