package net.azagwen.atbyw.block.slabs;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;

public class SlabBlockSubClass extends SlabBlock {
    public SlabBlockSubClass(Settings settings) {
        super(settings);
    }

    public BlockState copyStates(BlockState newState, BlockState copiedState) {
        return newState.with(TYPE, copiedState.get(TYPE));
    }
}
