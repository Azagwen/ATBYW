package net.azagwen.atbyw.client;

import net.azagwen.atbyw.client.render.AtbywBlockRenderLayers;
import net.azagwen.atbyw.client.render.model.SpriteRegistry;
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

        SpriteRegistry.init();
        AtbywColorProviders.initItems();
        AtbywColorProviders.initBlocks();
        AtbywBlockRenderLayers.init();
    }
}