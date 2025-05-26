package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;
import net.teamluxron.gooberarsenal.util.MiningUtils;

public class HammerItem extends DiggerItem {
    public HammerItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!level.isClientSide && miner instanceof ServerPlayer player) {
            // Determine radius (1 for 3x3, 2 for 5x5)
            int radius = stack.getEnchantmentLevel(
                    level.registryAccess().registryOrThrow(Registries.ENCHANTMENT)
                            .getHolderOrThrow(ModEnchantments.TUNNELBORN)) > 0 ? 2 : 1;

            // Get precise mining face
            BlockHitResult hitResult = (BlockHitResult)player.pick(5.0D, 0.0F, false);
            Direction face = hitResult.getDirection();

            MiningUtils.mineBlocksInRadius((ServerLevel)level, player, pos, face, radius);
        }
        return super.mineBlock(stack, level, state, pos, miner);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            boolean isShieldBreak = player.fallDistance > 0.0F && !player.onGround()
                    && !player.onClimbable() && !player.isInWater() && !player.hasEffect(MobEffects.BLINDNESS)
                    && !player.isPassenger();

            if (isShieldBreak && target instanceof Player targetPlayer) {
                ItemStack activeItem = targetPlayer.getUseItem();

                if (!activeItem.isEmpty() && activeItem.getItem().canPerformAction(activeItem, net.neoforged.neoforge.common.ItemAbilities.SHIELD_BLOCK)) {
                    targetPlayer.getCooldowns().addCooldown(activeItem.getItem(), 100);
                    targetPlayer.stopUsingItem();
                    targetPlayer.level().broadcastEntityEvent(targetPlayer, (byte) 30);
                }
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }
}
