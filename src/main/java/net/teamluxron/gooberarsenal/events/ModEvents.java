package net.teamluxron.gooberarsenal.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.item.custom.HammerItem;
import net.teamluxron.gooberarsenal.item.custom.TungstenShovelItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ModEvents {

    public static void register() {
        NeoForge.EVENT_BUS.register(ModEvents.class);
    }


    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    private static boolean isBreakingBlocks = false;
    private static final ThreadLocal<Boolean> isProcessing = ThreadLocal.withInitial(() -> false);

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        // Prevent recursive calls
        if (isProcessing.get()) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            // Set thread flag to prevent recursion
            isProcessing.set(true);
            try {
                // Default range (3x3)
                int range = 0;

                // Get enchantment registry
                Registry<Enchantment> enchantRegistry = event.getLevel().registryAccess().registryOrThrow(Registries.ENCHANTMENT);
                ResourceLocation tunnelbornLoc = ModEnchantments.TUNNELBORN.location();

                // Check if tunnelborn enchantment exists in registry
                if (enchantRegistry.containsKey(tunnelbornLoc)) {
                    // Get Holder reference for the enchantment
                    Holder<Enchantment> tunnelbornHolder = enchantRegistry.getHolderOrThrow(
                            ResourceKey.create(Registries.ENCHANTMENT, tunnelbornLoc)
                    );

                    // Check enchantment level using Holder
                    if (mainHandItem.getEnchantmentLevel(tunnelbornHolder) > 0) {
                        range = 0; // 5x5 area
                    }
                }

                // Collect all positions first
                List<BlockPos> positions = HammerItem.getBlocksToBeDestroyed(range, initialBlockPos, serverPlayer);

                // Process blocks
                for (BlockPos pos : positions) {
                    if (pos.equals(initialBlockPos) ||
                            HARVESTED_BLOCKS.contains(pos) ||
                            !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                        continue;
                    }

                    // Mark position as processed
                    HARVESTED_BLOCKS.add(pos);
                    try {
                        // Break block without triggering our event
                        serverPlayer.gameMode.destroyBlock(pos);
                    } finally {
                        HARVESTED_BLOCKS.remove(pos);
                    }
                }
            } finally {
                isProcessing.set(false);
            }
        }
    }



    @SubscribeEvent
    public static void onTungstenShovelUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof TungstenShovelItem shovel && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) return;

            Set<BlockPos> toBreak = new HashSet<>();
            for (BlockPos pos : TungstenShovelItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if (pos.equals(initialBlockPos)) continue;

                if (!shovel.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) continue;

                toBreak.add(pos);
            }

            HARVESTED_BLOCKS.addAll(toBreak);
            HARVESTED_BLOCKS.add(initialBlockPos);

            for (BlockPos pos : toBreak) {
                serverPlayer.gameMode.destroyBlock(pos);
            }

            HARVESTED_BLOCKS.removeAll(toBreak);
            HARVESTED_BLOCKS.remove(initialBlockPos);
        }
    }



    @SubscribeEvent
    public static void onPlayerKill(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player killedPlayer) {
            DamageSource source = killedPlayer.getLastDamageSource();

            if (source != null && source.getEntity() instanceof Player attacker) {
                ItemStack weapon = attacker.getMainHandItem();

                if (weapon.getItem() == ModItems.ROSE_QUARTZ_SWORD.get()) {
                    MobEffectInstance existingRegen = attacker.getEffect(MobEffects.REGENERATION);
                    int amplifier = existingRegen != null
                            ? Math.min(existingRegen.getAmplifier() + 1, 2)
                            : 0;

                    attacker.addEffect(new MobEffectInstance(
                            MobEffects.REGENERATION,
                            100,
                            amplifier,
                            false,
                            true
                    ));
                }
            }
        }
    }
}