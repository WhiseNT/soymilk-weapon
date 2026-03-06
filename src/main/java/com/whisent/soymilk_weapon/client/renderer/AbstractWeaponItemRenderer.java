package com.whisent.soymilk_weapon.client.renderer;

import com.whisent.soymilk_weapon.client.model.AbstractWeaponItemModel;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AbstractWeaponItemRenderer extends GeoItemRenderer<AbstractWeaponItem> {
    public AbstractWeaponItemRenderer() {
        super(new AbstractWeaponItemModel());
    }

}
