package com.whisent.soymilk_weapon.core.skill.weapon;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class SkillManager {
    // 存储每个玩家当前正在执行的技能
    private static final Map<Player, AbstractWeaponSkill> ACTIVE_SKILLS = new HashMap<>();

    /**
     * 开始执行一个技能
     * @param player 玩家
     * @param skill 技能实例
     */
    public static void startSkill(Player player, AbstractWeaponSkill skill) {
        // 如果玩家已经在执行技能，先停止当前技能
        if (ACTIVE_SKILLS.containsKey(player)) {
            stopSkill(player);
        }
        ACTIVE_SKILLS.put(player, skill);
        skill.preload();
    }

    /**
     * 更新所有活动技能
     * 这个方法应该在玩家tick事件中调用
     * @param player 玩家
     */
    public static void updateSkills(Player player) {
        AbstractWeaponSkill skill = ACTIVE_SKILLS.get(player);
        if (skill != null) {
            skill.tick(); // 更新技能状态
            
            // 如果技能已经执行完毕，移除它
            if (skill.isSkillFinished()) {
                ACTIVE_SKILLS.remove(player);
            }
        }
    }
    
    /**
     * 检查玩家是否正在执行技能
     * @param player 玩家
     * @return 是否正在执行技能
     */
    public static boolean isPerformingSkill(Player player) {
        return ACTIVE_SKILLS.containsKey(player);
    }
    
    /**
     * 停止玩家当前的技能
     * @param player 玩家
     */
    public static void stopSkill(Player player) {
        AbstractWeaponSkill skill = ACTIVE_SKILLS.remove(player);
        if (skill != null) {
            // 可以在这里添加技能中断的逻辑
            skill.skillEnd();
        }
    }
}