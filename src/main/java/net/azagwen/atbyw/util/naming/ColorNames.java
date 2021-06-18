package net.azagwen.atbyw.util.naming;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public enum ColorNames {
    WHITE(0, "white"),
    ORANGE(1, "orange"),
    MAGENTA(2, "magenta"),
    LIGHT_BLUE(3, "light_blue"),
    YELLOW(4, "yellow"),
    LIME(5, "lime"),
    PINK(6, "pink"),
    GRAY(7, "gray"),
    LIGHT_GRAY(8, "light_gray"),
    CYAN(9, "cyan"),
    PURPLE(10, "purple"),
    BLUE(11, "blue"),
    BROWN(12, "brown"),
    GREEN(13, "green"),
    RED(14, "red"),
    BLACK(15, "black");

    private final int id;
    private final String name;

    ColorNames(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static ColorNames getById(int id) {
        return Arrays.stream(ColorNames.values()).toList().get(id);
    }

    public static String getNameByID(int id) {
        return Arrays.stream(ColorNames.values()).toList().get(id).getName();
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
        for (ColorNames value : ColorNames.values()) {
            names.add(value.getName());
        }

        return names;
    }
}