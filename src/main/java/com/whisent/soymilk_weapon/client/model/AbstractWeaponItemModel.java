package com.whisent.soymilk_weapon.client.model;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AbstractWeaponItemModel extends GeoModel<AbstractWeaponItem> {

    @Override
    public ResourceLocation getModelResource(AbstractWeaponItem abstractWeaponItem) {
        return ResourceLocation.fromNamespaceAndPath(Soymilk_weapon.MODID,
                "geo/" + abstractWeaponItem.getId() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AbstractWeaponItem abstractWeaponItem) {
        return ResourceLocation.fromNamespaceAndPath(Soymilk_weapon.MODID,
                "textures/item/" + abstractWeaponItem.getId() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(AbstractWeaponItem abstractWeaponItem) {
        return ResourceLocation.fromNamespaceAndPath(Soymilk_weapon.MODID,
                abstractWeaponItem.getId());
    }

}
