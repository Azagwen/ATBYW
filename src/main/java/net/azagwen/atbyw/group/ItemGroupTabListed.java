package net.azagwen.atbyw.group;

import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

// This code was taken from https://github.com/Lemonszz/gubbins/blob/master/src/main/java/party/lemons/gubbins/itemgroup/ItemTab.java,
// which is licensed under MIT.
public class ItemGroupTabListed {

    private final ArrayList<Item> itemList;
    private final ItemStack icon;
    private final String name;

    public ItemGroupTabListed(ItemStack icon, String name, ArrayList<Item> itemList) {
        this.itemList = itemList;
        this.icon = icon;
        this.name = name;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public boolean matches(Item item) {
        return itemList == null || itemList.contains(item);
    }

    public boolean matches(ItemStack stack) {
        return matches(stack.getItem());
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public String getTranslationKey() {
        return "itemGroup.subTab." + AtbywMain.nameSpace + "." + name;
    }
}