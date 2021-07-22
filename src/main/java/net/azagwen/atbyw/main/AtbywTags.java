package net.azagwen.atbyw.main;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.HashSet;

public class AtbywTags {

    // Block Tags
    public static final Tag<Block> BOOKSHELVES = registerBlockTag("bookshelves");
    public static final Tag<Block> BOOKSHELVES_COMMON = registerBlockTag(new Identifier("c", "bookshelves"));
    public static final Tag<Block> SHROOMSTICK_REPLACEABLE_GROUND = registerBlockTag("shroomstick_replaceable_ground");
    public static final Tag<Block> SHROOMSTICK_REPLACEABLE_WATER = registerBlockTag("shroomstick_replaceable_water");
    public static final Tag<Block> LARGE_CHAIN_TRANSITION_BOTTOM = registerBlockTag("large_chain_transition_bottom");
    public static final Tag<Block> LARGE_CHAIN_TRANSITION_TOP = registerBlockTag("large_chain_transition_top");
    public static final Tag<Block> CONNECTS_TO_PIPES = registerBlockTag("connects_to_pipes");
    public static final Tag<Block> CONNECTS_TO_PIPES_AND_UPDATES = registerBlockTag("connects_to_pipes_and_updates");

    private AtbywTags() {
    }

    public static Tag<Item> blockItemTag(Tag<Block> blockTag) {
        var set = new HashSet<Item>();

        blockTag.values().forEach((block) -> {
            set.add(block.asItem());
        });

        return Tag.of(set);
    }

    public static Tag<Block> registerBlockTag(String id) {
        return TagRegistry.block(AtbywMain.id(id));
    }

    public static Tag<Block> registerBlockTag(Identifier id) {
        return TagRegistry.block(id);
    }

    public static Tag<Item> registerItemTag(String id) {
        return TagRegistry.item(AtbywMain.id(id));
    }
}
