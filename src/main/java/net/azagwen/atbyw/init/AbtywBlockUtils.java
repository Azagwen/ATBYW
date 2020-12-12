package net.azagwen.atbyw.init;

import net.azagwen.atbyw.blocks.StairsBlockSubClass;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.init.AtbywMain.*;

public class AbtywBlockUtils {
    public static String[] woodNames = new String[] {"oak", "spruce", "birch", "jungle", "acacia", "dar_oak", "crimson", "warped"};
    public static String[] stoneNames = new String[] {"granite", "diorite", "andesite"};
    public static String[] colorNames = new String[] {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};

    public static final Block[] TERRACOTTA_COLORS = {
            Blocks.WHITE_TERRACOTTA,
            Blocks.ORANGE_TERRACOTTA,
            Blocks.MAGENTA_TERRACOTTA,
            Blocks.LIGHT_BLUE_TERRACOTTA,
            Blocks.YELLOW_TERRACOTTA,
            Blocks.LIME_TERRACOTTA,
            Blocks.PINK_TERRACOTTA,
            Blocks.GRAY_TERRACOTTA,
            Blocks.LIGHT_GRAY_TERRACOTTA,
            Blocks.CYAN_TERRACOTTA,
            Blocks.PURPLE_TERRACOTTA,
            Blocks.BLUE_TERRACOTTA,
            Blocks.BROWN_TERRACOTTA,
            Blocks.GREEN_TERRACOTTA,
            Blocks.RED_TERRACOTTA,
            Blocks.BLACK_TERRACOTTA
    };

    /////////////////////////////////////////////////
    //              DECLARATION UTILS              //
    /////////////////////////////////////////////////
    public static Block[] MakeMultiBlock(int amount, FabricBlockSettings settings) {
        Block[] block = new Block[amount];
        for (int i = 0; i != amount; i++) {
            block[i] = new Block(settings);
        }

        return block;
    }
    public static StairsBlockSubClass[] MakeMultiStairs(int amount, Block[] type) {
        StairsBlockSubClass[] block = new StairsBlockSubClass[amount];
        for (int i = 0; i != amount; i++) {
            block[i] = new StairsBlockSubClass(type[i].getDefaultState(), FabricBlockSettings.copy(type[i]));
        }

        return block;
    }
    public static SlabBlock[] MakeMultiSlab(int amount, Block[] type) {
        SlabBlock[] block = new SlabBlock[amount];
        for (int i = 0; i != amount; i++) {
            block[i] = new SlabBlock(FabricBlockSettings.copy(type[i]));
        }

        return block;
    }
    public static WallBlock[] MakeMultiWall(int amount, Block[] type) {
        WallBlock[] block = new WallBlock[amount];
        for (int i = 0; i != amount; i++) {
            block[i] = new WallBlock(FabricBlockSettings.copy(type[i]));
        }

        return block;
    }

    //////////////////////////////////////////////////
    //              REGISTRATION UTILS              //
    //////////////////////////////////////////////////
    public static void registerBlock(boolean fireproof, ItemGroup group, String name, Block block) {
        Item.Settings normalSettings = new Item.Settings().group(group);
        Item.Settings fireproofSettings = new Item.Settings().group(group).fireproof();

        Registry.register(Registry.BLOCK, newID(name), block);
        Registry.register(Registry.ITEM, newID(name), new BlockItem(block, (fireproof ? fireproofSettings : normalSettings)));
    }

    public static void registerMultiBlock(boolean fireproof, ItemGroup group, String block_name, String[] variant_type, Block[] block) {
        Item.Settings normalSettings = new Item.Settings().group(group);
        Item.Settings fireproofSettings = new Item.Settings().group(group).fireproof();

        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                Registry.register(Registry.BLOCK, newID(variant_type[i] + "_" + block_name), block[i]);
                Registry.register(Registry.ITEM, newID(variant_type[i] + "_" + block_name), new BlockItem(block[i], (fireproof ? fireproofSettings : normalSettings)));
            }
        else
            System.out.println("could not register " + block + " : mismatched lengths !");
    }
}
