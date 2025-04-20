package net.teamluxron.gooberarsenal.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import java.util.HashSet;
import java.util.Set;

public class MiningUtils {
    public static void mineBlocksInRadius(ServerLevel level, ServerPlayer player, BlockPos center, Direction face, int radius) {
        // Get all blocks in the proper mining plane
        Set<BlockPos> positions = getBlocksToBeDestroyed(radius, center, face);

        ItemStack tool = player.getMainHandItem();
        for (BlockPos pos : positions) {
            if (!pos.equals(center)) {
                BlockState state = level.getBlockState(pos);
                if (tool.isCorrectToolForDrops(state)) {
                    level.destroyBlock(pos, true, player);
                }
            }
        }
    }

    public static Set<BlockPos> getBlocksToBeDestroyed(int radius, BlockPos center, Direction face) {
        Set<BlockPos> positions = new HashSet<>();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        // Calculate all positions in the proper plane
        for (int a = -radius; a <= radius; a++) {
            for (int b = -radius; b <= radius; b++) {
                switch (face.getAxis()) {
                    case X -> positions.add(mutablePos.set(center).move(0, a, b)); // East/West
                    case Y -> positions.add(mutablePos.set(center).move(a, 0, b)); // Up/Down
                    case Z -> positions.add(mutablePos.set(center).move(a, b, 0)); // North/South
                }
            }
        }

        return positions;
    }
}