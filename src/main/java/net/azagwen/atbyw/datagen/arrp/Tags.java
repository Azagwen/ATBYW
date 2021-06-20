package net.azagwen.atbyw.datagen.arrp;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.group.AtbywItemGroup;
import net.azagwen.atbyw.items.AtbywItems;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.tags.JTag;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static net.azagwen.atbyw.datagen.arrp.AtbywRRP.ATBYW_RESOURCE_PACK;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class Tags {

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
            AtbywBlocks.SPRUCE_BOOKSHELF,
            AtbywBlocks.BIRCH_BOOKSHELF,
            AtbywBlocks.JUNGLE_BOOKSHELF,
            AtbywBlocks.ACACIA_BOOKSHELF,
            AtbywBlocks.DARK_OAK_BOOKSHELF,
            AtbywBlocks.CRIMSON_BOOKSHELF,
            AtbywBlocks.WARPED_BOOKSHELF,
            AtbywBlocks.OAK_BOOKSHELF_TOGGLE,
            AtbywBlocks.SPRUCE_BOOKSHELF_TOGGLE,
            AtbywBlocks.BIRCH_BOOKSHELF_TOGGLE,
            AtbywBlocks.JUNGLE_BOOKSHELF_TOGGLE,
            AtbywBlocks.ACACIA_BOOKSHELF_TOGGLE,
            AtbywBlocks.DARK_OAK_BOOKSHELF_TOGGLE,
            AtbywBlocks.CRIMSON_BOOKSHELF_TOGGLE,
            AtbywBlocks.WARPED_BOOKSHELF_TOGGLE
    );

    public static final ArrayList<Block> LARGE_CHAIN_TRANSITION_BOTTOM = Lists.newArrayList(
            Blocks.CHAIN,
            Blocks.LANTERN,
            Blocks.SOUL_LANTERN,
            AtbywBlocks.REDSTONE_LANTERN
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

        createBlockTag(ATBYW_RESOURCE_PACK, AtbywNamespace, "bookshelves", BOOKSHELVES);
        createBlockTag(ATBYW_RESOURCE_PACK, "c", "bookshelves", BOOKSHELVES);
        createBlockTag(ATBYW_RESOURCE_PACK, AtbywNamespace, "large_chain_transition_bottom", LARGE_CHAIN_TRANSITION_BOTTOM);
        createBlockTag(ATBYW_RESOURCE_PACK, AtbywNamespace, "large_chain_transition_top", LARGE_CHAIN_TRANSITION_TOP);

        createItemTag(ATBYW_RESOURCE_PACK, AtbywNamespace, "bookshelves", getBlockItems(BOOKSHELVES));
        createItemTag(ATBYW_RESOURCE_PACK, "c", "bookshelves", getBlockItems(BOOKSHELVES));
        createItemTag(ATBYW_RESOURCE_PACK, AtbywNamespace, "large_chain_transition_bottom", getBlockItems(LARGE_CHAIN_TRANSITION_BOTTOM));
        createItemTag(ATBYW_RESOURCE_PACK, AtbywNamespace, "large_chain_transition_top", getBlockItems(LARGE_CHAIN_TRANSITION_TOP));
    }
}
