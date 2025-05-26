package net.teamluxron.gooberarsenal.mixin;

import net.minecraft.world.entity.player.Player;
import net.teamluxron.gooberarsenal.item.custom.TungstenArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(method = "getCurrentItemAttackStrengthDelay", at = @At("HEAD"))
    private void onCriticalHit(CallbackInfoReturnable<Float> cir) {
        Player player = (Player)(Object)this;
        if (player.isSprinting() && player.fallDistance > 0.0F) { // Vanilla critical hit conditions
            float multiplier = TungstenArmorItem.getCriticalDamageMultiplier(player);
            if (multiplier > 1.0f) {
                // Apply damage bonus to the next attack
                player.getPersistentData().putFloat("TungstenCritMultiplier", multiplier);
            }
        }
    }
}