package net.teamluxron.gooberarsenal.blocks.custom.decoblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.teamluxron.gooberarsenal.blocks.entity.FieldSwordBlockEntity;
import org.jetbrains.annotations.Nullable;

public class FieldSwordBlock extends Block implements EntityBlock {

    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 1, 16);
    public FieldSwordBlock(Properties properties) {
        super(properties);
    }


    @Nullable
    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FieldSwordBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(0, 0, 0, 16, 1, 16); // 1-pixel-high shape
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(0, 0, 0, 16, 1, 16);
    }



}