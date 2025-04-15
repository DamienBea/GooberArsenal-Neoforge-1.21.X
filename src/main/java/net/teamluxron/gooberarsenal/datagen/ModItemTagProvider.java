package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.util.ModTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, GooberArsenal.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
//        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
//                .add(ModItems.BISMUTH.get())
//                .add(ModItems.RAW_BISMUTH.get())
//                .add(Items.COAL)
//                .add(Items.STICK)
//                .add(Items.COMPASS);

        tag(ItemTags.SWORDS)
                .add(ModItems.SLAPSTICK_SWORD.get())
                .add(ModItems.FRYING_PAN.get())
                .add(ModItems.WOODEN_BAT.get())
                .add(ModItems.IRON_BAT.get())
                .add(ModItems.GOLDEN_BAT.get())
                .add(ModItems.DIAMOND_BAT.get())
                .add(ModItems.NETHERITE_BAT.get())
                .add(ModItems.BEE_BUNNY_BASHER.get())
                .add(ModItems.STAHP_SIGN.get())
                .add(ModItems.STEEL_PIPE.get())
                .add(ModItems.OBSIDIAN_SWORD.get())
                .add(ModItems.KENDO_STICK.get())
                .add(ModItems.CHAIR.get())
                .add(ModItems.SWITCH_BLADE.get())
                .add(ModItems.RED_EYES_DREAM.get())
                .add(ModItems.WOODEN_SCYTHE.get())
                .add(ModItems.LIFE_SABER.get());


        tag(ItemTags.AXES)
                .add(ModItems.FESTIVE_AXE.get())
                .add(ModItems.LYNNS_DESOLATION.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.SPOON.get());

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.CAGITE_BOOTS.get());

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.CAGITE_HELMET.get());

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.CAGITE_CHESTPLATE.get());

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.CAGITE_LEGGINGS.get());

    }
}
