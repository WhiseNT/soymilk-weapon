package com.whisent.soymilk_weapon.item.weapon;

import com.goldkl.soymilk.tracking.ForgeEventTracker;
import com.whisent.soymilk_weapon.core.skill.weapon.SkillManager;
import com.whisent.soymilk_weapon.core.skill.weapon.SupremeArtSkill;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;

public class MeasureOfTheFormlessItem extends AbstractWeaponItem {
    protected final String id = "measure_of_the_formless";

    public MeasureOfTheFormlessItem(Properties properties, int damage, float attack_speed) {
        super(properties, damage, attack_speed);
    }

    @Override
    public void skill(Player player) {
        super.skill(player);
        new SupremeArtSkill(player,this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
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