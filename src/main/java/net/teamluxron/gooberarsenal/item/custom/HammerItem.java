package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
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
}
