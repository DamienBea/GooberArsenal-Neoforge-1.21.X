package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
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

        tag(ItemTags.SWORDS)
                .add(ModItems.SLAPSTICK_SWORD.get())
                .add(ModItems.FRYING_PAN.get())
                .add(ModItems.WOODEN_BAT.get())
                .add(ModItems.STONE_SPIKED_BAT.get())
                .add(ModItems.IRON_BAT.get())
                .add(ModItems.GOLDEN_BAT.get())
                .add(ModItems.DIAMOND_BAT.get())
                .add(ModItems.SOULPHYRE_BAT.get())
                .add(ModItems.NETHERITE_BAT.get())
                .add(ModItems.TUNGSTEN_BAT.get())
                .add(ModItems.BEE_BUNNY_BASHER.get())
                .add(ModItems.STAHP_SIGN.get())
                .add(ModItems.STEEL_PIPE.get())
                .add(ModItems.OBSIDIAN_SWORD.get())
                .add(ModItems.KENDO_STICK.get())
                .add(ModItems.CHAIR.get())
                .add(ModItems.SWITCH_BLADE.get())
                .add(ModItems.REBELS_KNIFE.get())
                .add(ModItems.THORN_OF_THE_DEAD_GODS.get())
                .add(ModItems.POISONERS_SIDEARM.get())
                .add(ModItems.RED_EYES_DREAM.get())
                .add(ModItems.WOODEN_SCYTHE.get())
                .add(ModItems.STONE_SCYTHE.get())
                .add(ModItems.IRON_SCYTHE.get())
                .add(ModItems.GOLDEN_SCYTHE.get())
                .add(ModItems.DIAMOND_SCYTHE.get())
                .add(ModItems.SOULPHYRE_SCYTHE.get())
                .add(ModItems.NETHERITE_SCYTHE.get())
                .add(ModItems.TUNGSTEN_SCYTHE.get())
                .add(ModItems.LIFE_SABER.get())
                .add(ModItems.WOODEN_DAGGER.get())
                .add(ModItems.STONE_DAGGER.get())
                .add(ModItems.IRON_DAGGER.get())
                .add(ModItems.GOLDEN_DAGGER.get())
                .add(ModItems.DIAMOND_DAGGER.get())
                .add(ModItems.SOULPHYRE_DAGGER.get())
                .add(ModItems.NETHERITE_DAGGER.get())
                .add(ModItems.TUNGSTEN_DAGGER.get())
                .add(ModItems.SPOON.get())
                .add(ModItems.SOULPHYRE_SWORD.get())
                .add(ModItems.TUNGSTEN_SWORD.get())
                .add(ModItems.ROSE_QUARTZ_SWORD.get())
//                .add(ModItems.IRON_RAPIER.get())
                .add(ModItems.WOODEN_HAMMER.get())
                .add(ModItems.STONE_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.SOULPHYRE_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())
                .add(ModItems.TUNGSTEN_HAMMER.get())
                .add(ModItems.MOSSY_MASHER.get())


                .add(ModItems.LYNNS_DESOLATION.get())
                .add(ModItems.ACONITE_AXE.get())
                .add(ModItems.STONE_POLEARM.get())
                .add(ModItems.IRON_POLEARM.get())
                .add(ModItems.GOLDEN_POLEARM.get())
                .add(ModItems.DIAMOND_POLEARM.get())
                .add(ModItems.SOULPHYRE_POLEARM.get())
                .add(ModItems.NETHERITE_POLEARM.get())
                .add(ModItems.TUNGSTEN_POLEARM.get())
                .add(ModItems.WOODEN_POLEARM.get())
        ;

        tag(ItemTags.AXES)
                .add(ModItems.FESTIVE_AXE.get())
                .add(ModItems.LYNNS_DESOLATION.get())
                .add(ModItems.ACONITE_AXE.get())
                .add(ModItems.STONE_POLEARM.get())
                .add(ModItems.IRON_POLEARM.get())
                .add(ModItems.GOLDEN_POLEARM.get())
                .add(ModItems.DIAMOND_POLEARM.get())
                .add(ModItems.SOULPHYRE_POLEARM.get())
                .add(ModItems.NETHERITE_POLEARM.get())
                .add(ModItems.WOODEN_POLEARM.get())
                .add(ModItems.SOULPHYRE_AXE.get())
                .add(ModItems.TUNGSTEN_AXE.get())
        ;

        tag(ItemTags.PICKAXES)
                .add(ModItems.WOODEN_HAMMER.get())
                .add(ModItems.STONE_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.SOULPHYRE_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())
                .add(ModItems.MOSSY_MASHER.get())
                .add(ModItems.SOULPHYRE_PICKAXE.get())
                .add(ModItems.TUNGSTEN_PICKAXE.get())
        ;

        tag(ModTags.Items.HAMMERS)
                .add(ModItems.WOODEN_HAMMER.get())
                .add(ModItems.STONE_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.SOULPHYRE_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())
                .add(ModItems.TUNGSTEN_HAMMER.get())
                .add(ModItems.MOSSY_MASHER.get())
        ;

        tag(ModTags.Items.HAMMER_ENCHANTABLE)
                .add(ModItems.WOODEN_HAMMER.get())
                .add(ModItems.STONE_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())
                .add(ModItems.TUNGSTEN_HAMMER.get())
                .add(ModItems.MOSSY_MASHER.get())
        ;

        tag(ItemTags.HOES)
                .add(ModItems.RED_EYES_DREAM.get())
                .add(ModItems.WOODEN_SCYTHE.get())
                .add(ModItems.STONE_SCYTHE.get())
                .add(ModItems.IRON_SCYTHE.get())
                .add(ModItems.GOLDEN_SCYTHE.get())
                .add(ModItems.DIAMOND_SCYTHE.get())
                .add(ModItems.NETHERITE_SCYTHE.get())
                .add(ModItems.SOULPHYRE_HOE.get())
                .add(ModItems.TUNGSTEN_HOE.get())
                ;

        tag(ItemTags.SHOVELS)
                .add(ModItems.SPOON.get())
                .add(ModItems.SOULPHYRE_SHOVEL.get())
                .add(ModItems.TUNGSTEN_SHOVEL.get())
        ;

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.CAGITE_BOOTS.get())
                .add(ModItems.SOULPHYRE_BOOTS.get())
                .add(ModItems.TUNGSTEN_BOOTS.get())
        ;

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.CAGITE_HELMET.get())
                .add(ModItems.SOULPHYRE_HELMET.get())
                .add(ModItems.TUNGSTEN_HELMET.get())
        ;

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.CAGITE_CHESTPLATE.get())
                .add(ModItems.STEVENS_JACKET.get())
                .add(ModItems.SOULPHYRE_CHESTPLATE.get())
                .add(ModItems.TUNGSTEN_CHESTPLATE.get())
        ;

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.CAGITE_LEGGINGS.get())
                .add(ModItems.SOULPHYRE_LEGGINGS.get())
                .add(ModItems.TUNGSTEN_LEGGINGS.get())
        ;
        tag(ItemTags.DURABILITY_ENCHANTABLE)

                .add(ModItems.ROSE_QUARTZ_SHIELD.get())
                .add(ModItems.ALLOY_SHIELD.get())
                .add(ModItems.DOOR_SHIELD.get())
                .add(ModItems.GRASS_CREST_SHIELD.get())
//                .add(ModItems.KNIGHTS_SHIELD.get())
                .add(ModItems.PALETTE.get())
//                .add(ModItems.TRASH_CAN.get())
        ;



    }
}
