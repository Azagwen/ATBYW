package net.azagwen.atbyw.main;

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

    public String createStatueModelJson(String type, String id, int level) {
        if ("block".equals(type)) {
            return "{\n" +
                    "	\"parent\": \"atbyw:block/statues/template_" + id + "\",\n" +
                    "	\"textures\": {\n" +
                    "		\"texture\": \"atbyw:entity/" + id + "/" + id + "_" + level + "\"\n" +
                    "	}\n" +
                    "}";
        } else {
            return "";
        }
    }
}
