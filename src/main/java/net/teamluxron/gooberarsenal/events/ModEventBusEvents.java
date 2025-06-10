package net.teamluxron.gooberarsenal.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.teamluxron.gooberarsenal.GooberArsenal;
import net.teamluxron.gooberarsenal.entity.ModEntities;
import net.teamluxron.gooberarsenal.entity.client.model.PeaShooterModel;
import net.teamluxron.gooberarsenal.entity.client.model.ZombifiedBreadModel;
import net.teamluxron.gooberarsenal.entity.custom.PeaShooterEntity;
import net.teamluxron.gooberarsenal.entity.custom.ZombifiedBreadEntity;

@EventBusSubscriber(modid = GooberArsenal.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {


    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ZombifiedBreadModel.LAYER_LOCATION, ZombifiedBreadModel::createBodyLayer);
    }



    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ZOMBIFIED_BREAD.get(), ZombifiedBreadEntity.createAttributes().build());
        event.put(ModEntities.PEASHOOTER.get(), PeaShooterEntity.createAttributes().build());
    }
}
