package net.azagwen.atbyw.client;

import net.azagwen.atbyw.block.entity.AtbywBlockEntityTypes;
import net.azagwen.atbyw.client.render.AtbywBlockRenderLayers;
import net.azagwen.atbyw.client.render.AtbywEntityModelLayers;
import net.azagwen.atbyw.client.render.TimerRepeaterBlockEntityRenderer;
import net.azagwen.atbyw.client.render.model.TimerRepeaterDigitBakedModel;
import net.azagwen.atbyw.main.AtbywEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

@SuppressWarnings("deprecation")
public class AtbywClient implements ClientModInitializer {

    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {

        //noinspection UnstableApiUsage
        EntityModelLayerRegistry.registerModelLayer(AtbywEntityModelLayers.TIMER_REPEATER, TimerRepeaterBlockEntityRenderer::getTexturedModelData);
        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntityTypes.TIMER_REPEATER_ENTITY, TimerRepeaterBlockEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(AtbywEntityTypes.SHROOMSTICK, FlyingItemEntityRenderer::new);

        ModelLoadingRegistry.INSTANCE.registerResourceProvider(provider -> new TimerRepeaterDigitBakedModel.Provider());

        AtbywColorProviders.initItems();
        AtbywColorProviders.initBlocks();
        AtbywBlockRenderLayers.init();
    }
}