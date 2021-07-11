package net.azagwen.atbyw.block.extensions;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public record UseAction(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

    @Override
    public BlockState state() {
        return state;
    }

    @Override
    public World world() {
        return world;
    }

    @Override
    public BlockPos pos() {
        return pos;
    }

    @Override
    public PlayerEntity player() {
        return player;
    }

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public BlockHitResult hit() {
        return hit;
    }
}
