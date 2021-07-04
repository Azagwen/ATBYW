package net.azagwen.atbyw.archived;

import com.google.common.collect.Lists;
import com.google.gson.*;
import net.azagwen.atbyw.datagen.*;
import net.azagwen.atbyw.util.Pair;
import net.azagwen.atbyw.util.Quadruplet;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.azagwen.atbyw.datagen.RecipeUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.atbywNamespace;
import static net.azagwen.atbyw.main.AtbywMain.mcNameSpace;
import static net.azagwen.atbyw.util.AtbywUtils.getBlockID;
import static net.azagwen.atbyw.util.AtbywUtils.getItemID;

public class RecipeRegistry {
    static Gson builder = new GsonBuilder().setPrettyPrinting().create();
    public static Logger LOGGER = LogManager.getLogger("Atbyw Recipes");

    public static AtbywRecipe createShapelessRecipe(Identifier recipeId, String group, int count, ArrayList<Identifier> keys, Identifier output) {
        var json = new JsonObject();

        if (!group.equals(""))
            json.addProperty("group", group);

        //Keys
        JsonObject individualIngredient;
        var ingredients = new JsonArray();
        for (var key : keys) {
            //Key type + ingredient
            individualIngredient = new JsonObject();
            individualIngredient.addProperty("item", key.toString());
            //Key character
            ingredients.add(individualIngredient);
        }

        //Result
        var result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);

        //build final JSON
        json.addProperty("type", "minecraft:crafting_shapeless");
        json.add("ingredients", ingredients);
        json.add("result", result);

        JsonAdvancements.RECIPE_MAP.put(recipeId, JsonAdvancements.unlockShapelessRecipe(json, recipeId));
        return new AtbywRecipe(json, recipeId);
    }

    public static AtbywRecipe createShapedRecipe(Identifier recipeId, String group, ArrayList<String> pattern, ArrayList<Key> keys, Identifier output, int count) {
        var json = new JsonObject();

        if (!group.equals(""))
            json.addProperty("group", group);

        //Pattern
        var patternArray = new JsonArray();
        for (String s : pattern) {
            patternArray.add(s);
        }
        json.add("pattern", patternArray);

        //Keys
        JsonObject individualKey;
        var keyList = new JsonObject();
        for (var key : keys) {
            //Key type + ingredient
            individualKey = new JsonObject();
            individualKey.addProperty(key.getType(), key.getIngredient().toString());
            //Key character
            keyList.add(key.getChar() + "", individualKey);
        }

        //Result
        var result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);

        //build final JSON
        json.addProperty("type", "minecraft:crafting_shaped");
        json.add("key", keyList);
        json.add("result", result);

        JsonAdvancements.RECIPE_MAP.put(recipeId, JsonAdvancements.unlockShapedRecipe(json, recipeId));
        return new AtbywRecipe(json, recipeId);
    }

    //Shaped recipes config methods
    @SafeVarargs
    private static AtbywRecipe createRecipeFromConfig(Identifier recipeId, String group, int count, RecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        var keys = new Key[ingredient.length];

        for (int i = 0; i < ingredient.length; i++) {
            keys[i] = new Key(config.getKeyChars().get(i), ingredient[i].getFirst(), ingredient[i].getSecond());
        }

        return createShapedRecipe(recipeId, group, config.getPattern(), Lists.newArrayList(keys), result, count);
    }

    @SafeVarargs
    private static AtbywRecipe createRecipeFromConfig(Identifier recipeId, String group, RecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        return createRecipeFromConfig(recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    private static AtbywRecipe[] createMultiRecipesFromConfig(String[] nameArray, Identifier recipeId, String group, int count, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredients) {
        var recipes = new AtbywRecipe[nameArray.length];
        var ing = new Pair[ingredients.length];

        int i = 0;
        for (var prefix : nameArray) {
            var newRecipeId = new Identifier(recipeId.getNamespace(), prefix + "_" + recipeId.getPath());
            var fullName = "";

            int j = 0;
            for (var ingredient : ingredients) {
                var colorizeIngredient = ingredient.getFourth();
                var pathName = ingredient.getThird();

                if (colorizeIngredient) {
                    fullName = !pathName.isEmpty() ? (prefix + "_" + pathName) : prefix;
                } else {
                    fullName = pathName;
                }

                ing[j] = newKeyPair(ingredient.getFirst(), new Identifier(ingredient.getSecond(), fullName));
                j++;
            }
            recipes[i] = createRecipeFromConfig(newRecipeId, group, count, config, new Identifier(result.getFirst(), prefix + "_" + result.getSecond()), ing);
            i++;
        }
        return recipes;
    }

    @SafeVarargs
    private static AtbywRecipe[] createMultiRecipesFromConfig(String[] nameArray, Identifier recipeId, String group, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray, recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    private static AtbywRecipe[] createMultiRecipesFromConfig(List<String> nameArray, Identifier recipeId, String group, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray.toArray(String[]::new), recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    private static AtbywRecipe[] createMultiRecipesFromConfig(List<String> nameArray, Identifier recipeId, String group, int count, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray.toArray(String[]::new), recipeId, group, count, config, result, ingredient);
    }

    public static void init() {
        LOGGER.info("ATBYW Recipes Inintiliazed");
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void inject(Map<Identifier, JsonElement> map) {

    }
}