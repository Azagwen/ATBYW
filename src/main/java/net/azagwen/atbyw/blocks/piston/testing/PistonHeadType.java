package net.azagwen.atbyw.blocks.piston.testing;

import net.minecraft.block.Block;

public class PistonHeadType {
    private Block head_type;

    public PistonHeadType(Block head_type) {
        this.head_type = head_type;
    }

    public Block getPistonHeadType() {
        return head_type;
    }

    @Override
    public String toString() {
        return "PistonHeadType{" + "head_type=" + head_type + "}";
    }
}
