package net.azagwen.atbyw.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.awt.*;

public interface SimpleColoredItem {
    String COLOR_KEY = "color";
    String DISPLAY_KEY = "display";
    int DEFAULT_COLOR = Color.WHITE.getRGB();

    default boolean hasColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubTag(DISPLAY_KEY);
        return nbtCompound != null && nbtCompound.contains(COLOR_KEY, 99);
    }

    default int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubTag(DISPLAY_KEY);
        return nbtCompound != null && nbtCompound.contains(COLOR_KEY, 99) ? nbtCompound.getInt(COLOR_KEY) : DEFAULT_COLOR;
    }

    default void removeColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubTag(DISPLAY_KEY);
        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY)) {
            nbtCompound.remove(COLOR_KEY);
        }

    }

    default void setColor(ItemStack stack, int color) {
        stack.getOrCreateSubTag(DISPLAY_KEY).putInt(COLOR_KEY, color);
    }
}
