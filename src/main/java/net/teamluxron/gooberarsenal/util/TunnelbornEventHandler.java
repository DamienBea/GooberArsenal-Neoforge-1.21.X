package net.teamluxron.gooberarsenal.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;

public class TunnelbornEventHandler {

    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;

        ItemStack tool = player.getMainHandItem();
        if (!(tool.getItem() instanceof TieredItem)) return;

        Holder<Enchantment> tunnelborn = player.level().registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolder(ModEnchantments.TUNNELBORN)
                .orElse(null);

        if (tunnelborn != null && tool.getEnchantmentLevel(tunnelborn) > 0) {
            BlockPos pos = event.getPos();
            Direction faceHit = Direction.UP; // TODO: replace with actual face if possible
            RadialMiningHelper.breakBlocksAround((ServerLevel) player.level(), pos, 1, player, tool, faceHit);
        }
    }
}