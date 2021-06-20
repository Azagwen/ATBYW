package net.azagwen.atbyw.block;

import net.azagwen.atbyw.item.AtbywItems;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class ShroomStickBlock extends Block implements Waterloggable  {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final VoxelShape BASE_SHAPE;

    public ShroomStickBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false));
    }

    //TODO: (Low priority) change Sounds.

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return AtbywItems.SHROOMSTICK.getDefaultStack();
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (direction == Direction.DOWN && !this.canPlaceAt(state, world, pos))
            return Blocks.AIR.getDefaultState();
        else
            return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

        return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BASE_SHAPE;
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
        builder.add(WATERLOGGED);
    }

    static {
        BASE_SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 2.0D, 12.0D);
    }
}
