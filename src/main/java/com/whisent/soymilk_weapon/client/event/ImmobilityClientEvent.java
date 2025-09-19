package com.whisent.soymilk_weapon.client.event;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.effect.ImmobilityEffect;
import com.whisent.soymilk_weapon.effect.SWMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Soymilk_weapon.MODID, value = Dist.CLIENT)
public class ImmobilityClientEvent {

    // 用于存储玩家视角锁定前的视角
    private static float lockedYaw = 0.0f;
    private static float lockedPitch = 0.0f;
    private static double lockedY = 0.0f;
    private static boolean isLocked = false;

    @SubscribeEvent
    public static void onMovementInput(MovementInputUpdateEvent event) {
        Player player = event.getEntity();
        // 如果玩家有ImmobilityEffect效果，则限制其移动
        if (player.hasEffect(SWMobEffects.IMMOBILITY.get())) {
            // 禁止移动
            event.getInput().forwardImpulse = 0;
            event.getInput().leftImpulse = 0;
            event.getInput().jumping = false;
            event.getInput().shiftKeyDown = false;
            
            // 禁止视角转动
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == player) {
                if (!isLocked) {
                    // 首次锁定，保存当前视角
                    lockedYaw = player.getYRot();
                    lockedPitch = player.getXRot();
                    lockedY = player.getY();
                    isLocked = true;
                }


                // 锁定视角
                player.setYRot(lockedYaw);
                player.setXRot(lockedPitch);
                player.yRotO = lockedYaw;
                player.xRotO = lockedPitch;
            }
        } else {
            // 没有效果时解锁
            isLocked = false;
        }
    }
    //玩家技能动画期间禁用鼠标输入
    @SubscribeEvent
    public static void onPlayerInput(InputEvent.MouseButton.Pre event) {
        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null) return;
        if (Minecraft.getInstance().screen == null &&
                player.hasEffect(SWMobEffects.IMMOBILITY.get())) {
            event.setCanceled(true);
        }
    }
}