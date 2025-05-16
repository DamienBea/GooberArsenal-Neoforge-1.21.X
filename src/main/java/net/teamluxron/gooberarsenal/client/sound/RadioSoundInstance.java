package net.teamluxron.gooberarsenal.client.sound;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundEvent;

public class RadioSoundInstance extends AbstractSoundInstance {
    public RadioSoundInstance(SoundEvent sound, Vec3 position) {
        super(sound, SoundSource.RECORDS, RandomSource.create());
        this.x = position.x;
        this.y = position.y;
        this.z = position.z;
        this.volume = 1.0F;
        this.looping = true; // Enable native looping
        this.attenuation = Attenuation.LINEAR;
    }
}
