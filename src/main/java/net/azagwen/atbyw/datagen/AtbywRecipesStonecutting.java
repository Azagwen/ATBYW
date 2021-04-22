package net.azagwen.atbyw.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.Map;

import static net.azagwen.atbyw.datagen.AtbywRecipeUtils.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywRecipesStonecutting {

    public static JsonObject createStonecutterRecipe(Identifier ingredient, Identifier output, int count) {
        JsonObject json = new JsonObject();

        json.addProperty("type", "minecraft:stonecutting");

        //Ingredient
        JsonObject ingredientObj = new JsonObject();
        ingredientObj.addProperty("item", ingredient.toString());
        json.add("ingredient", ingredientObj);

        //Result
        json.addProperty("result", output.toString());
        json.addProperty("count", count);

        return json;
    }

    private static JsonObject[] createStonecutterColoredRecipes(Pair<String, String> ingredient, Pair<String, String> result, int count) {
        JsonObject[] obj = new JsonObject[COLOR_NAMES.length];

        for (int i = 0; i < COLOR_NAMES.length; i++) {
            obj[i] = createStonecutterRecipe(
                    getItemPseudoID(COLOR_NAMES, i, ingredient.getLeft(), ingredient.getRight()),
                    getItemPseudoID(COLOR_NAMES, i, result.getLeft(), result.getRight()),
                    count
            );
        }

        return obj;
    }

    public static JsonObject DIRT_STAIRS_STONECUTTING;
    public static JsonObject DIRT_STAIRS_STONECUTTING_GRASS_BLOCK;
    public static JsonObject DIRT_STAIRS_STONECUTTING_MYCELIUM;
    public static JsonObject DIRT_STAIRS_STONECUTTING_COARSE_DIRT;
    public static JsonObject DIRT_STAIRS_STONECUTTING_PODZOL;
    public static JsonObject GRASS_BLOCK_STAIRS_STONECUTTING;
    public static JsonObject MYCELIUM_STAIRS_STONECUTTING;
    public static JsonObject COARSE_DIRT_STAIRS_STONECUTTING;
    public static JsonObject PODZOL_STAIRS_STONECUTTING;
    public static JsonObject NETHERRACK_STAIRS_STONECUTTING;
    public static JsonObject NETHERRACK_STAIRS_STONECUTTING_CRIMSON_NYLIUM;
    public static JsonObject NETHERRACK_STAIRS_STONECUTTING_WARPED_NYLIUM;
    public static JsonObject CRIMSON_NYLIUM_STAIRS_STONECUTTING;
    public static JsonObject WARPED_NYLIUM_STAIRS_STONECUTTING;

    public static JsonObject DIRT_SLAB_STONECUTTING;
    public static JsonObject DIRT_SLAB_STONECUTTING_GRASS_BLOCK;
    public static JsonObject DIRT_SLAB_STONECUTTING_MYCELIUM;
    public static JsonObject DIRT_SLAB_STONECUTTING_COARSE_DIRT;
    public static JsonObject DIRT_SLAB_STONECUTTING_PODZOL;
    public static JsonObject GRASS_BLOCK_SLAB_STONECUTTING;
    public static JsonObject MYCELIUM_SLAB_STONECUTTING;
    public static JsonObject COARSE_DIRT_SLAB_STONECUTTING;
    public static JsonObject PODZOL_SLAB_STONECUTTING;
    public static JsonObject NETHERRACK_SLAB_STONECUTTING;
    public static JsonObject NETHERRACK_SLAB_STONECUTTING_CRIMSON_NYLIUM;
    public static JsonObject NETHERRACK_SLAB_STONECUTTING_WARPED_NYLIUM;
    public static JsonObject CRIMSON_NYLIUM_SLAB_STONECUTTING;
    public static JsonObject WARPED_NYLIUM_SLAB_STONECUTTING;

    public static JsonObject BASALT_BRICKS_STONECUTTING_BASALT;
    public static JsonObject BASALT_PILLAR_STONECUTTING_BASALT;
    public static JsonObject BASALT_BRICKS_STONECUTTING_POLISHED_BASALT;
    public static JsonObject BASALT_PILLAR_STONECUTTING_POLISHED_BASALT;

    public static JsonObject GRANITE_TILES_STONECUTTING;
    public static JsonObject DIORITE_BRICKS_STONECUTTING;
    public static JsonObject ANDESITE_BRICKS_STONECUTTING;
    public static JsonObject GRANITE_TILES_STONECUTTING_POLISHED;
    public static JsonObject DIORITE_BRICKS_STONECUTTING_POLISHED;
    public static JsonObject ANDESITE_BRICKS_STONECUTTING_POLISHED;

    public static JsonObject TERRACOTTA_STAIRS_STONECUTTING;
    public static JsonObject TERRACOTTA_SLAB_STONECUTTING;
    public static JsonObject TERRACOTTA_BRICKS_STONECUTTING;
    public static JsonObject TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA;
    public static JsonObject TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA;
    public static JsonObject TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA;
    public static JsonObject TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA_BRICKS;
    public static JsonObject TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA_BRICKS;
    public static JsonObject TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA_BRICKS;

    public static JsonObject[] TERRACOTTA_STAIRS_COLORS_STONECUTTING;
    public static JsonObject[] TERRACOTTA_SLAB_COLORS_STONECUTTING;
    public static JsonObject[] TERRACOTTA_BRICKS_COLORS_STONECUTTING;
    public static JsonObject[] TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA;
    public static JsonObject[] TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA;
    public static JsonObject[] TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA;
    public static JsonObject[] TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA_BRICKS;
    public static JsonObject[] TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA_BRICKS;
    public static JsonObject[] TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA_BRICKS;

    public static JsonObject[] CONCRETE_STAIRS_COLORS_STONECUTTING;
    public static JsonObject[] CONCRETE_SLAB_COLORS_STONECUTTING;
    public static JsonObject[] CINDER_BLOCKS_COLORS_STONECUTTING;
    public static JsonObject[] CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CONCRETE;
    public static JsonObject[] CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CINDER_BLOCKS;

    public static JsonObject GRANITE_COLUMN_STONECUTTING;
    public static JsonObject DIORITE_COLUMN_STONECUTTING;
    public static JsonObject ANDESITE_COLUMN_STONECUTTING;
    public static JsonObject GRANITE_COLUMN_FROM_POLISHED_STONECUTTING;
    public static JsonObject DIORITE_COLUMN_FROM_POLISHED_STONECUTTING;
    public static JsonObject ANDESITE_COLUMN_FROM_POLISHED_STONECUTTING;
    public static JsonObject SANDSTONE_COLUMN_STONECUTTING;
    public static JsonObject SANDSTONE_COLUMN_FROM_CUT_STONECUTTING;
    public static JsonObject CHISELED_SANDSTONE_COLUMN_STONECUTTING;
    public static JsonObject RED_SANDSTONE_COLUMN_STONECUTTING;
    public static JsonObject RED_SANDSTONE_COLUMN_FROM_CUT_STONECUTTING;
    public static JsonObject CHISELED_RED_SANDSTONE_COLUMN_STONECUTTING;
    public static JsonObject PURPUR_COLUMN_STONECUTTING;
    public static JsonObject STONE_BRICKS_COLUMN_STONECUTTING;
    public static JsonObject MOSSY_STONE_BRICKS_COLUMN_STONECUTTING;
    public static JsonObject CRACKED_STONE_BRICKS_COLUMN_STONECUTTING;
    public static JsonObject NETHER_BRICKS_COLUMN_STONECUTTING;
    public static JsonObject QUARTZ_COLUMN_STONECUTTING;
    public static JsonObject PRISMARINE_COLUMN_STONECUTTING;
    public static JsonObject BLACKSTONE_COLUMN_STONECUTTING;

    public static JsonObject PURPUR_TILES_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_BLOCK_STONECUTTING;
    public static JsonObject CHISELED_PURPUR_BLOCK_STONECUTTING;
    public static JsonObject PURPUR_TILES_STAIRS_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_STAIRS_STONECUTTING;
    public static JsonObject PURPUR_TILES_SLAB_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_SLAB_STONECUTTING;
    public static JsonObject PURPUR_TILES_STAIRS_FROM_PURPUR_TILES_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_STAIRS_FROM_SMOOTH_PURPUR_STONECUTTING;
    public static JsonObject PURPUR_TILES_SLAB_FROM_PURPUR_TILES_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_SLAB_FROM_SMOOTH_PURPUR_STONECUTTING;

    public static JsonObject COMPACTED_SNOW_STONECUTTING;
    public static JsonObject COMPACTED_SNOW_BRICKS_STONECUTTING;
    public static JsonObject PACKED_ICE_BRICKS_STONECUTTING;
    public static JsonObject BLUE_ICE_BRICKS_STONECUTTING;

    public static JsonObject COMPACTED_SNOW_BLOCK_STAIRS_STONECUTTING;
    public static JsonObject COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING;
    public static JsonObject COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING_COMPACTED_SNOW_BLOCK;
    public static JsonObject BLUE_ICE_STAIRS_STONECUTTING;
    public static JsonObject BLUE_ICE_BRICKS_STAIRS_STONECUTTING;
    public static JsonObject BLUE_ICE_BRICKS_STAIRS_STONECUTTING_BLUE_ICE;
    public static JsonObject PACKED_ICE_STAIRS_STONECUTTING;
    public static JsonObject PACKED_ICE_BRICKS_STAIRS_STONECUTTING;
    public static JsonObject PACKED_ICE_BRICKS_STAIRS_STONECUTTING_PACKED_ICE;

    public static JsonObject COMPACTED_SNOW_BLOCK_SLAB_STONECUTTING;
    public static JsonObject COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING;
    public static JsonObject COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING_COMPACTED_SNOW_BLOCK;
    public static JsonObject PACKED_ICE_SLAB_STONECUTTING;
    public static JsonObject PACKED_ICE_BRICKS_SLAB_STONECUTTING;
    public static JsonObject PACKED_ICE_BRICKS_SLAB_STONECUTTING_PACKED_ICE;
    public static JsonObject BLUE_ICE_SLAB_STONECUTTING;
    public static JsonObject BLUE_ICE_BRICKS_SLAB_STONECUTTING;
    public static JsonObject BLUE_ICE_BRICKS_SLAB_STONECUTTING_BLUE_ICE;

    public static void init() {
        DIRT_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.DIRT), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
        DIRT_STAIRS_STONECUTTING_GRASS_BLOCK = createStonecutterRecipe(getBlockID(Blocks.GRASS_BLOCK), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
        DIRT_STAIRS_STONECUTTING_MYCELIUM = createStonecutterRecipe(getBlockID(Blocks.MYCELIUM), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
        DIRT_STAIRS_STONECUTTING_COARSE_DIRT = createStonecutterRecipe(getBlockID(Blocks.COARSE_DIRT), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
        DIRT_STAIRS_STONECUTTING_PODZOL = createStonecutterRecipe(getBlockID(Blocks.PODZOL), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
        GRASS_BLOCK_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.GRASS_BLOCK), getBlockID(AtbywBlocks.GRASS_BLOCK_STAIRS), 1);
        MYCELIUM_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.MYCELIUM), getBlockID(AtbywBlocks.MYCELIUM_STAIRS), 1);
        COARSE_DIRT_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.COARSE_DIRT), getBlockID(AtbywBlocks.COARSE_DIRT_STAIRS), 1);
        PODZOL_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PODZOL), getBlockID(AtbywBlocks.PODZOL_STAIRS), 1);
        NETHERRACK_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.NETHERRACK), getBlockID(AtbywBlocks.NETHERRACK_STAIRS), 1);
        NETHERRACK_STAIRS_STONECUTTING_CRIMSON_NYLIUM = createStonecutterRecipe(getBlockID(Blocks.CRIMSON_NYLIUM), getBlockID(AtbywBlocks.NETHERRACK_STAIRS), 1);
        NETHERRACK_STAIRS_STONECUTTING_WARPED_NYLIUM = createStonecutterRecipe(getBlockID(Blocks.WARPED_NYLIUM), getBlockID(AtbywBlocks.NETHERRACK_STAIRS), 1);
        CRIMSON_NYLIUM_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.CRIMSON_NYLIUM), getBlockID(AtbywBlocks.CRIMSON_NYLIUM_STAIRS), 1);
        WARPED_NYLIUM_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.WARPED_NYLIUM), getBlockID(AtbywBlocks.WARPED_NYLIUM_STAIRS), 1);

        DIRT_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.DIRT), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
        DIRT_SLAB_STONECUTTING_GRASS_BLOCK = createStonecutterRecipe(getBlockID(Blocks.GRASS_BLOCK), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
        DIRT_SLAB_STONECUTTING_MYCELIUM = createStonecutterRecipe(getBlockID(Blocks.MYCELIUM), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
        DIRT_SLAB_STONECUTTING_COARSE_DIRT = createStonecutterRecipe(getBlockID(Blocks.COARSE_DIRT), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
        DIRT_SLAB_STONECUTTING_PODZOL = createStonecutterRecipe(getBlockID(Blocks.PODZOL), getBlockID(AtbywBlocks.DIRT_SLAB), 2);
        GRASS_BLOCK_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.GRASS_BLOCK), getBlockID(AtbywBlocks.GRASS_BLOCK_SLAB), 2);
        MYCELIUM_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.MYCELIUM), getBlockID(AtbywBlocks.MYCELIUM_SLAB), 2);
        COARSE_DIRT_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.COARSE_DIRT), getBlockID(AtbywBlocks.COARSE_DIRT_SLAB), 2);
        PODZOL_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PODZOL), getBlockID(AtbywBlocks.PODZOL_SLAB), 2);
        NETHERRACK_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.NETHERRACK), getBlockID(AtbywBlocks.NETHERRACK_SLAB), 2);
        NETHERRACK_SLAB_STONECUTTING_CRIMSON_NYLIUM = createStonecutterRecipe(getBlockID(Blocks.CRIMSON_NYLIUM), getBlockID(AtbywBlocks.NETHERRACK_SLAB), 2);
        NETHERRACK_SLAB_STONECUTTING_WARPED_NYLIUM = createStonecutterRecipe(getBlockID(Blocks.WARPED_NYLIUM), getBlockID(AtbywBlocks.NETHERRACK_SLAB), 2);
        CRIMSON_NYLIUM_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.CRIMSON_NYLIUM), getBlockID(AtbywBlocks.CRIMSON_NYLIUM_SLAB), 2);
        WARPED_NYLIUM_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.WARPED_NYLIUM), getBlockID(AtbywBlocks.WARPED_NYLIUM_SLAB), 2);

        BASALT_BRICKS_STONECUTTING_BASALT = createStonecutterRecipe(getBlockID(Blocks.BASALT), getBlockID(AtbywBlocks.BASALT_BRICKS), 1);
        BASALT_PILLAR_STONECUTTING_BASALT = createStonecutterRecipe(getBlockID(Blocks.BASALT), getBlockID(AtbywBlocks.BASALT_PILLAR), 1);
        BASALT_BRICKS_STONECUTTING_POLISHED_BASALT = createStonecutterRecipe(getBlockID(Blocks.POLISHED_BASALT), getBlockID(AtbywBlocks.BASALT_BRICKS), 1);
        BASALT_PILLAR_STONECUTTING_POLISHED_BASALT = createStonecutterRecipe(getBlockID(Blocks.POLISHED_BASALT), getBlockID(AtbywBlocks.BASALT_PILLAR), 1);

        GRANITE_TILES_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.GRANITE), getBlockID(AtbywBlocks.GRANITE_TILES), 1);
        DIORITE_BRICKS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.DIORITE), getBlockID(AtbywBlocks.DIORITE_BRICKS), 1);
        ANDESITE_BRICKS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.ANDESITE), getBlockID(AtbywBlocks.ANDESITE_BRICKS), 1);
        GRANITE_TILES_STONECUTTING_POLISHED = createStonecutterRecipe(getBlockID(Blocks.POLISHED_GRANITE), getBlockID(AtbywBlocks.GRANITE_TILES), 1);
        DIORITE_BRICKS_STONECUTTING_POLISHED = createStonecutterRecipe(getBlockID(Blocks.POLISHED_DIORITE), getBlockID(AtbywBlocks.DIORITE_BRICKS), 1);
        ANDESITE_BRICKS_STONECUTTING_POLISHED = createStonecutterRecipe(getBlockID(Blocks.POLISHED_ANDESITE), getBlockID(AtbywBlocks.ANDESITE_BRICKS), 1);

        TERRACOTTA_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_STAIRS), 1);
        TERRACOTTA_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_SLAB), 2);
        TERRACOTTA_BRICKS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), 1);
        TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA = createStonecutterRecipe(getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_STAIRS),1);
        TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA = createStonecutterRecipe(getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_SLAB),2);
        TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA = createStonecutterRecipe(getBlockID(Blocks.TERRACOTTA), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_WALL),1);
        TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterRecipe(getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_STAIRS), 1);
        TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterRecipe(getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_SLAB), 2);
        TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterRecipe(getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_WALL), 1);

        TERRACOTTA_STAIRS_COLORS_STONECUTTING = createStonecutterColoredRecipes(new Pair<>("minecraft", "terracotta"), new Pair<>(nameSpace, "terracotta_stairs"), 1);
        TERRACOTTA_SLAB_COLORS_STONECUTTING = createStonecutterColoredRecipes(new Pair<>("minecraft", "terracotta"), new Pair<>(nameSpace, "terracotta_slab"), 2);
        TERRACOTTA_BRICKS_COLORS_STONECUTTING = createStonecutterColoredRecipes(new Pair<>("minecraft", "terracotta"), new Pair<>(nameSpace, "terracotta_bricks"), 1);
        TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA = createStonecutterColoredRecipes(new Pair<>("minecraft", "terracotta"), new Pair<>(nameSpace, "terracotta_bricks_stairs"), 1);
        TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA = createStonecutterColoredRecipes(new Pair<>("minecraft", "terracotta"), new Pair<>(nameSpace, "terracotta_bricks_slab"), 2);
        TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA = createStonecutterColoredRecipes(new Pair<>("minecraft", "terracotta"), new Pair<>(nameSpace, "terracotta_bricks_wall"), 1);
        TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterColoredRecipes(new Pair<>(nameSpace, "terracotta_bricks"), new Pair<>(nameSpace, "terracotta_bricks_stairs"), 1);
        TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterColoredRecipes(new Pair<>(nameSpace, "terracotta_bricks"), new Pair<>(nameSpace, "terracotta_bricks_slab"), 2);
        TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterColoredRecipes(new Pair<>(nameSpace, "terracotta_bricks"), new Pair<>(nameSpace, "terracotta_bricks_wall"), 1);

        CONCRETE_STAIRS_COLORS_STONECUTTING = createStonecutterColoredRecipes(new Pair<>("minecraft", "concrete"), new Pair<>(nameSpace, "concrete_stairs"), 1);
        CONCRETE_SLAB_COLORS_STONECUTTING = createStonecutterColoredRecipes(new Pair<>("minecraft", "concrete"), new Pair<>(nameSpace, "concrete_slab"), 2);
        CINDER_BLOCKS_COLORS_STONECUTTING = createStonecutterColoredRecipes(new Pair<>("minecraft", "concrete"), new Pair<>(nameSpace, "cinder_bricks"), 1);
        CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CONCRETE = createStonecutterColoredRecipes(new Pair<>("minecraft", "concrete"), new Pair<>(nameSpace, "cinder_blocks_wall"), 1);
        CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CINDER_BLOCKS = createStonecutterColoredRecipes(new Pair<>(nameSpace, "cinder_bricks"), new Pair<>(nameSpace, "cinder_blocks_wall"), 1);

        GRANITE_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.GRANITE), getBlockID(AtbywBlocks.GRANITE_COLUMN), 1);
        DIORITE_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.DIORITE), getBlockID(AtbywBlocks.DIORITE_COLUMN), 1);
        ANDESITE_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.ANDESITE), getBlockID(AtbywBlocks.ANDESITE_COLUMN), 1);
        GRANITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.POLISHED_GRANITE), getBlockID(AtbywBlocks.GRANITE_COLUMN), 1);
        DIORITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.POLISHED_DIORITE), getBlockID(AtbywBlocks.DIORITE_COLUMN), 1);
        ANDESITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.POLISHED_ANDESITE), getBlockID(AtbywBlocks.ANDESITE_COLUMN), 1);
        SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.SANDSTONE), getBlockID(AtbywBlocks.SANDSTONE_COLUMN), 1);
        SANDSTONE_COLUMN_FROM_CUT_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.CUT_SANDSTONE), getBlockID(AtbywBlocks.SANDSTONE_COLUMN), 1);
        CHISELED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.CHISELED_SANDSTONE), getBlockID(AtbywBlocks.CHISELED_SANDSTONE_COLUMN), 1);
        RED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.RED_SANDSTONE), getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), 1);
        RED_SANDSTONE_COLUMN_FROM_CUT_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.CUT_RED_SANDSTONE), getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), 1);
        CHISELED_RED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.CHISELED_RED_SANDSTONE), getBlockID(AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN), 1);
        PURPUR_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.PURPUR_COLUMN), 1);
        STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.STONE_BRICKS), getBlockID(AtbywBlocks.STONE_BRICKS_COLUMN), 1);
        MOSSY_STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.MOSSY_STONE_BRICKS), getBlockID(AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN), 1);
        CRACKED_STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.CRACKED_STONE_BRICKS), getBlockID(AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN), 1);
        NETHER_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.NETHER_BRICKS), getBlockID(AtbywBlocks.NETHER_BRICKS_COLUMN), 1);
        QUARTZ_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.QUARTZ_BLOCK), getBlockID(AtbywBlocks.QUARTZ_COLUMN), 1);
        PRISMARINE_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PRISMARINE_BRICKS), getBlockID(AtbywBlocks.PRISMARINE_COLUMN), 1);
        BLACKSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.BLACKSTONE), getBlockID(AtbywBlocks.BLACKSTONE_COLUMN), 1);

        PURPUR_TILES_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK), 1);
        SMOOTH_PURPUR_BLOCK_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.PURPUR_TILES), 1);
        CHISELED_PURPUR_BLOCK_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.CHISELED_PURPUR_BLOCK), 1);
        PURPUR_TILES_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.PURPUR_TILES_STAIRS), 1);
        SMOOTH_PURPUR_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_STAIRS), 1);
        PURPUR_TILES_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.PURPUR_TILES_SLAB), 2);
        SMOOTH_PURPUR_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_SLAB), 2);
        PURPUR_TILES_STAIRS_FROM_PURPUR_TILES_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.PURPUR_TILES), getBlockID(AtbywBlocks.PURPUR_TILES_STAIRS), 1);
        SMOOTH_PURPUR_STAIRS_FROM_SMOOTH_PURPUR_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_STAIRS), 1);
        PURPUR_TILES_SLAB_FROM_PURPUR_TILES_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.PURPUR_TILES), getBlockID(AtbywBlocks.PURPUR_TILES_SLAB), 2);
        SMOOTH_PURPUR_SLAB_FROM_SMOOTH_PURPUR_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK), getBlockID(AtbywBlocks.CUT_PURPUR_SLAB), 2);

        COMPACTED_SNOW_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW), 8);
        COMPACTED_SNOW_BRICKS_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS), 1);
        PACKED_ICE_BRICKS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), 1);
        BLUE_ICE_BRICKS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), 1);

        COMPACTED_SNOW_BLOCK_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS), 1);
        COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS), 1);
        COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING_COMPACTED_SNOW_BLOCK = createStonecutterRecipe(getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS), 1);
        PACKED_ICE_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_STAIRS), 1);
        PACKED_ICE_BRICKS_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS), 1);
        PACKED_ICE_BRICKS_STAIRS_STONECUTTING_PACKED_ICE = createStonecutterRecipe(getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS), 1);
        BLUE_ICE_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_STAIRS), 1);
        BLUE_ICE_BRICKS_STAIRS_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_STAIRS), 1);
        BLUE_ICE_BRICKS_STAIRS_STONECUTTING_BLUE_ICE = createStonecutterRecipe(getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_STAIRS), 1);

        COMPACTED_SNOW_BLOCK_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB), 2);
        COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB), 2);
        COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING_COMPACTED_SNOW_BLOCK = createStonecutterRecipe(getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB), 2);
        PACKED_ICE_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_SLAB), 2);
        PACKED_ICE_BRICKS_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB), 2);
        PACKED_ICE_BRICKS_SLAB_STONECUTTING_PACKED_ICE = createStonecutterRecipe(getBlockID(Blocks.PACKED_ICE), getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB), 2);
        BLUE_ICE_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_SLAB), 2);
        BLUE_ICE_BRICKS_SLAB_STONECUTTING = createStonecutterRecipe(getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB), 2);
        BLUE_ICE_BRICKS_SLAB_STONECUTTING_BLUE_ICE = createStonecutterRecipe(getBlockID(Blocks.BLUE_ICE), getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB), 2);
    }

    public static void injectRecipes(Map<Identifier, JsonElement> map) {
        for (int i = 0; i < COLOR_NAMES.length; i++) {
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_stairs_from_stonecutting"), TERRACOTTA_STAIRS_COLORS_STONECUTTING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_slabs_from_stonecutting"), TERRACOTTA_SLAB_COLORS_STONECUTTING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_from_stonecutting"), TERRACOTTA_BRICKS_COLORS_STONECUTTING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_stairs_from_stonecutting_terracotta"), TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_slabs_from_stonecutting_terracotta"), TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_walls_from_stonecutting_terracotta"), TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_stairs_from_stonecutting_terracotta_bricks"), TERRACOTTA_BRICKS_STAIRS_COLORS_STONECUTTING_TERRACOTTA_BRICKS[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_slabs_from_stonecutting_terracotta_bricks"), TERRACOTTA_BRICKS_SLAB_COLORS_STONECUTTING_TERRACOTTA_BRICKS[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_walls_from_stonecutting_terracotta_bricks"), TERRACOTTA_BRICKS_WALL_COLORS_STONECUTTING_TERRACOTTA_BRICKS[i], map);

            putRecipe(AtbywID(COLOR_NAMES[i] + "_concrete_stairs_from_stonecutting"), CONCRETE_STAIRS_COLORS_STONECUTTING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_concrete_slab_from_stonecutting"), CONCRETE_SLAB_COLORS_STONECUTTING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_cinder_blocks_from_stonecutting"), CINDER_BLOCKS_COLORS_STONECUTTING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_cinder_blocks_wall_from_stonecutting_concrete"), CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CONCRETE[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_cinder_blocks_wall_from_stonecutting_cinder_blocks"), CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CINDER_BLOCKS[i], map);
        }

        putRecipe(AtbywID("dirt_stairs_stonecutting"), DIRT_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("dirt_stairs_stonecutting_grass_block"), DIRT_STAIRS_STONECUTTING_GRASS_BLOCK, map);
        putRecipe(AtbywID("dirt_stairs_stonecutting_mycelium"), DIRT_STAIRS_STONECUTTING_MYCELIUM, map);
        putRecipe(AtbywID("dirt_stairs_stonecutting_coarse_dirt"), DIRT_STAIRS_STONECUTTING_COARSE_DIRT, map);
        putRecipe(AtbywID("dirt_stairs_stonecutting_podzol"), DIRT_STAIRS_STONECUTTING_PODZOL, map);
        putRecipe(AtbywID("grass_block_stairs_stonecutting"), GRASS_BLOCK_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("mycelium_stairs_stonecutting"), MYCELIUM_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("coarse_dirt_stairs_stonecutting"), COARSE_DIRT_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("podzol_stairs_stonecutting"), PODZOL_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("netherrack_stairs_stonecutting"), NETHERRACK_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("netherrack_stairs_stonecutting_crimson_nylium"), NETHERRACK_STAIRS_STONECUTTING_CRIMSON_NYLIUM, map);
        putRecipe(AtbywID("netherrack_stairs_stonecutting_warped_nylium"), NETHERRACK_STAIRS_STONECUTTING_WARPED_NYLIUM, map);
        putRecipe(AtbywID("crimson_nylium_stairs_stonecutting"), CRIMSON_NYLIUM_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("warped_nylium_stairs_stonecutting"), WARPED_NYLIUM_STAIRS_STONECUTTING, map);

         putRecipe(AtbywID("dirt_slab_stonecutting"), DIRT_SLAB_STONECUTTING, map);
         putRecipe(AtbywID("dirt_slab_stonecutting_grass_block"), DIRT_SLAB_STONECUTTING_GRASS_BLOCK, map);
         putRecipe(AtbywID("dirt_slab_stonecutting_mycelium"), DIRT_SLAB_STONECUTTING_MYCELIUM, map);
         putRecipe(AtbywID("dirt_slab_stonecutting_coarse_dirt"), DIRT_SLAB_STONECUTTING_COARSE_DIRT, map);
         putRecipe(AtbywID("dirt_slab_stonecutting_podzol"), DIRT_SLAB_STONECUTTING_PODZOL, map);
         putRecipe(AtbywID("grass_block_slab_stonecutting"), GRASS_BLOCK_SLAB_STONECUTTING, map);
         putRecipe(AtbywID("mycelium_slab_stonecutting"), MYCELIUM_SLAB_STONECUTTING, map);
         putRecipe(AtbywID("coarse_dirt_slab_stonecutting"), COARSE_DIRT_SLAB_STONECUTTING, map);
         putRecipe(AtbywID("podzol_slab_stonecutting"), PODZOL_SLAB_STONECUTTING, map);
         putRecipe(AtbywID("netherrack_slab_stonecutting"), NETHERRACK_SLAB_STONECUTTING, map);
         putRecipe(AtbywID("netherrack_slab_stonecutting_crimson_nylium"), NETHERRACK_SLAB_STONECUTTING_CRIMSON_NYLIUM, map);
         putRecipe(AtbywID("netherrack_slab_stonecutting_warped_nylium"), NETHERRACK_SLAB_STONECUTTING_WARPED_NYLIUM, map);
         putRecipe(AtbywID("crimson_nylium_slab_stonecutting"), CRIMSON_NYLIUM_SLAB_STONECUTTING, map);
         putRecipe(AtbywID("warped_nylium_slab_stonecutting"), WARPED_NYLIUM_SLAB_STONECUTTING, map);

        putRecipe(AtbywID("basalt_bricks_stonecutting_basalt"), BASALT_BRICKS_STONECUTTING_BASALT, map);
        putRecipe(AtbywID("basalt_pillar_stonecutting_basalt"), BASALT_PILLAR_STONECUTTING_BASALT, map);
        putRecipe(AtbywID("basalt_bricks_stonecutting_polished_basalt"), BASALT_BRICKS_STONECUTTING_POLISHED_BASALT, map);
        putRecipe(AtbywID("basalt_pillar_stonecutting_polished_basalt"), BASALT_PILLAR_STONECUTTING_POLISHED_BASALT, map);

        putRecipe(AtbywID("granite_tiles_stonecutting"), GRANITE_TILES_STONECUTTING, map);
        putRecipe(AtbywID("diorite_bricks_stonecutting"), DIORITE_BRICKS_STONECUTTING, map);
        putRecipe(AtbywID("andesite_bricks_stonecutting"), ANDESITE_BRICKS_STONECUTTING, map);
        putRecipe(AtbywID("granite_tiles_stonecutting_from_polished"), GRANITE_TILES_STONECUTTING_POLISHED, map);
        putRecipe(AtbywID("diorite_bricks_stonecutting_from_polished"), DIORITE_BRICKS_STONECUTTING_POLISHED, map);
        putRecipe(AtbywID("andesite_bricks_stonecutting_from_polished"), ANDESITE_BRICKS_STONECUTTING_POLISHED, map);

        putRecipe(AtbywID("terracotta_stairs_from_stonecutting"), TERRACOTTA_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("terracotta_slabs_from_stonecutting"), TERRACOTTA_SLAB_STONECUTTING, map);
        putRecipe(AtbywID("terracotta_bricks_from_stonecutting"), TERRACOTTA_BRICKS_STONECUTTING, map);
        putRecipe(AtbywID("terracotta_bricks_stairs_from_stonecutting_terracotta"), TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA, map);
        putRecipe(AtbywID("terracotta_bricks_slabs_from_stonecutting_terracotta"), TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA, map);
        putRecipe(AtbywID("terracotta_bricks_walls_from_stonecutting_terracotta"), TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA, map);
        putRecipe(AtbywID("terracotta_bricks_stairs_from_stonecutting_terracotta_bricks"), TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA_BRICKS, map);
        putRecipe(AtbywID("terracotta_bricks_slabs_from_stonecutting_terracotta_bricks"), TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA_BRICKS, map);
        putRecipe(AtbywID("terracotta_bricks_walls_from_stonecutting_terracotta_bricks"), TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA_BRICKS, map);

        putRecipe(AtbywID("granite_column_stonecutting"), GRANITE_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("diorite_column_stonecutting"), DIORITE_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("andesite_column_stonecutting"), ANDESITE_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("granite_column_from_polished_stonecutting"), GRANITE_COLUMN_FROM_POLISHED_STONECUTTING, map);
        putRecipe(AtbywID("diorite_column_from_polished_stonecutting"), DIORITE_COLUMN_FROM_POLISHED_STONECUTTING, map);
        putRecipe(AtbywID("andesite_column_from_polished_stonecutting"), ANDESITE_COLUMN_FROM_POLISHED_STONECUTTING, map);
        putRecipe(AtbywID("sandstone_column_stonecutting"), SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("sandstone_column_from_cut_stonecutting"), SANDSTONE_COLUMN_FROM_CUT_STONECUTTING, map);
        putRecipe(AtbywID("chiseled_sandstone_column_stonecutting"), CHISELED_SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("red_sandstone_column_stonecutting"), RED_SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("red_sandstone_column_from_cut_stonecutting"), RED_SANDSTONE_COLUMN_FROM_CUT_STONECUTTING, map);
        putRecipe(AtbywID("chiseled_red_sandstone_column_stonecutting"), CHISELED_RED_SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("purpur_column_stonecutting"), PURPUR_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("stone_bricks_column_stonecutting"), STONE_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("mossy_stone_bricks_column_stonecutting"), MOSSY_STONE_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("cracked_stone_bricks_column_stonecutting"), CRACKED_STONE_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("nether_bricks_column_stonecutting"), NETHER_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("quartz_column_stonecutting"), QUARTZ_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("prismarine_column_stonecutting"), PRISMARINE_COLUMN_STONECUTTING, map);
        putRecipe(AtbywID("blackstone_column_stonecutting"), BLACKSTONE_COLUMN_STONECUTTING, map);

        putRecipe(AtbywID("purpur_tiles_stonecutting"), PURPUR_TILES_STONECUTTING, map);
        putRecipe(AtbywID("smooth_purpur_block_stonecutting"), SMOOTH_PURPUR_BLOCK_STONECUTTING, map);
        putRecipe(AtbywID("chiseled_purpur_block_stonecutting"), CHISELED_PURPUR_BLOCK_STONECUTTING, map);
        putRecipe(AtbywID("purpur_tiles_stairs_stonecutting"), PURPUR_TILES_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("smooth_purpur_stairs_stonecutting"), SMOOTH_PURPUR_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("purpur_tiles_slab_stonecutting"), PURPUR_TILES_SLAB_STONECUTTING, map);
        putRecipe(AtbywID("smooth_purpur_slab_stonecutting"), SMOOTH_PURPUR_SLAB_STONECUTTING, map);
        putRecipe(AtbywID("purpur_tiles_stairs_from_purpur_tiles_stonecutting"), PURPUR_TILES_STAIRS_FROM_PURPUR_TILES_STONECUTTING, map);
        putRecipe(AtbywID("smooth_purpur_stairs_from_smooth_purpur_stonecutting"), SMOOTH_PURPUR_STAIRS_FROM_SMOOTH_PURPUR_STONECUTTING, map);
        putRecipe(AtbywID("purpur_tiles_slab_from_purpur_tiles_stonecutting"), PURPUR_TILES_SLAB_FROM_PURPUR_TILES_STONECUTTING, map);
        putRecipe(AtbywID("smooth_purpur_slab_from_smooth_purpur_stonecutting"), SMOOTH_PURPUR_SLAB_FROM_SMOOTH_PURPUR_STONECUTTING, map);

        putRecipe(AtbywID("compacted_snow_stonecutting"), COMPACTED_SNOW_STONECUTTING, map);
        putRecipe(AtbywID("compacted_snow_bricks_stonecutting"), COMPACTED_SNOW_BRICKS_STONECUTTING, map);
        putRecipe(AtbywID("packed_ice_bricks_stonecutting"), PACKED_ICE_BRICKS_STONECUTTING, map);
        putRecipe(AtbywID("blue_ice_bricks_stonecutting"), BLUE_ICE_BRICKS_STONECUTTING, map);

        putRecipe(AtbywID("compacted_snow_block_stairs_stonecutting"), COMPACTED_SNOW_BLOCK_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("compacted_snow_bricks_stairs_stonecutting"), COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("compacted_snow_bricks_stairs_stonecutting_compacted_snow_block"), COMPACTED_SNOW_BRICKS_STAIRS_STONECUTTING_COMPACTED_SNOW_BLOCK, map);
        putRecipe(AtbywID("packed_ice_stairs_stonecutting"), PACKED_ICE_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("packed_ice_bricks_stairs_stonecutting"), PACKED_ICE_BRICKS_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("packed_ice_bricks_stairs_stonecutting_packed_ice"), PACKED_ICE_BRICKS_STAIRS_STONECUTTING_PACKED_ICE, map);
        putRecipe(AtbywID("blue_ice_stairs_stonecutting"), BLUE_ICE_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("blue_ice_bricks_stairs_stonecutting"), BLUE_ICE_BRICKS_STAIRS_STONECUTTING, map);
        putRecipe(AtbywID("blue_ice_bricks_stairs_stonecutting_blue_ice"), BLUE_ICE_BRICKS_STAIRS_STONECUTTING_BLUE_ICE, map);

        putRecipe(AtbywID("compacted_snow_block_slab_stonecutting"), COMPACTED_SNOW_BLOCK_SLAB_STONECUTTING, map);
        putRecipe(AtbywID("compacted_snow_bricks_slab_stonecutting"), COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING, map);
        putRecipe(AtbywID("compacted_snow_bricks_slab_stonecutting_compacted_snow_block"), COMPACTED_SNOW_BRICKS_SLAB_STONECUTTING_COMPACTED_SNOW_BLOCK, map);
        putRecipe(AtbywID("packed_ice_slab_stonecutting"), PACKED_ICE_SLAB_STONECUTTING, map);
        putRecipe(AtbywID("packed_ice_bricks_slab_stonecutting"), PACKED_ICE_BRICKS_SLAB_STONECUTTING, map);
        putRecipe(AtbywID("packed_ice_bricks_slab_stonecutting_packed_ice"), PACKED_ICE_BRICKS_SLAB_STONECUTTING_PACKED_ICE, map);
        putRecipe(AtbywID("blue_ice_slab_stonecutting"), BLUE_ICE_SLAB_STONECUTTING, map);
        putRecipe(AtbywID("blue_ice_bricks_slab_stonecutting"), BLUE_ICE_BRICKS_SLAB_STONECUTTING, map);
        putRecipe(AtbywID("blue_ice_bricks_slab_stonecutting_blue_ice"), BLUE_ICE_BRICKS_SLAB_STONECUTTING_BLUE_ICE, map);
    }
}
