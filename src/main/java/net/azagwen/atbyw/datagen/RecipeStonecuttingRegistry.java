package net.azagwen.atbyw.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.archived.JsonAdvancements;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.naming.ColorNames;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.Map;

import static net.azagwen.atbyw.datagen.RecipeUtils.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class RecipeStonecuttingRegistry {

    public static AtbywRecipe createStonecutterRecipe(Identifier recipeId, Identifier ingredient, Identifier output, int count) {
        JsonObject json = new JsonObject();

        json.addProperty("type", "minecraft:stonecutting");

        //Ingredient
        JsonObject ingredientObj = new JsonObject();
        ingredientObj.addProperty("item", ingredient.toString());
        json.add("ingredient", ingredientObj);

        //Result
        json.addProperty("result", output.toString());
        json.addProperty("count", count);

        JsonAdvancements.RECIPE_MAP.put(recipeId, JsonAdvancements.unlockSingleIngredientRecipe(json, recipeId));
        return new AtbywRecipe(json, recipeId);
    }

    private static AtbywRecipe[] createStonecutterColoredRecipes(Identifier recipeId, Pair<String, String> ingredient, Pair<String, String> result, int count) {
        AtbywRecipe[] recipes = new AtbywRecipe[ColorNames.values().length];

        int i = 0;
        for (var COLOR : ColorNames.getNames()) {
            var newRecipeId = new Identifier(recipeId.getNamespace(), COLOR + recipeId.getPath());
            recipes[i] = createStonecutterRecipe(
                    newRecipeId,
                    getItemPseudoID(ColorNames.getNames().toArray(String[]::new), i, ingredient.getLeft(), ingredient.getRight()),
                    getItemPseudoID(ColorNames.getNames().toArray(String[]::new), i, result.getLeft(), result.getRight()),
                    count
            );
            i++;
        }

        return recipes;
    }

    public static AtbywRecipe DIRT_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("dirt_stairs_stonecutting"), getBlockID(Blocks.DIRT), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
    public static AtbywRecipe DIRT_STAIRS_STONECUTTING_GRASS_BLOCK = createStonecutterRecipe(AtbywMain.Id("dirt_stairs_stonecutting_grass_block"), getBlockID(Blocks.GRASS_BLOCK), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
    public static AtbywRecipe DIRT_STAIRS_STONECUTTING_MYCELIUM = createStonecutterRecipe(AtbywMain.Id("dirt_stairs_stonecutting_mycelium"), getBlockID(Blocks.MYCELIUM), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
    public static AtbywRecipe DIRT_STAIRS_STONECUTTING_COARSE_DIRT = createStonecutterRecipe(AtbywMain.Id("dirt_stairs_stonecutting_coarse_dirt"), getBlockID(Blocks.COARSE_DIRT), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
    public static AtbywRecipe DIRT_STAIRS_STONECUTTING_PODZOL = createStonecutterRecipe(AtbywMain.Id("dirt_stairs_stonecutting_podzol"), getBlockID(Blocks.PODZOL), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
    public static AtbywRecipe GRASS_BLOCK_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("grass_block_stairs_stonecutting"), getBlockID(Blocks.GRASS_BLOCK), getBlockID(AtbywBlocks.GRASS_BLOCK_STAIRS), 1);
    public static AtbywRecipe MYCELIUM_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("mycelium_stairs_stonecutting"), getBlockID(Blocks.MYCELIUM), getBlockID(AtbywBlocks.MYCELIUM_STAIRS), 1);
    public static AtbywRecipe COARSE_DIRT_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("coarse_dirt_stairs_stonecutting"), getBlockID(Blocks.COARSE_DIRT), getBlockID(AtbywBlocks.COARSE_DIRT_STAIRS), 1);
    public static AtbywRecipe PODZOL_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("podzol_stairs_stonecutting"), getBlockID(Blocks.PODZOL), getBlockID(AtbywBlocks.PODZOL_STAIRS), 1);
    public static AtbywRecipe NETHERRACK_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("netherrack_stairs_stonecutting"), getBlockID(Blocks.NETHERRACK), getBlockID(AtbywBlocks.NETHERRACK_STAIRS), 1);
    public static AtbywRecipe NETHERRACK_STAIRS_STONECUTTING_CRIMSON_NYLIUM = createStonecutterRecipe(AtbywMain.Id("netherrack_stairs_stonecutting_crimson_nylium"), getBlockID(Blocks.CRIMSON_NYLIUM), getBlockID(AtbywBlocks.NETHERRACK_STAIRS), 1);
    public static AtbywRecipe NETHERRACK_STAIRS_STONECUTTING_WARPED_NYLIUM = createStonecutterRecipe(AtbywMain.Id("netherrack_stairs_stonecutting_warped_nylium"), getBlockID(Blocks.WARPED_NYLIUM), getBlockID(AtbywBlocks.NETHERRACK_STAIRS), 1);
    public static AtbywRecipe CRIMSON_NYLIUM_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("crimson_nylium_stairs_stonecutting"), getBlockID(Blocks.CRIMSON_NYLIUM), getBlockID(AtbywBlocks.CRIMSON_NYLIUM_STAIRS), 1);
    public static AtbywRecipe WARPED_NYLIUM_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("warped_nylium_stairs_stonecutting"), getBlockID(Blocks.WARPED_NYLIUM), getBlockID(AtbywBlocks.WARPED_NYLIUM_STAIRS), 1);

    public static AtbywRecipe DIRT_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("dirt_slab_stonecutting"), getBlockID(Blocks.DIRT), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
    public static AtbywRecipe DIRT_SLAB_STONECUTTING_GRASS_BLOCK = createStonecutterRecipe(AtbywMain.Id("dirt_slab_stonecutting_grass_block"), getBlockID(Blocks.GRASS_BLOCK), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
    public static AtbywRecipe DIRT_SLAB_STONECUTTING_MYCELIUM = createStonecutterRecipe(AtbywMain.Id("dirt_slab_stonecutting_mycelium"), getBlockID(Blocks.MYCELIUM), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
    public static AtbywRecipe DIRT_SLAB_STONECUTTING_COARSE_DIRT = createStonecutterRecipe(AtbywMain.Id("dirt_slab_stonecutting_coarse_dirt"), getBlockID(Blocks.COARSE_DIRT), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
    public static AtbywRecipe DIRT_SLAB_STONECUTTING_PODZOL = createStonecutterRecipe(AtbywMain.Id("dirt_slab_stonecutting_podzol"), getBlockID(Blocks.PODZOL), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
    public static AtbywRecipe GRASS_BLOCK_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("grass_block_slab_stonecutting"), getBlockID(Blocks.GRASS_BLOCK), getBlockID(AtbywBlocks.GRASS_BLOCK_SLAB), 2);
    public static AtbywRecipe MYCELIUM_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("mycelium_slab_stonecutting"), getBlockID(Blocks.MYCELIUM), getBlockID(AtbywBlocks.MYCELIUM_SLAB), 2);
    public static AtbywRecipe COARSE_DIRT_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("coarse_dirt_slab_stonecutting"), getBlockID(Blocks.COARSE_DIRT), getBlockID(AtbywBlocks.COARSE_DIRT_SLAB), 2);
    public static AtbywRecipe PODZOL_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("podzol_slab_stonecutting"), getBlockID(Blocks.PODZOL), getBlockID(AtbywBlocks.PODZOL_SLAB), 2);
    public static AtbywRecipe NETHERRACK_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("netherrack_slab_stonecutting"), getBlockID(Blocks.NETHERRACK), getBlockID(AtbywBlocks.NETHERRACK_SLAB), 2);
    public static AtbywRecipe NETHERRACK_SLAB_STONECUTTING_CRIMSON_NYLIUM = createStonecutterRecipe(AtbywMain.Id("netherrack_slab_stonecutting_crimson_nylium"), getBlockID(Blocks.CRIMSON_NYLIUM), getBlockID(AtbywBlocks.NETHERRACK_SLAB), 2);
    public static AtbywRecipe NETHERRACK_SLAB_STONECUTTING_WARPED_NYLIUM = createStonecutterRecipe(AtbywMain.Id("netherrack_slab_stonecutting_warped_nylium"), getBlockID(Blocks.WARPED_NYLIUM), getBlockID(AtbywBlocks.NETHERRACK_SLAB), 2);
    public static AtbywRecipe CRIMSON_NYLIUM_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("crimson_nylium_slab_stonecutting"), getBlockID(Blocks.CRIMSON_NYLIUM), getBlockID(AtbywBlocks.CRIMSON_NYLIUM_SLAB), 2);
    public static AtbywRecipe WARPED_NYLIUM_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("warped_nylium_slab_stonecutting"), getBlockID(Blocks.WARPED_NYLIUM), getBlockID(AtbywBlocks.WARPED_NYLIUM_SLAB), 2);

    public static AtbywRecipe BASALT_BRICKS_STONECUTTING_BASALT = createStonecutterRecipe(AtbywMain.Id("basalt_bricks_stonecutting_basalt"), getBlockID(Blocks.BASALT), getBlockID(AtbywBlocks.BASALT_BRICKS), 1);
    public static AtbywRecipe BASALT_PILLAR_STONECUTTING_BASALT = createStonecutterRecipe(AtbywMain.Id("basalt_pillar_stonecutting_basalt"), getBlockID(Blocks.BASALT), getBlockID(AtbywBlocks.BASALT_PILLAR), 1);
    public static AtbywRecipe BASALT_BRICKS_STONECUTTING_POLISHED_BASALT = createStonecutterRecipe(AtbywMain.Id("basalt_bricks_stonecutting_polished_basalt"), getBlockID(Blocks.POLISHED_BASALT), getBlockID(AtbywBlocks.BASALT_BRICKS), 1);
    public static AtbywRecipe BASALT_PILLAR_STONECUTTING_POLISHED_BASALT = createStonecutterRecipe(AtbywMain.Id("basalt_pillar_stonecutting_polished_basalt"), getBlockID(Blocks.POLISHED_BASALT), getBlockID(AtbywBlocks.BASALT_PILLAR), 1);

    public static AtbywRecipe GRANITE_TILES_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("granite_tiles_stonecutting"), getBlockID(Blocks.GRANITE), getBlockID(AtbywBlocks.GRANITE_TILES), 1);
    public static AtbywRecipe DIORITE_BRICKS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("diorite_bricks_stonecutting"), getBlockID(Blocks.DIORITE), getBlockID(AtbywBlocks.DIORITE_BRICKS), 1);
    public static AtbywRecipe ANDESITE_BRICKS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("andesite_bricks_stonecutting"), getBlockID(Blocks.ANDESITE), getBlockID(AtbywBlocks.ANDESITE_BRICKS), 1);
    public static AtbywRecipe GRANITE_TILES_STONECUTTING_POLISHED = createStonecutterRecipe(AtbywMain.Id("granite_tiles_stonecutting_from_polished"), getBlockID(Blocks.POLISHED_GRANITE), getBlockID(AtbywBlocks.GRANITE_TILES), 1);
    public static AtbywRecipe DIORITE_BRICKS_STONECUTTING_POLISHED = createStonecutterRecipe(AtbywMain.Id("diorite_bricks_stonecutting_from_polished"), getBlockID(Blocks.POLISHED_DIORITE), getBlockID(AtbywBlocks.DIORITE_BRICKS), 1);
    public static AtbywRecipe ANDESITE_BRICKS_STONECUTTING_POLISHED = createStonecutterRecipe(AtbywMain.Id("andesite_bricks_stonecutting_from_polished"), getBlockID(Blocks.POLISHED_ANDESITE), getBlockID(AtbywBlocks.ANDESITE_BRICKS), 1);

    public static AtbywRecipe GRANITE_TILES_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("granite_tiles_stairs_stonecutting"), getBlockID(Blocks.GRANITE), getBlockID(AtbywBlocks.GRANITE_TILES_STAIRS), 1);
    public static AtbywRecipe DIORITE_BRICKS_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("diorite_bricks_stairs_stonecutting"), getBlockID(Blocks.DIORITE), getBlockID(AtbywBlocks.DIORITE_BRICKS_STAIRS), 1);
    public static AtbywRecipe ANDESITE_BRICKS_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("andesite_bricks_stairs_stonecutting"), getBlockID(Blocks.ANDESITE), getBlockID(AtbywBlocks.ANDESITE_BRICKS_STAIRS), 1);
    public static AtbywRecipe GRANITE_TILES_STAIRS_STONECUTTING_POLISHED = createStonecutterRecipe(AtbywMain.Id("granite_tiles_stairs_stonecutting_from_polished"), getBlockID(Blocks.POLISHED_GRANITE), getBlockID(AtbywBlocks.GRANITE_TILES_STAIRS), 1);
    public static AtbywRecipe DIORITE_BRICKS_STAIRS_STONECUTTING_POLISHED = createStonecutterRecipe(AtbywMain.Id("diorite_bricks_stairs_stonecutting_from_polished"), getBlockID(Blocks.POLISHED_DIORITE), getBlockID(AtbywBlocks.DIORITE_BRICKS_STAIRS), 1);
    public static AtbywRecipe ANDESITE_BRICKS_STAIRS_STONECUTTING_POLISHED = createStonecutterRecipe(AtbywMain.Id("andesite_bricks_stairs_stonecutting_from_polished"), getBlockID(Blocks.POLISHED_ANDESITE), getBlockID(AtbywBlocks.ANDESITE_BRICKS_STAIRS), 1);
    public static AtbywRecipe GRANITE_TILES_STAIRS_STONECUTTING_SELF = createStonecutterRecipe(AtbywMain.Id("granite_tiles_stairs_stonecutting_from_self"), getBlockID(AtbywBlocks.GRANITE_TILES), getBlockID(AtbywBlocks.GRANITE_TILES_STAIRS), 1);
    public static AtbywRecipe DIORITE_BRICKS_STAIRS_STONECUTTING_SELF = createStonecutterRecipe(AtbywMain.Id("diorite_bricks_stairs_stonecutting_from_self"), getBlockID(AtbywBlocks.DIORITE_BRICKS), getBlockID(AtbywBlocks.DIORITE_BRICKS_STAIRS), 1);
    public static AtbywRecipe ANDESITE_BRICKS_STAIRS_STONECUTTING_SELF = createStonecutterRecipe(AtbywMain.Id("andesite_bricks_stairs_stonecutting_from_self"), getBlockID(AtbywBlocks.ANDESITE_BRICKS), getBlockID(AtbywBlocks.ANDESITE_BRICKS_STAIRS), 1);

    public static AtbywRecipe GRANITE_TILES_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("granite_tiles_slab_stonecutting"), getBlockID(Blocks.GRANITE), getBlockID(AtbywBlocks.GRANITE_TILES_SLAB), 1);
    public static AtbywRecipe DIORITE_BRICKS_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("diorite_bricks_slab_stonecutting"), getBlockID(Blocks.DIORITE), getBlockID(AtbywBlocks.DIORITE_BRICKS_SLAB), 1);
    public static AtbywRecipe ANDESITE_BRICKS_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("andesite_bricks_slab_stonecutting"), getBlockID(Blocks.ANDESITE), getBlockID(AtbywBlocks.ANDESITE_BRICKS_SLAB), 1);
    public static AtbywRecipe GRANITE_TILES_SLAB_STONECUTTING_POLISHED = createStonecutterRecipe(AtbywMain.Id("granite_tiles_slab_stonecutting_from_polished"), getBlockID(Blocks.POLISHED_GRANITE), getBlockID(AtbywBlocks.GRANITE_TILES_SLAB), 1);
    public static AtbywRecipe DIORITE_BRICKS_SLAB_STONECUTTING_POLISHED = createStonecutterRecipe(AtbywMain.Id("diorite_bricks_slab_stonecutting_from_polished"), getBlockID(Blocks.POLISHED_DIORITE), getBlockID(AtbywBlocks.DIORITE_BRICKS_SLAB), 1);
    public static AtbywRecipe ANDESITE_BRICKS_SLAB_STONECUTTING_POLISHED = createStonecutterRecipe(AtbywMain.Id("andesite_bricks_slab_stonecutting_from_polished"), getBlockID(Blocks.POLISHED_ANDESITE), getBlockID(AtbywBlocks.ANDESITE_BRICKS_SLAB), 1);
    public static AtbywRecipe GRANITE_TILES_SLAB_STONECUTTING_SELF = createStonecutterRecipe(AtbywMain.Id("granite_tiles_slab_stonecutting_from_self"), getBlockID(AtbywBlocks.GRANITE_TILES), getBlockID(AtbywBlocks.GRANITE_TILES_SLAB), 1);
    public static AtbywRecipe DIORITE_BRICKS_SLAB_STONECUTTING_SELF = createStonecutterRecipe(AtbywMain.Id("diorite_bricks_slab_stonecutting_from_self"), getBlockID(AtbywBlocks.DIORITE_BRICKS), getBlockID(AtbywBlocks.DIORITE_BRICKS_SLAB), 1);
    public static AtbywRecipe ANDESITE_BRICKS_SLAB_STONECUTTING_SELF = createStonecutterRecipe(AtbywMain.Id("andesite_bricks_slab_stonecutting_from_self"), getBlockID(AtbywBlocks.ANDESITE_BRICKS), getBlockID(AtbywBlocks.ANDESITE_BRICKS_SLAB), 1);

    public static AtbywRecipe TERRACOTTA_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("terracotta_stairs_from_stonecutting"), getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_STAIRS), 1);
    public static AtbywRecipe TERRACOTTA_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("terracotta_slabs_from_stonecutting"), getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_SLAB), 2);
    public static AtbywRecipe TERRACOTTA_BRICKS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("terracotta_bricks_from_stonecutting"), getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), 1);
    public static AtbywRecipe TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA = createStonecutterRecipe(AtbywMain.Id("terracotta_bricks_stairs_from_stonecutting_terracotta"), getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_STAIRS),1);
    public static AtbywRecipe TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA = createStonecutterRecipe(AtbywMain.Id("terracotta_bricks_slabs_from_stonecutting_terracotta"), getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_SLAB),2);
    public static AtbywRecipe TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA = createStonecutterRecipe(AtbywMain.Id("terracotta_bricks_walls_from_stonecutting_terracotta"), getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_WALL),1);
    public static AtbywRecipe TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterRecipe(AtbywMain.Id("terracotta_bricks_stairs_from_stonecutting_terracotta_bricks"), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_STAIRS), 1);
    public static AtbywRecipe TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterRecipe(AtbywMain.Id("terracotta_bricks_slabs_from_stonecutting_terracotta_bricks"), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_SLAB), 2);
    public static AtbywRecipe TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterRecipe(AtbywMain.Id("terracotta_bricks_walls_from_stonecutting_terracotta_bricks"), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_WALL), 1);

    public static AtbywRecipe[] TERRACOTTA_STAIRS_COLORS_STONECUTTING = createStonecutterColoredRecipes(AtbywMain.Id("_terracotta_stairs_from_stonecutting"), new Pair<>("minecraft", "terracotta"), new Pair<>(ATBYW, "terracotta_stairs"), 1);
    public static AtbywRecipe[] TERRACOTTA_SLAB_COLORS_STONECUTTING = createStonecutterColoredRecipes(AtbywMain.Id("_terracotta_slabs_from_stonecutting"), new Pair<>("minecraft", "terracotta"), new Pair<>(ATBYW, "terracotta_slab"), 2);
    public static AtbywRecipe[] TERRACOTTA_BRICKS_COLORS_STONECUTTING = createStonecutterColoredRecipes(AtbywMain.Id("_terracotta_bricks_from_stonecutting"), new Pair<>("minecraft", "terracotta"), new Pair<>(ATBYW, "terracotta_bricks"), 1);
    public static AtbywRecipe[] TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA = createStonecutterColoredRecipes(AtbywMain.Id("_terracotta_bricks_stairs_from_stonecutting_terracotta"), new Pair<>("minecraft", "terracotta"), new Pair<>(ATBYW, "terracotta_bricks_stairs"), 1);
    public static AtbywRecipe[] TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA = createStonecutterColoredRecipes(AtbywMain.Id("_terracotta_bricks_slabs_from_stonecutting_terracotta"), new Pair<>("minecraft", "terracotta"), new Pair<>(ATBYW, "terracotta_bricks_slab"), 2);
    public static AtbywRecipe[] TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA = createStonecutterColoredRecipes(AtbywMain.Id("_terracotta_bricks_walls_from_stonecutting_terracotta"), new Pair<>("minecraft", "terracotta"), new Pair<>(ATBYW, "terracotta_bricks_wall"), 1);
    public static AtbywRecipe[] TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterColoredRecipes(AtbywMain.Id("_terracotta_bricks_stairs_from_stonecutting_terracotta_bricks"), new Pair<>(ATBYW, "terracotta_bricks"), new Pair<>(ATBYW, "terracotta_bricks_stairs"), 1);
    public static AtbywRecipe[] TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterColoredRecipes(AtbywMain.Id("_terracotta_bricks_slabs_from_stonecutting_terracotta_bricks"), new Pair<>(ATBYW, "terracotta_bricks"), new Pair<>(ATBYW, "terracotta_bricks_slab"), 2);
    public static AtbywRecipe[] TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterColoredRecipes(AtbywMain.Id("_terracotta_bricks_walls_from_stonecutting_terracotta_bricks"), new Pair<>(ATBYW, "terracotta_bricks"), new Pair<>(ATBYW, "terracotta_bricks_wall"), 1);

    public static AtbywRecipe[] CONCRETE_STAIRS_COLORS_STONECUTTING = createStonecutterColoredRecipes(AtbywMain.Id("_concrete_stairs_from_stonecutting"), new Pair<>("minecraft", "concrete"), new Pair<>(ATBYW, "concrete_stairs"), 1);
    public static AtbywRecipe[] CONCRETE_SLAB_COLORS_STONECUTTING = createStonecutterColoredRecipes(AtbywMain.Id("_concrete_slab_from_stonecutting"), new Pair<>("minecraft", "concrete"), new Pair<>(ATBYW, "concrete_slab"), 2);
    public static AtbywRecipe[] CINDER_BLOCKS_COLORS_STONECUTTING = createStonecutterColoredRecipes(AtbywMain.Id("_cinder_blocks_from_stonecutting"), new Pair<>("minecraft", "concrete"), new Pair<>(ATBYW, "cinder_bricks"), 1);
    public static AtbywRecipe[] CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CONCRETE = createStonecutterColoredRecipes(AtbywMain.Id("_cinder_blocks_wall_from_stonecutting_concrete"), new Pair<>("minecraft", "concrete"), new Pair<>(ATBYW, "cinder_blocks_wall"), 1);
    public static AtbywRecipe[] CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CINDER_BLOCKS = createStonecutterColoredRecipes(AtbywMain.Id("_cinder_blocks_wall_from_stonecutting_cinder_blocks"), new Pair<>(ATBYW, "cinder_bricks"), new Pair<>(ATBYW, "cinder_blocks_wall"), 1);

    public static AtbywRecipe GRANITE_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("granite_column_stonecutting"), getBlockID(Blocks.GRANITE), getBlockID(AtbywBlocks.GRANITE_COLUMN), 1);
    public static AtbywRecipe DIORITE_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("diorite_column_stonecutting"), getBlockID(Blocks.DIORITE), getBlockID(AtbywBlocks.DIORITE_COLUMN), 1);
    public static AtbywRecipe ANDESITE_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("andesite_column_stonecutting"), getBlockID(Blocks.ANDESITE), getBlockID(AtbywBlocks.ANDESITE_COLUMN), 1);
    public static AtbywRecipe GRANITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("granite_column_from_polished_stonecutting"), getBlockID(Blocks.POLISHED_GRANITE), getBlockID(AtbywBlocks.GRANITE_COLUMN), 1);
    public static AtbywRecipe DIORITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("diorite_column_from_polished_stonecutting"), getBlockID(Blocks.POLISHED_DIORITE), getBlockID(AtbywBlocks.DIORITE_COLUMN), 1);
    public static AtbywRecipe ANDESITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("andesite_column_from_polished_stonecutting"), getBlockID(Blocks.POLISHED_ANDESITE), getBlockID(AtbywBlocks.ANDESITE_COLUMN), 1);
    public static AtbywRecipe SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("sandstone_column_stonecutting"), getBlockID(Blocks.SANDSTONE), getBlockID(AtbywBlocks.SANDSTONE_COLUMN), 1);
    public static AtbywRecipe SANDSTONE_COLUMN_FROM_CUT_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("sandstone_column_from_cut_stonecutting"), getBlockID(Blocks.CUT_SANDSTONE), getBlockID(AtbywBlocks.SANDSTONE_COLUMN), 1);
    public static AtbywRecipe CHISELED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("chiseled_sandstone_column_stonecutting"), getBlockID(Blocks.CHISELED_SANDSTONE), getBlockID(AtbywBlocks.CHISELED_SANDSTONE_COLUMN), 1);
    public static AtbywRecipe RED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("red_sandstone_column_stonecutting"), getBlockID(Blocks.RED_SANDSTONE), getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), 1);
    public static AtbywRecipe RED_SANDSTONE_COLUMN_FROM_CUT_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("red_sandstone_column_from_cut_stonecutting"), getBlockID(Blocks.CUT_RED_SANDSTONE), getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), 1);
    public static AtbywRecipe CHISELED_RED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("chiseled_red_sandstone_column_stonecutting"), getBlockID(Blocks.CHISELED_RED_SANDSTONE), getBlockID(AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN), 1);
    public static AtbywRecipe PURPUR_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("purpur_column_stonecutting"), getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.PURPUR_COLUMN), 1);
    public static AtbywRecipe STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("stone_bricks_column_stonecutting"), getBlockID(Blocks.STONE_BRICKS), getBlockID(AtbywBlocks.STONE_BRICKS_COLUMN), 1);
    public static AtbywRecipe MOSSY_STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("mossy_stone_bricks_column_stonecutting"), getBlockID(Blocks.MOSSY_STONE_BRICKS), getBlockID(AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN), 1);
    public static AtbywRecipe CRACKED_STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("cracked_stone_bricks_column_stonecutting"), getBlockID(Blocks.CRACKED_STONE_BRICKS), getBlockID(AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN), 1);
    public static AtbywRecipe NETHER_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("nether_bricks_column_stonecutting"), getBlockID(Blocks.NETHER_BRICKS), getBlockID(AtbywBlocks.NETHER_BRICKS_COLUMN), 1);
    public static AtbywRecipe QUARTZ_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("quartz_column_stonecutting"), getBlockID(Blocks.QUARTZ_BLOCK), getBlockID(AtbywBlocks.QUARTZ_COLUMN), 1);
    public static AtbywRecipe PRISMARINE_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("prismarine_column_stonecutting"), getBlockID(Blocks.PRISMARINE_BRICKS), getBlockID(AtbywBlocks.PRISMARINE_COLUMN), 1);
    public static AtbywRecipe BLACKSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("blackstone_column_stonecutting"), getBlockID(Blocks.BLACKSTONE), getBlockID(AtbywBlocks.BLACKSTONE_COLUMN), 1);

    public static AtbywRecipe PURPUR_TILES_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("purpur_tiles_stonecutting"), getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK), 1);
    public static AtbywRecipe SMOOTH_PURPUR_BLOCK_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("smooth_purpur_block_stonecutting"), getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.PURPUR_TILES), 1);
    public static AtbywRecipe CHISELED_PURPUR_BLOCK_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("chiseled_purpur_block_stonecutting"), getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.CHISELED_PURPUR_BLOCK), 1);
    public static AtbywRecipe PURPUR_TILES_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("purpur_tiles_stairs_stonecutting"), getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.PURPUR_TILES_STAIRS), 1);
    public static AtbywRecipe SMOOTH_PURPUR_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("smooth_purpur_stairs_stonecutting"), getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_STAIRS), 1);
    public static AtbywRecipe PURPUR_TILES_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("purpur_tiles_slab_stonecutting"), getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.PURPUR_TILES_SLAB), 2);
    public static AtbywRecipe SMOOTH_PURPUR_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("smooth_purpur_slab_stonecutting"), getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_SLAB), 2);
    public static AtbywRecipe PURPUR_TILES_STAIRS_FROM_PURPUR_TILES_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("purpur_tiles_stairs_from_purpur_tiles_stonecutting"), getBlockID(AtbywBlocks.PURPUR_TILES), getBlockID(AtbywBlocks.PURPUR_TILES_STAIRS), 1);
    public static AtbywRecipe SMOOTH_PURPUR_STAIRS_FROM_SMOOTH_PURPUR_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("smooth_purpur_stairs_from_smooth_purpur_stonecutting"), getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_STAIRS), 1);
    public static AtbywRecipe PURPUR_TILES_SLAB_FROM_PURPUR_TILES_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("purpur_tiles_slab_from_purpur_tiles_stonecutting"), getBlockID(AtbywBlocks.PURPUR_TILES), getBlockID(AtbywBlocks.PURPUR_TILES_SLAB), 2);
    public static AtbywRecipe SMOOTH_PURPUR_SLAB_FROM_SMOOTH_PURPUR_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("smooth_purpur_slab_from_smooth_purpur_stonecutting"), getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_SLAB), 2);

    public static AtbywRecipe COMPACTED_SNOW_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("compacted_snow_stonecutting"), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW), 8);
    public static AtbywRecipe COMPACTED_SNOW_BRICKS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("compacted_snow_bricks_stonecutting"), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS), 1);
    public static AtbywRecipe PACKED_ICE_BRICKS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("packed_ice_bricks_stonecutting"), getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), 1);
    public static AtbywRecipe BLUE_ICE_BRICKS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("blue_ice_bricks_stonecutting"), getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), 1);
    public static AtbywRecipe CHISELED_PACKED_ICE_BRICKS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("chiseled_packed_ice_bricks_stonecutting"), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), getBlockID(AtbywBlocks.CHISELED_PACKED_ICE_BRICKS), 1);
    public static AtbywRecipe CHISELED_BLUE_ICE_BRICKS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("chiseled_blue_ice_bricks_stonecutting"), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), getBlockID(AtbywBlocks.CHISELED_BLUE_ICE_BRICKS), 1);
    public static AtbywRecipe CHISELED_PACKED_ICE_BRICKS_STONECUTTING_PACKED_ICE = createStonecutterRecipe(AtbywMain.Id("chiseled_packed_ice_bricks_stonecutting_packed_ice"), getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.CHISELED_PACKED_ICE_BRICKS), 1);
    public static AtbywRecipe CHISELED_BLUE_ICE_BRICKS_STONECUTTING_BLUE_ICE = createStonecutterRecipe(AtbywMain.Id("chiseled_blue_ice_bricks_stonecutting_blue_ice"), getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.CHISELED_BLUE_ICE_BRICKS), 1);

    public static AtbywRecipe COMPACTED_SNOW_BLOCK_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("compacted_snow_block_stairs_stonecutting"), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS), 1);
    public static AtbywRecipe COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("compacted_snow_bricks_stairs_stonecutting"), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS), 1);
    public static AtbywRecipe COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING_COMPACTED_SNOW_BLOCK = createStonecutterRecipe(AtbywMain.Id("compacted_snow_bricks_stairs_stonecutting_compacted_snow_block"), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS), 1);
    public static AtbywRecipe PACKED_ICE_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("packed_ice_stairs_stonecutting"), getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_STAIRS), 1);
    public static AtbywRecipe PACKED_ICE_BRICKS_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("packed_ice_bricks_stairs_stonecutting"), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS), 1);
    public static AtbywRecipe PACKED_ICE_BRICKS_STAIRS_STONECUTTING_PACKED_ICE = createStonecutterRecipe(AtbywMain.Id("packed_ice_bricks_stairs_stonecutting_packed_ice"), getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS), 1);
    public static AtbywRecipe BLUE_ICE_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("blue_ice_stairs_stonecutting"), getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_STAIRS), 1);
    public static AtbywRecipe BLUE_ICE_BRICKS_STAIRS_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("blue_ice_bricks_stairs_stonecutting"), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_STAIRS), 1);
    public static AtbywRecipe BLUE_ICE_BRICKS_STAIRS_STONECUTTING_BLUE_ICE = createStonecutterRecipe(AtbywMain.Id("blue_ice_bricks_stairs_stonecutting_blue_ice"), getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_STAIRS), 1);

    public static AtbywRecipe COMPACTED_SNOW_BLOCK_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("compacted_snow_block_slab_stonecutting"), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB), 2);
    public static AtbywRecipe COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("compacted_snow_bricks_slab_stonecutting"), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB), 2);
    public static AtbywRecipe COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING_COMPACTED_SNOW_BLOCK = createStonecutterRecipe(AtbywMain.Id("compacted_snow_bricks_slab_stonecutting_compacted_snow_block"), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB), 2);
    public static AtbywRecipe PACKED_ICE_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("packed_ice_slab_stonecutting"), getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_SLAB), 2);
    public static AtbywRecipe PACKED_ICE_BRICKS_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("packed_ice_bricks_slab_stonecutting"), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB), 2);
    public static AtbywRecipe PACKED_ICE_BRICKS_SLAB_STONECUTTING_PACKED_ICE = createStonecutterRecipe(AtbywMain.Id("packed_ice_bricks_slab_stonecutting_packed_ice"), getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB), 2);
    public static AtbywRecipe BLUE_ICE_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("blue_ice_slab_stonecutting"), getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_SLAB), 2);
    public static AtbywRecipe BLUE_ICE_BRICKS_SLAB_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("blue_ice_bricks_slab_stonecutting"), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB), 2);
    public static AtbywRecipe BLUE_ICE_BRICKS_SLAB_STONECUTTING_BLUE_ICE = createStonecutterRecipe(AtbywMain.Id("blue_ice_bricks_slab_stonecutting_blue_ice"), getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB), 2);

    public static AtbywRecipe BEE_STATUE_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("bee_statue_stonecutting"), getBlockID(Blocks.STONE), getBlockID(StatueRegistry.BEE_STATUE), 1);
    public static AtbywRecipe SILVERFISH_STATUE_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("silverfish_statue_stonecutting"), getBlockID(Blocks.STONE), getBlockID(StatueRegistry.SILVERFISH_STATUE), 1);
    public static AtbywRecipe ENDERMITE_STATUE_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("endermite_statue_stonecutting"), getBlockID(Blocks.STONE), getBlockID(StatueRegistry.ENDERMITE_STATUE), 1);
    public static AtbywRecipe WOLF_STATUE_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("wolf_statue_stonecutting"), getBlockID(Blocks.STONE), getBlockID(StatueRegistry.WOLF_STATUE), 1);
    public static AtbywRecipe CAT_STATUE_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("cat_statue_stonecutting"), getBlockID(Blocks.STONE), getBlockID(StatueRegistry.CAT_STATUE), 1);
    public static AtbywRecipe FOX_STATUE_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("fox_statue_stonecutting"), getBlockID(Blocks.STONE), getBlockID(StatueRegistry.FOX_STATUE), 1);

    public static AtbywRecipe AXOLOTL_STATUE_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("axolotl_statue_stonecutting"), getBlockID(Blocks.STONE), getBlockID(StatueRegistry.AXOLOTL_STATUE), 1);
    public static AtbywRecipe BAT_STATUE_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("bat_statue_stonecutting"), getBlockID(Blocks.STONE), getBlockID(StatueRegistry.BAT_STATUE), 1);

    public static AtbywRecipe ROOTED_DIRT_STAIRS_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("rooted_dirt_stairs_stonecutting"), getBlockID(Blocks.ROOTED_DIRT), getBlockID(AtbywBlocks.ROOTED_DIRT_STAIRS), 1);
    public static AtbywRecipe ROOTED_DIRT_SLAB_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("rooted_dirt_slab_stonecutting"), getBlockID(Blocks.ROOTED_DIRT), getBlockID(AtbywBlocks.ROOTED_DIRT_SLAB), 2);

    public static AtbywRecipe SAND_STAIRS_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("sand_stairs_stonecutting"), getBlockID(Blocks.SAND), getBlockID(AtbywBlocks.SAND_STAIRS), 1);
    public static AtbywRecipe SAND_SLAB_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("sand_slab_stonecutting"), getBlockID(Blocks.SAND), getBlockID(AtbywBlocks.SAND_SLAB), 2);
    public static AtbywRecipe RED_SAND_STAIRS_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("red_sand_stairs_stonecutting"), getBlockID(Blocks.RED_SAND), getBlockID(AtbywBlocks.RED_SAND_STAIRS), 1);
    public static AtbywRecipe RED_SAND_SLAB_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("red_sand_slab_stonecutting"), getBlockID(Blocks.RED_SAND), getBlockID(AtbywBlocks.RED_SAND_SLAB), 2);
    public static AtbywRecipe GRAVEL_STAIRS_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("gravel_stairs_stonecutting"), getBlockID(Blocks.GRAVEL), getBlockID(AtbywBlocks.GRAVEL_STAIRS), 1);
    public static AtbywRecipe GRAVEL_SLAB_FROM_STONECUTTING = createStonecutterRecipe(AtbywMain.Id("gravel_slab_stonecutting"), getBlockID(Blocks.GRAVEL), getBlockID(AtbywBlocks.GRAVEL_SLAB), 2);

    public static void inject(Map<Identifier, JsonElement> map) {
        for (int i = 0; i < ColorNames.values().length; i++) {
            putRecipe(TERRACOTTA_STAIRS_COLORS_STONECUTTING[i], map);
            putRecipe(TERRACOTTA_SLAB_COLORS_STONECUTTING[i], map);
            putRecipe(TERRACOTTA_BRICKS_COLORS_STONECUTTING[i], map);
            putRecipe(TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA[i], map);
            putRecipe(TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA[i], map);
            putRecipe(TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA[i], map);
            putRecipe(TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA_BRICKS[i], map);
            putRecipe(TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA_BRICKS[i], map);
            putRecipe(TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA_BRICKS[i], map);

            putRecipe(CONCRETE_STAIRS_COLORS_STONECUTTING[i], map);
            putRecipe(CONCRETE_SLAB_COLORS_STONECUTTING[i], map);
            putRecipe(CINDER_BLOCKS_COLORS_STONECUTTING[i], map);
            putRecipe(CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CONCRETE[i], map);
            putRecipe(CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CINDER_BLOCKS[i], map);
        }

        putRecipe(DIRT_STAIRS_STONECUTTING, map);
        putRecipe(DIRT_STAIRS_STONECUTTING_GRASS_BLOCK, map);
        putRecipe(DIRT_STAIRS_STONECUTTING_MYCELIUM, map);
        putRecipe(DIRT_STAIRS_STONECUTTING_COARSE_DIRT, map);
        putRecipe(DIRT_STAIRS_STONECUTTING_PODZOL, map);
        putRecipe(GRASS_BLOCK_STAIRS_STONECUTTING, map);
        putRecipe(MYCELIUM_STAIRS_STONECUTTING, map);
        putRecipe(COARSE_DIRT_STAIRS_STONECUTTING, map);
        putRecipe(PODZOL_STAIRS_STONECUTTING, map);
        putRecipe(NETHERRACK_STAIRS_STONECUTTING, map);
        putRecipe(NETHERRACK_STAIRS_STONECUTTING_CRIMSON_NYLIUM, map);
        putRecipe(NETHERRACK_STAIRS_STONECUTTING_WARPED_NYLIUM, map);
        putRecipe(CRIMSON_NYLIUM_STAIRS_STONECUTTING, map);
        putRecipe(WARPED_NYLIUM_STAIRS_STONECUTTING, map);

         putRecipe(DIRT_SLAB_STONECUTTING, map);
         putRecipe(DIRT_SLAB_STONECUTTING_GRASS_BLOCK, map);
         putRecipe(DIRT_SLAB_STONECUTTING_MYCELIUM, map);
         putRecipe(DIRT_SLAB_STONECUTTING_COARSE_DIRT, map);
         putRecipe(DIRT_SLAB_STONECUTTING_PODZOL, map);
         putRecipe(GRASS_BLOCK_SLAB_STONECUTTING, map);
         putRecipe(MYCELIUM_SLAB_STONECUTTING, map);
         putRecipe(COARSE_DIRT_SLAB_STONECUTTING, map);
         putRecipe(PODZOL_SLAB_STONECUTTING, map);
         putRecipe(NETHERRACK_SLAB_STONECUTTING, map);
         putRecipe(NETHERRACK_SLAB_STONECUTTING_CRIMSON_NYLIUM, map);
         putRecipe(NETHERRACK_SLAB_STONECUTTING_WARPED_NYLIUM, map);
         putRecipe(CRIMSON_NYLIUM_SLAB_STONECUTTING, map);
         putRecipe(WARPED_NYLIUM_SLAB_STONECUTTING, map);

        putRecipe(BASALT_BRICKS_STONECUTTING_BASALT, map);
        putRecipe(BASALT_PILLAR_STONECUTTING_BASALT, map);
        putRecipe(BASALT_BRICKS_STONECUTTING_POLISHED_BASALT, map);
        putRecipe(BASALT_PILLAR_STONECUTTING_POLISHED_BASALT, map);

        putRecipe(GRANITE_TILES_STONECUTTING, map);
        putRecipe(DIORITE_BRICKS_STONECUTTING, map);
        putRecipe(ANDESITE_BRICKS_STONECUTTING, map);
        putRecipe(GRANITE_TILES_STONECUTTING_POLISHED, map);
        putRecipe(DIORITE_BRICKS_STONECUTTING_POLISHED, map);
        putRecipe(ANDESITE_BRICKS_STONECUTTING_POLISHED, map);

        putRecipe(GRANITE_TILES_STAIRS_STONECUTTING, map);
        putRecipe(DIORITE_BRICKS_STAIRS_STONECUTTING, map);
        putRecipe(ANDESITE_BRICKS_STAIRS_STONECUTTING, map);
        putRecipe(GRANITE_TILES_STAIRS_STONECUTTING_POLISHED, map);
        putRecipe(DIORITE_BRICKS_STAIRS_STONECUTTING_POLISHED, map);
        putRecipe(ANDESITE_BRICKS_STAIRS_STONECUTTING_POLISHED, map);
        putRecipe(GRANITE_TILES_STAIRS_STONECUTTING_SELF, map);
        putRecipe(DIORITE_BRICKS_STAIRS_STONECUTTING_SELF, map);
        putRecipe(ANDESITE_BRICKS_STAIRS_STONECUTTING_SELF, map);

        putRecipe(GRANITE_TILES_SLAB_STONECUTTING, map);
        putRecipe(DIORITE_BRICKS_SLAB_STONECUTTING, map);
        putRecipe(ANDESITE_BRICKS_SLAB_STONECUTTING, map);
        putRecipe(GRANITE_TILES_SLAB_STONECUTTING_POLISHED, map);
        putRecipe(DIORITE_BRICKS_SLAB_STONECUTTING_POLISHED, map);
        putRecipe(ANDESITE_BRICKS_SLAB_STONECUTTING_POLISHED, map);
        putRecipe(GRANITE_TILES_SLAB_STONECUTTING_SELF, map);
        putRecipe(DIORITE_BRICKS_SLAB_STONECUTTING_SELF, map);
        putRecipe(ANDESITE_BRICKS_SLAB_STONECUTTING_SELF, map);

        putRecipe(TERRACOTTA_STAIRS_STONECUTTING, map);
        putRecipe(TERRACOTTA_SLAB_STONECUTTING, map);
        putRecipe(TERRACOTTA_BRICKS_STONECUTTING, map);
        putRecipe(TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA, map);
        putRecipe(TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA, map);
        putRecipe(TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA, map);
        putRecipe(TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA_BRICKS, map);
        putRecipe(TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA_BRICKS, map);
        putRecipe(TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA_BRICKS, map);

        putRecipe(GRANITE_COLUMN_STONECUTTING, map);
        putRecipe(DIORITE_COLUMN_STONECUTTING, map);
        putRecipe(ANDESITE_COLUMN_STONECUTTING, map);
        putRecipe(GRANITE_COLUMN_FROM_POLISHED_STONECUTTING, map);
        putRecipe(DIORITE_COLUMN_FROM_POLISHED_STONECUTTING, map);
        putRecipe(ANDESITE_COLUMN_FROM_POLISHED_STONECUTTING, map);
        putRecipe(SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(SANDSTONE_COLUMN_FROM_CUT_STONECUTTING, map);
        putRecipe(CHISELED_SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(RED_SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(RED_SANDSTONE_COLUMN_FROM_CUT_STONECUTTING, map);
        putRecipe(CHISELED_RED_SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(PURPUR_COLUMN_STONECUTTING, map);
        putRecipe(STONE_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(MOSSY_STONE_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(CRACKED_STONE_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(NETHER_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(QUARTZ_COLUMN_STONECUTTING, map);
        putRecipe(PRISMARINE_COLUMN_STONECUTTING, map);
        putRecipe(BLACKSTONE_COLUMN_STONECUTTING, map);

        putRecipe(PURPUR_TILES_STONECUTTING, map);
        putRecipe(SMOOTH_PURPUR_BLOCK_STONECUTTING, map);
        putRecipe(CHISELED_PURPUR_BLOCK_STONECUTTING, map);
        putRecipe(PURPUR_TILES_STAIRS_STONECUTTING, map);
        putRecipe(SMOOTH_PURPUR_STAIRS_STONECUTTING, map);
        putRecipe(PURPUR_TILES_SLAB_STONECUTTING, map);
        putRecipe(SMOOTH_PURPUR_SLAB_STONECUTTING, map);
        putRecipe(PURPUR_TILES_STAIRS_FROM_PURPUR_TILES_STONECUTTING, map);
        putRecipe(SMOOTH_PURPUR_STAIRS_FROM_SMOOTH_PURPUR_STONECUTTING, map);
        putRecipe(PURPUR_TILES_SLAB_FROM_PURPUR_TILES_STONECUTTING, map);
        putRecipe(SMOOTH_PURPUR_SLAB_FROM_SMOOTH_PURPUR_STONECUTTING, map);

        putRecipe(COMPACTED_SNOW_STONECUTTING, map);
        putRecipe(COMPACTED_SNOW_BRICKS_STONECUTTING, map);
        putRecipe(PACKED_ICE_BRICKS_STONECUTTING, map);
        putRecipe(BLUE_ICE_BRICKS_STONECUTTING, map);
        putRecipe(CHISELED_PACKED_ICE_BRICKS_STONECUTTING, map);
        putRecipe(CHISELED_BLUE_ICE_BRICKS_STONECUTTING, map);
        putRecipe(CHISELED_PACKED_ICE_BRICKS_STONECUTTING_PACKED_ICE, map);
        putRecipe(CHISELED_BLUE_ICE_BRICKS_STONECUTTING_BLUE_ICE, map);

        putRecipe(COMPACTED_SNOW_BLOCK_STAIRS_STONECUTTING, map);
        putRecipe(COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING, map);
        putRecipe(COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING_COMPACTED_SNOW_BLOCK, map);
        putRecipe(PACKED_ICE_STAIRS_STONECUTTING, map);
        putRecipe(PACKED_ICE_BRICKS_STAIRS_STONECUTTING, map);
        putRecipe(PACKED_ICE_BRICKS_STAIRS_STONECUTTING_PACKED_ICE, map);
        putRecipe(BLUE_ICE_STAIRS_STONECUTTING, map);
        putRecipe(BLUE_ICE_BRICKS_STAIRS_STONECUTTING, map);
        putRecipe(BLUE_ICE_BRICKS_STAIRS_STONECUTTING_BLUE_ICE, map);

        putRecipe(COMPACTED_SNOW_BLOCK_SLAB_STONECUTTING, map);
        putRecipe(COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING, map);
        putRecipe(COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING_COMPACTED_SNOW_BLOCK, map);
        putRecipe(PACKED_ICE_SLAB_STONECUTTING, map);
        putRecipe(PACKED_ICE_BRICKS_SLAB_STONECUTTING, map);
        putRecipe(PACKED_ICE_BRICKS_SLAB_STONECUTTING_PACKED_ICE, map);
        putRecipe(BLUE_ICE_SLAB_STONECUTTING, map);
        putRecipe(BLUE_ICE_BRICKS_SLAB_STONECUTTING, map);
        putRecipe(BLUE_ICE_BRICKS_SLAB_STONECUTTING_BLUE_ICE, map);

        putRecipe(BEE_STATUE_FROM_STONECUTTING, map);
        putRecipe(SILVERFISH_STATUE_FROM_STONECUTTING, map);
        putRecipe(ENDERMITE_STATUE_FROM_STONECUTTING, map);
        putRecipe(WOLF_STATUE_FROM_STONECUTTING, map);
        putRecipe(CAT_STATUE_FROM_STONECUTTING, map);
        putRecipe(FOX_STATUE_FROM_STONECUTTING, map);

        putRecipe(AXOLOTL_STATUE_FROM_STONECUTTING, map);
        putRecipe(BAT_STATUE_FROM_STONECUTTING, map);

        putRecipe(ROOTED_DIRT_STAIRS_FROM_STONECUTTING, map);
        putRecipe(ROOTED_DIRT_SLAB_FROM_STONECUTTING, map);

        putRecipe(SAND_STAIRS_FROM_STONECUTTING, map);
        putRecipe(SAND_SLAB_FROM_STONECUTTING, map);
        putRecipe(RED_SAND_STAIRS_FROM_STONECUTTING, map);
        putRecipe(RED_SAND_SLAB_FROM_STONECUTTING, map);
        putRecipe(GRAVEL_STAIRS_FROM_STONECUTTING, map);
        putRecipe(GRAVEL_SLAB_FROM_STONECUTTING, map);
    }
}
