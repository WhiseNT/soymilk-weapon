package com.whisent.soymilk_weapon.mixin;

import cn.anecansaitin.cameraanim.common.network.S2CPayloadReply;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = S2CPayloadReply.class,remap = false)
public class S2CPayloadReplyMixin {
    @Inject(method = "handle", at = @At("RETURN"))
    private static void handle(S2CPayloadReply payload, Supplier<NetworkEvent.Context> contextSupplier, CallbackInfo ci) {
        if (contextSupplier.get().getDirection().getReceptionSide().isClient()) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                player.setDeltaMovement(0,0,0);
            }

        }
    }
}
