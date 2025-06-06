package net.teamluxron.gooberarsenal.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.enchantment.effect.TunnelbornEffect;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, GooberArsenal.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> TUNNELBORN =
            ENCHANTMENT_EFFECTS.register("tunnelborn", () -> TunnelbornEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENCHANTMENT_EFFECTS.register(eventBus);
    }
}