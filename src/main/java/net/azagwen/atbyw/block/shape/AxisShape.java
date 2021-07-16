package net.azagwen.atbyw.block.shape;

import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShape;

public record AxisShape(double minRadius, double minDepth, double maxRadius, double maxDepth) {

    public VoxelShape xShape() {
        return Block.createCuboidShape(minRadius, minRadius, minDepth, maxRadius, maxRadius, maxDepth);
    }

    public VoxelShape zShape() {
        return Block.createCuboidShape(minDepth, minRadius, minRadius, maxDepth, maxRadius, maxRadius);
    }

    public VoxelShape yShape(boolean downwards) {
        var downShape = Block.createCuboidShape(minRadius, minDepth, minRadius, maxRadius, maxDepth, maxRadius);
        var upShape = Block.createCuboidShape(minRadius, (minDepth + 8), minRadius, maxRadius, (maxDepth + 8), maxRadius);

        return downwards ? downShape : upShape;
    }
}
