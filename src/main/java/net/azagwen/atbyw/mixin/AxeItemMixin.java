package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.main.AtbywDataResourceListener;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public class AxeItemMixin {

    //TODO: read https://fabricmc.net/wiki/tutorial:custom_resources

    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        var world = context.getWorld();
        var player = context.getPlayer();
        var pos = context.getBlockPos();

    }

    private void strip(World world, PlayerEntity player, BlockPos pos) {
        var hitBlock = world.getBlockState(pos).getBlock();
        var strippingMap = AtbywDataResourceListener.AXE_STRIPPING;

        //Block Stripping
        if (strippingMap.containsKey(hitBlock)) {
            for (var log : strippingMap.entrySet()) {
                var original = log.getKey();
                var stripped = log.getValue();

                if (original == hitBlock) {
                    var originalState = world.getBlockState(pos);

                    world.setBlockState(pos, stripped.getStateWithProperties(originalState));
                    world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }

    private void stripWithLoot(World world, PlayerEntity player, BlockPos pos) {
        var hitBlock = world.getBlockState(pos).getBlock();
        var strippingMap = AtbywDataResourceListener.AXE_STRIPPING_WITH_LOOT;

        //Block Stripping
        strippingMap.forEach((pair, loot) -> {
            if (pair.getLeft() == hitBlock) {
                var original = pair.getLeft();
                var stripped = pair.getRight();

                if (original == hitBlock) {
                    var originalState = world.getBlockState(pos);

                    world.setBlockState(pos, stripped.getStateWithProperties(originalState));
                    world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    Block.dropStack(world, pos, new ItemStack(loot));
                }
            }
        });
    }
}
