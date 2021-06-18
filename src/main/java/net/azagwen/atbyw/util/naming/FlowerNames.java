package net.azagwen.atbyw.util.naming;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public enum FlowerNames {
    DANDELION(0, "dandelion"),
    POPPY(1, "poppy"),
    BLUE_ORCHID(2, "blue_orchid"),
    ALLIUM(3, "allium"),
    AZURE_BLUET(4, "azure_bluet"),
    RED_TULIP(5, "red_tulip"),
    ORANGE_TULIP(6, "orange_tulip"),
    WHITE_TULIP(7, "white_tulip"),
    PINK_TULIP(8, "pink_tulip"),
    OXEYE_DAISY(9, "oxeye_daisy"),
    CORNFLOWER(10, "cornflower"),
    LILY_OF_THE_VALLEY(11, "lily_of_the_valley"),
    WITHER_ROSE(12, "wither_rose");

    private final int id;
    private final String name;

    FlowerNames(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static FlowerNames getById(int id) {
        return Arrays.stream(FlowerNames.values()).toList().get(id);
    }

    public static String getNameByID(int id) {
        return Arrays.stream(FlowerNames.values()).toList().get(id).getName();
    }

    public static List<String> getNamesInRange(int min, int max) {
        var names = Lists.<String>newArrayList();
        for (int i = min; i < max + 1; i++) {
            names.add(getById(i).getName());
        }

        return names;
    }

    public static List<String> getNames() {
        var names = Lists.<String>newArrayList();
        for (FlowerNames value : FlowerNames.values()) {
            names.add(value.getName());
        }

        return names;
    }
}