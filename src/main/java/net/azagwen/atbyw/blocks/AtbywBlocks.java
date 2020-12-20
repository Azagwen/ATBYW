package net.azagwen.atbyw.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
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

    private static FabricBlockSettings MakeTerracotta() {
        return FabricBlockSettings.of(Material.STONE, MaterialColor.ORANGE).requiresTool().breakByTool(FabricToolTags.PICKAXES).strength(1.25F, 4.2F);
    }

    private static FabricBlockSettings MakeWoodenFenceDoor(Block copiedMatColor) {
        return FabricBlockSettings.of(Material.WOOD, copiedMatColor.getDefaultMaterialColor()).breakByTool(FabricToolTags.AXES).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
    }

    private static Block MakeStairs(Block copiedBlock, boolean requiresTool, Tag<Item> tag) {
        Block toolRequired = new StairsBlockSubClass(copiedBlock.getDefaultState(), FabricBlockSettings.copyOf(copiedBlock).requiresTool().breakByTool(tag));
        Block toolNotRequired = new StairsBlockSubClass(copiedBlock.getDefaultState(), FabricBlockSettings.copyOf(copiedBlock).breakByTool(tag));

        return requiresTool ? toolRequired : toolNotRequired;
    }


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

    public static final Block BASALT_BRICKS = new Block(MakeBasalt());
    public static final Block BASALT_PILLAR = new PillarBlock(MakeBasalt());
    public static final Block TERRACOTTA_BRICKS = new Block(MakeTerracotta());
    public static final Block TERRACOTTA_STAIRS = new StairsBlockSubClass(Blocks.TERRACOTTA.getDefaultState(), FabricBlockSettings.copy(Blocks.TERRACOTTA));
    public static final Block TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.TERRACOTTA));
    public static final Block TERRACOTTA_BRICKS_STAIRS = new StairsBlockSubClass(TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.copy(TERRACOTTA_BRICKS));
    public static final Block TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.copyOf(TERRACOTTA_BRICKS));
    public static final Block TERRACOTTA_BRICKS_WALL = new WallBlock(FabricBlockSettings.copyOf(TERRACOTTA_BRICKS));
    public static final Block[] TERRACOTTA_BRICKS_COLORS = MakeMultiBlock(16, MakeTerracotta());
    public static final Block[] TERRACOTTA_STAIRS_COLORS = MakeMultiStairs(16, TERRACOTTA_COLORS);
    public static final Block[] TERRACOTTA_SLAB_COLORS = MakeMultiSlab(16, TERRACOTTA_COLORS);
    public static final Block[] TERRACOTTA_BRICKS_STAIRS_COLORS = MakeMultiStairs(16, TERRACOTTA_BRICKS_COLORS);
    public static final Block[] TERRACOTTA_BRICKS_SLAB_COLORS = MakeMultiSlab(16, TERRACOTTA_BRICKS_COLORS);
    public static final Block[] TERRACOTTA_BRICKS_WALL_COLORS = MakeMultiWall(16, TERRACOTTA_BRICKS_COLORS);

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

    public static final Block DIRT_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.DIRT).breakByTool(FabricToolTags.SHOVELS));
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

        registerBlock(false, ATBYW_REDSTONE, "oak_fence_door", OAK_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "spruce_fence_door", SPRUCE_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "birch_fence_door", BIRCH_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "jungle_fence_door", JUNGLE_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "acacia_fence_door", ACACIA_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "dark_oak_fence_door", DARK_OAK_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "crimson_fence_door", CRIMSON_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "warped_fence_door", WARPED_FENCE_DOOR);
        registerBlock(false, ATBYW_REDSTONE, "iron_fence_door", IRON_FENCE_DOOR);

        registerBlock(false, ATBYW_BLOCKS, "spruce_bookshelf", SPRUCE_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "birch_bookshelf", BIRCH_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "jungle_bookshelf", JUNGLE_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "acacia_bookshelf", ACACIA_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "dark_oak_bookshelf", DARK_OAK_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "crimson_bookshelf", CRIMSON_BOOKSHELF);
        registerBlock(false, ATBYW_BLOCKS, "warped_bookshelf", WARPED_BOOKSHELF);

        registerBlock(false, ATBYW_REDSTONE, "oak_bookshelf_toggle", OAK_BOOKSHELF_TOGGLE);
        registerBlock(false, ATBYW_REDSTONE, "spruce_bookshelf_toggle", SPRUCE_BOOKSHELF_TOGGLE);
        registerBlock(false, ATBYW_REDSTONE, "birch_bookshelf_toggle", BIRCH_BOOKSHELF_TOGGLE);
        registerBlock(false, ATBYW_REDSTONE, "jungle_bookshelf_toggle", JUNGLE_BOOKSHELF_TOGGLE);
        registerBlock(false, ATBYW_REDSTONE, "acacia_bookshelf_toggle", ACACIA_BOOKSHELF_TOGGLE);
        registerBlock(false, ATBYW_REDSTONE, "dark_oak_bookshelf_toggle", DARK_OAK_BOOKSHELF_TOGGLE);
        registerBlock(false, ATBYW_REDSTONE, "crimson_bookshelf_toggle", CRIMSON_BOOKSHELF_TOGGLE);
        registerBlock(false, ATBYW_REDSTONE, "warped_bookshelf_toggle", WARPED_BOOKSHELF_TOGGLE);

        registerBlock(false, ATBYW_BLOCKS, "spruce_ladder", SPRUCE_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "birch_ladder", BIRCH_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "jungle_ladder", JUNGLE_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "acacia_ladder", ACACIA_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "dark_oak_ladder", DARK_OAK_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "crimson_ladder", CRIMSON_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "warped_ladder", WARPED_LADDER);
        registerBlock(false, ATBYW_BLOCKS, "bamboo_ladder", BAMBOO_LADDER);

        registerBlock(false, ATBYW_BLOCKS, "terracotta_bricks", TERRACOTTA_BRICKS);
        registerMultiBlock(false, ATBYW_BLOCKS, "terracotta_bricks", colorNames, TERRACOTTA_BRICKS_COLORS);

        registerBlock(false, ATBYW_BLOCKS, "terracotta_stairs", TERRACOTTA_STAIRS);
        registerMultiBlock(false, ATBYW_BLOCKS, "terracotta_stairs", colorNames, TERRACOTTA_STAIRS_COLORS);

        registerBlock(false, ATBYW_BLOCKS, "terracotta_slab", TERRACOTTA_SLAB);
        registerMultiBlock(false, ATBYW_BLOCKS, "terracotta_slab", colorNames, TERRACOTTA_SLAB_COLORS);

        registerBlock(false, ATBYW_BLOCKS, "terracotta_bricks_stairs", TERRACOTTA_BRICKS_STAIRS);
        registerMultiBlock(false, ATBYW_BLOCKS, "terracotta_bricks_stairs", colorNames, TERRACOTTA_BRICKS_STAIRS_COLORS);

        registerBlock(false, ATBYW_BLOCKS, "terracotta_bricks_slab", TERRACOTTA_BRICKS_SLAB);
        registerMultiBlock(false, ATBYW_BLOCKS, "terracotta_bricks_slab", colorNames, TERRACOTTA_BRICKS_SLAB_COLORS);

        registerBlock(false, ATBYW_BLOCKS, "terracotta_bricks_wall", TERRACOTTA_BRICKS_WALL);
        registerMultiBlock(false, ATBYW_BLOCKS, "terracotta_bricks_wall", colorNames, TERRACOTTA_BRICKS_WALL_COLORS);

        registerBlock(false, ATBYW_BLOCKS, "basalt_bricks", BASALT_BRICKS);
        registerBlock(false, ATBYW_BLOCKS, "basalt_pillar", BASALT_PILLAR);
        registerBlock(false, ATBYW_MISC, "dev_block", DEVELOPER_BLOCK);


    }
}
