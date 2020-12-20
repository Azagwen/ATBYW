package net.azagwen.atbyw.blocks;

import net.azagwen.atbyw.misc.AtbywTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

import java.util.Random;

public class NyliumStairsBlock extends StairsBlockSubClass {

    public NyliumStairsBlock(Block copiedBlock, Settings settings) {
        super(copiedBlock.getDefaultState(), settings);
    }

    private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i < world.getMaxLightLevel() || state.isIn(AtbywTags.TRAMPLES_GRASS_BLOCKS);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!stayAlive(state, world, pos)) {
            BlockState newState = world.getBlockState(pos);

            world.setBlockState(pos, copyStates(AtbywBlocks.NETHERRACK_STAIRS.getDefaultState(), newState));
        }
    }
}
