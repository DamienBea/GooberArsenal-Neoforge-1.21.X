package net.teamluxron.gooberarsenal.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.teamluxron.gooberarsenal.item.custom.HammerItem;


public record TunnelbornEnchantment() implements EnchantmentEntityEffect {
    public static final MapCodec<TunnelbornEnchantment> CODEC = MapCodec.unit(TunnelbornEnchantment::new);

    @Override
    public void apply(ServerLevel level, int enchantLevel, EnchantedItemInUse enchantedItem, Entity entity, Vec3 pos) {
        if (!(entity instanceof ServerPlayer player)) return;
        ItemStack stack = enchantedItem.itemStack();

        // ONLY work with hammers
        if (!(stack.getItem() instanceof HammerItem)) return;

        // Hammer gets 5x5 when enchanted (radius 2)
        int radius = 2;
        mineBlocksInRadius(level, player, BlockPos.containing(pos), radius);
    }

    private void mineBlocksInRadius(ServerLevel level, ServerPlayer player, BlockPos center, int radius) {
        // Only mine one layer (same y-level)
        BlockPos.betweenClosedStream(
                        center.offset(-radius, 0, -radius),
                        center.offset(radius, 0, radius))
                .forEach(pos -> {
                    if (!pos.equals(center) && player.getMainHandItem().isCorrectToolForDrops(level.getBlockState(pos))) {
                        level.destroyBlock(pos, true, player);
                    }
                });
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
