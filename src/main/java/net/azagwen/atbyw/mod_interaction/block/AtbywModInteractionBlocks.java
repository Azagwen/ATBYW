package net.azagwen.atbyw.mod_interaction.block;

import net.azagwen.atbyw.block.BookshelfToggleBlock;
import net.minecraft.block.Block;

import static net.azagwen.atbyw.mod_interaction.util.BlockUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywModInteractionBlocks {

    public static final Block STALAGNATE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block REEDS_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block WILLOW_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block WART_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block RUBEUS_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block MUSHROOM_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block MUSHROOM_FIR_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block ANCHOR_TREE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block NETHER_SAKURA_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();

    public static final Block MOSSY_GLOWSROOM_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block PYTHADENDRON_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block END_LOTUS_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block LACUGROVE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block DRAGON_TREE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block TENANEA_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block HELIX_TREE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block UMBRELLA_TREE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();

    public static void initBookshelfToggles() {
        if (isModLoaded("betternether")) {
            Block[] toggles = new Block[] {
                    STALAGNATE_BOOKSHELF_TOGGLE,
                    REEDS_BOOKSHELF_TOGGLE,
                    WILLOW_BOOKSHELF_TOGGLE,
                    WART_BOOKSHELF_TOGGLE,
                    RUBEUS_BOOKSHELF_TOGGLE,
                    MUSHROOM_BOOKSHELF_TOGGLE,
                    MUSHROOM_FIR_BOOKSHELF_TOGGLE,
                    ANCHOR_TREE_BOOKSHELF_TOGGLE,
                    NETHER_SAKURA_BOOKSHELF_TOGGLE
            };

//            registerModInteractBlocks(false, ATBYW_REDSTONE, "bookshelf_toggle", BETTER_NETHER_WOOD_NAMES, toggles);
        }

        if (isModLoaded("betterend")) {
            Block[] toggles = new Block[]{
                    MOSSY_GLOWSROOM_BOOKSHELF_TOGGLE,
                    PYTHADENDRON_BOOKSHELF_TOGGLE,
                    END_LOTUS_BOOKSHELF_TOGGLE,
                    LACUGROVE_BOOKSHELF_TOGGLE,
                    DRAGON_TREE_BOOKSHELF_TOGGLE,
                    TENANEA_BOOKSHELF_TOGGLE,
                    HELIX_TREE_BOOKSHELF_TOGGLE,
                    UMBRELLA_TREE_BOOKSHELF_TOGGLE
            };

            registerModInteractBlocks(false, null, "bookshelf_toggle", BETTER_END_WOOD_NAMES, toggles);
        }
    }
}
