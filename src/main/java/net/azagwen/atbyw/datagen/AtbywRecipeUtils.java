package net.azagwen.atbyw.datagen;

import com.google.gson.JsonElement;
import net.azagwen.atbyw.util.AtbywUtils;
import net.azagwen.atbyw.util.Quadruplet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

import java.util.Map;

public class AtbywRecipeUtils {

    protected static Pair<String, Identifier> newKeyPair(String T, Identifier I) {
        return new Pair<>(T, I);
    }

    protected static Quadruplet<String, String, String, Boolean> newKeyQuadruplet(String T, String N, String P, boolean C) {
        return new Quadruplet<>(T, N, P, C);
    }

    protected static Identifier getItemID(Item item) {
        return Registry.ITEM.getId(item);
    }

    protected static Identifier getBlockID(Block block) {
        return Registry.BLOCK.getId(block);
    }

    protected static Identifier[] getItemsID(Item... items) {
        Identifier[] identifiers = new Identifier[items.length];
        for (int i = 0; i < items.length; i++) {
            identifiers[i] = getItemID(items[i]);
        }

        return identifiers;
    }

    protected static Identifier getItemPseudoID(String[] nameArray, int nameIndex, String namespace, String path) {
        return new Identifier(namespace, nameArray[nameIndex] + "_" + path);
    }

    protected static Item getMultiItemFromID(String[] nameArray, int nameIndex, String namespace, String path) {
        return Registry.ITEM.get(new Identifier(namespace, nameArray[nameIndex] + "_" + path));
    }

    protected static void putRecipe(Identifier identifier, JsonElement recipe, Map<Identifier, JsonElement> map) {
        if (recipe != null) {
            map.put(identifier, recipe);
        }
    }
}
