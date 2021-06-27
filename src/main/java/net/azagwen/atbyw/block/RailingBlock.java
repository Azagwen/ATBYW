package net.azagwen.atbyw.block;

import net.azagwen.atbyw.util.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import static net.azagwen.atbyw.util.BlockUtils.makeDirectionalShapes;
import static net.azagwen.atbyw.util.AtbywUtils.getItemFromID;

public class RailingBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    private static final VoxelShape[] SHAPES;
    private  final Identifier handStack;

    public RailingBlock(Identifier handStack, Settings settings) {
        super(settings);
        this.handStack = handStack;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());

        boolean north = ctx.getPlayerFacing() == Direction.NORTH;
        boolean east = ctx.getPlayerFacing() == Direction.EAST;
        boolean south = ctx.getPlayerFacing() == Direction.SOUTH;
        boolean west = ctx.getPlayerFacing() == Direction.WEST;

        if (blockState.isOf(this)) {
            north = north || blockState.get(RailingBlock.NORTH);
            east = east || blockState.get(RailingBlock.EAST);
            south = south || blockState.get(RailingBlock.SOUTH);
            west = west || blockState.get(RailingBlock.WEST);

            return blockState.with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west).with(WATERLOGGED, blockState.get(RailingBlock.WATERLOGGED));
        } else {
            boolean isSubmerged = ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER;

            return this.getDefaultState().with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west).with(WATERLOGGED, isSubmerged);
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
//        context.getWorld().getBlockState(context.getBlockPos()).isOf(this)
        return context.getStack().getItem() == this.asItem();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape SHAPE_NORTH = state.get(NORTH) ? SHAPES[BlockUtils.NORTH] : VoxelShapes.empty();
        VoxelShape SHAPE_EAST = state.get(EAST) ? SHAPES[BlockUtils.EAST] : VoxelShapes.empty();
        VoxelShape SHAPE_SOUTH = state.get(SOUTH) ? SHAPES[BlockUtils.SOUTH] : VoxelShapes.empty();
        VoxelShape SHAPE_WEST = state.get(WEST) ? SHAPES[BlockUtils.WEST] : VoxelShapes.empty();

        return VoxelShapes.union(SHAPE_NORTH, SHAPE_EAST, SHAPE_SOUTH, SHAPE_WEST);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, NORTH, EAST, SOUTH, WEST);
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        NORTH = Properties.NORTH;
        EAST = Properties.EAST;
        SOUTH = Properties.SOUTH;
        WEST = Properties.WEST;

        SHAPES = makeDirectionalShapes(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
    }
}

