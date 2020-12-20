package net.azagwen.atbyw.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

import static net.azagwen.atbyw.blocks.AtbywBlockUtils.*;
import static net.azagwen.atbyw.init.AtbywMain.*;

public class AtbywBlocks {

    public static Boolean always(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) { return true; }
    public static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) { return false; }
    public static boolean always(BlockState state, BlockView world, BlockPos pos) { return true; }
    public static boolean never(BlockState state, BlockView world, BlockPos pos) { return false; }

    private static FabricBlockSettings MakeBasalt() {
        return FabricBlockSettings.of(Material.STONE, MaterialColor.BLACK).requiresTool().breakByTool(FabricToolTags.PICKAXES).strength(1.25F, 4.2F).sounds(BlockSoundGroup.BASALT);
    }

    private static FabricBlockSettings MakeWoodenFenceDoor(Block copiedMatColor) {
        return FabricBlockSettings.of(Material.WOOD, copiedMatColor.getDefaultMaterialColor()).breakByTool(FabricToolTags.AXES).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
    }

    private static Block MakeStairs(Block copiedBlock, boolean requiresTool, Tag<Item> tag) {
        Block toolRequired = new StairsBlockSubClass(copiedBlock.getDefaultState(), FabricBlockSettings.copyOf(copiedBlock).requiresTool().breakByTool(tag));
        Block toolNotRequired = new StairsBlockSubClass(copiedBlock.getDefaultState(), FabricBlockSettings.copyOf(copiedBlock).breakByTool(tag));

        return requiresTool ? toolRequired : toolNotRequired;
    }

    public static final Block[] TESTBLOCK = AtbywBlockUtils.<SlabBlockSubClass>DeclareMultipleBlocks(3, Material.BAMBOO, MaterialColor.BROWN);

    //Technical Blocks
    public static final Block STERILE_DIRT = new SterileDirtBlock(FabricBlockSettings.of(Material.SOIL, MaterialColor.DIRT).breakByTool(FabricToolTags.SHOVELS).strength(0.5F).sounds(BlockSoundGroup.GRAVEL).ticksRandomly());
    public static final Block STERILE_NETHERRACK = new SterileNetherrackBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).ticksRandomly());
    public static final Block TICKING_DIRT = new Block(FabricBlockSettings.of(Material.SOIL, MaterialColor.DIRT).breakByTool(FabricToolTags.SHOVELS).strength(0.5F).sounds(BlockSoundGroup.GRAVEL).ticksRandomly());

    //Full Blocks
    public static final Block SPRUCE_BOOKSHELF = new Block(FabricBlockSettings.copyOf(Blocks.BOOKSHELF));
    public static final Block BIRCH_BOOKSHELF = new Block(FabricBlockSettings.copyOf(Blocks.BOOKSHELF));
    public static final Block JUNGLE_BOOKSHELF = new Block(FabricBlockSettings.copyOf(Blocks.BOOKSHELF));
    public static final Block ACACIA_BOOKSHELF = new Block(FabricBlockSettings.copyOf(Blocks.BOOKSHELF));
    public static final Block DARK_OAK_BOOKSHELF = new Block(FabricBlockSettings.copyOf(Blocks.BOOKSHELF));
    public static final Block CRIMSON_BOOKSHELF = new Block(FabricBlockSettings.copyOf(Blocks.BOOKSHELF));
    public static final Block WARPED_BOOKSHELF = new Block(FabricBlockSettings.copyOf(Blocks.BOOKSHELF));

    public static final Block OAK_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block SPRUCE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block BIRCH_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block JUNGLE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block ACACIA_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block DARK_OAK_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block CRIMSON_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block WARPED_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();

    public static final Block TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.TERRACOTTA.getDefaultState(), FabricBlockSettings.copy(Blocks.TERRACOTTA));
    public static final Block WHITE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.WHITE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.ORANGE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.ORANGE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.MAGENTA_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.MAGENTA_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.YELLOW_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.YELLOW_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.LIME_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIME_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.PINK_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.PINK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.GRAY_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.CYAN_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.CYAN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.PURPLE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.PURPLE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.BLUE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.BROWN_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BROWN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.GREEN_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.GREEN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.RED_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.RED_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.BLACK_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WHITE_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIME_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PINK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.CYAN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BROWN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.GREEN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.RED_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WHITE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.ORANGE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.MAGENTA_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.YELLOW_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.LIME_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.PINK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.CYAN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.PURPLE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.BROWN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.GREEN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.RED_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.TERRACOTTA.getDefaultState(), FabricBlockSettings.copy(Blocks.TERRACOTTA));
    public static final Block WHITE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.WHITE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.ORANGE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.ORANGE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.MAGENTA_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.MAGENTA_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.YELLOW_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.YELLOW_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.LIME_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIME_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.PINK_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.PINK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.GRAY_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.CYAN_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.CYAN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.PURPLE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.PURPLE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.BLUE_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.BROWN_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BROWN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.GREEN_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.GREEN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.RED_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.RED_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.BLACK_TERRACOTTA.getDefaultState(), FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WHITE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIME_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PINK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.CYAN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BROWN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.GREEN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.RED_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));

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

    public static final Block WHITE_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.ORANGE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.MAGENTA_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.YELLOW_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.LIME_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.PINK_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.GRAY_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.CYAN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.PURPLE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.BROWN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.GREEN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.RED_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_CINDER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));


    public static final Block BASALT_BRICKS = new Block(MakeBasalt());
    public static final Block BASALT_PILLAR = new PillarBlock(MakeBasalt());

    //Other Blocks
    public static final Block OAK_FENCE_DOOR = new FenceDoorBlock(MakeWoodenFenceDoor(Blocks.OAK_PLANKS));
    public static final Block SPRUCE_FENCE_DOOR = new FenceDoorBlock(MakeWoodenFenceDoor(Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_FENCE_DOOR = new FenceDoorBlock(MakeWoodenFenceDoor(Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_FENCE_DOOR = new FenceDoorBlock(MakeWoodenFenceDoor(Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_FENCE_DOOR = new FenceDoorBlock(MakeWoodenFenceDoor(Blocks.ACACIA_PLANKS));
    public static final Block DARK_OAK_FENCE_DOOR = new FenceDoorBlock(MakeWoodenFenceDoor(Blocks.DARK_OAK_PLANKS));
    public static final Block CRIMSON_FENCE_DOOR = new FenceDoorBlock(MakeWoodenFenceDoor(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_FENCE_DOOR = new FenceDoorBlock(MakeWoodenFenceDoor(Blocks.WARPED_PLANKS));
    public static final Block IRON_FENCE_DOOR = new FenceDoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_DOOR).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block SPRUCE_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block BIRCH_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block JUNGLE_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block ACACIA_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block DARK_OAK_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block CRIMSON_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block WARPED_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block BAMBOO_LADDER = new BambooLadderBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO));

    public static final Block DIRT_STAIRS = new DirtStairsBlock(FabricBlockSettings.of(Material.SOIL, MaterialColor.DIRT).breakByTool(FabricToolTags.SHOVELS).strength(0.5F).sounds(BlockSoundGroup.GRAVEL).ticksRandomly());
    public static final Block COARSE_DIRT_STAIRS = MakeStairs(Blocks.COARSE_DIRT, false, FabricToolTags.SHOVELS);
    public static final Block PODZOL_STAIRS = MakeStairs(Blocks.PODZOL, false, FabricToolTags.SHOVELS);
    public static final Block GRASS_BLOCK_STAIRS = new SpreadableStairsBlock(Blocks.GRASS_BLOCK, FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).breakByTool(FabricToolTags.SHOVELS).ticksRandomly());
    public static final Block GRASS_PATH_STAIRS = new GrassPathStairsBlock(Blocks.GRASS_PATH, FabricBlockSettings.copyOf(Blocks.GRASS_PATH).breakByTool(FabricToolTags.SHOVELS));
    public static final Block MYCELIUM_STAIRS = new SpreadableStairsBlock(Blocks.MYCELIUM, FabricBlockSettings.copyOf(Blocks.MYCELIUM).breakByTool(FabricToolTags.SHOVELS).ticksRandomly());
    public static final Block NETHERRACK_STAIRS = new NetherrackStairsBlock(Blocks.NETHERRACK, FabricBlockSettings.copyOf(Blocks.NETHERRACK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CRIMSON_NYLIUM_STAIRS = new NyliumStairsBlock(Blocks.CRIMSON_NYLIUM, FabricBlockSettings.copyOf(Blocks.CRIMSON_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WARPED_NYLIUM_STAIRS = new NyliumStairsBlock(Blocks.WARPED_NYLIUM, FabricBlockSettings.copyOf(Blocks.WARPED_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block DIRT_SLAB = new DirtSlabBlock(FabricBlockSettings.copyOf(Blocks.DIRT).breakByTool(FabricToolTags.SHOVELS));
    public static final Block COARSE_DIRT_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.COARSE_DIRT).breakByTool(FabricToolTags.SHOVELS));
    public static final Block PODZOL_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PODZOL).breakByTool(FabricToolTags.SHOVELS));
    public static final Block GRASS_BLOCK_SLAB = new SpreadableSlabBlock(Blocks.GRASS_BLOCK, FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).breakByTool(FabricToolTags.SHOVELS).ticksRandomly());
    public static final Block GRASS_PATH_SLAB = new GrassPathSlabBlock(FabricBlockSettings.copyOf(Blocks.GRASS_PATH).breakByTool(FabricToolTags.SHOVELS));
    public static final Block MYCELIUM_SLAB = new SpreadableSlabBlock(Blocks.MYCELIUM, FabricBlockSettings.copyOf(Blocks.MYCELIUM).breakByTool(FabricToolTags.SHOVELS).ticksRandomly());
    public static final Block NETHERRACK_SLAB = new NetherrackSlabBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CRIMSON_NYLIUM_SLAB = new NyliumSlabBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WARPED_NYLIUM_SLAB = new NyliumSlabBlock(FabricBlockSettings.copyOf(Blocks.WARPED_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block DEVELOPER_BLOCK = new DevBlock(FabricBlockSettings.of(Material.WOOL, MaterialColor.ORANGE).nonOpaque().breakByHand(true).strength(0.1F).sounds(BlockSoundGroup.BONE));

    public static void init() {
        Registry.register(Registry.BLOCK, newID("sterile_dirt"), STERILE_DIRT);
        Registry.register(Registry.BLOCK, newID("sterile_netherrack"), STERILE_NETHERRACK);

//        registerBlocks(false, ATBYW_BLOCKS, "grass_block_stairs", new String[] {"test111", "test222", "test333"}, TESTBLOCK);

        registerBlock(false, ATBYW_BLOCKS, "grass_block_stairs", GRASS_BLOCK_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "mycelium_stairs", MYCELIUM_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "podzol_stairs", PODZOL_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "grass_path_stairs", GRASS_PATH_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "dirt_stairs", DIRT_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "coarse_dirt_stairs", COARSE_DIRT_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "crimson_nylium_stairs", CRIMSON_NYLIUM_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "warped_nylium_stairs", WARPED_NYLIUM_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "netherrack_stairs", NETHERRACK_STAIRS);

        registerBlock(false, ATBYW_BLOCKS, "grass_block_slab", GRASS_BLOCK_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "mycelium_slab", MYCELIUM_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "podzol_slab", PODZOL_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "grass_path_slab", GRASS_PATH_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "dirt_slab", DIRT_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "coarse_dirt_slab", COARSE_DIRT_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "crimson_nylium_slab", CRIMSON_NYLIUM_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "warped_nylium_slab", WARPED_NYLIUM_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "netherrack_slab", NETHERRACK_SLAB);

        registerBlocks(false, ATBYW_REDSTONE, "fence_door", woodNames, new Block[] {
                OAK_FENCE_DOOR,
                SPRUCE_FENCE_DOOR,
                BIRCH_FENCE_DOOR,
                JUNGLE_FENCE_DOOR,
                ACACIA_FENCE_DOOR,
                DARK_OAK_FENCE_DOOR,
                CRIMSON_FENCE_DOOR,
                WARPED_FENCE_DOOR
        });
        registerBlock(false, ATBYW_REDSTONE, "iron_fence_door", IRON_FENCE_DOOR);

        registerBlock(false, ATBYW_BLOCKS, "spruce_bookshelf", SPRUCE_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "birch_bookshelf", BIRCH_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "jungle_bookshelf", JUNGLE_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "acacia_bookshelf", ACACIA_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "dark_oak_bookshelf", DARK_OAK_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "crimson_bookshelf", CRIMSON_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "warped_bookshelf", WARPED_BOOKSHELF);

        registerBlocks(false, ATBYW_REDSTONE, "bookshelf_toggle", woodNames, new Block[] {
                OAK_BOOKSHELF_TOGGLE,
                SPRUCE_BOOKSHELF_TOGGLE,
                BIRCH_BOOKSHELF_TOGGLE,
                JUNGLE_BOOKSHELF_TOGGLE,
                ACACIA_BOOKSHELF_TOGGLE,
                DARK_OAK_BOOKSHELF_TOGGLE,
                CRIMSON_BOOKSHELF_TOGGLE,
                WARPED_BOOKSHELF_TOGGLE
        });

        registerBlock(false, ATBYW_BLOCKS, "spruce_ladder", SPRUCE_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "birch_ladder", BIRCH_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "jungle_ladder", JUNGLE_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "acacia_ladder", ACACIA_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "dark_oak_ladder", DARK_OAK_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "crimson_ladder", CRIMSON_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "warped_ladder", WARPED_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "bamboo_ladder", BAMBOO_LADDER);

        registerBlock(false, ATBYW_BLOCKS, "terracotta_bricks", TERRACOTTA_BRICKS);
        registerBlocks(false, ATBYW_BLOCKS, "terracotta_bricks", colorNames, new Block[] {
                WHITE_TERRACOTTA_BRICKS,
                ORANGE_TERRACOTTA_BRICKS,
                MAGENTA_TERRACOTTA_BRICKS,
                LIGHT_BLUE_TERRACOTTA_BRICKS,
                YELLOW_TERRACOTTA_BRICKS,
                LIME_TERRACOTTA_BRICKS,
                PINK_TERRACOTTA_BRICKS,
                GRAY_TERRACOTTA_BRICKS,
                LIGHT_GRAY_TERRACOTTA_BRICKS,
                CYAN_TERRACOTTA_BRICKS,
                PURPLE_TERRACOTTA_BRICKS,
                BLUE_TERRACOTTA_BRICKS,
                BROWN_TERRACOTTA_BRICKS,
                GREEN_TERRACOTTA_BRICKS,
                RED_TERRACOTTA_BRICKS,
                BLACK_TERRACOTTA_BRICKS
        });


        registerBlock(false, ATBYW_BLOCKS, "terracotta_stairs", TERRACOTTA_STAIRS);
        registerBlocks(false, ATBYW_BLOCKS, "terracotta_stairs", colorNames, new Block[] {
                WHITE_TERRACOTTA_STAIRS,
                ORANGE_TERRACOTTA_STAIRS,
                MAGENTA_TERRACOTTA_STAIRS,
                LIGHT_BLUE_TERRACOTTA_STAIRS,
                YELLOW_TERRACOTTA_STAIRS,
                LIME_TERRACOTTA_STAIRS,
                PINK_TERRACOTTA_STAIRS,
                GRAY_TERRACOTTA_STAIRS,
                LIGHT_GRAY_TERRACOTTA_STAIRS,
                CYAN_TERRACOTTA_STAIRS,
                PURPLE_TERRACOTTA_STAIRS,
                BLUE_TERRACOTTA_STAIRS,
                BROWN_TERRACOTTA_STAIRS,
                GREEN_TERRACOTTA_STAIRS,
                RED_TERRACOTTA_STAIRS,
                BLACK_TERRACOTTA_STAIRS
        });

        registerBlock(false, ATBYW_BLOCKS, "terracotta_slab", TERRACOTTA_SLAB);
        registerBlocks(false, ATBYW_BLOCKS, "terracotta_slab", colorNames, new Block[] {
                WHITE_TERRACOTTA_SLAB,
                ORANGE_TERRACOTTA_SLAB,
                MAGENTA_TERRACOTTA_SLAB,
                LIGHT_BLUE_TERRACOTTA_SLAB,
                YELLOW_TERRACOTTA_SLAB,
                LIME_TERRACOTTA_SLAB,
                PINK_TERRACOTTA_SLAB,
                GRAY_TERRACOTTA_SLAB,
                LIGHT_GRAY_TERRACOTTA_SLAB,
                CYAN_TERRACOTTA_SLAB,
                PURPLE_TERRACOTTA_SLAB,
                BLUE_TERRACOTTA_SLAB,
                BROWN_TERRACOTTA_SLAB,
                GREEN_TERRACOTTA_SLAB,
                RED_TERRACOTTA_SLAB,
                BLACK_TERRACOTTA_SLAB
        });

        registerBlock(false, ATBYW_BLOCKS, "terracotta_bricks_stairs", TERRACOTTA_BRICKS_STAIRS);
        registerBlocks(false, ATBYW_BLOCKS, "terracotta_bricks_stairs", colorNames, new Block[] {
                WHITE_TERRACOTTA_BRICKS_STAIRS,
                ORANGE_TERRACOTTA_BRICKS_STAIRS,
                MAGENTA_TERRACOTTA_BRICKS_STAIRS,
                LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS,
                YELLOW_TERRACOTTA_BRICKS_STAIRS,
                LIME_TERRACOTTA_BRICKS_STAIRS,
                PINK_TERRACOTTA_BRICKS_STAIRS,
                GRAY_TERRACOTTA_BRICKS_STAIRS,
                LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS,
                CYAN_TERRACOTTA_BRICKS_STAIRS,
                PURPLE_TERRACOTTA_BRICKS_STAIRS,
                BLUE_TERRACOTTA_BRICKS_STAIRS,
                BROWN_TERRACOTTA_BRICKS_STAIRS,
                GREEN_TERRACOTTA_BRICKS_STAIRS,
                RED_TERRACOTTA_BRICKS_STAIRS,
                BLACK_TERRACOTTA_BRICKS_STAIRS
        });

        registerBlock(false, ATBYW_BLOCKS, "terracotta_bricks_slab", TERRACOTTA_BRICKS_SLAB);
        registerBlocks(false, ATBYW_BLOCKS, "terracotta_bricks_slab", colorNames, new Block[] {
                WHITE_TERRACOTTA_BRICKS_SLAB,
                ORANGE_TERRACOTTA_BRICKS_SLAB,
                MAGENTA_TERRACOTTA_BRICKS_SLAB,
                LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB,
                YELLOW_TERRACOTTA_BRICKS_SLAB,
                LIME_TERRACOTTA_BRICKS_SLAB,
                PINK_TERRACOTTA_BRICKS_SLAB,
                GRAY_TERRACOTTA_BRICKS_SLAB,
                LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB,
                CYAN_TERRACOTTA_BRICKS_SLAB,
                PURPLE_TERRACOTTA_BRICKS_SLAB,
                BLUE_TERRACOTTA_BRICKS_SLAB,
                BROWN_TERRACOTTA_BRICKS_SLAB,
                GREEN_TERRACOTTA_BRICKS_SLAB,
                RED_TERRACOTTA_BRICKS_SLAB,
                BLACK_TERRACOTTA_BRICKS_SLAB
        });

        registerBlock(false, ATBYW_BLOCKS, "terracotta_bricks_wall", TERRACOTTA_BRICKS_WALL);
        registerBlocks(false, ATBYW_BLOCKS, "terracotta_bricks_wall", colorNames, new Block[] {
                WHITE_TERRACOTTA_BRICKS_WALL,
                ORANGE_TERRACOTTA_BRICKS_WALL,
                MAGENTA_TERRACOTTA_BRICKS_WALL,
                LIGHT_BLUE_TERRACOTTA_BRICKS_WALL,
                YELLOW_TERRACOTTA_BRICKS_WALL,
                LIME_TERRACOTTA_BRICKS_WALL,
                PINK_TERRACOTTA_BRICKS_WALL,
                GRAY_TERRACOTTA_BRICKS_WALL,
                LIGHT_GRAY_TERRACOTTA_BRICKS_WALL,
                CYAN_TERRACOTTA_BRICKS_WALL,
                PURPLE_TERRACOTTA_BRICKS_WALL,
                BLUE_TERRACOTTA_BRICKS_WALL,
                BROWN_TERRACOTTA_BRICKS_WALL,
                GREEN_TERRACOTTA_BRICKS_WALL,
                RED_TERRACOTTA_BRICKS_WALL,
                BLACK_TERRACOTTA_BRICKS_WALL
        });

        registerBlock(false, ATBYW_BLOCKS, "basalt_bricks", BASALT_BRICKS);
        registerBlock(false, ATBYW_BLOCKS, "basalt_pillar", BASALT_PILLAR);
        registerBlock(false, ATBYW_MISC, "dev_block", DEVELOPER_BLOCK);


    }
}
