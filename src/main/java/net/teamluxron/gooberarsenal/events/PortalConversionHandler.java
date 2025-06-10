package net.teamluxron.gooberarsenal.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.teamluxron.gooberarsenal.entity.ModEntities;
import net.teamluxron.gooberarsenal.entity.custom.ZombifiedBreadEntity;

public class PortalConversionHandler {
    private static final int PORTAL_SEARCH_RADIUS = 16;

    public void onLevelTick(LevelTickEvent.Post event) {
        Level rawLevel = event.getLevel();
        if (!(rawLevel instanceof ServerLevel level)) return;

        for (ItemEntity item : level.getEntities(EntityType.ITEM, e -> true)) {
            checkItemInPortal(level, item);
        }
    }

    private static void checkItemInPortal(ServerLevel level, ItemEntity itemEntity) {
        if (!itemEntity.getItem().is(Items.BREAD)) return;

        BlockPos pos = itemEntity.blockPosition();
        BlockState blockState = level.getBlockState(pos);

        if (!(blockState.getBlock() instanceof NetherPortalBlock) &&
                !(blockState.getBlock() instanceof EndPortalBlock)) {
            return;
        }

        ResourceKey<Level> destinationDim = level.dimension() == Level.END ? Level.OVERWORLD :
                level.dimension() == Level.OVERWORLD ? Level.END :
                        Level.OVERWORLD;

        ServerLevel destLevel = level.getServer().getLevel(destinationDim);
        if (destLevel == null) return;

        BlockPos destPortalPos = findConnectedPortal(level, pos, destLevel);
        Vec3 spawnPos = calculateSafeSpawnPosition(destLevel, destPortalPos);

        int count = itemEntity.getItem().getCount();
        for (int i = 0; i < count; i++) {
            ZombifiedBreadEntity bread = ModEntities.ZOMBIFIED_BREAD.get().create(destLevel);
            if (bread != null) {
                bread.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
                bread.setDeltaMovement(
                        (level.random.nextDouble() - 0.5) * 0.25,
                        0.25,
                        (level.random.nextDouble() - 0.5) * 0.25
                );
                destLevel.addFreshEntity(bread);
            }
        }

        itemEntity.discard();
    }

    private static BlockPos findConnectedPortal(ServerLevel sourceLevel, BlockPos sourcePos, ServerLevel destLevel) {
        if (destLevel.dimension() == Level.END) {
            return new BlockPos(100, 50, 0);
        }
        double scale = sourceLevel.dimension() == Level.NETHER ? 8.0 : 1.0/8.0;
        BlockPos scaledPos = BlockPos.containing(
                sourcePos.getX() * scale,
                sourcePos.getY(),
                sourcePos.getZ() * scale
        );
        for (int x = -PORTAL_SEARCH_RADIUS; x <= PORTAL_SEARCH_RADIUS; x++) {
            for (int z = -PORTAL_SEARCH_RADIUS; z <= PORTAL_SEARCH_RADIUS; z++) {
                for (int y = -PORTAL_SEARCH_RADIUS; y <= PORTAL_SEARCH_RADIUS; y++) {
                    BlockPos testPos = scaledPos.offset(x, y, z);
                    if (destLevel.getBlockState(testPos).getBlock() instanceof NetherPortalBlock) {
                        return testPos;
                    }
                }
            }
        }
        return scaledPos;
    }

    private static Vec3 calculateSafeSpawnPosition(ServerLevel destLevel, BlockPos portalPos) {
        if (destLevel.dimension() == Level.END) {
            return new Vec3(100.5, 50.0, 0.5);
        }

        int groundY = findGroundHeight(destLevel, portalPos);
        return new Vec3(
                portalPos.getX() + 0.5,
                groundY + 1.0,
                portalPos.getZ() + 0.5
        );
    }

    private static int findGroundHeight(ServerLevel level, BlockPos pos) {
        for (int y = level.getMinBuildHeight(); y < level.getMaxBuildHeight(); y++) {
            BlockPos testPos = new BlockPos(pos.getX(), y, pos.getZ());
            if (isSolidGround(level, testPos)) {
                return y;
            }
        }

        return level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ());
    }

    private static boolean isSolidGround(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.isFaceSturdy(level, pos, Direction.UP)
                && level.isEmptyBlock(pos.above());
    }
}