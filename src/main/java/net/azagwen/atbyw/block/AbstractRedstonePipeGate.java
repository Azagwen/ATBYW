package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.registry.AtbywBlocks;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.main.Tags;
import net.azagwen.atbyw.util.BlockUtils;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;

import java.util.Random;

public abstract class AbstractRedstonePipeGate extends FacingBlock implements RedstonePipeComponent, Waterloggable {
    public static final BooleanProperty POWERED;
    public static final BooleanProperty WATERLOGGED;

    protected AbstractRedstonePipeGate(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState().with(FACING, Direction.NORTH).with(POWERED, false).with(WATERLOGGED, false));
    }

    @Override
    public ComponentType getType() {
        return ComponentType.GATE;
    }

    @Override
    public boolean isInverted() {
        return false;
    }

    private boolean getPowered(BlockState state) {
        return this.isInverted() == state.get(POWERED);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        this.updatePowered(world, pos, state);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (this.hasPower(world, pos, state)) {
            world.getBlockTickScheduler().schedule(pos, this, 1);
        }

    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        this.updateTarget(world, pos, state);
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!moved && !state.isOf(newState.getBlock())) {
            super.onStateReplaced(state, world, pos, newState, moved);
            this.updateTarget(world, pos, state);
        }
    }

    protected void updateTarget(World world, BlockPos pos, BlockState state) {
        var direction = state.get(FACING);
        var blockPos = pos.offset(direction);

        world.updateNeighbor(blockPos, this, pos);
        world.updateNeighborsExcept(blockPos, this, direction);
        this.updateOffsetNeighbors(world, blockPos);
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

    private void updateNeighbors(World world, BlockPos pos) {
        if (world.getBlockState(pos).isOf(this)) {
            world.updateNeighborsAlways(pos, this);

            for (Direction direction : Direction.values()) {
                world.updateNeighborsAlways(pos.offset(direction), this);
            }
        }
    }

    protected void updatePowered(World world, BlockPos pos, BlockState state) {
        var powered = state.get(POWERED);
        var hasPower = this.hasPower(world, pos, state);

        if (powered != hasPower && !world.getBlockTickScheduler().isTicking(pos, this)) {
            TickPriority tickPriority = TickPriority.HIGH;
            if (this.isTargetNotAligned(world, pos, state)) {
                tickPriority = TickPriority.EXTREMELY_HIGH;
            } else if (powered) {
                tickPriority = TickPriority.VERY_HIGH;
            }
            world.getBlockTickScheduler().schedule(pos, this, this.getUpdateDelayInternal(state), tickPriority);
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        var powered = state.get(POWERED);
        var hasPower = this.hasPower(world, pos, state);
        if (powered && !hasPower) {
            world.setBlockState(pos, state.with(POWERED, false), 2);
        } else if (!powered) {
            world.setBlockState(pos, state.with(POWERED, true), 2);
            if (!hasPower) {
                world.getBlockTickScheduler().schedule(pos, this, this.getUpdateDelayInternal(state), TickPriority.VERY_HIGH);
            }
        }
    }

    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return this.getWeakRedstonePower(state, world, pos, direction);
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (this.getPowered(state)) {
            return 0;
        } else {
            return state.get(FACING) == direction.getOpposite() ? this.getOutputLevel(world, pos, state) : 0;
        }
    }

    public boolean emitsRedstonePower(BlockState state) {
        for (var direction : Direction.values()) {
            return state.get(FACING) == direction;
        }
        return this.getPowered(state);
    }


    protected int getOutputLevel(BlockView world, BlockPos pos, BlockState state) {
        return 15;
    }

    protected boolean hasPower(World world, BlockPos pos, BlockState state) {
        return this.getPower(world, pos, state) > 0;
    }

    public int getValidReceivedPower(World world, BlockPos pos, Direction direction) {
        var i = 0;
        var currentState = world.getBlockState(pos.offset(direction));
        var currentBlock = currentState.getBlock();
        var connectsToPipes = currentState.isIn(Tags.BlockTags.CONNECTS_TO_PIPES);
        var connectsToPipesAndUpdates = currentState.isIn(Tags.BlockTags.CONNECTS_TO_PIPES_AND_UPDATES);
        var isFullSquare = BlockUtils.checkFullSquare(direction, world, pos);

        if (connectsToPipes || connectsToPipesAndUpdates || isFullSquare || currentBlock instanceof RedstonePipeComponent) {
            i = world.getEmittedRedstonePower(pos.offset(direction), direction);
        }
        return i;
    }

    protected int getPower(World world, BlockPos pos, BlockState state) {
        var direction = state.get(FACING).getOpposite();
        var i = this.getValidReceivedPower(world, pos, direction);
        if (i >= 15) {
            return i;
        } else {
            var blockState = world.getBlockState(pos.offset(direction));
            return Math.max(i, blockState.isOf(RedstoneBlockRegistry.REDSTONE_PIPE) ? blockState.get(RedstonePipeBlock.POWER) : 0);
        }
    }

    public boolean isTargetNotAligned(BlockView world, BlockPos pos, BlockState state) {
        var direction = (state.get(FACING));
        var blockState = world.getBlockState(pos.offset(direction));
        return blockState.getBlock() instanceof AbstractRedstonePipeGate && blockState.get(FACING) != direction;
    }

    protected abstract int getUpdateDelayInternal(BlockState state);

    private void addExposedEndParticles(World world, BlockPos pos, BlockState state, boolean opposite) {
        var x = (double) pos.getX();
        var y = (double) pos.getY();
        var z = (double) pos.getZ();
        var vec3d = new Vec3d(x, y, z);
        for (var direction : Direction.values()) {
            direction = (opposite ? direction.getOpposite() : direction);
            if (state.get(FACING) == direction && !BlockUtils.checkFullSquare(direction, world, pos) && !(world.getBlockState(pos.offset(direction)).getBlock() instanceof RedstonePipeComponent)) {
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
        if (!this.getPowered(state)) {
            for (Direction direction : Direction.values()) {
                RedstonePipeBlock.addPoweredParticles(world, random, pos, RedstonePipeBlock.COLORS.get(15), direction, direction.getOpposite(), 0.0F, 0.5F);
                this.addExposedEndParticles(world, pos, state, false);
            }
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(FACING, ctx.getSide()).with(WATERLOGGED, fluidState);
    }

    static {
        POWERED = Properties.POWERED;
        WATERLOGGED = Properties.WATERLOGGED;
    }
}
