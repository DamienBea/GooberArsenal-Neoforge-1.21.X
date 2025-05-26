package net.teamluxron.gooberarsenal.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @ModifyVariable(method = "hurt", at = @At("HEAD"), ordinal = 0)
    private float modifyDamage(float amount, DamageSource source) {
        if (source.getEntity() instanceof Player player) {
            float multiplier = player.getPersistentData().getFloat("TungstenCritMultiplier");
            if (multiplier > 1.0f) {
                player.getPersistentData().remove("TungstenCritMultiplier");
                return amount * multiplier;
            }
        }
        return amount;
    }
}