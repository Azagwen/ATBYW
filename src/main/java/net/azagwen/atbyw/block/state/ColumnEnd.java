package net.azagwen.atbyw.block.state;

import net.minecraft.util.StringIdentifiable;

public enum ColumnEnd implements StringIdentifiable {
    TOP("v1"),
    MIDDLE("middle"),
    BOTTOM("v2"),
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
