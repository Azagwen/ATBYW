package net.azagwen.atbyw.blocks;

import net.azagwen.atbyw.misc.AtbywTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.PistonType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

import java.util.Random;

public class SterileDirtBlock extends SpreadableBlock {

    public SterileDirtBlock(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(Blocks.DIRT);
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
        BlockState blockUp = world.getBlockState(pos.up()) ;
        boolean isBlockTramplig = blockUp.isIn(AtbywTags.TRAMPLES_GRASS_BLOCKS) || blockUp.isIn(AtbywTags.TRAMPLES_NYLIUM_BLOCKS);

        if (!canSurvive(state, world, pos)  || isBlockTramplig) {
            world.setBlockState(pos, this.getDefaultState());
        } else {
            if (world.getLightLevel(pos.up()) >= 9) {
                BlockState grassBlockState = Blocks.GRASS_BLOCK.getDefaultState();
                BlockState MyceliumState = Blocks.MYCELIUM.getDefaultState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (world.getBlockState(blockPos).isOf(grassBlockState.getBlock()) && canSpread(grassBlockState, world, blockPos)) {
                        world.setBlockState(pos, grassBlockState.with(GrassBlock.SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                    }
                    else if (world.getBlockState(blockPos).isOf(MyceliumState.getBlock()) && canSpread(grassBlockState, world, blockPos)) {
                        world.setBlockState(pos, MyceliumState);
                    }
                }
            }
        }
    }
}
