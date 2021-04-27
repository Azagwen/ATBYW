package net.azagwen.atbyw.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RedstoneJackOlantern extends CarvedPumpkinBlock {
    public static final BooleanProperty LIT;

    public RedstoneJackOlantern(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, true));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            world.setBlockState(pos, state.with(LIT, !world.isReceivingRedstonePower(pos)));
        }
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();

        return this.getDefaultState().with(LIT, !world.isReceivingRedstonePower(pos));
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(FACING) == direction.getOpposite()) {
            return 15;
        } else {
            return 0;
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}
