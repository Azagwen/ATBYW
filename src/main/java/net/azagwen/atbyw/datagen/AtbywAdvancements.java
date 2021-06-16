package net.azagwen.atbyw.datagen;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.util.Identifier;

import java.util.Map;

public class AtbywAdvancements {

    public static JsonObject inventoryChangedCriteria(String identifier) {
        var obj = new JsonObject();
        obj.addProperty("trigger", "minecraft:inventory_changed");

        var itemChildArray = new JsonArray();
        itemChildArray.add(identifier);

        var itemChild = new JsonObject();
        itemChild.add("items", itemChildArray);

        var items = new JsonArray();
        items.add(itemChild);

        var conditions = new JsonObject();
        conditions.add("items", items);

        obj.add("conditions", conditions);

        return obj;
    }

    public static JsonObject createRecipeAdvancementFromShapedRecipe(JsonObject recipe, Identifier reward) {
        var builder = new GsonBuilder().setPrettyPrinting().create();

        //Rewards
        var rewardsArray = new JsonArray();
        rewardsArray.add(reward.toString());

        var rewardsObject = new JsonObject();
        rewardsObject.add("recipes", rewardsArray);

        //Has the recipe
        var recipeCondition = new JsonObject();
        recipeCondition.addProperty("recipe", reward.toString());

        var hasTheRecipe = new JsonObject();
        hasTheRecipe.addProperty("trigger", "minecraft:recipe_unlocked");
        hasTheRecipe.add("conditions", recipeCondition);

        var criteria = new JsonObject();
        criteria.add("has_the_recipe", hasTheRecipe);

        var requirements = new JsonArray();
        if (recipe.has("key")) {
            var keys = recipe.get("key").getAsJsonObject().entrySet();
            for (var key : keys) {
                var keyContent = key.getValue().getAsJsonObject().entrySet();
                for (var content : keyContent) {
                    var id = content.getValue().getAsString();
                    criteria.add("has_" + id.split(":")[1], inventoryChangedCriteria(id));
                    requirements.add("has_" + id.split(":")[1]);
                }
            }
        }
        requirements.add("has_the_recipe");

        var requirementArray = new JsonArray();
        requirementArray.add(requirements);

        var advancement = new JsonObject();
        advancement.addProperty("parent", "recipes/root");
        advancement.add("rewards", rewardsObject);
        advancement.add("criteria", criteria);
        advancement.add("requirements", requirementArray);

        AtbywMain.LOGGER.info(builder.toJson(advancement));

        return advancement;
    }

    //Used in net.azagwen.atbyw.mixin.ServerAdvancementLoaderMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        Map<Identifier, JsonElement> recipeMap = Maps.newConcurrentMap();
        AtbywRecipes.inject(recipeMap);
        recipeMap.forEach((id, element) -> {
            map.put(id, createRecipeAdvancementFromShapedRecipe(element.getAsJsonObject(), id));
//            var object = element.getAsJsonObject();
//            if (object.has("type")) {
//                if (object.get("type").getAsString().contains("minecraft:crafting_shaped")) {
//                }
//            }
        });
    }
}
