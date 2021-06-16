package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;
import com.google.gson.*;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.items.AtbywItems;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.Quadruplet;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

import static net.azagwen.atbyw.datagen.AtbywRecipeUtils.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywRecipes {
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

        AtbywAdvancements.recipeMap.put(recipeId, AtbywAdvancements.unlockShapelessRecipe(json, recipeId));
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

        AtbywAdvancements.recipeMap.put(recipeId, AtbywAdvancements.unlockShapedRecipe(json, recipeId));
        return new AtbywRecipe(json, recipeId);
    }

    //Shaped recipes config methods
    @SafeVarargs
    private static AtbywRecipe createRecipeFromConfig(Identifier recipeId, String group, int count, AtbywRecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        var keys = new Key[ingredient.length];

        for (int i = 0; i < ingredient.length; i++) {
            keys[i] = new Key(config.getKeyChars().get(i), ingredient[i].getLeft(), ingredient[i].getRight());
        }

        return createShapedRecipe(recipeId, group, config.getPattern(), Lists.newArrayList(keys), result, count);
    }

    @SafeVarargs
    private static AtbywRecipe createRecipeFromConfig(Identifier recipeId, String group, AtbywRecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        return createRecipeFromConfig(recipeId, group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    private static AtbywRecipe[] createMultiRecipesFromConfig(String[] nameArray, Identifier recipeId, String group, int count, AtbywRecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredients) {
        var recipes = new AtbywRecipe[nameArray.length];
        var ing = new Pair[ingredients.length];

        int i = 0;
        for (var prefix : nameArray) {
            var newRecipeId = new Identifier(recipeId.getNamespace(), prefix + recipeId.getPath());
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
    private static AtbywRecipe[] createMultiRecipesFromConfig(String[] nameArray, Identifier recipeId, String group, AtbywRecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray, recipeId, group, config.getCount(), config, result, ingredient);
    }
    public static AtbywRecipe DIRT_STAIRS = createRecipeFromConfig(NewAtbywID("dirt_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.DIRT)));
    public static AtbywRecipe GRASS_BLOCK_STAIRS = createRecipeFromConfig(NewAtbywID("grass_block_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.GRASS_BLOCK_STAIRS), newKeyPair("item", getBlockID(Blocks.GRASS_BLOCK)));
    public static AtbywRecipe MYCELIUM_STAIRS = createRecipeFromConfig(NewAtbywID("mycelium_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.MYCELIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.MYCELIUM)));
    public static AtbywRecipe COARSE_DIRT_STAIRS = createRecipeFromConfig(NewAtbywID("coarse_dirt_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COARSE_DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.COARSE_DIRT)));
    public static AtbywRecipe PODZOL_STAIRS = createRecipeFromConfig(NewAtbywID("podzol_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PODZOL_STAIRS), newKeyPair("item", getBlockID(Blocks.PODZOL)));
    public static AtbywRecipe NETHERRACK_STAIRS = createRecipeFromConfig(NewAtbywID("netherrack_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.NETHERRACK_STAIRS), newKeyPair("item", getBlockID(Blocks.NETHERRACK)));
    public static AtbywRecipe CRIMSON_NYLIUM_STAIRS = createRecipeFromConfig(NewAtbywID("crimson_nylium_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.CRIMSON_NYLIUM)));
    public static AtbywRecipe WARPED_NYLIUM_STAIRS = createRecipeFromConfig(NewAtbywID("warped_nylium_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.WARPED_NYLIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.WARPED_NYLIUM)));

    public static AtbywRecipe DIRT_SLAB = createRecipeFromConfig(NewAtbywID("dirt_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.DIRT)));
    public static AtbywRecipe GRASS_BLOCK_SLAB = createRecipeFromConfig(NewAtbywID("grass_block_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.GRASS_BLOCK_SLAB), newKeyPair("item", getBlockID(Blocks.GRASS_BLOCK)));
    public static AtbywRecipe MYCELIUM_SLAB = createRecipeFromConfig(NewAtbywID("mycelium_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.MYCELIUM_SLAB), newKeyPair("item", getBlockID(Blocks.MYCELIUM)));
    public static AtbywRecipe COARSE_DIRT_SLAB = createRecipeFromConfig(NewAtbywID("coarse_dirt_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COARSE_DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.COARSE_DIRT)));
    public static AtbywRecipe PODZOL_SLAB = createRecipeFromConfig(NewAtbywID("podzol_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PODZOL_SLAB), newKeyPair("item", getBlockID(Blocks.PODZOL)));
    public static AtbywRecipe NETHERRACK_SLAB = createRecipeFromConfig(NewAtbywID("netherrack_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.NETHERRACK_SLAB), newKeyPair("item", getBlockID(Blocks.NETHERRACK)));
    public static AtbywRecipe CRIMSON_NYLIUM_SLAB = createRecipeFromConfig(NewAtbywID("crimson_nylium_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_SLAB), newKeyPair("item", getBlockID(Blocks.CRIMSON_NYLIUM)));
    public static AtbywRecipe WARPED_NYLIUM_SLAB = createRecipeFromConfig(NewAtbywID("warped_nylium_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.WARPED_NYLIUM_SLAB), newKeyPair("item", getBlockID(Blocks.WARPED_NYLIUM)));

    public static AtbywRecipe BASALT_BRICKS_FROM_BASALT = createRecipeFromConfig(NewAtbywID("basalt_bricks_from_basalt"), "basalt_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BASALT_BRICKS), newKeyPair("item", getBlockID(Blocks.BASALT)));
    public static AtbywRecipe BASALT_PILLAR_FROM_BASALT = createRecipeFromConfig(NewAtbywID("basalt_pillar_from_basalt"), "basalt_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BASALT_BRICKS), newKeyPair("item", getBlockID(Blocks.BASALT)));
    public static AtbywRecipe BASALT_BRICKS_FROM_POLISHED_BASALT = createRecipeFromConfig(NewAtbywID("basalt_bricks_from_polished_basalt"), "basalt_pillar", 2, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.BASALT_PILLAR), newKeyPair("item", getBlockID(Blocks.POLISHED_BASALT)));
    public static AtbywRecipe BASALT_PILLAR_FROM_POLISHED_BASALT = createRecipeFromConfig(NewAtbywID("basalt_pillar_from_polished_basalt"), "basalt_pillar", 2, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.BASALT_PILLAR), newKeyPair("item", getBlockID(Blocks.POLISHED_BASALT)));

    public static AtbywRecipe IRON_FENCE_DOOR = createRecipeFromConfig(NewAtbywID("iron_fence_door"), "fence_door", AtbywRecipeConfigs.FENCE_DOOR_1, getBlockID(AtbywBlocks.IRON_FENCE_DOOR), newKeyPair("item", getItemID(Items.IRON_INGOT)));
    public static AtbywRecipe BAMBOO_LADDER = createRecipeFromConfig(NewAtbywID("bamboo_ladder"), "ladders", 3, AtbywRecipeConfigs.RAIL_2, getBlockID(AtbywBlocks.BAMBOO_LADDER), newKeyPair("item", getItemID(AtbywItems.BAMBOO_STICK)), newKeyPair("item", getItemID(Items.BAMBOO)));
    public static AtbywRecipe BAMBOO_STICK = createRecipeFromConfig(NewAtbywID("bamboo_stick"), "stick", AtbywRecipeConfigs.STICK_1, getItemID(AtbywItems.BAMBOO_STICK), newKeyPair("item", getItemID(Items.BAMBOO)));
    public static AtbywRecipe REDSTONE_LANTERN = createRecipeFromConfig(NewAtbywID("redstone_lantern"), "", AtbywRecipeConfigs.DYING_2, getBlockID(AtbywBlocks.REDSTONE_LANTERN), newKeyPair("item", getItemID(Items.IRON_NUGGET)), newKeyPair("item", getItemID(Items.REDSTONE_TORCH)));
    public static AtbywRecipe SHROOM_STICK = createRecipeFromConfig(NewAtbywID("shroomstick"), "", AtbywRecipeConfigs.STICK_1, getItemID(AtbywItems.SHROOMSTICK), newKeyPair("item", getBlockID(Blocks.SHROOMLIGHT)));

    public static AtbywRecipe GRANITE_TILES = createRecipeFromConfig(NewAtbywID("granite_tiles"), "", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.GRANITE_TILES), newKeyPair("item", getBlockID(Blocks.GRANITE)));
    public static AtbywRecipe DIORITE_BRICKS = createRecipeFromConfig(NewAtbywID("diorite_bricks"), "", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.DIORITE_BRICKS), newKeyPair("item", getBlockID(Blocks.DIORITE)));
    public static AtbywRecipe ANDESITE_BRICKS = createRecipeFromConfig(NewAtbywID("andesite_bricks"), "", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS), newKeyPair("item", getBlockID(Blocks.ANDESITE)));

    public static AtbywRecipe GRANITE_TILES_STAIRS = createRecipeFromConfig(NewAtbywID("granite_tiles_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.GRANITE_TILES_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.GRANITE_TILES)));
    public static AtbywRecipe DIORITE_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("diorite_bricks_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.DIORITE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.DIORITE_BRICKS)));
    public static AtbywRecipe ANDESITE_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("andesite_bricks_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.ANDESITE_BRICKS)));

    public static AtbywRecipe GRANITE_TILES_SLAB = createRecipeFromConfig(NewAtbywID("granite_tiles_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.GRANITE_TILES_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.GRANITE_TILES)));
    public static AtbywRecipe DIORITE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("diorite_bricks_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.DIORITE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.DIORITE_BRICKS)));
    public static AtbywRecipe ANDESITE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("andesite_bricks_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.ANDESITE_BRICKS)));

    public static AtbywRecipe[] BOOKSHELF_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES_NO_OAK, NewAtbywID("_bookshelf"), "bookshelf", AtbywRecipeConfigs.BOOKSHELF_2, new Pair<>(AtbywNamespace, "bookshelf"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false));
    public static AtbywRecipe[] BOOKSHELF_TOGGLES_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, NewAtbywID("_bookshelf_toggle"), "bookshelf_toggle", AtbywRecipeConfigs.BOOKSHELF_TOGGLE_4, new Pair<>(AtbywNamespace, "bookshelf_toggle"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false), newKeyQuadruplet("tag", mcNameSpace, "stone_tool_materials", false));
    public static AtbywRecipe[] LADDERS_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES_NO_OAK, NewAtbywID("_ladder"), "ladders", (AtbywRecipeConfigs.LADDER_1.getCount() * 2), AtbywRecipeConfigs.LADDER_1, new Pair<>(AtbywNamespace, "ladder"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
    public static AtbywRecipe[] FENCE_DOOR_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, NewAtbywID("_fence_door"), "fence_door", AtbywRecipeConfigs.FENCE_DOOR_1, new Pair<>(AtbywNamespace, "fence_door"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
    public static AtbywRecipe[] FLOWER_SWITCHES = createMultiRecipesFromConfig(FLOWER_NAMES, NewAtbywID("_flower_pull_switch"), "flower_switches", AtbywRecipeConfigs.FLOWER_SWITCH_3, new Pair<>(AtbywNamespace, "pull_switch"), newKeyQuadruplet("item", mcNameSpace, "", true), newKeyQuadruplet("item", mcNameSpace, "stick", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false));

    public static AtbywRecipe TERRACOTTA_STAIRS = createRecipeFromConfig(NewAtbywID("terracotta_stairs_from_stick_tag"), "terracotta_stairs", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_STAIRS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
    public static AtbywRecipe TERRACOTTA_SLAB = createRecipeFromConfig(NewAtbywID("terracotta_slab_from_stick_tag"), "terracotta_slab", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_SLAB), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
    public static AtbywRecipe TERRACOTTA_BRICKS = createRecipeFromConfig(NewAtbywID("terracotta_bricks_from_stick_tag"), "terracotta_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
    public static AtbywRecipe TERRACOTTA_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("terracotta_bricks_stairs_from_stick_tag"), "terracotta_bricks_stairs", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
    public static AtbywRecipe TERRACOTTA_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("terracotta_bricks_slab_from_stick_tag"), "terracotta_bricks_slab", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
    public static AtbywRecipe TERRACOTTA_BRICKS_WALL = createRecipeFromConfig(NewAtbywID("terracotta_bricks_wall_from_stick_tag"), "terracotta_bricks_wall", AtbywRecipeConfigs.WALL_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_WALL), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));

    public static AtbywRecipe[] TERRACOTTA_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_stairs_from_dye"), "terracotta_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "terracotta_stairs"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static AtbywRecipe[] TERRACOTTA_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_slab_from_dye"), "terracotta_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "terracotta_slab"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_bricks_from_dye"), "terracotta_bricks", AtbywRecipeConfigs.BRICKS_1, new Pair<>(AtbywNamespace, "terracotta_bricks"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_bricks_stairs_from_dye"), "terracotta_bricks_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_bricks_slab_from_dye"), "terracotta_bricks_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "terracotta_bricks_slab"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_WALL_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_bricks_wall_from_dye"), "terracotta_bricks_wall", AtbywRecipeConfigs.WALL_1, new Pair<>(AtbywNamespace, "terracotta_bricks_wall"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", true));

    public static AtbywRecipe[] TERRACOTTA_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_stairs"), "terracotta_stairs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_stairs"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_SLAB_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_slab"), "terracotta_slabs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_slab"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_bricks"), "terracotta_bricks_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_bricks_stairs"), "terracotta_bricks_stairs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_SLAB_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_bricks_slab"), "terracotta_bricks_slabs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks_slab"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static AtbywRecipe[] TERRACOTTA_BRICKS_WALL_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_terracotta_bricks_wall"), "terracotta_bricks_walls_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks_wall"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks_wall", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));

    public static AtbywRecipe[] CONCRETE_STAIRS_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_concrete_stairs"), "concrete_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "concrete_stairs"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static AtbywRecipe[] CONCRETE_SLAB_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_concrete_slab"), "concrete_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "concrete_slab"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static AtbywRecipe[] CINDER_BLOCKS_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_cinder_blocks"), "cinder_blocks", AtbywRecipeConfigs.BRICKS_1, new Pair<>(AtbywNamespace, "cinder_bricks"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static AtbywRecipe[] CINDER_BLOCKS_WALL_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, NewAtbywID("_cinder_blocks_wall"), "cinder_blocks_wall", AtbywRecipeConfigs.WALL_1, new Pair<>(AtbywNamespace, "cinder_blocks_wall"), newKeyQuadruplet("item", AtbywNamespace, "cinder_bricks", true));

    public static AtbywRecipe SHULKER_ESSENCE = createRecipeFromConfig(NewAtbywID("shulker_essence"), "essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.SHULKER_ESSENCE), newKeyPair("item", getItemID(Items.SHULKER_SHELL)), newKeyPair("item", getItemID(Items.SHULKER_SHELL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static AtbywRecipe CHICKEN_ESSENCE = createRecipeFromConfig(NewAtbywID("chicken_essence"), "essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.CHICKEN_ESSENCE), newKeyPair("item", getItemID(Items.CHICKEN)), newKeyPair("item", getItemID(Items.FEATHER)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static AtbywRecipe RABBIT_ESSENCE = createRecipeFromConfig(NewAtbywID("rabbit_essence"), "essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.RABBIT_ESSENCE), newKeyPair("item", getItemID(Items.RABBIT)), newKeyPair("item", getItemID(Items.RABBIT_HIDE)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static AtbywRecipe COD_ESSENCE = createRecipeFromConfig(NewAtbywID("cod_essence"), "essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.COD_ESSENCE), newKeyPair("item", getItemID(Items.COD)), newKeyPair("item", getItemID(Items.BONE_MEAL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static AtbywRecipe SALMON_ESSENCE = createRecipeFromConfig(NewAtbywID("salmon_essence"), "essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.SALMON_ESSENCE), newKeyPair("item", getItemID(Items.SALMON)), newKeyPair("item", getItemID(Items.BONE_MEAL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static AtbywRecipe PUFFER_FISH_ESSENCE = createRecipeFromConfig(NewAtbywID("puffer_fish_essence"), "essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.PUFFER_FISH_ESSENCE), newKeyPair("item", getItemID(Items.PUFFERFISH)), newKeyPair("item", getItemID(Items.BONE_MEAL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static AtbywRecipe SLIME_ESSENCE = createRecipeFromConfig(NewAtbywID("slime_essence"), "essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.SLIME_ESSENCE), newKeyPair("item", getItemID(Items.SLIME_BALL)), newKeyPair("item", getItemID(Items.SLIME_BALL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static AtbywRecipe MAGMA_CUBE_ESSENCE = createRecipeFromConfig(NewAtbywID("magma_cube_essence"), "essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.MAGMA_CUBE_ESSENCE), newKeyPair("item", getItemID(Items.MAGMA_CREAM)), newKeyPair("item", getItemID(Items.MAGMA_CREAM)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));

    public static AtbywRecipe SHULKER_STATUE = createShapelessRecipe(NewAtbywID("shulker_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.SHULKER_ESSENCE)), getBlockID(StatueRegistry.SHULKER_STATUE));
    public static AtbywRecipe CHICKEN_STATUE = createShapelessRecipe(NewAtbywID("chicken_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.CHICKEN_ESSENCE)), getBlockID(StatueRegistry.CHICKEN_STATUE));
    public static AtbywRecipe RABBIT_STATUE = createShapelessRecipe(NewAtbywID("rabbit_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.RABBIT_ESSENCE)), getBlockID(StatueRegistry.RABBIT_STATUE));
    public static AtbywRecipe COD_STATUE = createShapelessRecipe(NewAtbywID("cod_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.COD_ESSENCE)), getBlockID(StatueRegistry.COD_STATUE));
    public static AtbywRecipe SALMON_STATUE = createShapelessRecipe(NewAtbywID("salmon_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.SALMON_ESSENCE)), getBlockID(StatueRegistry.SALMON_STATUE));
    public static AtbywRecipe PUFFER_FISH_STATUE = createShapelessRecipe(NewAtbywID("puffer_fish_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.PUFFER_FISH_ESSENCE)), getBlockID(StatueRegistry.PUFFER_FISH_STATUE));
    public static AtbywRecipe SLIME_STATUE = createShapelessRecipe(NewAtbywID("slime_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.SLIME_ESSENCE)), getBlockID(StatueRegistry.SLIME_STATUE));
    public static AtbywRecipe MAGMA_CUBE_STATUE = createShapelessRecipe(NewAtbywID("magma_cube_statue"), "statues", 1, Lists.newArrayList(getBlockID(Blocks.STONE), getItemID(AtbywItems.MAGMA_CUBE_ESSENCE)), getBlockID(StatueRegistry.MAGMA_CUBE_STATUE));

    public static AtbywRecipe GRANITE_COLUMN = createRecipeFromConfig(NewAtbywID("granite_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.GRANITE_COLUMN), newKeyPair("item", getBlockID(Blocks.GRANITE)));
    public static AtbywRecipe DIORITE_COLUMN = createRecipeFromConfig(NewAtbywID("diorite_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.DIORITE_COLUMN), newKeyPair("item", getBlockID(Blocks.DIORITE)));
    public static AtbywRecipe ANDESITE_COLUMN = createRecipeFromConfig(NewAtbywID("andesite_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.ANDESITE_COLUMN), newKeyPair("item", getBlockID(Blocks.ANDESITE)));
    public static AtbywRecipe GRANITE_COLUMN_FROM_POLISHED = createRecipeFromConfig(NewAtbywID("granite_column_from_polished"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.GRANITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_GRANITE)));
    public static AtbywRecipe DIORITE_COLUMN_FROM_POLISHED = createRecipeFromConfig(NewAtbywID("diorite_column_from_polished"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.DIORITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_DIORITE)));
    public static AtbywRecipe ANDESITE_COLUMN_FROM_POLISHED = createRecipeFromConfig(NewAtbywID("andesite_column_from_polished"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.ANDESITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_ANDESITE)));
    public static AtbywRecipe SANDSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("sandstone_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SANDSTONE)));
    public static AtbywRecipe SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig(NewAtbywID("sandstone_column_from_cut"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SMOOTH_SANDSTONE)));
    public static AtbywRecipe CHISELED_SANDSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("chiseled_sandstone_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CHISELED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.CHISELED_SANDSTONE)));
    public static AtbywRecipe RED_SANDSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("red_sandstone_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.RED_SANDSTONE)));
    public static AtbywRecipe RED_SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig(NewAtbywID("red_sandstone_column_from_cut"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SMOOTH_RED_SANDSTONE)));
    public static AtbywRecipe CHISELED_RED_SANDSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("chiseled_red_sandstone_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.CHISELED_RED_SANDSTONE)));
    public static AtbywRecipe PURPUR_COLUMN = createRecipeFromConfig(NewAtbywID("purpur_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.PURPUR_COLUMN), newKeyPair("item", getBlockID(Blocks.PURPUR_BLOCK)));
    public static AtbywRecipe STONE_BRICKS_COLUMN = createRecipeFromConfig(NewAtbywID("stone_bricks_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.STONE_BRICKS)));
    public static AtbywRecipe MOSSY_STONE_BRICKS_COLUMN = createRecipeFromConfig(NewAtbywID("mossy_stone_bricks_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.MOSSY_STONE_BRICKS)));
    public static AtbywRecipe CRACKED_STONE_BRICKS_COLUMN = createRecipeFromConfig(NewAtbywID("cracked_stone_bricks_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.CRACKED_STONE_BRICKS)));
    public static AtbywRecipe NETHER_BRICKS_COLUMN = createRecipeFromConfig(NewAtbywID("nether_bricks_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.NETHER_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.NETHER_BRICKS)));
    public static AtbywRecipe QUARTZ_COLUMN = createRecipeFromConfig(NewAtbywID("quartz_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.QUARTZ_COLUMN), newKeyPair("item", getBlockID(Blocks.QUARTZ_BLOCK)));
    public static AtbywRecipe PRISMARINE_COLUMN = createRecipeFromConfig(NewAtbywID("prismarine_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.PRISMARINE_COLUMN), newKeyPair("item", getBlockID(Blocks.PRISMARINE_BRICKS)));
    public static AtbywRecipe BLACKSTONE_COLUMN = createRecipeFromConfig(NewAtbywID("blackstone_column"), "columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.BLACKSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.BLACKSTONE)));

    public static AtbywRecipe PURPUR_TILES = createRecipeFromConfig(NewAtbywID("purpur_tiles"), "", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.PURPUR_TILES), newKeyPair("item", getBlockID(Blocks.PURPUR_PILLAR)));
    public static AtbywRecipe CUT_PURPUR_BLOCK = createRecipeFromConfig(NewAtbywID("cut_purpur_block"), "", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK), newKeyPair("item", getBlockID(Blocks.PURPUR_BLOCK)));
    public static AtbywRecipe CHISELED_PURPUR_BLOCK = createRecipeFromConfig(NewAtbywID("chiseled_purpur_block"), "", 1, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.CHISELED_PURPUR_BLOCK), newKeyPair("item", getBlockID(Blocks.PURPUR_SLAB)));
    public static AtbywRecipe PURPUR_TILES_STAIRS = createRecipeFromConfig(NewAtbywID("purpur_tiles_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PURPUR_TILES_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.PURPUR_TILES)));
    public static AtbywRecipe CUT_PURPUR_STAIRS = createRecipeFromConfig(NewAtbywID("cut_purpur_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.CUT_PURPUR_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK)));
    public static AtbywRecipe SMOOTH_PURPUR_STAIRS = createRecipeFromConfig(NewAtbywID("smooth_purpur_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.SMOOTH_PURPUR_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK)));
    public static AtbywRecipe PURPUR_TILES_SLAB = createRecipeFromConfig(NewAtbywID("purpur_tiles_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PURPUR_TILES_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.PURPUR_TILES)));
    public static AtbywRecipe CUT_PURPUR_SLAB = createRecipeFromConfig(NewAtbywID("cut_purpur_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.CUT_PURPUR_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK)));
    public static AtbywRecipe SMOOTH_PURPUR_SLAB = createRecipeFromConfig(NewAtbywID("smooth_purpur_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.SMOOTH_PURPUR_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK)));

    public static AtbywRecipe COMPACTED_SNOW = createRecipeFromConfig(NewAtbywID("compacted_snow"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COMPACTED_SNOW), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static AtbywRecipe COMPACTED_SNOW_BLOCK = createRecipeFromConfig(NewAtbywID("compacted_snow_block"), "", 1, AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), newKeyPair("item", getBlockID(Blocks.SNOW_BLOCK)));
    public static AtbywRecipe COMPACTED_SNOW_BRICKS = createRecipeFromConfig(NewAtbywID("compacted_snow_bricks"), "", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static AtbywRecipe PACKED_ICE_BRICKS = createRecipeFromConfig(NewAtbywID("packed_ice_bricks"), "", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), newKeyPair("item", getBlockID(Blocks.PACKED_ICE)));
    public static AtbywRecipe BLUE_ICE_BRICKS = createRecipeFromConfig(NewAtbywID("blue_ice_bricks"), "", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), newKeyPair("item", getBlockID(Blocks.BLUE_ICE)));

    public static AtbywRecipe COMPACTED_SNOW_BLOCK_STAIRS = createRecipeFromConfig(NewAtbywID("compacted_snow_block_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static AtbywRecipe COMPACTED_SNOW_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("compacted_snow_bricks_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS)));
    public static AtbywRecipe PACKED_ICE_STAIRS = createRecipeFromConfig(NewAtbywID("packed_ice_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PACKED_ICE_STAIRS), newKeyPair("item", getBlockID(Blocks.PACKED_ICE)));
    public static AtbywRecipe BLUE_ICE_STAIRS = createRecipeFromConfig(NewAtbywID("blue_ice_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.BLUE_ICE_STAIRS), newKeyPair("item", getBlockID(Blocks.BLUE_ICE)));
    public static AtbywRecipe PACKED_ICE_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("packed_ice_bricks_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.PACKED_ICE_BRICKS)));
    public static AtbywRecipe BLUE_ICE_BRICKS_STAIRS = createRecipeFromConfig(NewAtbywID("blue_ice_bricks_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.BLUE_ICE_BRICKS)));

    public static AtbywRecipe COMPACTED_SNOW_BLOCK_SLAB = createRecipeFromConfig(NewAtbywID("compacted_snow_block_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static AtbywRecipe COMPACTED_SNOW_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("compacted_snow_bricks_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS)));
    public static AtbywRecipe PACKED_ICE_SLAB = createRecipeFromConfig(NewAtbywID("packed_ice_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PACKED_ICE_SLAB), newKeyPair("item", getBlockID(Blocks.PACKED_ICE)));
    public static AtbywRecipe BLUE_ICE_SLAB = createRecipeFromConfig(NewAtbywID("blue_ice_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.BLUE_ICE_SLAB), newKeyPair("item", getBlockID(Blocks.BLUE_ICE)));
    public static AtbywRecipe PACKED_ICE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("packed_ice_bricks_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.PACKED_ICE_BRICKS)));
    public static AtbywRecipe BLUE_ICE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("blue_ice_bricks_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.BLUE_ICE_BRICKS)));
    public static AtbywRecipe CHISELED_PACKED_ICE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("chiseled_packed_ice_bricks_slab"), "", 1, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.CHISELED_PACKED_ICE_BRICKS), newKeyPair("item", getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB)));
    public static AtbywRecipe CHISELED_BLUE_ICE_BRICKS_SLAB = createRecipeFromConfig(NewAtbywID("chiseled_blue_ice_bricks_slab"), "", 1, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.CHISELED_BLUE_ICE_BRICKS), newKeyPair("item", getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB)));

    public static AtbywRecipe IRON_SPIKE_TRAP = createRecipeFromConfig(NewAtbywID("iron_spike_trap"), "", AtbywRecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.IRON_SPIKE_TRAP), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));
    public static AtbywRecipe GOLD_SPIKE_TRAP = createRecipeFromConfig(NewAtbywID("gold_spike_trap"), "", AtbywRecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.GOLD_SPIKE_TRAP), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));
    public static AtbywRecipe DIAMOND_SPIKE_TRAP = createRecipeFromConfig(NewAtbywID("diamond_spike_trap"), "", AtbywRecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.DIAMOND_SPIKE_TRAP), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));
    public static AtbywRecipe NETHERITE_SPIKE_TRAP = createRecipeFromConfig(NewAtbywID("netherite_spike_trap"), "", AtbywRecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.NETHERITE_SPIKE_TRAP), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));

    public static AtbywRecipe SOUL_JACK_O_LANTERN = createRecipeFromConfig(NewAtbywID("soul_jack_o_lantern"), "", 1, AtbywRecipeConfigs.TORCH_2, getBlockID(AtbywBlocks.SOUL_JACK_O_LANTERN), newKeyPair("item", getBlockID(Blocks.JACK_O_LANTERN)), newKeyPair("item", getItemID(Items.SOUL_TORCH)));
    public static AtbywRecipe REDSTONE_JACK_O_LANTERN = createRecipeFromConfig(NewAtbywID("redstone_jack_o_lantern"), "", 1, AtbywRecipeConfigs.TORCH_2, getBlockID(AtbywBlocks.REDSTONE_JACK_O_LANTERN), newKeyPair("item", getBlockID(Blocks.JACK_O_LANTERN)), newKeyPair("item", getItemID(Items.REDSTONE_TORCH)));

    public static AtbywRecipe TIMER_REPEATER_MANUAL = createRecipeFromConfig(NewAtbywID("timer_repeater_manual"), "timer_repeater", 1, AtbywRecipeConfigs.DYING_DASHED_3, getBlockID(AtbywBlocks.TIMER_REPEATER), newKeyPair("item", getItemID(Items.REDSTONE)), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("item", getItemID(Items.REPEATER)));
    public static AtbywRecipe TIMER_REPEATER_CLOCK = createRecipeFromConfig(NewAtbywID("timer_repeater_clock"), "timer_repeater", AtbywRecipeConfigs.TIMER_REPEATER_3, getBlockID(AtbywBlocks.TIMER_REPEATER), newKeyPair("item", getItemID(Items.CLOCK)), newKeyPair("item", getItemID(Items.REDSTONE)), newKeyPair("item", getItemID(Items.REPEATER)));

    public static AtbywRecipe REDSTONE_CROSS_PATH = createRecipeFromConfig(NewAtbywID("redstone_cross_path"), "", AtbywRecipeConfigs.BED_2, getBlockID(AtbywBlocks.REDSTONE_CROSS_PATH), newKeyPair("item", getItemID(Items.STONE)), newKeyPair("item", getItemID(Items.REDSTONE)));

    public static AtbywRecipe LARGE_CHAIN_LINK = createRecipeFromConfig(NewAtbywID("large_chain_link"), "", AtbywRecipeConfigs.STAR_1, getItemID(AtbywItems.LARGE_CHAIN_LINK), newKeyPair("item", getItemID(Items.IRON_INGOT)));
    public static AtbywRecipe LARGE_CHAIN = createRecipeFromConfig(NewAtbywID("large_chain"), "", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.LARGE_CHAIN), newKeyPair("item", getItemID(AtbywItems.LARGE_CHAIN_LINK)));

    public static AtbywRecipe ROOTED_DIRT_STAIRS = createRecipeFromConfig(NewAtbywID("rooted_dirt_stairs"), "", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.ROOTED_DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.ROOTED_DIRT)));
    public static AtbywRecipe ROOTED_DIRT_SLAB = createRecipeFromConfig(NewAtbywID("rooted_dirt_slab"), "", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.ROOTED_DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.ROOTED_DIRT)));

    public static void init() {
        LOGGER.info("ATBYW Recipes Inintiliazed");
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        AtbywRecipesSmelting.inject(map);
        AtbywRecipesStonecutting.inject(map);

        // Multi recipes
        for (int i = 0; i < FLOWER_NAMES.length; i++) {
            putRecipe(FLOWER_SWITCHES[i], map);
        }

        for (int i = 0; i < WOOD_NAMES_NO_OAK.length; i++) {
            putRecipe(BOOKSHELF_VARIANTS[i], map);
            putRecipe(LADDERS_VARIANTS[i], map);
        }

        for (int i = 0; i < WOOD_NAMES.length; i++) {
            putRecipe(FENCE_DOOR_VARIANTS[i], map);
            putRecipe(BOOKSHELF_TOGGLES_VARIANTS[i], map);
        }

        for (int i = 0; i < COLOR_NAMES.length; i++) {
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
    }
}