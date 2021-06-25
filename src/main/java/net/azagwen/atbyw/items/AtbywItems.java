package net.azagwen.atbyw.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywItems {

    protected static Item.Settings createSimpleItem(@Nullable ItemGroup group) {
        return group != null ? new Item.Settings().group(group) : new Item.Settings();
    }

    protected static void registerItem(ArrayList<Item> itemTab, String name, Item item) {
        Registry.register(Registry.ITEM, AtbywID(name), item);
        itemTab.add(item);
    }
    public static final Item BAMBOO_STICK = new Item(createSimpleItem(null));

    public static final Item SHULKER_ESSENCE = new EssenceItem(0x976997);
    public static final Item CHICKEN_ESSENCE = new EssenceItem(0xEDDFDF);
    public static final Item RABBIT_ESSENCE = new EssenceItem(0x947D62);
    public static final Item COD_ESSENCE = new EssenceItem(0xC2A885);
    public static final Item SALMON_ESSENCE = new EssenceItem(0xAB3533);
    public static final Item PUFFER_FISH_ESSENCE = new EssenceItem(0xD2A44D);
    public static final Item MAGMA_CUBE_ESSENCE = new EssenceItem(0x630000);
    public static final Item SLIME_ESSENCE = new EssenceItem(0x8CD782);

    public static final Item SHROOMSTICK = new ShroomStickItem(createSimpleItem(null));

    public static final Item LARGE_CHAIN_LINK = new Item(createSimpleItem(null));

    public static final Item[] ESSENCE_BOTTLES = {
            SHULKER_ESSENCE,
            CHICKEN_ESSENCE,
            RABBIT_ESSENCE,
            COD_ESSENCE,
            SALMON_ESSENCE,
            PUFFER_FISH_ESSENCE,
            MAGMA_CUBE_ESSENCE,
            SLIME_ESSENCE
    };

    public static void init() {
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

        registerItem(DECO_TAB, "shroomstick", SHROOMSTICK);

        AtbywModInteractionItems.init();

        LOGGER.info("ATBYW Items Inintiliazed");
    }
}
