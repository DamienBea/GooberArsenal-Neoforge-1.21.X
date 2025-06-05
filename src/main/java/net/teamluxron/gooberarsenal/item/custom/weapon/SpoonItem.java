package net.teamluxron.gooberarsenal.item.custom.weapon;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.teamluxron.gooberarsenal.item.custom.coreitem.AreaMiningItem;

public class SpoonItem extends AreaMiningItem {
    public SpoonItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_SHOVEL, properties);
    }


    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide() && attacker instanceof Player player) {
            if (player.getAttackStrengthScale(0.5F) >= 1.0F) {
                player.getFoodData().eat(1, 0.25f);
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility ability) {

        if    (
                ability == ItemAbilities.SWORD_SWEEP||
                ability == ItemAbilities.SWORD_DIG||
                ability == ItemAbilities.SHOVEL_DOUSE||
                ability == ItemAbilities.SHOVEL_FLATTEN) {
            return true;
        }

        return super.canPerformAction(stack, ability);
    }
}
