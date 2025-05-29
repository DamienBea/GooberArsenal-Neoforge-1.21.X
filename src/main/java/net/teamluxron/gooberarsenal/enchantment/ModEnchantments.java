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

import java.util.List;
import java.util.Optional;


public class ModEnchantments {

    public static final ResourceKey<Enchantment> TUNNELBORN = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "tunnelborn")
    );

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        ResourceKey<Enchantment> TUNNELBORN = ResourceKey.create(Registries.ENCHANTMENT,
                ResourceLocation.fromNamespaceAndPath("gooberarsenal", "tunnelborn"));
        EquipmentSlotGroup mainhand = EquipmentSlotGroup.MAINHAND;

        HolderGetter<Item> itemGetter = context.lookup(Registries.ITEM);
        TagKey<Item> hammerEnchantableTag = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("gooberarsenal", "hammer_enchantable"));
        HolderSet<Item> supportedItems = itemGetter.getOrThrow(hammerEnchantableTag);
        HolderSet<Item> primaryItems = HolderSet.direct();


        Enchantment.EnchantmentDefinition definition = new Enchantment.EnchantmentDefinition(
                supportedItems,
                Optional.of(primaryItems),
                5,                             // weight
                3,                             // max level
                Enchantment.constantCost(10), // min cost
                Enchantment.constantCost(20), // max cost
                1,                             // anvil cost
                List.of(mainhand)             // slots
        );

        // Build the enchantment
        Enchantment enchantment = new Enchantment(
                Component.translatable("enchantment.gooberarsenal.tunnelborn"),
                definition,
                HolderSet.direct(),           // exclusiveSet (empty)
                DataComponentMap.EMPTY        // effects (you can define later)
        );

        // Register the enchantment
        context.register(TUNNELBORN, enchantment);
    }
}