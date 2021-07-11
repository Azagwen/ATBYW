package net.azagwen.atbyw.block.piston;

import net.azagwen.atbyw.block.AtbywExperimentalBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.PistonBlockEntity;

public enum PistonWoodTypes implements PistonWoodType {
    OAK("oak", Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.PISTON_HEAD, Blocks.MOVING_PISTON, BlockEntityType.PISTON),
    SPRUCE("spruce", AtbywExperimentalBlocks.SPRUCE_PISTON, AtbywExperimentalBlocks.SPRUCE_STICKY_PISTON, AtbywExperimentalBlocks.SPRUCE_PISTON_HEAD, AtbywExperimentalBlocks.SPRUCE_MOVING_PISTON, AtbywExperimentalBlocks.SPRUCE_PISTON_ENTITY),
    BIRCH("birch", AtbywExperimentalBlocks.BIRCH_PISTON, AtbywExperimentalBlocks.BIRCH_STICKY_PISTON, AtbywExperimentalBlocks.BIRCH_PISTON_HEAD, AtbywExperimentalBlocks.BIRCH_MOVING_PISTON, AtbywExperimentalBlocks.BIRCH_PISTON_ENTITY),
    JUNGLE("jungle", AtbywExperimentalBlocks.JUNGLE_PISTON, AtbywExperimentalBlocks.JUNGLE_STICKY_PISTON, AtbywExperimentalBlocks.JUNGLE_PISTON_HEAD, AtbywExperimentalBlocks.JUNGLE_MOVING_PISTON, AtbywExperimentalBlocks.JUNGLE_PISTON_ENTITY),
    ACACIA("acacia", AtbywExperimentalBlocks.ACACIA_PISTON, AtbywExperimentalBlocks.ACACIA_STICKY_PISTON, AtbywExperimentalBlocks.ACACIA_PISTON_HEAD, AtbywExperimentalBlocks.ACACIA_MOVING_PISTON, AtbywExperimentalBlocks.ACACIA_PISTON_ENTITY),
    DARK_OAK("dark_oak", AtbywExperimentalBlocks.DARK_OAK_PISTON, AtbywExperimentalBlocks.DARK_OAK_STICKY_PISTON, AtbywExperimentalBlocks.DARK_OAK_PISTON_HEAD, AtbywExperimentalBlocks.DARK_OAK_MOVING_PISTON, AtbywExperimentalBlocks.DARK_OAK_PISTON_ENTITY),
    CRIMSON("crimson", AtbywExperimentalBlocks.CRIMSON_PISTON, AtbywExperimentalBlocks.CRIMSON_STICKY_PISTON, AtbywExperimentalBlocks.CRIMSON_PISTON_HEAD, AtbywExperimentalBlocks.CRIMSON_MOVING_PISTON, AtbywExperimentalBlocks.CRIMSON_PISTON_ENTITY),
    WARPED("warped", AtbywExperimentalBlocks.WARPED_PISTON, AtbywExperimentalBlocks.WARPED_STICKY_PISTON, AtbywExperimentalBlocks.WARPED_PISTON_HEAD, AtbywExperimentalBlocks.WARPED_MOVING_PISTON, AtbywExperimentalBlocks.WARPED_PISTON_ENTITY);

    private final Block piston;
    private final Block sticky_piston;
    private final Block piston_head;
    private final Block moving_piston;
    private final BlockEntityType<PistonBlockEntity> piston_entity;
    private final String name;

    PistonWoodTypes(String name, Block piston, Block sticky_piston, Block piston_head, Block moving_piston, BlockEntityType<PistonBlockEntity> piston_entity) {
        this.piston = piston;
        this.sticky_piston = sticky_piston;
        this.piston_head = piston_head;
        this.moving_piston = moving_piston;
        this.piston_entity = piston_entity;
        this.name = name;
    }

    @Override
    public Block getPiston() {
        return piston;
    }

    @Override
    public Block getStickyPiston() {
        return sticky_piston;
    }

    @Override
    public Block getPistonHead() {
        return piston_head;
    }

    @Override
    public Block getMovingPiston() {
        return moving_piston;
    }

    @Override
    public String toString() {
        return "PistonWoodType{piston=(" + this.getPiston() + "), sticky_piston=(" + this.getStickyPiston() + "), piston_head=(" + this.getPistonHead() + "), moving_piston=(" + this.getMovingPiston() + ")}";
    }
}
