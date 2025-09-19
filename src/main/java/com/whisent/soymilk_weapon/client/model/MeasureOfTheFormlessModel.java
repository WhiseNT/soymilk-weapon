package com.whisent.soymilk_weapon.client.model;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import com.whisent.soymilk_weapon.item.weapon.MeasureOfTheFormlessItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MeasureOfTheFormlessModel extends GeoModel<MeasureOfTheFormlessItem> {

    @Override
    public ResourceLocation getModelResource(MeasureOfTheFormlessItem measureOfTheFormlessItem) {
        return ResourceLocation.fromNamespaceAndPath(Soymilk_weapon.MODID,
                "geo/" + "measure_of_the_formless" + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MeasureOfTheFormlessItem measureOfTheFormlessItem) {
        return ResourceLocation.fromNamespaceAndPath(Soymilk_weapon.MODID,
                "textures/item/" + "measure_of_the_formless" + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(MeasureOfTheFormlessItem measureOfTheFormlessItem) {
        return ResourceLocation.fromNamespaceAndPath(Soymilk_weapon.MODID,
                "measure_of_the_formless");
    }
}
