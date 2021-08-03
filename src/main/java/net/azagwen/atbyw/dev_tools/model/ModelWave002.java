package net.azagwen.atbyw.dev_tools.model;

import net.azagwen.atbyw.dev_tools.AutoJsonWriter;
import net.azagwen.atbyw.util.AtbywUtils;

import java.util.Map;

public class ModelWave002 {

    protected static Map<String, String> textures(String itemName) {
        return Map.ofEntries(
                Map.entry("layer0", "atbyw:item/" + itemName)
        );
    }

    protected static void write() {
        var writer = new AutoJsonWriter();

        AtbywUtils.dyeColorNames().forEach((color) -> {
            writer.write("models/item/" + color + "_stained_glass_shard.json", ModelMethods.modelFromParent("minecraft:item/generated", textures(color + "_stained_glass_shard")));
        });
        writer.write("models/item/glass_shard.json", ModelMethods.modelFromParent("minecraft:item/generated", textures("glass_shard")));
    }
}
