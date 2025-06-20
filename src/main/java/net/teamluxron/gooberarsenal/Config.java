package net.teamluxron.gooberarsenal;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = GooberArsenal.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // General debug/test configs
    private static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    private static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);

    // Echo Flower Configs
    private static final ModConfigSpec.IntValue NATURAL_RADIUS = BUILDER
            .comment("Detection radius for naturally generated Echo Flowers")
            .defineInRange("echoFlower.naturalRadius", 32, 1, 128);

    private static final ModConfigSpec.IntValue PLAYER_PLACED_RADIUS = BUILDER
            .comment("Detection radius for player-placed Echo Flowers")
            .defineInRange("echoFlower.playerPlacedRadius", 64, 1, 128);

    public static final ModConfigSpec.ConfigValue<List<? extends String>> ECHO_FLOWER_MESSAGES = BUILDER
            .comment("List of default messages the Echo Flower will use when placed naturally (not by a player)")
            .defineList("echoFlower.defaultMessages", List.of(
                    "It's strangely silent.",
                    "All that gives my life validation is explaining the Echo Flower...",
                    "No one can know...",
                    "This is an Echo Flower. It repeats the last thing it heard, over and over...",
                    "Neat, huh?",
                    "I swore I saw something... Behind that rushing water...",
                    "A long time ago, monsters would whisper their wishes to the stars in the sky.",
                    "If you hoped with all your heart, your wish would come true.",
                    "Now, all we have are these sparkling stones on the ceiling...",
                    "Thousands of people wishing together can't be wrong!",
                    "The king will prove that.",
                    "C'mon, sis! Make a wish!",
                    "I wish my sister and I will see the real stars someday...",
                    "Ah... seems my horoscope is the same as last week's...",
                    "Squeak.",
                    "(You hear a passing conversation.)",
                    "So? Don't you have any wishes to make?",
                    "... hmmm, just one, but...",
                    "It's kind of stupid.",
                    "Don't say that! Come on, I promise I won't laugh",
                    "Behind you.",
                    "You aren't gonna tell my parents about this, are you?",
                    "... hmmm... if I say my wish... You promise you won't laugh at me?",
                    "Of course I won't laugh!",
                    "Someday, I'd like to climb this mountain we're all buried under.",
                    "Standing under the sky, looking at the world all around... That's my wish.",
                    "(You hear laughter.)",
                    "... hey, you said you wouldn't laugh at it!",
                    "Sorry, it's just funny...",
                    "That's my wish, too.",
                    "Where oh where could that child be...?",
                    "I've been looking all over for them...",
                    "...",
                    "Hee hee hee.",
                    "THAT'S not true.",
                    "She'll find another kid, and instantly forget about you.",
                    "You'll NEVER see her again.",
                    "Where am I...?",
                    "It's so cold here... And so dark...",
                    "Someone help me... Anyone... please... Help me...",
                    "But nobody came.",
                    "Error!",
                    "Sitting behind rushing water... It makes me feel relaxed.",
                    "I can't run any longer... Somebody, anybody..."
            ), o -> o instanceof String);


    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;

    public static int echoFlowerNaturalRadius;
    public static int echoFlowerPlayerPlacedRadius;

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();

        items = ITEM_STRINGS.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName)))
                .collect(Collectors.toSet());

        echoFlowerNaturalRadius = NATURAL_RADIUS.get();
        echoFlowerPlayerPlacedRadius = PLAYER_PLACED_RADIUS.get();
    }
}