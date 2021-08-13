package net.azagwen.atbyw.block.sign;

import net.minecraft.block.WallSignBlock;
import net.minecraft.util.SignType;

public class WallSignBlockExt extends WallSignBlock implements AtbywSign {
    private final AtbywSignType type;

    public WallSignBlockExt(AtbywSignType signType, Settings settings) {
        super(settings, SignType.OAK);
        this.type = signType;
    }

    @Override
    public AtbywSignType getType() {
        return type;
    }
}
