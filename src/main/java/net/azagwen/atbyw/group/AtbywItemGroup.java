package net.azagwen.atbyw.group;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.items.AtbywItems;
import net.azagwen.atbyw.main.AtbywTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.azagwen.atbyw.main.AtbywMain.AtbywID;

// This code was taken from https://github.com/Lemonszz/gubbins/blob/master/src/main/java/party/lemons/gubbins/mixin/client/CreativeInventoryScreenMixin.java,
// which is licensed under MIT.
// and edited to match my needs.
public class AtbywItemGroup extends TabbedItemGroup {

    public AtbywItemGroup(Identifier id) {
        super(id);
    }

    public static final Tag<Item> ATBYW_BLOCKS_TAB = AtbywTags.registerItemTag("tab_blocks");
    public static final Tag<Item> ATBYW_DECO_TAB = AtbywTags.registerItemTag("tab_deco");
    public static final Tag<Item> ATBYW_REDSTONE_TAB = AtbywTags.registerItemTag("tab_redstone");
    public static final Tag<Item> ATBYW_MISC_TAB = AtbywTags.registerItemTag("tab_misc");

    public static final ItemTab ATBYW_BLOCKS = new ItemTab(new ItemStack(AtbywBlocks.CYAN_CINDER_BLOCKS), "blocks", ATBYW_BLOCKS_TAB);
    public static final ItemTab ATBYW_DECO = new ItemTab(new ItemStack(AtbywBlocks.CYAN_CINDER_BLOCKS_WALL), "decoration", ATBYW_DECO_TAB);
    public static final ItemTab ATBYW_REDSTONE = new ItemTab(new ItemStack(AtbywBlocks.REDSTONE_LANTERN), "redstone", ATBYW_REDSTONE_TAB);
    public static final ItemTab ATBYW_MISC = new ItemTab(new ItemStack(AtbywItems.ACACIA_STICK), "misc", ATBYW_MISC_TAB);

    @Override
    public void initTabs(List<ItemTab> tabs) {
        tabs.add(ATBYW_BLOCKS);
        tabs.add(ATBYW_DECO);
        tabs.add(ATBYW_REDSTONE);
        tabs.add(ATBYW_MISC);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(AtbywBlocks.TERRACOTTA_BRICKS);
    }
}