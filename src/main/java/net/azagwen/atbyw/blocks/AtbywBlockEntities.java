package net.azagwen.atbyw.blocks;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.blocks.AtbywExperimentalBlocks.*;
import static net.azagwen.atbyw.main.AtbywMain.newID;

public class AtbywBlockEntities {
    public static BlockEntityType<PistonBlockEntity> SPRUCE_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> BIRCH_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> JUNGLE_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> ACACIA_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> DARK_OAK_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> CRIMSON_PISTON_ENTITY;
    public static BlockEntityType<PistonBlockEntity> WARPED_PISTON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, newID("warped_piston"), BlockEntityType.Builder.create(PistonBlockEntity::new, WARPED_MOVING_PISTON).build(null));

    public static void initPistons() {
        SPRUCE_PISTON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, newID("spruce_piston"), BlockEntityType.Builder.create(PistonBlockEntity::new, SPRUCE_MOVING_PISTON).build(null));
        BIRCH_PISTON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, newID("birch_piston"), BlockEntityType.Builder.create(PistonBlockEntity::new, BIRCH_MOVING_PISTON).build(null));
        JUNGLE_PISTON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, newID("jungle_piston"), BlockEntityType.Builder.create(PistonBlockEntity::new, JUNGLE_MOVING_PISTON).build(null));
        ACACIA_PISTON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, newID("acacia_piston"), BlockEntityType.Builder.create(PistonBlockEntity::new, ACACIA_MOVING_PISTON).build(null));
        DARK_OAK_PISTON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, newID("dark_oak_piston"), BlockEntityType.Builder.create(PistonBlockEntity::new, DARK_OAK_MOVING_PISTON).build(null));
        CRIMSON_PISTON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, newID("crimson_piston"), BlockEntityType.Builder.create(PistonBlockEntity::new, CRIMSON_MOVING_PISTON).build(null));
    }
}
