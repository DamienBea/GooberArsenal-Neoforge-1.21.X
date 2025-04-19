package net.teamluxron.gooberarsenal.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDamageTypes {
    public static final DeferredRegister<DamageType> DAMAGE_TYPES =
            DeferredRegister.create(Registries.DAMAGE_TYPE, "gooberarsenal");

    public static final DeferredHolder<DamageType, DamageType> RAPIER =
            DAMAGE_TYPES.register("rapier", () -> new DamageType(
                    "rapier",
                    DamageScaling.NEVER,  // Doesn't scale with difficulty
                    0.0f                 // No exhaustion
            ));

    public static void register(IEventBus bus) {
        DAMAGE_TYPES.register(bus);
    }
}
