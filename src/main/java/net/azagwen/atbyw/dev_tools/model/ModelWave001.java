package net.azagwen.atbyw.dev_tools.model;

import com.google.common.collect.HashBasedTable;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.dev_tools.AutoJsonWriter;
import net.azagwen.atbyw.util.AtbywUtils;
import net.azagwen.atbyw.util.Pair;

import java.util.Map;

public class ModelWave001 {

    public static JsonObject writeColumnStairsBlockState(String color) {
        var variantTable = HashBasedTable.<String, Boolean, Pair<Integer, Integer>>create();
        var prefix = "";

        if (!color.equals("")) {
            prefix = color + "_stained_";
        }

        for (int i = 0; i <= 2; i++) {
            variantTable.put("atbyw:block/" + prefix + "shattered_glass_" + i, false, new Pair<>(0, 0));
        }

        return BlockStatesMethods.randomisedBlockState(Map.ofEntries(
                Map.entry("", BlockStatesMethods.randomisedBlockStateVariant(variantTable))
        ));
    }

    protected static Map<String, String> glassTextures(String blockName) {
        return Map.ofEntries(
                Map.entry("all", "atbyw:block/" + blockName)
        );
    }

    protected static void write() {
        var writer = new AutoJsonWriter();

        AtbywUtils.dyeColorNames().forEach((color) -> {
            writer.write("blockstates/" + color + "_stained_shattered_glass.json", writeColumnStairsBlockState(color));

            writer.write("models/block/" + color + "_stained_shattered_glass_0.json", ModelMethods.modelFromParent("minecraft:block/cube_all", glassTextures(color + "_stained_shattered_glass_0")));
            writer.write("models/block/" + color + "_stained_shattered_glass_1.json", ModelMethods.modelFromParent("minecraft:block/cube_all", glassTextures(color + "_stained_shattered_glass_1")));
            writer.write("models/block/" + color + "_stained_shattered_glass_2.json", ModelMethods.modelFromParent("minecraft:block/cube_all", glassTextures(color + "_stained_shattered_glass_2")));

            writer.write("models/item/" + color + "_stained_shattered_glass.json", ModelMethods.modelFromParent("atbyw:block/" + color + "_stained_shattered_glass_0"));
        });

        writer.write("blockstates/shattered_glass.json", writeColumnStairsBlockState(""));

        writer.write("models/block/shattered_glass_0.json", ModelMethods.modelFromParent("minecraft:block/cube_all", glassTextures("shattered_glass_0")));
        writer.write("models/block/shattered_glass_1.json", ModelMethods.modelFromParent("minecraft:block/cube_all", glassTextures("shattered_glass_1")));
        writer.write("models/block/shattered_glass_2.json", ModelMethods.modelFromParent("minecraft:block/cube_all", glassTextures("shattered_glass_2")));

        writer.write("models/item/shattered_glass.json", ModelMethods.modelFromParent("atbyw:block/shattered_glass_0"));

    }
}
