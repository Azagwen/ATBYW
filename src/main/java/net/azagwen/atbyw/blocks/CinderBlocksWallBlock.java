package net.azagwen.atbyw.blocks;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.WallShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Iterator;
import java.util.Map;

public class CinderBlocksWallBlock extends WallBlock {
    private static final VoxelShape POST_SHAPE;
    private static final VoxelShape SLAB_NORTH_SHAPE;
    private static final VoxelShape SLAB_SOUTH_SHAPE;
    private static final VoxelShape SLAB_WEST_SHAPE;
    private static final VoxelShape SLAB_EAST_SHAPE;
    private static final VoxelShape LOW_NORTH_SHAPE;
    private static final VoxelShape LOW_SOUTH_SHAPE;
    private static final VoxelShape LOW_WEST_SHAPE;
    private static final VoxelShape LOW_EAST_SHAPE;
    private static final VoxelShape TALL_NORTH_SHAPE;
    private static final VoxelShape TALL_SOUTH_SHAPE;
    private static final VoxelShape TALL_WEST_SHAPE;
    private static final VoxelShape TALL_EAST_SHAPE;
    private static final VoxelShape POST_COLLISION_SHAPE;
    private static final VoxelShape LOW_NORTH_COLLISION_SHAPE;
    private static final VoxelShape LOW_SOUTH_COLLISION_SHAPE;
    private static final VoxelShape LOW_WEST_COLLISION_SHAPE;
    private static final VoxelShape LOW_EAST_COLLISION_SHAPE;
    private static final VoxelShape TALL_NORTH_COLLISION_SHAPE;
    private static final VoxelShape TALL_SOUTH_COLLISION_SHAPE;
    private static final VoxelShape TALL_WEST_COLLISION_SHAPE;
    private static final VoxelShape TALL_EAST_COLLISION_SHAPE;
    private final Map<BlockState, VoxelShape> shapeMap;
    private final Map<BlockState, VoxelShape> collisionShapeMap;

    public CinderBlocksWallBlock(Settings settings) {
        super(settings);
        this.shapeMap = this.getShapeMap(
                POST_SHAPE,
                new VoxelShape[] {LOW_NORTH_SHAPE, TALL_NORTH_SHAPE},
                new VoxelShape[] {LOW_EAST_SHAPE,  TALL_EAST_SHAPE },
                new VoxelShape[] {LOW_SOUTH_SHAPE, TALL_SOUTH_SHAPE},
                new VoxelShape[] {LOW_WEST_SHAPE,  TALL_WEST_SHAPE }
                );
        this.collisionShapeMap = this.getShapeMap(
                POST_COLLISION_SHAPE,
                new VoxelShape[] {LOW_NORTH_COLLISION_SHAPE, TALL_NORTH_COLLISION_SHAPE},
                new VoxelShape[] {LOW_EAST_COLLISION_SHAPE,  TALL_EAST_COLLISION_SHAPE },
                new VoxelShape[] {LOW_SOUTH_COLLISION_SHAPE, TALL_SOUTH_COLLISION_SHAPE},
                new VoxelShape[] {LOW_WEST_COLLISION_SHAPE,  TALL_WEST_COLLISION_SHAPE }
        );
    }

    private static VoxelShape isWallTall(VoxelShape postShape, WallShape wallShape, VoxelShape lowSideShape, VoxelShape tallSideShape) {
        if (wallShape == WallShape.TALL) {
            return VoxelShapes.union(postShape, tallSideShape);
        } else {
            return wallShape == WallShape.LOW ? VoxelShapes.union(postShape, lowSideShape) : postShape;
        }
    }

    private Map<BlockState, VoxelShape> getShapeMap(VoxelShape post, VoxelShape[] north, VoxelShape[] east, VoxelShape[] south, VoxelShape[] west) {
        ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();
        Iterator upIterator = UP.getValues().iterator();

        while(upIterator.hasNext()) {
            Boolean isUp = (Boolean)upIterator.next();
            Iterator eastShapeIterator = EAST_SHAPE.getValues().iterator();

            while(eastShapeIterator.hasNext()) {
                WallShape eastShape = (WallShape)eastShapeIterator.next();
                Iterator northShapeIterator = NORTH_SHAPE.getValues().iterator();

                while(northShapeIterator.hasNext()) {
                    WallShape northShape = (WallShape)northShapeIterator.next();
                    Iterator westShapeIterator = WEST_SHAPE.getValues().iterator();

                    while(westShapeIterator.hasNext()) {
                        WallShape westShape = (WallShape)westShapeIterator.next();
                        Iterator southShapeIterator = SOUTH_SHAPE.getValues().iterator();

                        while(southShapeIterator.hasNext()) {
                            WallShape southShape = (WallShape)southShapeIterator.next();
                            VoxelShape SHAPE_NO_POST = VoxelShapes.empty();
                            SHAPE_NO_POST = isWallTall(SHAPE_NO_POST, eastShape, east[0], east[1]);
                            SHAPE_NO_POST = isWallTall(SHAPE_NO_POST, westShape, west[0], west[1]);
                            SHAPE_NO_POST = isWallTall(SHAPE_NO_POST, northShape, north[0], north[1]);
                            SHAPE_NO_POST = isWallTall(SHAPE_NO_POST, southShape, south[0], south[1]);
                            if (isUp) {
                                SHAPE_NO_POST = VoxelShapes.union(SHAPE_NO_POST, post);
                            }

                            BlockState blockState = this.getDefaultState().with(UP, isUp).with(EAST_SHAPE, eastShape).with(WEST_SHAPE, westShape).with(NORTH_SHAPE, northShape).with(SOUTH_SHAPE, southShape);
                            builder.put(blockState.with(WATERLOGGED, false), SHAPE_NO_POST);
                            builder.put(blockState.with(WATERLOGGED, true), SHAPE_NO_POST);
                        }
                    }
                }
            }
        }

        return builder.build();
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.shapeMap.get(state);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.collisionShapeMap.get(state);
    }

    static {
        POST_SHAPE = createCuboidShape( 2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
        SLAB_NORTH_SHAPE = Block.createCuboidShape(2.5D, 10.0D, 0.0D, 13.5D, 15.0D, 8.0D);
        SLAB_SOUTH_SHAPE = Block.createCuboidShape(2.5D, 10.0D, 8.0D, 13.5D, 15.0D, 16.0D);
        SLAB_WEST_SHAPE =  Block.createCuboidShape(0.0D, 10.0D, 2.5D, 8.0D,  15.0D, 13.5D);
        SLAB_EAST_SHAPE =  Block.createCuboidShape(8.0D, 10.0D, 2.5D, 16.0D, 15.0D, 13.5D);
        LOW_NORTH_SHAPE = VoxelShapes.union(Block.createCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 13.0D, 8.0D ), SLAB_NORTH_SHAPE);
        LOW_SOUTH_SHAPE = VoxelShapes.union(Block.createCuboidShape(5.0D, 0.0D, 8.0D, 11.0D, 13.0D, 16.0D), SLAB_SOUTH_SHAPE);
        LOW_WEST_SHAPE =  VoxelShapes.union(Block.createCuboidShape(0.0D, 0.0D, 5.0D, 8.0D,  13.0D, 11.0D), SLAB_WEST_SHAPE);
        LOW_EAST_SHAPE =  VoxelShapes.union(Block.createCuboidShape(8.0D, 0.0D, 5.0D, 16.0D, 13.0D, 11.0D), SLAB_EAST_SHAPE);
        TALL_NORTH_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 16.0D, 8.0D);
        TALL_SOUTH_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 8.0D, 11.0D, 16.0D, 16.0D);
        TALL_WEST_SHAPE =  Block.createCuboidShape(0.0D, 0.0D, 5.0D, 8.0D,  16.0D, 11.0D);
        TALL_EAST_SHAPE =  Block.createCuboidShape(8.0D, 0.0D, 5.0D, 16.0D, 16.0D, 11.0D);

        POST_COLLISION_SHAPE = createCuboidShape( 2.0D, 0.0D, 2.0D, 14.0D, 24.0D, 14.0D);
        LOW_NORTH_COLLISION_SHAPE =  Block.createCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 24.0D, 8.0D);
        LOW_SOUTH_COLLISION_SHAPE =  Block.createCuboidShape(5.0D, 0.0D, 8.0D, 11.0D, 24.0D, 16.0D);
        LOW_WEST_COLLISION_SHAPE =   Block.createCuboidShape(0.0D, 0.0D, 5.0D, 8.0D,  24.0D, 11.0D);
        LOW_EAST_COLLISION_SHAPE =   Block.createCuboidShape(8.0D, 0.0D, 5.0D, 16.0D, 24.0D, 11.0D);
        TALL_NORTH_COLLISION_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 24.0D, 8.0D);
        TALL_SOUTH_COLLISION_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 8.0D, 11.0D, 24.0D, 16.0D);
        TALL_WEST_COLLISION_SHAPE =  Block.createCuboidShape(0.0D, 0.0D, 5.0D, 8.0D,  24.0D, 11.0D);
        TALL_EAST_COLLISION_SHAPE =  Block.createCuboidShape(8.0D, 0.0D, 5.0D, 16.0D, 24.0D, 11.0D);

    }
}
