package net.azagwen.atbyw.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.Map;

import static net.azagwen.atbyw.datagen.AtbywRecipeUtils.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.nameSpace;
import static net.azagwen.atbyw.main.AtbywMain.AtbywID;

public class AtbywRecipesStonecutting {

    public static JsonObject createStonecutterRecipe(Item ingredient, Item output, int count) {
        JsonObject json = new JsonObject();

        json.addProperty("type", "minecraft:stonecutting");

        //Ingredient
        JsonObject ingredientObj = new JsonObject();
        ingredientObj.addProperty("item", getItemID(ingredient).toString());
        json.add("ingredient", ingredientObj);

        //Result
        json.addProperty("result", getItemID(output).toString());
        json.addProperty("count", count);

        return json;
    }

    private static JsonObject[] createStonecutterColoredRecipes(Pair<String, String> ingredient, Pair<String, String> result, int count) {
        JsonObject[] obj = new JsonObject[COLOR_NAMES.length];

        for (int i = 0; i < COLOR_NAMES.length; i++) {
            obj[i] = createStonecutterRecipe(
                    getMultiItemFromID(COLOR_NAMES, i, ingredient.getLeft(), ingredient.getRight()),
                    getMultiItemFromID(COLOR_NAMES, i, result.getLeft(), result.getRight()),
                    count);
        }

        return obj;
    }

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

    public static void init() {
        TERRACOTTA_STAIRS_STONECUTTING = createStonecutterRecipe(Blocks.TERRACOTTA.asItem(), AtbywBlocks.TERRACOTTA_STAIRS.asItem(), 1);
        TERRACOTTA_SLAB_STONECUTTING = createStonecutterRecipe(Blocks.TERRACOTTA.asItem(), AtbywBlocks.TERRACOTTA_SLAB.asItem(), 2);
        TERRACOTTA_BRICKS_STONECUTTING = createStonecutterRecipe(Blocks.TERRACOTTA.asItem(), AtbywBlocks.TERRACOTTA_BRICKS.asItem(), 1);
        TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA = createStonecutterRecipe(Blocks.TERRACOTTA.asItem(), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS.asItem(),1);
        TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA = createStonecutterRecipe(Blocks.TERRACOTTA.asItem(), AtbywBlocks.TERRACOTTA_BRICKS_SLAB.asItem(),2);
        TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA = createStonecutterRecipe(Blocks.TERRACOTTA.asItem(), AtbywBlocks.TERRACOTTA_BRICKS_WALL.asItem(),1);
        TERRACOTTA_BRICKS_STAIRS_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterRecipe(AtbywBlocks.TERRACOTTA_BRICKS.asItem(), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS.asItem(), 1);
        TERRACOTTA_BRICKS_SLAB_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterRecipe(AtbywBlocks.TERRACOTTA_BRICKS.asItem(), AtbywBlocks.TERRACOTTA_BRICKS_SLAB.asItem(), 2);
        TERRACOTTA_BRICKS_WALL_STONECUTTING_TERRACOTTA_BRICKS = createStonecutterRecipe(AtbywBlocks.TERRACOTTA_BRICKS.asItem(), AtbywBlocks.TERRACOTTA_BRICKS_WALL.asItem(), 1);

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
        CINDER_BLOCKS_COLORS_STONECUTTING = createStonecutterColoredRecipes(new Pair<>("minecraft", "concrete"), new Pair<>(nameSpace, "cinder_blocks"), 1);
        CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CONCRETE = createStonecutterColoredRecipes(new Pair<>("minecraft", "concrete"), new Pair<>(nameSpace, "cinder_blocks_wall"), 1);
        CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CINDER_BLOCKS = createStonecutterColoredRecipes(new Pair<>(nameSpace, "cinder_blocks"), new Pair<>(nameSpace, "cinder_blocks_wall"), 1);

        GRANITE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.GRANITE.asItem(), AtbywBlocks.GRANITE_COLUMN.asItem(), 1);
        DIORITE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.DIORITE.asItem(), AtbywBlocks.DIORITE_COLUMN.asItem(), 1);
        ANDESITE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.ANDESITE.asItem(), AtbywBlocks.ANDESITE_COLUMN.asItem(), 1);
        GRANITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(Blocks.POLISHED_GRANITE.asItem(), AtbywBlocks.GRANITE_COLUMN.asItem(), 1);
        DIORITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(Blocks.POLISHED_DIORITE.asItem(), AtbywBlocks.DIORITE_COLUMN.asItem(), 1);
        ANDESITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(Blocks.POLISHED_ANDESITE.asItem(), AtbywBlocks.ANDESITE_COLUMN.asItem(), 1);
        SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.SANDSTONE.asItem(), AtbywBlocks.SANDSTONE_COLUMN.asItem(), 1);
        SANDSTONE_COLUMN_FROM_CUT_STONECUTTING = createStonecutterRecipe(Blocks.CUT_SANDSTONE.asItem(), AtbywBlocks.SANDSTONE_COLUMN.asItem(), 1);
        CHISELED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.CHISELED_SANDSTONE.asItem(), AtbywBlocks.CHISELED_SANDSTONE_COLUMN.asItem(), 1);
        RED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.RED_SANDSTONE.asItem(), AtbywBlocks.RED_SANDSTONE_COLUMN.asItem(), 1);
        RED_SANDSTONE_COLUMN_FROM_CUT_STONECUTTING = createStonecutterRecipe(Blocks.CUT_RED_SANDSTONE.asItem(), AtbywBlocks.RED_SANDSTONE_COLUMN.asItem(), 1);
        CHISELED_RED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.CHISELED_RED_SANDSTONE.asItem(), AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN.asItem(), 1);
        PURPUR_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.PURPUR_COLUMN.asItem(), 1);
        STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.STONE_BRICKS.asItem(), AtbywBlocks.STONE_BRICKS_COLUMN.asItem(), 1);
        MOSSY_STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.MOSSY_STONE_BRICKS.asItem(), AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN.asItem(), 1);
        CRACKED_STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.CRACKED_STONE_BRICKS.asItem(), AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN.asItem(), 1);
        NETHER_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.NETHER_BRICKS.asItem(), AtbywBlocks.NETHER_BRICKS_COLUMN.asItem(), 1);
        QUARTZ_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.QUARTZ_BLOCK.asItem(), AtbywBlocks.QUARTZ_COLUMN.asItem(), 1);
        PRISMARINE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.PRISMARINE_BRICKS.asItem(), AtbywBlocks.PRISMARINE_COLUMN.asItem(), 1);
        BLACKSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.BLACKSTONE.asItem(), AtbywBlocks.BLACKSTONE_COLUMN.asItem(), 1);

        PURPUR_TILES_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_BLOCK.asItem(), 1);
        SMOOTH_PURPUR_BLOCK_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.PURPUR_TILES.asItem(), 1);
        CHISELED_PURPUR_BLOCK_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.CHISELED_PURPUR_BLOCK.asItem(), 1);
        PURPUR_TILES_STAIRS_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.PURPUR_TILES_STAIRS.asItem(), 1);
        SMOOTH_PURPUR_STAIRS_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_STAIRS.asItem(), 1);
        PURPUR_TILES_SLAB_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.PURPUR_TILES_SLAB.asItem(), 2);
        SMOOTH_PURPUR_SLAB_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_SLAB.asItem(), 2);
        PURPUR_TILES_STAIRS_FROM_PURPUR_TILES_STONECUTTING = createStonecutterRecipe(AtbywBlocks.PURPUR_TILES.asItem(), AtbywBlocks.PURPUR_TILES_STAIRS.asItem(), 1);
        SMOOTH_PURPUR_STAIRS_FROM_SMOOTH_PURPUR_STONECUTTING = createStonecutterRecipe(AtbywBlocks.SMOOTH_PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_STAIRS.asItem(), 1);
        PURPUR_TILES_SLAB_FROM_PURPUR_TILES_STONECUTTING = createStonecutterRecipe(AtbywBlocks.PURPUR_TILES.asItem(), AtbywBlocks.PURPUR_TILES_SLAB.asItem(), 2);
        SMOOTH_PURPUR_SLAB_FROM_SMOOTH_PURPUR_STONECUTTING = createStonecutterRecipe(AtbywBlocks.SMOOTH_PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_SLAB.asItem(), 2);
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
    }
}
