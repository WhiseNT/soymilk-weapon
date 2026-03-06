package com.whisent.soymilk_weapon.client.renderer;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.client.model.MeasureOfTheFormlessEntityModel;
import com.whisent.soymilk_weapon.entity.MeasureOfTheFormlessEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
/**
 * 没用
 */
public class MeasureOfTheFormlessEntityRenderer  extends GeoEntityRenderer<MeasureOfTheFormlessEntity> {
    public MeasureOfTheFormlessEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new MeasureOfTheFormlessEntityModel());

    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(MeasureOfTheFormlessEntity entity) {
        return new ResourceLocation(Soymilk_weapon.MODID,
                "textures/item/measure_of_the_formless.png");
    }
}
