package net.teamluxron.gooberarsenal.item.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.item.custom.HammerItem;
import net.teamluxron.gooberarsenal.item.custom.SoulphyreArmorItem;
import net.teamluxron.gooberarsenal.item.material.SoulphyreMaterial;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = GooberArsenal.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    //Hammer code
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) return;

            int radius = mainHandItem.getEnchantmentLevel(
                    event.getLevel().registryAccess().registryOrThrow(Registries.ENCHANTMENT)
                            .getHolderOrThrow(ModEnchantments.TUNNELBORN)) > 0 ? 2 : 1;

            BlockPos.betweenClosedStream(
                            initialBlockPos.offset(-radius, -radius, -radius),
                            initialBlockPos.offset(radius, radius, radius))
                    .forEach(pos -> {
                        if (pos.equals(initialBlockPos)) return;

                        BlockState state = event.getLevel().getBlockState(pos);
                        if (!hammer.isCorrectToolForDrops(mainHandItem, state)) return;
                        if (HARVESTED_BLOCKS.contains(pos)) return;

                        HARVESTED_BLOCKS.add(pos);
                        serverPlayer.gameMode.destroyBlock(pos);
                        HARVESTED_BLOCKS.remove(pos);
                    });
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