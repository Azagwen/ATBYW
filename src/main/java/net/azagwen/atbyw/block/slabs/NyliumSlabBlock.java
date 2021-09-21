package net.azagwen.atbyw.block.slabs;

import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

import java.util.Random;

public class NyliumSlabBlock extends SlabBlockSubClass {

    public NyliumSlabBlock(Settings settings) {
        super(settings);
    }

    private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);

        return !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!stayAlive(state, world, pos)) {
            BlockState newState = world.getBlockState(pos);

            world.setBlockState(pos, copyStates(BuildingBlockRegistry.NETHERRACK_SLAB.getDefaultState(), newState));
        }
    }
}
