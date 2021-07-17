package net.azagwen.atbyw.block;

import com.google.common.collect.Sets;
import net.azagwen.atbyw.block.shape.AxisShape;
import net.azagwen.atbyw.block.shape.HorizontalShape;
import net.azagwen.atbyw.block.state.Connector;
import net.azagwen.atbyw.util.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.WireConnection;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Iterator;
import java.util.Map;

public class RedstonePipeBlock extends Block {
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final IntProperty POWER;
    public static final VoxelShape JOINT_SHAPE;
    public static final Map<Direction, VoxelShape> SHAPES;
    public static final Map<Direction, BooleanProperty> STATES;
    private static final Color[] COLORS;
    private boolean pipesGivePower = true;

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

    public BlockState tryConnect(BlockView world, BlockPos pos) {
        var state = this.getDefaultState();
        var connector = new Connector();

        for (var direction : Direction.values()) {
            var checkSelf = this.checkSelf(direction, world, pos);
            var checkEmitsPower = BlockUtils.checkEmitsPower(direction, world, pos);
            var checkFullSquare = BlockUtils.checkFullSquare(direction, world, pos);
            var checkAll = (checkSelf || (checkEmitsPower && checkFullSquare));
            for (var blockState : STATES.entrySet()) {
                if (blockState.getKey() == direction) {
                    state = state.with(blockState.getValue(), checkAll);
                }
            }
            connector.setFromDirection(direction, checkAll);
        }
        var notNorthSouth = !connector.north() && !connector.south();
        var notEastWest = !connector.east() && !connector.west();
        var notUpDown = !connector.up() && !connector.down();

        if (!connector.up() && notEastWest && notNorthSouth)
            state = state.with(UP, true);
        if (!connector.down() && notEastWest && notNorthSouth)
            state = state.with(DOWN, true);
        if (!connector.west() && notNorthSouth && notUpDown)
            state = state.with(WEST, true);
        if (!connector.east() && notNorthSouth && notUpDown)
            state = state.with(EAST, true);
        if (!connector.north() && notEastWest && notUpDown)
            state = state.with(NORTH, true);
        if (!connector.south() && notEastWest && notUpDown)
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

    public boolean checkSelf(Direction direction, BlockView world, BlockPos pos) {
        var offset = pos.offset(direction);
        return world.getBlockState(offset).getBlock() instanceof RedstonePipeBlock;
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock()) && !world.isClient) {
            this.update(world, pos, state);
            Iterator var6 = Direction.Type.VERTICAL.iterator();

            while(var6.hasNext()) {
                Direction direction = (Direction)var6.next();
                world.updateNeighborsAlways(pos.offset(direction), this);
            }

            this.updateOffsetNeighbors(world, pos);
        }
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!moved && !state.isOf(newState.getBlock())) {
            super.onStateReplaced(state, world, pos, newState, moved);
            if (!world.isClient) {
                for (Direction direction : Direction.values()) {
                    world.updateNeighborsAlways(pos.offset(direction), this);
                }

                this.update(world, pos, state);
                this.updateOffsetNeighbors(world, pos);
            }
        }
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            this.update(world, pos, state);
        }
    }

    private void updateNeighbors(World world, BlockPos pos) {
        if (world.getBlockState(pos).isOf(this)) {
            world.updateNeighborsAlways(pos, this);

            for (Direction direction : Direction.values()) {
                world.updateNeighborsAlways(pos.offset(direction), this);
            }

        }
    }

    private void updateOffsetNeighbors(World world, BlockPos pos) {
        for (var dir : Direction.values()) {
            this.updateNeighbors(world, pos.offset(dir));

            var blockPos = pos.offset(dir);
            if (world.getBlockState(blockPos).isSolidBlock(world, blockPos)) {
                this.updateNeighbors(world, blockPos.up());
            } else {
                this.updateNeighbors(world, blockPos.down());
            }
        }
    }

    private void update(World world, BlockPos pos, BlockState state) {
        int i = this.getReceivedRedstonePower(world, pos);
        if (state.get(POWER) != i) {
            if (world.getBlockState(pos) == state) {
                world.setBlockState(pos, state.with(POWER, i), 2);
            }

            var set = Sets.<BlockPos>newHashSet();
            set.add(pos);
            for (Direction direction : Direction.values()) {
                set.add(pos.offset(direction));
            }

            for (BlockPos blockPos : set) {
                world.updateNeighborsAlways(blockPos, this);
            }
        }
    }

    private int getReceivedRedstonePower(World world, BlockPos pos) {
        this.pipesGivePower = false;
        int i = world.getReceivedRedstonePower(pos);
        this.pipesGivePower = true;
        int j = 0;
        if (i < 15) {
            while(true) {
                for (var direction : Direction.values()) {
                    var blockPos = pos.offset(direction);
                    var blockState = world.getBlockState(blockPos);
                    j = Math.max(j, this.increasePower(blockState));
                    var up = pos.up();
                    if (blockState.isSolidBlock(world, blockPos) && !world.getBlockState(up).isSolidBlock(world, up)) {
                        j = Math.max(j, this.increasePower(world.getBlockState(blockPos.up())));
                    } else if (!blockState.isSolidBlock(world, blockPos)) {
                        j = Math.max(j, this.increasePower(world.getBlockState(blockPos.down())));
                    }
                }
                return Math.max(i, j - 1);
            }
        } else {
            return Math.max(i, j - 1);
        }
    }

    private int increasePower(BlockState state) {
        return state.isOf(this) ? state.get(POWER) : 0;
    }

    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return !this.pipesGivePower ? 0 : state.getWeakRedstonePower(world, pos, direction);
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (this.pipesGivePower && direction != Direction.DOWN) {
            int i = state.get(POWER);
            if (i == 0) {
                return 0;
            } else {
                return direction != Direction.UP && !(this.tryConnect(world, pos).get(STATES.get(direction.getOpposite()))) ? 0 : i;
            }
        } else {
            return 0;
        }
    }

    public static int getRedstoneColor(int powerLevel) {
        return COLORS[powerLevel].getRGB();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, POWER);
    }

    static {
        UP = Properties.UP;
        DOWN = Properties.DOWN;
        NORTH = Properties.NORTH;
        EAST = Properties.EAST;
        SOUTH = Properties.SOUTH;
        WEST = Properties.WEST;
        POWER = Properties.POWER;

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
        COLORS = Util.make(new Color[16], (Colors) -> {
            for(int i = 0; i <= 15; ++i) {
                var factor = (int)((i / 15.0F) * 255);
                var r = factor;
                var g = factor / 4;
                var b = factor / 4;
                Colors[i] = new Color(r, g, b);
            }
        });
    }
}
