package net.teamluxron.gooberarsenal.client.sound;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundEvent;

public class RadioSoundInstance extends AbstractSoundInstance {
    public RadioSoundInstance(SoundEvent sound, SoundSource source, Vec3 position, float volume, float pitch) {
        super(sound, source, RandomSource.create());
        this.x = position.x;
        this.y = position.y;
        this.z = position.z;
        this.volume = volume;
        this.pitch = pitch;
        this.looping = true; // Sound loops continuously
        this.attenuation = Attenuation.LINEAR;
    }
}
