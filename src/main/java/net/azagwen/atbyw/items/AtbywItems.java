package net.azagwen.atbyw.items;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.init.AtbywMain.*;

public class AtbywItems {

    public static final Item SPRUCE_STICK = new Item(new Item.Settings().group(ATBYW_ITEMS));
    public static final Item BIRCH_STICK = new Item(new Item.Settings().group(ATBYW_ITEMS));
    public static final Item JUNGLE_STICK = new Item(new Item.Settings().group(ATBYW_ITEMS));
    public static final Item ACACIA_STICK = new Item(new Item.Settings().group(ATBYW_ITEMS));
    public static final Item DARK_OAK_STICK = new Item(new Item.Settings().group(ATBYW_ITEMS));
    public static final Item CRIMSON_STICK = new Item(new Item.Settings().group(ATBYW_ITEMS));
    public static final Item WARPED_STICK = new Item(new Item.Settings().group(ATBYW_ITEMS));
    public static final Item BAMBOO_STICK = new Item(new Item.Settings().group(ATBYW_ITEMS));

    public static void init() {
        Registry.register(Registry.ITEM, newID("spruce_stick"), SPRUCE_STICK);
        Registry.register(Registry.ITEM, newID("birch_stick"), BIRCH_STICK);
        Registry.register(Registry.ITEM, newID("jungle_stick"), JUNGLE_STICK);
        Registry.register(Registry.ITEM, newID("acacia_stick"), ACACIA_STICK);
        Registry.register(Registry.ITEM, newID("dark_oak_stick"), DARK_OAK_STICK);
        Registry.register(Registry.ITEM, newID("crimson_stick"), CRIMSON_STICK);
        Registry.register(Registry.ITEM, newID("warped_stick"), WARPED_STICK);
        Registry.register(Registry.ITEM, newID("bamboo_stick"), BAMBOO_STICK);

    }
}
