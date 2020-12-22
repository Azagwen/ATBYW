package net.azagwen.atbyw.blocks;

import net.azagwen.atbyw.misc.AtbywTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherrackBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Iterator;
import java.util.Random;

public class SterileNetherrackBlock extends NetherrackBlock {

    public SterileNetherrackBlock(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(Blocks.NETHERRACK);
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        boolean isCrimson = false;
        boolean isWarped = false;
        Iterator iterator = BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)).iterator();

        while (iterator.hasNext()) {
            BlockPos blockPos = (BlockPos) iterator.next();
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.WARPED_NYLIUM)) {
                isWarped = true;
            }

            if (blockState.isOf(Blocks.CRIMSON_NYLIUM)) {
                isCrimson = true;
            }

            if (isWarped && isCrimson) {
                break;
            }
        }

        BlockState blockUp = world.getBlockState(pos.up());
        boolean isBlockTramplig = blockUp.isIn(AtbywTags.TRAMPLES_GRASS_BLOCKS) || blockUp.isIn(AtbywTags.TRAMPLES_NYLIUM_BLOCKS);
        if (!isBlockTramplig) {
            if (isWarped && isCrimson) {
                world.setBlockState(pos, random.nextBoolean() ? Blocks.WARPED_NYLIUM.getDefaultState() : Blocks.CRIMSON_NYLIUM.getDefaultState(), 3);
            } else if (isWarped) {
                world.setBlockState(pos, Blocks.WARPED_NYLIUM.getDefaultState(), 3);
            } else if (isCrimson) {
                world.setBlockState(pos, Blocks.CRIMSON_NYLIUM.getDefaultState(), 3);
            }
        }
    }
}
