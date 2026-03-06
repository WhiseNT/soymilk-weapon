package com.whisent.soymilk_weapon.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(net.minecraft.client.gui.components.DebugScreenOverlay.class) // 这里使用一个示例类，实际应该针对渲染效果的类
public class MobEffectRendererMixin {
    
    // 实际上，要修改效果图标的位置，我们需要注入到实际渲染这些图标的方法中
    // 由于在代码库中没有找到确切的渲染类，这里提供一个示例实现方式
    
    /*
    @Inject(method = "renderEffects", at = @At("HEAD"), cancellable = true)
    private void onRenderEffects(GuiGraphics guiGraphics, CallbackInfo ci) {
        // 取消默认渲染，我们可以完全自定义渲染位置
        ci.cancel();
        
        // 在这里实现自定义的效果图标渲染逻辑
        // 可以修改渲染位置、大小、透明度等
        renderCustomEffects(guiGraphics);
    }
    
    private void renderCustomEffects(GuiGraphics guiGraphics) {
        // 获取当前玩家
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        
        // 获取玩家的所有效果
        Collection<MobEffectInstance> effects = player.getActiveEffects();
        
        // 自定义渲染位置 - 例如在屏幕左上角而不是右上角
        int x = 5;  // 左边距
        int y = 5;  // 上边距
        
        for (MobEffectInstance effect : effects) {
            // 渲染效果图标
            // guiGraphics.blit(...);
            
            // 更新下一个图标的位置
            y += 24; // 每个图标高度大约为24像素
        }
    }
    */
}