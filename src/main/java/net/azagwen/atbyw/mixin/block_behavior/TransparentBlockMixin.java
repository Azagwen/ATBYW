package net.azagwen.atbyw.mixin.block_behavior;

import net.azagwen.atbyw.block.ShatteredGlassBlock;
import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TransparentBlock.class)
public class TransparentBlockMixin {

    @Inject(at = @At("HEAD"), method = "isSideInvisible", cancellable = true)
    public void isSideInvisible(BlockState state, BlockState stateFrom, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        BuildingBlockRegistry.SHATTERED_GLASS_SET.forEach((block) -> {
            var shatteredGlass = (ShatteredGlassBlock) block;

            if (state.getBlock() instanceof GlassBlock glassBlock && shatteredGlass.getColor() == null) {
                cir.setReturnValue(stateFrom.isOf(glassBlock) || stateFrom.isOf(shatteredGlass));
            }
            if (state.getBlock() instanceof StainedGlassBlock stainedGlassBlock){
                if (stainedGlassBlock.getColor().equals(shatteredGlass.getColor())) {
                    cir.setReturnValue(stateFrom.isOf(stainedGlassBlock) || stateFrom.isOf(shatteredGlass));
                }
            }
        });
    }
}
