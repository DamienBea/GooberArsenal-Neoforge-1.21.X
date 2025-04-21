package net.teamluxron.gooberarsenal.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.teamluxron.gooberarsenal.blocks.entity.ForgingAnvilBlockEntity;
import net.teamluxron.gooberarsenal.recipe.ForgingRecipe;
import net.teamluxron.gooberarsenal.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ForgingAnvilBlock extends Block implements EntityBlock {
    public ForgingAnvilBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ForgingAnvilBlockEntity(pos, state);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof ForgingAnvilBlockEntity anvil)) {
            return InteractionResult.PASS;
        }

        ItemStack heldItem = player.getItemInHand(hand);

        // Hammer interaction
        if (heldItem.is(ModTags.Items.HAMMERS)) {
            Optional<RecipeHolder<ForgingRecipe>> recipe = anvil.getCurrentRecipe();
            if (recipe.isPresent()) {
                // Play anvil sound
                level.playSound(null, pos, SoundEvents.ANVIL_USE,
                        SoundSource.BLOCKS, 1.0F, 1.0F);

                // Set result
                anvil.setStoredItem(recipe.get().value().getResultItem(level.registryAccess()));
                return InteractionResult.CONSUME;
            }
        }
        // Insert item if empty
        else if (anvil.getStoredItem().isEmpty() && !heldItem.isEmpty()) {
            anvil.setStoredItem(heldItem.split(1));
            return InteractionResult.CONSUME;
        }
        // Extract item if not empty
        else if (!anvil.getStoredItem().isEmpty()) {
            player.getInventory().placeItemBackInInventory(anvil.getStoredItem());
            anvil.setStoredItem(ItemStack.EMPTY);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}