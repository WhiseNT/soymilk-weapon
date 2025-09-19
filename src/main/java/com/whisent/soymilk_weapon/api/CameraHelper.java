package com.whisent.soymilk_weapon.api;

import cn.anecansaitin.cameraanim.common.GlobalCameraSavedData;
import cn.anecansaitin.cameraanim.common.animation.GlobalCameraPath;
import cn.anecansaitin.cameraanim.common.network.ServerPayloadSender;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;

public class CameraHelper {
    public static void playCameraAnimation(String id, ServerPlayer player) {
        GlobalCameraSavedData data = GlobalCameraSavedData.getData((ServerLevel)player.level());
        GlobalCameraPath path = data.getPath(id);
        ServerPayloadSender.sendNativePath(path, player, player);
    }
}
