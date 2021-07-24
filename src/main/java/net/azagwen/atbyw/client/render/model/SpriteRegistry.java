package net.azagwen.atbyw.client.render.model;

import net.azagwen.atbyw.main.AtbywMain;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

public class SpriteRegistry {
    public static SpriteIdentifier TIMER_REPEATER_DIGITS_TEXTURE;
    public static SpriteIdentifier TIMER_REPEATER_EMISSIVE_TEXTURE;

    public static SpriteIdentifier RegisterBlockSprite(Identifier identifier) {
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlas, registry) -> {
            registry.register(identifier);
        });
        return new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, identifier);
    }

    public static void init() {
        TIMER_REPEATER_DIGITS_TEXTURE = RegisterBlockSprite(AtbywMain.id("baked_models/timer_repeater_digits"));
        TIMER_REPEATER_EMISSIVE_TEXTURE = RegisterBlockSprite(AtbywMain.id("baked_models/timer_repeater_on_emissive"));
    }
}
