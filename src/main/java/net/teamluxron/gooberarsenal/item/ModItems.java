package net.teamluxron.gooberarsenal.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.item.custom.ScytheItem;
import net.teamluxron.gooberarsenal.item.custom.ModArmorItem;
import net.teamluxron.gooberarsenal.item.material.*;
import net.teamluxron.gooberarsenal.sound.ModSounds;

import java.util.List;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GooberArsenal.MOD_ID);

    //Ingredients
    public static final DeferredItem<Item> OBSIDIAN_HILT = ITEMS.register("obsidian_hilt",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> OBSIDIAN_HANDGUARD = ITEMS.register("obsidian_handguard",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> GOOBER_UPGRADE_TEMPLATE = ITEMS.register("goober_upgrade_template",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> CAGITE_SCRAP = ITEMS.register("cagite_scrap",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> CAGITE_INGOT = ITEMS.register("cagite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> KEVIN_SHARDS = ITEMS.register("kevin_shard",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LIFE_SAVER = ITEMS.register("life_saver",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RUBBER_CHICKEN = ITEMS.register("rubber_chicken",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PLASTIC_BAG = ITEMS.register("plastic_bag",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PLASTIC = ITEMS.register("plastic",
            () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> SWITCH_CARTRIDGE = ITEMS.register("switch_cartridge",
            () -> new Item(new Item.Properties()) {

                @Override
                public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
                    if (!level.isClientSide) {
                        level.playSound(
                                null,  // null = heard by all nearby players
                                player.getX(), player.getY(), player.getZ(),
                                ModSounds.WEEZER.get(),  // your sound event
                                SoundSource.PLAYERS,
                                1.0F,  // volume
                                1.0F   // pitch

                        );
                    }
                    return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
                }
            });


    //Foods

    public static final DeferredItem<Item>  ENERGY_BAR = ITEMS.register("energy_bar",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ENERGY_BAR).rarity(Rarity.EPIC)));

    public static final DeferredItem<Item>  SANDVICH = ITEMS.register("sandvich",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SANDVICH)));

    public static final DeferredItem<Item>  CHOCOLATE_CHIP_PANCAKES = ITEMS.register("chocolate_chip_pancakes",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHOCOLATE_CHIP_PANCAKES)));

    public static final DeferredItem<Item>  COPPER_APPLE = ITEMS.register("copper_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COPPER_APPLE).rarity(Rarity.UNCOMMON)));



    //Weapons
    public static final DeferredItem<SwordItem> FRYING_PAN = ITEMS.register("frying_pan",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 9, -3.5f))));

    public static final DeferredItem<SwordItem> STEEL_PIPE = ITEMS.register("steel_pipe",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 5, -2.6f))));

    public static final DeferredItem<SwordItem> CHAIR = ITEMS.register("chair",
            () -> new SwordItem(ChairMaterial.INSTANCE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ChairMaterial.INSTANCE, 1, -3f))));

    public static final DeferredItem<SwordItem> BEE_BUNNY_BASHER = ITEMS.register("bee_bunny_basher",
            () -> new SwordItem(CagiteMaterial.INSTANCE, new Item.Properties()
                    .fireResistant()
                    .attributes(SwordItem.createAttributes(CagiteMaterial.INSTANCE, 1, -2f))) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.bee_bunny_basher"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        target.addEffect(new MobEffectInstance(MobEffects.POISON, 40));
                        target.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40));
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.3, 0.3, direction.z * 0.3);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
                    if (!level.isClientSide() && entity instanceof Player player && selected) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 1, true, false));
                        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 0, true, false));
                    }
                    super.inventoryTick(stack, level, entity, slot, selected);
                }
            });

    public static final DeferredItem<SwordItem> STAHP_SIGN = ITEMS.register("stahp_sign",
            () -> new SwordItem(CagiteMaterial.INSTANCE, new Item.Properties()
                    .fireResistant()
                    .attributes(SwordItem.createAttributes(CagiteMaterial.INSTANCE, 1, -2.5f))) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60));
                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60));
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> WOODEN_BAT = ITEMS.register("wooden_bat",
            () -> new SwordItem(Tiers.WOOD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.WOOD, 2, -2f))) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.3, 0.3, direction.z * 0.3);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> IRON_BAT = ITEMS.register("iron_bat",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 2, -2f))) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.3, 0.3, direction.z * 0.3);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> GOLDEN_BAT = ITEMS.register("golden_bat",
            () -> new SwordItem(Tiers.GOLD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.GOLD, 2, -2f))) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.3, 0.3, direction.z * 0.3);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> DIAMOND_BAT = ITEMS.register("diamond_bat",
            () -> new SwordItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.DIAMOND, 2, -2f))) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.3, 0.3, direction.z * 0.3);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> NETHERITE_BAT = ITEMS.register("netherite_bat",
            () -> new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .fireResistant()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -2f))) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.3, 0.3, direction.z * 0.3);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword",
            () -> new SwordItem(ObsidianSwordMaterial.INSTANCE, new Item.Properties()
                    .fireResistant()
                    .attributes(SwordItem.createAttributes(ObsidianSwordMaterial.INSTANCE, 3, -3f))) {
                @Override
                public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
                    if (!level.isClientSide() && entity instanceof Player player && selected) {
                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20, 0, true, false));
                    }
                    super.inventoryTick(stack, level, entity, slot, selected);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        target.setRemainingFireTicks(5 * 20);
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<ShovelItem> SPOON = ITEMS.register("spoon",
            () -> new ShovelItem(Tiers.IRON, new Item.Properties()
                    .fireResistant()
                    .attributes(ShovelItem.createAttributes(Tiers.IRON, 5f, -3f))));

    public static final DeferredItem<SwordItem> SWITCH_BLADE = ITEMS.register("switch_blade",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()

                    .fireResistant()
                    .attributes(ShovelItem.createAttributes(Tiers.IRON, 3f, -2f)))
            {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.switch_blade"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<AxeItem> FESTIVE_AXE = ITEMS.register("festive_axe",
            () -> new AxeItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(AxeItem.createAttributes(Tiers.DIAMOND, 5f, -3f))) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.festive_axe"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> LIFE_SABER = ITEMS.register("life_saber",
            () -> new SwordItem(KevinMaterial.INSTANCE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(KevinMaterial.INSTANCE, -1, 2f))) {
                @Override
                public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
                    if (!level.isClientSide() && entity instanceof Player player && selected) {
                        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, true, false));
                        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 20, 1, true, false));
                    }
                    super.inventoryTick(stack, level, entity, slot, selected);
                }
            });

    public static final DeferredItem<SwordItem> KENDO_STICK = ITEMS.register("kendo_stick",
            () -> new SwordItem(KendoStickMaterial.INSTANCE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(KendoStickMaterial.INSTANCE, 0, -2.4f))));

    public static final DeferredItem<SwordItem> SLAPSTICK_SWORD = ITEMS.register("slapstick_sword",
            () -> new SwordItem(CagiteMaterial.INSTANCE, new Item.Properties()
                    .fireResistant()
                    .attributes(SwordItem.createAttributes(CagiteMaterial.INSTANCE, 1, -2f))) {


                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60));
                        target.level().playSound(null, target.blockPosition(), ModSounds.RUBBER_CHICKEN.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.3, 1, direction.z * 0.3);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<Item> ENDERS_SCRUTINY = ITEMS.register("enders_scrutiny",
            () -> new ScytheItem(
                    ObsidianSwordMaterial.INSTANCE,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(ObsidianSwordMaterial.INSTANCE, 1, -3f)
                                    .withModifierAdded(
                                            Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(
                                                    GooberArsenal.res("enders_scrutiny_range_bonus"),
                                                    2.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                                    .withModifierAdded(
                                            Attributes.BLOCK_INTERACTION_RANGE,
                                            new AttributeModifier(
                                                    GooberArsenal.res("enders_scrutiny_block_range_bonus"),
                                                    2.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                    )
            )
    );

    //Armor
    public static final DeferredItem<ArmorItem> CAGITE_HELMET = ITEMS.register("cagite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.CAGITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));

    public static final DeferredItem<ArmorItem> CAGITE_CHESTPLATE = ITEMS.register("cagite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.CAGITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));

    public static final DeferredItem<ArmorItem> CAGITE_LEGGINGS = ITEMS.register("cagite_leggings",
            () -> new ArmorItem(ModArmorMaterials.CAGITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));

    public static final DeferredItem<ArmorItem> CAGITE_BOOTS = ITEMS.register("cagite_boots",
            () -> new ArmorItem(ModArmorMaterials.CAGITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));



//    public static final DeferredItem<Item> ROSE_QUARTZ_SHIELD = ITEMS.register("rose_quartz_shield",
//            () -> new RoseQuartzShield(new Item.Properties().durability(500).fireResistant()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
