package net.teamluxron.gooberarsenal.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.blocks.custom.crops.PeaCropBlock;
import net.teamluxron.gooberarsenal.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // Blocks that drop themselves
        dropSelf(ModBlocks.KEVIN_BLOCK.get());
        dropSelf(ModBlocks.SOULPHYRE_BLOCK.get());
        dropSelf(ModBlocks.ROSE_QUARTZ_BLOCK.get());
        dropSelf(ModBlocks.ANCIENT_CAGITE.get());
        dropSelf(ModBlocks.CAGITE_BLOCK.get());
        dropSelf(ModBlocks.DRAGON_SCALED_TUNGSTEN_BLOCK.get());
        dropSelf(ModBlocks.FORGING_ANVIL.get());
        dropSelf(ModBlocks.RADIO.get());
        dropSelf(ModBlocks.BROKEN_RADIO.get());
        dropSelf(ModBlocks.FIELD_SWORD.get());
        dropSelf(ModBlocks.SCALED_ENDSTONE.get());
        dropSelf(ModBlocks.BALLS.get());
        dropSelf(ModBlocks.ECHO_FLOWER.get());

// Ore Drops
        add(ModBlocks.KEVIN_ORE.get(),
                block -> createOreDrop(ModBlocks.KEVIN_ORE.get(), ModItems.KEVIN_SHARDS.get()));

        add(ModBlocks.DEEPSLATE_KEVIN_ORE.get(),
                block -> createOreDrop(ModBlocks.KEVIN_ORE.get(), ModItems.KEVIN_SHARDS.get()));

        add(ModBlocks.SOULPHYRE_ORE.get(),
                block -> createOreDrop(ModBlocks.SOULPHYRE_ORE.get(), ModItems.SOULPHYRE.get()));

        add(ModBlocks.ROSE_QUARTZ_ORE.get(),
                block -> createOreDrop(ModBlocks.ROSE_QUARTZ_ORE.get(), ModItems.ROSE_QUARTZ.get()));


//Crops
        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MAGICAL_BEAN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PeaCropBlock.AGE, 3));

        this.add(ModBlocks.MAGICAL_BEAN_CROP.get(), this.createCropDrops(ModBlocks.MAGICAL_BEAN_CROP.get(),
                ModItems.MAGICAL_BEAN_SEED.get(), ModItems.MAGICAL_BEAN_SEED.get(), lootItemConditionBuilder));



    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
