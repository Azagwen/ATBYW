package net.azagwen.atbyw.mixin.block_behavior;

import net.azagwen.atbyw.block.*;
import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {

    @Inject(method = "connectsTo(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z", at =
    @At(value = "HEAD"), cancellable = true)
    private static void connectsTo(BlockState state, Direction dir, CallbackInfoReturnable<Boolean> cbir) {
        if (state.getBlock() instanceof BookshelfToggleBlock) {
            cbir.setReturnValue(dir == state.get(BookshelfToggleBlock.FACING) || dir == state.get(BookshelfToggleBlock.FACING).rotateYClockwise() || dir == state.get(BookshelfToggleBlock.FACING).rotateYCounterclockwise());
        }
        if ((state.getBlock() instanceof SpikeTrapBlock) && (dir != null)) {
            cbir.setReturnValue(true);
        }
        if (state.getBlock() instanceof RedstoneJackOlantern) {
            cbir.setReturnValue(dir == state.get(RedstoneJackOlantern.FACING).getOpposite());
        }
        if (state.getBlock() instanceof TimerRepeaterBlock) {
            Direction direction = state.get(TimerRepeaterBlock.FACING);
            cbir.setReturnValue(direction == dir || direction.getOpposite() == dir);
        }
        if (state.getBlock() instanceof RedstonePipeComponent) {
            cbir.setReturnValue(false);
        }
    }
}
