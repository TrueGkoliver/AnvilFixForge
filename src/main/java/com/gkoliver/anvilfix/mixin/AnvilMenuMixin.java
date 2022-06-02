package com.gkoliver.anvilfix.mixin;

import com.gkoliver.anvilfix.AnvilFix;
import com.gkoliver.anvilfix.AnvilFixConfig;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
    @ModifyConstant(method = "createResult()V", constant = @Constant(intValue = 40, ordinal = 2))
    private int modifyInt(int input) {
        return AnvilFixConfig.getLevelLimit();
    }

    @Redirect(method = "createResult()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"))
    private int getMaximumLevelProxy(Enchantment enchantment) {
        return AnvilFixConfig.getEnchantmentLimit(enchantment);
    }
}
