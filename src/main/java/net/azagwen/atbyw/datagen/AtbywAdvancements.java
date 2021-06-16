package net.azagwen.atbyw.datagen;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.util.Identifier;

import java.util.Map;

public class AtbywAdvancements {

    public static JsonObject hasTheRecipeCriteria(Identifier reward) {
        var recipeCondition = new JsonObject();
        var hasTheRecipe = new JsonObject();

        recipeCondition.addProperty("recipe", reward.toString());
        hasTheRecipe.addProperty("trigger", "minecraft:recipe_unlocked");
        hasTheRecipe.add("conditions", recipeCondition);

        return hasTheRecipe;
    }

    public static JsonObject inventoryChangedCriteria(String identifier, String keyType) {
        var obj = new JsonObject();
        var itemChild = new JsonObject();
        var conditions = new JsonObject();

        obj.addProperty("trigger", "minecraft:inventory_changed");
        if (keyType.equals("item")) {
            itemChild.add("items", AtbywUtils.jsonArray(identifier));
        } else if (keyType.equals("tag")) {
            itemChild.addProperty("tag", identifier);
        }
        conditions.add("items", AtbywUtils.jsonArray(itemChild));
        obj.add("conditions", conditions);

        return obj;
    }

    public static JsonObject unlockShapedRecipe(JsonObject recipe, Identifier reward) {
        var builder = new GsonBuilder().setPrettyPrinting().create();
        var criteria = new JsonObject();
        criteria.add("has_the_recipe", hasTheRecipeCriteria(reward));

        //Rewards
        var rewardsObject = new JsonObject();
        rewardsObject.add("recipes", AtbywUtils.jsonArray(reward.toString()));

        //Deserialize and add ingredients to inventoryChangedCriteria and requirements
        var requirements = new JsonArray();
        if (recipe.has("key")) {
            var keys = recipe.get("key").getAsJsonObject().entrySet();
            for (var key : keys) {
                var keyContent = key.getValue().getAsJsonObject().entrySet();
                for (var content : keyContent) {
                    var item = content.getValue().getAsString();
                    var type = content.getKey();
                    criteria.add("has_" + item.split(":")[1], inventoryChangedCriteria(item, type));
                    requirements.add("has_" + item.split(":")[1]);
                }
            }
        }
        requirements.add("has_the_recipe");

        var requirementArray = AtbywUtils.jsonArray(requirements);

        var advancement = new JsonObject();
        advancement.addProperty("parent", "recipes/root");
        advancement.add("rewards", rewardsObject);
        advancement.add("criteria", criteria);
        advancement.add("requirements", requirementArray);

        return advancement;
    }

    public static JsonObject unlockSingleIngredientRecipe(JsonObject recipe, Identifier reward) {
        var builder = new GsonBuilder().setPrettyPrinting().create();
        var criteria = new JsonObject();
        criteria.add("has_the_recipe", hasTheRecipeCriteria(reward));

        //Rewards
        var rewardsObject = new JsonObject();
        rewardsObject.add("recipes", AtbywUtils.jsonArray(reward.toString()));

        //Deserialize and add ingredients to inventoryChangedCriteria and requirements
        var requirements = new JsonArray();
        if (recipe.has("ingredient")) {
            var ingredients = recipe.get("ingredient").getAsJsonObject().entrySet();
            for (var ingredient : ingredients) {
                var item = ingredient.getValue().getAsString();
                var type = ingredient.getKey();
                criteria.add("has_" + item.split(":")[1], inventoryChangedCriteria(item, type));
                requirements.add("has_" + item.split(":")[1]);
            }
        }
        requirements.add("has_the_recipe");

        var requirementArray = AtbywUtils.jsonArray(requirements);

        var advancement = new JsonObject();
        advancement.addProperty("parent", "recipes/root");
        advancement.add("rewards", rewardsObject);
        advancement.add("criteria", criteria);
        advancement.add("requirements", requirementArray);

        return advancement;
    }

    //Used in net.azagwen.atbyw.mixin.ServerAdvancementLoaderMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        Map<Identifier, JsonElement> recipeMap = Maps.newConcurrentMap();
        AtbywRecipes.inject(recipeMap);
        recipeMap.forEach((id, element) -> {
            var object = element.getAsJsonObject();
            if (object.has("type")) {
                String type = object.get("type").getAsString();
                if (type.contains("minecraft:crafting_shaped")) {
                    map.put(id, unlockShapedRecipe(element.getAsJsonObject(), id));
                }
                if (type.contains("minecraft:stonecutting") || type.contains("minecraft:smelting") || type.contains("minecraft:smoking") || type.contains("campfire_cooking")) {
                    map.put(id, unlockSingleIngredientRecipe(element.getAsJsonObject(), id));
                }
            }
        });
    }
}
