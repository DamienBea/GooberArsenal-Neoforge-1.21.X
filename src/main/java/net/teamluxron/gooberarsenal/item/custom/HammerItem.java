package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.enchantment.effect.TunnelbornEffect;


public class HammerItem extends DiggerItem {

    public HammerItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            Direction face = player.getDirection(); // or use actual hit direction from context if available
            TunnelbornEffect.FACE_HIT.set(face);
        }

        return super.mineBlock(stack, level, state, pos, entity);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Direction face = context.getClickedFace();
        TunnelbornEffect.FACE_HIT.set(face);
        return super.useOn(context);
    }
}