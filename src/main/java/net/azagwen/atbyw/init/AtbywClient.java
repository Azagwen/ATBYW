package net.azagwen.atbyw.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import static net.azagwen.atbyw.blocks.AtbywBlocks.*;

public class AtbywClient implements ClientModInitializer {

    private static void renderColored(Block[] block, RenderLayer layer) {
        for (int i = 0; i < block.length - 1; i++)
            BlockRenderLayerMap.INSTANCE.putBlock(block[i], layer);
    }

    private static void renderColored(Block[] block, Block uncoloredBlock, RenderLayer layer) {
        BlockRenderLayerMap.INSTANCE.putBlock(uncoloredBlock, layer);
        for (int i = 0; i < block.length - 1; i++)
            BlockRenderLayerMap.INSTANCE.putBlock(block[i], layer);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(OAK_FENCE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SPRUCE_FENCE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BIRCH_FENCE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JUNGLE_FENCE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ACACIA_FENCE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DARK_OAK_FENCE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CRIMSON_FENCE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WARPED_FENCE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IRON_FENCE_DOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SPRUCE_LADDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BIRCH_LADDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JUNGLE_LADDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ACACIA_LADDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DARK_OAK_LADDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CRIMSON_LADDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WARPED_LADDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BAMBOO_LADDER, RenderLayer.getCutout());
    }
}