package net.azagwen.atbyw.block;

import com.google.common.collect.ImmutableMap;
import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.*;
import net.minecraft.block.enums.WallShape;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.Map;

public class CinderBlocksWallBlock extends WallBlock {
    public static final BooleanProperty POST_SLAB;
    private static final VoxelShape POST_SHAPE;
    private static final VoxelShape POST_SLAB_SHAPE;
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
    private final Map<BlockState, VoxelShape> collisionShapeMap;
    private final Map<BlockState, VoxelShape> outlineShapeMap;


    public CinderBlocksWallBlock(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState().with(POST_SLAB, true));
        this.outlineShapeMap = this.getShapeMap(
                new VoxelShape[] {POST_SHAPE, POST_SLAB_SHAPE},
                new VoxelShape[] {LOW_NORTH_SHAPE, TALL_NORTH_SHAPE},
                new VoxelShape[] {LOW_EAST_SHAPE,  TALL_EAST_SHAPE },
                new VoxelShape[] {LOW_SOUTH_SHAPE, TALL_SOUTH_SHAPE},
                new VoxelShape[] {LOW_WEST_SHAPE,  TALL_WEST_SHAPE }
        );
        this.collisionShapeMap = this.getShapeMap(
                new VoxelShape[] {POST_COLLISION_SHAPE, POST_COLLISION_SHAPE},
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

    private Map<BlockState, VoxelShape> getShapeMap(VoxelShape[] post, VoxelShape[] north, VoxelShape[] east, VoxelShape[] south, VoxelShape[] west) {
        ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();

        /* Defines what shapes to use depending on "shape" block states.
        *  "shape" block states define the direction and height of the wall.
        */
        for (Boolean isUp : UP.getValues()) {
            for (Boolean hasSlab : POST_SLAB.getValues()) {
                for (WallShape eastShape : EAST_SHAPE.getValues()) {
                    for (WallShape northShape : NORTH_SHAPE.getValues()) {
                        for (WallShape westShape : WEST_SHAPE.getValues()) {
                            for (WallShape southShape : SOUTH_SHAPE.getValues()) {
                                VoxelShape SHAPE_NO_POST = VoxelShapes.empty();
                                VoxelShape SHAPE_SLAB_POST = VoxelShapes.union(post[0], post[1]);

                                SHAPE_NO_POST = isWallTall(SHAPE_NO_POST, eastShape, east[0], east[1]);
                                SHAPE_NO_POST = isWallTall(SHAPE_NO_POST, westShape, west[0], west[1]);
                                SHAPE_NO_POST = isWallTall(SHAPE_NO_POST, northShape, north[0], north[1]);
                                SHAPE_NO_POST = isWallTall(SHAPE_NO_POST, southShape, south[0], south[1]);
                                if (isUp) {
                                    if (hasSlab) {
                                        SHAPE_NO_POST = VoxelShapes.union(SHAPE_NO_POST, SHAPE_SLAB_POST);
                                    } else {
                                        SHAPE_NO_POST = VoxelShapes.union(SHAPE_NO_POST, post[0]);
                                    }
                                }

                                BlockState blockState = this.getDefaultState().with(UP, isUp).with(POST_SLAB, hasSlab).with(EAST_SHAPE, eastShape).with(WEST_SHAPE, westShape).with(NORTH_SHAPE, northShape).with(SOUTH_SHAPE, southShape);
                                builder.put(blockState.with(WATERLOGGED, false), SHAPE_NO_POST);
                                builder.put(blockState.with(WATERLOGGED, true), SHAPE_NO_POST);
                            }
                        }
                    }
                }
            }
        }

        return builder.build();
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var superState = super.getPlacementState(ctx);
        var world = ctx.getWorld();
        var pos = ctx.getBlockPos();
        return superState.with(POST_SLAB, world.getBlockState(pos.up()).canReplace(ctx));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return outlineShapeMap.get(state);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.collisionShapeMap.get(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        var superState = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        return superState.with(POST_SLAB, world.getBlockState(pos.up()).getMaterial().isReplaceable());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(POST_SLAB);
    }

    static {
        POST_SLAB = AtbywProperties.POST_SLAB;

        /* Base values for each wall element
        *  those values are passed to the individual directions shapes below,
        *  allowing for fast edition fo all 4 sides from a small set of numbers
        */
        double[] postValues =      {4.0D, 0.0D , 12.0D, 16.0D};
        double[] slabValuesN =     {4.0D, 12.0D, 0.0D , 12.0D, 14.0D, 8.0D };
        double[] slabValuesP =     {4.0D, 12.0D, 8.0D , 12.0D, 14.0D, 16.0D};
        double[] lowSideValuesN =  {5.0D, 0.0D , 0.0D , 11.0D, 12.0D, 8.0D };
        double[] lowSideValuesP =  {5.0D, 0.0D , 8.0D , 11.0D, 12.0D, 16.0D};
        double[] tallSideValuesN = {5.0D, 0.0D , 0.0D , 11.0D, 16.0D, 8.0D };
        double[] tallSideValuesP = {5.0D, 0.0D , 8.0D , 11.0D, 16.0D, 16.0D};

        /* Creating every needed cuboid shapes using the values above,
        *  for both collision and outlines
        */
        POST_SHAPE =       Block.createCuboidShape(postValues[0], postValues[1], postValues[0], postValues[2], postValues[3], postValues[2]);
        POST_SLAB_SHAPE =  Block.createCuboidShape(3.0D, 16.0D, 3.0D , 13.0D, 18.0D, 13.0D);

        SLAB_NORTH_SHAPE = Block.createCuboidShape(slabValuesN[0], slabValuesN[1], slabValuesN[2], slabValuesN[3], slabValuesN[4], slabValuesN[5]);
        SLAB_SOUTH_SHAPE = Block.createCuboidShape(slabValuesP[0], slabValuesP[1], slabValuesP[2], slabValuesP[3], slabValuesP[4], slabValuesP[5]);
        SLAB_WEST_SHAPE =  Block.createCuboidShape(slabValuesN[2], slabValuesN[1], slabValuesN[0], slabValuesN[5], slabValuesN[4], slabValuesN[3]);
        SLAB_EAST_SHAPE =  Block.createCuboidShape(slabValuesP[2], slabValuesP[1], slabValuesP[0], slabValuesP[5], slabValuesP[4], slabValuesP[3]);
        LOW_NORTH_SHAPE = VoxelShapes.union(SLAB_NORTH_SHAPE, Block.createCuboidShape(lowSideValuesN[0], lowSideValuesN[1], lowSideValuesN[2], lowSideValuesN[3], lowSideValuesN[4], lowSideValuesN[5]));
        LOW_SOUTH_SHAPE = VoxelShapes.union(SLAB_SOUTH_SHAPE, Block.createCuboidShape(lowSideValuesP[0], lowSideValuesP[1], lowSideValuesP[2], lowSideValuesP[3], lowSideValuesP[4], lowSideValuesP[5]));
        LOW_WEST_SHAPE =  VoxelShapes.union(SLAB_WEST_SHAPE , Block.createCuboidShape(lowSideValuesN[2], lowSideValuesN[1], lowSideValuesN[0], lowSideValuesN[5], lowSideValuesN[4], lowSideValuesN[3]));
        LOW_EAST_SHAPE =  VoxelShapes.union(SLAB_EAST_SHAPE , Block.createCuboidShape(lowSideValuesP[2], lowSideValuesP[1], lowSideValuesP[0], lowSideValuesP[5], lowSideValuesP[4], lowSideValuesP[3]));
        TALL_NORTH_SHAPE = Block.createCuboidShape(tallSideValuesN[0], tallSideValuesN[1], tallSideValuesN[2], tallSideValuesN[3], tallSideValuesN[4], tallSideValuesN[5]);
        TALL_SOUTH_SHAPE = Block.createCuboidShape(tallSideValuesP[0], tallSideValuesP[1], tallSideValuesP[2], tallSideValuesP[3], tallSideValuesP[4], tallSideValuesP[5]);
        TALL_WEST_SHAPE =  Block.createCuboidShape(tallSideValuesN[2], tallSideValuesN[1], tallSideValuesN[0], tallSideValuesN[5], tallSideValuesN[4], tallSideValuesN[3]);
        TALL_EAST_SHAPE =  Block.createCuboidShape(tallSideValuesP[2], tallSideValuesP[1], tallSideValuesP[0], tallSideValuesP[5], tallSideValuesP[4], tallSideValuesP[3]);

        POST_COLLISION_SHAPE =       Block.createCuboidShape(postValues[0], postValues[1], postValues[0], postValues[2], 24.0D, postValues[2]);
        LOW_NORTH_COLLISION_SHAPE =  Block.createCuboidShape(lowSideValuesN[0] , lowSideValuesN[1] , lowSideValuesN[2] , lowSideValuesN[3] , 24.0D, lowSideValuesN[5] );
        LOW_SOUTH_COLLISION_SHAPE =  Block.createCuboidShape(lowSideValuesP[0] , lowSideValuesP[1] , lowSideValuesP[2] , lowSideValuesP[3] , 24.0D, lowSideValuesP[5] );
        LOW_WEST_COLLISION_SHAPE =   Block.createCuboidShape(lowSideValuesN[2] , lowSideValuesN[1] , lowSideValuesN[0] , lowSideValuesN[5] , 24.0D, lowSideValuesN[3] );
        LOW_EAST_COLLISION_SHAPE =   Block.createCuboidShape(lowSideValuesP[2] , lowSideValuesP[1] , lowSideValuesP[0] , lowSideValuesP[5] , 24.0D, lowSideValuesP[3] );
        TALL_NORTH_COLLISION_SHAPE = Block.createCuboidShape(tallSideValuesN[0], tallSideValuesN[1], tallSideValuesN[2], tallSideValuesN[3], 24.0D, tallSideValuesN[5]);
        TALL_SOUTH_COLLISION_SHAPE = Block.createCuboidShape(tallSideValuesP[0], tallSideValuesP[1], tallSideValuesP[2], tallSideValuesP[3], 24.0D, tallSideValuesP[5]);
        TALL_WEST_COLLISION_SHAPE =  Block.createCuboidShape(tallSideValuesN[2], tallSideValuesN[1], tallSideValuesN[0], tallSideValuesN[5], 24.0D, tallSideValuesN[3]);
        TALL_EAST_COLLISION_SHAPE =  Block.createCuboidShape(tallSideValuesP[2], tallSideValuesP[1], tallSideValuesP[0], tallSideValuesP[5], 24.0D, tallSideValuesP[3]);

    }
}
