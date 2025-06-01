package net.teamluxron.gooberarsenal.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.blocks.entity.renderer.FieldSwordRenderer;

public class ClientInit {
    public static void onClientSetup(FMLClientSetupEvent event) {
        GooberArsenalClient.onClientSetup(event);
    }


    private static void onRegisterClientPayloads(RegisterPayloadHandlersEvent event) {
        net.teamluxron.gooberarsenal.client.GooberArsenalClient.registerClientReceivers(event);
    }

}
