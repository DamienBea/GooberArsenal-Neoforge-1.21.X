package net.teamluxron.gooberarsenal.client.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.teamluxron.gooberarsenal.network.ModNetwork;
import net.teamluxron.gooberarsenal.network.packet.EchoFlowerUpdateMessage;
import org.lwjgl.glfw.GLFW;

public class EchoFlowerEditScreen extends Screen {
    private final BlockPos pos;
    private final String initialMessage;
    private EditBox textBox;

    public EchoFlowerEditScreen(BlockPos pos, String initialMessage) {
        super(Component.translatable("gui.gooberarsenal.echo_flower.edit"));
        this.pos = pos;
        this.initialMessage = initialMessage;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        textBox = new EditBox(this.font, centerX - 100, centerY - 10, 200, 20, Component.literal(""));
        textBox.setMaxLength(32);
        textBox.setValue(initialMessage);

        this.addRenderableWidget(textBox);

        this.addRenderableWidget(
                Button.builder(Component.translatable("gui.done"), btn -> {
                    submitMessageToServer();
                    this.onClose();
                }).pos(centerX - 40, centerY + 20).size(80, 20).build()
        );
    }

    private void submitMessageToServer() {
        String message = textBox.getValue().trim();
        ModNetwork.sendToServer(new EchoFlowerUpdateMessage(pos, message));
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
