package net.azagwen.atbyw.item;

import net.azagwen.atbyw.block.entity.CanvasBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

import java.awt.*;

public class CanvasBlockItem extends BlockItem implements DyeableItem {
    public CanvasBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public int getColor(ItemStack stack) {
        var nbtCompound = stack.getSubTag("display");
        var color = Color.WHITE.getRGB();
        if (nbtCompound != null && nbtCompound.contains("color", 99)) {
            color = nbtCompound.getInt("color");
        }
        return color;
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        var place = super.place(context);
        var world = context.getWorld();
        var pos = context.getBlockPos();
        var entity = world.getBlockEntity(pos);
        var stack = context.getStack();

        if (place.isAccepted()) {
            if (entity instanceof CanvasBlockEntity blockEntity) {
                blockEntity.setColor(this.getColor(stack));
            }
        }
        return place;
    }
}
