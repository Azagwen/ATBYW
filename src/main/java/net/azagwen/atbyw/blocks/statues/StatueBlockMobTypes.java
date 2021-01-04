package net.azagwen.atbyw.blocks.statues;

import net.azagwen.atbyw.blocks.AtbywBlockEntities;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.shape.VoxelShape;

import java.util.function.Supplier;

public enum StatueBlockMobTypes implements StatueBlockMobType {
    CHICKEN("chicken_statue", EntityType.CHICKEN.getLootTableId(), () -> AtbywBlockEntities.CHICKEN_STATUE, StatueVoxelShapes.CHICKEN_OUTLINES, StatueVoxelShapes.CHICKEN_COLLISIONS),
    WOLF("wolf_statue", EntityType.WOLF.getLootTableId(), () -> AtbywBlockEntities.WOLF_STATUE, StatueVoxelShapes.WOLF_OUTLINES, StatueVoxelShapes.WOLF_COLLISIONS),
    SLIME("slime_statue", EntityType.SLIME.getLootTableId(), () -> AtbywBlockEntities.SLIME_STATUE),
    MAGMA_CUBE("magma_cube_statue", EntityType.MAGMA_CUBE.getLootTableId(), () -> AtbywBlockEntities.MAGMA_CUBE_STATUE),
    SILVERFISH("silverfish_statue", EntityType.SILVERFISH.getLootTableId(), () -> AtbywBlockEntities.SILVERFISH_STATUE, StatueVoxelShapes.SILVERFISH_OUTLINES),
    CAT("cat_statue", EntityType.CAT.getLootTableId(), () -> AtbywBlockEntities.CAT_STATUE),
    RABBIT("rabbit_statue", EntityType.RABBIT.getLootTableId(), () -> AtbywBlockEntities.RABBIT_STATUE, StatueVoxelShapes.RABBIT_OUTLINES, StatueVoxelShapes.RABBIT_COLLISIONS),
    SHULKER("shulker_statue", EntityType.SHULKER.getLootTableId(), () -> AtbywBlockEntities.SHULKER_STATUE),
    ENDERMITE("endermite_statue", EntityType.ENDERMITE.getLootTableId(), () -> AtbywBlockEntities.ENDERMITE_STATUE),
    COD("cod_statue", EntityType.COD.getLootTableId(), () -> AtbywBlockEntities.COD_STATUE),
    SALMON("salmon_statue", EntityType.SALMON.getLootTableId(), () -> AtbywBlockEntities.SALMON_STATUE),
    PUFFER_FISH("puffer_fish_statue", EntityType.PUFFERFISH.getLootTableId(), () -> AtbywBlockEntities.PUFFER_FISH_STATUE),
    FOX("fox_statue", EntityType.FOX.getLootTableId(), () -> AtbywBlockEntities.FOX_STATUE),
    BEE("bee_statue", EntityType.BEE.getLootTableId(), () -> AtbywBlockEntities.BEE_STATUE);

    //Negative Z = NORTH
    //Positive Z = SOUTH
    //Negative X = EAST
    //Positive X = WEST

    private String name;
    private Lazy<BlockEntityType<?>> blockEntityType;
    private VoxelShape[] outlineShapes;
    private VoxelShape[] collisionShapes;
    private Identifier lootTableID;

    StatueBlockMobTypes(String name, Identifier lootTableID, Supplier<BlockEntityType<?>> blockEntityType, VoxelShape[] outlineShapes, VoxelShape[] collisionShapes) {
        this.name = name;
        this.lootTableID = lootTableID;
        this.outlineShapes = outlineShapes;
        this.collisionShapes = collisionShapes;
        this.blockEntityType = new Lazy(blockEntityType);

    }

    StatueBlockMobTypes(String name, Identifier lootTableID, Supplier<BlockEntityType<?>> blockEntityType, VoxelShape[] outlineShapes) {
        this.name = name;
        this.lootTableID = lootTableID;
        this.outlineShapes = outlineShapes;
        this.blockEntityType = new Lazy(blockEntityType);

    }

    StatueBlockMobTypes(String name, Identifier lootTableID, Supplier<BlockEntityType<?>> blockEntityType) {
        this.name = name;
        this.lootTableID = lootTableID;
        this.blockEntityType = new Lazy(blockEntityType);

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BlockEntityType getBlockEntityType() {
        return this.blockEntityType.get();
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

    @Override
    public Identifier getLootTable() {
        return lootTableID;
    }
}
