package net.teamluxron.gooberarsenal.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.item.ModItems;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class FieldSwordBlockEntity extends BlockEntity {
    private static final String TAG_CHOICE = "swordChoice";
    private static final String TAG_SCALE = "swordScale";
    private static final String TAG_ROT_X = "rotationX";
    private static final String TAG_ROT_Y = "rotationY";
    private static final String TAG_ROT_Z = "rotationZ";
    private static final String TAG_SECOND_Y = "swordSecondaryY";

    private static final Item[] SWORDS = {
            ModItems.NETHERITE_GREATSWORD.get(),
            ModItems.TUNGSTEN_GREATSWORD.get(),
            ModItems.SOULPHYRE_GREATSWORD.get(),
            ModItems.IRON_GREATSWORD.get()
    };

    public Item swordItem;
    public float lengthScale;
    public float rotationXDeg;
    public float rotationYDeg;
    public float rotationZDeg;
    public float secondaryYRotDeg;

    public FieldSwordBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FIELD_SWORD_BE.get(), pos, state);

        Random r = new Random(Objects.hashCode(pos));
        this.swordItem = SWORDS[r.nextInt(SWORDS.length)];
        this.lengthScale = 16f + r.nextFloat() * 4f;
        this.rotationXDeg = r.nextFloat() * 30f - 15f;
        this.rotationYDeg = r.nextFloat() * 30f - 15f;
        this.rotationZDeg = r.nextFloat() * 30f - 15f;
        this.secondaryYRotDeg = r.nextFloat() * 360f;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        super.saveAdditional(tag, regs);
        tag.putInt(TAG_CHOICE, Arrays.asList(SWORDS).indexOf(swordItem));
        tag.putFloat(TAG_SCALE, lengthScale);
        tag.putFloat(TAG_ROT_X, rotationXDeg);
        tag.putFloat(TAG_ROT_Y, rotationYDeg);
        tag.putFloat(TAG_ROT_Z, rotationZDeg);
        tag.putFloat(TAG_SECOND_Y, secondaryYRotDeg);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        super.loadAdditional(tag, regs);
        int idx = tag.getInt(TAG_CHOICE);
        if (idx >= 0 && idx < SWORDS.length) {
            swordItem = SWORDS[idx];
        }
        this.lengthScale = tag.getFloat(TAG_SCALE);
        this.rotationXDeg = tag.getFloat(TAG_ROT_X);
        this.rotationYDeg = tag.getFloat(TAG_ROT_Y);
        this.rotationZDeg = tag.getFloat(TAG_ROT_Z);
        this.secondaryYRotDeg = tag.getFloat(TAG_SECOND_Y);
    }
}
