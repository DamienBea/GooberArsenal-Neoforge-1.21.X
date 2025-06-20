package net.teamluxron.gooberarsenal.item.custom.weapon;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.teamluxron.gooberarsenal.item.custom.coreitem.CapitatorMiningItem;

import java.util.*;


public class PolearmItem extends CapitatorMiningItem {

    public static final ResourceLocation ATTACK_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "polearm_attack_damage");
    public static final ResourceLocation ATTACK_SPEED_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "polearm_attack_speed");
    public static final ResourceLocation ENTITY_REACH_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "polearm_entity_reach");
    public static final ResourceLocation BLOCK_REACH_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "polearm_block_reach");

    public PolearmItem(Tier tier, int damage, float speed, Item.Properties props) {
        super(tier, props
                .component(DataComponents.TOOL, new Tool(
                        List.of(),
                        damage + tier.getAttackDamageBonus(),
                        (int) speed)).attributes(buildAttributes(tier, damage, speed)));
    }

    public static ItemAttributeModifiers buildAttributes(Tier tier, int baseDamage, float baseSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(ATTACK_DAMAGE_ID,
                                baseDamage + tier.getAttackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(ATTACK_SPEED_ID,
                                baseSpeed,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(ENTITY_REACH_ID,
                                2.0D,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.BLOCK_INTERACTION_RANGE,
                        new AttributeModifier(BLOCK_REACH_ID,
                                2.0D,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }


    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }
    public float getShieldDisableModifier(ItemStack stack, ItemStack shield) {
        return 0.5f; // Same value as vanilla axes
    }

}
