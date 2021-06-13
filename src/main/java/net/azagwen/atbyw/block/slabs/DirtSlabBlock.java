package net.azagwen.atbyw.block.slabs;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
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

public class DirtSlabBlock extends SlabBlockSubClass {
    private final boolean pathConvertible;

    public DirtSlabBlock(boolean pathConvertible, Settings settings) {
        super(settings);
        this.pathConvertible = pathConvertible;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (pathConvertible) {
            if (player.getMainHandStack().isIn(FabricToolTags.SHOVELS)) {
                BlockState oldState = world.getBlockState(pos);

                world.setBlockState(pos, copyStates(AtbywBlocks.GRASS_PATH_SLAB.getDefaultState(), oldState));
                world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
