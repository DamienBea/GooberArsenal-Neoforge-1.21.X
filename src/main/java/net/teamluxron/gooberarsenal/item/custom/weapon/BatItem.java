package net.teamluxron.gooberarsenal.item.custom.weapon;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class BatItem extends SwordItem {

    private final float horizontalKnockback;
    private final float verticalKnockback;

    public BatItem(Tier tier, int attackDamageMod, float attackSpeedMod,
                   float horizontalKnockback, float verticalKnockback,
                   Item.Properties properties) {
        super(tier, properties.attributes(SwordItem.createAttributes(tier, attackDamageMod, attackSpeedMod)));
        this.horizontalKnockback = horizontalKnockback;
        this.verticalKnockback = verticalKnockback;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide()) {
            Vec3 direction = target.position().subtract(attacker.position()).normalize();
            target.setDeltaMovement(direction.x * horizontalKnockback, verticalKnockback, direction.z * horizontalKnockback);
            target.hurtMarked = true;
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}