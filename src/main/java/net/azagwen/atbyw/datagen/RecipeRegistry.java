package net.azagwen.atbyw.datagen;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.gson.*;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.main.AtbywIdentifier;
import net.azagwen.atbyw.util.AtbywUtils;
import net.azagwen.atbyw.util.Pair;
import net.azagwen.atbyw.util.Quadruplet;
import net.azagwen.atbyw.util.naming.WoodNames;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.ServerTagManagerHolder;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.azagwen.atbyw.datagen.RecipeUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class RecipeRegistry {
    public static Logger LOGGER = LogManager.getLogger("Atbyw Recipes");

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
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Blocks.DIRT, AtbywBlocks.DIRT_STAIRS);
        map.put(Blocks.GRASS_BLOCK, AtbywBlocks.GRASS_BLOCK_STAIRS);
        map.put(Blocks.MYCELIUM, AtbywBlocks.MYCELIUM_STAIRS);
        map.put(Blocks.COARSE_DIRT, AtbywBlocks.COARSE_DIRT_STAIRS);
        map.put(Blocks.PODZOL, AtbywBlocks.PODZOL_STAIRS);
        map.put(Blocks.NETHERRACK, AtbywBlocks.NETHERRACK_STAIRS);
        map.put(Blocks.CRIMSON_NYLIUM, AtbywBlocks.CRIMSON_NYLIUM_STAIRS);
        map.put(Blocks.WARPED_NYLIUM, AtbywBlocks.WARPED_NYLIUM_STAIRS);
        map.put(Blocks.GRANITE, AtbywBlocks.GRANITE_TILES_STAIRS);
        map.put(Blocks.DIORITE, AtbywBlocks.DIORITE_BRICKS_STAIRS);
        map.put(Blocks.ANDESITE, AtbywBlocks.ANDESITE_BRICKS_STAIRS);
        map.put(Blocks.TERRACOTTA, AtbywBlocks.TERRACOTTA_STAIRS);
        map.put(Blocks.WHITE_TERRACOTTA, AtbywBlocks.WHITE_TERRACOTTA_STAIRS);
        map.put(Blocks.ORANGE_TERRACOTTA, AtbywBlocks.ORANGE_TERRACOTTA_STAIRS);
        map.put(Blocks.MAGENTA_TERRACOTTA, AtbywBlocks.MAGENTA_TERRACOTTA_STAIRS);
        map.put(Blocks.LIGHT_BLUE_TERRACOTTA, AtbywBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS);
        map.put(Blocks.YELLOW_TERRACOTTA, AtbywBlocks.YELLOW_TERRACOTTA_STAIRS);
        map.put(Blocks.LIME_TERRACOTTA, AtbywBlocks.LIME_TERRACOTTA_STAIRS);
        map.put(Blocks.PINK_TERRACOTTA, AtbywBlocks.PINK_TERRACOTTA_STAIRS);
        map.put(Blocks.GRAY_TERRACOTTA, AtbywBlocks.GRAY_TERRACOTTA_STAIRS);
        map.put(Blocks.LIGHT_GRAY_TERRACOTTA, AtbywBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS);
        map.put(Blocks.CYAN_TERRACOTTA, AtbywBlocks.CYAN_TERRACOTTA_STAIRS);
        map.put(Blocks.PURPLE_TERRACOTTA, AtbywBlocks.PURPLE_TERRACOTTA_STAIRS);
        map.put(Blocks.BLUE_TERRACOTTA, AtbywBlocks.BLUE_TERRACOTTA_STAIRS);
        map.put(Blocks.BROWN_TERRACOTTA, AtbywBlocks.BROWN_TERRACOTTA_STAIRS);
        map.put(Blocks.GREEN_TERRACOTTA, AtbywBlocks.GREEN_TERRACOTTA_STAIRS);
        map.put(Blocks.RED_TERRACOTTA, AtbywBlocks.RED_TERRACOTTA_STAIRS);
        map.put(Blocks.BLACK_TERRACOTTA, AtbywBlocks.BLACK_TERRACOTTA_STAIRS);
        map.put(AtbywBlocks.TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.WHITE_TERRACOTTA_BRICKS, AtbywBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.LIME_TERRACOTTA_BRICKS, AtbywBlocks.LIME_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.PINK_TERRACOTTA_BRICKS, AtbywBlocks.PINK_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.GRAY_TERRACOTTA_BRICKS, AtbywBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.CYAN_TERRACOTTA_BRICKS, AtbywBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.BLUE_TERRACOTTA_BRICKS, AtbywBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.BROWN_TERRACOTTA_BRICKS, AtbywBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.GREEN_TERRACOTTA_BRICKS, AtbywBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.RED_TERRACOTTA_BRICKS, AtbywBlocks.RED_TERRACOTTA_BRICKS_STAIRS);
        map.put(AtbywBlocks.BLACK_TERRACOTTA_BRICKS, AtbywBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS);
        map.put(Blocks.WHITE_CONCRETE, AtbywBlocks.WHITE_CONCRETE_STAIRS);
        map.put(Blocks.ORANGE_CONCRETE, AtbywBlocks.ORANGE_CONCRETE_STAIRS);
        map.put(Blocks.MAGENTA_CONCRETE, AtbywBlocks.MAGENTA_CONCRETE_STAIRS);
        map.put(Blocks.LIGHT_BLUE_CONCRETE, AtbywBlocks.LIGHT_BLUE_CONCRETE_STAIRS);
        map.put(Blocks.YELLOW_CONCRETE, AtbywBlocks.YELLOW_CONCRETE_STAIRS);
        map.put(Blocks.LIME_CONCRETE, AtbywBlocks.LIME_CONCRETE_STAIRS);
        map.put(Blocks.PINK_CONCRETE, AtbywBlocks.PINK_CONCRETE_STAIRS);
        map.put(Blocks.GRAY_CONCRETE, AtbywBlocks.GRAY_CONCRETE_STAIRS);
        map.put(Blocks.LIGHT_GRAY_CONCRETE, AtbywBlocks.LIGHT_GRAY_CONCRETE_STAIRS);
        map.put(Blocks.CYAN_CONCRETE, AtbywBlocks.CYAN_CONCRETE_STAIRS);
        map.put(Blocks.PURPLE_CONCRETE, AtbywBlocks.PURPLE_CONCRETE_STAIRS);
        map.put(Blocks.BLUE_CONCRETE, AtbywBlocks.BLUE_CONCRETE_STAIRS);
        map.put(Blocks.BROWN_CONCRETE, AtbywBlocks.BROWN_CONCRETE_STAIRS);
        map.put(Blocks.GREEN_CONCRETE, AtbywBlocks.GREEN_CONCRETE_STAIRS);
        map.put(Blocks.RED_CONCRETE, AtbywBlocks.RED_CONCRETE_STAIRS);
        map.put(Blocks.BLACK_CONCRETE, AtbywBlocks.BLACK_CONCRETE_STAIRS);
        map.put(AtbywBlocks.PURPUR_TILES, AtbywBlocks.PURPUR_TILES_STAIRS);
        map.put(AtbywBlocks.CUT_PURPUR_BLOCK, AtbywBlocks.CUT_PURPUR_STAIRS);
        map.put(AtbywBlocks.SMOOTH_PURPUR_BLOCK, AtbywBlocks.SMOOTH_PURPUR_STAIRS);
        map.put(AtbywBlocks.COMPACTED_SNOW_BLOCK, AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS);
        map.put(AtbywBlocks.COMPACTED_SNOW_BRICKS, AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS);
        map.put(Blocks.PACKED_ICE, AtbywBlocks.PACKED_ICE_STAIRS);
        map.put(Blocks.BLUE_ICE, AtbywBlocks.BLUE_ICE_STAIRS);
        map.put(AtbywBlocks.PACKED_ICE_BRICKS, AtbywBlocks.PACKED_ICE_BRICKS_STAIRS);
        map.put(AtbywBlocks.BLUE_ICE_BRICKS, AtbywBlocks.BLUE_ICE_BRICKS_STAIRS);
        map.put(Blocks.SAND, AtbywBlocks.SAND_STAIRS);
        map.put(Blocks.RED_SAND, AtbywBlocks.RED_SAND_STAIRS);
        map.put(Blocks.GRAVEL, AtbywBlocks.GRAVEL_STAIRS);
        map.put(Blocks.ROOTED_DIRT, AtbywBlocks.ROOTED_DIRT_STAIRS);
        map.put(Blocks.OAK_LOG, AtbywBlocks.OAK_LOG_STAIRS);
        map.put(Blocks.SPRUCE_LOG, AtbywBlocks.SPRUCE_LOG_STAIRS);
        map.put(Blocks.BIRCH_LOG, AtbywBlocks.BIRCH_LOG_STAIRS);
        map.put(Blocks.JUNGLE_LOG, AtbywBlocks.JUNGLE_LOG_STAIRS);
        map.put(Blocks.ACACIA_LOG, AtbywBlocks.ACACIA_LOG_STAIRS);
        map.put(Blocks.DARK_OAK_LOG, AtbywBlocks.DARK_OAK_LOG_STAIRS);
        map.put(Blocks.CRIMSON_STEM, AtbywBlocks.CRIMSON_STEM_STAIRS);
        map.put(Blocks.WARPED_STEM, AtbywBlocks.WARPED_STEM_STAIRS);
        map.put(Blocks.STRIPPED_OAK_LOG, AtbywBlocks.STRIPPED_OAK_LOG_STAIRS);
        map.put(Blocks.STRIPPED_SPRUCE_LOG, AtbywBlocks.STRIPPED_SPRUCE_LOG_STAIRS);
        map.put(Blocks.STRIPPED_BIRCH_LOG, AtbywBlocks.STRIPPED_BIRCH_LOG_STAIRS);
        map.put(Blocks.STRIPPED_JUNGLE_LOG, AtbywBlocks.STRIPPED_JUNGLE_LOG_STAIRS);
        map.put(Blocks.STRIPPED_ACACIA_LOG, AtbywBlocks.STRIPPED_ACACIA_LOG_STAIRS);
        map.put(Blocks.STRIPPED_DARK_OAK_LOG, AtbywBlocks.STRIPPED_DARK_OAK_LOG_STAIRS);
        map.put(Blocks.STRIPPED_CRIMSON_STEM, AtbywBlocks.STRIPPED_CRIMSON_STEM_STAIRS);
        map.put(Blocks.STRIPPED_WARPED_STEM, AtbywBlocks.STRIPPED_WARPED_STEM_STAIRS);

        var pattern = new String[] {"  #", " ##", "###"};
        for (var component : map.entrySet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('#', Ingredient.ofItems(component.getKey().asItem()));
            registerShapedRecipe("", "stairs", pattern, keys, component.getValue(), 4);
        }
    }

    public static void registerSlabPatterns() {
        //Table<Category, Input, Pair<Output, Count>>
        var table = HashBasedTable.<String, ItemConvertible, Pair<ItemConvertible, Integer>>create();
        table.put("slab", Blocks.DIRT, new Pair<>(AtbywBlocks.DIRT_SLAB, 6));
        table.put("slab", Blocks.GRASS_BLOCK, new Pair<>(AtbywBlocks.GRASS_BLOCK_SLAB, 6));
        table.put("slab", Blocks.MYCELIUM, new Pair<>(AtbywBlocks.MYCELIUM_SLAB, 6));
        table.put("slab", Blocks.COARSE_DIRT, new Pair<>(AtbywBlocks.COARSE_DIRT_SLAB, 6));
        table.put("slab", Blocks.PODZOL, new Pair<>(AtbywBlocks.PODZOL_SLAB, 6));
        table.put("slab", Blocks.NETHERRACK, new Pair<>(AtbywBlocks.NETHERRACK_SLAB, 6));
        table.put("slab", Blocks.CRIMSON_NYLIUM, new Pair<>(AtbywBlocks.CRIMSON_NYLIUM_SLAB, 6));
        table.put("slab", Blocks.WARPED_NYLIUM, new Pair<>(AtbywBlocks.WARPED_NYLIUM_SLAB, 6));
        table.put("slab", Blocks.GRANITE, new Pair<>(AtbywBlocks.GRANITE_TILES_SLAB, 6));
        table.put("slab", Blocks.DIORITE, new Pair<>(AtbywBlocks.DIORITE_BRICKS_SLAB, 6));
        table.put("slab", Blocks.ANDESITE, new Pair<>(AtbywBlocks.ANDESITE_BRICKS_SLAB, 6));
        table.put("slab", Blocks.TERRACOTTA, new Pair<>(AtbywBlocks.TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.WHITE_TERRACOTTA, new Pair<>(AtbywBlocks.WHITE_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.ORANGE_TERRACOTTA, new Pair<>(AtbywBlocks.ORANGE_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.MAGENTA_TERRACOTTA, new Pair<>(AtbywBlocks.MAGENTA_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.LIGHT_BLUE_TERRACOTTA, new Pair<>(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.YELLOW_TERRACOTTA, new Pair<>(AtbywBlocks.YELLOW_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.LIME_TERRACOTTA, new Pair<>(AtbywBlocks.LIME_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.PINK_TERRACOTTA, new Pair<>(AtbywBlocks.PINK_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.GRAY_TERRACOTTA, new Pair<>(AtbywBlocks.GRAY_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.LIGHT_GRAY_TERRACOTTA, new Pair<>(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.CYAN_TERRACOTTA, new Pair<>(AtbywBlocks.CYAN_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.PURPLE_TERRACOTTA, new Pair<>(AtbywBlocks.PURPLE_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.BLUE_TERRACOTTA, new Pair<>(AtbywBlocks.BLUE_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.BROWN_TERRACOTTA, new Pair<>(AtbywBlocks.BROWN_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.GREEN_TERRACOTTA, new Pair<>(AtbywBlocks.GREEN_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.RED_TERRACOTTA, new Pair<>(AtbywBlocks.RED_TERRACOTTA_SLAB, 6));
        table.put("slab", Blocks.BLACK_TERRACOTTA, new Pair<>(AtbywBlocks.BLACK_TERRACOTTA_SLAB, 6));
        table.put("slab", AtbywBlocks.TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.WHITE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.WHITE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.LIME_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.LIME_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.PINK_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.PINK_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.GRAY_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.GRAY_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.CYAN_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.CYAN_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.BLUE_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.BLUE_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.BROWN_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.BROWN_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.GREEN_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.GREEN_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.RED_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.RED_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.BLACK_TERRACOTTA_BRICKS, new Pair<>(AtbywBlocks.BLACK_TERRACOTTA_BRICKS_SLAB, 6));
        table.put("slab", Blocks.WHITE_CONCRETE, new Pair<>(AtbywBlocks.WHITE_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.ORANGE_CONCRETE, new Pair<>(AtbywBlocks.ORANGE_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.MAGENTA_CONCRETE, new Pair<>(AtbywBlocks.MAGENTA_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.LIGHT_BLUE_CONCRETE, new Pair<>(AtbywBlocks.LIGHT_BLUE_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.YELLOW_CONCRETE, new Pair<>(AtbywBlocks.YELLOW_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.LIME_CONCRETE, new Pair<>(AtbywBlocks.LIME_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.PINK_CONCRETE, new Pair<>(AtbywBlocks.PINK_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.GRAY_CONCRETE, new Pair<>(AtbywBlocks.GRAY_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.LIGHT_GRAY_CONCRETE, new Pair<>(AtbywBlocks.LIGHT_GRAY_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.CYAN_CONCRETE, new Pair<>(AtbywBlocks.CYAN_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.PURPLE_CONCRETE, new Pair<>(AtbywBlocks.PURPLE_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.BLUE_CONCRETE, new Pair<>(AtbywBlocks.BLUE_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.BROWN_CONCRETE, new Pair<>(AtbywBlocks.BROWN_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.GREEN_CONCRETE, new Pair<>(AtbywBlocks.GREEN_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.RED_CONCRETE, new Pair<>(AtbywBlocks.RED_CONCRETE_SLAB, 6));
        table.put("slab", Blocks.BLACK_CONCRETE, new Pair<>(AtbywBlocks.BLACK_CONCRETE_SLAB, 6));
        table.put("slab", AtbywBlocks.PURPUR_TILES, new Pair<>(AtbywBlocks.PURPUR_TILES_SLAB, 6));
        table.put("slab", AtbywBlocks.CUT_PURPUR_BLOCK, new Pair<>(AtbywBlocks.CUT_PURPUR_SLAB, 6));
        table.put("slab", AtbywBlocks.SMOOTH_PURPUR_BLOCK, new Pair<>(AtbywBlocks.SMOOTH_PURPUR_SLAB, 6));
        table.put("slab", AtbywBlocks.COMPACTED_SNOW_BLOCK, new Pair<>(AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB, 6));
        table.put("slab", AtbywBlocks.COMPACTED_SNOW_BRICKS, new Pair<>(AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB, 6));
        table.put("slab", Blocks.PACKED_ICE, new Pair<>(AtbywBlocks.PACKED_ICE_SLAB, 6));
        table.put("slab", Blocks.BLUE_ICE, new Pair<>(AtbywBlocks.BLUE_ICE_SLAB, 6));
        table.put("slab", AtbywBlocks.PACKED_ICE_BRICKS, new Pair<>(AtbywBlocks.PACKED_ICE_BRICKS_SLAB, 6));
        table.put("slab", AtbywBlocks.BLUE_ICE_BRICKS, new Pair<>(AtbywBlocks.BLUE_ICE_BRICKS_SLAB, 6));
        table.put("slab", Blocks.SAND, new Pair<>(AtbywBlocks.SAND_SLAB, 6));
        table.put("slab", Blocks.RED_SAND, new Pair<>(AtbywBlocks.RED_SAND_SLAB, 6));
        table.put("slab", Blocks.GRAVEL, new Pair<>(AtbywBlocks.GRAVEL_SLAB, 6));
        table.put("slab", Blocks.ROOTED_DIRT, new Pair<>(AtbywBlocks.ROOTED_DIRT_SLAB, 6));
        table.put("slab", Blocks.OAK_LOG, new Pair<>(AtbywBlocks.OAK_LOG_SLAB, 6));
        table.put("slab", Blocks.SPRUCE_LOG, new Pair<>(AtbywBlocks.SPRUCE_LOG_SLAB, 6));
        table.put("slab", Blocks.BIRCH_LOG, new Pair<>(AtbywBlocks.BIRCH_LOG_SLAB, 6));
        table.put("slab", Blocks.JUNGLE_LOG, new Pair<>(AtbywBlocks.JUNGLE_LOG_SLAB, 6));
        table.put("slab", Blocks.ACACIA_LOG, new Pair<>(AtbywBlocks.ACACIA_LOG_SLAB, 6));
        table.put("slab", Blocks.DARK_OAK_LOG, new Pair<>(AtbywBlocks.DARK_OAK_LOG_SLAB, 6));
        table.put("slab", Blocks.CRIMSON_STEM, new Pair<>(AtbywBlocks.CRIMSON_STEM_SLAB, 6));
        table.put("slab", Blocks.WARPED_STEM, new Pair<>(AtbywBlocks.WARPED_STEM_SLAB, 6));
        table.put("slab", Blocks.STRIPPED_OAK_LOG, new Pair<>(AtbywBlocks.STRIPPED_OAK_LOG_SLAB, 6));
        table.put("slab", Blocks.STRIPPED_SPRUCE_LOG, new Pair<>(AtbywBlocks.STRIPPED_SPRUCE_LOG_SLAB, 6));
        table.put("slab", Blocks.STRIPPED_BIRCH_LOG, new Pair<>(AtbywBlocks.STRIPPED_BIRCH_LOG_SLAB, 6));
        table.put("slab", Blocks.STRIPPED_JUNGLE_LOG, new Pair<>(AtbywBlocks.STRIPPED_JUNGLE_LOG_SLAB, 6));
        table.put("slab", Blocks.STRIPPED_ACACIA_LOG, new Pair<>(AtbywBlocks.STRIPPED_ACACIA_LOG_SLAB, 6));
        table.put("slab", Blocks.STRIPPED_DARK_OAK_LOG, new Pair<>(AtbywBlocks.STRIPPED_DARK_OAK_LOG_SLAB, 6));
        table.put("slab", Blocks.STRIPPED_CRIMSON_STEM, new Pair<>(AtbywBlocks.STRIPPED_CRIMSON_STEM_SLAB, 6));
        table.put("slab", Blocks.STRIPPED_WARPED_STEM, new Pair<>(AtbywBlocks.STRIPPED_WARPED_STEM_SLAB, 6));
        table.put("", AtbywBlocks.COMPACTED_SNOW_BLOCK, new Pair<>(AtbywBlocks.COMPACTED_SNOW, 6));

        var pattern = new String[] {"###"};
        for (var component : table.cellSet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('#', Ingredient.ofItems(component.getColumnKey().asItem()));
            registerShapedRecipe("", component.getRowKey(), pattern, keys, component.getValue().getFirst(), component.getValue().getSecond());
        }
    }

    public static void registerBricksPatterns() {
        //Table<Category, Input, Pair<Output, Count>>
        var table = HashBasedTable.<String, ItemConvertible, Pair<ItemConvertible, Integer>>create();
        table.put("bricks", Blocks.POLISHED_GRANITE, new Pair<>(AtbywBlocks.GRANITE_TILES, 4));
        table.put("bricks", Blocks.POLISHED_DIORITE, new Pair<>(AtbywBlocks.DIORITE_BRICKS, 4));
        table.put("bricks", Blocks.POLISHED_ANDESITE, new Pair<>(AtbywBlocks.ANDESITE_BRICKS, 4));
        table.put("bricks", Blocks.BASALT, new Pair<>(AtbywBlocks.BASALT_BRICKS, 4));
        table.put("bricks", Blocks.POLISHED_BASALT, new Pair<>(AtbywBlocks.BASALT_BRICKS, 4));
        table.put("bricks", Blocks.TERRACOTTA, new Pair<>(AtbywBlocks.TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.WHITE_TERRACOTTA, new Pair<>(AtbywBlocks.WHITE_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.ORANGE_TERRACOTTA, new Pair<>(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.MAGENTA_TERRACOTTA, new Pair<>(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.LIGHT_BLUE_TERRACOTTA, new Pair<>(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.YELLOW_TERRACOTTA, new Pair<>(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.LIME_TERRACOTTA, new Pair<>(AtbywBlocks.LIME_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.PINK_TERRACOTTA, new Pair<>(AtbywBlocks.PINK_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.GRAY_TERRACOTTA, new Pair<>(AtbywBlocks.GRAY_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.LIGHT_GRAY_TERRACOTTA, new Pair<>(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.CYAN_TERRACOTTA, new Pair<>(AtbywBlocks.CYAN_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.PURPLE_TERRACOTTA, new Pair<>(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.BLUE_TERRACOTTA, new Pair<>(AtbywBlocks.BLUE_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.BROWN_TERRACOTTA, new Pair<>(AtbywBlocks.BROWN_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.GREEN_TERRACOTTA, new Pair<>(AtbywBlocks.GREEN_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.RED_TERRACOTTA, new Pair<>(AtbywBlocks.RED_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.BLACK_TERRACOTTA, new Pair<>(AtbywBlocks.BLACK_TERRACOTTA_BRICKS, 4));
        table.put("bricks", Blocks.WHITE_CONCRETE, new Pair<>(AtbywBlocks.WHITE_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.ORANGE_CONCRETE, new Pair<>(AtbywBlocks.ORANGE_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.MAGENTA_CONCRETE, new Pair<>(AtbywBlocks.MAGENTA_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.LIGHT_BLUE_CONCRETE, new Pair<>(AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.YELLOW_CONCRETE, new Pair<>(AtbywBlocks.YELLOW_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.LIME_CONCRETE, new Pair<>(AtbywBlocks.LIME_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.PINK_CONCRETE, new Pair<>(AtbywBlocks.PINK_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.GRAY_CONCRETE, new Pair<>(AtbywBlocks.GRAY_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.LIGHT_GRAY_CONCRETE, new Pair<>(AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.CYAN_CONCRETE, new Pair<>(AtbywBlocks.CYAN_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.PURPLE_CONCRETE, new Pair<>(AtbywBlocks.PURPLE_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.BLUE_CONCRETE, new Pair<>(AtbywBlocks.BLUE_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.BROWN_CONCRETE, new Pair<>(AtbywBlocks.BROWN_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.GREEN_CONCRETE, new Pair<>(AtbywBlocks.GREEN_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.RED_CONCRETE, new Pair<>(AtbywBlocks.RED_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.BLACK_CONCRETE, new Pair<>(AtbywBlocks.BLACK_CINDER_BLOCKS, 4));
        table.put("bricks", Blocks.PURPUR_PILLAR, new Pair<>(AtbywBlocks.PURPUR_TILES, 4));
        table.put("bricks", Blocks.PURPUR_BLOCK, new Pair<>(AtbywBlocks.CUT_PURPUR_BLOCK, 4));
        table.put("bricks", Blocks.SNOW_BLOCK, new Pair<>(AtbywBlocks.COMPACTED_SNOW_BLOCK, 4));
        table.put("bricks", AtbywBlocks.COMPACTED_SNOW_BLOCK, new Pair<>(AtbywBlocks.COMPACTED_SNOW_BRICKS, 4));
        table.put("bricks", Blocks.PACKED_ICE, new Pair<>(AtbywBlocks.PACKED_ICE_BRICKS, 4));
        table.put("bricks", Blocks.BLUE_ICE, new Pair<>(AtbywBlocks.BLUE_ICE_BRICKS, 4));
        table.put("shattered_glass", AtbywItems.GLASS_SHARD, new Pair<>(AtbywBlocks.SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.WHITE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.WHITE_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.ORANGE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.ORANGE_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.MAGENTA_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.MAGENTA_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.LIGHT_BLUE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.LIGHT_BLUE_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.YELLOW_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.YELLOW_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.LIME_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.LIME_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.PINK_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.PINK_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.GRAY_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.GRAY_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.LIGHT_GRAY_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.LIGHT_GRAY_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.CYAN_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.CYAN_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.PURPLE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.PURPLE_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.BLUE_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.BLUE_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.BROWN_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.BROWN_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.GREEN_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.GREEN_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.RED_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.RED_STAINED_SHATTERED_GLASS, 1));
        table.put("shattered_glass", AtbywItems.BLACK_STAINED_GLASS_SHARD, new Pair<>(AtbywBlocks.BLACK_STAINED_SHATTERED_GLASS, 1));

        var pattern = new String[] {"##", "##"};
        for (var component : table.cellSet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('#', Ingredient.ofItems(component.getColumnKey().asItem()));
            registerShapedRecipe("", component.getRowKey(), pattern, keys, component.getValue().getFirst(), component.getValue().getSecond());
        }
    }

    public static void registerWallPatterns() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(AtbywBlocks.TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.WHITE_TERRACOTTA_BRICKS, AtbywBlocks.WHITE_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.LIME_TERRACOTTA_BRICKS, AtbywBlocks.LIME_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.PINK_TERRACOTTA_BRICKS, AtbywBlocks.PINK_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.GRAY_TERRACOTTA_BRICKS, AtbywBlocks.GRAY_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.CYAN_TERRACOTTA_BRICKS, AtbywBlocks.CYAN_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.BLUE_TERRACOTTA_BRICKS, AtbywBlocks.BLUE_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.BROWN_TERRACOTTA_BRICKS, AtbywBlocks.BROWN_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.GREEN_TERRACOTTA_BRICKS, AtbywBlocks.GREEN_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.RED_TERRACOTTA_BRICKS, AtbywBlocks.RED_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.BLACK_TERRACOTTA_BRICKS, AtbywBlocks.BLACK_TERRACOTTA_BRICKS_WALL);
        map.put(AtbywBlocks.WHITE_CINDER_BLOCKS, AtbywBlocks.WHITE_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.ORANGE_CINDER_BLOCKS, AtbywBlocks.ORANGE_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.MAGENTA_CINDER_BLOCKS, AtbywBlocks.MAGENTA_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS, AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.YELLOW_CINDER_BLOCKS, AtbywBlocks.YELLOW_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.LIME_CINDER_BLOCKS, AtbywBlocks.LIME_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.PINK_CINDER_BLOCKS, AtbywBlocks.PINK_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.GRAY_CINDER_BLOCKS, AtbywBlocks.GRAY_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS, AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.CYAN_CINDER_BLOCKS, AtbywBlocks.CYAN_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.PURPLE_CINDER_BLOCKS, AtbywBlocks.PURPLE_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.BLUE_CINDER_BLOCKS, AtbywBlocks.BLUE_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.BROWN_CINDER_BLOCKS, AtbywBlocks.BROWN_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.GREEN_CINDER_BLOCKS, AtbywBlocks.GREEN_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.RED_CINDER_BLOCKS, AtbywBlocks.RED_CINDER_BLOCKS_WALL);
        map.put(AtbywBlocks.BLACK_CINDER_BLOCKS, AtbywBlocks.BLACK_CINDER_BLOCKS_WALL);

        var pattern = new String[] {"###", "###"};
        for (var component : map.entrySet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('#', Ingredient.ofItems(component.getKey().asItem()));
            registerShapedRecipe("", "wall", pattern, keys, component.getValue(), 6);
        }
    }

    public static void registerDyingPatterns() {
        //Map<Output, Input>
        var ringMap = new HashMap<ItemConvertible, ItemConvertible>();
        ringMap.put(AtbywBlocks.WHITE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.ORANGE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.MAGENTA_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.YELLOW_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.LIME_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.PINK_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.GRAY_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.CYAN_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.PURPLE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.BLUE_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.BROWN_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.GREEN_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.RED_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.BLACK_TERRACOTTA_STAIRS, AtbywBlocks.TERRACOTTA_STAIRS);
        ringMap.put(AtbywBlocks.WHITE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.ORANGE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.MAGENTA_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.YELLOW_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.LIME_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.PINK_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.GRAY_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.CYAN_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.PURPLE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.BLUE_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.BROWN_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.GREEN_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.RED_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.BLACK_TERRACOTTA_SLAB, AtbywBlocks.TERRACOTTA_SLAB);
        ringMap.put(AtbywBlocks.WHITE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.LIME_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.PINK_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.GRAY_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.CYAN_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.BLUE_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.BROWN_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.GREEN_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.RED_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.BLACK_TERRACOTTA_BRICKS, AtbywBlocks.TERRACOTTA_BRICKS);
        ringMap.put(AtbywBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.LIME_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.PINK_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.RED_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS);
        ringMap.put(AtbywBlocks.WHITE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.LIME_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.PINK_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.GRAY_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.CYAN_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.BLUE_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.BROWN_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.GREEN_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.RED_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.BLACK_TERRACOTTA_BRICKS_SLAB, AtbywBlocks.TERRACOTTA_BRICKS_SLAB);
        ringMap.put(AtbywBlocks.WHITE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.LIME_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.PINK_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.GRAY_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.CYAN_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.BLUE_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.BROWN_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.GREEN_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.RED_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);
        ringMap.put(AtbywBlocks.BLACK_TERRACOTTA_BRICKS_WALL, AtbywBlocks.TERRACOTTA_BRICKS_WALL);

        //Map<Pair<Input outer ring, Input middle>, Output>
        var lanternMap = new HashMap<Pair<ItemConvertible, ItemConvertible>, ItemConvertible>();
        lanternMap.put(new Pair<>(Items.IRON_NUGGET, Items.REDSTONE_TORCH), AtbywBlocks.REDSTONE_LANTERN);

        var pattern = new String[] {"###", "#D#", "###"};
        for (var dye : AtbywUtils.DYES) {
            for (var component : ringMap.entrySet()) {
                var keys = new HashMap<Character, Ingredient>();
                keys.put('#', Ingredient.ofItems(component.getValue().asItem()));
                keys.put('D', Ingredient.ofItems(dye));
                registerShapedRecipe("from_dying", "dying", pattern, keys, component.getKey(), 8);
            }
        }
        for (var component : lanternMap.entrySet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('#', Ingredient.ofItems(component.getKey().getFirst().asItem()));
            keys.put('D', Ingredient.ofItems(component.getKey().getSecond().asItem()));
            registerShapedRecipe("", "", pattern, keys, component.getValue(), 8);
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

        var pattern = new String[] {"#", "#", "#"};
        for (var component : table.cellSet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('#', Ingredient.ofItems(component.getColumnKey().asItem()));
            registerShapedRecipe("", component.getRowKey(), pattern, keys, component.getValue().getFirst(), component.getValue().getSecond());
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

        var pattern = new String[] {" F ", "RSR"};
        for (var component : map.entrySet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('F', Ingredient.ofItems(component.getKey().asItem()));
            keys.put('S', Ingredient.ofItems(Items.STICK));
            keys.put('R', Ingredient.ofItems(Items.REDSTONE));
            registerShapedRecipe("", "", pattern, keys, component.getValue(), 1);
        }
    }

    public static void registerStickPatterns() {
        //Table<Suffix, Input, Pair<Output, Count>>
        var table = HashBasedTable.<String, ItemConvertible, Pair<ItemConvertible, Integer>>create();
        table.put("from_basalt", Blocks.BASALT, new Pair<>(AtbywBlocks.BASALT_PILLAR, 2));
        table.put("from_polished_basalt", Blocks.POLISHED_BASALT, new Pair<>(AtbywBlocks.BASALT_PILLAR, 2));
        table.put("", Items.BAMBOO, new Pair<>(AtbywItems.BAMBOO_STICK, 4));
        table.put("", Blocks.SHROOMLIGHT, new Pair<>(AtbywItems.SHROOMSTICK, 4));
        table.put("", Blocks.PURPUR_SLAB, new Pair<>(AtbywBlocks.CHISELED_PURPUR_BLOCK, 1));

        var pattern = new String[] {"#", "#"};
        for (var component : table.cellSet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('#', Ingredient.ofItems(component.getColumnKey().asItem()));
            registerShapedRecipe(component.getRowKey(), "", pattern, keys, component.getValue().getFirst(), component.getValue().getSecond());
        }
    }

    public static void registerSpikeTraps() {
        //Map<Input, Output>
        var map = new HashMap<ItemConvertible, ItemConvertible>();
        map.put(Items.IRON_INGOT, AtbywBlocks.IRON_SPIKE_TRAP);
        map.put(Items.GOLD_INGOT, AtbywBlocks.GOLD_SPIKE_TRAP);
        map.put(Items.DIAMOND, AtbywBlocks.DIAMOND_SPIKE_TRAP);
        map.put(Items.NETHERITE_INGOT, AtbywBlocks.NETHERITE_SPIKE_TRAP);

        var pattern = new String[] {"S#S", "#I#", "SRS"};
        for (var component : map.entrySet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('#', Ingredient.ofItems(component.getKey().asItem()));
            keys.put('R', Ingredient.ofItems(Items.REDSTONE));
            keys.put('I', Ingredient.ofItems(Items.IRON_INGOT));
            keys.put('S', Ingredient.fromTag(ItemTags.STONE_CRAFTING_MATERIALS));
            registerShapedRecipe("", "redstone", pattern, keys, component.getValue(), 1);
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

        var pattern = new String[] {"# #", "# #", "# #"};
        for (var component : table.cellSet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('#', Ingredient.ofItems(component.getColumnKey().asItem()));
            registerShapedRecipe("", component.getRowKey(), pattern, keys, component.getValue(), 3);
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

        var pattern = new String[] {"PPP", "RBR", "CRC"};
        for (var component : map.entrySet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('P', Ingredient.ofItems(component.getKey().asItem()));
            keys.put('R', Ingredient.ofItems(Items.REDSTONE));
            keys.put('B', Ingredient.ofItems(Items.BOOK));
            keys.put('C', Ingredient.fromTag(ItemTags.STONE_CRAFTING_MATERIALS));
            registerShapedRecipe("", "redstone", pattern, keys, component.getValue(), 1);
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

        var pattern = new String[] {"PPP", "BBB", "PPP"};
        for (var component : map.entrySet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('P', Ingredient.ofItems(component.getKey().asItem()));
            keys.put('B', Ingredient.ofItems(Items.BOOK));
            registerShapedRecipe("", "", pattern, keys, component.getValue(), 1);
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

        var pattern = new String[] {"P P", "PPP", "P P"};
        for (var component : map.entrySet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('P', Ingredient.ofItems(component.getKey().asItem()));
            registerShapedRecipe("", "ladders", pattern, keys, component.getValue(), 6);
        }
        //Bamboo Ladder
        var keys = new HashMap<Character, Ingredient>();
        keys.put('B', Ingredient.ofItems(Items.BAMBOO));
        keys.put('S', Ingredient.ofItems(AtbywItems.BAMBOO_STICK));
        registerShapedRecipe("", "ladders", new String[] {"B B", "BSB", "B B"}, keys, AtbywBlocks.BAMBOO_LADDER, 3);
    }

    public static void registerTorchPatterns() {
        //Map<Pair<Input "coal", Input "stick">, Output>
        var map = new HashMap<Pair<ItemConvertible, ItemConvertible>, ItemConvertible>();
        map.put(new Pair<>(Items.CARVED_PUMPKIN, Items.SOUL_TORCH), AtbywBlocks.SOUL_JACK_O_LANTERN);
        map.put(new Pair<>(Items.CARVED_PUMPKIN, Items.REDSTONE_TORCH), AtbywBlocks.REDSTONE_JACK_O_LANTERN);

        var pattern = new String[] {"C", "S"};
        for (var component : map.entrySet()) {
            var keys = new HashMap<Character, Ingredient>();
            keys.put('C', Ingredient.ofItems(component.getKey().getFirst().asItem()));
            keys.put('S', Ingredient.ofItems(component.getKey().getSecond().asItem()));
            registerShapedRecipe("", "", pattern, keys, component.getValue(), 1);
        }
    }

    public static void registerMisc() {
        var keys = new HashMap<Character, Ingredient>();

        //Timer Repeater using Clock
        keys.put('C', Ingredient.ofItems(Items.CLOCK));
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('#', Ingredient.ofItems(Items.REPEATER));
        registerShapedRecipe("from_clock", "redstone", new String[] {" C ", "R#R", " R "}, keys, AtbywBlocks.TIMER_REPEATER, 1);

        //Timer Repeater Manual
        keys.clear();
        keys.put('G', Ingredient.ofItems(Items.GOLD_INGOT));
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('#', Ingredient.ofItems(Items.REPEATER));
        registerShapedRecipe("", "redstone", new String[] {"GRG", "R#R", "GRG"}, keys, AtbywBlocks.TIMER_REPEATER, 1);

        //Redstone Cross Path
        keys.clear();
        keys.put('R', Ingredient.ofItems(Items.REDSTONE));
        keys.put('S', Ingredient.ofItems(Blocks.STONE));
        registerShapedRecipe("", "redstone", new String[] {"RRR", "SSS"}, keys, AtbywBlocks.REDSTONE_CROSS_PATH, 1);

        //Marge Chain Link
        keys.clear();
        keys.put('I', Ingredient.ofItems(Items.IRON_INGOT));
        registerShapedRecipe("", "", new String[] {" I ", "I I", " I "}, keys, AtbywItems.LARGE_CHAIN_LINK, 2);

    }

    public static void registerShapedRecipe(String suffix, String category, String[] pattern, Map<Character, Ingredient> keys, ItemConvertible result, int count) {
        var recipe = (Recipe<?>) null;

        for (var line : pattern) {
            if (line.contains(" ")) {
                keys.put(' ', Ingredient.EMPTY);
            }
        }

        var recipeId = AtbywUtils.getItemID(result.asItem());
        recipe = Datagen.shapedRecipe(new AtbywIdentifier(recipeId.getPath() + "_" + suffix), "", pattern, keys, result.asItem(), count);
        Datagen.registerRecipe(recipe, category);
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        RecipeSmeltingRegistry.inject(map);
        RecipeStonecuttingRegistry.inject(map);
    }
}