package com.whisent.soymilk_weapon.client;

import com.google.common.collect.Ordering;
import com.mojang.blaze3d.systems.RenderSystem;
import com.whisent.soymilk_weapon.Soymilk_weapon;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
/**
 * 处理Effect渲染的类，有用。
 */
public class EffectRenderer {
    // 存储每个效果实例的初始持续时间
    private static final Map<Integer, Integer> INITIAL_DURATIONS = new HashMap<>();
    // 存储上一帧中活跃的效果实例ID
    private static final Set<Integer> LAST_ACTIVE_EFFECTS = new HashSet<>();
    
    // 自定义效果图标背景纹理路径
    private static final ResourceLocation BUFF_BACKGROUND = 
        ResourceLocation.fromNamespaceAndPath(Soymilk_weapon.MODID, "textures/gui/buff.png");
    
    // 进度条外框纹理路径
    private static final ResourceLocation MOB_EFFECT_TEXTURE = 
        ResourceLocation.fromNamespaceAndPath(Soymilk_weapon.MODID, "textures/gui/mob_effect.png");
    
    /**
     * 自定义效果图标渲染方法
     * 将效果图标渲染位置从右上角改为左上角
     */
    public static void renderCustomMobEffects(GuiGraphics guiGraphics, Collection<MobEffectInstance> effects) {
        RenderSystem.enableBlend();

        // 创建当前活跃效果ID集合
        Set<Integer> currentActiveEffects = new HashSet<>();
        
        int startX = 5;
        int startY = 48;
        int spacing = 25;
        int currentY = startY;

        long currentTime = System.currentTimeMillis(); // 获取当前时间

        // 按照自然顺序排序效果
        for(MobEffectInstance effectInstance : Ordering.natural().sortedCopy(effects)) {
            MobEffect effect = effectInstance.getEffect();
            if (effectInstance.showIcon()) {
                int x = startX;
                int y = currentY;
                
                // 获取效果实例的唯一ID
                int effectId = System.identityHashCode(effectInstance);
                currentActiveEffects.add(effectId);
                
                // 获取该效果的初始持续时间
                Integer initialDuration = INITIAL_DURATIONS.get(effectId);
                
                // 只有当这是新效果（没有记录初始持续时间）或者效果被刷新时才更新初始持续时间
                // 判断效果被刷新的条件：当前持续时间明显大于记录的初始持续时间（加一个小的缓冲值）
                if (initialDuration == null || effectInstance.getDuration() > initialDuration + 100) {
                    INITIAL_DURATIONS.put(effectId, effectInstance.getDuration());
                    initialDuration = effectInstance.getDuration();
                }
                
                // 计算进度比例 (从100%到0%)
                float progress = (float) effectInstance.getDuration() / initialDuration;
                
                // 绘制背景
                guiGraphics.blit(BUFF_BACKGROUND, x, y, 0, 0, 24, 24, 24, 24);
                
                // 绘制竖向进度条，表示剩余时间
                if (!effectInstance.isAmbient()) {
                    // 根据进度变化颜色
                    if (progress > 0.6f) {
                        // 大于50%时显示绿色
                        renderVerticalProgressBar(guiGraphics, x + 24, y, 8, 22, effectInstance, 0.0F, 1.0F, 0.0F);
                    } else if (progress > 0.3f) {
                        // 25%-50%时显示黄色
                        renderVerticalProgressBar(guiGraphics, x + 24, y, 8, 22, effectInstance, 1.0F, 1.0F, 0.0F);
                    } else {
                        // 低于25%时显示红色
                        renderVerticalProgressBar(guiGraphics, x + 24, y, 8, 22, effectInstance, 1.0F, 0.0F, 0.0F);
                    }
                }

                // 计算图标透明度
                float alpha = 1.0F;
                if (progress <= 0.3f) {
                    // 闪烁周期为1000毫秒
                    long flashPeriod = 1000L;
                    // 根据当前时间计算透明度
                    long timeInCycle = currentTime % flashPeriod;
                    
                    // 使用正弦函数生成平滑变化的透明度
                    double angle = (timeInCycle / (double)flashPeriod) * Math.PI * 2; // 将时间映射到[0, 2π]
                    alpha = (float)(Math.sin(angle) * 0.25 + 0.75); // 将sin值映射到[0.5, 1.0]
                }
                
                TextureAtlasSprite sprite = Minecraft.getInstance().getMobEffectTextures().get(effect);
                // 直接渲染图标
                guiGraphics.pose().pushPose();
                RenderSystem.enableBlend();
                guiGraphics.setColor(1.0F, 1.0F, 1.0F, alpha);
                guiGraphics.blit(x + 3, y + 3, 0, 18, 18, sprite);
                guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
                guiGraphics.pose().popPose();
                
                
                
                // 渲染效果名称和剩余时间
                String effectName = effect.getDisplayName().getString();
                // 计算剩余时间（以秒为单位）
                int remainingSeconds = effectInstance.getDuration() / 20;
                // 转换为分钟和秒的格式
                int minutes = remainingSeconds / 60;
                int seconds = remainingSeconds % 60;
                String timeString = String.format("%02d:%02d", minutes, seconds);

                // 在进度条右侧绘制文本
                guiGraphics.drawString(Minecraft.getInstance().font, 
                        effectName, 
                        x + 36, // 进度条右侧位置
                        y + 3,  // 垂直居中
                        0xFFFFFF);
                
                guiGraphics.drawString(Minecraft.getInstance().font, 
                        timeString, 
                        x + 36, // 进度条右侧位置
                        y + 13, // 垂直居中靠下
                        0xFFFFFF);

                currentY += spacing;
            }
        }
        
        // 清理已消失的效果实例数据
        LAST_ACTIVE_EFFECTS.removeAll(currentActiveEffects);
        for (Integer removedEffectId : LAST_ACTIVE_EFFECTS) {
            INITIAL_DURATIONS.remove(removedEffectId);
        }
        
        // 更新上一帧活跃效果集合
        LAST_ACTIVE_EFFECTS.clear();
        LAST_ACTIVE_EFFECTS.addAll(currentActiveEffects);
    }
    
    /**
     * 渲染竖向进度条方法
     * @param guiGraphics GuiGraphics对象
     * @param x 进度条x坐标
     * @param y 进度条y坐标
     * @param width 进度条宽度
     * @param height 进度条高度
     * @param effectInstance 效果实例
     * @param r 颜色R值
     * @param g 颜色G值
     * @param b 颜色B值
     */
    private static void renderVerticalProgressBar(GuiGraphics guiGraphics, int x, int y, int width, int height, 
                                   MobEffectInstance effectInstance, float r, float g, float b) {
        // 获取效果的当前持续时间
        int duration = effectInstance.getDuration();
        
        // 使用效果实例的对象哈希码作为唯一标识符
        int effectId = System.identityHashCode(effectInstance);
        
        // 获取该效果的初始持续时间
        Integer initialDuration = INITIAL_DURATIONS.get(effectId);
        
        // 如果是新效果，记录其初始持续时间
        if (initialDuration == null || duration > initialDuration + 100) {
            INITIAL_DURATIONS.put(effectId, duration);
            initialDuration = duration;
        }
        
        // 计算进度比例 (从100%到0%)
        float progress = (float) duration / initialDuration;
        
        // 根据进度确定颜色
        if (progress > 0.5f) {
            // 大于50%时保持传入的颜色（通常是绿色）
        } else if (progress > 0.25f) {
            // 25%-50%时显示黄色
            r = 1.0F;
            g = 1.0F;
            b = 0.0F;
        } else {
            // 低于25%时显示红色
            r = 1.0F;
            g = 0.0F;
            b = 0.0F;
        }
        
        // 绘制进度条外框 (从纹理的24,0到32,23的位置)
        // 使用完整的纹理大小参数32,24
        guiGraphics.blit(MOB_EFFECT_TEXTURE, x+1, y,
                0,
                24, 0,
                9, 24,
                128, 128);
        
        // 绘制进度（根据效果类型使用不同颜色）
        // 竖向进度条从下往上填充
        guiGraphics.setColor(r, g, b, 1.0F);
        int progressHeight = (int) (height * progress);
        if (progressHeight > 0) {
            // 从底部开始绘制进度，留出边框空间
            guiGraphics.fill(x + 4, y + 1 + height - progressHeight, x + width, y + height, -1);
        }
        
        // 重置颜色
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
    
    /**
     * 将整数转换为罗马数字
     * @param number 要转换的整数 (1-10)
     * @return 对应的罗马数字字符串
     */
    private static String intToRoman(int number) {
        switch (number) {
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            case 8: return "VIII";
            case 9: return "IX";
            case 10: return "X";
            default: return String.valueOf(number);
        }
    }
}