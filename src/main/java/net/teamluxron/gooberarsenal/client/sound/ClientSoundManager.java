package net.teamluxron.gooberarsenal.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.teamluxron.gooberarsenal.sound.ModSounds;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientSoundManager {
    private static final Map<BlockPos, RadioSoundInstance> activeSounds = new HashMap<>();

    public static void playRadioSound(BlockPos pos) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.getSoundManager() == null) return;

        // Stop existing sound if any
        stopRadioSound(pos);

        RadioSoundInstance sound = new RadioSoundInstance(
                ModSounds.RADIO.get(),
                Vec3.atCenterOf(pos)
        );

        mc.getSoundManager().play(sound);
        activeSounds.put(pos, sound);
    }

    public static void stopRadioSound(BlockPos pos) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.getSoundManager() == null) return;

        RadioSoundInstance sound = activeSounds.get(pos);
        if (sound != null) {
            sound.isStopped = true; // Proper way to stop
            mc.getSoundManager().stop(sound);
            activeSounds.remove(pos);
        }
    }

    public static void stopAllRadioSounds() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.getSoundManager() == null) return;

        activeSounds.forEach((pos, sound) -> {
            sound.isStopped = true;
            mc.getSoundManager().stop(sound);
        });
        activeSounds.clear();
    }
}