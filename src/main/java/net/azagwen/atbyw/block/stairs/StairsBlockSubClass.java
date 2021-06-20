package net.azagwen.atbyw.block.stairs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class StairsBlockSubClass extends StairsBlock {
    public StairsBlockSubClass(Block copiedBlock, Settings settings) {
        super(copiedBlock.getDefaultState(), settings);
    }
}
