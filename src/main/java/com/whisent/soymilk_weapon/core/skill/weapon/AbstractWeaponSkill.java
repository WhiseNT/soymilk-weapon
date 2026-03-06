package com.whisent.soymilk_weapon.core.skill.weapon;

import com.goldkl.soymilk.tracking.ForgeEventTracker;
import com.whisent.soymilk_weapon.api.EnergyHelper;
import com.whisent.soymilk_weapon.effect.SWMobEffects;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractWeaponSkill {
    public Player player;
    public AbstractWeaponItem item;
    protected int animTickCounter = 0;
    protected int skillTickCounter = 0;


    public AbstractWeaponSkill(Player player, AbstractWeaponItem item) {
        this.player = player;
        this.item = item;
    }

    /**
     * 武器技能的动画时长
     * @return
     */
    public abstract int getAnimTime();

    /**
     * 武器技能的持续时间
     * @return
     */
    public abstract int getSkillTime();
    
    // 技能释放入口
    public void cast() {
        preload();
    }

    public void tick() {
        if (!isAnimFinished()) {
            animTickCounter+=1;
            if (animTickCounter == 1) {
                ForgeEventTracker.addPlayerSkillEnergy(player,-item.getNeedenergy());
                int rate = 1;
                if (!EnergyHelper.getOnSpecial(player)) {
                    ForgeEventTracker.addPlayerSpecialEnergy(player,
                            rate * item.getNeedenergy());
                }
                player.addEffect(
                        new MobEffectInstance(SWMobEffects.IMMOBILITY.get(),
                                getAnimTime(),1,false,false));


            }
            //player.sendSystemMessage(Component.literal("动画"+String.valueOf(animTickCounter)));
            initAnim();
            if (animTickCounter > getAnimTime() * 2) {
                skillStart();
            }
        } else if (!isSkillFinished()) {

            if (skillTickCounter == 0) {
                skillTickCounter = 1;
            } else {
                skillTickCounter++;
            }
            initSkill();
            if (skillTickCounter > getSkillTime()) {
                skillEnd();
            }
        }
    }

    /**
     * 在动画播放的特定tick中添加内容
     */

    public void initAnim() {

    }
    /**
     * 在技能持续期间添加内容
     */
    public void initSkill() {

    }
    
    // 加载动画
    public void preload() {

    }
    
    // 动画完成播放，技能正式开始
    public void skillStart() {

    }
    
    // 技能结束
    public void skillEnd() {

    }
    
    // 获取当前tick计数
    public int getAnimTickCounter() {
        return animTickCounter;
    }
    
    // 检查技能动画是否已完成
    public boolean isAnimFinished() {
        return animTickCounter > getAnimTime() * 2;
    }
    //检查技能是否完成
    public boolean isSkillFinished() {
      return skillTickCounter > getSkillTime();
    };
}