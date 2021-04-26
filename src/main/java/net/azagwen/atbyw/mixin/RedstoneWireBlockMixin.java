package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.blocks.BookshelfToggleBlock;
import net.azagwen.atbyw.blocks.SpikeTrapBlock;
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
    private static void connectsTo(BlockState state, Direction dir, CallbackInfoReturnable cbir) {
        if (state.getBlock() instanceof BookshelfToggleBlock) {
            cbir.setReturnValue(dir == state.get(BookshelfToggleBlock.FACING) || dir == state.get(BookshelfToggleBlock.FACING).rotateYClockwise() || dir == state.get(BookshelfToggleBlock.FACING).rotateYCounterclockwise());
        }
        if (state.getBlock() instanceof SpikeTrapBlock) {
            cbir.setReturnValue(true);
        }
    }
}
