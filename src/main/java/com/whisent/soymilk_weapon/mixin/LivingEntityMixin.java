package com.whisent.soymilk_weapon.mixin;

import com.whisent.soymilk_weapon.effect.SWMobEffects;
import com.whisent.soymilk_weapon.item.SWWeapons;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    public void onLivingHurt(DamageSource ds, float originalDamage, CallbackInfoReturnable<Boolean> cir) {
        if (thiz().getUseItem().is(SWWeapons.MeasureOfTheFormless.get())) {
            thiz().stopUsingItem();
            Entity attacker = ds.getEntity();
            int damage = 10;
            if (thiz() instanceof Player player) {
                attacker.hurt(
                        thiz().damageSources()
                                .playerAttack(player),
                        damage);
                player.getCooldowns().addCooldown(
                        SWWeapons.MeasureOfTheFormless.get(), 6 * 20);
            } else {
                attacker.hurt(
                        thiz().damageSources()
                                .mobAttack(thiz()),
                        damage);
            }
            cir.setReturnValue(false);
        }
    }
    @Inject(method = "aiStep", at = @At("TAIL"), cancellable = true)
    private void modifyGravity(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasEffect(SWMobEffects.IMMOBILITY.get())) {
            thiz().setDeltaMovement(0,0,0);

        }
    }


    private LivingEntity thiz() {
        return (LivingEntity) (Object) this;
    };
}
