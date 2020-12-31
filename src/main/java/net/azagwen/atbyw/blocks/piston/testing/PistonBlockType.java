package net.azagwen.atbyw.blocks.piston.testing;

import net.minecraft.block.Block;

public class PistonBlockType{
    private final Block type;
    private final Block sticky_type;

    public PistonBlockType(Block type, Block sticky_type) {
        this.type = type;
        this.sticky_type = sticky_type;

    }

    public Block getPistonType(boolean sticky) {
        return sticky ? sticky_type : type;
    }

    @Override
    public String toString() {
        return "PistonBlockType{" + "type=" + type + ", sticky_type=" + sticky_type + "}";
    }
}
