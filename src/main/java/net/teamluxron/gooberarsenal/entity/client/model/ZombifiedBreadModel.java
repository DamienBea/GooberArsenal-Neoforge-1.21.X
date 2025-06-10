package net.teamluxron.gooberarsenal.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.entity.client.animations.ZombifiedBreadAnimations;
import net.teamluxron.gooberarsenal.entity.custom.ZombifiedBreadEntity;

public class ZombifiedBreadModel<T extends ZombifiedBreadEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "zombified_bread"), "main");
    private final ModelPart mainbody;
    private final ModelPart mid;
    private final ModelPart hind;
    private final ModelPart front;
    private final ModelPart jawup;
    private final ModelPart jawdown;

    public ZombifiedBreadModel(ModelPart root) {
        this.mainbody = root.getChild("mainbody");
        this.mid = this.mainbody.getChild("mid");
        this.hind = this.mainbody.getChild("hind");
        this.front = this.mainbody.getChild("front");
        this.jawup = this.front.getChild("jawup");
        this.jawdown = this.front.getChild("jawdown");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition mainbody = partdefinition.addOrReplaceChild("mainbody", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition mid = mainbody.addOrReplaceChild("mid", CubeListBuilder.create().texOffs(24, 30).addBox(1.1F, 0.8F, 1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(1.05F, -0.9F, -1.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(1.05F, 0.35F, 1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(1.05F, -0.6F, 2.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-2.05F, -0.65F, -2.45F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-2.05F, 1.05F, -1.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-0.05F, 1.05F, 1.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-2.05F, 0.6F, 2.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-2.05F, -0.7F, 1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 30).addBox(-2.05F, 0.8F, 0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 30).addBox(-2.05F, -2.05F, 3.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 30).addBox(-0.3F, -2.05F, -1.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 30).addBox(1.05F, 0.4F, -1.4F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 30).addBox(-1.05F, -2.05F, 0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 30).addBox(1.05F, -1.35F, -0.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.05F, 0.0F));

        PartDefinition hind = mainbody.addOrReplaceChild("hind", CubeListBuilder.create().texOffs(16, 11).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(24, 30).addBox(-2.05F, -0.45F, 1.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 30).addBox(-2.05F, -2.05F, 1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 30).addBox(-0.65F, -0.25F, 2.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 30).addBox(1.05F, -0.35F, 1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 30).addBox(1.05F, 0.75F, 2.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(1.05F, -0.2F, 0.15F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-2.05F, 1.05F, 2.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(0.65F, -2.05F, 1.65F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition front = mainbody.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 11).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(1.05F, -0.6F, -0.45F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 30).addBox(1.05F, 0.65F, -0.35F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-2.05F, 0.6F, -0.45F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-1.25F, -2.05F, -1.45F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

        PartDefinition jawup = front.addOrReplaceChild("jawup", CubeListBuilder.create().texOffs(16, 18).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(0.95F, 1.1F, -0.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-1.05F, 1.1F, -1.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-2.05F, 0.95F, -0.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(0.9F, 0.95F, -1.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -2.0F));

        PartDefinition jawdown = front.addOrReplaceChild("jawdown", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(0.55F, -2.1F, -1.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-1.85F, -2.1F, -1.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -2.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(ZombifiedBreadEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        applyHeadRotation(netHeadYaw, headPitch);

        boolean isMoving = limbSwingAmount > 0.01f;
        boolean isAttacking = entity.swinging || entity.getTarget() != null;

        if (isAttacking) {
            this.animate(entity.attackAnimationState, ZombifiedBreadAnimations.ZOMBIFIED_BREAD_ATTACKING, ageInTicks, 1f);
            this.animate(entity.chasingAnimationState, ZombifiedBreadAnimations.ZOMBIFIED_BREAD_ATTACKING, ageInTicks, 1f);
        } else if (isMoving) {
            this.animateWalk(ZombifiedBreadAnimations.ZOMBIFIED_BREAD_IDLE, limbSwing, limbSwingAmount, 2f, 2.5f);
        } else {
            this.animate(entity.idleAnimationState, ZombifiedBreadAnimations.ZOMBIFIED_BREAD_IDLE, ageInTicks, 1f);
        }
    }



    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.front.yRot = headYaw * ((float)Math.PI / 180f);
        this.front.xRot = headPitch *  ((float)Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        mainbody.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return mainbody;
    }
}