package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.blocks.FenceDoorBlock;
import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.block.Block.cannotConnect;

@Mixin(FenceBlock.class)
public class FenceBlockMixin {

    /**
     * @author
     *
     * I don't know how I could do this better.
     * Sorry if it interfers with anything...
     * feel free to do better, I'm tired ¯\_(ツ)_/¯
     *
     */
    @Overwrite
    public boolean canConnect(BlockState state, boolean faceFullSquare, Direction side) {
        Block block = state.getBlock();
        boolean isFence = block.isIn(BlockTags.FENCES) && block.isIn(BlockTags.WOODEN_FENCES);
        boolean isFenceGate = block instanceof FenceGateBlock && FenceGateBlock.canWallConnect(state, side);
        boolean isFenceDoor = block instanceof FenceDoorBlock && FenceDoorBlock.canWallConnect(state, side);
        boolean isBlockAbleToConnect = isFenceGate || isFenceDoor;

        return isFence || !cannotConnect(block) && faceFullSquare || isBlockAbleToConnect;
    }
}
