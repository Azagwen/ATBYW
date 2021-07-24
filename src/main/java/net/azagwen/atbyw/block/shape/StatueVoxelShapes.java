package net.azagwen.atbyw.block.shape;

import net.azagwen.atbyw.block.statues.StatueBlockMobType;
import net.minecraft.block.Block;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.*;

import static net.azagwen.atbyw.util.BlockUtils.makeDirectionalShapes;

public class StatueVoxelShapes {
    public static final VoxelShape DEFAULT_OUTLINE = VoxelShapes.fullCube();
    public static final VoxelShape DEFAULT_COLLISIONS = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    public static final VoxelShape[] BEE_OUTLINES;
    public static final VoxelShape[] BEE_COLLISIONS;
    public static final VoxelShape[] BAT_OUTLINES;
    public static final VoxelShape[] BAT_COLLISIONS;
    public static final VoxelShape[] SILVERFISH_OUTLINES;
    public static final VoxelShape[] ENDERMITE_OUTLINES;
    public static final VoxelShape[] SHULKER_OUTLINES;
    public static final VoxelShape[] SHULKER_COLLISIONS;
    public static final VoxelShape[] SHULKER_CLOSED_COLLISIONS;
    public static final VoxelShape[] AXOLOTL_OUTLINES;
    public static final VoxelShape[] WOLF_OUTLINES;
    public static final VoxelShape[] CAT_OUTLINES;
    public static final VoxelShape[] CHICKEN_OUTLINES;
    public static final VoxelShape[] RABBIT_OUTLINES;
    public static final VoxelShape[] RABBIT_COLLISIONS;
    public static final VoxelShape[] FOX_OUTLINES;
    public static final VoxelShape[] COD_OUTLINES;
    public static final VoxelShape[] SALMON_OUTLINES;
    public static final VoxelShape[] PUFFER_FISH_OUTLINES;
    public static final VoxelShape[] SLIME_SMALL_OUTLINES;

    /** Combines shapes from a double array of shapes into
     *  4 directional shapes for use with Statues.
     *
     *  @param shapes  Array containing arrays of directional shapes made using "makeDirectionalShapes()"
     *
     *  @return        Array of all 4 directions combined from the input arrays.
     */
    private static VoxelShape[] combineDoubleArrayShapes(VoxelShape[][] shapes) {
       VoxelShape NORTH = combineDoubleArrayShapes(shapes, StatueBlockMobType.NORTH);
       VoxelShape SOUTH = combineDoubleArrayShapes(shapes, StatueBlockMobType.SOUTH);
       VoxelShape EAST = combineDoubleArrayShapes(shapes, StatueBlockMobType.EAST);
       VoxelShape WEST = combineDoubleArrayShapes(shapes, StatueBlockMobType.WEST);

       return new VoxelShape[] {NORTH, SOUTH, EAST, WEST};
    }

    /** Combines Shapes of a given direction index into a single,
     *  united Shape using a modified implementation of VoxelShapes.union
     *
     *  @param shapes           Array containing arrays of directional shapes made using "makeDirectionalShapes()"
     *  @param directionIndex   Index of the direction picked for union (0 = NORTH, 1 = SOUTH, 2 = EAST, 3 = WEST)
     *
     *  @return                 Combination of every shape's selected direction from the double array
     */
    private static VoxelShape combineDoubleArrayShapes(VoxelShape[][] shapes, int directionIndex) {
        ArrayList<VoxelShape> combinedShapes = new ArrayList<>();

        for (VoxelShape[] shapeArray : shapes) {
            combinedShapes.add(shapeArray[directionIndex]);
        }

        Optional<VoxelShape> streamedShape = combinedShapes.stream().reduce(
                (v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)
        );

        if (streamedShape.isPresent()) {
            return streamedShape.get();
        } else {
            throw new IllegalArgumentException("Invalid value passed to combineDoubleArrayShapes()");
        }
    }

    static {

        //Pedestal shapes
        final VoxelShape[][] PEDESTAL_SHAPES = {
                makeDirectionalShapes(2.0D, 0.0D, 2.0D, 14.0D, 1.0D, 14.0D),    //Slab          0
                makeDirectionalShapes(6.5D, 1.0D, 6.5D,  9.5D, 2.0D,  9.5D),    //Rod Socket    1
                makeDirectionalShapes(7.5D, 2.0D, 7.5D,  8.5D, 8.0D,  8.5D)     //Rod           2
        };
        final VoxelShape[] PEDESTAL_SHAPE = combineDoubleArrayShapes(PEDESTAL_SHAPES);

        //Bat Shapes
        final VoxelShape[][] BAT_SHAPES = {
                makeDirectionalShapes(6.5D, 11.00D, 5.00D,  9.5D, 14.00D,   8.00D),   //Head        0
                makeDirectionalShapes(8.5D, 13.50D, 5.50D, 10.0D, 15.50D,   6.00D),   //Ear_Left    1
                makeDirectionalShapes(6.0D, 13.50D, 5.50D,  7.5D, 15.50D,   6.00D),   //Ear_Right   2
                makeDirectionalShapes(6.5D,  9.50D, 5.50D,  9.5D, 11.00D,   9.00D),   //Body_0      3
                makeDirectionalShapes(6.5D,  8.25D, 6.75D,  9.5D,  9.75D,  10.25D),   //Body_1      4
                makeDirectionalShapes(6.5D,  7.00D, 8.00D,  9.5D,  8.50D,  11.50D),   //Body_2      5
                PEDESTAL_SHAPE
        };
        BAT_OUTLINES = combineDoubleArrayShapes(BAT_SHAPES);
        BAT_COLLISIONS = combineDoubleArrayShapes(new VoxelShape[][] {BAT_SHAPES[0], BAT_SHAPES[3], BAT_SHAPES[4], BAT_SHAPES[5]});

        //Bee Shapes
        final VoxelShape[][] BEE_SHAPES = {
                makeDirectionalShapes(15.5D, 8.999D, 8.0D, 16.500D, 9.0D, 10.0D),   //Wing Right tail   0
                makeDirectionalShapes(14.5D, 8.999D, 7.0D, 15.500D, 9.0D, 11.0D),   //Wing Right middle 1
                makeDirectionalShapes(12.5D, 8.999D, 6.0D, 14.500D, 9.0D, 11.0D),   //Wing Right head   2
                makeDirectionalShapes( 3.5D, 8.999D, 5.0D, 12.500D, 9.0D, 10.0D),   //Wing Center Piece 3
                makeDirectionalShapes( 1.5D, 8.999D, 6.0D,  3.500D, 9.0D, 11.0D),   //Wing Left head    4
                makeDirectionalShapes( 0.5D, 8.999D, 7.0D,  1.500D, 9.0D, 11.0D),   //Wing Left middle  5
                makeDirectionalShapes(-0.5D, 8.999D, 8.0D,  0.500D, 9.0D, 10.0D),   //Wing Left tail    6
                makeDirectionalShapes( 9.5D, 7.000D, 2.0D,  9.504D, 8.0D, 3.0D),    //Antenna Right     7
                makeDirectionalShapes( 9.5D, 8.000D, 0.0D,  9.504D, 9.0D, 2.0D),    //Antenna Right     8
                makeDirectionalShapes( 6.5D, 7.000D, 2.0D,  6.504D, 8.0D, 3.0D),    //Antenna Left      9
                makeDirectionalShapes( 6.5D, 8.000D, 0.0D,  6.504D, 9.0D, 2.0D),    //Antenna Left      10
                makeDirectionalShapes(4.500D, 2.0D,  3.0D, 11.500D, 9.0D, 13.0D),   //Body              11
                makeDirectionalShapes(7.999D, 5.0D, 13.0D,  8.004D, 6.0D, 15.0D)    //Stinger           12
        };
        BEE_OUTLINES = combineDoubleArrayShapes(BEE_SHAPES);
        BEE_COLLISIONS = BEE_SHAPES[11];

        //Silverfish Outlines
        final VoxelShape[][] SILVERFISH_SHAPES = {
                makeDirectionalShapes(7.5D, 0.0D, 14.5D,  8.5D, 1.0D, 16.5D),
                makeDirectionalShapes(6.5D, 0.0D, -0.5D,  9.5D, 2.0D,  1.5D),
                makeDirectionalShapes(6.0D, 0.0D,  1.5D, 10.0D, 3.0D,  3.5D),
                makeDirectionalShapes(5.0D, 0.0D,  3.5D, 11.0D, 4.0D,  6.5D),
                makeDirectionalShapes(6.5D, 0.0D,  6.5D,  9.5D, 3.0D,  9.5D),
                makeDirectionalShapes(7.0D, 0.0D,  9.5D,  9.0D, 2.0D, 12.5D),
                makeDirectionalShapes(7.0D, 0.0D, 12.5D,  9.0D, 1.0D, 14.5D)
        };
        SILVERFISH_OUTLINES = combineDoubleArrayShapes(SILVERFISH_SHAPES);

        //Endermite Outlines
        final VoxelShape[][] ENDERMITE_SHAPES = {
                makeDirectionalShapes(7.5D, 0.0D, 11.5D,  8.5D, 2.0D, 12.5D),
                makeDirectionalShapes(6.0D, 0.0D,  3.5D, 10.0D, 3.0D,  5.5D),
                makeDirectionalShapes(5.0D, 0.0D,  5.5D, 11.0D, 4.0D, 10.5D),
                makeDirectionalShapes(6.5D, 0.0D, 10.5D,  9.5D, 3.0D, 11.5D)
        };
        ENDERMITE_OUTLINES = combineDoubleArrayShapes(ENDERMITE_SHAPES);

        SHULKER_OUTLINES = new VoxelShape[] {DEFAULT_OUTLINE, DEFAULT_OUTLINE, DEFAULT_OUTLINE, DEFAULT_OUTLINE};
        SHULKER_COLLISIONS = makeDirectionalShapes(0.0D, 0.0D, 0.0D, 16.0D, (16 + 5), 16.0D );
        SHULKER_CLOSED_COLLISIONS = SHULKER_OUTLINES;

        //Axolotl Outlines
        final VoxelShape[][] AXOLOTL_SHAPES = {
                makeDirectionalShapes(4.0D, 2.5D,  0.0D, 12.0D, 7.5D, 5.0D),   //Head              0
                makeDirectionalShapes(4.0D, 2.5D,  5.0D, 12.0D, 6.5D,  15.0D),  //Body              1
        };
        AXOLOTL_OUTLINES = combineDoubleArrayShapes(AXOLOTL_SHAPES);

        //Wolf Outlines
        final VoxelShape[][] WOLF_SHAPES = {
                makeDirectionalShapes(5.0D, 8.0D,  7.0D, 11.0D, 14.0D, 16.0D),  //Body              0
                makeDirectionalShapes(4.0D, 7.5D,  2.0D, 12.0D, 14.5D,  8.0D),  //Mane              1
                makeDirectionalShapes(5.5D, 0.0D,  2.0D,  7.5D,  8.0D,  4.0D),  //Leg Front Left    2
                makeDirectionalShapes(8.5D, 0.0D,  2.0D, 10.5D,  8.0D,  4.0D),  //Leg Front Right   3
                makeDirectionalShapes(5.5D, 0.0D, 13.0D,  7.5D,  8.0D, 15.0D),  //Leg Back Left     4
                makeDirectionalShapes(8.5D, 0.0D, 13.0D, 10.5D,  8.0D, 15.0D )  //Leg Back Right    5
        };
        WOLF_OUTLINES = combineDoubleArrayShapes(WOLF_SHAPES);

        //Cat Outlines
        final VoxelShape[][] CAT_SHAPES = {
                makeDirectionalShapes(6.0D, 4.0D,  0.0D, 10.0D, 10.0D, 16.0D),   //Body             0
                makeDirectionalShapes(8.1D, 0.0D, 13.0D, 10.1D,  6.0D, 15.0D),   //Leg Back Left    1
                makeDirectionalShapes(5.9D, 0.0D, 13.0D,  7.9D,  6.0D, 15.0D),   //Leg Back Right   2
                makeDirectionalShapes(8.2D, 0.0D,  2.0D, 10.2D, 10.0D,  4.0D),   //Leg Front Left   3
                makeDirectionalShapes(5.8D, 0.0D,  2.0D,  7.8D, 10.0D,  4.0D)    //Leg Front Right  4
        };
        CAT_OUTLINES = combineDoubleArrayShapes(CAT_SHAPES);

        //Chicken Outlines
        final VoxelShape[][] CHICKEN_SHAPES = {
                makeDirectionalShapes(5.0D, 5.0D, 4.0D, 11.0D, 11.0D, 12.0D),   //Body        0
                makeDirectionalShapes(4.0D, 7.0D, 5.0D, 12.0D, 11.0D, 11.0D),   //Wings       1
                makeDirectionalShapes(6.0D, 9.0D, 2.0D, 10.0D, 15.0D,  5.0D),   //Head        2
                makeDirectionalShapes(6.0D,11.0D, 0.0D, 10.0D, 13.0D,  2.0D),   //Beak        3
                makeDirectionalShapes(7.0D, 9.0D, 1.0D,  9.0D, 11.0D,  2.0D)    //Beak Flap   4
        };
        CHICKEN_OUTLINES = combineDoubleArrayShapes(CHICKEN_SHAPES);

        //Rabbit Shapes
        final VoxelShape[][] RABBIT_OUTLINE_SHAPES = {
                makeDirectionalShapes( 5.5D, 12.25D,  4.35D,  7.5D, 17.25D,  5.35),    //Ear u1      0
                makeDirectionalShapes( 8.5D, 12.25D,  4.35D, 10.5D, 17.25D,  5.35),    //Ear Right     1
                makeDirectionalShapes( 5.5D,  8.25D,  0.35D, 10.5D, 12.25D,  5.35),    //Head          2
                makeDirectionalShapes( 7.5D,  9.75D, -0.15D,  8.5D, 10.75D,  0.85),    //Nose          3
                makeDirectionalShapes(10.0D,  0.00D,  3.50D, 12.0D,  7.00D,  5.50),    //Arm Left      4
                makeDirectionalShapes( 4.0D,  0.00D,  3.50D,  6.0D,  7.00D,  5.50),    //Arm Right     5
                makeDirectionalShapes( 5.0D,  2.40D, 11.00D, 11.0D,  7.40D, 14.50),    //Body_0        6
                makeDirectionalShapes( 5.0D,  3.75D,  7.75D, 11.0D,  8.75D, 11.25),    //Body_1        7
                makeDirectionalShapes( 5.0D,  5.10D,  4.50D, 11.0D, 10.10D,  8.00),    //Body_2        8
                makeDirectionalShapes(10.0D,  0.00D,  6.00D, 12.0D,  1.00D, 13.00),    //Foot Left     9
                makeDirectionalShapes( 4.0D,  0.00D,  6.00D,  6.0D,  1.00D, 13.00)     //Foot Right    10
        };
        final VoxelShape[][] RABBIT_COLLISION_SHAPES = {
                RABBIT_OUTLINE_SHAPES[2],  RABBIT_OUTLINE_SHAPES[4],
                RABBIT_OUTLINE_SHAPES[5],  RABBIT_OUTLINE_SHAPES[6],
                RABBIT_OUTLINE_SHAPES[7],  RABBIT_OUTLINE_SHAPES[8],
                RABBIT_OUTLINE_SHAPES[9],  RABBIT_OUTLINE_SHAPES[10]
        };
        RABBIT_OUTLINES = combineDoubleArrayShapes(RABBIT_OUTLINE_SHAPES);
        RABBIT_COLLISIONS = combineDoubleArrayShapes(RABBIT_COLLISION_SHAPES);

        //Fox outlines
        final VoxelShape[][] FOX_SHAPES = {
                makeDirectionalShapes(5.0D, 4.5D,  2.5D, 11.0D, 10.5D, 13.5D),  //Body              0
                makeDirectionalShapes(5.0D, 0.0D,  3.5D,  7.0D,  4.5D,  5.5D),  //Leg Front Left    1
                makeDirectionalShapes(9.0D, 0.0D,  3.5D, 11.0D,  4.5D,  5.5D),  //Leg Front Right   2
                makeDirectionalShapes(5.0D, 0.0D, 10.5D,  7.0D,  4.5D, 12.5D),  //Leg Back Left     3
                makeDirectionalShapes(9.0D, 0.0D, 10.5D, 11.0D,  4.5D, 12.5D)   //Leg Back Right    4
        };
        FOX_OUTLINES = combineDoubleArrayShapes(FOX_SHAPES);

        //Cod Outlines
        final VoxelShape[][] COD_SHAPES = {
                makeDirectionalShapes(7.0D, 9.0D, 2.0D, 9.0D, 12.0D,  3.0D),
                makeDirectionalShapes(7.0D, 8.0D, 3.0D, 9.0D, 12.0D, 13.0D),
                PEDESTAL_SHAPE
        };
        COD_OUTLINES = combineDoubleArrayShapes(COD_SHAPES);

        //Salmon Outlines
        final VoxelShape[][] SALMON_SHAPES = {
                makeDirectionalShapes(6.5D, 8.0D, 0.0D,  9.5D, 13.0D, 16.0D),
                PEDESTAL_SHAPE
        };
        SALMON_OUTLINES = combineDoubleArrayShapes(SALMON_SHAPES);

        //Pufferfish Outlines
        final VoxelShape[][] PUFFER_FISH_SHAPES = {
                makeDirectionalShapes(3.5D, 6.5D, 3.5D,  12.5D, 15.5D, 12.5D),
                PEDESTAL_SHAPE
        };
        PUFFER_FISH_OUTLINES = combineDoubleArrayShapes(PUFFER_FISH_SHAPES);

        SLIME_SMALL_OUTLINES = makeDirectionalShapes(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    }
}
