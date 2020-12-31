package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.blocks.piston.PistonDuck;
import net.minecraft.block.*;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.block.enums.PistonType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.azagwen.atbyw.blocks.piston.PistonHelper.*;
import static net.azagwen.atbyw.blocks.piston.PistonHelper.STICKY_PISTON;

@Mixin(PistonBlockEntity.class)
public class PistonBlockEntityMixin implements PistonDuck {
    @Shadow private boolean source;
    @Shadow private BlockState pushedBlock;
    @Shadow private boolean extending;
    @Shadow private float progress;
    private int type;

    private Block getStickyPistonBlock() {
        return getWoodType(this.type, STICKY_PISTON);
    }

    private Block getPistonHeadBlock() {
        return getWoodType(this.type, PISTON_HEAD);
    }

    @Inject(method = "getHeadBlockState()Lnet/minecraft/block/BlockState;", at =
    @At(value = "HEAD"), cancellable = true)
    private void getHeadBlockState(CallbackInfoReturnable<BlockState> cbir) {
        cbir.setReturnValue(!this.isExtending() && this.isSource() && this.pushedBlock.getBlock() instanceof PistonBlock ? getPistonHeadBlock().getDefaultState().with(PistonHeadBlock.SHORT, this.progress > 0.25F).with(PistonHeadBlock.TYPE, this.pushedBlock.isOf(getStickyPistonBlock()) ? PistonType.STICKY : PistonType.DEFAULT).with(PistonHeadBlock.FACING, this.pushedBlock.get(PistonBlock.FACING)) : this.pushedBlock);
    }

    @Shadow
    public boolean isSource() {
        return source;
    }

    @Shadow
    public boolean isExtending() {
        return this.extending;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
