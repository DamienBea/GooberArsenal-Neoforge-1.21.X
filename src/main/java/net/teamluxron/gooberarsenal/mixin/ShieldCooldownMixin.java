package net.teamluxron.gooberarsenal.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ShieldCooldownMixin {
    @Inject(method = "canDisableShield", at = @At("HEAD"), cancellable = true)
    private void onCanDisableShield(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;
        long disabledUntil = entity.getPersistentData().getLong("ShieldDisabledUntil");
        if (entity.level().getGameTime() < disabledUntil) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}