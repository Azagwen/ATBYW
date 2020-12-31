package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.blocks.piston.PistonBlockType;
import net.azagwen.atbyw.blocks.piston.PistonHeadBlockDuck;
import net.minecraft.block.*;
import net.minecraft.block.enums.PistonType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.PistonHeadBlock.TYPE;
import static net.minecraft.state.property.Properties.FACING;

@Mixin(PistonHeadBlock.class)
public class PistonHeadBlockMixin implements PistonHeadBlockDuck {
    private PistonBlockType pistonBlockType;

    @Inject(method = "method_26980(Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)Z", at =
    @At(value = "HEAD"), cancellable = true)
    private void canExist(BlockState blockState, BlockState blockState2, CallbackInfoReturnable cbir) {
        Block block = blockState.get(TYPE) == PistonType.DEFAULT ? pistonBlockType.getPistonType(false) : pistonBlockType.getPistonType(true);
        cbir.setReturnValue(blockState2.isOf(block) && (Boolean)blockState2.get(PistonBlock.EXTENDED) && blockState2.get(FACING) == blockState.get(FACING));
    }

    @Override
    public void setPistonType(PistonBlockType type) {
        this.pistonBlockType = type;
    }
}
