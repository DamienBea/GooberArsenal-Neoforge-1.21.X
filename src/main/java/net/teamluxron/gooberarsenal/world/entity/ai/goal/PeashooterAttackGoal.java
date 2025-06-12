package net.teamluxron.gooberarsenal.world.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.teamluxron.gooberarsenal.entity.custom.PeaShooterEntity;

public class PeashooterAttackGoal extends Goal {
    private final PeaShooterEntity shooter;
    private int cooldown = 0;
    private int lookHold = 0;

    public PeashooterAttackGoal(PeaShooterEntity shooter) {
        this.shooter = shooter;
    }

    @Override
    public boolean canUse() {
        LivingEntity tgt = shooter.getTarget();
        return tgt != null && shooter.hasLineOfSight(tgt)
                && shooter.distanceToSqr(tgt) <= 20 * 20;
    }

    @Override
    public void tick() {
        if (cooldown-- > 0) return;

        LivingEntity tgt = shooter.getTarget();
        if (tgt == null) return;

        double eyeHeight = 12.0 / 16.0;
        Vec3 src = shooter.position().add(0, eyeHeight, 0);
        Vec3 dst = tgt.position().add(0, tgt.getBbHeight() * 0.8, 0);
        Vec3 delta = dst.subtract(src);

        float yaw  = (float)(Math.toDegrees(Math.atan2(delta.z, delta.x))) - 90f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(delta.y, Math.hypot(delta.x, delta.z))));
        shooter.setYRot(yaw);
        shooter.setXRot(pitch);
        shooter.yHeadRot = yaw;

        if (lookHold-- > 0) {
            shooter.getLookControl().setLookAt(tgt, 30f, 30f);
        }

        if (!shooter.level().isClientSide) {
            shooter.shootBean();
            shooter.level().broadcastEntityEvent(shooter, (byte)4);
        }

        cooldown = 20;
        lookHold = 30;
    }
}