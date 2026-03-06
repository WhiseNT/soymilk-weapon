package com.whisent.soymilk_weapon.entity;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Soymilk_weapon.MODID);
    public static final RegistryObject<EntityType<MeasureOfTheFormlessEntity>> MEASURE_OF_THE_FORMLESS =
            ENTITIES.register("measure_of_the_formless",
            ()-> EntityType.Builder.of(MeasureOfTheFormlessEntity::new, MobCategory.MISC)
                    .sized(1f,1f)
                    .build(new ResourceLocation(Soymilk_weapon.MODID,
                            "measure_of_the_formless").toString()));


    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

}
