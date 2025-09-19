package com.whisent.soymilk_weapon;

import com.mojang.logging.LogUtils;
import com.whisent.soymilk_weapon.client.event.KeybindingEvent;
import com.whisent.soymilk_weapon.effect.SWMobEffects;
import com.whisent.soymilk_weapon.item.SWWeapons;
import com.whisent.soymilk_weapon.network.NetworkHandler;
import net.minecraft.client.Minecraft;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;

@Mod(Soymilk_weapon.MODID)
public class Soymilk_weapon {

    public static final String MODID = "soymilk_weapon";

    private static final Logger LOGGER = LogUtils.getLogger();



    public Soymilk_weapon() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        SWWeapons.register(modEventBus);
        SWMobEffects.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        NetworkHandler.register();
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(KeybindingEvent.SKILL);
        }
    }
}