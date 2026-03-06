package com.whisent.soymilk_weapon.network;

import com.goldkl.soymilk.client.Animation;
import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;
/**
 * 没用。
 */
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
                    player.setDeltaMovement(0,0,0);


                }
            }
            contextSupplier.get().setPacketHandled(true);
        });
    }
}