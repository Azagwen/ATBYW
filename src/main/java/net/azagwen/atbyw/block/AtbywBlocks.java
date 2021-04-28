package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.slabs.*;
import net.azagwen.atbyw.block.stairs.*;
import net.azagwen.atbyw.block.state.AtbywProperties;
import net.azagwen.atbyw.block.statues.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.ToIntFunction;

import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywBlocks {

    //TODO: Idea: locks to lock chests & doors
    //TODO: Experiment with connected models/textures further
    //TODO: Experiment with World Gen
    //TODO: Port Atbyw Mod Interaction recipes to datagen

    //Blocks to add
    //TODO: Add Bipedal Statues
    //TODO: Add thin ice (world gen when ready)
    //TODO: Add Railing Blocks (catwalk handles)
    //TODO: Add regular ice bricks that melt
    //TODO: Idea > "dried" coral blocks that keep their colors
    //TODO: Add beds that accept banners as sheets.
    //TODO: Add chairs ?

    //TODO: IMPLEMENT TIMER REPEATERS

    public static Boolean always(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) { return true; }
    public static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) { return false; }
    public static boolean always(BlockState state, BlockView world, BlockPos pos) { return true; }
    public static boolean never(BlockState state, BlockView world, BlockPos pos) { return false; }

    private static ToIntFunction<BlockState> createLightLevelFromBlockState(int litLevel, BooleanProperty isLit) {
        return (blockState) -> blockState.get(isLit) ? litLevel : 0;
    }

    private static ToIntFunction<BlockState> createLightLevelFromBlockState(int divider, IntProperty litLevel, BooleanProperty isLit) {
        return (blockState) -> blockState.get(isLit) ? ((int) Math.ceil((double) blockState.get(litLevel) / (double) divider)) : 0;
    }

    private static FabricBlockSettings MakeBasalt() {
        return FabricBlockSettings.of(Material.STONE, MaterialColor.BLACK).requiresTool().breakByTool(FabricToolTags.PICKAXES).strength(1.25F, 4.2F).sounds(BlockSoundGroup.BASALT);
    }

    private static FabricBlockSettings MakeWoodenFenceDoor(Block copiedMatColor) {
        return FabricBlockSettings.of(Material.WOOD, copiedMatColor.getDefaultMaterialColor()).breakByTool(FabricToolTags.AXES).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
    }

    //Dummy Blocks (used only in-code references and specific properties)
    public static final Block TICKING_DIRT = new Block(FabricBlockSettings.of(Material.SOIL, MaterialColor.DIRT).ticksRandomly().breakByTool(FabricToolTags.SHOVELS).strength(0.5F).sounds(BlockSoundGroup.GRAVEL));
    public static final Block DUMMY_GRASS_BLOCK = new GrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).ticksRandomly().breakByTool(FabricToolTags.SHOVELS).strength(0.6F).sounds(BlockSoundGroup.GRASS));
    public static final Block DUMMY_MYCELIUM = new MyceliumBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MaterialColor.PURPLE).ticksRandomly().breakByTool(FabricToolTags.SHOVELS).strength(0.6F).sounds(BlockSoundGroup.GRASS));

    //"Full" Blocks
    public static final Block BASALT_BRICKS = new Block(MakeBasalt());
    public static final Block BASALT_PILLAR = new PillarBlock(MakeBasalt());

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

    public static final Block TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.TERRACOTTA, FabricBlockSettings.copy(Blocks.TERRACOTTA));
    public static final Block WHITE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.WHITE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.ORANGE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.ORANGE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.MAGENTA_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.MAGENTA_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_BLUE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.YELLOW_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.YELLOW_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.LIME_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.LIME_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.PINK_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.PINK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.GRAY_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_GRAY_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.CYAN_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.CYAN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.PURPLE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.PURPLE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.BLUE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.BROWN_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.BROWN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.GREEN_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.GREEN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.RED_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.RED_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.BLACK_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));

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

    public static final Block TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.TERRACOTTA, FabricBlockSettings.copy(Blocks.TERRACOTTA));
    public static final Block WHITE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.WHITE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.ORANGE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.ORANGE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.MAGENTA_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.MAGENTA_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_BLUE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.YELLOW_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.YELLOW_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.LIME_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.LIME_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.PINK_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.PINK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.GRAY_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_GRAY_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.CYAN_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.CYAN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.PURPLE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.PURPLE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.BLUE_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.BROWN_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.BROWN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.GREEN_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.GREEN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.RED_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.RED_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.BLACK_TERRACOTTA, FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));

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

    public static final Block WHITE_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.WHITE_CONCRETE, FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.ORANGE_CONCRETE, FabricBlockSettings.copyOf(Blocks.ORANGE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.MAGENTA_CONCRETE, FabricBlockSettings.copyOf(Blocks.MAGENTA_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_BLUE_CONCRETE, FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.YELLOW_CONCRETE, FabricBlockSettings.copyOf(Blocks.YELLOW_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.LIME_CONCRETE, FabricBlockSettings.copyOf(Blocks.LIME_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.PINK_CONCRETE, FabricBlockSettings.copyOf(Blocks.PINK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.GRAY_CONCRETE, FabricBlockSettings.copyOf(Blocks.GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.LIGHT_GRAY_CONCRETE, FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.CYAN_CONCRETE, FabricBlockSettings.copyOf(Blocks.CYAN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.PURPLE_CONCRETE, FabricBlockSettings.copyOf(Blocks.PURPLE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.BLUE_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLUE_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.BROWN_CONCRETE, FabricBlockSettings.copyOf(Blocks.BROWN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.GREEN_CONCRETE, FabricBlockSettings.copyOf(Blocks.GREEN_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.RED_CONCRETE, FabricBlockSettings.copyOf(Blocks.RED_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_CONCRETE_STAIRS = new StairsBlockSubClass(Blocks.BLACK_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block WHITE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIME_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PINK_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.GRAY_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.CYAN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BROWN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.GREEN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.RED_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block WHITE_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ORANGE_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block MAGENTA_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_BLUE_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block YELLOW_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIME_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.LIME_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PINK_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.PINK_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GRAY_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.GRAY_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block LIGHT_GRAY_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CYAN_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.CYAN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPLE_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLUE_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BROWN_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.BROWN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block GREEN_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.GREEN_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block RED_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block BLACK_CINDER_BLOCKS = new PillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).requiresTool().breakByTool(FabricToolTags.PICKAXES));

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

    public static final Block DIRT_STAIRS = new DirtStairsBlock(FabricBlockSettings.copyOf(TICKING_DIRT));
    public static final Block GRASS_BLOCK_STAIRS = new SpreadableStairsBlock(DUMMY_GRASS_BLOCK, Blocks.GRASS_BLOCK, FabricBlockSettings.copyOf(DUMMY_GRASS_BLOCK));
    public static final Block MYCELIUM_STAIRS = new SpreadableStairsBlock(DUMMY_MYCELIUM, Blocks.MYCELIUM, FabricBlockSettings.copyOf(DUMMY_MYCELIUM));
    public static final Block COARSE_DIRT_STAIRS = new StairsBlockSubClass(Blocks.COARSE_DIRT, FabricBlockSettings.of(Material.SOIL, MaterialColor.DIRT).breakByTool(FabricToolTags.SHOVELS).strength(0.5F).sounds(BlockSoundGroup.GRAVEL).ticksRandomly());
    public static final Block PODZOL_STAIRS = new StairsBlockSubClass(Blocks.PODZOL, FabricBlockSettings.of(Material.SOIL, MaterialColor.SPRUCE).breakByTool(FabricToolTags.SHOVELS).strength(0.5F).sounds(BlockSoundGroup.GRAVEL));
    public static final Block GRASS_PATH_STAIRS = new GrassPathStairsBlock(Blocks.GRASS_PATH, FabricBlockSettings.copyOf(Blocks.GRASS_PATH).breakByTool(FabricToolTags.SHOVELS));
    public static final Block NETHERRACK_STAIRS = new NetherrackStairsBlock(Blocks.NETHERRACK, FabricBlockSettings.copyOf(Blocks.NETHERRACK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CRIMSON_NYLIUM_STAIRS = new NyliumStairsBlock(Blocks.CRIMSON_NYLIUM, FabricBlockSettings.copyOf(Blocks.CRIMSON_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WARPED_NYLIUM_STAIRS = new NyliumStairsBlock(Blocks.WARPED_NYLIUM, FabricBlockSettings.copyOf(Blocks.WARPED_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block DIRT_SLAB = new DirtSlabBlock(FabricBlockSettings.copyOf(TICKING_DIRT));
    public static final Block GRASS_BLOCK_SLAB = new SpreadableSlabBlock(Blocks.GRASS_BLOCK, FabricBlockSettings.copyOf(DUMMY_GRASS_BLOCK));
    public static final Block MYCELIUM_SLAB = new SpreadableSlabBlock(Blocks.MYCELIUM, FabricBlockSettings.copyOf(DUMMY_MYCELIUM));
    public static final Block COARSE_DIRT_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.COARSE_DIRT).breakByTool(FabricToolTags.SHOVELS));
    public static final Block PODZOL_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PODZOL).breakByTool(FabricToolTags.SHOVELS));
    public static final Block GRASS_PATH_SLAB = new GrassPathSlabBlock(FabricBlockSettings.copyOf(Blocks.GRASS_PATH).breakByTool(FabricToolTags.SHOVELS));
    public static final Block NETHERRACK_SLAB = new NetherrackSlabBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CRIMSON_NYLIUM_SLAB = new NyliumSlabBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WARPED_NYLIUM_SLAB = new NyliumSlabBlock(FabricBlockSettings.copyOf(Blocks.WARPED_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block GRANITE_TILES = new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE));
    public static final Block DIORITE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE));
    public static final Block ANDESITE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE));

    public static final Block CHISELED_PURPUR_BLOCK = new SurfaceFacingBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK));
    public static final Block PURPUR_TILES = new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK));
    public static final Block PURPUR_TILES_STAIRS = new StairsBlockSubClass(PURPUR_TILES, FabricBlockSettings.copyOf(Blocks.PURPUR_STAIRS));
    public static final Block PURPUR_TILES_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_SLAB));
    public static final Block CUT_PURPUR_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK));
    public static final Block CUT_PURPUR_STAIRS = new StairsBlockSubClass(CUT_PURPUR_BLOCK, FabricBlockSettings.copyOf(Blocks.PURPUR_STAIRS));
    public static final Block CUT_PURPUR_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_SLAB));
    public static final Block SMOOTH_PURPUR_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK));
    public static final Block SMOOTH_PURPUR_STAIRS = new StairsBlockSubClass(CUT_PURPUR_BLOCK, FabricBlockSettings.copyOf(Blocks.PURPUR_STAIRS));
    public static final Block SMOOTH_PURPUR_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_SLAB));

    public static final Block COMPACTED_SNOW = new SnowBlockSubClass(FabricBlockSettings.of(Material.SNOW_LAYER).strength(0.2F).requiresTool().breakByTool(FabricToolTags.SHOVELS).sounds(BlockSoundGroup.SNOW));
    public static final Block COMPACTED_SNOW_BLOCK = new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).strength(0.4F).requiresTool().breakByTool(FabricToolTags.SHOVELS).sounds(BlockSoundGroup.SNOW));
    public static final Block COMPACTED_SNOW_BRICKS = new Block(FabricBlockSettings.copyOf(COMPACTED_SNOW_BLOCK));
    public static final Block CHISELED_COMPACTED_SNOW_BRICKS = new Block(FabricBlockSettings.copyOf(COMPACTED_SNOW_BLOCK));
    public static final Block PACKED_ICE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final Block CHISELED_PACKED_ICE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final Block BLUE_ICE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE));
    public static final Block CHISELED_BLUE_ICE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE));

    public static final Block COMPACTED_SNOW_BLOCK_STAIRS = new StairsBlockSubClass(COMPACTED_SNOW_BLOCK, FabricBlockSettings.copyOf(COMPACTED_SNOW_BLOCK));
    public static final Block COMPACTED_SNOW_BRICKS_STAIRS = new StairsBlockSubClass(COMPACTED_SNOW_BRICKS, FabricBlockSettings.copyOf(COMPACTED_SNOW_BLOCK));
    public static final Block PACKED_ICE_STAIRS = new StairsBlockSubClass(Blocks.PACKED_ICE, FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final Block BLUE_ICE_STAIRS = new StairsBlockSubClass(Blocks.BLUE_ICE, FabricBlockSettings.copyOf(Blocks.BLUE_ICE));
    public static final Block PACKED_ICE_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.PACKED_ICE, FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final Block BLUE_ICE_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.BLUE_ICE, FabricBlockSettings.copyOf(Blocks.BLUE_ICE));

    public static final Block COMPACTED_SNOW_BLOCK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(COMPACTED_SNOW_BLOCK));
    public static final Block COMPACTED_SNOW_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(COMPACTED_SNOW_BLOCK));
    public static final Block PACKED_ICE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final Block BLUE_ICE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLUE_ICE));
    public static final Block PACKED_ICE_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final Block BLUE_ICE_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLUE_ICE));

    public static final Block REDSTONE_JACK_O_LANTERN = new RedstoneJackOlantern(FabricBlockSettings.of(Material.GOURD, MaterialColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance(createLightLevelFromBlockState(7, RedstoneJackOlantern.LIT)).solidBlock(AtbywBlocks::never).allowsSpawning(AtbywBlocks::always));
    public static final Block SOUL_JACK_O_LANTERN = new CarvedPumpkinBlockSubClass(FabricBlockSettings.of(Material.GOURD, MaterialColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> 10).allowsSpawning(AtbywBlocks::always));

    //Non-Full Blocks
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

    public static final Block DANDELION_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.DANDELION).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block POPPY_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.POPPY).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block BLUE_ORCHID_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.BLUE_ORCHID).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block ALLIUM_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.ALLIUM).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block AZURE_BLUET_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.AZURE_BLUET).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block RED_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.RED_TULIP).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block ORANGE_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_TULIP).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block WHITE_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.WHITE_TULIP).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block PINK_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.PINK_TULIP).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block OXEYE_DAISY_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.OXEYE_DAISY).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block CORNFLOWER_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.CORNFLOWER).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block LILY_OF_THE_VALLEY_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.LILY_OF_THE_VALLEY).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block WITHER_ROSE_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.WITHER_ROSE).luminance(createLightLevelFromBlockState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));

    public static final Block REDSTONE_LANTERN = new RedstoneLanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN).breakByTool(FabricToolTags.PICKAXES).luminance(createLightLevelFromBlockState(2, AtbywProperties.POWER_INTENSITY, Properties.LIT)));
    public static final Block SHROOMSTICK = new ShroomStickBlock(FabricBlockSettings.of(AtbywMaterials.SHROOMSTICK).breakByHand(true).breakInstantly().noCollision().nonOpaque().luminance((state) -> 15));

    public static final Block GRANITE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.GRANITE));
    public static final Block DIORITE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.DIORITE));
    public static final Block ANDESITE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.ANDESITE));
    public static final Block SANDSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE));
    public static final Block CHISELED_SANDSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE));
    public static final Block RED_SANDSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE));
    public static final Block CHISELED_RED_SANDSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE));
    public static final Block PURPUR_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK));
    public static final Block STONE_BRICKS_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS));
    public static final Block MOSSY_STONE_BRICKS_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS));
    public static final Block CRACKED_STONE_BRICKS_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.CRACKED_STONE_BRICKS));
    public static final Block NETHER_BRICKS_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS));
    public static final Block QUARTZ_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK));
    public static final Block PRISMARINE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.PRISMARINE));
    public static final Block BLACKSTONE_COLUMN = new ColumnBlock(FabricBlockSettings.copyOf(Blocks.BLACKSTONE));

    public static final Block IRON_SPIKE_TRAP_SPIKES = new SpikeBlock(AtbywID("iron_spike_trap"), 2.0F, 1, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block GOLD_SPIKE_TRAP_SPIKES = new SpikeBlock(AtbywID("gold_spike_trap"), 0.5F, 0, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block DIAMOND_SPIKE_TRAP_SPIKES = new SpikeBlock(AtbywID("diamond_spike_trap"), 3.0F, 2, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block NETHERITE_SPIKE_TRAP_SPIKES = new SpikeBlock(AtbywID("netherite_spike_trap"), 4.0F, 2, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());

    public static final Block IRON_SPIKE_TRAP = new SpikeTrapBlock(IRON_SPIKE_TRAP_SPIKES, 1.0F, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block GOLD_SPIKE_TRAP = new SpikeTrapBlock(GOLD_SPIKE_TRAP_SPIKES, 0.5F, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block DIAMOND_SPIKE_TRAP = new SpikeTrapBlock(DIAMOND_SPIKE_TRAP_SPIKES, 3.0F, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block NETHERITE_SPIKE_TRAP = new SpikeTrapBlock(NETHERITE_SPIKE_TRAP_SPIKES, 6.0F, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));

    public static final Block TIMER_REPEATER = new TimerRepeaterBlockNew(FabricBlockSettings.copyOf(Blocks.REPEATER));

    public static final Block DEVELOPER_BLOCK = new DevBlock(FabricBlockSettings.of(Material.WOOL, MaterialColor.ORANGE).nonOpaque().breakByHand(true).strength(0.1F).sounds(BlockSoundGroup.BONE));

    public static void init() {
        //ATBYW REDSTONE
        registerBlocks(false, ATBYW_REDSTONE, null, "fence_door", WOOD_NAMES, OAK_FENCE_DOOR, SPRUCE_FENCE_DOOR, BIRCH_FENCE_DOOR, JUNGLE_FENCE_DOOR, ACACIA_FENCE_DOOR, DARK_OAK_FENCE_DOOR, CRIMSON_FENCE_DOOR, WARPED_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "iron_fence_door", IRON_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "iron_spike_trap", IRON_SPIKE_TRAP);
        registerBlock(false, ATBYW_REDSTONE, "gold_spike_trap", GOLD_SPIKE_TRAP);
        registerBlock(false, ATBYW_REDSTONE, "diamond_spike_trap", DIAMOND_SPIKE_TRAP);
        registerBlock(false, ATBYW_REDSTONE, "netherite_spike_trap", NETHERITE_SPIKE_TRAP);
        registerBlocks(false, ATBYW_REDSTONE, null, "bookshelf_toggle", WOOD_NAMES, OAK_BOOKSHELF_TOGGLE, SPRUCE_BOOKSHELF_TOGGLE, BIRCH_BOOKSHELF_TOGGLE, JUNGLE_BOOKSHELF_TOGGLE, ACACIA_BOOKSHELF_TOGGLE, DARK_OAK_BOOKSHELF_TOGGLE, CRIMSON_BOOKSHELF_TOGGLE, WARPED_BOOKSHELF_TOGGLE);
        AtbywModInteractionBlocks.initBookshelfToggles();
        registerBlock(false, ATBYW_REDSTONE, "redstone_jack_o_lantern", REDSTONE_JACK_O_LANTERN);
        registerBlock(false, ATBYW_REDSTONE, "redstone_lantern", REDSTONE_LANTERN);
        registerBlocks(false, ATBYW_REDSTONE, null, "pull_switch", FLOWER_NAMES, DANDELION_PULL_SWITCH, POPPY_PULL_SWITCH, BLUE_ORCHID_PULL_SWITCH, ALLIUM_PULL_SWITCH, AZURE_BLUET_PULL_SWITCH, RED_TULIP_PULL_SWITCH, ORANGE_TULIP_PULL_SWITCH, WHITE_TULIP_PULL_SWITCH, PINK_TULIP_PULL_SWITCH, OXEYE_DAISY_PULL_SWITCH, CORNFLOWER_PULL_SWITCH, LILY_OF_THE_VALLEY_PULL_SWITCH, WITHER_ROSE_PULL_SWITCH);
        registerBlock(false, ATBYW_REDSTONE, "timer_repeater", TIMER_REPEATER);

        //ATBYW BLOCKS
        registerBlock(false, ATBYW_BLOCKS, "granite_tiles", GRANITE_TILES);
        registerBlock(false, ATBYW_BLOCKS, "diorite_bricks", DIORITE_BRICKS);
        registerBlock(false, ATBYW_BLOCKS, "andesite_bricks", ANDESITE_BRICKS);

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

        registerBlock(false, ATBYW_BLOCKS, "soul_jack_o_lantern", SOUL_JACK_O_LANTERN);

        registerBlocks(false, ATBYW_BLOCKS, null, "bookshelf", WOOD_NAMES_NO_OAK, SPRUCE_BOOKSHELF, BIRCH_BOOKSHELF, JUNGLE_BOOKSHELF, ACACIA_BOOKSHELF, DARK_OAK_BOOKSHELF, CRIMSON_BOOKSHELF, WARPED_BOOKSHELF);
        registerBlocks(false, ATBYW_BLOCKS, null, "ladder", WOOD_NAMES_NO_OAK, SPRUCE_LADDER, BIRCH_LADDER, JUNGLE_LADDER, ACACIA_LADDER, DARK_OAK_LADDER, CRIMSON_LADDER, WARPED_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "bamboo_ladder", BAMBOO_LADDER);

        registerBlock(false, ATBYW_BLOCKS, "purpur_tiles", PURPUR_TILES);
        registerBlock(false, ATBYW_BLOCKS, "chiseled_purpur_block", CHISELED_PURPUR_BLOCK);
        registerBlock(false, ATBYW_BLOCKS, "cut_purpur_block", CUT_PURPUR_BLOCK);
        registerBlock(false, ATBYW_BLOCKS, "smooth_purpur_block", SMOOTH_PURPUR_BLOCK);

        registerBlock(false, ATBYW_BLOCKS, "purpur_tiles_stairs", PURPUR_TILES_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "cut_purpur_stairs", CUT_PURPUR_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "smooth_purpur_stairs", SMOOTH_PURPUR_STAIRS);

        registerBlock(false, ATBYW_BLOCKS, "purpur_tiles_slab", PURPUR_TILES_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "cut_purpur_slab", CUT_PURPUR_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "smooth_purpur_slab", SMOOTH_PURPUR_SLAB);

        registerBlock(false, ATBYW_BLOCKS, "compacted_snow_block", COMPACTED_SNOW_BLOCK);
        registerBlock(false, ATBYW_BLOCKS, "compacted_snow_bricks", COMPACTED_SNOW_BRICKS);
        registerBlock(false, ATBYW_BLOCKS, "chiseled_compacted_snow_bricks", CHISELED_COMPACTED_SNOW_BRICKS);
        registerBlock(false, ATBYW_BLOCKS, "packed_ice_bricks", PACKED_ICE_BRICKS);
        registerBlock(false, ATBYW_BLOCKS, "chiseled_packed_ice_bricks", CHISELED_PACKED_ICE_BRICKS);
        registerBlock(false, ATBYW_BLOCKS, "blue_ice_bricks", BLUE_ICE_BRICKS);
        registerBlock(false, ATBYW_BLOCKS, "chiseled_blue_ice_bricks", CHISELED_BLUE_ICE_BRICKS);

        registerBlock(false, ATBYW_BLOCKS, "compacted_snow_block_stairs", COMPACTED_SNOW_BLOCK_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "compacted_snow_bricks_stairs", COMPACTED_SNOW_BRICKS_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "packed_ice_stairs", PACKED_ICE_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "blue_ice_stairs", BLUE_ICE_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "packed_ice_bricks_stairs", PACKED_ICE_BRICKS_STAIRS);
        registerBlock(false, ATBYW_BLOCKS, "blue_ice_bricks_stairs", BLUE_ICE_BRICKS_STAIRS);

        registerBlock(false, ATBYW_BLOCKS, "compacted_snow_block_slab", COMPACTED_SNOW_BLOCK_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "compacted_snow_bricks_slab", COMPACTED_SNOW_BRICKS_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "packed_ice_slab", PACKED_ICE_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "blue_ice_slab", BLUE_ICE_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "packed_ice_bricks_slab", PACKED_ICE_BRICKS_SLAB);
        registerBlock(false, ATBYW_BLOCKS, "blue_ice_bricks_slab", BLUE_ICE_BRICKS_SLAB);

        registerBlock(false, ATBYW_BLOCKS, "basalt_bricks", BASALT_BRICKS);
        registerBlock(false, ATBYW_BLOCKS, "basalt_pillar", BASALT_PILLAR);

        registerBlock( false, ATBYW_BLOCKS, "terracotta_stairs", TERRACOTTA_STAIRS);
        registerBlocks(false, ATBYW_BLOCKS, null, "terracotta_stairs", COLOR_NAMES, WHITE_TERRACOTTA_STAIRS, ORANGE_TERRACOTTA_STAIRS, MAGENTA_TERRACOTTA_STAIRS, LIGHT_BLUE_TERRACOTTA_STAIRS, YELLOW_TERRACOTTA_STAIRS, LIME_TERRACOTTA_STAIRS, PINK_TERRACOTTA_STAIRS, GRAY_TERRACOTTA_STAIRS, LIGHT_GRAY_TERRACOTTA_STAIRS, CYAN_TERRACOTTA_STAIRS, PURPLE_TERRACOTTA_STAIRS, BLUE_TERRACOTTA_STAIRS, BROWN_TERRACOTTA_STAIRS, GREEN_TERRACOTTA_STAIRS, RED_TERRACOTTA_STAIRS, BLACK_TERRACOTTA_STAIRS);
        registerBlock( false, ATBYW_BLOCKS, "terracotta_slab", TERRACOTTA_SLAB);
        registerBlocks(false, ATBYW_BLOCKS, null, "terracotta_slab", COLOR_NAMES, WHITE_TERRACOTTA_SLAB, ORANGE_TERRACOTTA_SLAB, MAGENTA_TERRACOTTA_SLAB, LIGHT_BLUE_TERRACOTTA_SLAB, YELLOW_TERRACOTTA_SLAB, LIME_TERRACOTTA_SLAB, PINK_TERRACOTTA_SLAB, GRAY_TERRACOTTA_SLAB, LIGHT_GRAY_TERRACOTTA_SLAB, CYAN_TERRACOTTA_SLAB, PURPLE_TERRACOTTA_SLAB, BLUE_TERRACOTTA_SLAB, BROWN_TERRACOTTA_SLAB, GREEN_TERRACOTTA_SLAB, RED_TERRACOTTA_SLAB, BLACK_TERRACOTTA_SLAB);
        registerBlock( false, ATBYW_BLOCKS, "terracotta_bricks", TERRACOTTA_BRICKS);
        registerBlocks(false, ATBYW_BLOCKS, null, "terracotta_bricks", COLOR_NAMES, WHITE_TERRACOTTA_BRICKS, ORANGE_TERRACOTTA_BRICKS, MAGENTA_TERRACOTTA_BRICKS, LIGHT_BLUE_TERRACOTTA_BRICKS, YELLOW_TERRACOTTA_BRICKS, LIME_TERRACOTTA_BRICKS, PINK_TERRACOTTA_BRICKS, GRAY_TERRACOTTA_BRICKS, LIGHT_GRAY_TERRACOTTA_BRICKS, CYAN_TERRACOTTA_BRICKS, PURPLE_TERRACOTTA_BRICKS, BLUE_TERRACOTTA_BRICKS, BROWN_TERRACOTTA_BRICKS, GREEN_TERRACOTTA_BRICKS, RED_TERRACOTTA_BRICKS, BLACK_TERRACOTTA_BRICKS);
        registerBlock( false, ATBYW_BLOCKS, "terracotta_bricks_stairs", TERRACOTTA_BRICKS_STAIRS);
        registerBlocks(false, ATBYW_BLOCKS, null, "terracotta_bricks_stairs", COLOR_NAMES, WHITE_TERRACOTTA_BRICKS_STAIRS, ORANGE_TERRACOTTA_BRICKS_STAIRS, MAGENTA_TERRACOTTA_BRICKS_STAIRS, LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, YELLOW_TERRACOTTA_BRICKS_STAIRS, LIME_TERRACOTTA_BRICKS_STAIRS, PINK_TERRACOTTA_BRICKS_STAIRS, GRAY_TERRACOTTA_BRICKS_STAIRS, LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, CYAN_TERRACOTTA_BRICKS_STAIRS, PURPLE_TERRACOTTA_BRICKS_STAIRS, BLUE_TERRACOTTA_BRICKS_STAIRS, BROWN_TERRACOTTA_BRICKS_STAIRS, GREEN_TERRACOTTA_BRICKS_STAIRS, RED_TERRACOTTA_BRICKS_STAIRS, BLACK_TERRACOTTA_BRICKS_STAIRS);
        registerBlock( false, ATBYW_BLOCKS, "terracotta_bricks_slab", TERRACOTTA_BRICKS_SLAB);
        registerBlocks(false, ATBYW_BLOCKS, null, "terracotta_bricks_slab", COLOR_NAMES, WHITE_TERRACOTTA_BRICKS_SLAB, ORANGE_TERRACOTTA_BRICKS_SLAB, MAGENTA_TERRACOTTA_BRICKS_SLAB, LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, YELLOW_TERRACOTTA_BRICKS_SLAB, LIME_TERRACOTTA_BRICKS_SLAB, PINK_TERRACOTTA_BRICKS_SLAB, GRAY_TERRACOTTA_BRICKS_SLAB, LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, CYAN_TERRACOTTA_BRICKS_SLAB, PURPLE_TERRACOTTA_BRICKS_SLAB, BLUE_TERRACOTTA_BRICKS_SLAB, BROWN_TERRACOTTA_BRICKS_SLAB, GREEN_TERRACOTTA_BRICKS_SLAB, RED_TERRACOTTA_BRICKS_SLAB, BLACK_TERRACOTTA_BRICKS_SLAB);

        registerBlocks(false, ATBYW_BLOCKS, null, "concrete_stairs", COLOR_NAMES, WHITE_CONCRETE_STAIRS, ORANGE_CONCRETE_STAIRS, MAGENTA_CONCRETE_STAIRS, LIGHT_BLUE_CONCRETE_STAIRS, YELLOW_CONCRETE_STAIRS, LIME_CONCRETE_STAIRS, PINK_CONCRETE_STAIRS, GRAY_CONCRETE_STAIRS, LIGHT_GRAY_CONCRETE_STAIRS, CYAN_CONCRETE_STAIRS, PURPLE_CONCRETE_STAIRS, BLUE_CONCRETE_STAIRS, BROWN_CONCRETE_STAIRS, GREEN_CONCRETE_STAIRS, RED_CONCRETE_STAIRS, BLACK_CONCRETE_STAIRS);
        registerBlocks(false, ATBYW_BLOCKS, null, "concrete_slab", COLOR_NAMES, WHITE_CONCRETE_SLAB, ORANGE_CONCRETE_SLAB, MAGENTA_CONCRETE_SLAB, LIGHT_BLUE_CONCRETE_SLAB, YELLOW_CONCRETE_SLAB, LIME_CONCRETE_SLAB, PINK_CONCRETE_SLAB, GRAY_CONCRETE_SLAB, LIGHT_GRAY_CONCRETE_SLAB, CYAN_CONCRETE_SLAB, PURPLE_CONCRETE_SLAB, BLUE_CONCRETE_SLAB, BROWN_CONCRETE_SLAB, GREEN_CONCRETE_SLAB, RED_CONCRETE_SLAB, BLACK_CONCRETE_SLAB);
        registerBlocks(false, ATBYW_BLOCKS, null, "cinder_bricks", COLOR_NAMES, WHITE_CINDER_BLOCKS, ORANGE_CINDER_BLOCKS, MAGENTA_CINDER_BLOCKS, LIGHT_BLUE_CINDER_BLOCKS, YELLOW_CINDER_BLOCKS, LIME_CINDER_BLOCKS, PINK_CINDER_BLOCKS, GRAY_CINDER_BLOCKS, LIGHT_GRAY_CINDER_BLOCKS, CYAN_CINDER_BLOCKS, PURPLE_CINDER_BLOCKS, BLUE_CINDER_BLOCKS, BROWN_CINDER_BLOCKS, GREEN_CINDER_BLOCKS, RED_CINDER_BLOCKS, BLACK_CINDER_BLOCKS);

        //ATBYW DECO
        registerBlock(false, ATBYW_DECO, "compacted_snow", COMPACTED_SNOW);

        registerBlock(false, ATBYW_DECO, "granite_column", GRANITE_COLUMN);
        registerBlock(false, ATBYW_DECO, "diorite_column", DIORITE_COLUMN);
        registerBlock(false, ATBYW_DECO, "andesite_column", ANDESITE_COLUMN);
        registerBlock(false, ATBYW_DECO, "sandstone_column", SANDSTONE_COLUMN);
        registerBlock(false, ATBYW_DECO, "chiseled_sandstone_column", CHISELED_SANDSTONE_COLUMN);
        registerBlock(false, ATBYW_DECO, "red_sandstone_column", RED_SANDSTONE_COLUMN);
        registerBlock(false, ATBYW_DECO, "chiseled_red_sandstone_column", CHISELED_RED_SANDSTONE_COLUMN);
        registerBlock(false, ATBYW_DECO, "purpur_column", PURPUR_COLUMN);
        registerBlock(false, ATBYW_DECO, "stone_bricks_column", STONE_BRICKS_COLUMN);
        registerBlock(false, ATBYW_DECO, "mossy_stone_bricks_column", MOSSY_STONE_BRICKS_COLUMN);
        registerBlock(false, ATBYW_DECO, "cracked_stone_bricks_column", CRACKED_STONE_BRICKS_COLUMN);
        registerBlock(false, ATBYW_DECO, "nether_bricks_column", NETHER_BRICKS_COLUMN);
        registerBlock(false, ATBYW_DECO, "quartz_column", QUARTZ_COLUMN);
        registerBlock(false, ATBYW_DECO, "prismarine_column", PRISMARINE_COLUMN);
        registerBlock(false, ATBYW_DECO, "blackstone_column", BLACKSTONE_COLUMN);

        registerBlock(false, ATBYW_DECO, "terracotta_bricks_wall", TERRACOTTA_BRICKS_WALL);
        registerBlocks(false, ATBYW_DECO, null, "terracotta_bricks_wall", COLOR_NAMES, WHITE_TERRACOTTA_BRICKS_WALL, ORANGE_TERRACOTTA_BRICKS_WALL, MAGENTA_TERRACOTTA_BRICKS_WALL, LIGHT_BLUE_TERRACOTTA_BRICKS_WALL, YELLOW_TERRACOTTA_BRICKS_WALL, LIME_TERRACOTTA_BRICKS_WALL, PINK_TERRACOTTA_BRICKS_WALL, GRAY_TERRACOTTA_BRICKS_WALL, LIGHT_GRAY_TERRACOTTA_BRICKS_WALL, CYAN_TERRACOTTA_BRICKS_WALL, PURPLE_TERRACOTTA_BRICKS_WALL, BLUE_TERRACOTTA_BRICKS_WALL, BROWN_TERRACOTTA_BRICKS_WALL, GREEN_TERRACOTTA_BRICKS_WALL, RED_TERRACOTTA_BRICKS_WALL, BLACK_TERRACOTTA_BRICKS_WALL);
        registerBlocks(false, ATBYW_DECO, null, "cinder_blocks_wall", COLOR_NAMES, WHITE_CINDER_BLOCKS_WALL, ORANGE_CINDER_BLOCKS_WALL, MAGENTA_CINDER_BLOCKS_WALL, LIGHT_BLUE_CINDER_BLOCKS_WALL, YELLOW_CINDER_BLOCKS_WALL, LIME_CINDER_BLOCKS_WALL, PINK_CINDER_BLOCKS_WALL, GRAY_CINDER_BLOCKS_WALL, LIGHT_GRAY_CINDER_BLOCKS_WALL, CYAN_CINDER_BLOCKS_WALL, PURPLE_CINDER_BLOCKS_WALL, BLUE_CINDER_BLOCKS_WALL, BROWN_CINDER_BLOCKS_WALL, GREEN_CINDER_BLOCKS_WALL, RED_CINDER_BLOCKS_WALL, BLACK_CINDER_BLOCKS_WALL);

        StatueRegistry.initStatues();

        //ATBYW MISC
        registerBlock(false, ATBYW_MISC, "dev_block", DEVELOPER_BLOCK);

        //Item-less blocks
        registerBlockOnly("shroomstick", SHROOMSTICK);
        registerBlockOnly("iron_spike_trap_spikes", IRON_SPIKE_TRAP_SPIKES);
        registerBlockOnly("gold_spike_trap_spikes", GOLD_SPIKE_TRAP_SPIKES);
        registerBlockOnly("diamond_spike_trap_spikes", DIAMOND_SPIKE_TRAP_SPIKES);
        registerBlockOnly("netherite_spike_trap_spikes", NETHERITE_SPIKE_TRAP_SPIKES);

        LOGGER.info("ATBYW Blocks Inintiliazed");
    }
}
