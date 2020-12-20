package net.azagwen.atbyw.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;

import static net.azagwen.atbyw.blocks.AtbywBlocks.*;

public class AtbywClient implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), GRASS_BLOCK_STAIRS, GRASS_BLOCK_SLAB);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, GRASS_BLOCK_STAIRS, GRASS_BLOCK_SLAB);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return GrassColors.getColor(0.5D, 1.0D);
        }, GRASS_BLOCK_STAIRS, GRASS_BLOCK_SLAB);



        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                OAK_FENCE_DOOR,
                SPRUCE_FENCE_DOOR,
                BIRCH_FENCE_DOOR,
                JUNGLE_FENCE_DOOR,
                ACACIA_FENCE_DOOR,
                DARK_OAK_FENCE_DOOR,
                CRIMSON_FENCE_DOOR,
                WARPED_FENCE_DOOR,
                IRON_FENCE_DOOR
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                SPRUCE_LADDER,
                BIRCH_LADDER,
                JUNGLE_LADDER,
                ACACIA_LADDER,
                DARK_OAK_LADDER,
                CRIMSON_LADDER,
                WARPED_LADDER,
                BAMBOO_LADDER
        );
    }
}