package net.azagwen.atbyw.blocks.statues;

import net.minecraft.util.Identifier;
import net.minecraft.util.shape.VoxelShape;

import static net.azagwen.atbyw.main.AtbywMain.AtbywID;

public enum StatueBlockMobTypes implements StatueBlockMobType {
    //Bugs & Shell creatures
    BEE("bee", AtbywID("gameplay/bee_statue"), StatueVoxelShapes.BEE_OUTLINES, StatueVoxelShapes.BEE_COLLISIONS),
    SILVERFISH("silverfish", AtbywID("gameplay/silverfish_statue"), StatueVoxelShapes.SILVERFISH_OUTLINES),
    ENDERMITE("endermite", AtbywID("gameplay/endermite_statue"), StatueVoxelShapes.ENDERMITE_OUTLINES),
    SHULKER("shulker", AtbywID("gameplay/shulker_statue"), StatueVoxelShapes.SHULKER_OUTLINES, StatueVoxelShapes.SHULKER_COLLISIONS),

    //Mamals
    CAT("cat", AtbywID("gameplay/cat_statue"), StatueVoxelShapes.CAT_OUTLINES),
    WOLF("wolf", AtbywID("gameplay/wolf_statue"), StatueVoxelShapes.WOLF_OUTLINES),
    CHICKEN("chicken", AtbywID("gameplay/chicken_statue"), StatueVoxelShapes.CHICKEN_OUTLINES),
    RABBIT("rabbit", AtbywID("gameplay/rabbit_statue"), StatueVoxelShapes.RABBIT_OUTLINES, StatueVoxelShapes.RABBIT_COLLISIONS),
    FOX("fox", AtbywID("gameplay/fox_statue"), StatueVoxelShapes.FOX_OUTLINES),

    //Fishes
    COD("cod", AtbywID("gameplay/cod_statue"), StatueVoxelShapes.COD_OUTLINES),
    SALMON("salmon", AtbywID("gameplay/salmon_statue"), StatueVoxelShapes.SALMON_OUTLINES),
    PUFFER_FISH("puffer_fish", AtbywID("gameplay/puffer_fish_statue"), StatueVoxelShapes.PUFFER_FISH_OUTLINES),

    //Slimes & others
    SLIME("slime", AtbywID("gameplay/slime_statue"), StatueVoxelShapes.SLIME_SMALL_OUTLINES),
    MAGMA_CUBE("magma_cube", AtbywID("gameplay/magma_cube_statue"), StatueVoxelShapes.SLIME_SMALL_OUTLINES);

    //Negative Z = NORTH
    //Positive Z = SOUTH
    //Negative X = EAST
    //Positive X = WEST

    private final String name;
    private final Identifier lootTableID;
    private final VoxelShape[] outlineShapes;
    private VoxelShape[] collisionShapes;

    private String makeName(String name) {
        return name + "_statue";
    }

    StatueBlockMobTypes(String name, Identifier lootTableID, VoxelShape[] outlineShapes, VoxelShape[] collisionShapes) {
        this.name = makeName(name);
        this.lootTableID = lootTableID;
        this.outlineShapes = outlineShapes;
        this.collisionShapes = collisionShapes;
    }

    StatueBlockMobTypes(String name, Identifier lootTableID, VoxelShape[] outlineShapes) {
        this.name = makeName(name);
        this.lootTableID = lootTableID;
        this.outlineShapes = outlineShapes;

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Identifier getLootTable() {
        return lootTableID;
    }

    @Override
    public VoxelShape getOutlineShape(int direction) {
        return this.outlineShapes[direction];
    }

    @Override
    public VoxelShape getCollisionShape(int direction) {
        return this.collisionShapes[direction];
    }

    @Override
    public VoxelShape[] getOutlineShapes() {
        return this.outlineShapes;
    }

    @Override
    public VoxelShape[] getCollisionShapes() {
        return this.collisionShapes;
    }

}
