package net.teamluxron.gooberarsenal.item.custom.weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.*;


public class PolearmItem extends SwordItem {

    public static final ResourceLocation ATTACK_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "polearm_attack_damage");
    public static final ResourceLocation ATTACK_SPEED_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "polearm_attack_speed");
    public static final ResourceLocation ENTITY_REACH_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "polearm_entity_reach");
    public static final ResourceLocation BLOCK_REACH_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "polearm_block_reach");

    public PolearmItem(Tier tier, int damage, float speed, Item.Properties props) {
        super(tier, props
                .component(DataComponents.TOOL, new Tool(
                        List.of(),
                        damage + tier.getAttackDamageBonus(),
                        (int) speed
                ))
                .attributes(buildAttributes(tier, damage, speed))
        );
    }

    public static ItemAttributeModifiers buildAttributes(Tier tier, int baseDamage, float baseSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(ATTACK_DAMAGE_ID,
                                baseDamage + tier.getAttackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(ATTACK_SPEED_ID,
                                baseSpeed,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(ENTITY_REACH_ID,
                                2.0D,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.BLOCK_INTERACTION_RANGE,
                        new AttributeModifier(BLOCK_REACH_ID,
                                2.0D,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }



    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

    public float getShieldDisableModifier(ItemStack stack, ItemStack shield) {
        return 0.5f; // Same value as vanilla axes
    }


    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (state.is(BlockTags.LOGS)) {
                breakTree(level, pos, state.getBlock(), stack, player);
            }
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }

    private void breakTree(Level level, BlockPos startPos, Block logBlock, ItemStack tool, Player player) {
        Queue<BlockPos> toCheck = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();
        toCheck.add(startPos);

        while (!toCheck.isEmpty()) {
            BlockPos current = toCheck.poll();
            if (visited.contains(current)) continue;
            visited.add(current);

            BlockState state = level.getBlockState(current);
            if (state.is(logBlock)) {
                // Destroy the block without triggering hand animations
                level.destroyBlock(current, true, player);

                player.swing(InteractionHand.MAIN_HAND);

                // Check all 26 directions (cardinal and diagonal)
                for (Direction dir : Direction.values()) {
                    BlockPos offset = current.relative(dir);
                    if (!visited.contains(offset) && level.getBlockState(offset).is(logBlock)) {
                        toCheck.add(offset);
                    }
                }

                // Check all diagonal directions manually by combining two directions
                for (Direction dir1 : Direction.Plane.HORIZONTAL) {  // Horizontal directions (N, E, S, W)
                    for (Direction dir2 : Direction.Plane.VERTICAL) { // Vertical directions (Up, Down)
                        BlockPos offset = current.relative(dir1).relative(dir2);
                        if (!visited.contains(offset) && level.getBlockState(offset).is(logBlock)) {
                            toCheck.add(offset);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility ability) {
        // Disable sweeping attack
        if (ability == ItemAbilities.SWORD_SWEEP) {
            return false;
        }

        // Allow the normal Sword abilities and your extra ones
        if    ( ability == ItemAbilities.AXE_STRIP ||
                ability == ItemAbilities.AXE_SCRAPE ||
                ability == ItemAbilities.AXE_WAX_OFF) {
            return true;
        }

        return super.canPerformAction(stack, ability);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return state.is(BlockTags.MINEABLE_WITH_AXE) ? this.getTier().getSpeed() : super.getDestroySpeed(stack, state);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = context.getItemInHand();

        Block block = state.getBlock();

        // Stripping logs
        BlockState stripped = block.getToolModifiedState(state, context, ItemAbilities.AXE_STRIP, false);
        if (stripped != null) {
            level.setBlock(pos, stripped, 11);
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        // Scraping copper
        BlockState scraped = block.getToolModifiedState(state, context, ItemAbilities.AXE_SCRAPE, false);
        if (scraped != null) {
            level.setBlock(pos, scraped, 11);
            level.levelEvent(player, 3005, pos, 0);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        // Wax removal
        BlockState unwaxed = block.getToolModifiedState(state, context, ItemAbilities.AXE_WAX_OFF, false);
        if (unwaxed != null) {
            level.setBlock(pos, unwaxed, 11);
            level.levelEvent(player, 3004, pos, 0);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        return super.useOn(context);
    }
}
