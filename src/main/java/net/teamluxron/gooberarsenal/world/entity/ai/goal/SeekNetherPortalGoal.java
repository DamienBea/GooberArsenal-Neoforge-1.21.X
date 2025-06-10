package net.teamluxron.gooberarsenal.world.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.teamluxron.gooberarsenal.entity.custom.ZombifiedBreadEntity;

import java.util.EnumSet;

public class SeekNetherPortalGoal extends Goal {
    private final ZombifiedBreadEntity bread;
    private BlockPos portalPos;
    private double speedModifier;

    public SeekNetherPortalGoal(ZombifiedBreadEntity bread, double speedModifier) {
        this.bread = bread;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!bread.level().dimension().equals(Level.NETHER)) return false;
        if (bread.getTarget() != null) return false;
        portalPos = locateNearestPortal(bread.level(), bread.blockPosition());
        return portalPos != null;
    }

    @Override
    public void start() {
        bread.getNavigation().moveTo(portalPos.getX(), portalPos.getY(), portalPos.getZ(), speedModifier);
    }

    @Override
    public boolean canContinueToUse() {
        return !bread.getNavigation().isDone() &&
                bread.getTarget() == null &&
                bread.level().getBlockState(portalPos).getBlock() instanceof NetherPortalBlock;
    }

    private BlockPos locateNearestPortal(Level level, BlockPos center) {
        int range = 16;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int y = -4; y <= 4; y++) {
            for (int x = -range; x <= range; x++) {
                for (int z = -range; z <= range; z++) {
                    mutablePos.set(center.getX() + x, center.getY() + y, center.getZ() + z);
                    if (level.getBlockState(mutablePos).getBlock() instanceof NetherPortalBlock) {
                        return mutablePos.immutable();
                    }
                }
            }
        }
        return null;
    }
}