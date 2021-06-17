package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class ColumnBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED;
    public static final BooleanProperty TOP;
    public static final BooleanProperty MIDDLE;
    public static final BooleanProperty BOTTOM;
    private static final VoxelShape TOP_SHAPE;
    private static final VoxelShape MIDDLE_SHAPE;
    private static final VoxelShape BOTTOM_SHAPE;

    public ColumnBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(TOP, true).with(MIDDLE, true).with(BOTTOM, true).with(WATERLOGGED, false));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        var upPos = pos.up();
        var downPos = pos.down();
        var isSneaking = placer instanceof PlayerEntity && placer.isSneaking();
        if (!isSneaking) {
            if (world.getBlockState(upPos).getBlock() instanceof ColumnBlock) {
                world.setBlockState(upPos, world.getBlockState(upPos).with(ColumnBlock.BOTTOM, false));
            }
            if (world.getBlockState(downPos).getBlock() instanceof ColumnBlock) {
                world.setBlockState(downPos, world.getBlockState(downPos).with(ColumnBlock.TOP, false));
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        var world = ctx.getWorld();
        var pos = ctx.getBlockPos();
        var upPos = pos.up();
        var downPos = pos.down();
        var isSneaking = ctx.shouldCancelInteraction();

        return this.getDefaultState()
                .with(TOP, isSneaking || !(world.getBlockState(upPos).getBlock() instanceof ColumnBlock))
                .with(MIDDLE, true)
                .with(BOTTOM, isSneaking || !(world.getBlockState(downPos).getBlock() instanceof ColumnBlock))
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        BlockState upState = world.getBlockState(pos.up());
        BlockState downState = world.getBlockState(pos.down());

        boolean isCovered = upState.getBlock() instanceof ColumnBlock && !upState.get(BOTTOM);
        boolean isCovering = downState.getBlock() instanceof ColumnBlock && !downState.get(TOP);

        return this.getDefaultState()
                .with(TOP, !isCovered)
                .with(MIDDLE, true)
                .with(BOTTOM, !isCovering)
                .with(WATERLOGGED, state.get(WATERLOGGED));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                state.get(TOP) ? TOP_SHAPE : VoxelShapes.empty(),
                state.get(MIDDLE) ? MIDDLE_SHAPE : VoxelShapes.empty(),
                state.get(BOTTOM) ? BOTTOM_SHAPE : VoxelShapes.empty()
        );
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
        builder.add(TOP, MIDDLE, BOTTOM, WATERLOGGED);
    }

    static {
        TOP = AtbywProperties.TOP;
        MIDDLE = AtbywProperties.MIDDLE;
        BOTTOM = AtbywProperties.BOTTOM;
        WATERLOGGED = Properties.WATERLOGGED;
        TOP_SHAPE = Block.createCuboidShape(1.0D, 13.0D, 1.0D, 15.0D, 16.0D, 15.0D);
        MIDDLE_SHAPE = Block.createCuboidShape(3.0D,  0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
        BOTTOM_SHAPE = Block.createCuboidShape(1.0D,  0.0D, 1.0D, 15.0D,  3.0D, 15.0D);
    }
}
