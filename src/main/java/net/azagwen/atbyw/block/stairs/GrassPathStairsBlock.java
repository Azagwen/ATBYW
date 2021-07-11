package net.azagwen.atbyw.block.stairs;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.stream.IntStream;

public class GrassPathStairsBlock extends StairsBlockSubClass {
    private static final VoxelShape TOP_SHAPE;
    private static final VoxelShape TOP_NORTH_WEST_CORNER_SHAPE;
    private static final VoxelShape TOP_SOUTH_WEST_CORNER_SHAPE;
    private static final VoxelShape TOP_NORTH_EAST_CORNER_SHAPE;
    private static final VoxelShape TOP_SOUTH_EAST_CORNER_SHAPE;
    private static final VoxelShape BOTTOM_SHAPE;
    private static final VoxelShape BOTTOM_NORTH_WEST_CORNER_SHAPE;
    private static final VoxelShape BOTTOM_SOUTH_WEST_CORNER_SHAPE;
    private static final VoxelShape BOTTOM_NORTH_EAST_CORNER_SHAPE;
    private static final VoxelShape BOTTOM_SOUTH_EAST_CORNER_SHAPE;
    private static final VoxelShape[] TOP_SHAPES;
    private static final VoxelShape[] BOTTOM_SHAPES;
    private static final int[] SHAPE_INDICES;

    public GrassPathStairsBlock(Block copiedBlock, Settings settings) {
        super(copiedBlock, settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (state.get(HALF) == BlockHalf.TOP ? TOP_SHAPES : BOTTOM_SHAPES)[SHAPE_INDICES[this.getShapeIndexIndex(state)]];
    }

    private int getShapeIndexIndex(BlockState state) {
        return state.get(SHAPE).ordinal() * 4 + (state.get(FACING)).getHorizontal();
    }

    private static VoxelShape[] composeShapes(VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        return IntStream.range(0, 16).mapToObj((i) -> composeShape(i, base, northWest, northEast, southWest, southEast)).toArray(VoxelShape[]::new);
    }

    private static VoxelShape composeShape(int i, VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
        VoxelShape voxelShape = base;
        if ((i & 1) != 0) {
            voxelShape = VoxelShapes.union(base, northWest);
        }

        if ((i & 2) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, northEast);
        }

        if ((i & 4) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, southWest);
        }

        if ((i & 8) != 0) {
            voxelShape = VoxelShapes.union(voxelShape, southEast);
        }

        return voxelShape;
    }

    static {
        TOP_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 15.0D, 16.0D);
        BOTTOM_NORTH_WEST_CORNER_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D,  8.0D, 8.0D );
        BOTTOM_SOUTH_WEST_CORNER_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 8.0D,  8.0D, 16.0D);
        BOTTOM_NORTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D );
        BOTTOM_SOUTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);

        BOTTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
        TOP_NORTH_WEST_CORNER_SHAPE = Block.createCuboidShape(0.0D, 7.0D, 0.0D, 8.0D,  15.0D, 8.0D );
        TOP_SOUTH_WEST_CORNER_SHAPE = Block.createCuboidShape(0.0D, 7.0D, 8.0D, 8.0D,  15.0D, 16.0D);
        TOP_NORTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0D, 7.0D, 0.0D, 16.0D, 15.0D, 8.0D );
        TOP_SOUTH_EAST_CORNER_SHAPE = Block.createCuboidShape(8.0D, 7.0D, 8.0D, 16.0D, 15.0D, 16.0D);

        TOP_SHAPES = composeShapes(
                TOP_SHAPE,
                BOTTOM_NORTH_WEST_CORNER_SHAPE,
                BOTTOM_NORTH_EAST_CORNER_SHAPE,
                BOTTOM_SOUTH_WEST_CORNER_SHAPE,
                BOTTOM_SOUTH_EAST_CORNER_SHAPE
        );
        BOTTOM_SHAPES = composeShapes(
                BOTTOM_SHAPE,
                TOP_NORTH_WEST_CORNER_SHAPE,
                TOP_NORTH_EAST_CORNER_SHAPE,
                TOP_SOUTH_WEST_CORNER_SHAPE,
                TOP_SOUTH_EAST_CORNER_SHAPE
        );
        SHAPE_INDICES = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};
    }
}
