package net.azagwen.atbyw.datagen.recipe.registry;

import com.google.common.collect.Maps;
import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
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
        map.put(suffixOnlyData("smelting", BuildingBlockRegistry.SMOOTH_PURPUR_BLOCK, 0.1F, 200), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.GLASS, 0.0F, 100), BuildingBlockRegistry.SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.WHITE_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.WHITE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.ORANGE_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.ORANGE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.MAGENTA_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.MAGENTA_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.LIGHT_BLUE_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.LIGHT_BLUE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.YELLOW_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.YELLOW_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.LIME_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.LIME_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.PINK_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.PINK_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.GRAY_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.GRAY_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.LIGHT_GRAY_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.LIGHT_GRAY_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.CYAN_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.CYAN_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.PURPLE_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.PURPLE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.BLUE_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.BLUE_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.BROWN_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.BROWN_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.GREEN_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.GREEN_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.RED_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.RED_STAINED_SHATTERED_GLASS);
        map.put(suffixOnlyData("smelting_shattered_glass", Blocks.BLACK_STAINED_GLASS, 0.0F, 100), BuildingBlockRegistry.BLACK_STAINED_SHATTERED_GLASS);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerSmeltingRecipe(recipeData, entry.getValue());
        }
    }
}