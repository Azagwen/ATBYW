package net.azagwen.atbyw.block.statues;

import net.minecraft.block.BlockState;

public interface StatueBlockMethods {

    BlockState getResetState(BlockState state);

    int getMaxMossLevel();
}
