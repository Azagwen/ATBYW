package net.azagwen.atbyw.dev_tools;

import com.google.gson.JsonObject;
import net.azagwen.atbyw.util.naming.WoodNames;

import java.util.Map;

public class ModelWave000 {

    public JsonObject writeColumnStairsBlockState(String blockName) {
        return BlockStatesMethods.blockState(Map.<String, JsonObject>ofEntries(
                //Straight X
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=straight", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=straight", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=straight", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=straight", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=straight", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=straight", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=straight", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=straight", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 180, 180)),

                //Straight Y
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=straight", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName, 270, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=straight", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName, 0, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=straight", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName, 90, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=straight", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName, 180, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=straight", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName, 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=straight", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName, 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=straight", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName, 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=straight", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName, 180, 180, true)),

                //Straight Z
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=straight", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=straight", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=straight", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=straight", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=straight", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=straight", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=straight", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=straight", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_h_z", 180, 180)),

                //Inner X
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 180, 180)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 180, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 270, 180)),

                //Inner Y
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 180, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 270, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 0, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 90, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 180, 180, true)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 270, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 0, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 90, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 180, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner", 180, 180, true)),

                //Inner Z
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 180, 180)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=inner_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=inner_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=inner_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=inner_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=inner_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=inner_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=inner_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_z", 180, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=inner_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_inner_h_x", 270, 180)),

                //Outer X
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_left", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 180, 180)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 0, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 90, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 180, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_right", "axis=x"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 270, 180)),

                //Outer Y
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 180, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 270, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 0, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 90, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_left", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 180, 180, true)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 270, 0, true)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 0, 0, true)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 90, 0, true)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 180, 0, true)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 270, 180, true)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 0, 180, true)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 90, 180, true)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_right", "axis=y"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer", 180, 180, true)),

                //Outer Z
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 270, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_left", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 180, 180)),
                Map.entry(String.join(",", "facing=north", "half=bottom", "shape=outer_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 270, 0)),
                Map.entry(String.join(",", "facing=east",  "half=bottom", "shape=outer_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 0, 0)),
                Map.entry(String.join(",", "facing=south", "half=bottom", "shape=outer_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 90, 0)),
                Map.entry(String.join(",", "facing=west",  "half=bottom", "shape=outer_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 180, 0)),
                Map.entry(String.join(",", "facing=north", "half=top", "shape=outer_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 0, 180)),
                Map.entry(String.join(",", "facing=east",  "half=top", "shape=outer_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 90, 180)),
                Map.entry(String.join(",", "facing=south", "half=top", "shape=outer_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_z", 180, 180)),
                Map.entry(String.join(",", "facing=west",  "half=top", "shape=outer_right", "axis=z"), BlockStatesMethods.blockStateVariant("atbyw:block/stairs/" + blockName + "_outer_h_x", 270, 180))
        ));
    }

    protected Map<String, String> logStairsTextures(String blockName) {
        return Map.<String, String>ofEntries(
                Map.entry("top", "minecraft:block/" + blockName + "_top"),
                Map.entry("side", "minecraft:block/" + blockName),
                Map.entry("cut", "atbyw:block/" + blockName + "_cut")
        );
    }

    private void writeLogStairs() {
        var writer = new AutoModelWriter();

        WoodNames.getNamesOverworld().forEach((name) -> {
            writer.write("models/block/" + name + "_log_stairs.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs", logStairsTextures(name + "_log")));
            writer.write("models/block/" + name + "_log_stairs_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_h_x", logStairsTextures(name + "_log")));
            writer.write("models/block/" + name + "_log_stairs_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_h_z", logStairsTextures(name + "_log")));
            writer.write("models/block/" + name + "_log_stairs_outer.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer", logStairsTextures(name + "_log")));
            writer.write("models/block/" + name + "_log_stairs_outer_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_x", logStairsTextures(name + "_log")));
            writer.write("models/block/" + name + "_log_stairs_outer_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_z", logStairsTextures(name + "_log")));
            writer.write("models/block/" + name + "_log_stairs_inner.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner", logStairsTextures(name + "_log")));
            writer.write("models/block/" + name + "_log_stairs_inner_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_x", logStairsTextures(name + "_log")));
            writer.write("models/block/" + name + "_log_stairs_inner_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_z", logStairsTextures(name + "_log")));

            writer.write("models/item/" + name + "_log_stairs.json", ModelMethods.modelFromParent("atbyw:block/stairs/" + name + "_log_stairs"));
            writer.write("blockstates/" + name + "_log_stairs.json", this.writeColumnStairsBlockState(name + "_log_stairs"));

            writer.write("models/block/stripped_" + name + "_log_stairs.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs", logStairsTextures("stripped_" + name + "_log")));
            writer.write("models/block/stripped_" + name + "_log_stairs_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_h_x", logStairsTextures("stripped_" + name + "_log")));
            writer.write("models/block/stripped_" + name + "_log_stairs_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_h_z", logStairsTextures("stripped_" + name + "_log")));
            writer.write("models/block/stripped_" + name + "_log_stairs_outer.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer", logStairsTextures("stripped_" + name + "_log")));
            writer.write("models/block/stripped_" + name + "_log_stairs_outer_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_x", logStairsTextures("stripped_" + name + "_log")));
            writer.write("models/block/stripped_" + name + "_log_stairs_outer_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_z", logStairsTextures("stripped_" + name + "_log")));
            writer.write("models/block/stripped_" + name + "_log_stairs_inner.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner", logStairsTextures("stripped_" + name + "_log")));
            writer.write("models/block/stripped_" + name + "_log_stairs_inner_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_x", logStairsTextures("stripped_" + name + "_log")));
            writer.write("models/block/stripped_" + name + "_log_stairs_inner_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_z", logStairsTextures("stripped_" + name + "_log")));

            writer.write("models/item/stripped_" + name + "_log_stairs.json", ModelMethods.modelFromParent("atbyw:block/stairs/stripped_" + name + "_log_stairs"));
            writer.write("blockstates/stripped_" + name + "_log_stairs.json", this.writeColumnStairsBlockState("stripped_" + name + "_log_stairs"));
        });

        WoodNames.getNamesNether().forEach((name) -> {
            writer.write("models/block/" + name + "_stem_stairs.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs", logStairsTextures(name + "_stem")));
            writer.write("models/block/" + name + "_stem_stairs_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_h_x", logStairsTextures(name + "_stem")));
            writer.write("models/block/" + name + "_stem_stairs_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_h_z", logStairsTextures(name + "_stem")));
            writer.write("models/block/" + name + "_stem_stairs_outer.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer", logStairsTextures(name + "_stem")));
            writer.write("models/block/" + name + "_stem_stairs_outer_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_x", logStairsTextures(name + "_stem")));
            writer.write("models/block/" + name + "_stem_stairs_outer_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_z", logStairsTextures(name + "_stem")));
            writer.write("models/block/" + name + "_stem_stairs_inner.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner", logStairsTextures(name + "_stem")));
            writer.write("models/block/" + name + "_stem_stairs_inner_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_x", logStairsTextures(name + "_stem")));
            writer.write("models/block/" + name + "_stem_stairs_inner_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_z", logStairsTextures(name + "_stem")));

            writer.write("models/item/" + name + "_stem_stairs.json", ModelMethods.modelFromParent("atbyw:block/stairs/" + name + "_stem_stairs"));
            writer.write("blockstates/" + name + "_stem_stairs.json", this.writeColumnStairsBlockState(name + "_stem_stairs"));

            writer.write("models/block/stripped_" + name + "_stem_stairs.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs", logStairsTextures("stripped_" + name + "_stem")));
            writer.write("models/block/stripped_" + name + "_stem_stairs_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_h_x", logStairsTextures("stripped_" + name + "_stem")));
            writer.write("models/block/stripped_" + name + "_stem_stairs_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_h_z", logStairsTextures("stripped_" + name + "_stem")));
            writer.write("models/block/stripped_" + name + "_stem_stairs_outer.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer", logStairsTextures("stripped_" + name + "_stem")));
            writer.write("models/block/stripped_" + name + "_stem_stairs_outer_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_x", logStairsTextures("stripped_" + name + "_stem")));
            writer.write("models/block/stripped_" + name + "_stem_stairs_outer_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_outer_h_z", logStairsTextures("stripped_" + name + "_stem")));
            writer.write("models/block/stripped_" + name + "_stem_stairs_inner.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner", logStairsTextures("stripped_" + name + "_stem")));
            writer.write("models/block/stripped_" + name + "_stem_stairs_inner_h_x.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_x", logStairsTextures("stripped_" + name + "_stem")));
            writer.write("models/block/stripped_" + name + "_stem_stairs_inner_h_z.json", ModelMethods.modelFromParent("atbyw:block/stairs/template_column_stairs_inner_h_z", logStairsTextures("stripped_" + name + "_stem")));

            writer.write("models/item/stripped_" + name + "_stem_stairs.json", ModelMethods.modelFromParent("atbyw:block/stairs/stripped_" + name + "_stem_stairs"));
            writer.write("blockstates/stripped_" + name + "_stem_stairs.json", this.writeColumnStairsBlockState("stripped_" + name + "_stem_stairs"));
        });
    }
}