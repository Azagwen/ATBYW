package net.azagwen.atbyw.mixin.sign;

import net.azagwen.atbyw.block.AtbywSignBlock;
import net.azagwen.atbyw.block.AtbywWallSignBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
        var block = state.getBlock();
        if (BlockEntityType.SIGN.equals(this) && (block instanceof AtbywSignBlock || block instanceof AtbywWallSignBlock)) {
            info.setReturnValue(true);
        }
    }
}
