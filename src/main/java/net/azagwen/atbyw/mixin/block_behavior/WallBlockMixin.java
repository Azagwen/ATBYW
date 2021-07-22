package net.azagwen.atbyw.mixin.block_behavior;

import net.azagwen.atbyw.block.FenceDoorBlock;
import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WallBlock.class)
public class WallBlockMixin {

    @Inject(method = "shouldConnectTo(Lnet/minecraft/block/BlockState;ZLnet/minecraft/util/math/Direction;)Z", at =
    @At(value = "HEAD"), cancellable = true)
    private void shouldConnectTo(BlockState state, boolean faceFullSquare, Direction side, CallbackInfoReturnable cbir) {
        Block block = state.getBlock();
        boolean isFenceDoor = block instanceof FenceDoorBlock && FenceDoorBlock.canWallConnect(state, side);
        if (isFenceDoor)
            cbir.setReturnValue(true);
    }
}
