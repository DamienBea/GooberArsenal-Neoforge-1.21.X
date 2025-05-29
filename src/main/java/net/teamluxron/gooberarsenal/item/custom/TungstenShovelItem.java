package net.teamluxron.gooberarsenal.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.teamluxron.gooberarsenal.enchantment.ModEnchantments;

import java.util.ArrayList;
import java.util.List;

public class TungstenShovelItem extends DiggerItem {

    public TungstenShovelItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_SHOVEL, properties);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initialPos, ServerPlayer player) {
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

        if (face == Direction.UP || face == Direction.DOWN) {
            for (int x = -range; x <= range; x++) {
                for (int z = -range; z <= range; z++) {
                    if (hasTunnelborn) {
                        for (int y = -range; y <= range; y++) {
                            positions.add(new BlockPos(initialPos.getX() + x, initialPos.getY() + y, initialPos.getZ() + z));
                        }
                    } else {
                        positions.add(new BlockPos(initialPos.getX() + x, initialPos.getY(), initialPos.getZ() + z));
                    }
                }
            }
        }

        if (face == Direction.NORTH || face == Direction.SOUTH) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    if (hasTunnelborn) {
                        for (int z = -range; z <= range; z++) {
                            positions.add(new BlockPos(initialPos.getX() + x, initialPos.getY() + y, initialPos.getZ() + z));
                        }
                    } else {
                        positions.add(new BlockPos(initialPos.getX() + x, initialPos.getY() + y, initialPos.getZ()));
                    }
                }
            }
        }

        if (face == Direction.EAST || face == Direction.WEST) {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {
                    if (hasTunnelborn) {
                        for (int x = -range; x <= range; x++) {
                            positions.add(new BlockPos(initialPos.getX() + x, initialPos.getY() + y, initialPos.getZ() + z));
                        }
                    } else {
                        positions.add(new BlockPos(initialPos.getX(), initialPos.getY() + y, initialPos.getZ() + z));
                    }
                }
            }
        }

        return positions;
    }
}