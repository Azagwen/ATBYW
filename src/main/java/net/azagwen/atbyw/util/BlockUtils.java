package net.azagwen.atbyw.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public record BlockUtils() {
    public static int NORTH = 0;
    public static int SOUTH = 1;
    public static int EAST = 2;
    public static int WEST = 3;

    /** Inverts the input value for use in VoxelShapes.
     *
     *  @param i Value to invert.
     *  @return  Inverted Value.
     */
    public static double invert(double i) {
        return -(i - 16);
    }

    /** Creates an array of 4 shapes, corresponding
     *  to the 4 directions statues can be in.
     *
     *  @return        Array of all 4 directions combined from the input arrays.
     */
    public static VoxelShape[] makeDirectionalShapes(double xMin, double yMin, double zMin, double xMax, double yMax, double zMax) {
        double xMax2 = invert(zMax);
        double zMin2 = invert(zMin);

        VoxelShape NORTH = Block.createCuboidShape(xMin , yMin, zMin , xMax , yMax, zMax );
        VoxelShape SOUTH = Block.createCuboidShape(xMin , yMin, xMax2, xMax , yMax, zMin2);
        VoxelShape EAST  = Block.createCuboidShape(xMax2, yMin, xMin , zMin2, yMax, xMax );
        VoxelShape WEST  = Block.createCuboidShape(zMin , yMin, xMin , zMax , yMax, xMax );

        return new VoxelShape[] {NORTH, SOUTH, EAST, WEST};
    }
}
