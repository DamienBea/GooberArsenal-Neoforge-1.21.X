package net.teamluxron.gooberarsenal.blocks.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;

import java.util.function.Supplier;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, GooberArsenal.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ForgingAnvilBlockEntity>> FORGING_ANVIL_BE =
            BLOCK_ENTITIES.register("forging_anvil_be", () ->
                    BlockEntityType.Builder.of(ForgingAnvilBlockEntity::new, ModBlocks.FORGING_ANVIL.get()).build(null));

    public static final Supplier<BlockEntityType<RadioBlockEntity>> RADIO_BE = BLOCK_ENTITIES.register(
            "radio_be",
            () -> BlockEntityType.Builder.of(
                    RadioBlockEntity::new,
                    ModBlocks.RADIO.get()
            ).build(null)
    );

    public static final Supplier<BlockEntityType<BrokenRadioBlockEntity>> BROKEN_RADIO_BE = BLOCK_ENTITIES.register(
            "broken_radio_be",
            () -> BlockEntityType.Builder.of(
                    BrokenRadioBlockEntity::new,
                    ModBlocks.BROKEN_RADIO.get()
            ).build(null)
    );

    public static final Supplier<BlockEntityType<BrokenRadioBlockEntity>> BALLS_BE = BLOCK_ENTITIES.register(
            "balls_be",
            () -> BlockEntityType.Builder.of(
                    BrokenRadioBlockEntity::new,
                    ModBlocks.BALLS.get()
            ).build(null)
    );

    public static final Supplier<BlockEntityType<FieldSwordBlockEntity>> FIELD_SWORD_BE =
            BLOCK_ENTITIES.register("field_sword", () ->
                    BlockEntityType.Builder.of(FieldSwordBlockEntity::new, ModBlocks.FIELD_SWORD.get()).build(null));


    public static final Supplier<BlockEntityType<FieldSwordBlockEntity>> MAGICAL_BEAN_CROP_BE =
            BLOCK_ENTITIES.register("magical_bean_crop", () ->
                    BlockEntityType.Builder.of(FieldSwordBlockEntity::new, ModBlocks.MAGICAL_BEAN_CROP.get()).build(null));




    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
