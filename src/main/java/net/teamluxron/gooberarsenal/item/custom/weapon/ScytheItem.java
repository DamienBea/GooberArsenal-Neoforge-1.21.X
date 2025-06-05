package net.teamluxron.gooberarsenal.item.custom.weapon;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;


public class ScytheItem extends SwordItem {
    public ScytheItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public static final ResourceLocation ATTACK_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "scythe_attack_damage");
    public static final ResourceLocation ATTACK_SPEED_ID = ResourceLocation.fromNamespaceAndPath("gooberarsenal", "scythe_attack_speed");


    public static ItemAttributeModifiers createAttributes(Tier tier, int baseDamage, float baseSpeed) {
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
                        Attributes.SWEEPING_DAMAGE_RATIO,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath("gooberarsenal", "scythe_sweeping_damage"),
                                1.0D,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    public ScytheItem(Tier tier, int baseDamage, float baseSpeed, Properties properties) {
        super(tier, properties.attributes(createAttributes(tier, baseDamage, baseSpeed)));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockState state = level.getBlockState(pos);

        if (!level.isClientSide && player != null && state.is(BlockTags.CROPS) && state.getBlock() instanceof CropBlock crop) {
            harvestCrops(level, pos, player, stack);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    private void harvestCrops(Level level, BlockPos centerPos, Player player, ItemStack stack) {
        BlockPos.betweenClosedStream(centerPos.offset(-2, 0, -2), centerPos.offset(2, 0, 2)).forEach(pos -> {
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof CropBlock crop && state.getValue(CropBlock.AGE) == crop.getMaxAge()) {
                state.getBlock().playerDestroy(level, player, pos, state, level.getBlockEntity(pos), stack);
                BlockState replanted = crop.defaultBlockState().setValue(CropBlock.AGE, 0);
                level.setBlock(pos, replanted, 3);
            }
        });
    }

//    @Override
//    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
//        tooltip.add(Component.translatable("tooltip.gooberarsenal.scythes"));
//        super.appendHoverText(stack, context, tooltip, flag);
//    }
}