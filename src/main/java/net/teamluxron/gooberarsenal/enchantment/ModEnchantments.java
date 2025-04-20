package net.teamluxron.gooberarsenal.enchantment;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.item.custom.HammerItem;

import java.util.Optional;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(Registries.ENCHANTMENT, "gooberarsenal");

    public static final DeferredHolder<Enchantment, Enchantment> TUNNELBORN =
            ENCHANTMENTS.register("tunnelborn", () ->
                    new Enchantment(
                            // Rarity
                            Enchantment.Rarity.RARE,
                            // Category (custom predicate)
                            new Enchantment.EnchantmentDefinition(
                                    item -> item instanceof PickaxeItem || item instanceof HammerItem,
                                    EquipmentSlot.MAINHAND
                            ),
                            // Effect
                            new Enchantment.EnchantmentEffectInstance<>(
                                    new TunnelbornEnchantment(),
                                    1, // min level
                                    1  // max level
                            )
                    ) {
                        @Override
                        public int getMinCost(int level) {
                            return 1 + level * 10;
                        }

                        @Override
                        public int getMaxCost(int level) {
                            return this.getMinCost(level) + 5;
                        }

                        @Override
                        public int getMaxLevel() {
                            return 1;
                        }
                    }
            );

    public static Optional<Holder<Enchantment>> getTunnelbornHolder(Level level) {
        return level.registryAccess()
                .registry(Registries.ENCHANTMENT)
                .flatMap(registry -> registry.getHolder(TUNNELBORN.getKey()));
    }
}

