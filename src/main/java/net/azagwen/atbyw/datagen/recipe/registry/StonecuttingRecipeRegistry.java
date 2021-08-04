package net.azagwen.atbyw.datagen.recipe.registry;

import com.google.common.collect.Maps;
import net.azagwen.atbyw.block.AtbywBlocks;
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
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.TERRACOTTA_STAIRS, count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.WHITE_TERRACOTTA_STAIRS, count), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ORANGE_TERRACOTTA_STAIRS, count), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.MAGENTA_TERRACOTTA_STAIRS, count), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.YELLOW_TERRACOTTA_STAIRS, count), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIME_TERRACOTTA_STAIRS, count), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PINK_TERRACOTTA_STAIRS, count), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRAY_TERRACOTTA_STAIRS, count), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CYAN_TERRACOTTA_STAIRS, count), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPLE_TERRACOTTA_STAIRS, count), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_TERRACOTTA_STAIRS, count), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BROWN_TERRACOTTA_STAIRS, count), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GREEN_TERRACOTTA_STAIRS, count), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.RED_TERRACOTTA_STAIRS, count), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLACK_TERRACOTTA_STAIRS, count), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.TERRACOTTA_BRICKS_STAIRS,count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS,count), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS,count), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.LIME_TERRACOTTA_BRICKS_STAIRS,count), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.PINK_TERRACOTTA_BRICKS_STAIRS,count), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS,count), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS,count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS,count), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS,count), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS,count), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS,count), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.RED_TERRACOTTA_BRICKS_STAIRS,count), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS,count), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.WHITE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.ORANGE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.YELLOW_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.LIME_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.LIME_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.PINK_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.PINK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.CYAN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.PURPLE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.BROWN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.GREEN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.RED_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.RED_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.BLACK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.WHITE_CONCRETE_STAIRS, count), Blocks.WHITE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ORANGE_CONCRETE_STAIRS, count), Blocks.ORANGE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.MAGENTA_CONCRETE_STAIRS, count), Blocks.MAGENTA_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_BLUE_CONCRETE_STAIRS, count), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.YELLOW_CONCRETE_STAIRS, count), Blocks.YELLOW_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIME_CONCRETE_STAIRS, count), Blocks.LIME_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PINK_CONCRETE_STAIRS, count), Blocks.PINK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRAY_CONCRETE_STAIRS, count), Blocks.GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_GRAY_CONCRETE_STAIRS, count), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CYAN_CONCRETE_STAIRS, count), Blocks.CYAN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPLE_CONCRETE_STAIRS, count), Blocks.PURPLE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_CONCRETE_STAIRS, count), Blocks.BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BROWN_CONCRETE_STAIRS, count), Blocks.BROWN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GREEN_CONCRETE_STAIRS, count), Blocks.GREEN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.RED_CONCRETE_STAIRS, count), Blocks.RED_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLACK_CONCRETE_STAIRS, count), Blocks.BLACK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.DIRT_STAIRS, count), Blocks.DIRT);
        map.put(suffixOnlyData("stonecutting_grass_block", AtbywBlocks.DIRT_STAIRS, count), Blocks.GRASS_BLOCK);
        map.put(suffixOnlyData("stonecutting_mycelium", AtbywBlocks.DIRT_STAIRS, count), Blocks.MYCELIUM);
        map.put(suffixOnlyData("stonecutting_coarse_dirt", AtbywBlocks.DIRT_STAIRS, count), Blocks.COARSE_DIRT);
        map.put(suffixOnlyData("stonecutting_podzol", AtbywBlocks.DIRT_STAIRS, count), Blocks.PODZOL);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRASS_BLOCK_STAIRS, count), Blocks.GRASS_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.MYCELIUM_STAIRS, count), Blocks.MYCELIUM);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.COARSE_DIRT_STAIRS, count), Blocks.COARSE_DIRT);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PODZOL_STAIRS, count), Blocks.PODZOL);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.NETHERRACK_STAIRS, count), Blocks.NETHERRACK);
        map.put(suffixOnlyData("stonecutting_crimson_nylium", AtbywBlocks.NETHERRACK_STAIRS, count), Blocks.CRIMSON_NYLIUM);
        map.put(suffixOnlyData("stonecutting_warped_nylium", AtbywBlocks.NETHERRACK_STAIRS, count), Blocks.WARPED_NYLIUM);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CRIMSON_NYLIUM_STAIRS, count), Blocks.CRIMSON_NYLIUM);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.WARPED_NYLIUM_STAIRS, count), Blocks.WARPED_NYLIUM);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRANITE_TILES_STAIRS, count), Blocks.GRANITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.DIORITE_BRICKS_STAIRS, count), Blocks.DIORITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ANDESITE_BRICKS_STAIRS, count), Blocks.ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_polished", AtbywBlocks.GRANITE_TILES_STAIRS, count), Blocks.POLISHED_GRANITE);
        map.put(suffixOnlyData("stonecutting_from_polished", AtbywBlocks.DIORITE_BRICKS_STAIRS, count), Blocks.POLISHED_DIORITE);
        map.put(suffixOnlyData("stonecutting_from_polished", AtbywBlocks.ANDESITE_BRICKS_STAIRS, count), Blocks.POLISHED_ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_self", AtbywBlocks.GRANITE_TILES_STAIRS, count), AtbywBlocks.GRANITE_TILES);
        map.put(suffixOnlyData("stonecutting_from_self", AtbywBlocks.DIORITE_BRICKS_STAIRS, count), AtbywBlocks.DIORITE_BRICKS);
        map.put(suffixOnlyData("stonecutting_from_self", AtbywBlocks.ANDESITE_BRICKS_STAIRS, count), AtbywBlocks.ANDESITE_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS, count), AtbywBlocks.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS, count), AtbywBlocks.COMPACTED_SNOW_BRICKS);
        map.put(suffixOnlyData("stonecutting_compacted_snow_block", AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS, count), AtbywBlocks.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PACKED_ICE_STAIRS, count), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PACKED_ICE_BRICKS_STAIRS, count), AtbywBlocks.PACKED_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_packed_ice", AtbywBlocks.PACKED_ICE_BRICKS_STAIRS, count), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_ICE_STAIRS, count), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_ICE_BRICKS_STAIRS, count), AtbywBlocks.BLUE_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_blue_ice", AtbywBlocks.BLUE_ICE_BRICKS_STAIRS, count), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ROOTED_DIRT_STAIRS, count), Blocks.ROOTED_DIRT);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.SAND_STAIRS, count), Blocks.SAND);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.RED_SAND_STAIRS, count), Blocks.RED_SAND);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRAVEL_STAIRS, count), Blocks.GRAVEL);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPUR_TILES_STAIRS, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CUT_PURPUR_STAIRS, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting_purpur_tiles", AtbywBlocks.PURPUR_TILES_STAIRS, count), AtbywBlocks.PURPUR_TILES);
        map.put(suffixOnlyData("stonecutting_smooth_purpur", AtbywBlocks.CUT_PURPUR_STAIRS, count), AtbywBlocks.CUT_PURPUR_BLOCK);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerStonecuttingRecipe(recipeData, entry.getValue());
        }
    }

    private static void registerSlabRecipes() {
        //Map<RecipeData, Input>
        var count = 2;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.TERRACOTTA_SLAB, count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.WHITE_TERRACOTTA_SLAB, count), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ORANGE_TERRACOTTA_SLAB, count), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.MAGENTA_TERRACOTTA_SLAB, count), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.YELLOW_TERRACOTTA_SLAB, count), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIME_TERRACOTTA_SLAB, count), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PINK_TERRACOTTA_SLAB, count), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRAY_TERRACOTTA_SLAB, count), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CYAN_TERRACOTTA_SLAB, count), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPLE_TERRACOTTA_SLAB, count), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_TERRACOTTA_SLAB, count), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BROWN_TERRACOTTA_SLAB, count), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GREEN_TERRACOTTA_SLAB, count), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.RED_TERRACOTTA_SLAB, count), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLACK_TERRACOTTA_SLAB, count), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.TERRACOTTA_BRICKS_SLAB,count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_SLAB,count), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB,count), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB,count), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB,count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB,count), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.LIME_TERRACOTTA_BRICKS_SLAB,count), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.PINK_TERRACOTTA_BRICKS_SLAB,count), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_SLAB,count), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB,count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_SLAB,count), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB,count), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_SLAB,count), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_SLAB,count), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_SLAB,count), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.RED_TERRACOTTA_BRICKS_SLAB,count), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_SLAB,count), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.WHITE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.ORANGE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.YELLOW_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.LIME_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.LIME_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.PINK_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.PINK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.CYAN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.PURPLE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.BROWN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.GREEN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.RED_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.RED_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.BLACK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.WHITE_CONCRETE_SLAB, count), Blocks.WHITE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ORANGE_CONCRETE_SLAB, count), Blocks.ORANGE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.MAGENTA_CONCRETE_SLAB, count), Blocks.MAGENTA_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_BLUE_CONCRETE_SLAB, count), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.YELLOW_CONCRETE_SLAB, count), Blocks.YELLOW_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIME_CONCRETE_SLAB, count), Blocks.LIME_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PINK_CONCRETE_SLAB, count), Blocks.PINK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRAY_CONCRETE_SLAB, count), Blocks.GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_GRAY_CONCRETE_SLAB, count), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CYAN_CONCRETE_SLAB, count), Blocks.CYAN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPLE_CONCRETE_SLAB, count), Blocks.PURPLE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_CONCRETE_SLAB, count), Blocks.BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BROWN_CONCRETE_SLAB, count), Blocks.BROWN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GREEN_CONCRETE_SLAB, count), Blocks.GREEN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.RED_CONCRETE_SLAB, count), Blocks.RED_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLACK_CONCRETE_SLAB, count), Blocks.BLACK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.DIRT_SLAB, count), Blocks.DIRT);
        map.put(suffixOnlyData("stonecutting_grass_block", AtbywBlocks.DIRT_SLAB, count), Blocks.GRASS_BLOCK);
        map.put(suffixOnlyData("stonecutting_mycelium", AtbywBlocks.DIRT_SLAB, count), Blocks.MYCELIUM);
        map.put(suffixOnlyData("stonecutting_coarse_dirt", AtbywBlocks.DIRT_SLAB, count), Blocks.COARSE_DIRT);
        map.put(suffixOnlyData("stonecutting_podzol", AtbywBlocks.DIRT_SLAB, count), Blocks.PODZOL);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRASS_BLOCK_SLAB, count), Blocks.GRASS_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.MYCELIUM_SLAB, count), Blocks.MYCELIUM);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.COARSE_DIRT_SLAB, count), Blocks.COARSE_DIRT);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PODZOL_SLAB, count), Blocks.PODZOL);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.NETHERRACK_SLAB, count), Blocks.NETHERRACK);
        map.put(suffixOnlyData("stonecutting_crimson_nylium", AtbywBlocks.NETHERRACK_SLAB, count), Blocks.CRIMSON_NYLIUM);
        map.put(suffixOnlyData("stonecutting_warped_nylium", AtbywBlocks.NETHERRACK_SLAB, count), Blocks.WARPED_NYLIUM);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CRIMSON_NYLIUM_SLAB, count), Blocks.CRIMSON_NYLIUM);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.WARPED_NYLIUM_SLAB, count), Blocks.WARPED_NYLIUM);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRANITE_TILES_SLAB, count), Blocks.GRANITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.DIORITE_BRICKS_SLAB, count), Blocks.DIORITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ANDESITE_BRICKS_SLAB, count), Blocks.ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_polished", AtbywBlocks.GRANITE_TILES_SLAB, count), Blocks.POLISHED_GRANITE);
        map.put(suffixOnlyData("stonecutting_from_polished", AtbywBlocks.DIORITE_BRICKS_SLAB, count), Blocks.POLISHED_DIORITE);
        map.put(suffixOnlyData("stonecutting_from_polished", AtbywBlocks.ANDESITE_BRICKS_SLAB, count), Blocks.POLISHED_ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_self", AtbywBlocks.GRANITE_TILES_SLAB, count), AtbywBlocks.GRANITE_TILES);
        map.put(suffixOnlyData("stonecutting_from_self", AtbywBlocks.DIORITE_BRICKS_SLAB, count), AtbywBlocks.DIORITE_BRICKS);
        map.put(suffixOnlyData("stonecutting_from_self", AtbywBlocks.ANDESITE_BRICKS_SLAB, count), AtbywBlocks.ANDESITE_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB, count), AtbywBlocks.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB, count), AtbywBlocks.COMPACTED_SNOW_BRICKS);
        map.put(suffixOnlyData("stonecutting_compacted_snow_block", AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB, count), AtbywBlocks.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PACKED_ICE_SLAB, count), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PACKED_ICE_BRICKS_SLAB, count), AtbywBlocks.PACKED_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_packed_ice", AtbywBlocks.PACKED_ICE_BRICKS_SLAB, count), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_ICE_SLAB, count), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_ICE_BRICKS_SLAB, count), AtbywBlocks.BLUE_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_blue_ice", AtbywBlocks.BLUE_ICE_BRICKS_SLAB, count), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ROOTED_DIRT_SLAB, count), Blocks.ROOTED_DIRT);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.SAND_SLAB, count), Blocks.SAND);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.RED_SAND_SLAB, count), Blocks.RED_SAND);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRAVEL_SLAB, count), Blocks.GRAVEL);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPUR_TILES_SLAB, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CUT_PURPUR_SLAB, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting_purpur_tiles", AtbywBlocks.PURPUR_TILES_SLAB, count), AtbywBlocks.PURPUR_TILES);
        map.put(suffixOnlyData("stonecutting_smooth_purpur", AtbywBlocks.CUT_PURPUR_SLAB, count), AtbywBlocks.CUT_PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.TERRACOTTA_SLAB, count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.TERRACOTTA_BRICKS_SLAB,count), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerStonecuttingRecipe(recipeData, entry.getValue());
        }
    }

    private static void registerColumnRecipes() {
        //Map<RecipeData, Input>
        var count = 1;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRANITE_COLUMN, count), Blocks.GRANITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.DIORITE_COLUMN, count), Blocks.DIORITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ANDESITE_COLUMN, count), Blocks.ANDESITE);
        map.put(suffixOnlyData("stonecutting_polished", AtbywBlocks.GRANITE_COLUMN, count), Blocks.POLISHED_GRANITE);
        map.put(suffixOnlyData("stonecutting_polished", AtbywBlocks.DIORITE_COLUMN, count), Blocks.POLISHED_DIORITE);
        map.put(suffixOnlyData("stonecutting_polished", AtbywBlocks.ANDESITE_COLUMN, count), Blocks.POLISHED_ANDESITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.SANDSTONE_COLUMN, count), Blocks.SANDSTONE);
        map.put(suffixOnlyData("stonecutting_cut_sandstone", AtbywBlocks.SANDSTONE_COLUMN, count), Blocks.CUT_SANDSTONE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CHISELED_SANDSTONE_COLUMN, count), Blocks.CHISELED_SANDSTONE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.RED_SANDSTONE_COLUMN, count), Blocks.RED_SANDSTONE);
        map.put(suffixOnlyData("stonecutting_cut_red_sandstone", AtbywBlocks.RED_SANDSTONE_COLUMN, count), Blocks.CUT_RED_SANDSTONE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN, count), Blocks.CHISELED_RED_SANDSTONE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPUR_COLUMN, count), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.STONE_BRICKS_COLUMN, count), Blocks.STONE_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN, count), Blocks.MOSSY_STONE_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN, count), Blocks.CRACKED_STONE_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.NETHER_BRICKS_COLUMN, count), Blocks.NETHER_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.QUARTZ_COLUMN, count), Blocks.QUARTZ_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PRISMARINE_COLUMN, count), Blocks.PRISMARINE_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLACKSTONE_COLUMN, count), Blocks.BLACKSTONE);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerStonecuttingRecipe(recipeData, entry.getValue());
        }
    }

    private static void registerWallRecipes() {
        //Map<RecipeData, Input>
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.TERRACOTTA_BRICKS_WALL,1), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_WALL,1), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_WALL,1), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_WALL,1), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL,1), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_WALL,1), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.LIME_TERRACOTTA_BRICKS_WALL,1), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.PINK_TERRACOTTA_BRICKS_WALL,1), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_WALL,1), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL,1), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_WALL,1), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_WALL,1), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_WALL,1), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_WALL,1), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_WALL,1), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.RED_TERRACOTTA_BRICKS_WALL,1), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_WALL,1), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.WHITE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.ORANGE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.YELLOW_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.LIME_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.LIME_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.PINK_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.PINK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.CYAN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.PURPLE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.BLUE_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.BROWN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.GREEN_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.RED_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.RED_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_terracotta_bricks", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_WALL, 1), AtbywBlocks.BLACK_TERRACOTTA_BRICKS);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.WHITE_CINDER_BLOCKS_WALL, 1), Blocks.WHITE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.ORANGE_CINDER_BLOCKS_WALL, 1), Blocks.ORANGE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.MAGENTA_CINDER_BLOCKS_WALL, 1), Blocks.MAGENTA_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS_WALL, 1), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.YELLOW_CINDER_BLOCKS_WALL, 1), Blocks.YELLOW_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.LIME_CINDER_BLOCKS_WALL, 1), Blocks.LIME_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.PINK_CINDER_BLOCKS_WALL, 1), Blocks.PINK_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.GRAY_CINDER_BLOCKS_WALL, 1), Blocks.GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS_WALL, 1), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.CYAN_CINDER_BLOCKS_WALL, 1), Blocks.CYAN_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.PURPLE_CINDER_BLOCKS_WALL, 1), Blocks.PURPLE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.BLUE_CINDER_BLOCKS_WALL, 1), Blocks.BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.BROWN_CINDER_BLOCKS_WALL, 1), Blocks.BROWN_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.GREEN_CINDER_BLOCKS_WALL, 1), Blocks.GREEN_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.RED_CINDER_BLOCKS_WALL, 1), Blocks.RED_CONCRETE);
        map.put(suffixOnlyData("stonecutting_concrete", AtbywBlocks.BLACK_CINDER_BLOCKS_WALL, 1), Blocks.BLACK_CONCRETE);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.WHITE_CINDER_BLOCKS_WALL, 1), AtbywBlocks.WHITE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.ORANGE_CINDER_BLOCKS_WALL, 1), AtbywBlocks.ORANGE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.MAGENTA_CINDER_BLOCKS_WALL, 1), AtbywBlocks.MAGENTA_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS_WALL, 1), AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.YELLOW_CINDER_BLOCKS_WALL, 1), AtbywBlocks.YELLOW_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.LIME_CINDER_BLOCKS_WALL, 1), AtbywBlocks.LIME_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.PINK_CINDER_BLOCKS_WALL, 1), AtbywBlocks.PINK_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.GRAY_CINDER_BLOCKS_WALL, 1), AtbywBlocks.GRAY_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS_WALL, 1), AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.CYAN_CINDER_BLOCKS_WALL, 1), AtbywBlocks.CYAN_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.PURPLE_CINDER_BLOCKS_WALL, 1), AtbywBlocks.PURPLE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.BLUE_CINDER_BLOCKS_WALL, 1), AtbywBlocks.BLUE_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.BROWN_CINDER_BLOCKS_WALL, 1), AtbywBlocks.BROWN_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.GREEN_CINDER_BLOCKS_WALL, 1), AtbywBlocks.GREEN_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.RED_CINDER_BLOCKS_WALL, 1), AtbywBlocks.RED_CINDER_BLOCKS);
        map.put(suffixOnlyData("stonecutting_cinder_blocks", AtbywBlocks.BLACK_CINDER_BLOCKS_WALL, 1), AtbywBlocks.BLACK_CINDER_BLOCKS);

        for (var entry : map.entrySet()) {
            var recipeData = entry.getKey();
            registerStonecuttingRecipe(recipeData, entry.getValue());
        }
    }

    private static void registerMiscRecipes() {
        //Map<RecipeData, Input>
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.TERRACOTTA_BRICKS, 1), Blocks.TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.WHITE_TERRACOTTA_BRICKS, 1), Blocks.WHITE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, 1), Blocks.ORANGE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, 1), Blocks.MAGENTA_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, 1), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, 1), Blocks.YELLOW_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIME_TERRACOTTA_BRICKS, 1), Blocks.LIME_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PINK_TERRACOTTA_BRICKS, 1), Blocks.PINK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRAY_TERRACOTTA_BRICKS, 1), Blocks.GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, 1), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CYAN_TERRACOTTA_BRICKS, 1), Blocks.CYAN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, 1), Blocks.PURPLE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_TERRACOTTA_BRICKS, 1), Blocks.BLUE_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BROWN_TERRACOTTA_BRICKS, 1), Blocks.BROWN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GREEN_TERRACOTTA_BRICKS, 1), Blocks.GREEN_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.RED_TERRACOTTA_BRICKS, 1), Blocks.RED_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLACK_TERRACOTTA_BRICKS, 1), Blocks.BLACK_TERRACOTTA);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.WHITE_CINDER_BLOCKS, 1), Blocks.WHITE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ORANGE_CINDER_BLOCKS, 1), Blocks.ORANGE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.MAGENTA_CINDER_BLOCKS, 1), Blocks.MAGENTA_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS, 1), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.YELLOW_CINDER_BLOCKS, 1), Blocks.YELLOW_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIME_CINDER_BLOCKS, 1), Blocks.LIME_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PINK_CINDER_BLOCKS, 1), Blocks.PINK_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRAY_CINDER_BLOCKS, 1), Blocks.GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS, 1), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CYAN_CINDER_BLOCKS, 1), Blocks.CYAN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPLE_CINDER_BLOCKS, 1), Blocks.PURPLE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_CINDER_BLOCKS, 1), Blocks.BLUE_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BROWN_CINDER_BLOCKS, 1), Blocks.BROWN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GREEN_CINDER_BLOCKS, 1), Blocks.GREEN_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.RED_CINDER_BLOCKS, 1), Blocks.RED_CONCRETE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLACK_CINDER_BLOCKS, 1), Blocks.BLACK_CONCRETE);
        map.put(suffixOnlyData("stonecutting_basalt", AtbywBlocks.BASALT_BRICKS, 1), Blocks.BASALT);
        map.put(suffixOnlyData("stonecutting_basalt", AtbywBlocks.BASALT_PILLAR, 1), Blocks.BASALT);
        map.put(suffixOnlyData("stonecutting_polished_basalt", AtbywBlocks.BASALT_BRICKS, 1), Blocks.POLISHED_BASALT);
        map.put(suffixOnlyData("stonecutting_polished_basalt", AtbywBlocks.BASALT_PILLAR, 1), Blocks.POLISHED_BASALT);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.GRANITE_TILES, 1), Blocks.GRANITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.DIORITE_BRICKS, 1), Blocks.DIORITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.ANDESITE_BRICKS, 1), Blocks.ANDESITE);
        map.put(suffixOnlyData("stonecutting_from_polished", AtbywBlocks.GRANITE_TILES, 1), Blocks.POLISHED_GRANITE);
        map.put(suffixOnlyData("stonecutting_from_polished", AtbywBlocks.DIORITE_BRICKS, 1), Blocks.POLISHED_DIORITE);
        map.put(suffixOnlyData("stonecutting_from_polished", AtbywBlocks.ANDESITE_BRICKS, 1), Blocks.POLISHED_ANDESITE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CUT_PURPUR_BLOCK, 1), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PURPUR_TILES, 1), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CHISELED_PURPUR_BLOCK, 1), Blocks.PURPUR_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.COMPACTED_SNOW, 8), AtbywBlocks.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.COMPACTED_SNOW_BRICKS, 1), AtbywBlocks.COMPACTED_SNOW_BLOCK);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.PACKED_ICE_BRICKS, 1), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.BLUE_ICE_BRICKS, 1), Blocks.BLUE_ICE);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CHISELED_PACKED_ICE_BRICKS, 1), AtbywBlocks.PACKED_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting", AtbywBlocks.CHISELED_BLUE_ICE_BRICKS, 1), AtbywBlocks.BLUE_ICE_BRICKS);
        map.put(suffixOnlyData("stonecutting_packed_ice", AtbywBlocks.CHISELED_PACKED_ICE_BRICKS, 1), Blocks.PACKED_ICE);
        map.put(suffixOnlyData("stonecutting_blue_ice", AtbywBlocks.CHISELED_BLUE_ICE_BRICKS, 1), Blocks.BLUE_ICE);
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
