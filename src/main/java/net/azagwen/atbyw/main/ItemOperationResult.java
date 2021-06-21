package net.azagwen.atbyw.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;

public record ItemOperationResult(Block result, Item loot, int damage, int decrement, SoundEvent sound) {

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
    public SoundEvent sound() {
        return sound;
    }
}
