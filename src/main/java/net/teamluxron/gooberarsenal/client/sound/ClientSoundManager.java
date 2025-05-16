package net.teamluxron.gooberarsenal.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import net.teamluxron.gooberarsenal.sound.ModSounds;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientSoundManager {
    private static final Map<BlockPos, RadioSoundInstance> PLAYING_SOUNDS = new ConcurrentHashMap<>();

    public static void playRadioSound(BlockPos pos) {
        stopRadioSound(pos); // Ensure no duplicate

        var sound = new RadioSoundInstance(
                ModSounds.RADIO.get(),
                SoundSource.RECORDS,
                Vec3.atCenterOf(pos),
                1.0f,
                1.0f
        );

        Minecraft.getInstance().getSoundManager().play(sound);
        PLAYING_SOUNDS.put(pos, sound);
    }

    public static void stopRadioSound(BlockPos pos) {
        var sound = PLAYING_SOUNDS.remove(pos);
        if (sound != null) {
            Minecraft.getInstance().getSoundManager().stop(sound);
        }
    }
}