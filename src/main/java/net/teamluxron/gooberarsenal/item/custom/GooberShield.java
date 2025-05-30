package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class GooberShield extends ShieldItem {
    public GooberShield(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Nullable
    @Override
    public Holder<SoundEvent> getEquipSound() {
        return Holder.direct(SoundEvents.SHIELD_BLOCK);
    }

    public void onBlock(Level level, LivingEntity entity, ItemStack shieldStack) {

    }

    public void onShieldDisabled(Level level, LivingEntity entity, ItemStack shieldStack) {

    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public void handleBlockEvent(LivingEntity entity, ItemStack shieldStack, boolean broken) {
        if (entity.level() instanceof Level level) {
            if (broken) {
                onShieldDisabled(level, entity, shieldStack);
            } else {
                onBlock(level, entity, shieldStack);
            }
        }
    }
}
