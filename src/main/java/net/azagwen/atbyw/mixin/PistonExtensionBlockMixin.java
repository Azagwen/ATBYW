package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.blocks.piston.PistonDuck;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonExtensionBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonExtensionBlock.class)
public class PistonExtensionBlockMixin implements PistonDuck {
    private static int type;

    @Inject(method = "createBlockEntityPiston(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;ZZ)Lnet/minecraft/block/entity/BlockEntity;", at =
    @At(value = "HEAD"), cancellable = true)
    private static void createBlockEntityPiston(BlockState pushedBlock, Direction dir, boolean extending, boolean bl, CallbackInfoReturnable<BlockEntity> cbir) {
        BlockEntity entity = new PistonBlockEntity(pushedBlock, dir, extending, bl);
        ((PistonDuck) entity).setType(type);

        cbir.setReturnValue(entity);
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
