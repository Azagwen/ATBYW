package net.azagwen.atbyw.block.stairs;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RootedDirtStairsBlock extends StairsBlockSubClass {

    public RootedDirtStairsBlock(Block copiedBlock, Settings settings) {
        super(copiedBlock, settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getMainHandStack().isIn(FabricToolTags.SHOVELS)) {
            BlockState oldState = world.getBlockState(pos);

            world.setBlockState(pos, copyStates(AtbywBlocks.GRASS_PATH_STAIRS.getDefaultState(), oldState));
            world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        } else if (player.getMainHandStack().isIn(FabricToolTags.HOES)) {
            BlockState oldState = world.getBlockState(pos);

            world.setBlockState(pos, copyStates(AtbywBlocks.DIRT_STAIRS.getDefaultState(), oldState));
            world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            Block.dropStack(world, pos, hit.getSide(), new ItemStack(Items.HANGING_ROOTS));
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
