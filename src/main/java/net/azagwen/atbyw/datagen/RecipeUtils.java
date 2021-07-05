package net.azagwen.atbyw.datagen;

import com.google.gson.JsonElement;
import net.azagwen.atbyw.util.Pair;
import net.azagwen.atbyw.util.Quadruplet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.Map;

public class RecipeUtils {

    public static Pair<String, ItemConvertible> newKeyPair(String T, ItemConvertible I) {
        return new Pair<>(T, I);
    }

    public static Pair<String, Tag<Item>> newKeyPair(String T, Tag<Item> I) {
        return new Pair<>(T, I);
    }

    public static Pair<String, Identifier> newKeyPair(String T, Identifier I) {
        return new Pair<>(T, I);
    }

    protected static Quadruplet<String, String, String, Boolean> newKeyQuadruplet(String T, String N, String P, boolean C) {
        return new Quadruplet<>(T, N, P, C);
    }

    protected static Identifier getItemPseudoID(String[] nameArray, int nameIndex, String namespace, String path) {
        return new Identifier(namespace, nameArray[nameIndex] + "_" + path);
    }

    protected static void putRecipe(AtbywRecipe recipe, Map<Identifier, JsonElement> map) {
        if (recipe != null) {
            map.put(recipe.getIdentifier(), recipe.getJson());
        }
    }

    protected static void putRecipe(AtbywRecipe recipe, String colorPrefix, Map<Identifier, JsonElement> map) {
        if (recipe != null) {
            var id = new Identifier(recipe.getIdentifier().getNamespace(), colorPrefix + "_" + recipe.getIdentifier().getPath());
            map.put(id, recipe.getJson());
        }
    }
}
