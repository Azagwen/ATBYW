package net.azagwen.atbyw.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class FenceDoorBlock extends HorizontalFacingBlock {
    public static final BooleanProperty OPEN;
    public static final BooleanProperty POWERED;
    public static final EnumProperty<DoubleBlockHalf> HALF;
    protected static final VoxelShape Z_AXIS_SHAPE;
    protected static final VoxelShape X_AXIS_SHAPE;


    public FenceDoorBlock(AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(OPEN, false).with(POWERED, false));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return switch (type) {
            case LAND, AIR -> state.get(OPEN);
            default -> false;
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        if (blockPos.getY() < 255 && ctx.getWorld().getBlockState(blockPos.up()).canReplace(ctx)) {
            World world = ctx.getWorld();
            boolean bl = world.isReceivingRedstonePower(blockPos) || world.isReceivingRedstonePower(blockPos.up());
            return this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(POWERED, bl).with(OPEN, bl).with(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (state.get(FACING).getAxis() == Direction.Axis.Z) ? Z_AXIS_SHAPE : X_AXIS_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(OPEN)) {
            return VoxelShapes.empty();
        } else {
            return (state.get(FACING).getAxis() == Direction.Axis.Z) ? Z_AXIS_SHAPE : X_AXIS_SHAPE;
        }
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            DoubleBlockHalf doubleBlockHalf = state.get(HALF);
            if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
                BlockPos blockPos2 = pos.down();
                BlockState blockState2 = world.getBlockState(blockPos2);
                if (blockState2.getBlock() == state.getBlock() && blockState2.get(HALF) == DoubleBlockHalf.LOWER) {
                    world.setBlockState(blockPos2, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, blockPos2, Block.getRawIdFromState(blockState2));
                }
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (this.material == Material.METAL) {
            return ActionResult.PASS;
        } else {
            if (state.get(OPEN)) {
                state = state.with(OPEN, false);
            } else {
                Direction direction = player.getHorizontalFacing();
                if (state.get(FACING) == direction.getOpposite()) {
                    state = state.with(FACING, direction);
                }

                state = state.with(OPEN, true);
            }
            world.setBlockState(pos, state, 10);
        }

        world.syncWorldEvent(player, state.get(OPEN) ? this.getCloseSoundEventId() : this.getOpenSoundEventId(), pos, 0);
        return ActionResult.success(world.isClient);
    }

    private int getOpenSoundEventId() {
        return this.material == Material.METAL ? 1011 : 1012;
    }

    private int getCloseSoundEventId() {
        return this.material == Material.METAL ? 1005 : 1006;
    }

    private void playOpenCloseSound(World world, BlockPos pos, boolean open) {
        world.syncWorldEvent(null, open ? this.getCloseSoundEventId() : this.getOpenSoundEventId(), pos, 0);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (direction.getAxis() == Direction.Axis.Y && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP)) {
            return newState.isOf(this) && newState.get(HALF) != doubleBlockHalf ? (state.with(FACING, newState.get(FACING)).with(OPEN, newState.get(OPEN))).with(POWERED, newState.get(POWERED)) : Blocks.AIR.getDefaultState();
        } else {
            return doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        boolean bl = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if (block != this && bl != state.get(POWERED)) {
            if (bl != state.get(OPEN)) {
                this.playOpenCloseSound(world, pos, bl);
            }

            world.setBlockState(pos, (state.with(POWERED, bl).with(OPEN, bl)), 2);
        }

    }

    public static boolean canWallConnect(BlockState state, Direction side) {
        return (state.get(FACING)).getAxis() == side.rotateYClockwise().getAxis();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF, OPEN, POWERED, FACING);
        super.appendProperties(builder);
    }


    static {
        double zMin = 6.5D;
        double zMax = 9.5D;
        OPEN = Properties.OPEN;
        POWERED = Properties.POWERED;
        HALF = Properties.DOUBLE_BLOCK_HALF;
        Z_AXIS_SHAPE = Block.createCuboidShape(0.0D, 0.0D, zMin, 16.0D, 16.0D, zMax);
        X_AXIS_SHAPE = Block.createCuboidShape(zMin, 0.0D, 0.0D, zMax, 16.0D, 16.0D);
    }
}
