package net.teamluxron.gooberarsenal.blocks.custom;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.teamluxron.gooberarsenal.blocks.entity.RadioBlockEntity;
import net.teamluxron.gooberarsenal.sound.ModSounds;
import org.jetbrains.annotations.Nullable;


public class RadioBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final MapCodec<RadioBlock> CODEC = simpleCodec(RadioBlock::new);

    public RadioBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof RadioBlockEntity radio) {
            boolean newState = !state.getValue(POWERED);

            // Update block state
            level.setBlock(pos, state.setValue(POWERED, newState), Block.UPDATE_ALL);

            // Update block entity
            radio.setPlaying(newState);

            // Play appropriate sound
            if (level.isClientSide) {
                return ItemInteractionResult.SUCCESS;
            } else {
                level.playSound(null, pos,
                        newState ? ModSounds.RADIO.get() : ModSounds.BUTTON.get(),
                        SoundSource.RECORDS,
                        newState ? 1.0f : 0.5f,
                        1.0f);
                return ItemInteractionResult.CONSUME;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean moved) {
        if (!level.isClientSide) {
            boolean hasPower = level.hasNeighborSignal(pos);
            if (hasPower != state.getValue(POWERED)) {
                level.setBlock(pos, state.setValue(POWERED, hasPower), Block.UPDATE_ALL);
                if (level.getBlockEntity(pos) instanceof RadioBlockEntity radio) {
                    radio.setPlaying(hasPower);
                }
            }
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }
}