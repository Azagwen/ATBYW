package net.azagwen.atbyw.blocks;

import net.azagwen.atbyw.misc.AtbywTags;
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
import net.minecraft.world.chunk.light.ChunkLightProvider;

import java.util.Random;

public class SpreadableSlabBlock extends SlabBlockSubClass {
    private Block fullBlockEquivalent;

    public SpreadableSlabBlock(Block copiedBlock, Settings settings) {
        super(settings.ticksRandomly().nonOpaque());
        this.fullBlockEquivalent = copiedBlock;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.isOf(AtbywBlocks.GRASS_BLOCK_SLAB)) {
            if (player.getMainHandStack().getItem().isIn(FabricToolTags.SHOVELS)) {
                BlockState oldState = world.getBlockState(pos);

                world.setBlockState(pos, copyStates(AtbywBlocks.GRASS_PATH_SLAB.getDefaultState(), oldState));
                world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    private static boolean canSurvive(BlockState state, WorldView worldView, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = worldView.getBlockState(blockPos);
        if ((blockState.getFluidState().getLevel() == 8) || blockState.isIn(AtbywTags.TRAMPLES_GRASS_BLOCKS) || blockState.getBlock() instanceof SpreadableSlabBlock) {
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

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state, world, pos)) {
            BlockState oldState = world.getBlockState(pos);

            world.setBlockState(pos, copyStates(AtbywBlocks.DIRT_SLAB.getDefaultState(), oldState));
        } else {
            BlockState blockState = this.getDefaultState();

            if (world.getLightLevel(pos.up()) >= 9) {
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    BlockState newState = world.getBlockState(blockPos);

                    if (world.getBlockState(blockPos).isOf(AtbywBlocks.DIRT_SLAB) && canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos, copyStates(blockState, newState));
                    }
                    else if (world.getBlockState(blockPos).isOf(AtbywBlocks.DIRT_STAIRS) && canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos, AtbywBlocks.GRASS_BLOCK_STAIRS.getDefaultState()
                                .with(SpreadableStairsBlock.FACING, newState.get(SpreadableStairsBlock.FACING))
                                .with(SpreadableStairsBlock.SHAPE, newState.get(SpreadableStairsBlock.SHAPE))
                                .with(SpreadableStairsBlock.HALF, newState.get(SpreadableStairsBlock.HALF))
                        );
                    }
                    else if (world.getBlockState(blockPos).isOf(Blocks.DIRT) && canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos, this.fullBlockEquivalent.getDefaultState());
                    }
                }
            }
        }

        for (int i = 0; i < Grass_Blocks.length; i++) {
            if (world.getBlockState(pos.down()).getBlock() == Grass_Blocks[i]) {
                world.setBlockState(pos.down(), Dirt_Blocks[i].getDefaultState());
            }
        }
    }
}
