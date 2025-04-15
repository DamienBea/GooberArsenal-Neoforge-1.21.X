package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;


public class PolearmItem extends AxeItem {

    private static final int MAX_BLOCKS = 512;

    public PolearmItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (state.is(BlockTags.LOGS)) {
                breakTree(level, pos, state.getBlock(), stack, player);
            }
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }

    private void breakTree(Level level, BlockPos startPos, Block logBlock, ItemStack tool, Player player) {
        Queue<BlockPos> toCheck = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();
        toCheck.add(startPos);

        while (!toCheck.isEmpty() && visited.size() < MAX_BLOCKS) {
            BlockPos current = toCheck.poll();
            if (!visited.add(current)) continue;

            BlockState state = level.getBlockState(current);
            if (state.is(logBlock)) {
                level.destroyBlock(current, true, player);

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            BlockPos offset = current.offset(dx, dy, dz);
                            if (!visited.contains(offset) && level.getBlockState(offset).is(logBlock)) {
                                toCheck.add(offset);
                            }
                        }
                    }
                }
            }
        }
    }
}