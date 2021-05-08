package net.azagwen.atbyw.world.structure;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.RedstoneLanternBlock;
import net.azagwen.atbyw.world.AtbywWorldGen;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.SlabType;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class DesertCryptPiece extends SimpleStructurePiece {
    private final BlockRotation rotation;
    private final Identifier template;

    public DesertCryptPiece(StructureManager structureManager, CompoundTag compoundTag) {
        super(AtbywWorldGen.DESERT_CRYPT_PIECE, compoundTag);
        this.template = new Identifier(compoundTag.getString("Template"));
        this.rotation = BlockRotation.valueOf(compoundTag.getString("Rot"));
        this.initializeStructureData(structureManager);
    }

    public DesertCryptPiece(StructureManager structureManager, BlockPos pos, Identifier template, BlockRotation rotation) {
        super(AtbywWorldGen.DESERT_CRYPT_PIECE, 0);
        this.pos = pos;
        this.rotation = rotation;
        this.template = template;

        this.initializeStructureData(structureManager);
    }

    private void initializeStructureData(StructureManager structureManager) {
        Structure structure = structureManager.getStructureOrBlank(this.template);
        StructurePlacementData placementData = (new StructurePlacementData())
                .setRotation(this.rotation)
                .setMirror(BlockMirror.NONE)
                .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
        this.setStructureData(structure, this.pos, placementData);
    }

    protected void toNbt(CompoundTag tag) {
        super.toNbt(tag);
        tag.putString("Template", this.template.toString());
        tag.putString("Rot", this.rotation.name());
    }

    @Override
    protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random, BlockBox boundingBox) {
        if ("chest".equals(metadata)) {
            serverWorldAccess.setBlockState(pos, Blocks.SMOOTH_SANDSTONE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP), 3);
            BlockEntity blockEntity = serverWorldAccess.getBlockEntity(pos.down());
            if (blockEntity instanceof ChestBlockEntity) {
                ((ChestBlockEntity)blockEntity).setLootTable(LootTables.DESERT_PYRAMID_CHEST, random.nextLong());
            }
        }
        if ("wall".equals(metadata)) {
            if (!serverWorldAccess.isAir(pos.up())) {
                serverWorldAccess.setBlockState(pos, serverWorldAccess.getBlockState(pos.up()), 3);
            } else {
                serverWorldAccess.setBlockState(pos, Blocks.SANDSTONE_WALL.getDefaultState(), 3);
            }
        }
        if ("lantern".equals(metadata)) {
            int rand = random.nextInt(8);
            serverWorldAccess.setBlockState(pos, AtbywBlocks.REDSTONE_LANTERN.getDefaultState().with(RedstoneLanternBlock.POWER_INTENSITY, rand + 7).with(RedstoneLanternBlock.HANGING, true), 3);
        }
    }

    public boolean generate(StructureWorldAccess structureWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        BlockPos storedPos = this.pos;
        this.pos = this.pos.add(0, -14, 0);
        boolean generate = super.generate(structureWorldAccess, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, blockPos);
        this.pos = storedPos;
        return generate;
    }
}