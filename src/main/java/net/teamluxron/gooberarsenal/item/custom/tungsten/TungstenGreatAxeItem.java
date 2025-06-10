package net.teamluxron.gooberarsenal.item.custom.tungsten;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.teamluxron.gooberarsenal.item.custom.weapon.GreatAxeItem;

public class TungstenGreatAxeItem extends GreatAxeItem {

    public static final ResourceLocation ATTACK_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "tungsten_greataxe_attack_damage");
    public static final ResourceLocation ATTACK_SPEED_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "tungsten_greataxe_attack_speed");
    public static final ResourceLocation ENTITY_REACH_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "tungsten_greataxe_entity_reach");
    public static final ResourceLocation BLOCK_REACH_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "tungsten_greataxe_block_reach");
    
    public TungstenGreatAxeItem(Tier tier, int damage, float speed, Properties props) {
        super(tier, damage, speed, props);
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
                                1.0D,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.BLOCK_INTERACTION_RANGE,
                        new AttributeModifier(BLOCK_REACH_ID,
                                1.0D,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }


}
