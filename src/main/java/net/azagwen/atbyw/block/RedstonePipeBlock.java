package net.azagwen.atbyw.block;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.azagwen.atbyw.block.shape.AxisShape;
import net.azagwen.atbyw.block.state.Connector;
import net.azagwen.atbyw.main.Tags.BlockTags;
import net.azagwen.atbyw.util.BlockUtils;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("deprecation")
public class RedstonePipeBlock extends Block implements Waterloggable, RedstonePipeComponent {
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final IntProperty POWER;
    public static final BooleanProperty WATERLOGGED;
    public static final VoxelShape JOINT_SHAPE;
    public static final Map<Direction, VoxelShape> SHAPES;
    public static final Map<Direction, BooleanProperty> STATES;
    public static final Map<Integer, Integer> COLORS;
    private boolean pipesGivePower = true;

    public RedstonePipeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState().with(UP, false).with(DOWN, false).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(POWER, 0).with(WATERLOGGED, false));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        var world = ctx.getWorld();
        var pos = ctx.getBlockPos();
        var state = this.getDefaultState();
        return this.tryConnect(world, pos, state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return this.tryConnect(world, pos, state);
    }

    public BlockState tryConnect(BlockView world, BlockPos pos, BlockState state) {
        var connector = new Connector();
        var newState = this.getDefaultState();
        newState = newState.with(POWER, state.get(POWER));
        newState = newState.with(WATERLOGGED, world.getFluidState(pos).getFluid() == Fluids.WATER);

        for (var direction : Direction.values()) {
            var checkCanConnect = this.canConnect(direction, world, pos);
            for (var blockState : STATES.entrySet()) {
                if (blockState.getKey() == direction) {
                    newState = newState.with(blockState.getValue(), checkCanConnect);
                }
            }
            connector.setFromDirection(direction, checkCanConnect);
        }
        return this.setStatesFromConnector(newState, connector);
    }

    private BlockState setStatesFromConnector(BlockState inputState, Connector connector) {
        var notNorthSouth = !connector.north() && !connector.south();
        var notEastWest = !connector.east() && !connector.west();
        var notUpDown = !connector.up() && !connector.down();

        if (!connector.up() && notEastWest && notNorthSouth)
            inputState = inputState.with(UP, true);
        if (!connector.down() && notEastWest && notNorthSouth)
            inputState = inputState.with(DOWN, true);
        if (!connector.west() && notNorthSouth && notUpDown)
            inputState = inputState.with(WEST, true);
        if (!connector.east() && notNorthSouth && notUpDown)
            inputState = inputState.with(EAST, true);
        if (!connector.north() && notEastWest && notUpDown)
            inputState = inputState.with(NORTH, true);
        if (!connector.south() && notEastWest && notUpDown)
            inputState = inputState.with(SOUTH, true);

        return inputState;
    }

    protected boolean canConnect(Direction direction, BlockView world, BlockPos pos) {
        var state = world.getBlockState(pos.offset(direction));
        var checkEmitsPower = BlockUtils.checkEmitsPower(direction, world, pos);
        var checkFullSquare = BlockUtils.checkFullSquare(direction, world, pos);

        if (state.getBlock() instanceof RedstonePipeComponent component) {
            switch (component.getType()) {
                case SIMPLE -> {
                    return true;
                }
                case GATE -> {
                    var gateFacing = state.get(AbstractRedstonePipeGate.FACING);
                    return direction == gateFacing || direction == gateFacing.getOpposite();
                }
            }
        } else if (state.isIn(BlockTags.CONNECTS_TO_PIPES) || state.isIn(BlockTags.CONNECTS_TO_PIPES_AND_UPDATES)) {
            return true;
        } else if (state.isOf(Blocks.OBSERVER)) {
            return direction == state.get(ObserverBlock.FACING);
        } else if (state.isOf(Blocks.PISTON) || state.isOf(Blocks.STICKY_PISTON) || state.isOf(Blocks.MOVING_PISTON)) {
            return direction != state.get(PistonBlock.FACING).getOpposite();
        }
        return checkEmitsPower && checkFullSquare && direction != null;
    }



    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        var northSouth = ((state.get(NORTH) || state.get(SOUTH)) && !(state.get(NORTH) && state.get(SOUTH)));
        var eastWest = ((state.get(EAST) || state.get(WEST)) && !(state.get(EAST) && state.get(WEST)));
        var upDown = ((state.get(UP) || state.get(DOWN)) && !(state.get(UP) && state.get(DOWN)));
        var hasJoint = (northSouth && eastWest) || (eastWest && upDown) || (northSouth && upDown);

        var jointShape = (hasJoint ? JOINT_SHAPE : VoxelShapes.empty());
        for (var dir : Direction.values()) {
            if (state.get(STATES.get(dir))) {
                jointShape = VoxelShapes.union(jointShape, SHAPES.get(dir));
            }
        }

        return jointShape;
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock()) && !world.isClient) {
            this.update(world, pos, state);

            for (Direction direction : Direction.values()) {
                world.updateNeighborsAlways(pos.offset(direction), this);
            }

            this.updateOffsetNeighbors(world, pos);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!moved && !state.isOf(newState.getBlock())) {
            super.onStateReplaced(state, world, pos, newState, false);
            if (!world.isClient) {
                for (Direction direction : Direction.values()) {
                    world.updateNeighborsAlways(pos.offset(direction), this);
                }

                this.ensureUpdate(world, pos, state);
                this.updateOffsetNeighbors(world, pos);
            }
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            this.ensureUpdate(world, pos, state);
        }
        if (world.getBlockState(fromPos).isIn(BlockTags.CONNECTS_TO_PIPES_AND_UPDATES)) {
            world.updateNeighbor(fromPos, this, pos);
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

    public void updateOffsetNeighbors(World world, BlockPos pos) {
        for (var direction : Direction.values()) {
            var blockPos = pos.offset(direction);

            this.updateNeighbors(world, blockPos);

            if (world.getBlockState(blockPos).isSolidBlock(world, blockPos)) {
                this.updateNeighbors(world, blockPos.up());
            } else {
                this.updateNeighbors(world, blockPos.down());
            }
        }
    }

    private void update(World world, BlockPos pos, BlockState state) {
        var receivedPower = this.getReceivedRedstonePower(world, pos, state);
        if (state.get(POWER) != receivedPower) {
            if (world.getBlockState(pos) == state) {
                world.setBlockState(pos, state.with(POWER, receivedPower), 2);
            } else {
                return;
            }

            var poses = Sets.<BlockPos>newHashSet();
            poses.add(pos);
            for (Direction direction : Direction.values()) {
                if (state.get(STATES.get(direction))) {
                    poses.add(pos.offset(direction));
                }
            }

            for (BlockPos blockPos : poses) {
                world.updateNeighborsAlways(blockPos, this);
            }
        }
    }

    private void ensureUpdate(World world, BlockPos pos, BlockState state) {
        if (!world.isClient) {
            this.update(world, pos, state);
        }
        world.getBlockTickScheduler().schedule(pos, this, 0);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            this.update(world, pos, state);
        }
    }

    /**
     * Ensures that the power picked up by the pipe is from
     * a side that is connected to a full face, this is what I call "Valid power".
     * So the pipe can only receive power if it is connected to a block physically.
     */
    public int getValidReceivedPower(World world, BlockState state, BlockPos pos) {
        int i = 0;
        for (var direction : Direction.values()) {
            var currentState = world.getBlockState(pos.offset(direction));
            var currentBlock = currentState.getBlock();
            var connectsToPipes = currentState.isIn(BlockTags.CONNECTS_TO_PIPES);
            var connectsToPipesAndUpdates = currentState.isIn(BlockTags.CONNECTS_TO_PIPES_AND_UPDATES);
            var isFullSquare = BlockUtils.checkFullSquare(direction, world, pos);

            if (connectsToPipes || connectsToPipesAndUpdates || isFullSquare || currentBlock instanceof RedstonePipeComponent) {
                if (state.get(STATES.get(direction))) {
                    int j = world.getEmittedRedstonePower(pos.offset(direction), direction);
                    if (j >= 15) {
                        return 15;
                    }

                    if (j > i) {
                        i = j;
                    }
                }
            }
        }
        return i;
    }

    private int getReceivedRedstonePower(World world, BlockPos pos, BlockState state) {
        var receivedPower = 0;
        var minPower = 0;

        this.pipesGivePower = false;
        receivedPower = this.getValidReceivedPower(world, state, pos);
        this.pipesGivePower = true;

        if (receivedPower < 15) {
            for (var direction : Direction.values()) {
                var blockPos = pos.offset(direction);
                var blockState = world.getBlockState(blockPos);
                minPower = Math.max(minPower, this.increasePower(blockState));
            }
        }
        return Math.max(receivedPower, minPower - 1);
    }

    private int increasePower(BlockState state) {
        return state.isOf(this) ? state.get(POWER) : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return !this.pipesGivePower ? 0 : this.getWeakRedstonePower(state, world, pos, direction);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (this.pipesGivePower) {
            var power = state.get(POWER);
            if (power == 0) {
                return 0;
            } else {
                return !(this.tryConnect(world, pos, state).get(STATES.get(direction.getOpposite()))) ? 0 : power;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        for (var direction : Direction.values()) {
            return this.pipesGivePower && state.get(STATES.get(direction));
        }
        return this.pipesGivePower;
    }


    public static int getRedstoneColor(int powerLevel) {
        return COLORS.get(powerLevel);
    }

    public static void addPoweredParticles(World world, Random random, BlockPos pos, int color, Direction direction, Direction direction2, float f, float g) {
        float h = g - f;
        if (!(random.nextFloat() >= 0.2F * h)) {
            var multiplier = 0.4375F;
            var j = f + h * random.nextFloat();
            var d = 0.5D + (multiplier * direction.getOffsetX()) + (j * direction2.getOffsetX());
            var e = 0.5D + (multiplier * direction.getOffsetY()) + (j * direction2.getOffsetY());
            var k = 0.5D + (multiplier * direction.getOffsetZ()) + (j * direction2.getOffsetZ());
            var unpackedColor = new Color(color);
            var particleColor = new Vec3f((unpackedColor.getRed() / 255.0F), (unpackedColor.getGreen() / 255.0F), (unpackedColor.getBlue() / 255.0F));
            world.addParticle(new DustParticleEffect(particleColor, 1.0F), pos.getX() + d, pos.getY() + e, pos.getZ() + k, 0.0D, 0.0D, 0.0D);
        }
    }

    private void addExposedEndParticles(World world, BlockPos pos, BlockState state) {
        var x = (double) pos.getX();
        var y = (double) pos.getY();
        var z = (double) pos.getZ();
        var vec3d = new Vec3d(x, y, z);
        for (var direction : Direction.values()) {
            if (state.get(STATES.get(direction)) && !BlockUtils.checkFullSquare(direction, world, pos) && !this.canConnect(direction, world, pos)) {
                switch (direction) {
                    case UP -> vec3d = new Vec3d(x + 0.5D, y + 1.1D, z + 0.5D);
                    case DOWN -> vec3d = new Vec3d(x + 0.5D, y + (-0.1D), z + 0.5D);
                    case NORTH -> vec3d = new Vec3d(x + 0.5D, y + 0.5D, z + (-0.1D));
                    case SOUTH -> vec3d = new Vec3d(x + 0.5D, y + 0.5D, z + 1.1D);
                    case EAST -> vec3d = new Vec3d(x + 1.1D, y + 0.5D, z + 0.5D);
                    case WEST -> vec3d = new Vec3d(x + (-0.1D), y + 0.5D, z + 0.5D);
                }

                if (state.get(WATERLOGGED) || world.getFluidState(pos.offset(direction)).getFluid() == Fluids.WATER) {
                    world.addParticle(ParticleTypes.BUBBLE, vec3d.getX(), vec3d.getY(), vec3d.getZ(), 0.0D, (direction.equals(Direction.DOWN) ? 0.0D : 0.5D), 0.0D);
                } else {
                    world.addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, state), vec3d.getX(), vec3d.getY(), vec3d.getZ(), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int i = state.get(POWER);
        if (i != 0) {
            for (Direction direction : Direction.values()) {
                addPoweredParticles(world, random, pos, COLORS.get(i), direction, direction.getOpposite(), 0.0F, 0.5F);
                this.addExposedEndParticles(world, pos, state);
            }
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public ComponentType getType() {
        return ComponentType.SIMPLE;
    }

    @Override
    public boolean isInverted() {
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, POWER, WATERLOGGED);
    }

    static {
        UP = Properties.UP;
        DOWN = Properties.DOWN;
        NORTH = Properties.NORTH;
        EAST = Properties.EAST;
        SOUTH = Properties.SOUTH;
        WEST = Properties.WEST;
        POWER = Properties.POWER;
        WATERLOGGED = Properties.WATERLOGGED;

        var shapes = new AxisShape(5.0D, 0.0D, 11.0D, 8.0D);
        SHAPES = shapes.getAsDirectionShapeMap();
        STATES = Map.ofEntries(
                Map.entry(Direction.UP, UP),
                Map.entry(Direction.DOWN, DOWN),
                Map.entry(Direction.NORTH, NORTH),
                Map.entry(Direction.SOUTH, SOUTH),
                Map.entry(Direction.EAST, EAST),
                Map.entry(Direction.WEST, WEST)
        );
        JOINT_SHAPE = Block.createCuboidShape(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);
        COLORS = Util.make(() -> {
            var map = Maps.<Integer, Integer>newHashMap();
            for (int value = 0; value <= 15; ++value) {
                var factor = value / 15.0F;
                var red = factor * 0.6F + (factor > 0.0F ? 0.4F : 0.3F);
                var green = MathHelper.clamp(factor * factor * 0.7F - 0.5F, 0.0F, 1.0F);
                var blue = MathHelper.clamp(factor * factor * 0.6F - 0.7F, 0.0F, 1.0F);
                map.put(value, MathHelper.packRgb(red, green, blue));
            }
            return map;
        });
    }
}
