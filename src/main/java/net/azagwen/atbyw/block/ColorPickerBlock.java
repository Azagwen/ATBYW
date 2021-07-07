package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.entity.ColorPickerBlockEntity;
import net.azagwen.atbyw.item.ColorPickerBlockItem;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class ColorPickerBlock extends BlockWithEntity {

    public ColorPickerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ColorPickerBlockEntity(pos, state);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        var itemStack = super.getPickStack(world, pos, state);
        var entity = (ColorPickerBlockEntity) world.getBlockEntity(pos);
        var color = entity.getColor();

        var nbt = new NbtCompound();
        nbt.putInt("color", new Color(color.getRed(), color.getGreen(), color.getBlue()).getRGB());
        if (!nbt.isEmpty()) {
            itemStack.putSubTag("display", nbt);
        }

        return itemStack;
    }
}
