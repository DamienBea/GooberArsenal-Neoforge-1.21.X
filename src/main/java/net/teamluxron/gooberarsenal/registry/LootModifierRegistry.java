package net.teamluxron.gooberarsenal.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.teamluxron.gooberarsenal.loot.TungstenHammerLootModifier;

import java.util.function.Supplier;


public class LootModifierRegistry {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(
                    NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    "gooberarsenal"
            );

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> TUNGSTEN_HAMMER_FORTUNE =
            LOOT_MODIFIERS.register("tungsten_hammer_fortune", () -> TungstenHammerLootModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
    }
}