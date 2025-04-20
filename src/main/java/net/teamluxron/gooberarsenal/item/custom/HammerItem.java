package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends DiggerItem {
    public HammerItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!level.isClientSide && miner instanceof ServerPlayer player) {
            int baseRange = 1; // Default 3x3
            int extraRange = stack.getEnchantmentLevel(
                    level.registryAccess().registryOrThrow(Registries.ENCHANTMENT)
                            .getHolderOrThrow(ModEnchantments.TUNNELBORN.getKey())
            ) > 0 ? 1 : 0;

            List<BlockPos> blocksToMine = getBlocksToBeDestroyed(baseRange + extraRange, pos, player);

            for (BlockPos targetPos : blocksToMine) {
                BlockState targetState = level.getBlockState(targetPos);
                if (stack.isCorrectToolForDrops(targetState)) {
                    level.destroyBlock(targetPos, true, player);
                }
            }
        }
        return super.mineBlock(stack, level, state, pos, miner);
    }


    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initialBlockPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();
        BlockHitResult traceResult = player.level().clip(new ClipContext(
                player.getEyePosition(1f),
                player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f)),
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        if (traceResult.getType() != HitResult.Type.BLOCK) {
            return positions;
        }

        Direction face = traceResult.getDirection();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        if (face.getAxis() == Direction.Axis.Y) {
            for (int x = -range; x <= range; x++) {
                for (int z = -range; z <= range; z++) {
                    positions.add(mutablePos.set(initialBlockPos).move(x, 0, z));
                }
            }
        } else if (face.getAxis() == Direction.Axis.Z) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(mutablePos.set(initialBlockPos).move(x, y, 0));
                }
            }
        } else {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(mutablePos.set(initialBlockPos).move(0, y, z));
                }
            }
        }

        return positions;
    }
}
