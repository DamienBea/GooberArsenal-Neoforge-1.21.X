package net.teamluxron.gooberarsenal.blocks.custom.decoblock;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.teamluxron.gooberarsenal.blocks.entity.FieldSwordBlockEntity;
import net.teamluxron.gooberarsenal.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class FieldSwordBlock extends Block implements EntityBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 1, 16);

    public FieldSwordBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FieldSwordBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(0, 0, 0, 16, 1, 16);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(0, 0, 0, 16, 1, 16);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit
    ) {
        // Handle Transformation Template
        if (stack.getItem() == ModItems.TRANSFORMATION_TEMPLATE.get()) {
            if (!level.isClientSide() && level.getBlockEntity(pos) instanceof FieldSwordBlockEntity be) {
                ItemStack offhand = player.getOffhandItem();
                boolean sneaking = player.isShiftKeyDown();

                if (offhand.is(Items.COPPER_INGOT)) {
                    be.adjustSecondaryYRot(!sneaking);
                } else if (offhand.is(Items.IRON_INGOT)) {
                    be.adjustRotX(!sneaking);
                } else if (offhand.is(Items.GOLD_INGOT)) {
                    be.adjustRotZ(!sneaking);
                } else if (offhand.is(Items.DIAMOND)) {
                    be.adjustScale(!sneaking);
                } else {
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                }

                level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 0.8f, 1.2f);
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }
        // Handle Sword/Axe/Hammer replacement
        else if (FieldSwordBlockEntity.isAllowedReplacementItem(stack)) {
            if (!level.isClientSide() && level.getBlockEntity(pos) instanceof FieldSwordBlockEntity be) {
                be.setSwordItem(stack.getItem());
                level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_NETHERITE.value(), SoundSource.BLOCKS, 1.0f, 1.2f);
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}