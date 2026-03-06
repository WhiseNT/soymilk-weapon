package com.whisent.soymilk_weapon.network;

import com.goldkl.soymilk.tracking.ForgeEventTracker;
import com.whisent.soymilk_weapon.api.EnergyHelper;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpecialConsumePacket {
    public void encode(FriendlyByteBuf buf) {
    }
    public static SpecialConsumePacket decode(FriendlyByteBuf buf) {
        return new SpecialConsumePacket();
    }
    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            if (contextSupplier.get().getDirection().getReceptionSide().isServer()) {
                ServerPlayer player = contextSupplier.get().getSender();
                if (player != null && EnergyHelper.getPlayerSpecialEnergy(player) >= 100) {
                    ForgeEventTracker.setPlayerOnSpecial(player, true);
                }
            }
            contextSupplier.get().setPacketHandled(true);
        });
    }
}
