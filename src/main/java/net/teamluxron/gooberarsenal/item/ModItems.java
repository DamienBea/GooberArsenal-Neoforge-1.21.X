package net.teamluxron.gooberarsenal.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;



public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GooberArsenal.MOD_ID);

    public static final DeferredItem<Item> KEVIN_SHARD = ITEMS.register("kevin_shard",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
