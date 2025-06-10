package net.teamluxron.gooberarsenal.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.entity.client.animations.PeaShooterAnimations;
import net.teamluxron.gooberarsenal.entity.custom.PeaShooterEntity;

public class PeaShooterModel extends HierarchicalModel<PeaShooterEntity> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "pea_shooter"), "main");
    private final ModelPart stem;
    private final ModelPart stemdown;
    private final ModelPart stemup;
    private final ModelPart head;
    private final ModelPart leaf;
    private final ModelPart throatgoat;
    private final ModelPart lips;
    private final ModelPart petals;
    private final ModelPart petalsnw;
    private final ModelPart petalsne;
    private final ModelPart petalssw;
    private final ModelPart petalsse;

    public PeaShooterModel(ModelPart root) {
        this.stem = root.getChild("stem");
        this.stemdown = this.stem.getChild("stemdown");
        this.stemup = this.stemdown.getChild("stemup");
        this.head = this.stemup.getChild("head");
        this.leaf = this.head.getChild("leaf");
        this.throatgoat = this.head.getChild("throatgoat");
        this.lips = this.throatgoat.getChild("lips");
        this.petals = this.stem.getChild("petals");
        this.petalsnw = this.petals.getChild("petalsnw");
        this.petalsne = this.petals.getChild("petalsne");
        this.petalssw = this.petals.getChild("petalssw");
        this.petalsse = this.petals.getChild("petalsse");
    }

    @Override
    public ModelPart root() {
        return this.stem; // Return stem as the root
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition stem = partdefinition.addOrReplaceChild("stem", CubeListBuilder.create(), PartPose.offset(0.0F, 20.35F, -0.25F));

        PartDefinition stemdown = stem.addOrReplaceChild("stemdown", CubeListBuilder.create(), PartPose.offset(0.0F, 3.65F, 0.25F));

        PartDefinition cube_r1 = stemdown.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 14).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -0.5F, -0.1745F, 0.0F, 0.0F));

        PartDefinition stemup = stemdown.addOrReplaceChild("stemup", CubeListBuilder.create(), PartPose.offset(0.0F, -2.75F, 0.5F));

        PartDefinition cube_r2 = stemup.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.0F, -0.25F, 0.2182F, 0.0F, 0.0F));

        PartDefinition head = stemup.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -2.8731F, -0.5125F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 0).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.9731F, -0.2375F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 3).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5205F, 1.7204F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.9795F, -0.7204F, 0.2182F, 0.0F, 0.0F));

        PartDefinition leaf = head.addOrReplaceChild("leaf", CubeListBuilder.create(), PartPose.offset(0.0F, -4.3985F, 2.073F));

        PartDefinition cube_r6 = leaf.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(16, 6).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.5F, 0.6981F, 0.0F, 0.0F));

        PartDefinition throatgoat = head.addOrReplaceChild("throatgoat", CubeListBuilder.create(), PartPose.offset(0.0F, -2.2196F, -2.5276F));

        PartDefinition cube_r7 = throatgoat.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(8, 14).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition lips = throatgoat.addOrReplaceChild("lips", CubeListBuilder.create(), PartPose.offset(0.0F, 0.266F, -2.2523F));

        PartDefinition cube_r8 = lips.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(1, 10).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0377F, 0.1678F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r9 = lips.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 14).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0377F, -0.1678F, 0.2182F, 0.0F, 0.0F));

        PartDefinition petals = stem.addOrReplaceChild("petals", CubeListBuilder.create(), PartPose.offset(0.0F, 3.8F, 0.25F));

        PartDefinition petalsnw = petals.addOrReplaceChild("petalsnw", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r10 = petalsnw.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(-4, 28).addBox(0.0F, 0.0F, -4.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, -0.2618F));

        PartDefinition petalsne = petals.addOrReplaceChild("petalsne", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r11 = petalsne.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(-4, 23).addBox(-4.0F, 0.0F, -4.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2533F, 0.067F, 0.2533F));

        PartDefinition petalssw = petals.addOrReplaceChild("petalssw", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r12 = petalssw.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(5, 23).addBox(0.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2533F, 0.067F, -0.2533F));

        PartDefinition petalsse = petals.addOrReplaceChild("petalsse", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r13 = petalsse.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(5, 28).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2533F, -0.067F, 0.2533F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }



    private void applyHeadRotation(float netHeadYaw, float headPitch) {
        netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

        // Apply rotations directly to head bone
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public void setupAnim(PeaShooterEntity entity,
                          float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        // Reset all parts starting from stem
        this.root().getAllParts().forEach(ModelPart::resetPose);

        // Apply head rotation for targeting
        this.applyHeadRotation(netHeadYaw, headPitch);

        // Handle animations
        if (entity.idleAnimationState.isStarted()) {
            this.animate(entity.idleAnimationState, PeaShooterAnimations.IDLE, ageInTicks, 1f);
        }

        if (entity.attackAnimationState.isStarted()) {
            this.animate(entity.attackAnimationState, PeaShooterAnimations.SHOOTING, ageInTicks, 1f);
        }
    }

}