package net.azagwen.atbyw.items;

import net.minecraft.item.Item;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class EssenceItem extends Item {
    private final int color;

    public EssenceItem(int color) {
        super(new Item.Settings());
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }
}
