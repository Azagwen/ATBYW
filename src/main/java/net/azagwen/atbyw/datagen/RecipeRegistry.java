package net.azagwen.atbyw.datagen;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.gson.*;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.main.AtbywIdentifier;
import net.azagwen.atbyw.util.AtbywUtils;
import net.azagwen.atbyw.util.Pair;
import net.azagwen.atbyw.util.Triplet;
import net.azagwen.atbyw.util.naming.ColorNames;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.azagwen.atbyw.datagen.RecipePatterns.*;

import java.util.HashMap;
import java.util.Map;

public class RecipeRegistry {
    public static final Logger LOGGER = LogManager.getLogger("Atbyw Recipes");
    public static final RecipePatterns patterns = new RecipePatterns();

    public static Recipe<?> SHULKER_ESSENCE = Datagen.shapelessRecipe(new AtbywIdentifier("shulker_essence"), "essence", Lists.newArrayList(Items.SHULKER_SHELL, Items.SHULKER_SHELL, Items.GLASS_BOTTLE), AtbywItems.SHULKER_ESSENCE, 1);
    public static Recipe<?> CHICKEN_ESSENCE = Datagen.shapelessRecipe(new AtbywIdentifier("chicken_essence"), "essence", Lists.newArrayList(Items.CHICKEN, Items.FEATHER, Items.GLASS_BOTTLE), AtbywItems.CHICKEN_ESSENCE, 1);
    public static Recipe<?> RABBIT_ESSENCE = Datagen.shapelessRecipe(new AtbywIdentifier("rabbit_essence"), "essence", Lists.newArrayList(Items.RABBIT, Items.RABBIT_HIDE, Items.GLASS_BOTTLE), AtbywItems.RABBIT_ESSENCE, 1);
    public static Recipe<?> COD_ESSENCE = Datagen.shapelessRecipe(new AtbywIdentifier("cod_essence"), "essence", Lists.newArrayList(Items.COD, Items.BONE_MEAL, Items.GLASS_BOTTLE), AtbywItems.COD_ESSENCE, 1);
    public static Recipe<?> SALMON_ESSENCE = Datagen.shapelessRecipe(new AtbywIdentifier("salmon_essence"), "essence", Lists.newArrayList(Items.SALMON, Items.BONE_MEAL, Items.GLASS_BOTTLE), AtbywItems.SALMON_ESSENCE, 1);
    public static Recipe<?> PUFFER_FISH_ESSENCE = Datagen.shapelessRecipe(new AtbywIdentifier("puffer_fish_essence"), "essence", Lists.newArrayList(Items.PUFFERFISH, Items.BONE_MEAL, Items.GLASS_BOTTLE), AtbywItems.PUFFER_FISH_ESSENCE, 1);
    public static Recipe<?> SLIME_ESSENCE = Datagen.shapelessRecipe(new AtbywIdentifier("slime_essence"), "essence", Lists.newArrayList(Items.SLIME_BALL, Items.GLASS_BOTTLE), AtbywItems.SLIME_ESSENCE, 1);
    public static Recipe<?> MAGMA_CUBE_ESSENCE = Datagen.shapelessRecipe(new AtbywIdentifier("magma_cube_essence"), "essence", Lists.newArrayList(Items.MAGMA_CREAM, Items.GLASS_BOTTLE), AtbywItems.MAGMA_CUBE_ESSENCE, 1);

    public static Recipe<?> SHULKER_STATUE = Datagen.shapelessRecipe(new AtbywIdentifier("shulker_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.SHULKER_ESSENCE), StatueRegistry.SHULKER_STATUE, 1);
    public static Recipe<?> CHICKEN_STATUE = Datagen.shapelessRecipe(new AtbywIdentifier("chicken_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.CHICKEN_ESSENCE), StatueRegistry.CHICKEN_STATUE, 1);
    public static Recipe<?> RABBIT_STATUE = Datagen.shapelessRecipe(new AtbywIdentifier("rabbit_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.RABBIT_ESSENCE), StatueRegistry.RABBIT_STATUE, 1);
    public static Recipe<?> COD_STATUE = Datagen.shapelessRecipe(new AtbywIdentifier("cod_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.COD_ESSENCE), StatueRegistry.COD_STATUE, 1);
    public static Recipe<?> SALMON_STATUE = Datagen.shapelessRecipe(new AtbywIdentifier("salmon_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.SALMON_ESSENCE), StatueRegistry.SALMON_STATUE, 1);
    public static Recipe<?> PUFFER_FISH_STATUE = Datagen.shapelessRecipe(new AtbywIdentifier("puffer_fish_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.PUFFER_FISH_ESSENCE), StatueRegistry.PUFFER_FISH_STATUE, 1);
    public static Recipe<?> SLIME_STATUE = Datagen.shapelessRecipe(new AtbywIdentifier("slime_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.SLIME_ESSENCE), StatueRegistry.SLIME_STATUE, 1);
    public static Recipe<?> MAGMA_CUBE_STATUE = Datagen.shapelessRecipe(new AtbywIdentifier("magma_cube_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.MAGMA_CUBE_ESSENCE), StatueRegistry.MAGMA_CUBE_STATUE, 1);

    public static void registerShapedRecipe(String suffix, String category, String group, String[] pattern, Multimap<Character, Ingredient> keys, ItemConvertible result, int count) {
        var recipe = (Recipe<?>) null;

        for (var line : pattern) {
            if (line.contains(" ")) {
                keys.put(' ', Ingredient.EMPTY);
            }
        }

        var resultId = AtbywUtils.getItemID(result.asItem());
        var recipeId = new AtbywIdentifier(resultId.getPath() + (suffix.equals("") ? "" : ("_" + suffix)));
        recipe = Datagen.shapedRecipe(recipeId, group, pattern, keys, result.asItem(), count);
        Datagen.registerRecipe(recipe, category);
    }

    public static void registerShapedRecipe(String suffix, String category, String[] pattern, Multimap<Character, Ingredient> keys, ItemConvertible result, int count) {
        registerShapedRecipe(suffix, category, "", pattern, keys, result, count);
    }

    public static void init() {
        //Generic patterns
        registerStairPatterns();
        registerSlabPatterns();
        registerBricksPatterns();
        registerColumnPatterns();
        registerWallPatterns();
        registerDyingPatterns();
        registerStickPatterns();
        registerFenceDoorPatterns();
        registerBookshelfPatterns();
        registerLadderPatterns();
        registerTorchPatterns();

        //Unique patterns
        registerBookshelfToggles();
        registerFlowerSwitches();
        registerSpikeTraps();
        registerMisc();

        //Shapeless
        Datagen.registerRecipe(SHULKER_ESSENCE, "");
        Datagen.registerRecipe(CHICKEN_ESSENCE, "");
        Datagen.registerRecipe(RABBIT_ESSENCE, "");
        Datagen.registerRecipe(COD_ESSENCE, "");
        Datagen.registerRecipe(SALMON_ESSENCE, "");
        Datagen.registerRecipe(PUFFER_FISH_ESSENCE, "");
        Datagen.registerRecipe(MAGMA_CUBE_ESSENCE, "");
        Datagen.registerRecipe(SLIME_ESSENCE, "");

        Datagen.registerRecipe(SHULKER_STATUE, "statues");
        Datagen.registerRecipe(CHICKEN_STATUE, "statues");
        Datagen.registerRecipe(RABBIT_STATUE, "statues");
        Datagen.registerRecipe(COD_STATUE, "statues");
        Datagen.registerRecipe(SALMON_STATUE, "statues");
        Datagen.registerRecipe(PUFFER_FISH_STATUE, "statues");
        Datagen.registerRecipe(SLIME_STATUE, "statues");
        Datagen.registerRecipe(MAGMA_CUBE_STATUE, "statues");

        LOGGER.info("ATBYW Recipes Inintiliazed");
    }

    public static void registerStairPatterns() {
        //Map<Input, Output>
        var map = HashBasedTable.<String, ItemConvertible, ItemConvertible>create();
        map.put("", Blocks.DIRT, AtbywBlocks.DIRT_STAIRS);
        map.put("", Blocks.GRASS_BLOCK, AtbywBlocks.GRASS_BLOCK_STAIRS);
        map.put("", Blocks.MYCELIUM, AtbywBlocks.MYCELIUM_STAIRS);
        map.put("", Blocks.COARSE_DIRT, AtbywBlocks.COARSE_DIRT_STAIRS);
        map.put("", Blocks.PODZOL, AtbywBlocks.PODZOL_STAIRS);
        map.put("", Blocks.NETHERRACK, AtbywBlocks.NETHERRACK_STAIRS);
        map.put("", Blocks.CRIMSON_NYLIUM, AtbywBlocks.CRIMSON_NYLIUM_STAIRS);
        map.put("", Blocks.WARPED_NYLIUM, AtbywBlocks.WARPED_NYLIUM_STAIRS);
        map.put("", Blocks.GRANITE, AtbywBlocks.GRANITE_TILES_STAIRS);
        map.put("", Blocks.DIORITE, AtbywBlocks.DIORITE_BRICKS_STAIRS);
        map.put("", Blocks.ANDESITE, AtbywBlocks.ANDESITE_BRICKS_STAIRS);
        map.put("terracotta_stairs", Blocks.TERRACOTTA, AtbywBlocks.TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.WHITE_TERRACOTTA, AtbywBlocks.WHITE_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.ORANGE_TERRACOTTA, AtbywBlocks.ORANGE_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.MAGENTA_TERRACOTTA, AtbywBlocks.MAGENTA_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.LIGHT_BLUE_TERRACOTTA, AtbywBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.YELLOW_TERRACOTTA, AtbywBlocks.YELLOW_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.LIME_TERRACOTTA, AtbywBlocks.LIME_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.PINK_TERRACOTTA, AtbywBlocks.PINK_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.GRAY_TERRACOTTA, AtbywBlocks.GRAY_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.LIGHT_GRAY_TERRACOTTA, AtbywBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.CYAN_TERRACOTTA, AtbywBlocks.CYAN_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.PURPLE_TERRACOTTA, AtbywBlocks.PURPLE_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.BLUE_TERRACOTTA, AtbywBlocks.BLUE_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.BROWN_TERRACOTTA, AtbywBlocks.BROWN_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.GREEN_TERRACOTTA, AtbywBlocks.GREEN_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.RED_TERRACOTTA, AtbywBlocks.RED_TERRACOTTA_STAIRS);
        map.put("terracotta_stairs", Blocks.BLACK_TERRACOTTA, AtbywBlocks.BLACK_TERRACOTTA_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.WHITE_TERRACOTTA_BRICKS, AtbywBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.LIME_TERRACOTTA_BRICKS, AtbywBlocks.LIME_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.PINK_TERRACOTTA_BRICKS, AtbywBlocks.PINK_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.GRAY_TERRACOTTA_BRICKS, AtbywBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.CYAN_TERRACOTTA_BRICKS, AtbywBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.BLUE_TERRACOTTA_BRICKS, AtbywBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.BROWN_TERRACOTTA_BRICKS, AtbywBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.GREEN_TERRACOTTA_BRICKS, AtbywBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.RED_TERRACOTTA_BRICKS, AtbywBlocks.RED_TERRACOTTA_BRICKS_STAIRS);
        map.put("terracotta_bricks_stairs", AtbywBlocks.BLACK_TERRACOTTA_BRICKS, AtbywBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS);
        map.put("concrete_stairs", Blocks.WHITE_CONCRETE, AtbywBlocks.WHITE_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.ORANGE_CONCRETE, AtbywBlocks.ORANGE_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.MAGENTA_CONCRETE, AtbywBlocks.MAGENTA_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.LIGHT_BLUE_CONCRETE, AtbywBlocks.LIGHT_BLUE_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.YELLOW_CONCRETE, AtbywBlocks.YELLOW_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.LIME_CONCRETE, AtbywBlocks.LIME_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.PINK_CONCRETE, AtbywBlocks.PINK_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.GRAY_CONCRETE, AtbywBlocks.GRAY_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.LIGHT_GRAY_CONCRETE, AtbywBlocks.LIGHT_GRAY_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.CYAN_CONCRETE, AtbywBlocks.CYAN_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.PURPLE_CONCRETE, AtbywBlocks.PURPLE_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.BLUE_CONCRETE, AtbywBlocks.BLUE_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.BROWN_CONCRETE, AtbywBlocks.BROWN_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.GREEN_CONCRETE, AtbywBlocks.GREEN_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.RED_CONCRETE, AtbywBlocks.RED_CONCRETE_STAIRS);
        map.put("concrete_stairs", Blocks.BLACK_CONCRETE, AtbywBlocks.BLACK_CONCRETE_STAIRS);
        map.put("", AtbywBlocks.PURPUR_TILES, AtbywBlocks.PURPUR_TILES_STAIRS);
        map.put("", AtbywBlocks.CUT_PURPUR_BLOCK, AtbywBlocks.CUT_PURPUR_STAIRS);
        map.put("", AtbywBlocks.SMOOTH_PURPUR_BLOCK, AtbywBlocks.SMOOTH_PURPUR_STAIRS);
        map.put("", AtbywBlocks.COMPACTED_SNOW_BLOCK, AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS);
        map.put("", AtbywBlocks.COMPACTED_SNOW_BRICKS, AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS);
        map.put("", Blocks.PACKED_ICE, AtbywBlocks.PACKED_ICE_STAIRS);
        map.put("", Blocks.BLUE_ICE, AtbywBlocks.BLUE_ICE_STAIRS);
        map.put("", AtbywBlocks.PACKED_ICE_BRICKS, AtbywBlocks.PACKED_ICE_BRICKS_STAIRS);
        map.put("", AtbywBlocks.BLUE_ICE_BRICKS, AtbywBlocks.BLUE_ICE_BRICKS_STAIRS);
        map.put("", Blocks.SAND, AtbywBlocks.SAND_STAIRS);
        map.put("", Blocks.RED_SAND, AtbywBlocks.RED_SAND_STAIRS);
        map.put("", Blocks.GRAVEL, AtbywBlocks.GRAVEL_STAIRS);
        map.put("", Blocks.ROOTED_DIRT, AtbywBlocks.ROOTED_DIRT_STAIRS);
        map.put("log_stairs", Blocks.OAK_LOG, AtbywBlocks.OAK_LOG_STAIRS);
        map.put("log_stairs", Blocks.SPRUCE_LOG, AtbywBlocks.SPRUCE_LOG_STAIRS);
        map.put("log_stairs", Blocks.BIRCH_LOG, AtbywBlocks.BIRCH_LOG_STAIRS);
        map.put("log_stairs", Blocks.JUNGLE_LOG, AtbywBlocks.JUNGLE_LOG_STAIRS);
        map.put("log_stairs", Blocks.ACACIA_LOG, AtbywBlocks.ACACIA_LOG_STAIRS);
        map.put("log_stairs", Blocks.DARK_OAK_LOG, AtbywBlocks.DARK_OAK_LOG_STAIRS);
        map.put("log_stairs", Blocks.CRIMSON_STEM, AtbywBlocks.CRIMSON_STEM_STAIRS);
        map.put("log_stairs", Blocks.WARPED_STEM, AtbywBlocks.WARPED_STEM_STAIRS);
        map.put("stripped_log_stairs", Blocks.STRIPPED_OAK_LOG, AtbywBlocks.STRIPPED_OAK_LOG_STAIRS);
        map.put("stripped_log_stairs", Blocks.STRIPPED_SPRUCE_LOG, AtbywBlocks.STRIPPED_SPRUCE_LOG_STAIRS);
        map.put("stripped_log_stairs", Blocks.STRIPPED_BIRCH_LOG, AtbywBlocks.STRIPPED_BIRCH_LOG_STAIRS);
        map.put("stripped_log_stairs", Blocks.STRIPPED_JUNGLE_LOG, AtbywBlocks.STRIPPED_JUNGLE_LOG_STAIRS);
        map.put("stripped_log_stairs", Blocks.STRIPPED_ACACIA_LOG, AtbywBlocks.STRIPPED_ACACIA_LOG_STAIRS);
        map.put("stripped_log_stairs", Blocks.STRIPPED_DARK_OAK_LOG, AtbywBlocks.STRIPPED_DARK_OAK_LOG_STAIRS);
        map.put("stripped_log_stairs", Blocks.STRIPPED_CRIMSON_STEM, AtbywBlocks.STRIPPED_CRIMSON_STEM_STAIRS);
        map.put("stripped_log_stairs", Blocks.STRIPPED_WARPED_STEM, AtbywBlocks.STRIPPED_WARPED_STEM_STAIRS);

        for (var entry : map.cellSet()) {
            var pattern = patterns.stairsPattern(entry.getColumnKey());
            registerShapedRecipe("", "stairs", entry.getRowKey(), pattern.getFirst(), pattern.getSecond(), entry.getValue(), 4);
        }
    }

    public static void registerSlabPatterns() {
        //Table<Pair<Category, Group>, Input, Pair<Output, Count>>
        var table = HashBasedTable.<Pair<String, String>, ItemConvertible, Pair<ItemConvertible, Integer>>create();
        table.put(new Pair<>("slab", ""), Blocks.DIRT, new Pair<>(AtbywBlocks.DIRT_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.GRASS_BLOCK, new Pair<>(AtbywBlocks.GRASS_BLOCK_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.MYCELIUM, new Pair<>(AtbywBlocks.MYCELIUM_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.COARSE_DIRT, new Pair<>(AtbywBlocks.COARSE_DIRT_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.PODZOL, new Pair<>(AtbywBlocks.PODZOL_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.NETHERRACK, new Pair<>(AtbywBlocks.NETHERRACK_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.CRIMSON_NYLIUM, new Pair<>(AtbywBlocks.CRIMSON_NYLIUM_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.WARPED_NYLIUM, new Pair<>(AtbywBlocks.WARPED_NYLIUM_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.GRANITE, new Pair<>(AtbywBlocks.GRANITE_TILES_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.DIORITE, new Pair<>(AtbywBlocks.DIORITE_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.ANDESITE, new Pair<>(AtbywBlocks.ANDESITE_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.TERRACOTTA, new Pair<>(AtbywBlocks.TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.WHITE_TERRACOTTA, new Pair<>(AtbywBlocks.WHITE_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.ORANGE_TERRACOTTA, new Pair<>(AtbywBlocks.ORANGE_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.MAGENTA_TERRACOTTA, new Pair<>(AtbywBlocks.MAGENTA_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.LIGHT_BLUE_TERRACOTTA, new Pair<>(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.YELLOW_TERRACOTTA, new Pair<>(AtbywBlocks.YELLOW_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.LIME_TERRACOTTA, new Pair<>(AtbywBlocks.LIME_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.PINK_TERRACOTTA, new Pair<>(AtbywBlocks.PINK_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.GRAY_TERRACOTTA, new Pair<>(AtbywBlocks.GRAY_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.LIGHT_GRAY_TERRACOTTA, new Pair<>(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.CYAN_TERRACOTTA, new Pair<>(AtbywBlocks.CYAN_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.PURPLE_TERRACOTTA, new Pair<>(AtbywBlocks.PURPLE_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.BLUE_TERRACOTTA, new Pair<>(AtbywBlocks.BLUE_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.BROWN_TERRACOTTA, new Pair<>(AtbywBlocks.BROWN_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.GREEN_TERRACOTTA, new Pair<>(AtbywBlocks.GREEN_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.RED_TERRACOTTA, new Pair<>(AtbywBlocks.RED_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_slabs"), Blocks.BLACK_TERRACOTTA, new Pair<>(AtbywBlocks.BLACK_TERRACOTTA_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.WHITE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.WHITE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.LIME_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.LIME_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.PINK_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.PINK_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.GRAY_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.GRAY_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.CYAN_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.CYAN_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.BLUE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.BLUE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.BROWN_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.BROWN_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.GREEN_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.GREEN_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.RED_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.RED_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "terracotta_bricks_slabs"), AtbywBlocks.BLACK_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.BLACK_TERRACOTTA_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.WHITE_CONCRETE, new Pair<>(AtbywBlocks.WHITE_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.ORANGE_CONCRETE, new Pair<>(AtbywBlocks.ORANGE_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.MAGENTA_CONCRETE, new Pair<>(AtbywBlocks.MAGENTA_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.LIGHT_BLUE_CONCRETE, new Pair<>(AtbywBlocks.LIGHT_BLUE_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.YELLOW_CONCRETE, new Pair<>(AtbywBlocks.YELLOW_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.LIME_CONCRETE, new Pair<>(AtbywBlocks.LIME_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.PINK_CONCRETE, new Pair<>(AtbywBlocks.PINK_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.GRAY_CONCRETE, new Pair<>(AtbywBlocks.GRAY_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.LIGHT_GRAY_CONCRETE, new Pair<>(AtbywBlocks.LIGHT_GRAY_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.CYAN_CONCRETE, new Pair<>(AtbywBlocks.CYAN_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.PURPLE_CONCRETE, new Pair<>(AtbywBlocks.PURPLE_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.BLUE_CONCRETE, new Pair<>(AtbywBlocks.BLUE_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.BROWN_CONCRETE, new Pair<>(AtbywBlocks.BROWN_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.GREEN_CONCRETE, new Pair<>(AtbywBlocks.GREEN_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.RED_CONCRETE, new Pair<>(AtbywBlocks.RED_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", "concrete_slabs"), Blocks.BLACK_CONCRETE, new Pair<>(AtbywBlocks.BLACK_CONCRETE_SLAB, 6));
        table.put(new Pair<>("slab", ""), AtbywBlocks.PURPUR_TILES, new Pair<>(AtbywBlocks.PURPUR_TILES_SLAB, 6));
        table.put(new Pair<>("slab", ""), AtbywBlocks.CUT_PURPUR_BLOCK, new Pair<>(AtbywBlocks.CUT_PURPUR_SLAB, 6));
        table.put(new Pair<>("slab", ""), AtbywBlocks.SMOOTH_PURPUR_BLOCK, new Pair<>(AtbywBlocks.SMOOTH_PURPUR_SLAB, 6));
        table.put(new Pair<>("slab", ""), AtbywBlocks.COMPACTED_SNOW_BLOCK, new Pair<>(AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB, 6));
        table.put(new Pair<>("slab", ""), AtbywBlocks.COMPACTED_SNOW_BRICKS, new Pair<>(AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.PACKED_ICE, new Pair<>(AtbywBlocks.PACKED_ICE_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.BLUE_ICE, new Pair<>(AtbywBlocks.BLUE_ICE_SLAB, 6));
        table.put(new Pair<>("slab", ""), AtbywBlocks.PACKED_ICE_BRICKS, new Pair<>(AtbywBlocks.PACKED_ICE_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", ""), AtbywBlocks.BLUE_ICE_BRICKS, new Pair<>(AtbywBlocks.BLUE_ICE_BRICKS_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.SAND, new Pair<>(AtbywBlocks.SAND_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.RED_SAND, new Pair<>(AtbywBlocks.RED_SAND_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.GRAVEL, new Pair<>(AtbywBlocks.GRAVEL_SLAB, 6));
        table.put(new Pair<>("slab", ""), Blocks.ROOTED_DIRT, new Pair<>(AtbywBlocks.ROOTED_DIRT_SLAB, 6));
        table.put(new Pair<>("slab", "log_slabs"), Blocks.OAK_LOG, new Pair<>(AtbywBlocks.OAK_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "log_slabs"), Blocks.SPRUCE_LOG, new Pair<>(AtbywBlocks.SPRUCE_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "log_slabs"), Blocks.BIRCH_LOG, new Pair<>(AtbywBlocks.BIRCH_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "log_slabs"), Blocks.JUNGLE_LOG, new Pair<>(AtbywBlocks.JUNGLE_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "log_slabs"), Blocks.ACACIA_LOG, new Pair<>(AtbywBlocks.ACACIA_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "log_slabs"), Blocks.DARK_OAK_LOG, new Pair<>(AtbywBlocks.DARK_OAK_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "log_slabs"), Blocks.CRIMSON_STEM, new Pair<>(AtbywBlocks.CRIMSON_STEM_SLAB, 6));
        table.put(new Pair<>("slab", "log_slabs"), Blocks.WARPED_STEM, new Pair<>(AtbywBlocks.WARPED_STEM_SLAB, 6));
        table.put(new Pair<>("slab", "stripped_log_slabs"), Blocks.STRIPPED_OAK_LOG, new Pair<>(AtbywBlocks.STRIPPED_OAK_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "stripped_log_slabs"), Blocks.STRIPPED_SPRUCE_LOG, new Pair<>(AtbywBlocks.STRIPPED_SPRUCE_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "stripped_log_slabs"), Blocks.STRIPPED_BIRCH_LOG, new Pair<>(AtbywBlocks.STRIPPED_BIRCH_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "stripped_log_slabs"), Blocks.STRIPPED_JUNGLE_LOG, new Pair<>(AtbywBlocks.STRIPPED_JUNGLE_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "stripped_log_slabs"), Blocks.STRIPPED_ACACIA_LOG, new Pair<>(AtbywBlocks.STRIPPED_ACACIA_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "stripped_log_slabs"), Blocks.STRIPPED_DARK_OAK_LOG, new Pair<>(AtbywBlocks.STRIPPED_DARK_OAK_LOG_SLAB, 6));
        table.put(new Pair<>("slab", "stripped_log_slabs"), Blocks.STRIPPED_CRIMSON_STEM, new Pair<>(AtbywBlocks.STRIPPED_CRIMSON_STEM_SLAB, 6));
        table.put(new Pair<>("slab", "stripped_log_slabs"), Blocks.STRIPPED_WARPED_STEM, new Pair<>(AtbywBlocks.STRIPPED_WARPED_STEM_SLAB, 6));
        table.put(new Pair<>("", ""), AtbywBlocks.COMPACTED_SNOW_BLOCK, new Pair<>(AtbywBlocks.COMPACTED_SNOW, 6));

        for (var entry : table.cellSet()) {
            var pattern = patterns.slabPattern(entry.getColumnKey());
            registerShapedRecipe("", entry.getRowKey().getFirst(), entry.getRowKey().getSecond(), pattern.getFirst(), pattern.getSecond(), entry.getValue().getFirst(), entry.getValue().getSecond());
        }
    }

    public static void registerBricksPatterns() {
        //Table<Category, Input, Pair<Output, Count>>
        var table = HashBasedTable.<Triplet<String, String, String>, ItemConvertible, Pair<ItemConvertible, Integer>>create();
        table.put(new Triplet<>("from_basalt", "bricks", "basalt_bricks"), Blocks.BASALT, new Pair<>(AtbywBlocks.BASALT_BRICKS, 4));
        table.put(new Triplet<>("from_polished_basalt", "bricks", "basalt_bricks"), Blocks.POLISHED_BASALT, new Pair<>(AtbywBlocks.BASALT_BRICKS, 4));
        table.put(new Triplet<>("from_smooth_basalt", "bricks", "basalt_bricks"), Blocks.SMOOTH_BASALT, new Pair<>(AtbywBlocks.BASALT_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", ""), Blocks.POLISHED_GRANITE, new Pair<>(AtbywBlocks.GRANITE_TILES, 4));
        table.put(new Triplet<>("", "bricks", ""), Blocks.POLISHED_DIORITE, new Pair<>(AtbywBlocks.DIORITE_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", ""), Blocks.POLISHED_ANDESITE, new Pair<>(AtbywBlocks.ANDESITE_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.TERRACOTTA, new Pair<>(AtbywBlocks.TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.WHITE_TERRACOTTA, new Pair<>(AtbywBlocks.WHITE_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.ORANGE_TERRACOTTA, new Pair<>(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.MAGENTA_TERRACOTTA, new Pair<>(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.LIGHT_BLUE_TERRACOTTA, new Pair<>(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.YELLOW_TERRACOTTA, new Pair<>(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.LIME_TERRACOTTA, new Pair<>(AtbywBlocks.LIME_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.PINK_TERRACOTTA, new Pair<>(AtbywBlocks.PINK_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.GRAY_TERRACOTTA, new Pair<>(AtbywBlocks.GRAY_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.LIGHT_GRAY_TERRACOTTA, new Pair<>(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.CYAN_TERRACOTTA, new Pair<>(AtbywBlocks.CYAN_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.PURPLE_TERRACOTTA, new Pair<>(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.BLUE_TERRACOTTA, new Pair<>(AtbywBlocks.BLUE_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.BROWN_TERRACOTTA, new Pair<>(AtbywBlocks.BROWN_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.GREEN_TERRACOTTA, new Pair<>(AtbywBlocks.GREEN_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.RED_TERRACOTTA, new Pair<>(AtbywBlocks.RED_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "terracotta_bricks"), Blocks.BLACK_TERRACOTTA, new Pair<>(AtbywBlocks.BLACK_TERRACOTTA_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.WHITE_CONCRETE, new Pair<>(AtbywBlocks.WHITE_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.ORANGE_CONCRETE, new Pair<>(AtbywBlocks.ORANGE_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.MAGENTA_CONCRETE, new Pair<>(AtbywBlocks.MAGENTA_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.LIGHT_BLUE_CONCRETE, new Pair<>(AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.YELLOW_CONCRETE, new Pair<>(AtbywBlocks.YELLOW_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.LIME_CONCRETE, new Pair<>(AtbywBlocks.LIME_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.PINK_CONCRETE, new Pair<>(AtbywBlocks.PINK_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.GRAY_CONCRETE, new Pair<>(AtbywBlocks.GRAY_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.LIGHT_GRAY_CONCRETE, new Pair<>(AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.CYAN_CONCRETE, new Pair<>(AtbywBlocks.CYAN_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.PURPLE_CONCRETE, new Pair<>(AtbywBlocks.PURPLE_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.BLUE_CONCRETE, new Pair<>(AtbywBlocks.BLUE_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.BROWN_CONCRETE, new Pair<>(AtbywBlocks.BROWN_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.GREEN_CONCRETE, new Pair<>(AtbywBlocks.GREEN_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.RED_CONCRETE, new Pair<>(AtbywBlocks.RED_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", "cinder_blocks"), Blocks.BLACK_CONCRETE, new Pair<>(AtbywBlocks.BLACK_CINDER_BLOCKS, 4));
        table.put(new Triplet<>("", "bricks", ""), Blocks.PURPUR_PILLAR, new Pair<>(AtbywBlocks.PURPUR_TILES, 4));
        table.put(new Triplet<>("", "bricks", ""), Blocks.PURPUR_BLOCK, new Pair<>(AtbywBlocks.CUT_PURPUR_BLOCK, 4));
        table.put(new Triplet<>("", "bricks", ""), Blocks.SNOW_BLOCK, new Pair<>(AtbywBlocks.COMPACTED_SNOW_BLOCK, 4));
        table.put(new Triplet<>("", "bricks", ""), AtbywBlocks.COMPACTED_SNOW_BLOCK, new Pair<>(AtbywBlocks.COMPACTED_SNOW_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", ""), Blocks.PACKED_ICE, new Pair<>(AtbywBlocks.PACKED_ICE_BRICKS, 4));
        table.put(new Triplet<>("", "bricks", ""), Blocks.BLUE_ICE, new Pair<>(AtbywBlocks.BLUE_ICE_BRICKS, 4));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.GLASS_SHARD, new Pair<>(AtbywBlocks.SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.WHITE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.WHITE_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.ORANGE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.ORANGE_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.MAGENTA_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.MAGENTA_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.LIGHT_BLUE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.LIGHT_BLUE_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.YELLOW_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.YELLOW_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.LIME_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.LIME_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.PINK_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.PINK_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.GRAY_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.GRAY_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.LIGHT_GRAY_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.LIGHT_GRAY_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.CYAN_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.CYAN_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.PURPLE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.PURPLE_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.BLUE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.BLUE_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.BROWN_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.BROWN_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.GREEN_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.GREEN_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.RED_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.RED_STAINED_SHATTERED_GLASS, 1));
        table.put(new Triplet<>("", "shattered_glass", "shattered_glass"), AtbywItems.BLACK_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.BLACK_STAINED_SHATTERED_GLASS, 1));

        for (var entry : table.cellSet()) {
            var pattern = patterns.bricksPattern(entry.getColumnKey());
            var suffix = entry.getRowKey().getFirst();
            var category = entry.getRowKey().getSecond();
            var group = entry.getRowKey().getThird();
            var result = entry.getValue().getFirst();
            var count = entry.getValue().getSecond();
            registerShapedRecipe(suffix, category, group, pattern.getFirst(), pattern.getSecond(), result, count);
        }
    }

    public static void registerWallPatterns() {
        //Table<Group, Input, Output>
        var map = HashBasedTable.<String, ItemConvertible, ItemConvertible>create();
        map.put("terracotta_bricks_wall", AtbywBlocks.TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.WHITE_TERRACOTTA_BRICKS, AtbywBlocks.WHITE_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.LIME_TERRACOTTA_BRICKS, AtbywBlocks.LIME_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.PINK_TERRACOTTA_BRICKS, AtbywBlocks.PINK_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.GRAY_TERRACOTTA_BRICKS, AtbywBlocks.GRAY_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.CYAN_TERRACOTTA_BRICKS, AtbywBlocks.CYAN_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.BLUE_TERRACOTTA_BRICKS, AtbywBlocks.BLUE_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.BROWN_TERRACOTTA_BRICKS, AtbywBlocks.BROWN_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.GREEN_TERRACOTTA_BRICKS, AtbywBlocks.GREEN_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.RED_TERRACOTTA_BRICKS, AtbywBlocks.RED_TERRACOTTA_BRICKS_WALL);
        map.put("terracotta_bricks_wall", AtbywBlocks.BLACK_TERRACOTTA_BRICKS, AtbywBlocks.BLACK_TERRACOTTA_BRICKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.WHITE_CINDER_BLOCKS, AtbywBlocks.WHITE_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.ORANGE_CINDER_BLOCKS, AtbywBlocks.ORANGE_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.MAGENTA_CINDER_BLOCKS, AtbywBlocks.MAGENTA_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS, AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.YELLOW_CINDER_BLOCKS, AtbywBlocks.YELLOW_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.LIME_CINDER_BLOCKS, AtbywBlocks.LIME_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.PINK_CINDER_BLOCKS, AtbywBlocks.PINK_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.GRAY_CINDER_BLOCKS, AtbywBlocks.GRAY_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS, AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.CYAN_CINDER_BLOCKS, AtbywBlocks.CYAN_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.PURPLE_CINDER_BLOCKS, AtbywBlocks.PURPLE_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.BLUE_CINDER_BLOCKS, AtbywBlocks.BLUE_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.BROWN_CINDER_BLOCKS, AtbywBlocks.BROWN_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.GREEN_CINDER_BLOCKS, AtbywBlocks.GREEN_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.RED_CINDER_BLOCKS, AtbywBlocks.RED_CINDER_BLOCKS_WALL);
        map.put("cinder_blocks_wall", AtbywBlocks.BLACK_CINDER_BLOCKS, AtbywBlocks.BLACK_CINDER_BLOCKS_WALL);

        for (var entry : map.cellSet()) {
            var pattern = patterns.wallPattern(entry.getColumnKey());
            registerShapedRecipe("", "wall", entry.getRowKey(), pattern.getFirst(), pattern.getSecond(), entry.getValue(), 6);
        }
    }

    public static void registerDyingPatterns() {
        //Table<Group, Output, Input>
        var dyeMap = HashBasedTable.<String, ItemConvertible, ItemConvertible>create();
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.WHITE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.ORANGE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.MAGENTA_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.YELLOW_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.LIME_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.PINK_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.GRAY_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.CYAN_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.PURPLE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.BLUE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.BROWN_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.GREEN_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.RED_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_stairs_dying", AtbywBlocks.BLACK_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.WHITE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.ORANGE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.MAGENTA_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.YELLOW_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.LIME_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.PINK_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.GRAY_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.CYAN_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.PURPLE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.BLUE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.BROWN_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.GREEN_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.RED_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_slab_dying", AtbywBlocks.BLACK_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.WHITE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.LIME_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.PINK_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.GRAY_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.CYAN_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.BLUE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.BROWN_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.GREEN_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.RED_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_dying", AtbywBlocks.BLACK_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.LIME_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.PINK_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.RED_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_stairs_dying", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.LIME_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.PINK_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.RED_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_slab_dying", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.LIME_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.PINK_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.RED_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeMap.put("terracotta_bricks_wall_dying", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);

        //Map<Pair<Input outer ring, Input middle>, Output>
        var ringMap = new HashMap<Pair<ItemConvertible, ItemConvertible>, ItemConvertible>();
        ringMap.put(new Pair<>(Items.IRON_NUGGET, Items.REDSTONE_TORCH), AtbywBlocks.REDSTONE_LANTERN);

        for (var entry : dyeMap.cellSet()) {
            for (var dye : AtbywUtils.DYES) {
                var pattern = patterns.dyingRingPattern(entry.getValue(), dye);
                registerShapedRecipe("from_dying", "dying", entry.getRowKey(), pattern.getFirst(), pattern.getSecond(), entry.getColumnKey(), 8);
            }
        }
        for (var entry : ringMap.entrySet()) {
            var pattern = patterns.dyingRingPattern(entry.getKey().getFirst(), entry.getKey().getSecond());
            registerShapedRecipe("", "", pattern.getFirst(), pattern.getSecond(), entry.getValue(), 1);
        }
    }

    public static void registerColumnPatterns() {
        //Table<Category, Input, Pair<Output, Count>>
        var table = HashBasedTable.<String, ItemConvertible, Pair<ItemConvertible, Integer>>create();
        table.put("column", Blocks.GRANITE, new Pair<>(AtbywBlocks.GRANITE_COLUMN, 3));
        table.put("column", Blocks.DIORITE, new Pair<>(AtbywBlocks.DIORITE_COLUMN, 3)) ;
        table.put("column", Blocks.ANDESITE, new Pair<>(AtbywBlocks.ANDESITE_COLUMN, 3));
        table.put("column", Blocks.POLISHED_GRANITE, new Pair<>(AtbywBlocks.GRANITE_COLUMN, 3)) ;
        table.put("column", Blocks.POLISHED_DIORITE, new Pair<>(AtbywBlocks.DIORITE_COLUMN, 3));
        table.put("column", Blocks.POLISHED_ANDESITE, new Pair<>(AtbywBlocks.ANDESITE_COLUMN, 3)) ;
        table.put("column", Blocks.SANDSTONE, new Pair<>(AtbywBlocks.SANDSTONE_COLUMN, 3));
        table.put("column", Blocks.SMOOTH_SANDSTONE, new Pair<>(AtbywBlocks.SANDSTONE_COLUMN, 3)) ;
        table.put("column", Blocks.CHISELED_SANDSTONE, new Pair<>(AtbywBlocks.CHISELED_SANDSTONE_COLUMN, 3));
        table.put("column", Blocks.RED_SANDSTONE, new Pair<>(AtbywBlocks.RED_SANDSTONE_COLUMN, 3)) ;
        table.put("column", Blocks.SMOOTH_RED_SANDSTONE, new Pair<>(AtbywBlocks.RED_SANDSTONE_COLUMN, 3));
        table.put("column", Blocks.CHISELED_RED_SANDSTONE, new Pair<>(AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN, 3)) ;
        table.put("column", Blocks.PURPUR_BLOCK, new Pair<>(AtbywBlocks.PURPUR_COLUMN, 3));
        table.put("column", Blocks.STONE_BRICKS, new Pair<>(AtbywBlocks.STONE_BRICKS_COLUMN, 3)) ;
        table.put("column", Blocks.MOSSY_STONE_BRICKS, new Pair<>(AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN, 3));
        table.put("column", Blocks.CRACKED_STONE_BRICKS, new Pair<>(AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN, 3)) ;
        table.put("column", Blocks.NETHER_BRICKS, new Pair<>(AtbywBlocks.NETHER_BRICKS_COLUMN, 3));
        table.put("column", Blocks.QUARTZ_BLOCK, new Pair<>(AtbywBlocks.QUARTZ_COLUMN, 3)) ;
        table.put("column", Blocks.PRISMARINE_BRICKS, new Pair<>(AtbywBlocks.PRISMARINE_COLUMN, 3));
        table.put("column", Blocks.BLACKSTONE, new Pair<>(AtbywBlocks.BLACKSTONE_COLUMN, 3));
        table.put("", AtbywItems.LARGE_CHAIN_LINK, new Pair<>(AtbywBlocks.LARGE_CHAIN, 1));

        for (var entry : table.cellSet()) {
            var group = entry.getRowKey().equals("column") ? "columns" : "";
            var pattern = patterns.columnPattern(entry.getColumnKey());
            registerShapedRecipe("", entry.getRowKey(), group, pattern.getFirst(), pattern.getSecond(), entry.getValue().getFirst(), entry.getValue().getSecond());
        }
    }

    public static void registerFlowerSwitches() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Items.DANDELION, AtbywBlocks.DANDELION_PULL_SWITCH);
        map.put(Items.POPPY, AtbywBlocks.POPPY_PULL_SWITCH);
        map.put(Items.BLUE_ORCHID, AtbywBlocks.BLUE_ORCHID_PULL_SWITCH);
        map.put(Items.ALLIUM, AtbywBlocks.ALLIUM_PULL_SWITCH);
        map.put(Items.AZURE_BLUET, AtbywBlocks.AZURE_BLUET_PULL_SWITCH);
        map.put(Items.RED_TULIP, AtbywBlocks.RED_TULIP_PULL_SWITCH);
        map.put(Items.ORANGE_TULIP, AtbywBlocks.ORANGE_TULIP_PULL_SWITCH);
        map.put(Items.WHITE_TULIP, AtbywBlocks.WHITE_TULIP_PULL_SWITCH);
        map.put(Items.PINK_TULIP, AtbywBlocks.PINK_TULIP_PULL_SWITCH);
        map.put(Items.OXEYE_DAISY, AtbywBlocks.OXEYE_DAISY_PULL_SWITCH);
        map.put(Items.CORNFLOWER, AtbywBlocks.CORNFLOWER_PULL_SWITCH);
        map.put(Items.LILY_OF_THE_VALLEY, AtbywBlocks.LILY_OF_THE_VALLEY_PULL_SWITCH);
        map.put(Items.WITHER_ROSE, AtbywBlocks.WITHER_ROSE_PULL_SWITCH);

        for (var entry : map.entrySet()) {
            var keys = HashMultimap.<Character, Ingredient>create();
            keys.put('F', Ingredient.ofItems(entry.getKey().asItem()));
            keys.put('S', Ingredient.ofItems(Items.STICK));
            keys.put('R', Ingredient.ofItems(Items.REDSTONE));
            registerShapedRecipe("", "", "flower_pull_switches", new String[] {" F ", "RSR"}, keys, entry.getValue(), 1);
        }
    }

    public static void registerStickPatterns() {
        //Table<Suffix, Input, Pair<Output, Count>>
        var table = HashBasedTable.<String, ItemConvertible, Pair<ItemConvertible, Integer>>create();
        table.put("from_basalt", Blocks.BASALT, new Pair<>(AtbywBlocks.BASALT_PILLAR, 2));
        table.put("from_polished_basalt", Blocks.POLISHED_BASALT, new Pair<>(AtbywBlocks.BASALT_PILLAR, 2));
        table.put("from_smooth_basalt", Blocks.SMOOTH_BASALT, new Pair<>(AtbywBlocks.BASALT_PILLAR, 2));
        table.put("", Items.BAMBOO, new Pair<>(AtbywItems.BAMBOO_STICK, 4));
        table.put("", Blocks.SHROOMLIGHT, new Pair<>(AtbywItems.SHROOMSTICK, 4));
        table.put("", Blocks.PURPUR_SLAB, new Pair<>(AtbywBlocks.CHISELED_PURPUR_BLOCK, 1));

        for (var entry : table.cellSet()) {
            var group = !entry.getRowKey().equals("") ? "basalt_pillar" : "";
            var pattern = patterns.stickPattern(entry.getColumnKey());
            registerShapedRecipe(entry.getRowKey(), "", group, pattern.getFirst(), pattern.getSecond(), entry.getValue().getFirst(), entry.getValue().getSecond());
        }
    }

    public static void registerSpikeTraps() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Items.IRON_INGOT, AtbywBlocks.IRON_SPIKE_TRAP);
        map.put(Items.GOLD_INGOT, AtbywBlocks.GOLD_SPIKE_TRAP);
        map.put(Items.DIAMOND, AtbywBlocks.DIAMOND_SPIKE_TRAP);
        map.put(Items.NETHERITE_INGOT, AtbywBlocks.NETHERITE_SPIKE_TRAP);

        for (var entry : map.entrySet()) {
            var keys = HashMultimap.<Character, Ingredient>create();
            keys.put('#', Ingredient.ofItems(entry.getKey().asItem()));
            keys.put('R', Ingredient.ofItems(Items.REDSTONE));
            keys.put('I', Ingredient.ofItems(Items.IRON_INGOT));
            keys.put('S', Ingredient.fromTag(ItemTags.STONE_CRAFTING_MATERIALS));
            registerShapedRecipe("", "redstone", "spike_traps", new String[] {"S#S", "#I#", "SRS"}, keys, entry.getValue(), 1);
        }
    }

    public static void registerFenceDoorPatterns() {
        //Table<Category, Input, Output>
        var table = HashBasedTable.<String, ItemConvertible, ItemConvertible>create();
        table.put("", Blocks.OAK_PLANKS, AtbywBlocks.OAK_FENCE_DOOR);
        table.put("", Blocks.SPRUCE_PLANKS, AtbywBlocks.SPRUCE_FENCE_DOOR);
        table.put("", Blocks.BIRCH_PLANKS, AtbywBlocks.BIRCH_FENCE_DOOR);
        table.put("", Blocks.JUNGLE_PLANKS, AtbywBlocks.JUNGLE_FENCE_DOOR);
        table.put("", Blocks.ACACIA_PLANKS, AtbywBlocks.ACACIA_FENCE_DOOR);
        table.put("", Blocks.DARK_OAK_PLANKS, AtbywBlocks.DARK_OAK_FENCE_DOOR);
        table.put("", Blocks.CRIMSON_PLANKS, AtbywBlocks.CRIMSON_FENCE_DOOR);
        table.put("", Blocks.WARPED_PLANKS, AtbywBlocks.WARPED_FENCE_DOOR);
        table.put("redstone", Items.IRON_INGOT, AtbywBlocks.IRON_FENCE_DOOR);

        for (var entry : table.cellSet()) {
            var group = entry.getRowKey().equals("") ? "fence_doors" : "";
            var pattern = patterns.fenceDoorPattern(entry.getColumnKey());
            registerShapedRecipe("", entry.getRowKey(), group, pattern.getFirst(), pattern.getSecond(), entry.getValue(), 3);
        }
    }

    public static void registerBookshelfToggles() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Items.OAK_PLANKS, AtbywBlocks.OAK_BOOKSHELF_TOGGLE);
        map.put(Items.SPRUCE_PLANKS, AtbywBlocks.SPRUCE_BOOKSHELF_TOGGLE);
        map.put(Items.BIRCH_PLANKS, AtbywBlocks.BIRCH_BOOKSHELF_TOGGLE);
        map.put(Items.JUNGLE_PLANKS, AtbywBlocks.JUNGLE_BOOKSHELF_TOGGLE);
        map.put(Items.ACACIA_PLANKS, AtbywBlocks.ACACIA_BOOKSHELF_TOGGLE);
        map.put(Items.DARK_OAK_PLANKS, AtbywBlocks.DARK_OAK_BOOKSHELF_TOGGLE);
        map.put(Items.CRIMSON_PLANKS, AtbywBlocks.CRIMSON_BOOKSHELF_TOGGLE);
        map.put(Items.WARPED_PLANKS, AtbywBlocks.WARPED_BOOKSHELF_TOGGLE);

        for (var entry : map.entrySet()) {
            var keys = HashMultimap.<Character, Ingredient>create();
            keys.put('P', Ingredient.ofItems(entry.getKey().asItem()));
            keys.put('R', Ingredient.ofItems(Items.REDSTONE));
            keys.put('B', Ingredient.ofItems(Items.BOOK));
            keys.put('C', Ingredient.fromTag(ItemTags.STONE_CRAFTING_MATERIALS));
            registerShapedRecipe("", "redstone", "bookshelf_toggles", new String[] {"PPP", "RBR", "CRC"}, keys, entry.getValue(), 1);
        }
    }

    public static void registerBookshelfPatterns() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Items.SPRUCE_PLANKS, AtbywBlocks.SPRUCE_BOOKSHELF);
        map.put(Items.BIRCH_PLANKS, AtbywBlocks.BIRCH_BOOKSHELF);
        map.put(Items.JUNGLE_PLANKS, AtbywBlocks.JUNGLE_BOOKSHELF);
        map.put(Items.ACACIA_PLANKS, AtbywBlocks.ACACIA_BOOKSHELF);
        map.put(Items.DARK_OAK_PLANKS, AtbywBlocks.DARK_OAK_BOOKSHELF);
        map.put(Items.CRIMSON_PLANKS, AtbywBlocks.CRIMSON_BOOKSHELF);
        map.put(Items.WARPED_PLANKS, AtbywBlocks.WARPED_BOOKSHELF);

        for (var entry : map.entrySet()) {
            var pattern = patterns.bookshelfPattern(entry.getKey(), Items.BOOK);
            registerShapedRecipe("", "", "bookshelves", pattern.getFirst(), pattern.getSecond(), entry.getValue(), 1);
        }
    }

    public static void registerLadderPatterns() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Items.SPRUCE_PLANKS, AtbywBlocks.SPRUCE_LADDER);
        map.put(Items.BIRCH_PLANKS, AtbywBlocks.BIRCH_LADDER);
        map.put(Items.JUNGLE_PLANKS, AtbywBlocks.JUNGLE_LADDER);
        map.put(Items.ACACIA_PLANKS, AtbywBlocks.ACACIA_LADDER);
        map.put(Items.DARK_OAK_PLANKS, AtbywBlocks.DARK_OAK_LADDER);
        map.put(Items.CRIMSON_PLANKS, AtbywBlocks.CRIMSON_LADDER);
        map.put(Items.WARPED_PLANKS, AtbywBlocks.WARPED_LADDER);

        for (var entry : map.entrySet()) {
            var pattern = patterns.ladderPattern(entry.getKey());
            registerShapedRecipe("", "ladders", "ladders", pattern.getFirst(), pattern.getSecond(), entry.getValue(), 6);
        }
        //Bamboo Ladder
        var keys = HashMultimap.<Character, Ingredient>create();
        keys.put('B', Ingredient.ofItems(Items.BAMBOO));
        keys.put('S', Ingredient.ofItems(AtbywItems.BAMBOO_STICK));
        registerShapedRecipe("", "ladders", new String[] {"B B", "BSB", "B B"}, keys, AtbywBlocks.BAMBOO_LADDER, 3);
    }

    public static void registerTorchPatterns() {
        //Map<Pair<Input "coal", Input "stick">, Output>
        var map = new HashMap<Pair<ItemConvertible, ItemConvertible>, ItemConvertible>();
        map.put(new Pair<>(Items.CARVED_PUMPKIN, Items.SOUL_TORCH), AtbywBlocks.SOUL_JACK_O_LANTERN);
        map.put(new Pair<>(Items.CARVED_PUMPKIN, Items.REDSTONE_TORCH), AtbywBlocks.REDSTONE_JACK_O_LANTERN);

        for (var entry : map.entrySet()) {
            var pattern = patterns.torchPattern(entry.getKey().getFirst(), entry.getKey().getSecond());
            registerShapedRecipe("", "", pattern.getFirst(), pattern.getSecond(), entry.getValue(), 1);
        }
    }

    public static void registerMisc() {
        var keys = HashMultimap.<Character, Ingredient>create();

        //Timer Repeater using Clock
        keys.put('C', Ingredient.ofItems(Items.CLOCK));
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('#', Ingredient.ofItems(Items.REPEATER));
        registerShapedRecipe("from_clock", "redstone", "timer_repeater", new String[] {" C ", "R#R", " R "}, keys, AtbywBlocks.TIMER_REPEATER, 1);

        //Timer Repeater Manual
        keys.clear();
        keys.put('G', Ingredient.ofItems(Items.GOLD_INGOT));
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('#', Ingredient.ofItems(Items.REPEATER));
        registerShapedRecipe("", "redstone", "timer_repeater", new String[] {"GRG", "R#R", "GRG"}, keys, AtbywBlocks.TIMER_REPEATER, 1);

        //Redstone Cross Path
        keys.clear();
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('S', Ingredient.ofItems(Blocks.STONE));
        registerShapedRecipe("", "redstone", new String[] {"RRR", "SSS"}, keys, AtbywBlocks.REDSTONE_CROSS_PATH, 1);

        //Marge Chain Link
        var pattern = patterns.hollowStarPattern(Items.IRON_INGOT);
        registerShapedRecipe("", "", pattern.getFirst(), pattern.getSecond(), AtbywItems.LARGE_CHAIN_LINK, 2);

    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        RecipeSmeltingRegistry.inject(map);
        RecipeStonecuttingRegistry.inject(map);
    }
}