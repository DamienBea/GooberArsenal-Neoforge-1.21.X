package net.teamluxron.gooberarsenal.enchantment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.teamluxron.gooberarsenal.item.custom.HammerItem;


public class TunnelbornEnchantment implements EnchantmentEntityEffect {
    public static final Codec<TunnelbornEnchantment> CODEC = Codec.unit(new TunnelbornEnchantment());

    @Override
    public void apply(ServerLevel level, int enchantLevel, EnchantedItemInUse enchantedItem, Entity entity, Vec3 pos) {
        if (!(entity instanceof ServerPlayer player)) return;

        ItemStack stack = enchantedItem.itemStack();

        if (!(stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof HammerItem)) return;

        int radius = (stack.getItem() instanceof HammerItem) ? 2 : 1;

        mineBlocksInRadius(level, player, BlockPos.containing(pos), radius);
    }

    private void mineBlocksInRadius(ServerLevel level, ServerPlayer player, BlockPos center, int radius) {
        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-radius, -radius, -radius),
                center.offset(radius, radius, radius))) {

            if (!pos.equals(center) && player.getMainHandItem().isCorrectToolForDrops(level.getBlockState(pos))) {
                level.destroyBlock(pos, true, player);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return (MapCodec<? extends EnchantmentEntityEffect>) CODEC;
    }
}
