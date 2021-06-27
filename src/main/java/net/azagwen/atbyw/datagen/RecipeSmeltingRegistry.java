package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.main.AtbywIdentifier;
import net.azagwen.atbyw.util.naming.ColorNames;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.Map;

import static net.azagwen.atbyw.datagen.RecipeUtils.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;

public class RecipeSmeltingRegistry {
    private static final String SMELTING = "minecraft:smelting";
    private static final String BLASTING = "minecraft:blasting";
    private static final String SMOKING = "minecraft:smoking";

    public static AtbywRecipe createSmeltingRecipe(Identifier recipeId, String type, ArrayList<Identifier> ingredient, Identifier output, double xp, int time) {
        JsonObject json = new JsonObject();

        json.addProperty("type", type);

        //Ingredient
        JsonObject ingredientObj = new JsonObject();
        for (Identifier ing : ingredient) {
            ingredientObj.addProperty("item", ing.toString());
        }
        json.add("ingredient", ingredientObj);

        //Result
        json.addProperty("result", output.toString());
        json.addProperty("experience", xp);
        json.addProperty("cookingtime", time);

        Advancements.RECIPE_MAP.put(recipeId, Advancements.unlockSingleIngredientRecipe(json, recipeId));
        return new AtbywRecipe(json, recipeId);
    }

    private static AtbywRecipe[] createMultiSmeltingRecipes(Identifier recipeId, String type, ArrayList<Pair<String, String>> ingredients, Pair<String, String> result, double xp, int time) {
        AtbywRecipe[] obj = new AtbywRecipe[ColorNames.values().length];
        Identifier[] ing = new Identifier[ingredients.size()];

        int i = 0;
        for (var COLOR : ColorNames.getNames()) {
            var newRecipeId = new Identifier(recipeId.getNamespace(), COLOR + recipeId.getPath());
            int j = 0;
            for (var ingredient : ingredients) {
                ing[j] = getItemPseudoID(ColorNames.getNames().toArray(String[]::new), i, ingredient.getLeft(), ingredient.getRight());
                j++;
            }
            obj[i] = createSmeltingRecipe(newRecipeId, type, Lists.newArrayList(ing), getItemPseudoID(ColorNames.getNames().toArray(String[]::new), i, result.getLeft(), result.getRight()), xp, time);
            i++;
        }

        return obj;
    }

    public static AtbywRecipe SMOOTH_PURPUR_BLOCK_SMELTING = createSmeltingRecipe(new AtbywIdentifier("smooth_purpur_block_smelting"), SMELTING, Lists.newArrayList(getBlockID(Blocks.PURPUR_BLOCK)), getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK), 0.1D, 200);

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        putRecipe(SMOOTH_PURPUR_BLOCK_SMELTING, map);
    }
}