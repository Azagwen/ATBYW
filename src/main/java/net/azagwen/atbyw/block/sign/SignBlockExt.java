package net.azagwen.atbyw.block.sign;

import net.minecraft.block.SignBlock;
import net.minecraft.util.SignType;

public class SignBlockExt extends SignBlock implements AtbywSign {
    private final AtbywSignType type;

    public SignBlockExt(AtbywSignType signType, Settings settings) {
        super(settings, SignType.OAK);
        this.type = signType;
    }

    @Override
    public AtbywSignType getType() {
        return type;
    }
}
