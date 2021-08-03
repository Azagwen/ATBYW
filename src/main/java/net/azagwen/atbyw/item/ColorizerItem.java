package net.azagwen.atbyw.item;

import net.azagwen.atbyw.block.CanvasBlock;
import net.azagwen.atbyw.block.entity.CanvasBlockEntity;
import net.azagwen.atbyw.main.AtbywStats;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ColorizerItem extends Item implements SimpleColoredItem {

    public ColorizerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var stack = context.getStack();
        var pos = context.getBlockPos();
        var world = context.getWorld();
        var player = context.getPlayer();
        var state = world.getBlockState(pos);

        if (state.getBlock() instanceof CanvasBlock) {
            var entity = (CanvasBlockEntity) world.getBlockEntity(pos);
            if (entity.getColor() != this.getColor(stack)) {
                this.applyPaint(stack, player, entity, state, pos, world);
                player.increaseStat(AtbywStats.COLOR_CANVAS_BLOCK, 1);
                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        } else {
            return ActionResult.PASS;
        }
    }

    public void applyPaint(ItemStack stack, PlayerEntity player, CanvasBlockEntity entity, BlockState state, BlockPos pos, World world) {
        updateCanvas(entity, this.getColor(stack));
        world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!world.isClient && !player.isCreative()) {
            stack.damage(1, world.random, ((ServerPlayerEntity) player));
        }
    }

    public void updateCanvas(CanvasBlockEntity entity, int color) {
        entity.setColor(color);
        entity.updateListeners();
    }
}
