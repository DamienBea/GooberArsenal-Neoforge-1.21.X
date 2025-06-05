package net.teamluxron.gooberarsenal.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.item.custom.special.StevensJacketItem;

@EventBusSubscriber(modid = GooberArsenal.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PvPBlockHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
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

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDamagePre(LivingDamageEvent.Pre event) {
        LivingEntity target = event.getEntity();
        DamageSource source = event.getSource();

        if (!(target instanceof Player targetPlayer) || !(source.getEntity() instanceof Player attacker))
            return;

        boolean cancel = false;

        if (isWearingStevensJacket(attacker)) {
            attacker.displayClientMessage(Component.translatable("message.gooberarsenal.no_pvp_jacket"), true);
            cancel = true;
        }

        if (isWearingStevensJacket(targetPlayer)) {
            attacker.displayClientMessage(Component.translatable("message.gooberarsenal.pvp_protected"), true);
            cancel = true;
        }

        if (cancel) {
            // NeoForge 1.21.1 uses setNewDamage(0.0f) instead of setCanceled()
            event.setNewDamage(0.0f);
        }
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getRayTraceResult() instanceof EntityHitResult entityHit &&
                event.getProjectile() instanceof AbstractArrow arrow &&
                arrow.getOwner() instanceof Player shooter &&
                entityHit.getEntity() instanceof Player target) {

            if (isWearingStevensJacket(shooter) || isWearingStevensJacket(target)) {
                event.setCanceled(true);
                arrow.discard();
            }
        }
    }

    private static boolean isWearingStevensJacket(Player player) {
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        return chest.getItem() instanceof StevensJacketItem;
    }
}