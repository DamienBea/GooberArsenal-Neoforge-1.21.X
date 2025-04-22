package net.teamluxron.gooberarsenal.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, GooberArsenal.MOD_ID);

    public static final Supplier<SoundEvent> WEEZER = registerSoundEvent("weezer");

    public static final Supplier<SoundEvent> RUBBER_CHICKEN = registerSoundEvent("rubber_chicken");

    public static final Supplier<SoundEvent> RADIO = registerSoundEvent("radio");
    public static final Supplier<SoundEvent> BROKEN_RADIO = registerSoundEvent("broken_radio");
    public static final Supplier<SoundEvent> BUTTON = registerSoundEvent("button");


    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
