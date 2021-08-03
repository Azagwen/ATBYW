package net.azagwen.atbyw.datagen.recipe.util;

import net.minecraft.item.ItemConvertible;

import java.util.Objects;

/**
 * Used solely to make general Recipe Data easier to retrieve for registration
 */
public final class FurnaceRecipeData {
    private final String suffix;
    private final String category;
    private final String group;
    private final ItemConvertible result;
    private final float exp;
    private final int cookTime;

    public FurnaceRecipeData(String suffix, String category, String group, ItemConvertible result, float exp, int cookTime) {
        this.suffix = suffix;
        this.category = category;
        this.group = group;
        this.result = result;
        this.exp = exp;
        this.cookTime = cookTime;
    }

    public FurnaceRecipeData(String category, String group, ItemConvertible result, float exp, int cookTime) {
        this("", category, group, result, exp, cookTime);
    }

    public FurnaceRecipeData(String group, ItemConvertible result, float exp, int cookTime) {
        this("", "", group, result, exp, cookTime);
    }

    public FurnaceRecipeData(ItemConvertible result, float exp, int cookTime) {
        this("", "", "", result, exp, cookTime);
    }

    public String suffix() {
        return this.suffix;
    }

    public String category() {
        return this.category;
    }

    public String group() {
        return this.group;
    }

    public ItemConvertible result() {
        return this.result;
    }

    public float exp() {
        return this.exp;
    }

    public int cookTime() {
        return this.cookTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        var that = (FurnaceRecipeData) obj;
        var compareSuffix = this.suffix.equals(that.suffix);
        var compareCategory = this.category.equals(that.category);
        var compareGroup = this.group.equals(that.group);
        var compareResult = this.result.equals(that.result);
        var compareExp = this.exp == that.exp;
        var compareCookTime = this.cookTime == that.cookTime;
        return compareSuffix && compareCategory && compareGroup && compareResult && compareExp && compareCookTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.suffix, this.category, this.group, this.result, this.cookTime);
    }

    @Override
    public String toString() {
        return "RecipeData["+"suffix="+this.suffix+", "+"category="+this.category+", "+"group="+this.group+", "+"result="+this.result+", "+"exp="+this.exp+", "+"cookTime="+this.cookTime+']';
    }
}
