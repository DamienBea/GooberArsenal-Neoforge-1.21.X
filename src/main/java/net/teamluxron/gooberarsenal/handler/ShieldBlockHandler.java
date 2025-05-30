package net.teamluxron.gooberarsenal.handler;

import net.minecraft.core.Holder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.teamluxron.gooberarsenal.item.custom.GooberShield;

public class ShieldBlockHandler {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        LivingEntity livingEntity = event.getEntity();
        DamageSource source = event.getSource();
        Holder<DamageType> damageTypeHolder = Holder.direct(source.type());

        if (damageTypeHolder.is(DamageTypeTags.BYPASSES_ARMOR) ||
                damageTypeHolder.is(DamageTypeTags.BYPASSES_INVULNERABILITY) ||
                damageTypeHolder.is(DamageTypeTags.BYPASSES_SHIELD)) {
            return;
        }

        if (livingEntity.isUsingItem()) {
            ItemStack stack = livingEntity.getUseItem();
            if (stack.getItem() instanceof GooberShield shield) {
                boolean brokeShield = event.getShieldDamage() >= stack.getMaxDamage();

                shield.handleBlockEvent(livingEntity, stack, brokeShield);
            }
        }
    }
}