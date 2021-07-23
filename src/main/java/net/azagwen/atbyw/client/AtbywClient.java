package net.azagwen.atbyw.client;

import net.azagwen.atbyw.client.render.AtbywBlockRenderLayers;
import net.azagwen.atbyw.main.AtbywEntityTypes;
import net.azagwen.atbyw.main.AtbywMain;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;

public class AtbywClient implements ClientModInitializer {
    public final MinecraftClient client = MinecraftClient.getInstance();

    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(AtbywEntityTypes.SHROOMSTICK, FlyingItemEntityRenderer::new);

        //noinspection deprecation
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlas, registry) -> {
            registry.register(AtbywMain.id("block/timer_repeater_digits"));
        });

        AtbywColorProviders.initItems();
        AtbywColorProviders.initBlocks();
        AtbywBlockRenderLayers.init();
    }
}