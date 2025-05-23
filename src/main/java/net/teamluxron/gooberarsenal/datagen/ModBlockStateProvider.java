package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.blocks.custom.BrokenRadioBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GooberArsenal.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Basic blocks
        blockWithItem(ModBlocks.KEVIN_BLOCK);
        blockWithItem(ModBlocks.KEVIN_ORE);
        blockWithItem(ModBlocks.SCALED_ENDSTONE);
        blockWithItem(ModBlocks.DEEPSLATE_KEVIN_ORE);
        blockWithItem(ModBlocks.ANCIENT_CAGITE);
        blockWithItem(ModBlocks.CAGITE_BLOCK);

        // Radios with directional handling
        directionalRadio(ModBlocks.RADIO.get());
        directionalRadio(ModBlocks.BROKEN_RADIO.get());
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void directionalRadio(Block block) {
        ResourceLocation name = BuiltInRegistries.BLOCK.getKey(block);

        getVariantBuilder(block).forAllStates(state -> {
            int rotation = (int) state.getValue(HorizontalDirectionalBlock.FACING).toYRot();
            return ConfiguredModel.builder()
                    .modelFile(models().cubeAll(name.getPath(), modLoc("block/" + name.getPath())))
                    .rotationY(rotation)
                    .build();
        });

        simpleBlockItem(block, models().cubeAll(name.getPath(), modLoc("block/" + name.getPath())));
    }
}