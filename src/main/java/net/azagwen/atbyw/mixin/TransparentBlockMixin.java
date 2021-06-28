package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TransparentBlock.class)
public class TransparentBlockMixin {

    @Inject(at = @At("HEAD"), method = "isSideInvisible", cancellable = true)
    public void isSideInvisible(BlockState state, BlockState stateFrom, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        AtbywBlocks.GLASS_MAP.forEach((glass, shatteredGlass) -> {
            if (state.isOf(glass)) {
                cir.setReturnValue(stateFrom.isOf(glass) || stateFrom.isOf(shatteredGlass));
            }
        });
    }
}
