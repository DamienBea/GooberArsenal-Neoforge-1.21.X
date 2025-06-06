package net.teamluxron.gooberarsenal.item.custom.tungsten;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.Vec3;
import net.teamluxron.gooberarsenal.item.custom.weapon.PolearmItem;

public class TungstenPolearmItem extends PolearmItem {

    public TungstenPolearmItem(Tier tier, int damage, float speed, Properties props) {
        super(tier, damage, speed, props);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Apply base polearm behavior
        boolean success = super.hurtEnemy(stack, target, attacker);

        // Apply additional knockback
        if (!attacker.level().isClientSide && attacker instanceof Player player) {
            applyExtraKnockback(player, target, 1.0F); // 1.0F is extra knockback strength
        }

        return success;
    }

    private void applyExtraKnockback(Player player, LivingEntity target, float strength) {
        Vec3 direction = new Vec3(
                -Math.sin(player.getYRot() * ((float) Math.PI / 180F)),
                0,
                Math.cos(player.getYRot() * ((float) Math.PI / 180F))
        );
        target.push(direction.x * strength, 0.1D, direction.z * strength);
        target.hasImpulse = true;
    }
}