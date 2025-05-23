package net.teamluxron.gooberarsenal.item;

import net.minecraft.ChatFormatting;
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
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.item.custom.*;
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

    public static final DeferredItem<Item> DRAGON_SCALE_SHARD = ITEMS.register("dragon_scale_shard",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> DRAGON_SCALED_TUNGSTEN = ITEMS.register("dragon_scaled_tungsten",
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

    public static final DeferredItem<Item> OBSIDIAN_ROSE = ITEMS.register("obsidian_rose",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLEAMING_RED_EYE = ITEMS.register("gleaming_red_eye",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> THORN_OF_ZAZIKEL = ITEMS.register("thorn_of_zazikel",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> THORN_OF_TOTH = ITEMS.register("thorn_of_toth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> THORN_OF_ANDORAL = ITEMS.register("thorn_of_andoral",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MOSSY_GEM = ITEMS.register("mossy_gem",
            () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> SWITCH_CARTRIDGE = ITEMS.register("switch_cartridge",
            () -> new Item(new Item.Properties()) {

                @Override
                public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
                    if (!level.isClientSide) {
                        level.playSound(
                                null,
                                player.getX(), player.getY(), player.getZ(),
                                ModSounds.WEEZER.get(),
                                SoundSource.PLAYERS,
                                1.0F,
                                1.0F

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
                    .attributes(SwordItem.createAttributes(CagiteMaterial.INSTANCE, 0, -2f))) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.bats"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        target.addEffect(new MobEffectInstance(MobEffects.POISON, 40));
                        target.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40));
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.5, 0.3, direction.z * 0.5);
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
                    .attributes(SwordItem.createAttributes(Tiers.WOOD, 2, -2.2f))) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.bats"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.5, 0.3, direction.z * 0.5);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> STONE_SPIKED_BAT = ITEMS.register("stone_spiked_bat",
            () -> new SwordItem(Tiers.STONE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.STONE, 2, -2.2f))) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.bats"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.5, 0.3, direction.z * 0.5);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> IRON_BAT = ITEMS.register("iron_bat",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 2, -2.2f))) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.bats"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.5, 0.3, direction.z * 0.5);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> GOLDEN_BAT = ITEMS.register("golden_bat",
            () -> new SwordItem(Tiers.GOLD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.GOLD, 2, -2.2f))) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.bats"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.5, 0.3, direction.z * 0.5);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> DIAMOND_BAT = ITEMS.register("diamond_bat",
            () -> new SwordItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.DIAMOND, 2, -2.2f))) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.bats"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.5, 0.3, direction.z * 0.5);
                        target.hurtMarked = true;
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<SwordItem> NETHERITE_BAT = ITEMS.register("netherite_bat",
            () -> new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .fireResistant()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -2.2f))) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.bats"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        Vec3 direction = target.position().subtract(attacker.position()).normalize();
                        target.setDeltaMovement(direction.x * 0.5, 0.3, direction.z * 0.5);
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

    public static final DeferredItem<SwordItem> WOODEN_DAGGER = ITEMS.register("wooden_dagger",
            () -> new SwordItem(Tiers.WOOD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.WOOD, 1f, -2f)))
            {

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> STONE_DAGGER = ITEMS.register("stone_dagger",
            () -> new SwordItem(Tiers.STONE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.STONE, 0.5f, -2f)))
            {

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> IRON_DAGGER = ITEMS.register("iron_dagger",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, -0.5f, -1.5f)))
            {

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> GOLDEN_DAGGER = ITEMS.register("golden_dagger",
            () -> new SwordItem(Tiers.GOLD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.GOLD, 1f, -2f)))
            {

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> DIAMOND_DAGGER = ITEMS.register("diamond_dagger",
            () -> new SwordItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.DIAMOND, -1f, -1.5f)))
            {

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> NETHERITE_DAGGER = ITEMS.register("netherite_dagger",
            () -> new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, -1f, -1f)))
            {

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> SWITCH_BLADE = ITEMS.register("switch_blade",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, -0.5f, -1.5f)))
            {

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> REBELS_KNIFE = ITEMS.register("rebels_knife",
            () -> new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .fireResistant()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 0f, 0f)))
            {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> THORN_OF_THE_DEAD_GODS = ITEMS.register("thorn_of_the_dead_gods",
            () -> new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .fireResistant()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 0f, -1f)))
            {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> POISONERS_SIDEARM = ITEMS.register("poisoners_sidearm",
            () -> new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .fireResistant()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 0f, -0.5f)))
            {


                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!attacker.level().isClientSide()) {
                        if (target.invulnerableTime > 5) {
                            target.invulnerableTime = 5;
                        }
                        target.addEffect(new MobEffectInstance(MobEffects.POISON, 100));
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.daggers"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }


            });


//    public static final DeferredItem<SwordItem> IRON_RAPIER = ITEMS.register("iron_rapier",
//            () -> new RapierItem(Tiers.IRON, new Item.Properties()
//                    .fireResistant()
//                    .attributes(SwordItem.createAttributes(Tiers.IRON, 3f, -3f))) {
//            });


    public static final DeferredItem<AxeItem> FESTIVE_AXE = ITEMS.register("festive_axe",
            () -> new AxeItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(AxeItem.createAttributes(Tiers.DIAMOND, 5f, -3f))) {
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

    public static final DeferredItem<Item> RED_EYES_DREAM = ITEMS.register("red_eyes_dream",
            () -> new ScytheItem(RedEyesDreamMaterial.INSTANCE, new Item.Properties()
                    .attributes(
                            ScytheItem.createAttributes(RedEyesDreamMaterial.INSTANCE, 4, -3f)
                                    .withModifierAdded(
                                            Attributes.SWEEPING_DAMAGE_RATIO,
                                            new AttributeModifier(
                                                    GooberArsenal.res("red_eyes_dream_sweeping_damage"),
                                                    1.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                    )) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.scythes"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1));
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });



    public static final DeferredItem<Item> LYNNS_DESOLATION = ITEMS.register("lynns_desolation",
            () -> new PolearmItem(
                    ObsidianSwordMaterial.INSTANCE,
                    new Item.Properties().attributes(
                            PolearmItem.createAttributes(ObsidianSwordMaterial.INSTANCE, 1, -2.6f)
                                    .withModifierAdded(
                                            Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(
                                                    GooberArsenal.res("polearm_range_bonus"),
                                                    2.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                                    .withModifierAdded(
                                            Attributes.BLOCK_INTERACTION_RANGE,
                                            new AttributeModifier(
                                                    GooberArsenal.res("polearm_block_range_bonus"),
                                                    2.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                    )) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.polearms"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40));
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

    public static final DeferredItem<Item> WOODEN_POLEARM = ITEMS.register("wooden_polearm",
            () -> new PolearmItem(
                    Tiers.WOOD,
                    new Item.Properties()
                            .attributes(
                                    PolearmItem.createAttributes(Tiers.WOOD, 4, -3f)
                                            .withModifierAdded(
                                                    Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("wooden_polearm_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                                            .withModifierAdded(
                                                    Attributes.BLOCK_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("wooden_polearm_block_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )

                            )) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.polearms"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> STONE_POLEARM = ITEMS.register("stone_polearm",
            () -> new PolearmItem(
                    Tiers.STONE,
                    new Item.Properties()
                            .attributes(
                                    PolearmItem.createAttributes(Tiers.STONE, 5, -3f)
                                            .withModifierAdded(
                                                    Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("stone_polearm_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                                            .withModifierAdded(
                                                    Attributes.BLOCK_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("stone_polearm_block_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                            )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.polearms"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> IRON_POLEARM = ITEMS.register("iron_polearm",
            () -> new PolearmItem(
                    Tiers.IRON,
                    new Item.Properties()
                            .attributes(
                                    PolearmItem.createAttributes(Tiers.IRON, 4, -2.9f)
                                            .withModifierAdded(
                                                    Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("iron_polearm_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                                            .withModifierAdded(
                                                    Attributes.BLOCK_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("iron_polearm_block_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                            )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.polearms"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> GOLDEN_POLEARM = ITEMS.register("golden_polearm",
            () -> new PolearmItem(
                    Tiers.GOLD,
                    new Item.Properties()
                            .attributes(
                                    PolearmItem.createAttributes(Tiers.GOLD, 4, -2.8f)
                                            .withModifierAdded(
                                                    Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("golden_polearm_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                                            .withModifierAdded(
                                                    Attributes.BLOCK_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("golden_polearm_block_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                            )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.polearms"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> DIAMOND_POLEARM = ITEMS.register("diamond_polearm",
            () -> new PolearmItem(
                    Tiers.DIAMOND,
                    new Item.Properties()
                            .attributes(
                                    PolearmItem.createAttributes(Tiers.DIAMOND, 3, -2.8f)
                                            .withModifierAdded(
                                                    Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("diamond_polearm_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                                            .withModifierAdded(
                                                    Attributes.BLOCK_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("diamond_polearm_block_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                            )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.polearms"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> NETHERITE_POLEARM = ITEMS.register("netherite_polearm",
            () -> new PolearmItem(
                    Tiers.NETHERITE,
                    new Item.Properties()
                            .attributes(
                                    PolearmItem.createAttributes(Tiers.NETHERITE, 3, -2.8f)
                                            .withModifierAdded(
                                                    Attributes.ENTITY_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("netherite_polearm_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                                            .withModifierAdded(
                                                    Attributes.BLOCK_INTERACTION_RANGE,
                                                    new AttributeModifier(
                                                            GooberArsenal.res("netherite_polearm_block_range_bonus"),
                                                            2.0D,
                                                            AttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    EquipmentSlotGroup.MAINHAND
                                            )
                            )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.polearms"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });



    public static final DeferredItem<ScytheItem> WOODEN_SCYTHE = ITEMS.register("wooden_scythe",
            () -> new ScytheItem(Tiers.WOOD, new Item.Properties()
                    .attributes(
                            ScytheItem.createAttributes(RedEyesDreamMaterial.INSTANCE, -1, -3.4f)
                                    .withModifierAdded(
                                            Attributes.SWEEPING_DAMAGE_RATIO,
                                            new AttributeModifier(
                                                    GooberArsenal.res("wooden_scythe_sweeping_damage"),
                                                    1.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                    )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.scythes"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ScytheItem> STONE_SCYTHE = ITEMS.register("stone_scythe",
            () -> new ScytheItem(Tiers.STONE, new Item.Properties()
                    .attributes(
                            ScytheItem.createAttributes(RedEyesDreamMaterial.INSTANCE, 1, -3.4f)
                                    .withModifierAdded(
                                            Attributes.SWEEPING_DAMAGE_RATIO,
                                            new AttributeModifier(
                                                    GooberArsenal.res("stone_scythe_sweeping_damage"),
                                                    1.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                    )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.scythes"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ScytheItem> IRON_SCYTHE = ITEMS.register("iron_scythe",
            () -> new ScytheItem(Tiers.IRON, new Item.Properties()
                    .attributes(
                            ScytheItem.createAttributes(RedEyesDreamMaterial.INSTANCE, 1, -3.3f)
                                    .withModifierAdded(
                                            Attributes.SWEEPING_DAMAGE_RATIO,
                                            new AttributeModifier(
                                                    GooberArsenal.res("iron_scythe_sweeping_damage"),
                                                    1.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                    )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.scythes"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ScytheItem> GOLDEN_SCYTHE = ITEMS.register("golden_scythe",
            () -> new ScytheItem(Tiers.GOLD, new Item.Properties()
                    .attributes(
                            ScytheItem.createAttributes(RedEyesDreamMaterial.INSTANCE, -1, -3.2f)
                                    .withModifierAdded(
                                            Attributes.SWEEPING_DAMAGE_RATIO,
                                            new AttributeModifier(
                                                    GooberArsenal.res("gold_scythe_sweeping_damage"),
                                                    1.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                    )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.scythes"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ScytheItem> DIAMOND_SCYTHE = ITEMS.register("diamond_scythe",
            () -> new ScytheItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(
                            ScytheItem.createAttributes(RedEyesDreamMaterial.INSTANCE, 1, -3.2f)
                                    .withModifierAdded(
                                            Attributes.SWEEPING_DAMAGE_RATIO,
                                            new AttributeModifier(
                                                    GooberArsenal.res("diamond_scythe_sweeping_damage"),
                                                    1.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                    )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.scythes"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ScytheItem> NETHERITE_SCYTHE = ITEMS.register("netherite_scythe",
            () -> new ScytheItem(Tiers.NETHERITE, new Item.Properties()
                    .fireResistant()
                    .attributes(
                            ScytheItem.createAttributes(RedEyesDreamMaterial.INSTANCE, 2, -3.2f)
                                    .withModifierAdded(
                                            Attributes.SWEEPING_DAMAGE_RATIO,
                                            new AttributeModifier(
                                                    GooberArsenal.res("netherite_scythe_sweeping_damage"),
                                                    1.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                    )){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.gooberarsenal.scythes"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });


    //Hammers


    public static final DeferredItem<HammerItem> WOODEN_HAMMER = ITEMS.register("wooden_hammer",
            () -> new HammerItem(Tiers.WOOD, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.WOOD, 3F, -3.5f))));


    public static final DeferredItem<HammerItem> STONE_HAMMER = ITEMS.register("stone_hammer",
            () -> new HammerItem(Tiers.STONE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.STONE, 3F, -3.5f))));


    public static final DeferredItem<HammerItem> IRON_HAMMER = ITEMS.register("iron_hammer",
            () -> new HammerItem(Tiers.IRON, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.IRON, 3F, -3.5f))));


    public static final DeferredItem<HammerItem> GOLDEN_HAMMER = ITEMS.register("golden_hammer",
            () -> new HammerItem(Tiers.GOLD, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.GOLD, 3F, -3.5f))));


    public static final DeferredItem<HammerItem> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
            () -> new HammerItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.DIAMOND, 3F, -3.5f))));


    public static final DeferredItem<HammerItem> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.NETHERITE, 3F, -3.5f))));

    public static final DeferredItem<HammerItem> MOSSY_MASHER = ITEMS.register("mossy_masher",
            () -> new HammerItem(MossMaterial.INSTANCE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(MossMaterial.INSTANCE, 3F, -3.5f))));




    //Armor
    public static final DeferredItem<ArmorItem> CAGITE_HELMET = ITEMS.register("cagite_helmet",
            () -> new CagiteArmorItem(ModArmorMaterials.CAGITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
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


    public static final DeferredItem<ArmorItem> STEVENS_JACKET = ITEMS.register("stevens_jacket",
            () -> new StevensJacketItem(ModArmorMaterials.STEVENS, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant()
            ) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.gooberarsenal.stevens_jacket").withStyle(ChatFormatting.LIGHT_PURPLE));
                    super.appendHoverText(stack, context, tooltip, flag);
                }
            }
    );

    //ItemsWithModels

//    public static final DeferredItem<Item> FORGING_ANVIL = ITEMS.register("forging_anvil",
//            () -> new BlockItem(ModBlocks.FORGING_ANVIL.get(), new Item.Properties()));


//    public static final DeferredItem<Item> ROSE_QUARTZ_SHIELD = ITEMS.register("rose_quartz_shield",
//            () -> new RoseQuartzShield(new Item.Properties().durability(500).fireResistant()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
