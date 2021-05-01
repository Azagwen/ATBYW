package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SpikeTrapBlock extends Block {
    private final Block spikeBlock;
    private final float strength;
    private boolean hasBeenBlockedOnce;
    public static final BooleanProperty ACTIVE;
    public static final BooleanProperty CAN_BREAK;

    public SpikeTrapBlock(Block spikeBlock, float strength, Settings settings) {
        super(settings);
        this.spikeBlock = spikeBlock;
        this.strength = strength;
        this.setDefaultState(this.getDefaultState().with(ACTIVE, false).with(CAN_BREAK, false));
    }

    private boolean isNeighBorBreakable(World world, BlockPos pos) {
        BlockState upState = world.getBlockState(pos.up());

        boolean negativeHardness = upState.getHardness(world, pos.up()) < 0.0F;
        boolean aboveOneHardness = upState.getHardness(world, pos.up()) < strength;
        boolean isSpikeBlock = upState.getBlock() instanceof SpikeBlock;

        return aboveOneHardness && !negativeHardness && !isSpikeBlock;
    }

    private boolean shouldExpand(World world, BlockPos pos) {
        Direction[] directions = Direction.values();

        for (Direction direction : directions) {
            if (direction != Direction.UP && world.isEmittingRedstonePower(pos.offset(direction), direction)) {
                return true;
            }
        }
        return false;
    }

    private void tryExpand(BlockState state, World world, BlockPos pos) {
        if (shouldExpand(world, pos)) {
            world.getBlockTickScheduler().schedule(pos, this, 4);
        } else {
            retract(world, pos);
        }

        world.setBlockState(pos, state.with(ACTIVE, shouldExpand(world, pos)).with(CAN_BREAK, isNeighBorBreakable(world, pos)));

    }

    private void retract(World world, BlockPos pos) {
        if (world.getBlockState(pos.up()).getBlock() instanceof SpikeBlock) {
            world.removeBlock(pos.up(), false);
            world.playSound(null, pos, SoundEvents.BLOCK_CHAIN_HIT, SoundCategory.BLOCKS, 0.5F, 0.5F);
        }
    }

    private void placeSpikes(BlockState state, World world, BlockPos pos) {
        if (state.get(ACTIVE)) {
            world.setBlockState(pos.up(), spikeBlock.getDefaultState());
            world.playSound(null, pos, SoundEvents.BLOCK_CHAIN_PLACE, SoundCategory.BLOCKS, 0.5F, 1);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            if (!state.get(ACTIVE)) {
                hasBeenBlockedOnce = false;
            }
            tryExpand(state, world, pos);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isAir(pos.up()) || world.isWater(pos.up())) {
            placeSpikes(state, world, pos);
            hasBeenBlockedOnce = false;
        } else if (state.get(CAN_BREAK)) {
            world.breakBlock(pos.up(), true);
            placeSpikes(state, world, pos);
            hasBeenBlockedOnce = false;
        } else {
            if (state.get(ACTIVE) && !(world.getBlockState(pos.up()).getBlock() instanceof SpikeBlock) && !hasBeenBlockedOnce) {
                world.playSound(null, pos, SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.BLOCKS, 0.5F, 1.5F);
            }
            hasBeenBlockedOnce = true;
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            tryExpand(state, world, pos);
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) {
            tryExpand(state, world, pos);
        }
    }



    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(ACTIVE, false).with(CAN_BREAK, isNeighBorBreakable(ctx.getWorld(), ctx.getBlockPos()));
    }

    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        builder.add(ACTIVE, CAN_BREAK);
    }


    static {
        ACTIVE = AtbywProperties.ACTIVE;
        CAN_BREAK = AtbywProperties.CAN_BREAK;
    }
}
