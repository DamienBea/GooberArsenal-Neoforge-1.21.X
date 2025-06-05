package net.teamluxron.gooberarsenal.item.custom.armor;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.teamluxron.gooberarsenal.item.material.ModArmorMaterials;

import java.util.Map;

public class TungstenArmorItem extends ArmorItem {
    private static final Map<Holder<ArmorMaterial>, Float> CRITICAL_DAMAGE_BONUS =
            ImmutableMap.of(ModArmorMaterials.TUNGSTEN_ARMOR_MATERIAL, 2f); // 50% bonus

    private static final ResourceLocation ATTACK_SPEED_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("gooberarsenal", "tungsten_attack_speed_bonus");


    public TungstenArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!(entity instanceof Player player) || level.isClientSide()) return;

        int tungstenPieces = 0;

        for (ItemStack armorPiece : player.getInventory().armor) {
            if (armorPiece.getItem() instanceof TungstenArmorItem) {
                tungstenPieces++;
            }
        }

        boolean hasFullSet = tungstenPieces == 4;
        player.getPersistentData().putBoolean("HasFullTungstenArmor", hasFullSet);

        // Each piece = +5% attack speed, full set = +5% more
        double bonus = 0.05 * tungstenPieces;
        if (hasFullSet) bonus += 0.05;

        AttributeInstance attackSpeedAttr = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeedAttr == null) return;

        // Remove old modifier if present
        if (attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_ID) != null) {
            attackSpeedAttr.removeModifier(ATTACK_SPEED_MODIFIER_ID);
        }

        // Apply only if bonus exists
        if (bonus > 0) {
            attackSpeedAttr.addTransientModifier(new AttributeModifier(
                    ATTACK_SPEED_MODIFIER_ID,
                    bonus,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ));
        }
    }

    // Helper method to check if player is wearing full set
    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && boots.getItem() instanceof TungstenArmorItem
                && !leggings.isEmpty() && leggings.getItem() instanceof TungstenArmorItem
                && !chestplate.isEmpty() && chestplate.getItem() instanceof TungstenArmorItem
                && !helmet.isEmpty() && helmet.getItem() instanceof TungstenArmorItem;
    }

    // Static method to check critical damage bonus
    public static float getCriticalDamageMultiplier(Player player) {
        return player.getPersistentData().getBoolean("HasFullTungstenArmor")
                ? CRITICAL_DAMAGE_BONUS.get(ModArmorMaterials.TUNGSTEN_ARMOR_MATERIAL)
                : 1.0f;
    }



}