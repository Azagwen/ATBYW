package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.shape.AxisShape;
import net.azagwen.atbyw.block.shape.HorizontalShape;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class RedstonePipeBlock extends Block {
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final VoxelShape JOINT_SHAPE;
    public static final Map<Direction, VoxelShape> SHAPES;
    public static final Map<Direction, BooleanProperty> STATES;

    public RedstonePipeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState().with(UP, false).with(DOWN, false).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        var world = ctx.getWorld();
        var pos = ctx.getBlockPos();
        return this.tryConnect(world, pos);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return this.tryConnect(world, pos);
    }

    private static boolean isFullyConnected(BlockState state) {
        return state.get(NORTH) && state.get(SOUTH) && state.get(EAST) && state.get(WEST);
    }

    private static boolean isNotConnected(BlockState state) {
        return !state.get(NORTH) && !state.get(SOUTH) && !state.get(EAST) && !state.get(WEST);
    }

    public BlockState tryConnect(WorldAccess world, BlockPos pos) {
        var state = this.getDefaultState();

        for (var direction : Direction.values()) {
            for (var blockState : STATES.entrySet()) {
                if (blockState.getKey() == direction) {
                    var isSelf = this.checkSelf(direction, world, pos);
                    var canEmitsPower = this.checkEmitsPower(direction, world, pos);
                    var isFullSquare = this.checkFullSquare(direction, world, pos);

                    state = state.with(blockState.getValue(), (isSelf || (canEmitsPower && isFullSquare)));
                }
            }
        }
        return connectToSelf(world, pos, state);
    }

    public BlockState connectToSelf(WorldAccess world, BlockPos pos, BlockState state) {
        var up = this.checkSelf(Direction.UP, world, pos) || this.checkFullSquareAndPower(Direction.UP, world, pos);
        var down = this.checkSelf(Direction.DOWN, world, pos) || this.checkFullSquareAndPower(Direction.DOWN, world, pos);
        var north = this.checkSelf(Direction.NORTH, world, pos) || this.checkFullSquareAndPower(Direction.NORTH, world, pos);
        var east = this.checkSelf(Direction.EAST, world, pos) || this.checkFullSquareAndPower(Direction.EAST, world, pos);
        var south = this.checkSelf(Direction.SOUTH, world, pos) || this.checkFullSquareAndPower(Direction.SOUTH, world, pos);
        var west = this.checkSelf(Direction.WEST, world, pos) || this.checkFullSquareAndPower(Direction.WEST, world, pos);
        var notNorthSouth = !north && !south;
        var notEastWest = !east && !west;
        var notUpDown = !up && !down;

        if (!up && notEastWest && notNorthSouth)
            state = state.with(UP, true);
        if (!down && notEastWest && notNorthSouth)
            state = state.with(DOWN, true);
        if (!west && notNorthSouth && notUpDown)
            state = state.with(WEST, true);
        if (!east && notNorthSouth && notUpDown)
            state = state.with(EAST, true);
        if (!north && notEastWest && notUpDown)
            state = state.with(NORTH, true);
        if (!south && notEastWest && notUpDown)
            state = state.with(SOUTH, true);

        return state;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
       var northSouth = (state.get(NORTH) || state.get(SOUTH)) && !(state.get(NORTH) && state.get(SOUTH));
       var eastWest = (state.get(EAST) || state.get(WEST)) && !(state.get(EAST) && state.get(WEST));
       var upDown = (state.get(UP) || state.get(DOWN)) && !(state.get(UP) && state.get(DOWN));
       var hasJoint = (northSouth && eastWest) || (eastWest && upDown) || (northSouth && upDown);

        var jointShape = (hasJoint ? JOINT_SHAPE : VoxelShapes.empty());
        for (var blockState : STATES.entrySet()) {
            for (var dir : Direction.values()) {
                if (blockState.getKey() == dir) {
                    jointShape = VoxelShapes.union(jointShape, state.get(STATES.get(dir)) ? SHAPES.get(dir) : VoxelShapes.empty());
                }
            }
        }

        return jointShape;
    }

    public boolean checkSelf(Direction direction, WorldAccess world, BlockPos pos) {
        var offset = pos.offset(direction);
        return world.getBlockState(offset).getBlock() instanceof RedstonePipeBlock;
    }

    public boolean checkEmitsPower(Direction direction, WorldAccess world, BlockPos pos) {
        var offset = pos.offset(direction);
        return world.getBlockState(offset).emitsRedstonePower();
    }

    public boolean checkFullSquare(Direction direction, WorldAccess world, BlockPos pos) {
        var offset = pos.offset(direction);
        return world.getBlockState(offset).isSideSolidFullSquare(world, offset, direction.getOpposite());
    }

    public boolean checkFullSquareAndPower(Direction direction, WorldAccess world, BlockPos pos) {
        return this.checkFullSquare(direction, world, pos) && this.checkEmitsPower(direction, world, pos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
    }

    static {
        UP = Properties.UP;
        DOWN = Properties.DOWN;
        NORTH = Properties.NORTH;
        EAST = Properties.EAST;
        SOUTH = Properties.SOUTH;
        WEST = Properties.WEST;

        var hs = new HorizontalShape(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 8.0D);
        var vs = new AxisShape(5.0D, 0.0D, 11.0D, 8.0D);
        SHAPES = Map.ofEntries(
                Map.entry(Direction.UP, vs.yShape(false)),
                Map.entry(Direction.DOWN, vs.yShape(true)),
                Map.entry(Direction.NORTH, hs.northShape()),
                Map.entry(Direction.SOUTH, hs.southShape()),
                Map.entry(Direction.EAST, hs.eastShape()),
                Map.entry(Direction.WEST, hs.westShape())
        );
        STATES = Map.ofEntries(
                Map.entry(Direction.UP, UP),
                Map.entry(Direction.DOWN, DOWN),
                Map.entry(Direction.NORTH, NORTH),
                Map.entry(Direction.SOUTH, SOUTH),
                Map.entry(Direction.EAST, EAST),
                Map.entry(Direction.WEST, WEST)
        );
        JOINT_SHAPE = Block.createCuboidShape(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);
    }
}
