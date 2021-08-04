package net.azagwen.atbyw.datagen;

import com.google.common.collect.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeDatagen {
    private static final Map<RecipeType<?>, List<Recipe<?>>> RECIPES = new Object2ObjectOpenHashMap<>();
    private static final Map<Recipe<?>, String> RECIPES_CATEGORIES = new Object2ObjectOpenHashMap<>();
    private static final Map<Identifier, Advancement.Task> RECIPE_ADVANCEMENTS = new Object2ObjectOpenHashMap<>();

    //Used in net.azagwen.atbyw.mixin.datagen.ServerAdvancementLoaderMixin
    public static void applyAdvancements(Map<Identifier, Advancement.Task> builder) {
        RECIPE_ADVANCEMENTS.forEach((identifier, task) -> {
            task.parent((Advancement) null);

            builder.put(identifier, task);
        });
    }

    //Used in net.azagwen.atbyw.mixin.datagen.RecipeManagerMixin
    public static void applyRecipes(Map<Identifier, JsonElement> map, Map<RecipeType<?>, ImmutableMap.Builder<Identifier, Recipe<?>>> builderMap) {
        var recipeCount = new int[]{0};
        RECIPES.forEach((key, recipes) -> {
            var recipeBuilder = builderMap.computeIfAbsent(key, o -> ImmutableMap.builder());

            recipes.forEach(recipe -> {
                if (!map.containsKey(recipe.getId())) {
                    recipeBuilder.put(recipe.getId(), recipe);
                    recipeCount[0]++;
                }
            });
        });

        AtbywMain.LOGGER.info("Loaded {} additional recipe" + (recipeCount[0] > 1 ? "s" : ""), recipeCount[0]);
    }

    public static void registerRecipe(Recipe<?> recipe, String category) {
        var recipes = RECIPES.computeIfAbsent(recipe.getType(), recipeType -> new ArrayList<>());

        for (var currentRecipe : recipes) {
            if (currentRecipe.getId().equals(recipe.getId()))
                return;
        }

        recipes.add(recipe);
        RECIPES_CATEGORIES.put(recipe, category);

        var advancementIdPath = "recipes/" + category + "/" + recipe.getId().getPath();
        var advancementId = new Identifier(recipe.getId().getNamespace(), advancementIdPath);
        RECIPE_ADVANCEMENTS.put(advancementId, simpleRecipeUnlock(recipe));
    }

    public static void registerRecipes(List<Recipe<?>> recipes, String category) {
        for (var recipe : recipes) {
            registerRecipe(recipe, category);
        }
    }

    public static Advancement.Task simpleRecipeUnlock(Recipe<?> recipe) {
        var advancement = Advancement.Task.create();
        advancement.parent(new Identifier("recipes/root"));
        advancement.rewards(AdvancementRewards.Builder.recipe(recipe.getId()));
        advancement.criteriaMerger(CriterionMerger.OR);
        advancement.criterion("has_self", InventoryChangedCriterion.Conditions.items(recipe.getOutput().getItem()));
        advancement.criterion("has_the_recipe", new RecipeUnlockedCriterion.Conditions(EntityPredicate.Extended.EMPTY, recipe.getId()));

        var ingredients = Lists.<Identifier>newArrayList();
        for (var ingredient : recipe.getIngredients()) {
            if (!ingredient.isEmpty()) {
                var items = ingredient.toJson().getAsJsonObject().entrySet();
                for (var item : items) {
                    var ingredientId = new Identifier(item.getValue().getAsString());
                    if (!ingredients.contains(ingredientId)) {
                        advancement.criterion("has_" + ingredientId.getPath(), inventoryChangedCriteria(ingredient));
                        ingredients.add(ingredientId);
                    }
                }
            }
        }

        if (true == false){
            var gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            AtbywMain.LOGGER.info(gson.toJson(advancement.toJson()));
        }
        return advancement;
    }

    public static InventoryChangedCriterion.Conditions inventoryChangedCriteria(Ingredient item) {
        var ingredients = new JsonArray();
        var ingredient = item.toJson();

        if (ingredient instanceof JsonObject json) {
            if (json.has("item")) {
                var child = new JsonObject();
                child.add("items", AtbywUtils.jsonArray(JsonHelper.getString(json, "item")));
                ingredients.add(child);
            } else {
                ingredients.add(ingredient);
            }
        }

        var entityPredicate = EntityPredicate.Extended.EMPTY;
        var intRange = NumberRange.IntRange.ANY;
        var items = ItemPredicate.deserializeAll(ingredients);
        return new InventoryChangedCriterion.Conditions(entityPredicate, intRange, intRange, intRange, items);
    }

    /**
     * @param recipeId  Identifier of the recipe being created.
     * @param group     Group the recipe belongs in (for recipe grouping in the recipe book).
     * @param pattern   The recipe Pattern (must not contain any empty space).
     * @param keys      The keys of the ingredient items (must correspond to the pattern characters).
     * @param output    The Resulting item from the recipe.
     *
     * @return          A new ShapedRecipe() created from the input parameters.
     */
    public static Recipe<?> shapedRecipe(Identifier recipeId, String group, String[] pattern, Multimap<Character, Ingredient> keys, ItemConvertible output, int count) {
        var keyMap = Maps.<String, Ingredient>newHashMap();
        var outStack = ItemStack.EMPTY;

        for (var pair : keys.entries()) {
            keyMap.put(pair.getKey().toString(), pair.getValue());
        }

        int x = pattern[0].length();
        int y = pattern.length;
        var ingredients = ShapedRecipe.createPatternMatrix(pattern, keyMap, x, y);

        outStack = new ItemStack(output);
        outStack.setCount(count);
        return new ShapedRecipe(recipeId, group, x, y, ingredients, outStack);
    }

    /**
     * @param recipeId  Identifier of the recipe being created.
     * @param group     Group the recipe belongs in (for recipe grouping in the recipe book).
     * @param input     Items the recipe will take as Ingredients.
     * @param output    The Resulting item from the recipe.
     *
     * @return          A new ShapelessRecipe() created from the input parameters.
     */
    public static Recipe<?> shapelessRecipe(Identifier recipeId, String group, List<ItemConvertible> input, ItemConvertible output, int count) {
        var ingredients = DefaultedList.<Ingredient>of();
        var outStack = ItemStack.EMPTY;

        for (var item : input) {
            ingredients.add(Ingredient.ofItems(item));
        }

        outStack = new ItemStack(output);
        outStack.setCount(count);
        return new ShapelessRecipe(recipeId, group, new ItemStack(output), ingredients);
    }

    public static void test() {
        var recipeId = AtbywMain.id("testo");
        var keys = HashMultimap.<Character, Ingredient>create();
        keys.put('X', Ingredient.ofItems(Items.ACACIA_LOG));
        keys.put('E', Ingredient.ofItems(Items.BREAD));

        var test = shapedRecipe(recipeId, "", new String[] {"XX", "E", "E"}, keys, Items.AMETHYST_BLOCK, 1);

        registerRecipe(test, "aaa");
    }

    /**
     * @param recipeId  Identifier of the recipe being created.
     * @param group     Group the recipe belongs in (for recipe grouping in the recipe book).
     * @param input     The input ingredient block.
     * @param output    The Resulting item from the recipe.
     *
     * @return          A new StonecuttingRecipe() created from the input parameters.
     */
    public static Recipe<?> stonecuttingRecipe(Identifier recipeId, String group, Ingredient input, ItemConvertible output, int count) {
        var outStack = ItemStack.EMPTY;
        outStack = new ItemStack(output);
        outStack.setCount(count);
        return new StonecuttingRecipe(recipeId, group, input, outStack);
    }

    /**
     * @param recipeId  Identifier of the recipe being created.
     * @param group     Group the recipe belongs in (for recipe grouping in the recipe book).
     * @param input     The input ingredient block.
     * @param output    The Resulting item from the recipe.
     *
     * @return          A new StonecuttingRecipe() created from the input parameters.
     */
    public static Recipe<?> smeltingRecipe(Identifier recipeId, String group, Ingredient input, ItemConvertible output, float exp, int cookTime) {
        return new SmeltingRecipe(recipeId, group, input, new ItemStack(output), exp, cookTime);
    }
}
