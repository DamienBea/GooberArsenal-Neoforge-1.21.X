package net.teamluxron.gooberarsenal.enchantment;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.util.ModTags;


public class ModEnchantments {
    public static final ResourceKey<Enchantment> TUNNELBORN = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "tunnelborn")
    );

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);


        register(context, TUNNELBORN, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ModTags.Items.HAMMER_ENCHANTABLE), // Supported items from tag
                        HolderSet.direct(),                     // No primary items
                        5,                                      // Weight
                        1,                                      // Max level
                        Enchantment.constantCost(10),           // Min cost
                        Enchantment.constantCost(20),           // Max cost
                        1,                                      // Anvil cost
                        EquipmentSlotGroup.MAINHAND)            // Applicable slot
        ));
    }

    private static void register(BootstrapContext<Enchantment> registry,
                                 ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}