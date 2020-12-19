package net.azagwen.atbyw.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//import svenhjol.charm.base.helper.EnchantmentsHelper;
import static net.azagwen.atbyw.blocks.AtbywTags.BOOKSHELVES;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {

    @Redirect(
            method = "randomDisplayTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", args = {"log=false"})
    )
    public boolean redirectIsOf(BlockState state, Block block) {
//        boolean charmsValue = EnchantmentsHelper.canBlockPowerEnchantingTable(state) || state.isIn(BOOKSHELVES);
        boolean atbywValue = state.isIn(BOOKSHELVES);

        return atbywValue;
    }
}
