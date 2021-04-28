package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.block.FenceDoorBlock;
import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.Block.cannotConnect;

@Mixin(FenceBlock.class)
public class FenceBlockMixin {

    @Inject(method = "canConnect(Lnet/minecraft/block/BlockState;ZLnet/minecraft/util/math/Direction;)Z", at =
    @At(value = "HEAD", args = {"log=false"}), cancellable = true)
    private void canConnect(BlockState state, boolean faceFullSquare, Direction side, CallbackInfoReturnable cbir) {
        Block block = state.getBlock();
        boolean isFenceDoor = block instanceof FenceDoorBlock && FenceDoorBlock.canWallConnect(state, side);
        if (isFenceDoor)
            cbir.setReturnValue(true);
    }
}
