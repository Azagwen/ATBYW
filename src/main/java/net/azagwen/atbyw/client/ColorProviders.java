package net.azagwen.atbyw.client;

import net.azagwen.atbyw.block.AbstractRedstonePipeGate;
import net.azagwen.atbyw.block.registry.DecorationBlockRegistry;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.azagwen.atbyw.block.RedstonePipeBlock;
import net.azagwen.atbyw.block.entity.CanvasBlockEntity;
import net.azagwen.atbyw.block.slabs.FallingSlabBlock;
import net.azagwen.atbyw.block.stairs.FallingStairsBlock;
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
        }, DecorationBlockRegistry.CANVAS_BLOCK, DecorationBlockRegistry.GLOWING_CANVAS_BLOCK);

        //Colorizer color
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return tintIndex > 0 ? -1 : ((SimpleColoredItem) stack.getItem()).getColor(stack);
        }, AtbywItems.COLORIZER);

        //Grass stairs & slab color
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return GrassColors.getColor(0.5D, 1.0D);
        }, BuildingBlockRegistry.GRASS_BLOCK_STAIRS, BuildingBlockRegistry.GRASS_BLOCK_SLAB);

        //Essence bottle colors
        for (var item : AtbywItems.ESSENCE_BOTTLES) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                return tintIndex > 0 ? -1 : item.getColor();
            }, item);
        }

        //Redstone Pipe redstone Color
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return RedstonePipeBlock.getRedstoneColor(0);
        }, RedstoneBlockRegistry.REDSTONE_PIPE);

        //Redstone Pipe Logic Gates redstone Color
        ColorProviderRegistry.BLOCK.ITEM.register((stack, tintIndex) -> {
            return RedstonePipeBlock.getRedstoneColor(2);
        }, RedstoneBlockRegistry.REDSTONE_PIPE_REPEATER);
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
        }, DecorationBlockRegistry.CANVAS_BLOCK, DecorationBlockRegistry.GLOWING_CANVAS_BLOCK);

        //Grass stairs & slab color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, BuildingBlockRegistry.GRASS_BLOCK_STAIRS, BuildingBlockRegistry.GRASS_BLOCK_SLAB);

        //Redstone Pipe redstone Color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return RedstonePipeBlock.getRedstoneColor(state.get(RedstoneWireBlock.POWER));
        }, RedstoneBlockRegistry.REDSTONE_PIPE);

        //Redstone Pipe Logic Gates redstone Color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (state.get(AbstractRedstonePipeGate.POWERED)) {
                return RedstonePipeBlock.getRedstoneColor(15);
            }
            return RedstonePipeBlock.getRedstoneColor(2);
        }, RedstoneBlockRegistry.REDSTONE_PIPE_REPEATER);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (!state.get(AbstractRedstonePipeGate.POWERED)) {
                return RedstonePipeBlock.getRedstoneColor(15);
            }
            return RedstonePipeBlock.getRedstoneColor(2);
        }, RedstoneBlockRegistry.REDSTONE_PIPE_INVERTER);

        //Falling Stairs
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return ((FallingStairsBlock) state.getBlock()).getColor(state, world, pos);
        }, BuildingBlockRegistry.SAND_STAIRS, BuildingBlockRegistry.RED_SAND_STAIRS, BuildingBlockRegistry.GRAVEL_STAIRS);

        //Falling Slabs
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return ((FallingSlabBlock) state.getBlock()).getColor(state, world, pos);
        }, BuildingBlockRegistry.SAND_SLAB, BuildingBlockRegistry.RED_SAND_SLAB, BuildingBlockRegistry.GRAVEL_SLAB);
    }
}
