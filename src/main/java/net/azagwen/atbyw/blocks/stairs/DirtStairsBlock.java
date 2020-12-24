package net.azagwen.atbyw.blocks.stairs;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

import java.util.Random;

public class DirtStairsBlock extends StairsBlockSubClass {

    public DirtStairsBlock(Settings settings) {
        super(AtbywBlocks.TICKING_DIRT, settings);
    }

    private static boolean canSurvive(BlockState state, WorldView worldView, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = worldView.getBlockState(blockPos);

        return !blockState.isSideSolidFullSquare(worldView, blockPos, Direction.UP);
    }

    private static boolean canSpread(BlockState state, WorldView worldView, BlockPos pos) {
        BlockPos blockPos = pos.up();

        return canSurvive(state, worldView, pos) && !worldView.getFluidState(blockPos).isIn(FluidTags.WATER);
    }


    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9) {
            for(int i = 0; i < 4; ++i) {
                BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                BlockState newState = world.getBlockState(pos);

                if (world.getBlockState(blockPos).isOf(Blocks.GRASS_BLOCK) && canSpread(state, world, pos)) {
                    world.setBlockState(pos, copyStates(AtbywBlocks.GRASS_BLOCK_STAIRS.getDefaultState(), newState));
                }
                else if (world.getBlockState(blockPos).isOf(Blocks.MYCELIUM) && canSpread(state, world, pos)) {
                    world.setBlockState(pos, copyStates(AtbywBlocks.MYCELIUM_STAIRS.getDefaultState(), newState));
                }
            }
        }
    }
}
