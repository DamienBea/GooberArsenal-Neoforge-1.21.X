package net.teamluxron.gooberarsenal.world.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.teamluxron.gooberarsenal.entity.custom.PeaShooterEntity;

public class RevengeHelpTargetGoal extends TargetGoal {
    private final PeaShooterEntity shooter;
    private final TargetingConditions conds = TargetingConditions.forCombat().range(16.0);
    private LivingEntity toAttack;
    private int lastTimestamp;

    public RevengeHelpTargetGoal(PeaShooterEntity shooter) {
        super(shooter, false);
        this.shooter = shooter;
    }

    @Override
    public boolean canUse() {
        Player owner = shooter.getOwner();
        if (owner != null) {
            LivingEntity attacker = owner.getLastHurtByMob();
            int ts1 = owner.getLastHurtByMobTimestamp();
            if (attacker != null && ts1 != lastTimestamp && canAttack(attacker, conds)) {
                toAttack = attacker;
                lastTimestamp = ts1;
                return true;
            }
            LivingEntity victim = owner.getLastHurtMob();
            int ts2 = owner.getLastHurtMobTimestamp();
            if (victim != null && ts2 != lastTimestamp && canAttack(victim, conds)) {
                toAttack = victim;
                lastTimestamp = ts2;
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        this.shooter.setTarget(toAttack);
        super.start();
    }
}