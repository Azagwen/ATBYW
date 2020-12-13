package net.azagwen.atbyw.blocks;

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
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class BookshelfToggleBlock extends Block {
    public static final BooleanProperty POWERED;
    public static final DirectionProperty FACING;

    public BookshelfToggleBlock() {
        super(Settings.copy(Blocks.BOOKSHELF));
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false).with(FACING, Direction.NORTH));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockState blockState;
        if (world.isClient) {
            blockState = state.cycle(POWERED);
            if (blockState.get(POWERED)) {
                spawnParticles(world, pos);
            }

            return ActionResult.SUCCESS;
        } else {
            blockState = this.cyclePowered(state, world, pos);
            float f = blockState.get(POWERED) ? 0.6F : 0.5F;
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
            return ActionResult.CONSUME;
        }
    }

    public BlockState cyclePowered(BlockState state, World world, BlockPos blockPos) {
        BlockState cycle = state.cycle(POWERED);
        world.setBlockState(blockPos, cycle, 3);
        this.updateNeighbors(cycle, world, blockPos);
        return cycle;
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(POWERED)) {
            spawnParticles(world, pos);
        }

    }

    private static void spawnParticles(World world, BlockPos pos) {
        double d = 0.5625D;
        Random random = world.random;
        Direction[] directions = Direction.values();
        int var6 = directions.length;

        for(int i = 0; i < var6; ++i) {
            Direction direction = directions[i];
            BlockPos blockPos = pos.offset(direction);
            if (!world.getBlockState(blockPos).isOpaqueFullCube(world, blockPos)) {
                Direction.Axis axis = direction.getAxis();
                double e = axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getOffsetX() : (double)random.nextFloat();
                double f = axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getOffsetY() : (double)random.nextFloat();
                double g = axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getOffsetZ() : (double)random.nextFloat();
                world.addParticle(DustParticleEffect.RED, (double)pos.getX() + e, (double)pos.getY() + f, (double)pos.getZ() + g, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.getWeakRedstonePower(world, pos, direction);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) && state.get(FACING) == direction ? 15 : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return state.get(POWERED);
    }

    protected void updateNeighbors(BlockState state, World world, BlockPos pos) {
        Direction direction = state.get(FACING);

        BlockPos blockPos1 = pos.offset(direction.getOpposite());
        BlockPos blockPos2 = pos.offset(direction.rotateYClockwise().getOpposite());
        BlockPos blockPos3 = pos.offset(direction.rotateYCounterclockwise().getOpposite());

        world.updateNeighborsAlways(pos, this);

//        world.updateNeighbor(blockPos1, this, pos);
//        world.updateNeighbor(blockPos2, this, pos);
//        world.updateNeighbor(blockPos3, this, pos);
//        world.updateNeighborsExcept(blockPos1, this, direction);
//        world.updateNeighborsExcept(blockPos2, this, direction);
//        world.updateNeighborsExcept(blockPos3, this, direction);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, FACING);
    }

    static {
        POWERED = Properties.POWERED;
        FACING = HorizontalFacingBlock.FACING;
    }
}
