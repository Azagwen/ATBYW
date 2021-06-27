package net.azagwen.atbyw.dev_tools;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.naming.WoodNames;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.util.Map;

public class AutoModelWriter {

    public AutoModelWriter() {
    }

    protected JsonObject blockStateVariant(String model, int rotY, int rotX, boolean uvLock) {
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
    protected JsonObject blockStateVariant(String model, int rotY, int rotX) {
        return blockStateVariant(model, rotY, rotX, false);
    }

    protected JsonObject blockState(Map<String, JsonObject> variantsMap) {
        var blockState = new JsonObject();
        var variants = new JsonObject();

        variantsMap.forEach(variants::add);

        blockState.add("variants", variants);

        return blockState;
    }

    protected JsonObject modelFromParent(String parent, Map<String, String> texturesMap) {
        var model = new JsonObject();
        var textures = new JsonObject();

        model.addProperty("parent", parent);

        if (texturesMap != null) {
            texturesMap.forEach(textures::addProperty);
            model.add("textures", textures);
        }

        return model;
    }

    protected JsonObject modelFromParent(String parent) {
        return modelFromParent(parent, null);
    }

    public void write(String fileName, JsonObject jsonObject) {
        var gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        var client = MinecraftClient.getInstance();
        var file = new File(client.runDirectory, "atbyw_dev_output/" + fileName);

        //Create the File
        if (file.getParentFile().mkdirs()) {
            AtbywMain.MYS_LOGGER.info(file.getParentFile().getAbsolutePath() + " folders created.");
        }
        try {
            if (file.createNewFile()) {
                AtbywMain.MYS_LOGGER.info(file.getName() + " created.");
            }
        } catch (IOException e) {
            AtbywMain.MYS_LOGGER.info("Failed to create " + file.getName());
            e.printStackTrace();
        }

        //Write in the File
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(jsonObject));
            writer.close();
            AtbywMain.MYS_LOGGER.info("Successfully wrote options to " + file.getName());
        } catch (IOException e) {
            AtbywMain.MYS_LOGGER.info("An error occurred writing to " + file.getName());
            e.printStackTrace();
        }
    }

    public JsonObject writeColumnStairsBlockState(String blockName) {
        return this.blockState(Map.<String, JsonObject>ofEntries(
                //Straight X
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=straight", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=straight", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=straight", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=straight", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=straight", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=straight", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=straight", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=straight", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 180, 180)),

                //Straight Y
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=straight", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName, 270, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=straight", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName, 0, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=straight", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName, 90, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=straight", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName, 180, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=straight", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName, 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=straight", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName, 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=straight", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName, 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=straight", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName, 180, 180, true)),

                //Straight Z
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=straight", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=straight", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=straight", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=straight", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=straight", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=straight", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=straight", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=straight", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 180, 180)),

                //Inner X
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 180, 180)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 180, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 270, 180)),

                //Inner Y
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 180, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 270, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 0, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 90, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 180, 180, true)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 270, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 0, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 90, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 180, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 180, 180, true)),

                //Inner Z
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 180, 180)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 180, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 270, 180)),

                //Outer X
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_left", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 180, 180)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 180, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_right", "axis=x"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 270, 180)),

                //Outer Y
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 180, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 270, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 0, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 90, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_left", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 180, 180, true)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 270, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 0, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 90, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 180, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_right", "axis=y"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 180, 180, true)),

                //Outer Z
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_left", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 180, 180)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 180, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_right", "axis=z"), blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 270, 180))
        ));
    }

    protected Map<String, String> lotStairsTextures(String blockName) {
        return Map.<String, String>ofEntries(
                Map.entry("top", "minecraft:block/" + blockName + "_top"),
                Map.entry("side", "minecraft:block/" + blockName),
                Map.entry("cut", "atbyw:block/" + blockName + "_cut")
        );
    }

    private void writeLogStairs() {
        WoodNames.getNamesOverworld().forEach((name) -> {
            this.write("models/block/" + name + "_log_stairs.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs", lotStairsTextures(name + "_log")));
            this.write("models/block/" + name + "_log_stairs_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_h_x", lotStairsTextures(name + "_log")));
            this.write("models/block/" + name + "_log_stairs_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_h_z", lotStairsTextures(name + "_log")));
            this.write("models/block/" + name + "_log_stairs_outer.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer", lotStairsTextures(name + "_log")));
            this.write("models/block/" + name + "_log_stairs_outer_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_x", lotStairsTextures(name + "_log")));
            this.write("models/block/" + name + "_log_stairs_outer_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_z", lotStairsTextures(name + "_log")));
            this.write("models/block/" + name + "_log_stairs_inner.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner", lotStairsTextures(name + "_log")));
            this.write("models/block/" + name + "_log_stairs_inner_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_x", lotStairsTextures(name + "_log")));
            this.write("models/block/" + name + "_log_stairs_inner_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_z", lotStairsTextures(name + "_log")));

            this.write("models/item/" + name + "_log_stairs.json", this.modelFromParent("atbyw:block/stairs/" + name + "_log_stairs"));
            this.write("blockstates/" + name + "_log_stairs.json", this.writeColumnStairsBlockState(name + "_log_stairs"));

            this.write("models/block/stripped_" + name + "_log_stairs.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs", lotStairsTextures("stripped_" + name + "_log")));
            this.write("models/block/stripped_" + name + "_log_stairs_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_h_x", lotStairsTextures("stripped_" + name + "_log")));
            this.write("models/block/stripped_" + name + "_log_stairs_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_h_z", lotStairsTextures("stripped_" + name + "_log")));
            this.write("models/block/stripped_" + name + "_log_stairs_outer.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer", lotStairsTextures("stripped_" + name + "_log")));
            this.write("models/block/stripped_" + name + "_log_stairs_outer_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_x", lotStairsTextures("stripped_" + name + "_log")));
            this.write("models/block/stripped_" + name + "_log_stairs_outer_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_z", lotStairsTextures("stripped_" + name + "_log")));
            this.write("models/block/stripped_" + name + "_log_stairs_inner.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner", lotStairsTextures("stripped_" + name + "_log")));
            this.write("models/block/stripped_" + name + "_log_stairs_inner_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_x", lotStairsTextures("stripped_" + name + "_log")));
            this.write("models/block/stripped_" + name + "_log_stairs_inner_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_z", lotStairsTextures("stripped_" + name + "_log")));

            this.write("models/item/stripped_" + name + "_log_stairs.json", this.modelFromParent("atbyw:block/stairs/stripped_" + name + "_log_stairs"));
            this.write("blockstates/stripped_" + name + "_log_stairs.json", this.writeColumnStairsBlockState("stripped_" + name + "_log_stairs"));
        });

        WoodNames.getNamesNether().forEach((name) -> {
            this.write("models/block/" + name + "_stem_stairs.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs", lotStairsTextures(name + "_stem")));
            this.write("models/block/" + name + "_stem_stairs_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_h_x", lotStairsTextures(name + "_stem")));
            this.write("models/block/" + name + "_stem_stairs_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_h_z", lotStairsTextures(name + "_stem")));
            this.write("models/block/" + name + "_stem_stairs_outer.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer", lotStairsTextures(name + "_stem")));
            this.write("models/block/" + name + "_stem_stairs_outer_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_x", lotStairsTextures(name + "_stem")));
            this.write("models/block/" + name + "_stem_stairs_outer_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_z", lotStairsTextures(name + "_stem")));
            this.write("models/block/" + name + "_stem_stairs_inner.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner", lotStairsTextures(name + "_stem")));
            this.write("models/block/" + name + "_stem_stairs_inner_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_x", lotStairsTextures(name + "_stem")));
            this.write("models/block/" + name + "_stem_stairs_inner_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_z", lotStairsTextures(name + "_stem")));

            this.write("models/item/" + name + "_stem_stairs.json", this.modelFromParent("atbyw:block/stairs/" + name + "_stem_stairs"));
            this.write("blockstates/" + name + "_stem_stairs.json", this.writeColumnStairsBlockState(name + "_stem_stairs"));

            this.write("models/block/stripped_" + name + "_stem_stairs.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs", lotStairsTextures("stripped_" + name + "_stem")));
            this.write("models/block/stripped_" + name + "_stem_stairs_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_h_x", lotStairsTextures("stripped_" + name + "_stem")));
            this.write("models/block/stripped_" + name + "_stem_stairs_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_h_z", lotStairsTextures("stripped_" + name + "_stem")));
            this.write("models/block/stripped_" + name + "_stem_stairs_outer.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer", lotStairsTextures("stripped_" + name + "_stem")));
            this.write("models/block/stripped_" + name + "_stem_stairs_outer_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_x", lotStairsTextures("stripped_" + name + "_stem")));
            this.write("models/block/stripped_" + name + "_stem_stairs_outer_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_z", lotStairsTextures("stripped_" + name + "_stem")));
            this.write("models/block/stripped_" + name + "_stem_stairs_inner.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner", lotStairsTextures("stripped_" + name + "_stem")));
            this.write("models/block/stripped_" + name + "_stem_stairs_inner_h_x.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_x", lotStairsTextures("stripped_" + name + "_stem")));
            this.write("models/block/stripped_" + name + "_stem_stairs_inner_h_z.json", this.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_z", lotStairsTextures("stripped_" + name + "_stem")));

            this.write("models/item/stripped_" + name + "_stem_stairs.json", this.modelFromParent("atbyw:block/stairs/stripped_" + name + "_stem_stairs"));
            this.write("blockstates/stripped_" + name + "_stem_stairs.json", this.writeColumnStairsBlockState("stripped_" + name + "_stem_stairs"));
        });
    }

    public void writeAll() {
        AtbywMain.MYS_LOGGER.info("3D Modeling :)");
    }
}
