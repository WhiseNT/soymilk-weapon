package com.whisent.soymilk_weapon.core.skill.weapon;

import cn.anecansaitin.cameraanim.common.GlobalCameraSavedData;
import cn.anecansaitin.cameraanim.common.animation.GlobalCameraPath;
import cn.anecansaitin.cameraanim.common.network.ServerPayloadSender;
import com.goldkl.soymilk.tracking.ForgeEventTracker;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class SupremeArtSkill extends AbstractWeaponSkill{


    public SupremeArtSkill(Player player) {
        super(player);
    }

    @Override
    public int getAnimTime() {
        return 20;
    }

    @Override
    public int getSkillTime() {
        return 10;
    }

    @Override
    public int getSkillCost() {
        return 4;
    }

    @Override
    public void cast() {
        // 直接将技能注册到SkillManager中，不调用startSkill避免递归
        SkillManager.startSkill(player, this);
        // 重置执行状态，允许下次施放
    }

    @Override
    public void initAnim() {
        //动画第一tick时，初始化内容.
        if (animTickCounter == 1) {
            ForgeEventTracker.addPlayerSkillEnergy(player,-4);
            GlobalCameraSavedData data = GlobalCameraSavedData.getData((ServerLevel)player.level());
            GlobalCameraPath path = data.getPath("id");
            System.out.println(path);
            ServerPayloadSender.sendNativePath(path, (ServerPlayer) player, player);
            player.sendSystemMessage(Component.literal("技能准备中..."));
        }
    }

    @Override
    public void skillStart() {
        super.skillStart();
        player.sendSystemMessage(Component.literal("释放技能"));
    }

    @Override
    public void skillEnd() {
        super.skillEnd();
        player.sendSystemMessage(Component.literal("技能已结束"));
    }
    
    @Override
    public boolean isAnimFinished() {
        return animTickCounter > getAnimTime();
    }
}