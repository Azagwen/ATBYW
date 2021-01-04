package net.azagwen.atbyw.blocks.statues;

import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class StatueVoxelShapes {
    public static final VoxelShape DEFAULT_OUTLINE = VoxelShapes.fullCube();
    public static final VoxelShape DEFAULT_COLLISIONS = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    public static final VoxelShape[] CHICKEN_OUTLINES;
    public static final VoxelShape[] CHICKEN_COLLISIONS;
    public static final VoxelShape[] WOLF_OUTLINES;
    public static final VoxelShape[] WOLF_COLLISIONS;
    public static final VoxelShape[] SILVERFISH_OUTLINES;
    public static final VoxelShape[] CAT_OUTLINES = null;
    public static final VoxelShape[] RABBIT_OUTLINES;
    public static final VoxelShape[] RABBIT_COLLISIONS;
    public static final VoxelShape[] ENDERMITE_OUTLINES = null;
    public static final VoxelShape[] BEE_OUTLINES = null;

    private static double invert(double i) {
        return -(i - 16);
    }

    private static VoxelShape[] makeDirectionalShapes(double xMin, double yMin, double zMin, double xMax, double yMax, double zMax) {
        double xMax2 = invert(zMax);
        double zMin2 = invert(zMin);

        VoxelShape NORTH = Block.createCuboidShape(xMin , yMin, zMin , xMax , yMax, zMax );
        VoxelShape SOUTH = Block.createCuboidShape(xMin , yMin, xMax2, xMax , yMax, zMin2);
        VoxelShape EAST  = Block.createCuboidShape(xMax2, yMin, xMin , zMin2, yMax, xMax );
        VoxelShape WEST  = Block.createCuboidShape(zMin , yMin, xMin , zMax , yMax, xMax );

        return new VoxelShape[] {NORTH, SOUTH, EAST, WEST};
    }

    static {
        SILVERFISH_OUTLINES = makeDirectionalShapes(4.5D, 0.0D, 0.0D, 11.5D, 4.5D, 16.0D);

        final VoxelShape CHICKEN_COLLISION_B = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
        final VoxelShape CHICKEN_COLLISION_T = Block.createCuboidShape(4.0D, 8.0D, 4.0D, 12.0D, 11.0D, 12.0D);
        final VoxelShape CHICKEN_COLLISION = VoxelShapes.union(CHICKEN_COLLISION_B, CHICKEN_COLLISION_T);
        CHICKEN_OUTLINES = makeDirectionalShapes(3.5D, 0.0D, 3.5D, 12.5D, 11.5D, 12.5D);
        CHICKEN_COLLISIONS = new VoxelShape[] {CHICKEN_COLLISION, CHICKEN_COLLISION, CHICKEN_COLLISION, CHICKEN_COLLISION};

        final VoxelShape[] WOLF_COLLISIONS_B = makeDirectionalShapes(3.0D, 0.0D, 0.0D, 13.0D,  8.0D, 16.0D);
        final VoxelShape[] WOLF_COLLISIONS_T = makeDirectionalShapes(4.0D, 0.0D, 1.0D, 12.0D, 13.0D, 15.0D);
        final VoxelShape WOLF_COLLISION_NORTH = VoxelShapes.union(WOLF_COLLISIONS_B[0], WOLF_COLLISIONS_T[0]);
        final VoxelShape WOLF_COLLISION_SOUTH = VoxelShapes.union(WOLF_COLLISIONS_B[1], WOLF_COLLISIONS_T[1]);
        final VoxelShape WOLF_COLLISION_EAST  = VoxelShapes.union(WOLF_COLLISIONS_B[2], WOLF_COLLISIONS_T[2]);
        final VoxelShape WOLF_COLLISION_WEST  = VoxelShapes.union(WOLF_COLLISIONS_B[3], WOLF_COLLISIONS_T[3]);
        WOLF_OUTLINES = makeDirectionalShapes(3.5D, 0.0D, 0.0D, 12.5D, 14.5D, 16.0D);
        WOLF_COLLISIONS = new VoxelShape[] {WOLF_COLLISION_NORTH, WOLF_COLLISION_SOUTH, WOLF_COLLISION_EAST, WOLF_COLLISION_WEST};

        final VoxelShape[] RABBIT_COLLISIONS_B = makeDirectionalShapes(2.5D, 0.0D, 1.5D, 13.0D,  8.0D, 15.5D);
        final VoxelShape[] RABBIT_COLLISIONS_T = makeDirectionalShapes(3.5D, 8.0D, 2.5D, 12.5D, 10.5D, 14.5D);
        final VoxelShape RABBIT_COLLISION_NORTH = VoxelShapes.union(RABBIT_COLLISIONS_B[0], RABBIT_COLLISIONS_T[0]);
        final VoxelShape RABBIT_COLLISION_SOUTH = VoxelShapes.union(RABBIT_COLLISIONS_B[1], RABBIT_COLLISIONS_T[1]);
        final VoxelShape RABBIT_COLLISION_EAST =  VoxelShapes.union(RABBIT_COLLISIONS_B[2], RABBIT_COLLISIONS_T[2]);
        final VoxelShape RABBIT_COLLISION_WEST =  VoxelShapes.union(RABBIT_COLLISIONS_B[3], RABBIT_COLLISIONS_T[3]);
        RABBIT_OUTLINES = makeDirectionalShapes(3.0D, 0.0D, 2.0D, 13.0D, 11.0D, 15.0D);
        RABBIT_COLLISIONS = new VoxelShape[] {RABBIT_COLLISION_NORTH, RABBIT_COLLISION_SOUTH, RABBIT_COLLISION_EAST, RABBIT_COLLISION_WEST};
    }
}
