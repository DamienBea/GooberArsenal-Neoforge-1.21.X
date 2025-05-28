package net.teamluxron.gooberarsenal.enchantment.effect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.teamluxron.gooberarsenal.util.RadialMiningHelper;

public class TunnelbornEffect implements EnchantmentEntityEffect {
    public static final MapCodec<TunnelbornEffect> CODEC = MapCodec.unit(TunnelbornEffect::new);
    private static final ThreadLocal<Direction> LAST_HIT_FACE = new ThreadLocal<>();
    public static final ThreadLocal<Direction> FACE_HIT = new ThreadLocal<>();


    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 pos) {
        ItemStack stack = item.itemStack();
        if (stack.isEmpty()) return;
        if (!(entity instanceof Player player)) return;

        BlockPos blockPos = BlockPos.containing(pos);
        int radius = enchantmentLevel >= 2 ? 2 : 1;

        Direction faceHit = FACE_HIT.get();
        if (faceHit == null) faceHit = Direction.UP; // fallback

        RadialMiningHelper.breakBlocksAround(level, blockPos, radius, player, stack, faceHit);
    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}