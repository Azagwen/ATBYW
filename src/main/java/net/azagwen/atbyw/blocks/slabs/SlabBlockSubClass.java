package net.azagwen.atbyw.blocks.slabs;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;

public class SlabBlockSubClass extends SlabBlock {
    public SlabBlockSubClass(Settings settings) {
        super(settings);
    }

    public BlockState copyStates(BlockState newState, BlockState copiedState) {
        return newState.with(TYPE, copiedState.get(TYPE));
    }
}
