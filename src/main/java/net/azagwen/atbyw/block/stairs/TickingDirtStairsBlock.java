package net.azagwen.atbyw.block.stairs;

import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

import java.util.Random;

public class TickingDirtStairsBlock extends StairsBlockSubClass {

    public TickingDirtStairsBlock(Block copiedBlock, FabricBlockSettings settings) {
        super(copiedBlock, settings);
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
                    world.setBlockState(pos, BuildingBlockRegistry.GRASS_BLOCK_STAIRS.getStateWithProperties(newState));
                }
                else if (world.getBlockState(blockPos).isOf(Blocks.MYCELIUM) && canSpread(state, world, pos)) {
                    world.setBlockState(pos, BuildingBlockRegistry.MYCELIUM_STAIRS.getStateWithProperties(newState));
                }
            }
        }
    }
}
