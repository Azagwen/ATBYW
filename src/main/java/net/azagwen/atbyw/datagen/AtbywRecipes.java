package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.items.AtbywItems;
import net.azagwen.atbyw.util.Quadruplet;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.Map;

import static net.azagwen.atbyw.datagen.AtbywRecipeUtils.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywRecipes {

    public static JsonObject createShapedRecipeJson(String group, ArrayList<String> pattern, ArrayList<Key> keys, Identifier output, int count) {
        JsonObject json = new JsonObject();

        json.addProperty("type", "minecraft:crafting_shaped");

        if (!group.equals(""))
            json.addProperty("group", group);

        //Pattern
        JsonArray patternArray = new JsonArray();
        for (String s : pattern) {
            patternArray.add(s);
        }
        json.add("pattern", patternArray);

        //Keys
        JsonObject individualKey;
        JsonObject keyList = new JsonObject();
        for (Key key : keys) {
            //Key type + ingredient
            individualKey = new JsonObject();
            individualKey.addProperty(key.getType(), key.getIngredient().toString());
            //Key character
            keyList.add(key.getChar() + "", individualKey);
        }
        json.add("key", keyList);

        //Result
        JsonObject result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);
        json.add("result", result);

        return json;
    }

    //Config recipes methods
    @SafeVarargs
    private static JsonObject createRecipeFromConfig(String group, int count, AtbywRecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        Key[] keys = new Key[ingredient.length];

        for (int i = 0; i < ingredient.length; i++) {
            keys[i] = new Key(config.getKeyChars().get(i), ingredient[i].getLeft(), ingredient[i].getRight());
        }
//        LOGGER.info(Arrays.toString(keys));

        return createShapedRecipeJson(group, config.getPattern(), Lists.newArrayList(keys), result, count);
    }

    @SafeVarargs
    private static JsonObject createRecipeFromConfig(String group, AtbywRecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        return createRecipeFromConfig(group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    private static JsonObject[] createMultiRecipesFromConfig(String[] nameArray, String group, int count, AtbywRecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        JsonObject[] objects = new JsonObject[nameArray.length];
        Pair<String, Identifier>[] ingredients = new Pair[ingredient.length];

        for (int i = 0; i < nameArray.length; i++) {
            String namePrefix = nameArray[i];
            String fullName;

            for (int j = 0; j < ingredient.length; j++) {
                boolean colorizeIngredient = ingredient[j].getFourth();
                String pathName = ingredient[j].getThird();

                if (colorizeIngredient)
                    if (!(ingredient[j].getThird().isEmpty()))
                        fullName = namePrefix + "_" + pathName;
                    else
                        fullName = namePrefix;
                else
                    fullName = pathName;

                ingredients[j] = newKeyPair(ingredient[j].getFirst(), new Identifier(ingredient[j].getSecond(), fullName));
            }
            objects[i] = createRecipeFromConfig(group, count, config, new Identifier(result.getLeft(), namePrefix + "_" + result.getRight()), ingredients);
        }
        return objects;
    }

    @SafeVarargs
    private static JsonObject[] createMultiRecipesFromConfig(String[] nameArray, String group, AtbywRecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray, group, config.getCount(), config, result, ingredient);
    }
    public static JsonObject DIRT_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.DIRT)));
    public static JsonObject GRASS_BLOCK_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.GRASS_BLOCK_STAIRS), newKeyPair("item", getBlockID(Blocks.GRASS_BLOCK)));
    public static JsonObject MYCELIUM_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.MYCELIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.MYCELIUM)));
    public static JsonObject COARSE_DIRT_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COARSE_DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.COARSE_DIRT)));
    public static JsonObject PODZOL_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PODZOL_STAIRS), newKeyPair("item", getBlockID(Blocks.PODZOL)));
    public static JsonObject NETHERRACK_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.NETHERRACK_STAIRS), newKeyPair("item", getBlockID(Blocks.NETHERRACK)));
    public static JsonObject CRIMSON_NYLIUM_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.CRIMSON_NYLIUM)));
    public static JsonObject WARPED_NYLIUM_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.WARPED_NYLIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.WARPED_NYLIUM)));

    public static JsonObject DIRT_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.DIRT)));
    public static JsonObject GRASS_BLOCK_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.GRASS_BLOCK_SLAB), newKeyPair("item", getBlockID(Blocks.GRASS_BLOCK)));
    public static JsonObject MYCELIUM_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.MYCELIUM_SLAB), newKeyPair("item", getBlockID(Blocks.MYCELIUM)));
    public static JsonObject COARSE_DIRT_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COARSE_DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.COARSE_DIRT)));
    public static JsonObject PODZOL_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PODZOL_SLAB), newKeyPair("item", getBlockID(Blocks.PODZOL)));
    public static JsonObject NETHERRACK_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.NETHERRACK_SLAB), newKeyPair("item", getBlockID(Blocks.NETHERRACK)));
    public static JsonObject CRIMSON_NYLIUM_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_SLAB), newKeyPair("item", getBlockID(Blocks.CRIMSON_NYLIUM)));
    public static JsonObject WARPED_NYLIUM_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.WARPED_NYLIUM_SLAB), newKeyPair("item", getBlockID(Blocks.WARPED_NYLIUM)));

    public static JsonObject BASALT_BRICKS_FROM_BASALT = createRecipeFromConfig("basalt_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BASALT_BRICKS), newKeyPair("item", getBlockID(Blocks.BASALT)));
    public static JsonObject BASALT_PILLAR_FROM_BASALT = createRecipeFromConfig("basalt_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BASALT_BRICKS), newKeyPair("item", getBlockID(Blocks.BASALT)));
    public static JsonObject BASALT_BRICKS_FROM_POLISHED_BASALT = createRecipeFromConfig("basalt_pillar", 2, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.BASALT_PILLAR), newKeyPair("item", getBlockID(Blocks.POLISHED_BASALT)));
    public static JsonObject BASALT_PILLAR_FROM_POLISHED_BASALT = createRecipeFromConfig("basalt_pillar", 2, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.BASALT_PILLAR), newKeyPair("item", getBlockID(Blocks.POLISHED_BASALT)));

    public static JsonObject IRON_FENCE_DOOR = createRecipeFromConfig("fence_door", AtbywRecipeConfigs.FENCE_DOOR_1, getBlockID(AtbywBlocks.IRON_FENCE_DOOR), newKeyPair("item", getItemID(Items.IRON_INGOT)));
    public static JsonObject BAMBOO_LADDER = createRecipeFromConfig("ladders", 3, AtbywRecipeConfigs.RAIL_2, getBlockID(AtbywBlocks.BAMBOO_LADDER), newKeyPair("item", getItemID(AtbywItems.BAMBOO_STICK)), newKeyPair("item", getItemID(Items.BAMBOO)));
    public static JsonObject BAMBOO_STICK = createRecipeFromConfig("stick", AtbywRecipeConfigs.STICK_1, getItemID(AtbywItems.BAMBOO_STICK), newKeyPair("item", getItemID(Items.BAMBOO)));
    public static JsonObject REDSTONE_LANTERN = createRecipeFromConfig("", AtbywRecipeConfigs.DYING_2, getBlockID(AtbywBlocks.REDSTONE_LANTERN), newKeyPair("item", getItemID(Items.IRON_NUGGET)), newKeyPair("item", getItemID(Items.REDSTONE_TORCH)));
    public static JsonObject SHROOM_STICK = createRecipeFromConfig("", AtbywRecipeConfigs.STICK_1, getItemID(AtbywItems.SHROOMSTICK), newKeyPair("item", getBlockID(Blocks.SHROOMLIGHT)));

    public static JsonObject GRANITE_TILES = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.GRANITE_TILES), newKeyPair("item", getBlockID(Blocks.GRANITE)));
    public static JsonObject DIORITE_BRICKS = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.DIORITE_BRICKS), newKeyPair("item", getBlockID(Blocks.DIORITE)));
    public static JsonObject ANDESITE_BRICKS = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS), newKeyPair("item", getBlockID(Blocks.ANDESITE)));

    public static JsonObject GRANITE_TILES_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.GRANITE_TILES_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.GRANITE_TILES)));
    public static JsonObject DIORITE_BRICKS_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.DIORITE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.DIORITE_BRICKS)));
    public static JsonObject ANDESITE_BRICKS_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.ANDESITE_BRICKS)));

    public static JsonObject GRANITE_TILES_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.GRANITE_TILES_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.GRANITE_TILES)));
    public static JsonObject DIORITE_BRICKS_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.DIORITE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.DIORITE_BRICKS)));
    public static JsonObject ANDESITE_BRICKS_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.ANDESITE_BRICKS)));

    public static JsonObject[] BOOKSHELF_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES_NO_OAK, "bookshelf", AtbywRecipeConfigs.BOOKSHELF_2, new Pair<>(AtbywNamespace, "bookshelf"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false));
    public static JsonObject[] BOOKSHELF_TOGGLES_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "bookshelf_toggle", AtbywRecipeConfigs.BOOKSHELF_TOGGLE_4, new Pair<>(AtbywNamespace, "bookshelf_toggle"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false), newKeyQuadruplet("tag", mcNameSpace, "stone_tool_materials", false));
    public static JsonObject[] LADDERS_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES_NO_OAK, "ladders", (AtbywRecipeConfigs.LADDER_1.getCount() * 2), AtbywRecipeConfigs.LADDER_1, new Pair<>(AtbywNamespace, "ladder"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
    public static JsonObject[] FENCE_DOOR_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "fence_door", AtbywRecipeConfigs.FENCE_DOOR_1, new Pair<>(AtbywNamespace, "fence_door"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
    public static JsonObject[] FLOWER_SWITCHES = createMultiRecipesFromConfig(FLOWER_NAMES, "flower_switches", AtbywRecipeConfigs.FLOWER_SWITCH_3, new Pair<>(AtbywNamespace, "pull_switch"), newKeyQuadruplet("item", mcNameSpace, "", true), newKeyQuadruplet("item", mcNameSpace, "stick", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false));

    public static JsonObject TERRACOTTA_STAIRS = createRecipeFromConfig("terracotta_stairs", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_STAIRS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
    public static JsonObject TERRACOTTA_SLAB = createRecipeFromConfig("terracotta_slab", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_SLAB), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
    public static JsonObject TERRACOTTA_BRICKS = createRecipeFromConfig("terracotta_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
    public static JsonObject TERRACOTTA_BRICKS_STAIRS = createRecipeFromConfig("terracotta_bricks_stairs", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
    public static JsonObject TERRACOTTA_BRICKS_SLAB = createRecipeFromConfig("terracotta_bricks_slab", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
    public static JsonObject TERRACOTTA_BRICKS_WALL = createRecipeFromConfig("terracotta_bricks_wall", AtbywRecipeConfigs.WALL_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_WALL), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));

    public static JsonObject[] TERRACOTTA_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "terracotta_stairs"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static JsonObject[] TERRACOTTA_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "terracotta_slab"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static JsonObject[] TERRACOTTA_BRICKS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks", AtbywRecipeConfigs.BRICKS_1, new Pair<>(AtbywNamespace, "terracotta_bricks"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
    public static JsonObject[] TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", true));
    public static JsonObject[] TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "terracotta_bricks_slab"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", true));
    public static JsonObject[] TERRACOTTA_BRICKS_WALL_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_wall", AtbywRecipeConfigs.WALL_1, new Pair<>(AtbywNamespace, "terracotta_bricks_wall"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", true));

    public static JsonObject[] TERRACOTTA_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_stairs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_stairs"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static JsonObject[] TERRACOTTA_SLAB_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_slabs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_slab"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static JsonObject[] TERRACOTTA_BRICKS_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static JsonObject[] TERRACOTTA_BRICKS_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_stairs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static JsonObject[] TERRACOTTA_BRICKS_SLAB_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_slabs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks_slab"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
    public static JsonObject[] TERRACOTTA_BRICKS_WALL_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_walls_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(AtbywNamespace, "terracotta_bricks_wall"), newKeyQuadruplet("item", AtbywNamespace, "terracotta_bricks_wall", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));

    public static JsonObject[] CONCRETE_STAIRS_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, "concrete_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(AtbywNamespace, "concrete_stairs"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static JsonObject[] CONCRETE_SLAB_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, "concrete_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(AtbywNamespace, "concrete_slab"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static JsonObject[] CINDER_BLOCKS_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, "cinder_blocks", AtbywRecipeConfigs.BRICKS_1, new Pair<>(AtbywNamespace, "cinder_bricks"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
    public static JsonObject[] CINDER_BLOCKS_WALL_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, "cinder_blocks_wall", AtbywRecipeConfigs.WALL_1, new Pair<>(AtbywNamespace, "cinder_blocks_wall"), newKeyQuadruplet("item", AtbywNamespace, "cinder_bricks", true));

    public static JsonObject BEE_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.BEE_ESSENCE), newKeyPair("item", getItemID(Items.HONEYCOMB)), newKeyPair("item", getItemID(Items.HONEY_BOTTLE)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject SHULKER_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.SHULKER_ESSENCE), newKeyPair("item", getItemID(Items.SHULKER_SHELL)), newKeyPair("item", getItemID(Items.SHULKER_SHELL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject CAT_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.CAT_ESSENCE), newKeyPair("item", getItemID(Items.STRING)), newKeyPair("item", getItemID(Items.STRING)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject CHICKEN_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.CHICKEN_ESSENCE), newKeyPair("item", getItemID(Items.CHICKEN)), newKeyPair("item", getItemID(Items.FEATHER)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject RABBIT_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.RABBIT_ESSENCE), newKeyPair("item", getItemID(Items.RABBIT)), newKeyPair("item", getItemID(Items.RABBIT_HIDE)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject FOX_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.FOX_ESSENCE), newKeyPair("item", getItemID(Items.SWEET_BERRIES)), newKeyPair("item", getItemID(Items.SWEET_BERRIES)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject COD_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.COD_ESSENCE), newKeyPair("item", getItemID(Items.COD)), newKeyPair("item", getItemID(Items.BONE_MEAL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject SALMON_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.SALMON_ESSENCE), newKeyPair("item", getItemID(Items.SALMON)), newKeyPair("item", getItemID(Items.BONE_MEAL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject PUFFER_FISH_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.PUFFER_FISH_ESSENCE), newKeyPair("item", getItemID(Items.PUFFERFISH)), newKeyPair("item", getItemID(Items.BONE_MEAL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject SLIME_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.SLIME_ESSENCE), newKeyPair("item", getItemID(Items.SLIME_BALL)), newKeyPair("item", getItemID(Items.SLIME_BALL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
    public static JsonObject MAGMA_CUBE_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.MAGMA_CUBE_ESSENCE), newKeyPair("item", getItemID(Items.MAGMA_CREAM)), newKeyPair("item", getItemID(Items.MAGMA_CREAM)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));

    public static JsonObject BEE_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.BEE_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.BEE_ESSENCE)));
    public static JsonObject SILVERFISH_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.SILVERFISH_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.SILVERFISH_ESSENCE)));
    public static JsonObject ENDERMITE_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.ENDERMITE_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.ENDERMITE_ESSENCE)));
    public static JsonObject SHULKER_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.SHULKER_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.SHULKER_ESSENCE)));
    public static JsonObject CAT_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.CAT_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.CAT_ESSENCE)));
    public static JsonObject WOLF_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.WOLF_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.WOLF_ESSENCE)));
    public static JsonObject CHICKEN_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.CHICKEN_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.CHICKEN_ESSENCE)));
    public static JsonObject RABBIT_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.RABBIT_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.RABBIT_ESSENCE)));
    public static JsonObject FOX_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.FOX_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.FOX_ESSENCE)));
    public static JsonObject COD_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.COD_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.COD_ESSENCE)));
    public static JsonObject SALMON_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.SALMON_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.SALMON_ESSENCE)));
    public static JsonObject PUFFER_FISH_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.PUFFER_FISH_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.PUFFER_FISH_ESSENCE)));
    public static JsonObject SLIME_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.SLIME_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.SLIME_ESSENCE)));
    public static JsonObject MAGMA_CUBE_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.MAGMA_CUBE_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.MAGMA_CUBE_ESSENCE)));

    public static JsonObject GRANITE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.GRANITE_COLUMN), newKeyPair("item", getBlockID(Blocks.GRANITE)));
    public static JsonObject DIORITE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.DIORITE_COLUMN), newKeyPair("item", getBlockID(Blocks.DIORITE)));
    public static JsonObject ANDESITE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.ANDESITE_COLUMN), newKeyPair("item", getBlockID(Blocks.ANDESITE)));
    public static JsonObject GRANITE_COLUMN_FROM_POLISHED = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.GRANITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_GRANITE)));
    public static JsonObject DIORITE_COLUMN_FROM_POLISHED = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.DIORITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_DIORITE)));
    public static JsonObject ANDESITE_COLUMN_FROM_POLISHED = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.ANDESITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_ANDESITE)));
    public static JsonObject SANDSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SANDSTONE)));
    public static JsonObject SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SMOOTH_SANDSTONE)));
    public static JsonObject CHISELED_SANDSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CHISELED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.CHISELED_SANDSTONE)));
    public static JsonObject RED_SANDSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.RED_SANDSTONE)));
    public static JsonObject RED_SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SMOOTH_RED_SANDSTONE)));
    public static JsonObject CHISELED_RED_SANDSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.CHISELED_RED_SANDSTONE)));
    public static JsonObject PURPUR_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.PURPUR_COLUMN), newKeyPair("item", getBlockID(Blocks.PURPUR_BLOCK)));
    public static JsonObject STONE_BRICKS_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.STONE_BRICKS)));
    public static JsonObject MOSSY_STONE_BRICKS_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.MOSSY_STONE_BRICKS)));
    public static JsonObject CRACKED_STONE_BRICKS_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.CRACKED_STONE_BRICKS)));
    public static JsonObject NETHER_BRICKS_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.NETHER_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.NETHER_BRICKS)));
    public static JsonObject QUARTZ_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.QUARTZ_COLUMN), newKeyPair("item", getBlockID(Blocks.QUARTZ_BLOCK)));
    public static JsonObject PRISMARINE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.PRISMARINE_COLUMN), newKeyPair("item", getBlockID(Blocks.PRISMARINE_BRICKS)));
    public static JsonObject BLACKSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.BLACKSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.BLACKSTONE)));

    public static JsonObject PURPUR_TILES = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.PURPUR_TILES), newKeyPair("item", getBlockID(Blocks.PURPUR_PILLAR)));
    public static JsonObject CUT_PURPUR_BLOCK = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK), newKeyPair("item", getBlockID(Blocks.PURPUR_BLOCK)));
    public static JsonObject CHISELED_PURPUR_BLOCK = createRecipeFromConfig("", 1, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.CHISELED_PURPUR_BLOCK), newKeyPair("item", getBlockID(Blocks.PURPUR_SLAB)));
    public static JsonObject PURPUR_TILES_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PURPUR_TILES_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.PURPUR_TILES)));
    public static JsonObject CUT_PURPUR_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.CUT_PURPUR_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK)));
    public static JsonObject SMOOTH_PURPUR_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.SMOOTH_PURPUR_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK)));
    public static JsonObject PURPUR_TILES_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PURPUR_TILES_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.PURPUR_TILES)));
    public static JsonObject CUT_PURPUR_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.CUT_PURPUR_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.CUT_PURPUR_BLOCK)));
    public static JsonObject SMOOTH_PURPUR_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.SMOOTH_PURPUR_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK)));

    public static JsonObject COMPACTED_SNOW = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COMPACTED_SNOW), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static JsonObject COMPACTED_SNOW_BLOCK = createRecipeFromConfig("", 1, AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK), newKeyPair("item", getBlockID(Blocks.SNOW_BLOCK)));
    public static JsonObject COMPACTED_SNOW_BRICKS = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static JsonObject PACKED_ICE_BRICKS = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS), newKeyPair("item", getBlockID(Blocks.PACKED_ICE)));
    public static JsonObject BLUE_ICE_BRICKS = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS), newKeyPair("item", getBlockID(Blocks.BLUE_ICE)));

    public static JsonObject COMPACTED_SNOW_BLOCK_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static JsonObject COMPACTED_SNOW_BRICKS_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS)));
    public static JsonObject PACKED_ICE_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PACKED_ICE_STAIRS), newKeyPair("item", getBlockID(Blocks.PACKED_ICE)));
    public static JsonObject BLUE_ICE_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.BLUE_ICE_STAIRS), newKeyPair("item", getBlockID(Blocks.BLUE_ICE)));
    public static JsonObject PACKED_ICE_BRICKS_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.PACKED_ICE_BRICKS)));
    public static JsonObject BLUE_ICE_BRICKS_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.BLUE_ICE_BRICKS)));

    public static JsonObject COMPACTED_SNOW_BLOCK_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BLOCK)));
    public static JsonObject COMPACTED_SNOW_BRICKS_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.COMPACTED_SNOW_BRICKS)));
    public static JsonObject PACKED_ICE_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PACKED_ICE_SLAB), newKeyPair("item", getBlockID(Blocks.PACKED_ICE)));
    public static JsonObject BLUE_ICE_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.BLUE_ICE_SLAB), newKeyPair("item", getBlockID(Blocks.BLUE_ICE)));
    public static JsonObject PACKED_ICE_BRICKS_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.PACKED_ICE_BRICKS)));
    public static JsonObject BLUE_ICE_BRICKS_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.BLUE_ICE_BRICKS)));
    public static JsonObject CHISELED_PACKED_ICE_BRICKS_SLAB = createRecipeFromConfig("", 1, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.CHISELED_PACKED_ICE_BRICKS), newKeyPair("item", getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB)));
    public static JsonObject CHISELED_BLUE_ICE_BRICKS_SLAB = createRecipeFromConfig("", 1, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.CHISELED_BLUE_ICE_BRICKS), newKeyPair("item", getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB)));

    public static JsonObject IRON_SPIKE_TRAP = createRecipeFromConfig("", AtbywRecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.IRON_SPIKE_TRAP), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));
    public static JsonObject GOLD_SPIKE_TRAP = createRecipeFromConfig("", AtbywRecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.GOLD_SPIKE_TRAP), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));
    public static JsonObject DIAMOND_SPIKE_TRAP = createRecipeFromConfig("", AtbywRecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.DIAMOND_SPIKE_TRAP), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));
    public static JsonObject NETHERITE_SPIKE_TRAP = createRecipeFromConfig("", AtbywRecipeConfigs.SPIKE_TRAP_4, getBlockID(AtbywBlocks.NETHERITE_SPIKE_TRAP), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("item", getItemID(Items.REDSTONE)));

    public static JsonObject SOUL_JACK_O_LANTERN = createRecipeFromConfig("", 1, AtbywRecipeConfigs.TORCH_2, getBlockID(AtbywBlocks.SOUL_JACK_O_LANTERN), newKeyPair("item", getBlockID(Blocks.JACK_O_LANTERN)), newKeyPair("item", getItemID(Items.SOUL_TORCH)));
    public static JsonObject REDSTONE_JACK_O_LANTERN = createRecipeFromConfig("", 1, AtbywRecipeConfigs.TORCH_2, getBlockID(AtbywBlocks.REDSTONE_JACK_O_LANTERN), newKeyPair("item", getBlockID(Blocks.JACK_O_LANTERN)), newKeyPair("item", getItemID(Items.REDSTONE_TORCH)));

    public static JsonObject TIMER_REPEATER_MANUAL = createRecipeFromConfig("timer_repeater", 1, AtbywRecipeConfigs.DYING_DASHED_3, getBlockID(AtbywBlocks.TIMER_REPEATER), newKeyPair("item", getItemID(Items.REDSTONE)), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("item", getItemID(Items.REPEATER)));
    public static JsonObject TIMER_REPEATER_CLOCK = createRecipeFromConfig("timer_repeater", AtbywRecipeConfigs.TIMER_REPEATER_3, getBlockID(AtbywBlocks.TIMER_REPEATER), newKeyPair("item", getItemID(Items.CLOCK)), newKeyPair("item", getItemID(Items.REDSTONE)), newKeyPair("item", getItemID(Items.REPEATER)));

    public static JsonObject REDSTONE_CROSS_PATH = createRecipeFromConfig("", AtbywRecipeConfigs.BED_2, getBlockID(AtbywBlocks.REDSTONE_CROSS_PATH), newKeyPair("item", getItemID(Items.STONE)), newKeyPair("item", getItemID(Items.REDSTONE)));

    public static JsonObject LARGE_CHAIN_LINK = createRecipeFromConfig("", AtbywRecipeConfigs.STAR_1, getItemID(AtbywItems.LARGE_CHAIN_LINK), newKeyPair("item", getItemID(Items.IRON_INGOT)));
    public static JsonObject LARGE_CHAIN = createRecipeFromConfig("", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.LARGE_CHAIN), newKeyPair("item", getItemID(AtbywItems.LARGE_CHAIN_LINK)));

    public static JsonObject ROOTED_DIRT_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.ROOTED_DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.ROOTED_DIRT)));
    public static JsonObject ROOTED_DIRT_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.ROOTED_DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.ROOTED_DIRT)));

    public static void init() {
        LOGGER.info("ATBYW Recipes Inintiliazed");
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void injectRecipes(Map<Identifier, JsonElement> map) {
        AtbywRecipesSmelting.injectRecipes(map);
        AtbywRecipesStonecutting.injectRecipes(map);

        // Multi recipes
        for (int i = 0; i < FLOWER_NAMES.length; i++) {
            putRecipe(NewAtbywID(FLOWER_NAMES[i] + "_flower_pull_switch"), FLOWER_SWITCHES[i], map);
        }

        for (int i = 0; i < WOOD_NAMES_NO_OAK.length; i++) {
            putRecipe(NewAtbywID(WOOD_NAMES_NO_OAK[i] + "_bookshelf"), BOOKSHELF_VARIANTS[i], map);
            putRecipe(NewAtbywID(WOOD_NAMES_NO_OAK[i] + "_ladder"), LADDERS_VARIANTS[i], map);
        }

        for (int i = 0; i < WOOD_NAMES.length; i++) {
            putRecipe(NewAtbywID(WOOD_NAMES[i] + "_fence_door"), FENCE_DOOR_VARIANTS[i], map);
            putRecipe(NewAtbywID(WOOD_NAMES[i] + "_bookshelf_toggle"), BOOKSHELF_TOGGLES_VARIANTS[i], map);
        }

        for (int i = 0; i < COLOR_NAMES.length; i++) {
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_stairs_from_dye"), TERRACOTTA_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_slab_from_dye"), TERRACOTTA_SLAB_COLORS_SHAPED[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_bricks_from_dye"), TERRACOTTA_BRICKS_COLORS_SHAPED[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_bricks_stairs_from_dye"), TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_bricks_slab_from_dye"), TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_bricks_wall_from_dye"), TERRACOTTA_BRICKS_WALL_COLORS_SHAPED[i], map);

            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_stairs"), TERRACOTTA_STAIRS_COLORS_DYING[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_slab"), TERRACOTTA_SLAB_COLORS_DYING[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_bricks"), TERRACOTTA_BRICKS_COLORS_DYING[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_bricks_stairs"), TERRACOTTA_BRICKS_STAIRS_COLORS_DYING[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_bricks_slab"), TERRACOTTA_BRICKS_SLAB_COLORS_DYING[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_terracotta_bricks_wall"), TERRACOTTA_BRICKS_WALL_COLORS_DYING[i], map);

            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_concrete_stairs"), CONCRETE_STAIRS_COLORS[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_concrete_slab"), CONCRETE_SLAB_COLORS[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_cinder_blocks"), CINDER_BLOCKS_COLORS[i], map);
            putRecipe(NewAtbywID(COLOR_NAMES[i] + "_cinder_blocks_wall"), CINDER_BLOCKS_WALL_COLORS[i], map);
        }

        // Single recipes
        putRecipe(NewAtbywID("terracotta_stairs_from_stick_tag"), TERRACOTTA_STAIRS, map);
        putRecipe(NewAtbywID("terracotta_slab_from_stick_tag"), TERRACOTTA_SLAB, map);
        putRecipe(NewAtbywID("terracotta_bricks_from_stick_tag"), TERRACOTTA_BRICKS, map);
        putRecipe(NewAtbywID("terracotta_bricks_stairs_from_stick_tag"), TERRACOTTA_BRICKS_STAIRS, map);
        putRecipe(NewAtbywID("terracotta_bricks_slab_from_stick_tag"), TERRACOTTA_BRICKS_SLAB, map);
        putRecipe(NewAtbywID("terracotta_bricks_wall_from_stick_tag"), TERRACOTTA_BRICKS_WALL, map);

        putRecipe(NewAtbywID("dirt_stairs"), DIRT_STAIRS, map);
        putRecipe(NewAtbywID("grass_block_stairs"), GRASS_BLOCK_STAIRS, map);
        putRecipe(NewAtbywID("mycelium_stairs"), MYCELIUM_STAIRS, map);
        putRecipe(NewAtbywID("coarse_dirt_stairs"), COARSE_DIRT_STAIRS, map);
        putRecipe(NewAtbywID("podzol_stairs"), PODZOL_STAIRS, map);
        putRecipe(NewAtbywID("netherrack_stairs"), NETHERRACK_STAIRS, map);
        putRecipe(NewAtbywID("crimson_nylium_stairs"), CRIMSON_NYLIUM_STAIRS, map);
        putRecipe(NewAtbywID("warped_nylium_stairs"), WARPED_NYLIUM_STAIRS, map);

        putRecipe(NewAtbywID("dirt_slab"), DIRT_SLAB, map);
        putRecipe(NewAtbywID("grass_block_slab"), GRASS_BLOCK_SLAB, map);
        putRecipe(NewAtbywID("mycelium_slab"), MYCELIUM_SLAB, map);
        putRecipe(NewAtbywID("coarse_dirt_slab"), COARSE_DIRT_SLAB, map);
        putRecipe(NewAtbywID("podzol_slab"), PODZOL_SLAB, map);
        putRecipe(NewAtbywID("netherrack_slab"), NETHERRACK_SLAB, map);
        putRecipe(NewAtbywID("crimson_nylium_slab"), CRIMSON_NYLIUM_SLAB, map);
        putRecipe(NewAtbywID("warped_nylium_slab"), WARPED_NYLIUM_SLAB, map);

        putRecipe(NewAtbywID("basalt_bricks_from_basalt"), BASALT_BRICKS_FROM_BASALT, map);
        putRecipe(NewAtbywID("basalt_pillar_from_basalt"), BASALT_PILLAR_FROM_BASALT, map);
        putRecipe(NewAtbywID("basalt_bricks_from_polished_basalt"), BASALT_BRICKS_FROM_POLISHED_BASALT, map);
        putRecipe(NewAtbywID("basalt_pillar_from_polished_basalt"), BASALT_PILLAR_FROM_POLISHED_BASALT, map);

        putRecipe(NewAtbywID("iron_fence_door"), IRON_FENCE_DOOR, map);
        putRecipe(NewAtbywID("bamboo_ladder"), BAMBOO_LADDER, map);
        putRecipe(NewAtbywID("bamboo_stick"), BAMBOO_STICK, map);
        putRecipe(NewAtbywID("redstone_lantern"), REDSTONE_LANTERN, map);
        putRecipe(NewAtbywID("shroomstick"), SHROOM_STICK, map);

        putRecipe(NewAtbywID("granite_tiles"), GRANITE_TILES, map);
        putRecipe(NewAtbywID("diorite_bricks"), DIORITE_BRICKS, map);
        putRecipe(NewAtbywID("andesite_bricks"), ANDESITE_BRICKS, map);

        putRecipe(NewAtbywID("granite_tiles_stairs"), GRANITE_TILES_STAIRS, map);
        putRecipe(NewAtbywID("diorite_bricks_stairs"), DIORITE_BRICKS_STAIRS, map);
        putRecipe(NewAtbywID("andesite_bricks_stairs"), ANDESITE_BRICKS_STAIRS, map);

        putRecipe(NewAtbywID("granite_tiles_slab"), GRANITE_TILES_SLAB, map);
        putRecipe(NewAtbywID("diorite_bricks_slab"), DIORITE_BRICKS_SLAB, map);
        putRecipe(NewAtbywID("andesite_bricks_slab"), ANDESITE_BRICKS_SLAB, map);

        putRecipe(NewAtbywID("bee_essence"), BEE_ESSENCE, map);
        putRecipe(NewAtbywID("shulker_essence"), SHULKER_ESSENCE, map);
        putRecipe(NewAtbywID("cat_essence"), CAT_ESSENCE, map);
        putRecipe(NewAtbywID("chicken_essence"), CHICKEN_ESSENCE, map);
        putRecipe(NewAtbywID("rabbit_essence"), RABBIT_ESSENCE, map);
        putRecipe(NewAtbywID("fox_essence"), FOX_ESSENCE, map);
        putRecipe(NewAtbywID("cod_essence"), COD_ESSENCE, map);
        putRecipe(NewAtbywID("salmon_essence"), SALMON_ESSENCE, map);
        putRecipe(NewAtbywID("puffer_fish_essence"), PUFFER_FISH_ESSENCE, map);
        putRecipe(NewAtbywID("magma_cube_essence"), MAGMA_CUBE_ESSENCE, map);
        putRecipe(NewAtbywID("slime_essence"), SLIME_ESSENCE, map);

        putRecipe(NewAtbywID("bee_statue"), BEE_STATUE, map);
        putRecipe(NewAtbywID("silverfish_statue"), SILVERFISH_STATUE, map);
        putRecipe(NewAtbywID("endermite_statue"), ENDERMITE_STATUE, map);
        putRecipe(NewAtbywID("shulker_statue"), SHULKER_STATUE, map);
        putRecipe(NewAtbywID("cat_statue"), CAT_STATUE, map);
        putRecipe(NewAtbywID("wolf_statue"), WOLF_STATUE, map);
        putRecipe(NewAtbywID("chicken_statue"), CHICKEN_STATUE, map);
        putRecipe(NewAtbywID("rabbit_statue"), RABBIT_STATUE, map);
        putRecipe(NewAtbywID("fox_statue"), FOX_STATUE, map);
        putRecipe(NewAtbywID("cod_statue"), COD_STATUE, map);
        putRecipe(NewAtbywID("salmon_statue"), SALMON_STATUE, map);
        putRecipe(NewAtbywID("puffer_fish_statue"), PUFFER_FISH_STATUE, map);
        putRecipe(NewAtbywID("magma_cube_statue"), MAGMA_CUBE_STATUE, map);
        putRecipe(NewAtbywID("slime_statue"), SLIME_STATUE, map);

        putRecipe(NewAtbywID("granite_column"), GRANITE_COLUMN, map);
        putRecipe(NewAtbywID("diorite_column"), DIORITE_COLUMN, map);
        putRecipe(NewAtbywID("andesite_column"), ANDESITE_COLUMN, map);
        putRecipe(NewAtbywID("granite_column_from_polished"), GRANITE_COLUMN_FROM_POLISHED, map);
        putRecipe(NewAtbywID("diorite_column_from_polished"), DIORITE_COLUMN_FROM_POLISHED, map);
        putRecipe(NewAtbywID("andesite_column_from_polished"), ANDESITE_COLUMN_FROM_POLISHED, map);
        putRecipe(NewAtbywID("sandstone_column"), SANDSTONE_COLUMN, map);
        putRecipe(NewAtbywID("sandstone_column_from_cut"), SANDSTONE_COLUMN_FROM_CUT, map);
        putRecipe(NewAtbywID("chiseled_sandstone_column"), CHISELED_SANDSTONE_COLUMN, map);
        putRecipe(NewAtbywID("red_sandstone_column"), RED_SANDSTONE_COLUMN, map);
        putRecipe(NewAtbywID("red_sandstone_column_from_cut"), RED_SANDSTONE_COLUMN_FROM_CUT, map);
        putRecipe(NewAtbywID("chiseled_red_sandstone_column"), CHISELED_RED_SANDSTONE_COLUMN, map);
        putRecipe(NewAtbywID("purpur_column"), PURPUR_COLUMN, map);
        putRecipe(NewAtbywID("stone_bricks_column"), STONE_BRICKS_COLUMN, map);
        putRecipe(NewAtbywID("mossy_stone_bricks_column"), MOSSY_STONE_BRICKS_COLUMN, map);
        putRecipe(NewAtbywID("cracked_stone_bricks_column"), CRACKED_STONE_BRICKS_COLUMN, map);
        putRecipe(NewAtbywID("nether_bricks_column"), NETHER_BRICKS_COLUMN, map);
        putRecipe(NewAtbywID("quartz_column"), QUARTZ_COLUMN, map);
        putRecipe(NewAtbywID("prismarine_column"), PRISMARINE_COLUMN, map);
        putRecipe(NewAtbywID("blackstone_column"), BLACKSTONE_COLUMN, map);

        putRecipe(NewAtbywID("purpur_tiles"), PURPUR_TILES, map);
        putRecipe(NewAtbywID("cut_purpur_block"), CUT_PURPUR_BLOCK, map);
        putRecipe(NewAtbywID("chiseled_purpur_block"), CHISELED_PURPUR_BLOCK, map);
        putRecipe(NewAtbywID("purpur_tiles_stairs"), PURPUR_TILES_STAIRS, map);
        putRecipe(NewAtbywID("cut_purpur_stairs"), CUT_PURPUR_STAIRS, map);
        putRecipe(NewAtbywID("smooth_purpur_stairs"), SMOOTH_PURPUR_STAIRS, map);
        putRecipe(NewAtbywID("purpur_tiles_slab"), PURPUR_TILES_SLAB, map);
        putRecipe(NewAtbywID("cut_purpur_slab"), CUT_PURPUR_SLAB, map);
        putRecipe(NewAtbywID("smooth_purpur_slab"), SMOOTH_PURPUR_SLAB, map);

        putRecipe(NewAtbywID("compacted_snow"), COMPACTED_SNOW, map);
        putRecipe(NewAtbywID("compacted_snow_block"), COMPACTED_SNOW_BLOCK, map);
        putRecipe(NewAtbywID("compacted_snow_bricks"), COMPACTED_SNOW_BRICKS, map);
        putRecipe(NewAtbywID("packed_ice_bricks"), PACKED_ICE_BRICKS, map);
        putRecipe(NewAtbywID("blue_ice_bricks"), BLUE_ICE_BRICKS, map);

        putRecipe(NewAtbywID("compacted_snow_block_stairs"), COMPACTED_SNOW_BLOCK_STAIRS, map);
        putRecipe(NewAtbywID("compacted_snow_bricks_stairs"), COMPACTED_SNOW_BRICKS_STAIRS, map);
        putRecipe(NewAtbywID("packed_ice_stairs"), PACKED_ICE_STAIRS, map);
        putRecipe(NewAtbywID("blue_ice_stairs"), BLUE_ICE_STAIRS, map);
        putRecipe(NewAtbywID("packed_ice_bricks_stairs"), PACKED_ICE_BRICKS_STAIRS, map);
        putRecipe(NewAtbywID("blue_ice_bricks_stairs"), BLUE_ICE_BRICKS_STAIRS, map);

        putRecipe(NewAtbywID("compacted_snow_block_slab"), COMPACTED_SNOW_BLOCK_SLAB, map);
        putRecipe(NewAtbywID("compacted_snow_bricks_slab"), COMPACTED_SNOW_BRICKS_SLAB, map);
        putRecipe(NewAtbywID("packed_ice_slab"), PACKED_ICE_SLAB, map);
        putRecipe(NewAtbywID("blue_ice_slab"), BLUE_ICE_SLAB, map);
        putRecipe(NewAtbywID("packed_ice_bricks_slab"), PACKED_ICE_BRICKS_SLAB, map);
        putRecipe(NewAtbywID("blue_ice_bricks_slab"), BLUE_ICE_BRICKS_SLAB, map);
        putRecipe(NewAtbywID("chiseled_packed_ice_bricks_slab"), CHISELED_PACKED_ICE_BRICKS_SLAB, map);
        putRecipe(NewAtbywID("chiseled_blue_ice_bricks_slab"), CHISELED_BLUE_ICE_BRICKS_SLAB, map);

        putRecipe(NewAtbywID("iron_spike_trap"), IRON_SPIKE_TRAP, map);
        putRecipe(NewAtbywID("gold_spike_trap"), GOLD_SPIKE_TRAP, map);
        putRecipe(NewAtbywID("diamond_spike_trap"), DIAMOND_SPIKE_TRAP, map);
        putRecipe(NewAtbywID("netherite_spike_trap"), NETHERITE_SPIKE_TRAP, map);

        putRecipe(NewAtbywID("soul_jack_o_lantern"), SOUL_JACK_O_LANTERN, map);
        putRecipe(NewAtbywID("redstone_jack_o_lantern"), REDSTONE_JACK_O_LANTERN, map);

        putRecipe(NewAtbywID("timer_repeater_manual"), TIMER_REPEATER_MANUAL, map);
        putRecipe(NewAtbywID("timer_repeater_clock"), TIMER_REPEATER_CLOCK, map);

        putRecipe(NewAtbywID("redstone_cross_path"), REDSTONE_CROSS_PATH, map);

        putRecipe(NewAtbywID("large_chain_link"), LARGE_CHAIN_LINK, map);
        putRecipe(NewAtbywID("large_chain"), LARGE_CHAIN, map);

        putRecipe(NewAtbywID("rooted_dirt_stairs"), ROOTED_DIRT_STAIRS, map);
        putRecipe(NewAtbywID("rooted_dirt_slab"), ROOTED_DIRT_SLAB, map);
    }
}