package com.gkoliver.anvilfix.mixin;

import com.gkoliver.anvilfix.AnvilFixConfig;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {
    @Inject(method = "damage(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/state/BlockState;", at = @At("HEAD"), cancellable = true)
    private static void getLandingState(BlockState orignalState, CallbackInfoReturnable<BlockState> cir) {
        if(AnvilFixConfig.shouldStopAnvilBreaking()) {
            cir.setReturnValue(orignalState);
        }
    }
}
