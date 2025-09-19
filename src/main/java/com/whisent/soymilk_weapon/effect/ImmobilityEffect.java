package com.whisent.soymilk_weapon.effect;

import com.whisent.soymilk_weapon.effect.SWMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

public class ImmobilityEffect extends MobEffect {

    // 重力影响减少的修饰符UUID
    private static final java.util.UUID GRAVITY_MODIFIER_UUID = java.util.UUID.fromString("DC3498BE-0F8A-4F1B-BD5D-7DEDD648E42A");

    protected ImmobilityEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity entity, @Nullable Entity p_19463_, LivingEntity livingEntity, int p_19465_, double p_19466_) {
        super.applyInstantenousEffect(entity, p_19463_, livingEntity, p_19465_, p_19466_);
        double value = livingEntity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getValue();
        double modifyValue = 0 - value;
        livingEntity.getAttribute(ForgeMod.ENTITY_GRAVITY.get())
                .addTransientModifier(new AttributeModifier(GRAVITY_MODIFIER_UUID,
                        "Immobility Effect",
                        modifyValue,
                        AttributeModifier.Operation.ADDITION));
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
        entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).removeModifier(GRAVITY_MODIFIER_UUID);
    }


    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 持续应用效果
    }
}