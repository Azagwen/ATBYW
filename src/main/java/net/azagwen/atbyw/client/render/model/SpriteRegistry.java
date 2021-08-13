package net.azagwen.atbyw.client.render.model;

import net.azagwen.atbyw.main.AtbywMain;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

public class SpriteRegistry {
    public static SpriteIdentifier TIMER_REPEATER_DIGITS_TEXTURE;
    public static SpriteIdentifier GLOWING_CANVAS_BLOCK_SURFACE;
    public static SpriteIdentifier GLOWING_CANVAS_BLOCK_EDGES;

    public static SpriteIdentifier RegisterBlockSprite(Identifier identifier) {
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlas, registry) -> {
            registry.register(identifier);
        });
        return new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, identifier);
    }

    public static void init() {
        TIMER_REPEATER_DIGITS_TEXTURE = RegisterBlockSprite(AtbywMain.id("baked_models/timer_repeater_digits"));
        GLOWING_CANVAS_BLOCK_SURFACE = RegisterBlockSprite(AtbywMain.id("baked_models/glowing_canvas_block"));
        GLOWING_CANVAS_BLOCK_EDGES = RegisterBlockSprite(AtbywMain.id("baked_models/glowing_canvas_block_outline"));
    }
}
