package net.teamluxron.gooberarsenal.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties ENERGY_BAR = new FoodProperties.Builder()

            .nutrition(6).saturationModifier(1f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1), 1f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1), 1f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 1), 1f)
            .build();

    public static final FoodProperties SANDVICH = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(1f)
            .effect(new MobEffectInstance(MobEffects.HEAL, 1, 20), 1f)
            .build();

    public static final FoodProperties CHOCOLATE_CHIP_PANCAKES = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(1f)
            .build();

    public static final FoodProperties COPPER_APPLE = new FoodProperties.Builder()
            .nutrition(4).saturationModifier(2f)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 600, 2), 1f)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 600, 1), 1f)
            .build();

}
