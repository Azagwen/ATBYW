package net.azagwen.atbyw.mixin.sign;

import net.azagwen.atbyw.block.sign.SignBlockExt;
import net.azagwen.atbyw.block.sign.WallSignBlockExt;
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
        if (BlockEntityType.SIGN.equals(this) && (block instanceof SignBlockExt || block instanceof WallSignBlockExt)) {
            info.setReturnValue(true);
        }
    }
}
