package com.gkoliver.anvilfix.mixin;

import com.gkoliver.anvilfix.AnvilFixConfig;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
    @ModifyConstant(method = "renderLabels(Lcom/mojang/blaze3d/vertex/PoseStack;II)V", constant = @Constant(intValue = 40, ordinal = 0))
    private int modifyInt(int input) {
        return AnvilFixConfig.getLevelLimit();
    }
}
