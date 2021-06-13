package net.azagwen.atbyw.items;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.main.AtbywMain.*;
import static net.azagwen.atbyw.items.AtbywItems.*;

public class AtbywModInteractionItems {

    public static final Item STALAGNATE_STICK = new Item(createSimpleItem(null));
    public static final Item REEDS_STICK = new Item(createSimpleItem(null));
    public static final Item WILLOW_STICK = new Item(createSimpleItem(null));
    public static final Item WART_STICK = new Item(createSimpleItem(null));
    public static final Item RUBEUS_STICK = new Item(createSimpleItem(null));
    public static final Item MUSHROOM_STICK = new Item(createSimpleItem(null));
    public static final Item MUSHROOM_FIR_STICK = new Item(createSimpleItem(null));
    public static final Item ANCHOR_TREE_STICK = new Item(createSimpleItem(null));
    public static final Item NETHER_SAKURA_STICK = new Item(createSimpleItem(null));

    public static final Item MOSSY_GLOWSROOM_STICK = new Item(createSimpleItem(null));
    public static final Item PYTHADENDRON_STICK = new Item(createSimpleItem(null));
    public static final Item END_LOTUS_STICK = new Item(createSimpleItem(null));
    public static final Item LACUGROVE_STICK = new Item(createSimpleItem(null));
    public static final Item DRAGON_TREE_STICK = new Item(createSimpleItem(null));
    public static final Item TENANEA_STICK = new Item(createSimpleItem(null));
    public static final Item HELIX_TREE_STICK = new Item(createSimpleItem(null));
    public static final Item UMBRELLA_TREE_STICK = new Item(createSimpleItem(null));

    public static void init() {
        if (isModLoaded("betternether")) {
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("stalagnate_stick"), STALAGNATE_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("reeds_stick"), REEDS_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("willow_stick"), WILLOW_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("wart_stick"), WART_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("rubeus_stick"), RUBEUS_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("mushroom_stick"), MUSHROOM_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("mushroom_fir_stick"), MUSHROOM_FIR_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("anchor_tree_stick"), ANCHOR_TREE_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("nether_sakura_stick"), NETHER_SAKURA_STICK);
        }

        if (isModLoaded("betterend")) {
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("mossy_glowshroom_stick"), MOSSY_GLOWSROOM_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("pythadendron_stick"), PYTHADENDRON_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("end_lotus_stick"), END_LOTUS_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("lacugrove_stick"), LACUGROVE_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("dragon_tree_stick"), DRAGON_TREE_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("tenanea_stick"), TENANEA_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("helix_tree_stick"), HELIX_TREE_STICK);
            Registry.register(Registry.ITEM, NewAtbywModInteractionID("umbrella_tree_stick"), UMBRELLA_TREE_STICK);
        }
    }
}
