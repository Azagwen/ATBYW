package net.azagwen.atbyw.mixin.block_behavior;

import net.azagwen.atbyw.block.FenceDoorBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PaneBlock.class)
public class PaneBlockMixin {

    @Inject(method = "connectsTo(Lnet/minecraft/block/BlockState;Z)Z", at =
    @At(value = "HEAD"), cancellable = true)
    private void connectsTo(BlockState state, boolean bl, CallbackInfoReturnable cbir) {
        Block block = state.getBlock();

        boolean isFenceDoor = block instanceof FenceDoorBlock;
        if (isFenceDoor)
            cbir.setReturnValue(true);
    }
}
