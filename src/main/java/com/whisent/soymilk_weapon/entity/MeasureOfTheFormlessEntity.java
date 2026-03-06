package com.whisent.soymilk_weapon.entity;

import com.whisent.soymilk_weapon.item.SWWeapons;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
/**
 * 没用
 */
public class MeasureOfTheFormlessEntity extends Entity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation SKILL_ANIM =
            RawAnimation.begin().thenPlay("技能");

    public MeasureOfTheFormlessEntity(EntityType<? extends Entity> type, Level level) {
        super(type, level);

    }
    public InteractionResult interactAt(Player player, Vec3 hitPos, InteractionHand hand) {
        return super.interactAt(player, hitPos, hand);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{(new AnimationController(this,
                "controller",
                0, this::predicate))
                .triggerableAnim("技能", SKILL_ANIM)
        });
    }
    private <T extends GeoAnimatable>PlayState predicate(AnimationState<T> state) {
        state.getController().setAnimation(RawAnimation.begin());
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    public InteractionResult interact(Player p_19978_, InteractionHand p_19979_) {
        p_19978_.sendSystemMessage(Component.literal("触发技能"));

        return super.interact(p_19978_, p_19979_);

    }


    @Override
    public void readAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        super.tick();
    }
}
