package com.whisent.soymilk_weapon.mixin;

import com.whisent.soymilk_weapon.item.weapon.MeasureOfTheFormlessItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Display;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import software.bernie.geckolib.animatable.GeoItem;
/**
 * 没用
 */
@Mixin(Display.ItemDisplay.class)
public class DisplayMixin {

    @Inject(method = "getItemStack", at = @At("HEAD"), cancellable = true)
    public void setItemStack(CallbackInfoReturnable<ItemStack> cir) {
        ItemStack itemStack = cir.getReturnValue();
        // 添加null检查以避免空指针异常
        if (itemStack != null && itemStack.getItem() instanceof MeasureOfTheFormlessItem measure
                && (((Display)(Object)this).level() instanceof ServerLevel serverLevel)) {
            long id = GeoItem.getOrAssignId(itemStack, serverLevel);
            measure.triggerAnim((Display)(Object)this, id, "controller", "技能");
        }
    }
}