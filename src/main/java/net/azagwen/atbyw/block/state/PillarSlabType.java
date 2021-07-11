package net.azagwen.atbyw.block.state;

import net.minecraft.util.StringIdentifiable;

public enum PillarSlabType implements StringIdentifiable {
    NONE("none"),
    X("x"),
    Y("y"),
    Z("z");

    private final String name;

    PillarSlabType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }
}