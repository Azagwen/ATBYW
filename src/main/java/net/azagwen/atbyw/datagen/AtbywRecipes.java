package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.azagwen.atbyw.blocks.statues.StatueRegistry;
import net.azagwen.atbyw.items.AtbywItems;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Map;

import static net.azagwen.atbyw.main.AtbywMain.*;
import static net.azagwen.atbyw.main.AtbywMain.newID;

public class AtbywRecipes {
    private static final Item[] DYES = AtbywUtils.DYES;
    private static final String[] COLORS = AtbywUtils.COLOR_NAMES;

    private static Key<Character, String> newKey(Character c, String type) {
        return new Key<>(c, type);
    }

    public static JsonObject createShapedRecipeJson(String group, ArrayList<String> pattern, ArrayList<Key<Character, String>> keys, ArrayList<Identifier> keyItems, Identifier output, int count) {
        JsonObject json = new JsonObject();

        json.addProperty("type", "minecraft:crafting_shaped");

        if (!group.equals(""))
            json.addProperty("group", group);

        //Pattern
        JsonArray patternArray = new JsonArray();
        if (pattern.size() == 3) {
            patternArray.add(pattern.get(0));
            patternArray.add(pattern.get(1));
            patternArray.add(pattern.get(2));
        } else if (pattern.size() == 2) {
            patternArray.add(pattern.get(0));
            patternArray.add(pattern.get(1));
        } else {
            patternArray.add(pattern.get(0));
        }
        json.add("pattern", patternArray);

        //Keys
        JsonObject individualKey;
        JsonObject keyList = new JsonObject();
        for (int i = 0; i < keys.size(); ++i) {
            //Key type + key item/tag
            individualKey = new JsonObject();
            individualKey.addProperty(keys.get(i).getType(), keyItems.get(i).toString());
            //Key character
            keyList.add(keys.get(i).getCharacter() + "", individualKey);
        }
        json.add("key", keyList);

        //Result
        JsonObject result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);
        json.add("result", result);

        return json;
    }

    public static JsonObject createStonecutterRecipe(Item ingredient, Item output, int count) {
        JsonObject json = new JsonObject();

        json.addProperty("type", "minecraft:stonecutting");

        //Ingredient
        JsonObject ingredientObj = new JsonObject();
        ingredientObj.addProperty("item", getItemId(ingredient).toString());
        json.add("ingredient", ingredientObj);

        //Result
        json.addProperty("result", getItemId(output).toString());
        json.addProperty("count", count);

        return json;
    }

    private static Identifier getItemId(Item item) {
        return Registry.ITEM.getId(item);
    }

    private static Identifier[] getItemsId(Item... items) {
        Identifier[] identifiers = new Identifier[items.length];
        for (int i = 0; i < items.length; i++) {
            identifiers[i] = getItemId(items[i]);
        }

        return identifiers;
    }

    private static JsonObject createRecipeFromConfig1(String group, AtbywRecipeConfig config, Item result, Item... ingredient) {
        return createShapedRecipeJson(
                group,
                config.getPattern(),
                config.getKeys(),
                Lists.newArrayList(getItemsId(ingredient)),
                getItemId(result),
                config.getCount()
        );
    }

    private static JsonObject createEssenceRecipe(String result, Item item1, Item item2) {
        return createShapedRecipeJson(
                "essences",
                Lists.newArrayList("X#X", "#O#", "X#X"),
                Lists.newArrayList(
                        newKey('#', "item"),
                        newKey('X', "item"),
                        newKey('O', "item")
                ),
                Lists.newArrayList(getItemId(item1), getItemId(item2), getItemId(Items.GLASS_BOTTLE)),
                newID(result),
                1
        );
    }

    private static Item getColoredItemFromID(String namespace, String path, int colorIndex) {
        return Registry.ITEM.get(new Identifier(namespace, COLORS[colorIndex] + "_" + path));
    }

    private static JsonObject[] createColoredRecipesFromConfig(AtbywRecipeConfig config, String group, String[] result, String[]... ingredient) {
        JsonObject[] obj = new JsonObject[COLORS.length];

        for (int i = 0; i < COLORS.length; i++) {
            obj[i] = createRecipeFromConfig1(group, config, getColoredItemFromID(result[0], result[1], i), getColoredItemFromID(ingredient[i][0], ingredient[i][1], i));
        }

        return obj;
    }

    private static JsonObject[] createStonecutterColoredRecipes(String[] ingredient, String[] result) {
        JsonObject[] obj = new JsonObject[COLORS.length];

        for (int i = 0; i < COLORS.length; i++) {
            obj[i] = createStonecutterRecipe(getColoredItemFromID(ingredient[0], ingredient[1], i), getColoredItemFromID(result[0], result[1], i), 1);
        }

        return obj;
    }

    //TODO: add stonecutting recipes for Terracotta and Concrete.

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

    public static JsonObject[] CONCRETE_STAIRS_COLORS_STONECUTTING;
    public static JsonObject[] CONCRETE_SLAB_COLORS_STONECUTTING;
    public static JsonObject[] CINDER_BLOCKS_COLORS_STONECUTTING;
    public static JsonObject[] CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CONCRETE;
    public static JsonObject[] CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CINDER_BLOCKS;

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

    public static JsonObject GRANITE_COLUMN_STONECUTTING;
    public static JsonObject DIORITE_COLUMN_STONECUTTING;
    public static JsonObject ANDESITE_COLUMN_STONECUTTING;
    public static JsonObject GRANITE_COLUMN_FROM_POLISHED_STONECUTTING;
    public static JsonObject DIORITE_COLUMN_FROM_POLISHED_STONECUTTING;
    public static JsonObject ANDESITE_COLUMN_FROM_POLISHED_STONECUTTING;
    public static JsonObject SANDSTONE_COLUMN_STONECUTTING;
    public static JsonObject SANDSTONE_COLUMN_FROM_CUT_STONECUTTING;
    public static JsonObject CHISELED_SANDSTONE_COLUMN_STONECUTTING;
    public static JsonObject RED_SANDSTONE_COLUMN_STONECUTTING;
    public static JsonObject RED_SANDSTONE_COLUMN_FROM_CUT_STONECUTTING;
    public static JsonObject CHISELED_RED_SANDSTONE_COLUMN_STONECUTTING;
    public static JsonObject PURPUR_COLUMN_STONECUTTING;
    public static JsonObject STONE_BRICKS_COLUMN_STONECUTTING;
    public static JsonObject MOSSY_STONE_BRICKS_COLUMN_STONECUTTING;
    public static JsonObject CRACKED_STONE_BRICKS_COLUMN_STONECUTTING;
    public static JsonObject NETHER_BRICKS_COLUMN_STONECUTTING;
    public static JsonObject QUARTZ_COLUMN_STONECUTTING;
    public static JsonObject PRISMARINE_COLUMN_STONECUTTING;
    public static JsonObject BLACKSTONE_COLUMN_STONECUTTING;

    public static JsonObject PURPUR_TILES;
    public static JsonObject SMOOTH_PURPUR_BLOCK;
    public static JsonObject CHISELED_PURPUR_BLOCK;
    public static JsonObject PURPUR_TILES_STAIRS;
    public static JsonObject SMOOTH_PURPUR_STAIRS;
    public static JsonObject PURPUR_TILES_SLAB;
    public static JsonObject SMOOTH_PURPUR_SLAB;

    public static JsonObject PURPUR_TILES_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_BLOCK_STONECUTTING;
    public static JsonObject CHISELED_PURPUR_BLOCK_STONECUTTING;
    public static JsonObject PURPUR_TILES_STAIRS_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_STAIRS_STONECUTTING;
    public static JsonObject PURPUR_TILES_SLAB_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_SLAB_STONECUTTING;
    public static JsonObject PURPUR_TILES_STAIRS_FROM_PURPUR_TILES_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_STAIRS_FROM_SMOOTH_PURPUR_STONECUTTING;
    public static JsonObject PURPUR_TILES_SLAB_FROM_PURPUR_TILES_STONECUTTING;
    public static JsonObject SMOOTH_PURPUR_SLAB_FROM_SMOOTH_PURPUR_STONECUTTING;

    public static void init() {
        TERRACOTTA_STAIRS_SHAPED = createRecipeFromConfig1("terracotta_stairs", AtbywRecipeConfigs.STAIRS_1, AtbywBlocks.TERRACOTTA_STAIRS.asItem(), Blocks.TERRACOTTA.asItem());
        TERRACOTTA_SLAB_SHAPED = createRecipeFromConfig1("terracotta_slab", AtbywRecipeConfigs.SLAB_1, AtbywBlocks.TERRACOTTA_STAIRS.asItem(), Blocks.TERRACOTTA.asItem());
        TERRACOTTA_BRICKS_SHAPED = createRecipeFromConfig1("terracotta_bricks", AtbywRecipeConfigs.BRICKS_1, AtbywBlocks.TERRACOTTA_BRICKS.asItem(), Blocks.TERRACOTTA.asItem());
        TERRACOTTA_BRICKS_STAIRS_SHAPED = createRecipeFromConfig1("terracotta_bricks_stairs", AtbywRecipeConfigs.STAIRS_1, AtbywBlocks.TERRACOTTA_BRICKS_STAIRS.asItem(), AtbywBlocks.TERRACOTTA_BRICKS.asItem());
        TERRACOTTA_BRICKS_SLAB_SHAPED = createRecipeFromConfig1("terracotta_bricks_slab", AtbywRecipeConfigs.SLAB_1, AtbywBlocks.TERRACOTTA_BRICKS_SLAB.asItem(), AtbywBlocks.TERRACOTTA_BRICKS.asItem());
        TERRACOTTA_BRICKS_WALL_SHAPED = createRecipeFromConfig1("terracotta_bricks_wall", AtbywRecipeConfigs.WALL_1, AtbywBlocks.TERRACOTTA_BRICKS_WALL.asItem(), AtbywBlocks.TERRACOTTA_BRICKS.asItem());

        TERRACOTTA_STAIRS_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.STAIRS_1, "terracotta_stairs", new String[] {"minecraft", "terracotta"}, new String[] {nameSpace, "terracotta_stairs"});
        TERRACOTTA_SLAB_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.SLAB_1, "terracotta_slab", new String[] {"minecraft", "terracotta"}, new String[] {nameSpace, "terracotta_slab"});
        TERRACOTTA_BRICKS_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.BRICKS_1, "terracotta_bricks", new String[] {"minecraft", "terracotta"}, new String[] {nameSpace, "terracotta_bricks"});
        TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.STAIRS_1, "terracotta_bricks_stairs", new String[] {nameSpace, "terracotta_bricks"}, new String[] {nameSpace, "terracotta_bricks_stairs"});
        TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.SLAB_1, "terracotta_bricks_slab", new String[] {nameSpace, "terracotta_bricks"}, new String[] {nameSpace, "terracotta_bricks_slab"});
        TERRACOTTA_BRICKS_WALL_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.WALL_1, "terracotta_bricks_wall", new String[] {nameSpace, "terracotta_bricks"}, new String[] {nameSpace, "terracotta_bricks_wall"});

        TERRACOTTA_STAIRS_COLORS_DYING = createColoredRecipesFromConfig(AtbywRecipeConfigs.DYING_2, "terracotta_stairs_dying", new String[] {nameSpace, "terracotta_stairs"}, new String[] {"minecraft", "dye"}, new String[] {"minecraft", "dye"});
        TERRACOTTA_SLAB_COLORS_DYING = createColoredRecipesFromConfig(AtbywRecipeConfigs.DYING_2, "terracotta_slabs_dying", new String[] {nameSpace, "terracotta_slab"}, new String[] {"minecraft", "dye"}, new String[] {"minecraft", "dye"});
        TERRACOTTA_BRICKS_COLORS_DYING = createColoredRecipesFromConfig(AtbywRecipeConfigs.DYING_2, "terracotta_bricks_dying", new String[] {nameSpace, "terracotta_bricks"}, new String[] {nameSpace, "terracotta_bricks"}, new String[] {"minecraft", "dye"});
        TERRACOTTA_BRICKS_STAIRS_COLORS_DYING = createColoredRecipesFromConfig(AtbywRecipeConfigs.DYING_2, "terracotta_bricks_stairs_dying", new String[] {nameSpace, "terracotta_bricks_stairs"}, new String[] {nameSpace, "terracotta_bricks_stairs"}, new String[] {"minecraft", "dye"});
        TERRACOTTA_BRICKS_SLAB_COLORS_DYING = createColoredRecipesFromConfig(AtbywRecipeConfigs.DYING_2, "terracotta_bricks_slabs_dying", new String[] {nameSpace, "terracotta_bricks_slab"}, new String[] {nameSpace, "terracotta_bricks_slab"}, new String[] {"minecraft", "dye"});
        TERRACOTTA_BRICKS_WALL_COLORS_DYING = createColoredRecipesFromConfig(AtbywRecipeConfigs.DYING_2, "terracotta_bricks_walls_dying", new String[] {nameSpace, "terracotta_bricks_wall"}, new String[] {nameSpace, "terracotta_bricks_wall"}, new String[] {"minecraft", "dye"});

        CONCRETE_STAIRS_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.STAIRS_1, "concrete_stairs", new String[] {"minecraft", "concrete"}, new String[] {nameSpace, "concrete_stairs"});
        CONCRETE_SLAB_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.SLAB_1, "concrete_slab", new String[] {"minecraft", "concrete"}, new String[] {nameSpace, "concrete_slab"});
        CINDER_BLOCKS_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.BRICKS_1, "cinder_blocks", new String[] {"minecraft", "concrete"}, new String[] {nameSpace, "cinder_blocks"});
        CINDER_BLOCKS_WALL_COLORS_SHAPED = createColoredRecipesFromConfig(AtbywRecipeConfigs.WALL_1, "cinder_blocks_wall", new String[] {nameSpace, "cinder_blocks"}, new String[] {nameSpace, "cinder_blocks_wall"});

        CONCRETE_STAIRS_COLORS_STONECUTTING = createStonecutterColoredRecipes(new String[] {"minecraft", "concrete"}, new String[] {nameSpace, "concrete_stairs"});
        CONCRETE_SLAB_COLORS_STONECUTTING = createStonecutterColoredRecipes(new String[] {"minecraft", "concrete"}, new String[] {nameSpace, "concrete_slab"});
        CINDER_BLOCKS_COLORS_STONECUTTING = createStonecutterColoredRecipes(new String[] {"minecraft", "concrete"}, new String[] {nameSpace, "cinder_blocks"});
        CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CONCRETE = createStonecutterColoredRecipes(new String[] {"minecraft", "concrete"}, new String[] {nameSpace, "cinder_blocks_wall"});
        CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CINDER_BLOCKS = createStonecutterColoredRecipes(new String[] {nameSpace, "cinder_blocks"}, new String[] {nameSpace, "cinder_blocks_wall"});

        BEE_ESSENCE = createEssenceRecipe("bee_essence", Items.HONEYCOMB, Items.HONEY_BOTTLE);
        SHULKER_ESSENCE = createEssenceRecipe("shulker_essence", Items.SHULKER_SHELL, Items.SHULKER_SHELL);
        CAT_ESSENCE = createEssenceRecipe("wolf_essence", Items.STRING, Items.STRING);
        CHICKEN_ESSENCE = createEssenceRecipe("chicken_essence", Items.CHICKEN, Items.FEATHER);
        RABBIT_ESSENCE = createEssenceRecipe("rabbit_essence", Items.RABBIT, Items.RABBIT_HIDE);
        FOX_ESSENCE = createEssenceRecipe("fox_essence", Items.SWEET_BERRIES, Items.SWEET_BERRIES);
        COD_ESSENCE = createEssenceRecipe("cod_essence", Items.COD, Items.BONE_MEAL);
        SALMON_ESSENCE = createEssenceRecipe("salmon_essence", Items.SALMON, Items.BONE_MEAL);
        PUFFER_FISH_ESSENCE = createEssenceRecipe("puffer_fish_essence", Items.PUFFERFISH, Items.BONE_MEAL);
        SLIME_ESSENCE = createEssenceRecipe("slime_essence", Items.SLIME_BALL, Items.SLIME_BALL);
        MAGMA_CUBE_ESSENCE = createEssenceRecipe("magma_cube_essence", Items.MAGMA_CREAM, Items.MAGMA_CREAM);

        BEE_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.BEE_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.BEE_ESSENCE);
        SILVERFISH_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.SILVERFISH_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.SILVERFISH_ESSENCE);
        ENDERMITE_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.ENDERMITE_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.ENDERMITE_ESSENCE);
        SHULKER_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.SHULKER_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.SHULKER_ESSENCE);
        CAT_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.CAT_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.CAT_ESSENCE);
        WOLF_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.WOLF_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.WOLF_ESSENCE);
        CHICKEN_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.CHICKEN_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.CHICKEN_ESSENCE);
        RABBIT_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.RABBIT_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.RABBIT_ESSENCE);
        FOX_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.FOX_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.FOX_ESSENCE);
        COD_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.COD_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.COD_ESSENCE);
        SALMON_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.SALMON_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.SALMON_ESSENCE);
        PUFFER_FISH_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.PUFFER_FISH_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.PUFFER_FISH_ESSENCE);
        SLIME_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.SLIME_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.SLIME_ESSENCE);
        MAGMA_CUBE_STATUE = createRecipeFromConfig1("statues", AtbywRecipeConfigs.DYING_2.overrideCount(1), StatueRegistry.MAGMA_CUBE_STATUE.asItem(), Blocks.STONE.asItem(), AtbywItems.MAGMA_CUBE_ESSENCE);

        GRANITE_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.GRANITE_COLUMN.asItem(), Blocks.GRANITE.asItem());
        DIORITE_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.DIORITE_COLUMN.asItem(), Blocks.DIORITE.asItem());
        ANDESITE_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.ANDESITE_COLUMN.asItem(), Blocks.ANDESITE.asItem());
        GRANITE_COLUMN_FROM_POLISHED = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.GRANITE_COLUMN.asItem(), Blocks.POLISHED_GRANITE.asItem());
        DIORITE_COLUMN_FROM_POLISHED = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.DIORITE_COLUMN.asItem(), Blocks.POLISHED_DIORITE.asItem());
        ANDESITE_COLUMN_FROM_POLISHED = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.ANDESITE_COLUMN.asItem(), Blocks.POLISHED_ANDESITE.asItem());
        SANDSTONE_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.SANDSTONE_COLUMN.asItem(), Blocks.SANDSTONE.asItem());
        SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.SANDSTONE_COLUMN.asItem(), Blocks.SMOOTH_SANDSTONE.asItem());
        CHISELED_SANDSTONE_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.CHISELED_SANDSTONE_COLUMN.asItem(), Blocks.CHISELED_SANDSTONE.asItem());
        RED_SANDSTONE_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.RED_SANDSTONE_COLUMN.asItem(), Blocks.RED_SANDSTONE.asItem());
        RED_SANDSTONE_COLUMN_FROM_CUT = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.RED_SANDSTONE_COLUMN.asItem(), Blocks.SMOOTH_RED_SANDSTONE.asItem());
        CHISELED_RED_SANDSTONE_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN.asItem(), Blocks.CHISELED_RED_SANDSTONE.asItem());
        PURPUR_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.PURPUR_COLUMN.asItem(), Blocks.PURPUR_BLOCK.asItem());
        STONE_BRICKS_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.STONE_BRICKS_COLUMN.asItem(), Blocks.STONE_BRICKS.asItem());
        MOSSY_STONE_BRICKS_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN.asItem(), Blocks.MOSSY_STONE_BRICKS.asItem());
        CRACKED_STONE_BRICKS_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN.asItem(), Blocks.CRACKED_STONE_BRICKS.asItem());
        NETHER_BRICKS_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.NETHER_BRICKS_COLUMN.asItem(), Blocks.NETHER_BRICKS.asItem());
        QUARTZ_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.QUARTZ_COLUMN.asItem(), Blocks.QUARTZ_BLOCK.asItem());
        PRISMARINE_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.PRISMARINE_COLUMN.asItem(), Blocks.PRISMARINE_BRICKS.asItem());
        BLACKSTONE_COLUMN = createRecipeFromConfig1("columns", AtbywRecipeConfigs.COLUMN_1, AtbywBlocks.BLACKSTONE_COLUMN.asItem(), Blocks.BLACKSTONE.asItem());

        GRANITE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.GRANITE.asItem(), AtbywBlocks.GRANITE_COLUMN.asItem(), 1);
        DIORITE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.DIORITE.asItem(), AtbywBlocks.DIORITE_COLUMN.asItem(), 1);
        ANDESITE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.ANDESITE.asItem(), AtbywBlocks.ANDESITE_COLUMN.asItem(), 1);
        GRANITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(Blocks.POLISHED_GRANITE.asItem(), AtbywBlocks.GRANITE_COLUMN.asItem(), 1);
        DIORITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(Blocks.POLISHED_DIORITE.asItem(), AtbywBlocks.DIORITE_COLUMN.asItem(), 1);
        ANDESITE_COLUMN_FROM_POLISHED_STONECUTTING = createStonecutterRecipe(Blocks.POLISHED_ANDESITE.asItem(), AtbywBlocks.ANDESITE_COLUMN.asItem(), 1);
        SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.SANDSTONE.asItem(), AtbywBlocks.SANDSTONE_COLUMN.asItem(), 1);
        SANDSTONE_COLUMN_FROM_CUT_STONECUTTING = createStonecutterRecipe(Blocks.CUT_SANDSTONE.asItem(), AtbywBlocks.SANDSTONE_COLUMN.asItem(), 1);
        CHISELED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.CHISELED_SANDSTONE.asItem(), AtbywBlocks.CHISELED_SANDSTONE_COLUMN.asItem(), 1);
        RED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.RED_SANDSTONE.asItem(), AtbywBlocks.RED_SANDSTONE_COLUMN.asItem(), 1);
        RED_SANDSTONE_COLUMN_FROM_CUT_STONECUTTING = createStonecutterRecipe(Blocks.CUT_RED_SANDSTONE.asItem(), AtbywBlocks.RED_SANDSTONE_COLUMN.asItem(), 1);
        CHISELED_RED_SANDSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.CHISELED_RED_SANDSTONE.asItem(), AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN.asItem(), 1);
        PURPUR_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.PURPUR_COLUMN.asItem(), 1);
        STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.STONE_BRICKS.asItem(), AtbywBlocks.STONE_BRICKS_COLUMN.asItem(), 1);
        MOSSY_STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.MOSSY_STONE_BRICKS.asItem(), AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN.asItem(), 1);
        CRACKED_STONE_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.CRACKED_STONE_BRICKS.asItem(), AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN.asItem(), 1);
        NETHER_BRICKS_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.NETHER_BRICKS.asItem(), AtbywBlocks.NETHER_BRICKS_COLUMN.asItem(), 1);
        QUARTZ_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.QUARTZ_BLOCK.asItem(), AtbywBlocks.QUARTZ_COLUMN.asItem(), 1);
        PRISMARINE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.PRISMARINE_BRICKS.asItem(), AtbywBlocks.PRISMARINE_COLUMN.asItem(), 1);
        BLACKSTONE_COLUMN_STONECUTTING = createStonecutterRecipe(Blocks.BLACKSTONE.asItem(), AtbywBlocks.BLACKSTONE_COLUMN.asItem(), 1);

        PURPUR_TILES = createRecipeFromConfig1("", AtbywRecipeConfigs.BRICKS_1, AtbywBlocks.SMOOTH_PURPUR_BLOCK.asItem(), Blocks.PURPUR_BLOCK.asItem());
        SMOOTH_PURPUR_BLOCK = createRecipeFromConfig1("", AtbywRecipeConfigs.BRICKS_1, AtbywBlocks.PURPUR_TILES.asItem(), Blocks.PURPUR_PILLAR.asItem());
        CHISELED_PURPUR_BLOCK = createRecipeFromConfig1("", AtbywRecipeConfigs.BRICKS_1, AtbywBlocks.CHISELED_PURPUR_BLOCK.asItem(), Blocks.PURPUR_SLAB.asItem());
        PURPUR_TILES_STAIRS = createRecipeFromConfig1("", AtbywRecipeConfigs.STAIRS_1, AtbywBlocks.PURPUR_TILES_STAIRS.asItem(), AtbywBlocks.PURPUR_TILES.asItem());
        SMOOTH_PURPUR_STAIRS = createRecipeFromConfig1("", AtbywRecipeConfigs.STAIRS_1, AtbywBlocks.SMOOTH_PURPUR_STAIRS.asItem(), AtbywBlocks.SMOOTH_PURPUR_BLOCK.asItem());
        PURPUR_TILES_SLAB = createRecipeFromConfig1("", AtbywRecipeConfigs.SLAB_1, AtbywBlocks.PURPUR_TILES_SLAB.asItem(), AtbywBlocks.PURPUR_TILES.asItem());
        SMOOTH_PURPUR_SLAB = createRecipeFromConfig1("", AtbywRecipeConfigs.SLAB_1, AtbywBlocks.SMOOTH_PURPUR_SLAB.asItem(), AtbywBlocks.SMOOTH_PURPUR_BLOCK.asItem());

        PURPUR_TILES_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_BLOCK.asItem(), 1);
        SMOOTH_PURPUR_BLOCK_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.PURPUR_TILES.asItem(), 1);
        CHISELED_PURPUR_BLOCK_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.CHISELED_PURPUR_BLOCK.asItem(), 1);
        PURPUR_TILES_STAIRS_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.PURPUR_TILES_STAIRS.asItem(), 1);
        SMOOTH_PURPUR_STAIRS_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_STAIRS.asItem(), 1);
        PURPUR_TILES_SLAB_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.PURPUR_TILES_SLAB.asItem(), 2);
        SMOOTH_PURPUR_SLAB_STONECUTTING = createStonecutterRecipe(Blocks.PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_SLAB.asItem(), 2);
        PURPUR_TILES_STAIRS_FROM_PURPUR_TILES_STONECUTTING = createStonecutterRecipe(AtbywBlocks.PURPUR_TILES.asItem(), AtbywBlocks.PURPUR_TILES_STAIRS.asItem(), 1);
        SMOOTH_PURPUR_STAIRS_FROM_SMOOTH_PURPUR_STONECUTTING = createStonecutterRecipe(AtbywBlocks.SMOOTH_PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_STAIRS.asItem(), 1);
        PURPUR_TILES_SLAB_FROM_PURPUR_TILES_STONECUTTING = createStonecutterRecipe(AtbywBlocks.PURPUR_TILES.asItem(), AtbywBlocks.PURPUR_TILES_SLAB.asItem(), 2);
        SMOOTH_PURPUR_SLAB_FROM_SMOOTH_PURPUR_STONECUTTING = createStonecutterRecipe(AtbywBlocks.SMOOTH_PURPUR_BLOCK.asItem(), AtbywBlocks.SMOOTH_PURPUR_SLAB.asItem(), 2);
    }

    private static void putRecipe(Identifier identifier, JsonElement recipe, Map<Identifier, JsonElement> map) {
        if (recipe != null) {
            map.put(identifier, recipe);
        }
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void injectRecipes(Map<Identifier, JsonElement> map) {
        putRecipe(newID("terracotta_stairs"), TERRACOTTA_STAIRS_SHAPED, map);
        putRecipe(newID("terracotta_slab"), TERRACOTTA_SLAB_SHAPED, map);
        putRecipe(newID("terracotta_bricks"), TERRACOTTA_BRICKS_SHAPED, map);
        putRecipe(newID("terracotta_bricks_stairs"), TERRACOTTA_BRICKS_STAIRS_SHAPED, map);
        putRecipe(newID("terracotta_bricks_slab"), TERRACOTTA_BRICKS_SLAB_SHAPED, map);
        putRecipe(newID("terracotta_bricks_wall"), TERRACOTTA_BRICKS_WALL_SHAPED, map);

        for (int i = 0; i < COLORS.length; i++){
            putRecipe(newID(COLORS[i] + "_terracotta_stairs_from_dye"), TERRACOTTA_STAIRS_COLORS_DYING[i], map);
            putRecipe(newID(COLORS[i] + "_terracotta_slab_from_dye"), TERRACOTTA_SLAB_COLORS_DYING[i], map);
            putRecipe(newID(COLORS[i] + "_terracotta_bricks_from_dye"), TERRACOTTA_BRICKS_COLORS_DYING[i], map);
            putRecipe(newID(COLORS[i] + "_terracotta_bricks_stairs_from_dye"), TERRACOTTA_BRICKS_STAIRS_COLORS_DYING[i], map);
            putRecipe(newID(COLORS[i] + "_terracotta_bricks_slab_from_dye"), TERRACOTTA_BRICKS_SLAB_COLORS_DYING[i], map);

            putRecipe(newID(COLORS[i] + "_terracotta_stairs"), TERRACOTTA_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(newID(COLORS[i] + "_terracotta_slab"), TERRACOTTA_SLAB_COLORS_SHAPED[i], map);
            putRecipe(newID(COLORS[i] + "_terracotta_bricks"), TERRACOTTA_BRICKS_COLORS_SHAPED[i], map);
            putRecipe(newID(COLORS[i] + "_terracotta_bricks_stairs"), TERRACOTTA_BRICKS_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(newID(COLORS[i] + "_terracotta_bricks_slab"), TERRACOTTA_BRICKS_SLAB_COLORS_SHAPED[i], map);

            putRecipe(newID(COLORS[i] + "_concrete_stairs"), CONCRETE_STAIRS_COLORS_SHAPED[i], map);
            putRecipe(newID(COLORS[i] + "_concrete_slab"), CONCRETE_SLAB_COLORS_SHAPED[i], map);
            putRecipe(newID(COLORS[i] + "_cinder_blocks"), CINDER_BLOCKS_COLORS_SHAPED[i], map);
            putRecipe(newID(COLORS[i] + "_cinder_blocks_wall"), CINDER_BLOCKS_WALL_COLORS_SHAPED[i], map);

            putRecipe(newID(COLORS[i] + "_concrete_stairs_from_stonecutting"), CONCRETE_STAIRS_COLORS_STONECUTTING[i], map);
            putRecipe(newID(COLORS[i] + "_concrete_slab_from_stonecutting"), CONCRETE_SLAB_COLORS_STONECUTTING[i], map);
            putRecipe(newID(COLORS[i] + "_cinder_blocks_from_stonecutting"), CINDER_BLOCKS_COLORS_STONECUTTING[i], map);
            putRecipe(newID(COLORS[i] + "_cinder_blocks_wall_from_stonecutting_concrete"), CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CONCRETE[i], map);
            putRecipe(newID(COLORS[i] + "_cinder_blocks_wall_from_stonecutting_cinder_blocks"), CINDER_BLOCKS_WALL_COLORS_STONECUTTING_CINDER_BLOCKS[i], map);

        }

        putRecipe(newID("bee_essence"), BEE_ESSENCE, map);
        putRecipe(newID("shulker_essence"), SHULKER_ESSENCE, map);
        putRecipe(newID("cat_essence"), CAT_ESSENCE, map);
        putRecipe(newID("chicken_essence"), CHICKEN_ESSENCE, map);
        putRecipe(newID("rabbit_essence"), RABBIT_ESSENCE, map);
        putRecipe(newID("fox_essence"), FOX_ESSENCE, map);
        putRecipe(newID("cod_essence"), COD_ESSENCE, map);
        putRecipe(newID("salmon_essence"), SALMON_ESSENCE, map);
        putRecipe(newID("puffer_fish_essence"), PUFFER_FISH_ESSENCE, map);
        putRecipe(newID("magma_cube_essence"), MAGMA_CUBE_ESSENCE, map);
        putRecipe(newID("slime_essence"), SLIME_ESSENCE, map);

        putRecipe(newID("bee_statue"), BEE_STATUE, map);
        putRecipe(newID("silverfish_statue"), SILVERFISH_STATUE, map);
        putRecipe(newID("endermite_statue"), ENDERMITE_STATUE, map);
        putRecipe(newID("shulker_statue"), SHULKER_STATUE, map);
        putRecipe(newID("cat_statue"), CAT_STATUE, map);
        putRecipe(newID("chicken_statue"), CHICKEN_STATUE, map);
        putRecipe(newID("rabbit_statue"), RABBIT_STATUE, map);
        putRecipe(newID("fox_statue"), FOX_STATUE, map);
        putRecipe(newID("cod_statue"), COD_STATUE, map);
        putRecipe(newID("salmon_statue"), SALMON_STATUE, map);
        putRecipe(newID("puffer_fish_statue"), PUFFER_FISH_STATUE, map);
        putRecipe(newID("magma_cube_statue"), MAGMA_CUBE_STATUE, map);
        putRecipe(newID("slime_statue"), SLIME_STATUE, map);

        putRecipe(newID("granite_column"), GRANITE_COLUMN, map);
        putRecipe(newID("diorite_column"), DIORITE_COLUMN, map);
        putRecipe(newID("andesite_column"), ANDESITE_COLUMN, map);
        putRecipe(newID("granite_column_from_polished"), GRANITE_COLUMN_FROM_POLISHED, map);
        putRecipe(newID("diorite_column_from_polished"), DIORITE_COLUMN_FROM_POLISHED, map);
        putRecipe(newID("andesite_column_from_polished"), ANDESITE_COLUMN_FROM_POLISHED, map);
        putRecipe(newID("sandstone_column"), SANDSTONE_COLUMN, map);
        putRecipe(newID("sandstone_column_from_cut"), SANDSTONE_COLUMN_FROM_CUT, map);
        putRecipe(newID("chiseled_sandstone_column"), CHISELED_SANDSTONE_COLUMN, map);
        putRecipe(newID("red_sandstone_column"), RED_SANDSTONE_COLUMN, map);
        putRecipe(newID("red_sandstone_column_from_cut"), RED_SANDSTONE_COLUMN_FROM_CUT, map);
        putRecipe(newID("chiseled_red_sandstone_column"), CHISELED_RED_SANDSTONE_COLUMN, map);
        putRecipe(newID("purpur_column"), PURPUR_COLUMN, map);
        putRecipe(newID("stone_bricks_column"), STONE_BRICKS_COLUMN, map);
        putRecipe(newID("mossy_stone_bricks_column"), MOSSY_STONE_BRICKS_COLUMN, map);
        putRecipe(newID("cracked_stone_bricks_column"), CRACKED_STONE_BRICKS_COLUMN, map);
        putRecipe(newID("nether_bricks_column"), NETHER_BRICKS_COLUMN, map);
        putRecipe(newID("quartz_column"), QUARTZ_COLUMN, map);
        putRecipe(newID("prismarine_column"), PRISMARINE_COLUMN, map);
        putRecipe(newID("blackstone_column"), BLACKSTONE_COLUMN, map);

        putRecipe(newID("granite_column_stonecutting"), GRANITE_COLUMN_STONECUTTING, map);
        putRecipe(newID("diorite_column_stonecutting"), DIORITE_COLUMN_STONECUTTING, map);
        putRecipe(newID("andesite_column_stonecutting"), ANDESITE_COLUMN_STONECUTTING, map);
        putRecipe(newID("granite_column_from_polished_stonecutting"), GRANITE_COLUMN_FROM_POLISHED_STONECUTTING, map);
        putRecipe(newID("diorite_column_from_polished_stonecutting"), DIORITE_COLUMN_FROM_POLISHED_STONECUTTING, map);
        putRecipe(newID("andesite_column_from_polished_stonecutting"), ANDESITE_COLUMN_FROM_POLISHED_STONECUTTING, map);
        putRecipe(newID("sandstone_column_stonecutting"), SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(newID("sandstone_column_from_cut_stonecutting"), SANDSTONE_COLUMN_FROM_CUT_STONECUTTING, map);
        putRecipe(newID("chiseled_sandstone_column_stonecutting"), CHISELED_SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(newID("red_sandstone_column_stonecutting"), RED_SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(newID("red_sandstone_column_from_cut_stonecutting"), RED_SANDSTONE_COLUMN_FROM_CUT_STONECUTTING, map);
        putRecipe(newID("chiseled_red_sandstone_column_stonecutting"), CHISELED_RED_SANDSTONE_COLUMN_STONECUTTING, map);
        putRecipe(newID("purpur_column_stonecutting"), PURPUR_COLUMN_STONECUTTING, map);
        putRecipe(newID("stone_bricks_column_stonecutting"), STONE_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(newID("mossy_stone_bricks_column_stonecutting"), MOSSY_STONE_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(newID("cracked_stone_bricks_column_stonecutting"), CRACKED_STONE_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(newID("nether_bricks_column_stonecutting"), NETHER_BRICKS_COLUMN_STONECUTTING, map);
        putRecipe(newID("quartz_column_stonecutting"), QUARTZ_COLUMN_STONECUTTING, map);
        putRecipe(newID("prismarine_column_stonecutting"), PRISMARINE_COLUMN_STONECUTTING, map);
        putRecipe(newID("blackstone_column_stonecutting"), BLACKSTONE_COLUMN_STONECUTTING, map);

        putRecipe(newID("purpur_tiles"), PURPUR_TILES, map);
        putRecipe(newID("smooth_purpur_block"), SMOOTH_PURPUR_BLOCK, map);
        putRecipe(newID("chiseled_purpur_block"), CHISELED_PURPUR_BLOCK, map);
        putRecipe(newID("purpur_tiles_stairs"), PURPUR_TILES_STAIRS, map);
        putRecipe(newID("smooth_purpur_stairs"), SMOOTH_PURPUR_STAIRS, map);
        putRecipe(newID("purpur_tiles_slab"), PURPUR_TILES_SLAB, map);
        putRecipe(newID("smooth_purpur_slab"), SMOOTH_PURPUR_SLAB, map);
        putRecipe(newID("purpur_tiles_stonecutting"), PURPUR_TILES_STONECUTTING, map);
        putRecipe(newID("smooth_purpur_block_stonecutting"), SMOOTH_PURPUR_BLOCK_STONECUTTING, map);
        putRecipe(newID("chiseled_purpur_block_stonecutting"), CHISELED_PURPUR_BLOCK_STONECUTTING, map);
        putRecipe(newID("purpur_tiles_stairs_stonecutting"), PURPUR_TILES_STAIRS_STONECUTTING, map);
        putRecipe(newID("smooth_purpur_stairs_stonecutting"), SMOOTH_PURPUR_STAIRS_STONECUTTING, map);
        putRecipe(newID("purpur_tiles_slab_stonecutting"), PURPUR_TILES_SLAB_STONECUTTING, map);
        putRecipe(newID("smooth_purpur_slab_stonecutting"), SMOOTH_PURPUR_SLAB_STONECUTTING, map);
        putRecipe(newID("purpur_tiles_stairs_from_purpur_tiles_stonecutting"), PURPUR_TILES_STAIRS_FROM_PURPUR_TILES_STONECUTTING, map);
        putRecipe(newID("smooth_purpur_stairs_from_smooth_purpur_stonecutting"), SMOOTH_PURPUR_STAIRS_FROM_SMOOTH_PURPUR_STONECUTTING, map);
        putRecipe(newID("purpur_tiles_slab_from_purpur_tiles_stonecutting"), PURPUR_TILES_SLAB_FROM_PURPUR_TILES_STONECUTTING, map);
        putRecipe(newID("smooth_purpur_slab_from_smooth_purpur_stonecutting"), SMOOTH_PURPUR_SLAB_FROM_SMOOTH_PURPUR_STONECUTTING, map);

    }
}
