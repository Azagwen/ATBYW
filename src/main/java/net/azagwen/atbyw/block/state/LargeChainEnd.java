package net.azagwen.atbyw.block.state;

import net.minecraft.util.StringIdentifiable;

public enum LargeChainEnd implements StringIdentifiable {
    CONNECT("connect"),
    EMPTY("empty"),
    TRANSITION("transition");

    private final String name;

    LargeChainEnd(String name) {
        this.name = name;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }
}
