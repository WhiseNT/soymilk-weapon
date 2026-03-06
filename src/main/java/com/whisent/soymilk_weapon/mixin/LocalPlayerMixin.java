package com.whisent.soymilk_weapon.mixin;

import com.whisent.soymilk_weapon.effect.SWMobEffects;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.minecraft.client.player.LocalPlayer.class)
public class LocalPlayerMixin {
    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    public void onMove(MoverType p_108670_, Vec3 p_108671_, CallbackInfo ci) {
        if (thiz().hasEffect(SWMobEffects.IMMOBILITY.get())) {
            thiz().setDeltaMovement(0,0,0);
        }
    }

    private net.minecraft.client.player.LocalPlayer thiz() {
        return (net.minecraft.client.player.LocalPlayer) (Object) this;
    }
}
