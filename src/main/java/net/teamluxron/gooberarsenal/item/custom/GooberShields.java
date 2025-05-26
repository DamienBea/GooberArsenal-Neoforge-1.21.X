package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class GooberShields extends ShieldItem {


    public GooberShields(Properties properties) {
        super(properties
                .durability(336)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }


    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repairItem) {
        // Override if you want specific repair items per shield type
        return super.isValidRepairItem(toRepair, repairItem);
    }
}