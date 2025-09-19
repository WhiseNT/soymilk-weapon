package com.whisent.soymilk_weapon.item;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.item.weapon.MeasureOfTheFormlessItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.IModBusEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SWWeapons {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Soymilk_weapon.MODID);

    public static final RegistryObject<Item> MeasureOfTheFormless =
            ITEMS.register("measure_of_the_formless",
                    () -> new MeasureOfTheFormlessItem(new Item.Properties(),4,-2));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
