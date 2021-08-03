package net.azagwen.atbyw.archived;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class JsonAdvancements {
    public static Gson builder = new GsonBuilder().setPrettyPrinting().create();
    public static Logger LOGGER = LogManager.getLogger("Atbyw Advancements");
    public static Map<Identifier, JsonElement> RECIPE_MAP = Maps.newConcurrentMap();

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

    public static JsonObject unlockShapelessRecipe(JsonObject recipe, Identifier reward) {
        var criteria = new JsonObject();
        criteria.add("has_the_recipe", hasTheRecipeCriteria(reward));

        //Rewards
        var rewardsObject = new JsonObject();
        rewardsObject.add("recipes", AtbywUtils.jsonArray(reward.toString()));

        //Deserialize and add ingredients to inventoryChangedCriteria and requirements
        var requirements = new JsonArray();
        if (recipe.has("ingredients")) {
            var ingredients = recipe.get("ingredients").getAsJsonArray();
            for (var ingredient : ingredients) {
                var items = ingredient.getAsJsonObject().entrySet();
                for (var itemValue : items) {
                    var item = itemValue.getValue().getAsString();
                    var type = itemValue.getKey();
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

    /**
     * Unused as of now, can be used to register recipe advancements separately
     *
     * @param map the map the advancement will be added to.
     * @param id identifier of the recipe the advancement is tied to.
     * @param element Json of the recipe the advancement is tied to.
     *
     **/
    public static void translateRecipes(Map<Identifier, JsonElement> map, Identifier id, JsonElement element) {
        var object = element.getAsJsonObject();
        if (object.has("type")) {
            var type = object.get("type").getAsString();
            var isFurnace = type.contains("minecraft:smelting");
            var isSmoker = type.contains("minecraft:smoking");
            var isBlastFurnace = type.contains("minecraft:blasting");
            var isCampFire = type.contains("minecraft:campfire_cooking");
            var isStoneCutter = type.contains("minecraft:stonecutting");

            if (type.contains("minecraft:crafting_shaped")) {
                map.put(id, unlockShapedRecipe(element.getAsJsonObject(), id));
            }
            if (isFurnace || isSmoker || isBlastFurnace || isCampFire || isStoneCutter) {
                map.put(id, unlockSingleIngredientRecipe(element.getAsJsonObject(), id));
            }
            if (type.contains("minecraft:crafting_shapeless")) {
                map.put(id, unlockShapelessRecipe(element.getAsJsonObject(), id));
            }
        }
    }

    public static void unlockAllRecipes(Map<Identifier, JsonElement> map) {
        var recipeMap = Maps.<Identifier, JsonElement>newConcurrentMap();
        recipeMap.forEach((id, element) -> translateRecipes(map, id, element));
    }

    //Used in net.azagwen.atbyw.mixin.datagen.ServerAdvancementLoaderMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        RECIPE_MAP.forEach(map::put);
        LOGGER.info("Atbyw Recipe Advancements built");
    }
}
