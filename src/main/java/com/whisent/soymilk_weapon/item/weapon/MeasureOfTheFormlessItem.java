package com.whisent.soymilk_weapon.item.weapon;

import com.goldkl.soymilk.tracking.ForgeEventTracker;
import com.whisent.soymilk_weapon.client.renderer.AbstractWeaponItemRenderer;
import com.whisent.soymilk_weapon.client.renderer.MeasureOfTheFormlessRenderer;
import com.whisent.soymilk_weapon.core.skill.weapon.SkillManager;
import com.whisent.soymilk_weapon.core.skill.weapon.SupremeArtSkill;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import software.bernie.example.registry.SoundRegistry;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.ClientUtils;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
/**
 * 没用
 */
public class MeasureOfTheFormlessItem extends AbstractWeaponItem {

    protected final String id = "measure_of_the_formless";
    private static final RawAnimation SKILL_ANIM =
            RawAnimation.begin().thenPlay("技能");

    public MeasureOfTheFormlessItem(Properties properties, int damage, float attack_speed) {
        super(properties, damage, attack_speed);
        // 注册为可同步动画对象
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void skill(Player player) {
        super.skill(player);

        if (!player.level().isClientSide() && player.level() instanceof ServerLevel serverLevel) {
            ItemStack itemStack = player.getMainHandItem();


        }

        SkillManager.startSkill(player, new SupremeArtSkill(player,this));
        player.sendSystemMessage(Component.literal("measure_of_the_formless"));
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private MeasureOfTheFormlessRenderer renderer;

            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                        this.renderer = new MeasureOfTheFormlessRenderer();
                }

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        super.registerControllers(controllers);
        controllers.add(new AnimationController[]{(new AnimationController(this,
                "controller",
                0, this::predicate))
                .triggerableAnim("技能", SKILL_ANIM)
                });
    }
    
    private PlayState predicate(AnimationState<MeasureOfTheFormlessItem> state) {
        return PlayState.STOP;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(hand == InteractionHand.OFF_HAND) {
            return InteractionResultHolder.fail(itemstack);
        }
        if(!this.canUseSkill(player)){
            return InteractionResultHolder.fail(itemstack);
        }
        // 允许主手使用物品并开始使用动画
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public double getEfficiency() {
        return 1.7;
    }

    @Override
    public double getNeedenergy() {
        return 4;
    }

}