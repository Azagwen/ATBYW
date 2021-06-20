package net.azagwen.atbyw.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;

public class BlockToBlockOperation {
    private Object usedItem;
    private int usedItemDamage;
    private int usedItemDecrement;
    private Block original;
    private Block result;
    private Item loot;
    private SoundEvent operationSound;

    public BlockToBlockOperation(Object usedItem, int usedItemDamage, int usedItemDecrement, Block original, Block result, @Nullable Item loot, @Nullable SoundEvent operationSound) {
        this.usedItem = usedItem;
        this.usedItemDamage = usedItemDamage;
        this.usedItemDecrement = usedItemDecrement;
        this.original = original;
        this.result = result;
        this.loot = loot;
        this.operationSound = operationSound;
    }

    public BlockToBlockOperation() {}

    @Override
    public String toString() {
        var soundID = Registry.SOUND_EVENT.getId(operationSound);

        return "BlockToBlockOperation{ \n" +
                "original = " + original + ",\n" +
                "result = " + result + ",\n" +
                "loot = " + loot + ",\n" +
                "operationSound = " + soundID + "\n" +
                "}";
    }

    public void setUsedItem(Object usedItem) {
        this.usedItem = usedItem;
    }

    public void setUsedItemDamage(int usedItemDamage) {
        this.usedItemDamage = usedItemDamage;
    }

    public void setUsedItemDecrement(int usedItemDecrement) {
        this.usedItemDecrement = usedItemDecrement;
    }

    public void setOriginal(Block original) {
        this.original = original;
    }

    public void setResult(Block result) {
        this.result = result;
    }

    public void setLoot(Item loot) {
        this.loot = loot;
    }

    public void setOperationSound(SoundEvent operationSound) {
        this.operationSound = operationSound;
    }

    public Object getUsedItem() {
        return usedItem;
    }

    public int getUsedItemDamage() {
        return usedItemDamage;
    }

    public int getUsedItemDecrement() {
        return usedItemDecrement;
    }

    public Block getOriginal() {
        return original;
    }

    public Block getResult() {
        return result;
    }

    public Item getLoot() {
        return loot;
    }

    public SoundEvent getOperationSound() {
        return operationSound;
    }
}
