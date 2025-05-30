package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;

import java.util.ArrayList;
import java.util.List;


public class HammerItem extends DiggerItem {

    public HammerItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int baseRange, BlockPos initialPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();

        BlockHitResult traceResult = player.level().clip(new ClipContext(
                player.getEyePosition(1f),
                player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f)),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        if (traceResult.getType() == HitResult.Type.MISS) return positions;

        ItemStack heldItem = player.getMainHandItem();
        Holder<Enchantment> tunnelborn = player.level().registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolder(ModEnchantments.TUNNELBORN)
                .orElse(null);

        boolean hasTunnelborn = tunnelborn != null && heldItem.getEnchantmentLevel(tunnelborn) > 0;

        Direction face = traceResult.getDirection();

        int r = baseRange; // 1
        int extra = hasTunnelborn ? 1 : 0;

        if (face == Direction.UP || face == Direction.DOWN) {
            // XZ plane (Y fixed)
            for (int x = -r - extra; x <= r + extra; x++) {
                for (int z = -r - extra; z <= r + extra; z++) {
                    positions.add(new BlockPos(initialPos.getX() + x, initialPos.getY(), initialPos.getZ() + z));
                }
            }
        } else if (face == Direction.NORTH || face == Direction.SOUTH) {
            // XY plane (Z fixed)
            for (int x = -r - extra; x <= r + extra; x++) {
                for (int y = -r - extra; y <= r + extra; y++) {
                    positions.add(new BlockPos(initialPos.getX() + x, initialPos.getY() + y, initialPos.getZ()));
                }
            }
        } else if (face == Direction.EAST || face == Direction.WEST) {
            // YZ plane (X fixed)
            for (int y = -r - extra; y <= r + extra; y++) {
                for (int z = -r - extra; z <= r + extra; z++) {
                    positions.add(new BlockPos(initialPos.getX(), initialPos.getY() + y, initialPos.getZ() + z));
                }
            }
        }

        return positions;
    }
}
