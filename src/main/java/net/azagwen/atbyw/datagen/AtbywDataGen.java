package net.azagwen.atbyw.datagen;

import com.google.gson.JsonObject;
import org.lwjgl.system.CallbackI;

public class AtbywDataGen {

    public static JsonObject createEssenceItemModelJson(String type) {
        if ("generated".equals(type)) {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("parent", "atbyw:item/essence");

            return jsonObject;
        } else {
            return new JsonObject();
        }
    }
}
