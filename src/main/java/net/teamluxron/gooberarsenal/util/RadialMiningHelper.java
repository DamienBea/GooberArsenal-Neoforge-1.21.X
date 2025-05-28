package net.teamluxron.gooberarsenal.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;


public class RadialMiningHelper {

    public static void breakBlocksAround(ServerLevel level, BlockPos center, int radius, Player player, ItemStack stack, Direction faceHit) {
        int cx = center.getX();
        int cy = center.getY();
        int cz = center.getZ();

        switch (faceHit) {
            case UP, DOWN -> {
                for (int x = cx - radius; x <= cx + radius; x++) {
                    for (int z = cz - radius; z <= cz + radius; z++) {
                        BlockPos pos = new BlockPos(x, cy, z);
                        breakBlock(level, pos, player, stack);
                    }
                }
            }
            case NORTH, SOUTH -> {
                for (int x = cx - radius; x <= cx + radius; x++) {
                    for (int y = cy - radius; y <= cy + radius; y++) {
                        BlockPos pos = new BlockPos(x, y, cz);
                        breakBlock(level, pos, player, stack);
                    }
                }
            }
            case EAST, WEST -> {
                for (int y = cy - radius; y <= cy + radius; y++) {
                    for (int z = cz - radius; z <= cz + radius; z++) {
                        BlockPos pos = new BlockPos(cx, y, z);
                        breakBlock(level, pos, player, stack);
                    }
                }
            }
        }
    }

    private static void breakBlock(ServerLevel level, BlockPos pos, Player player, ItemStack stack) {
        BlockState state = level.getBlockState(pos);

        if (state.isAir()) return;

        float destroySpeed = state.getDestroySpeed(level, pos);
        if (!Float.isFinite(destroySpeed)) return;

        if (!player.mayUseItemAt(pos, null, stack)) return;

        level.destroyBlock(pos, true, player);
    }
}