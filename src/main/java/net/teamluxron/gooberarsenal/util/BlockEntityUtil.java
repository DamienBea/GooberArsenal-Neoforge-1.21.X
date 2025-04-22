package net.teamluxron.gooberarsenal.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.teamluxron.gooberarsenal.blocks.entity.AbstractTickingBlockEntity;

public class BlockEntityUtil {

    public static void tickAll(ServerLevel serverLevel) {
        for (LevelChunk chunk : serverLevel.getChunkSource().getLoadedChunks()) {
            for (BlockEntity blockEntity : chunk.getBlockEntities().values()) {
                if (blockEntity instanceof AbstractTickingBlockEntity tickingBE) {
                    tickingBE.tick();
                }
            }
        }
    }
}
