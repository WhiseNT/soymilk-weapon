package com.whisent.soymilk_weapon.client.renderer;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.client.model.MeasureOfTheFormlessModel;
import com.whisent.soymilk_weapon.item.weapon.MeasureOfTheFormlessItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MeasureOfTheFormlessRenderer  extends GeoItemRenderer<MeasureOfTheFormlessItem> {
    public MeasureOfTheFormlessRenderer() {
        super(new MeasureOfTheFormlessModel());
    }
}
