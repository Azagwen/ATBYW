package net.azagwen.atbyw.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywBlockUtils {
    public static String[] WOOD_NAMES = {
            "oak",
            "spruce",
            "birch",
            "jungle",
            "acacia",
            "dark_oak",
            "crimson",
            "warped"
    };
    public static String[] WOOD_NAMES_FROM_SPRUCE = {
            WOOD_NAMES[1],
            WOOD_NAMES[2],
            WOOD_NAMES[3],
            WOOD_NAMES[4],
            WOOD_NAMES[5],
            WOOD_NAMES[6],
            WOOD_NAMES[7]
    };
    public static String[] STONE_NAMES = {
            "granite",
            "diorite",
            "andesite"
    };
    public static String[] COLOR_NAMES = {
            "white",
            "orange",
            "magenta",
            "light_blue",
            "yellow",
            "lime",
            "pink",
            "gray",
            "light_gray",
            "cyan",
            "purple",
            "blue",
            "brown",
            "green",
            "red",
            "black"
    };
    public static String[] FLOWER_NAMES = {
            "dandelion",
            "poppy",
            "blue_orchid",
            "allium",
            "azure_bluet",
            "red_tulip",
            "orange_tulip",
            "white_tulip",
            "pink_tulip",
            "oxeye_daisy",
            "cornflower",
            "lily_of_the_valley",
            "wither_rose"
    };
    public static String[] SMALL_STATUE_NAMES = {
            "bee",
            "silverfish",
            "endermite",
            "shulker",
            "wolf",
            "cat",
            "chicken",
            "rabbit",
            "fox",
            "cod",
            "salmon",
            "puffer_fish",
            "slime",
            "magma_cube"
    };

    ///////////////////////////////////////////////////////////////
    //              DECLARATION UTILS (EXPERIMENTAL)             //
    ///////////////////////////////////////////////////////////////
    public static <T extends Block> Block[] DeclareMultipleBlocks(int amount, Material material, MaterialColor materialcolor) {
        T[] block = (T[]) new Block[amount];
        for (int i = 0; i != amount; i++)
            block[i] = (T) new Block(FabricBlockSettings.of(material, materialcolor));

        return block;
    }

    //////////////////////////////////////////////////
    //              REGISTRATION UTILS              //
    //////////////////////////////////////////////////
    public static void registerBlockOnly(String name, Block block) {
        Registry.register(Registry.BLOCK, newID(name), block);
    }

    public static void registerBlock(boolean fireproof, ItemGroup group, String name, Block block) {
        Item.Settings normalSettings = new Item.Settings().group(group);
        Item.Settings fireproofSettings = new Item.Settings().group(group).fireproof();

        Registry.register(Registry.BLOCK, newID(name), block);
        Registry.register(Registry.ITEM, newID(name), new BlockItem(block, (fireproof ? fireproofSettings : normalSettings)));
    }

    public static void registerBlock(boolean fireproof, String name, Block block) {
        Item.Settings normalSettings = new Item.Settings();
        Item.Settings fireproofSettings = new Item.Settings().fireproof();

        Registry.register(Registry.BLOCK, newID(name), block);
        Registry.register(Registry.ITEM, newID(name), new BlockItem(block, (fireproof ? fireproofSettings : normalSettings)));
    }

    public static void registerBlocksOnly(String block_name, String[] variant_type, Block[] block) {
        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                registerBlockOnly((variant_type[i] + "_" + block_name), block[i]);
            }
        else
            throw new IllegalArgumentException("could not register " + block_name + " : mismatched lengths !");
    }

    public static void registerBlocks(boolean fireproof, ItemGroup group, String prefix, String block_name, String[] variant_type, Block[] block) {
        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                String name = prefix.equals("") ? (variant_type[i] + "_" + block_name) : (prefix + "_" + variant_type[i] + "_" + block_name);

                registerBlock(fireproof, group, name, block[i]);
            }
        else
            throw new IllegalArgumentException("could not register " + block_name + " : mismatched lengths !");
    }

    public static void registerBlocks(boolean fireproof, String block_name, String[] variant_type, Block[] block) {
        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                registerBlock(fireproof, (variant_type[i] + "_" + block_name), block[i]);
            }
        else
            throw new IllegalArgumentException("could not register " + block_name + " : mismatched lengths !");
    }
}
