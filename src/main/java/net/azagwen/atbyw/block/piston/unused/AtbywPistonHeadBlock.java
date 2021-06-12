package net.azagwen.atbyw.block.piston.unused;

import net.azagwen.atbyw.block.piston.PistonWoodTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.PistonType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Arrays;

public class AtbywPistonHeadBlock extends FacingBlock {
    public static final EnumProperty<PistonType> TYPE;
    public static final BooleanProperty SHORT;
    protected static final VoxelShape EAST_HEAD_SHAPE;
    protected static final VoxelShape WEST_HEAD_SHAPE;
    protected static final VoxelShape SOUTH_HEAD_SHAPE;
    protected static final VoxelShape NORTH_HEAD_SHAPE;
    protected static final VoxelShape UP_HEAD_SHAPE;
    protected static final VoxelShape DOWN_HEAD_SHAPE;
    protected static final VoxelShape UP_ARM_SHAPE;
    protected static final VoxelShape DOWN_ARM_SHAPE;
    protected static final VoxelShape SOUTH_ARM_SHAPE;
    protected static final VoxelShape NORTH_ARM_SHAPE;
    protected static final VoxelShape EAST_ARM_SHAPE;
    protected static final VoxelShape WEST_ARM_SHAPE;
    protected static final VoxelShape SHORT_UP_ARM_SHAPE;
    protected static final VoxelShape SHORT_DOWN_ARM_SHAPE;
    protected static final VoxelShape SHORT_SOUTH_ARM_SHAPE;
    protected static final VoxelShape SHORT_NORTH_ARM_SHAPE;
    protected static final VoxelShape SHORT_EAST_ARM_SHAPE;
    protected static final VoxelShape SHORT_WEST_ARM_SHAPE;
    private static final VoxelShape[] SHORT_SHAPES;
    private static final VoxelShape[] SHAPES;
    private final String type;

    public AtbywPistonHeadBlock(String type, Settings settings) {
        super(settings);
        this.type = type;
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(SHORT, false));
    }

    private Block getPistonFromType() {
        return PistonWoodTypes.valueOf(type).getPiston();
    }

    private Block getStickyPistonFromType() {
        return PistonWoodTypes.valueOf(type).getStickyPiston();
    }

    private boolean canExist(BlockState blockState, BlockState blockState2) {
        Block block = blockState.get(TYPE) == PistonType.DEFAULT ? getPistonFromType() : getStickyPistonFromType();
        return blockState2.isOf(block) && blockState2.get(PistonBlock.EXTENDED) && blockState2.get(FACING) == blockState.get(FACING);
    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.getAbilities().creativeMode) {
            BlockPos blockPos = pos.offset((state.get(FACING)).getOpposite());
            if (this.canExist(state, world.getBlockState(blockPos))) {
                world.breakBlock(blockPos, false);
            }
        }

        super.onBreak(world, pos, state, player);
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            super.onStateReplaced(state, world, pos, newState, moved);
            BlockPos blockPos = pos.offset((state.get(FACING)).getOpposite());
            if (this.canExist(state, world.getBlockState(blockPos))) {
                world.breakBlock(blockPos, true);
            }

        }
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(state.get(TYPE) == PistonType.STICKY ? getStickyPistonFromType() : getPistonFromType());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, SHORT);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (state.get(SHORT) ? SHORT_SHAPES : SHAPES)[(state.get(FACING)).ordinal()];
    }

    private static VoxelShape[] getHeadShapes(boolean isShort) {
        return Arrays.stream(Direction.values()).map((direction) -> makeHeadShapes(direction, isShort)).toArray(VoxelShape[]::new);
    }

    private static VoxelShape makeHeadShapes(Direction direction, boolean isShort) {
        switch(direction) {
            case DOWN:
            default:
                return VoxelShapes.union(DOWN_HEAD_SHAPE, isShort ? SHORT_DOWN_ARM_SHAPE : DOWN_ARM_SHAPE);
            case UP:
                return VoxelShapes.union(UP_HEAD_SHAPE, isShort ? SHORT_UP_ARM_SHAPE : UP_ARM_SHAPE);
            case NORTH:
                return VoxelShapes.union(NORTH_HEAD_SHAPE, isShort ? SHORT_NORTH_ARM_SHAPE : NORTH_ARM_SHAPE);
            case SOUTH:
                return VoxelShapes.union(SOUTH_HEAD_SHAPE, isShort ? SHORT_SOUTH_ARM_SHAPE : SOUTH_ARM_SHAPE);
            case WEST:
                return VoxelShapes.union(WEST_HEAD_SHAPE, isShort ? SHORT_WEST_ARM_SHAPE : WEST_ARM_SHAPE);
            case EAST:
                return VoxelShapes.union(EAST_HEAD_SHAPE, isShort ? SHORT_EAST_ARM_SHAPE : EAST_ARM_SHAPE);
        }
    }

    static {
        TYPE = Properties.PISTON_TYPE;
        SHORT = Properties.SHORT;
        EAST_HEAD_SHAPE = Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        WEST_HEAD_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
        SOUTH_HEAD_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
        NORTH_HEAD_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
        UP_HEAD_SHAPE = Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        DOWN_HEAD_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

        UP_ARM_SHAPE = Block.createCuboidShape(6.0D, -4.0D, 6.0D, 10.0D, 12.0D, 10.0D);
        DOWN_ARM_SHAPE = Block.createCuboidShape(6.0D, 4.0D, 6.0D, 10.0D, 20.0D, 10.0D);
        SOUTH_ARM_SHAPE = Block.createCuboidShape(6.0D, 6.0D, -4.0D, 10.0D, 10.0D, 12.0D);
        NORTH_ARM_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 20.0D);
        EAST_ARM_SHAPE = Block.createCuboidShape(-4.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
        WEST_ARM_SHAPE = Block.createCuboidShape(4.0D, 6.0D, 6.0D, 20.0D, 10.0D, 10.0D);

        SHORT_UP_ARM_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 12.0D, 10.0D);
        SHORT_DOWN_ARM_SHAPE = Block.createCuboidShape(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D);
        SHORT_SOUTH_ARM_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 12.0D);
        SHORT_NORTH_ARM_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 16.0D);
        SHORT_EAST_ARM_SHAPE = Block.createCuboidShape(0.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
        SHORT_WEST_ARM_SHAPE = Block.createCuboidShape(4.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);

        SHORT_SHAPES = getHeadShapes(true);
        SHAPES = getHeadShapes(false);
    }
}
