package net.azagwen.atbyw.main;

import com.google.common.collect.Table;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ItemOperations {

    public static ActionResult replaceBlock(World world, PlayerEntity player, BlockPos pos, Direction side, Table.Cell<Item, Block, ItemOperationResult> operation, Supplier<ActionResult> command) {
        var hitBlock = world.getBlockState(pos).getBlock();
        var oldBlock = operation.getColumnKey();
        var newBlock = operation.getValue().result();
        var lootItem = operation.getValue().loot();
        var sound = operation.getValue().sound();

        if (oldBlock == hitBlock) {
            var originalState = world.getBlockState(pos);
            world.setBlockState(pos, newBlock.getStateWithProperties(originalState));

            if (sound != null) {
                world.playSound(player, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            if (lootItem != null) {
                Block.dropStack(world, pos, side, new ItemStack(lootItem));
            }
            return command.get();
        } else {
            return ActionResult.PASS;
        }
    }
}
