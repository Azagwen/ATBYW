package net.azagwen.atbyw.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

public class RedstoneLanternBlock extends LanternBlock {
    protected static final VoxelShape STANDING_SHAPE;
    protected static final VoxelShape HANGING_SHAPE;
    public static final BooleanProperty LIT;
    private static final BooleanProperty WATERLOGGED = field_26441;
    private static final IntProperty POWER_INTENSITY;
    private static final Map<BlockView, List<RedstoneLanternBlock.BurnoutEntry>> BURNOUT_MAP;

    public RedstoneLanternBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(HANGING, false).with(LIT, true).with(POWER_INTENSITY, 15));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.abilities.allowModifyWorld) {
            return ActionResult.PASS;
        }
        else {
            int intensity = (state.get(POWER_INTENSITY) != 1) ? (state.get(POWER_INTENSITY)  - 1) : 15;

            world.setBlockState(pos, state.with(POWER_INTENSITY, intensity).with(LIT, state.get(LIT)), 3);
            player.sendMessage(new TranslatableText("block.atbyw.redstone_lantern.power_intensity", intensity), true);
            world.playSound(null, pos, SoundEvents.BLOCK_LANTERN_PLACE, SoundCategory.BLOCKS, 0.4F, (state.get(POWER_INTENSITY) / 7.5F) + 0.5F);
            return ActionResult.success(world.isClient);
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (state.get(HANGING)) {
            world.updateNeighborsAlways(pos.up(), this);
        }
        world.updateNeighborsAlways(pos, this);

    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!moved) {
            if (state.get(HANGING)) {
                world.updateNeighborsAlways(pos.up(), this);
            }
            world.updateNeighborsAlways(pos, this);
        }
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (state.get(LIT) ? state.get(POWER_INTENSITY) : 0);
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        int powerUpwards = direction == Direction.DOWN ? (state.get(LIT) ? state.get(POWER_INTENSITY) : 0) : 0;

        return state.get(HANGING) ? powerUpwards : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    protected boolean shouldUnpower(World world, BlockPos pos, BlockState state) {
        boolean onGroundBehavior = world.isEmittingRedstonePower(pos.down(), Direction.DOWN);
        boolean hangingBehavior = (world.getBlockState(pos.down()).getBlock() instanceof RedstoneTorchBlock) && onGroundBehavior;

        return state.get(HANGING) ? hangingBehavior : onGroundBehavior;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean shouldUnpower = this.shouldUnpower(world, pos, state);
        List list = BURNOUT_MAP.get(world);

        while(list != null && !list.isEmpty() && world.getTime() - ((RedstoneLanternBlock.BurnoutEntry)list.get(0)).time > 60L) {
            list.remove(0);
        }

        if (state.get(LIT)) {
            if (shouldUnpower) {
                world.setBlockState(pos, state.with(LIT, false), 3);
                if (isBurnedOut(world, pos, true)) {
                    world.syncWorldEvent(1502, pos, 0);
                    world.getBlockTickScheduler().schedule(pos, world.getBlockState(pos).getBlock(), 160);
                }
            }
        } else if (!shouldUnpower && !isBurnedOut(world, pos, false)) {
            world.setBlockState(pos, state.with(LIT, true), 3);
        }
    }

    private static boolean isBurnedOut(World world, BlockPos pos, boolean addNew) {
        List<RedstoneLanternBlock.BurnoutEntry> list = BURNOUT_MAP.computeIfAbsent(world, (blockView) -> Lists.newArrayList());
        if (addNew) {
            list.add(new RedstoneLanternBlock.BurnoutEntry(pos.toImmutable(), world.getTime()));
        }

        int i = 0;

        for(int j = 0; j < list.size(); ++j) {
            RedstoneLanternBlock.BurnoutEntry burnoutEntry = list.get(j);
            if (burnoutEntry.pos.equals(pos)) {
                ++i;
                if (i >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (state.get(LIT) == this.shouldUnpower(world, pos, state) && !world.getBlockTickScheduler().isTicking(pos, this)) {
            world.getBlockTickScheduler().schedule(pos, this, 2);
        }

    }

    public static class BurnoutEntry {
        private final BlockPos pos;
        private final long time;

        public BurnoutEntry(BlockPos pos, long time) {
            this.pos = pos;
            this.time = time;
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, HANGING, WATERLOGGED, POWER_INTENSITY);
    }

    private static VoxelShape getShape(boolean isHanging, int power_intensity) {
        double height = 3.0D;
        for (int i = 0; i < power_intensity; i++) {
            if (isHanging) {
                height -= (1.0D / 3);
            }
            else {
                height += (1.0D / 3);
            }
        }

        if (isHanging)
            return VoxelShapes.union(
                    Block.createCuboidShape(5.0D, height + 2, 5.0D, 11.0D, 8.0D, 11.0D),
                    Block.createCuboidShape(6.0D, 8.0D, 6.0D, 10.0D, 10.0D, 10.0D)
            );
        else
            return VoxelShapes.union(
                    Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, height, 11.0D),
                    Block.createCuboidShape(6.0D, height, 6.0D, 10.0D, height + 2, 10.0D)
            );
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state.get(HANGING), state.get(POWER_INTENSITY));
    }

    static {
        LIT = Properties.LIT;
        POWER_INTENSITY = AtbywProperties.POWER_INTENSITY;
        BURNOUT_MAP = new WeakHashMap();
        STANDING_SHAPE = VoxelShapes.union(Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D), Block.createCuboidShape(6.0D, 7.0D, 6.0D, 10.0D, 9.0D, 10.0D));
        HANGING_SHAPE = VoxelShapes.union(Block.createCuboidShape(5.0D, 1.0D, 5.0D, 11.0D, 8.0D, 11.0D), Block.createCuboidShape(6.0D, 8.0D, 6.0D, 10.0D, 10.0D, 10.0D));
    }
}
