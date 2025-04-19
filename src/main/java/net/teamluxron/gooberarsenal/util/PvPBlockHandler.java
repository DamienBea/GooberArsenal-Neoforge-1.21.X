package net.teamluxron.gooberarsenal.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.teamluxron.gooberarsenal.item.custom.StevensJacketItem;

public class PvPBlockHandler {

    @SubscribeEvent
    public static void onPlayerAttackEntity(AttackEntityEvent event) {
        Player attacker = event.getEntity();
        Entity target = event.getTarget();

        if (target instanceof Player targetPlayer) {
            // If attacker is wearing Steven's Jacket, prevent attacking
            if (isWearingStevensJacket(attacker)) {
                if (!attacker.level().isClientSide()) {
                    attacker.displayClientMessage(Component.translatable("message.gooberarsenal.no_pvp_jacket"), true);
                }
                event.setCanceled(true);
            }

            // if target is wearing it, prevent hurting them
            if (isWearingStevensJacket(targetPlayer)) {
                if (!attacker.level().isClientSide()) {
                    attacker.displayClientMessage(Component.translatable("message.gooberarsenal.pvp_protected"), true);
                }
                event.setCanceled(true);
            }
        }
    }

    private static boolean isWearingStevensJacket(Player player) {
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        return chest.getItem() instanceof StevensJacketItem;
    }
}
