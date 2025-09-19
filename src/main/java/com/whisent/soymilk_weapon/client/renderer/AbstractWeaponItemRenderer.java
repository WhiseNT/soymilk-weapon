package com.whisent.soymilk_weapon.client.renderer;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.client.model.AbstractWeaponItemModel;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AbstractWeaponItemRenderer extends GeoItemRenderer<AbstractWeaponItem> {
    public AbstractWeaponItemRenderer() {
        super(new AbstractWeaponItemModel());
    }
}
