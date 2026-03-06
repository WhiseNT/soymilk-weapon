package com.whisent.soymilk_weapon.api;

import com.goldkl.soymilk.capability.onspecial.PlayerOnSpecial;
import com.goldkl.soymilk.capability.onspecial.PlayerOnSpecialProvider;
import com.goldkl.soymilk.capability.skillenergy.PlayerSkillEnergy;
import com.goldkl.soymilk.capability.skillenergy.PlayerSkillEnergyProvider;
import com.goldkl.soymilk.capability.specialenergy.PlayerSpecialEnergyProvider;
import com.goldkl.soymilk.tracking.ForgeEventTracker;
import net.minecraft.world.entity.player.Player;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 该工具类旨在简化玩家在武器能量、必杀能量的获取。
 */
public class EnergyHelper {
    /**
     * 获取玩家的武器能量值
     * @param player
     * @return
     */
    public static double getPlayerEnergy(Player player) {
        AtomicReference<Double> currentSkillEnergy = new AtomicReference<>((double) 0);
        player.getCapability(PlayerSkillEnergyProvider.PLAYER_SKILL_ENERGY_CAPABILITY)
                .ifPresent(skillCap -> {
            currentSkillEnergy.set(skillCap.getEnergy());
        });
        return currentSkillEnergy.get().doubleValue();
    }

    /**
     * 检查玩家是否在必杀状态
     * @param player
     * @return
     */
    public static boolean getOnSpecial(Player player) {
        AtomicReference<Boolean> isSpecial = new AtomicReference<>(false);
        player.getCapability(PlayerOnSpecialProvider.PLAYER_ON_SPECIAL_CAPABILITY).ifPresent(
                specialCap -> {
                    isSpecial.set(specialCap.getOnSpecial());
                });
        return isSpecial.get();
    }

    /**
     * 获取玩家的必杀能量值
     * @param player
     * @return
     */

    public static double getPlayerSpecialEnergy(Player player) {
        AtomicReference<Double> currentSpecialEnergy = new AtomicReference<>((double) 0);
        player.getCapability(PlayerSpecialEnergyProvider.PLAYER_SPECIAL_ENERGY_CAPABILITY).ifPresent(
                specialCap -> {
                    currentSpecialEnergy.set(specialCap.getspEnergy());
        });
        return currentSpecialEnergy.get().doubleValue();
    }


}
