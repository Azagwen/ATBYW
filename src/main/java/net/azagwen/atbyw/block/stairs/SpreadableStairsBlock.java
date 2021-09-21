package net.azagwen.atbyw.block.stairs;

import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.azagwen.atbyw.block.slabs.SpreadableSlabBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

import java.util.Random;

public class SpreadableStairsBlock extends StairsBlockSubClass {
    private final Block fullBlockEquivalent;

    public SpreadableStairsBlock(Block copiedBlock, Block fullBlockEquivalent, Settings settings) {
        super(copiedBlock, settings);
        this.fullBlockEquivalent = fullBlockEquivalent;
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
        if (!canSurvive(state, world, pos)) {
            BlockState oldState = world.getBlockState(pos);

            world.setBlockState(pos, BuildingBlockRegistry.DIRT_STAIRS.getStateWithProperties(oldState));
        }
        else {
            var blockState = this.getDefaultState();

            if (world.getLightLevel(pos.up()) >= 9) {
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                    BlockState newState = world.getBlockState(blockPos);
                    BlockPos downPos = new BlockPos(pos.getX(), (pos.getY() - 1), pos.getZ());
                    BlockPos upPos = new BlockPos(pos.getX(), (pos.getY() + 1), pos.getZ());

                    if (!blockPos.equals(downPos)) {
                        if (!blockPos.equals(upPos)) {
                            if (world.getBlockState(blockPos).isOf(BuildingBlockRegistry.DIRT_STAIRS) && canSpread(blockState, world, blockPos)) {
                                world.setBlockState(blockPos, this.getStateWithProperties(newState));
                            } else if (world.getBlockState(blockPos).isOf(BuildingBlockRegistry.DIRT_SLAB) && canSpread(blockState, world, blockPos)) {
                                Block self = world.getBlockState(pos).getBlock();

                                if (self == BuildingBlockRegistry.GRASS_BLOCK_STAIRS)
                                    world.setBlockState(blockPos, BuildingBlockRegistry.GRASS_BLOCK_SLAB.getDefaultState()
                                            .with(SpreadableSlabBlock.TYPE, newState.get(SpreadableSlabBlock.TYPE)));
                                else if (self == BuildingBlockRegistry.MYCELIUM_STAIRS)
                                    world.setBlockState(blockPos, BuildingBlockRegistry.MYCELIUM_SLAB.getDefaultState()
                                            .with(SpreadableSlabBlock.TYPE, newState.get(SpreadableSlabBlock.TYPE)));
                            } else if (world.getBlockState(blockPos).isOf(Blocks.DIRT) && canSpread(blockState, world, blockPos)) {
                                world.setBlockState(blockPos, this.fullBlockEquivalent.getDefaultState());
                            }
                        }
                    }
                }
            }
        }
    }
}
