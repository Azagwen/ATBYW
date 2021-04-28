package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class TimerRepeaterBlock extends AbstractRedstoneGateBlock {
    public static final IntProperty TIMER_DELAY;
    public static final IntProperty TIMER_DIGIT_LEFT;
    public static final IntProperty TIMER_DIGIT_RIGHT;

    protected TimerRepeaterBlock(Settings settings) {
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
            world.setBlockState(pos, state.cycle(TIMER_DELAY).with(TIMER_DIGIT_LEFT, getDigits(state)[0]).with(TIMER_DIGIT_RIGHT, getDigits(state)[1]), 3);
            return ActionResult.success(world.isClient);
        }
    }

    /**
     * Feel free to judge me on how I did the system below,
     * I could find a way better than this that wouldn't
     * have forced me to have a 5000+ lines blockstate json.
     **/
    private int shiftedDelay(BlockState state) {
        int delay = state.get(TIMER_DELAY);

        if (delay < 64) {
            return delay + 1;
        } else {
            return 1;
        }
    }

    private int adjustRightDigit(BlockState state, int digitRight) {
        int delay = shiftedDelay(state);

        if (delay != 1) {
            if (digitRight < 9) {
                return digitRight + 1;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    private int adjustLeftDigit(BlockState state, int digitLeft, int digitRight) {
        int delay = shiftedDelay(state);

        if (delay != 1) {
            if (digitRight == 0) {
                return digitLeft + 1;
            } else {
                return digitLeft;
            }
        } else {
            return 0;
        }
    }

    private int[] getDigits(BlockState state) {
        int digitLeft = 0;
        int digitRight;

        if (state.get(TIMER_DELAY) < 10) {
            digitRight = state.get(TIMER_DELAY);
        } else {
            String delayStr = state.get(TIMER_DELAY).toString();
            char[] digits = delayStr.toCharArray();

            digitLeft = Integer.parseInt(String.valueOf(digits[0]));
            digitRight = Integer.parseInt(String.valueOf(digits[1]));
        }

        return new int[] {adjustLeftDigit(state, digitLeft, adjustRightDigit(state, digitRight)), adjustRightDigit(state, digitRight)};
    }

    protected int getUpdateDelayInternal(BlockState state) {
        return state.get(TIMER_DELAY) * 2;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TIMER_DELAY, TIMER_DIGIT_LEFT, TIMER_DIGIT_RIGHT, POWERED);
    }

    static {
        TIMER_DELAY = AtbywProperties.TIMER_DELAY;
        TIMER_DIGIT_LEFT = AtbywProperties.TIMER_DIGIT_LEFT;
        TIMER_DIGIT_RIGHT = AtbywProperties.TIMER_DIGIT_RIGHT;
    }
}
