package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.blocks.FenceDoorBlock;
import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.block.Block.cannotConnect;

@Mixin(WallBlock.class)
public class WallBlockMixin {

    /**
     * @author
     *
     * I don't know how I could do this better.
     * Sorry if it interfers with anything...
     * feel free to do better, I'm tired ¯\_(ツ)_/¯
     *
     */
    @Overwrite
    private boolean shouldConnectTo(BlockState state, boolean faceFullSquare, Direction side) {
        Block block = state.getBlock();
        boolean isGlassPane = block instanceof PaneBlock;
        boolean isFenceGate = block instanceof FenceGateBlock && FenceGateBlock.canWallConnect(state, side);
        boolean isFenceDoor = block instanceof FenceDoorBlock && FenceDoorBlock.canWallConnect(state, side);
        boolean isBlockAbleToConnect = isGlassPane || isFenceGate || isFenceDoor;

//        System.out.println("From [" + this.getClass() + "] can connect to [" + PaneBlock.class + ", " + FenceGateBlock.class + ", " + FenceDoorBlock.class + "]");

        return state.isIn(BlockTags.WALLS) || !cannotConnect(block) && faceFullSquare || isBlockAbleToConnect;
    }
}
