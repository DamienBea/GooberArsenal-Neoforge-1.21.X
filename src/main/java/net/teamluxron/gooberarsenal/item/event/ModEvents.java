package net.teamluxron.gooberarsenal.item.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;
import net.teamluxron.gooberarsenal.item.ModItems;
import net.teamluxron.gooberarsenal.item.custom.HammerItem;

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

    // Lifesteal
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
}