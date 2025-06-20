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
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.blocks.ModBlocks;
import net.teamluxron.gooberarsenal.entity.ModEntities;
import net.teamluxron.gooberarsenal.item.custom.armor.CagiteArmorItem;
import net.teamluxron.gooberarsenal.item.custom.armor.SoulphyreArmorItem;
import net.teamluxron.gooberarsenal.item.custom.armor.TungstenArmorItem;
import net.teamluxron.gooberarsenal.item.custom.special.*;
import net.teamluxron.gooberarsenal.item.custom.tools.GooberShield;
import net.teamluxron.gooberarsenal.item.custom.tools.HammerItem;
import net.teamluxron.gooberarsenal.item.custom.tungsten.*;
import net.teamluxron.gooberarsenal.item.custom.weapon.*;
import net.teamluxron.gooberarsenal.item.material.*;
import net.teamluxron.gooberarsenal.sound.ModSounds;

import java.util.List;

import static net.teamluxron.gooberarsenal.item.custom.weapon.PolearmItem.buildAttributes;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GooberArsenal.MOD_ID);


    //Ingredients
    public static final DeferredItem<Item> OBSIDIAN_HILT = ITEMS.register("obsidian_hilt",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> OBSIDIAN_HANDGUARD = ITEMS.register("obsidian_handguard",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> GOOBER_UPGRADE_TEMPLATE = ITEMS.register("goober_upgrade_template",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> TRANSFORMATION_TEMPLATE = ITEMS.register("transformation_template",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> SOULPHYRE = ITEMS.register("soulphyre",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> ROSE_QUARTZ = ITEMS.register("rose_quartz",
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

    public static final DeferredItem<Item> CHAIN_OF_FATE = ITEMS.register("chain_of_fate",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> VENOMOUS_FANG = ITEMS.register("venomous_fang",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ACONITE = ITEMS.register("aconite",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> POLE = ITEMS.register("pole",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AUSTRALIUM_NUGGET = ITEMS.register("australium_nugget",
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
                }});



    //Crops


    public static final DeferredItem<Item> MAGICAL_BEAN_SEED = ITEMS.register("magical_bean_seed",
            () -> new ItemNameBlockItem(ModBlocks.MAGICAL_BEAN_CROP.get(), new Item.Properties()));

    //Tools
    public static final DeferredItem<Item> SOULPHYRE_SWORD = ITEMS.registerItem("soulphyre_sword",
            (properties) -> new SwordItem(SoulphyreMaterial.INSTANCE, properties
                    .attributes(SwordItem.createAttributes(ModToolTiers.SOULPHYRE_TIER, 3, -2.4f))));

    public static final DeferredItem<Item> SOULPHYRE_PICKAXE = ITEMS.registerItem("soulphyre_pickaxe",
            (properties) -> new PickaxeItem(SoulphyreMaterial.INSTANCE,  properties
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.SOULPHYRE_TIER, 1, -2.8f))));

    public static final DeferredItem<Item> SOULPHYRE_SHOVEL = ITEMS.registerItem("soulphyre_shovel",
            (properties) -> new ShovelItem(SoulphyreMaterial.INSTANCE,  properties
                    .attributes(ShovelItem.createAttributes(ModToolTiers.SOULPHYRE_TIER, 1.5f, -3))));

    public static final DeferredItem<Item> SOULPHYRE_AXE = ITEMS.registerItem("soulphyre_axe",
            (properties) -> new AxeItem(SoulphyreMaterial.INSTANCE,  properties
                    .attributes(AxeItem.createAttributes(ModToolTiers.SOULPHYRE_TIER, 5, -3))));

    public static final DeferredItem<Item> SOULPHYRE_HOE = ITEMS.registerItem("soulphyre_hoe",
            (properties) -> new HoeItem(SoulphyreMaterial.INSTANCE,  properties
                    .attributes(HoeItem.createAttributes(ModToolTiers.SOULPHYRE_TIER, -3, 0))));


    public static final DeferredItem<Item> TUNGSTEN_SWORD = ITEMS.registerItem("tungsten_sword",
            (properties) -> new SwordItem(ModToolTiers.TUNGSTEN_TIER, properties
                    .attributes(SwordItem.createAttributes(ModToolTiers.TUNGSTEN_TIER, 2.5f, -2.4f))));

    public static final DeferredItem<Item> TUNGSTEN_PICKAXE = ITEMS.registerItem("tungsten_pickaxe",
            (properties) -> new PickaxeItem(ModToolTiers.TUNGSTEN_TIER, properties
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.TUNGSTEN_TIER, 0.5f, -1.8f))));

    public static final DeferredItem<TungstenShovelItem> TUNGSTEN_SHOVEL = ITEMS.registerItem("tungsten_shovel",
            (properties) -> new TungstenShovelItem(ModToolTiers.TUNGSTEN_TIER, properties
                    .attributes(ShovelItem.createAttributes(ModToolTiers.TUNGSTEN_TIER, 1.5F, -3.0F))));

    public static final DeferredItem<Item> TUNGSTEN_AXE = ITEMS.registerItem("tungsten_axe",
            (properties) -> new AxeItem(ModToolTiers.TUNGSTEN_TIER, properties
                    .attributes(AxeItem.createAttributes(ModToolTiers.TUNGSTEN_TIER, 4.5f, -3))));

    public static final DeferredItem<Item> TUNGSTEN_HOE = ITEMS.registerItem("tungsten_hoe",
            (properties) -> new HoeItem(ModToolTiers.TUNGSTEN_TIER, properties
                    .attributes(HoeItem.createAttributes(ModToolTiers.TUNGSTEN_TIER, -4.5f, 0))));



    public static final DeferredItem<Item> GUST_JAR = ITEMS.register("gust_jar",
            () -> new Item(new Item.Properties()));




    //SpawnEggs
    public static final DeferredItem<Item> ZOMBIFIED_BREAD_SPAWN_EGG = ITEMS.register("zombified_bread_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ZOMBIFIED_BREAD, 0xcac07d, 0xcac07d,
                    new Item.Properties()
            ));


    public static final DeferredItem<Item> PEASHOOTER_SPAWN_EGG = ITEMS.register("pea_shooter_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.PEASHOOTER, 0x94c74d, 0x4b6a1f,
                    new Item.Properties()
            ));


    //Foods

    public static final DeferredItem<Item>  ENERGY_BAR = ITEMS.register("energy_bar",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ENERGY_BAR).rarity(Rarity.EPIC)));

    public static final DeferredItem<Item>  SANDVICH = ITEMS.register("sandvich",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SANDVICH)));

    public static final DeferredItem<Item>  CHOCOLATE_CHIP_PANCAKES = ITEMS.register("chocolate_chip_pancakes",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHOCOLATE_CHIP_PANCAKES)));

    public static final DeferredItem<Item>  COPPER_APPLE = ITEMS.register("copper_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COPPER_APPLE).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item>  ROTTEN_BREAD = ITEMS.register("rotten_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROTTEN_BREAD)));






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

    public static final DeferredItem<RoseQuartzSwordItem> ROSE_QUARTZ_SWORD = ITEMS.register("rose_quartz_sword",
            () -> new RoseQuartzSwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(SwordItem.createAttributes(RoseQuartzMaterial.INSTANCE, 4, -2.4f))));

    public static final DeferredItem<SwordItem> STAHP_SIGN = ITEMS.register("stahp_sign",
            () -> new SwordItem(CagiteMaterial.INSTANCE, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(CagiteMaterial.INSTANCE, 1, -2.5f))) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60));
                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60));
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }});

    //Bats

    public static final DeferredItem<BeeBunnyBasherItem> BEE_BUNNY_BASHER = ITEMS.register("bee_bunny_basher",
            () -> new BeeBunnyBasherItem(CagiteMaterial.INSTANCE, 0, -2f, 0.5f,  0.3f,
                    new Item.Properties().fireResistant()
            ));

    public static final DeferredItem<BatItem> WOODEN_BAT = ITEMS.register("wooden_bat",
            () -> new BatItem(Tiers.WOOD, 2, -2.2f, 0.5f,  0.3f,
                    new Item.Properties()
            ));

    public static final DeferredItem<BatItem> STONE_SPIKED_BAT = ITEMS.register("stone_spiked_bat",
            () -> new BatItem(Tiers.STONE, 2, -2.2f, 0.5f,   0.3f,
                    new Item.Properties()));

    public static final DeferredItem<BatItem> IRON_BAT = ITEMS.register("iron_bat",
            () -> new BatItem(Tiers.IRON, 2, -2.2f, 0.5f,  0.3f,
                    new Item.Properties()));

    public static final DeferredItem<BatItem> GOLDEN_BAT = ITEMS.register("golden_bat",
            () -> new BatItem(Tiers.GOLD, 2, -2.2f, 0.5f, 0.3f,
                    new Item.Properties()));

    public static final DeferredItem<BatItem> DIAMOND_BAT = ITEMS.register("diamond_bat",
            () -> new BatItem(Tiers.DIAMOND, 2, 2.2f, 0.5f,  0.3f,
                    new Item.Properties()));

    public static final DeferredItem<BatItem> SOULPHYRE_BAT = ITEMS.register("soulphyre_bat",
            () -> new BatItem(SoulphyreMaterial.INSTANCE, 2, -2.2f, 0.5f,  0.3f,
                    new Item.Properties()));

    public static final DeferredItem<BatItem> NETHERITE_BAT = ITEMS.register("netherite_bat",
            () -> new BatItem(Tiers.NETHERITE, 2, -2.2f, 0.5f,  0.3f,
                    new Item.Properties().fireResistant()));

    public static final DeferredItem<TungstenBatItem> TUNGSTEN_BAT = ITEMS.register("tungsten_bat",
            () -> new TungstenBatItem(TungstenMaterial.INSTANCE, -1, -2.2f, 1.0f,  0.5f,
                    new Item.Properties()));


    public static final DeferredItem<GreatSwordItem> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword",
            () -> new GreatSwordItem(ObsidianSwordMaterial.INSTANCE, new Item.Properties()
                    .fireResistant().durability(ObsidianSwordMaterial.INSTANCE.getUses())
                    .attributes(GreatSwordItem.createAttributes(ObsidianSwordMaterial.INSTANCE, 3, -3f))) {

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


    //Spoon Registration

    public static final DeferredItem<SpoonItem> WOODEN_SPOON = ITEMS.register("wooden_spoon",
            () -> new SpoonItem(Tiers.WOOD, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.WOOD, 5f, -3f))));

    public static final DeferredItem<SpoonItem> STONE_SPOON = ITEMS.register("stone_spoon",
            () -> new SpoonItem(Tiers.STONE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.STONE, 5f, -3f))));

    public static final DeferredItem<SpoonItem> IRON_SPOON = ITEMS.register("iron_spoon",
            () -> new SpoonItem(Tiers.IRON, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.IRON, 5f, -3f))));

    public static final DeferredItem<SpoonItem> GOLDEN_SPOON = ITEMS.register("golden_spoon",
            () -> new SpoonItem(Tiers.GOLD, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.GOLD, 5f, -3f))));

    public static final DeferredItem<SpoonItem> DIAMOND_SPOON = ITEMS.register("diamond_spoon",
            () -> new SpoonItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.DIAMOND, 5f, -3f))));

    public static final DeferredItem<SpoonItem> NETHERITE_SPOON = ITEMS.register("netherite_spoon",
            () -> new SpoonItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.NETHERITE, 5f, -3f))
                    .fireResistant()));


    // Dagger Registration

    public static final DeferredItem<DaggerItem> WOODEN_DAGGER = ITEMS.register("wooden_dagger",
            () -> new DaggerItem(Tiers.WOOD, 1, -2.0f, new Item.Properties()));

    public static final DeferredItem<DaggerItem> STONE_DAGGER = ITEMS.register("stone_dagger",
            () -> new DaggerItem(Tiers.STONE, 1, -2.0f, new Item.Properties())); // Rounded 0.5->1

    public static final DeferredItem<DaggerItem> IRON_DAGGER = ITEMS.register("iron_dagger",
            () -> new DaggerItem(Tiers.IRON, -1, -1.5f, new Item.Properties()));

    public static final DeferredItem<DaggerItem> GOLDEN_DAGGER = ITEMS.register("golden_dagger",
            () -> new DaggerItem(Tiers.GOLD, 1, -2.0f, new Item.Properties()));

    public static final DeferredItem<DaggerItem> DIAMOND_DAGGER = ITEMS.register("diamond_dagger",
            () -> new DaggerItem(Tiers.DIAMOND, -1, -1.5f, new Item.Properties()));

    public static final DeferredItem<DaggerItem> SOULPHYRE_DAGGER = ITEMS.register("soulphyre_dagger",
            () -> new DaggerItem(SoulphyreMaterial.INSTANCE, -1, -1.5f, new Item.Properties()));

    public static final DeferredItem<DaggerBaseItem> TUNGSTEN_DAGGER = ITEMS.register("tungsten_dagger",
            () -> new DaggerBaseItem(TungstenMaterial.INSTANCE, -4, -1f, new Item.Properties()));

    public static final DeferredItem<DaggerItem> NETHERITE_DAGGER = ITEMS.register("netherite_dagger",
            () -> new DaggerItem(Tiers.NETHERITE, -1, -1.0f, new Item.Properties()));

    public static final DeferredItem<DaggerItem> SWITCH_BLADE = ITEMS.register("switch_blade",
            () -> new DaggerItem(Tiers.IRON, -1, -1.5f, new Item.Properties()));

    public static final DeferredItem<DaggerItem> REBELS_KNIFE = ITEMS.register("rebels_knife",
            () -> new DaggerItem(SoulphyreMaterial.INSTANCE, 0, 0.0f, new Item.Properties().fireResistant()) {
                @Override
                public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
                    if (!level.isClientSide() && entity instanceof Player player && selected) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0, true, false));
                    }
                    super.inventoryTick(stack, level, entity, slot, selected);
                }
            }
            );

    public static final DeferredItem<DaggerItem> THORN_OF_THE_DEAD_GODS = ITEMS.register("thorn_of_the_dead_gods",
            () -> new DaggerItem(Tiers.NETHERITE, 0, -1.0f, new Item.Properties().fireResistant()));

    public static final DeferredItem<DaggerItem> POISONERS_SIDEARM = ITEMS.register("poisoners_sidearm",
            () -> new DaggerItem(Tiers.NETHERITE, 0, -0.5f, new Item.Properties().fireResistant()) {
                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    boolean result = super.hurtEnemy(stack, target, attacker);
                    if (!attacker.level().isClientSide()) {
                        target.addEffect(new MobEffectInstance(MobEffects.POISON, 100));
                    }
                    return result;
                }
            });

    public static final DeferredItem<DaggerItem> LIFE_SABER = ITEMS.register("life_saber",
            () -> new DaggerItem(Tiers.DIAMOND, -1, -1.5f, new Item.Properties()) {
                @Override
                public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
                    if (!level.isClientSide() && entity instanceof Player player && selected) {
                        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, true, false));
                        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 20, 1, true, false));
                    }
                    super.inventoryTick(stack, level, entity, slot, selected);
                }
            });


//    public static final DeferredItem<SwordItem> IRON_RAPIER = ITEMS.register("iron_rapier",
//            () -> new RapierItem(Tiers.IRON, new Item.Properties()
//                    .fireResistant()
//                    .attributes(SwordItem.createAttributes(Tiers.IRON, 3f, -3f))) {
//            });


    public static final DeferredItem<AxeItem> FESTIVE_AXE = ITEMS.register("festive_axe",
            () -> new AxeItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(AxeItem.createAttributes(Tiers.DIAMOND, 5f, -3f))));


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
                            ScytheItem.createAttributes(RedEyesDreamMaterial.INSTANCE, 1, -2.6f))) {

                @Override
                public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    if (!target.level().isClientSide()) {
                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1));
                    }
                    return super.hurtEnemy(stack, target, attacker);
                }
            });

//Polearms

    public static final DeferredItem<Item> LYNNS_DESOLATION = ITEMS.register("lynns_desolation",
            () -> new LynnsDesolationItem(ObsidianSwordMaterial.INSTANCE, 4, -3f,
                    new Item.Properties().attributes(
                            buildAttributes(ObsidianSwordMaterial.INSTANCE, 4, -3f ))));

    public static final DeferredItem<Item> ACONITE_AXE = ITEMS.register("aconite_axe",
            () -> new AconiteAxeItem(ObsidianSwordMaterial.INSTANCE, 4, -3f,
                    new Item.Properties().attributes(
                            buildAttributes(ObsidianSwordMaterial.INSTANCE, 4, -3f))));

    public static final DeferredItem<Item> WOODEN_POLEARM = ITEMS.register("wooden_polearm",
            () -> new PolearmItem(Tiers.WOOD, 7, -3.4f,
                    new Item.Properties().attributes(
                            buildAttributes(Tiers.WOOD, 7, -3.4f))));

    public static final DeferredItem<Item> STONE_POLEARM = ITEMS.register("stone_polearm",
            () -> new PolearmItem(Tiers.STONE, 8, -3.4f,
                    new Item.Properties().attributes(
                            buildAttributes(Tiers.STONE, 8, -3.4f))));

    public static final DeferredItem<Item> IRON_POLEARM = ITEMS.register("iron_polearm",
            () -> new PolearmItem(Tiers.IRON, 7, -3.3f,
                    new Item.Properties().attributes(
                            buildAttributes(Tiers.IRON, 7, -3.3f))));

    public static final DeferredItem<Item> GOLDEN_POLEARM = ITEMS.register("golden_polearm",
            () -> new PolearmItem(Tiers.GOLD, 7, -3.2f,
                    new Item.Properties().attributes(
                            buildAttributes(Tiers.GOLD, 7, -3.2f))));

    public static final DeferredItem<Item> DIAMOND_POLEARM = ITEMS.register("diamond_polearm",
            () -> new PolearmItem(Tiers.DIAMOND, 6, -3.2f,
                    new Item.Properties().attributes(
                            buildAttributes(Tiers.DIAMOND, 6, -3.2f))));

    public static final DeferredItem<Item> SOULPHYRE_POLEARM = ITEMS.register("soulphyre_polearm",
            () -> new PolearmItem(SoulphyreMaterial.INSTANCE, 6, -3.2f,
                    new Item.Properties().attributes(
                            buildAttributes(SoulphyreMaterial.INSTANCE, 6, -3.2f))));

    public static final DeferredItem<Item> NETHERITE_POLEARM = ITEMS.register("netherite_polearm",
            () -> new PolearmItem(Tiers.NETHERITE, 6, -3.2f,
                    new Item.Properties().attributes(
                            buildAttributes(Tiers.NETHERITE, 6, -3.2f))));

    public static final DeferredItem<Item> TUNGSTEN_POLEARM = ITEMS.register("tungsten_polearm",
            () -> new TungstenPolearmItem(TungstenMaterial.INSTANCE, 3, -3.2f,
                    new Item.Properties().attributes(
                            buildAttributes(TungstenMaterial.INSTANCE, 3, -3.2f))));


    //Scythes

    public static final DeferredItem<ScytheItem> WOODEN_SCYTHE = ITEMS.register("wooden_scythe",
            () -> new ScytheItem(Tiers.WOOD, new Item.Properties().attributes(
                    ScytheItem.createAttributes(Tiers.WOOD, -4, -3f))));

    public static final DeferredItem<ScytheItem> STONE_SCYTHE = ITEMS.register("stone_scythe",
            () -> new ScytheItem(Tiers.STONE, new Item.Properties().attributes(
                    ScytheItem.createAttributes(Tiers.STONE, -2, -3f))));

    public static final DeferredItem<ScytheItem> IRON_SCYTHE = ITEMS.register("iron_scythe",
            () -> new ScytheItem(Tiers.IRON, new Item.Properties().attributes(
                    ScytheItem.createAttributes(Tiers.IRON, -2, -2.9f))));

    public static final DeferredItem<ScytheItem> GOLDEN_SCYTHE = ITEMS.register("golden_scythe",
            () -> new ScytheItem(Tiers.GOLD, new Item.Properties().attributes(
                    ScytheItem.createAttributes(Tiers.GOLD, -4, -2.8f))));

    public static final DeferredItem<ScytheItem> DIAMOND_SCYTHE = ITEMS.register("diamond_scythe",
            () -> new ScytheItem(Tiers.DIAMOND, new Item.Properties().attributes(
                    ScytheItem.createAttributes(Tiers.DIAMOND, -2, -2.8f))));

    public static final DeferredItem<ScytheItem> SOULPHYRE_SCYTHE = ITEMS.register("soulphyre_scythe",
            () -> new ScytheItem(SoulphyreMaterial.INSTANCE, new Item.Properties().attributes(
                    ScytheItem.createAttributes(SoulphyreMaterial.INSTANCE, -2, -2.8f))));

    public static final DeferredItem<ScytheItem> NETHERITE_SCYTHE = ITEMS.register("netherite_scythe",
            () -> new ScytheItem(Tiers.NETHERITE, new Item.Properties().fireResistant().attributes(
                    ScytheItem.createAttributes(Tiers.NETHERITE, -1, -2.8f))));

    public static final DeferredItem<ScytheItem> TUNGSTEN_SCYTHE = ITEMS.register("tungsten_scythe",
            () -> new ScytheItem(TungstenMaterial.INSTANCE, new Item.Properties().attributes(
                            ScytheItem.createAttributes(TungstenMaterial.INSTANCE, 0, -2.8f)
                                    .withModifierAdded(
                                            Attributes.ENTITY_INTERACTION_RANGE,
                                            new AttributeModifier(
                                                    GooberArsenal.res("tungsten_scythe_range_bonus"),
                                                    1.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    )
                                    .withModifierAdded(
                                            Attributes.BLOCK_INTERACTION_RANGE,
                                            new AttributeModifier(
                                                    GooberArsenal.res("tungsten_scythe_block_range_bonus"),
                                                    1.0D,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.MAINHAND
                                    ))));

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

    public static final DeferredItem<HammerItem> SOULPHYRE_HAMMER = ITEMS.register("soulphyre_hammer",
            () -> new HammerItem(SoulphyreMaterial.INSTANCE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(SoulphyreMaterial.INSTANCE, 3F, -3.5f))));


    public static final DeferredItem<HammerItem> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.NETHERITE, 3F, -3.5f))));

    public static final DeferredItem<TungstenHammerItem> TUNGSTEN_HAMMER = ITEMS.register("tungsten_hammer",
            () -> new TungstenHammerItem(TungstenMaterial.INSTANCE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(TungstenMaterial.INSTANCE, 0, -3.5f))));

    public static final DeferredItem<MossyMasherItem> MOSSY_MASHER = ITEMS.register("mossy_masher",
            () -> new MossyMasherItem(MossMaterial.INSTANCE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(MossMaterial.INSTANCE, 3F, -3.5f))));



    //Great Swords

    public static final DeferredItem<GreatSwordItem> WOODEN_GREATSWORD = ITEMS.register("wooden_greatsword",
            () -> new GreatSwordItem(Tiers.WOOD,
                    new Item.Properties().attributes(buildAttributes(Tiers.WOOD, 8, -3.5f))));

    public static final DeferredItem<GreatSwordItem> STONE_GREATSWORD = ITEMS.register("stone_greatsword",
            () -> new GreatSwordItem(Tiers.STONE,
                    new Item.Properties().attributes(buildAttributes(Tiers.STONE, 8, -3.5f))));

    public static final DeferredItem<GreatSwordItem> IRON_GREATSWORD = ITEMS.register("iron_greatsword",
            () -> new GreatSwordItem(Tiers.IRON,
                    new Item.Properties().attributes(buildAttributes(Tiers.IRON, 8, -3.5f))));

    public static final DeferredItem<GreatSwordItem> GOLDEN_GREATSWORD = ITEMS.register("golden_greatsword",
            () -> new GreatSwordItem(Tiers.GOLD,
                    new Item.Properties().attributes(buildAttributes(Tiers.GOLD, 8, -3.5f))));

    public static final DeferredItem<GreatSwordItem> DIAMOND_GREATSWORD = ITEMS.register("diamond_greatsword",
            () -> new GreatSwordItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(buildAttributes(Tiers.DIAMOND, 8, -3.5f))));

    public static final DeferredItem<GreatSwordItem> SOULPHYRE_GREATSWORD = ITEMS.register("soulphyre_greatsword",
            () -> new GreatSwordItem(SoulphyreMaterial.INSTANCE,
                    new Item.Properties().attributes(buildAttributes(SoulphyreMaterial.INSTANCE, 8, -3.5f))));

    public static final DeferredItem<GreatSwordItem> NETHERITE_GREATSWORD = ITEMS.register("netherite_greatsword",
            () -> new GreatSwordItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(buildAttributes(Tiers.NETHERITE, 8, -3.5f))));

    public static final DeferredItem<TungstenGreatSwordItem> TUNGSTEN_GREATSWORD = ITEMS.register("tungsten_greatsword",
            () -> new TungstenGreatSwordItem(TungstenMaterial.INSTANCE,
                    new Item.Properties().attributes(buildAttributes(TungstenMaterial.INSTANCE, 8, -3.5f))));


    //Great Axes

    public static final DeferredItem<GreatAxeItem> WOODEN_GREATAXE = ITEMS.register("wooden_greataxe",
            () -> new GreatAxeItem(Tiers.WOOD, 8, -3.5f,
                    new Item.Properties().attributes(buildAttributes(Tiers.WOOD, 7, -3.4f))));

    public static final DeferredItem<GreatAxeItem> STONE_GREATAXE = ITEMS.register("stone_greataxe",
            () -> new GreatAxeItem(Tiers.STONE, 8, -3.5f,
                    new Item.Properties().attributes(buildAttributes(Tiers.STONE, 8, -3.4f))));

    public static final DeferredItem<GreatAxeItem> IRON_GREATAXE = ITEMS.register("iron_greataxe",
            () -> new GreatAxeItem(Tiers.IRON, 8, -3.4f,
                    new Item.Properties().attributes(buildAttributes(Tiers.IRON, 7, -3.3f))));

    public static final DeferredItem<GreatAxeItem> GOLDEN_GREATAXE = ITEMS.register("golden_greataxe",
            () -> new GreatAxeItem(Tiers.GOLD, 8, -3.3f,
                    new Item.Properties().attributes(buildAttributes(Tiers.GOLD, 7, -3.2f))));

    public static final DeferredItem<GreatAxeItem> DIAMOND_GREATAXE = ITEMS.register("diamond_greataxe",
            () -> new GreatAxeItem(Tiers.DIAMOND, 7, -3.3f,
                    new Item.Properties().attributes(buildAttributes(Tiers.DIAMOND, 6, -3.2f))));

    public static final DeferredItem<GreatAxeItem> SOULPHYRE_GREATAXE = ITEMS.register("soulphyre_greataxe",
            () -> new GreatAxeItem(SoulphyreMaterial.INSTANCE, 7, -3.3f,
                    new Item.Properties().attributes(buildAttributes(SoulphyreMaterial.INSTANCE, 6, -3.2f))));

    public static final DeferredItem<GreatAxeItem> NETHERITE_GREATAXE = ITEMS.register("netherite_greataxe",
            () -> new GreatAxeItem(Tiers.NETHERITE, 7, -3.3f,
                    new Item.Properties().attributes(buildAttributes(Tiers.NETHERITE, 6, -3.2f))));

    public static final DeferredItem<TungstenGreatAxeItem> TUNGSTEN_GREATAXE = ITEMS.register("tungsten_greataxe",
            () -> new TungstenGreatAxeItem(TungstenMaterial.INSTANCE, 4, -3.3f,
                    new Item.Properties().attributes(buildAttributes(TungstenMaterial.INSTANCE, 3, -3.2f))));



    //Armor
    public static final DeferredItem<ArmorItem> SOULPHYRE_HELMET = ITEMS.register("soulphyre_helmet",
            () -> new SoulphyreArmorItem(ModArmorMaterials.SOULPHYRE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1)
                            .durability(ArmorItem.Type.HELMET.getDurability(33))));

    public static final DeferredItem<ArmorItem> SOULPHYRE_CHESTPLATE = ITEMS.register("soulphyre_chestplate",
            () -> new SoulphyreArmorItem(ModArmorMaterials.SOULPHYRE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)
                            .durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));

    public static final DeferredItem<ArmorItem> SOULPHYRE_LEGGINGS = ITEMS.register("soulphyre_leggings",
            () -> new SoulphyreArmorItem(ModArmorMaterials.SOULPHYRE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)
                            .durability(ArmorItem.Type.LEGGINGS.getDurability(33))));

    public static final DeferredItem<ArmorItem> SOULPHYRE_BOOTS = ITEMS.register("soulphyre_boots",
            () -> new SoulphyreArmorItem(ModArmorMaterials.SOULPHYRE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1)
                            .durability(ArmorItem.Type.BOOTS.getDurability(33))));

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
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))) {

                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.gooberarsenal.stevens_jacket").withStyle(ChatFormatting.LIGHT_PURPLE));
                    super.appendHoverText(stack, context, tooltip, flag);
                }});


    public static final DeferredItem<ArmorItem> TUNGSTEN_HELMET = ITEMS.register("tungsten_helmet",
            () -> new TungstenArmorItem(ModArmorMaterials.TUNGSTEN_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1)));

    public static final DeferredItem<ArmorItem> TUNGSTEN_CHESTPLATE = ITEMS.register("tungsten_chestplate",
            () -> new TungstenArmorItem(ModArmorMaterials.TUNGSTEN_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final DeferredItem<ArmorItem> TUNGSTEN_LEGGINGS = ITEMS.register("tungsten_leggings",
            () -> new TungstenArmorItem(ModArmorMaterials.TUNGSTEN_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final DeferredItem<ArmorItem> TUNGSTEN_BOOTS = ITEMS.register("tungsten_boots",
            () -> new TungstenArmorItem(ModArmorMaterials.TUNGSTEN_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1)));

    //Shields


    public static final DeferredItem<RoseQuartzShield> ROSE_QUARTZ_SHIELD = ITEMS.register("rose_quartz_shield",
            () -> new RoseQuartzShield(new Item.Properties()
                    .durability(1650).stacksTo(1)));

    public static final DeferredItem<GooberShield> ALLOY_SHIELD = ITEMS.register("alloy_shield",
            () -> new GooberShield(new Item.Properties()
                    .durability(650).stacksTo(1)));

    public static final DeferredItem<GooberShield> DOOR_SHIELD = ITEMS.register("door_shield",
            () -> new GooberShield(new Item.Properties()
                    .durability(650).stacksTo(1)));

    public static final DeferredItem<GooberShield> GRASS_CREST_SHIELD = ITEMS.register("grass_crest_shield",
            () -> new GooberShield(new Item.Properties()
                    .durability(650).stacksTo(1)));

//    public static final DeferredItem<GooberShield> KNIGHTS_SHIELD = ITEMS.register("knights_shield",
//            () -> new GooberShield(new Item.Properties()
//                    .durability(650)
//                    .stacksTo(1)
//            ));

    public static final DeferredItem<GooberShield> PALETTE = ITEMS.register("palette",
            () -> new GooberShield(new Item.Properties()
                    .durability(650).stacksTo(1)));

//    public static final DeferredItem<GooberShield> TRASH_CAN = ITEMS.register("trash_can",
//            () -> new GooberShield(new Item.Properties()
//                    .durability(650)
//                    .stacksTo(1)
//            ));

    //ItemsWithModels

//    public static final DeferredItem<Item> FORGING_ANVIL = ITEMS.register("forging_anvil",
//            () -> new BlockItem(ModBlocks.FORGING_ANVIL.get(), new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
