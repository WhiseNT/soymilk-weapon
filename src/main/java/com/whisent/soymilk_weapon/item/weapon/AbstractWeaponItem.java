package com.whisent.soymilk_weapon.item.weapon;


import com.goldkl.soymilk.item.SkillWeaponItem;
import com.goldkl.soymilk.tracking.ForgeEventTracker;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.whisent.soymilk_weapon.api.EnergyHelper;
import com.whisent.soymilk_weapon.client.renderer.AbstractWeaponItemRenderer;
import com.whisent.soymilk_weapon.core.skill.weapon.SkillManager;
import com.whisent.soymilk_weapon.core.skill.weapon.SupremeArtSkill;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class AbstractWeaponItem extends SwordItem implements GeoItem, SkillWeaponItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected final String id = "abstract_weapon";
    public AbstractWeaponItem( Properties properties, int damage, float attack_speed) {
        super(Tiers.IRON,damage,attack_speed,properties);

    }

    public void castSkill(Player player) {
        if (canUseSkill(player)) {
            skill(player);
        }
    }

    public void skill(Player player) {
        SkillManager.startSkill(player, new SupremeArtSkill(player,this));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public boolean canUseSkill(Player player) {
        if (EnergyHelper.getPlayerEnergy(player) >= getNeedenergy()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void SpendEnergy(Player player) {
        SkillWeaponItem.super.SpendEnergy(player);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private AbstractWeaponItemRenderer renderer;

            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new AbstractWeaponItemRenderer();
                }

                return this.renderer;
            }
        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(hand == InteractionHand.OFF_HAND) {
            return InteractionResultHolder.fail(itemstack);
        }
        if(!this.canUseSkill(player)){
            return InteractionResultHolder.fail(itemstack);
        }
        return InteractionResultHolder.fail(itemstack);
    }




    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public String getId() {
        return this.id;
    }


    @Override
    public double getEfficiency() {
        return 1;
    }

    @Override
    public double getNeedenergy() {
        return 3;
    }
}
