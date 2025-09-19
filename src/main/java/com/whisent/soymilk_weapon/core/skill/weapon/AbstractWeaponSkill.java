package com.whisent.soymilk_weapon.core.skill.weapon;

import com.goldkl.soymilk.tracking.ForgeEventTracker;
import com.whisent.soymilk_weapon.effect.SWMobEffects;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
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
    
    public abstract int getAnimTime();
    public abstract int getSkillTime();
    
    // 技能释放入口
    public void cast() {
        preload();
    }

    public void tick() {
        if (!isAnimFinished()) {
            animTickCounter++;
            if (animTickCounter == 1) {
                ForgeEventTracker.addPlayerSkillEnergy(player,-item.getNeedenergy());
                player.addEffect(
                new MobEffectInstance(SWMobEffects.IMMOBILITY.get(),
                20,1,false,false));
            }
            initAnim();
            if (animTickCounter > getAnimTime()) {
                skillTickCounter++;
                skillStart();
            }
        } else if (!isSkillFinished()) {
            skillTickCounter++;
            initSkill();
            if (skillTickCounter > getSkillTime()) {
                skillEnd();
            }
        }
    }

    public void initAnim() {

    }
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
        return animTickCounter > getAnimTime();
    }
    //检查技能是否完成
    public boolean isSkillFinished() {
      return skillTickCounter > getSkillTime();
    };
}