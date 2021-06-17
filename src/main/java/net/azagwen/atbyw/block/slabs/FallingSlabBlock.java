package net.azagwen.atbyw.block.slabs;

import net.azagwen.atbyw.block.entity.FallingSlabEntity;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
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

public class FallingSlabBlock extends SlabBlock implements LandingBlock {
    private final int colorHash;

    public FallingSlabBlock(Color color, Settings settings) {
        super(settings);
        this.colorHash = color.hashCode();
    }

    public FallingSlabBlock(int color, Settings settings) {
        super(settings);
        this.colorHash = color;
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
            var fallingBlockEntity = new FallingSlabEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, world.getBlockState(pos), pos);
            this.configureFallingBlockEntity(fallingBlockEntity);
            world.spawnEntity(fallingBlockEntity);
        }
        if (world.getBlockState(pos.down()).isOf(this) && world.getBlockState(pos.down()).get(TYPE) == SlabType.BOTTOM) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            world.setBlockState(pos.down(), this.getDefaultState().with(TYPE, SlabType.DOUBLE));
        } else {
            if (state.get(TYPE) == SlabType.TOP) {
                world.setBlockState(pos, state.with(TYPE, SlabType.BOTTOM));
            }
        }
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        var type = SlabType.BOTTOM;
        if (fallingBlockEntity.getBlockState().get(TYPE) == SlabType.DOUBLE) {
            type = SlabType.DOUBLE;
        }
        world.setBlockState(pos, this.getDefaultState().with(TYPE, type));
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
