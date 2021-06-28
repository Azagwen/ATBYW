package net.azagwen.atbyw.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.util.math.Direction;

public class ShatteredGlassBlock extends GlassBlock {
    private final Block copiedBlock;

    public ShatteredGlassBlock(Block copiedBlock) {
        super(FabricBlockSettings.copyOf(copiedBlock));
        this.copiedBlock = copiedBlock;
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        var isBlockCullable = stateFrom.isOf(this) || stateFrom.isOf(this.copiedBlock);

        return isBlockCullable || super.isSideInvisible(state, stateFrom, direction);
    }
}
