package net.azagwen.atbyw.datagen;

import net.azagwen.atbyw.util.Pair;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;

import java.util.HashMap;
import java.util.Map;

public record RecipePatterns() {

    private static Pair<String[], Map<Character, Ingredient>> oneIngredientPattern(String[] pattern, ItemConvertible ingredient) {
        var keys = new HashMap<Character, Ingredient>();
        keys.put('A', Ingredient.ofItems(ingredient));
        return new Pair<>(pattern, keys);
    }

    private static Pair<String[], Map<Character, Ingredient>> twoIngredientsPattern(String[] pattern, ItemConvertible ingredientA, ItemConvertible ingredientB) {
        var keys = new HashMap<Character, Ingredient>();
        keys.put('A', Ingredient.ofItems(ingredientA));
        keys.put('B', Ingredient.ofItems(ingredientB));
        return new Pair<>(pattern, keys);
    }

    public static Pair<String[], Map<Character, Ingredient>> stickPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"A", "A"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public static Pair<String[], Map<Character, Ingredient>> stairsPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"  A", " AA", "AAA"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public static Pair<String[], Map<Character, Ingredient>> slabPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"AAA"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public static Pair<String[], Map<Character, Ingredient>> bricksPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"AA", "AA"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public static Pair<String[], Map<Character, Ingredient>> wallPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"AAA", "AAA"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public static Pair<String[], Map<Character, Ingredient>> columnPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"A", "A", "A"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public static Pair<String[], Map<Character, Ingredient>> ladderPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"A A", "AAA", "A A"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public static Pair<String[], Map<Character, Ingredient>> fenceDoorPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"A A", "A A", "A A"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public static Pair<String[], Map<Character, Ingredient>> hollowStarPattern(ItemConvertible ingredient) {
        var pattern = new String[] {" A ", "A A", " A "};
        return oneIngredientPattern(pattern, ingredient);
    }

    public static Pair<String[], Map<Character, Ingredient>> dyingRingPattern(ItemConvertible ingredient, ItemConvertible dye) {
        var pattern = new String[] {"AAA", "ABA", "AAA"};
        return twoIngredientsPattern(pattern, ingredient, dye);
    }

    public static Pair<String[], Map<Character, Ingredient>> torchPattern(ItemConvertible coal, ItemConvertible stick) {
        var pattern = new String[] {"A", "B"};
        return twoIngredientsPattern(pattern, coal, stick);
    }

    public static Pair<String[], Map<Character, Ingredient>> bookshelfPattern(ItemConvertible planks, ItemConvertible book) {
        var pattern = new String[] {"AAA", "BBB", "AAA"};
        return twoIngredientsPattern(pattern, planks, book);
    }
}
