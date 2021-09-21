package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.registry.AtbywBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class BookshelfToggleBlock extends HorizontalFacingBlock {
    public static final BooleanProperty POWERED;

    public BookshelfToggleBlock() {
        super(Settings.copy(Blocks.BOOKSHELF).solidBlock(AtbywBlocks::never));
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false).with(FACING, Direction.NORTH));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockState blockState = this.cyclePowered(state, world, pos);
        if (world.isClient) {
            if (blockState.get(POWERED)) {
                spawnParticles(world, pos);
            }

            return ActionResult.SUCCESS;
        } else {
            float f = blockState.get(POWERED) ? 0.6F : 0.5F;
            world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
            return ActionResult.CONSUME;
        }
    }

    public BlockState cyclePowered(BlockState state, World world, BlockPos blockPos) {
        BlockState blockState = state.cycle(POWERED);
        world.setBlockState(blockPos, blockState, 3);
        this.updateNeighbors(blockState, world, blockPos);
        return blockState;
    }

    protected void updateNeighbors(BlockState state, World world, BlockPos pos) {
        Direction direction = state.get(FACING);

        BlockPos blockPos1 = pos.offset(direction.getOpposite());
        BlockPos blockPos2 = pos.offset(direction.rotateYClockwise().getOpposite());
        BlockPos blockPos3 = pos.offset(direction.rotateYCounterclockwise().getOpposite());

        world.updateNeighbor(blockPos1, this, pos);
        world.updateNeighbor(blockPos2, this, pos);
        world.updateNeighbor(blockPos3, this, pos);
        world.updateNeighborsExcept(blockPos1, this, direction);
        world.updateNeighborsExcept(blockPos2, this, direction);
        world.updateNeighborsExcept(blockPos3, this, direction);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(POWERED)) {
            spawnParticles(world, pos);
        }

    }

    private static void spawnParticles(World world, BlockPos pos) {
        var d = 0.5625D;
        var random = world.random;
        for (Direction direction : Direction.values()) {
            var blockPos = pos.offset(direction);
            if (!world.getBlockState(blockPos).isOpaqueFullCube(world, blockPos)) {
                var axis = direction.getAxis();
                var e = axis == Direction.Axis.X ? 0.5D + d * (double) direction.getOffsetX() : (double) random.nextFloat();
                var f = axis == Direction.Axis.Y ? 0.5D + d * (double) direction.getOffsetY() : (double) random.nextFloat();
                var g = axis == Direction.Axis.Z ? 0.5D + d * (double) direction.getOffsetZ() : (double) random.nextFloat();
                world.addParticle(DustParticleEffect.DEFAULT, (double) pos.getX() + e, (double) pos.getY() + f, (double) pos.getZ() + g, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.getWeakRedstonePower(world, pos, direction);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        boolean southFace = state.get(FACING) == direction;
        boolean westFace = state.get(FACING).rotateYClockwise() == direction;
        boolean eastFace = state.get(FACING).rotateYCounterclockwise() == direction;
        return state.get(POWERED) && (southFace || westFace || eastFace) ? 15 : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, FACING);
    }

    static {
        POWERED = Properties.POWERED;
    }
}
