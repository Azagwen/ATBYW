package net.azagwen.atbyw.client;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.entity.AtbywBlockEntityTypes;
import net.azagwen.atbyw.block.entity.ColorPickerBlockEntity;
import net.azagwen.atbyw.client.render.AtbywBlockRenderLayers;
import net.azagwen.atbyw.client.render.AtbywEntityModelLayers;
import net.azagwen.atbyw.client.render.TimerRepeaterBlockEntityRenderer;
import net.azagwen.atbyw.item.AtbywItems;
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

import static net.azagwen.atbyw.item.AtbywItems.*;

@SuppressWarnings("deprecation")
public class AtbywClient implements ClientModInitializer {

    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {

        //noinspection UnstableApiUsage
        EntityModelLayerRegistry.registerModelLayer(AtbywEntityModelLayers.TIMER_REPEATER, TimerRepeaterBlockEntityRenderer::getTexturedModelData);
        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntityTypes.TIMER_REPEATER_ENTITY, TimerRepeaterBlockEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(AtbywEntityTypes.SHROOMSTICK, FlyingItemEntityRenderer::new);

        //Color picker block color
        //!!! must only contain instances of ColorPickerBlock !!!
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            var color = Color.WHITE.getRGB();
            if (world != null && pos != null) {
                var entity = world.getBlockEntity(pos);
                if (entity instanceof ColorPickerBlockEntity blockEntity){
                    color = blockEntity.getColor().getRGB();
                }
            }
            return color;
        }, AtbywBlocks.COLOR_PICKER_BLOCK);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return ((DyeableItem)stack.getItem()).getColor(stack);
        }, AtbywItems.COLOR_PICKER_BLOCK);

        //Grass stairs & slab color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return GrassColors.getColor(0.5D, 1.0D);
        }, AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        //Essence bottle colors
        for (var item : ESSENCE_BOTTLES) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                return tintIndex > 0 ? -1 : item.getColor();
            }, item);
        }

        //Render layers
        AtbywBlockRenderLayers.init();
    }
}