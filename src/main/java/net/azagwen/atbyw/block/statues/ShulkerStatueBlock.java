package net.azagwen.atbyw.block.statues;

import net.azagwen.atbyw.block.shape.StatueVoxelShapes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class ShulkerStatueBlock extends StatueBlock {
    public static final BooleanProperty OPEN;

    public ShulkerStatueBlock(List<Block> waxedStates, StatueBlockMobType mobType, Settings settings) {
        super(waxedStates, mobType, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(OPEN, true).with(FACING, Direction.NORTH).with(MOSS_LEVEL, 0).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(OPEN)) {
            return super.getCollisionShape(state, world, pos, context);
        } else {
            return setClosedCollisionShape(state.get(FACING));
        }
    }

    private VoxelShape setClosedCollisionShape(Direction direction) {
        return switch (direction) {
            case NORTH -> StatueVoxelShapes.SHULKER_CLOSED_COLLISIONS[StatueBlockMobType.NORTH];
            case SOUTH -> StatueVoxelShapes.SHULKER_CLOSED_COLLISIONS[StatueBlockMobType.SOUTH];
            case EAST -> StatueVoxelShapes.SHULKER_CLOSED_COLLISIONS[StatueBlockMobType.EAST];
            case WEST -> StatueVoxelShapes.SHULKER_CLOSED_COLLISIONS[StatueBlockMobType.WEST];
            default -> null;
        };
    }

    @Override
    public BlockState getResetState(BlockState state) {
        return state.with(MOSS_LEVEL, 0).with(FACING, state.get(FACING)).with(OPEN, state.get(OPEN));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        boolean canOpen = world.getBlockState(pos.up()).isAir() || world.getBlockState(pos.up()).isOf(Blocks.WATER);

        world.setBlockState(pos, state.with(OPEN, canOpen).with(FACING, state.get(FACING)).with(MOSS_LEVEL, state.get(MOSS_LEVEL)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(OPEN, FACING, MOSS_LEVEL, WATERLOGGED);
    }

    static {
        OPEN = Properties.OPEN;
    }
}
