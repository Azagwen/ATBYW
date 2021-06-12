package net.azagwen.atbyw.datagen;

public class AtbywDataGen {

    public static String createEssenceItemModelJson(String type) {
        if ("generated".equals(type)) {
            return "{\n" +
                    "   \"parent\": \"atbyw:item/essence\"\n" +
                    "}";
        } else {
            return "";
        }
    }
}
