package net.azagwen.atbyw.datagen;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

import static net.azagwen.atbyw.main.AtbywMain.NewAtbywID;

public class AtbywAdvancements {

    /**
     * Dummy Classes used to generate Json using Gson
     */
    static class HasItemCriteria {
        public String trigger;
        public Conditions conditions;

        public HasItemCriteria(Item[] items) {
            this.trigger = "minecraft:inventory_changed";
            this.conditions = new Conditions(items);
        }

        static class Conditions {
            public Item[] items;

            public Conditions(Item[] items) {
                this.items = items;
            }
        }

        static class Item {
            public String[] items;

            public Item(String[] items) {
                this.items = items;
            }
        }
    }

    public static JsonObject createRecipeAdvancementFromShapedRecipe(JsonObject recipe, Identifier reward) {
        Gson builder = new GsonBuilder().setPrettyPrinting().create();
        JsonObject advancement = new JsonObject();
        JsonObject rewardsObject = new JsonObject();
        JsonArray rewardsArray = new JsonArray();
        JsonObject criteria = new JsonObject();
        JsonObject hasTheRecipe = new JsonObject();
        JsonObject recipeCondition = new JsonObject();

        rewardsArray.add(reward.toString());
        recipeCondition.addProperty("recipe", reward.toString());

        advancement.addProperty("parent", "recipes/root");
        rewardsObject.add("recipes", rewardsArray);
        advancement.add("rewards", rewardsObject);

        hasTheRecipe.addProperty("trigger", "minecraft:recipe_unlocked");
        hasTheRecipe.add("conditions", recipeCondition);
        criteria.add("has_the_recipe", hasTheRecipe);

        if (recipe.has("key")) {
            JsonObject keys = recipe.get("key").getAsJsonObject();
            List<Map.Entry<String, JsonElement>> elements = keys.entrySet().stream().toList();

            AtbywMain.LOGGER.info(elements);
            try {
                for (int i = 0; i < keys.size(); i++) {
                    JsonObject currentKey = elements.get(i).getValue().getAsJsonObject();
                    List<Map.Entry<String, JsonElement>> keyContent = currentKey.entrySet().stream().toList();
                    HasItemCriteria.Item[] items = new HasItemCriteria.Item[keyContent.size()];
                    items[i] = new HasItemCriteria.Item(new String[]{keyContent.get(0).getValue().getAsString()});

                    criteria.add("has_item", builder.toJsonTree(new HasItemCriteria(items)));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                AtbywMain.LOGGER.warn("Failed to finish translation for advancement to obtain " + reward + " recipe.");
            }
        }

        advancement.add("criteria", criteria);

        String[][] requirements = new String[][]{
                new String[]{
                        "has_item",
                        "has_the_recipe"
                }
        };
        advancement.add("requirements", builder.toJsonTree(requirements));

//        AtbywMain.LOGGER.info(builder.toJson(advancement));

        return advancement;
    }

    public static final JsonObject TEST_ADVANCEMENT = createRecipeAdvancementFromShapedRecipe(AtbywRecipes.ANDESITE_BRICKS, NewAtbywID("andesite_bricks"));

    //Used in net.azagwen.atbyw.mixin.ServerAdvancementLoaderMixin
    public static void inject(Map<Identifier, JsonElement> map) {
        Map<Identifier, JsonElement> recipeMap = Maps.newConcurrentMap();
        AtbywRecipes.inject(recipeMap);
        recipeMap.forEach((id, element) -> {
            if (element.getAsJsonObject().has("type") && element.getAsJsonObject().get("type").getAsString().contains("minecraft:crafting_shaped")) {
                map.put(id, createRecipeAdvancementFromShapedRecipe(element.getAsJsonObject(), id));
            }
        });
    }
}
