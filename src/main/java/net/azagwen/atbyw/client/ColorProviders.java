package net.azagwen.atbyw.client;

import net.azagwen.atbyw.block.AbstractRedstonePipeGate;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.RedstonePipeBlock;
import net.azagwen.atbyw.block.entity.CanvasBlockEntity;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.item.SimpleColoredItem;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.item.DyeableItem;

import java.awt.*;

public class ColorProviders {

    public static void initItems() {
        //Canvas blocks color
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return ((DyeableItem) stack.getItem()).getColor(stack);
        }, AtbywBlocks.CANVAS_BLOCK, AtbywBlocks.GLOWING_CANVAS_BLOCK);

        //Colorizer color
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return tintIndex > 0 ? -1 : ((SimpleColoredItem) stack.getItem()).getColor(stack);
        }, AtbywItems.COLORIZER);

        //Grass stairs & slab color
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return GrassColors.getColor(0.5D, 1.0D);
        }, AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        //Essence bottle colors
        for (var item : AtbywItems.ESSENCE_BOTTLES) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                return tintIndex > 0 ? -1 : item.getColor();
            }, item);
        }

        //Redstone Pipe redstone Color
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return RedstonePipeBlock.getRedstoneColor(0);
        }, AtbywBlocks.REDSTONE_PIPE);

        //Redstone Pipe Logic Gates redstone Color
        ColorProviderRegistry.BLOCK.ITEM.register((stack, tintIndex) -> {
            return RedstonePipeBlock.getRedstoneColor(2);
        }, AtbywBlocks.REDSTONE_PIPE_REPEATER);
    }

    public static void initBlocks() {
        //Color picker block color
        //!!! must only contain instances of ColorPickerBlock !!!
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            var color = Color.WHITE.getRGB();
            if (world != null && pos != null) {
                var entity = world.getBlockEntity(pos);
                if (entity instanceof CanvasBlockEntity blockEntity) {
                    color = blockEntity.getColor();
                }
            }
            return color;
        }, AtbywBlocks.CANVAS_BLOCK, AtbywBlocks.GLOWING_CANVAS_BLOCK);

        //Grass stairs & slab color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        //Redstone Pipe redstone Color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return RedstonePipeBlock.getRedstoneColor(state.get(RedstoneWireBlock.POWER));
        }, AtbywBlocks.REDSTONE_PIPE);

        //Redstone Pipe Logic Gates redstone Color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (state.get(AbstractRedstonePipeGate.POWERED)) {
                return RedstonePipeBlock.getRedstoneColor(15);
            }
            return RedstonePipeBlock.getRedstoneColor(2);
        }, AtbywBlocks.REDSTONE_PIPE_REPEATER);
    }
}
