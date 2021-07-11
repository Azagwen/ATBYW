package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.extensions.BlockExt;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ShatteredGlassBlock extends BlockExt implements Stainable {
    private final Block copiedBlock;
    private final DyeColor color;

    public ShatteredGlassBlock(Set<Block> set, Block copiedBlock, Settings settings) {
        this(set, null, copiedBlock, settings);
    }

    public ShatteredGlassBlock(Set<Block> set, @Nullable DyeColor color, Block copiedBlock, Settings settings) {
        super(set, settings);
        this.copiedBlock = copiedBlock;
        this.color = color;
    }

    @Override
    public DyeColor getColor() {
        return color;
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        var isBlockCullable = stateFrom.isOf(this) || stateFrom.isOf(this.copiedBlock);

        return isBlockCullable || super.isSideInvisible(state, stateFrom, direction);
    }
}
