package net.teamluxron.gooberarsenal.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.custom.BrokenRadioBlock;
import net.teamluxron.gooberarsenal.blocks.custom.FieldSwordBlock;
import net.teamluxron.gooberarsenal.blocks.custom.ForgingAnvilBlock;
import net.teamluxron.gooberarsenal.blocks.custom.RadioBlock;
import net.teamluxron.gooberarsenal.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(GooberArsenal.MOD_ID);

    //Ores and materials

    public static final DeferredBlock<Block> KEVIN_ORE = registerBlock("kevin_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> SCALED_ENDSTONE = registerBlock("scaled_endstone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_KEVIN_ORE = registerBlock("deepslate_kevin_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> KEVIN_BLOCK = registerBlock("kevin_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));


    public static final DeferredBlock<Block> SOULPHYRE_ORE = registerBlock("soulphyre_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERRACK)
            ));

    public static final DeferredBlock<Block> SOULPHYRE_BLOCK = registerBlock("soulphyre_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> ROSE_QUARTZ_ORE = registerBlock("rose_quartz_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GILDED_BLACKSTONE)
            ));

    public static final DeferredBlock<Block> ROSE_QUARTZ_BLOCK = registerBlock("rose_quartz_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> ANCIENT_CAGITE = registerBlock("ancient_cagite",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.ANCIENT_DEBRIS)
            ));

    public static final DeferredBlock<Block> CAGITE_BLOCK = registerBlock("cagite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)
            ));


    public static final DeferredBlock<Block> DRAGON_SCALED_TUNGSTEN_BLOCK = registerBlock("dragon_scaled_tungsten_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)
            ));


    // BlockEntities

    public static final DeferredBlock<Block> FORGING_ANVIL = registerBlock("forging_anvil",
            () -> new ForgingAnvilBlock(BlockBehaviour.Properties.of()
                    .strength(5f, 1200f)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.ANVIL)
            ));

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public static final DeferredBlock<Block> RADIO = registerBlock("radio",
            () -> new RadioBlock(BlockBehaviour.Properties.of()
                    .strength(0f, 1200f)
                    .noOcclusion()
            ));

    public static final DeferredBlock<Block> BROKEN_RADIO = registerBlock("broken_radio",
            () -> new BrokenRadioBlock(BlockBehaviour.Properties.of()
                    .strength(0f, 1200f)
                    .noOcclusion()
            ));

    public static final DeferredBlock<Block> FIELD_SWORD = registerBlock("field_sword",
            () -> new FieldSwordBlock(BlockBehaviour.Properties.of()
                    .strength(0f, 1200f)
                    .noOcclusion()
            ));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}