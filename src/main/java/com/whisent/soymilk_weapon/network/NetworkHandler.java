package com.whisent.soymilk_weapon.network;

import com.whisent.soymilk_weapon.Soymilk_weapon;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel CHANNEL;

    public static void register() {
        CHANNEL = NetworkRegistry.ChannelBuilder.named(
                ResourceLocation.fromNamespaceAndPath(
                        Soymilk_weapon.MODID,"main"))
                .serverAcceptedVersions((version) -> true)
                .clientAcceptedVersions((version) -> true)
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .simpleChannel();
        
        int id = 0;
        CHANNEL.registerMessage(id++, SkillConsumePacket.class, 
                SkillConsumePacket::encode, 
                SkillConsumePacket::decode, 
                SkillConsumePacket::handle);
    }
    
    public static void sendToServer(Object msg) {
        if (CHANNEL != null) {
            CHANNEL.send(PacketDistributor.SERVER.noArg(),msg);
        }
    }
    
    public static void sendToAllClient(Object msg) {
        if (CHANNEL != null) {
            CHANNEL.send(PacketDistributor.ALL.noArg(),msg);
        }
    }
}