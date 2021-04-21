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
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.Map;

import static net.azagwen.atbyw.datagen.AtbywRecipeUtils.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;
import static net.azagwen.atbyw.main.AtbywMain.AtbywID;

public class AtbywRecipes {

    //TODO: add stonecutting recipes for granite tiles, bricks, etc...

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

    //Config recipe methods
    @SafeVarargs
    private static JsonObject createRecipeFromConfig(String group, int count, AtbywRecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        Key[] keys = new Key[ingredient.length];

        for (int i = 0; i < ingredient.length; i++) {
            keys[i] = new Key(config.getKeyChars().get(i), ingredient[i].getLeft(), ingredient[i].getRight());
            System.out.println(keys[i > 0 ? i - 1 : i].toString());
        }

        return createShapedRecipeJson(group, config.getPattern(), Lists.newArrayList(keys), result, count);
    }

    @SafeVarargs
    private static JsonObject createRecipeFromConfig(String group, AtbywRecipeConfig config, Identifier result, Pair<String, Identifier>... ingredient) {
        return createRecipeFromConfig(group, config.getCount(), config, result, ingredient);
    }

    @SafeVarargs
    private static JsonObject[] createMultiRecipesFromConfig(String[] nameArray, String group, int count, AtbywRecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        JsonObject[] objects = new JsonObject[nameArray.length];
        Pair<String, Identifier>[] ingredients = new Pair[ingredient.length];

        for (int i = 0; i < nameArray.length; i++) {
            String namePrefix = nameArray[i] + "_";

            for (int j = 0; j < ingredient.length; j++) {
                boolean colorizeIngredient = ingredient[j].getFourth();
                String ingredientPrefix;

                if (colorizeIngredient)
                    ingredientPrefix = namePrefix;
                else
                    ingredientPrefix = "";

                ingredients[j] = newKeyPair(ingredient[j].getFirst(), new Identifier(ingredient[j].getSecond(), ingredientPrefix + ingredient[j].getThird()));
            }

            objects[i] = createRecipeFromConfig(group, count, config, new Identifier(result.getLeft(), namePrefix + result.getRight()), ingredients);
        }

        return objects;
    }

    @SafeVarargs
    private static JsonObject[] createMultiRecipesFromConfig(String[] nameArray, String group, AtbywRecipeConfig config, Pair<String, String> result, Quadruplet<String, String, String, Boolean>... ingredient) {
        return createMultiRecipesFromConfig(nameArray, group, config.getCount(), config, result, ingredient);
    }


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

    public static JsonObject TERRACOTTA_STAIRS_SHAPED;
    public static JsonObject TERRACOTTA_SLAB_SHAPED;
    public static JsonObject TERRACOTTA_BRICKS_SHAPED;
    public static JsonObject TERRACOTTA_BRICKS_STAIRS_SHAPED;
    public static JsonObject TERRACOTTA_BRICKS_SLAB_SHAPED;
    public static JsonObject TERRACOTTA_BRICKS_WALL_SHAPED;

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

    public static JsonObject[] CONCRETE_STAIRS_COLORS_SHAPED;
    public static JsonObject[] CONCRETE_SLAB_COLORS_SHAPED;
    public static JsonObject[] CINDER_BLOCKS_COLORS_SHAPED;
    public static JsonObject[] CINDER_BLOCKS_WALL_COLORS_SHAPED;

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

        WOODEN_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("swords_stick_tags", AtbywRecipeConfigs.SWORD_2, getItemID(Items.WOODEN_SWORD), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("swords_stick_tags", AtbywRecipeConfigs.SWORD_2, getItemID(Items.STONE_SWORD), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("swords_stick_tags", AtbywRecipeConfigs.SWORD_2, getItemID(Items.IRON_SWORD), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("swords_stick_tags", AtbywRecipeConfigs.SWORD_2, getItemID(Items.GOLDEN_SWORD), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("swords_stick_tags", AtbywRecipeConfigs.SWORD_2, getItemID(Items.DIAMOND_SWORD), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_SWORD_FROM_STICK_VARIANTS = createRecipeFromConfig("swords_stick_tags", AtbywRecipeConfigs.SWORD_2, getItemID(Items.NETHERITE_SWORD), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        WOODEN_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("axe_stick_tags", AtbywRecipeConfigs.AXE_2, getItemID(Items.WOODEN_AXE), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("axe_stick_tags", AtbywRecipeConfigs.AXE_2, getItemID(Items.STONE_AXE), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("axe_stick_tags", AtbywRecipeConfigs.AXE_2, getItemID(Items.IRON_AXE), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("axe_stick_tags", AtbywRecipeConfigs.AXE_2, getItemID(Items.GOLDEN_AXE), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("axe_stick_tags", AtbywRecipeConfigs.AXE_2, getItemID(Items.DIAMOND_AXE), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_AXE_FROM_STICK_VARIANTS = createRecipeFromConfig("axe_stick_tags", AtbywRecipeConfigs.AXE_2, getItemID(Items.NETHERITE_AXE), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        WOODEN_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("pickaxe_stick_tags", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.WOODEN_PICKAXE), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("pickaxe_stick_tags", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.STONE_PICKAXE), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("pickaxe_stick_tags", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.IRON_PICKAXE), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("pickaxe_stick_tags", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.GOLDEN_PICKAXE), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("pickaxe_stick_tags", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.DIAMOND_PICKAXE), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_PICKAXE_FROM_STICK_VARIANTS = createRecipeFromConfig("pickaxe_stick_tags", AtbywRecipeConfigs.PICKAXE_2, getItemID(Items.NETHERITE_PICKAXE), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        WOODEN_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("shovel_stick_tags", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.WOODEN_SHOVEL), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("shovel_stick_tags", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.STONE_SHOVEL), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("shovel_stick_tags", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.IRON_SHOVEL), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("shovel_stick_tags", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.GOLDEN_SHOVEL), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("shovel_stick_tags", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.DIAMOND_SHOVEL), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_SHOVEL_FROM_STICK_VARIANTS = createRecipeFromConfig("shovel_stick_tags", AtbywRecipeConfigs.SHOVEL_2, getItemID(Items.NETHERITE_SHOVEL), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        WOODEN_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("hoe_stick_tags", AtbywRecipeConfigs.HOE_2, getItemID(Items.WOODEN_HOE), newKeyPair("tag", BlockTags.PLANKS.getId()), newKeyPair("tag", AtbywID("sticks")));
        STONE_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("hoe_stick_tags", AtbywRecipeConfigs.HOE_2, getItemID(Items.STONE_HOE), newKeyPair("tag", new Identifier("stone_tool_materials")), newKeyPair("tag", AtbywID("sticks")));
        IRON_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("hoe_stick_tags", AtbywRecipeConfigs.HOE_2, getItemID(Items.IRON_HOE), newKeyPair("item", getItemID(Items.IRON_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        GOLDEN_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("hoe_stick_tags", AtbywRecipeConfigs.HOE_2, getItemID(Items.GOLDEN_HOE), newKeyPair("item", getItemID(Items.GOLD_INGOT)), newKeyPair("tag", AtbywID("sticks")));
        DIAMOND_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("hoe_stick_tags", AtbywRecipeConfigs.HOE_2, getItemID(Items.DIAMOND_HOE), newKeyPair("item", getItemID(Items.DIAMOND)), newKeyPair("tag", AtbywID("sticks")));
        NETHERITE_HOE_FROM_STICK_VARIANTS = createRecipeFromConfig("hoe_stick_tags", AtbywRecipeConfigs.HOE_2, getItemID(Items.NETHERITE_HOE), newKeyPair("item", getItemID(Items.NETHERITE_INGOT)), newKeyPair("tag", AtbywID("sticks")));

        TERRACOTTA_STAIRS_SHAPED = createRecipeFromConfig("terracotta_stairs", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_STAIRS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
        TERRACOTTA_SLAB_SHAPED = createRecipeFromConfig("terracotta_slab", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_STAIRS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
        TERRACOTTA_BRICKS_SHAPED = createRecipeFromConfig("terracotta_bricks", AtbywRecipeConfigs.BRICKS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS), newKeyPair("item", getBlockID(Blocks.TERRACOTTA)));
        TERRACOTTA_BRICKS_STAIRS_SHAPED = createRecipeFromConfig("terracotta_bricks_stairs", AtbywRecipeConfigs.STAIRS_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_STAIRS), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
        TERRACOTTA_BRICKS_SLAB_SHAPED = createRecipeFromConfig("terracotta_bricks_slab", AtbywRecipeConfigs.SLAB_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_SLAB), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));
        TERRACOTTA_BRICKS_WALL_SHAPED = createRecipeFromConfig("terracotta_bricks_wall", AtbywRecipeConfigs.WALL_1, getBlockID(AtbywBlocks.TERRACOTTA_BRICKS_WALL), newKeyPair("item", getBlockID(AtbywBlocks.TERRACOTTA_BRICKS)));

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

        CONCRETE_STAIRS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "concrete_stairs", AtbywRecipeConfigs.STAIRS_1, new Pair<>(nameSpace, "concrete_stairs"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
        CONCRETE_SLAB_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "concrete_slab", AtbywRecipeConfigs.SLAB_1, new Pair<>(nameSpace, "concrete_slab"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
        CINDER_BLOCKS_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "cinder_blocks", AtbywRecipeConfigs.BRICKS_1, new Pair<>(nameSpace, "cinder_blocks"), newKeyQuadruplet("item", mcNameSpace, "concrete", true));
        CINDER_BLOCKS_WALL_COLORS_SHAPED = createMultiRecipesFromConfig(COLOR_NAMES, "cinder_blocks_wall", AtbywRecipeConfigs.WALL_1, new Pair<>(nameSpace, "cinder_blocks_wall"), newKeyQuadruplet("item", nameSpace, "cinder_blocks", true));

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

        putRecipe(new Identifier("terracotta_stairs_from_stick_tag"), TERRACOTTA_STAIRS_SHAPED, map);
        putRecipe(new Identifier("terracotta_slab_from_stick_tag"), TERRACOTTA_SLAB_SHAPED, map);
        putRecipe(new Identifier("terracotta_bricks_from_stick_tag"), TERRACOTTA_BRICKS_SHAPED, map);
        putRecipe(new Identifier("terracotta_bricks_stairs_from_stick_tag"), TERRACOTTA_BRICKS_STAIRS_SHAPED, map);
        putRecipe(new Identifier("terracotta_bricks_slab_from_stick_tag"), TERRACOTTA_BRICKS_SLAB_SHAPED, map);
        putRecipe(new Identifier("terracotta_bricks_wall_from_stick_tag"), TERRACOTTA_BRICKS_WALL_SHAPED, map);

        //Colored recipes
        for (int i = 0; i < COLOR_NAMES.length; i++){
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_stairs_from_dye"), TERRACOTTA_STAIRS_COLORS_DYING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_slab_from_dye"), TERRACOTTA_SLAB_COLORS_DYING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_from_dye"), TERRACOTTA_BRICKS_COLORS_DYING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_stairs_from_dye"), TERRACOTTA_BRICKS_STAIRS_COLORS_DYING[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_slab_from_dye"), TERRACOTTA_BRICKS_SLAB_COLORS_DYING[i], map);

            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_stairs"), TERRACOTTA_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_slab"), TERRACOTTA_SLAB_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks"), TERRACOTTA_BRICKS_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_stairs"), TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_terracotta_bricks_slab"), TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED[i], map);

            putRecipe(AtbywID(COLOR_NAMES[i] + "_concrete_stairs"), CONCRETE_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_concrete_slab"), CONCRETE_SLAB_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_cinder_blocks"), CINDER_BLOCKS_COLORS_SHAPED[i], map);
            putRecipe(AtbywID(COLOR_NAMES[i] + "_cinder_blocks_wall"), CINDER_BLOCKS_WALL_COLORS_SHAPED[i], map);
        }

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
