package net.teamluxron.gooberarsenal.blocks.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.entity.FieldSword;
import software.bernie.geckolib.model.GeoModel;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class FieldSwordModel extends GeoModel<FieldSword> {



    @Override
    public ResourceLocation getModelResource(FieldSword animatable) {
        int index = animatable.getModelIndex();
        return fromNamespaceAndPath(GooberArsenal.MOD_ID,
                "geo/field_sword_" + (index + 1) + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FieldSword animatable) {
        return fromNamespaceAndPath(GooberArsenal.MOD_ID, "textures/block/field_sword.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FieldSword animatable) {
        return fromNamespaceAndPath(GooberArsenal.MOD_ID, "animations/field_sword.animation.json");
    }
}