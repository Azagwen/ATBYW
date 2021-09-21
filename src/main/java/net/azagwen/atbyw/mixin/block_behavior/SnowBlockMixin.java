package net.azagwen.atbyw.mixin.block_behavior;

import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(SnowBlock.class)
public class SnowBlockMixin {

    @Inject(method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", at =
    @At(value = "HEAD"), cancellable = true)
    public void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> returnable) {
        BlockState blockState = world.getBlockState(pos.down());
        if (blockState.isOf(BuildingBlockRegistry.PACKED_ICE_BRICKS) || blockState.isOf(BuildingBlockRegistry.CHISELED_PACKED_ICE_BRICKS)) {
            returnable.setReturnValue(false);
        }
        if (blockState.isOf(BuildingBlockRegistry.PACKED_ICE_STAIRS) || blockState.isOf(BuildingBlockRegistry.PACKED_ICE_BRICKS_STAIRS)) {
            returnable.setReturnValue(false);
        }
        if (blockState.isOf(BuildingBlockRegistry.PACKED_ICE_SLAB) || blockState.isOf(BuildingBlockRegistry.PACKED_ICE_BRICKS_SLAB)) {
            returnable.setReturnValue(false);
        }
    }
}
