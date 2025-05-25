package net.teamluxron.gooberarsenal.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.teamluxron.gooberarsenal.item.material.ModArmorMaterials;

import java.util.Map;

public class TungstenArmorItem extends ArmorItem {
    private static final Map<Holder<ArmorMaterial>, Float> CRITICAL_DAMAGE_BONUS =
            ImmutableMap.of(ModArmorMaterials.TUNGSTEN_ARMOR_MATERIAL, 1.5f); // 50% bonus

    public TungstenArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide()) {
            if (hasFullSuitOfArmorOn(player)) {
                player.getPersistentData().putBoolean("HasFullTungstenArmor", true);
            } else {
                player.getPersistentData().remove("HasFullTungstenArmor");
            }
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
