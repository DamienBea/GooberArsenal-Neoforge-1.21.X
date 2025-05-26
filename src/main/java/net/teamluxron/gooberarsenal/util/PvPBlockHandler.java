package net.teamluxron.gooberarsenal.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.teamluxron.gooberarsenal.item.custom.StevensJacketItem;

public class PvPBlockHandler {

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Player attacker = event.getEntity();
        if (event.getTarget() instanceof Player target) {
            if (isWearingStevensJacket(attacker)) {
                if (!attacker.level().isClientSide()) {
                    attacker.displayClientMessage(Component.translatable("message.gooberarsenal.no_pvp_jacket"), true);
                }
                event.setCanceled(true);
            } else if (isWearingStevensJacket(target)) {
                if (!attacker.level().isClientSide()) {
                    attacker.displayClientMessage(Component.translatable("message.gooberarsenal.pvp_protected"), true);
                }
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamagePre(LivingDamageEvent.Pre event) {
        LivingEntity target = event.getEntity();
        DamageSource source = event.getSource();
        if (target instanceof Player targetPlayer && source.getEntity() instanceof Player attacker) {

            // Cancel PvP damage if attacker or target is wearing the jacket
            if (isWearingStevensJacket(attacker)) {
                if (!attacker.level().isClientSide()) {
                    attacker.displayClientMessage(Component.translatable("message.gooberarsenal.no_pvp_jacket"), true);
                }
                event.setNewDamage(0.0F);
            } else if (isWearingStevensJacket(targetPlayer)) {
                if (!attacker.level().isClientSide()) {
                    attacker.displayClientMessage(Component.translatable("message.gooberarsenal.pvp_protected"), true);
                }
                event.setNewDamage(0.0F);
            }
        }
    }

    private static boolean isWearingStevensJacket(Player player) {
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        return chest.getItem() instanceof StevensJacketItem;
    }
}