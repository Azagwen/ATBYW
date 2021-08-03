package net.azagwen.atbyw.datagen.recipe.util;

import net.minecraft.item.ItemConvertible;

import java.util.Objects;

/**
 * Used solely to make general Recipe Data easier to retrieve for registration
 */
public final class RecipeData {
    private final String suffix;
    private final String category;
    private final String group;
    private final ItemConvertible result;
    private final int count;

    public RecipeData(String suffix, String category, String group, ItemConvertible result, int count) {
        this.suffix = suffix;
        this.category = category;
        this.group = group;
        this.result = result;
        this.count = count;
    }

    public RecipeData(String category, String group, ItemConvertible result, int count) {
        this("", category, group, result, count);
    }

    public RecipeData(String group, ItemConvertible result, int count) {
        this("", "", group, result, count);
    }

    public RecipeData(ItemConvertible result, int count) {
        this("", "", "", result, count);
    }

    public String suffix() {
        return suffix;
    }

    public String category() {
        return category;
    }

    public String group() {
        return group;
    }

    public ItemConvertible result() {
        return result;
    }

    public int count() {
        return count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        var that = (RecipeData) obj;
        var compareSuffix = this.suffix.equals(that.suffix);
        var compareCategory = this.category.equals(that.category);
        var compareGroup = this.group.equals(that.group);
        var compareResult = this.result.equals(that.result);
        var compareCount = this.count == that.count;
        return compareSuffix && compareCategory && compareGroup && compareResult && compareCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suffix, category, group, result, count);
    }

    @Override
    public String toString() {
        return "RecipeData["+"suffix="+suffix+", "+"category="+category+", "+"group="+group+", "+"result="+result+", "+"count="+count+']';
    }
}
