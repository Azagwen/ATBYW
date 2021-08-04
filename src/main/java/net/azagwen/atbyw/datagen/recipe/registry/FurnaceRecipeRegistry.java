package net.azagwen.atbyw.datagen.recipe.registry;

import com.google.common.collect.Maps;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.datagen.RecipeDatagen;
import net.azagwen.atbyw.datagen.recipe.util.FurnaceRecipeData;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;

public class FurnaceRecipeRegistry {

    public static Recipe<?> registerSmeltingRecipe(FurnaceRecipeData recipeData, Ingredient input) {
        var recipe = (Recipe<?>) null;
        var suffix = recipeData.suffix();
        var category = recipeData.category();
        var group = recipeData.group();
        var result = recipeData.result();
        var exp = recipeData.exp();
        var cookTime = recipeData.cookTime();

        var resultId = AtbywUtils.getItemID(result.asItem());
        var recipeId = AtbywMain.id(resultId.getPath() + (suffix.equals("") ? "" : ("_" + suffix)));
        recipe = RecipeDatagen.smeltingRecipe(recipeId, group, input, result, exp, cookTime);
        RecipeDatagen.registerRecipe(recipe, category);
        return recipe;
    }

    public static Recipe<?> registerSmeltingRecipe(FurnaceRecipeData recipeData, ItemConvertible input) {
        return registerSmeltingRecipe(recipeData, Ingredient.ofItems(input));
    }

    private static FurnaceRecipeData suffixOnlyData(String suffix, ItemConvertible result, float exp, int cookTime) {
        return new FurnaceRecipeData(suffix, "", "", result, exp, cookTime);
    }

    public static void init() {
        //Map<FurnaceRecipeData, Input>
        var map = Maps.<FurnaceRecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("smelting", AtbywBlocks.SMOOTH_PURPUR_BLOCK, 0.1F, 200), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.GLASS, 0.0F, 100), AtbywBlocks.SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.WHITE_STAINED_GLASS, 0.0F, 100), AtbywBlocks.WHITE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.ORANGE_STAINED_GLASS, 0.0F, 100), AtbywBlocks.ORANGE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.MAGENTA_STAINED_GLASS, 0.0F, 100), AtbywBlocks.MAGENTA_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.LIGHT_BLUE_STAINED_GLASS, 0.0F, 100), AtbywBlocks.LIGHT_BLUE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.YELLOW_STAINED_GLASS, 0.0F, 100), AtbywBlocks.YELLOW_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.LIME_STAINED_GLASS, 0.0F, 100), AtbywBlocks.LIME_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.PINK_STAINED_GLASS, 0.0F, 100), AtbywBlocks.PINK_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.GRAY_STAINED_GLASS, 0.0F, 100), AtbywBlocks.GRAY_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.LIGHT_GRAY_STAINED_GLASS, 0.0F, 100), AtbywBlocks.LIGHT_GRAY_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.CYAN_STAINED_GLASS, 0.0F, 100), AtbywBlocks.CYAN_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.PURPLE_STAINED_GLASS, 0.0F, 100), AtbywBlocks.PURPLE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.BLUE_STAINED_GLASS, 0.0F, 100), AtbywBlocks.BLUE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.BROWN_STAINED_GLASS, 0.0F, 100), AtbywBlocks.BROWN_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.GREEN_STAINED_GLASS, 0.0F, 100), AtbywBlocks.GREEN_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.RED_STAINED_GLASS, 0.0F, 100), AtbywBlocks.RED_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.BLACK_STAINED_GLASS, 0.0F, 100), AtbywBlocks.BLACK_STAINED_SHATTERED_GLASS);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerSmeltingRecipe(recipeData, entry.getValue());
        }
    }
}