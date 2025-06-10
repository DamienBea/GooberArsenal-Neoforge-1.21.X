package net.teamluxron.gooberarsenal.item.custom.weapon;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.teamluxron.gooberarsenal.item.custom.coreitem.CapitatorMiningItem;

import java.util.List;

public class GreatAxeItem extends CapitatorMiningItem {

    public GreatAxeItem(Tier tier, int damage, float speed, Item.Properties props) {
        super(tier, props.component(DataComponents.TOOL, new Tool(
                List.of(),
                damage + tier.getAttackDamageBonus(), (int) speed
        )).attributes(buildAttributes(tier, damage, speed)));
    }

    public static ItemAttributeModifiers buildAttributes(Tier tier, int baseDamage, float baseSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath("gooberarsenal", "greataxe_attack_damage"),
                                baseDamage + tier.getAttackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath("gooberarsenal", "greataxe_attack_speed"),
                                baseSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }


    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

}
