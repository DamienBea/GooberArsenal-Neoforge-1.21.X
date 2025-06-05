package net.teamluxron.gooberarsenal.events;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.item.custom.coreitem.AreaMiningItem;
import net.teamluxron.gooberarsenal.item.custom.weapon.GreatSwordItem;
import net.teamluxron.gooberarsenal.item.custom.armor.SoulphyreArmorItem;
import net.teamluxron.gooberarsenal.item.material.SoulphyreMaterial;

import java.util.HashSet;
import java.util.Set;



@EventBusSubscriber(modid = GooberArsenal.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    private static final ThreadLocal<Boolean> isProcessing = ThreadLocal.withInitial(() -> false);

    public static void register() {
        NeoForge.EVENT_BUS.register(ModEvents.class);
    }

    @SubscribeEvent
    public static void onAreaMiningToolUsage(BlockEvent.BreakEvent event) {
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
        if (!(mainHandItem.getItem() instanceof AreaMiningItem miningTool)) {
            return;
        }

        BlockPos initialBlockPos = event.getPos();
        if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
            return;
        }

        Set<BlockPos> toBreak = new HashSet<>();

        isProcessing.set(true);
        try {
            for (BlockPos pos : AreaMiningItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if (pos.equals(initialBlockPos)) {
                    continue;
                }

                BlockState state = level.getBlockState(pos);
                if (!miningTool.isCorrectToolForDrops(mainHandItem, state)) {
                    continue;
                }

                toBreak.add(pos);
            }

            HARVESTED_BLOCKS.addAll(toBreak);
            HARVESTED_BLOCKS.add(initialBlockPos);

            for (BlockPos pos : toBreak) {
                serverPlayer.gameMode.destroyBlock(pos);
            }
        } finally {
            HARVESTED_BLOCKS.remove(initialBlockPos);
            HARVESTED_BLOCKS.removeAll(toBreak);
            isProcessing.set(false);
        }
    }

//    @SubscribeEvent
//    public static void onPlayerKill(LivingDeathEvent event) {
//        if (event.getEntity() instanceof Player killedPlayer) {
//            DamageSource source = killedPlayer.getLastDamageSource();
//
//            if (source != null && source.getEntity() instanceof Player attacker) {
//                ItemStack weapon = attacker.getMainHandItem();
//
//                if (weapon.getItem() == ModItems.ROSE_QUARTZ_SWORD.get()) {
//                    MobEffectInstance existingRegen = attacker.getEffect(MobEffects.REGENERATION);
//                    int amplifier = existingRegen != null
//                            ? Math.min(existingRegen.getAmplifier() + 1, 2)
//                            : 0;
//
//                    attacker.addEffect(new MobEffectInstance(
//                            MobEffects.REGENERATION,
//                            100,
//                            amplifier,
//                            false,
//                            true
//                    ));
//                }
//            }
//        }
//    }

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

    @SubscribeEvent
    public static void onXpGain(PlayerXpEvent.XpChange event) {
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
//Code to throw stuff on the ground if G sword
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) return;

        ItemStack mainhand = player.getMainHandItem();
        ItemStack offhand = player.getOffhandItem();

        boolean isMainHandGreatSword = mainhand.getItem() instanceof GreatSwordItem;
        boolean isOffHandGreatSword = offhand.getItem() instanceof GreatSwordItem;

        if (isOffHandGreatSword) {
            player.drop(offhand, true);
            player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            return;
        }

        if (isMainHandGreatSword && !offhand.isEmpty()) {
            player.drop(offhand, true);
            player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
        }
    }

}