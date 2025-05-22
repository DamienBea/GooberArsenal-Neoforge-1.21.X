package net.teamluxron.gooberarsenal.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RadioSoundInstance extends AbstractSoundInstance {
    private static final float MAX_DISTANCE = 65.0f;
    private final Vec3 position;

    public RadioSoundInstance(SoundEvent sound, Vec3 position) {
        super(sound, SoundSource.RECORDS, RandomSource.create());
        this.position = position;
        this.attenuation = Attenuation.LINEAR;
        this.looping = true;
        this.volume = calculateVolume();
        this.x = position.x;
        this.y = position.y;
        this.z = position.z;
    }

    private float calculateVolume() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options == null) return 0f;

        float distance = (float)mc.player.position().distanceTo(position);
        float musicVolume = mc.options.getSoundSourceVolume(SoundSource.RECORDS);

        float volumePercent;
        if (distance <= 32) {
            volumePercent = 1.0f; // 100%
        } else if (distance <= 40) {
            volumePercent = 0.7f + (0.3f * (40 - distance) / 8f); // 70-100%
        } else if (distance <= 56) {
            volumePercent = 0.5f + (0.2f * (56 - distance) / 16f); // 50-70%
        } else if (distance <= 65) {
            volumePercent = 0.3f * (65 - distance) / 9f; // 0-30%
        } else {
            volumePercent = 0f;
        }

        return 4.0f * musicVolume * volumePercent; // 4.0f matches jukebox max volume
    }

    public boolean isStopped = false;

    public void tick() {
        float newVolume = calculateVolume();
        if (newVolume <= 0.001f) {
            this.isStopped = true; // Mark for stopping
        } else {
            this.volume = newVolume;
        }
    }

    public boolean isStopped() {
        return isStopped;
    }
}