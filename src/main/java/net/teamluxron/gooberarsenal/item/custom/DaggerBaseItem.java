package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.teamluxron.gooberarsenal.GooberArsenal;

public class DaggerBaseItem extends SwordItem {
    private static final AttributeModifier DAGGER_RANGE_MODIFIER = new AttributeModifier(
            GooberArsenal.res("dagger_range_modifier"),
            -0.5,
            AttributeModifier.Operation.ADD_VALUE
    );

    public DaggerBaseItem(Tier tier, int baseDamage, float attackSpeed, Properties properties) {
        super(tier, properties.attributes(createDaggerAttributes(tier, baseDamage, attackSpeed)));
    }

    private static ItemAttributeModifiers createDaggerAttributes(Tier tier, int baseDamage, float attackSpeed) {
        return SwordItem.createAttributes(tier, baseDamage, attackSpeed)
                .withModifierAdded(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        DAGGER_RANGE_MODIFIER,
                        EquipmentSlotGroup.MAINHAND
                );
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide() && target.invulnerableTime > 8) {
            target.invulnerableTime = 8;
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility ability) {
        if    ( ability == ItemAbilities.SHEARS_DISARM ||
                ability == ItemAbilities.SHEARS_CARVE) {
            return true;
        }
        return super.canPerformAction(stack, ability);
    }


//    @Override
//    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
//        tooltip.add(Component.translatable("tooltip.gooberarsenal.daggers"));
//        super.appendHoverText(stack, context, tooltip, flag);
//    }
}