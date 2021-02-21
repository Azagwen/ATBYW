package net.azagwen.atbyw.main;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.items.AtbywItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Map;

import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywRecipes {

    public static JsonObject createShapedRecipeJson(ArrayList<Identifier> items, ArrayList<String> pattern, ArrayList<String> type, ArrayList<Character> keys, Identifier output) {
        JsonObject json = new JsonObject();

        json.addProperty("type", "minecraft:crafting_shaped");

        //Pattern
        JsonArray patternArray = new JsonArray();
        patternArray.add(pattern.get(0));
        patternArray.add(pattern.get(1));
        patternArray.add(pattern.get(2));
        json.add("pattern", patternArray);

        //Keys
        JsonObject individualKey;
        JsonObject keyList = new JsonObject();
        for (int i = 0; i < keys.size(); ++i) {
            individualKey = new JsonObject();
            individualKey.addProperty(type.get(i), items.get(i).toString());
            keyList.add(keys.get(i) + "", individualKey);
        }
        json.add("key", keyList);

        //Result
        JsonObject result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", 1);
        json.add("result", result);

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

    private static JsonObject createEssenceRecipe(String result, Item... items) {
        ArrayList<Identifier> ingredients = Lists.newArrayList();
        ingredients.addAll(Lists.newArrayList(getItemsId(items)));
        ingredients.add(getItemId(Items.GLASS_BOTTLE));

        return createShapedRecipeJson(
                ingredients,
                Lists.newArrayList("X#X", "#O#", "X#X"),
                Lists.newArrayList("item", "item", "item"),
                Lists.newArrayList('#', 'X', 'O'),
                newID(result)
        );
    }

    private static JsonObject createStatueRecipe(String result, Item essence) {

        return createShapedRecipeJson(
                Lists.newArrayList(getItemId(Blocks.STONE.asItem()), getItemId(essence)),
                Lists.newArrayList("###", "#X#", "###"),
                Lists.newArrayList("item", "item"),
                Lists.newArrayList('#', 'X'),
                newID(result)
        );
    }

    public static JsonObject BEE_ESSENCE_RECIPE = null;
    public static JsonObject SHULKER_ESSENCE_RECIPE = null;
    public static JsonObject CAT_ESSENCE_RECIPE = null;
    public static JsonObject CHICKEN_ESSENCE_RECIPE = null;
    public static JsonObject RABBIT_ESSENCE_RECIPE = null;
    public static JsonObject FOX_ESSENCE_RECIPE = null;
    public static JsonObject COD_ESSENCE_RECIPE = null;
    public static JsonObject SALMON_ESSENCE_RECIPE = null;
    public static JsonObject PUFFER_FISH_ESSENCE_RECIPE = null;
    public static JsonObject MAGMA_CUBE_ESSENCE_RECIPE = null;
    public static JsonObject SLIME_ESSENCE_RECIPE = null;

    public static JsonObject BEE_STATUE_RECIPE = null;
    public static JsonObject SILVERFISH_STATUE_RECIPE = null;
    public static JsonObject ENDERMITE_STATUE_RECIPE = null;
    public static JsonObject SHULKER_STATUE_RECIPE = null;
    public static JsonObject CAT_STATUE_RECIPE = null;
    public static JsonObject WOLF_STATUE_RECIPE = null;
    public static JsonObject CHICKEN_STATUE_RECIPE = null;
    public static JsonObject RABBIT_STATUE_RECIPE = null;
    public static JsonObject FOX_STATUE_RECIPE = null;
    public static JsonObject COD_STATUE_RECIPE = null;
    public static JsonObject SALMON_STATUE_RECIPE = null;
    public static JsonObject PUFFER_FISH_STATUE_RECIPE = null;
    public static JsonObject MAGMA_CUBE_STATUE_RECIPE = null;
    public static JsonObject SLIME_STATUE_RECIPE = null;

    public static void init() {
        BEE_ESSENCE_RECIPE = createEssenceRecipe("bee_essence", Items.HONEYCOMB, Items.HONEY_BOTTLE);
        SHULKER_ESSENCE_RECIPE = createEssenceRecipe("shulker_essence", Items.SHULKER_SHELL, Items.SHULKER_SHELL);
        CAT_ESSENCE_RECIPE = createEssenceRecipe("wolf_essence", Items.STRING, Items.STRING);
        CHICKEN_ESSENCE_RECIPE = createEssenceRecipe("chicken_essence", Items.CHICKEN, Items.FEATHER);
        RABBIT_ESSENCE_RECIPE = createEssenceRecipe("rabbit_essence", Items.RABBIT, Items.RABBIT_HIDE);
        FOX_ESSENCE_RECIPE = createEssenceRecipe("fox_essence", Items.SWEET_BERRIES, Items.SWEET_BERRIES);
        COD_ESSENCE_RECIPE = createEssenceRecipe("cod_essence", Items.COD, Items.BONE_MEAL);
        SALMON_ESSENCE_RECIPE = createEssenceRecipe("salmon_essence", Items.SALMON, Items.BONE_MEAL);
        PUFFER_FISH_ESSENCE_RECIPE = createEssenceRecipe("puffer_fish_essence", Items.PUFFERFISH, Items.BONE_MEAL);
        SLIME_ESSENCE_RECIPE = createEssenceRecipe("slime_essence", Items.SLIME_BALL, Items.SLIME_BALL);
        MAGMA_CUBE_ESSENCE_RECIPE = createEssenceRecipe("magma_cube_essence", Items.MAGMA_CREAM, Items.MAGMA_CREAM);


        BEE_STATUE_RECIPE = createStatueRecipe("bee_statue", AtbywItems.BEE_ESSENCE);
        SILVERFISH_STATUE_RECIPE = createStatueRecipe("silverfish_statue", AtbywItems.SILVERFISH_ESSENCE);
        ENDERMITE_STATUE_RECIPE = createStatueRecipe("endermite_statue", AtbywItems.ENDERMITE_ESSENCE);
        SHULKER_STATUE_RECIPE = createStatueRecipe("shulker_statue", AtbywItems.SHULKER_ESSENCE);
        CAT_STATUE_RECIPE = createStatueRecipe("wolf_statue", AtbywItems.CAT_ESSENCE);
        WOLF_STATUE_RECIPE = createStatueRecipe("cat_statue", AtbywItems.WOLF_ESSENCE);
        CHICKEN_STATUE_RECIPE = createStatueRecipe("chicken_statue", AtbywItems.CHICKEN_ESSENCE);
        RABBIT_STATUE_RECIPE = createStatueRecipe("rabbit_statue", AtbywItems.RABBIT_ESSENCE);
        FOX_STATUE_RECIPE = createStatueRecipe("fox_statue", AtbywItems.FOX_ESSENCE);
        COD_STATUE_RECIPE = createStatueRecipe("cod_statue", AtbywItems.COD_ESSENCE);
        SALMON_STATUE_RECIPE = createStatueRecipe("salmon_statue", AtbywItems.SALMON_ESSENCE);
        PUFFER_FISH_STATUE_RECIPE = createStatueRecipe("puffer_fish_statue", AtbywItems.PUFFER_FISH_ESSENCE);
        SLIME_STATUE_RECIPE = createStatueRecipe("slime_statue", AtbywItems.SLIME_ESSENCE);
        MAGMA_CUBE_STATUE_RECIPE = createStatueRecipe("magma_cube_statue", AtbywItems.MAGMA_CUBE_ESSENCE);
    }

    private static void putRecipe(Identifier identifier, JsonElement recipe, Map<Identifier, JsonElement> map) {
        if (recipe != null) {
            map.put(identifier, recipe);
        }
    }

    //Used in net.azagwen.atbyw.mixin.RecipeManagerMixin
    public static void injectRecipes(Map<Identifier, JsonElement> map) {
        putRecipe(newID("bee_essence"), AtbywRecipes.BEE_ESSENCE_RECIPE, map);
        putRecipe(newID("shulker_essence"), AtbywRecipes.SHULKER_ESSENCE_RECIPE, map);
        putRecipe(newID("cat_essence"), AtbywRecipes.CAT_ESSENCE_RECIPE, map);
        putRecipe(newID("chicken_essence"), AtbywRecipes.CHICKEN_ESSENCE_RECIPE, map);
        putRecipe(newID("rabbit_essence"), AtbywRecipes.RABBIT_ESSENCE_RECIPE, map);
        putRecipe(newID("fox_essence"), AtbywRecipes.FOX_ESSENCE_RECIPE, map);
        putRecipe(newID("cod_essence"), AtbywRecipes.COD_ESSENCE_RECIPE, map);
        putRecipe(newID("salmon_essence"), AtbywRecipes.SALMON_ESSENCE_RECIPE, map);
        putRecipe(newID("puffer_fish_essence"), AtbywRecipes.PUFFER_FISH_ESSENCE_RECIPE, map);
        putRecipe(newID("magma_cube_essence"), AtbywRecipes.MAGMA_CUBE_ESSENCE_RECIPE, map);
        putRecipe(newID("slime_essence"), AtbywRecipes.SLIME_ESSENCE_RECIPE, map);

        putRecipe(newID("bee_statue"), AtbywRecipes.BEE_STATUE_RECIPE, map);
        putRecipe(newID("silverfish_statue"), AtbywRecipes.SILVERFISH_STATUE_RECIPE, map);
        putRecipe(newID("endermite_statue"), AtbywRecipes.ENDERMITE_STATUE_RECIPE, map);
        putRecipe(newID("shulker_statue"), AtbywRecipes.SHULKER_STATUE_RECIPE, map);
        putRecipe(newID("cat_statue"), AtbywRecipes.CAT_STATUE_RECIPE, map);
        putRecipe(newID("chicken_statue"), AtbywRecipes.CHICKEN_STATUE_RECIPE, map);
        putRecipe(newID("rabbit_statue"), AtbywRecipes.RABBIT_STATUE_RECIPE, map);
        putRecipe(newID("fox_statue"), AtbywRecipes.FOX_STATUE_RECIPE, map);
        putRecipe(newID("cod_statue"), AtbywRecipes.COD_STATUE_RECIPE, map);
        putRecipe(newID("salmon_statue"), AtbywRecipes.SALMON_STATUE_RECIPE, map);
        putRecipe(newID("puffer_fish_statue"), AtbywRecipes.PUFFER_FISH_STATUE_RECIPE, map);
        putRecipe(newID("magma_cube_statue"), AtbywRecipes.MAGMA_CUBE_STATUE_RECIPE, map);
        putRecipe(newID("slime_statue"), AtbywRecipes.SLIME_STATUE_RECIPE, map);
    }
}
