package net.azagwen.atbyw.datagen.recipe;

import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;

public class AtbywRecipe {
    private final JsonObject recipeJson;
    private final Identifier recipeIdentifier;

    public AtbywRecipe(JsonObject recipeJson, Identifier recipeIdentifier) {
        this.recipeJson = recipeJson;
        this.recipeIdentifier = recipeIdentifier;
    }

    public JsonObject getJson() {
        return recipeJson;
    }

    public Identifier getIdentifier() {
        return recipeIdentifier;
    }
}
