package net.azagwen.atbyw.blocks.piston;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class PistonHelper {
    public static final int PISTON = 0;
    public static final int STICKY_PISTON = 1;
    public static final int PISTON_HEAD = 2;
    public static final int MOVING_PISTON = 3;

    public static final int OAK = 0;
    public static final int SPRUCE = 1;
    public static final int BIRCH = 2;
    public static final int JUNGLE = 3;
    public static final int ACACIA = 4;
    public static final int DARK_OAK = 5;
    public static final int CRIMSON = 6;
    public static final int WARPED = 7;

    private static Block[] setBlocks(Block piston, Block sticky_piston, Block piston_head, Block moving_piston) {
        return new Block[] {piston, sticky_piston, piston_head, moving_piston};
    }

    /**
     * This could probably be done a better way, this comment explains why it was done this way, and what you need to take into account to derive it :
     *
     * The methods below were done this way because it was found out that to avoid throwing NullPointerException when defining the wood type, the
     * blocks wood_type defines have to be picked up during run-time and not from fields defined on initialization of the block.
     */

    public static Block getWoodType(int wood_type, int piece) {
        switch (wood_type) {
            default:
            case OAK:
                return setBlocks(Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.PISTON_HEAD, Blocks.MOVING_PISTON)[piece];
            case SPRUCE:
                return setBlocks(AtbywBlocks.SPRUCE_PISTON, AtbywBlocks.SPRUCE_STICKY_PISTON, AtbywBlocks.SPRUCE_PISTON_HEAD, null)[piece];
            case BIRCH:
                return setBlocks(AtbywBlocks.BIRCH_PISTON, AtbywBlocks.BIRCH_STICKY_PISTON, AtbywBlocks.BIRCH_PISTON_HEAD, null)[piece];
            case JUNGLE:
                return setBlocks(AtbywBlocks.JUNGLE_PISTON, AtbywBlocks.JUNGLE_STICKY_PISTON, AtbywBlocks.JUNGLE_PISTON_HEAD, null)[piece];
            case ACACIA:
                return setBlocks(AtbywBlocks.ACACIA_PISTON, AtbywBlocks.ACACIA_STICKY_PISTON, AtbywBlocks.ACACIA_PISTON_HEAD, null)[piece];
            case DARK_OAK:
                return setBlocks(AtbywBlocks.DARK_OAK_PISTON, AtbywBlocks.DARK_OAK_STICKY_PISTON, AtbywBlocks.DARK_OAK_PISTON_HEAD, null)[piece];
            case CRIMSON:
                return setBlocks(AtbywBlocks.CRIMSON_PISTON, AtbywBlocks.CRIMSON_STICKY_PISTON, AtbywBlocks.CRIMSON_PISTON_HEAD, null)[piece];
            case WARPED:
                return setBlocks(AtbywBlocks.WARPED_PISTON, AtbywBlocks.WARPED_STICKY_PISTON, AtbywBlocks.WARPED_PISTON_HEAD, null)[piece];
        }
    }

    public static String getName(int wood_type) {
        switch (wood_type) {
            default:
            case OAK:
                return "oak";
            case SPRUCE:
                return "spruce";
            case BIRCH:
                return "birch";
            case JUNGLE:
                return "jungle";
            case ACACIA:
                return "acacia";
            case DARK_OAK:
                return "dark_oak";
            case CRIMSON:
                return "crimson";
            case WARPED:
                return "warped";
        }
    }
}
