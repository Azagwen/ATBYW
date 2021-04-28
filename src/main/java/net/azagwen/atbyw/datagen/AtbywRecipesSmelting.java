package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.Map;

import static net.azagwen.atbyw.datagen.AtbywRecipeUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;

public class AtbywRecipesSmelting {
    private static final String SMELTING = "minecraft:smelting";
    private static final String BLASTING = "minecraft:blasting";
    private static final String SMOKING = "minecraft:smoking";

    public static JsonObject createSmeltingRecipe(String type, ArrayList<Identifier> ingredient, Identifier output, double xp, int time) {
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

        return json;
    }

    private static JsonObject[] createMultiSmeltingRecipes(String type, ArrayList<Pair<String, String>> ingredient, Pair<String, String> result, double xp, int time) {
        JsonObject[] obj = new JsonObject[COLOR_NAMES.length];
        Identifier[] ing = new Identifier[ingredient.size()];

        for (int i = 0; i < COLOR_NAMES.length; i++) {
            for (int j = 0; j < ingredient.size(); j++) {
                ing[j] = getItemPseudoID(COLOR_NAMES, i, ingredient.get(j).getLeft(), ingredient.get(j).getRight());
            }
            obj[i] = createSmeltingRecipe(type, Lists.newArrayList(ing), getItemPseudoID(COLOR_NAMES, i, result.getLeft(), result.getRight()), xp, time);
        }

        return obj;
    }

    public static JsonObject SMOOTH_PURPUR_BLOCK_SMELTING;

    public static void init() {
        SMOOTH_PURPUR_BLOCK_SMELTING = createSmeltingRecipe(SMELTING, Lists.newArrayList(getBlockID(Blocks.PURPUR_BLOCK)), getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK), 0.1D, 200);
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void injectRecipes(Map<Identifier, JsonElement> map) {
        putRecipe(AtbywID("smooth_purpur_block_smelting"), SMOOTH_PURPUR_BLOCK_SMELTING, map);
    }
}