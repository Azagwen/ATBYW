package net.azagwen.atbyw.blocks.state;

import net.minecraft.util.StringIdentifiable;

public enum PillarEnd implements StringIdentifiable {
    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom"),
    SINGLE("single");

    private final String name;

    private PillarEnd(String name) {
        this.name = name;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }
}
