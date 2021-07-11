package net.azagwen.atbyw.block.state;

import net.minecraft.util.StringIdentifiable;

public enum ColumnEnd implements StringIdentifiable {
    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom"),
    SINGLE("single");

    private final String name;

    ColumnEnd(String name) {
        this.name = name;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }
}
