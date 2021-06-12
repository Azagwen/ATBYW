package net.azagwen.atbyw.block.stairs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class StairsBlockSubClass extends StairsBlock {
    public StairsBlockSubClass(Block copiedBlock, Settings settings) {
        super(copiedBlock.getDefaultState(), settings);
    }

    public BlockState copyStates(BlockState newState, BlockState copiedState) {
        return newState.with(FACING, copiedState.get(FACING)).with(HALF, copiedState.get(HALF)).with(SHAPE, copiedState.get(SHAPE));
    }
}
