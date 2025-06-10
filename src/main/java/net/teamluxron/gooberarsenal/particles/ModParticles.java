package net.teamluxron.gooberarsenal.particles;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, GooberArsenal.MOD_ID);


    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEAN_PARTICLE =
            PARTICLE_TYPES.register("bean_particle", () -> new SimpleParticleType(true));
}
