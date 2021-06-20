package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.item.PickaxeItemDuck;
import net.azagwen.atbyw.main.DataResourceListener;
import net.azagwen.atbyw.main.ItemOperations;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin implements PickaxeItemDuck {

    //TODO: read https://fabricmc.net/wiki/tutorial:custom_resources

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var world = context.getWorld();
        var player = context.getPlayer();
        var pos = context.getBlockPos();
        var direction = context.getSide();
        var heldItem = context.getStack();

        var replaceMap = DataResourceListener.PICKAXE_REPLACE;

        if (replaceMap.containsKey(world.getBlockState(pos).getBlock())) {
            ItemOperations.replaceBlock(world, player, pos, direction, SoundEvents.BLOCK_NETHER_BRICKS_BREAK, replaceMap);

            if (player != null) {
                heldItem.damage(1, player, ((entity) -> {
                    entity.sendToolBreakStatus(context.getHand());
                }));
            }

            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}
