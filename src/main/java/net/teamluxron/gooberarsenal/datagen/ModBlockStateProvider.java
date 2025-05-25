package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, "gooberarsenal", exFileHelper);
    }

    protected void registerStatesAndModels() {
        this.blockWithItem(ModBlocks.KEVIN_BLOCK);
        this.blockWithItem(ModBlocks.KEVIN_ORE);
        this.blockWithItem(ModBlocks.DEEPSLATE_KEVIN_ORE);
        this.blockWithItem(ModBlocks.SOULPHYRE_ORE);
        this.blockWithItem(ModBlocks.SOULPHYRE_BLOCK);
        this.blockWithItem(ModBlocks.ROSE_QUARTZ_ORE);
        this.blockWithItem(ModBlocks.ROSE_QUARTZ_BLOCK);
        this.blockWithItem(ModBlocks.SCALED_ENDSTONE);
        this.blockWithItem(ModBlocks.ANCIENT_CAGITE);
        this.blockWithItem(ModBlocks.CAGITE_BLOCK);
        this.blockWithItem(ModBlocks.DRAGON_SCALED_TUNGSTEN_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        this.simpleBlockWithItem((Block)deferredBlock.get(), this.cubeAll((Block)deferredBlock.get()));
    }
}
