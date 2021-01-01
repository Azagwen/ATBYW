package net.azagwen.atbyw.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.azagwen.atbyw.misc.AtbywTags.BOOKSHELVES;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {

    @Redirect(method = "randomDisplayTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V", at =
    @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", args = {"log=false"}))
    public boolean redirectIsOf(BlockState state, Block block) {
        boolean atbywValue = state.isIn(BOOKSHELVES);

        return atbywValue;
    }
}
