package net.azagwen.atbyw.block.entity;

import net.azagwen.atbyw.block.slabs.FallingSlabBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class FallingSlabEntity extends FallingBlockEntity {
    private BlockState block;
    private BlockPos originalPos;

    public FallingSlabEntity(EntityType<? extends FallingBlockEntity> entityType, World world) {
        super(entityType, world);
    }

    public FallingSlabEntity(World world, double x, double y, double z, BlockState block, BlockPos originalPos) {
        super(world, x, y, z, block);
        this.block = block;
        this.originalPos = originalPos;
    }

    @Override
    public void tick() {
        var world = this.world;
        var pos = this.getBlockPos();
        if (pos.getY() <= (this.originalPos.getY() - 1) && world.getBlockState(pos).getBlock() instanceof FallingSlabBlock) {
            if (world.getBlockState(pos).isOf(this.block.getBlock())) {
                if (world.getBlockState(pos).get(FallingSlabBlock.TYPE) == SlabType.BOTTOM) {
                    world.setBlockState(pos, this.block.with(FallingSlabBlock.TYPE, SlabType.DOUBLE));
                    this.dropItem = false;
                }
            }
        } else {
            this.dropItem = true;
        }
        super.tick();
    }
}
