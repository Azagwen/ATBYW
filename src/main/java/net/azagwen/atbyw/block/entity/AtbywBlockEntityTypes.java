package net.azagwen.atbyw.block.entity;

import com.mojang.datafixers.types.Type;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.main.AtbywMain.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;

public class AtbywBlockEntityTypes<T extends BlockEntity>{

    public static <T extends BlockEntity> BlockEntityType<T> create(FabricBlockEntityTypeBuilder.Factory<T> factory, Block block) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, getBlockID(block).getPath());
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, getBlockID(block), FabricBlockEntityTypeBuilder.create(factory, block).build(type));
    }

    public static <T extends BlockEntity> BlockEntityType<T> create(FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, getBlockID(blocks[0]), FabricBlockEntityTypeBuilder.create(factory, blocks).build(null));
    }

    public static BlockEntityType<CanvasBlockEntity> CANVAS_BLOCK_ENTITY;
    public static BlockEntityType<TintingTableBlockEntity> TINTING_TABLE_BLOCK_ENTITY;

    public static void init() {
        CANVAS_BLOCK_ENTITY = create(CanvasBlockEntity::new, AtbywBlocks.CANVAS_BLOCK, AtbywBlocks.GLOWING_CANVAS_BLOCK);
        TINTING_TABLE_BLOCK_ENTITY = create(TintingTableBlockEntity::new, AtbywBlocks.TINTING_TABLE);
    }
}
