package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.archived.JsonAdvancements;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.naming.ColorNames;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.azagwen.atbyw.datagen.RecipeUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.ATBYW;
import static net.azagwen.atbyw.main.AtbywMain.MINECRAFT;
import static net.azagwen.atbyw.util.AtbywUtils.*;

public class RecipeSmeltingRegistry {
    private static final String SMELTING = "minecraft:smelting";
    private static final String BLASTING = "minecraft:blasting";
    private static final String SMOKING = "minecraft:smoking";

    public static AtbywRecipe createSmeltingRecipe(Identifier recipeId, String type, List<Identifier> ingredient, Identifier output, double xp, int time) {
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

        JsonAdvancements.RECIPE_MAP.put(recipeId, JsonAdvancements.unlockSingleIngredientRecipe(json, recipeId));
        return new AtbywRecipe(json, recipeId);
    }

    private static AtbywRecipe[] createMultiSmeltingRecipes(List<String> variants, Identifier recipeId, String type, ArrayList<Pair<String, String>> ingredients, Pair<String, String> result, double xp, int time) {
        List<AtbywRecipe> recipes = Lists.newArrayList();
        List<Identifier> ingredientList = Lists.newArrayList();

        for (var variant : variants) {
            var newRecipeId = new Identifier(recipeId.getNamespace(), variant + recipeId.getPath());
            int i = 0;
            for (var ingredient : ingredients) {
                ingredientList.add(new Identifier(ingredient.getLeft(), String.join("_", variant, ingredient.getRight())));
                i++;
            }
            var resultId = new Identifier(result.getLeft(), String.join("_", variant, result.getRight()));
            recipes.add(createSmeltingRecipe(newRecipeId, type, ingredientList, resultId, xp, time));
        }

        return recipes.toArray(AtbywRecipe[]::new);
    }

    public static AtbywRecipe SMOOTH_PURPUR_BLOCK_SMELTING = createSmeltingRecipe(AtbywMain.id("smooth_purpur_block_smelting"), SMELTING, Lists.newArrayList(getBlockID(Blocks.PURPUR_BLOCK)), getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK), 0.1D, 200);
    public static AtbywRecipe GLASS_SMELTING_FROM_SHATTERED_GLASS = createSmeltingRecipe(AtbywMain.id("glass_smelting"), SMELTING, Lists.newArrayList(getBlockID(AtbywBlocks.SHATTERED_GLASS)), getBlockID(Blocks.GLASS), 0.0D, 100);
    public static AtbywRecipe[] STAINED_GLASS_SMELTING_FROM_STAINED_SHATTERED_GLASS = createMultiSmeltingRecipes(ColorNames.getNames(), AtbywMain.id("stained_glass_smelting"), SMELTING, Lists.newArrayList(new Pair<>(ATBYW, "stained_shattered_glass")), new Pair<>(MINECRAFT, "stained_glass"), 0.0D, 100);

    //Used in net.azagwen.atbyw.mixin.datagen.RecipeManagerMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        var i = 0;
        for (var color : ColorNames.getNames()) {
            putRecipe(STAINED_GLASS_SMELTING_FROM_STAINED_SHATTERED_GLASS[i], map);
            i++;
        }
        putRecipe(SMOOTH_PURPUR_BLOCK_SMELTING, map);
        putRecipe(GLASS_SMELTING_FROM_SHATTERED_GLASS, map);
    }
}