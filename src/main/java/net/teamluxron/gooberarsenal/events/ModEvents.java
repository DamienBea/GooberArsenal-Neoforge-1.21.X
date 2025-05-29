package net.teamluxron.gooberarsenal.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.item.custom.HammerItem;
import net.teamluxron.gooberarsenal.item.custom.TungstenShovelItem;

import java.util.HashSet;
import java.util.Set;

public class ModEvents {

    public static void register() {
        NeoForge.EVENT_BUS.register(ModEvents.class);
    }

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    private static boolean isBreakingBlocks = false;

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        if (isBreakingBlocks) return;

        Player player = event.getPlayer();
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        LevelAccessor levelAccessor = event.getLevel();
        if (!(levelAccessor instanceof Level level)) return;

        ItemStack mainHandItem = player.getMainHandItem();
        if (!(mainHandItem.getItem() instanceof HammerItem hammer)) return;

        BlockPos initialBlockPos = event.getPos();

        if (HARVESTED_BLOCKS.contains(initialBlockPos)) return;

        var enchantmentRegistry = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        var tunnelbornHolder = enchantmentRegistry.getHolder(ModEnchantments.TUNNELBORN).orElse(null);
        if (tunnelbornHolder == null) return;

        boolean hasTunnelborn = mainHandItem.getEnchantmentLevel(tunnelbornHolder) > 0;

        Set<BlockPos> toBreak = new HashSet<>();
        for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
            if (pos.equals(initialBlockPos)) continue;

            BlockState state = level.getBlockState(pos);
            if (!hammer.isCorrectToolForDrops(mainHandItem, state)) continue;

            toBreak.add(pos);
        }

        HARVESTED_BLOCKS.addAll(toBreak);
        HARVESTED_BLOCKS.add(initialBlockPos);

        isBreakingBlocks = true;

        for (BlockPos pos : toBreak) {
            serverPlayer.gameMode.destroyBlock(pos);
        }

        isBreakingBlocks = false;
        HARVESTED_BLOCKS.removeAll(toBreak);
        HARVESTED_BLOCKS.remove(initialBlockPos);
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