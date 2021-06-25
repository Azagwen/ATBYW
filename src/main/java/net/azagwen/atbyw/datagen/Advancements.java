package net.azagwen.atbyw.datagen;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class Advancements {
    public static Gson builder = new GsonBuilder().setPrettyPrinting().create();
    public static Logger LOGGER = LogManager.getLogger("Atbyw Advancements");
    public static Map<Identifier, JsonElement> RECIPE_MAP = Maps.newConcurrentMap();

    public static JsonObject hasTheRecipeCriteria(Identifier reward) {
        JsonObject recipeCondition = new JsonObject();
        JsonObject hasTheRecipe = new JsonObject();

        recipeCondition.addProperty("recipe", reward.toString());
        hasTheRecipe.addProperty("trigger", "minecraft:recipe_unlocked");
        hasTheRecipe.add("conditions", recipeCondition);

        return hasTheRecipe;
    }

    public static JsonObject inventoryChangedCriteria(String identifier, String keyType) {
        JsonObject obj = new JsonObject();
        JsonObject itemChild = new JsonObject();
        JsonObject conditions = new JsonObject();

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
        JsonObject criteria = new JsonObject();
        criteria.add("has_the_recipe", hasTheRecipeCriteria(reward));

        //Rewards
        JsonObject rewardsObject = new JsonObject();
        rewardsObject.add("recipes", AtbywUtils.jsonArray(reward.toString()));

        //Deserialize and add ingredients to inventoryChangedCriteria and requirements
        JsonArray requirements = new JsonArray();
        if (recipe.has("key")) {
            Set<Map.Entry<String, JsonElement>> keys = recipe.get("key").getAsJsonObject().entrySet();
            for (Map.Entry<String, JsonElement> key : keys) {
                Set<Map.Entry<String, JsonElement>> keyContent = key.getValue().getAsJsonObject().entrySet();
                for (Map.Entry<String, JsonElement> content : keyContent) {
                    String item = content.getValue().getAsString();
                    String type = content.getKey();
                    criteria.add("has_" + item.split(":")[1], inventoryChangedCriteria(item, type));
                    requirements.add("has_" + item.split(":")[1]);
                }
            }
        }
        requirements.add("has_the_recipe");

        JsonArray requirementArray = AtbywUtils.jsonArray(requirements);

        JsonObject advancement = new JsonObject();
        advancement.addProperty("parent", "recipes/root");
        advancement.add("rewards", rewardsObject);
        advancement.add("criteria", criteria);
        advancement.add("requirements", requirementArray);

        return advancement;
    }

    public static JsonObject unlockShapelessRecipe(JsonObject recipe, Identifier reward) {
        JsonObject criteria = new JsonObject();
        criteria.add("has_the_recipe", hasTheRecipeCriteria(reward));

        //Rewards
        JsonObject rewardsObject = new JsonObject();
        rewardsObject.add("recipes", AtbywUtils.jsonArray(reward.toString()));

        //Deserialize and add ingredients to inventoryChangedCriteria and requirements
        JsonArray requirements = new JsonArray();
        if (recipe.has("ingredients")) {
            JsonArray ingredients = recipe.get("ingredients").getAsJsonArray();
            for (JsonElement ingredient : ingredients) {
                Set<Map.Entry<String, JsonElement>> items = ingredient.getAsJsonObject().entrySet();
                for (Map.Entry<String, JsonElement> itemValue : items) {
                    String item = itemValue.getValue().getAsString();
                    String type = itemValue.getKey();
                    criteria.add("has_" + item.split(":")[1], inventoryChangedCriteria(item, type));
                    requirements.add("has_" + item.split(":")[1]);
                }
            }
        }
        requirements.add("has_the_recipe");

        JsonArray requirementArray = AtbywUtils.jsonArray(requirements);

        JsonObject advancement = new JsonObject();
        advancement.addProperty("parent", "recipes/root");
        advancement.add("rewards", rewardsObject);
        advancement.add("criteria", criteria);
        advancement.add("requirements", requirementArray);

        return advancement;
    }

    public static JsonObject unlockSingleIngredientRecipe(JsonObject recipe, Identifier reward) {
        JsonObject criteria = new JsonObject();
        criteria.add("has_the_recipe", hasTheRecipeCriteria(reward));

        //Rewards
        JsonObject rewardsObject = new JsonObject();
        rewardsObject.add("recipes", AtbywUtils.jsonArray(reward.toString()));

        //Deserialize and add ingredients to inventoryChangedCriteria and requirements
        JsonArray requirements = new JsonArray();
        if (recipe.has("ingredient")) {
            Set<Map.Entry<String, JsonElement>> ingredients = recipe.get("ingredient").getAsJsonObject().entrySet();
            for (Map.Entry<String, JsonElement> ingredient : ingredients) {
                String item = ingredient.getValue().getAsString();
                String type = ingredient.getKey();
                criteria.add("has_" + item.split(":")[1], inventoryChangedCriteria(item, type));
                requirements.add("has_" + item.split(":")[1]);
            }
        }
        requirements.add("has_the_recipe");

        JsonArray requirementArray = AtbywUtils.jsonArray(requirements);

        JsonObject advancement = new JsonObject();
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
        JsonObject object = element.getAsJsonObject();
        if (object.has("type")) {
            String type = object.get("type").getAsString();
            boolean isFurnace = type.contains("minecraft:smelting");
            boolean isSmoker = type.contains("minecraft:smoking");
            boolean isBlastFurnace = type.contains("minecraft:blasting");
            boolean isCampFire = type.contains("minecraft:campfire_cooking");
            boolean isStoneCutter = type.contains("minecraft:stonecutting");

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
        ConcurrentMap<Identifier, JsonElement> recipeMap = Maps.<Identifier, JsonElement>newConcurrentMap();
        RecipeRegistry.inject(recipeMap);
        recipeMap.forEach((id, element) -> translateRecipes(map, id, element));
    }

    //Used in net.azagwen.atbyw.mixin.ServerAdvancementLoaderMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        unlockAllRecipes(map);
        LOGGER.info("Atbyw Recipe Advancements built");
    }
}
