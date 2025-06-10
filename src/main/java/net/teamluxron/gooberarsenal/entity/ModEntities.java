package net.teamluxron.gooberarsenal.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.entity.custom.BeanProjectile;
import net.teamluxron.gooberarsenal.entity.custom.PeaShooterEntity;
import net.teamluxron.gooberarsenal.entity.custom.ZombifiedBreadEntity;

import java.util.function.Supplier;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, GooberArsenal.MOD_ID);

public static final Supplier<EntityType<ZombifiedBreadEntity>> ZOMBIFIED_BREAD =
        ENTITY_TYPES.register("zombified_bread", () -> EntityType.Builder.of(ZombifiedBreadEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1f) 
                .clientTrackingRange(8)
                .build("zombified_bread"));

    public static final Supplier<EntityType<PeaShooterEntity>> PEASHOOTER = ENTITY_TYPES.register(
            "pea_shooter",
            () -> EntityType.Builder.of(PeaShooterEntity::new, MobCategory.MISC)
                    .sized(0.6f, 1.0f)
                    .clientTrackingRange(8)
                    .build("pea_shooter")
    );

    public static final Supplier<EntityType<BeanProjectile>> BEAN_PROJECTILE = ENTITY_TYPES.register(
            "bean_projectile",
            () -> EntityType.Builder.<BeanProjectile>of(BeanProjectile::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("bean_projectile")
    );





    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
