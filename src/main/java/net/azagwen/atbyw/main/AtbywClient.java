package net.azagwen.atbyw.main;

import net.azagwen.atbyw.blocks.AtbywBlockEntities;
import net.azagwen.atbyw.blocks.statues.render.*;
import net.azagwen.atbyw.blocks.statues.render.model.ChickenStatueModel;
import net.azagwen.atbyw.blocks.statues.render.model.RabbitStatueModel;
import net.azagwen.atbyw.blocks.statues.render.model.WolfStatueModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.SilverfishEntityModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.azagwen.atbyw.blocks.AtbywBlocks.*;
import static net.azagwen.atbyw.main.AtbywMain.newID;

public class AtbywClient implements ClientModInitializer {
    private static final Logger LOGGER = LogManager.getLogger();

    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntities.RABBIT_STATUE, RabbitStatueBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntities.CHICKEN_STATUE, ChickenStatueBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntities.WOLF_STATUE, WolfStatueBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntities.SILVERFISH_STATUE, SilverfishStatueBlockEntityRenderer::new);

        BuiltinItemRendererRegistry.INSTANCE.register(RABBIT_STATUE.asItem(), new StatueItemRenderer(new RabbitStatueModel(), RabbitStatueBlockEntityRenderer.getItemTexture()));
        BuiltinItemRendererRegistry.INSTANCE.register(CHICKEN_STATUE.asItem(), new StatueItemRenderer(new ChickenStatueModel(), ChickenStatueBlockEntityRenderer.getItemTexture()));
        BuiltinItemRendererRegistry.INSTANCE.register(WOLF_STATUE.asItem(), new StatueItemRenderer(new WolfStatueModel(), WolfStatueBlockEntityRenderer.getItemTexture()));
        BuiltinItemRendererRegistry.INSTANCE.register(SILVERFISH_STATUE.asItem(), new StatueItemRenderer(new SilverfishEntityModel(), SilverfishStatueBlockEntityRenderer.getItemTexture()));

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), GRASS_BLOCK_STAIRS, GRASS_BLOCK_SLAB);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, GRASS_BLOCK_STAIRS, GRASS_BLOCK_SLAB);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return GrassColors.getColor(0.5D, 1.0D);
        }, GRASS_BLOCK_STAIRS, GRASS_BLOCK_SLAB);

        BlockRenderLayerMap.INSTANCE.putBlock(REDSTONE_LANTERN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                DANDELION_PULL_SWITCH,
                POPPY_PULL_SWITCH,
                BLUE_ORCHID_PULL_SWITCH,
                ALLIUM_PULL_SWITCH,
                AZURE_BLUET_PULL_SWITCH,
                RED_TULIP_PULL_SWITCH,
                ORANGE_TULIP_PULL_SWITCH,
                WHITE_TULIP_PULL_SWITCH,
                PINK_TULIP_PULL_SWITCH,
                OXEYE_DAISY_PULL_SWITCH,
                CORNFLOWER_PULL_SWITCH,
                WITHER_ROSE_PULL_SWITCH,
                LILY_OF_THE_VALLEY_PULL_SWITCH
        );

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