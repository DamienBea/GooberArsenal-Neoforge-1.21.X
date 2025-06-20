package net.teamluxron.gooberarsenal.network.payload;

import net.minecraft.network.chat.Component;
import net.teamluxron.gooberarsenal.Config;
import net.teamluxron.gooberarsenal.blocks.entity.function.EchoFlowerBlockEntity;

import java.util.List;
import java.util.Random;

public class EchoFlowerMessageManager {
    private static final Random RANDOM = new Random();

    public static Component getMessage(EchoFlowerBlockEntity flower) {
        if (flower.isPlacedByPlayer()) {
            String msg = flower.getCustomMessage();
            return Component.literal(msg.isEmpty() ? "..." : msg);
        } else {
            List<? extends String> messages = Config.ECHO_FLOWER_MESSAGES.get();
            if (messages.isEmpty()) return Component.literal("...");
            return Component.literal(messages.get(RANDOM.nextInt(messages.size())));
        }
    }
}
