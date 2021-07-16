package net.azagwen.atbyw.block.statues;

import net.azagwen.atbyw.block.shape.StatueVoxelShapes;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public abstract class AbstractStatueBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final StatueBlockMobType mobType;

    public AbstractStatueBlock(StatueBlockMobType mobType, Settings settings) {
        super(settings);
        this.mobType = mobType;
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    private Direction getDirection(BlockState state) {
        return state.get(FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (mobType.getOutlineShapes() != null) {
            return setOutlineShape(getDirection(state));
        }
        return StatueVoxelShapes.DEFAULT_OUTLINE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        if (mobType.getCollisionShapes() != null) {
            return setCollisionShape(getDirection(state));
        }
        else if (mobType.getOutlineShapes() != null) {
            return setOutlineShape(getDirection(state));
        }
        return StatueVoxelShapes.DEFAULT_COLLISIONS;
    }

    private VoxelShape setOutlineShape(Direction direction) {
        return switch (direction) {
            case NORTH -> mobType.getOutlineShape(StatueBlockMobType.NORTH);
            case SOUTH -> mobType.getOutlineShape(StatueBlockMobType.SOUTH);
            case EAST -> mobType.getOutlineShape(StatueBlockMobType.EAST);
            case WEST -> mobType.getOutlineShape(StatueBlockMobType.WEST);
            default -> null;
        };
    }

    private VoxelShape setCollisionShape(Direction direction) {
        return switch (direction) {
            case NORTH -> mobType.getCollisionShape(StatueBlockMobType.NORTH);
            case SOUTH -> mobType.getCollisionShape(StatueBlockMobType.SOUTH);
            case EAST -> mobType.getCollisionShape(StatueBlockMobType.EAST);
            case WEST -> mobType.getCollisionShape(StatueBlockMobType.WEST);
            default -> null;
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }
}
