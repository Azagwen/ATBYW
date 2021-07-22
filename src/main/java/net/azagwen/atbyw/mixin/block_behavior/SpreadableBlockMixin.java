package net.azagwen.atbyw.mixin.block_behavior;

import net.azagwen.atbyw.block.slabs.SpreadableSlabBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpreadableBlock.class)
public class SpreadableBlockMixin {

    @Inject(method = "canSurvive", at = @At(value = "HEAD", args = {"log=false"}), cancellable = true)
    private static void canSurvive(BlockState state, WorldView worldView, BlockPos pos, CallbackInfoReturnable<Boolean> cbir) {
        var blockState = worldView.getBlockState(pos.up());
        if (blockState.getBlock() instanceof SpreadableSlabBlock)
            cbir.setReturnValue(blockState.get(SpreadableSlabBlock.TYPE) == SlabType.TOP);
    }
}
