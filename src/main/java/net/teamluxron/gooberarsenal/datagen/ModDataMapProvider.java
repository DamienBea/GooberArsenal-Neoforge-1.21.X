package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.teamluxron.gooberarsenal.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    @SuppressWarnings("removal")
    protected void gather() {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModItems.WOODEN_BAT.getId(), new FurnaceFuel(200), false)
                .add(ModItems.WOODEN_DAGGER.getId(), new FurnaceFuel(200), false)
                .add(ModItems.WOODEN_HAMMER.getId(), new FurnaceFuel(200), false)
                .add(ModItems.WOODEN_POLEARM.getId(), new FurnaceFuel(200), false)
                .add(ModItems.WOODEN_SCYTHE.getId(), new FurnaceFuel(200), false)
                ;
    }
}
