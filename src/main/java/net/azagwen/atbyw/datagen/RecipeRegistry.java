package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;
import com.google.gson.*;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.util.Quadruplet;
import net.azagwen.atbyw.util.naming.ColorNames;
import net.azagwen.atbyw.util.naming.FlowerNames;
import net.azagwen.atbyw.util.naming.WoodNames;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.azagwen.atbyw.datagen.RecipeUtils.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class RecipeRegistry {
    static Gson builder = new GsonBuilder().setPrettyPrinting().create();
    public static Logger LOGGER = LogManager.getLogger("Atbyw Recipes");

    public static AtbywRecipe createShapelessRecipe(Identifier recipeId, String group, int count, ArrayList<Identifier> keys, Identifier output) {
        var json = new JsonObject();

        if (!group.equals(""))
            json.addProperty("group", group);

        //Keys
        JsonObject individualIngredient;
        var ingredients = new JsonArray();
        for (var key : keys) {
            //Key type + ingredient
            individualIngredient = new JsonObject();
            individualIngredient.addProperty("item", key.toString());
            //Key character
            ingredients.add(individualIngredient);
        }

        //Result
        var result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);

        //build final JSON
        json.addProperty("type", "minecraft:crafting_shapeless");
        json.add("ingredients", ingredients);
        json.add("result", result);

        Advancements.RECIPE_MAP.put(recipeId, Advancements.unlockShapelessRecipe(json, recipeId));
        return new AtbywRecipe(json, recipeId);
    }

    public static AtbywRecipe createShapedRecipe(Identifier recipeId, String group, ArrayList<String> pattern, ArrayList<Key> keys, Identifier output, int count) {
        var json = new JsonObject();

        if (!group.equals(""))
            json.addProperty("group", group);

        //Pattern
        var patternArray = new JsonArray();
        for (String s : pattern) {
            patternArray.add(s);
        }
        json.add("pattern", patternArray);

        //Keys
        JsonObject individualKey;
        var keyList = new JsonObject();
        for (var key : keys) {
            //Key type + ingredient
            individualKey = new JsonObject();
            individualKey.addProperty(key.getType(), key.getIngredient().toString());
            //Key character
            keyList.add(key.getChar() + "", individualKey);
        }

        //Result
        var result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);

        //build final JSON
        json.addProperty("type", "minecraft:crafting_shaped");
        json.add("key", keyList);
        json.add("result", result);

        Advancements.RECIPE_MAP.put(recipeId, Advancements.unlockShapedRecipe(json, recipeId));
        return new AtbywRecipe(json, recipeId);
    }

    //Shaped recipes config methods
    @SafeVarargs
    private static AtbywRecipe createRecipeFromConfig(Identifier recipeId, String group, int count, RecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        var keys = new Key[ingredient.length];

        for (int i = 0; i < ingredient.length; i++) {
            keys[i] = new Key(config.getKeyChars().get(i), ingredient[i].getLeft(), ingredient[i].getRight());
        }

        return createShapedRecipe(recipeId, group, config.getPattern(), Lists.newArrayList(keys), result, count);
    }

    @SafeVarargs
    private static AtbywRecipe createRecipeFromConfig(Identifier recipeId, String group, RecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        return createRecipeFromConfig(recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    private static AtbywRecipe[] createMultiRecipesFromConfig(String[] nameArray, Identifier recipeId, String group, int count, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredients) {
        var recipes = new AtbywRecipe[nameArray.length];
        var ing = new Pair[ingredients.length];

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

                ing[j] = newKeyPair(ingredient.getFirst(), new Identifier(ingredient.getSecond(), fullName));
                j++;
            }
            recipes[i] = createRecipeFromConfig(newRecipeId, group, count, config, new Identifier(result.getLeft(), prefix + "_" + result.getRight()), ing);
            i++;
        }
        return recipes;
    }

    @SafeVarargs
    private static AtbywRecipe[] createMultiRecipesFromConfig(String[] nameArray, Identifier recipeId, String group, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray, recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    private static AtbywRecipe[] createMultiRecipesFromConfig(List<String> nameArray, Identifier recipeId, String group, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray.toArray(String[]::new), recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    private static AtbywRecipe[] createMultiRecipesFromConfig(List<String> nameArray, Identifier recipeId, String group, int count, RecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray.toArray(String[]::new), recipeId, group, count, config, result, ingredient);
    }


    public static AtbywRecipe DIRT_STAIRS = createRecipeFromConfig(NewAtbywID("dirt_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.DIRT)));
    public static AtbywRecipe GRASS_BLOCK_STAIRS = createRecipeFromConfig(NewAtbywID("grass_block_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.GRASS_BLOCK_STAIRS), newKeyPair("item", getBlockID(Blocks.GRASS_BLOCK)));
    public static AtbywRecipe MYCELIUM_STAIRS = createRecipeFromConfig(NewAtbywID("mycelium_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.MYCELIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.MYCELIUM)));
    public static AtbywRecipe COARSE_DIRT_STAIRS = createRecipeFromConfig(NewAtbywID("coarse_dirt_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COARSE_DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.COARSE_DIRT)));
    public static AtbywRecipe PODZOL_STAIRS = createRecipeFromConfig(NewAtbywID("podzol_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PODZOL_STAIRS), newKeyPair("item", getBlockID(Blocks.PODZOL)));
    public static AtbywRecipe NETHERRACK_STAIRS = createRecipeFromConfig(NewAtbywID("netherrack_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.NETHERRACK_STAIRS), newKeyPair("item", getBlockID(Blocks.NETHERRACK)));
    public static AtbywRecipe CRIMSON_NYLIUM_STAIRS = createRecipeFromConfig(NewAtbywID("crimson_nylium_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.CRIMSON_NYLIUM)));
    public static AtbywRecipe WARPED_NYLIUM_STAIRS = createRecipeFromConfig(NewAtbywID("warped_nylium_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.WARPED_NYLIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.WARPED_NYLIUM)));

    public static AtbywRecipe DIRT_SLAB = createRecipeFromConfig(NewAtbywID("dirt_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.DIRT)));
    public static AtbywRecipe GRASS_BLOCK_SLAB = createRecipeFromConfig(NewAtbywID("grass_block_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.GRASS_BLOCK_SLAB), newKeyPair("item", getBlockID(Blocks.GRASS_BLOCK)));
    public static AtbywRecipe MYCELIUM_SLAB = createRecipeFromConfig(NewAtbywID("mycelium_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.MYCELIUM_SLAB), newKeyPair("item", getBlockID(Blocks.MYCELIUM)));
    public static AtbywRecipe COARSE_DIRT_SLAB = createRecipeFromConfig(NewAtbywID("coarse_dirt_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COARSE_DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.COARSE_DIRT)));
    public static AtbywRecipe PODZOL_SLAB = createRecipeFromConfig(NewAtbywID("podzol_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PODZOL_SLAB), newKeyPair("item", getBlockID(Blocks.PODZOL)));
    public static AtbywRecipe NETHERRACK_SLAB = createRecipeFromConfig(NewAtbywID("netherrack_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.NETHERRACK_SLAB), newKeyPair("item", getBlockID(Blocks.NETHERRACK)));
    public static AtbywRecipe CRIMSON_NYLIUM_SLAB = createRecipeFromConfig(NewAtbywID("crimson_nylium_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_SLAB), newKeyPair("item", getBlockID(Blocks.CRIMSON_NYLIUM)));
    public static AtbywRecipe WARPED_NYLIUM_SLAB = createRecipeFromConfig(NewAtbywID("warped_nylium_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.WARPED_NYLIUM_SLAB), newKeyPair("item", getBlockID(Blocks.WARPED_NYLIUM)));

    public static AtbywRecipe BASALT_BRICKS_FROM_BASALT = createRecipeFromConfig(NewAtbywID("basalt_bricks_from_basalt"), "basalt_bricks", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BASALT_BRICKS), newKeyPair("item", getBlockID(Blocks.BASALT)));
    public static AtbywRecipe BASALT_PILLAR_FROM_BASALT = createRecipeFromConfig(NewAtbywID("basalt_pillar_from_basalt"), "basalt_bricks", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BASALT_BRICKS), newKeyPair("item", getBlockID(Blocks.BASALT)));
    public static AtbywRecipe BASALT_BRICKS_FROM_POLISHED_BASALT = createRecipeFromConfig(NewAtbywID("basalt_bricks_from_polished_basalt"), "basalt_pillar", 2, RecipeConfigs.STICK_1, getBlockID(AtbywBlocks.BASALT_PILLAR), newKeyPair("item", getBlockID(Blocks.POLISHED_BASALT)));
    public static AtbywRecipe BASALT_PILLAR_FROM_POLISHED_BASALT = createRecipeFromConfig(NewAtbywID("basalt_pillar_from_polished_basalt"), "basalt_pillar", 2, RecipeConfigs.STICK_1, getBlockID(AtbywBlocks.BASALT_PILLAR), newKeyPair("item", getBlockID(Blocks.POLISHED_BASALT)));

    public static AtbywRecipe IRON_FENCE_DOOR = createRecipeFromConfig(NewAtbywID("iron_fence_door"), "fence_door", RecipeConfigs.FENCE_DOOR_1, getBlockID(AtbywBlocks.IRON_FENCE_DOOR), newKeyPair("item", getItemID(Items.IRON_INGOT)));
    public static AtbywRecipe BAMBOO_LADDER = createRecipeFromConfig(NewAtbywID("bamboo_ladder"), "ladders", 3, RecipeConfigs.RAIL_2, getBlockID(AtbywBlocks.BAMBOO_LADDER), newKeyPair("item", getItemID(AtbywItems.BAMBOO_STICK)), newKeyPair("item", getItemID(Items.BAMBOO)));
    public static AtbywRecipe BAMBOO_STICK = createRecipeFromConfig(NewAtbywID("bamboo_stick"), "stick", RecipeConfigs.STICK_1, getItemID(AtbywItems.BAMBOO_STICK), newKeyPair("item", getItemID(Items.BAMBOO)));
    public static AtbywRecipe REDSTONE_LANTERN = createRecipeFromConfig(NewAtbywID("redstone_lantern"), "", RecipeConfigs.DYING_2, getBlockID(AtbywBlocks.REDSTONE_LANTERN), newKeyPair("item", getItemID(Items.IRON_NUGGET)), newKeyPair("item", getItemID(Items.REDSTONE_TORCH)));
    public static AtbywRecipe SHROOM_STICK = createRecipeFromConfig(NewAtbywID("shroomstick"), "", RecipeConfigs.STICK_1, getItemID(AtbywItems.SHROOMSTICK), newKeyPair("item", getBlockID(Blocks.SHROOMLIGHT)));

    public static AtbywRecipe GRANITE_TILES = createRecipeFromConfig(NewAtbywID("granite_tiles"), "", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.GRANITE_TILES), newKeyPair("item", getBlockID(Blocks.GRANITE)));
    public static AtbywRecipe DIORITE_BRICKS = createRecipeFromConfig(NewAtbywID("diorite_bricks"), "", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.DIORITE_BRICKS), newKeyPair("item", getBlockID(Blocks.DIORITE)));
    public static AtbywRecipe ANDESITE_BRICKS = createRecipeFromConfig(NewAtbywID("andesite_bricks"), "", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS), newKeyPair("item", getBlockID(Blocks.ANDESITE)));

    public static AtbywRecipe GRANITE_TILES_STAIRS = createRecipeFromConfig(NewAtbywID("granite_tiles_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.GRANITE_TILES_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.GRANITE_TILES)));
    public static AtbywRecipe DIORITE_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("diorite_bricks_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.DIORITE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.DIORITE_BRICKS)));
    public static AtbywRecipe ANDESITE_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("andesite_bricks_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.ANDESITE_BRICKS)));

    public static AtbywRecipe GRANITE_TILES_SLAB = createRecipeFromConfig(NewAtbywID("granite_tiles_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.GRANITE_TILES_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.GRANITE_TILES)));
    public static AtbywRecipe DIORITE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("diorite_bricks_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.DIORITE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.DIORITE_BRICKS)));
    public static AtbywRecipe ANDESITE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("andesite_bricks_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.ANDESITE_BRICKS)));

    public static AtbywRecipe[] BOOKSHELF_VARIANTS = createMultiRecipesFromConfig(WoodNames.getNamesNoOak(), NewAtbywID("bookshelf"), "bookshelf", RecipeConfigs.BOOKSHELF_2, new Pair<>(AtbywNamespace, "bookshelf"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false));
    public static AtbywRecipe[] BOOKSHELF_TOGGLES_VARIANTS = createMultiRecipesFromConfig(WoodNames.getNames(), NewAtbywID("bookshelf_toggle"), "bookshelf_toggle", RecipeConfigs.BOOKSHELF_TOGGLE_4, new Pair<>(AtbywNamespace, "bookshelf_toggle"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false), newKeyQuadruplet("tag", mcNameSpace, "stone_tool_materials", false));
    public static AtbywRecipe[] LADDERS_VARIANTS = createMultiRecipesFromConfig(WoodNames.getNamesNoOak(), NewAtbywID("ladder"), "ladders", (RecipeConfigs.LADDER_1.getCount() * 2), RecipeConfigs.LADDER_1, new Pair<>(AtbywNamespace, "ladder"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
    public static AtbywRecipe[] FENCE_DOOR_VARIANTS = createMultiRecipesFromConfig(WoodNames.getNames(), NewAtbywID("fence_door"), "fence_door", RecipeConfigs.FENCE_DOOR_1, new Pair<>(AtbywNamespace, "fence_door"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
    public static AtbywRecipe[] FLOWER_SWITCHES = createMultiRecipesFromConfig(FlowerNames.getNames(), NewAtbywID("flower_pull_switch"), "flower_switches", RecipeConfigs.FLOWER_SWITCH_3, new Pair<>(AtbywNamespace, "pull_switch"), newKeyQuadruplet("item", mcNameSpace, "", true), newKeyQuadruplet("item", mcNameSpace, "stick", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false));

    public static AtbywRecipe TERRACOTTA_STAIRS = createRecipeFromConfig(NewAtbywID("terracotta_stairs_from_stick_tag"), "terracotta_stairs", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_STAIRS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
    public static AtbywRecipe TERRACOTTA_SLAB = createRecipeFromConfig(NewAtbywID("terracotta_slab_from_stick_tag"), "terracotta_slab", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_SLAB), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
    public static AtbywRecipe TERRACOTTA_BRICKS = createRecipeFromConfig(NewAtbywID("terracotta_bricks_from_stick_tag"), "terracotta_bricks", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
    public static AtbywRecipe TERRACOTTA_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("terracotta_bricks_stairs_from_stick_tag"), "terracotta_bricks_stairs", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
    public static AtbywRecipe TERRACOTTA_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("terracotta_bricks_slab_from_stick_tag"), "terracotta_bricks_slab", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
    public static AtbywRecipe TERRACOTTA_BRICKS_WALL = createRecipeFromConfig(NewAtbywID("terracotta_bricks_wall_from_stick_tag"), "terracotta_bricks_wall", RecipeConfigs.WALL_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_WALL), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));

    public static AtbywRecipe[] TERRACOTTA_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_stairs_from_dye"), "terracotta_stairs", RecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "terracotta_stairs"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static AtbywRecipe[] TERRACOTTA_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_slab_from_dye"), "terracotta_slab", RecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "terracotta_slab"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_bricks_from_dye"), "terracotta_bricks", RecipeConfigs.BRICKS_1, new Pair<>(AtbywNamespace, "terracotta_bricks"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_bricks_stairs_from_dye"), "terracotta_bricks_stairs", RecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_bricks_slab_from_dye"), "terracotta_bricks_slab", RecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "terracotta_bricks_slab"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_WALL_COLORS_SHAPED = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_bricks_wall_from_dye"), "terracotta_bricks_wall", RecipeConfigs.WALL_1, new Pair<>(AtbywNamespace, "terracotta_bricks_wall"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", true));

    public static AtbywRecipe[] TERRACOTTA_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_stairs"), "terracotta_stairs_dying", RecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_stairs"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_SLAB_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_slab"), "terracotta_slabs_dying", RecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_slab"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_bricks"), "terracotta_bricks_dying", RecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_bricks_stairs"), "terracotta_bricks_stairs_dying", RecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_SLAB_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_bricks_slab"), "terracotta_bricks_slabs_dying", RecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks_slab"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_WALL_COLORS_DYING = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("terracotta_bricks_wall"), "terracotta_bricks_walls_dying", RecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks_wall"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks_wall", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));

    public static AtbywRecipe[] CONCRETE_STAIRS_COLORS = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("concrete_stairs"), "concrete_stairs", RecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "concrete_stairs"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static AtbywRecipe[] CONCRETE_SLAB_COLORS = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("concrete_slab"), "concrete_slab", RecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "concrete_slab"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static AtbywRecipe[] CINDER_BLOCKS_COLORS = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("cinder_blocks"), "cinder_blocks", RecipeConfigs.BRICKS_1, new Pair<>(AtbywNamespace, "cinder_bricks"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static AtbywRecipe[] CINDER_BLOCKS_WALL_COLORS = createMultiRecipesFromConfig(ColorNames.getNames(), NewAtbywID("cinder_blocks_wall"), "cinder_blocks_wall", RecipeConfigs.WALL_1, new Pair<>(AtbywNamespace, "cinder_blocks_wall"), newKeyQuadruplet("item", AtbywNamespace, "cinder_bricks", true));

    public static AtbywRecipe SHULKER_ESSENCE = createShapelessRecipe(NewAtbywID("shulker_essence"), "essence", 1, Lists.newArrayList(getItemID(Items.SHULKER_SHELL), getItemID(Items.SHULKER_SHELL), getItemID(Items.GLASS_BOTTLE)), getItemID(AtbywItems.SHULKER_ESSENCE));
    public static AtbywRecipe CHICKEN_ESSENCE = createShapelessRecipe(NewAtbywID("chicken_essence"), "essence", 1, Lists.newArrayList(getItemID(Items.CHICKEN), getItemID(Items.FEATHER), getItemID(Items.GLASS_BOTTLE)), getItemID(AtbywItems.CHICKEN_ESSENCE));
    public static AtbywRecipe RABBIT_ESSENCE = createShapelessRecipe(NewAtbywID("rabbit_essence"), "essence", 1, Lists.newArrayList(getItemID(Items.RABBIT), getItemID(Items.RABBIT_HIDE), getItemID(Items.GLASS_BOTTLE)), getItemID(AtbywItems.RABBIT_ESSENCE));
    public static AtbywRecipe COD_ESSENCE = createShapelessRecipe(NewAtbywID("cod_essence"), "essence", 1, Lists.newArrayList(getItemID(Items.COD), getItemID(Items.BONE_MEAL), getItemID(Items.GLASS_BOTTLE)), getItemID(AtbywItems.COD_ESSENCE));
    public static AtbywRecipe SALMON_ESSENCE = createShapelessRecipe(NewAtbywID("salmon_essence"), "essence", 1, Lists.newArrayList(getItemID(Items.SALMON), getItemID(Items.BONE_MEAL), getItemID(Items.GLASS_BOTTLE)), getItemID(AtbywItems.SALMON_ESSENCE));
    public static AtbywRecipe PUFFER_FISH_ESSENCE = createShapelessRecipe(NewAtbywID("puffer_fish_essence"), "essence", 1, Lists.newArrayList(getItemID(Items.PUFFERFISH), getItemID(Items.BONE_MEAL), getItemID(Items.GLASS_BOTTLE)), getItemID(AtbywItems.PUFFER_FISH_ESSENCE));
    public static AtbywRecipe SLIME_ESSENCE = createShapelessRecipe(NewAtbywID("slime_essence"), "essence", 1, Lists.newArrayList(getItemID(Items.SLIME_BALL), getItemID(Items.GLASS_BOTTLE)), getItemID(AtbywItems.SLIME_ESSENCE));
    public static AtbywRecipe MAGMA_CUBE_ESSENCE = createShapelessRecipe(NewAtbywID("magma_cube_essence"), "essence", 1, Lists.newArrayList(getItemID(Items.MAGMA_CREAM), getItemID(Items.GLASS_BOTTLE)), getItemID(AtbywItems.MAGMA_CUBE_ESSENCE));

    public static AtbywRecipe SHULKER_STATUE = createShapelessRecipe(NewAtbywID("shulker_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.SHULKER_ESSENCE)), getBlockID(StatueRegistry.SHULKER_STATUE));
    public static AtbywRecipe CHICKEN_STATUE = createShapelessRecipe(NewAtbywID("chicken_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.CHICKEN_ESSENCE)), getBlockID(StatueRegistry.CHICKEN_STATUE));
    public static AtbywRecipe RABBIT_STATUE = createShapelessRecipe(NewAtbywID("rabbit_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.RABBIT_ESSENCE)), getBlockID(StatueRegistry.RABBIT_STATUE));
    public static AtbywRecipe COD_STATUE = createShapelessRecipe(NewAtbywID("cod_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.COD_ESSENCE)), getBlockID(StatueRegistry.COD_STATUE));
    public static AtbywRecipe SALMON_STATUE = createShapelessRecipe(NewAtbywID("salmon_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.SALMON_ESSENCE)), getBlockID(StatueRegistry.SALMON_STATUE));
    public static AtbywRecipe PUFFER_FISH_STATUE = createShapelessRecipe(NewAtbywID("puffer_fish_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.PUFFER_FISH_ESSENCE)), getBlockID(StatueRegistry.PUFFER_FISH_STATUE));
    public static AtbywRecipe SLIME_STATUE = createShapelessRecipe(NewAtbywID("slime_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.SLIME_ESSENCE)), getBlockID(StatueRegistry.SLIME_STATUE));
    public static AtbywRecipe MAGMA_CUBE_STATUE = createShapelessRecipe(NewAtbywID("magma_cube_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.MAGMA_CUBE_ESSENCE)), getBlockID(StatueRegistry.MAGMA_CUBE_STATUE));

    public static AtbywRecipe GRANITE_COLUMN = createRecipeFromConfig(NewAtbywID("granite_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.GRANITE_COLUMN), newKeyPair("item", getBlockID(Blocks.GRANITE)));
    public static AtbywRecipe DIORITE_COLUMN = createRecipeFromConfig(NewAtbywID("diorite_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.DIORITE_COLUMN), newKeyPair("item", getBlockID(Blocks.DIORITE)));
    public static AtbywRecipe ANDESITE_COLUMN = createRecipeFromConfig(NewAtbywID("andesite_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.ANDESITE_COLUMN), newKeyPair("item", getBlockID(Blocks.ANDESITE)));
    public static AtbywRecipe GRANITE_COLUMN_FROM_POLISHED = createRecipeFromConfig(NewAtbywID("granite_column_from_polished"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.GRANITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_GRANITE)));
    public static AtbywRecipe DIORITE_COLUMN_FROM_POLISHED = createRecipeFromConfig(NewAtbywID("diorite_column_from_polished"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.DIORITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_DIORITE)));
    public static AtbywRecipe ANDESITE_COLUMN_FROM_POLISHED = createRecipeFromConfig(NewAtbywID("andesite_column_from_polished"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.ANDESITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_ANDESITE)));
    public static AtbywRecipe SANDSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("sandstone_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SANDSTONE)));
    public static AtbywRecipe SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig(NewAtbywID("sandstone_column_from_cut"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SMOOTH_SANDSTONE)));
    public static AtbywRecipe CHISELED_SANDSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("chiseled_sandstone_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CHISELED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.CHISELED_SANDSTONE)));
    public static AtbywRecipe RED_SANDSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("red_sandstone_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.RED_SANDSTONE)));
    public static AtbywRecipe RED_SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig(NewAtbywID("red_sandstone_column_from_cut"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SMOOTH_RED_SANDSTONE)));
    public static AtbywRecipe CHISELED_RED_SANDSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("chiseled_red_sandstone_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.CHISELED_RED_SANDSTONE)));
    public static AtbywRecipe PURPUR_COLUMN = createRecipeFromConfig(NewAtbywID("purpur_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.PURPUR_COLUMN), newKeyPair("item", getBlockID(Blocks.PURPUR_BLOCK)));
    public static AtbywRecipe STONE_BRICKS_COLUMN = createRecipeFromConfig(NewAtbywID("stone_bricks_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.STONE_BRICKS)));
    public static AtbywRecipe MOSSY_STONE_BRICKS_COLUMN = createRecipeFromConfig(NewAtbywID("mossy_stone_bricks_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.MOSSY_STONE_BRICKS)));
    public static AtbywRecipe CRACKED_STONE_BRICKS_COLUMN = createRecipeFromConfig(NewAtbywID("cracked_stone_bricks_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.CRACKED_STONE_BRICKS)));
    public static AtbywRecipe NETHER_BRICKS_COLUMN = createRecipeFromConfig(NewAtbywID("nether_bricks_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.NETHER_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.NETHER_BRICKS)));
    public static AtbywRecipe QUARTZ_COLUMN = createRecipeFromConfig(NewAtbywID("quartz_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.QUARTZ_COLUMN), newKeyPair("item", getBlockID(Blocks.QUARTZ_BLOCK)));
    public static AtbywRecipe PRISMARINE_COLUMN = createRecipeFromConfig(NewAtbywID("prismarine_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.PRISMARINE_COLUMN), newKeyPair("item", getBlockID(Blocks.PRISMARINE_BRICKS)));
    public static AtbywRecipe BLACKSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("blackstone_column"), "columns", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.BLACKSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.BLACKSTONE)));

    public static AtbywRecipe PURPUR_TILES = createRecipeFromConfig(NewAtbywID("purpur_tiles"), "", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.PURPUR_TILES), newKeyPair("item", getBlockID(Blocks.PURPUR_PILLAR)));
    public static AtbywRecipe CUT_PURPUR_BLOCK = createRecipeFromConfig(NewAtbywID("cut_purpur_block"), "", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK), newKeyPair("item", getBlockID(Blocks.PURPUR_BLOCK)));
    public static AtbywRecipe CHISELED_PURPUR_BLOCK = createRecipeFromConfig(NewAtbywID("chiseled_purpur_block"), "", 1, RecipeConfigs.STICK_1, getBlockID(AtbywBlocks.CHISELED_PURPUR_BLOCK), newKeyPair("item", getBlockID(Blocks.PURPUR_SLAB)));
    public static AtbywRecipe PURPUR_TILES_STAIRS = createRecipeFromConfig(NewAtbywID("purpur_tiles_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PURPUR_TILES_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.PURPUR_TILES)));
    public static AtbywRecipe CUT_PURPUR_STAIRS = createRecipeFromConfig(NewAtbywID("cut_purpur_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.CUT_PURPUR_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK)));
    public static AtbywRecipe SMOOTH_PURPUR_STAIRS = createRecipeFromConfig(NewAtbywID("smooth_purpur_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.SMOOTH_PURPUR_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK)));
    public static AtbywRecipe PURPUR_TILES_SLAB = createRecipeFromConfig(NewAtbywID("purpur_tiles_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PURPUR_TILES_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.PURPUR_TILES)));
    public static AtbywRecipe CUT_PURPUR_SLAB = createRecipeFromConfig(NewAtbywID("cut_purpur_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.CUT_PURPUR_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK)));
    public static AtbywRecipe SMOOTH_PURPUR_SLAB = createRecipeFromConfig(NewAtbywID("smooth_purpur_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.SMOOTH_PURPUR_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK)));

    public static AtbywRecipe COMPACTED_SNOW = createRecipeFromConfig(NewAtbywID("compacted_snow"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COMPACTED_SNOW), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static AtbywRecipe COMPACTED_SNOW_BLOCK = createRecipeFromConfig(NewAtbywID("compacted_snow_block"), "", 1, RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), newKeyPair("item", getBlockID(Blocks.SNOW_BLOCK)));
    public static AtbywRecipe COMPACTED_SNOW_BRICKS = createRecipeFromConfig(NewAtbywID("compacted_snow_bricks"), "", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static AtbywRecipe PACKED_ICE_BRICKS = createRecipeFromConfig(NewAtbywID("packed_ice_bricks"), "", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), newKeyPair("item", getBlockID(Blocks.PACKED_ICE)));
    public static AtbywRecipe BLUE_ICE_BRICKS = createRecipeFromConfig(NewAtbywID("blue_ice_bricks"), "", RecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), newKeyPair("item", getBlockID(Blocks.BLUE_ICE)));

    public static AtbywRecipe COMPACTED_SNOW_BLOCK_STAIRS = createRecipeFromConfig(NewAtbywID("compacted_snow_block_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static AtbywRecipe COMPACTED_SNOW_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("compacted_snow_bricks_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS)));
    public static AtbywRecipe PACKED_ICE_STAIRS = createRecipeFromConfig(NewAtbywID("packed_ice_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PACKED_ICE_STAIRS), newKeyPair("item", getBlockID(Blocks.PACKED_ICE)));
    public static AtbywRecipe BLUE_ICE_STAIRS = createRecipeFromConfig(NewAtbywID("blue_ice_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.BLUE_ICE_STAIRS), newKeyPair("item", getBlockID(Blocks.BLUE_ICE)));
    public static AtbywRecipe PACKED_ICE_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("packed_ice_bricks_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.PACKED_ICE_BRICKS)));
    public static AtbywRecipe BLUE_ICE_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("blue_ice_bricks_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.BLUE_ICE_BRICKS)));

    public static AtbywRecipe COMPACTED_SNOW_BLOCK_SLAB = createRecipeFromConfig(NewAtbywID("compacted_snow_block_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static AtbywRecipe COMPACTED_SNOW_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("compacted_snow_bricks_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS)));
    public static AtbywRecipe PACKED_ICE_SLAB = createRecipeFromConfig(NewAtbywID("packed_ice_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PACKED_ICE_SLAB), newKeyPair("item", getBlockID(Blocks.PACKED_ICE)));
    public static AtbywRecipe BLUE_ICE_SLAB = createRecipeFromConfig(NewAtbywID("blue_ice_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.BLUE_ICE_SLAB), newKeyPair("item", getBlockID(Blocks.BLUE_ICE)));
    public static AtbywRecipe PACKED_ICE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("packed_ice_bricks_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.PACKED_ICE_BRICKS)));
    public static AtbywRecipe BLUE_ICE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("blue_ice_bricks_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.BLUE_ICE_BRICKS)));
    public static AtbywRecipe CHISELED_PACKED_ICE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("chiseled_packed_ice_bricks_slab"), "", 1, RecipeConfigs.STICK_1, getBlockID(AtbywBlocks.CHISELED_PACKED_ICE_BRICKS), newKeyPair("item", getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB)));
    public static AtbywRecipe CHISELED_BLUE_ICE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("chiseled_blue_ice_bricks_slab"), "", 1, RecipeConfigs.STICK_1, getBlockID(AtbywBlocks.CHISELED_BLUE_ICE_BRICKS), newKeyPair("item", getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB)));

    public static AtbywRecipe IRON_SPIKE_TRAP = createRecipeFromConfig(NewAtbywID("iron_spike_trap"), "", RecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.IRON_SPIKE_TRAP), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));
    public static AtbywRecipe GOLD_SPIKE_TRAP = createRecipeFromConfig(NewAtbywID("gold_spike_trap"), "", RecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.GOLD_SPIKE_TRAP), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));
    public static AtbywRecipe DIAMOND_SPIKE_TRAP = createRecipeFromConfig(NewAtbywID("diamond_spike_trap"), "", RecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.DIAMOND_SPIKE_TRAP), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));
    public static AtbywRecipe NETHERITE_SPIKE_TRAP = createRecipeFromConfig(NewAtbywID("netherite_spike_trap"), "", RecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.NETHERITE_SPIKE_TRAP), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));

    public static AtbywRecipe SOUL_JACK_O_LANTERN = createRecipeFromConfig(NewAtbywID("soul_jack_o_lantern"), "", 1, RecipeConfigs.TORCH_2, getBlockID(AtbywBlocks.SOUL_JACK_O_LANTERN), newKeyPair("item", getBlockID(Blocks.JACK_O_LANTERN)), newKeyPair("item", getItemID(Items.SOUL_TORCH)));
    public static AtbywRecipe REDSTONE_JACK_O_LANTERN = createRecipeFromConfig(NewAtbywID("redstone_jack_o_lantern"), "", 1, RecipeConfigs.TORCH_2, getBlockID(AtbywBlocks.REDSTONE_JACK_O_LANTERN), newKeyPair("item", getBlockID(Blocks.JACK_O_LANTERN)), newKeyPair("item", getItemID(Items.REDSTONE_TORCH)));

    public static AtbywRecipe TIMER_REPEATER_MANUAL = createRecipeFromConfig(NewAtbywID("timer_repeater_manual"), "timer_repeater", 1, RecipeConfigs.DYING_DASHED_3, getBlockID(AtbywBlocks.TIMER_REPEATER), newKeyPair("item", getItemID(Items.REDSTONE)), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("item", getItemID(Items.REPEATER)));
    public static AtbywRecipe TIMER_REPEATER_CLOCK = createRecipeFromConfig(NewAtbywID("timer_repeater_clock"), "timer_repeater", RecipeConfigs.TIMER_REPEATER_3, getBlockID(AtbywBlocks.TIMER_REPEATER), newKeyPair("item", getItemID(Items.CLOCK)), newKeyPair("item", getItemID(Items.REDSTONE)), newKeyPair("item", getItemID(Items.REPEATER)));

    public static AtbywRecipe REDSTONE_CROSS_PATH = createRecipeFromConfig(NewAtbywID("redstone_cross_path"), "", RecipeConfigs.BED_2, getBlockID(AtbywBlocks.REDSTONE_CROSS_PATH), newKeyPair("item", getItemID(Items.STONE)), newKeyPair("item", getItemID(Items.REDSTONE)));

    public static AtbywRecipe LARGE_CHAIN_LINK = createRecipeFromConfig(NewAtbywID("large_chain_link"), "", RecipeConfigs.STAR_1, getItemID(AtbywItems.LARGE_CHAIN_LINK), newKeyPair("item", getItemID(Items.IRON_INGOT)));
    public static AtbywRecipe LARGE_CHAIN = createRecipeFromConfig(NewAtbywID("large_chain"), "", RecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.LARGE_CHAIN), newKeyPair("item", getItemID(AtbywItems.LARGE_CHAIN_LINK)));

    public static AtbywRecipe ROOTED_DIRT_STAIRS = createRecipeFromConfig(NewAtbywID("rooted_dirt_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.ROOTED_DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.ROOTED_DIRT)));
    public static AtbywRecipe ROOTED_DIRT_SLAB = createRecipeFromConfig(NewAtbywID("rooted_dirt_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.ROOTED_DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.ROOTED_DIRT)));

    public static AtbywRecipe SAND_STAIRS = createRecipeFromConfig(NewAtbywID("sand_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.SAND_STAIRS), newKeyPair("item", getBlockID(Blocks.SAND)));
    public static AtbywRecipe SAND_SLAB = createRecipeFromConfig(NewAtbywID("sand_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.SAND_SLAB), newKeyPair("item", getBlockID(Blocks.SAND)));
    public static AtbywRecipe RED_SAND_STAIRS = createRecipeFromConfig(NewAtbywID("red_sand_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.RED_SAND_STAIRS), newKeyPair("item", getBlockID(Blocks.RED_SAND)));
    public static AtbywRecipe RED_SAND_SLAB = createRecipeFromConfig(NewAtbywID("red_sand_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.RED_SAND_SLAB), newKeyPair("item", getBlockID(Blocks.RED_SAND)));
    public static AtbywRecipe GRAVEL_STAIRS = createRecipeFromConfig(NewAtbywID("gravel_stairs"), "", RecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.GRAVEL_STAIRS), newKeyPair("item", getBlockID(Blocks.GRAVEL)));
    public static AtbywRecipe GRAVEL_SLAB = createRecipeFromConfig(NewAtbywID("gravel_slab"), "", RecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.GRAVEL_SLAB), newKeyPair("item", getBlockID(Blocks.GRAVEL)));

    public static AtbywRecipe[] LOG_STAIRS = createMultiRecipesFromConfig(WoodNames.getNamesOverworld(), NewAtbywID("log_stairs"), "", RecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "log_stairs"), new Quadruplet<>("item", mcNameSpace, "log", true));
    public static AtbywRecipe[] STEM_STAIRS = createMultiRecipesFromConfig(WoodNames.getNamesNether(), NewAtbywID("stem_stairs"), "", RecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "stem_stairs"), new Quadruplet<>("item", mcNameSpace, "stem", true));

    public static AtbywRecipe[] LOG_SLAB = createMultiRecipesFromConfig(WoodNames.getNamesOverworld(), NewAtbywID("log_slab"), "", RecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "log_slab"), new Quadruplet<>("item", mcNameSpace, "log", true));
    public static AtbywRecipe[] STEM_SLAB = createMultiRecipesFromConfig(WoodNames.getNamesNether(), NewAtbywID("stem_slab"), "", RecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "stem_slab"), new Quadruplet<>("item", mcNameSpace, "stem", true));

    public static void init() {
        LOGGER.info("ATBYW Recipes Inintiliazed");
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        RecipeSmeltingRegistry.inject(map);
        RecipeStonecuttingRegistry.inject(map);

        // Multi recipes
        var i = 0;
        for (var prefix : FlowerNames.getNames()) {
            putRecipe(FLOWER_SWITCHES[i], map);
            i++;
        }

        //Woods without oak
        i = 0;
        for (var prefix : WoodNames.getNamesNoOak()) {
            putRecipe(BOOKSHELF_VARIANTS[i], map);
            putRecipe(LADDERS_VARIANTS[i], map);
            i++;
        }

        //Woods Overworld
        i = 0;
        for (var prefix : WoodNames.getNamesOverworld()) {
            putRecipe(LOG_STAIRS[i], map);
            putRecipe(LOG_SLAB[i], map);
            i++;
        }

        //Woods Nether
        i = 0;
        for (var prefix : WoodNames.getNamesNether()) {
            putRecipe(STEM_STAIRS[i], map);
            putRecipe(STEM_SLAB[i], map);
            i++;
        }

        //Woods
        i = 0;
        for (var prefix : WoodNames.getNames()) {
            putRecipe(FENCE_DOOR_VARIANTS[i], map);
            putRecipe(BOOKSHELF_TOGGLES_VARIANTS[i], map);
            i++;
        }

        //Colors
        i = 0;
        for (var prefix : ColorNames.getNames()) {
            putRecipe(TERRACOTTA_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(TERRACOTTA_SLAB_COLORS_SHAPED[i], map);
            putRecipe(TERRACOTTA_BRICKS_COLORS_SHAPED[i], map);
            putRecipe(TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED[i], map);
            putRecipe(TERRACOTTA_BRICKS_WALL_COLORS_SHAPED[i], map);

            putRecipe(TERRACOTTA_STAIRS_COLORS_DYING[i], map);
            putRecipe(TERRACOTTA_SLAB_COLORS_DYING[i], map);
            putRecipe(TERRACOTTA_BRICKS_COLORS_DYING[i], map);
            putRecipe(TERRACOTTA_BRICKS_STAIRS_COLORS_DYING[i], map);
            putRecipe(TERRACOTTA_BRICKS_SLAB_COLORS_DYING[i], map);
            putRecipe(TERRACOTTA_BRICKS_WALL_COLORS_DYING[i], map);

            putRecipe(CONCRETE_STAIRS_COLORS[i], map);
            putRecipe(CONCRETE_SLAB_COLORS[i], map);
            putRecipe(CINDER_BLOCKS_COLORS[i], map);
            putRecipe(CINDER_BLOCKS_WALL_COLORS[i], map);
            i++;
        }

        // Single recipes
        putRecipe(TERRACOTTA_STAIRS, map);
        putRecipe(TERRACOTTA_SLAB, map);
        putRecipe(TERRACOTTA_BRICKS, map);
        putRecipe(TERRACOTTA_BRICKS_STAIRS, map);
        putRecipe(TERRACOTTA_BRICKS_SLAB, map);
        putRecipe(TERRACOTTA_BRICKS_WALL, map);

        putRecipe(DIRT_STAIRS, map);
        putRecipe(GRASS_BLOCK_STAIRS, map);
        putRecipe(MYCELIUM_STAIRS, map);
        putRecipe(COARSE_DIRT_STAIRS, map);
        putRecipe(PODZOL_STAIRS, map);
        putRecipe(NETHERRACK_STAIRS, map);
        putRecipe(CRIMSON_NYLIUM_STAIRS, map);
        putRecipe(WARPED_NYLIUM_STAIRS, map);

        putRecipe(DIRT_SLAB, map);
        putRecipe(GRASS_BLOCK_SLAB, map);
        putRecipe(MYCELIUM_SLAB, map);
        putRecipe(COARSE_DIRT_SLAB, map);
        putRecipe(PODZOL_SLAB, map);
        putRecipe(NETHERRACK_SLAB, map);
        putRecipe(CRIMSON_NYLIUM_SLAB, map);
        putRecipe(WARPED_NYLIUM_SLAB, map);

        putRecipe(BASALT_BRICKS_FROM_BASALT, map);
        putRecipe(BASALT_PILLAR_FROM_BASALT, map);
        putRecipe(BASALT_BRICKS_FROM_POLISHED_BASALT, map);
        putRecipe(BASALT_PILLAR_FROM_POLISHED_BASALT, map);

        putRecipe(IRON_FENCE_DOOR, map);
        putRecipe(BAMBOO_LADDER, map);
        putRecipe(BAMBOO_STICK, map);
        putRecipe(REDSTONE_LANTERN, map);
        putRecipe(SHROOM_STICK, map);

        putRecipe(GRANITE_TILES, map);
        putRecipe(DIORITE_BRICKS, map);
        putRecipe(ANDESITE_BRICKS, map);

        putRecipe(GRANITE_TILES_STAIRS, map);
        putRecipe(DIORITE_BRICKS_STAIRS, map);
        putRecipe(ANDESITE_BRICKS_STAIRS, map);

        putRecipe(GRANITE_TILES_SLAB, map);
        putRecipe(DIORITE_BRICKS_SLAB, map);
        putRecipe(ANDESITE_BRICKS_SLAB, map);

        putRecipe(SHULKER_ESSENCE, map);
        putRecipe(CHICKEN_ESSENCE, map);
        putRecipe(RABBIT_ESSENCE, map);
        putRecipe(COD_ESSENCE, map);
        putRecipe(SALMON_ESSENCE, map);
        putRecipe(PUFFER_FISH_ESSENCE, map);
        putRecipe(MAGMA_CUBE_ESSENCE, map);
        putRecipe(SLIME_ESSENCE, map);

        putRecipe(SHULKER_STATUE, map);
        putRecipe(CHICKEN_STATUE, map);
        putRecipe(RABBIT_STATUE, map);
        putRecipe(COD_STATUE, map);
        putRecipe(SALMON_STATUE, map);
        putRecipe(PUFFER_FISH_STATUE, map);
        putRecipe(SLIME_STATUE, map);
        putRecipe(MAGMA_CUBE_STATUE, map);

        putRecipe(GRANITE_COLUMN, map);
        putRecipe(DIORITE_COLUMN, map);
        putRecipe(ANDESITE_COLUMN, map);
        putRecipe(GRANITE_COLUMN_FROM_POLISHED, map);
        putRecipe(DIORITE_COLUMN_FROM_POLISHED, map);
        putRecipe(ANDESITE_COLUMN_FROM_POLISHED, map);
        putRecipe(SANDSTONE_COLUMN, map);
        putRecipe(SANDSTONE_COLUMN_FROM_CUT, map);
        putRecipe(CHISELED_SANDSTONE_COLUMN, map);
        putRecipe(RED_SANDSTONE_COLUMN, map);
        putRecipe(RED_SANDSTONE_COLUMN_FROM_CUT, map);
        putRecipe(CHISELED_RED_SANDSTONE_COLUMN, map);
        putRecipe(PURPUR_COLUMN, map);
        putRecipe(STONE_BRICKS_COLUMN, map);
        putRecipe(MOSSY_STONE_BRICKS_COLUMN, map);
        putRecipe(CRACKED_STONE_BRICKS_COLUMN, map);
        putRecipe(NETHER_BRICKS_COLUMN, map);
        putRecipe(QUARTZ_COLUMN, map);
        putRecipe(PRISMARINE_COLUMN, map);
        putRecipe(BLACKSTONE_COLUMN, map);

        putRecipe(PURPUR_TILES, map);
        putRecipe(CUT_PURPUR_BLOCK, map);
        putRecipe(CHISELED_PURPUR_BLOCK, map);
        putRecipe(PURPUR_TILES_STAIRS, map);
        putRecipe(CUT_PURPUR_STAIRS, map);
        putRecipe(SMOOTH_PURPUR_STAIRS, map);
        putRecipe(PURPUR_TILES_SLAB, map);
        putRecipe(CUT_PURPUR_SLAB, map);
        putRecipe(SMOOTH_PURPUR_SLAB, map);

        putRecipe(COMPACTED_SNOW, map);
        putRecipe(COMPACTED_SNOW_BLOCK, map);
        putRecipe(COMPACTED_SNOW_BRICKS, map);
        putRecipe(PACKED_ICE_BRICKS, map);
        putRecipe(BLUE_ICE_BRICKS, map);

        putRecipe(COMPACTED_SNOW_BLOCK_STAIRS, map);
        putRecipe(COMPACTED_SNOW_BRICKS_STAIRS, map);
        putRecipe(PACKED_ICE_STAIRS, map);
        putRecipe(BLUE_ICE_STAIRS, map);
        putRecipe(PACKED_ICE_BRICKS_STAIRS, map);
        putRecipe(BLUE_ICE_BRICKS_STAIRS, map);

        putRecipe(COMPACTED_SNOW_BLOCK_SLAB, map);
        putRecipe(COMPACTED_SNOW_BRICKS_SLAB, map);
        putRecipe(PACKED_ICE_SLAB, map);
        putRecipe(BLUE_ICE_SLAB, map);
        putRecipe(PACKED_ICE_BRICKS_SLAB, map);
        putRecipe(BLUE_ICE_BRICKS_SLAB, map);
        putRecipe(CHISELED_PACKED_ICE_BRICKS_SLAB, map);
        putRecipe(CHISELED_BLUE_ICE_BRICKS_SLAB, map);

        putRecipe(IRON_SPIKE_TRAP, map);
        putRecipe(GOLD_SPIKE_TRAP, map);
        putRecipe(DIAMOND_SPIKE_TRAP, map);
        putRecipe(NETHERITE_SPIKE_TRAP, map);

        putRecipe(SOUL_JACK_O_LANTERN, map);
        putRecipe(REDSTONE_JACK_O_LANTERN, map);

        putRecipe(TIMER_REPEATER_MANUAL, map);
        putRecipe(TIMER_REPEATER_CLOCK, map);

        putRecipe(REDSTONE_CROSS_PATH, map);

        putRecipe(LARGE_CHAIN_LINK, map);
        putRecipe(LARGE_CHAIN, map);

        putRecipe(ROOTED_DIRT_STAIRS, map);
        putRecipe(ROOTED_DIRT_SLAB, map);

        putRecipe(SAND_STAIRS, map);
        putRecipe(SAND_SLAB, map);
        putRecipe(RED_SAND_STAIRS, map);
        putRecipe(RED_SAND_SLAB, map);
        putRecipe(GRAVEL_STAIRS, map);
        putRecipe(GRAVEL_SLAB, map);
    }
}