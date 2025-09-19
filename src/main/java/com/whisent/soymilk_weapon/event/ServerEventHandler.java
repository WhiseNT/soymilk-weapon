package com.whisent.soymilk_weapon.event;

import com.goldkl.soymilk.item.SkillWeaponItem;
import com.goldkl.soymilk.tracking.ForgeEventTracker;
import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.item.SWWeapons;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Soymilk_weapon.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {
    @SubscribeEvent
    public static void onBlockedHurt(LivingHurtEvent event) {

    }
    
    @SubscribeEvent
    public static void onLivingKnockback(LivingKnockBackEvent event) {
        // 检查实体是否正在使用MeasureOfTheFormless武器进行格挡
        if (event.getEntity().getUseItem().is(SWWeapons.MeasureOfTheFormless.get())) {
            // 取消击退效果
            event.getEntity().stopUsingItem();
            event.setCanceled(true);
        }
    }
}