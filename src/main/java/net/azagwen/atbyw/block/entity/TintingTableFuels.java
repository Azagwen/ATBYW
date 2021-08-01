package net.azagwen.atbyw.block.entity;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum TintingTableFuels {
    RED(Items.RED_DYE),
    GREEN(Items.GREEN_DYE),
    BLUE(Items.BLUE_DYE);

    private final Item item;
    TintingTableFuels(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }
}