package net.azagwen.atbyw.block.statues;

import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class WaxedStatueBlock extends AbstractStatueBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final int mossLevel;
    private final List<Block> stateList;

    public WaxedStatueBlock(int mossLevel, List<Block> list, StatueBlockMobType mobType, Settings settings) {
        super(mobType, settings);
        this.mossLevel = mossLevel;
        this.stateList = list;
        list.add(this);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    private BlockState getUnwaxedState(BlockState state) {
        for (var block : this.stateList) {
            if (block instanceof StatueBlock) {
                return block.getDefaultState()
                        .with(StatueBlock.FACING, state.get(FACING))
                        .with(StatueBlock.WATERLOGGED, state.get(WATERLOGGED))
                        .with(StatueBlock.MOSS_LEVEL, this.mossLevel);
            }
        }
        return null; //Should be unreachable if the registries are correctly assigned
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getMainHandStack().isIn(FabricToolTags.AXES)) {
            if (!world.isClient && !player.isCreative()) {
                player.getMainHandStack().damage(1, world.random, (ServerPlayerEntity) player);
            }
            world.setBlockState(pos, this.getUnwaxedState(state));
            world.playSound(null, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F);
            ParticleUtil.spawnParticle(world, pos, ParticleTypes.WAX_OFF, UniformIntProvider.create(3, 5));
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    public int getMossLevel() {
        return mossLevel;
    }
}
