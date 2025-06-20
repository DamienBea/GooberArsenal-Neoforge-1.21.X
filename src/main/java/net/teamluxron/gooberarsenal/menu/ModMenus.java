package net.teamluxron.gooberarsenal.menu;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.teamluxron.gooberarsenal.GooberArsenal;

public class ModMenus {

    public static final ResourceKey<MenuType<?>> ECHO_FLOWER_MENU_KEY =
            ResourceKey.create(Registries.MENU, ResourceLocation.fromNamespaceAndPath(GooberArsenal.MOD_ID, "echo_flower"));

    public static MenuType<EchoFlowerEditMenu> ECHO_FLOWER_MENU_TYPE;

    public static void bootstrap(BootstrapContext<MenuType<?>> context) {
        MenuType<EchoFlowerEditMenu> type = new MenuType<>(EchoFlowerEditMenu::new, FeatureFlags.VANILLA_SET);
        context.register(ECHO_FLOWER_MENU_KEY, type);
        ECHO_FLOWER_MENU_TYPE = type;
    }
}