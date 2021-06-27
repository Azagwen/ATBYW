package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.piston.PistonDuck;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.PistonBlockEntity;

import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywExperimentalBlocks {

    private static PistonBlock createPistonBlock(boolean sticky, String type) {
        PistonBlock block = new PistonBlock(sticky, FabricBlockSettings.copyOf(Blocks.PISTON).breakByTool(FabricToolTags.PICKAXES));
        ((PistonDuck) block).setType(type);
        LOGGER.info(sticky ? "sticky_piston_wood_type = " + type : "piston_wood_type = " + type);

        return block;
    }

    private static PistonHeadBlock createPistonHeadBlock(String type) {
        PistonHeadBlock block = new PistonHeadBlock(FabricBlockSettings.copyOf(Blocks.PISTON_HEAD).breakByTool(FabricToolTags.PICKAXES));
        ((PistonDuck) block).setType(type);
        LOGGER.info("piston_head_wood_type = " + type);

        return block;
    }

    private static PistonExtensionBlock createPistonExtensionBlock(String type) {
        PistonExtensionBlock block = new PistonExtensionBlock(FabricBlockSettings.copyOf(Blocks.MOVING_PISTON));
//        ((PistonDuck) block).setType(type);
        LOGGER.info("moving_piston_wood_type = " + type);

        return block;
    }

    public static final Block SPRUCE_PISTON = createPistonBlock(false, "SPRUCE");
    public static final Block BIRCH_PISTON = createPistonBlock(false, "BIRCH");
    public static final Block JUNGLE_PISTON = createPistonBlock(false, "JUNGLE");
    public static final Block ACACIA_PISTON = createPistonBlock(false, "ACACIA");
    public static final Block DARK_OAK_PISTON = createPistonBlock(false, "DARK_OAK");
    public static final Block CRIMSON_PISTON = createPistonBlock(false, "CRIMSON");
    public static final Block WARPED_PISTON = createPistonBlock(false, "WARPED");

    public static final Block SPRUCE_STICKY_PISTON = createPistonBlock(true, "SPRUCE");
    public static final Block BIRCH_STICKY_PISTON = createPistonBlock(true, "BIRCH");
    public static final Block JUNGLE_STICKY_PISTON = createPistonBlock(true, "JUNGLE");
    public static final Block ACACIA_STICKY_PISTON = createPistonBlock(true, "ACACIA");
    public static final Block DARK_OAK_STICKY_PISTON = createPistonBlock(true, "DARK_OAK");
    public static final Block CRIMSON_STICKY_PISTON = createPistonBlock(true, "CRIMSON");
    public static final Block WARPED_STICKY_PISTON = createPistonBlock(true, "WARPED");

    public static final Block SPRUCE_PISTON_HEAD = createPistonHeadBlock("SPRUCE");
    public static final Block BIRCH_PISTON_HEAD = createPistonHeadBlock("BIRCH");
    public static final Block JUNGLE_PISTON_HEAD = createPistonHeadBlock("JUNGLE");
    public static final Block ACACIA_PISTON_HEAD = createPistonHeadBlock("ACACIA");
    public static final Block DARK_OAK_PISTON_HEAD = createPistonHeadBlock("DARK_OAK");
    public static final Block CRIMSON_PISTON_HEAD = createPistonHeadBlock("CRIMSON");
    public static final Block WARPED_PISTON_HEAD = createPistonHeadBlock("WARPED");

    public static final Block SPRUCE_MOVING_PISTON = createPistonExtensionBlock("SPRUCE");
    public static final Block BIRCH_MOVING_PISTON = createPistonExtensionBlock("BIRCH");
    public static final Block JUNGLE_MOVING_PISTON = createPistonExtensionBlock("JUNGLE");
    public static final Block ACACIA_MOVING_PISTON = createPistonExtensionBlock("ACACIA");
    public static final Block DARK_OAK_MOVING_PISTON = createPistonExtensionBlock("DARK_OAK");
    public static final Block CRIMSON_MOVING_PISTON = createPistonExtensionBlock("CRIMSON");
    public static final Block WARPED_MOVING_PISTON = createPistonExtensionBlock("WARPED");

    public static BlockEntityType<PistonBlockEntity> SPRUCE_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> BIRCH_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> JUNGLE_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> ACACIA_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> DARK_OAK_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> CRIMSON_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> WARPED_PISTON_ENTITY;

    public static void initPistons() {

    }

    public static void initPistonEntities() {

    }
}
