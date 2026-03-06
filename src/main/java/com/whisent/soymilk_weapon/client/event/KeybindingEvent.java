package com.whisent.soymilk_weapon.client.event;

import com.goldkl.soymilk.SoymilkCore;
import com.goldkl.soymilk.client.Animation;
import com.goldkl.soymilk.item.SkillWeaponItem;
import com.mojang.blaze3d.platform.InputConstants;
import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.client.NonPausingScreen;
import com.whisent.soymilk_weapon.effect.SWMobEffects;
import com.whisent.soymilk_weapon.entity.EntityRegistry;
import com.whisent.soymilk_weapon.entity.MeasureOfTheFormlessEntity;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import com.whisent.soymilk_weapon.network.NetworkHandler;
import com.whisent.soymilk_weapon.network.SkillConsumePacket;
import com.whisent.soymilk_weapon.network.SpecialConsumePacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

/*
* 这个类是监听玩家按键以释放技能和进入必杀状态的类。
* */
@Mod.EventBusSubscriber(modid = Soymilk_weapon.MODID,value = { Dist.CLIENT })
public class KeybindingEvent {
    public static final KeyMapping SKILL =
            new KeyMapping("key.soymilk_weapon.skill",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_V,
                    "key.categories.soymilk_weapon");

    public static final KeyMapping SPECIAL =
            new KeyMapping("key.soymilk_weapon.special",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_X,
                    "key.categories.soymilk_weapon");
    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        if (SKILL.consumeClick()) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                if (player.getMainHandItem().getItem() instanceof SkillWeaponItem item) {
                    if (NetworkHandler.CHANNEL != null) {
                        if (player.hasEffect(SWMobEffects.IMMOBILITY.get())) return;

                        // 重置玩家的姿态、视角和坐标
                        resetPlayerPoseAndPosition(player);
                        Minecraft.getInstance().setScreen(new NonPausingScreen());

                        NetworkHandler.sendToServer(new SkillConsumePacket());
                    }
                }
            }
        }
        if (SPECIAL.consumeClick()) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                if (NetworkHandler.CHANNEL != null) {
                    if (player.hasEffect(SWMobEffects.IMMOBILITY.get())) return;

                    
                    NetworkHandler.sendToServer(new SpecialConsumePacket());
                }
            }
        }
    }
    
    /**
     * 重置玩家的姿态、视角和坐标，使视角与身体方向一致
     * @param player 玩家实体
     */
    private static void resetPlayerPoseAndPosition(LocalPlayer player) {
        // 重置玩家的视角，使其与身体方向一致
        player.setYRot(player.yBodyRot);
        player.setXRot(0); // 重置俯仰角为水平视角
        player.yRotO = player.yBodyRot;
        player.xRotO = 0;
        player.setDeltaMovement(0,0,0);
        player.lerpMotion(0,0,0);
        // 重置玩家的姿态
        player.setPose(net.minecraft.world.entity.Pose.STANDING);
    }

    @SubscribeEvent
    public static void onChatReceived(ClientChatReceivedEvent event) {
        //Test if it is a player (main or other) and the message
        if (event.getMessage().contains(Component.literal("a"))) {


            //UUID player = event.getSender();
            //SoymilkCore.channel.sendToServer(new AnimationMessage(player,new ResourceLocation(SoymilkCore.MODID,"waving")));

            //Get the player from Minecraft, using the chat profile ID. From network packets, you'll receive entity IDs instead of UUIDs
            //AbstractClientPlayer player = (AbstractClientPlayer)Minecraft.getInstance().level.getPlayerByUUID(event.getSender());
/*
            if (player == null) return; //The player can be null because it was a system message or because it is not loaded by this player.
            //Get the animation for that player
            var animation1 = (ModifierLayer<IAnimation>)PlayerAnimationAccess.getPlayerAssociatedData( player).get(new ResourceLocation(SoymilkCore.MODID, "first_animation"));
            var animation2 = (ModifierLayer<IAnimation>)PlayerAnimationAccess.getPlayerAssociatedData( player).get(new ResourceLocation(SoymilkCore.MODID, "third_animation"));
            if (animation1 != null) {
                //You can set an animation from anywhere ON THE CLIENT
                //Do not attempt to do this on a server, that will only fail
                //SoymilkCore.LOGGER.info("{}",tem1.bodyParts);
                animation1.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation(SoymilkCore.MODID, "first_person.waving"))).setFirstPersonConfiguration(new FirstPersonConfiguration(true,true,true,true)).setFirstPersonMode(FirstPersonMode.THIRD_PERSON_MODEL));
                //You might use  animation.replaceAnimationWithFade(); to create fade effect instead of sudden change
                //See javadoc for details
            }
            if (animation2 != null) {
                //You can set an animation from anywhere ON THE CLIENT
                //Do not attempt to do this on a server, that will only fail
               // SoymilkCore.LOGGER.info("{}",tem1.bodyParts);
                animation2.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation(SoymilkCore.MODID, "third_person.waving"))));
                //You might use  animation.replaceAnimationWithFade(); to create fade effect instead of sudden change
                //See javadoc for details
            }*/
        }
    }



}
