package net.azagwen.atbyw.items;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.azagwen.atbyw.blocks.ShroomStickBlock;
import net.azagwen.atbyw.main.AtbywClient;
import net.azagwen.atbyw.main.AtbywEntityType;
import net.azagwen.atbyw.main.EntitySpawnPacket;
import net.azagwen.atbyw.main.AtbywTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ShroomStickEntity extends ThrownItemEntity {
    private int bounceCount = 0;
    private final int maxBouncesBeforeLand = 3;
    private final int maxBouncesBeforeLoot = 5;
    private final boolean showDebugInfo = false;

    public ShroomStickEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ShroomStickEntity(World world, LivingEntity owner) {
        super(AtbywEntityType.SHROOMSTICK, owner, world);
    }

    public ShroomStickEntity(World world, double x, double y, double z) {
        super(AtbywEntityType.SHROOMSTICK, x, y, z, world);
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, AtbywClient.PacketID);
    }

    protected Item getDefaultItem() {
        return AtbywItems.SHROOMSTICK;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

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

        ItemStack stack = AtbywItems.SHROOMSTICK.getDefaultStack();
        ItemEntity itemEntity = new ItemEntity(world, x, y, z, stack);
        itemEntity.setToDefaultPickupDelay();

        world.spawnEntity(itemEntity);
        this.remove();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = this.world;
        BlockPos hitPos = blockHitResult.getBlockPos();

        if (bounceCount <= maxBouncesBeforeLand) {
            modularBounce(blockHitResult.getSide());
        } else if (bounceCount <= maxBouncesBeforeLoot) {
            if (Block.sideCoversSmallSquare(world, hitPos, Direction.UP)) {
                land(world, hitPos.up());
            }
            modularBounce(blockHitResult.getSide());
        } else {
            spawnItem(world, hitPos);
        }
        if (bounceCount >= maxBouncesBeforeLand -1 && blockHitResult.getSide() != Direction.UP) {
            bounceCount--;
        } else {
            bounceCount++;
        }


        world.playSound(null, hitPos, SoundEvents.ENTITY_SLIME_SQUISH_SMALL, SoundCategory.AMBIENT, 0.5F, 0.75F);
    }

    private void modularBounce(Direction direction) {
        Vec3d vec3d = this.getVelocity();
        double multiplier = bounceCount > 0 ? 0.75D : 0.5D;
        switch (direction) {
            case EAST:
            case WEST:
                this.setVelocity(vec3d.x * -multiplier, vec3d.y * multiplier, vec3d.z * multiplier);
                break;
            case DOWN:
            case UP:
                this.setVelocity(vec3d.x * multiplier, vec3d.y * -multiplier, vec3d.z * multiplier);
                break;
            case NORTH:
            case SOUTH:
                this.setVelocity(vec3d.x * multiplier, vec3d.y * multiplier, vec3d.z * -multiplier);
                break;
        }
    }

    private void land(World world, BlockPos hitPos) {
        boolean waterLogged = world.getBlockState(hitPos).getFluidState().getFluid() == Fluids.WATER;
        BlockState state = world.getBlockState(hitPos);

        if (state.isAir() || state.isIn(AtbywTags.SHROOMSTICK_REPLACEABLE_GROUND) || state.isOf(Blocks.WATER) || state.isIn(AtbywTags.SHROOMSTICK_REPLACEABLE_WATER)) {
            placeBlock(world, hitPos, waterLogged);
        }  else {
            spawnItem(world, hitPos);
        }
    }

    private void placeBlock(World world, BlockPos hitPos, boolean waterLogged) {
        world.playSound(null, hitPos, SoundEvents.BLOCK_SHROOMLIGHT_PLACE, SoundCategory.BLOCKS, 0.5F, 2.0F);
        world.breakBlock(hitPos, true, this);
        world.setBlockState(hitPos, AtbywBlocks.SHROOMSTICK.getDefaultState().with(ShroomStickBlock.WATERLOGGED, waterLogged));
        this.remove();
    }

    @Override
    public boolean hasCustomName() {
        return this.showDebugInfo;
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.showDebugInfo;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.showDebugInfo) {
            String simplifiedVelocity = (
                    "X: " + (this.getVelocity().x) + ", " +
                    "Y: " + (this.getVelocity().y) + ", " +
                    "Z: " + (this.getVelocity().z)
            );


            this.setCustomName(new TranslatableText(String.valueOf(bounceCount)));
        }
    }
}
