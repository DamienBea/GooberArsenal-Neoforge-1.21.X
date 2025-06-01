package net.teamluxron.gooberarsenal.blocks.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public class FieldSword extends BlockEntity implements GeoAnimatable {
    private final int modelIndex;
    private final float randomZRotation;
    private final float randomXRotation;
    private final float randomYRotation;
    private final float randomUniformScale;

    public FieldSword(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FIELD_SWORD_BE.get(), pos, state);

        if (Minecraft.getInstance().isSameThread()) { // Only generate on client
            this.modelIndex = new Random().nextInt(3);
            this.randomZRotation = (float) (Math.random() * 30 - 15);
            this.randomXRotation = (float) (Math.random() * 30 - 15);
            this.randomYRotation = (float) (Math.random() * 30 - 15);
            this.randomUniformScale = 0.8f + (float) (Math.random() * 0.4);
        } else {
            this.modelIndex = 0;
            this.randomZRotation = 0;
            this.randomXRotation = 0;
            this.randomYRotation = 0;
            this.randomUniformScale = 1;
        }
    }


    public int getModelIndex() {
        return modelIndex;
    }

    public float getRandomZRotation() {
        return randomZRotation;
    }
    public float getRandomXRotation() {
        return randomXRotation;
    }
    public float getRandomYRotation() {
        return randomYRotation;
    }

    public float getRandomUniformScale() {
        return randomUniformScale;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }
}