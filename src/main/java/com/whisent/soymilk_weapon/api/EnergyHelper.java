package com.whisent.soymilk_weapon.api;

import com.goldkl.soymilk.capability.skillenergy.PlayerSkillEnergyProvider;
import net.minecraft.world.entity.player.Player;

import java.util.concurrent.atomic.AtomicReference;

public class EnergyHelper {
    public static double getPlayerEnergy(Player player) {
        AtomicReference<Double> currentSkillEnergy = new AtomicReference<>((double) 0);
        player.getCapability(PlayerSkillEnergyProvider.PLAYER_SKILL_ENERGY_CAPABILITY)
                .ifPresent(skillCap -> {
            currentSkillEnergy.set(skillCap.getEnergy());
        });
        return currentSkillEnergy.get().doubleValue();
    }
}
