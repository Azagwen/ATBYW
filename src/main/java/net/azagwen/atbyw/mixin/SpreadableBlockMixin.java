package net.azagwen.atbyw.mixin;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(SpreadableBlockMixin.class)
public class SpreadableBlockMixin {

//    @Inject(method = "canSurvive(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", at =
//    @At(value = "HEAD", args = {"log=true"}), cancellable = true)
//    private void canSurvive(BlockState state, WorldView worldView, BlockPos pos, CallbackInfoReturnable cbir) {
//        BlockPos blockPos = pos.up();
//        BlockState blockState = worldView.getBlockState(blockPos);
//        if (blockState.isOf(AtbywBlocks.GRASS_BLOCK_SLAB) || blockState.isOf(AtbywBlocks.MYCELIUM_SLAB))
//            cbir.setReturnValue(true);
//    }
}
