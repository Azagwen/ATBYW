package net.azagwen.atbyw.item;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywItems {

    protected static Item.Settings createSimpleItem(@Nullable ItemGroup group, boolean fireProof) {
        var settings = fireProof ? new Item.Settings().fireproof() : new Item.Settings();

        return group != null ? settings.group(group) : settings;
    }

    protected static Item.Settings createSimpleItem() {
        return createSimpleItem(null, false);
    }

    protected static void registerItem(ArrayList<Item> itemTab, String name, Item item) {
        Registry.register(Registry.ITEM, AtbywMain.id(name), item);
        itemTab.add(item);
    }
    protected static void registerItems(ArrayList<Item> itemTab, @Nullable String prefix, String name, List<String> variant_type, Item... items) {
        if (items.length == variant_type.size())
            for (int i = 0; i < items.length; i++) {
                var newName = "";
                if (prefix == null || prefix.isEmpty()) {
                    newName = String.join("_", variant_type.get(i), name);
                } else {
                    newName = String.join("_", prefix, variant_type.get(i), name);
                }

                registerItem(itemTab, newName, items[i]);
            }
        else
            throw new IllegalArgumentException(String.join("could not register " + name + " : mismatched lengths !"));
    }

    protected static void registerItems(ArrayList<Item> itemTab, String name, List<String> variant_type, Item... items) {
        registerItems(itemTab, null, name, variant_type, items);
    }
    public static final List<EssenceItem> ESSENCE_BOTTLES = Lists.newArrayList();

    public static final Item BAMBOO_STICK = new Item(createSimpleItem());
    public static final Item SHULKER_ESSENCE = new EssenceItem(0x976997, ESSENCE_BOTTLES);
    public static final Item CHICKEN_ESSENCE = new EssenceItem(0xEDDFDF, ESSENCE_BOTTLES);
    public static final Item RABBIT_ESSENCE = new EssenceItem(0x947D62, ESSENCE_BOTTLES);
    public static final Item COD_ESSENCE = new EssenceItem(0xC2A885, ESSENCE_BOTTLES);
    public static final Item SALMON_ESSENCE = new EssenceItem(0xAB3533, ESSENCE_BOTTLES);
    public static final Item PUFFER_FISH_ESSENCE = new EssenceItem(0xD2A44D, ESSENCE_BOTTLES);
    public static final Item MAGMA_CUBE_ESSENCE = new EssenceItem(0x630000, ESSENCE_BOTTLES);
    public static final Item SLIME_ESSENCE = new EssenceItem(0x8CD782, ESSENCE_BOTTLES);
    public static final Item SHROOMSTICK = new ShroomStickItem(createSimpleItem());
    public static final Item LARGE_CHAIN_LINK = new Item(createSimpleItem());
    public static final Item GLASS_SHARD = new Item(createSimpleItem());
    public static final Item WHITE_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item ORANGE_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item MAGENTA_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item LIGHT_BLUE_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item YELLOW_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item LIME_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item PINK_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item GRAY_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item LIGHT_GRAY_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item CYAN_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item PURPLE_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item BLUE_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item BROWN_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item GREEN_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item RED_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item BLACK_STAINED_GLASS_SHARD = new Item(createSimpleItem());
    public static final Item COLORIZER = new ColorizerItem(createSimpleItem());

    public static void init() {
        registerItem(DECO_TAB, "shroomstick", SHROOMSTICK);
        registerItem(MISC_TAB, "bamboo_stick", BAMBOO_STICK);
        registerItem(MISC_TAB, "shulker_essence", SHULKER_ESSENCE);
        registerItem(MISC_TAB, "chicken_essence", CHICKEN_ESSENCE);
        registerItem(MISC_TAB, "rabbit_essence", RABBIT_ESSENCE);
        registerItem(MISC_TAB, "cod_essence", COD_ESSENCE);
        registerItem(MISC_TAB, "salmon_essence", SALMON_ESSENCE);
        registerItem(MISC_TAB, "puffer_fish_essence", PUFFER_FISH_ESSENCE);
        registerItem(MISC_TAB, "magma_cube_essence", MAGMA_CUBE_ESSENCE);
        registerItem(MISC_TAB, "slime_essence", SLIME_ESSENCE);
        registerItem(MISC_TAB, "large_chain_link", LARGE_CHAIN_LINK);
        registerItem(MISC_TAB, "glass_shard", GLASS_SHARD);
        registerItems(MISC_TAB, "stained_glass_shard", AtbywUtils.dyeColorNames(), WHITE_STAINED_GLASS_SHARD, ORANGE_STAINED_GLASS_SHARD, MAGENTA_STAINED_GLASS_SHARD, LIGHT_BLUE_STAINED_GLASS_SHARD, YELLOW_STAINED_GLASS_SHARD, LIME_STAINED_GLASS_SHARD, PINK_STAINED_GLASS_SHARD, GRAY_STAINED_GLASS_SHARD, LIGHT_GRAY_STAINED_GLASS_SHARD, CYAN_STAINED_GLASS_SHARD, PURPLE_STAINED_GLASS_SHARD, BLUE_STAINED_GLASS_SHARD, BROWN_STAINED_GLASS_SHARD, GREEN_STAINED_GLASS_SHARD, RED_STAINED_GLASS_SHARD, BLACK_STAINED_GLASS_SHARD);
        registerItem(MISC_TAB, "colorizer", COLORIZER);

        LOGGER.info("ATBYW Items Inintiliazed");
    }
}
