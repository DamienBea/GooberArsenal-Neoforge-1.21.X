package net.teamluxron.gooberarsenal.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.util.ModTags;


public class ModEnchantments {
    public static final ResourceKey<Enchantment> TUNNELBORN = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "tunnelborn"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, TUNNELBORN, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ModTags.Items.HAMMER_ENCHANTABLE),
                1,
                1,
                Enchantment.dynamicCost(5 ,7),
                Enchantment.dynamicCost(25, 7),
                2,
                EquipmentSlotGroup.MAINHAND
        ))

//                        .withEffect(EnchantmentEffectComponents.HIT_BLOCK, EnchantmentTarget.ATTACKER,
//                                EnchantmentTarget.VICTIM, new TunnelbornEnchantment())

        );
    }
    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}

