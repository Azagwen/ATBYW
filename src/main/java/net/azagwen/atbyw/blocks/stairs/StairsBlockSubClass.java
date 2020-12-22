package net.azagwen.atbyw.blocks.stairs;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class StairsBlockSubClass extends StairsBlock {
    public StairsBlockSubClass(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
    }

    public BlockState copyStates(BlockState newState, BlockState copiedState) {
        return newState.with(FACING, copiedState.get(FACING)).with(HALF, copiedState.get(HALF)).with(SHAPE, copiedState.get(SHAPE));
    }
}
