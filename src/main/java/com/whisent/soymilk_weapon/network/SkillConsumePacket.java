package com.whisent.soymilk_weapon.network;

import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SkillConsumePacket {
    public void encode(FriendlyByteBuf buf) {
        // 该数据包不需要编码任何数据
    }
    
    public static SkillConsumePacket decode(FriendlyByteBuf buf) {
        return new SkillConsumePacket();
    }
    
    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            if (contextSupplier.get().getDirection().getReceptionSide().isServer()) {
                ServerPlayer player = contextSupplier.get().getSender();
                if (player != null && player.getMainHandItem().getItem() instanceof AbstractWeaponItem weapon) {
                    weapon.castSkill(player);
                }
            }
            contextSupplier.get().setPacketHandled(true);
        });
    }
}