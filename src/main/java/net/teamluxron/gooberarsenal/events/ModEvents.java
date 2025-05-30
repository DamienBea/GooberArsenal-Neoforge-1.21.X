package net.teamluxron.gooberarsenal.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.item.custom.HammerItem;
import net.teamluxron.gooberarsenal.item.custom.SoulphyreArmorItem;
import net.teamluxron.gooberarsenal.item.custom.TungstenShovelItem;
import net.teamluxron.gooberarsenal.item.material.SoulphyreMaterial;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



@EventBusSubscriber(modid = GooberArsenal.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    private static final ThreadLocal<Boolean> isProcessing = ThreadLocal.withInitial(() -> false);

    public static void register() {
        NeoForge.EVENT_BUS.register(ModEvents.class);
    }

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        // Prevent recursion
        if (isProcessing.get()) {
            return;
        }

        Player player = event.getPlayer();
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        LevelAccessor levelAccessor = event.getLevel();
        if (!(levelAccessor instanceof Level level)) {
            return;
        }

        ItemStack mainHandItem = player.getMainHandItem();
        if (!(mainHandItem.getItem() instanceof HammerItem hammer)) {
            return;
        }

        BlockPos initialBlockPos = event.getPos();
        if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
            return;
        }

        // Declare toBreak outside try block
        Set<BlockPos> toBreak = new HashSet<>();

        // Set processing flag
        isProcessing.set(true);
        try {
            // Get all positions to break
            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                // Skip initial block (already being broken)
                if (pos.equals(initialBlockPos)) {
                    continue;
                }

                BlockState state = level.getBlockState(pos);
                // Check if tool is appropriate for the block
                if (!hammer.isCorrectToolForDrops(mainHandItem, state)) {
                    continue;
                }

                toBreak.add(pos);
            }

            // Add all positions to HARVESTED_BLOCKS to prevent recursion
            HARVESTED_BLOCKS.addAll(toBreak);
            HARVESTED_BLOCKS.add(initialBlockPos);

            // Break all collected blocks
            for (BlockPos pos : toBreak) {
                serverPlayer.gameMode.destroyBlock(pos);
            }
        } finally {
            // Clean up after processing
            HARVESTED_BLOCKS.remove(initialBlockPos);
            HARVESTED_BLOCKS.removeAll(toBreak);
            isProcessing.set(false);
        }
    }

    private static Direction getHitDirection(ServerPlayer player) {
        BlockHitResult result = player.level().clip(new ClipContext(
                player.getEyePosition(1.0F),
                player.getEyePosition(1.0F).add(player.getViewVector(1.0F).scale(6.0F)),
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                player
        ));
        return result.getType() == HitResult.Type.BLOCK ? result.getDirection() : null;
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

    // Lifesteal stuff
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        DamageContainer container = event.getContainer();

        if (container.getSource().getEntity() instanceof LivingEntity attacker) {
            ItemStack weapon = attacker.getMainHandItem();

            if (weapon.is(ModItems.THORN_OF_THE_DEAD_GODS.get())) {
                float lifestealAmount = container.getNewDamage() * 0.2f;

                attacker.heal(lifestealAmount);
            }
        }
    }

    //EXP gain stuff
    @SubscribeEvent
    public static void onXpGain(PlayerXpEvent.XpChange event) { // Add 'static' here
        Player player = event.getEntity();
        int soulphyreCount = 0;

        for(ItemStack armor : player.getArmorSlots()) {
            if(armor.getItem() instanceof SoulphyreArmorItem) {
                soulphyreCount++;
            }
        }

        if(soulphyreCount > 0) {
            float multiplier = soulphyreCount * 0.05f;
            if(soulphyreCount == 4) multiplier += 0.30f;
            event.setAmount((int) (event.getAmount() * (1 + multiplier)));
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (player == null || event.isCanceled()) return;

        ItemStack tool = player.getMainHandItem();
        if (!tool.isEmpty() &&
                tool.getItem() instanceof TieredItem tieredItem &&
                tieredItem.getTier() == SoulphyreMaterial.INSTANCE) {

            ServerLevel level = (ServerLevel) event.getLevel();
            BlockPos pos = event.getPos();

            level.getServer().execute(() -> {
                int xp = 3 + level.random.nextInt(3); // 3-5 XP
                ExperienceOrb.award(level, Vec3.atCenterOf(pos), xp);
            });
        }
    }
}