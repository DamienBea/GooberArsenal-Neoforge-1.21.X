package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.teamluxron.gooberarsenal.registry.ModDamageTypes;

public class RapierDamageSource {
    public static DamageSource rapierDamage(Entity attacker) {
        return new DamageSource(
                attacker.level().registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.RAPIER.getKey()),
                attacker
        );
    }
}