package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.blocks.custom.crops.PeaCropBlock;

import java.util.function.Function;

import static net.minecraft.world.level.block.DoublePlantBlock.HALF;

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





        makeCrop(((CropBlock) ModBlocks.MAGICAL_BEAN_CROP.get()), "magical_bean_crop_stage", "magical_bean_crop_stage");



    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        this.simpleBlockWithItem((Block)deferredBlock.get(), this.cubeAll((Block)deferredBlock.get()));
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }






    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((PeaCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "block/" + textureName + state.getValue(((PeaCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
}
