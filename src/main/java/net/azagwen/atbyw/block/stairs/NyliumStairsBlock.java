package net.azagwen.atbyw.block.stairs;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

import java.util.Random;

public class NyliumStairsBlock extends StairsBlockSubClass {

    public NyliumStairsBlock(Block copiedBlock, Settings settings) {
        super(copiedBlock, settings);
    }

    private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);

        return !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!stayAlive(state, world, pos)) {
            BlockState newState = world.getBlockState(pos);

            world.setBlockState(pos, AtbywBlocks.NETHERRACK_STAIRS.getStateWithProperties(newState));
        }
    }
}
