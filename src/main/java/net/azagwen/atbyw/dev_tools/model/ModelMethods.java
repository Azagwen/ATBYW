package net.azagwen.atbyw.dev_tools.model;

import com.google.gson.JsonObject;

import java.util.Map;

public class ModelMethods {
    protected static JsonObject modelFromParent(String parent, Map<String, String> texturesMap) {
        var model = new JsonObject();
        var textures = new JsonObject();

        model.addProperty("parent", parent);

        if (texturesMap != null) {
            texturesMap.forEach(textures::addProperty);
            model.add("textures", textures);
        }

        return model;
    }

    protected static JsonObject modelFromParent(String parent) {
        return modelFromParent(parent, null);
    }
}
