package net.teamluxron.gooberarsenal.item.custom.special;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.item.custom.tools.HammerItem;

import java.util.List;

public class MossyMasherItem extends HammerItem {
    private static final int COOLDOWN_TICKS = 20 * 16;
    private static final int RADIUS = 2;
    private static final double CIRCLE_RADIUS_SQ = 6.25; // 2.5^2 for smooth circle
    private static final float CONVERSION_CHANCE = 0.85f;
    private static final float VEGETATION_CHANCE = 0.5f;

    private static final List<Block> VEGETATION = List.of(
            Blocks.MOSS_CARPET,
            Blocks.AZALEA,
            Blocks.FLOWERING_AZALEA,
            Blocks.SHORT_GRASS,
            Blocks.TALL_GRASS
    );

    public MossyMasherItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos centerPos = context.getClickedPos();
        Player player = context.getPlayer();

        if (!level.isClientSide() && player != null) {
            ServerLevel serverLevel = (ServerLevel) level;
            RandomSource random = serverLevel.random;
            int conversions = 0;

            for (int xOffset = -RADIUS; xOffset <= RADIUS; xOffset++) {
                for (int zOffset = -RADIUS; zOffset <= RADIUS; zOffset++) {
                    if (xOffset*xOffset + zOffset*zOffset > CIRCLE_RADIUS_SQ) continue;

                    BlockPos targetPos = centerPos.offset(xOffset, 0, zOffset);

                    if (canConvertToMoss(serverLevel, targetPos)) {
                        if (random.nextFloat() < CONVERSION_CHANCE) {
                            serverLevel.setBlock(targetPos, Blocks.MOSS_BLOCK.defaultBlockState(), 3);
                            conversions++;

                            tryPlaceVegetation(serverLevel, targetPos.above(), random);
                        }
                    }
                }
            }

            if (conversions > 0) {
                player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
                serverLevel.levelEvent(1505, centerPos, 0);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    private void tryPlaceVegetation(ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isEmptyBlock(pos) && random.nextFloat() < VEGETATION_CHANCE) {
            Block vegetation = VEGETATION.get(random.nextInt(VEGETATION.size()));

            if (vegetation == Blocks.TALL_GRASS) {
                if (level.getBlockState(pos.below()).is(Blocks.MOSS_BLOCK)) {
                    level.setBlock(pos, vegetation.defaultBlockState(), 3);
                }
            } else {
                level.setBlock(pos, vegetation.defaultBlockState(), 3);
            }
        }
    }

    private boolean canConvertToMoss(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.is(BlockTags.BASE_STONE_OVERWORLD) ||
                state.is(BlockTags.DIRT) ||
                state.is(Blocks.GRAVEL);
    }
}