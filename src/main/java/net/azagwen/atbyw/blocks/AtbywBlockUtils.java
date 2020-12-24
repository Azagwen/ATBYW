package net.azagwen.atbyw.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import static net.azagwen.atbyw.init.AtbywMain.*;

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
            "wither_rose",
            "lily_of_the_valley"
    };

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
    public static final Block[] CONCRETE_COLORS = {
            Blocks.WHITE_CONCRETE,
            Blocks.ORANGE_CONCRETE,
            Blocks.MAGENTA_CONCRETE,
            Blocks.LIGHT_BLUE_CONCRETE,
            Blocks.YELLOW_CONCRETE,
            Blocks.LIME_CONCRETE,
            Blocks.PINK_CONCRETE,
            Blocks.GRAY_CONCRETE,
            Blocks.LIGHT_GRAY_CONCRETE,
            Blocks.CYAN_CONCRETE,
            Blocks.PURPLE_CONCRETE,
            Blocks.BLUE_CONCRETE,
            Blocks.BROWN_CONCRETE,
            Blocks.GREEN_CONCRETE,
            Blocks.RED_CONCRETE,
            Blocks.BLACK_CONCRETE
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
    public static void registerBlock(boolean fireproof, ItemGroup group, String name, Block block) {
        Item.Settings normalSettings = new Item.Settings().group(group);
        Item.Settings fireproofSettings = new Item.Settings().group(group).fireproof();

        Registry.register(Registry.BLOCK, newID(name), block);
        Registry.register(Registry.ITEM, newID(name), new BlockItem(block, (fireproof ? fireproofSettings : normalSettings)));
    }

    public static void registerBlocks(boolean fireproof, ItemGroup group, String block_name, String[] variant_type, Block[] block) {
        Item.Settings normalSettings = new Item.Settings().group(group);
        Item.Settings fireproofSettings = new Item.Settings().group(group).fireproof();

        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                Registry.register(Registry.BLOCK, newID(variant_type[i] + "_" + block_name), block[i]);
                Registry.register(Registry.ITEM, newID(variant_type[i] + "_" + block_name), new BlockItem(block[i], (fireproof ? fireproofSettings : normalSettings)));
            }
        else
            System.out.println("could not register " + block[0].getTranslationKey() + " : mismatched lengths !");
    }
}
