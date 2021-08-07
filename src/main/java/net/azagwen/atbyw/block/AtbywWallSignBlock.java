package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywSignType;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.SignType;

public class AtbywWallSignBlock extends WallSignBlock {
    private final AtbywSignType type;

    public AtbywWallSignBlock(AtbywSignType signType, Settings settings) {
        super(settings, SignType.OAK);
        this.type = signType;
    }

    public SpriteIdentifier getTexture() {
        var id = AtbywMain.id("entity/signs/" + this.type.getName());
        return new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, id);
    }
}
