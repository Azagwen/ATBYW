package net.azagwen.atbyw.mod_interaction.util;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import static net.azagwen.atbyw.main.AtbywMain.miId;

public class BlockUtils {
    public static String[] BETTER_NETHER_WOOD_NAMES = {
            "stalagnate",
            "reeds",
            "willow",
            "wart",
            "rubeus",
            "mushroom",
            "mushroom_fir",
            "anchor_tree",
            "nether_sakura"
    };

    public static String[] BETTER_END_WOOD_NAMES = {
            "mossy_glowshroom",
            "pythadendron",
            "end_lotus",
            "lacugrove",
            "dragon_tree",
            "tenanea",
            "helix_tree",
            "umbrella_tree"
    };

    public static void registerIfPresent(Identifier blockToLookFor, boolean fireproof, ItemGroup group, String name, Block block) {
        if (Registry.BLOCK.getOrEmpty(blockToLookFor).isPresent()) {
            registerModInteractBlock(fireproof, group, name, block);
        }
    }

    public static void registerModInteractBlock(boolean fireproof, @Nullable ItemGroup group, String name, Block block) {
        Item.Settings normalSettings = group != null ? new Item.Settings().group(group) : new Item.Settings();
        Item.Settings fireproofSettings = group != null ? new Item.Settings().group(group).fireproof() : new Item.Settings().fireproof();

        Registry.register(Registry.BLOCK, miId(name), block);
        Registry.register(Registry.ITEM, miId(name), new BlockItem(block, (fireproof ? fireproofSettings : normalSettings)));
    }

    public static void registerModInteractBlocks(boolean fireproof, @Nullable ItemGroup group, String block_name, String[] variant_type, Block[] block) {
        Item.Settings normalSettings = new Item.Settings().group(group);
        Item.Settings fireproofSettings = new Item.Settings().group(group).fireproof();

        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                Registry.register(Registry.BLOCK, miId(variant_type[i] + "_" + block_name), block[i]);
                Registry.register(Registry.ITEM, miId(variant_type[i] + "_" + block_name), new BlockItem(block[i], (fireproof ? fireproofSettings : normalSettings)));
            }
        else
            throw new IllegalArgumentException("could not register " + block_name + " : mismatched lengths !");
    }
}
