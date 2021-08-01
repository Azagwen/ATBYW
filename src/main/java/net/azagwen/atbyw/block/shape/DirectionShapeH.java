package net.azagwen.atbyw.block.shape;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.Map;
import java.util.Objects;

public class DirectionShapeH {
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;
    private final double minZ;
    private final double maxZ;
    private final double mirrorMinZ;
    private final double mirrorMaxZ;

    public DirectionShapeH(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.mirrorMinZ = Math.abs(16 - maxZ);
        this.mirrorMaxZ = Math.abs(16 - minZ);
    }

    public VoxelShape northShape() {
        return Block.createCuboidShape(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public VoxelShape southShape() {
        return Block.createCuboidShape(minX, minY, mirrorMinZ, maxX, maxY, mirrorMaxZ);
    }

    public VoxelShape eastShape() {
        return Block.createCuboidShape(mirrorMinZ, minY, minX, mirrorMaxZ, maxY, maxX);
    }

    public VoxelShape westShape() {
        return Block.createCuboidShape(minZ, minY, minX, maxZ, maxY, maxX);
    }

    public VoxelShape getFromDirection(Direction direction) {
        return switch (direction) {
            case UP, DOWN -> VoxelShapes.empty();
            case NORTH -> this.northShape();
            case SOUTH -> this.southShape();
            case EAST -> this.eastShape();
            case WEST -> this.westShape();
        };
    }

    public Map<Direction, VoxelShape> getAsDirectionShapeMap() {
        var map = Maps.<Direction, VoxelShape>newHashMap();
        for (var dir : Direction.Type.HORIZONTAL) {
            map.put(dir, this.getFromDirection(dir));
        }
        return map;
    }

    public double minX() {
        return minX;
    }

    public double minY() {
        return minY;
    }

    public double minZ() {
        return minZ;
    }

    public double maxX() {
        return maxX;
    }

    public double maxY() {
        return maxY;
    }

    public double maxZ() {
        return maxZ;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        var that = (DirectionShapeH) obj;
        var compareMinX = Double.doubleToLongBits(this.minX) == Double.doubleToLongBits(that.minX);
        var compareMinY = Double.doubleToLongBits(this.minY) == Double.doubleToLongBits(that.minY);
        var compareMinZ = Double.doubleToLongBits(this.minZ) == Double.doubleToLongBits(that.minZ);
        var compareMaxX = Double.doubleToLongBits(this.maxX) == Double.doubleToLongBits(that.maxX);
        var compareMaxY = Double.doubleToLongBits(this.maxY) == Double.doubleToLongBits(that.maxY);
        var compareMaxZ = Double.doubleToLongBits(this.maxZ) == Double.doubleToLongBits(that.maxZ);
        var compareMirrorMinZ = Double.doubleToLongBits(this.mirrorMinZ) == Double.doubleToLongBits(that.mirrorMinZ);
        var compareMirrorMaxZ = Double.doubleToLongBits(this.mirrorMaxZ) == Double.doubleToLongBits(that.mirrorMaxZ);

        return compareMinX && compareMinY && compareMinZ && compareMaxX && compareMaxY && compareMaxZ && compareMirrorMinZ && compareMirrorMaxZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minX, minY, minZ, maxX, maxY, maxZ, mirrorMinZ, mirrorMaxZ);
    }

    @Override
    public String toString() {
        return "DirectionSaeH["+"minX="+minX+", "+"minY=" +minY+", "+"minZ="+minZ+", "+"maxX="+maxX+","+"maxY=" +maxY+","+"maxZ="+ maxZ+']';
    }

}
