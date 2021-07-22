package net.azagwen.atbyw.block.shape;

import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShape;

public record DirectionShapeH(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {

    public VoxelShape northShape() {
        return Block.createCuboidShape(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public VoxelShape southShape() {
        return Block.createCuboidShape(minX, minY, (minZ + 8), maxX, maxY, (maxZ + 8));
    }

    public VoxelShape eastShape() {
        return Block.createCuboidShape((minZ + 8), minY, minX, (maxZ + 8), maxY, maxX);
    }

    public VoxelShape westShape() {
        return Block.createCuboidShape(minZ, minY, minX, maxZ, maxY, maxX);
    }
}
