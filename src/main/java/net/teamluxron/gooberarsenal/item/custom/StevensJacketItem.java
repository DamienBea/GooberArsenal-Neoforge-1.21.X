package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class StevensJacketItem extends ArmorItem {

    public StevensJacketItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player) || level.isClientSide) return;

        ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);

        // Check if the player is wearing the Stevens Jacket
        if (chestItem.getItem() == this) {
            // Apply regeneration effect if the player doesn't already have it
            if (!player.hasEffect(MobEffects.REGENERATION)) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, false));
            }

            // Disable PvP when the jacket is worn
            player.getPersistentData().putBoolean("DisablePvP", true);
        } else {
            // Remove the "DisablePvP" flag when the jacket is not worn
            player.getPersistentData().remove("DisablePvP");
        }
    }
}
