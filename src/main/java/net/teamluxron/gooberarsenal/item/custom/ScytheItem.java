package net.teamluxron.gooberarsenal.item.custom;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;


public class ScytheItem extends SwordItem {

    public ScytheItem(Tier tier, Properties properties) {
        super(tier, properties);
    }



    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {

            if (state.is(BlockTags.CROPS) && state.getBlock() instanceof CropBlock crop) {

                BlockPos.betweenClosedStream(pos.offset(-2, 0, -2), pos.offset(2, 0, 2)).forEach(blockPos -> {
                    BlockState nearbyState = level.getBlockState(blockPos);

                    if (nearbyState.getBlock() instanceof CropBlock nearbyCrop) {
                        if (nearbyState.getValue(CropBlock.AGE) == nearbyCrop.getMaxAge()) {

                            nearbyState.getBlock().playerDestroy(level, player, blockPos, nearbyState, level.getBlockEntity(blockPos), stack);

                            BlockState replanted = nearbyCrop.defaultBlockState().setValue(CropBlock.AGE, 0);
                            level.setBlock(blockPos, replanted, 3);
                        }
                    }
                });
            }
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(BlockTags.CROPS)) {
            return 0.01F;
        }

        return super.getDestroySpeed(stack, state);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.scythes"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}