package net.azagwen.atbyw.main;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.HashSet;

public class Tags {

    public static class BlockTags {
        public static final Tag<Block> BOOKSHELVES_COMMON = registerBlockTag(new Identifier("c", "bookshelves"));
        public static final Tag<Block> SHROOMSTICK_REPLACEABLE_GROUND = registerBlockTag("shroomstick_replaceable_ground");
        public static final Tag<Block> SHROOMSTICK_REPLACEABLE_WATER = registerBlockTag("shroomstick_replaceable_water");
        public static final Tag<Block> LARGE_CHAIN_TRANSITION_BOTTOM = registerBlockTag("large_chain_transition_bottom");
        public static final Tag<Block> LARGE_CHAIN_TRANSITION_TOP = registerBlockTag("large_chain_transition_top");
        public static final Tag<Block> CONNECTS_TO_PIPES = registerBlockTag("connects_to_pipes");
        public static final Tag<Block> CONNECTS_TO_PIPES_AND_UPDATES = registerBlockTag("connects_to_pipes_and_updates");
    }

    public static class ItemTags {
        public static final Tag<Item> BOOKSHELVES_COMMON = blockItemTag(BlockTags.BOOKSHELVES_COMMON);
        public static final Tag<Item> SHROOMSTICK_REPLACEABLE_GROUND = blockItemTag(BlockTags.SHROOMSTICK_REPLACEABLE_GROUND);
        public static final Tag<Item> SHROOMSTICK_REPLACEABLE_WATER = blockItemTag(BlockTags.SHROOMSTICK_REPLACEABLE_WATER);
        public static final Tag<Item> LARGE_CHAIN_TRANSITION_BOTTOM = blockItemTag(BlockTags.LARGE_CHAIN_TRANSITION_BOTTOM);
        public static final Tag<Item> LARGE_CHAIN_TRANSITION_TOP = blockItemTag(BlockTags.LARGE_CHAIN_TRANSITION_TOP);
        public static final Tag<Item> CONNECTS_TO_PIPES = blockItemTag(BlockTags.CONNECTS_TO_PIPES);
        public static final Tag<Item> CONNECTS_TO_PIPES_AND_UPDATES = blockItemTag(BlockTags.CONNECTS_TO_PIPES_AND_UPDATES);
    }

    private Tags() {
    }

    public static Tag<Item> blockItemTag(Tag<Block> blockTag) {
        var set = new HashSet<Item>();
        blockTag.values().forEach((block) -> set.add(block.asItem()));
        return Tag.of(set);
    }

    public static Tag<Block> registerBlockTag(String path) {
        return TagRegistry.block(AtbywMain.id(path));
    }

    public static Tag<Block> registerBlockTag(Identifier id) {
        return TagRegistry.block(id);
    }

    public static Tag<Item> registerItemTag(String path) {
        return TagRegistry.item(AtbywMain.id(path));
    }
}
