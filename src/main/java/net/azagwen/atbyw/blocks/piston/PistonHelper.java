package net.azagwen.atbyw.blocks.piston;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class PistonHelper {
    public static final int OAK = 0;
    public static final int SPRUCE = 1;
    public static final int BIRCH = 2;
    public static final int JUNGLE = 3;
    public static final int ACACIA = 4;
    public static final int DARK_OAK = 5;
    public static final int CRIMSON = 6;
    public static final int WARPED = 7;

    public PistonHelper() {

    }

    public static Block getHeadWoodType(int wood_type) {
        switch (wood_type) {
            default:
            case OAK:
                return Blocks.PISTON_HEAD;
            case SPRUCE:
                return AtbywBlocks.SPRUCE_PISTON_HEAD;
            case BIRCH:
                return AtbywBlocks.BIRCH_PISTON_HEAD;
            case JUNGLE:
                return AtbywBlocks.JUNGLE_PISTON_HEAD;
            case ACACIA:
                return AtbywBlocks.ACACIA_PISTON_HEAD;
            case DARK_OAK:
                return AtbywBlocks.DARK_OAK_PISTON_HEAD;
            case CRIMSON:
                return AtbywBlocks.CRIMSON_PISTON_HEAD;
            case WARPED:
                return AtbywBlocks.WARPED_PISTON_HEAD;
        }
    }

    public static Block getPistonWoodType(int wood_type, boolean sticky) {
        switch (wood_type) {
            default:
            case OAK:
                return sticky ? Blocks.STICKY_PISTON : Blocks.PISTON;
            case SPRUCE:
                return sticky ? AtbywBlocks.SPRUCE_STICKY_PISTON : AtbywBlocks.SPRUCE_PISTON;
            case BIRCH:
                return sticky ? AtbywBlocks.BIRCH_STICKY_PISTON : AtbywBlocks.BIRCH_PISTON;
            case JUNGLE:
                return sticky ? AtbywBlocks.JUNGLE_STICKY_PISTON : AtbywBlocks.JUNGLE_PISTON;
            case ACACIA:
                return sticky ? AtbywBlocks.ACACIA_STICKY_PISTON : AtbywBlocks.ACACIA_PISTON;
            case DARK_OAK:
                return sticky ? AtbywBlocks.DARK_OAK_STICKY_PISTON : AtbywBlocks.DARK_OAK_PISTON;
            case CRIMSON:
                return sticky ? AtbywBlocks.CRIMSON_STICKY_PISTON : AtbywBlocks.CRIMSON_PISTON;
            case WARPED:
                return sticky ? AtbywBlocks.WARPED_STICKY_PISTON : AtbywBlocks.WARPED_PISTON;
        }
    }
}
