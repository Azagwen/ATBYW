package net.azagwen.atbyw.item.entity;

import net.azagwen.atbyw.block.registry.AtbywBlocks;
import net.azagwen.atbyw.block.ShroomStickBlock;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.main.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class ShroomStickEntity extends ThrownItemEntity {
    private final boolean hasDebug = AtbywMain.checkDebugEnabled("shroomstick");
    private int bounceCount = 0;

    public ShroomStickEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ShroomStickEntity(World world, LivingEntity owner) {
        super(EntityTypes.SHROOMSTICK, owner, world);
    }

    public ShroomStickEntity(World world, double x, double y, double z) {
        super(EntityTypes.SHROOMSTICK, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return AtbywItems.SHROOMSTICK;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        var itemStack = this.getItem();
        return (itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            var particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void spawnItem(World world, BlockPos pos) {
        float f = 0.7F;
        double x = (double) pos.getX() + (double) (world.random.nextFloat() * f) + 0.15000000596046448D;
        double y = (double) pos.getY() + (double) (world.random.nextFloat() * f) + 0.06000000238418579D + 0.6D;
        double z = (double) pos.getZ() + (double) (world.random.nextFloat() * f) + 0.15000000596046448D;

        var stack = AtbywItems.SHROOMSTICK.getDefaultStack();
        var itemEntity = new ItemEntity(world, x, y, z, stack);
        itemEntity.setToDefaultPickupDelay();

        world.spawnEntity(itemEntity);
        this.remove(RemovalReason.KILLED);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        var world = this.world;
        var hitPos = blockHitResult.getBlockPos();
        var maxBouncesBeforeLand = 3;
        var maxBouncesBeforeLoot = 5;

        if (this.bounceCount <= maxBouncesBeforeLand) {
            this.modularBounce(blockHitResult.getSide());
        } else if (this.bounceCount <= maxBouncesBeforeLoot) {
            if (Block.sideCoversSmallSquare(world, hitPos, Direction.UP)) {
                this.land(world, hitPos.up());
            }
            this.modularBounce(blockHitResult.getSide());
        } else {
            this.spawnItem(world, hitPos);
        }
        if (this.bounceCount >= maxBouncesBeforeLand -1 && blockHitResult.getSide() != Direction.UP) {
            this.bounceCount--;
        } else {
            this.bounceCount++;
        }


        world.playSound(null, hitPos, SoundEvents.ENTITY_SLIME_SQUISH_SMALL, SoundCategory.AMBIENT, 0.5F, 0.75F);
    }

    private void modularBounce(Direction direction) {
        var vec3d = this.getVelocity();
        var multiplier = this.bounceCount > 0 ? 0.75D : 0.5D;
        switch (direction) {
            case EAST, WEST -> this.setVelocity(vec3d.x * -multiplier, vec3d.y * multiplier, vec3d.z * multiplier);
            case DOWN, UP -> this.setVelocity(vec3d.x * multiplier, vec3d.y * -multiplier, vec3d.z * multiplier);
            case NORTH, SOUTH -> this.setVelocity(vec3d.x * multiplier, vec3d.y * multiplier, vec3d.z * -multiplier);
        }
    }

    private void land(World world, BlockPos hitPos) {
        var waterLogged = world.getBlockState(hitPos).getFluidState().getFluid() == Fluids.WATER;
        var state = world.getBlockState(hitPos);

        if (state.isAir() || state.isIn(Tags.BlockTags.SHROOMSTICK_REPLACEABLE_GROUND) || state.isOf(Blocks.WATER) || state.isIn(Tags.BlockTags.SHROOMSTICK_REPLACEABLE_WATER)) {
            this.placeBlock(world, hitPos, waterLogged);
        }  else {
            this.spawnItem(world, hitPos);
        }
    }

    private void placeBlock(World world, BlockPos hitPos, boolean waterLogged) {
        world.playSound(null, hitPos, SoundEvents.BLOCK_SHROOMLIGHT_PLACE, SoundCategory.BLOCKS, 0.5F, 2.0F);
        world.breakBlock(hitPos, true, this);
        world.setBlockState(hitPos, AtbywBlocks.SHROOMSTICK.getDefaultState().with(ShroomStickBlock.WATERLOGGED, waterLogged));
        this.remove(RemovalReason.KILLED);
    }

    @Override
    public boolean hasCustomName() {
        return this.hasDebug;
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.hasDebug;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.hasDebug) {
            String simplifiedVelocity = (
                    "X: " + (this.getVelocity().x) + ", " +
                    "Y: " + (this.getVelocity().y) + ", " +
                    "Z: " + (this.getVelocity().z)
            );


            this.setCustomName(new TranslatableText(String.valueOf(this.bounceCount)));
        }
    }
}
