package net.teamluxron.gooberarsenal.blocks.custom.functionblock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.blocks.entity.function.EchoFlowerBlockEntity;
import net.teamluxron.gooberarsenal.menu.EchoFlowerOpenScreenMessage;
import net.teamluxron.gooberarsenal.network.ModNetwork;
import org.jetbrains.annotations.Nullable;

public class EchoFlowerBlock extends TallFlowerBlock implements EntityBlock {

    public EchoFlowerBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .instabreak()
                .sound(SoundType.GRASS)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
        );
    }


    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EchoFlowerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return createTickerHelper(type, ModBlockEntities.ECHO_FLOWER_BE.get(),
                (level1, pos, state1, blockEntity) -> blockEntity.tickServer(level1));
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> type, BlockEntityType<E> targetType, BlockEntityTicker<? super E> ticker) {
        return targetType == type ? (BlockEntityTicker<A>) ticker : null;
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            ItemStack held = player.getItemInHand(hand);
            if (held.getItem() instanceof DyeItem dyeItem) {
                DyeColor newColor = dyeItem.getDyeColor();

                BlockPos basePos = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
                BlockEntity be = level.getBlockEntity(basePos);
                if (be instanceof EchoFlowerBlockEntity echoBE) {
                    echoBE.setColor(newColor);

                    BlockState baseState = level.getBlockState(basePos);
                    BlockState topState = level.getBlockState(basePos.above());

                    level.sendBlockUpdated(basePos, baseState, baseState, 3);
                    level.sendBlockUpdated(basePos.above(), topState, topState, 3);

                    if (!player.getAbilities().instabuild) {
                        held.shrink(1);
                    }
                    return ItemInteractionResult.CONSUME;
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }



    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof EchoFlowerBlockEntity echo) {
                if (!echo.hasColor()) {
                    echo.setColor(DyeColor.CYAN);
                }
                if (placer instanceof ServerPlayer serverPlayer) {
                    echo.setPlacedByPlayer(true);
                    ModNetwork.sendToPlayer(new EchoFlowerOpenScreenMessage(pos, echo.getCustomMessage()), serverPlayer);
                }
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof EchoFlowerBlockEntity echo) {
                ModNetwork.sendToPlayer(new EchoFlowerOpenScreenMessage(pos, echo.getCustomMessage()), serverPlayer);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}