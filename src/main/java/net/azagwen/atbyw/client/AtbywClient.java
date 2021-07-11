package net.azagwen.atbyw.client;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.entity.AtbywBlockEntityTypes;
import net.azagwen.atbyw.block.entity.CanvasBlockEntity;
import net.azagwen.atbyw.client.render.AtbywBlockRenderLayers;
import net.azagwen.atbyw.client.render.AtbywEntityModelLayers;
import net.azagwen.atbyw.client.render.TimerRepeaterBlockEntityRenderer;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.item.SimpleColoredItem;
import net.azagwen.atbyw.main.AtbywEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.item.DyeableItem;

import java.awt.*;

@SuppressWarnings("deprecation")
public class AtbywClient implements ClientModInitializer {

    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {

        //noinspection UnstableApiUsage
        EntityModelLayerRegistry.registerModelLayer(AtbywEntityModelLayers.TIMER_REPEATER, TimerRepeaterBlockEntityRenderer::getTexturedModelData);
        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntityTypes.TIMER_REPEATER_ENTITY, TimerRepeaterBlockEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(AtbywEntityTypes.SHROOMSTICK, FlyingItemEntityRenderer::new);

        AtbywColorProviders.initItems();
        AtbywColorProviders.initBlocks();
        AtbywBlockRenderLayers.init();
    }
}