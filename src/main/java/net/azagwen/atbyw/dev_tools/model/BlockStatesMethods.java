package net.azagwen.atbyw.dev_tools.model;

import com.google.common.collect.HashBasedTable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.util.Pair;

import java.util.Map;

public class BlockStatesMethods {

    protected static JsonObject blockStateVariant(String model, int rotY, int rotX, boolean uvLock) {
        var variant = new JsonObject();

        variant.addProperty("model", model);
        if (rotY != 0) {
            variant.addProperty("y", rotY);
        }
        if (rotX != 0) {
            variant.addProperty("x", rotX);
        }
        if (uvLock) {
            variant.addProperty("uvlock", true);
        }

        return variant;
    }

    protected static JsonObject blockStateVariant(String model, boolean uvLock) {
        return blockStateVariant(model, 0, 0, uvLock);
    }

    protected static JsonObject blockStateVariant(String model, int rotY, int rotX) {
        return blockStateVariant(model, rotY, rotX, false);
    }

    public static JsonObject blockStateVariant(String model) {
        return blockStateVariant(model, 0, 0, false);
    }

    protected static JsonArray randomisedBlockStateVariant(HashBasedTable<String, Boolean, Pair<Integer, Integer>> modelTable) {
        var variants = new JsonArray();

        for (var cell : modelTable.cellSet()) {
            var model = cell.getRowKey();
            var uvLock = cell.getColumnKey();
            var rotY = cell.getValue().getFirst();
            var rotX = cell.getValue().getSecond();
            var variant = new JsonObject();

            variant.addProperty("model", model);
            if (rotY != 0) {
                variant.addProperty("y", rotY);
            }
            if (rotX != 0) {
                variant.addProperty("x", rotX);
            }
            if (uvLock) {
                variant.addProperty("uvlock", true);
            }

            variants.add(variant);
        }

        return variants;
    }

    protected static JsonObject randomisedBlockState(Map<String, JsonArray> variantsMap) {
        var blockState = new JsonObject();
        var variants = new JsonObject();

        variantsMap.forEach(variants::add);

        blockState.add("variants", variants);

        return blockState;
    }

    protected static JsonObject blockState(Map<String, JsonObject> variantsMap) {
        var blockState = new JsonObject();
        var variants = new JsonObject();

        variantsMap.forEach(variants::add);

        blockState.add("variants", variants);

        return blockState;
    }
}
