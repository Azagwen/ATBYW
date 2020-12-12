package net.azagwen.atbyw.blocks;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import static net.azagwen.atbyw.init.AtbywMain.*;

public class AtbywBlockTags {
    public static final Tag<Block> BOOKSHELVES = registerBlockTag("bookshelves");

    //Block item tags
    public static final Tag<Item> BOOKSHELVES_I = registerItemTag("bookshelves");

    private AtbywBlockTags() {
    }

    private static Tag<Block> registerBlockTag(String id) {
        return TagRegistry.block(newID(id));
    }
    private static Tag<Item> registerItemTag(String id) {
        return TagRegistry.item(newID(id));
    }
}
