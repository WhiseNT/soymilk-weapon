package com.whisent.soymilk_weapon.client.event;

import com.goldkl.soymilk.item.SkillWeaponItem;
import com.mojang.blaze3d.platform.InputConstants;
import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.effect.SWMobEffects;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import com.whisent.soymilk_weapon.network.NetworkHandler;
import com.whisent.soymilk_weapon.network.SkillConsumePacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Soymilk_weapon.MODID,value = { Dist.CLIENT })
public class KeybindingEvent {
    public static final KeyMapping SKILL =
            new KeyMapping("key.soymilk_weapon.skill",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_V,
                    "key.categories.soymilk_weapon");
    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        if (SKILL.consumeClick()) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                if (player.getMainHandItem().getItem() instanceof SkillWeaponItem item) {
                    if (NetworkHandler.CHANNEL != null) {
                        if (player.hasEffect(SWMobEffects.IMMOBILITY.get())) return;
                        NetworkHandler.sendToServer(new SkillConsumePacket());
                    }
                }
            }
        }
    }



}
