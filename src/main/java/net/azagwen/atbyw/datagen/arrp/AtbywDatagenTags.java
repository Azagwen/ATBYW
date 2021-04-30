package net.azagwen.atbyw.datagen.arrp;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.tags.JTag;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static net.azagwen.atbyw.datagen.arrp.AtbywRRP.ATBYW_RESOURCE_PACK;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywDatagenTags {

    private static void createBlockTag(RuntimeResourcePack pack, String nameSpace, String tagName, ArrayList<Block> blocks) {
        JTag tag = JTag.tag();

        for (Block block : blocks) {
            tag.add(getBlockID(block));
        }

        pack.addTag(new Identifier(nameSpace, "blocks/" + tagName), tag);
    }

    private static void createItemTag(RuntimeResourcePack pack, String nameSpace, String tagName, ArrayList<Item> items) {
        JTag tag = JTag.tag();

        for (Item item : items) {
            tag.add(getItemID(item));
        }

        pack.addTag(new Identifier(nameSpace, "items/" + tagName), tag);
    }

    public static void init() {
        createItemTag(ATBYW_RESOURCE_PACK, nameSpace, "tab_blocks", BLOCKS_TAB);
        createItemTag(ATBYW_RESOURCE_PACK, nameSpace, "tab_deco", DECO_TAB);
        createItemTag(ATBYW_RESOURCE_PACK, nameSpace, "tab_redstone", REDSTONE_TAB);
        createItemTag(ATBYW_RESOURCE_PACK, nameSpace, "tab_misc", MISC_TAB);
    }
}
