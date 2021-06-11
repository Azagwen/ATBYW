package net.azagwen.atbyw.main;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywTags {

    // Block Tags
    public static final Tag<Block> BOOKSHELVES = registerBlockTag("bookshelves");
    public static final Tag<Block> PISTONS = registerBlockTag("pistons");
    public static final Tag<Block> SHROOMSTICK_REPLACEABLE_GROUND = registerBlockTag("shroomstick_replaceable_ground");
    public static final Tag<Block> SHROOMSTICK_REPLACEABLE_WATER = registerBlockTag("shroomstick_replaceable_water");
    public static final Tag<Block> LARGE_CHAIN_TRANSITION_BOTTOM = registerBlockTag("large_chain_transition_bottom");
    public static final Tag<Block> LARGE_CHAIN_TRANSITION_TOP = registerBlockTag("large_chain_transition_top");

    // Item Tags
    public static final Tag<Item> ITEM_BOOKSHELVES = Tag.of(new HashSet<>(BOOKSHELVES.values().hashCode()));
    public static final Tag<Item> ITEM_PISTONS = registerItemTag("pistons");
    public static final Tag<Item> STICKS = registerItemTag("sticks.json");

    private AtbywTags() {
    }

    public static Tag<Block> registerBlockTag(String id) {
        return TagRegistry.block(AtbywID(id));
    }

    public static Tag<Item> registerItemTag(String id) {
        return TagRegistry.item(AtbywID(id));
    }
}
