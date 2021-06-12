package net.azagwen.atbyw.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SnowBlockSubClass extends SnowBlock {

    public SnowBlockSubClass(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world.getBlockState(pos.down()).getBlock() instanceof SpreadableBlock) {
            world.setBlockState(pos.down(), world.getBlockState(pos.down()).with(SpreadableBlock.SNOWY, true));
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
