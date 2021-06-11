package net.azagwen.atbyw.util;

import net.azagwen.atbyw.datagen.arrp.AtbywDatagenTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywUtils {
    public static final String[] WOOD_NAMES = {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "crimson", "warped"};
    public static final String[] WOOD_NAMES_NO_OAK = {"spruce", "birch", "jungle", "acacia", "dark_oak", "crimson", "warped"};
    public static final String[] STONE_NAMES = {"granite", "diorite", "andesite"};
    public static final String[] FLOWER_NAMES = {"dandelion", "poppy", "blue_orchid", "allium", "azure_bluet", "red_tulip", "orange_tulip", "white_tulip", "pink_tulip", "oxeye_daisy", "cornflower", "lily_of_the_valley", "wither_rose"};
    public static final String[] COLOR_NAMES = {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};
    public static final String[] TOOL_MATERIALS = {"wooden", "stone", "iron", "golden", "diamond", "netherite"};
    public static final Item[] DYES = {Items.WHITE_DYE, Items.ORANGE_DYE, Items.MAGENTA_DYE, Items.LIGHT_BLUE_DYE, Items.YELLOW_DYE, Items.LIME_DYE, Items.PINK_DYE, Items.GRAY_DYE, Items.LIGHT_GRAY_DYE, Items.CYAN_DYE, Items.PURPLE_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.GREEN_DYE, Items.RED_DYE, Items.BLACK_DYE};

    public static int WHITE = 0;
    public static int ORANGE = 1;
    public static int MAGENTA = 2;
    public static int LIGHT_BLUE = 3;
    public static int YELLOW = 4;
    public static int LIME = 5;
    public static int PINK = 6;
    public static int GRAY = 7;
    public static int LIGHT_GRAY = 8;
    public static int CYAN = 9;
    public static int PURPLE = 10;
    public static int BLUE = 11;
    public static int BROWN = 12;
    public static int GREEN = 13;
    public static int RED = 14;
    public static int BLACK = 15;


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

    public boolean isReceivingModularRedstonePower(World world, BlockPos pos) {

        if (world.getEmittedRedstonePower(pos.down(), Direction.DOWN) > 0) {
            return true;
        } else if (world.getEmittedRedstonePower(pos.up(), Direction.UP) > 0) {
            return true;
        } else if (world.getEmittedRedstonePower(pos.north(), Direction.NORTH) > 0) {
            return true;
        } else if (world.getEmittedRedstonePower(pos.south(), Direction.SOUTH) > 0) {
            return true;
        } else if (world.getEmittedRedstonePower(pos.west(), Direction.WEST) > 0) {
            return true;
        } else {
            return (world.getEmittedRedstonePower(pos.east(), Direction.EAST) > 0);
        }
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

    //////////////////////////////////////////////////
    //              REGISTRATION UTILS              //
    //////////////////////////////////////////////////

    /** Registers a block without a block item.
     *
     *  @param name     Name of the block (path)
     *  @param block    The Block field
     */
    public static void registerBlockOnly(String name, Block block) {
        Registry.register(Registry.BLOCK, AtbywID(name), block);
    }

    /** Registers a block and its block item.
     *
     *  @param fireproof    if the Block item should resist to fire & Lava.
     *  @param group        the ItemGroup this block should be in.
     *  @param name         Name of the block (Identifier path).
     *  @param block        The declared Block that will be registered.
     */
    public static void registerBlock(boolean fireproof, @Nullable ItemGroup group, String name, Block block) {
        Item.Settings normalSettings = group != null ? new Item.Settings().group(group) : new Item.Settings();
        Item.Settings fireproofSettings = group != null ? new Item.Settings().group(group).fireproof() : new Item.Settings().fireproof();

        Registry.register(Registry.BLOCK, AtbywID(name), block);
        Registry.register(Registry.ITEM, AtbywID(name), new BlockItem(block, (fireproof ? fireproofSettings : normalSettings)));
    }

    public static void registerBlock(boolean fireproof, String name, Block block) {
        registerBlock(fireproof, (ItemGroup) null, name, block);
    }

    /** Registers a block and its block item.
     *
     *  @param fireproof    if the Block item should resist to fire & Lava.
     *  @param itemTab      the ItemTab list this block should be in.
     *  @param name         Name of the block (Identifier path).
     *  @param block        The declared Block that will be registered.
     */
    public static void registerBlock(boolean fireproof, ArrayList<Item> itemTab, String name, Block block) {
        Item.Settings normalSettings = new Item.Settings();
        Item.Settings fireproofSettings = new Item.Settings().fireproof();

        Registry.register(Registry.BLOCK, AtbywID(name), block);
        Registry.register(Registry.ITEM, AtbywID(name), new BlockItem(block, (fireproof ? fireproofSettings : normalSettings)));

        itemTab.add(block.asItem());
    }

    /** Will only register blocks, without block items associated to them.
     *  Registers a given amount of blocks determined by "block" and "variant_type"'s length,
     *  those two arrays MUST match in order to register those blocks, if the lengths mismatch
     *  the game will crash on its own and notify you of that mistake.
     *
     *  @param block_name       The name of the block.
     *  @param variant_type     An array of Strings of which every index will be put between "prefix" and "block_name".
     *  @param block            An Array of Blocks that must match the length of "variant_type".
     */
    public static void registerBlocksOnly(String block_name, String[] variant_type, Block... block) {
        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                registerBlockOnly(String.join("_", variant_type[i], block_name), block[i]);
            }
        else
            throw new IllegalArgumentException("could not register " + block_name + " : mismatched lengths !");
    }

    /** Registers a given amount of blocks determined by "block" and "variant_type"'s length,
     *  those two arrays MUST match in order to register those blocks, if the lengths mismatch
     *  the game will crash on its own and notify you of that mistake.
     *
     *  @param fireproof        Passed to the item registry to determine if the block item should burn in fire and lava or not.
     *  @param group            The Creative tab the block should appear in.
     *  @param prefix           Optional, a prefix that will be added in front of the "block_name".
     *  @param block_name       The name of the block.
     *  @param variant_type     An array of Strings of which every index will be put between "prefix" and "block_name".
     *  @param block            An Array of Blocks that must match the length of "variant_type".
     */
    public static void registerBlocks(boolean fireproof, @Nullable ItemGroup group, @Nullable String prefix, String block_name, String[] variant_type, Block... block) {
        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                String name;
                if (prefix == null || prefix.isEmpty()) {
                    name = String.join("_", variant_type[i], block_name);
                } else {
                    name = String.join("_", prefix, variant_type[i], block_name);
                }

                registerBlock(fireproof, group, name, block[i]);
            }
        else
            throw new IllegalArgumentException(String.join("could not register " + block_name + " : mismatched lengths !"));
    }

    public static void registerBlocks(boolean fireproof, @Nullable String prefix, String block_name, String[] variant_type, Block... block) {
        registerBlocks(fireproof, (ItemGroup) null, prefix, block_name, variant_type, block);
    }

    /** Registers a given amount of blocks determined by "block" and "variant_type"'s length,
     *  those two arrays MUST match in order to register those blocks, if the lengths mismatch
     *  the game will crash on its own and notify you of that mistake.
     *
     *  @param fireproof    if the Block item should resist to fire & Lava.
     *  @param itemTab      the ItemTab list this block should be in.
     *  @param prefix           Optional, a prefix that will be added in front of the "block_name".
     *  @param block_name       The name of the block.
     *  @param variant_type     An array of Strings of which every index will be put between "prefix" and "block_name".
     *  @param block            An Array of Blocks that must match the length of "variant_type".
     */
    public static void registerBlocks(boolean fireproof, ArrayList<Item> itemTab, @Nullable String prefix, String block_name, String[] variant_type, Block... block) {
        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                String name;
                if (prefix == null || prefix.isEmpty()) {
                    name = String.join("_", variant_type[i], block_name);
                } else {
                    name = String.join("_", prefix, variant_type[i], block_name);
                }

                registerBlock(fireproof, itemTab, name, block[i]);
            }
        else
            throw new IllegalArgumentException(String.join("could not register " + block_name + " : mismatched lengths !"));
    }
}
