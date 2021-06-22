package net.azagwen.atbyw.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public record ItemOperationResult(Block result, Item loot, int damage, int decrement, Identifier sound) {

    @Override
    public Block result() {
        return result;
    }

    @Override
    public Item loot() {
        return loot;
    }

    @Override
    public int damage() {
        return damage;
    }

    @Override
    public int decrement() {
        return decrement;
    }

    @Override
    public Identifier sound() {
        return sound;
    }
}
