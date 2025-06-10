package net.teamluxron.gooberarsenal.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.entity.client.model.PeaShooterModel;
import net.teamluxron.gooberarsenal.entity.custom.PeaShooterEntity;

public class PeaShooterRenderer extends MobRenderer<PeaShooterEntity, PeaShooterModel> {

    public PeaShooterRenderer(EntityRendererProvider.Context context) {
        super(context, new PeaShooterModel(context.bakeLayer(PeaShooterModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PeaShooterEntity peaShooterEntity) {
        return ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "textures/entity/pea_shooter.png");
    }

    @Override
    public void render(PeaShooterEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}

