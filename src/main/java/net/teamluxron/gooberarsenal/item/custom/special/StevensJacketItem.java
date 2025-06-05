package net.teamluxron.gooberarsenal.item.custom.special;

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

        if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == this) {
            if (!player.hasEffect(MobEffects.REGENERATION)) {
                player.addEffect(new MobEffectInstance(
                        MobEffects.REGENERATION,
                        100,  // Duration (5 seconds)
                        0,    // Amplifier (level I)
                        false, // Ambient
                        false // Show particles
                ));
            }
        }
    }
}
