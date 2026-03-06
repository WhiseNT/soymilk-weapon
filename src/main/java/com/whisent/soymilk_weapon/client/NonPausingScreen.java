package com.whisent.soymilk_weapon.client;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
/**
 * 没用。
 */
public class NonPausingScreen extends Screen {
    public NonPausingScreen() {
        super(Component.empty());
    }

    @Override
    protected void init() {
        // 可以在这里添加组件
    }

    @Override
    public void render(net.minecraft.client.gui.GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // 可以在这里添加渲染逻辑，或者保持空白
    }

    @Override
    public boolean isPauseScreen() {
        // 这是关键：返回false使游戏不会暂停
        return false;
    }
}

