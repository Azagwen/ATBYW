package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.main.DataResourceListener;
import net.azagwen.atbyw.main.ToolOperations;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoeItem.class)
public class HoeItemMixin {

    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        var world = context.getWorld();
        var player = context.getPlayer();
        var pos = context.getBlockPos();
        var direction = context.getSide();
        var heldItem = context.getStack();

        var replaceMap = DataResourceListener.HOE_REPLACE;
        var replaceLootMap = DataResourceListener.HOE_REPLACE_WITH_LOOT;

        if (replaceMap.containsKey(world.getBlockState(pos).getBlock())) {
            ToolOperations.replaceBlock(world, player, pos, SoundEvents.ITEM_HOE_TILL, replaceMap);
            cir.setReturnValue(ActionResult.success(world.isClient));
        }

        if (replaceLootMap.containsKey(world.getBlockState(pos).getBlock())) {
            ToolOperations.replaceBlockThenLoot(world, player, pos, direction, SoundEvents.ITEM_HOE_TILL, replaceLootMap);
            cir.setReturnValue(ActionResult.success(world.isClient));
        }

        if (player != null) {
            heldItem.damage(1, player, ((entity) -> {
                entity.sendToolBreakStatus(context.getHand());
            }));
        }
    }
}
