package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FlowerButtonBlock extends PlantBlock {
    protected static final VoxelShape SHAPE;
    public static final BooleanProperty POWERED;
    public static final BooleanProperty LIT;
    public static final IntProperty TIMER;
    private static final int delay = 15;

    public FlowerButtonBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false).with(LIT, false).with(TIMER, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(POWERED)) {
            return ActionResult.CONSUME;
        } else {
            this.powerOn(state, world, pos);
            this.playButtonSound(player, world, pos, true);
            return ActionResult.success(world.isClient);
        }
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(POWERED, true).with(TIMER, 6).with(LIT, true), 3);
        this.updateNeighbors(state, world, pos);
        world.getBlockTickScheduler().schedule(pos, this, delay);
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.down(), this);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean powered = state.get(POWERED);

        if (state.get(TIMER) == 6) {
            world.setBlockState(pos, state.with(POWERED, true).with(LIT, powered).with(TIMER, 5), 3);
            world.getBlockTickScheduler().schedule(pos, this, delay);
            this.playClickSound(world, pos);
        }
        else if (state.get(TIMER) == 5) {
            world.setBlockState(pos, state.with(POWERED, true).with(LIT, powered).with(TIMER, 4), 3);
            world.getBlockTickScheduler().schedule(pos, this, delay);
            this.playClickSound(world, pos);
        }
        else if (state.get(TIMER) == 4) {
            world.setBlockState(pos, state.with(POWERED, true).with(LIT, powered).with(TIMER, 3), 3);
            world.getBlockTickScheduler().schedule(pos, this, delay);
            this.playClickSound(world, pos);
        }
        else if (state.get(TIMER) == 3) {
            world.setBlockState(pos, state.with(POWERED, true).with(LIT, powered).with(TIMER, 2), 3);
            world.getBlockTickScheduler().schedule(pos, this, delay);
            this.playClickSound(world, pos);
        }
        else if (state.get(TIMER) == 2) {
            world.setBlockState(pos, state.with(POWERED, true).with(LIT, powered).with(TIMER, 1), 3);
            world.getBlockTickScheduler().schedule(pos, this, delay);
            this.playClickSound(world, pos);
        }
        else if (state.get(TIMER) == 1) {
            world.setBlockState(pos, state.with(POWERED, true).with(LIT, powered).with(TIMER, 0), 3);
            world.getBlockTickScheduler().schedule(pos, this, delay);
            this.playClickSound(world, pos);
        }
        else if (state.get(TIMER) == 0) {
            world.setBlockState(pos, state.with(POWERED, false).with(LIT, state.get(POWERED)).with(LIT, false).with(TIMER, 0), 3);
            this.updateNeighbors(state, world, pos);
            this.playButtonSound(null, world, pos, false);
        }
    }

    protected void playButtonSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean powered) {
        world.playSound(powered ? player : null, pos, this.getClickSound(powered), SoundCategory.BLOCKS, 0.3F, powered ? 1.5F : 1F);
    }

    protected void playClickSound(WorldAccess world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.BLOCK_CHAIN_STEP, SoundCategory.BLOCKS, 0.3F, 2F);
    }

    protected SoundEvent getClickSound(boolean powered) {
        return powered ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
    }

    private int getRedstonePower(BlockState state) {
        return ((state.get(TIMER) * 2) + 3);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (state.get(POWERED) ? getRedstonePower(state) : 0);
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return direction == Direction.UP ? (state.get(POWERED) ? getRedstonePower(state) : 0) : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, LIT, TIMER);
    }

    static {
        SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
        TIMER = AtbywProperties.TIMER;
        POWERED = Properties.POWERED;
        LIT = Properties.LIT;
    }
}
