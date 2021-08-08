package net.azagwen.atbyw.mixin.unused.piston;

import net.azagwen.atbyw.block.piston.PistonWoodTypes;
import net.azagwen.atbyw.block.piston.PistonDuck;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.PistonType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.PistonHeadBlock.TYPE;
import static net.minecraft.state.property.Properties.FACING;

@Mixin(PistonHeadBlock.class)
public class PistonHeadBlockMixin implements PistonDuck {
    private String type;

    private Block getPistonBlock() {
        if (this.type == null)
            return PistonWoodTypes.valueOf("OAK").getPiston();
        return PistonWoodTypes.valueOf(type).getPiston();
    }

    private Block getStickyPistonBlock() {
        if (this.type == null)
            return PistonWoodTypes.valueOf("OAK").getStickyPiston();
        return PistonWoodTypes.valueOf(type).getStickyPiston();
    }

    @Inject(method = "method_26980(Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)Z", at =
    @At(value = "HEAD"), cancellable = true)
    private void canExist(BlockState blockState, BlockState blockState2, CallbackInfoReturnable<Boolean> cbir) {
        Block block = blockState.get(TYPE) == PistonType.DEFAULT ? getPistonBlock() : getStickyPistonBlock();
        cbir.setReturnValue(blockState2.isOf(block) && blockState2.get(PistonBlock.EXTENDED) && blockState2.get(FACING) == blockState.get(FACING));
    }

    @Inject(method = "getPickStack(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Lnet/minecraft/item/ItemStack;", at =
    @At(value = "HEAD"), cancellable = true)
    @Environment(EnvType.CLIENT)
    private void getPickStack(BlockView world, BlockPos pos, BlockState state, CallbackInfoReturnable<ItemStack> cbir) {
        cbir.setReturnValue(new ItemStack(state.get(TYPE) == PistonType.STICKY ? getStickyPistonBlock() : getPistonBlock()));
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
