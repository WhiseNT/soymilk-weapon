package com.whisent.soymilk_weapon.mixin;

import com.whisent.soymilk_weapon.effect.SWMobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "move", at = @At("HEAD"))
    public void onMove(MoverType p_19973_, Vec3 p_19974_, CallbackInfo ci) {
        if (thiz() instanceof Mob mob) {

        }
    }
    @Inject(method = "isControlledByLocalInstance", at = @At("HEAD"), cancellable = true)
    public void onControl(CallbackInfoReturnable<Boolean> cir) {
        if (thiz() instanceof Mob mob) {
            if (mob.hasEffect(SWMobEffects.IMMOBILITY.get())) {
                cir.setReturnValue(false);
            }
        }
    }
    private Entity thiz() {
        return (Entity) (Object) this;
    }
}
