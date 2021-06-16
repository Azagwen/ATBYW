package net.azagwen.atbyw.items;

import net.minecraft.item.Item;

import java.util.List;

import static net.azagwen.atbyw.main.AtbywMain.*;

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
