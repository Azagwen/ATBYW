package net.azagwen.atbyw.world.structure;

import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.world.AtbywWorldGen;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.*;
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

public class BigIglooPiece extends SimpleStructurePiece {
    static final BlockPos DEFAULT_POSITION = new BlockPos(0, 0, 0);

    public BigIglooPiece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
        super(AtbywWorldGen.BIG_IGLOO_PIECE, 0, manager, identifier, identifier.toString(), createPlacementData(rotation, identifier), pos);
    }

    public BigIglooPiece(ServerWorld world, NbtCompound nbt) {
        super(AtbywWorldGen.BIG_IGLOO_PIECE, nbt, world, (identifier) -> {
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
            serverWorldAccess.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            BlockEntity blockEntity = serverWorldAccess.getBlockEntity(pos.down());
            if (blockEntity instanceof ChestBlockEntity) {
                ((ChestBlockEntity)blockEntity).setLootTable(AtbywMain.id("chests/big_igloo_chest"), random.nextLong());
            }
        }
    }

    public boolean generate(StructureWorldAccess structureWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        BlockPos storedPos = this.pos;
        this.pos = this.pos.add(0, -1, 0);
        boolean generate = super.generate(structureWorldAccess, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, blockPos);
        this.pos = storedPos;
        return generate;
    }
}