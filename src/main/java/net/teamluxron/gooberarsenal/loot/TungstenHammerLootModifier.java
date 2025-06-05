package net.teamluxron.gooberarsenal.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.teamluxron.gooberarsenal.item.custom.tungsten.TungstenHammerItem;

import javax.annotation.Nonnull;

public class TungstenHammerLootModifier extends LootModifier {
    public static final MapCodec<TungstenHammerLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).apply(inst, TungstenHammerLootModifier::new)
    );

    public TungstenHammerLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);

        if (tool != null && tool.getItem() instanceof TungstenHammerItem) {
            ObjectArrayList<ItemStack> modifiedLoot = new ObjectArrayList<>();

            for (ItemStack stack : generatedLoot) {
                if (!stack.isEmpty()) {
                    int bonus = 0;

                    if (stack.getCount() > 0) {
                        int chance = context.getRandom().nextInt(2); // 0 or 1
                        bonus = chance == 0 ? 0 : 1;
                    }

                    ItemStack copy = stack.copy();
                    copy.grow(bonus);
                    modifiedLoot.add(copy);
                }
            }

            return modifiedLoot;
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}