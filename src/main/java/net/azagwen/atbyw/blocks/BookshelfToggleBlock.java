package net.azagwen.atbyw.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class BookshelfToggleBlock extends Block {
    public static final BooleanProperty POWERED;

    public BookshelfToggleBlock() {
        super(Settings.copy(Blocks.BOOKSHELF));
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockState blockState;
        if (world.isClient) {
            blockState = (BlockState)state.cycle(POWERED);
            if ((Boolean)blockState.get(POWERED)) {
                spawnParticles(blockState, world, pos, 1.0F);
            }

            return ActionResult.SUCCESS;
        } else {
            blockState = this.cyclePowered(state, world, pos);
            float f = (Boolean)blockState.get(POWERED) ? 0.6F : 0.5F;
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
            return ActionResult.CONSUME;
        }
    }

    private static void spawnParticles(BlockState state, WorldAccess world, BlockPos pos, float alpha) {
        double d = (double)pos.getX() + 0.5D + 0.1D + 0.2D;
        double e = (double)pos.getY() + 0.5D + 0.1D + 0.2D;
        double f = (double)pos.getZ() + 0.5D + 0.1D + 0.2D;
        world.addParticle(new DustParticleEffect(1.0F, 0.0F, 0.0F, alpha), d, e, f, 0.0D, 0.0D, 0.0D);
    }

    public BlockState cyclePowered(BlockState blockState, World world, BlockPos blockPos) {
        blockState = (BlockState)blockState.cycle(POWERED);
        world.setBlockState(blockPos, blockState, 3);
        this.updateNeighbors(blockState, world, blockPos);
        return blockState;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (Boolean)state.get(POWERED) ? 15 : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{POWERED});
    }

    static {
        POWERED = Properties.POWERED;
    }
}
