package com.whisent.soymilk_weapon.core.skill.weapon;

import cn.anecansaitin.cameraanim.common.GlobalCameraSavedData;
import cn.anecansaitin.cameraanim.common.animation.GlobalCameraPath;
import cn.anecansaitin.cameraanim.common.network.ServerPayloadSender;
import com.goldkl.soymilk.client.Animation;
import com.goldkl.soymilk.tracking.ForgeEventTracker;
import com.whisent.soymilk_weapon.Soymilk_weapon;
import com.whisent.soymilk_weapon.api.CameraHelper;
import com.whisent.soymilk_weapon.entity.EntityRegistry;
import com.whisent.soymilk_weapon.entity.MeasureOfTheFormlessEntity;
import com.whisent.soymilk_weapon.item.weapon.AbstractWeaponItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoItem;

public class SupremeArtSkill extends AbstractWeaponSkill{


    public SupremeArtSkill(Player player, AbstractWeaponItem item) {
        super(player, item);
    }
    MeasureOfTheFormlessEntity entity;

    @Override
    public int getAnimTime() {
        return 72;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public int getSkillTime() {
        return 100;
    }

    @Override
    public void cast() {
        // 直接将技能注册到SkillManager中，不调用startSkill避免递归
        SkillManager.startSkill(player, this);

    }

    @Override
    public void initAnim() {
        // 动画第一tick时，初始化内容
        if (animTickCounter == 5) {
            ForgeEventTracker.addPlayerSkillEnergy(player, -4);
            entity = new MeasureOfTheFormlessEntity(EntityRegistry.MEASURE_OF_THE_FORMLESS.get(), player.level());
            final double HORIZONTAL_OFFSET = -0.2D;
            // 修改实体生成位置为玩家视角正前方
            Vec3 lookAngle = player.getLookAngle();
            // 计算玩家的右侧方向向量（通过将朝向向量绕Y轴旋转90度）
            Vec3 rightVector = new Vec3(-lookAngle.z, 0, lookAngle.x).normalize();
            Vec3 spawnPos = player.getEyePosition().add(lookAngle.scale(1.0D))
                    .add(rightVector.scale(HORIZONTAL_OFFSET))
                    .add(0, -0.5, 0);
            entity.setPos(spawnPos);

            player.level().addFreshEntity(entity);
            entity.triggerAnim("controller", "技能");
            player.sendSystemMessage(Component.literal("技能准备中..."));
        }
        if (animTickCounter == 1) {
            Animation.doAnimation(Minecraft.getInstance().player,
                    ResourceLocation.fromNamespaceAndPath(Soymilk_weapon.MODID,"supreme_art"),2);
        }
        if (animTickCounter == 5) {
            player.setDeltaMovement(0, 0, 0);
            long id = GeoItem.getOrAssignId(player.getMainHandItem(), (ServerLevel) player.level());
            item.triggerAnim(player, id, "controller", "技能");
            // 使用正确的ID而不是硬编码的"id"
            CameraHelper.playCameraAnimation("supreme_art", (ServerPlayer) player);

        }
        if (animTickCounter == 80 && entity != null) {
            entity.discard();
        }

    }

    @Override
    public void skillStart() {
        super.skillStart();


        // 重置执行状态，允许下次施放
        player.sendSystemMessage(Component.literal("释放技能"));
    }

    @Override
    public void skillEnd() {
        super.skillEnd();
        player.sendSystemMessage(Component.literal("技能已结束"));

    }

    @Override
    public boolean isAnimFinished() {
        return animTickCounter > getAnimTime()*2;
    }
}