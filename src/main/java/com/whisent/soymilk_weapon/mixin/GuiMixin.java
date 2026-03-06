package com.whisent.soymilk_weapon.mixin;

import com.google.common.collect.Ordering;
import com.whisent.soymilk_weapon.client.EffectRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(Gui.class)
public class GuiMixin {
    
    /**
     * 修改效果图标渲染逻辑，改变渲染位置
     */
    @Inject(method = "renderEffects", at = @At("HEAD"), cancellable = true)
    private void renderCustomEffects(GuiGraphics guiGraphics, CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        Collection<MobEffectInstance> collection = minecraft.player.getActiveEffects();
        
        if (!collection.isEmpty()) {
            Screen screen = minecraft.screen;
            if (screen instanceof EffectRenderingInventoryScreen) {
                EffectRenderingInventoryScreen effectRenderScreen = (EffectRenderingInventoryScreen) screen;
                if (effectRenderScreen.canSeeEffects()) {
                    return;
                }
            }

            // 取消默认渲染逻辑
            ci.cancel();
            
            // 使用新的渲染类处理效果渲染
            EffectRenderer.renderCustomMobEffects(guiGraphics, collection);
        }
    }
}