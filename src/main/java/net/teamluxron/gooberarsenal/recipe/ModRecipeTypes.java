package net.teamluxron.gooberarsenal.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.teamluxron.gooberarsenal.GooberArsenal;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, GooberArsenal.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<ForgingRecipe>> FORGING =
            TYPES.register("forging", () -> new RecipeType<ForgingRecipe>() {
                @Override
                public String toString() {
                    return GooberArsenal.MOD_ID + ":forging";
                }
            });

    public static void register(IEventBus eventBus) {
        TYPES.register(eventBus);
    }
}