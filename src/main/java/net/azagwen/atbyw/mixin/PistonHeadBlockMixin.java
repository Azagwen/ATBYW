package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.blocks.piston.PistonDuck;
import net.azagwen.atbyw.blocks.piston.testing.PistonBlockType;
import net.azagwen.atbyw.blocks.piston.PistonHeadBlockDuck;
import net.minecraft.block.*;
import net.minecraft.block.enums.PistonType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.azagwen.atbyw.blocks.piston.PistonHelper.*;
import static net.minecraft.block.PistonHeadBlock.TYPE;
import static net.minecraft.state.property.Properties.FACING;

@Mixin(PistonHeadBlock.class)
public class PistonHeadBlockMixin implements PistonDuck {
    private int type;

    private Block getPistonBlock() {
        return getWoodType(this.type, PISTON);
    }

    private Block getStickyPistonBlock() {
        return getWoodType(this.type, STICKY_PISTON);
    }

    @Inject(method = "method_26980(Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)Z", at =
    @At(value = "HEAD"), cancellable = true)
    private void canExist(BlockState blockState, BlockState blockState2, CallbackInfoReturnable<Boolean> cbir) {
        Block block = blockState.get(TYPE) == PistonType.DEFAULT ? getPistonBlock() : getStickyPistonBlock();
        cbir.setReturnValue(blockState2.isOf(block) && blockState2.get(PistonBlock.EXTENDED) && blockState2.get(FACING) == blockState.get(FACING));
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
