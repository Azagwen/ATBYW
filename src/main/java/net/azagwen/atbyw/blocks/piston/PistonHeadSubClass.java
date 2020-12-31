package net.azagwen.atbyw.blocks.piston;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.PistonType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class PistonHeadSubClass extends PistonHeadBlock {
    private int wood_type;

    public PistonHeadSubClass(int wood_type, Settings settings) {
        super(settings);
        this.wood_type = wood_type;
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(state.get(TYPE) == PistonType.STICKY ? PistonHelper.getPistonWoodType(wood_type, true) : PistonHelper.getPistonWoodType(wood_type, false));
    }

    private boolean canSurvive(BlockState blockState, BlockState targetBlock) {
        Block block = blockState.get(TYPE) == PistonType.DEFAULT ? PistonHelper.getPistonWoodType(wood_type, false) : PistonHelper.getPistonWoodType(wood_type, true);
        return targetBlock.isOf(block) && targetBlock.get(PistonBlock.EXTENDED) && targetBlock.get(FACING) == blockState.get(FACING);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.abilities.creativeMode) {
            BlockPos blockPos = pos.offset((state.get(FACING)).getOpposite());
            if (this.canSurvive(state, world.getBlockState(blockPos))) {
                world.breakBlock(blockPos, false);
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            super.onStateReplaced(state, world, pos, newState, moved);
            BlockPos blockPos = pos.offset((state.get(FACING)).getOpposite());
            if (this.canSurvive(state, world.getBlockState(blockPos))) {
                world.breakBlock(blockPos, true);
            }
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.offset((state.get(FACING)).getOpposite()));
        return this.canSurvive(state, blockState) || blockState.isOf(Blocks.MOVING_PISTON) && blockState.get(FACING) == state.get(FACING);
    }
}
