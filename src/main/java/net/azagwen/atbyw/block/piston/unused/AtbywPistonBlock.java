package net.azagwen.atbyw.block.piston.unused;

import net.azagwen.atbyw.block.piston.PistonWoodTypes;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;


public class AtbywPistonBlock extends FacingBlock {
    public static final BooleanProperty EXTENDED;
    protected static final VoxelShape EXTENDED_EAST_SHAPE;
    protected static final VoxelShape EXTENDED_WEST_SHAPE;
    protected static final VoxelShape EXTENDED_SOUTH_SHAPE;
    protected static final VoxelShape EXTENDED_NORTH_SHAPE;
    protected static final VoxelShape EXTENDED_UP_SHAPE;
    protected static final VoxelShape EXTENDED_DOWN_SHAPE;
    private final boolean sticky;
    private final String type;

    public AtbywPistonBlock(boolean sticky, String type, Settings settings) {
        super(settings);
        this.sticky = sticky;
        this.type = type;
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(EXTENDED, false));
    }

    private Block getHeadFromType() {
        return PistonWoodTypes.valueOf(type).getPistonHead();
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite()).with(EXTENDED, false);
    }

    public boolean canExtend(World world, BlockPos pos, BlockState state) {
        Direction direction = state.get(FACING);
        boolean isBlockPushable = !world.getBlockState(pos.offset(direction)).isOf(Blocks.OBSIDIAN);
        boolean isAir = world.getBlockState(pos.offset(direction)).isOf(Blocks.AIR);

        return isBlockPushable || isAir;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        Direction direction = state.get(FACING);
        if (!world.isClient) {
            boolean isExtended = state.get(EXTENDED);
            if (isExtended != world.isReceivingRedstonePower(pos)) {
                if (isExtended) {
                    world.getBlockTickScheduler().schedule(pos, this, 2);
                } else {
                    if (canExtend(world, pos, state)) {
                        world.setBlockState(pos, state.cycle(EXTENDED), 2);
                        world.playSound(null, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.25F + 0.6F);
                        world.setBlockState(pos.offset(direction), getHeadFromType().getDefaultState().with(FACING, state.get(FACING)));
                    }
                }
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        Direction direction = state.get(FACING);
        if (state.get(EXTENDED) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(EXTENDED), 2);
            if (world.getBlockState(pos.offset(direction)).isOf(getHeadFromType())) {
                world.playSound(null, pos, SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.25F + 0.6F);
                world.setBlockState(pos.offset(direction), Blocks.AIR.getDefaultState());
            }
        }
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, EXTENDED);
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return state.get(EXTENDED);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(EXTENDED)) {
            switch(state.get(FACING)) {
                case DOWN:
                    return EXTENDED_DOWN_SHAPE;
                case UP:
                default:
                    return EXTENDED_UP_SHAPE;
                case NORTH:
                    return EXTENDED_NORTH_SHAPE;
                case SOUTH:
                    return EXTENDED_SOUTH_SHAPE;
                case WEST:
                    return EXTENDED_WEST_SHAPE;
                case EAST:
                    return EXTENDED_EAST_SHAPE;
            }
        } else {
            return VoxelShapes.fullCube();
        }
    }

    static {
        EXTENDED = Properties.EXTENDED;
        EXTENDED_EAST_SHAPE =  Block.createCuboidShape(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
        EXTENDED_WEST_SHAPE =  Block.createCuboidShape(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        EXTENDED_SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
        EXTENDED_NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
        EXTENDED_UP_SHAPE =    Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
        EXTENDED_DOWN_SHAPE =  Block.createCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }
}
