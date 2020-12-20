package net.azagwen.atbyw.blocks;

import net.azagwen.atbyw.misc.AtbywTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Random;

public class NetherrackStairsBlock extends StairsBlockSubClass implements Fertilizable {

    public NetherrackStairsBlock(Block copiedBlock, Settings settings) {
        super(copiedBlock.getDefaultState(), settings);
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        if (!world.getBlockState(pos.up()).isTranslucent(world, pos) || state.isIn(AtbywTags.TRAMPLES_GRASS_BLOCKS)) {
            return false;
        } else {
            Iterator iterator = BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)).iterator();

            BlockPos blockPos;
            do {
                if (!iterator.hasNext()) {
                    return false;
                }

                blockPos = (BlockPos)iterator.next();
            } while(!world.getBlockState(blockPos).isIn(BlockTags.NYLIUM));

            return true;
        }
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockState oldState = world.getBlockState(pos);
        boolean isCrimsonNylium = false;
        boolean isWarpedNylium = false;
        Block crimsonStairs = AtbywBlocks.CRIMSON_NYLIUM_STAIRS;
        Block warpedStairs = AtbywBlocks.WARPED_NYLIUM_STAIRS;
        Block crimsonSlab = AtbywBlocks.CRIMSON_NYLIUM_SLAB;
        Block warpedSlab= AtbywBlocks.WARPED_NYLIUM_SLAB;

        Iterator iterator = BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)).iterator();

        while(iterator.hasNext()) {
            BlockPos blockPos = (BlockPos)iterator.next();
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(warpedStairs) || blockState.isOf(warpedSlab) || blockState.isOf(Blocks.WARPED_NYLIUM)) {
                isWarpedNylium = true;
            }

            if (blockState.isOf(crimsonStairs) || blockState.isOf(crimsonSlab) || blockState.isOf(Blocks.CRIMSON_NYLIUM)) {
                isCrimsonNylium = true;
            }

            if (isWarpedNylium && isCrimsonNylium) {
                break;
            }
        }

        if (isWarpedNylium && isCrimsonNylium) {
            world.setBlockState(pos, random.nextBoolean() ? copyStates(warpedStairs.getDefaultState(), oldState) : copyStates(crimsonStairs.getDefaultState(), oldState), 3);
        } else if (isWarpedNylium) {
            world.setBlockState(pos, copyStates(warpedStairs.getDefaultState(), oldState), 3);
        } else if (isCrimsonNylium) {
            world.setBlockState(pos, copyStates(crimsonStairs.getDefaultState(), oldState), 3);
        }

    }
}
