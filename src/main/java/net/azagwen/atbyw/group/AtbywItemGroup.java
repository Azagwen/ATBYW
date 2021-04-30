package net.azagwen.atbyw.group;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.items.AtbywItems;
import net.azagwen.atbyw.main.AtbywTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.List;

// This code was taken from https://github.com/Lemonszz/gubbins/blob/master/src/main/java/party/lemons/gubbins/mixin/client/CreativeInventoryScreenMixin.java,
// which is licensed under MIT.
// and edited to match my needs.
public class AtbywItemGroup extends TabbedItemGroup {

    public AtbywItemGroup(Identifier id) {
        super(id);
    }

    public static Tag<Item> ATBYW_BLOCKS_TAB = AtbywTags.registerItemTag("tab_blocks");
    public static Tag<Item> ATBYW_DECO_TAB = AtbywTags.registerItemTag("tab_deco");
    public static Tag<Item> ATBYW_REDSTONE_TAB = AtbywTags.registerItemTag("tab_redstone");
    public static Tag<Item> ATBYW_MISC_TAB = AtbywTags.registerItemTag("tab_misc");

    public static ItemGroupTabTagged ATBYW_BLOCKS = new ItemGroupTabTagged(new ItemStack(AtbywBlocks.CYAN_CINDER_BLOCKS), "blocks", ATBYW_BLOCKS_TAB);
    public static ItemGroupTabTagged ATBYW_DECO = new ItemGroupTabTagged(new ItemStack(AtbywBlocks.CYAN_CINDER_BLOCKS_WALL), "decoration", ATBYW_DECO_TAB);
    public static ItemGroupTabTagged ATBYW_REDSTONE = new ItemGroupTabTagged(new ItemStack(AtbywBlocks.REDSTONE_LANTERN), "redstone", ATBYW_REDSTONE_TAB);
    public static ItemGroupTabTagged ATBYW_MISC = new ItemGroupTabTagged(new ItemStack(AtbywItems.ACACIA_STICK), "misc", ATBYW_MISC_TAB);

    @Override
    public void initTabs(List<ItemGroupTabTagged> tabs) {
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