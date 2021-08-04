package net.azagwen.atbyw.datagen.recipe.registry;

import com.google.common.collect.*;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.datagen.RecipeDatagen;
import net.azagwen.atbyw.datagen.recipe.util.RecipeData;
import net.azagwen.atbyw.datagen.recipe.util.RecipePatterns;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.AtbywUtils;
import net.azagwen.atbyw.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.DyeColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class RecipeRegistry {
    public static final Logger LOGGER = LogManager.getLogger("Atbyw Recipes");
    public static final RecipePatterns patterns = new RecipePatterns();
    public static final List<Recipe<?>> dyingRecipes = Lists.newArrayList();

    public static Recipe<?> SHULKER_ESSENCE = RecipeDatagen.shapelessRecipe(AtbywMain.id("shulker_essence"), "essence", Lists.newArrayList(Items.SHULKER_SHELL, Items.SHULKER_SHELL, Items.GLASS_BOTTLE), AtbywItems.SHULKER_ESSENCE, 1);
    public static Recipe<?> CHICKEN_ESSENCE = RecipeDatagen.shapelessRecipe(AtbywMain.id("chicken_essence"), "essence", Lists.newArrayList(Items.CHICKEN, Items.FEATHER, Items.GLASS_BOTTLE), AtbywItems.CHICKEN_ESSENCE, 1);
    public static Recipe<?> RABBIT_ESSENCE = RecipeDatagen.shapelessRecipe(AtbywMain.id("rabbit_essence"), "essence", Lists.newArrayList(Items.RABBIT, Items.RABBIT_HIDE, Items.GLASS_BOTTLE), AtbywItems.RABBIT_ESSENCE, 1);
    public static Recipe<?> COD_ESSENCE = RecipeDatagen.shapelessRecipe(AtbywMain.id("cod_essence"), "essence", Lists.newArrayList(Items.COD, Items.BONE_MEAL, Items.GLASS_BOTTLE), AtbywItems.COD_ESSENCE, 1);
    public static Recipe<?> SALMON_ESSENCE = RecipeDatagen.shapelessRecipe(AtbywMain.id("salmon_essence"), "essence", Lists.newArrayList(Items.SALMON, Items.BONE_MEAL, Items.GLASS_BOTTLE), AtbywItems.SALMON_ESSENCE, 1);
    public static Recipe<?> PUFFER_FISH_ESSENCE = RecipeDatagen.shapelessRecipe(AtbywMain.id("puffer_fish_essence"), "essence", Lists.newArrayList(Items.PUFFERFISH, Items.BONE_MEAL, Items.GLASS_BOTTLE), AtbywItems.PUFFER_FISH_ESSENCE, 1);
    public static Recipe<?> SLIME_ESSENCE = RecipeDatagen.shapelessRecipe(AtbywMain.id("slime_essence"), "essence", Lists.newArrayList(Items.SLIME_BALL, Items.GLASS_BOTTLE), AtbywItems.SLIME_ESSENCE, 1);
    public static Recipe<?> MAGMA_CUBE_ESSENCE = RecipeDatagen.shapelessRecipe(AtbywMain.id("magma_cube_essence"), "essence", Lists.newArrayList(Items.MAGMA_CREAM, Items.GLASS_BOTTLE), AtbywItems.MAGMA_CUBE_ESSENCE, 1);

    public static Recipe<?> SHULKER_STATUE = RecipeDatagen.shapelessRecipe(AtbywMain.id("shulker_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.SHULKER_ESSENCE), StatueRegistry.SHULKER_STATUE, 1);
    public static Recipe<?> CHICKEN_STATUE = RecipeDatagen.shapelessRecipe(AtbywMain.id("chicken_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.CHICKEN_ESSENCE), StatueRegistry.CHICKEN_STATUE, 1);
    public static Recipe<?> RABBIT_STATUE = RecipeDatagen.shapelessRecipe(AtbywMain.id("rabbit_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.RABBIT_ESSENCE), StatueRegistry.RABBIT_STATUE, 1);
    public static Recipe<?> COD_STATUE = RecipeDatagen.shapelessRecipe(AtbywMain.id("cod_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.COD_ESSENCE), StatueRegistry.COD_STATUE, 1);
    public static Recipe<?> SALMON_STATUE = RecipeDatagen.shapelessRecipe(AtbywMain.id("salmon_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.SALMON_ESSENCE), StatueRegistry.SALMON_STATUE, 1);
    public static Recipe<?> PUFFER_FISH_STATUE = RecipeDatagen.shapelessRecipe(AtbywMain.id("puffer_fish_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.PUFFER_FISH_ESSENCE), StatueRegistry.PUFFER_FISH_STATUE, 1);
    public static Recipe<?> SLIME_STATUE = RecipeDatagen.shapelessRecipe(AtbywMain.id("slime_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.SLIME_ESSENCE), StatueRegistry.SLIME_STATUE, 1);
    public static Recipe<?> MAGMA_CUBE_STATUE = RecipeDatagen.shapelessRecipe(AtbywMain.id("magma_cube_statue"), "statues", Lists.newArrayList(Blocks.STONE, AtbywItems.MAGMA_CUBE_ESSENCE), StatueRegistry.MAGMA_CUBE_STATUE, 1);

    public static Recipe<?> registerShapedRecipe(RecipeData recipeData, String[] pattern, Multimap<Character, Ingredient> keys) {
        var recipe = (Recipe<?>) null;
        var suffix = recipeData.suffix();
        var category = recipeData.category();
        var group = recipeData.group();
        var result = recipeData.result();
        var count = recipeData.count();

        for (var line : pattern) {
            if (line.contains(" ")) {
                keys.put(' ', Ingredient.EMPTY);
            }
        }

        var resultId = AtbywUtils.getItemID(result.asItem());
        var recipeId = AtbywMain.id(resultId.getPath() + (suffix.equals("") ? "" : ("_" + suffix)));
        recipe = RecipeDatagen.shapedRecipe(recipeId, group, pattern, keys, result.asItem(), count);
        RecipeDatagen.registerRecipe(recipe, category);
        return recipe;
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
        RecipeDatagen.registerRecipe(SHULKER_ESSENCE, "");
        RecipeDatagen.registerRecipe(CHICKEN_ESSENCE, "");
        RecipeDatagen.registerRecipe(RABBIT_ESSENCE, "");
        RecipeDatagen.registerRecipe(COD_ESSENCE, "");
        RecipeDatagen.registerRecipe(SALMON_ESSENCE, "");
        RecipeDatagen.registerRecipe(PUFFER_FISH_ESSENCE, "");
        RecipeDatagen.registerRecipe(MAGMA_CUBE_ESSENCE, "");
        RecipeDatagen.registerRecipe(SLIME_ESSENCE, "");

        RecipeDatagen.registerRecipe(SHULKER_STATUE, "statues");
        RecipeDatagen.registerRecipe(CHICKEN_STATUE, "statues");
        RecipeDatagen.registerRecipe(RABBIT_STATUE, "statues");
        RecipeDatagen.registerRecipe(COD_STATUE, "statues");
        RecipeDatagen.registerRecipe(SALMON_STATUE, "statues");
        RecipeDatagen.registerRecipe(PUFFER_FISH_STATUE, "statues");
        RecipeDatagen.registerRecipe(SLIME_STATUE, "statues");
        RecipeDatagen.registerRecipe(MAGMA_CUBE_STATUE, "statues");

        //Other Types
        StonecuttingRecipeRegistry.init();
        FurnaceRecipeRegistry.init();

        LOGGER.info("ATBYW Recipes Inintiliazed");
    }

    private static void registerStairPatterns() {
        //Map<RecipeData, Input
        var count = 4;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(new RecipeData(AtbywBlocks.DIRT_STAIRS, count), Blocks.DIRT);
        map.put(new RecipeData(AtbywBlocks.GRASS_BLOCK_STAIRS, count), Blocks.GRASS_BLOCK);
        map.put(new RecipeData(AtbywBlocks.MYCELIUM_STAIRS, count), Blocks.MYCELIUM);
        map.put(new RecipeData(AtbywBlocks.COARSE_DIRT_STAIRS, count), Blocks.COARSE_DIRT);
        map.put(new RecipeData(AtbywBlocks.PODZOL_STAIRS, count), Blocks.PODZOL);
        map.put(new RecipeData(AtbywBlocks.NETHERRACK_STAIRS, count), Blocks.NETHERRACK);
        map.put(new RecipeData(AtbywBlocks.CRIMSON_NYLIUM_STAIRS, count), Blocks.CRIMSON_NYLIUM);
        map.put(new RecipeData(AtbywBlocks.WARPED_NYLIUM_STAIRS, count), Blocks.WARPED_NYLIUM);
        map.put(new RecipeData(AtbywBlocks.GRANITE_TILES_STAIRS, count), Blocks.GRANITE);
        map.put(new RecipeData(AtbywBlocks.DIORITE_BRICKS_STAIRS, count), Blocks.DIORITE);
        map.put(new RecipeData(AtbywBlocks.ANDESITE_BRICKS_STAIRS, count), Blocks.ANDESITE);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.TERRACOTTA_STAIRS, count), Blocks.TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.WHITE_TERRACOTTA_STAIRS, count), Blocks.WHITE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.ORANGE_TERRACOTTA_STAIRS, count), Blocks.ORANGE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.MAGENTA_TERRACOTTA_STAIRS, count), Blocks.MAGENTA_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.YELLOW_TERRACOTTA_STAIRS, count), Blocks.YELLOW_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.LIME_TERRACOTTA_STAIRS, count), Blocks.LIME_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.PINK_TERRACOTTA_STAIRS, count), Blocks.PINK_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.GRAY_TERRACOTTA_STAIRS, count), Blocks.GRAY_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.CYAN_TERRACOTTA_STAIRS, count), Blocks.CYAN_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.PURPLE_TERRACOTTA_STAIRS, count), Blocks.PURPLE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.BLUE_TERRACOTTA_STAIRS, count), Blocks.BLUE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.BROWN_TERRACOTTA_STAIRS, count), Blocks.BROWN_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.GREEN_TERRACOTTA_STAIRS, count), Blocks.GREEN_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.RED_TERRACOTTA_STAIRS, count), Blocks.RED_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", AtbywBlocks.BLACK_TERRACOTTA_STAIRS, count), Blocks.BLACK_TERRACOTTA);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.WHITE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.ORANGE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.YELLOW_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.LIME_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.LIME_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.PINK_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.PINK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.CYAN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.PURPLE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.BROWN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.GREEN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.RED_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.RED_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.BLACK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.WHITE_CONCRETE_STAIRS, count), Blocks.WHITE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.ORANGE_CONCRETE_STAIRS, count), Blocks.ORANGE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.MAGENTA_CONCRETE_STAIRS, count), Blocks.MAGENTA_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.LIGHT_BLUE_CONCRETE_STAIRS, count), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.YELLOW_CONCRETE_STAIRS, count), Blocks.YELLOW_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.LIME_CONCRETE_STAIRS, count), Blocks.LIME_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.PINK_CONCRETE_STAIRS, count), Blocks.PINK_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.GRAY_CONCRETE_STAIRS, count), Blocks.GRAY_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.LIGHT_GRAY_CONCRETE_STAIRS, count), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.CYAN_CONCRETE_STAIRS, count), Blocks.CYAN_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.PURPLE_CONCRETE_STAIRS, count), Blocks.PURPLE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.BLUE_CONCRETE_STAIRS, count), Blocks.BLUE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.BROWN_CONCRETE_STAIRS, count), Blocks.BROWN_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.GREEN_CONCRETE_STAIRS, count), Blocks.GREEN_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.RED_CONCRETE_STAIRS, count), Blocks.RED_CONCRETE);
        map.put(new RecipeData("concrete_stairs", AtbywBlocks.BLACK_CONCRETE_STAIRS, count), Blocks.BLACK_CONCRETE);
        map.put(new RecipeData(AtbywBlocks.PURPUR_TILES_STAIRS, count), AtbywBlocks.PURPUR_TILES);
        map.put(new RecipeData(AtbywBlocks.CUT_PURPUR_STAIRS, count), AtbywBlocks.CUT_PURPUR_BLOCK);
        map.put(new RecipeData(AtbywBlocks.SMOOTH_PURPUR_STAIRS, count), AtbywBlocks.SMOOTH_PURPUR_BLOCK);
        map.put(new RecipeData(AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS, count), AtbywBlocks.COMPACTED_SNOW_BLOCK);
        map.put(new RecipeData(AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS, count), AtbywBlocks.COMPACTED_SNOW_BRICKS);
        map.put(new RecipeData(AtbywBlocks.PACKED_ICE_STAIRS, count), Blocks.PACKED_ICE);
        map.put(new RecipeData(AtbywBlocks.BLUE_ICE_STAIRS, count), Blocks.BLUE_ICE);
        map.put(new RecipeData(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS, count), AtbywBlocks.PACKED_ICE_BRICKS);
        map.put(new RecipeData(AtbywBlocks.BLUE_ICE_BRICKS_STAIRS, count), AtbywBlocks.BLUE_ICE_BRICKS);
        map.put(new RecipeData(AtbywBlocks.SAND_STAIRS, count), Blocks.SAND);
        map.put(new RecipeData(AtbywBlocks.RED_SAND_STAIRS, count), Blocks.RED_SAND);
        map.put(new RecipeData(AtbywBlocks.GRAVEL_STAIRS, count), Blocks.GRAVEL);
        map.put(new RecipeData(AtbywBlocks.ROOTED_DIRT_STAIRS, count), Blocks.ROOTED_DIRT);
        map.put(new RecipeData("log_stairs", AtbywBlocks.OAK_LOG_STAIRS, count), Blocks.OAK_LOG);
        map.put(new RecipeData("log_stairs", AtbywBlocks.SPRUCE_LOG_STAIRS, count), Blocks.SPRUCE_LOG);
        map.put(new RecipeData("log_stairs", AtbywBlocks.BIRCH_LOG_STAIRS, count), Blocks.BIRCH_LOG);
        map.put(new RecipeData("log_stairs", AtbywBlocks.JUNGLE_LOG_STAIRS, count), Blocks.JUNGLE_LOG);
        map.put(new RecipeData("log_stairs", AtbywBlocks.ACACIA_LOG_STAIRS, count), Blocks.ACACIA_LOG);
        map.put(new RecipeData("log_stairs", AtbywBlocks.DARK_OAK_LOG_STAIRS, count), Blocks.DARK_OAK_LOG);
        map.put(new RecipeData("log_stairs", AtbywBlocks.CRIMSON_STEM_STAIRS, count), Blocks.CRIMSON_STEM);
        map.put(new RecipeData("log_stairs", AtbywBlocks.WARPED_STEM_STAIRS, count), Blocks.WARPED_STEM);
        map.put(new RecipeData("stripped_log_stairs", AtbywBlocks.STRIPPED_OAK_LOG_STAIRS, count), Blocks.STRIPPED_OAK_LOG);
        map.put(new RecipeData("stripped_log_stairs", AtbywBlocks.STRIPPED_SPRUCE_LOG_STAIRS, count), Blocks.STRIPPED_SPRUCE_LOG);
        map.put(new RecipeData("stripped_log_stairs", AtbywBlocks.STRIPPED_BIRCH_LOG_STAIRS, count), Blocks.STRIPPED_BIRCH_LOG);
        map.put(new RecipeData("stripped_log_stairs", AtbywBlocks.STRIPPED_JUNGLE_LOG_STAIRS, count), Blocks.STRIPPED_JUNGLE_LOG);
        map.put(new RecipeData("stripped_log_stairs", AtbywBlocks.STRIPPED_ACACIA_LOG_STAIRS, count), Blocks.STRIPPED_ACACIA_LOG);
        map.put(new RecipeData("stripped_log_stairs", AtbywBlocks.STRIPPED_DARK_OAK_LOG_STAIRS, count), Blocks.STRIPPED_DARK_OAK_LOG);
        map.put(new RecipeData("stripped_log_stairs", AtbywBlocks.STRIPPED_CRIMSON_STEM_STAIRS, count), Blocks.STRIPPED_CRIMSON_STEM);
        map.put(new RecipeData("stripped_log_stairs", AtbywBlocks.STRIPPED_WARPED_STEM_STAIRS, count), Blocks.STRIPPED_WARPED_STEM);

        for (var entry : map.entrySet()) {
            var pattern = patterns.stairsPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerSlabPatterns() {
        //Map<RecipeData, Input>
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(new RecipeData("slab", "", AtbywBlocks.DIRT_SLAB, 6), Blocks.DIRT);
        map.put(new RecipeData("slab", "", AtbywBlocks.GRASS_BLOCK_SLAB, 6), Blocks.GRASS_BLOCK);
        map.put(new RecipeData("slab", "", AtbywBlocks.MYCELIUM_SLAB, 6), Blocks.MYCELIUM);
        map.put(new RecipeData("slab", "", AtbywBlocks.COARSE_DIRT_SLAB, 6), Blocks.COARSE_DIRT);
        map.put(new RecipeData("slab", "", AtbywBlocks.PODZOL_SLAB, 6), Blocks.PODZOL);
        map.put(new RecipeData("slab", "", AtbywBlocks.NETHERRACK_SLAB, 6), Blocks.NETHERRACK);
        map.put(new RecipeData("slab", "", AtbywBlocks.CRIMSON_NYLIUM_SLAB, 6), Blocks.CRIMSON_NYLIUM);
        map.put(new RecipeData("slab", "", AtbywBlocks.WARPED_NYLIUM_SLAB, 6), Blocks.WARPED_NYLIUM);
        map.put(new RecipeData("slab", "", AtbywBlocks.GRANITE_TILES_SLAB, 6), Blocks.GRANITE);
        map.put(new RecipeData("slab", "", AtbywBlocks.DIORITE_BRICKS_SLAB, 6), Blocks.DIORITE);
        map.put(new RecipeData("slab", "", AtbywBlocks.ANDESITE_BRICKS_SLAB, 6), Blocks.ANDESITE);
        map.put(new RecipeData("slab", "", AtbywBlocks.TERRACOTTA_SLAB, 6), Blocks.TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.WHITE_TERRACOTTA_SLAB, 6), Blocks.WHITE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.ORANGE_TERRACOTTA_SLAB, 6), Blocks.ORANGE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.MAGENTA_TERRACOTTA_SLAB, 6), Blocks.MAGENTA_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, 6), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.YELLOW_TERRACOTTA_SLAB, 6), Blocks.YELLOW_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.LIME_TERRACOTTA_SLAB, 6), Blocks.LIME_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.PINK_TERRACOTTA_SLAB, 6), Blocks.PINK_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.GRAY_TERRACOTTA_SLAB, 6), Blocks.GRAY_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, 6), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.CYAN_TERRACOTTA_SLAB, 6), Blocks.CYAN_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.PURPLE_TERRACOTTA_SLAB, 6), Blocks.PURPLE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.BLUE_TERRACOTTA_SLAB, 6), Blocks.BLUE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.BROWN_TERRACOTTA_SLAB, 6), Blocks.BROWN_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.GREEN_TERRACOTTA_SLAB, 6), Blocks.GREEN_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.RED_TERRACOTTA_SLAB, 6), Blocks.RED_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", AtbywBlocks.BLACK_TERRACOTTA_SLAB, 6), Blocks.BLACK_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.WHITE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.ORANGE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.YELLOW_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.LIME_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.LIME_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.PINK_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.PINK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.CYAN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.PURPLE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.BROWN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.GREEN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.RED_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.RED_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_SLAB, 6), AtbywBlocks.BLACK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.WHITE_CONCRETE_SLAB, 6), Blocks.WHITE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.ORANGE_CONCRETE_SLAB, 6), Blocks.ORANGE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.MAGENTA_CONCRETE_SLAB, 6), Blocks.MAGENTA_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.LIGHT_BLUE_CONCRETE_SLAB, 6), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.YELLOW_CONCRETE_SLAB, 6), Blocks.YELLOW_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.LIME_CONCRETE_SLAB, 6), Blocks.LIME_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.PINK_CONCRETE_SLAB, 6), Blocks.PINK_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.GRAY_CONCRETE_SLAB, 6), Blocks.GRAY_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.LIGHT_GRAY_CONCRETE_SLAB, 6), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.CYAN_CONCRETE_SLAB, 6), Blocks.CYAN_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.PURPLE_CONCRETE_SLAB, 6), Blocks.PURPLE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.BLUE_CONCRETE_SLAB, 6), Blocks.BLUE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.BROWN_CONCRETE_SLAB, 6), Blocks.BROWN_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.GREEN_CONCRETE_SLAB, 6), Blocks.GREEN_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.RED_CONCRETE_SLAB, 6), Blocks.RED_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", AtbywBlocks.BLACK_CONCRETE_SLAB, 6), Blocks.BLACK_CONCRETE);
        map.put(new RecipeData("slab", "", AtbywBlocks.PURPUR_TILES_SLAB, 6), AtbywBlocks.PURPUR_TILES);
        map.put(new RecipeData("slab", "", AtbywBlocks.CUT_PURPUR_SLAB, 6), AtbywBlocks.CUT_PURPUR_BLOCK);
        map.put(new RecipeData("slab", "", AtbywBlocks.SMOOTH_PURPUR_SLAB, 6), AtbywBlocks.SMOOTH_PURPUR_BLOCK);
        map.put(new RecipeData("slab", "", AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB, 6), AtbywBlocks.COMPACTED_SNOW_BLOCK);
        map.put(new RecipeData("slab", "", AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB, 6), AtbywBlocks.COMPACTED_SNOW_BRICKS);
        map.put(new RecipeData("slab", "", AtbywBlocks.PACKED_ICE_SLAB, 6), Blocks.PACKED_ICE);
        map.put(new RecipeData("slab", "", AtbywBlocks.BLUE_ICE_SLAB, 6), Blocks.BLUE_ICE);
        map.put(new RecipeData("slab", "", AtbywBlocks.PACKED_ICE_BRICKS_SLAB, 6), AtbywBlocks.PACKED_ICE_BRICKS);
        map.put(new RecipeData("slab", "", AtbywBlocks.BLUE_ICE_BRICKS_SLAB, 6), AtbywBlocks.BLUE_ICE_BRICKS);
        map.put(new RecipeData("slab", "", AtbywBlocks.SAND_SLAB, 6), Blocks.SAND);
        map.put(new RecipeData("slab", "", AtbywBlocks.RED_SAND_SLAB, 6), Blocks.RED_SAND);
        map.put(new RecipeData("slab", "", AtbywBlocks.GRAVEL_SLAB, 6), Blocks.GRAVEL);
        map.put(new RecipeData("slab", "", AtbywBlocks.ROOTED_DIRT_SLAB, 6), Blocks.ROOTED_DIRT);
        map.put(new RecipeData("slab", "log_slabs", AtbywBlocks.OAK_LOG_SLAB, 6), Blocks.OAK_LOG);
        map.put(new RecipeData("slab", "log_slabs", AtbywBlocks.SPRUCE_LOG_SLAB, 6), Blocks.SPRUCE_LOG);
        map.put(new RecipeData("slab", "log_slabs", AtbywBlocks.BIRCH_LOG_SLAB, 6), Blocks.BIRCH_LOG);
        map.put(new RecipeData("slab", "log_slabs", AtbywBlocks.JUNGLE_LOG_SLAB, 6), Blocks.JUNGLE_LOG);
        map.put(new RecipeData("slab", "log_slabs", AtbywBlocks.ACACIA_LOG_SLAB, 6), Blocks.ACACIA_LOG);
        map.put(new RecipeData("slab", "log_slabs", AtbywBlocks.DARK_OAK_LOG_SLAB, 6), Blocks.DARK_OAK_LOG);
        map.put(new RecipeData("slab", "log_slabs", AtbywBlocks.CRIMSON_STEM_SLAB, 6), Blocks.CRIMSON_STEM);
        map.put(new RecipeData("slab", "log_slabs", AtbywBlocks.WARPED_STEM_SLAB, 6), Blocks.WARPED_STEM);
        map.put(new RecipeData("slab", "stripped_log_slabs", AtbywBlocks.STRIPPED_OAK_LOG_SLAB, 6), Blocks.STRIPPED_OAK_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", AtbywBlocks.STRIPPED_SPRUCE_LOG_SLAB, 6), Blocks.STRIPPED_SPRUCE_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", AtbywBlocks.STRIPPED_BIRCH_LOG_SLAB, 6), Blocks.STRIPPED_BIRCH_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", AtbywBlocks.STRIPPED_JUNGLE_LOG_SLAB, 6), Blocks.STRIPPED_JUNGLE_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", AtbywBlocks.STRIPPED_ACACIA_LOG_SLAB, 6), Blocks.STRIPPED_ACACIA_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", AtbywBlocks.STRIPPED_DARK_OAK_LOG_SLAB, 6), Blocks.STRIPPED_DARK_OAK_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", AtbywBlocks.STRIPPED_CRIMSON_STEM_SLAB, 6), Blocks.STRIPPED_CRIMSON_STEM);
        map.put(new RecipeData("slab", "stripped_log_slabs", AtbywBlocks.STRIPPED_WARPED_STEM_SLAB, 6), Blocks.STRIPPED_WARPED_STEM);
        map.put(new RecipeData(AtbywBlocks.COMPACTED_SNOW, 6), AtbywBlocks.COMPACTED_SNOW_BLOCK);

        for (var entry : map.entrySet()) {
            var pattern = patterns.slabPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerBricksPatterns() {
        //Map<RecipeData, Input>
        var brickCount = 4;
        var glassCount = 1;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(new RecipeData("from_basalt", "bricks", "basalt_bricks", AtbywBlocks.BASALT_BRICKS, brickCount), Blocks.BASALT);
        map.put(new RecipeData("from_polished_basalt", "bricks", "basalt_bricks", AtbywBlocks.BASALT_BRICKS, brickCount), Blocks.POLISHED_BASALT);
        map.put(new RecipeData("from_smooth_basalt", "bricks", "basalt_bricks", AtbywBlocks.BASALT_BRICKS, brickCount), Blocks.SMOOTH_BASALT);
        map.put(new RecipeData("bricks", "", AtbywBlocks.GRANITE_TILES, brickCount), Blocks.POLISHED_GRANITE);
        map.put(new RecipeData("bricks", "", AtbywBlocks.DIORITE_BRICKS, brickCount), Blocks.POLISHED_DIORITE);
        map.put(new RecipeData("bricks", "", AtbywBlocks.ANDESITE_BRICKS, brickCount), Blocks.POLISHED_ANDESITE);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.TERRACOTTA_BRICKS, brickCount), Blocks.TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.WHITE_TERRACOTTA_BRICKS, brickCount), Blocks.WHITE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, brickCount), Blocks.ORANGE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, brickCount), Blocks.MAGENTA_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, brickCount), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, brickCount), Blocks.YELLOW_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.LIME_TERRACOTTA_BRICKS, brickCount), Blocks.LIME_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.PINK_TERRACOTTA_BRICKS, brickCount), Blocks.PINK_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.GRAY_TERRACOTTA_BRICKS, brickCount), Blocks.GRAY_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, brickCount), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.CYAN_TERRACOTTA_BRICKS, brickCount), Blocks.CYAN_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, brickCount), Blocks.PURPLE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.BLUE_TERRACOTTA_BRICKS, brickCount), Blocks.BLUE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.BROWN_TERRACOTTA_BRICKS, brickCount), Blocks.BROWN_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.GREEN_TERRACOTTA_BRICKS, brickCount), Blocks.GREEN_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.RED_TERRACOTTA_BRICKS, brickCount), Blocks.RED_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", AtbywBlocks.BLACK_TERRACOTTA_BRICKS, brickCount), Blocks.BLACK_TERRACOTTA);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.WHITE_CINDER_BLOCKS, brickCount), Blocks.WHITE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.ORANGE_CINDER_BLOCKS, brickCount), Blocks.ORANGE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.MAGENTA_CINDER_BLOCKS, brickCount), Blocks.MAGENTA_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS, brickCount), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.YELLOW_CINDER_BLOCKS, brickCount), Blocks.YELLOW_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.LIME_CINDER_BLOCKS, brickCount), Blocks.LIME_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.PINK_CINDER_BLOCKS, brickCount), Blocks.PINK_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.GRAY_CINDER_BLOCKS, brickCount), Blocks.GRAY_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS, brickCount), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.CYAN_CINDER_BLOCKS, brickCount), Blocks.CYAN_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.PURPLE_CINDER_BLOCKS, brickCount), Blocks.PURPLE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.BLUE_CINDER_BLOCKS, brickCount), Blocks.BLUE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.BROWN_CINDER_BLOCKS, brickCount), Blocks.BROWN_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.GREEN_CINDER_BLOCKS, brickCount), Blocks.GREEN_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.RED_CINDER_BLOCKS, brickCount), Blocks.RED_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", AtbywBlocks.BLACK_CINDER_BLOCKS, brickCount), Blocks.BLACK_CONCRETE);
        map.put(new RecipeData("bricks", "", AtbywBlocks.PURPUR_TILES, brickCount), Blocks.PURPUR_PILLAR);
        map.put(new RecipeData("bricks", "", AtbywBlocks.CUT_PURPUR_BLOCK, brickCount), Blocks.PURPUR_BLOCK);
        map.put(new RecipeData("bricks", "", AtbywBlocks.COMPACTED_SNOW_BLOCK, brickCount), Blocks.SNOW_BLOCK);
        map.put(new RecipeData("bricks", "", AtbywBlocks.COMPACTED_SNOW_BRICKS, brickCount), AtbywBlocks.COMPACTED_SNOW_BLOCK);
        map.put(new RecipeData("bricks", "", AtbywBlocks.PACKED_ICE_BRICKS, brickCount), Blocks.PACKED_ICE);
        map.put(new RecipeData("bricks", "", AtbywBlocks.BLUE_ICE_BRICKS, brickCount), Blocks.BLUE_ICE);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.SHATTERED_GLASS, glassCount), AtbywItems.GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.WHITE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.WHITE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.ORANGE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.ORANGE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.MAGENTA_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.MAGENTA_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.LIGHT_BLUE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.LIGHT_BLUE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.YELLOW_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.YELLOW_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.LIME_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.LIME_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.PINK_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.PINK_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.GRAY_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.GRAY_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.LIGHT_GRAY_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.LIGHT_GRAY_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.CYAN_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.CYAN_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.PURPLE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.PURPLE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.BLUE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.BLUE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.BROWN_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.BROWN_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.GREEN_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.GREEN_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.RED_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.RED_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", AtbywBlocks.BLACK_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.BLACK_STAINED_GLASS_SHARD);

        for (var entry : map.entrySet()) {
            var pattern = patterns.bricksPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerWallPatterns() {
        //Map<RecipeData, Input>
        var count = 6;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.WHITE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.ORANGE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.YELLOW_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.LIME_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.LIME_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.PINK_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.PINK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.CYAN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.PURPLE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.BROWN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.GREEN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.RED_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.RED_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.BLACK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.WHITE_CINDER_BLOCKS_WALL, count), AtbywBlocks.WHITE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.ORANGE_CINDER_BLOCKS_WALL, count), AtbywBlocks.ORANGE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.MAGENTA_CINDER_BLOCKS_WALL, count), AtbywBlocks.MAGENTA_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS_WALL, count), AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.YELLOW_CINDER_BLOCKS_WALL, count), AtbywBlocks.YELLOW_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.LIME_CINDER_BLOCKS_WALL, count), AtbywBlocks.LIME_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.PINK_CINDER_BLOCKS_WALL, count), AtbywBlocks.PINK_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.GRAY_CINDER_BLOCKS_WALL, count), AtbywBlocks.GRAY_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS_WALL, count), AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.CYAN_CINDER_BLOCKS_WALL, count), AtbywBlocks.CYAN_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.PURPLE_CINDER_BLOCKS_WALL, count), AtbywBlocks.PURPLE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.BLUE_CINDER_BLOCKS_WALL, count), AtbywBlocks.BLUE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.BROWN_CINDER_BLOCKS_WALL, count), AtbywBlocks.BROWN_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.GREEN_CINDER_BLOCKS_WALL, count), AtbywBlocks.GREEN_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.RED_CINDER_BLOCKS_WALL, count), AtbywBlocks.RED_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", AtbywBlocks.BLACK_CINDER_BLOCKS_WALL, count), AtbywBlocks.BLACK_CINDER_BLOCKS);

        for (var entry : map.entrySet()) {
            var pattern = patterns.bricksPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static RecipeData dyeingRecipeData(String group, ItemConvertible result, int count) {
        return new RecipeData("dyeing", "dyeing", group, result, count);
    }

    private static void registerDyingPatterns() {
        //Map<RecipeData, Input>
        var count = 8;
        var dyeingMap = Maps.<RecipeData, ItemConvertible>newHashMap();
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.WHITE_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.ORANGE_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.MAGENTA_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.YELLOW_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.LIME_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.PINK_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.GRAY_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.CYAN_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.PURPLE_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.BLUE_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.BROWN_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.GREEN_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.RED_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", AtbywBlocks.BLACK_TERRACOTTA_STAIRS, count), AtbywBlocks.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.WHITE_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.ORANGE_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.MAGENTA_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.YELLOW_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.LIME_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.PINK_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.GRAY_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.CYAN_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.PURPLE_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.BLUE_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.BROWN_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.GREEN_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.RED_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", AtbywBlocks.BLACK_TERRACOTTA_SLAB, count), AtbywBlocks.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.WHITE_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.LIME_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.PINK_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.GRAY_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.CYAN_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.BLUE_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.BROWN_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.GREEN_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.RED_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", AtbywBlocks.BLACK_TERRACOTTA_BRICKS, count), AtbywBlocks.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.LIME_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.PINK_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.RED_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS, count), AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.LIME_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.PINK_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.RED_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_SLAB, count), AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.WHITE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.LIME_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.PINK_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.GRAY_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.CYAN_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.BLUE_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.BROWN_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.GREEN_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.RED_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", AtbywBlocks.BLACK_TERRACOTTA_BRICKS_WALL, count), AtbywBlocks.TERRACOTTA_BRICKS_WALL);

        //Map<Pair<Input outer ring, Input middle>, Output>
        var map = Maps.<RecipeData, Pair<ItemConvertible, ItemConvertible>>newHashMap();
        map.put(new RecipeData(AtbywBlocks.REDSTONE_LANTERN, 1), new Pair<>(Items.IRON_NUGGET, Items.REDSTONE_TORCH));

        for (var color : DyeColor.values()) {
            for (var entry : dyeingMap.entrySet()) {
                var recipeData = entry.getKey();
                if (AtbywUtils.getItemID(recipeData.result().asItem()).getPath().contains(color.asString())) {
                    var pattern = patterns.dyingRingPattern(entry.getValue(), DyeItem.byColor(color));
                    registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
                }
            }
        }

        for (var entry : map.entrySet()) {
            var pattern = patterns.dyingRingPattern(entry.getValue().getFirst(), entry.getValue().getSecond());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerColumnPatterns() {
        //Map<RecipeData, Input>
        var columnCount = 3;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(new RecipeData("column", "column", AtbywBlocks.GRANITE_COLUMN, columnCount), Blocks.GRANITE);
        map.put(new RecipeData("column", "column", AtbywBlocks.DIORITE_COLUMN, columnCount), Blocks.DIORITE);
        map.put(new RecipeData("column", "column", AtbywBlocks.ANDESITE_COLUMN, columnCount), Blocks.ANDESITE);
        map.put(new RecipeData("column", "column", AtbywBlocks.GRANITE_COLUMN, columnCount), Blocks.POLISHED_GRANITE);
        map.put(new RecipeData("column", "column", AtbywBlocks.DIORITE_COLUMN, columnCount), Blocks.POLISHED_DIORITE);
        map.put(new RecipeData("column", "column", AtbywBlocks.ANDESITE_COLUMN, columnCount), Blocks.POLISHED_ANDESITE);
        map.put(new RecipeData("column", "column", AtbywBlocks.SANDSTONE_COLUMN, columnCount), Blocks.SANDSTONE);
        map.put(new RecipeData("column", "column", AtbywBlocks.SANDSTONE_COLUMN, columnCount), Blocks.SMOOTH_SANDSTONE);
        map.put(new RecipeData("column", "column", AtbywBlocks.CHISELED_SANDSTONE_COLUMN, columnCount), Blocks.CHISELED_SANDSTONE);
        map.put(new RecipeData("column", "column", AtbywBlocks.RED_SANDSTONE_COLUMN, columnCount), Blocks.RED_SANDSTONE);
        map.put(new RecipeData("column", "column", AtbywBlocks.RED_SANDSTONE_COLUMN, columnCount), Blocks.SMOOTH_RED_SANDSTONE);
        map.put(new RecipeData("column", "column", AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN, columnCount), Blocks.CHISELED_RED_SANDSTONE);
        map.put(new RecipeData("column", "column", AtbywBlocks.PURPUR_COLUMN, columnCount), Blocks.PURPUR_BLOCK);
        map.put(new RecipeData("column", "column", AtbywBlocks.STONE_BRICKS_COLUMN, columnCount), Blocks.STONE_BRICKS);
        map.put(new RecipeData("column", "column", AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN, columnCount), Blocks.MOSSY_STONE_BRICKS);
        map.put(new RecipeData("column", "column", AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN, columnCount), Blocks.CRACKED_STONE_BRICKS);
        map.put(new RecipeData("column", "column", AtbywBlocks.NETHER_BRICKS_COLUMN, columnCount), Blocks.NETHER_BRICKS);
        map.put(new RecipeData("column", "column", AtbywBlocks.QUARTZ_COLUMN, columnCount), Blocks.QUARTZ_BLOCK);
        map.put(new RecipeData("column", "column", AtbywBlocks.PRISMARINE_COLUMN, columnCount), Blocks.PRISMARINE_BRICKS);
        map.put(new RecipeData("column", "column", AtbywBlocks.BLACKSTONE_COLUMN, columnCount), Blocks.BLACKSTONE);
        map.put(new RecipeData(AtbywBlocks.LARGE_CHAIN, 1), AtbywItems.LARGE_CHAIN_LINK);

        for (var entry : map.entrySet()) {
            var pattern = patterns.columnPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerFlowerSwitches() {
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
            var recipeData = new RecipeData("redstone", "flower_pull_switches", entry.getValue(), 1);
            registerShapedRecipe(recipeData, new String[] {" F ", "RSR"}, keys);
        }
    }

    private static void registerStickPatterns() {
        //Map<RecipeData, Input>
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(new RecipeData("from_basalt", "", "", AtbywBlocks.BASALT_PILLAR, 2), Blocks.BASALT);
        map.put(new RecipeData("from_polished_basalt", "", "", AtbywBlocks.BASALT_PILLAR, 2), Blocks.POLISHED_BASALT);
        map.put(new RecipeData("from_smooth_basalt", "", "", AtbywBlocks.BASALT_PILLAR, 2), Blocks.SMOOTH_BASALT);
        map.put(new RecipeData(AtbywItems.BAMBOO_STICK, 4), Items.BAMBOO);
        map.put(new RecipeData(AtbywItems.SHROOMSTICK, 4), Blocks.SHROOMLIGHT);
        map.put(new RecipeData(AtbywBlocks.CHISELED_PURPUR_BLOCK, 1), Blocks.PURPUR_SLAB);

        for (var entry : map.entrySet()) {
            var pattern = patterns.stickPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerSpikeTraps() {
        //Map<Input, Output>
        var map = Maps.<ItemConvertible, ItemConvertible>newHashMap();
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
            var recipeData = new RecipeData("redstone", "spike_traps", entry.getValue(), 1);
            registerShapedRecipe(recipeData, new String[] {"S#S", "#I#", "SRS"}, keys);
        }
    }

    private static void registerFenceDoorPatterns() {
        //Table<Category, Input, Output>
        var count = 3;
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(new RecipeData("", "fence_doors", AtbywBlocks.OAK_FENCE_DOOR, count), Blocks.OAK_PLANKS);
        map.put(new RecipeData("", "fence_doors", AtbywBlocks.SPRUCE_FENCE_DOOR, count), Blocks.SPRUCE_PLANKS);
        map.put(new RecipeData("", "fence_doors", AtbywBlocks.BIRCH_FENCE_DOOR, count), Blocks.BIRCH_PLANKS);
        map.put(new RecipeData("", "fence_doors", AtbywBlocks.JUNGLE_FENCE_DOOR, count), Blocks.JUNGLE_PLANKS);
        map.put(new RecipeData("", "fence_doors", AtbywBlocks.ACACIA_FENCE_DOOR, count), Blocks.ACACIA_PLANKS);
        map.put(new RecipeData("", "fence_doors", AtbywBlocks.DARK_OAK_FENCE_DOOR, count), Blocks.DARK_OAK_PLANKS);
        map.put(new RecipeData("", "fence_doors", AtbywBlocks.CRIMSON_FENCE_DOOR, count), Blocks.CRIMSON_PLANKS);
        map.put(new RecipeData("", "fence_doors", AtbywBlocks.WARPED_FENCE_DOOR, count), Blocks.WARPED_PLANKS);
        map.put(new RecipeData("redstone", "", AtbywBlocks.IRON_FENCE_DOOR, count), Items.IRON_INGOT);

        for (var entry : map.entrySet()) {
            var pattern = patterns.fenceDoorPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerBookshelfToggles() {
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
            var recipeData = new RecipeData("redstone", "bookshelf_toggles", entry.getValue(), 1);
            registerShapedRecipe(recipeData, new String[] {"PPP", "RBR", "CRC"}, keys);
        }
    }

    private static void registerBookshelfPatterns() {
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
            var recipeData = new RecipeData("bookshelves", "bookshelves", entry.getValue(), 1);
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerLadderPatterns() {
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
            var recipeData = new RecipeData("ladders", "ladders", entry.getValue(), 6);
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
        //Bamboo Ladder
        var keys = HashMultimap.<Character, Ingredient>create();
        keys.put('B', Ingredient.ofItems(Items.BAMBOO));
        keys.put('S', Ingredient.ofItems(AtbywItems.BAMBOO_STICK));
        var recipeData = new RecipeData(AtbywBlocks.BAMBOO_LADDER, 3);
        registerShapedRecipe(recipeData, new String[] {"B B", "BSB", "B B"}, keys);
    }

    private static void registerTorchPatterns() {
        //Map<Pair<Input "coal", Input "stick">, Output>
        var map = Maps.<RecipeData, Pair<ItemConvertible, ItemConvertible>>newHashMap();
        map.put(new RecipeData(AtbywBlocks.SOUL_JACK_O_LANTERN, 1), new Pair<>(Items.CARVED_PUMPKIN, Items.SOUL_TORCH));
        map.put(new RecipeData(AtbywBlocks.REDSTONE_JACK_O_LANTERN, 1), new Pair<>(Items.CARVED_PUMPKIN, Items.REDSTONE_TORCH));

        for (var entry : map.entrySet()) {
            var pattern = patterns.torchPattern(entry.getValue().getFirst(), entry.getValue().getSecond());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerMisc() {
        var keys = HashMultimap.<Character, Ingredient>create();
        var recipeData = (RecipeData) null;

        //Timer Repeater using Clock
        keys.put('C', Ingredient.ofItems(Items.CLOCK));
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('#', Ingredient.ofItems(Items.REPEATER));
        recipeData = new RecipeData("from_clock", "redstone", "timer_repeater", AtbywBlocks.TIMER_REPEATER, 1);
        registerShapedRecipe(recipeData, new String[] {" C ", "R#R", " R "}, keys);

        //Timer Repeater Manual
        keys.clear();
        keys.put('G', Ingredient.ofItems(Items.GOLD_INGOT));
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('#', Ingredient.ofItems(Items.REPEATER));
        recipeData = new RecipeData("redstone", "timer_repeater", AtbywBlocks.TIMER_REPEATER, 1);
        registerShapedRecipe(recipeData, new String[] {"GRG", "R#R", "GRG"}, keys);

        //Redstone Cross Path
        keys.clear();
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('S', Ingredient.ofItems(Blocks.STONE));
        recipeData = new RecipeData("", "redstone", AtbywBlocks.REDSTONE_CROSS_PATH, 1);
        registerShapedRecipe(recipeData, new String[] {"RRR", "SSS"}, keys);

        //Redstone Pipe
        keys.clear();
        keys.put('C', Ingredient.ofItems(Items.COPPER_INGOT));
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        recipeData = new RecipeData("", "redstone", AtbywBlocks.REDSTONE_PIPE, 3);
        registerShapedRecipe(recipeData, new String[] {"CRC", "CRC", "CRC"}, keys);

        //Redstone Pipe Repeater
        keys.clear();
        keys.put('C', Ingredient.ofItems(Items.COPPER_INGOT));
        keys.put('R', Ingredient.ofItems(Items.REPEATER));
        recipeData = new RecipeData("", "redstone", AtbywBlocks.REDSTONE_PIPE_REPEATER, 1);
        registerShapedRecipe(recipeData, new String[] {"CRC"}, keys);

        //Canvas Block
        keys.clear();
        keys.put('W', Ingredient.ofItems(Blocks.WHITE_WOOL));
        keys.put('S', Ingredient.ofItems(Items.STICK));
        recipeData = new RecipeData(AtbywBlocks.CANVAS_BLOCK, 1);
        registerShapedRecipe(recipeData, new String[] {"SWS", "W W", "SWS"}, keys);

        //Tinting Table
        keys.clear();
        keys.put('I', Ingredient.ofItems(Blocks.IRON_BLOCK));
        keys.put('P', Ingredient.fromTag(ItemTags.PLANKS));
        recipeData = new RecipeData(AtbywBlocks.TINTING_TABLE, 1);
        registerShapedRecipe(recipeData, new String[] {"PP", "II"}, keys);

        //Large Chain Link
        var pattern = patterns.hollowStarPattern(Items.IRON_INGOT);
        recipeData = new RecipeData(AtbywBlocks.REDSTONE_CROSS_PATH, 2);
        registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());

    }
}