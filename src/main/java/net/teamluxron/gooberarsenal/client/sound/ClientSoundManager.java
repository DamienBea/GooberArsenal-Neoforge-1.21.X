package net.teamluxron.gooberarsenal.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.teamluxron.gooberarsenal.sound.ModSounds;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientSoundManager {
    private static final Map<BlockPos, RadioSoundInstance> ACTIVE_SOUNDS = new HashMap<>();

    public static void playRadioSound(BlockPos pos) {
        if (ACTIVE_SOUNDS.containsKey(pos)) return; // Avoid duplicates

        Minecraft mc = Minecraft.getInstance();
        SoundEvent sound = ModSounds.RADIO.get(); // Replace with your sound
        RadioSoundInstance soundInstance = new RadioSoundInstance(sound, Vec3.atCenterOf(pos));

        mc.getSoundManager().play(soundInstance);
        ACTIVE_SOUNDS.put(pos, soundInstance);
    }

    public static void stopRadioSound(BlockPos pos) {
        RadioSoundInstance sound = ACTIVE_SOUNDS.get(pos);
        if (sound != null) {
            Minecraft.getInstance().getSoundManager().stop(sound);
            ACTIVE_SOUNDS.remove(pos);
        }
    }
}