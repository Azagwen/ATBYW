package net.azagwen.atbyw.blocks.statues;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.shape.VoxelShape;

public interface StatueBlockMobType {
    int NORTH = 0;
    int SOUTH = 1;
    int EAST = 2;
    int WEST = 3;

    String getName();
    VoxelShape getOutlineShape(int direction);
    VoxelShape getCollisionShape(int direction);
    VoxelShape[] getOutlineShapes();
    VoxelShape[] getCollisionShapes();
    Identifier getLootTable();
}
