package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GooberArsenal.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.OBSIDIAN_HILT.get());
        basicItem(ModItems.OBSIDIAN_HANDGUARD.get());
        basicItem(ModItems.GOOBER_UPGRADE_TEMPLATE.get());
        basicItem(ModItems.CAGITE_SCRAP.get());
        basicItem(ModItems.CAGITE_INGOT.get());
        basicItem(ModItems.KEVIN_SHARDS.get());
        basicItem(ModItems.IRON_PLATE.get());
        basicItem(ModItems.LIFE_SAVER.get());
//        basicItem(ModItems.RUBBER_CHICKEN.get());
//        basicItem(ModItems.ENERGY_BAR.get());
//        basicItem(ModItems.SANDVICH.get());
//        basicItem(ModItems.CHOCOLATE_CHIP_PANCAKES.get());
//        basicItem(ModItems.COPPER_APPLE.get());
//        basicItem(ModItems.SWITCH_CARTRIDGE.get());
    }
}
