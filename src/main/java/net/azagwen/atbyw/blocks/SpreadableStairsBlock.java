package net.azagwen.atbyw.blocks;

import net.azagwen.atbyw.misc.AtbywTags;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BlockHalf;
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
import net.minecraft.world.chunk.light.ChunkLightProvider;

import java.util.Random;

public class SpreadableStairsBlock extends StairsBlockSubClass {
    private Block fullBlockEquivalent;

    public SpreadableStairsBlock(Block copiedBlock, Settings settings) {
        super(copiedBlock.getDefaultState(), settings);
        this.fullBlockEquivalent = copiedBlock;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.isOf(AtbywBlocks.GRASS_BLOCK_STAIRS)) {
            if (player.getMainHandStack().getItem().isIn(FabricToolTags.SHOVELS)) {
                BlockState oldState = world.getBlockState(pos);

                world.setBlockState(pos, copyStates(AtbywBlocks.GRASS_PATH_STAIRS.getDefaultState(), oldState));
                world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    private static boolean canSurvive(BlockState state, WorldView worldView, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = worldView.getBlockState(blockPos);
        if ((blockState.getFluidState().getLevel() == 8) || state.isIn(AtbywTags.TRAMPLES_GRASS_BLOCKS)) {
            return false;
        } else {
            int i = ChunkLightProvider.getRealisticOpacity(worldView, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(worldView, blockPos));
            return i < worldView.getMaxLightLevel();
        }
    }

    private static boolean canSpread(BlockState state, WorldView worldView, BlockPos pos) {
        BlockPos blockPos = pos.up();

        return canSurvive(state, worldView, pos) && !worldView.getFluidState(blockPos).isIn(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state, world, pos) && !(world.getBlockState(pos).get(HALF) == BlockHalf.TOP)) {
            BlockState oldState = world.getBlockState(pos);

            world.setBlockState(pos, copyStates(AtbywBlocks.DIRT_STAIRS.getDefaultState(), oldState));
        } else {
            BlockState blockState = this.getDefaultState();

            if (world.getLightLevel(pos.up()) >= 9) {
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    BlockState newState = world.getBlockState(blockPos);

                    if (world.getBlockState(blockPos).isOf(AtbywBlocks.DIRT_STAIRS) && canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos, copyStates(blockState, newState));
                    }
                    else if (world.getBlockState(blockPos).isOf(AtbywBlocks.DIRT_SLAB) && canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos, AtbywBlocks.GRASS_BLOCK_SLAB.getDefaultState()
                                .with(SpreadableSlabBlock.TYPE, newState.get(SpreadableSlabBlock.TYPE))
                        );
                    }
                    else if (world.getBlockState(blockPos).isOf(Blocks.DIRT) && canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos, this.fullBlockEquivalent.getDefaultState());
                    }
                }
            }
        }
    }
}
