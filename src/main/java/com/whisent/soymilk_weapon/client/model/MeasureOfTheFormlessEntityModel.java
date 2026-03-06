package com.whisent.soymilk_weapon.client.model;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.entity.MeasureOfTheFormlessEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
/**
 * 没用
 */
public class MeasureOfTheFormlessEntityModel extends DefaultedEntityGeoModel<MeasureOfTheFormlessEntity> {
    public MeasureOfTheFormlessEntityModel() {
        super(new ResourceLocation(Soymilk_weapon.MODID, "measure_of_the_formless"), true);

    }
}
