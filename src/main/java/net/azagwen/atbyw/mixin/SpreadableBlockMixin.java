package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.azagwen.atbyw.blocks.FenceDoorBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
