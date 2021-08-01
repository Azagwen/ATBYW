package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.azagwen.atbyw.block.state.LargeChainEnd;
import net.azagwen.atbyw.main.Tags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class LargeChainBlock extends ChainBlock {
    public static final EnumProperty<LargeChainEnd> CONNECT_BOTTOM;
    public static final EnumProperty<LargeChainEnd> CONNECT_TOP;
    protected static final VoxelShape Y_SHAPE;
    protected static final VoxelShape Z_SHAPE;
    protected static final VoxelShape X_SHAPE;

    public LargeChainBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(WATERLOGGED, false)
                .with(AXIS, Direction.Axis.Y)
                .with(CONNECT_BOTTOM, LargeChainEnd.CONNECT)
                .with(CONNECT_TOP, LargeChainEnd.CONNECT)
        );
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = super.getPlacementState(ctx);

        return updateConnectionStates(state, world, pos, this.stateManager::getDefaultState);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        return updateConnectionStates(state, (World) world, pos, () -> {
            return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
        });
    }

    private BlockState updateConnectionStates(BlockState state, World world, BlockPos pos, Supplier<BlockState> def) {
        BlockPos posTop;
        BlockPos posBottom;

        switch (state.get(AXIS)) {
            case X:
                posTop = pos.east();
                posBottom = pos.west();
                return this.setConnectionStates(state, world, posTop, posBottom);
            case Z:
                posTop = pos.north();
                posBottom = pos.south();
                return this.setConnectionStates(state, world, posTop, posBottom);
            case Y:
                posTop = pos.up();
                posBottom = pos.down();
                return this.setConnectionStates(state, world, posTop, posBottom);
            default:
                return def.get();
        }
    }

    private LargeChainEnd getEndState(BlockState state, World world, BlockPos connectedPos, boolean isTop) {
        BlockState connectedState = world.getBlockState(connectedPos);
        Direction.AxisDirection axisDirection = isTop ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE;
        boolean isConnectStateTransitionBlock = isTop ? connectedState.isIn(Tags.BlockTags.LARGE_CHAIN_TRANSITION_TOP) : connectedState.isIn(Tags.BlockTags.LARGE_CHAIN_TRANSITION_BOTTOM);
        boolean isConnectedStateInstance = (connectedState.getBlock() instanceof LargeChainBlock) && connectedState.get(LargeChainBlock.AXIS).equals(state.get(AXIS));
        boolean isConnectedStateValid = sideCoversSmallSquare(world, connectedPos, Direction.from(state.get(AXIS), axisDirection));

        if ((isConnectedStateValid || isConnectedStateInstance) && !isConnectStateTransitionBlock) {
            return LargeChainEnd.CONNECT;
        } else if (isConnectStateTransitionBlock) {
            return LargeChainEnd.TRANSITION;
        } else {
            return LargeChainEnd.EMPTY;
        }
    }

    private BlockState setConnectionStates(BlockState state, World world, BlockPos posTop, BlockPos posBottom) {
        return this.stateManager.getDefaultState()
                .with(WATERLOGGED, state.get(WATERLOGGED))
                .with(AXIS, state.get(AXIS))
                .with(CONNECT_BOTTOM, getEndState(state, world, posBottom, false))
                .with(CONNECT_TOP, getEndState(state, world, posTop, true));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, AXIS, CONNECT_BOTTOM, CONNECT_TOP);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch(state.get(AXIS)) {
            case X:
            default:
                return X_SHAPE;
            case Z:
                return Z_SHAPE;
            case Y:
                return Y_SHAPE;
        }
    }

    static {
        double Min = 3.0D;
        double Max = 13.0D;
        double yMin = 0.0D;
        double yMax = 16.0D;

        Y_SHAPE = Block.createCuboidShape( Min, yMin,  Min,  Max, yMax,  Max);
        Z_SHAPE = Block.createCuboidShape( Min,  Min, yMin,  Max,  Max, yMax);
        X_SHAPE = Block.createCuboidShape(yMin,  Min,  Min, yMax,  Max,  Max);

        CONNECT_BOTTOM = AtbywProperties.CONNECT_BOTTOM;
        CONNECT_TOP = AtbywProperties.CONNECT_TOP;
    }
}
