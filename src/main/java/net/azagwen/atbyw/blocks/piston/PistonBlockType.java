package net.azagwen.atbyw.blocks.piston;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class PistonBlockType {
    private final Block type;
    private final Block sticky_type;
    public static final PistonBlockType OAK = new PistonBlockType(Blocks.PISTON, Blocks.STICKY_PISTON);
    public static final PistonBlockType SPRUCE = new PistonBlockType(AtbywBlocks.SPRUCE_PISTON, AtbywBlocks.SPRUCE_STICKY_PISTON);
    public static final PistonBlockType BIRCH = new PistonBlockType(AtbywBlocks.BIRCH_PISTON, AtbywBlocks.BIRCH_STICKY_PISTON);
    public static final PistonBlockType JUNGLE = new PistonBlockType(AtbywBlocks.JUNGLE_PISTON, AtbywBlocks.JUNGLE_STICKY_PISTON);
    public static final PistonBlockType ACACIA = new PistonBlockType(AtbywBlocks.ACACIA_PISTON, AtbywBlocks.ACACIA_STICKY_PISTON);
    public static final PistonBlockType DARK_OAK = new PistonBlockType(AtbywBlocks.DARK_OAK_PISTON, AtbywBlocks.DARK_OAK_STICKY_PISTON);
    public static final PistonBlockType CRIMSON = new PistonBlockType(AtbywBlocks.CRIMSON_PISTON, AtbywBlocks.CRIMSON_STICKY_PISTON);
    public static final PistonBlockType WARPED = new PistonBlockType(AtbywBlocks.WARPED_PISTON, AtbywBlocks.WARPED_STICKY_PISTON);

    public PistonBlockType(Block type, Block sticky_type) {
        if (type != null) {
            this.type = type;
            this.sticky_type = sticky_type;
        } else {
            this.type = Blocks.PISTON;
            this.sticky_type = Blocks.STICKY_PISTON;
        }
    }

    public Block getPistonType(boolean sticky) {
        return sticky ? sticky_type : type;
    }
}
