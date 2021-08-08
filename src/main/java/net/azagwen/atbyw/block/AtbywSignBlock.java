package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywSignType;
import net.minecraft.block.SignBlock;
import net.minecraft.util.SignType;

public class AtbywSignBlock extends SignBlock implements AtbywSign {
    private final AtbywSignType type;

    public AtbywSignBlock(AtbywSignType signType, Settings settings) {
        super(settings, SignType.OAK);
        this.type = signType;
    }

    @Override
    public AtbywSignType getType() {
        return type;
    }
}
