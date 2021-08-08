package net.azagwen.atbyw.block;

import com.google.common.collect.Sets;
import net.azagwen.atbyw.block.slabs.*;
import net.azagwen.atbyw.block.stairs.*;
import net.azagwen.atbyw.block.state.AtbywProperties;
import net.azagwen.atbyw.block.state.AtbywSignType;
import net.azagwen.atbyw.block.statues.*;
import net.azagwen.atbyw.item.CanvasBlockItem;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.mod_interaction.block.AtbywModInteractionBlocks;
import net.azagwen.atbyw.util.AtbywUtils;
import net.azagwen.atbyw.util.naming.FlowerNames;
import net.azagwen.atbyw.util.naming.WoodNames;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.ToIntFunction;

import static net.azagwen.atbyw.main.AtbywMain.*;
import static net.azagwen.atbyw.util.BlockUtils.*;

public class AtbywBlocks {

    //TODO: Idea: locks to lock chests & doors
    //TODO: Experiment with connected models/textures further
    //TODO: Experiment with World Gen
    //TODO: Port Atbyw Mod Interaction recipes to datagen

    //Blocks to add
    //TODO: STATUES Add Bipedal Statues
    //TODO: STATUES Add signing fish function.
    //TODO: STATUES Make slime statues combine-able.
    //TODO: Add thin ice (world gen when ready)
    //TODO: Add Railing Blocks (catwalk handles) || update: WIP
    //TODO: Add regular ice bricks that melt
    //TODO: Idea > "dried" coral blocks that keep their colors
    //TODO: Add chairs ?
    //TODO: Add step detectors.
    //TODO: Add a chain hook that you can hook items and blocks to.
    //TODO: add a paint mixer workstation (precise color item manipulation).

    public static Boolean always(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) { return true; }
    public static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) { return false; }
    public static boolean always(BlockState state, BlockView world, BlockPos pos) { return true; }
    public static boolean never(BlockState state, BlockView world, BlockPos pos) { return false; }

    public static void registerCanvasBlock(ArrayList<Item> itemTab, String name, Block block) {
        Registry.register(Registry.BLOCK, AtbywMain.id(name), block);
        Registry.register(Registry.ITEM, AtbywMain.id(name), new CanvasBlockItem(block, new Item.Settings()));

        itemTab.add(block.asItem());
    }

    public static void registerSign(ArrayList<Item> itemTab, Block standingSign, Block wallSign) {
        var type = ((AtbywSignBlock) standingSign).getType().getName();
        Registry.register(Registry.BLOCK, AtbywMain.id(type + "_sign"), standingSign);
        Registry.register(Registry.BLOCK, AtbywMain.id(type + "_wall_sign"), wallSign);
        Registry.register(Registry.ITEM, AtbywMain.id(type + "_sign"), new SignItem(new Item.Settings(), standingSign, wallSign));

        itemTab.add(standingSign.asItem());
    }

    private static ToIntFunction<BlockState> lightLevelFromState(int litLevel, BooleanProperty isLit) {
        return (blockState) -> blockState.get(isLit) ? litLevel : 0;
    }

    private static ToIntFunction<BlockState> lightLevelFromState(int divider, IntProperty litLevel, BooleanProperty isLit) {
        return (blockState) -> blockState.get(isLit) ? ((int) Math.ceil((double) blockState.get(litLevel) / (double) divider)) : 0;
    }

    private static FabricBlockSettings basaltSettings() {
        return FabricBlockSettings.of(Material.STONE, MapColor.BLACK).requiresTool().breakByTool(FabricToolTags.PICKAXES).strength(1.25F, 4.2F).sounds(BlockSoundGroup.BASALT);
    }

    private static FabricBlockSettings woodenFenceDoorSettings(Block copiedMatColor) {
        return FabricBlockSettings.of(Material.WOOD, copiedMatColor.getDefaultMapColor()).breakByTool(FabricToolTags.AXES).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
    }

    private static FabricBlockSettings logSettings(Block copiedBlock) {
        return FabricBlockSettings.of(Material.WOOD, copiedBlock.getDefaultMapColor()).strength(2.0F).breakByTool(FabricToolTags.AXES).sounds(copiedBlock.getSoundGroup(copiedBlock.getDefaultState()));
    }

    private static ShatteredGlassBlock shatteredGlass(Block copiedBlock) {
        var settings = FabricBlockSettings.of(Material.GLASS).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque().allowsSpawning(AtbywBlocks::never).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never);

        if (copiedBlock instanceof StainedGlassBlock stainedGlass) {
            return new ShatteredGlassBlock(SHATTERED_GLASS_SET, stainedGlass.getColor(), stainedGlass, settings);
        } else {
            return new ShatteredGlassBlock(SHATTERED_GLASS_SET, copiedBlock, settings);
        }
    }

    private static CanvasBlock createCanvasBlock(boolean glowing) {
        return new CanvasBlock(glowing, FabricBlockSettings.of(Material.WOOL).strength(0.5F).sounds(BlockSoundGroup.WOOL));
    }

    //Dummy Blocks (used only in-code references and specific properties)
    public static final Block TICKING_DIRT = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).ticksRandomly().breakByTool(FabricToolTags.SHOVELS).strength(0.5F).sounds(BlockSoundGroup.GRAVEL));
    public static final Block DUMMY_GRASS_BLOCK = new GrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).ticksRandomly().breakByTool(FabricToolTags.SHOVELS).strength(0.6F).sounds(BlockSoundGroup.GRASS));
    public static final Block DUMMY_MYCELIUM = new MyceliumBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).ticksRandomly().breakByTool(FabricToolTags.SHOVELS).strength(0.6F).sounds(BlockSoundGroup.GRASS));

    //Blocks
    public static final Block BASALT_BRICKS = new Block(basaltSettings());
    public static final Block BASALT_PILLAR = new PillarBlock(basaltSettings());

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

    public static final Block DIRT_STAIRS = new TickingDirtStairsBlock(TICKING_DIRT, FabricBlockSettings.copyOf(TICKING_DIRT));
    public static final Block GRASS_BLOCK_STAIRS = new SpreadableStairsBlock(DUMMY_GRASS_BLOCK, Blocks.GRASS_BLOCK, FabricBlockSettings.copyOf(DUMMY_GRASS_BLOCK));
    public static final Block MYCELIUM_STAIRS = new SpreadableStairsBlock(DUMMY_MYCELIUM, Blocks.MYCELIUM, FabricBlockSettings.copyOf(DUMMY_MYCELIUM));
    public static final Block COARSE_DIRT_STAIRS = new StairsBlockSubClass(Blocks.COARSE_DIRT, FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).breakByTool(FabricToolTags.SHOVELS).strength(0.5F).sounds(BlockSoundGroup.GRAVEL).ticksRandomly());
    public static final Block PODZOL_STAIRS = new StairsBlockSubClass(Blocks.PODZOL, FabricBlockSettings.of(Material.SOIL, MapColor.SPRUCE_BROWN).breakByTool(FabricToolTags.SHOVELS).strength(0.5F).sounds(BlockSoundGroup.GRAVEL));
    public static final Block GRASS_PATH_STAIRS = new GrassPathStairsBlock(Blocks.DIRT_PATH, FabricBlockSettings.copyOf(Blocks.DIRT_PATH).breakByTool(FabricToolTags.SHOVELS));
    public static final Block NETHERRACK_STAIRS = new NetherrackStairsBlock(Blocks.NETHERRACK, FabricBlockSettings.copyOf(Blocks.NETHERRACK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CRIMSON_NYLIUM_STAIRS = new NyliumStairsBlock(Blocks.CRIMSON_NYLIUM, FabricBlockSettings.copyOf(Blocks.CRIMSON_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WARPED_NYLIUM_STAIRS = new NyliumStairsBlock(Blocks.WARPED_NYLIUM, FabricBlockSettings.copyOf(Blocks.WARPED_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ROOTED_DIRT_STAIRS = new StairsBlockSubClass(Blocks.ROOTED_DIRT, FabricBlockSettings.copyOf(Blocks.ROOTED_DIRT).breakByTool(FabricToolTags.SHOVELS));

    public static final Block DIRT_SLAB = new TickingDirtSlabBlock(FabricBlockSettings.copyOf(TICKING_DIRT));
    public static final Block GRASS_BLOCK_SLAB = new SpreadableSlabBlock(Blocks.GRASS_BLOCK, FabricBlockSettings.copyOf(DUMMY_GRASS_BLOCK));
    public static final Block MYCELIUM_SLAB = new SpreadableSlabBlock(Blocks.MYCELIUM, FabricBlockSettings.copyOf(DUMMY_MYCELIUM));
    public static final Block COARSE_DIRT_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.COARSE_DIRT).breakByTool(FabricToolTags.SHOVELS));
    public static final Block PODZOL_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PODZOL).breakByTool(FabricToolTags.SHOVELS));
    public static final Block GRASS_PATH_SLAB = new GrassPathSlabBlock(FabricBlockSettings.copyOf(Blocks.DIRT_PATH).breakByTool(FabricToolTags.SHOVELS));
    public static final Block NETHERRACK_SLAB = new NetherrackSlabBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CRIMSON_NYLIUM_SLAB = new NyliumSlabBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block WARPED_NYLIUM_SLAB = new NyliumSlabBlock(FabricBlockSettings.copyOf(Blocks.WARPED_NYLIUM).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ROOTED_DIRT_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.ROOTED_DIRT).breakByTool(FabricToolTags.SHOVELS));

    public static final Block GRANITE_TILES = new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block DIORITE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ANDESITE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block GRANITE_TILES_STAIRS = new StairsBlockSubClass(Blocks.POLISHED_GRANITE, FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block DIORITE_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.POLISHED_DIORITE, FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ANDESITE_BRICKS_STAIRS = new StairsBlockSubClass(Blocks.POLISHED_ANDESITE, FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block GRANITE_TILES_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block DIORITE_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block ANDESITE_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block CHISELED_PURPUR_BLOCK = new SurfaceFacingBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPUR_TILES = new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPUR_TILES_STAIRS = new StairsBlockSubClass(PURPUR_TILES, FabricBlockSettings.copyOf(Blocks.PURPUR_STAIRS).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block PURPUR_TILES_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_SLAB).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CUT_PURPUR_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CUT_PURPUR_STAIRS = new StairsBlockSubClass(CUT_PURPUR_BLOCK, FabricBlockSettings.copyOf(Blocks.PURPUR_STAIRS).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block CUT_PURPUR_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_SLAB).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block SMOOTH_PURPUR_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block SMOOTH_PURPUR_STAIRS = new StairsBlockSubClass(CUT_PURPUR_BLOCK, FabricBlockSettings.copyOf(Blocks.PURPUR_STAIRS).requiresTool().breakByTool(FabricToolTags.PICKAXES));
    public static final Block SMOOTH_PURPUR_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PURPUR_SLAB).requiresTool().breakByTool(FabricToolTags.PICKAXES));

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

    public static final Block REDSTONE_JACK_O_LANTERN = new RedstoneJackOlantern(FabricBlockSettings.of(Material.GOURD, MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance(lightLevelFromState(7, RedstoneJackOlantern.LIT)).solidBlock(AtbywBlocks::never).allowsSpawning(AtbywBlocks::always));
    public static final Block SOUL_JACK_O_LANTERN = new CarvedPumpkinBlockSubClass(FabricBlockSettings.of(Material.GOURD, MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> 10).allowsSpawning(AtbywBlocks::always));

    public static final Block SAND_SLAB = new FallingSlabBlock(14406560, FabricBlockSettings.copyOf(Blocks.SAND).breakByTool(FabricToolTags.SHOVELS));
    public static final Block RED_SAND_SLAB = new FallingSlabBlock(11098145, FabricBlockSettings.copyOf(Blocks.RED_SAND).breakByTool(FabricToolTags.SHOVELS));
    public static final Block GRAVEL_SLAB = new FallingSlabBlock(-8356741, FabricBlockSettings.copyOf(Blocks.GRAVEL).breakByTool(FabricToolTags.SHOVELS));

    public static final Block SAND_STAIRS = new FallingStairsBlock(14406560, Blocks.SAND, SAND_SLAB, FabricBlockSettings.copyOf(Blocks.SAND).breakByTool(FabricToolTags.SHOVELS));
    public static final Block RED_SAND_STAIRS = new FallingStairsBlock(11098145, Blocks.RED_SAND, RED_SAND_SLAB, FabricBlockSettings.copyOf(Blocks.RED_SAND).breakByTool(FabricToolTags.SHOVELS));
    public static final Block GRAVEL_STAIRS = new FallingStairsBlock(-8356741, Blocks.GRAVEL, GRAVEL_SLAB, FabricBlockSettings.copyOf(Blocks.GRAVEL).breakByTool(FabricToolTags.SHOVELS));

    public static final Set<Block> SHATTERED_GLASS_SET = Sets.newHashSet();
    public static final Block SHATTERED_GLASS = shatteredGlass(Blocks.GLASS);
    public static final Block WHITE_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.WHITE_STAINED_GLASS);
    public static final Block ORANGE_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.ORANGE_STAINED_GLASS);
    public static final Block MAGENTA_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.MAGENTA_STAINED_GLASS);
    public static final Block LIGHT_BLUE_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.LIGHT_BLUE_STAINED_GLASS);
    public static final Block YELLOW_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.YELLOW_STAINED_GLASS);
    public static final Block LIME_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.LIME_STAINED_GLASS);
    public static final Block PINK_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.PINK_STAINED_GLASS);
    public static final Block GRAY_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.GRAY_STAINED_GLASS);
    public static final Block LIGHT_GRAY_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.LIGHT_GRAY_STAINED_GLASS);
    public static final Block CYAN_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.CYAN_STAINED_GLASS);
    public static final Block PURPLE_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.PURPLE_STAINED_GLASS);
    public static final Block BLUE_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.BLUE_STAINED_GLASS);
    public static final Block BROWN_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.BROWN_STAINED_GLASS);
    public static final Block GREEN_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.GREEN_STAINED_GLASS);
    public static final Block RED_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.RED_STAINED_GLASS);
    public static final Block BLACK_STAINED_SHATTERED_GLASS = shatteredGlass(Blocks.BLACK_STAINED_GLASS);

    public static final Block DEVELOPER_BLOCK = new DevBlock(FabricBlockSettings.of(Material.WOOL, MapColor.ORANGE).nonOpaque().breakByHand(true).strength(0.1F).sounds(BlockSoundGroup.BONE));

    public static final Block OAK_FENCE_DOOR = new FenceDoorBlock(woodenFenceDoorSettings(Blocks.OAK_PLANKS));
    public static final Block SPRUCE_FENCE_DOOR = new FenceDoorBlock(woodenFenceDoorSettings(Blocks.SPRUCE_PLANKS));
    public static final Block BIRCH_FENCE_DOOR = new FenceDoorBlock(woodenFenceDoorSettings(Blocks.BIRCH_PLANKS));
    public static final Block JUNGLE_FENCE_DOOR = new FenceDoorBlock(woodenFenceDoorSettings(Blocks.JUNGLE_PLANKS));
    public static final Block ACACIA_FENCE_DOOR = new FenceDoorBlock(woodenFenceDoorSettings(Blocks.ACACIA_PLANKS));
    public static final Block DARK_OAK_FENCE_DOOR = new FenceDoorBlock(woodenFenceDoorSettings(Blocks.DARK_OAK_PLANKS));
    public static final Block CRIMSON_FENCE_DOOR = new FenceDoorBlock(woodenFenceDoorSettings(Blocks.CRIMSON_PLANKS));
    public static final Block WARPED_FENCE_DOOR = new FenceDoorBlock(woodenFenceDoorSettings(Blocks.WARPED_PLANKS));
    public static final Block IRON_FENCE_DOOR = new FenceDoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_DOOR).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block SPRUCE_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block BIRCH_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block JUNGLE_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block ACACIA_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block DARK_OAK_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block CRIMSON_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block WARPED_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER));
    public static final Block BAMBOO_LADDER = new BambooLadderBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO));

    public static final Block DANDELION_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.DANDELION).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block POPPY_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.POPPY).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block BLUE_ORCHID_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.BLUE_ORCHID).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block ALLIUM_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.ALLIUM).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block AZURE_BLUET_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.AZURE_BLUET).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block RED_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.RED_TULIP).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block ORANGE_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_TULIP).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block WHITE_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.WHITE_TULIP).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block PINK_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.PINK_TULIP).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block OXEYE_DAISY_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.OXEYE_DAISY).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block CORNFLOWER_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.CORNFLOWER).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block LILY_OF_THE_VALLEY_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.LILY_OF_THE_VALLEY).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block WITHER_ROSE_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.WITHER_ROSE).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));

    public static final Block REDSTONE_LANTERN = new RedstoneLanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN).breakByTool(FabricToolTags.PICKAXES).luminance(lightLevelFromState(2, AtbywProperties.POWER_INTENSITY, Properties.LIT)));
    public static final Block SHROOMSTICK = new ShroomStickBlock(FabricBlockSettings.of(AtbywMaterials.SHROOMSTICK).breakByHand(true).breakInstantly().noCollision().nonOpaque().luminance((state) -> 15));

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

    public static final Block IRON_SPIKE_TRAP_SPIKES = new SpikeBlock(AtbywMain.id("iron_spike_trap"), 2.0F, 1, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block GOLD_SPIKE_TRAP_SPIKES = new SpikeBlock(AtbywMain.id("gold_spike_trap"), 0.5F, 0, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block DIAMOND_SPIKE_TRAP_SPIKES = new SpikeBlock(AtbywMain.id("diamond_spike_trap"), 3.0F, 2, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block NETHERITE_SPIKE_TRAP_SPIKES = new SpikeBlock(AtbywMain.id("netherite_spike_trap"), 4.0F, 2, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());

    public static final Block IRON_SPIKE_TRAP = new SpikeTrapBlock(IRON_SPIKE_TRAP_SPIKES, 1.0F, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block GOLD_SPIKE_TRAP = new SpikeTrapBlock(GOLD_SPIKE_TRAP_SPIKES, 0.5F, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block DIAMOND_SPIKE_TRAP = new SpikeTrapBlock(DIAMOND_SPIKE_TRAP_SPIKES, 3.0F, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block NETHERITE_SPIKE_TRAP = new SpikeTrapBlock(NETHERITE_SPIKE_TRAP_SPIKES, 6.0F, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));

    public static final Block TIMER_REPEATER = new TimerRepeaterBlock(FabricBlockSettings.copyOf(Blocks.REPEATER));
    public static final Block REDSTONE_CROSS_PATH = new RedstoneCrossPathBlock(FabricBlockSettings.copyOf(Blocks.REPEATER));

    public static final Block ACACIA_RAILING = new RailingBlock(AtbywMain.id("acacia_railing"), FabricBlockSettings.copyOf(Blocks.ACACIA_FENCE));

    public static final Block LARGE_CHAIN = new LargeChainBlock(FabricBlockSettings.copyOf(Blocks.CHAIN).requiresTool().breakByTool(FabricToolTags.PICKAXES));

    public static final Block OAK_LOG_STAIRS = new PillarStairsBlock(Blocks.OAK_LOG, logSettings(Blocks.OAK_LOG));
    public static final Block SPRUCE_LOG_STAIRS = new PillarStairsBlock(Blocks.SPRUCE_LOG, logSettings(Blocks.SPRUCE_LOG));
    public static final Block BIRCH_LOG_STAIRS = new PillarStairsBlock(Blocks.BIRCH_LOG, logSettings(Blocks.BIRCH_LOG));
    public static final Block JUNGLE_LOG_STAIRS = new PillarStairsBlock(Blocks.JUNGLE_LOG, logSettings(Blocks.JUNGLE_LOG));
    public static final Block ACACIA_LOG_STAIRS = new PillarStairsBlock(Blocks.ACACIA_LOG, logSettings(Blocks.ACACIA_LOG));
    public static final Block DARK_OAK_LOG_STAIRS = new PillarStairsBlock(Blocks.DARK_OAK_LOG, logSettings(Blocks.DARK_OAK_LOG));
    public static final Block CRIMSON_STEM_STAIRS = new PillarStairsBlock(Blocks.CRIMSON_STEM, logSettings(Blocks.CRIMSON_STEM));
    public static final Block WARPED_STEM_STAIRS = new PillarStairsBlock(Blocks.WARPED_STEM, logSettings(Blocks.WARPED_STEM));

    public static final Block STRIPPED_OAK_LOG_STAIRS = new PillarStairsBlock(Blocks.STRIPPED_OAK_LOG, logSettings(Blocks.STRIPPED_OAK_LOG));
    public static final Block STRIPPED_SPRUCE_LOG_STAIRS = new PillarStairsBlock(Blocks.STRIPPED_SPRUCE_LOG, logSettings(Blocks.STRIPPED_SPRUCE_LOG));
    public static final Block STRIPPED_BIRCH_LOG_STAIRS = new PillarStairsBlock(Blocks.STRIPPED_BIRCH_LOG, logSettings(Blocks.STRIPPED_BIRCH_LOG));
    public static final Block STRIPPED_JUNGLE_LOG_STAIRS = new PillarStairsBlock(Blocks.STRIPPED_JUNGLE_LOG, logSettings(Blocks.STRIPPED_JUNGLE_LOG));
    public static final Block STRIPPED_ACACIA_LOG_STAIRS = new PillarStairsBlock(Blocks.STRIPPED_ACACIA_LOG, logSettings(Blocks.STRIPPED_ACACIA_LOG));
    public static final Block STRIPPED_DARK_OAK_LOG_STAIRS = new PillarStairsBlock(Blocks.STRIPPED_DARK_OAK_LOG, logSettings(Blocks.STRIPPED_DARK_OAK_LOG));
    public static final Block STRIPPED_CRIMSON_STEM_STAIRS = new PillarStairsBlock(Blocks.STRIPPED_CRIMSON_STEM, logSettings(Blocks.STRIPPED_CRIMSON_STEM));
    public static final Block STRIPPED_WARPED_STEM_STAIRS = new PillarStairsBlock(Blocks.STRIPPED_WARPED_STEM, logSettings(Blocks.STRIPPED_WARPED_STEM));

    public static final Block OAK_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.OAK_LOG));
    public static final Block SPRUCE_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.SPRUCE_LOG));
    public static final Block BIRCH_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.BIRCH_LOG));
    public static final Block JUNGLE_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.JUNGLE_LOG));
    public static final Block ACACIA_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.ACACIA_LOG));
    public static final Block DARK_OAK_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.DARK_OAK_LOG));
    public static final Block CRIMSON_STEM_SLAB = new PillarSlabBlock(logSettings(Blocks.CRIMSON_STEM));
    public static final Block WARPED_STEM_SLAB = new PillarSlabBlock(logSettings(Blocks.WARPED_STEM));

    public static final Block STRIPPED_OAK_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.STRIPPED_OAK_LOG));
    public static final Block STRIPPED_SPRUCE_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.STRIPPED_SPRUCE_LOG));
    public static final Block STRIPPED_BIRCH_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.STRIPPED_BIRCH_LOG));
    public static final Block STRIPPED_JUNGLE_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.STRIPPED_JUNGLE_LOG));
    public static final Block STRIPPED_ACACIA_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.STRIPPED_ACACIA_LOG));
    public static final Block STRIPPED_DARK_OAK_LOG_SLAB = new PillarSlabBlock(logSettings(Blocks.STRIPPED_DARK_OAK_LOG));
    public static final Block STRIPPED_CRIMSON_STEM_SLAB = new PillarSlabBlock(logSettings(Blocks.STRIPPED_CRIMSON_STEM));
    public static final Block STRIPPED_WARPED_STEM_SLAB = new PillarSlabBlock(logSettings(Blocks.STRIPPED_WARPED_STEM));

    public static final Block CANVAS_BLOCK = createCanvasBlock(false);
    public static final Block GLOWING_CANVAS_BLOCK = createCanvasBlock(true);

    public static final Block REDSTONE_PIPE = new RedstonePipeBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.COPPER));
    public static final Block REDSTONE_PIPE_REPEATER = new RedstonePipeRepeaterBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.COPPER));

    public static final Block TINTING_TABLE = new TintingTableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).requiresTool().breakByTool(FabricToolTags.PICKAXES).sounds(BlockSoundGroup.COPPER));

    public static final Block RAW_CACTUS_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_PLANKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_PLANKS_STAIRS = new StairsBlockSubClass(CACTUS_PLANKS, FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_LADDER = new LadderBlockSubClass(FabricBlockSettings.copyOf(Blocks.LADDER).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_FENCE = new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_SIGN = new AtbywSignBlock(AtbywSignType.CACTUS, FabricBlockSettings.copyOf(Blocks.OAK_SIGN).breakByTool(FabricToolTags.AXES));
    public static final Block CACTUS_WALL_SIGN = new AtbywWallSignBlock(AtbywSignType.CACTUS, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).breakByTool(FabricToolTags.AXES));

    public static void init() {

        //ATBYW REDSTONE
        registerBlock(false, REDSTONE_TAB, "redstone_pipe", REDSTONE_PIPE);
        registerBlock(false, REDSTONE_TAB, "redstone_pipe_repeater", REDSTONE_PIPE_REPEATER);
        registerBlock(false, REDSTONE_TAB, "timer_repeater", TIMER_REPEATER);
        registerBlock(false, REDSTONE_TAB, "redstone_cross_path", REDSTONE_CROSS_PATH);
        registerBlock(false, REDSTONE_TAB, "redstone_lantern", REDSTONE_LANTERN);
        registerBlock(false, REDSTONE_TAB, "redstone_jack_o_lantern", REDSTONE_JACK_O_LANTERN);
        registerBlocks(false, REDSTONE_TAB, "bookshelf_toggle", WoodNames.getNames(), OAK_BOOKSHELF_TOGGLE, SPRUCE_BOOKSHELF_TOGGLE, BIRCH_BOOKSHELF_TOGGLE, JUNGLE_BOOKSHELF_TOGGLE, ACACIA_BOOKSHELF_TOGGLE, DARK_OAK_BOOKSHELF_TOGGLE, CRIMSON_BOOKSHELF_TOGGLE, WARPED_BOOKSHELF_TOGGLE);
        AtbywModInteractionBlocks.initBookshelfToggles();
        registerBlocks(false, REDSTONE_TAB, "pull_switch", FlowerNames.getNames(), DANDELION_PULL_SWITCH, POPPY_PULL_SWITCH, BLUE_ORCHID_PULL_SWITCH, ALLIUM_PULL_SWITCH, AZURE_BLUET_PULL_SWITCH, RED_TULIP_PULL_SWITCH, ORANGE_TULIP_PULL_SWITCH, WHITE_TULIP_PULL_SWITCH, PINK_TULIP_PULL_SWITCH, OXEYE_DAISY_PULL_SWITCH, CORNFLOWER_PULL_SWITCH, LILY_OF_THE_VALLEY_PULL_SWITCH, WITHER_ROSE_PULL_SWITCH);
        registerBlock(false, REDSTONE_TAB, "iron_spike_trap", IRON_SPIKE_TRAP);
        registerBlock(false, REDSTONE_TAB, "gold_spike_trap", GOLD_SPIKE_TRAP);
        registerBlock(false, REDSTONE_TAB, "diamond_spike_trap", DIAMOND_SPIKE_TRAP);
        registerBlock(false, REDSTONE_TAB, "netherite_spike_trap", NETHERITE_SPIKE_TRAP);
        registerBlocks(false, REDSTONE_TAB, "fence_door", WoodNames.getNames(), OAK_FENCE_DOOR, SPRUCE_FENCE_DOOR, BIRCH_FENCE_DOOR, JUNGLE_FENCE_DOOR, ACACIA_FENCE_DOOR, DARK_OAK_FENCE_DOOR, CRIMSON_FENCE_DOOR, WARPED_FENCE_DOOR);
        registerBlock(false, REDSTONE_TAB, "iron_fence_door", IRON_FENCE_DOOR);

        //ATBYW BLOCKS
        registerBlock(false, BLOCKS_TAB, "granite_tiles", GRANITE_TILES);
        registerBlock(false, BLOCKS_TAB, "diorite_bricks", DIORITE_BRICKS);
        registerBlock(false, BLOCKS_TAB, "andesite_bricks", ANDESITE_BRICKS);
        registerBlock(false, BLOCKS_TAB, "granite_tiles_stairs", GRANITE_TILES_STAIRS);
        registerBlock(false, BLOCKS_TAB, "diorite_bricks_stairs", DIORITE_BRICKS_STAIRS);
        registerBlock(false, BLOCKS_TAB, "andesite_bricks_stairs", ANDESITE_BRICKS_STAIRS);
        registerBlock(false, BLOCKS_TAB, "granite_tiles_slab", GRANITE_TILES_SLAB);
        registerBlock(false, BLOCKS_TAB, "diorite_bricks_slab", DIORITE_BRICKS_SLAB);
        registerBlock(false, BLOCKS_TAB, "andesite_bricks_slab", ANDESITE_BRICKS_SLAB);
        registerBlock(false, BLOCKS_TAB, "raw_cactus_planks", RAW_CACTUS_PLANKS);
        registerBlock(false, BLOCKS_TAB, "cactus_planks", CACTUS_PLANKS);
        registerBlock(false, BLOCKS_TAB, "cactus_slab", CACTUS_PLANKS_SLAB);
        registerBlock(false, BLOCKS_TAB, "cactus_stairs", CACTUS_PLANKS_STAIRS);
        registerBlock(false, BLOCKS_TAB, "grass_block_stairs", GRASS_BLOCK_STAIRS);
        registerBlock(false, BLOCKS_TAB, "dirt_stairs", DIRT_STAIRS);
        registerBlock(false, BLOCKS_TAB, "coarse_dirt_stairs", COARSE_DIRT_STAIRS);
        registerBlock(false, BLOCKS_TAB, "podzol_stairs", PODZOL_STAIRS);
        registerBlock(false, BLOCKS_TAB, "rooted_dirt_stairs", ROOTED_DIRT_STAIRS);
        registerBlock(false, BLOCKS_TAB, "mycelium_stairs", MYCELIUM_STAIRS);
        registerBlock(false, BLOCKS_TAB, "grass_path_stairs", GRASS_PATH_STAIRS);
        registerBlock(false, BLOCKS_TAB, "crimson_nylium_stairs", CRIMSON_NYLIUM_STAIRS);
        registerBlock(false, BLOCKS_TAB, "warped_nylium_stairs", WARPED_NYLIUM_STAIRS);
        registerBlock(false, BLOCKS_TAB, "netherrack_stairs", NETHERRACK_STAIRS);
        registerBlock(false, BLOCKS_TAB, "sand_stairs", SAND_STAIRS);
        registerBlock(false, BLOCKS_TAB, "red_sand_stairs", RED_SAND_STAIRS);
        registerBlock(false, BLOCKS_TAB, "gravel_stairs", GRAVEL_STAIRS);
        registerBlock(false, BLOCKS_TAB, "grass_block_slab", GRASS_BLOCK_SLAB);
        registerBlock(false, BLOCKS_TAB, "dirt_slab", DIRT_SLAB);
        registerBlock(false, BLOCKS_TAB, "coarse_dirt_slab", COARSE_DIRT_SLAB);
        registerBlock(false, BLOCKS_TAB, "podzol_slab", PODZOL_SLAB);
        registerBlock(false, BLOCKS_TAB, "rooted_dirt_slab", ROOTED_DIRT_SLAB);
        registerBlock(false, BLOCKS_TAB, "mycelium_slab", MYCELIUM_SLAB);
        registerBlock(false, BLOCKS_TAB, "grass_path_slab", GRASS_PATH_SLAB);
        registerBlock(false, BLOCKS_TAB, "crimson_nylium_slab", CRIMSON_NYLIUM_SLAB);
        registerBlock(false, BLOCKS_TAB, "warped_nylium_slab", WARPED_NYLIUM_SLAB);
        registerBlock(false, BLOCKS_TAB, "netherrack_slab", NETHERRACK_SLAB);
        registerBlock(false, BLOCKS_TAB, "sand_slab", SAND_SLAB);
        registerBlock(false, BLOCKS_TAB, "red_sand_slab", RED_SAND_SLAB);
        registerBlock(false, BLOCKS_TAB, "gravel_slab", GRAVEL_SLAB);
        registerBlocks(false, BLOCKS_TAB, "log_stairs", WoodNames.getNamesOverworld(), OAK_LOG_STAIRS, SPRUCE_LOG_STAIRS, BIRCH_LOG_STAIRS, JUNGLE_LOG_STAIRS, ACACIA_LOG_STAIRS, DARK_OAK_LOG_STAIRS);
        registerBlocks(false, BLOCKS_TAB, "stem_stairs", WoodNames.getNamesNether(), CRIMSON_STEM_STAIRS, WARPED_STEM_STAIRS);
        registerBlocks(false, BLOCKS_TAB, "stripped", "log_stairs", WoodNames.getNamesOverworld(), STRIPPED_OAK_LOG_STAIRS, STRIPPED_SPRUCE_LOG_STAIRS, STRIPPED_BIRCH_LOG_STAIRS, STRIPPED_JUNGLE_LOG_STAIRS, STRIPPED_ACACIA_LOG_STAIRS, STRIPPED_DARK_OAK_LOG_STAIRS);
        registerBlocks(false, BLOCKS_TAB, "stripped", "stem_stairs", WoodNames.getNamesNether(), STRIPPED_CRIMSON_STEM_STAIRS, STRIPPED_WARPED_STEM_STAIRS);
        registerBlocks(false, BLOCKS_TAB, "log_slab", WoodNames.getNamesOverworld(), OAK_LOG_SLAB, SPRUCE_LOG_SLAB, BIRCH_LOG_SLAB, JUNGLE_LOG_SLAB, ACACIA_LOG_SLAB, DARK_OAK_LOG_SLAB);
        registerBlocks(false, BLOCKS_TAB, "stem_slab", WoodNames.getNamesNether(), CRIMSON_STEM_SLAB, WARPED_STEM_SLAB);
        registerBlocks(false, BLOCKS_TAB, "stripped", "log_slab", WoodNames.getNamesOverworld(), STRIPPED_OAK_LOG_SLAB, STRIPPED_SPRUCE_LOG_SLAB, STRIPPED_BIRCH_LOG_SLAB, STRIPPED_JUNGLE_LOG_SLAB, STRIPPED_ACACIA_LOG_SLAB, STRIPPED_DARK_OAK_LOG_SLAB);
        registerBlocks(false, BLOCKS_TAB, "stripped", "stem_slab", WoodNames.getNamesNether(), STRIPPED_CRIMSON_STEM_SLAB, STRIPPED_WARPED_STEM_SLAB);
        registerBlock(false, BLOCKS_TAB, "shattered_glass", SHATTERED_GLASS);
        registerBlock(false, BLOCKS_TAB, "soul_jack_o_lantern", SOUL_JACK_O_LANTERN);
        registerBlocks(false, BLOCKS_TAB, "bookshelf", WoodNames.getNamesNoOak(), SPRUCE_BOOKSHELF, BIRCH_BOOKSHELF, JUNGLE_BOOKSHELF, ACACIA_BOOKSHELF, DARK_OAK_BOOKSHELF, CRIMSON_BOOKSHELF, WARPED_BOOKSHELF);
        registerBlock(false, BLOCKS_TAB, "purpur_tiles", PURPUR_TILES);
        registerBlock(false, BLOCKS_TAB, "chiseled_purpur_block", CHISELED_PURPUR_BLOCK);
        registerBlock(false, BLOCKS_TAB, "cut_purpur_block", CUT_PURPUR_BLOCK);
        registerBlock(false, BLOCKS_TAB, "smooth_purpur_block", SMOOTH_PURPUR_BLOCK);
        registerBlock(false, BLOCKS_TAB, "purpur_tiles_stairs", PURPUR_TILES_STAIRS);
        registerBlock(false, BLOCKS_TAB, "cut_purpur_stairs", CUT_PURPUR_STAIRS);
        registerBlock(false, BLOCKS_TAB, "smooth_purpur_stairs", SMOOTH_PURPUR_STAIRS);
        registerBlock(false, BLOCKS_TAB, "purpur_tiles_slab", PURPUR_TILES_SLAB);
        registerBlock(false, BLOCKS_TAB, "cut_purpur_slab", CUT_PURPUR_SLAB);
        registerBlock(false, BLOCKS_TAB, "smooth_purpur_slab", SMOOTH_PURPUR_SLAB);
        registerBlock(false, BLOCKS_TAB, "compacted_snow_block", COMPACTED_SNOW_BLOCK);
        registerBlock(false, BLOCKS_TAB, "compacted_snow_bricks", COMPACTED_SNOW_BRICKS);
        registerBlock(false, BLOCKS_TAB, "chiseled_compacted_snow_bricks", CHISELED_COMPACTED_SNOW_BRICKS);
        registerBlock(false, BLOCKS_TAB, "packed_ice_bricks", PACKED_ICE_BRICKS);
        registerBlock(false, BLOCKS_TAB, "chiseled_packed_ice_bricks", CHISELED_PACKED_ICE_BRICKS);
        registerBlock(false, BLOCKS_TAB, "blue_ice_bricks", BLUE_ICE_BRICKS);
        registerBlock(false, BLOCKS_TAB, "chiseled_blue_ice_bricks", CHISELED_BLUE_ICE_BRICKS);
        registerBlock(false, BLOCKS_TAB, "compacted_snow_block_stairs", COMPACTED_SNOW_BLOCK_STAIRS);
        registerBlock(false, BLOCKS_TAB, "compacted_snow_bricks_stairs", COMPACTED_SNOW_BRICKS_STAIRS);
        registerBlock(false, BLOCKS_TAB, "packed_ice_stairs", PACKED_ICE_STAIRS);
        registerBlock(false, BLOCKS_TAB, "blue_ice_stairs", BLUE_ICE_STAIRS);
        registerBlock(false, BLOCKS_TAB, "packed_ice_bricks_stairs", PACKED_ICE_BRICKS_STAIRS);
        registerBlock(false, BLOCKS_TAB, "blue_ice_bricks_stairs", BLUE_ICE_BRICKS_STAIRS);
        registerBlock(false, BLOCKS_TAB, "compacted_snow_block_slab", COMPACTED_SNOW_BLOCK_SLAB);
        registerBlock(false, BLOCKS_TAB, "compacted_snow_bricks_slab", COMPACTED_SNOW_BRICKS_SLAB);
        registerBlock(false, BLOCKS_TAB, "packed_ice_slab", PACKED_ICE_SLAB);
        registerBlock(false, BLOCKS_TAB, "blue_ice_slab", BLUE_ICE_SLAB);
        registerBlock(false, BLOCKS_TAB, "packed_ice_bricks_slab", PACKED_ICE_BRICKS_SLAB);
        registerBlock(false, BLOCKS_TAB, "blue_ice_bricks_slab", BLUE_ICE_BRICKS_SLAB);
        registerBlocks(false, BLOCKS_TAB, "stained_shattered_glass", AtbywUtils.dyeColorNames(), WHITE_STAINED_SHATTERED_GLASS, ORANGE_STAINED_SHATTERED_GLASS, MAGENTA_STAINED_SHATTERED_GLASS, LIGHT_BLUE_STAINED_SHATTERED_GLASS, YELLOW_STAINED_SHATTERED_GLASS, LIME_STAINED_SHATTERED_GLASS, PINK_STAINED_SHATTERED_GLASS, GRAY_STAINED_SHATTERED_GLASS, LIGHT_GRAY_STAINED_SHATTERED_GLASS, CYAN_STAINED_SHATTERED_GLASS, PURPLE_STAINED_SHATTERED_GLASS, BLUE_STAINED_SHATTERED_GLASS, BROWN_STAINED_SHATTERED_GLASS, GREEN_STAINED_SHATTERED_GLASS, RED_STAINED_SHATTERED_GLASS, BLACK_STAINED_SHATTERED_GLASS);
        registerBlock(false, BLOCKS_TAB, "basalt_bricks", BASALT_BRICKS);
        registerBlock(false, BLOCKS_TAB, "basalt_pillar", BASALT_PILLAR);
        registerBlock( false, BLOCKS_TAB, "terracotta_stairs", TERRACOTTA_STAIRS);
        registerBlocks(false, BLOCKS_TAB, "terracotta_stairs", AtbywUtils.dyeColorNames(), WHITE_TERRACOTTA_STAIRS, ORANGE_TERRACOTTA_STAIRS, MAGENTA_TERRACOTTA_STAIRS, LIGHT_BLUE_TERRACOTTA_STAIRS, YELLOW_TERRACOTTA_STAIRS, LIME_TERRACOTTA_STAIRS, PINK_TERRACOTTA_STAIRS, GRAY_TERRACOTTA_STAIRS, LIGHT_GRAY_TERRACOTTA_STAIRS, CYAN_TERRACOTTA_STAIRS, PURPLE_TERRACOTTA_STAIRS, BLUE_TERRACOTTA_STAIRS, BROWN_TERRACOTTA_STAIRS, GREEN_TERRACOTTA_STAIRS, RED_TERRACOTTA_STAIRS, BLACK_TERRACOTTA_STAIRS);
        registerBlock( false, BLOCKS_TAB, "terracotta_slab", TERRACOTTA_SLAB);
        registerBlocks(false, BLOCKS_TAB, "terracotta_slab", AtbywUtils.dyeColorNames(), WHITE_TERRACOTTA_SLAB, ORANGE_TERRACOTTA_SLAB, MAGENTA_TERRACOTTA_SLAB, LIGHT_BLUE_TERRACOTTA_SLAB, YELLOW_TERRACOTTA_SLAB, LIME_TERRACOTTA_SLAB, PINK_TERRACOTTA_SLAB, GRAY_TERRACOTTA_SLAB, LIGHT_GRAY_TERRACOTTA_SLAB, CYAN_TERRACOTTA_SLAB, PURPLE_TERRACOTTA_SLAB, BLUE_TERRACOTTA_SLAB, BROWN_TERRACOTTA_SLAB, GREEN_TERRACOTTA_SLAB, RED_TERRACOTTA_SLAB, BLACK_TERRACOTTA_SLAB);
        registerBlock( false, BLOCKS_TAB, "terracotta_bricks", TERRACOTTA_BRICKS);
        registerBlocks(false, BLOCKS_TAB, "terracotta_bricks", AtbywUtils.dyeColorNames(), WHITE_TERRACOTTA_BRICKS, ORANGE_TERRACOTTA_BRICKS, MAGENTA_TERRACOTTA_BRICKS, LIGHT_BLUE_TERRACOTTA_BRICKS, YELLOW_TERRACOTTA_BRICKS, LIME_TERRACOTTA_BRICKS, PINK_TERRACOTTA_BRICKS, GRAY_TERRACOTTA_BRICKS, LIGHT_GRAY_TERRACOTTA_BRICKS, CYAN_TERRACOTTA_BRICKS, PURPLE_TERRACOTTA_BRICKS, BLUE_TERRACOTTA_BRICKS, BROWN_TERRACOTTA_BRICKS, GREEN_TERRACOTTA_BRICKS, RED_TERRACOTTA_BRICKS, BLACK_TERRACOTTA_BRICKS);
        registerBlock( false, BLOCKS_TAB, "terracotta_bricks_stairs", TERRACOTTA_BRICKS_STAIRS);
        registerBlocks(false, BLOCKS_TAB, "terracotta_bricks_stairs", AtbywUtils.dyeColorNames(), WHITE_TERRACOTTA_BRICKS_STAIRS, ORANGE_TERRACOTTA_BRICKS_STAIRS, MAGENTA_TERRACOTTA_BRICKS_STAIRS, LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, YELLOW_TERRACOTTA_BRICKS_STAIRS, LIME_TERRACOTTA_BRICKS_STAIRS, PINK_TERRACOTTA_BRICKS_STAIRS, GRAY_TERRACOTTA_BRICKS_STAIRS, LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, CYAN_TERRACOTTA_BRICKS_STAIRS, PURPLE_TERRACOTTA_BRICKS_STAIRS, BLUE_TERRACOTTA_BRICKS_STAIRS, BROWN_TERRACOTTA_BRICKS_STAIRS, GREEN_TERRACOTTA_BRICKS_STAIRS, RED_TERRACOTTA_BRICKS_STAIRS, BLACK_TERRACOTTA_BRICKS_STAIRS);
        registerBlock( false, BLOCKS_TAB, "terracotta_bricks_slab", TERRACOTTA_BRICKS_SLAB);
        registerBlocks(false, BLOCKS_TAB, "terracotta_bricks_slab", AtbywUtils.dyeColorNames(), WHITE_TERRACOTTA_BRICKS_SLAB, ORANGE_TERRACOTTA_BRICKS_SLAB, MAGENTA_TERRACOTTA_BRICKS_SLAB, LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, YELLOW_TERRACOTTA_BRICKS_SLAB, LIME_TERRACOTTA_BRICKS_SLAB, PINK_TERRACOTTA_BRICKS_SLAB, GRAY_TERRACOTTA_BRICKS_SLAB, LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, CYAN_TERRACOTTA_BRICKS_SLAB, PURPLE_TERRACOTTA_BRICKS_SLAB, BLUE_TERRACOTTA_BRICKS_SLAB, BROWN_TERRACOTTA_BRICKS_SLAB, GREEN_TERRACOTTA_BRICKS_SLAB, RED_TERRACOTTA_BRICKS_SLAB, BLACK_TERRACOTTA_BRICKS_SLAB);
        registerBlocks(false, BLOCKS_TAB, "concrete_stairs", AtbywUtils.dyeColorNames(), WHITE_CONCRETE_STAIRS, ORANGE_CONCRETE_STAIRS, MAGENTA_CONCRETE_STAIRS, LIGHT_BLUE_CONCRETE_STAIRS, YELLOW_CONCRETE_STAIRS, LIME_CONCRETE_STAIRS, PINK_CONCRETE_STAIRS, GRAY_CONCRETE_STAIRS, LIGHT_GRAY_CONCRETE_STAIRS, CYAN_CONCRETE_STAIRS, PURPLE_CONCRETE_STAIRS, BLUE_CONCRETE_STAIRS, BROWN_CONCRETE_STAIRS, GREEN_CONCRETE_STAIRS, RED_CONCRETE_STAIRS, BLACK_CONCRETE_STAIRS);
        registerBlocks(false, BLOCKS_TAB, "concrete_slab", AtbywUtils.dyeColorNames(), WHITE_CONCRETE_SLAB, ORANGE_CONCRETE_SLAB, MAGENTA_CONCRETE_SLAB, LIGHT_BLUE_CONCRETE_SLAB, YELLOW_CONCRETE_SLAB, LIME_CONCRETE_SLAB, PINK_CONCRETE_SLAB, GRAY_CONCRETE_SLAB, LIGHT_GRAY_CONCRETE_SLAB, CYAN_CONCRETE_SLAB, PURPLE_CONCRETE_SLAB, BLUE_CONCRETE_SLAB, BROWN_CONCRETE_SLAB, GREEN_CONCRETE_SLAB, RED_CONCRETE_SLAB, BLACK_CONCRETE_SLAB);
        registerBlocks(false, BLOCKS_TAB, "cinder_bricks", AtbywUtils.dyeColorNames(), WHITE_CINDER_BLOCKS, ORANGE_CINDER_BLOCKS, MAGENTA_CINDER_BLOCKS, LIGHT_BLUE_CINDER_BLOCKS, YELLOW_CINDER_BLOCKS, LIME_CINDER_BLOCKS, PINK_CINDER_BLOCKS, GRAY_CINDER_BLOCKS, LIGHT_GRAY_CINDER_BLOCKS, CYAN_CINDER_BLOCKS, PURPLE_CINDER_BLOCKS, BLUE_CINDER_BLOCKS, BROWN_CINDER_BLOCKS, GREEN_CINDER_BLOCKS, RED_CINDER_BLOCKS, BLACK_CINDER_BLOCKS);

        //ATBYW DECO
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

        //ATBYW MISC
        registerBlock(false, MISC_TAB, "dev_block", DEVELOPER_BLOCK);

        //Item-less blocks
        registerBlockOnly("shroomstick", SHROOMSTICK);
        registerBlockOnly("iron_spike_trap_spikes", IRON_SPIKE_TRAP_SPIKES);
        registerBlockOnly("gold_spike_trap_spikes", GOLD_SPIKE_TRAP_SPIKES);
        registerBlockOnly("diamond_spike_trap_spikes", DIAMOND_SPIKE_TRAP_SPIKES);
        registerBlockOnly("netherite_spike_trap_spikes", NETHERITE_SPIKE_TRAP_SPIKES);

        LOGGER.info("ATBYW Blocks Inintiliazed");
    }
}
