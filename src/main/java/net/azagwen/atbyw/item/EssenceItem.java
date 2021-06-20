package net.azagwen.atbyw.item;

import net.minecraft.item.Item;

import java.util.List;

public class EssenceItem extends Item {
    private final int color;

    public EssenceItem(int color, List<EssenceItem> essenceItems) {
        super(new Item.Settings());
        this.color = color;
        essenceItems.add(this);
    }

    public int getColor() {
        return this.color;
    }
}
