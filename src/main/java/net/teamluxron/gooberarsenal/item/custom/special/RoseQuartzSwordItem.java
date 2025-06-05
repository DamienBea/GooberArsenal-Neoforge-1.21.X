package net.teamluxron.gooberarsenal.item.custom.special;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class RoseQuartzSwordItem extends SwordItem {

    public RoseQuartzSwordItem(Tier tier, Item.Properties properties) {
        // Note: attributes must already be in properties
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);

        if (!target.level().isClientSide() && target.isDeadOrDying() && attacker instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0));
        }

        return result;
    }
}