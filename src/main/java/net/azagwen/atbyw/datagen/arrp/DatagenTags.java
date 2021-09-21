package net.azagwen.atbyw.datagen.arrp;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.azagwen.atbyw.group.AtbywItemGroup;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.tags.JTag;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static net.azagwen.atbyw.datagen.arrp.AtbywRRP.ATBYW_RESOURCE_PACK;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class DatagenTags {

    private static void createBlockTag(RuntimeResourcePack pack, String nameSpace, String tagName, ArrayList<Block> blocks) {
        JTag tag = JTag.tag();

        for (Block block : blocks) {
            tag.add(getBlockID(block));
        }

        pack.addTag(new Identifier(nameSpace, "blocks/" + tagName), tag);
    }

    public static void createItemTag(RuntimeResourcePack pack, String nameSpace, String tagName, ArrayList<Item> items) {
        JTag tag = JTag.tag();

        for (Item item : items) {
            tag.add(getItemID(item));
        }

        pack.addTag(new Identifier(nameSpace, "items/" + tagName), tag);
    }

    public static final ArrayList<Block> BOOKSHELVES = Lists.newArrayList(
            BuildingBlockRegistry.SPRUCE_BOOKSHELF,
            BuildingBlockRegistry.BIRCH_BOOKSHELF,
            BuildingBlockRegistry.JUNGLE_BOOKSHELF,
            BuildingBlockRegistry.ACACIA_BOOKSHELF,
            BuildingBlockRegistry.DARK_OAK_BOOKSHELF,
            BuildingBlockRegistry.CRIMSON_BOOKSHELF,
            BuildingBlockRegistry.WARPED_BOOKSHELF,
            RedstoneBlockRegistry.OAK_BOOKSHELF_TOGGLE,
            RedstoneBlockRegistry.SPRUCE_BOOKSHELF_TOGGLE,
            RedstoneBlockRegistry.BIRCH_BOOKSHELF_TOGGLE,
            RedstoneBlockRegistry.JUNGLE_BOOKSHELF_TOGGLE,
            RedstoneBlockRegistry.ACACIA_BOOKSHELF_TOGGLE,
            RedstoneBlockRegistry.DARK_OAK_BOOKSHELF_TOGGLE,
            RedstoneBlockRegistry.CRIMSON_BOOKSHELF_TOGGLE,
            RedstoneBlockRegistry.WARPED_BOOKSHELF_TOGGLE
    );

    public static final ArrayList<Block> LARGE_CHAIN_TRANSITION_BOTTOM = Lists.newArrayList(
            Blocks.CHAIN,
            Blocks.LANTERN,
            Blocks.SOUL_LANTERN,
            RedstoneBlockRegistry.REDSTONE_LANTERN
    );

    public static final ArrayList<Block> LARGE_CHAIN_TRANSITION_TOP = Lists.newArrayList(
            Blocks.CHAIN
    );

    private static ArrayList<Item> getBlockItems(ArrayList<Block> blockList) {
        ArrayList<Item> blockItems = Lists.newArrayList();

        for (Block block : blockList) {
            blockItems.add(block.asItem());
        }

        return blockItems;
    }


    public static void init() {
        AtbywItemGroup.registerTags();

        createBlockTag(ATBYW_RESOURCE_PACK, ATBYW, "large_chain_transition_bottom", LARGE_CHAIN_TRANSITION_BOTTOM);
        createBlockTag(ATBYW_RESOURCE_PACK, ATBYW, "large_chain_transition_top", LARGE_CHAIN_TRANSITION_TOP);

        createItemTag(ATBYW_RESOURCE_PACK, ATBYW, "bookshelves", getBlockItems(BOOKSHELVES));
        createItemTag(ATBYW_RESOURCE_PACK, ATBYW, "large_chain_transition_bottom", getBlockItems(LARGE_CHAIN_TRANSITION_BOTTOM));
        createItemTag(ATBYW_RESOURCE_PACK, ATBYW, "large_chain_transition_top", getBlockItems(LARGE_CHAIN_TRANSITION_TOP));
    }
}
