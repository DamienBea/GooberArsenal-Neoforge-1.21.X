package net.teamluxron.gooberarsenal.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.teamluxron.gooberarsenal.blocks.entity.ForgingAnvilBlockEntity;
import net.teamluxron.gooberarsenal.recipe.ForgingRecipe;
import net.teamluxron.gooberarsenal.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ForgingAnvilBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 12, 16);
    public static final MapCodec<ForgingAnvilBlock> CODEC = simpleCodec(ForgingAnvilBlock::new);

    public ForgingAnvilBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    /* BLOCK ENTITY */

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgingAnvilBlockEntity(blockPos, blockState);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()) {
            if(level.getBlockEntity(pos) instanceof ForgingAnvilBlockEntity forginganvilBlockEntity) {
                forginganvilBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!(level.getBlockEntity(pos) instanceof ForgingAnvilBlockEntity anvil)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();
        boolean holdingHammer = mainHand.is(ModTags.Items.HAMMERS) || offHand.is(ModTags.Items.HAMMERS);

        if(anvil.inventory.getStackInSlot(0).isEmpty()) {
            if((mainHand.isEmpty() || mainHand.is(ModTags.Items.HAMMERS)) && !offHand.isEmpty() && !offHand.is(ModTags.Items.HAMMERS)) {
                ItemStack toInsert = offHand.copy().split(1);
                if(anvil.inventory.insertItem(0, toInsert, false).isEmpty()) {
                    offHand.shrink(1);
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
                    return ItemInteractionResult.SUCCESS;
                }
            }
            else if(!mainHand.isEmpty() && !mainHand.is(ModTags.Items.HAMMERS)) {
                ItemStack toInsert = mainHand.copy().split(1);
                if(anvil.inventory.insertItem(0, toInsert, false).isEmpty()) {
                    mainHand.shrink(1);
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }

        if(holdingHammer) {
            Optional<RecipeHolder<ForgingRecipe>> recipe = anvil.getCurrentRecipe();
            if(recipe.isPresent()) {
                level.playSound(null, pos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

                ItemStack result = recipe.get().value().getResultItem(level.registryAccess());
                anvil.clearContents();

                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5,
                        pos.getY() + 1.25,
                        pos.getZ() + 0.5,
                        result);

                itemEntity.setDeltaMovement(
                        level.random.nextGaussian() * 0.02,
                        0.2,
                        level.random.nextGaussian() * 0.02);

                level.addFreshEntity(itemEntity);
                return ItemInteractionResult.CONSUME;
            }
        }

        if(mainHand.isEmpty() && !anvil.inventory.getStackInSlot(0).isEmpty()) {
            ItemStack extracted = anvil.inventory.extractItem(0, 1, false);
            player.addItem(extracted);
            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.SUCCESS;
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.getBlockEntity(pos) instanceof ForgingAnvilBlockEntity anvil) {
            // This will make the item spin slowly
            if (!anvil.inventory.getStackInSlot(0).isEmpty() && level.isClientSide()) {
                level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, anvil.inventory.getStackInSlot(0)),
                        pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5,
                        0, 0, 0);
            }
        }
    }
}