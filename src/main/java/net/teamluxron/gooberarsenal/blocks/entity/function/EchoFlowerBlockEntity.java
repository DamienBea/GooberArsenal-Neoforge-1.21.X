package net.teamluxron.gooberarsenal.blocks.entity.function;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.network.payload.EchoFlowerMessageManager;

import java.util.*;

public class EchoFlowerBlockEntity extends BlockEntity {

    private static final int DEFAULT_RADIUS_NATURAL = 32;
    private static final int DEFAULT_RADIUS_PLAYER_PLACED = 64;
    private static final long COOLDOWN_TICKS = 100; // ~5 seconds

    private DyeColor color = DyeColor.CYAN;
    private boolean playerPlaced = false;
    private String customMessage = "";

    private final Map<UUID, Long> lastTriggerTime = new HashMap<>();

    public EchoFlowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ECHO_FLOWER_BE.get(), pos, state);
    }

    public DyeColor getColor() {
        return color != null ? color : DyeColor.CYAN; // default cyan if null
    }

    public void setColor(DyeColor color) {
        this.color = color;
        setChanged();  // mark block entity dirty so it saves
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public boolean hasColor() {
        return color != null;
    }

    private boolean placedByPlayer = false;

    public void setPlacedByPlayer(boolean value) {
        this.placedByPlayer = value;
        setChanged();
    }

    public boolean isPlacedByPlayer() {
        return placedByPlayer;
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> serverTicker(BlockEntityType<T> type) {
        return (level, pos, state, be) -> {
            if (be instanceof EchoFlowerBlockEntity flower) {
                flower.tickServer(level);
            }
        };
    }

    public void tickServer(Level level) {
        int radius = playerPlaced ? DEFAULT_RADIUS_PLAYER_PLACED : DEFAULT_RADIUS_NATURAL;
        long gameTime = level.getGameTime();

        for (Player player : level.getEntitiesOfClass(Player.class,
                new AABB(worldPosition).inflate(radius))) {

            UUID uuid = player.getUUID();
            long lastTime = lastTriggerTime.getOrDefault(uuid, -COOLDOWN_TICKS);

            if (gameTime - lastTime >= COOLDOWN_TICKS) {
                lastTriggerTime.put(uuid, gameTime);
                Component message = EchoFlowerMessageManager.getMessage(this);

                // Use your network system to send message to player
                if (player instanceof ServerPlayer serverPlayer) {
                    // Implement your message display logic here
                    // For example: serverPlayer.displayClientMessage(message, true);
                }
            }
        }
    }


    public void setCustomMessage(String msg) {
        this.customMessage = msg.length() > 32 ? msg.substring(0, 32) : msg;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putString("Message", customMessage);
        tag.putString("Color", this.color.getName());
        tag.putBoolean("PlacedByPlayer", placedByPlayer);
    }

    @Override
    public void onLoad() {
        if (this.level != null && this.level.isClientSide) {
            this.requestModelDataUpdate();
        }
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.customMessage = tag.getString("Message");
        this.color = DyeColor.byName(tag.getString("Color"), DyeColor.CYAN);
        this.placedByPlayer = tag.getBoolean("PlacedByPlayer");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.putString("Color", this.color.getName());
        tag.putBoolean("PlacedByPlayer", placedByPlayer);
        tag.putString("Message", customMessage);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        this.color = DyeColor.byName(tag.getString("Color"), DyeColor.CYAN);
        this.placedByPlayer = tag.getBoolean("PlacedByPlayer");
        this.customMessage = tag.getString("Message");
    }

}
