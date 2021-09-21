package net.azagwen.atbyw.world.structure;

import net.azagwen.atbyw.block.registry.AtbywBlocks;
import net.azagwen.atbyw.block.RedstoneLanternBlock;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.world.AtbywWorldGen;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.SlabType;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class DesertCryptPiece extends SimpleStructurePiece {
    static final BlockPos DEFAULT_POSITION = new BlockPos(0, 0, 0);

    public DesertCryptPiece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
        super(AtbywWorldGen.DESERT_CRYPT_PIECE, 0, manager, identifier, identifier.toString(), createPlacementData(rotation, identifier), pos);
    }

    public DesertCryptPiece(ServerWorld world, NbtCompound nbt) {
        super(AtbywWorldGen.DESERT_CRYPT_PIECE, nbt, world, (identifier) -> {
            return createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")), identifier);
        });
    }

    private static StructurePlacementData createPlacementData(BlockRotation rotation, Identifier identifier) {
        return (new StructurePlacementData()).setRotation(rotation).setMirror(BlockMirror.NONE).setPosition(DEFAULT_POSITION).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
    }

    protected void writeNbt(ServerWorld world, NbtCompound nbt) {
        super.writeNbt(world, nbt);
        nbt.putString("Rot", this.placementData.getRotation().name());
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
            serverWorldAccess.setBlockState(pos, RedstoneBlockRegistry.REDSTONE_LANTERN.getDefaultState().with(RedstoneLanternBlock.POWER_INTENSITY, rand + 7).with(RedstoneLanternBlock.HANGING, true), 3);
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