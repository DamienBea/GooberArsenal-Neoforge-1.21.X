package net.teamluxron.gooberarsenal.handler;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.teamluxron.gooberarsenal.blocks.custom.functionblock.EchoFlowerBlock;
import net.teamluxron.gooberarsenal.blocks.entity.function.EchoFlowerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class EchoFlowerColorHandler implements BlockColor {

    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter level,
                        @Nullable BlockPos pos, int tintIndex) {
        if (level != null && pos != null) {
            BlockPos basePos = state.getValue(EchoFlowerBlock.HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
            var be = level.getBlockEntity(basePos);
            if (be instanceof EchoFlowerBlockEntity echo) {
                return echo.getColor().getTextColor();
            }
        }
        return 0x00FFFF;
    }

}

