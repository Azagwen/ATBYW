package net.azagwen.atbyw.datagen.recipe.registry;

import com.google.common.collect.*;
import net.azagwen.atbyw.block.registry.DecorationBlockRegistry;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
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
        map.put(new RecipeData(BuildingBlockRegistry.DIRT_STAIRS, count), Blocks.DIRT);
        map.put(new RecipeData(BuildingBlockRegistry.GRASS_BLOCK_STAIRS, count), Blocks.GRASS_BLOCK);
        map.put(new RecipeData(BuildingBlockRegistry.MYCELIUM_STAIRS, count), Blocks.MYCELIUM);
        map.put(new RecipeData(BuildingBlockRegistry.COARSE_DIRT_STAIRS, count), Blocks.COARSE_DIRT);
        map.put(new RecipeData(BuildingBlockRegistry.PODZOL_STAIRS, count), Blocks.PODZOL);
        map.put(new RecipeData(BuildingBlockRegistry.NETHERRACK_STAIRS, count), Blocks.NETHERRACK);
        map.put(new RecipeData(BuildingBlockRegistry.CRIMSON_NYLIUM_STAIRS, count), Blocks.CRIMSON_NYLIUM);
        map.put(new RecipeData(BuildingBlockRegistry.WARPED_NYLIUM_STAIRS, count), Blocks.WARPED_NYLIUM);
        map.put(new RecipeData(BuildingBlockRegistry.GRANITE_TILES_STAIRS, count), Blocks.GRANITE);
        map.put(new RecipeData(BuildingBlockRegistry.DIORITE_BRICKS_STAIRS, count), Blocks.DIORITE);
        map.put(new RecipeData(BuildingBlockRegistry.ANDESITE_BRICKS_STAIRS, count), Blocks.ANDESITE);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.TERRACOTTA_STAIRS, count), Blocks.TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.WHITE_TERRACOTTA_STAIRS, count), Blocks.WHITE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.ORANGE_TERRACOTTA_STAIRS, count), Blocks.ORANGE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.MAGENTA_TERRACOTTA_STAIRS, count), Blocks.MAGENTA_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_STAIRS, count), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.YELLOW_TERRACOTTA_STAIRS, count), Blocks.YELLOW_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.LIME_TERRACOTTA_STAIRS, count), Blocks.LIME_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.PINK_TERRACOTTA_STAIRS, count), Blocks.PINK_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.GRAY_TERRACOTTA_STAIRS, count), Blocks.GRAY_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_STAIRS, count), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.CYAN_TERRACOTTA_STAIRS, count), Blocks.CYAN_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.PURPLE_TERRACOTTA_STAIRS, count), Blocks.PURPLE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.BLUE_TERRACOTTA_STAIRS, count), Blocks.BLUE_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.BROWN_TERRACOTTA_STAIRS, count), Blocks.BROWN_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.GREEN_TERRACOTTA_STAIRS, count), Blocks.GREEN_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.RED_TERRACOTTA_STAIRS, count), Blocks.RED_TERRACOTTA);
        map.put(new RecipeData("terracotta_stairs", BuildingBlockRegistry.BLACK_TERRACOTTA_STAIRS, count), Blocks.BLACK_TERRACOTTA);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.RED_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_stairs", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.WHITE_CONCRETE_STAIRS, count), Blocks.WHITE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.ORANGE_CONCRETE_STAIRS, count), Blocks.ORANGE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.MAGENTA_CONCRETE_STAIRS, count), Blocks.MAGENTA_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.LIGHT_BLUE_CONCRETE_STAIRS, count), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.YELLOW_CONCRETE_STAIRS, count), Blocks.YELLOW_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.LIME_CONCRETE_STAIRS, count), Blocks.LIME_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.PINK_CONCRETE_STAIRS, count), Blocks.PINK_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.GRAY_CONCRETE_STAIRS, count), Blocks.GRAY_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.LIGHT_GRAY_CONCRETE_STAIRS, count), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.CYAN_CONCRETE_STAIRS, count), Blocks.CYAN_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.PURPLE_CONCRETE_STAIRS, count), Blocks.PURPLE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.BLUE_CONCRETE_STAIRS, count), Blocks.BLUE_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.BROWN_CONCRETE_STAIRS, count), Blocks.BROWN_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.GREEN_CONCRETE_STAIRS, count), Blocks.GREEN_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.RED_CONCRETE_STAIRS, count), Blocks.RED_CONCRETE);
        map.put(new RecipeData("concrete_stairs", BuildingBlockRegistry.BLACK_CONCRETE_STAIRS, count), Blocks.BLACK_CONCRETE);
        map.put(new RecipeData(BuildingBlockRegistry.PURPUR_TILES_STAIRS, count), BuildingBlockRegistry.PURPUR_TILES);
        map.put(new RecipeData(BuildingBlockRegistry.CUT_PURPUR_STAIRS, count), BuildingBlockRegistry.CUT_PURPUR_BLOCK);
        map.put(new RecipeData(BuildingBlockRegistry.SMOOTH_PURPUR_STAIRS, count), BuildingBlockRegistry.SMOOTH_PURPUR_BLOCK);
        map.put(new RecipeData(BuildingBlockRegistry.COMPACTED_SNOW_BLOCK_STAIRS, count), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);
        map.put(new RecipeData(BuildingBlockRegistry.COMPACTED_SNOW_BRICKS_STAIRS, count), BuildingBlockRegistry.COMPACTED_SNOW_BRICKS);
        map.put(new RecipeData(BuildingBlockRegistry.PACKED_ICE_STAIRS, count), Blocks.PACKED_ICE);
        map.put(new RecipeData(BuildingBlockRegistry.BLUE_ICE_STAIRS, count), Blocks.BLUE_ICE);
        map.put(new RecipeData(BuildingBlockRegistry.PACKED_ICE_BRICKS_STAIRS, count), BuildingBlockRegistry.PACKED_ICE_BRICKS);
        map.put(new RecipeData(BuildingBlockRegistry.BLUE_ICE_BRICKS_STAIRS, count), BuildingBlockRegistry.BLUE_ICE_BRICKS);
        map.put(new RecipeData(BuildingBlockRegistry.SAND_STAIRS, count), Blocks.SAND);
        map.put(new RecipeData(BuildingBlockRegistry.RED_SAND_STAIRS, count), Blocks.RED_SAND);
        map.put(new RecipeData(BuildingBlockRegistry.GRAVEL_STAIRS, count), Blocks.GRAVEL);
        map.put(new RecipeData(BuildingBlockRegistry.ROOTED_DIRT_STAIRS, count), Blocks.ROOTED_DIRT);
        map.put(new RecipeData("log_stairs", BuildingBlockRegistry.OAK_LOG_STAIRS, count), Blocks.OAK_LOG);
        map.put(new RecipeData("log_stairs", BuildingBlockRegistry.SPRUCE_LOG_STAIRS, count), Blocks.SPRUCE_LOG);
        map.put(new RecipeData("log_stairs", BuildingBlockRegistry.BIRCH_LOG_STAIRS, count), Blocks.BIRCH_LOG);
        map.put(new RecipeData("log_stairs", BuildingBlockRegistry.JUNGLE_LOG_STAIRS, count), Blocks.JUNGLE_LOG);
        map.put(new RecipeData("log_stairs", BuildingBlockRegistry.ACACIA_LOG_STAIRS, count), Blocks.ACACIA_LOG);
        map.put(new RecipeData("log_stairs", BuildingBlockRegistry.DARK_OAK_LOG_STAIRS, count), Blocks.DARK_OAK_LOG);
        map.put(new RecipeData("log_stairs", BuildingBlockRegistry.CRIMSON_STEM_STAIRS, count), Blocks.CRIMSON_STEM);
        map.put(new RecipeData("log_stairs", BuildingBlockRegistry.WARPED_STEM_STAIRS, count), Blocks.WARPED_STEM);
        map.put(new RecipeData("stripped_log_stairs", BuildingBlockRegistry.STRIPPED_OAK_LOG_STAIRS, count), Blocks.STRIPPED_OAK_LOG);
        map.put(new RecipeData("stripped_log_stairs", BuildingBlockRegistry.STRIPPED_SPRUCE_LOG_STAIRS, count), Blocks.STRIPPED_SPRUCE_LOG);
        map.put(new RecipeData("stripped_log_stairs", BuildingBlockRegistry.STRIPPED_BIRCH_LOG_STAIRS, count), Blocks.STRIPPED_BIRCH_LOG);
        map.put(new RecipeData("stripped_log_stairs", BuildingBlockRegistry.STRIPPED_JUNGLE_LOG_STAIRS, count), Blocks.STRIPPED_JUNGLE_LOG);
        map.put(new RecipeData("stripped_log_stairs", BuildingBlockRegistry.STRIPPED_ACACIA_LOG_STAIRS, count), Blocks.STRIPPED_ACACIA_LOG);
        map.put(new RecipeData("stripped_log_stairs", BuildingBlockRegistry.STRIPPED_DARK_OAK_LOG_STAIRS, count), Blocks.STRIPPED_DARK_OAK_LOG);
        map.put(new RecipeData("stripped_log_stairs", BuildingBlockRegistry.STRIPPED_CRIMSON_STEM_STAIRS, count), Blocks.STRIPPED_CRIMSON_STEM);
        map.put(new RecipeData("stripped_log_stairs", BuildingBlockRegistry.STRIPPED_WARPED_STEM_STAIRS, count), Blocks.STRIPPED_WARPED_STEM);

        for (var entry : map.entrySet()) {
            var pattern = patterns.stairsPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerSlabPatterns() {
        //Map<RecipeData, Input>
        var map = Maps.<RecipeData, ItemConvertible>newHashMap();
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.DIRT_SLAB, 6), Blocks.DIRT);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.GRASS_BLOCK_SLAB, 6), Blocks.GRASS_BLOCK);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.MYCELIUM_SLAB, 6), Blocks.MYCELIUM);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.COARSE_DIRT_SLAB, 6), Blocks.COARSE_DIRT);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.PODZOL_SLAB, 6), Blocks.PODZOL);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.NETHERRACK_SLAB, 6), Blocks.NETHERRACK);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.CRIMSON_NYLIUM_SLAB, 6), Blocks.CRIMSON_NYLIUM);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.WARPED_NYLIUM_SLAB, 6), Blocks.WARPED_NYLIUM);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.GRANITE_TILES_SLAB, 6), Blocks.GRANITE);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.DIORITE_BRICKS_SLAB, 6), Blocks.DIORITE);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.ANDESITE_BRICKS_SLAB, 6), Blocks.ANDESITE);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.TERRACOTTA_SLAB, 6), Blocks.TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.WHITE_TERRACOTTA_SLAB, 6), Blocks.WHITE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.ORANGE_TERRACOTTA_SLAB, 6), Blocks.ORANGE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.MAGENTA_TERRACOTTA_SLAB, 6), Blocks.MAGENTA_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_SLAB, 6), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.YELLOW_TERRACOTTA_SLAB, 6), Blocks.YELLOW_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.LIME_TERRACOTTA_SLAB, 6), Blocks.LIME_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.PINK_TERRACOTTA_SLAB, 6), Blocks.PINK_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.GRAY_TERRACOTTA_SLAB, 6), Blocks.GRAY_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_SLAB, 6), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.CYAN_TERRACOTTA_SLAB, 6), Blocks.CYAN_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.PURPLE_TERRACOTTA_SLAB, 6), Blocks.PURPLE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.BLUE_TERRACOTTA_SLAB, 6), Blocks.BLUE_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.BROWN_TERRACOTTA_SLAB, 6), Blocks.BROWN_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.GREEN_TERRACOTTA_SLAB, 6), Blocks.GREEN_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.RED_TERRACOTTA_SLAB, 6), Blocks.RED_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_slabs", BuildingBlockRegistry.BLACK_TERRACOTTA_SLAB, 6), Blocks.BLACK_TERRACOTTA);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.RED_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "terracotta_bricks_slabs", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_SLAB, 6), BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.WHITE_CONCRETE_SLAB, 6), Blocks.WHITE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.ORANGE_CONCRETE_SLAB, 6), Blocks.ORANGE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.MAGENTA_CONCRETE_SLAB, 6), Blocks.MAGENTA_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.LIGHT_BLUE_CONCRETE_SLAB, 6), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.YELLOW_CONCRETE_SLAB, 6), Blocks.YELLOW_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.LIME_CONCRETE_SLAB, 6), Blocks.LIME_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.PINK_CONCRETE_SLAB, 6), Blocks.PINK_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.GRAY_CONCRETE_SLAB, 6), Blocks.GRAY_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.LIGHT_GRAY_CONCRETE_SLAB, 6), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.CYAN_CONCRETE_SLAB, 6), Blocks.CYAN_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.PURPLE_CONCRETE_SLAB, 6), Blocks.PURPLE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.BLUE_CONCRETE_SLAB, 6), Blocks.BLUE_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.BROWN_CONCRETE_SLAB, 6), Blocks.BROWN_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.GREEN_CONCRETE_SLAB, 6), Blocks.GREEN_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.RED_CONCRETE_SLAB, 6), Blocks.RED_CONCRETE);
        map.put(new RecipeData("slab", "concrete_slabs", BuildingBlockRegistry.BLACK_CONCRETE_SLAB, 6), Blocks.BLACK_CONCRETE);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.PURPUR_TILES_SLAB, 6), BuildingBlockRegistry.PURPUR_TILES);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.CUT_PURPUR_SLAB, 6), BuildingBlockRegistry.CUT_PURPUR_BLOCK);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.SMOOTH_PURPUR_SLAB, 6), BuildingBlockRegistry.SMOOTH_PURPUR_BLOCK);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.COMPACTED_SNOW_BLOCK_SLAB, 6), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.COMPACTED_SNOW_BRICKS_SLAB, 6), BuildingBlockRegistry.COMPACTED_SNOW_BRICKS);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.PACKED_ICE_SLAB, 6), Blocks.PACKED_ICE);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.BLUE_ICE_SLAB, 6), Blocks.BLUE_ICE);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.PACKED_ICE_BRICKS_SLAB, 6), BuildingBlockRegistry.PACKED_ICE_BRICKS);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.BLUE_ICE_BRICKS_SLAB, 6), BuildingBlockRegistry.BLUE_ICE_BRICKS);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.SAND_SLAB, 6), Blocks.SAND);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.RED_SAND_SLAB, 6), Blocks.RED_SAND);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.GRAVEL_SLAB, 6), Blocks.GRAVEL);
        map.put(new RecipeData("slab", "", BuildingBlockRegistry.ROOTED_DIRT_SLAB, 6), Blocks.ROOTED_DIRT);
        map.put(new RecipeData("slab", "log_slabs", BuildingBlockRegistry.OAK_LOG_SLAB, 6), Blocks.OAK_LOG);
        map.put(new RecipeData("slab", "log_slabs", BuildingBlockRegistry.SPRUCE_LOG_SLAB, 6), Blocks.SPRUCE_LOG);
        map.put(new RecipeData("slab", "log_slabs", BuildingBlockRegistry.BIRCH_LOG_SLAB, 6), Blocks.BIRCH_LOG);
        map.put(new RecipeData("slab", "log_slabs", BuildingBlockRegistry.JUNGLE_LOG_SLAB, 6), Blocks.JUNGLE_LOG);
        map.put(new RecipeData("slab", "log_slabs", BuildingBlockRegistry.ACACIA_LOG_SLAB, 6), Blocks.ACACIA_LOG);
        map.put(new RecipeData("slab", "log_slabs", BuildingBlockRegistry.DARK_OAK_LOG_SLAB, 6), Blocks.DARK_OAK_LOG);
        map.put(new RecipeData("slab", "log_slabs", BuildingBlockRegistry.CRIMSON_STEM_SLAB, 6), Blocks.CRIMSON_STEM);
        map.put(new RecipeData("slab", "log_slabs", BuildingBlockRegistry.WARPED_STEM_SLAB, 6), Blocks.WARPED_STEM);
        map.put(new RecipeData("slab", "stripped_log_slabs", BuildingBlockRegistry.STRIPPED_OAK_LOG_SLAB, 6), Blocks.STRIPPED_OAK_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", BuildingBlockRegistry.STRIPPED_SPRUCE_LOG_SLAB, 6), Blocks.STRIPPED_SPRUCE_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", BuildingBlockRegistry.STRIPPED_BIRCH_LOG_SLAB, 6), Blocks.STRIPPED_BIRCH_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", BuildingBlockRegistry.STRIPPED_JUNGLE_LOG_SLAB, 6), Blocks.STRIPPED_JUNGLE_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", BuildingBlockRegistry.STRIPPED_ACACIA_LOG_SLAB, 6), Blocks.STRIPPED_ACACIA_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", BuildingBlockRegistry.STRIPPED_DARK_OAK_LOG_SLAB, 6), Blocks.STRIPPED_DARK_OAK_LOG);
        map.put(new RecipeData("slab", "stripped_log_slabs", BuildingBlockRegistry.STRIPPED_CRIMSON_STEM_SLAB, 6), Blocks.STRIPPED_CRIMSON_STEM);
        map.put(new RecipeData("slab", "stripped_log_slabs", BuildingBlockRegistry.STRIPPED_WARPED_STEM_SLAB, 6), Blocks.STRIPPED_WARPED_STEM);
        map.put(new RecipeData(DecorationBlockRegistry.COMPACTED_SNOW, 6), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);

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
        map.put(new RecipeData("from_basalt", "bricks", "basalt_bricks", BuildingBlockRegistry.BASALT_BRICKS, brickCount), Blocks.BASALT);
        map.put(new RecipeData("from_polished_basalt", "bricks", "basalt_bricks", BuildingBlockRegistry.BASALT_BRICKS, brickCount), Blocks.POLISHED_BASALT);
        map.put(new RecipeData("from_smooth_basalt", "bricks", "basalt_bricks", BuildingBlockRegistry.BASALT_BRICKS, brickCount), Blocks.SMOOTH_BASALT);
        map.put(new RecipeData("bricks", "", BuildingBlockRegistry.GRANITE_TILES, brickCount), Blocks.POLISHED_GRANITE);
        map.put(new RecipeData("bricks", "", BuildingBlockRegistry.DIORITE_BRICKS, brickCount), Blocks.POLISHED_DIORITE);
        map.put(new RecipeData("bricks", "", BuildingBlockRegistry.ANDESITE_BRICKS, brickCount), Blocks.POLISHED_ANDESITE);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.TERRACOTTA_BRICKS, brickCount), Blocks.TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS, brickCount), Blocks.WHITE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS, brickCount), Blocks.ORANGE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS, brickCount), Blocks.MAGENTA_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS, brickCount), Blocks.LIGHT_BLUE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS, brickCount), Blocks.YELLOW_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS, brickCount), Blocks.LIME_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS, brickCount), Blocks.PINK_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS, brickCount), Blocks.GRAY_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS, brickCount), Blocks.LIGHT_GRAY_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS, brickCount), Blocks.CYAN_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS, brickCount), Blocks.PURPLE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS, brickCount), Blocks.BLUE_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS, brickCount), Blocks.BROWN_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS, brickCount), Blocks.GREEN_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS, brickCount), Blocks.RED_TERRACOTTA);
        map.put(new RecipeData("bricks", "terracotta_bricks", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS, brickCount), Blocks.BLACK_TERRACOTTA);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.WHITE_CINDER_BLOCKS, brickCount), Blocks.WHITE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.ORANGE_CINDER_BLOCKS, brickCount), Blocks.ORANGE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.MAGENTA_CINDER_BLOCKS, brickCount), Blocks.MAGENTA_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.LIGHT_BLUE_CINDER_BLOCKS, brickCount), Blocks.LIGHT_BLUE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.YELLOW_CINDER_BLOCKS, brickCount), Blocks.YELLOW_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.LIME_CINDER_BLOCKS, brickCount), Blocks.LIME_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.PINK_CINDER_BLOCKS, brickCount), Blocks.PINK_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.GRAY_CINDER_BLOCKS, brickCount), Blocks.GRAY_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.LIGHT_GRAY_CINDER_BLOCKS, brickCount), Blocks.LIGHT_GRAY_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.CYAN_CINDER_BLOCKS, brickCount), Blocks.CYAN_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.PURPLE_CINDER_BLOCKS, brickCount), Blocks.PURPLE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.BLUE_CINDER_BLOCKS, brickCount), Blocks.BLUE_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.BROWN_CINDER_BLOCKS, brickCount), Blocks.BROWN_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.GREEN_CINDER_BLOCKS, brickCount), Blocks.GREEN_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.RED_CINDER_BLOCKS, brickCount), Blocks.RED_CONCRETE);
        map.put(new RecipeData("bricks", "cinder_blocks", BuildingBlockRegistry.BLACK_CINDER_BLOCKS, brickCount), Blocks.BLACK_CONCRETE);
        map.put(new RecipeData("bricks", "", BuildingBlockRegistry.PURPUR_TILES, brickCount), Blocks.PURPUR_PILLAR);
        map.put(new RecipeData("bricks", "", BuildingBlockRegistry.CUT_PURPUR_BLOCK, brickCount), Blocks.PURPUR_BLOCK);
        map.put(new RecipeData("bricks", "", BuildingBlockRegistry.COMPACTED_SNOW_BLOCK, brickCount), Blocks.SNOW_BLOCK);
        map.put(new RecipeData("bricks", "", BuildingBlockRegistry.COMPACTED_SNOW_BRICKS, brickCount), BuildingBlockRegistry.COMPACTED_SNOW_BLOCK);
        map.put(new RecipeData("bricks", "", BuildingBlockRegistry.PACKED_ICE_BRICKS, brickCount), Blocks.PACKED_ICE);
        map.put(new RecipeData("bricks", "", BuildingBlockRegistry.BLUE_ICE_BRICKS, brickCount), Blocks.BLUE_ICE);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.SHATTERED_GLASS, glassCount), AtbywItems.GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.WHITE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.WHITE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.ORANGE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.ORANGE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.MAGENTA_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.MAGENTA_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.LIGHT_BLUE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.LIGHT_BLUE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.YELLOW_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.YELLOW_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.LIME_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.LIME_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.PINK_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.PINK_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.GRAY_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.GRAY_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.LIGHT_GRAY_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.LIGHT_GRAY_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.CYAN_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.CYAN_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.PURPLE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.PURPLE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.BLUE_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.BLUE_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.BROWN_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.BROWN_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.GREEN_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.GREEN_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.RED_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.RED_STAINED_GLASS_SHARD);
        map.put(new RecipeData("shattered_glass", "shattered_glass", BuildingBlockRegistry.BLACK_STAINED_SHATTERED_GLASS, glassCount), AtbywItems.BLACK_STAINED_GLASS_SHARD);

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
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.WHITE_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.ORANGE_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.YELLOW_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.LIME_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.PINK_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.GRAY_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.CYAN_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.PURPLE_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.BLUE_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.BROWN_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.GREEN_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.RED_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.RED_TERRACOTTA_BRICKS);
        map.put(new RecipeData("terracotta_bricks_wall", DecorationBlockRegistry.BLACK_TERRACOTTA_BRICKS_WALL, count), BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.WHITE_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.WHITE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.ORANGE_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.ORANGE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.MAGENTA_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.MAGENTA_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.LIGHT_BLUE_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.LIGHT_BLUE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.YELLOW_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.YELLOW_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.LIME_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.LIME_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.PINK_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.PINK_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.GRAY_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.GRAY_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.LIGHT_GRAY_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.LIGHT_GRAY_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.CYAN_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.CYAN_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.PURPLE_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.PURPLE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.BLUE_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.BLUE_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.BROWN_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.BROWN_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.GREEN_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.GREEN_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.RED_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.RED_CINDER_BLOCKS);
        map.put(new RecipeData("cinder_blocks_wall", DecorationBlockRegistry.BLACK_CINDER_BLOCKS_WALL, count), BuildingBlockRegistry.BLACK_CINDER_BLOCKS);

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
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.WHITE_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.ORANGE_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.MAGENTA_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.YELLOW_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.LIME_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.PINK_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.GRAY_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.CYAN_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.PURPLE_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.BLUE_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.BROWN_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.GREEN_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.RED_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_stairs_dyeing", BuildingBlockRegistry.BLACK_TERRACOTTA_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.WHITE_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.ORANGE_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.MAGENTA_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.YELLOW_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.LIME_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.PINK_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.GRAY_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.CYAN_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.PURPLE_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.BLUE_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.BROWN_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.GREEN_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.RED_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_slab_dyeing", BuildingBlockRegistry.BLACK_TERRACOTTA_SLAB, count), BuildingBlockRegistry.TERRACOTTA_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_dyeing", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_stairs_dyeing", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_STAIRS, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_slab_dyeing", BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_SLAB, count), BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.WHITE_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.ORANGE_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.YELLOW_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.LIME_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.PINK_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.GRAY_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.CYAN_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.PURPLE_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.BLUE_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.BROWN_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.GREEN_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.RED_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);
        dyeingMap.put(dyeingRecipeData("terracotta_bricks_wall_dyeing", DecorationBlockRegistry.BLACK_TERRACOTTA_BRICKS_WALL, count), DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL);

        //Map<Pair<Input outer ring, Input middle>, Output>
        var map = Maps.<RecipeData, Pair<ItemConvertible, ItemConvertible>>newHashMap();
        map.put(new RecipeData(RedstoneBlockRegistry.REDSTONE_LANTERN, 1), new Pair<>(Items.IRON_NUGGET, Items.REDSTONE_TORCH));

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
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.GRANITE_COLUMN, columnCount), Blocks.GRANITE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.DIORITE_COLUMN, columnCount), Blocks.DIORITE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.ANDESITE_COLUMN, columnCount), Blocks.ANDESITE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.GRANITE_COLUMN, columnCount), Blocks.POLISHED_GRANITE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.DIORITE_COLUMN, columnCount), Blocks.POLISHED_DIORITE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.ANDESITE_COLUMN, columnCount), Blocks.POLISHED_ANDESITE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.SANDSTONE_COLUMN, columnCount), Blocks.SANDSTONE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.SANDSTONE_COLUMN, columnCount), Blocks.SMOOTH_SANDSTONE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.CHISELED_SANDSTONE_COLUMN, columnCount), Blocks.CHISELED_SANDSTONE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.RED_SANDSTONE_COLUMN, columnCount), Blocks.RED_SANDSTONE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.RED_SANDSTONE_COLUMN, columnCount), Blocks.SMOOTH_RED_SANDSTONE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.CHISELED_RED_SANDSTONE_COLUMN, columnCount), Blocks.CHISELED_RED_SANDSTONE);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.PURPUR_COLUMN, columnCount), Blocks.PURPUR_BLOCK);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.STONE_BRICKS_COLUMN, columnCount), Blocks.STONE_BRICKS);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.MOSSY_STONE_BRICKS_COLUMN, columnCount), Blocks.MOSSY_STONE_BRICKS);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.CRACKED_STONE_BRICKS_COLUMN, columnCount), Blocks.CRACKED_STONE_BRICKS);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.NETHER_BRICKS_COLUMN, columnCount), Blocks.NETHER_BRICKS);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.QUARTZ_COLUMN, columnCount), Blocks.QUARTZ_BLOCK);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.PRISMARINE_COLUMN, columnCount), Blocks.PRISMARINE_BRICKS);
        map.put(new RecipeData("column", "column", DecorationBlockRegistry.BLACKSTONE_COLUMN, columnCount), Blocks.BLACKSTONE);
        map.put(new RecipeData(DecorationBlockRegistry.LARGE_CHAIN, 1), AtbywItems.LARGE_CHAIN_LINK);

        for (var entry : map.entrySet()) {
            var pattern = patterns.columnPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerFlowerSwitches() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Items.DANDELION, RedstoneBlockRegistry.DANDELION_PULL_SWITCH);
        map.put(Items.POPPY, RedstoneBlockRegistry.POPPY_PULL_SWITCH);
        map.put(Items.BLUE_ORCHID, RedstoneBlockRegistry.BLUE_ORCHID_PULL_SWITCH);
        map.put(Items.ALLIUM, RedstoneBlockRegistry.ALLIUM_PULL_SWITCH);
        map.put(Items.AZURE_BLUET, RedstoneBlockRegistry.AZURE_BLUET_PULL_SWITCH);
        map.put(Items.RED_TULIP, RedstoneBlockRegistry.RED_TULIP_PULL_SWITCH);
        map.put(Items.ORANGE_TULIP, RedstoneBlockRegistry.ORANGE_TULIP_PULL_SWITCH);
        map.put(Items.WHITE_TULIP, RedstoneBlockRegistry.WHITE_TULIP_PULL_SWITCH);
        map.put(Items.PINK_TULIP, RedstoneBlockRegistry.PINK_TULIP_PULL_SWITCH);
        map.put(Items.OXEYE_DAISY, RedstoneBlockRegistry.OXEYE_DAISY_PULL_SWITCH);
        map.put(Items.CORNFLOWER, RedstoneBlockRegistry.CORNFLOWER_PULL_SWITCH);
        map.put(Items.LILY_OF_THE_VALLEY, RedstoneBlockRegistry.LILY_OF_THE_VALLEY_PULL_SWITCH);
        map.put(Items.WITHER_ROSE, RedstoneBlockRegistry.WITHER_ROSE_PULL_SWITCH);

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
        map.put(new RecipeData("from_basalt", "", "", BuildingBlockRegistry.BASALT_PILLAR, 2), Blocks.BASALT);
        map.put(new RecipeData("from_polished_basalt", "", "", BuildingBlockRegistry.BASALT_PILLAR, 2), Blocks.POLISHED_BASALT);
        map.put(new RecipeData("from_smooth_basalt", "", "", BuildingBlockRegistry.BASALT_PILLAR, 2), Blocks.SMOOTH_BASALT);
        map.put(new RecipeData(AtbywItems.BAMBOO_STICK, 4), Items.BAMBOO);
        map.put(new RecipeData(AtbywItems.SHROOMSTICK, 4), Blocks.SHROOMLIGHT);
        map.put(new RecipeData(BuildingBlockRegistry.CHISELED_PURPUR_BLOCK, 1), Blocks.PURPUR_SLAB);

        for (var entry : map.entrySet()) {
            var pattern = patterns.stickPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerSpikeTraps() {
        //Map<Input, Output>
        var map = Maps.<ItemConvertible, ItemConvertible>newHashMap();
        map.put(Items.IRON_INGOT, RedstoneBlockRegistry.IRON_SPIKE_TRAP);
        map.put(Items.GOLD_INGOT, RedstoneBlockRegistry.GOLD_SPIKE_TRAP);
        map.put(Items.DIAMOND, RedstoneBlockRegistry.DIAMOND_SPIKE_TRAP);
        map.put(Items.NETHERITE_INGOT, RedstoneBlockRegistry.NETHERITE_SPIKE_TRAP);

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
        map.put(new RecipeData("", "fence_doors", RedstoneBlockRegistry.OAK_FENCE_DOOR, count), Blocks.OAK_PLANKS);
        map.put(new RecipeData("", "fence_doors", RedstoneBlockRegistry.SPRUCE_FENCE_DOOR, count), Blocks.SPRUCE_PLANKS);
        map.put(new RecipeData("", "fence_doors", RedstoneBlockRegistry.BIRCH_FENCE_DOOR, count), Blocks.BIRCH_PLANKS);
        map.put(new RecipeData("", "fence_doors", RedstoneBlockRegistry.JUNGLE_FENCE_DOOR, count), Blocks.JUNGLE_PLANKS);
        map.put(new RecipeData("", "fence_doors", RedstoneBlockRegistry.ACACIA_FENCE_DOOR, count), Blocks.ACACIA_PLANKS);
        map.put(new RecipeData("", "fence_doors", RedstoneBlockRegistry.DARK_OAK_FENCE_DOOR, count), Blocks.DARK_OAK_PLANKS);
        map.put(new RecipeData("", "fence_doors", RedstoneBlockRegistry.CRIMSON_FENCE_DOOR, count), Blocks.CRIMSON_PLANKS);
        map.put(new RecipeData("", "fence_doors", RedstoneBlockRegistry.WARPED_FENCE_DOOR, count), Blocks.WARPED_PLANKS);
        map.put(new RecipeData("redstone", "", RedstoneBlockRegistry.IRON_FENCE_DOOR, count), Items.IRON_INGOT);

        for (var entry : map.entrySet()) {
            var pattern = patterns.fenceDoorPattern(entry.getValue());
            var recipeData = entry.getKey();
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerBookshelfToggles() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Items.OAK_PLANKS, RedstoneBlockRegistry.OAK_BOOKSHELF_TOGGLE);
        map.put(Items.SPRUCE_PLANKS, RedstoneBlockRegistry.SPRUCE_BOOKSHELF_TOGGLE);
        map.put(Items.BIRCH_PLANKS, RedstoneBlockRegistry.BIRCH_BOOKSHELF_TOGGLE);
        map.put(Items.JUNGLE_PLANKS, RedstoneBlockRegistry.JUNGLE_BOOKSHELF_TOGGLE);
        map.put(Items.ACACIA_PLANKS, RedstoneBlockRegistry.ACACIA_BOOKSHELF_TOGGLE);
        map.put(Items.DARK_OAK_PLANKS, RedstoneBlockRegistry.DARK_OAK_BOOKSHELF_TOGGLE);
        map.put(Items.CRIMSON_PLANKS, RedstoneBlockRegistry.CRIMSON_BOOKSHELF_TOGGLE);
        map.put(Items.WARPED_PLANKS, RedstoneBlockRegistry.WARPED_BOOKSHELF_TOGGLE);

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
        map.put(Items.SPRUCE_PLANKS, BuildingBlockRegistry.SPRUCE_BOOKSHELF);
        map.put(Items.BIRCH_PLANKS, BuildingBlockRegistry.BIRCH_BOOKSHELF);
        map.put(Items.JUNGLE_PLANKS, BuildingBlockRegistry.JUNGLE_BOOKSHELF);
        map.put(Items.ACACIA_PLANKS, BuildingBlockRegistry.ACACIA_BOOKSHELF);
        map.put(Items.DARK_OAK_PLANKS, BuildingBlockRegistry.DARK_OAK_BOOKSHELF);
        map.put(Items.CRIMSON_PLANKS, BuildingBlockRegistry.CRIMSON_BOOKSHELF);
        map.put(Items.WARPED_PLANKS, BuildingBlockRegistry.WARPED_BOOKSHELF);

        for (var entry : map.entrySet()) {
            var pattern = patterns.bookshelfPattern(entry.getKey(), Items.BOOK);
            var recipeData = new RecipeData("bookshelves", "bookshelves", entry.getValue(), 1);
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
    }

    private static void registerLadderPatterns() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Items.SPRUCE_PLANKS, DecorationBlockRegistry.SPRUCE_LADDER);
        map.put(Items.BIRCH_PLANKS, DecorationBlockRegistry.BIRCH_LADDER);
        map.put(Items.JUNGLE_PLANKS, DecorationBlockRegistry.JUNGLE_LADDER);
        map.put(Items.ACACIA_PLANKS, DecorationBlockRegistry.ACACIA_LADDER);
        map.put(Items.DARK_OAK_PLANKS, DecorationBlockRegistry.DARK_OAK_LADDER);
        map.put(Items.CRIMSON_PLANKS, DecorationBlockRegistry.CRIMSON_LADDER);
        map.put(Items.WARPED_PLANKS, DecorationBlockRegistry.WARPED_LADDER);

        for (var entry : map.entrySet()) {
            var pattern = patterns.ladderPattern(entry.getKey());
            var recipeData = new RecipeData("ladders", "ladders", entry.getValue(), 6);
            registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());
        }
        //Bamboo Ladder
        var keys = HashMultimap.<Character, Ingredient>create();
        keys.put('B', Ingredient.ofItems(Items.BAMBOO));
        keys.put('S', Ingredient.ofItems(AtbywItems.BAMBOO_STICK));
        var recipeData = new RecipeData(DecorationBlockRegistry.BAMBOO_LADDER, 3);
        registerShapedRecipe(recipeData, new String[] {"B B", "BSB", "B B"}, keys);
    }

    private static void registerTorchPatterns() {
        //Map<Pair<Input "coal", Input "stick">, Output>
        var map = Maps.<RecipeData, Pair<ItemConvertible, ItemConvertible>>newHashMap();
        map.put(new RecipeData(BuildingBlockRegistry.SOUL_JACK_O_LANTERN, 1), new Pair<>(Items.CARVED_PUMPKIN, Items.SOUL_TORCH));
        map.put(new RecipeData(RedstoneBlockRegistry.REDSTONE_JACK_O_LANTERN, 1), new Pair<>(Items.CARVED_PUMPKIN, Items.REDSTONE_TORCH));

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
        recipeData = new RecipeData("from_clock", "redstone", "timer_repeater", RedstoneBlockRegistry.TIMER_REPEATER, 1);
        registerShapedRecipe(recipeData, new String[] {" C ", "R#R", " R "}, keys);

        //Timer Repeater Manual
        keys.clear();
        keys.put('G', Ingredient.ofItems(Items.GOLD_INGOT));
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('#', Ingredient.ofItems(Items.REPEATER));
        recipeData = new RecipeData("redstone", "timer_repeater", RedstoneBlockRegistry.TIMER_REPEATER, 1);
        registerShapedRecipe(recipeData, new String[] {"GRG", "R#R", "GRG"}, keys);

        //Redstone Cross Path
        keys.clear();
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('S', Ingredient.ofItems(Blocks.STONE));
        recipeData = new RecipeData("", "redstone", RedstoneBlockRegistry.REDSTONE_CROSS_PATH, 1);
        registerShapedRecipe(recipeData, new String[] {"RRR", "SSS"}, keys);

        //Redstone Pipe
        keys.clear();
        keys.put('C', Ingredient.ofItems(Items.COPPER_INGOT));
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        recipeData = new RecipeData("", "redstone", RedstoneBlockRegistry.REDSTONE_PIPE, 3);
        registerShapedRecipe(recipeData, new String[] {"CRC", "CRC", "CRC"}, keys);

        //Redstone Pipe Repeater
        keys.clear();
        keys.put('C', Ingredient.ofItems(Items.COPPER_INGOT));
        keys.put('R', Ingredient.ofItems(Items.REPEATER));
        recipeData = new RecipeData("", "redstone", RedstoneBlockRegistry.REDSTONE_PIPE_REPEATER, 1);
        registerShapedRecipe(recipeData, new String[] {"CRC"}, keys);

        //Canvas Block
        keys.clear();
        keys.put('W', Ingredient.ofItems(Blocks.WHITE_WOOL));
        keys.put('S', Ingredient.ofItems(Items.STICK));
        recipeData = new RecipeData(DecorationBlockRegistry.CANVAS_BLOCK, 1);
        registerShapedRecipe(recipeData, new String[] {"SWS", "W W", "SWS"}, keys);

        //Tinting Table
        keys.clear();
        keys.put('I', Ingredient.ofItems(Blocks.IRON_BLOCK));
        keys.put('P', Ingredient.fromTag(ItemTags.PLANKS));
        recipeData = new RecipeData(DecorationBlockRegistry.TINTING_TABLE, 1);
        registerShapedRecipe(recipeData, new String[] {"PP", "II"}, keys);

        //Large Chain Link
        var pattern = patterns.hollowStarPattern(Items.IRON_INGOT);
        recipeData = new RecipeData(AtbywItems.LARGE_CHAIN_LINK, 2);
        registerShapedRecipe(recipeData, pattern.getFirst(), pattern.getSecond());

    }
}