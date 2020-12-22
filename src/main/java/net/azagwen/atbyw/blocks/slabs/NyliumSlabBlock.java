package net.azagwen.atbyw.blocks.slabs;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.azagwen.atbyw.misc.AtbywTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

import java.util.Random;

public class NyliumSlabBlock extends SlabBlockSubClass {

    public NyliumSlabBlock(Settings settings) {
        super(settings.nonOpaque());
    }

    private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isIn(AtbywTags.TRAMPLES_NYLIUM_BLOCKS) || blockState.getBlock() instanceof NyliumSlabBlock) {
            return false;
        }
        else {
            int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
            return i < world.getMaxLightLevel();
        }
    }

    private static Block[] Grass_Blocks = {
            Blocks.GRASS_BLOCK,
            Blocks.MYCELIUM,
            Blocks.CRIMSON_NYLIUM,
            Blocks.WARPED_NYLIUM
    };
    private static Block[] Dirt_Blocks = {
            AtbywBlocks.STERILE_DIRT,
            AtbywBlocks.STERILE_DIRT,
            AtbywBlocks.STERILE_NETHERRACK,
            AtbywBlocks.STERILE_NETHERRACK
    };

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!stayAlive(state, world, pos)) {
            BlockState newState = world.getBlockState(pos);

            world.setBlockState(pos, copyStates(AtbywBlocks.NETHERRACK_SLAB.getDefaultState(), newState));
        }

        for (int i = 0; i < Grass_Blocks.length; i++) {
            if (world.getBlockState(pos.down()).getBlock() == Grass_Blocks[i]) {
                world.setBlockState(pos.down(), Dirt_Blocks[i].getDefaultState());
            }
        }
    }
}
