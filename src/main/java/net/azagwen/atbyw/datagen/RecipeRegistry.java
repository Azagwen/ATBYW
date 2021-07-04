package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;
import com.google.gson.*;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.main.AtbywIdentifier;
import net.azagwen.atbyw.util.AtbywUtils;
import net.azagwen.atbyw.util.Pair;
import net.azagwen.atbyw.util.Quadruplet;
import net.azagwen.atbyw.util.naming.ColorNames;
import net.azagwen.atbyw.util.naming.FlowerNames;
import net.azagwen.atbyw.util.naming.WoodNames;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static net.azagwen.atbyw.datagen.RecipeUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class RecipeRegistry {
    static Gson builder = new GsonBuilder().setPrettyPrinting().create();
    public static Logger LOGGER = LogManager.getLogger("Atbyw Recipes");

    //Shaped recipes config methods
    @SafeVarargs
    private static Recipe<?> createRecipeFromConfig(Identifier recipeId, String group, int count, RecipeConfig config, ItemConvertible result, Pair<String, ItemConvertible>... ingredients) {
        var keys = Lists.<Pair<Character, ItemConvertible>>newArrayList();

        for (var ingredient : ingredients) {
            for (var chara : config.getKeyChars()) {
                keys.add(new Pair<>(chara, ingredient.getSecond()));
            }
        }
        keys.add(new Pair<>(' ', ItemStack.EMPTY.getItem()));
//        throw new IllegalArgumentException("Character and Ingredient lengths do not match for " + recipeId.toString());

        return Datagen.shapedRecipe(recipeId, group, config.getPattern().toArray(String[]::new), keys, result, count);
    }

    @SafeVarargs
    private static Recipe<?> createRecipeFromConfig(Identifier recipeId, String group, RecipeConfig config, ItemConvertible result, Pair<String, ItemConvertible>... ingredient) {
        return createRecipeFromConfig(recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    private static List<Recipe<?>> createMultiRecipesFromConfig(String[] nameArray, Identifier recipeId, String group, int count, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredients) {
        var recipes = Lists.<Recipe<?>>newArrayList();
        var ing = Lists.<Pair<String, ItemConvertible>>newArrayList();

        int i = 0;
        for (var prefix : nameArray) {
            var newRecipeId = new Identifier(recipeId.getNamespace(), prefix + "_" + recipeId.getPath());
            var fullName = "";

            int j = 0;
            for (var ingredient : ingredients) {
                var colorizeIngredient = ingredient.getFourth();
                var pathName = ingredient.getThird();

                if (colorizeIngredient) {
                    fullName = !pathName.isEmpty() ? (prefix + "_" + pathName) : prefix;
                } else {
                    fullName = pathName;
                }

                ing.add(newKeyPair(ingredient.getFirst(), AtbywUtils.getItemFromID(new Identifier(ingredient.getSecond(), fullName))));
                j++;
            }
            recipes.add(createRecipeFromConfig(newRecipeId, group, count, config, AtbywUtils.getItemFromID(new Identifier(result.getFirst(), prefix + "_" + result.getSecond())), ing.toArray(Pair[]::new)));
            i++;
        }
        return recipes;
    }

    @SafeVarargs
    private static List<Recipe<?>> createMultiRecipesFromConfig(String[] nameArray, Identifier recipeId, String group, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray, recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    private static List<Recipe<?>> createMultiRecipesFromConfig(List<String> nameArray, Identifier recipeId, String group, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray.toArray(String[]::new), recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    private static List<Recipe<?>> createMultiRecipesFromConfig(List<String> nameArray, Identifier recipeId, String group, int count, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray.toArray(String[]::new), recipeId, group, count, config, result, ingredient);
    }


    public static Recipe<?> DIRT_STAIRS = createRecipeFromConfig(new AtbywIdentifier("dirt_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.DIRT_STAIRS, newKeyPair("item", Blocks.DIRT));
    public static Recipe<?> GRASS_BLOCK_STAIRS = createRecipeFromConfig(new AtbywIdentifier("grass_block_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.GRASS_BLOCK_STAIRS, newKeyPair("item", Blocks.GRASS_BLOCK));
    public static Recipe<?> MYCELIUM_STAIRS = createRecipeFromConfig(new AtbywIdentifier("mycelium_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.MYCELIUM_STAIRS, newKeyPair("item", Blocks.MYCELIUM));
    public static Recipe<?> COARSE_DIRT_STAIRS = createRecipeFromConfig(new AtbywIdentifier("coarse_dirt_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.COARSE_DIRT_STAIRS, newKeyPair("item", Blocks.COARSE_DIRT));
    public static Recipe<?> PODZOL_STAIRS = createRecipeFromConfig(new AtbywIdentifier("podzol_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.PODZOL_STAIRS, newKeyPair("item", Blocks.PODZOL));
    public static Recipe<?> NETHERRACK_STAIRS = createRecipeFromConfig(new AtbywIdentifier("netherrack_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.NETHERRACK_STAIRS, newKeyPair("item", Blocks.NETHERRACK));
    public static Recipe<?> CRIMSON_NYLIUM_STAIRS = createRecipeFromConfig(new AtbywIdentifier("crimson_nylium_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.CRIMSON_NYLIUM_STAIRS, newKeyPair("item", Blocks.CRIMSON_NYLIUM));
    public static Recipe<?> WARPED_NYLIUM_STAIRS = createRecipeFromConfig(new AtbywIdentifier("warped_nylium_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.WARPED_NYLIUM_STAIRS, newKeyPair("item", Blocks.WARPED_NYLIUM));

    public static Recipe<?> DIRT_SLAB = createRecipeFromConfig(new AtbywIdentifier("dirt_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.DIRT_SLAB, newKeyPair("item", Blocks.DIRT));
    public static Recipe<?> GRASS_BLOCK_SLAB = createRecipeFromConfig(new AtbywIdentifier("grass_block_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.GRASS_BLOCK_SLAB, newKeyPair("item", Blocks.GRASS_BLOCK));
    public static Recipe<?> MYCELIUM_SLAB = createRecipeFromConfig(new AtbywIdentifier("mycelium_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.MYCELIUM_SLAB, newKeyPair("item", Blocks.MYCELIUM));
    public static Recipe<?> COARSE_DIRT_SLAB = createRecipeFromConfig(new AtbywIdentifier("coarse_dirt_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.COARSE_DIRT_SLAB, newKeyPair("item", Blocks.COARSE_DIRT));
    public static Recipe<?> PODZOL_SLAB = createRecipeFromConfig(new AtbywIdentifier("podzol_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.PODZOL_SLAB, newKeyPair("item", Blocks.PODZOL));
    public static Recipe<?> NETHERRACK_SLAB = createRecipeFromConfig(new AtbywIdentifier("netherrack_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.NETHERRACK_SLAB, newKeyPair("item", Blocks.NETHERRACK));
    public static Recipe<?> CRIMSON_NYLIUM_SLAB = createRecipeFromConfig(new AtbywIdentifier("crimson_nylium_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.CRIMSON_NYLIUM_SLAB, newKeyPair("item", Blocks.CRIMSON_NYLIUM));
    public static Recipe<?> WARPED_NYLIUM_SLAB = createRecipeFromConfig(new AtbywIdentifier("warped_nylium_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.WARPED_NYLIUM_SLAB, newKeyPair("item", Blocks.WARPED_NYLIUM));

    public static Recipe<?> BASALT_BRICKS_FROM_BASALT = createRecipeFromConfig(new AtbywIdentifier("basalt_bricks_from_basalt"), "basalt_bricks", RecipeConfigs.BRICKS_1, AtbywBlocks.BASALT_BRICKS, newKeyPair("item", Blocks.BASALT));
    public static Recipe<?> BASALT_PILLAR_FROM_BASALT = createRecipeFromConfig(new AtbywIdentifier("basalt_pillar_from_basalt"), "basalt_bricks", RecipeConfigs.BRICKS_1, AtbywBlocks.BASALT_BRICKS, newKeyPair("item", Blocks.BASALT));
    public static Recipe<?> BASALT_BRICKS_FROM_POLISHED_BASALT = createRecipeFromConfig(new AtbywIdentifier("basalt_bricks_from_polished_basalt"), "basalt_pillar", 2, RecipeConfigs.STICK_1, AtbywBlocks.BASALT_PILLAR, newKeyPair("item", Blocks.POLISHED_BASALT));
    public static Recipe<?> BASALT_PILLAR_FROM_POLISHED_BASALT = createRecipeFromConfig(new AtbywIdentifier("basalt_pillar_from_polished_basalt"), "basalt_pillar", 2, RecipeConfigs.STICK_1, AtbywBlocks.BASALT_PILLAR, newKeyPair("item", Blocks.POLISHED_BASALT));

    public static Recipe<?> IRON_FENCE_DOOR = createRecipeFromConfig(new AtbywIdentifier("iron_fence_door"), "fence_door", RecipeConfigs.FENCE_DOOR_1, AtbywBlocks.IRON_FENCE_DOOR, newKeyPair("item", Items.IRON_INGOT));
    public static Recipe<?> BAMBOO_LADDER = createRecipeFromConfig(new AtbywIdentifier("bamboo_ladder"), "ladders", 3, RecipeConfigs.RAIL_2, AtbywBlocks.BAMBOO_LADDER, newKeyPair("item", AtbywItems.BAMBOO_STICK), newKeyPair("item", Items.BAMBOO));
    public static Recipe<?> BAMBOO_STICK = createRecipeFromConfig(new AtbywIdentifier("bamboo_stick"), "stick", RecipeConfigs.STICK_1, AtbywItems.BAMBOO_STICK, newKeyPair("item", Items.BAMBOO));
    public static Recipe<?> REDSTONE_LANTERN = createRecipeFromConfig(new AtbywIdentifier("redstone_lantern"), "", RecipeConfigs.DYING_2, AtbywBlocks.REDSTONE_LANTERN, newKeyPair("item", Items.IRON_NUGGET), newKeyPair("item", Items.REDSTONE_TORCH));
    public static Recipe<?> SHROOM_STICK = createRecipeFromConfig(new AtbywIdentifier("shroomstick"), "", RecipeConfigs.STICK_1, AtbywItems.SHROOMSTICK, newKeyPair("item", Blocks.SHROOMLIGHT));

    public static Recipe<?> GRANITE_TILES = createRecipeFromConfig(new AtbywIdentifier("granite_tiles"), "", RecipeConfigs.BRICKS_1, AtbywBlocks.GRANITE_TILES, newKeyPair("item", Blocks.GRANITE));
    public static Recipe<?> DIORITE_BRICKS = createRecipeFromConfig(new AtbywIdentifier("diorite_bricks"), "", RecipeConfigs.BRICKS_1, AtbywBlocks.DIORITE_BRICKS, newKeyPair("item", Blocks.DIORITE));
    public static Recipe<?> ANDESITE_BRICKS = createRecipeFromConfig(new AtbywIdentifier("andesite_bricks"), "", RecipeConfigs.BRICKS_1, AtbywBlocks.ANDESITE_BRICKS, newKeyPair("item", Blocks.ANDESITE));

    public static Recipe<?> GRANITE_TILES_STAIRS = createRecipeFromConfig(new AtbywIdentifier("granite_tiles_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.GRANITE_TILES_STAIRS, newKeyPair("item", AtbywBlocks.GRANITE_TILES));
    public static Recipe<?> DIORITE_BRICKS_STAIRS = createRecipeFromConfig(new AtbywIdentifier("diorite_bricks_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.DIORITE_BRICKS_STAIRS, newKeyPair("item", AtbywBlocks.DIORITE_BRICKS));
    public static Recipe<?> ANDESITE_BRICKS_STAIRS = createRecipeFromConfig(new AtbywIdentifier("andesite_bricks_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.ANDESITE_BRICKS_STAIRS, newKeyPair("item", AtbywBlocks.ANDESITE_BRICKS));

    public static Recipe<?> GRANITE_TILES_SLAB = createRecipeFromConfig(new AtbywIdentifier("granite_tiles_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.GRANITE_TILES_SLAB, newKeyPair("item", AtbywBlocks.GRANITE_TILES));
    public static Recipe<?> DIORITE_BRICKS_SLAB = createRecipeFromConfig(new AtbywIdentifier("diorite_bricks_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.DIORITE_BRICKS_SLAB, newKeyPair("item", AtbywBlocks.DIORITE_BRICKS));
    public static Recipe<?> ANDESITE_BRICKS_SLAB = createRecipeFromConfig(new AtbywIdentifier("andesite_bricks_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.ANDESITE_BRICKS_SLAB, newKeyPair("item", AtbywBlocks.ANDESITE_BRICKS));

    public static List<Recipe<?>> BOOKSHELF_VARIANTS = createMultiRecipesFromConfig(WoodNames.getNamesNoOak(), new AtbywIdentifier("bookshelf"), "bookshelf", RecipeConfigs.BOOKSHELF_2, new Pair<>(atbywNamespace, "bookshelf"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false));
    public static List<Recipe<?>> BOOKSHELF_TOGGLES_VARIANTS = createMultiRecipesFromConfig(WoodNames.getNames(), new AtbywIdentifier("bookshelf_toggle"), "bookshelf_toggle", RecipeConfigs.BOOKSHELF_TOGGLE_4, new Pair<>(atbywNamespace, "bookshelf_toggle"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false), newKeyQuadruplet("item", mcNameSpace, "cobblestone", false));
    public static List<Recipe<?>> LADDERS_VARIANTS = createMultiRecipesFromConfig(WoodNames.getNamesNoOak(), new AtbywIdentifier("ladder"), "ladders", (RecipeConfigs.LADDER_1.getCount() * 2), RecipeConfigs.LADDER_1, new Pair<>(atbywNamespace, "ladder"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
    public static List<Recipe<?>> FENCE_DOOR_VARIANTS = createMultiRecipesFromConfig(WoodNames.getNames(), new AtbywIdentifier("fence_door"), "fence_door", RecipeConfigs.FENCE_DOOR_1, new Pair<>(atbywNamespace, "fence_door"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
    public static List<Recipe<?>> FLOWER_SWITCHES = createMultiRecipesFromConfig(FlowerNames.getNames(), new AtbywIdentifier("flower_pull_switch"), "flower_switches", RecipeConfigs.FLOWER_SWITCH_3, new Pair<>(atbywNamespace, "pull_switch"), newKeyQuadruplet("item", mcNameSpace, "", true), newKeyQuadruplet("item", mcNameSpace, "stick", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false));

    public static Recipe<?> TERRACOTTA_STAIRS = createRecipeFromConfig(new AtbywIdentifier("terracotta_stairs_from_stick_tag"), "terracotta_stairs", RecipeConfigs.STAIRS_1, AtbywBlocks.TERRACOTTA_STAIRS, newKeyPair("item", Blocks.TERRACOTTA));
    public static Recipe<?> TERRACOTTA_SLAB = createRecipeFromConfig(new AtbywIdentifier("terracotta_slab_from_stick_tag"), "terracotta_slab", RecipeConfigs.SLAB_1, AtbywBlocks.TERRACOTTA_SLAB, newKeyPair("item", Blocks.TERRACOTTA));
    public static Recipe<?> TERRACOTTA_BRICKS = createRecipeFromConfig(new AtbywIdentifier("terracotta_bricks_from_stick_tag"), "terracotta_bricks", RecipeConfigs.BRICKS_1, AtbywBlocks.TERRACOTTA_BRICKS, newKeyPair("item", Blocks.TERRACOTTA));
    public static Recipe<?> TERRACOTTA_BRICKS_STAIRS = createRecipeFromConfig(new AtbywIdentifier("terracotta_bricks_stairs_from_stick_tag"), "terracotta_bricks_stairs", RecipeConfigs.STAIRS_1, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS, newKeyPair("item", AtbywBlocks.TERRACOTTA_BRICKS));
    public static Recipe<?> TERRACOTTA_BRICKS_SLAB = createRecipeFromConfig(new AtbywIdentifier("terracotta_bricks_slab_from_stick_tag"), "terracotta_bricks_slab", RecipeConfigs.SLAB_1, AtbywBlocks.TERRACOTTA_BRICKS_SLAB, newKeyPair("item", AtbywBlocks.TERRACOTTA_BRICKS));
    public static Recipe<?> TERRACOTTA_BRICKS_WALL = createRecipeFromConfig(new AtbywIdentifier("terracotta_bricks_wall_from_stick_tag"), "terracotta_bricks_wall", RecipeConfigs.WALL_1, AtbywBlocks.TERRACOTTA_BRICKS_WALL, newKeyPair("item", AtbywBlocks.TERRACOTTA_BRICKS));

    public static List<Recipe<?>> TERRACOTTA_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_stairs_from_dye"), "terracotta_stairs", RecipeConfigs.STAIRS_1, new Pair<>(atbywNamespace, "terracotta_stairs"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static List<Recipe<?>> TERRACOTTA_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_slab_from_dye"), "terracotta_slab", RecipeConfigs.SLAB_1, new Pair<>(atbywNamespace, "terracotta_slab"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static List<Recipe<?>> TERRACOTTA_BRICKS_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_bricks_from_dye"), "terracotta_bricks", RecipeConfigs.BRICKS_1, new Pair<>(atbywNamespace, "terracotta_bricks"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static List<Recipe<?>> TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_bricks_stairs_from_dye"), "terracotta_bricks_stairs", RecipeConfigs.STAIRS_1, new Pair<>(atbywNamespace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", atbywNamespace, "terracotta_bricks", true));
    public static List<Recipe<?>> TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_bricks_slab_from_dye"), "terracotta_bricks_slab", RecipeConfigs.SLAB_1, new Pair<>(atbywNamespace, "terracotta_bricks_slab"), newKeyQuadruplet("item", atbywNamespace, "terracotta_bricks", true));
    public static List<Recipe<?>> TERRACOTTA_BRICKS_WALL_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_bricks_wall_from_dye"), "terracotta_bricks_wall", RecipeConfigs.WALL_1, new Pair<>(atbywNamespace, "terracotta_bricks_wall"), newKeyQuadruplet("item", atbywNamespace, "terracotta_bricks", true));

    public static List<Recipe<?>> TERRACOTTA_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_stairs"), "terracotta_stairs_dying", RecipeConfigs.DYING_2, new Pair<>(atbywNamespace, "terracotta_stairs"), newKeyQuadruplet("item", atbywNamespace, "terracotta_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static List<Recipe<?>> TERRACOTTA_SLAB_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_slab"), "terracotta_slabs_dying", RecipeConfigs.DYING_2, new Pair<>(atbywNamespace, "terracotta_slab"), newKeyQuadruplet("item", atbywNamespace, "terracotta_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static List<Recipe<?>> TERRACOTTA_BRICKS_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_bricks"), "terracotta_bricks_dying", RecipeConfigs.DYING_2, new Pair<>(atbywNamespace, "terracotta_bricks"), newKeyQuadruplet("item", atbywNamespace, "terracotta_bricks", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static List<Recipe<?>> TERRACOTTA_BRICKS_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_bricks_stairs"), "terracotta_bricks_stairs_dying", RecipeConfigs.DYING_2, new Pair<>(atbywNamespace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", atbywNamespace, "terracotta_bricks_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static List<Recipe<?>> TERRACOTTA_BRICKS_SLAB_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_bricks_slab"), "terracotta_bricks_slabs_dying", RecipeConfigs.DYING_2, new Pair<>(atbywNamespace, "terracotta_bricks_slab"), newKeyQuadruplet("item", atbywNamespace, "terracotta_bricks_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static List<Recipe<?>> TERRACOTTA_BRICKS_WALL_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("terracotta_bricks_wall"), "terracotta_bricks_walls_dying", RecipeConfigs.DYING_2, new Pair<>(atbywNamespace, "terracotta_bricks_wall"), newKeyQuadruplet("item", atbywNamespace, "terracotta_bricks_wall", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));

    public static List<Recipe<?>> CONCRETE_STAIRS_COLORS = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("concrete_stairs"), "concrete_stairs", RecipeConfigs.STAIRS_1, new Pair<>(atbywNamespace, "concrete_stairs"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static List<Recipe<?>> CONCRETE_SLAB_COLORS = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("concrete_slab"), "concrete_slab", RecipeConfigs.SLAB_1, new Pair<>(atbywNamespace, "concrete_slab"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static List<Recipe<?>> CINDER_BLOCKS_COLORS = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("cinder_blocks"), "cinder_blocks", RecipeConfigs.BRICKS_1, new Pair<>(atbywNamespace, "cinder_bricks"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static List<Recipe<?>> CINDER_BLOCKS_WALL_COLORS = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("cinder_blocks_wall"), "cinder_blocks_wall", RecipeConfigs.WALL_1, new Pair<>(atbywNamespace, "cinder_blocks_wall"), newKeyQuadruplet("item", atbywNamespace, "cinder_bricks", true));

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

    public static Recipe<?> GRANITE_COLUMN = createRecipeFromConfig(new AtbywIdentifier("granite_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.GRANITE_COLUMN, newKeyPair("item", Blocks.GRANITE));
    public static Recipe<?> DIORITE_COLUMN = createRecipeFromConfig(new AtbywIdentifier("diorite_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.DIORITE_COLUMN, newKeyPair("item", Blocks.DIORITE));
    public static Recipe<?> ANDESITE_COLUMN = createRecipeFromConfig(new AtbywIdentifier("andesite_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.ANDESITE_COLUMN, newKeyPair("item", Blocks.ANDESITE));
    public static Recipe<?> GRANITE_COLUMN_FROM_POLISHED = createRecipeFromConfig(new AtbywIdentifier("granite_column_from_polished"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.GRANITE_COLUMN, newKeyPair("item", Blocks.POLISHED_GRANITE));
    public static Recipe<?> DIORITE_COLUMN_FROM_POLISHED = createRecipeFromConfig(new AtbywIdentifier("diorite_column_from_polished"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.DIORITE_COLUMN, newKeyPair("item", Blocks.POLISHED_DIORITE));
    public static Recipe<?> ANDESITE_COLUMN_FROM_POLISHED = createRecipeFromConfig(new AtbywIdentifier("andesite_column_from_polished"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.ANDESITE_COLUMN, newKeyPair("item", Blocks.POLISHED_ANDESITE));
    public static Recipe<?> SANDSTONE_COLUMN = createRecipeFromConfig(new AtbywIdentifier("sandstone_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.SANDSTONE_COLUMN, newKeyPair("item", Blocks.SANDSTONE));
    public static Recipe<?> SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig(new AtbywIdentifier("sandstone_column_from_cut"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.SANDSTONE_COLUMN, newKeyPair("item", Blocks.SMOOTH_SANDSTONE));
    public static Recipe<?> CHISELED_SANDSTONE_COLUMN = createRecipeFromConfig(new AtbywIdentifier("chiseled_sandstone_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.CHISELED_SANDSTONE_COLUMN, newKeyPair("item", Blocks.CHISELED_SANDSTONE));
    public static Recipe<?> RED_SANDSTONE_COLUMN = createRecipeFromConfig(new AtbywIdentifier("red_sandstone_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.RED_SANDSTONE_COLUMN, newKeyPair("item", Blocks.RED_SANDSTONE));
    public static Recipe<?> RED_SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig(new AtbywIdentifier("red_sandstone_column_from_cut"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.RED_SANDSTONE_COLUMN, newKeyPair("item", Blocks.SMOOTH_RED_SANDSTONE));
    public static Recipe<?> CHISELED_RED_SANDSTONE_COLUMN = createRecipeFromConfig(new AtbywIdentifier("chiseled_red_sandstone_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN, newKeyPair("item", Blocks.CHISELED_RED_SANDSTONE));
    public static Recipe<?> PURPUR_COLUMN = createRecipeFromConfig(new AtbywIdentifier("purpur_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.PURPUR_COLUMN, newKeyPair("item", Blocks.PURPUR_BLOCK));
    public static Recipe<?> STONE_BRICKS_COLUMN = createRecipeFromConfig(new AtbywIdentifier("stone_bricks_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.STONE_BRICKS_COLUMN, newKeyPair("item", Blocks.STONE_BRICKS));
    public static Recipe<?> MOSSY_STONE_BRICKS_COLUMN = createRecipeFromConfig(new AtbywIdentifier("mossy_stone_bricks_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN, newKeyPair("item", Blocks.MOSSY_STONE_BRICKS));
    public static Recipe<?> CRACKED_STONE_BRICKS_COLUMN = createRecipeFromConfig(new AtbywIdentifier("cracked_stone_bricks_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN, newKeyPair("item", Blocks.CRACKED_STONE_BRICKS));
    public static Recipe<?> NETHER_BRICKS_COLUMN = createRecipeFromConfig(new AtbywIdentifier("nether_bricks_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.NETHER_BRICKS_COLUMN, newKeyPair("item", Blocks.NETHER_BRICKS));
    public static Recipe<?> QUARTZ_COLUMN = createRecipeFromConfig(new AtbywIdentifier("quartz_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.QUARTZ_COLUMN, newKeyPair("item", Blocks.QUARTZ_BLOCK));
    public static Recipe<?> PRISMARINE_COLUMN = createRecipeFromConfig(new AtbywIdentifier("prismarine_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.PRISMARINE_COLUMN, newKeyPair("item", Blocks.PRISMARINE_BRICKS));
    public static Recipe<?> BLACKSTONE_COLUMN = createRecipeFromConfig(new AtbywIdentifier("blackstone_column"), "columns", RecipeConfigs.COLUMN_1, AtbywBlocks.BLACKSTONE_COLUMN, newKeyPair("item", Blocks.BLACKSTONE));

    public static Recipe<?> PURPUR_TILES = createRecipeFromConfig(new AtbywIdentifier("purpur_tiles"), "", RecipeConfigs.BRICKS_1, AtbywBlocks.PURPUR_TILES, newKeyPair("item", Blocks.PURPUR_PILLAR));
    public static Recipe<?> CUT_PURPUR_BLOCK = createRecipeFromConfig(new AtbywIdentifier("cut_purpur_block"), "", RecipeConfigs.BRICKS_1, AtbywBlocks.CUT_PURPUR_BLOCK, newKeyPair("item", Blocks.PURPUR_BLOCK));
    public static Recipe<?> CHISELED_PURPUR_BLOCK = createRecipeFromConfig(new AtbywIdentifier("chiseled_purpur_block"), "", 1, RecipeConfigs.STICK_1, AtbywBlocks.CHISELED_PURPUR_BLOCK, newKeyPair("item", Blocks.PURPUR_SLAB));
    public static Recipe<?> PURPUR_TILES_STAIRS = createRecipeFromConfig(new AtbywIdentifier("purpur_tiles_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.PURPUR_TILES_STAIRS, newKeyPair("item", AtbywBlocks.PURPUR_TILES));
    public static Recipe<?> CUT_PURPUR_STAIRS = createRecipeFromConfig(new AtbywIdentifier("cut_purpur_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.CUT_PURPUR_STAIRS, newKeyPair("item", AtbywBlocks.CUT_PURPUR_BLOCK));
    public static Recipe<?> SMOOTH_PURPUR_STAIRS = createRecipeFromConfig(new AtbywIdentifier("smooth_purpur_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.SMOOTH_PURPUR_STAIRS, newKeyPair("item", AtbywBlocks.SMOOTH_PURPUR_BLOCK));
    public static Recipe<?> PURPUR_TILES_SLAB = createRecipeFromConfig(new AtbywIdentifier("purpur_tiles_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.PURPUR_TILES_SLAB, newKeyPair("item", AtbywBlocks.PURPUR_TILES));
    public static Recipe<?> CUT_PURPUR_SLAB = createRecipeFromConfig(new AtbywIdentifier("cut_purpur_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.CUT_PURPUR_SLAB, newKeyPair("item", AtbywBlocks.CUT_PURPUR_BLOCK));
    public static Recipe<?> SMOOTH_PURPUR_SLAB = createRecipeFromConfig(new AtbywIdentifier("smooth_purpur_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.SMOOTH_PURPUR_SLAB, newKeyPair("item", AtbywBlocks.SMOOTH_PURPUR_BLOCK));

    public static Recipe<?> COMPACTED_SNOW = createRecipeFromConfig(new AtbywIdentifier("compacted_snow"), "", RecipeConfigs.SLAB_1, AtbywBlocks.COMPACTED_SNOW, newKeyPair("item", AtbywBlocks.COMPACTED_SNOW_BLOCK));
    public static Recipe<?> COMPACTED_SNOW_BLOCK = createRecipeFromConfig(new AtbywIdentifier("compacted_snow_block"), "", 1, RecipeConfigs.BRICKS_1, AtbywBlocks.COMPACTED_SNOW_BLOCK, newKeyPair("item", Blocks.SNOW_BLOCK));
    public static Recipe<?> COMPACTED_SNOW_BRICKS = createRecipeFromConfig(new AtbywIdentifier("compacted_snow_bricks"), "", RecipeConfigs.BRICKS_1, AtbywBlocks.COMPACTED_SNOW_BRICKS, newKeyPair("item", AtbywBlocks.COMPACTED_SNOW_BLOCK));
    public static Recipe<?> PACKED_ICE_BRICKS = createRecipeFromConfig(new AtbywIdentifier("packed_ice_bricks"), "", RecipeConfigs.BRICKS_1, AtbywBlocks.PACKED_ICE_BRICKS, newKeyPair("item", Blocks.PACKED_ICE));
    public static Recipe<?> BLUE_ICE_BRICKS = createRecipeFromConfig(new AtbywIdentifier("blue_ice_bricks"), "", RecipeConfigs.BRICKS_1, AtbywBlocks.BLUE_ICE_BRICKS, newKeyPair("item", Blocks.BLUE_ICE));

    public static Recipe<?> COMPACTED_SNOW_BLOCK_STAIRS = createRecipeFromConfig(new AtbywIdentifier("compacted_snow_block_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS, newKeyPair("item", AtbywBlocks.COMPACTED_SNOW_BLOCK));
    public static Recipe<?> COMPACTED_SNOW_BRICKS_STAIRS = createRecipeFromConfig(new AtbywIdentifier("compacted_snow_bricks_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS, newKeyPair("item", AtbywBlocks.COMPACTED_SNOW_BRICKS));
    public static Recipe<?> PACKED_ICE_STAIRS = createRecipeFromConfig(new AtbywIdentifier("packed_ice_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.PACKED_ICE_STAIRS, newKeyPair("item", Blocks.PACKED_ICE));
    public static Recipe<?> BLUE_ICE_STAIRS = createRecipeFromConfig(new AtbywIdentifier("blue_ice_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.BLUE_ICE_STAIRS, newKeyPair("item", Blocks.BLUE_ICE));
    public static Recipe<?> PACKED_ICE_BRICKS_STAIRS = createRecipeFromConfig(new AtbywIdentifier("packed_ice_bricks_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.PACKED_ICE_BRICKS_STAIRS, newKeyPair("item", AtbywBlocks.PACKED_ICE_BRICKS));
    public static Recipe<?> BLUE_ICE_BRICKS_STAIRS = createRecipeFromConfig(new AtbywIdentifier("blue_ice_bricks_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.BLUE_ICE_BRICKS_STAIRS, newKeyPair("item", AtbywBlocks.BLUE_ICE_BRICKS));

    public static Recipe<?> COMPACTED_SNOW_BLOCK_SLAB = createRecipeFromConfig(new AtbywIdentifier("compacted_snow_block_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB, newKeyPair("item", AtbywBlocks.COMPACTED_SNOW_BLOCK));
    public static Recipe<?> COMPACTED_SNOW_BRICKS_SLAB = createRecipeFromConfig(new AtbywIdentifier("compacted_snow_bricks_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB, newKeyPair("item", AtbywBlocks.COMPACTED_SNOW_BRICKS));
    public static Recipe<?> PACKED_ICE_SLAB = createRecipeFromConfig(new AtbywIdentifier("packed_ice_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.PACKED_ICE_SLAB, newKeyPair("item", Blocks.PACKED_ICE));
    public static Recipe<?> BLUE_ICE_SLAB = createRecipeFromConfig(new AtbywIdentifier("blue_ice_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.BLUE_ICE_SLAB, newKeyPair("item", Blocks.BLUE_ICE));
    public static Recipe<?> PACKED_ICE_BRICKS_SLAB = createRecipeFromConfig(new AtbywIdentifier("packed_ice_bricks_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.PACKED_ICE_BRICKS_SLAB, newKeyPair("item", AtbywBlocks.PACKED_ICE_BRICKS));
    public static Recipe<?> BLUE_ICE_BRICKS_SLAB = createRecipeFromConfig(new AtbywIdentifier("blue_ice_bricks_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.BLUE_ICE_BRICKS_SLAB, newKeyPair("item", AtbywBlocks.BLUE_ICE_BRICKS));
    public static Recipe<?> CHISELED_PACKED_ICE_BRICKS_SLAB = createRecipeFromConfig(new AtbywIdentifier("chiseled_packed_ice_bricks_slab"), "", 1, RecipeConfigs.STICK_1, AtbywBlocks.CHISELED_PACKED_ICE_BRICKS, newKeyPair("item", AtbywBlocks.PACKED_ICE_BRICKS_SLAB));
    public static Recipe<?> CHISELED_BLUE_ICE_BRICKS_SLAB = createRecipeFromConfig(new AtbywIdentifier("chiseled_blue_ice_bricks_slab"), "", 1, RecipeConfigs.STICK_1, AtbywBlocks.CHISELED_BLUE_ICE_BRICKS, newKeyPair("item", AtbywBlocks.BLUE_ICE_BRICKS_SLAB));

    public static Recipe<?> IRON_SPIKE_TRAP = createRecipeFromConfig(new AtbywIdentifier("iron_spike_trap"), "", RecipeConfigs.SPIKE_TRAP_4, AtbywBlocks.IRON_SPIKE_TRAP, newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.COBBLESTONE), newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.REDSTONE));
    public static Recipe<?> GOLD_SPIKE_TRAP = createRecipeFromConfig(new AtbywIdentifier("gold_spike_trap"), "", RecipeConfigs.SPIKE_TRAP_4, AtbywBlocks.GOLD_SPIKE_TRAP, newKeyPair("item", Items.GOLD_INGOT), newKeyPair("item", Items.COBBLESTONE), newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.REDSTONE));
    public static Recipe<?> DIAMOND_SPIKE_TRAP = createRecipeFromConfig(new AtbywIdentifier("diamond_spike_trap"), "", RecipeConfigs.SPIKE_TRAP_4, AtbywBlocks.DIAMOND_SPIKE_TRAP, newKeyPair("item", Items.DIAMOND), newKeyPair("item", Items.COBBLESTONE), newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.REDSTONE));
    public static Recipe<?> NETHERITE_SPIKE_TRAP = createRecipeFromConfig(new AtbywIdentifier("netherite_spike_trap"), "", RecipeConfigs.SPIKE_TRAP_4, AtbywBlocks.NETHERITE_SPIKE_TRAP, newKeyPair("item", Items.NETHERITE_INGOT), newKeyPair("item", Items.COBBLESTONE), newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.REDSTONE));

    public static Recipe<?> IRON_SPIKE_TRAP_BLACKSTONE = createRecipeFromConfig(new AtbywIdentifier("iron_spike_trap_blackstone"), "", RecipeConfigs.SPIKE_TRAP_4, AtbywBlocks.IRON_SPIKE_TRAP, newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.BLACKSTONE), newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.REDSTONE));
    public static Recipe<?> GOLD_SPIKE_TRAP_BLACKSTONE = createRecipeFromConfig(new AtbywIdentifier("gold_spike_trap_blackstone"), "", RecipeConfigs.SPIKE_TRAP_4, AtbywBlocks.GOLD_SPIKE_TRAP, newKeyPair("item", Items.GOLD_INGOT), newKeyPair("item", Items.BLACKSTONE), newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.REDSTONE));
    public static Recipe<?> DIAMOND_SPIKE_TRAP_BLACKSTONE = createRecipeFromConfig(new AtbywIdentifier("diamond_spike_trap_blackstone"), "", RecipeConfigs.SPIKE_TRAP_4, AtbywBlocks.DIAMOND_SPIKE_TRAP, newKeyPair("item", Items.DIAMOND), newKeyPair("item", Items.BLACKSTONE), newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.REDSTONE));
    public static Recipe<?> NETHERITE_SPIKE_TRAP_BLACKSTONE = createRecipeFromConfig(new AtbywIdentifier("netherite_spike_trap_blackstone"), "", RecipeConfigs.SPIKE_TRAP_4, AtbywBlocks.NETHERITE_SPIKE_TRAP, newKeyPair("item", Items.NETHERITE_INGOT), newKeyPair("item", Items.BLACKSTONE), newKeyPair("item", Items.IRON_INGOT), newKeyPair("item", Items.REDSTONE));

    public static Recipe<?> SOUL_JACK_O_LANTERN = createRecipeFromConfig(new AtbywIdentifier("soul_jack_o_lantern"), "", 1, RecipeConfigs.TORCH_2, AtbywBlocks.SOUL_JACK_O_LANTERN, newKeyPair("item", Blocks.JACK_O_LANTERN), newKeyPair("item", Items.SOUL_TORCH));
    public static Recipe<?> REDSTONE_JACK_O_LANTERN = createRecipeFromConfig(new AtbywIdentifier("redstone_jack_o_lantern"), "", 1, RecipeConfigs.TORCH_2, AtbywBlocks.REDSTONE_JACK_O_LANTERN, newKeyPair("item", Blocks.JACK_O_LANTERN), newKeyPair("item", Items.REDSTONE_TORCH));

    public static Recipe<?> TIMER_REPEATER_MANUAL = createRecipeFromConfig(new AtbywIdentifier("timer_repeater_manual"), "timer_repeater", 1, RecipeConfigs.DYING_DASHED_3, AtbywBlocks.TIMER_REPEATER, newKeyPair("item", Items.REDSTONE), newKeyPair("item", Items.GOLD_INGOT), newKeyPair("item", Items.REPEATER));
    public static Recipe<?> TIMER_REPEATER_CLOCK = createRecipeFromConfig(new AtbywIdentifier("timer_repeater_clock"), "timer_repeater", RecipeConfigs.TIMER_REPEATER_3, AtbywBlocks.TIMER_REPEATER, newKeyPair("item", Items.CLOCK), newKeyPair("item", Items.REDSTONE), newKeyPair("item", Items.REPEATER));

    public static Recipe<?> REDSTONE_CROSS_PATH = createRecipeFromConfig(new AtbywIdentifier("redstone_cross_path"), "", RecipeConfigs.BED_2, AtbywBlocks.REDSTONE_CROSS_PATH, newKeyPair("item", Items.STONE), newKeyPair("item", Items.REDSTONE));

    public static Recipe<?> LARGE_CHAIN_LINK = createRecipeFromConfig(new AtbywIdentifier("large_chain_link"), "", RecipeConfigs.STAR_1, AtbywItems.LARGE_CHAIN_LINK, newKeyPair("item", Items.IRON_INGOT));
    public static Recipe<?> LARGE_CHAIN = createRecipeFromConfig(new AtbywIdentifier("large_chain"), "", RecipeConfigs.COLUMN_1, AtbywBlocks.LARGE_CHAIN, newKeyPair("item", AtbywItems.LARGE_CHAIN_LINK));

    public static Recipe<?> ROOTED_DIRT_STAIRS = createRecipeFromConfig(new AtbywIdentifier("rooted_dirt_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.ROOTED_DIRT_STAIRS, newKeyPair("item", Blocks.ROOTED_DIRT));
    public static Recipe<?> ROOTED_DIRT_SLAB = createRecipeFromConfig(new AtbywIdentifier("rooted_dirt_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.ROOTED_DIRT_SLAB, newKeyPair("item", Blocks.ROOTED_DIRT));

    public static Recipe<?> SAND_STAIRS = createRecipeFromConfig(new AtbywIdentifier("sand_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.SAND_STAIRS, newKeyPair("item", Blocks.SAND));
    public static Recipe<?> SAND_SLAB = createRecipeFromConfig(new AtbywIdentifier("sand_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.SAND_SLAB, newKeyPair("item", Blocks.SAND));
    public static Recipe<?> RED_SAND_STAIRS = createRecipeFromConfig(new AtbywIdentifier("red_sand_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.RED_SAND_STAIRS, newKeyPair("item", Blocks.RED_SAND));
    public static Recipe<?> RED_SAND_SLAB = createRecipeFromConfig(new AtbywIdentifier("red_sand_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.RED_SAND_SLAB, newKeyPair("item", Blocks.RED_SAND));
    public static Recipe<?> GRAVEL_STAIRS = createRecipeFromConfig(new AtbywIdentifier("gravel_stairs"), "", RecipeConfigs.STAIRS_1, AtbywBlocks.GRAVEL_STAIRS, newKeyPair("item", Blocks.GRAVEL));
    public static Recipe<?> GRAVEL_SLAB = createRecipeFromConfig(new AtbywIdentifier("gravel_slab"), "", RecipeConfigs.SLAB_1, AtbywBlocks.GRAVEL_SLAB, newKeyPair("item", Blocks.GRAVEL));

    public static List<Recipe<?>> LOG_STAIRS = createMultiRecipesFromConfig(WoodNames.getNamesOverworld(), new AtbywIdentifier("log_stairs"), "", RecipeConfigs.STAIRS_1, new Pair<>(atbywNamespace, "log_stairs"), new Quadruplet<>("item", mcNameSpace, "log", true));
    public static List<Recipe<?>> STEM_STAIRS = createMultiRecipesFromConfig(WoodNames.getNamesNether(), new AtbywIdentifier("stem_stairs"), "", RecipeConfigs.STAIRS_1, new Pair<>(atbywNamespace, "stem_stairs"), new Quadruplet<>("item", mcNameSpace, "stem", true));

    public static List<Recipe<?>> LOG_SLAB = createMultiRecipesFromConfig(WoodNames.getNamesOverworld(), new AtbywIdentifier("log_slab"), "", RecipeConfigs.SLAB_1, new Pair<>(atbywNamespace, "log_slab"), new Quadruplet<>("item", mcNameSpace, "log", true));
    public static List<Recipe<?>> STEM_SLAB = createMultiRecipesFromConfig(WoodNames.getNamesNether(), new AtbywIdentifier("stem_slab"), "", RecipeConfigs.SLAB_1, new Pair<>(atbywNamespace, "stem_slab"), new Quadruplet<>("item", mcNameSpace, "stem", true));

    public static List<Recipe<?>> STAINED_SHATTERED_GLASS = createMultiRecipesFromConfig(ColorNames.getNames(), new AtbywIdentifier("stained_shattered_glass"), "stained_shattered_glass", 1, RecipeConfigs.BRICKS_1, new Pair<>(atbywNamespace, "stained_shattered_glass"), new Quadruplet<>("item", atbywNamespace, "stained_glass_shard", true));
    public static Recipe<?> SHATTERED_GLASS = createRecipeFromConfig(new AtbywIdentifier("gravel_slab"), "", 1, RecipeConfigs.BRICKS_1, AtbywBlocks.SHATTERED_GLASS, newKeyPair("item", AtbywItems.GLASS_SHARD));

    public static void init() {
        LOGGER.info("ATBYW Recipes Inintiliazed");
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        RecipeSmeltingRegistry.inject(map);
        RecipeStonecuttingRegistry.inject(map);

        // Multi recipes
        Datagen.registerRecipes(FLOWER_SWITCHES, "redstone");

        //Woods except oak
        Datagen.registerRecipes(BOOKSHELF_VARIANTS, "");
        Datagen.registerRecipes(LADDERS_VARIANTS, "");

        //Woods Overworld
        Datagen.registerRecipes(LOG_STAIRS, "log_stairs");
        Datagen.registerRecipes(LOG_SLAB, "log_slab");

        //Woods Nether
        Datagen.registerRecipes(STEM_STAIRS, "log_stairs");
        Datagen.registerRecipes(STEM_SLAB, "log_slab");

        //Woods
        Datagen.registerRecipes(FENCE_DOOR_VARIANTS, "");
        Datagen.registerRecipes(BOOKSHELF_TOGGLES_VARIANTS, "redstone");

        //Colors
        Datagen.registerRecipes(TERRACOTTA_STAIRS_COLORS_SHAPED, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_SLAB_COLORS_SHAPED, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_BRICKS_COLORS_SHAPED, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_BRICKS_WALL_COLORS_SHAPED, "terracotta");

        Datagen.registerRecipes(TERRACOTTA_STAIRS_COLORS_DYING, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_SLAB_COLORS_DYING, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_BRICKS_COLORS_DYING, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_BRICKS_STAIRS_COLORS_DYING, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_BRICKS_SLAB_COLORS_DYING, "terracotta");
        Datagen.registerRecipes(TERRACOTTA_BRICKS_WALL_COLORS_DYING, "terracotta");

        Datagen.registerRecipes(CONCRETE_STAIRS_COLORS, "concrete");
        Datagen.registerRecipes(CONCRETE_SLAB_COLORS, "concrete");
        Datagen.registerRecipes(CINDER_BLOCKS_COLORS, "concrete");
        Datagen.registerRecipes(CINDER_BLOCKS_WALL_COLORS, "concrete");

        Datagen.registerRecipes(STAINED_SHATTERED_GLASS, "shattered_glass");

        // Single recipes
        Datagen.registerRecipe(TERRACOTTA_STAIRS, "");
        Datagen.registerRecipe(TERRACOTTA_SLAB, "");
        Datagen.registerRecipe(TERRACOTTA_BRICKS, "");
        Datagen.registerRecipe(TERRACOTTA_BRICKS_STAIRS, "");
        Datagen.registerRecipe(TERRACOTTA_BRICKS_SLAB, "");
        Datagen.registerRecipe(TERRACOTTA_BRICKS_WALL, "");

        Datagen.registerRecipe(DIRT_STAIRS, "");
        Datagen.registerRecipe(GRASS_BLOCK_STAIRS, "");
        Datagen.registerRecipe(MYCELIUM_STAIRS, "");
        Datagen.registerRecipe(COARSE_DIRT_STAIRS, "");
        Datagen.registerRecipe(PODZOL_STAIRS, "");
        Datagen.registerRecipe(NETHERRACK_STAIRS, "");
        Datagen.registerRecipe(CRIMSON_NYLIUM_STAIRS, "");
        Datagen.registerRecipe(WARPED_NYLIUM_STAIRS, "");

        Datagen.registerRecipe(DIRT_SLAB, "");
        Datagen.registerRecipe(GRASS_BLOCK_SLAB, "");
        Datagen.registerRecipe(MYCELIUM_SLAB, "");
        Datagen.registerRecipe(COARSE_DIRT_SLAB, "");
        Datagen.registerRecipe(PODZOL_SLAB, "");
        Datagen.registerRecipe(NETHERRACK_SLAB, "");
        Datagen.registerRecipe(CRIMSON_NYLIUM_SLAB, "");
        Datagen.registerRecipe(WARPED_NYLIUM_SLAB, "");

        Datagen.registerRecipe(BASALT_BRICKS_FROM_BASALT, "");
        Datagen.registerRecipe(BASALT_PILLAR_FROM_BASALT, "");
        Datagen.registerRecipe(BASALT_BRICKS_FROM_POLISHED_BASALT, "");
        Datagen.registerRecipe(BASALT_PILLAR_FROM_POLISHED_BASALT, "");

        Datagen.registerRecipe(IRON_FENCE_DOOR, "redstone");
        Datagen.registerRecipe(BAMBOO_LADDER, "");
        Datagen.registerRecipe(BAMBOO_STICK, "");
        Datagen.registerRecipe(REDSTONE_LANTERN, "redstone");
        Datagen.registerRecipe(SHROOM_STICK, "");

        Datagen.registerRecipe(GRANITE_TILES, "");
        Datagen.registerRecipe(DIORITE_BRICKS, "");
        Datagen.registerRecipe(ANDESITE_BRICKS, "");

        Datagen.registerRecipe(GRANITE_TILES_STAIRS, "");
        Datagen.registerRecipe(DIORITE_BRICKS_STAIRS, "");
        Datagen.registerRecipe(ANDESITE_BRICKS_STAIRS, "");

        Datagen.registerRecipe(GRANITE_TILES_SLAB, "");
        Datagen.registerRecipe(DIORITE_BRICKS_SLAB, "");
        Datagen.registerRecipe(ANDESITE_BRICKS_SLAB, "");

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

        Datagen.registerRecipe(GRANITE_COLUMN, "columns");
        Datagen.registerRecipe(DIORITE_COLUMN, "columns");
        Datagen.registerRecipe(ANDESITE_COLUMN, "columns");
        Datagen.registerRecipe(GRANITE_COLUMN_FROM_POLISHED, "columns");
        Datagen.registerRecipe(DIORITE_COLUMN_FROM_POLISHED, "columns");
        Datagen.registerRecipe(ANDESITE_COLUMN_FROM_POLISHED, "columns");
        Datagen.registerRecipe(SANDSTONE_COLUMN, "columns");
        Datagen.registerRecipe(SANDSTONE_COLUMN_FROM_CUT, "columns");
        Datagen.registerRecipe(CHISELED_SANDSTONE_COLUMN, "columns");
        Datagen.registerRecipe(RED_SANDSTONE_COLUMN, "columns");
        Datagen.registerRecipe(RED_SANDSTONE_COLUMN_FROM_CUT, "columns");
        Datagen.registerRecipe(CHISELED_RED_SANDSTONE_COLUMN, "columns");
        Datagen.registerRecipe(PURPUR_COLUMN, "columns");
        Datagen.registerRecipe(STONE_BRICKS_COLUMN, "columns");
        Datagen.registerRecipe(MOSSY_STONE_BRICKS_COLUMN, "columns");
        Datagen.registerRecipe(CRACKED_STONE_BRICKS_COLUMN, "columns");
        Datagen.registerRecipe(NETHER_BRICKS_COLUMN, "columns");
        Datagen.registerRecipe(QUARTZ_COLUMN, "columns");
        Datagen.registerRecipe(PRISMARINE_COLUMN, "columns");
        Datagen.registerRecipe(BLACKSTONE_COLUMN, "columns");

        Datagen.registerRecipe(PURPUR_TILES, "");
        Datagen.registerRecipe(CUT_PURPUR_BLOCK, "");
        Datagen.registerRecipe(CHISELED_PURPUR_BLOCK, "");
        Datagen.registerRecipe(PURPUR_TILES_STAIRS, "");
        Datagen.registerRecipe(CUT_PURPUR_STAIRS, "");
        Datagen.registerRecipe(SMOOTH_PURPUR_STAIRS, "");
        Datagen.registerRecipe(PURPUR_TILES_SLAB, "");
        Datagen.registerRecipe(CUT_PURPUR_SLAB, "");
        Datagen.registerRecipe(SMOOTH_PURPUR_SLAB, "");

        Datagen.registerRecipe(COMPACTED_SNOW, "compacted_snow");
        Datagen.registerRecipe(COMPACTED_SNOW_BLOCK, "compacted_snow");
        Datagen.registerRecipe(COMPACTED_SNOW_BRICKS, "compacted_snow");
        Datagen.registerRecipe(PACKED_ICE_BRICKS, "ice");
        Datagen.registerRecipe(BLUE_ICE_BRICKS, "ice");

        Datagen.registerRecipe(COMPACTED_SNOW_BLOCK_STAIRS, "compacted_snow");
        Datagen.registerRecipe(COMPACTED_SNOW_BRICKS_STAIRS, "compacted_snow");
        Datagen.registerRecipe(PACKED_ICE_STAIRS, "ice");
        Datagen.registerRecipe(BLUE_ICE_STAIRS, "ice");
        Datagen.registerRecipe(PACKED_ICE_BRICKS_STAIRS, "ice");
        Datagen.registerRecipe(BLUE_ICE_BRICKS_STAIRS, "ice");

        Datagen.registerRecipe(COMPACTED_SNOW_BLOCK_SLAB, "compacted_snow");
        Datagen.registerRecipe(COMPACTED_SNOW_BRICKS_SLAB, "compacted_snow");
        Datagen.registerRecipe(PACKED_ICE_SLAB, "ice");
        Datagen.registerRecipe(BLUE_ICE_SLAB, "ice");
        Datagen.registerRecipe(PACKED_ICE_BRICKS_SLAB, "ice");
        Datagen.registerRecipe(BLUE_ICE_BRICKS_SLAB, "ice");
        Datagen.registerRecipe(CHISELED_PACKED_ICE_BRICKS_SLAB, "ice");
        Datagen.registerRecipe(CHISELED_BLUE_ICE_BRICKS_SLAB, "ice");

        Datagen.registerRecipe(IRON_SPIKE_TRAP, "redstone");
        Datagen.registerRecipe(GOLD_SPIKE_TRAP, "redstone");
        Datagen.registerRecipe(DIAMOND_SPIKE_TRAP, "redstone");
        Datagen.registerRecipe(NETHERITE_SPIKE_TRAP, "redstone");
        Datagen.registerRecipe(IRON_SPIKE_TRAP_BLACKSTONE, "redstone");
        Datagen.registerRecipe(GOLD_SPIKE_TRAP_BLACKSTONE, "redstone");
        Datagen.registerRecipe(DIAMOND_SPIKE_TRAP_BLACKSTONE, "redstone");
        Datagen.registerRecipe(NETHERITE_SPIKE_TRAP_BLACKSTONE, "redstone");

        Datagen.registerRecipe(SOUL_JACK_O_LANTERN, "");
        Datagen.registerRecipe(REDSTONE_JACK_O_LANTERN, "redstone");

        Datagen.registerRecipe(TIMER_REPEATER_MANUAL, "redstone");
        Datagen.registerRecipe(TIMER_REPEATER_CLOCK, "redstone");

        Datagen.registerRecipe(REDSTONE_CROSS_PATH, "redstone");

        Datagen.registerRecipe(LARGE_CHAIN_LINK, "");
        Datagen.registerRecipe(LARGE_CHAIN, "");

        Datagen.registerRecipe(ROOTED_DIRT_STAIRS, "");
        Datagen.registerRecipe(ROOTED_DIRT_SLAB, "");

        Datagen.registerRecipe(SAND_STAIRS, "sand_stairs");
        Datagen.registerRecipe(SAND_SLAB, "sand_slab");
        Datagen.registerRecipe(RED_SAND_STAIRS, "sand_stairs");
        Datagen.registerRecipe(RED_SAND_SLAB, "sand_slab");
        Datagen.registerRecipe(GRAVEL_STAIRS, "sand_stairs");
        Datagen.registerRecipe(GRAVEL_SLAB, "sand_slab");

        Datagen.registerRecipe(SHATTERED_GLASS, "");
    }
}