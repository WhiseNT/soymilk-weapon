package com.whisent.soymilk_weapon.effect;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SWMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Soymilk_weapon.MODID);

    // 注册一个自定义效果
    public static final RegistryObject<MobEffect> IMMOBILITY =
            MOB_EFFECTS.register("immobility",
            () -> new ImmobilityEffect(MobEffectCategory.BENEFICIAL, 0x00FF00)
    );

    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
