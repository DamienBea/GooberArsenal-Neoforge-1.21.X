package net.teamluxron.gooberarsenal.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class TunnelbornEffect implements EnchantmentEntityEffect {
    public static final MapCodec<TunnelbornEffect> CODEC = MapCodec.unit(TunnelbornEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 pos) {
        ItemStack stack = item.itemStack();
        if (stack.isEmpty()) return;
        if (!(entity instanceof Player player)) return;
    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}