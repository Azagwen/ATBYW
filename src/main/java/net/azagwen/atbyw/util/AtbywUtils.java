package net.azagwen.atbyw.util;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

import static net.azagwen.atbyw.main.AtbywMain.*;

public record AtbywUtils() {

    public static List<String> dyeColorNames() {
        var list = Lists.<String>newArrayList();
        for (var color : DyeColor.values()) {
            list.add(color.getName());
        }
        return list;
    }

    public static List<Integer> dyeColorIds() {
        var list = Lists.<Integer>newArrayList();
        for (var color : DyeColor.values()) {
            list.add(color.getId());
        }
        return list;
    }

    public static int getRed(int color) {
        return (color >> 16) & 0xFF;
    }

    public static int getGreen(int color) {
        return (color >> 8) & 0xFF;
    }

    public static int getBlue(int color) {
        return (color >> 0) & 0xFF;
    }

    public static String getHexFromColor(Color color) {
        return "#"+Integer.toHexString(color.getRGB()).substring(2);

    }

    public static String getHexFromColor(int color) {
        return "#"+Integer.toHexString(color).substring(2);

    }

    public static JsonArray jsonArray(Object... elements) {
        var array = new JsonArray();
        for (var element : elements) {
            if (element instanceof Number)
                array.add((Number) element);
            else if (element instanceof Boolean)
                array.add((Boolean) element);
            else if (element instanceof Character)
                array.add((Character) element);
            else if (element instanceof JsonElement)
                array.add((JsonElement) element);
            else
                array.add(String.valueOf(element));

        }
        return array;
    }

    @SafeVarargs
    public static JsonObject jsonObject(Pair<String, Object>... elements) {
        var object = new JsonObject();
        for (var element : elements) {
            if (element.getSecond() instanceof Number)
                object.addProperty(element.getFirst(), (Number) element.getSecond());
            else if (element.getSecond() instanceof Boolean)
                object.addProperty(element.getFirst(), (Boolean) element.getSecond());
            else if (element.getSecond() instanceof Character)
                object.addProperty(element.getFirst(), (Character) element.getSecond());
            else if (element.getSecond() instanceof JsonElement)
                object.add(element.getFirst(), (JsonElement) element.getSecond());
            else
                object.addProperty(element.getFirst(), String.valueOf(element.getSecond()));
        }
        return object;
    }

    public static Identifier getItemID(Item item) {
        return Registry.ITEM.getId(item);
    }

    public static Identifier getBlockID(Block block) {
        return Registry.BLOCK.getId(block);
    }

    public static Item getItemFromID(Identifier identifier) {
        return Registry.ITEM.get(identifier);
    }

    public static Block getBlockFromID(Identifier identifier) {
        return Registry.BLOCK.get(identifier);
    }

    public static Box makeCenteredInflatableBox(BlockPos blockPos, float boxSize, float inflateFac) {
        float minBoxSize = 0.5F - (boxSize / 2);
        float maxBoxSize = 0.5F + (boxSize / 2);

        double minX = blockPos.getX() + minBoxSize - inflateFac;
        double minY = blockPos.getY() + minBoxSize - inflateFac;
        double minZ = blockPos.getZ() + minBoxSize - inflateFac;
        double maxX = blockPos.getX() + maxBoxSize + inflateFac;
        double maxY = blockPos.getY() + maxBoxSize + inflateFac;
        double maxZ = blockPos.getZ() + maxBoxSize + inflateFac;

        return new Box(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public static BlockPos createSubstractionBlockPos(BlockPos firstPos, BlockPos secondPos) {
        float X = firstPos.getX() - secondPos.getX();
        float Y = firstPos.getY() - secondPos.getY();
        float Z = firstPos.getZ() - secondPos.getZ();

        return new BlockPos(X, Y, Z);
    }

    @Environment(EnvType.CLIENT)
    public static void drawBox(MatrixStack matrices, VertexConsumer vertexConsumer, Box box, ColorRGB color, float alpha) {
        WorldRenderer.drawBox(matrices, vertexConsumer, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, color.redNormalized, color.greenNormalized, color.blueNormalized, alpha, color.redNormalized, color.greenNormalized, color.blueNormalized);
    }
}
