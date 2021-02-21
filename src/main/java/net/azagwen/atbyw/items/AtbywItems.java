package net.azagwen.atbyw.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywItems {

    protected static Item.Settings createSimpleItem(ItemGroup group) {
        return new Item.Settings().group(group);
    }

    public static final Item OAK_STICK = new Item(createSimpleItem(ATBYW_MISC));
    public static final Item SPRUCE_STICK = new Item(createSimpleItem(ATBYW_MISC));
    public static final Item BIRCH_STICK = new Item(createSimpleItem(ATBYW_MISC));
    public static final Item JUNGLE_STICK = new Item(createSimpleItem(ATBYW_MISC));
    public static final Item ACACIA_STICK = new Item(createSimpleItem(ATBYW_MISC));
    public static final Item DARK_OAK_STICK = new Item(createSimpleItem(ATBYW_MISC));
    public static final Item CRIMSON_STICK = new Item(createSimpleItem(ATBYW_MISC));
    public static final Item WARPED_STICK = new Item(createSimpleItem(ATBYW_MISC));
    public static final Item BAMBOO_STICK = new Item(createSimpleItem(ATBYW_MISC));

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
        Registry.register(Registry.ITEM, newID("oak_stick"), OAK_STICK);
        Registry.register(Registry.ITEM, newID("spruce_stick"), SPRUCE_STICK);
        Registry.register(Registry.ITEM, newID("birch_stick"), BIRCH_STICK);
        Registry.register(Registry.ITEM, newID("jungle_stick"), JUNGLE_STICK);
        Registry.register(Registry.ITEM, newID("acacia_stick"), ACACIA_STICK);
        Registry.register(Registry.ITEM, newID("dark_oak_stick"), DARK_OAK_STICK);
        Registry.register(Registry.ITEM, newID("crimson_stick"), CRIMSON_STICK);
        Registry.register(Registry.ITEM, newID("warped_stick"), WARPED_STICK);
        Registry.register(Registry.ITEM, newID("bamboo_stick"), BAMBOO_STICK);

        Registry.register(Registry.ITEM, newID("bee_essence"), BEE_ESSENCE);
        Registry.register(Registry.ITEM, newID("silverfish_essence"), SILVERFISH_ESSENCE);
        Registry.register(Registry.ITEM, newID("endermite_essence"), ENDERMITE_ESSENCE);
        Registry.register(Registry.ITEM, newID("shulker_essence"), SHULKER_ESSENCE);
        Registry.register(Registry.ITEM, newID("wolf_essence"), WOLF_ESSENCE);
        Registry.register(Registry.ITEM, newID("cat_essence"), CAT_ESSENCE);
        Registry.register(Registry.ITEM, newID("chicken_essence"), CHICKEN_ESSENCE);
        Registry.register(Registry.ITEM, newID("rabbit_essence"), RABBIT_ESSENCE);
        Registry.register(Registry.ITEM, newID("fox_essence"), FOX_ESSENCE);
        Registry.register(Registry.ITEM, newID("cod_essence"), COD_ESSENCE);
        Registry.register(Registry.ITEM, newID("salmon_essence"), SALMON_ESSENCE);
        Registry.register(Registry.ITEM, newID("puffer_fish_essence"), PUFFER_FISH_ESSENCE);
        Registry.register(Registry.ITEM, newID("magma_cube_essence"), MAGMA_CUBE_ESSENCE);
        Registry.register(Registry.ITEM, newID("slime_essence"), SLIME_ESSENCE);

        AtbywModInteractionItems.init();
    }
}
