package net.teamluxron.gooberarsenal.blocks.entity.function;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FieldSwordBlockEntity extends BlockEntity {
    private static final String TAG_CHOICE = "swordChoice";
    private static final String TAG_SEC_SCALE = "secScale";
    private static final String TAG_SEC_ROT_X = "secRotX";
    private static final String TAG_SEC_ROT_Y = "secRotY";
    private static final String TAG_SEC_ROT_Z = "secRotZ";
    private static final String TAG_SEC_Y_ROT = "secYRot";

    public static final List<TagKey<Item>> REPLACEMENT_TAGS = List.of(
            ItemTags.SWORDS,
            ItemTags.AXES,
            ModTags.Items.HAMMERS
    );

    private static final Item[] SWORDS = {
            ModItems.NETHERITE_GREATSWORD.get(),
            ModItems.TUNGSTEN_GREATSWORD.get(),
            ModItems.SOULPHYRE_GREATSWORD.get(),
            ModItems.IRON_GREATSWORD.get()
    };

    private static final float BASE_SCALE = 4.0f;
    private static final float BASE_ROT_X = 0;
    private static final float BASE_ROT_Y = 0;
    private static final float BASE_ROT_Z = 135;

    private float secondaryScaleFactor = 1.0f;
    private float secondaryRotX;
    private float secondaryRotY;
    private float secondaryRotZ;
    private float secondaryYRot;

    public Item swordItem;

    public FieldSwordBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FIELD_SWORD_BE.get(), pos, state);
        initializeRandomSword();
    }

    private void initializeRandomSword() {
        Random r = new Random(Objects.hashCode(worldPosition));
        this.swordItem = SWORDS[r.nextInt(SWORDS.length)];

        this.secondaryScaleFactor = 1.0f + r.nextFloat() * 2.0f;  // 1.0-3.0 range
        this.secondaryRotX = r.nextFloat() * 30f - 15f;
        this.secondaryRotY = r.nextFloat() * 30f - 15f;
        this.secondaryRotZ = r.nextFloat() * 30f - 15f;
        this.secondaryYRot = r.nextFloat() * 360f;
    }

    public float getTotalScale() {
        return BASE_SCALE * secondaryScaleFactor;
    }

    public float getTotalRotationX() {
        return BASE_ROT_X + secondaryRotX;
    }

    public float getTotalRotationY() {
        return BASE_ROT_Y + secondaryRotY;
    }

    public float getTotalRotationZ() {
        return BASE_ROT_Z + secondaryRotZ;
    }

    public float getSecondaryYRot() {
        return secondaryYRot;
    }

    public void adjustScale(boolean increase) {
        float step = 0.1f;
        if (increase) {
            secondaryScaleFactor = Math.min(3.0f, secondaryScaleFactor + step);
        } else {
            secondaryScaleFactor = Math.max(1.0f, secondaryScaleFactor - step);
        }
        setChanged();
        notifyClients();
    }

    public void adjustSecondaryYRot(boolean increase) {
        float step = 10f;
        if (increase) {
            secondaryYRot = (secondaryYRot + step) % 360;
        } else {
            secondaryYRot = (secondaryYRot - step) % 360;
        }
        if (secondaryYRot < 0) secondaryYRot += 360;
        setChanged();
        notifyClients();
    }

    public void adjustRotX(boolean increase) {
        adjustRotation(increase, () -> secondaryRotX, v -> secondaryRotX = v, -15f, 15f);
    }

    public void adjustRotZ(boolean increase) {
        adjustRotation(increase, () -> secondaryRotZ, v -> secondaryRotZ = v, -15f, 15f);
    }

    private void adjustRotation(boolean increase, Supplier<Float> getter, Consumer<Float> setter, float min, float max) {
        float step = 1f;
        float current = getter.get();
        float newVal = increase ? current + step : current - step;
        newVal = Mth.clamp(newVal, min, max);
        setter.accept(newVal);
        setChanged();
        notifyClients();
    }

    private void notifyClients() {
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    public static boolean isAllowedReplacementItem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        for (TagKey<Item> tag : REPLACEMENT_TAGS) {
            if (stack.is(tag)) return true;
        }
        return false;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        super.saveAdditional(tag, regs);
        tag.putString(TAG_CHOICE, BuiltInRegistries.ITEM.getKey(swordItem).toString());
        tag.putFloat(TAG_SEC_SCALE, secondaryScaleFactor);
        tag.putFloat(TAG_SEC_ROT_X, secondaryRotX);
        tag.putFloat(TAG_SEC_ROT_Y, secondaryRotY);
        tag.putFloat(TAG_SEC_ROT_Z, secondaryRotZ);
        tag.putFloat(TAG_SEC_Y_ROT, secondaryYRot);
    }

    public void setSwordItem(Item item) {
        this.swordItem = item;
        setChanged();
        notifyClients();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        super.loadAdditional(tag, regs);
        if (tag.contains(TAG_CHOICE)) {
            String itemId = tag.getString(TAG_CHOICE);
            ResourceLocation id = ResourceLocation.tryParse(itemId);
            if (id != null) {
                Item item = BuiltInRegistries.ITEM.get(id);
                if (item != null && item != Items.AIR) {
                    swordItem = item;
                } else {
                    initializeRandomSword();
                }
            } else {
                initializeRandomSword();
            }
        } else {
            initializeRandomSword();
        }

        if(tag.contains(TAG_SEC_SCALE)) secondaryScaleFactor = tag.getFloat(TAG_SEC_SCALE);
        if(tag.contains(TAG_SEC_ROT_X)) secondaryRotX = tag.getFloat(TAG_SEC_ROT_X);
        if(tag.contains(TAG_SEC_ROT_Y)) secondaryRotY = tag.getFloat(TAG_SEC_ROT_Y);
        if(tag.contains(TAG_SEC_ROT_Z)) secondaryRotZ = tag.getFloat(TAG_SEC_ROT_Z);
        if(tag.contains(TAG_SEC_Y_ROT)) secondaryYRot = tag.getFloat(TAG_SEC_Y_ROT);
    }

    // Network synchronization
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return saveWithoutMetadata(provider);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider provider) {
        super.onDataPacket(net, pkt, provider);
        handleUpdateTag(pkt.getTag(), provider);
    }

    public void loadFromUpdateTag(CompoundTag tag, HolderLookup.Provider provider) {
        loadAdditional(tag, provider);
    }
}