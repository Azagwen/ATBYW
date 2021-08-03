package net.azagwen.atbyw.datagen.recipe.util;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.azagwen.atbyw.util.Pair;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;

public record RecipePatterns() {

    private Pair<String[], Multimap<Character, Ingredient>> oneIngredientPattern(String[] pattern, ItemConvertible ingredient) {
        var keys = HashMultimap.<Character, Ingredient>create();
        keys.put('A', Ingredient.ofItems(ingredient));
        return new Pair<>(pattern, keys);
    }

    private Pair<String[], Multimap<Character, Ingredient>> twoIngredientsPattern(String[] pattern, ItemConvertible ingredientA, ItemConvertible ingredientB) {
        var keys = HashMultimap.<Character, Ingredient>create();
        keys.put('A', Ingredient.ofItems(ingredientA));
        keys.put('B', Ingredient.ofItems(ingredientB));
        return new Pair<>(pattern, keys);
    }

    public Pair<String[], Multimap<Character, Ingredient>> stickPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"A", "A"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public Pair<String[], Multimap<Character, Ingredient>> stairsPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"  A", " AA", "AAA"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public Pair<String[], Multimap<Character, Ingredient>> slabPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"AAA"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public Pair<String[], Multimap<Character, Ingredient>> bricksPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"AA", "AA"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public Pair<String[], Multimap<Character, Ingredient>> wallPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"AAA", "AAA"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public Pair<String[], Multimap<Character, Ingredient>> columnPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"A", "A", "A"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public Pair<String[], Multimap<Character, Ingredient>> ladderPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"A A", "AAA", "A A"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public Pair<String[], Multimap<Character, Ingredient>> fenceDoorPattern(ItemConvertible ingredient) {
        var pattern = new String[] {"A A", "A A", "A A"};
        return oneIngredientPattern(pattern, ingredient);
    }

    public Pair<String[], Multimap<Character, Ingredient>> hollowStarPattern(ItemConvertible ingredient) {
        var pattern = new String[] {" A ", "A A", " A "};
        return oneIngredientPattern(pattern, ingredient);
    }

    public Pair<String[], Multimap<Character, Ingredient>> dyingRingPattern(ItemConvertible ingredient, ItemConvertible dye) {
        var pattern = new String[] {"AAA", "ABA", "AAA"};
        return twoIngredientsPattern(pattern, ingredient, dye);
    }

    public Pair<String[], Multimap<Character, Ingredient>> torchPattern(ItemConvertible coal, ItemConvertible stick) {
        var pattern = new String[] {"A", "B"};
        return twoIngredientsPattern(pattern, coal, stick);
    }

    public Pair<String[], Multimap<Character, Ingredient>> bookshelfPattern(ItemConvertible planks, ItemConvertible book) {
        var pattern = new String[] {"AAA", "BBB", "AAA"};
        return twoIngredientsPattern(pattern, planks, book);
    }
}
