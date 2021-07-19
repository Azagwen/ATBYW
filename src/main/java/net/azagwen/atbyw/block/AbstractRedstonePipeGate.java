package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.FacingBlock;
import net.minecraft.state.property.IntProperty;

public class AbstractRedstonePipeGate extends FacingBlock {
    public static final IntProperty ROLL;

    protected AbstractRedstonePipeGate(Settings settings) {
        super(settings);
    }

    static {
        ROLL = AtbywProperties.ROLL;
    }
}
