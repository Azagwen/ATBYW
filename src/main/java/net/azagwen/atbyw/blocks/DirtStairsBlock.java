package net.azagwen.atbyw.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

import java.util.Iterator;
import java.util.Random;

public class DirtStairsBlock extends StairsBlockSubClass {

    public DirtStairsBlock(Settings settings) {
        super(Blocks.DIRT.getDefaultState(), settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9) {
            for(int i = 0; i < 4; ++i) {
                BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                BlockState newState = world.getBlockState(blockPos);

                System.out.println(blockPos);

                if (world.getBlockState(blockPos).isOf(AtbywBlocks.GRASS_BLOCK_STAIRS)) {
                    world.setBlockState(blockPos, copyStates(AtbywBlocks.GRASS_BLOCK_STAIRS.getDefaultState(), newState));
                }
            }
        }
    }
}
