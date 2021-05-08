package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.block.*;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(SnowBlock.class)
public class SnowBlockMixin {

    @Inject(method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", at =
    @At(value = "HEAD"), cancellable = true)
    public void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable returnable) {
        BlockState blockState = world.getBlockState(pos.down());
        if (blockState.isOf(AtbywBlocks.PACKED_ICE_BRICKS) || blockState.isOf(AtbywBlocks.CHISELED_PACKED_ICE_BRICKS)) {
            returnable.setReturnValue(false);
        }
        if (blockState.isOf(AtbywBlocks.PACKED_ICE_STAIRS) || blockState.isOf(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS)) {
            returnable.setReturnValue(false);
        }
        if (blockState.isOf(AtbywBlocks.PACKED_ICE_SLAB) || blockState.isOf(AtbywBlocks.PACKED_ICE_BRICKS_SLAB)) {
            returnable.setReturnValue(false);
        }
    }
}
