package net.azagwen.atbyw.datagen.recipe.registry;

import com.google.common.collect.Maps;
import net.azagwen.atbyw.block.registry.DecorationBlockRegistry;
import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.datagen.RecipeDatagen;
import net.azagwen.atbyw.datagen.recipe.util.RecipeData;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;

public class StonecuttingRecipeRegistry {

    public static Recipe<?> registerStonecuttingRecipe(RecipeData recipeData, Ingredient input) {
        var recipe = (Recipe<?>) null;
        var suffix = recipeData.suffix();
        var category = recipeData.category();
        var group = recipeData.group();
        var result = recipeData.result();
        var count = recipeData.count();

        var resultId = AtbywUtils.getItemID(result.asItem());
        var recipeId = AtbywMain.id(resultId.getPath() + (suffix.equals("") ? "" : ("_" + suffix)));
        recipe = RecipeDatagen.stonecuttingRecipe(recipeId, group, input, result, count);
        RecipeDatagen.registerRecipe(recipe, category);
        return recipe;
    }

    public static Recipe<?> registerStonecuttingRecipe(RecipeData recipeData, ItemConvertible input) {
        return registerStonecuttingRecipe(recipeData, Ingredient.ofItems(input));
    }

    private static RecipeData suffixOnlyData(String suffix, ItemConvertible result, int count) {
        return new RecipeData(suffix, "", "", result, count);
    }

    public static void init() {
        registerStairsRecipes();
        registerSlabRecipes();
        registerColumnRecipes();
        registerMiscRecipes();
    }

    private static void registerStairsRecipes() {
        //Map<RecipeData, Input>
        var count = 1;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.TERRACOTTA_STAIRS, count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.WHITE_TERRACOTTA_STAIRS, count), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ORANGE_TERRACOTTA_STAIRS, count), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.MAGENTA_TERRACOTTA_STAIRS, count), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_STAIRS, count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.YELLOW_TERRACOTTA_STAIRS, count), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIME_TERRACOTTA_STAIRS, count), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PINK_TERRACOTTA_STAIRS, count), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRAY_TERRACOTTA_STAIRS, count), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_STAIRS, count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CYAN_TERRACOTTA_STAIRS, count), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PURPLE_TERRACOTTA_STAIRS, count), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_TERRACOTTA_STAIRS, count), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BROWN_TERRACOTTA_STAIRS, count), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GREEN_TERRACOTTA_STAIRS, count), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.RED_TERRACOTTA_STAIRS, count), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLACK_TERRACOTTA_STAIRS, count), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS,count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_STAIRS,count), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_STAIRS,count), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_STAIRS,count), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_STAIRS,count), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_STAIRS,count), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS,count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_STAIRS,count), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_STAIRS,count), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_STAIRS,count), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_STAIRS,count), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_STAIRS,count), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.RED_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.WHITE_CONCRETE_STAIRS, count), Blocks.WHITE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ORANGE_CONCRETE_STAIRS, count), Blocks.ORANGE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.MAGENTA_CONCRETE_STAIRS, count), Blocks.MAGENTA_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_BLUE_CONCRETE_STAIRS, count), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.YELLOW_CONCRETE_STAIRS, count), Blocks.YELLOW_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIME_CONCRETE_STAIRS, count), Blocks.LIME_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PINK_CONCRETE_STAIRS, count), Blocks.PINK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRAY_CONCRETE_STAIRS, count), Blocks.GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_GRAY_CONCRETE_STAIRS, count), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CYAN_CONCRETE_STAIRS, count), Blocks.CYAN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PURPLE_CONCRETE_STAIRS, count), Blocks.PURPLE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_CONCRETE_STAIRS, count), Blocks.BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BROWN_CONCRETE_STAIRS, count), Blocks.BROWN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GREEN_CONCRETE_STAIRS, count), Blocks.GREEN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.RED_CONCRETE_STAIRS, count), Blocks.RED_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLACK_CONCRETE_STAIRS, count), Blocks.BLACK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.DIRT_STAIRS, count), Blocks.DIRT);
        map.put(suffixOnlyData("stonecutting_grass_block", BuildingBlockRegistry.DIRT_STAIRS, count), Blocks.GRASS_BLOCK);
        map.put(suffixOnlyData("stonecutting_mycelium", BuildingBlockRegistry.DIRT_STAIRS, count), Blocks.MYCELIUM);
        map.put(suffixOnlyData("stonecutting_coarse_dirt", BuildingBlockRegistry.DIRT_STAIRS, count), Blocks.COARSE_DIRT);
        map.put(suffixOnlyData("stonecutting_podzol", BuildingBlockRegistry.DIRT_STAIRS, count), Blocks.PODZOL);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRASS_BLOCK_STAIRS, count), Blocks.GRASS_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.MYCELIUM_STAIRS, count), Blocks.MYCELIUM);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.COARSE_DIRT_STAIRS, count), Blocks.COARSE_DIRT);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PODZOL_STAIRS, count), Blocks.PODZOL);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.NETHERRACK_STAIRS, count), Blocks.NETHERRACK);
        map.put(suffixOnlyData("stonecutting_crimson_nylium", BuildingBlockRegistry.NETHERRACK_STAIRS, count), Blocks.CRIMSON_NYLIUM);
        map.put(suffixOnlyData("stonecutting_warped_nylium", BuildingBlockRegistry.NETHERRACK_STAIRS, count), Blocks.WARPED_NYLIUM);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CRIMSON_NYLIUM_STAIRS, count), Blocks.CRIMSON_NYLIUM);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.WARPED_NYLIUM_STAIRS, count), Blocks.WARPED_NYLIUM);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRANITE_TILES_STAIRS, count), Blocks.GRANITE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.DIORITE_BRICKS_STAIRS, count), Blocks.DIORITE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ANDESITE_BRICKS_STAIRS, count), Blocks.ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_polished", BuildingBlockRegistry.GRANITE_TILES_STAIRS, count), Blocks.POLISHED_GRANITE);
        map.put(suffixOnlyData("stonecutting_from_polished", BuildingBlockRegistry.DIORITE_BRICKS_STAIRS, count), Blocks.POLISHED_DIORITE);
        map.put(suffixOnlyData("stonecutting_from_polished", BuildingBlockRegistry.ANDESITE_BRICKS_STAIRS, count), Blocks.POLISHED_ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_self", BuildingBlockRegistry.GRANITE_TILES_STAIRS, count), BuildingBlockRegistry.GRANITE_TILES);
        map.put(suffixOnlyData("stonecutting_from_self", BuildingBlockRegistry.DIORITE_BRICKS_STAIRS, count), BuildingBlockRegistry.DIORITE_BRICKS);
        map.put(suffixOnlyData("stonecutting_from_self", BuildingBlockRegistry.ANDESITE_BRICKS_STAIRS, count), BuildingBlockRegistry.ANDESITE_BRICKS);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.COMPACTED_SNOW_BLOCK_STAIRS, count), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.COMPACTED_SNOW_BRICKS_STAIRS, count), BuildingBlockRegistry.COMPACTED_SNOW_BRICKS);
        map.put(suffixOnlyData("stonecutting_compacted_snow_block", BuildingBlockRegistry.COMPACTED_SNOW_BRICKS_STAIRS, count), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PACKED_ICE_STAIRS, count), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PACKED_ICE_BRICKS_STAIRS, count), BuildingBlockRegistry.PACKED_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_packed_ice", BuildingBlockRegistry.PACKED_ICE_BRICKS_STAIRS, count), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_ICE_STAIRS, count), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_ICE_BRICKS_STAIRS, count), BuildingBlockRegistry.BLUE_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_blue_ice", BuildingBlockRegistry.BLUE_ICE_BRICKS_STAIRS, count), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ROOTED_DIRT_STAIRS, count), Blocks.ROOTED_DIRT);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.SAND_STAIRS, count), Blocks.SAND);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.RED_SAND_STAIRS, count), Blocks.RED_SAND);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRAVEL_STAIRS, count), Blocks.GRAVEL);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PURPUR_TILES_STAIRS, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CUT_PURPUR_STAIRS, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting_purpur_tiles", BuildingBlockRegistry.PURPUR_TILES_STAIRS, count), BuildingBlockRegistry.PURPUR_TILES);
        map.put(suffixOnlyData("stonecutting_smooth_purpur", BuildingBlockRegistry.CUT_PURPUR_STAIRS, count), BuildingBlockRegistry.CUT_PURPUR_BLOCK);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerStonecuttingRecipe(recipeData, entry.getValue());
        }
    }

    private static void registerSlabRecipes() {
        //Map<RecipeData, Input>
        var count = 2;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.TERRACOTTA_SLAB, count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.WHITE_TERRACOTTA_SLAB, count), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ORANGE_TERRACOTTA_SLAB, count), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.MAGENTA_TERRACOTTA_SLAB, count), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_SLAB, count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.YELLOW_TERRACOTTA_SLAB, count), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIME_TERRACOTTA_SLAB, count), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PINK_TERRACOTTA_SLAB, count), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRAY_TERRACOTTA_SLAB, count), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_SLAB, count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CYAN_TERRACOTTA_SLAB, count), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PURPLE_TERRACOTTA_SLAB, count), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_TERRACOTTA_SLAB, count), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BROWN_TERRACOTTA_SLAB, count), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GREEN_TERRACOTTA_SLAB, count), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.RED_TERRACOTTA_SLAB, count), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLACK_TERRACOTTA_SLAB, count), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB,count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_SLAB,count), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_SLAB,count), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_SLAB,count), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB,count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_SLAB,count), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_SLAB,count), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_SLAB,count), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_SLAB,count), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB,count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_SLAB,count), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_SLAB,count), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_SLAB,count), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_SLAB,count), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_SLAB,count), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_SLAB,count), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_SLAB,count), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.RED_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.WHITE_CONCRETE_SLAB, count), Blocks.WHITE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ORANGE_CONCRETE_SLAB, count), Blocks.ORANGE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.MAGENTA_CONCRETE_SLAB, count), Blocks.MAGENTA_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_BLUE_CONCRETE_SLAB, count), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.YELLOW_CONCRETE_SLAB, count), Blocks.YELLOW_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIME_CONCRETE_SLAB, count), Blocks.LIME_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PINK_CONCRETE_SLAB, count), Blocks.PINK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRAY_CONCRETE_SLAB, count), Blocks.GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_GRAY_CONCRETE_SLAB, count), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CYAN_CONCRETE_SLAB, count), Blocks.CYAN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PURPLE_CONCRETE_SLAB, count), Blocks.PURPLE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_CONCRETE_SLAB, count), Blocks.BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BROWN_CONCRETE_SLAB, count), Blocks.BROWN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GREEN_CONCRETE_SLAB, count), Blocks.GREEN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.RED_CONCRETE_SLAB, count), Blocks.RED_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLACK_CONCRETE_SLAB, count), Blocks.BLACK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.DIRT_SLAB, count), Blocks.DIRT);
        map.put(suffixOnlyData("stonecutting_grass_block", BuildingBlockRegistry.DIRT_SLAB, count), Blocks.GRASS_BLOCK);
        map.put(suffixOnlyData("stonecutting_mycelium", BuildingBlockRegistry.DIRT_SLAB, count), Blocks.MYCELIUM);
        map.put(suffixOnlyData("stonecutting_coarse_dirt", BuildingBlockRegistry.DIRT_SLAB, count), Blocks.COARSE_DIRT);
        map.put(suffixOnlyData("stonecutting_podzol", BuildingBlockRegistry.DIRT_SLAB, count), Blocks.PODZOL);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRASS_BLOCK_SLAB, count), Blocks.GRASS_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.MYCELIUM_SLAB, count), Blocks.MYCELIUM);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.COARSE_DIRT_SLAB, count), Blocks.COARSE_DIRT);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PODZOL_SLAB, count), Blocks.PODZOL);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.NETHERRACK_SLAB, count), Blocks.NETHERRACK);
        map.put(suffixOnlyData("stonecutting_crimson_nylium", BuildingBlockRegistry.NETHERRACK_SLAB, count), Blocks.CRIMSON_NYLIUM);
        map.put(suffixOnlyData("stonecutting_warped_nylium", BuildingBlockRegistry.NETHERRACK_SLAB, count), Blocks.WARPED_NYLIUM);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CRIMSON_NYLIUM_SLAB, count), Blocks.CRIMSON_NYLIUM);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.WARPED_NYLIUM_SLAB, count), Blocks.WARPED_NYLIUM);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRANITE_TILES_SLAB, count), Blocks.GRANITE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.DIORITE_BRICKS_SLAB, count), Blocks.DIORITE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ANDESITE_BRICKS_SLAB, count), Blocks.ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_polished", BuildingBlockRegistry.GRANITE_TILES_SLAB, count), Blocks.POLISHED_GRANITE);
        map.put(suffixOnlyData("stonecutting_from_polished", BuildingBlockRegistry.DIORITE_BRICKS_SLAB, count), Blocks.POLISHED_DIORITE);
        map.put(suffixOnlyData("stonecutting_from_polished", BuildingBlockRegistry.ANDESITE_BRICKS_SLAB, count), Blocks.POLISHED_ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_self", BuildingBlockRegistry.GRANITE_TILES_SLAB, count), BuildingBlockRegistry.GRANITE_TILES);
        map.put(suffixOnlyData("stonecutting_from_self", BuildingBlockRegistry.DIORITE_BRICKS_SLAB, count), BuildingBlockRegistry.DIORITE_BRICKS);
        map.put(suffixOnlyData("stonecutting_from_self", BuildingBlockRegistry.ANDESITE_BRICKS_SLAB, count), BuildingBlockRegistry.ANDESITE_BRICKS);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.COMPACTED_SNOW_BLOCK_SLAB, count), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.COMPACTED_SNOW_BRICKS_SLAB, count), BuildingBlockRegistry.COMPACTED_SNOW_BRICKS);
        map.put(suffixOnlyData("stonecutting_compacted_snow_block", BuildingBlockRegistry.COMPACTED_SNOW_BRICKS_SLAB, count), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PACKED_ICE_SLAB, count), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PACKED_ICE_BRICKS_SLAB, count), BuildingBlockRegistry.PACKED_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_packed_ice", BuildingBlockRegistry.PACKED_ICE_BRICKS_SLAB, count), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_ICE_SLAB, count), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_ICE_BRICKS_SLAB, count), BuildingBlockRegistry.BLUE_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_blue_ice", BuildingBlockRegistry.BLUE_ICE_BRICKS_SLAB, count), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ROOTED_DIRT_SLAB, count), Blocks.ROOTED_DIRT);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.SAND_SLAB, count), Blocks.SAND);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.RED_SAND_SLAB, count), Blocks.RED_SAND);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRAVEL_SLAB, count), Blocks.GRAVEL);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PURPUR_TILES_SLAB, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CUT_PURPUR_SLAB, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting_purpur_tiles", BuildingBlockRegistry.PURPUR_TILES_SLAB, count), BuildingBlockRegistry.PURPUR_TILES);
        map.put(suffixOnlyData("stonecutting_smooth_purpur", BuildingBlockRegistry.CUT_PURPUR_SLAB, count), BuildingBlockRegistry.CUT_PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.TERRACOTTA_SLAB, count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB,count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerStonecuttingRecipe(recipeData, entry.getValue());
        }
    }

    private static void registerColumnRecipes() {
        //Map<RecipeData, Input>
        var count = 1;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.GRANITE_COLUMN, count), Blocks.GRANITE);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.DIORITE_COLUMN, count), Blocks.DIORITE);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.ANDESITE_COLUMN, count), Blocks.ANDESITE);
        map.put(suffixOnlyData("stonecutting_polished", DecorationBlockRegistry.GRANITE_COLUMN, count), Blocks.POLISHED_GRANITE);
        map.put(suffixOnlyData("stonecutting_polished", DecorationBlockRegistry.DIORITE_COLUMN, count), Blocks.POLISHED_DIORITE);
        map.put(suffixOnlyData("stonecutting_polished", DecorationBlockRegistry.ANDESITE_COLUMN, count), Blocks.POLISHED_ANDESITE);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.SANDSTONE_COLUMN, count), Blocks.SANDSTONE);
        map.put(suffixOnlyData("stonecutting_cut_sandstone", DecorationBlockRegistry.SANDSTONE_COLUMN, count), Blocks.CUT_SANDSTONE);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.CHISELED_SANDSTONE_COLUMN, count), Blocks.CHISELED_SANDSTONE);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.RED_SANDSTONE_COLUMN, count), Blocks.RED_SANDSTONE);
        map.put(suffixOnlyData("stonecutting_cut_red_sandstone", DecorationBlockRegistry.RED_SANDSTONE_COLUMN, count), Blocks.CUT_RED_SANDSTONE);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.CHISELED_RED_SANDSTONE_COLUMN, count), Blocks.CHISELED_RED_SANDSTONE);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.PURPUR_COLUMN, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.STONE_BRICKS_COLUMN, count), Blocks.STONE_BRICKS);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.MOSSY_STONE_BRICKS_COLUMN, count), Blocks.MOSSY_STONE_BRICKS);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.CRACKED_STONE_BRICKS_COLUMN, count), Blocks.CRACKED_STONE_BRICKS);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.NETHER_BRICKS_COLUMN, count), Blocks.NETHER_BRICKS);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.QUARTZ_COLUMN, count), Blocks.QUARTZ_BLOCK);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.PRISMARINE_COLUMN, count), Blocks.PRISMARINE_BRICKS);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.BLACKSTONE_COLUMN, count), Blocks.BLACKSTONE);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerStonecuttingRecipe(recipeData, entry.getValue());
        }
    }

    private static void registerWallRecipes() {
        //Map<RecipeData, Input>
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL,1), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.WHITE_TERRACOTTA_BRICKS_WALL,1), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.ORANGE_TERRACOTTA_BRICKS_WALL,1), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_WALL,1), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL,1), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.YELLOW_TERRACOTTA_BRICKS_WALL,1), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.LIME_TERRACOTTA_BRICKS_WALL,1), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.PINK_TERRACOTTA_BRICKS_WALL,1), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.GRAY_TERRACOTTA_BRICKS_WALL,1), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL,1), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.CYAN_TERRACOTTA_BRICKS_WALL,1), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.PURPLE_TERRACOTTA_BRICKS_WALL,1), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.BLUE_TERRACOTTA_BRICKS_WALL,1), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.BROWN_TERRACOTTA_BRICKS_WALL,1), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.GREEN_TERRACOTTA_BRICKS_WALL,1), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.RED_TERRACOTTA_BRICKS_WALL,1), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", DecorationBlockRegistry.BLACK_TERRACOTTA_BRICKS_WALL,1), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.WHITE_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.ORANGE_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.YELLOW_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.LIME_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.PINK_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.GRAY_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.CYAN_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.PURPLE_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.BLUE_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.BROWN_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.GREEN_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.RED_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.RED_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", DecorationBlockRegistry.BLACK_TERRACOTTA_BRICKS_WALL, 1), BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.WHITE_CINDER_BLOCKS_WALL, 1), Blocks.WHITE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.ORANGE_CINDER_BLOCKS_WALL, 1), Blocks.ORANGE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.MAGENTA_CINDER_BLOCKS_WALL, 1), Blocks.MAGENTA_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.LIGHT_BLUE_CINDER_BLOCKS_WALL, 1), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.YELLOW_CINDER_BLOCKS_WALL, 1), Blocks.YELLOW_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.LIME_CINDER_BLOCKS_WALL, 1), Blocks.LIME_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.PINK_CINDER_BLOCKS_WALL, 1), Blocks.PINK_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.GRAY_CINDER_BLOCKS_WALL, 1), Blocks.GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.LIGHT_GRAY_CINDER_BLOCKS_WALL, 1), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.CYAN_CINDER_BLOCKS_WALL, 1), Blocks.CYAN_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.PURPLE_CINDER_BLOCKS_WALL, 1), Blocks.PURPLE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.BLUE_CINDER_BLOCKS_WALL, 1), Blocks.BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.BROWN_CINDER_BLOCKS_WALL, 1), Blocks.BROWN_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.GREEN_CINDER_BLOCKS_WALL, 1), Blocks.GREEN_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.RED_CINDER_BLOCKS_WALL, 1), Blocks.RED_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", DecorationBlockRegistry.BLACK_CINDER_BLOCKS_WALL, 1), Blocks.BLACK_CONCRETE);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.WHITE_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.WHITE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.ORANGE_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.ORANGE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.MAGENTA_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.MAGENTA_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.LIGHT_BLUE_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.LIGHT_BLUE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.YELLOW_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.YELLOW_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.LIME_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.LIME_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.PINK_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.PINK_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.GRAY_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.GRAY_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.LIGHT_GRAY_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.LIGHT_GRAY_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.CYAN_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.CYAN_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.PURPLE_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.PURPLE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.BLUE_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.BLUE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.BROWN_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.BROWN_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.GREEN_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.GREEN_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.RED_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.RED_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", DecorationBlockRegistry.BLACK_CINDER_BLOCKS_WALL, 1), BuildingBlockRegistry.BLACK_CINDER_BLOCKS);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerStonecuttingRecipe(recipeData, entry.getValue());
        }
    }

    private static void registerMiscRecipes() {
        //Map<RecipeData, Input>
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.TERRACOTTA_BRICKS, 1), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS, 1), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS, 1), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS, 1), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS, 1), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS, 1), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS, 1), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS, 1), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS, 1), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS, 1), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS, 1), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS, 1), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS, 1), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS, 1), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS, 1), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS, 1), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS, 1), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.WHITE_CINDER_BLOCKS, 1), Blocks.WHITE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ORANGE_CINDER_BLOCKS, 1), Blocks.ORANGE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.MAGENTA_CINDER_BLOCKS, 1), Blocks.MAGENTA_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_BLUE_CINDER_BLOCKS, 1), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.YELLOW_CINDER_BLOCKS, 1), Blocks.YELLOW_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIME_CINDER_BLOCKS, 1), Blocks.LIME_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PINK_CINDER_BLOCKS, 1), Blocks.PINK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRAY_CINDER_BLOCKS, 1), Blocks.GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.LIGHT_GRAY_CINDER_BLOCKS, 1), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CYAN_CINDER_BLOCKS, 1), Blocks.CYAN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PURPLE_CINDER_BLOCKS, 1), Blocks.PURPLE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_CINDER_BLOCKS, 1), Blocks.BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BROWN_CINDER_BLOCKS, 1), Blocks.BROWN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GREEN_CINDER_BLOCKS, 1), Blocks.GREEN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.RED_CINDER_BLOCKS, 1), Blocks.RED_CONCRETE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLACK_CINDER_BLOCKS, 1), Blocks.BLACK_CONCRETE);
        map.put(suffixOnlyData("stonecutting_basalt", BuildingBlockRegistry.BASALT_BRICKS, 1), Blocks.BASALT);
        map.put(suffixOnlyData("stonecutting_basalt", BuildingBlockRegistry.BASALT_PILLAR, 1), Blocks.BASALT);
        map.put(suffixOnlyData("stonecutting_polished_basalt", BuildingBlockRegistry.BASALT_BRICKS, 1), Blocks.POLISHED_BASALT);
        map.put(suffixOnlyData("stonecutting_polished_basalt", BuildingBlockRegistry.BASALT_PILLAR, 1), Blocks.POLISHED_BASALT);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.GRANITE_TILES, 1), Blocks.GRANITE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.DIORITE_BRICKS, 1), Blocks.DIORITE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.ANDESITE_BRICKS, 1), Blocks.ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_polished", BuildingBlockRegistry.GRANITE_TILES, 1), Blocks.POLISHED_GRANITE);
        map.put(suffixOnlyData("stonecutting_from_polished", BuildingBlockRegistry.DIORITE_BRICKS, 1), Blocks.POLISHED_DIORITE);
        map.put(suffixOnlyData("stonecutting_from_polished", BuildingBlockRegistry.ANDESITE_BRICKS, 1), Blocks.POLISHED_ANDESITE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CUT_PURPUR_BLOCK, 1), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PURPUR_TILES, 1), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CHISELED_PURPUR_BLOCK, 1), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", DecorationBlockRegistry.COMPACTED_SNOW, 8), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.COMPACTED_SNOW_BRICKS, 1), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.PACKED_ICE_BRICKS, 1), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.BLUE_ICE_BRICKS, 1), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CHISELED_PACKED_ICE_BRICKS, 1), BuildingBlockRegistry.PACKED_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting", BuildingBlockRegistry.CHISELED_BLUE_ICE_BRICKS, 1), BuildingBlockRegistry.BLUE_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_packed_ice", BuildingBlockRegistry.CHISELED_PACKED_ICE_BRICKS, 1), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting_blue_ice", BuildingBlockRegistry.CHISELED_BLUE_ICE_BRICKS, 1), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", StatueRegistry.AXOLOTL_STATUE, 1), Blocks.STONE);
        map.put(suffixOnlyData("stonecutting", StatueRegistry.BAT_STATUE, 1), Blocks.STONE);
        map.put(suffixOnlyData("stonecutting", StatueRegistry.BEE_STATUE, 1), Blocks.STONE);
        map.put(suffixOnlyData("stonecutting", StatueRegistry.SILVERFISH_STATUE, 1), Blocks.STONE);
        map.put(suffixOnlyData("stonecutting", StatueRegistry.ENDERMITE_STATUE, 1), Blocks.STONE);
        map.put(suffixOnlyData("stonecutting", StatueRegistry.WOLF_STATUE, 1), Blocks.STONE);
        map.put(suffixOnlyData("stonecutting", StatueRegistry.CAT_STATUE, 1), Blocks.STONE);
        map.put(suffixOnlyData("stonecutting", StatueRegistry.FOX_STATUE, 1), Blocks.STONE);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerStonecuttingRecipe(recipeData, entry.getValue());
        }
    }
}
