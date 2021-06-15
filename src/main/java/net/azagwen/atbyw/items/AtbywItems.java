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
        Registry.register(Registry.ITEM, NewAtbywID(name), item);
        itemTab.add(item);
    }
    public static final Item BAMBOO_STICK = new Item(createSimpleItem(null));

    public static final Item BEE_ESSENCE = new EssenceItem(0xFFC43B);
    public static final Item SILVERFISH_ESSENCE = new EssenceItem(0x7D7D7D);
    public static final Item ENDERMITE_ESSENCE = new EssenceItem(0x4E3865);
    public static final Item SHULKER_ESSENCE = new EssenceItem(0x976997);
    public static final Item WOLF_ESSENCE = new EssenceItem(0xD4D4D4);
    public static final Item CAT_ESSENCE = new EssenceItem(0xFFA03B);
    public static final Item CHICKEN_ESSENCE = new EssenceItem(0xEDDFDF);
    public static final Item RABBIT_ESSENCE = new EssenceItem(0x947D62);
    public static final Item FOX_ESSENCE = new EssenceItem(0xE27C21);
    public static final Item COD_ESSENCE = new EssenceItem(0xC2A885);
    public static final Item SALMON_ESSENCE = new EssenceItem(0xAB3533);
    public static final Item PUFFER_FISH_ESSENCE = new EssenceItem(0xD2A44D);
    public static final Item MAGMA_CUBE_ESSENCE = new EssenceItem(0x630000);
    public static final Item SLIME_ESSENCE = new EssenceItem(0x8CD782);

    public static final Item SHROOMSTICK = new ShroomStickItem(createSimpleItem(null));

    public static final Item LARGE_CHAIN_LINK = new Item(createSimpleItem(null));

    public static final Item[] ESSENCE_BOTTLES = {
            BEE_ESSENCE,
            SILVERFISH_ESSENCE,
            ENDERMITE_ESSENCE,
            SHULKER_ESSENCE,
            WOLF_ESSENCE,
            CAT_ESSENCE,
            CHICKEN_ESSENCE,
            RABBIT_ESSENCE,
            FOX_ESSENCE,
            COD_ESSENCE,
            SALMON_ESSENCE,
            PUFFER_FISH_ESSENCE,
            MAGMA_CUBE_ESSENCE,
            SLIME_ESSENCE
    };

    public static void init() {
        registerItem(MISC_TAB, "bamboo_stick", BAMBOO_STICK);

        registerItem(MISC_TAB, "bee_essence", BEE_ESSENCE);
        registerItem(MISC_TAB, "silverfish_essence", SILVERFISH_ESSENCE);
        registerItem(MISC_TAB, "endermite_essence", ENDERMITE_ESSENCE);
        registerItem(MISC_TAB, "shulker_essence", SHULKER_ESSENCE);
        registerItem(MISC_TAB, "wolf_essence", WOLF_ESSENCE);
        registerItem(MISC_TAB, "cat_essence", CAT_ESSENCE);
        registerItem(MISC_TAB, "chicken_essence", CHICKEN_ESSENCE);
        registerItem(MISC_TAB, "rabbit_essence", RABBIT_ESSENCE);
        registerItem(MISC_TAB, "fox_essence", FOX_ESSENCE);
        registerItem(MISC_TAB, "cod_essence", COD_ESSENCE);
        registerItem(MISC_TAB, "salmon_essence", SALMON_ESSENCE);
        registerItem(MISC_TAB, "puffer_fish_essence", PUFFER_FISH_ESSENCE);
        registerItem(MISC_TAB, "magma_cube_essence", MAGMA_CUBE_ESSENCE);
        registerItem(MISC_TAB, "slime_essence", SLIME_ESSENCE);

        registerItem(MISC_TAB, "large_chain_link", LARGE_CHAIN_LINK);

        registerItem(DECO_TAB, "shroomstick", SHROOMSTICK);

        LOGGER.info("ATBYW Items Inintiliazed");
    }
}
