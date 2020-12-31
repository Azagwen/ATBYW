package net.azagwen.atbyw.blocks.piston;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class PistonHeadType {
    private final Block head_type;
    public static final PistonHeadType OAK = new PistonHeadType(Blocks.PISTON_HEAD);
    public static final PistonHeadType SPRUCE = new PistonHeadType(AtbywBlocks.SPRUCE_PISTON_HEAD);
    public static final PistonHeadType BIRCH = new PistonHeadType(AtbywBlocks.BIRCH_PISTON_HEAD);
    public static final PistonHeadType JUNGLE = new PistonHeadType(AtbywBlocks.JUNGLE_PISTON_HEAD);
    public static final PistonHeadType ACACIA = new PistonHeadType(AtbywBlocks.ACACIA_PISTON_HEAD);
    public static final PistonHeadType DARK_OAK = new PistonHeadType(AtbywBlocks.DARK_OAK_PISTON_HEAD);
    public static final PistonHeadType CRIMSON = new PistonHeadType(AtbywBlocks.CRIMSON_PISTON_HEAD);
    public static final PistonHeadType WARPED = new PistonHeadType(AtbywBlocks.WARPED_PISTON_HEAD);

    public PistonHeadType(Block head_type) {
        if (head_type != null)
            this.head_type = head_type;
        else
            this.head_type = OAK.getPistonHeadType();
    }

    public Block getPistonHeadType() {
        return head_type;
    }
}
