package com.gkoliver.anvilfix.mixin;

import com.gkoliver.anvilfix.AnvilFixConfig;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class MixinItemStack {
    @Inject(method = "getBaseRepairCost()I", at = @At("RETURN"), cancellable = true)
    private void getRepairCost(CallbackInfoReturnable<Integer> cir) {
        if(cir.getReturnValueI() > 0 && AnvilFixConfig.removeIncrementalCost((ItemStack) (Object) this)) {
            cir.setReturnValue(0);
        }
    }
}
