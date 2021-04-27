package net.azagwen.atbyw.blocks;

import net.azagwen.atbyw.main.AtbywDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static net.azagwen.atbyw.util.AtbywUtils.getBlockFromID;

public class SpikeBlock extends Block {
    private static final VoxelShape SHAPE;
    private final Identifier spikeTrapBlock;
    private final float damageValue;

    public SpikeBlock(Identifier spikeTrapBlock, float damageValue, Settings settings) {
        super(settings);
        this.damageValue = damageValue;
        this.spikeTrapBlock = spikeTrapBlock;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        boolean isPlayerCreative = player.isCreative();

        if (world.getBlockState(pos.down()).getBlock() instanceof SpikeTrapBlock) {
            world.breakBlock(pos.down(), !isPlayerCreative, player);
        }

        super.onBreak(world, pos, state, player);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        boolean isImmuneEntity = (
                entity.getType() == EntityType.SKELETON ||
                entity.getType() == EntityType.SKELETON_HORSE ||
                entity.getType() == EntityType.WITHER_SKELETON ||
                entity.getType() == EntityType.BLAZE ||
                entity.getType() == EntityType.IRON_GOLEM
        );

        boolean isEntityPlayerAndCreative = entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative();

        if (entity instanceof LivingEntity && !isImmuneEntity && !isEntityPlayerAndCreative) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 40, 25));
            entity.slowMovement(state, new Vec3d(0.75D, 1.0D, 0.75D));
            entity.damage(AtbywDamageSource.SPIKE_TRAP, damageValue);
        }

        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return getBlockFromID(spikeTrapBlock).asItem().getDefaultStack();
    }

    static {
        SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    }
}
