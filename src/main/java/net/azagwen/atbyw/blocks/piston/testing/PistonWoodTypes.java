package net.azagwen.atbyw.blocks.piston.testing;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public enum PistonWoodTypes implements PistonWoodType {
    OAK(Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.PISTON_HEAD),
    SPRUCE(AtbywBlocks.SPRUCE_PISTON, AtbywBlocks.SPRUCE_STICKY_PISTON, AtbywBlocks.SPRUCE_PISTON_HEAD),
    BIRCH(AtbywBlocks.BIRCH_PISTON, AtbywBlocks.BIRCH_STICKY_PISTON, AtbywBlocks.BIRCH_PISTON_HEAD),
    JUNGLE(AtbywBlocks.JUNGLE_PISTON, AtbywBlocks.JUNGLE_STICKY_PISTON, AtbywBlocks.JUNGLE_PISTON_HEAD),
    ACACIA(AtbywBlocks.ACACIA_PISTON, AtbywBlocks.ACACIA_STICKY_PISTON, AtbywBlocks.ACACIA_PISTON_HEAD),
    DARK_OAK(AtbywBlocks.DARK_OAK_PISTON, AtbywBlocks.DARK_OAK_STICKY_PISTON, AtbywBlocks.DARK_OAK_PISTON_HEAD),
    CRIMSON(AtbywBlocks.CRIMSON_PISTON, AtbywBlocks.CRIMSON_STICKY_PISTON, AtbywBlocks.CRIMSON_PISTON_HEAD),
    WARPED(AtbywBlocks.WARPED_PISTON, AtbywBlocks.WARPED_STICKY_PISTON, AtbywBlocks.WARPED_PISTON_HEAD);

    private Block piston;
    private Block sticky_piston;
    private Block piston_head;

    PistonWoodTypes(Block piston, Block sticky_piston, Block piston_head) {
        this.piston = piston;
        this.sticky_piston = sticky_piston;
        this.piston_head = piston_head;
    }

    @Override
    public Block getPiston() {
        return piston;
    }

    @Override
    public Block getStickyPiston() {
        return sticky_piston;
    }

    @Override
    public Block getPistonHead() {
        return piston_head;
    }

    @Override
    public String toString() {
        return "\r\nPistonWoodType{\r\npiston=(" + this.getPiston() + "), \r\nsticky_piston=(" + this.getStickyPiston() + "), \r\nhead=(" + this.getPistonHead() + ")\r\n}";
    }
}
