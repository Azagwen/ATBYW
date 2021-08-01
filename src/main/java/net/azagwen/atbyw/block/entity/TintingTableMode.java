package net.azagwen.atbyw.block.entity;

import net.minecraft.util.StringIdentifiable;

public enum TintingTableMode implements StringIdentifiable {
    HEX(0, "hex"),
    RGB(1, "rgb");

    private final int id;
    private final String name;
    TintingTableMode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String asString() {
        return this.name;
    }
}