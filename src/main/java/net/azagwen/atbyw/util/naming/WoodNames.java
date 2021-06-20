package net.azagwen.atbyw.util.naming;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public enum WoodNames  {
    OAK(0, "oak"),
    SPRUCE(1, "spruce"),
    BIRCH(2, "birch"),
    JUNGLE(3, "jungle"),
    ACACIA(4, "acacia"),
    DARK_OAK(5, "dark_oak"),
    CRIMSON(6, "crimson"),
    WARPED(7, "warped");

    private final int id;
    private final String name;

    WoodNames(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static WoodNames getById(int id) {
        return Arrays.stream(WoodNames.values()).toList().get(id);
    }

    public static String getNameByID(int id) {
        return Arrays.stream(WoodNames.values()).toList().get(id).getName();
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
        for (WoodNames value : WoodNames.values()) {
            names.add(value.getName());
        }

        return names;
    }

    public static List<String> getNamesNoOak() {
        return getNamesInRange(1, 7);
    }

    public static List<String> getNamesOverworld() {
        return getNamesInRange(0, 5);
    }

    public static List<String> getNamesNether() {
        return getNamesInRange(6, 7);
    }
}