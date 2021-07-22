package net.azagwen.atbyw.block;

import net.azagwen.atbyw.main.AtbywTags;
import net.azagwen.atbyw.util.BlockUtils;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;

import java.util.Random;

public abstract class AbstractRedstonePipeGate extends FacingBlock {
    public static final BooleanProperty POWERED;

    protected AbstractRedstonePipeGate(Settings settings) {
        super(settings);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (state.canPlaceAt(world, pos)) {
            this.updatePowered(world, pos, state);
        }
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
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction);
        world.updateNeighbor(blockPos, this, pos);
        world.updateNeighborsExcept(blockPos, this, direction);
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
        return state.getWeakRedstonePower(world, pos, direction);
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (!(Boolean)state.get(POWERED)) {
            return 0;
        } else {
            return state.get(FACING) == direction.getOpposite() ? this.getOutputLevel(world, pos, state) : 0;
        }
    }

    public boolean emitsRedstonePower(BlockState state) {
        for (var direction : Direction.values()) {
            return state.get(FACING) == direction;
        }
        return state.get(POWERED);
    }


    protected int getOutputLevel(BlockView world, BlockPos pos, BlockState state) {
        return 15;
    }

    protected boolean hasPower(World world, BlockPos pos, BlockState state) {
        return this.getPower(world, pos, state) > 0;
    }
    public int getValidReceivedPower(World world, BlockState state, BlockPos pos, Direction direction) {
        var i = 0;
        var currentState = world.getBlockState(pos.offset(direction));
        var currentBlock = currentState.getBlock();
        var connectsToPipes = currentState.isIn(AtbywTags.CONNECTS_TO_PIPES);
        var connectsToPipesAndUpdates = currentState.isIn(AtbywTags.CONNECTS_TO_PIPES_AND_UPDATES);
        var isFullSquare = BlockUtils.checkFullSquare(direction, world, pos);

        if (connectsToPipes || connectsToPipesAndUpdates || isFullSquare || currentBlock instanceof RedstonePipeComponent) {
            if (state.get(FACING) == direction) {
                int j = world.getEmittedRedstonePower(pos.offset(direction), direction);
                if (j >= 15) {
                    return 15;
                }

                if (j > i) {
                    i = j;
                }
            }
        }
        return i;
    }

    public int getEmittedRedstonePower(World world, BlockState state, BlockPos pos, Direction direction) {
        var blockState = world.getBlockState(pos);
        var power = blockState.getWeakRedstonePower(world, pos, direction);
        return blockState.isSolidBlock(world, pos) ? Math.max(power, this.getValidReceivedPower(world, state, pos, direction)) : power;
    }

    protected int getPower(World world, BlockPos pos, BlockState state) {
        var direction = state.get(FACING).getOpposite();
        var blockPos = pos.offset(direction);
        var i = this.getEmittedRedstonePower(world, state, blockPos, direction);
        if (i >= 15) {
            return i;
        } else {
            var blockState = world.getBlockState(blockPos);
            return Math.max(i, blockState.isOf(AtbywBlocks.REDSTONE_PIPE) ? blockState.get(RedstonePipeBlock.POWER) : 0);
        }
    }

    public boolean isTargetNotAligned(BlockView world, BlockPos pos, BlockState state) {
        var direction = (state.get(FACING));
        var blockState = world.getBlockState(pos.offset(direction));
        return blockState.getBlock() instanceof AbstractRedstonePipeGate && blockState.get(FACING) != direction;
    }

    protected abstract int getUpdateDelayInternal(BlockState state);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getSide().getOpposite());
    }

    static {
        POWERED = Properties.POWERED;
    }
}
