package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.azagwen.atbyw.blocks.statues.StatueRegistry;
import net.azagwen.atbyw.items.AtbywItems;
import net.azagwen.atbyw.util.Quadruplet;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
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
        LOGGER.info(Arrays.toString(keys));

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

    //TODO: port vanilla stick-involving recipes (Armorstand, Bow, Crossbow and Fishing rod left)

    public static JsonObject GENERIC_STICK;
    public static JsonObject TORCH;
    public static JsonObject REDSTONE_TORCH;
    public static JsonObject SOUL_TORCH;
    public static JsonObject LEVER;
    public static JsonObject TRIPWIRE_HOOK;
    public static JsonObject ARROW;
    public static JsonObject OAK_BOOKSHELF;
    public static JsonObject OAK_LADDER;
    public static JsonObject PAINTING;
    public static JsonObject ITEM_FRAME;
    public static JsonObject RAIL;
    public static JsonObject POWERED_RAIL;
    public static JsonObject ACTIVATOR_RAIL;

    public static JsonObject[] FENCE_VARIANTS;
    public static JsonObject[] FENCE_GATE_VARIANTS;
    public static JsonObject[] SIGN_VARIANTS;
    public static JsonObject[] BANNER_COLORS;

    public static JsonObject DIRT_STAIRS;
    public static JsonObject GRASS_BLOCK_STAIRS;
    public static JsonObject MYCELIUM_STAIRS;
    public static JsonObject COARSE_DIRT_STAIRS;
    public static JsonObject PODZOL_STAIRS;
    public static JsonObject NETHERRACK_STAIRS;
    public static JsonObject CRIMSON_NYLIUM_STAIRS;
    public static JsonObject WARPED_NYLIUM_STAIRS;

    public static JsonObject DIRT_SLAB;
    public static JsonObject GRASS_BLOCK_SLAB;
    public static JsonObject MYCELIUM_SLAB;
    public static JsonObject COARSE_DIRT_SLAB;
    public static JsonObject PODZOL_SLAB;
    public static JsonObject NETHERRACK_SLAB;
    public static JsonObject CRIMSON_NYLIUM_SLAB;
    public static JsonObject WARPED_NYLIUM_SLAB;

    public static JsonObject BASALT_BRICKS_FROM_BASALT;
    public static JsonObject BASALT_PILLAR_FROM_BASALT;
    public static JsonObject BASALT_BRICKS_FROM_POLISHED_BASALT;
    public static JsonObject BASALT_PILLAR_FROM_POLISHED_BASALT;

    public static JsonObject IRON_FENCE_DOOR;
    public static JsonObject BAMBOO_LADDER;
    public static JsonObject BAMBOO_STICK;
    public static JsonObject REDSTONE_LANTERN;
    public static JsonObject SHROOM_STICK;

    public static JsonObject GRANITE_TILES;
    public static JsonObject DIORITE_BRICKS;
    public static JsonObject ANDESITE_BRICKS;

    public static JsonObject[] STICK_VARIANTS;
    public static JsonObject[] BOOKSHELF_VARIANTS;
    public static JsonObject[] BOOKSHELF_TOGGLES_VARIANTS;
    public static JsonObject[] LADDERS_VARIANTS;
    public static JsonObject[] FENCE_DOOR_VARIANTS;
    public static JsonObject[] FLOWER_SWITCHES;

    public static JsonObject WOODEN_SWORD_FROM_STICK_VARIANTS;
    public static JsonObject WOODEN_AXE_FROM_STICK_VARIANTS;
    public static JsonObject WOODEN_PICKAXE_FROM_STICK_VARIANTS;
    public static JsonObject WOODEN_SHOVEL_FROM_STICK_VARIANTS;
    public static JsonObject WOODEN_HOE_FROM_STICK_VARIANTS;

    public static JsonObject STONE_SWORD_FROM_STICK_VARIANTS;
    public static JsonObject STONE_AXE_FROM_STICK_VARIANTS;
    public static JsonObject STONE_PICKAXE_FROM_STICK_VARIANTS;
    public static JsonObject STONE_SHOVEL_FROM_STICK_VARIANTS;
    public static JsonObject STONE_HOE_FROM_STICK_VARIANTS;

    public static JsonObject IRON_SWORD_FROM_STICK_VARIANTS;
    public static JsonObject IRON_AXE_FROM_STICK_VARIANTS;
    public static JsonObject IRON_PICKAXE_FROM_STICK_VARIANTS;
    public static JsonObject IRON_SHOVEL_FROM_STICK_VARIANTS;
    public static JsonObject IRON_HOE_FROM_STICK_VARIANTS;

    public static JsonObject GOLDEN_SWORD_FROM_STICK_VARIANTS;
    public static JsonObject GOLDEN_AXE_FROM_STICK_VARIANTS;
    public static JsonObject GOLDEN_PICKAXE_FROM_STICK_VARIANTS;
    public static JsonObject GOLDEN_SHOVEL_FROM_STICK_VARIANTS;
    public static JsonObject GOLDEN_HOE_FROM_STICK_VARIANTS;

    public static JsonObject DIAMOND_SWORD_FROM_STICK_VARIANTS;
    public static JsonObject DIAMOND_AXE_FROM_STICK_VARIANTS;
    public static JsonObject DIAMOND_PICKAXE_FROM_STICK_VARIANTS;
    public static JsonObject DIAMOND_SHOVEL_FROM_STICK_VARIANTS;
    public static JsonObject DIAMOND_HOE_FROM_STICK_VARIANTS;

    public static JsonObject NETHERITE_SWORD_FROM_STICK_VARIANTS;
    public static JsonObject NETHERITE_AXE_FROM_STICK_VARIANTS;
    public static JsonObject NETHERITE_PICKAXE_FROM_STICK_VARIANTS;
    public static JsonObject NETHERITE_SHOVEL_FROM_STICK_VARIANTS;
    public static JsonObject NETHERITE_HOE_FROM_STICK_VARIANTS;

    public static JsonObject TERRACOTTA_STAIRS;
    public static JsonObject TERRACOTTA_SLAB;
    public static JsonObject TERRACOTTA_BRICKS;
    public static JsonObject TERRACOTTA_BRICKS_STAIRS;
    public static JsonObject TERRACOTTA_BRICKS_SLAB;
    public static JsonObject TERRACOTTA_BRICKS_WALL;

    public static JsonObject[] TERRACOTTA_STAIRS_COLORS_DYING;
    public static JsonObject[] TERRACOTTA_SLAB_COLORS_DYING;
    public static JsonObject[] TERRACOTTA_BRICKS_COLORS_DYING;
    public static JsonObject[] TERRACOTTA_BRICKS_STAIRS_COLORS_DYING;
    public static JsonObject[] TERRACOTTA_BRICKS_SLAB_COLORS_DYING;
    public static JsonObject[] TERRACOTTA_BRICKS_WALL_COLORS_DYING;

    public static JsonObject[] TERRACOTTA_STAIRS_COLORS_SHAPED;
    public static JsonObject[] TERRACOTTA_SLAB_COLORS_SHAPED;
    public static JsonObject[] TERRACOTTA_BRICKS_COLORS_SHAPED;
    public static JsonObject[] TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED;
    public static JsonObject[] TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED;
    public static JsonObject[] TERRACOTTA_BRICKS_WALL_COLORS_SHAPED;

    public static JsonObject[] CONCRETE_STAIRS_COLORS;
    public static JsonObject[] CONCRETE_SLAB_COLORS;
    public static JsonObject[] CINDER_BLOCKS_COLORS;
    public static JsonObject[] CINDER_BLOCKS_WALL_COLORS;

    public static JsonObject BEE_ESSENCE;
    public static JsonObject SHULKER_ESSENCE;
    public static JsonObject CAT_ESSENCE;
    public static JsonObject CHICKEN_ESSENCE;
    public static JsonObject RABBIT_ESSENCE;
    public static JsonObject FOX_ESSENCE;
    public static JsonObject COD_ESSENCE;
    public static JsonObject SALMON_ESSENCE;
    public static JsonObject PUFFER_FISH_ESSENCE;
    public static JsonObject MAGMA_CUBE_ESSENCE;
    public static JsonObject SLIME_ESSENCE;

    public static JsonObject BEE_STATUE;
    public static JsonObject SILVERFISH_STATUE;
    public static JsonObject ENDERMITE_STATUE;
    public static JsonObject SHULKER_STATUE;
    public static JsonObject CAT_STATUE;
    public static JsonObject WOLF_STATUE;
    public static JsonObject CHICKEN_STATUE;
    public static JsonObject RABBIT_STATUE;
    public static JsonObject FOX_STATUE;
    public static JsonObject COD_STATUE;
    public static JsonObject SALMON_STATUE;
    public static JsonObject PUFFER_FISH_STATUE;
    public static JsonObject MAGMA_CUBE_STATUE;
    public static JsonObject SLIME_STATUE;

    public static JsonObject GRANITE_COLUMN;
    public static JsonObject DIORITE_COLUMN;
    public static JsonObject ANDESITE_COLUMN;
    public static JsonObject GRANITE_COLUMN_FROM_POLISHED;
    public static JsonObject DIORITE_COLUMN_FROM_POLISHED;
    public static JsonObject ANDESITE_COLUMN_FROM_POLISHED;
    public static JsonObject SANDSTONE_COLUMN;
    public static JsonObject SANDSTONE_COLUMN_FROM_CUT;
    public static JsonObject CHISELED_SANDSTONE_COLUMN;
    public static JsonObject RED_SANDSTONE_COLUMN;
    public static JsonObject RED_SANDSTONE_COLUMN_FROM_CUT;
    public static JsonObject CHISELED_RED_SANDSTONE_COLUMN;
    public static JsonObject PURPUR_COLUMN;
    public static JsonObject STONE_BRICKS_COLUMN;
    public static JsonObject MOSSY_STONE_BRICKS_COLUMN;
    public static JsonObject CRACKED_STONE_BRICKS_COLUMN;
    public static JsonObject NETHER_BRICKS_COLUMN;
    public static JsonObject QUARTZ_COLUMN;
    public static JsonObject PRISMARINE_COLUMN;
    public static JsonObject BLACKSTONE_COLUMN;

    public static JsonObject PURPUR_TILES;
    public static JsonObject SMOOTH_PURPUR_BLOCK;
    public static JsonObject CHISELED_PURPUR_BLOCK;
    public static JsonObject PURPUR_TILES_STAIRS;
    public static JsonObject SMOOTH_PURPUR_STAIRS;
    public static JsonObject PURPUR_TILES_SLAB;
    public static JsonObject SMOOTH_PURPUR_SLAB;


    public static void init() {
        AtbywRecipesStonecutting.init();

        GENERIC_STICK = createRecipeFromConfig("", AtbywRecipeConfigs.STICK_1, getItemID(Items.STICK), newKeyPair("tag", BlockTags.PLANKS.getId()));
        TORCH = createRecipeFromConfig("", AtbywRecipeConfigs.TORCH_2, getItemID(Items.TORCH), newKeyPair("tag", ItemTags.COALS.getId()), newKeyPair("tag", AtbywID("sticks")));
        REDSTONE_TORCH = createRecipeFromConfig("", AtbywRecipeConfigs.TORCH_2, getItemID(Items.REDSTONE_TORCH), newKeyPair("item", getItemID(Items.REDSTONE)), newKeyPair("tag", AtbywID("sticks")));
        SOUL_TORCH = createRecipeFromConfig("", AtbywRecipeConfigs.ARROW_3, getItemID(Items.SOUL_TORCH), newKeyPair("tag", ItemTags.COALS.getId()), newKeyPair("tag", AtbywID("sticks")), newKeyPair("tag", BlockTags.SOUL_FIRE_BASE_BLOCKS.getId()));
        LEVER = createRecipeFromConfig("", AtbywRecipeConfigs.TORCH_2, getItemID(Items.LEVER), newKeyPair("tag", AtbywID("sticks")), newKeyPair("tag", new Identifier("stone_tool_materials")));
        TRIPWIRE_HOOK = createRecipeFromConfig("", AtbywRecipeConfigs.ARROW_3, getItemID(Items.TRIPWIRE_HOOK), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")), newKeyPair("tag", BlockTags.PLANKS.getId()));
        ARROW = createRecipeFromConfig("", AtbywRecipeConfigs.ARROW_3, getItemID(Items.ARROW), newKeyPair("item", getItemID(Items.FLINT)), newKeyPair("tag", AtbywID("sticks")), newKeyPair("item", getItemID(Items.FEATHER)));
        OAK_BOOKSHELF = createRecipeFromConfig("bookshelf", AtbywRecipeConfigs.BOOKSHELF_2, getBlockID(Blocks.BOOKSHELF), newKeyPair("item", getBlockID(Blocks.OAK_PLANKS)), newKeyPair("item", getItemID(Items.BOOK)));
        OAK_LADDER = createRecipeFromConfig("ladders", AtbywRecipeConfigs.LADDER_1, getBlockID(Blocks.LADDER), newKeyPair("item", getItemID(AtbywItems.OAK_STICK)));
        PAINTING = createRecipeFromConfig("", AtbywRecipeConfigs.DYING_2, getItemID(Items.PAINTING), newKeyPair("tag", AtbywID("sticks")), newKeyPair("tag", BlockTags.WOOL.getId()));
        ITEM_FRAME = createRecipeFromConfig("", AtbywRecipeConfigs.DYING_2, getItemID(Items.ITEM_FRAME), newKeyPair("tag", AtbywID("sticks")), newKeyPair("item", getItemID(Items.LEATHER)));
        RAIL = createRecipeFromConfig("", AtbywRecipeConfigs.RAIL_2, getBlockID(Blocks.RAIL), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        POWERED_RAIL = createRecipeFromConfig("", AtbywRecipeConfigs.RAIL_POWERED_3, getBlockID(Blocks.POWERED_RAIL), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")), newKeyPair("item", getItemID(Items.REDSTONE)));
        ACTIVATOR_RAIL = createRecipeFromConfig("", AtbywRecipeConfigs.RAIL_POWERED_3, getBlockID(Blocks.POWERED_RAIL), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")), newKeyPair("item", getItemID(Items.REDSTONE_TORCH)));;

        FENCE_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "fences", AtbywRecipeConfigs.FENCE_2, new Pair<>(mcNameSpace, "fence"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", nameSpace, "stick", true));
        FENCE_GATE_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "fence_gates", AtbywRecipeConfigs.FENCE_GATE_2, new Pair<>(mcNameSpace, "fence"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", nameSpace, "stick", true));
        SIGN_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "signs", AtbywRecipeConfigs.SIGN_2, new Pair<>(mcNameSpace, "fence"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", nameSpace, "stick", true));
        BANNER_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, "banners", AtbywRecipeConfigs.SIGN_2, new Pair<>(mcNameSpace, "banner"), newKeyQuadruplet("item", mcNameSpace, "wool", true), newKeyQuadruplet("tag", nameSpace, "sticks", false));

        DIRT_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.DIRT)));
        GRASS_BLOCK_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.GRASS_BLOCK_STAIRS), newKeyPair("item", getBlockID(Blocks.GRASS_BLOCK)));
        MYCELIUM_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.MYCELIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.MYCELIUM)));
        COARSE_DIRT_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.COARSE_DIRT_STAIRS), newKeyPair("item", getBlockID(Blocks.COARSE_DIRT)));
        PODZOL_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PODZOL_STAIRS), newKeyPair("item", getBlockID(Blocks.PODZOL)));
        NETHERRACK_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.NETHERRACK_STAIRS), newKeyPair("item", getBlockID(Blocks.NETHERRACK)));
        CRIMSON_NYLIUM_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.CRIMSON_NYLIUM)));
        WARPED_NYLIUM_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.WARPED_NYLIUM_STAIRS), newKeyPair("item", getBlockID(Blocks.WARPED_NYLIUM)));

        DIRT_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.DIRT)));
        GRASS_BLOCK_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.GRASS_BLOCK_SLAB), newKeyPair("item", getBlockID(Blocks.GRASS_BLOCK)));
        MYCELIUM_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.MYCELIUM_SLAB), newKeyPair("item", getBlockID(Blocks.MYCELIUM)));
        COARSE_DIRT_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.COARSE_DIRT_SLAB), newKeyPair("item", getBlockID(Blocks.COARSE_DIRT)));
        PODZOL_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PODZOL_SLAB), newKeyPair("item", getBlockID(Blocks.PODZOL)));
        NETHERRACK_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.NETHERRACK_SLAB), newKeyPair("item", getBlockID(Blocks.NETHERRACK)));
        CRIMSON_NYLIUM_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_SLAB), newKeyPair("item", getBlockID(Blocks.CRIMSON_NYLIUM)));
        WARPED_NYLIUM_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.WARPED_NYLIUM_SLAB), newKeyPair("item", getBlockID(Blocks.WARPED_NYLIUM)));

        BASALT_BRICKS_FROM_BASALT = createRecipeFromConfig("basalt_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BASALT_BRICKS), newKeyPair("item", getBlockID(Blocks.BASALT)));
        BASALT_PILLAR_FROM_BASALT = createRecipeFromConfig("basalt_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.BASALT_BRICKS), newKeyPair("item", getBlockID(Blocks.BASALT)));
        BASALT_BRICKS_FROM_POLISHED_BASALT = createRecipeFromConfig("basalt_pillar", 2, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.BASALT_PILLAR), newKeyPair("item", getBlockID(Blocks.POLISHED_BASALT)));
        BASALT_PILLAR_FROM_POLISHED_BASALT = createRecipeFromConfig("basalt_pillar", 2, AtbywRecipeConfigs.STICK_1, getBlockID(AtbywBlocks.BASALT_PILLAR), newKeyPair("item", getBlockID(Blocks.POLISHED_BASALT)));

        IRON_FENCE_DOOR = createRecipeFromConfig("fence_door", AtbywRecipeConfigs.FENCE_DOOR_1, getBlockID(AtbywBlocks.IRON_FENCE_DOOR), newKeyPair("item", getItemID(Items.IRON_INGOT)));
        BAMBOO_LADDER = createRecipeFromConfig("ladders", 3, AtbywRecipeConfigs.RAIL_2, getBlockID(AtbywBlocks.BAMBOO_LADDER), newKeyPair("item", getItemID(AtbywItems.BAMBOO_STICK)), newKeyPair("item", getItemID(Items.BAMBOO)));
        BAMBOO_STICK = createRecipeFromConfig("stick", AtbywRecipeConfigs.STICK_1, getItemID(AtbywItems.BAMBOO_STICK), newKeyPair("item", getItemID(Items.BAMBOO)));
        REDSTONE_LANTERN = createRecipeFromConfig("", AtbywRecipeConfigs.DYING_2, getBlockID(AtbywBlocks.REDSTONE_LANTERN), newKeyPair("item", getItemID(Items.IRON_NUGGET)), newKeyPair("item", getItemID(Items.REDSTONE_TORCH)));
        SHROOM_STICK = createRecipeFromConfig("", AtbywRecipeConfigs.STICK_1, getItemID(AtbywItems.SHROOMSTICK), newKeyPair("item", getBlockID(Blocks.SHROOMLIGHT)));

        GRANITE_TILES = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.GRANITE_TILES), newKeyPair("item", getBlockID(Blocks.GRANITE)));
        DIORITE_BRICKS = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.DIORITE_BRICKS), newKeyPair("item", getBlockID(Blocks.DIORITE)));
        ANDESITE_BRICKS = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.ANDESITE_BRICKS), newKeyPair("item", getBlockID(Blocks.ANDESITE)));

        STICK_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "stick", AtbywRecipeConfigs.STICK_1, new Pair<>(nameSpace, "stick"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
        BOOKSHELF_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "bookshelf", AtbywRecipeConfigs.BOOKSHELF_2, new Pair<>(nameSpace, "bookshelf"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false));
        BOOKSHELF_TOGGLES_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "bookshelf_toggle", AtbywRecipeConfigs.BOOKSHELF_TOGGLE_4, new Pair<>(nameSpace, "bookshelf_toggle"), newKeyQuadruplet("item", mcNameSpace, "planks", true), newKeyQuadruplet("item", mcNameSpace, "book", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false), newKeyQuadruplet("tag", mcNameSpace, "stone_tool_materials", false));
        LADDERS_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "ladders", AtbywRecipeConfigs.LADDER_1, new Pair<>(nameSpace, "ladder"), newKeyQuadruplet("item", nameSpace, "stick", true));
        FENCE_DOOR_VARIANTS = createMultiRecipesFromConfig(WOOD_NAMES, "fence_door", AtbywRecipeConfigs.FENCE_DOOR_1, new Pair<>(nameSpace, "fence_door"), newKeyQuadruplet("item", mcNameSpace, "planks", true));
        FLOWER_SWITCHES = createMultiRecipesFromConfig(FLOWER_NAMES, "flower_switches", AtbywRecipeConfigs.FLOWER_SWITCH_3, new Pair<>(nameSpace, "pull_switch"), newKeyQuadruplet("item", mcNameSpace, "", true), newKeyQuadruplet("tag", nameSpace, "sticks", false), newKeyQuadruplet("item", mcNameSpace, "redstone", false));

        WOODEN_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SWORD_2, getItemID(Items.WOODEN_SWORD), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SWORD_2, getItemID(Items.STONE_SWORD), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SWORD_2, getItemID(Items.IRON_SWORD), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SWORD_2, getItemID(Items.GOLDEN_SWORD), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SWORD_2, getItemID(Items.DIAMOND_SWORD), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SWORD_2, getItemID(Items.NETHERITE_SWORD), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        WOODEN_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.AXE_2, getItemID(Items.WOODEN_AXE), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.AXE_2, getItemID(Items.STONE_AXE), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.AXE_2, getItemID(Items.IRON_AXE), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.AXE_2, getItemID(Items.GOLDEN_AXE), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.AXE_2, getItemID(Items.DIAMOND_AXE), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.AXE_2, getItemID(Items.NETHERITE_AXE), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        WOODEN_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.WOODEN_PICKAXE), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.STONE_PICKAXE), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.IRON_PICKAXE), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.GOLDEN_PICKAXE), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.DIAMOND_PICKAXE), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.NETHERITE_PICKAXE), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        WOODEN_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.WOODEN_SHOVEL), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.STONE_SHOVEL), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.IRON_SHOVEL), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.GOLDEN_SHOVEL), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.DIAMOND_SHOVEL), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.NETHERITE_SHOVEL), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        WOODEN_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.HOE_2, getItemID(Items.WOODEN_HOE), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.HOE_2, getItemID(Items.STONE_HOE), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.HOE_2, getItemID(Items.IRON_HOE), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.HOE_2, getItemID(Items.GOLDEN_HOE), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.HOE_2, getItemID(Items.DIAMOND_HOE), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("", AtbywRecipeConfigs.HOE_2, getItemID(Items.NETHERITE_HOE), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        TERRACOTTA_STAIRS = createRecipeFromConfig("terracotta_stairs", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_STAIRS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
        TERRACOTTA_SLAB = createRecipeFromConfig("terracotta_slab", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_STAIRS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
        TERRACOTTA_BRICKS = createRecipeFromConfig("terracotta_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
        TERRACOTTA_BRICKS_STAIRS = createRecipeFromConfig("terracotta_bricks_stairs", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
        TERRACOTTA_BRICKS_SLAB = createRecipeFromConfig("terracotta_bricks_slab", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
        TERRACOTTA_BRICKS_WALL = createRecipeFromConfig("terracotta_bricks_wall", AtbywRecipeConfigs.WALL_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_WALL), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));

        TERRACOTTA_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(nameSpace, "terracotta_stairs"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
        TERRACOTTA_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(nameSpace, "terracotta_slab"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
        TERRACOTTA_BRICKS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks", AtbywRecipeConfigs.BRICKS_1, new Pair<>(nameSpace, "terracotta_bricks"), newKeyQuadruplet("item", mcNameSpace, "terracotta", true));
        TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(nameSpace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", nameSpace, "terracotta_bricks", true));
        TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(nameSpace, "terracotta_bricks_slab"), newKeyQuadruplet("item", nameSpace, "terracotta_bricks", true));
        TERRACOTTA_BRICKS_WALL_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_wall", AtbywRecipeConfigs.WALL_1, new Pair<>(nameSpace, "terracotta_bricks_wall"), newKeyQuadruplet("item", nameSpace, "terracotta_bricks", true));

        TERRACOTTA_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_stairs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(nameSpace, "terracotta_stairs"), newKeyQuadruplet("item", nameSpace, "terracotta_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
        TERRACOTTA_SLAB_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_slabs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(nameSpace, "terracotta_slab"), newKeyQuadruplet("item", nameSpace, "terracotta_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
        TERRACOTTA_BRICKS_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(nameSpace, "terracotta_bricks"), newKeyQuadruplet("item", nameSpace, "terracotta_bricks", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
        TERRACOTTA_BRICKS_STAIRS_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_stairs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(nameSpace, "terracotta_bricks_stairs"), newKeyQuadruplet("item", nameSpace, "terracotta_bricks_stairs", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
        TERRACOTTA_BRICKS_SLAB_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_slabs_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(nameSpace, "terracotta_bricks_slab"), newKeyQuadruplet("item", nameSpace, "terracotta_bricks_slab", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));
        TERRACOTTA_BRICKS_WALL_COLORS_DYING = createMultiRecipesFromConfig(COLOR_NAMES, "terracotta_bricks_walls_dying", AtbywRecipeConfigs.DYING_2, new Pair<>(nameSpace, "terracotta_bricks_wall"), newKeyQuadruplet("item", nameSpace, "terracotta_bricks_wall", false), newKeyQuadruplet("item", mcNameSpace, "dye", true));

        CONCRETE_STAIRS_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, "concrete_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(nameSpace, "concrete_stairs"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
        CONCRETE_SLAB_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, "concrete_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(nameSpace, "concrete_slab"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
        CINDER_BLOCKS_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, "cinder_blocks", AtbywRecipeConfigs.BRICKS_1, new Pair<>(nameSpace, "cinder_blocks"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
        CINDER_BLOCKS_WALL_COLORS = createMultiRecipesFromConfig(COLOR_NAMES, "cinder_blocks_wall", AtbywRecipeConfigs.WALL_1, new Pair<>(nameSpace, "cinder_blocks_wall"), newKeyQuadruplet("item", nameSpace, "cinder_blocks", true));

        BEE_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.BEE_ESSENCE), newKeyPair("item", getItemID(Items.HONEYCOMB)), newKeyPair("item", getItemID(Items.HONEY_BOTTLE)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        SHULKER_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.SHULKER_ESSENCE), newKeyPair("item", getItemID(Items.SHULKER_SHELL)), newKeyPair("item", getItemID(Items.SHULKER_SHELL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        CAT_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.CAT_ESSENCE), newKeyPair("item", getItemID(Items.STRING)), newKeyPair("item", getItemID(Items.STRING)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        CHICKEN_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.CHICKEN_ESSENCE), newKeyPair("item", getItemID(Items.CHICKEN)), newKeyPair("item", getItemID(Items.FEATHER)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        RABBIT_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.RABBIT_ESSENCE), newKeyPair("item", getItemID(Items.RABBIT)), newKeyPair("item", getItemID(Items.RABBIT_HIDE)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        FOX_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.FOX_ESSENCE), newKeyPair("item", getItemID(Items.SWEET_BERRIES)), newKeyPair("item", getItemID(Items.SWEET_BERRIES)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        COD_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.COD_ESSENCE), newKeyPair("item", getItemID(Items.COD)), newKeyPair("item", getItemID(Items.BONE_MEAL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        SALMON_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.SALMON_ESSENCE), newKeyPair("item", getItemID(Items.SALMON)), newKeyPair("item", getItemID(Items.BONE_MEAL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        PUFFER_FISH_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.PUFFER_FISH_ESSENCE), newKeyPair("item", getItemID(Items.PUFFERFISH)), newKeyPair("item", getItemID(Items.BONE_MEAL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        SLIME_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.SLIME_ESSENCE), newKeyPair("item", getItemID(Items.SLIME_BALL)), newKeyPair("item", getItemID(Items.SLIME_BALL)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));
        MAGMA_CUBE_ESSENCE = createRecipeFromConfig("essence", 1, AtbywRecipeConfigs.DYING_DASHED_3, getItemID(AtbywItems.MAGMA_CUBE_ESSENCE), newKeyPair("item", getItemID(Items.MAGMA_CREAM)), newKeyPair("item", getItemID(Items.MAGMA_CREAM)), newKeyPair("item", getItemID(Items.GLASS_BOTTLE)));

        BEE_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.BEE_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.BEE_ESSENCE)));
        SILVERFISH_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.SILVERFISH_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.SILVERFISH_ESSENCE)));
        ENDERMITE_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.ENDERMITE_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.ENDERMITE_ESSENCE)));
        SHULKER_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.SHULKER_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.SHULKER_ESSENCE)));
        CAT_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.CAT_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.CAT_ESSENCE)));
        WOLF_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.WOLF_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.WOLF_ESSENCE)));
        CHICKEN_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.CHICKEN_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.CHICKEN_ESSENCE)));
        RABBIT_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.RABBIT_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.RABBIT_ESSENCE)));
        FOX_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.FOX_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.FOX_ESSENCE)));
        COD_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.COD_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.COD_ESSENCE)));
        SALMON_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.SALMON_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.SALMON_ESSENCE)));
        PUFFER_FISH_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.PUFFER_FISH_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.PUFFER_FISH_ESSENCE)));
        SLIME_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.SLIME_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.SLIME_ESSENCE)));
        MAGMA_CUBE_STATUE = createRecipeFromConfig("statues", 1, AtbywRecipeConfigs.DYING_2, getBlockID(StatueRegistry.MAGMA_CUBE_STATUE), newKeyPair("item", getBlockID(Blocks.STONE)), newKeyPair("item", getItemID(AtbywItems.MAGMA_CUBE_ESSENCE)));

        GRANITE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.GRANITE_COLUMN), newKeyPair("item", getBlockID(Blocks.GRANITE)));
        DIORITE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.DIORITE_COLUMN), newKeyPair("item", getBlockID(Blocks.DIORITE)));
        ANDESITE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.ANDESITE_COLUMN), newKeyPair("item", getBlockID(Blocks.ANDESITE)));
        GRANITE_COLUMN_FROM_POLISHED = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.GRANITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_GRANITE)));
        DIORITE_COLUMN_FROM_POLISHED = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.DIORITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_DIORITE)));
        ANDESITE_COLUMN_FROM_POLISHED = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.ANDESITE_COLUMN), newKeyPair("item", getBlockID(Blocks.POLISHED_ANDESITE)));
        SANDSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SANDSTONE)));
        SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SMOOTH_SANDSTONE)));
        CHISELED_SANDSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CHISELED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.CHISELED_SANDSTONE)));
        RED_SANDSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.RED_SANDSTONE)));
        RED_SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.SMOOTH_RED_SANDSTONE)));
        CHISELED_RED_SANDSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.CHISELED_RED_SANDSTONE)));
        PURPUR_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.PURPUR_COLUMN), newKeyPair("item", getBlockID(Blocks.PURPUR_BLOCK)));
        STONE_BRICKS_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.STONE_BRICKS)));
        MOSSY_STONE_BRICKS_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.MOSSY_STONE_BRICKS)));
        CRACKED_STONE_BRICKS_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.CRACKED_STONE_BRICKS)));
        NETHER_BRICKS_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.NETHER_BRICKS_COLUMN), newKeyPair("item", getBlockID(Blocks.NETHER_BRICKS)));
        QUARTZ_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.QUARTZ_COLUMN), newKeyPair("item", getBlockID(Blocks.QUARTZ_BLOCK)));
        PRISMARINE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.PRISMARINE_COLUMN), newKeyPair("item", getBlockID(Blocks.PRISMARINE_BRICKS)));
        BLACKSTONE_COLUMN = createRecipeFromConfig("columns", AtbywRecipeConfigs.COLUMN_1, getBlockID(AtbywBlocks.BLACKSTONE_COLUMN), newKeyPair("item", getBlockID(Blocks.BLACKSTONE)));

        PURPUR_TILES = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK), newKeyPair("item", getBlockID(Blocks.PURPUR_BLOCK)));
        SMOOTH_PURPUR_BLOCK = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.PURPUR_TILES), newKeyPair("item", getBlockID(Blocks.PURPUR_PILLAR)));
        CHISELED_PURPUR_BLOCK = createRecipeFromConfig("", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.CHISELED_PURPUR_BLOCK), newKeyPair("item", getBlockID(Blocks.PURPUR_SLAB)));
        PURPUR_TILES_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.PURPUR_TILES_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.PURPUR_TILES)));
        SMOOTH_PURPUR_STAIRS = createRecipeFromConfig("", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.SMOOTH_PURPUR_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK)));
        PURPUR_TILES_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.PURPUR_TILES_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.PURPUR_TILES)));
        SMOOTH_PURPUR_SLAB = createRecipeFromConfig("", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.SMOOTH_PURPUR_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.SMOOTH_PURPUR_BLOCK)));
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void injectRecipes(Map<Identifier, JsonElement> map) {
        AtbywRecipesStonecutting.injectRecipes(map);

        // Multi recipes
        for (int i = 0; i < WOOD_NAMES_NO_OAK.length; i++) {
            putRecipe(AtbywID(WOOD_NAMES_NO_OAK[i] + "_bookshelf"), BOOKSHELF_VARIANTS[i], map);
            putRecipe(AtbywID(WOOD_NAMES_NO_OAK[i] + "_ladder"), LADDERS_VARIANTS[i], map);
        }

        for (int i = 0; i < WOOD_NAMES.length; i++) {
            putRecipe(AtbywID(WOOD_NAMES[i] + "_fence_door"), FENCE_DOOR_VARIANTS[i], map);
            putRecipe(AtbywID(WOOD_NAMES[i] + "_bookshelf_toggle"), BOOKSHELF_TOGGLES_VARIANTS[i], map);
            putRecipe(AtbywID(WOOD_NAMES[i] + "_stick"), STICK_VARIANTS[i], map);

            putRecipe(new Identifier(WOOD_NAMES[i] + "_fence"), FENCE_VARIANTS[i], map);
            putRecipe(new Identifier(WOOD_NAMES[i] + "_fence_gate"), FENCE_GATE_VARIANTS[i], map);
            putRecipe(new Identifier(WOOD_NAMES[i] + "_sign"), SIGN_VARIANTS[i], map);
        }

        for (int i = 0; i < COLOR_NAMES.length; i++) {
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_stairs_from_dye"), TERRACOTTA_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_slab_from_dye"), TERRACOTTA_SLAB_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_from_dye"), TERRACOTTA_BRICKS_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_stairs_from_dye"), TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_slab_from_dye"), TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_wall_from_dye"), TERRACOTTA_BRICKS_WALL_COLORS_SHAPED[i], map);

            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_stairs"), TERRACOTTA_STAIRS_COLORS_DYING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_slab"), TERRACOTTA_SLAB_COLORS_DYING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks"), TERRACOTTA_BRICKS_COLORS_DYING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_stairs"), TERRACOTTA_BRICKS_STAIRS_COLORS_DYING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_slab"), TERRACOTTA_BRICKS_SLAB_COLORS_DYING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_wall"), TERRACOTTA_BRICKS_WALL_COLORS_DYING[i], map);

            putRecipe(AtbywID(COLOR_NAMES[i] + "_concrete_stairs"), CONCRETE_STAIRS_COLORS[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_concrete_slab"), CONCRETE_SLAB_COLORS[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_cinder_blocks"), CINDER_BLOCKS_COLORS[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_cinder_blocks_wall"), CINDER_BLOCKS_WALL_COLORS[i], map);

            putRecipe(new Identifier(COLOR_NAMES[i] + "_banner"), BANNER_COLORS[i], map);
        }

        // Single recipes
        putRecipe(new Identifier("stick"), GENERIC_STICK, map);
        putRecipe(new Identifier("torch"), TORCH, map);
        putRecipe(new Identifier("redstone_torch"), REDSTONE_TORCH, map);
        putRecipe(new Identifier("soul_torch"), SOUL_TORCH, map);
        putRecipe(new Identifier("lever"), LEVER, map);
        putRecipe(new Identifier("tripwire_hook"), TRIPWIRE_HOOK, map);
        putRecipe(new Identifier("arrow"), ARROW, map);
        putRecipe(new Identifier("bookshelf"), OAK_BOOKSHELF, map);
        putRecipe(new Identifier("ladder"), OAK_LADDER, map);
        putRecipe(new Identifier("ladder"), PAINTING, map);
        putRecipe(new Identifier("item_frame"), ITEM_FRAME, map);
        putRecipe(new Identifier("rail"), RAIL, map);
        putRecipe(new Identifier("powered_rail"), POWERED_RAIL, map);
        putRecipe(new Identifier("activator_rail"), ACTIVATOR_RAIL, map);

        putRecipe(new Identifier("wooden_sword_from_stick_tag"), WOODEN_SWORD_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("stone_sword_from_stick_tag"), STONE_SWORD_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("iron_sword_from_stick_tag"), IRON_SWORD_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("golden_sword_from_stick_tag"), GOLDEN_SWORD_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("diamond_sword_from_stick_tag"), DIAMOND_SWORD_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("netherite_sword_from_stick_tag"), NETHERITE_SWORD_FROM_STICK_VARIANTS, map);

        putRecipe(new Identifier("wooden_axe_from_stick_tag"), WOODEN_AXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("stone_axe_from_stick_tag"), STONE_AXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("iron_axe_from_stick_tag"), IRON_AXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("golden_axe_from_stick_tag"), GOLDEN_AXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("diamond_axe_from_stick_tag"), DIAMOND_AXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("netherite_axe_from_stick_tag"), NETHERITE_AXE_FROM_STICK_VARIANTS, map);

        putRecipe(new Identifier("wooden_pickaxe_from_stick_tag"), WOODEN_PICKAXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("stone_pickaxe_from_stick_tag"), STONE_PICKAXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("iron_pickaxe_from_stick_tag"), IRON_PICKAXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("golden_pickaxe_from_stick_tag"), GOLDEN_PICKAXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("diamond_pickaxe_from_stick_tag"), DIAMOND_PICKAXE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("netherite_pickaxe_from_stick_tag"), NETHERITE_PICKAXE_FROM_STICK_VARIANTS, map);

        putRecipe(new Identifier("wooden_shovel_from_stick_tag"), WOODEN_SHOVEL_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("stone_shovel_from_stick_tag"), STONE_SHOVEL_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("iron_shovel_from_stick_tag"), IRON_SHOVEL_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("golden_shovel_from_stick_tag"), GOLDEN_SHOVEL_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("diamond_shovel_from_stick_tag"), DIAMOND_SHOVEL_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("netherite_shovel_from_stick_tag"), NETHERITE_SHOVEL_FROM_STICK_VARIANTS, map);

        putRecipe(new Identifier("wooden_hoe_from_stick_tag"), WOODEN_HOE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("stone_hoe_from_stick_tag"), STONE_HOE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("iron_hoe_from_stick_tag"), IRON_HOE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("golden_hoe_from_stick_tag"), GOLDEN_HOE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("diamond_hoe_from_stick_tag"), DIAMOND_HOE_FROM_STICK_VARIANTS, map);
        putRecipe(new Identifier("netherite_hoe_from_stick_tag"), NETHERITE_HOE_FROM_STICK_VARIANTS, map);

        putRecipe(new Identifier("terracotta_stairs_from_stick_tag"), TERRACOTTA_STAIRS, map);
        putRecipe(new Identifier("terracotta_slab_from_stick_tag"), TERRACOTTA_SLAB, map);
        putRecipe(new Identifier("terracotta_bricks_from_stick_tag"), TERRACOTTA_BRICKS, map);
        putRecipe(new Identifier("terracotta_bricks_stairs_from_stick_tag"), TERRACOTTA_BRICKS_STAIRS, map);
        putRecipe(new Identifier("terracotta_bricks_slab_from_stick_tag"), TERRACOTTA_BRICKS_SLAB, map);
        putRecipe(new Identifier("terracotta_bricks_wall_from_stick_tag"), TERRACOTTA_BRICKS_WALL, map);

        putRecipe(AtbywID("dirt_stairs"), DIRT_STAIRS, map);
        putRecipe(AtbywID("grass_block_stairs"), GRASS_BLOCK_STAIRS, map);
        putRecipe(AtbywID("mycelium_stairs"), MYCELIUM_STAIRS, map);
        putRecipe(AtbywID("coarse_dirt_stairs"), COARSE_DIRT_STAIRS, map);
        putRecipe(AtbywID("podzol_stairs"), PODZOL_STAIRS, map);
        putRecipe(AtbywID("netherrack_stairs"), NETHERRACK_STAIRS, map);
        putRecipe(AtbywID("crimson_nylium_stairs"), CRIMSON_NYLIUM_STAIRS, map);
        putRecipe(AtbywID("warped_nylium_stairs"), WARPED_NYLIUM_STAIRS, map);

        putRecipe(AtbywID("dirt_slab"), DIRT_SLAB, map);
        putRecipe(AtbywID("grass_block_slab"), GRASS_BLOCK_SLAB, map);
        putRecipe(AtbywID("mycelium_slab"), MYCELIUM_SLAB, map);
        putRecipe(AtbywID("coarse_dirt_slab"), COARSE_DIRT_SLAB, map);
        putRecipe(AtbywID("podzol_slab"), PODZOL_SLAB, map);
        putRecipe(AtbywID("netherrack_slab"), NETHERRACK_SLAB, map);
        putRecipe(AtbywID("crimson_nylium_slab"), CRIMSON_NYLIUM_SLAB, map);
        putRecipe(AtbywID("warped_nylium_slab"), WARPED_NYLIUM_SLAB, map);

        putRecipe(AtbywID("basalt_bricks_from_basalt"), BASALT_BRICKS_FROM_BASALT, map);
        putRecipe(AtbywID("basalt_pillar_from_basalt"), BASALT_PILLAR_FROM_BASALT, map);
        putRecipe(AtbywID("basalt_bricks_from_polished_basalt"), BASALT_BRICKS_FROM_POLISHED_BASALT, map);
        putRecipe(AtbywID("basalt_pillar_from_polished_basalt"), BASALT_PILLAR_FROM_POLISHED_BASALT, map);

        putRecipe(AtbywID("iron_fence_door"), IRON_FENCE_DOOR, map);
        putRecipe(AtbywID("bamboo_ladder"), BAMBOO_LADDER, map);
        putRecipe(AtbywID("bamboo_stick"), BAMBOO_STICK, map);
        putRecipe(AtbywID("redstone_lantern"), REDSTONE_LANTERN, map);
        putRecipe(AtbywID("shroomstick"), SHROOM_STICK, map);

        putRecipe(AtbywID("granite_tiles"), GRANITE_TILES, map);
        putRecipe(AtbywID("diorite_bricks"), DIORITE_BRICKS, map);
        putRecipe(AtbywID("andesite_bricks"), ANDESITE_BRICKS, map);

        putRecipe(AtbywID("bee_essence"), BEE_ESSENCE, map);
        putRecipe(AtbywID("shulker_essence"), SHULKER_ESSENCE, map);
        putRecipe(AtbywID("cat_essence"), CAT_ESSENCE, map);
        putRecipe(AtbywID("chicken_essence"), CHICKEN_ESSENCE, map);
        putRecipe(AtbywID("rabbit_essence"), RABBIT_ESSENCE, map);
        putRecipe(AtbywID("fox_essence"), FOX_ESSENCE, map);
        putRecipe(AtbywID("cod_essence"), COD_ESSENCE, map);
        putRecipe(AtbywID("salmon_essence"), SALMON_ESSENCE, map);
        putRecipe(AtbywID("puffer_fish_essence"), PUFFER_FISH_ESSENCE, map);
        putRecipe(AtbywID("magma_cube_essence"), MAGMA_CUBE_ESSENCE, map);
        putRecipe(AtbywID("slime_essence"), SLIME_ESSENCE, map);

        putRecipe(AtbywID("bee_statue"), BEE_STATUE, map);
        putRecipe(AtbywID("silverfish_statue"), SILVERFISH_STATUE, map);
        putRecipe(AtbywID("endermite_statue"), ENDERMITE_STATUE, map);
        putRecipe(AtbywID("shulker_statue"), SHULKER_STATUE, map);
        putRecipe(AtbywID("cat_statue"), CAT_STATUE, map);
        putRecipe(AtbywID("chicken_statue"), CHICKEN_STATUE, map);
        putRecipe(AtbywID("rabbit_statue"), RABBIT_STATUE, map);
        putRecipe(AtbywID("fox_statue"), FOX_STATUE, map);
        putRecipe(AtbywID("cod_statue"), COD_STATUE, map);
        putRecipe(AtbywID("salmon_statue"), SALMON_STATUE, map);
        putRecipe(AtbywID("puffer_fish_statue"), PUFFER_FISH_STATUE, map);
        putRecipe(AtbywID("magma_cube_statue"), MAGMA_CUBE_STATUE, map);
        putRecipe(AtbywID("slime_statue"), SLIME_STATUE, map);

        putRecipe(AtbywID("granite_column"), GRANITE_COLUMN, map);
        putRecipe(AtbywID("diorite_column"), DIORITE_COLUMN, map);
        putRecipe(AtbywID("andesite_column"), ANDESITE_COLUMN, map);
        putRecipe(AtbywID("granite_column_from_polished"), GRANITE_COLUMN_FROM_POLISHED, map);
        putRecipe(AtbywID("diorite_column_from_polished"), DIORITE_COLUMN_FROM_POLISHED, map);
        putRecipe(AtbywID("andesite_column_from_polished"), ANDESITE_COLUMN_FROM_POLISHED, map);
        putRecipe(AtbywID("sandstone_column"), SANDSTONE_COLUMN, map);
        putRecipe(AtbywID("sandstone_column_from_cut"), SANDSTONE_COLUMN_FROM_CUT, map);
        putRecipe(AtbywID("chiseled_sandstone_column"), CHISELED_SANDSTONE_COLUMN, map);
        putRecipe(AtbywID("red_sandstone_column"), RED_SANDSTONE_COLUMN, map);
        putRecipe(AtbywID("red_sandstone_column_from_cut"), RED_SANDSTONE_COLUMN_FROM_CUT, map);
        putRecipe(AtbywID("chiseled_red_sandstone_column"), CHISELED_RED_SANDSTONE_COLUMN, map);
        putRecipe(AtbywID("purpur_column"), PURPUR_COLUMN, map);
        putRecipe(AtbywID("stone_bricks_column"), STONE_BRICKS_COLUMN, map);
        putRecipe(AtbywID("mossy_stone_bricks_column"), MOSSY_STONE_BRICKS_COLUMN, map);
        putRecipe(AtbywID("cracked_stone_bricks_column"), CRACKED_STONE_BRICKS_COLUMN, map);
        putRecipe(AtbywID("nether_bricks_column"), NETHER_BRICKS_COLUMN, map);
        putRecipe(AtbywID("quartz_column"), QUARTZ_COLUMN, map);
        putRecipe(AtbywID("prismarine_column"), PRISMARINE_COLUMN, map);
        putRecipe(AtbywID("blackstone_column"), BLACKSTONE_COLUMN, map);

        putRecipe(AtbywID("purpur_tiles"), PURPUR_TILES, map);
        putRecipe(AtbywID("smooth_purpur_block"), SMOOTH_PURPUR_BLOCK, map);
        putRecipe(AtbywID("chiseled_purpur_block"), CHISELED_PURPUR_BLOCK, map);
        putRecipe(AtbywID("purpur_tiles_stairs"), PURPUR_TILES_STAIRS, map);
        putRecipe(AtbywID("smooth_purpur_stairs"), SMOOTH_PURPUR_STAIRS, map);
        putRecipe(AtbywID("purpur_tiles_slab"), PURPUR_TILES_SLAB, map);
        putRecipe(AtbywID("smooth_purpur_slab"), SMOOTH_PURPUR_SLAB, map);
    }
}