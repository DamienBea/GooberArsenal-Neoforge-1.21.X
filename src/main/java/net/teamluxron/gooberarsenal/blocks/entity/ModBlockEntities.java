package net.teamluxron.gooberarsenal.blocks.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, GooberArsenal.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ForgingAnvilBlockEntity>> FORGING_ANVIL_BE =
            BLOCK_ENTITIES.register("forging_anvil_be", () ->
                    BlockEntityType.Builder.of(ForgingAnvilBlockEntity::new, ModBlocks.FORGING_ANVIL.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RadioBlockEntity>> RADIO_BE =
            BLOCK_ENTITIES.register("radio", () ->
                    BlockEntityType.Builder.of(
                            (pos, state) -> new RadioBlockEntity(ModBlockEntities.RADIO_BE.get(), pos, state),
                            ModBlocks.RADIO.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RadioBlockEntity>> BROKEN_RADIO_BE =
            BLOCK_ENTITIES.register("broken_radio", () ->
                    BlockEntityType.Builder.of(
                            (pos, state) -> new RadioBlockEntity(ModBlockEntities.BROKEN_RADIO_BE.get(), pos, state),
                            ModBlocks.BROKEN_RADIO.get()
                    ).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
