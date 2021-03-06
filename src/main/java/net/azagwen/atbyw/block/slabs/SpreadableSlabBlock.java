package net.azagwen.atbyw.block.slabs;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.stairs.SpreadableStairsBlock;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;

public class SpreadableSlabBlock extends SlabBlockSubClass {
    private final Block fullBlockEquivalent;

    public SpreadableSlabBlock(Block copiedBlock, Settings settings) {
        super(settings);
        this.fullBlockEquivalent = copiedBlock;
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

            world.setBlockState(pos, copyStates(AtbywBlocks.DIRT_SLAB.getDefaultState(), oldState));
        }
        else {
            BlockState blockState = this.getDefaultState();

            if (world.getLightLevel(pos.up()) >= 9) {
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                    BlockState newState = world.getBlockState(blockPos);
                    BlockPos downPos = new BlockPos(pos.getX(), (pos.getY() - 1), pos.getZ());
                    BlockPos upPos = new BlockPos(pos.getX(), (pos.getY() + 1), pos.getZ());

                    if (!blockPos.equals(downPos)) {
                        if (!blockPos.equals(upPos)) {
                            if (world.getBlockState(blockPos).isOf(AtbywBlocks.DIRT_SLAB) && canSpread(blockState, world, blockPos)) {
                                world.setBlockState(blockPos, copyStates(blockState, newState));
                            } else if (world.getBlockState(blockPos).isOf(AtbywBlocks.DIRT_STAIRS) && canSpread(blockState, world, blockPos)) {
                                Block self = world.getBlockState(pos).getBlock();

                                if (self == AtbywBlocks.GRASS_BLOCK_SLAB)
                                    world.setBlockState(blockPos, AtbywBlocks.GRASS_BLOCK_STAIRS.getDefaultState()
                                            .with(SpreadableStairsBlock.FACING, newState.get(SpreadableStairsBlock.FACING))
                                            .with(SpreadableStairsBlock.SHAPE, newState.get(SpreadableStairsBlock.SHAPE))
                                            .with(SpreadableStairsBlock.HALF, newState.get(SpreadableStairsBlock.HALF))
                                    );
                                else if (self == AtbywBlocks.MYCELIUM_SLAB)
                                    world.setBlockState(blockPos, AtbywBlocks.MYCELIUM_STAIRS.getDefaultState()
                                            .with(SpreadableStairsBlock.FACING, newState.get(SpreadableStairsBlock.FACING))
                                            .with(SpreadableStairsBlock.SHAPE, newState.get(SpreadableStairsBlock.SHAPE))
                                            .with(SpreadableStairsBlock.HALF, newState.get(SpreadableStairsBlock.HALF))
                                    );
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
