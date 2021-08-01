package net.azagwen.atbyw.client;

import net.azagwen.atbyw.client.render.BlockRenderLayers;
import net.azagwen.atbyw.client.render.model.SpriteRegistry;
import net.azagwen.atbyw.client.screen.AtbywScreenRegistry;
import net.azagwen.atbyw.main.AtbywEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class AtbywClient implements ClientModInitializer {
    public final MinecraftClient client = MinecraftClient.getInstance();

    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(AtbywEntityTypes.SHROOMSTICK, FlyingItemEntityRenderer::new);

//        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new ClientResourceListener());

        SpriteRegistry.init();
        ColorProviders.initItems();
        ColorProviders.initBlocks();
        BlockRenderLayers.init();
        AtbywScreenRegistry.init();
    }
}