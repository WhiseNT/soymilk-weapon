package com.whisent.soymilk_weapon;

import com.mojang.logging.LogUtils;
import com.whisent.soymilk_weapon.client.event.KeybindingEvent;

import com.whisent.soymilk_weapon.client.renderer.MeasureOfTheFormlessEntityRenderer;
import com.whisent.soymilk_weapon.effect.SWMobEffects;
import com.whisent.soymilk_weapon.entity.EntityRegistry;
import com.whisent.soymilk_weapon.item.SWWeapons;
import com.whisent.soymilk_weapon.network.NetworkHandler;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
        modEventBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        SWWeapons.register(modEventBus);
        SWMobEffects.register(modEventBus);
        EntityRegistry.ENTITIES.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        NetworkHandler.register();
    }
    
    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // 注册物品属性以支持动画
            ItemProperties.register(SWWeapons.MeasureOfTheFormless.get(), 
                ResourceLocation.fromNamespaceAndPath(MODID, "blocking"), 
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
        });
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
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(EntityRegistry.MEASURE_OF_THE_FORMLESS.get(),
                    MeasureOfTheFormlessEntityRenderer::new);;
        }
    }
}