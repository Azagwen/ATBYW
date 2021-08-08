package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywSignType;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.block.WallSignBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

public class AtbywWallSignBlock extends WallSignBlock implements AtbywSign {
    private final AtbywSignType type;

    public AtbywWallSignBlock(AtbywSignType signType, Settings settings) {
        super(settings, SignType.OAK);
        this.type = signType;
    }

    @Override
    public AtbywSignType getType() {
        return type;
    }
}
