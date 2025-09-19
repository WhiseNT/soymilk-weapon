package com.whisent.soymilk_weapon.mixin;

import com.goldkl.soymilk.item.SkillWeaponItem;
import com.goldkl.soymilk.tracking.ForgeEventTracker;
import com.whisent.soymilk_weapon.core.skill.weapon.SkillManager;
import com.whisent.soymilk_weapon.effect.SWMobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    private boolean canGetEnegry = true;
    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void onPlayerAttack(Entity p_36347_, CallbackInfo ci) {
        if (thiz().level().isClientSide) return;
        if (!canGetEnegry) return;
        thiz().sendSystemMessage(Component.literal("onPlayerAttack"));
        ItemStack stack = thiz().getMainHandItem();
        if (thiz().getMainHandItem().getItem() instanceof SkillWeaponItem weaponItem ) {
            ForgeEventTracker.addPlayerSkillEnergy(thiz(),weaponItem.getEfficiency());
            canGetEnegry = false;
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onPlayerTick(CallbackInfo ci) {
        if (thiz().level().isClientSide) return;
        canGetEnegry = true;
        // 更新技能状态
        SkillManager.updateSkills(thiz());

    }
    @Inject(method = "isInvulnerableTo",at = @At("HEAD"), cancellable = true)
    public void onIsInvulnerableTo(DamageSource p_36101_, CallbackInfoReturnable<Boolean> cir) {
        if (thiz().hasEffect(SWMobEffects.IMMOBILITY.get())) {
            cir.setReturnValue(true);
        }
    }



    private Player thiz() {
        return (Player) (Object) this;
    }
}