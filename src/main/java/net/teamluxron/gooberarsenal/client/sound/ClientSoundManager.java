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
    private static final Map<BlockPos, RadioSoundInstance> soundInstances = new HashMap<>();

    public static void playRadioSound(BlockPos pos, SoundEvent sound) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || soundInstances.containsKey(pos)) return;

        RadioSoundInstance instance = new RadioSoundInstance(sound, Vec3.atCenterOf(pos));
        soundInstances.put(pos, instance);
        mc.getSoundManager().play(instance);
    }

    public static void stopRadioSound(BlockPos pos) {
        Minecraft mc = Minecraft.getInstance();
        RadioSoundInstance instance = soundInstances.remove(pos);
        if (instance != null) {
            mc.getSoundManager().stop(instance);
        }
    }

    public static void stopAllRadioSounds() {
        Minecraft mc = Minecraft.getInstance();
        for (RadioSoundInstance instance : soundInstances.values()) {
            mc.getSoundManager().stop(instance);
        }
        soundInstances.clear();
    }

}