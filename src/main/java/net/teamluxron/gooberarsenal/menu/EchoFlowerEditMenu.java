package net.teamluxron.gooberarsenal.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.teamluxron.gooberarsenal.blocks.entity.function.EchoFlowerBlockEntity;

public class EchoFlowerEditMenu extends AbstractContainerMenu {

    private final BlockPos pos;
    private final EchoFlowerBlockEntity flower;

    public EchoFlowerEditMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, buf.readBlockPos());
    }

    public EchoFlowerEditMenu(int id, Inventory inv) {
        this(id, inv, BlockPos.ZERO);
    }

    public EchoFlowerEditMenu(int id, Inventory inv, BlockPos pos) {
        super(ModMenus.ECHO_FLOWER_MENU_TYPE, id);
        this.pos = pos;

        Level level = inv.player.level();
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof EchoFlowerBlockEntity echo) {
            this.flower = echo;
        } else {
            this.flower = null;
        }
    }

    public EchoFlowerBlockEntity getFlower() {
        return flower;
    }

    public BlockPos getPos() {
        return pos;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return flower != null && !flower.isRemoved() && player.distanceToSqr(pos.getCenter()) <= 64.0;
    }

    public String getCurrentMessage() {
        return flower != null ? flower.getCustomMessage() : "";
    }
}