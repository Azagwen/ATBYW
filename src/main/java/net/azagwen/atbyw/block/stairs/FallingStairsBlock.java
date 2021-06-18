package net.azagwen.atbyw.block.stairs;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.awt.*;
import java.util.Random;

public class FallingStairsBlock extends StairsBlockSubClass implements LandingBlock {
    private final int colorHash;
    private final Block copiedBlock;
    private final Block slabEquivalent;

    public FallingStairsBlock(Color color, Block copiedBlock, Block slabEquivalent, Settings settings) {
        super(copiedBlock, settings);
        this.colorHash = color.hashCode();
        this.copiedBlock = copiedBlock;
        this.slabEquivalent = slabEquivalent;
    }

    public FallingStairsBlock(int color, Block copiedBlock, Block slabEquivalent, Settings settings) {
        super(copiedBlock, settings);
        this.colorHash = color;
        this.copiedBlock = copiedBlock;
        this.slabEquivalent = slabEquivalent;
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.getBlockTickScheduler().schedule(pos, this, this.getFallDelay());
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        world.getBlockTickScheduler().schedule(pos, this, this.getFallDelay());
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
            FallingBlockEntity fallingBlockEntity = new FallingBlockEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, world.getBlockState(pos));
            this.configureFallingBlockEntity(fallingBlockEntity);
            world.spawnEntity(fallingBlockEntity);
        }
        if (world.getBlockState(pos.down()).isOf(this) && world.getBlockState(pos.down()).get(HALF) == BlockHalf.BOTTOM) {
            if (world.getBlockState(pos.down()).get(FACING) == state.get(FACING).getOpposite()) {
                world.setBlockState(pos.down(), this.copiedBlock.getDefaultState());
                world.setBlockState(pos, this.slabEquivalent.getDefaultState());
            }
        } else {
            if (state.get(HALF) == BlockHalf.TOP) {
                world.setBlockState(pos, state.with(HALF, BlockHalf.BOTTOM));
            }
        }
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (world.getBlockState(pos.down()).isOf(this) && world.getBlockState(pos.down()).get(HALF) == BlockHalf.BOTTOM) {
            if (world.getBlockState(pos.down()).get(FACING) == fallingBlockEntity.getBlockState().get(FACING).getOpposite()) {
                world.setBlockState(pos.down(), this.copiedBlock.getDefaultState());
                world.setBlockState(pos, this.slabEquivalent.getDefaultState());
            }
        } else {
            world.setBlockState(pos, this.getDefaultState()
                    .with(SHAPE, getStairShape(fallingBlockState, world, pos))
                    .with(HALF, BlockHalf.BOTTOM)
                    .with(FACING, fallingBlockEntity.getBlockState().get(FACING))
            );
        }
    }

    protected int getFallDelay() {
        return 2;
    }

    public static boolean canFallThrough(BlockState state) {
        Material material = state.getMaterial();
        return state.isAir() || state.isIn(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(16) == 0) {
            BlockPos blockPos = pos.down();
            if (canFallThrough(world.getBlockState(blockPos))) {
                double d = (double)pos.getX() + random.nextDouble();
                double e = (double)pos.getY() - 0.05D;
                double f = (double)pos.getZ() + random.nextDouble();
                world.addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, state), d, e, f, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return colorHash;
    }
}
