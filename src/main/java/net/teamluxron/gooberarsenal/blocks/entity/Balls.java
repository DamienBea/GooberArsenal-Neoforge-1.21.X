package net.teamluxron.gooberarsenal.blocks.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Balls extends BlockEntity {


    public Balls(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BALLS_BE.get(), pos, blockState);
    }
}
