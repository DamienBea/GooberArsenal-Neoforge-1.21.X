package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.Nullable;

public class GooberShield extends ShieldItem {
    public GooberShield(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Nullable
    @Override
    public Holder<SoundEvent> getEquipSound() {
        return Holder.direct(SoundEvents.SHIELD_BLOCK);
    }



}