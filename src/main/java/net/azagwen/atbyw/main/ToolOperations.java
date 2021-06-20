package net.azagwen.atbyw.main;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Map;

public class ToolOperations {

    public static void replaceBlock(World world, PlayerEntity player, BlockPos pos, SoundEvent sound, Map<Block, Block> blockToBlockMap) {
        var hitBlock = world.getBlockState(pos).getBlock();

        //Block replacement
        for (var entry : blockToBlockMap.entrySet()) {
            var oldBlock = entry.getKey();
            var newBlock = entry.getValue();

            if (oldBlock == hitBlock) {
                var originalState = world.getBlockState(pos);

                world.setBlockState(pos, newBlock.getStateWithProperties(originalState));
                world.playSound(player, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

    public static void replaceBlockThenLoot(World world, PlayerEntity player, BlockPos pos, Direction side, SoundEvent sound, Map<Block, Pair<Block, Item>> blockToBlockLootMap) {
        var hitBlock = world.getBlockState(pos).getBlock();

        //Block replacement
        for (var entry : blockToBlockLootMap.entrySet()) {
            var oldBlock = entry.getKey();
            var newBlock = entry.getValue().getLeft();
            var lootItem = entry.getValue().getRight();

            if (oldBlock == hitBlock) {
                var originalState = world.getBlockState(pos);

                world.setBlockState(pos, newBlock.getStateWithProperties(originalState));
                world.playSound(player, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
                Block.dropStack(world, pos, side, new ItemStack(lootItem));
            }
        }
    }
}
