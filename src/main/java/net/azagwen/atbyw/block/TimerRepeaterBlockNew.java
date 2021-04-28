package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.entity.TimerRepeaterBlockEntity;
import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TimerRepeaterBlockNew extends AbstractRedstoneGateBlock implements BlockEntityProvider {
    public static final IntProperty TIMER_DELAY;
    public static final IntProperty TIMER_DIGIT_LEFT;
    public static final IntProperty TIMER_DIGIT_RIGHT;

    protected TimerRepeaterBlockNew(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(TIMER_DELAY, 1)
                .with(POWERED, false)
                .with(TIMER_DIGIT_LEFT, 0)
                .with(TIMER_DIGIT_RIGHT, 1));
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.abilities.allowModifyWorld) {
            return ActionResult.PASS;
        } else {
            world.setBlockState(pos, state.cycle(TIMER_DELAY), 3);
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof TimerRepeaterBlockEntity) {
                TimerRepeaterBlockEntity timerRepeaterBlockEntity = (TimerRepeaterBlockEntity) blockEntity;
                timerRepeaterBlockEntity.setTimerDelay(state.get(TIMER_DELAY));
            }
            return ActionResult.success(world.isClient);
        }
    }

    @Override
    protected void updatePowered(World world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof TimerRepeaterBlockEntity) {
            TimerRepeaterBlockEntity timerRepeaterBlockEntity = (TimerRepeaterBlockEntity) blockEntity;
            timerRepeaterBlockEntity.setPowered(state.get(POWERED));
        }

        super.updatePowered(world, pos, state);
    }

    protected int getUpdateDelayInternal(BlockState state) {
        return state.get(TIMER_DELAY) * 2;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx);
    }

    protected boolean isValidInput(BlockState state) {
        return isRedstoneGate(state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TIMER_DELAY, TIMER_DIGIT_LEFT, TIMER_DIGIT_RIGHT, POWERED);
    }

    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
        super.onSyncedBlockEvent(state, world, pos, type, data);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity != null && blockEntity.onSyncedBlockEvent(type, data);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new TimerRepeaterBlockEntity();
    }

    static {
        TIMER_DELAY = AtbywProperties.TIMER_DELAY;
        TIMER_DIGIT_LEFT = AtbywProperties.TIMER_DIGIT_LEFT;
        TIMER_DIGIT_RIGHT = AtbywProperties.TIMER_DIGIT_RIGHT;
    }

}
