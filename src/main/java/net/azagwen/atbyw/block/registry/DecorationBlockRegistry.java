package net.azagwen.atbyw.block.registry;

import net.azagwen.atbyw.block.*;
import net.azagwen.atbyw.block.sign.AtbywSignType;
import net.azagwen.atbyw.block.sign.SignBlockExt;
import net.azagwen.atbyw.block.sign.WallSignBlockExt;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.item.CanvasBlockItem;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.AtbywUtils;
import net.azagwen.atbyw.util.naming.WoodNames;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

import static net.azagwen.atbyw.main.AtbywMain.DECO_TAB;
import static net.azagwen.atbyw.util.BlockUtils.registerBlock;
import static net.azagwen.atbyw.util.BlockUtils.registerBlocks;

public class DecorationBlockRegistry extends AtbywBlocks {
    public static final Block TINTING_TABLE = new TintingTableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).requiresTool().breakByTool(FabricToolTags.PICKAXES).sounds(BlockSoundGroup.COPPER));
    public static final Block CANVAS_BLOCK = createCanvasBlock(false);
    public static final Block GLOWING_CANVAS_BLOCK = createCanvasBlock(true);
    public static final Block SPRUCE_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block BIRCH_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block JUNGLE_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block ACACIA_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block DARK_OAK_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block CRIMSON_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block WARPED_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block BAMBOO_LADDER = new BambooLadderBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO));
    public static final Block CACTUS_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_FENCE = new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_SIGN = new SignBlockExt(AtbywSignType.CACTUS, FabricBlockSettings.copyOf(Blocks.OAK_SIGN).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_WALL_SIGN = new WallSignBlockExt(AtbywSignType.CACTUS, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).breakByTool(FabricToolTags.AXES));
    public static final Block COMPACTED_SNOW = new SnowBlockSubClass(FabricBlockSettings.of(Material.SNOW_LAYER).strength(0.2F).requiresTool().breakByTool(FabricToolTags.SHOVELS).sounds(BlockSoundGroup.SNOW));
    public static final Block LARGE_CHAIN = new LargeChainBlock(FabricBlockSettings.copyOf(Blocks.CHAIN).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRANITE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.GRANITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block DIORITE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.DIORITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ANDESITE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.ANDESITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block SANDSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CHISELED_SANDSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_SANDSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CHISELED_RED_SANDSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPUR_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block STONE_BRICKS_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MOSSY_STONE_BRICKS_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CRACKED_STONE_BRICKS_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.CRACKED_STONE_BRICKS).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block NETHER_BRICKS_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block QUARTZ_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PRISMARINE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.PRISMARINE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACKSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.BLACKSTONE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ACACIA_RAILING = new RailingBlock(AtbywMain.id("acacia_railing"), FabricBlockSettings.copyOf(Blocks.ACACIA_FENCE));
    public static final Block TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WHITE_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.LIME_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.PINK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.CYAN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.BROWN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.GREEN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.RED_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WHITE_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.LIME_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.PINK_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.GRAY_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.CYAN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.BROWN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.GREEN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.RED_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_CINDER_BLOCKS_WALL = new CinderBlocksWallBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    private static CanvasBlock createCanvasBlock(boolean glowing) {
        return new CanvasBlock(glowing, FabricBlockSettings.of(Material.WOOL).strength(0.5F).sounds(BlockSoundGroup.WOOL));
    }

    public static void registerCanvasBlock(ArrayList<Item> itemTab, String name, Block block) {
        Registry.register(Registry.BLOCK, AtbywMain.id(name), block);
        Registry.register(Registry.ITEM, AtbywMain.id(name), new CanvasBlockItem(block, new Item.Settings()));

        itemTab.add(block.asItem());
    }

    public static void registerSign(ArrayList<Item> itemTab, Block standingSign, Block wallSign) {
        var type = ((SignBlockExt) standingSign).getType().getName();
        Registry.register(Registry.BLOCK, AtbywMain.id(type + "_sign"), standingSign);
        Registry.register(Registry.BLOCK, AtbywMain.id(type + "_wall_sign"), wallSign);
        Registry.register(Registry.ITEM, AtbywMain.id(type + "_sign"), new SignItem(new Item.Settings(), standingSign, wallSign));

        itemTab.add(standingSign.asItem());
    }

    public static void init() {
        registerBlock(false, DECO_TAB, "tinting_table", TINTING_TABLE);
        registerCanvasBlock(DECO_TAB, "canvas_block", CANVAS_BLOCK);
        registerCanvasBlock(DECO_TAB, "glowing_canvas_block", GLOWING_CANVAS_BLOCK);
        registerBlocks(false, DECO_TAB, "ladder", WoodNames.getNamesNoOak(), SPRUCE_LADDER, BIRCH_LADDER, JUNGLE_LADDER, ACACIA_LADDER, DARK_OAK_LADDER, CRIMSON_LADDER, WARPED_LADDER);
        registerBlock(false, DECO_TAB, "bamboo_ladder", BAMBOO_LADDER);
        registerBlock(false, DECO_TAB, "cactus_ladder", CACTUS_LADDER);
        registerBlock(false, DECO_TAB, "cactus_fence", CACTUS_FENCE);
        registerSign(DECO_TAB, CACTUS_SIGN, CACTUS_WALL_SIGN);
        registerBlock(false, DECO_TAB, "compacted_snow", COMPACTED_SNOW);
        registerBlock(false, DECO_TAB, "large_chain", LARGE_CHAIN);
        registerBlock(false, DECO_TAB, "granite_column", GRANITE_COLUMN);
        registerBlock(false, DECO_TAB, "diorite_column", DIORITE_COLUMN);
        registerBlock(false, DECO_TAB, "andesite_column", ANDESITE_COLUMN);
        registerBlock(false, DECO_TAB, "sandstone_column", SANDSTONE_COLUMN);
        registerBlock(false, DECO_TAB, "chiseled_sandstone_column", CHISELED_SANDSTONE_COLUMN);
        registerBlock(false, DECO_TAB, "red_sandstone_column", RED_SANDSTONE_COLUMN);
        registerBlock(false, DECO_TAB, "chiseled_red_sandstone_column", CHISELED_RED_SANDSTONE_COLUMN);
        registerBlock(false, DECO_TAB, "purpur_column", PURPUR_COLUMN);
        registerBlock(false, DECO_TAB, "stone_bricks_column", STONE_BRICKS_COLUMN);
        registerBlock(false, DECO_TAB, "mossy_stone_bricks_column", MOSSY_STONE_BRICKS_COLUMN);
        registerBlock(false, DECO_TAB, "cracked_stone_bricks_column", CRACKED_STONE_BRICKS_COLUMN);
        registerBlock(false, DECO_TAB, "nether_bricks_column", NETHER_BRICKS_COLUMN);
        registerBlock(false, DECO_TAB, "quartz_column", QUARTZ_COLUMN);
        registerBlock(false, DECO_TAB, "prismarine_column", PRISMARINE_COLUMN);
        registerBlock(false, DECO_TAB, "blackstone_column", BLACKSTONE_COLUMN);
        registerBlock(false, "acacia_railing", ACACIA_RAILING);
        registerBlock(false, DECO_TAB, "terracotta_bricks_wall", TERRACOTTA_BRICKS_WALL);
        registerBlocks(false, DECO_TAB, "terracotta_bricks_wall", AtbywUtils.dyeColorNames(), WHITE_TERRACOTTA_BRICKS_WALL, ORANGE_TERRACOTTA_BRICKS_WALL, MAGENTA_TERRACOTTA_BRICKS_WALL, LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, YELLOW_TERRACOTTA_BRICKS_WALL, LIME_TERRACOTTA_BRICKS_WALL, PINK_TERRACOTTA_BRICKS_WALL, GRAY_TERRACOTTA_BRICKS_WALL, LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, CYAN_TERRACOTTA_BRICKS_WALL, PURPLE_TERRACOTTA_BRICKS_WALL, BLUE_TERRACOTTA_BRICKS_WALL, BROWN_TERRACOTTA_BRICKS_WALL, GREEN_TERRACOTTA_BRICKS_WALL, RED_TERRACOTTA_BRICKS_WALL, BLACK_TERRACOTTA_BRICKS_WALL);
        registerBlocks(false, DECO_TAB, "cinder_blocks_wall", AtbywUtils.dyeColorNames(), WHITE_CINDER_BLOCKS_WALL, ORANGE_CINDER_BLOCKS_WALL, MAGENTA_CINDER_BLOCKS_WALL, LIGHT_BLUE_CINDER_BLOCKS_WALL, YELLOW_CINDER_BLOCKS_WALL, LIME_CINDER_BLOCKS_WALL, PINK_CINDER_BLOCKS_WALL, GRAY_CINDER_BLOCKS_WALL, LIGHT_GRAY_CINDER_BLOCKS_WALL, CYAN_CINDER_BLOCKS_WALL, PURPLE_CINDER_BLOCKS_WALL, BLUE_CINDER_BLOCKS_WALL, BROWN_CINDER_BLOCKS_WALL, GREEN_CINDER_BLOCKS_WALL, RED_CINDER_BLOCKS_WALL, BLACK_CINDER_BLOCKS_WALL);

        StatueRegistry.initStatues();
    }
}
