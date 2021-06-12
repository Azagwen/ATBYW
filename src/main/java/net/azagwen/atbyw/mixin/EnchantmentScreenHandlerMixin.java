package net.azagwen.atbyw.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.screen.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.azagwen.atbyw.main.AtbywTags.*;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {

	/**
	 * Redirects method Blockstate.isOf in synthetic method "method_17411" from lambda in "onContentChanged()" to Blockstate.isIn
	 * @param state Blockstate from the world
	 * @param block unused
	 * @return Blockstate.isIn(TAG)
	 */

	@Redirect(
			method = "method_17411(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", args = {"log=false"})
	)
	public boolean redirectIsOf(BlockState state, Block block) {
		return state.isIn(BOOKSHELVES);
	}
}
