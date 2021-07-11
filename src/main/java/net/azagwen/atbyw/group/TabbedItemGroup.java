package net.azagwen.atbyw.group;

import com.google.common.collect.Lists;
import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import java.util.List;

// This code was originally taken from https://github.com/Lemonszz/gubbins/blob/master/src/main/java/party/lemons/gubbins/itemgroup/TabbedItemGroup.java,
// which is licensed under MIT.
// and edited to match my needs & updated to 1.17.
public abstract class TabbedItemGroup extends ItemGroup {

    private int selectedTab = 0;
    private final List<ItemGroupTab> tabs = Lists.newArrayList();
    private boolean hasInitialized = false;

    protected TabbedItemGroup(Identifier id) {
        super(createTabIndex(), String.format("%s.%s", id.getNamespace(), id.getPath()));
    }

    public void initialize() {
        hasInitialized = true;
        initTabs(tabs);
    }

    protected abstract void initTabs(List<ItemGroupTab> tabs);

    @Override
    public void appendStacks(DefaultedList<ItemStack> stacks) {
        for (Item item : Registry.ITEM) {
            if (getSelectedTab().matches(item)) {
                stacks.add(new ItemStack(item));
            }
        }
    }

    public ItemGroupTab getSelectedTab() {
        return tabs.get(selectedTab);
    }

    public List<ItemGroupTab> getTabs() {
        return tabs;
    }

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }

    public int getSelectedTabIndex() {
        return selectedTab;
    }

    public boolean hasInitialized() {
        return hasInitialized;
    }

    private static int createTabIndex() {
        ((ItemGroupExtensions) ItemGroup.BUILDING_BLOCKS).fabric_expandArray();
        return ItemGroup.GROUPS.length - 1;
    }
}