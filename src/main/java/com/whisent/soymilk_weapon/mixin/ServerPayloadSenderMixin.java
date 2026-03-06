package com.whisent.soymilk_weapon.mixin;

import cn.anecansaitin.cameraanim.common.animation.GlobalCameraPath;
import cn.anecansaitin.cameraanim.common.network.ServerPayloadSender;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/**
 * 没用。
 */
@Mixin(value = ServerPayloadSender.class,remap = false)
public class ServerPayloadSenderMixin {
    @Inject(method = "sendNativePath", at = @At("HEAD"), cancellable = true)
    private static void sendPayload(GlobalCameraPath path, ServerPlayer player, Entity center, CallbackInfo ci) {
        player.setDeltaMovement(0, 0, 0);
        player.hurtMarked = false;

    }
}
