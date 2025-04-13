package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.teamluxron.gooberarsenal.GooberArsenal;

import java.util.List;

//@Mod.EventBusSubscriber(modid = GooberArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RoseQuartzShield {
//
//    @SubscribeEvent
//    public static void onBlockBreak(BlockEvent.BreakEvent event) {
//        Player player = event.getPlayer();
//        if (player == null || player.level().isClientSide()) return;
//
//        ItemStack heldItem = player.getUseItem();
//        if (!(heldItem.getItem() instanceof RoseQuartzShield)) return;
//
//        if (player.isBlocking() && player.getRandom().nextFloat() < 0.1f) {
//            Level level = player.level();
//            AABB area = player.getBoundingBox().inflate(3.0D);
//            List<Entity> nearbyEntities = level.getEntities(player, area, e -> e instanceof LivingEntity && e != player);
//
//            for (Entity entity : nearbyEntities) {
//                if (entity instanceof LivingEntity target) {
//                    Vec3 knockbackDirection = target.position().subtract(player.position()).normalize();
//                    target.push(knockbackDirection.x * 2, 1.5, knockbackDirection.z * 2);
//                    target.hasImpulse = true;
//                }
//            }
//        }
//    }
}


