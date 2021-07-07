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

import java.util.Set;

import static net.azagwen.atbyw.main.AtbywMain.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;

public class AtbywBlockEntityTypes<T extends BlockEntity>{

    public static <T extends BlockEntity> BlockEntityType<T> create(FabricBlockEntityTypeBuilder.Factory<T> factory, Block block) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, getBlockID(block).getPath());
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, getBlockID(block).getPath(), FabricBlockEntityTypeBuilder.create(factory, block).build(type));
    }

    public static BlockEntityType<TimerRepeaterBlockEntity> TIMER_REPEATER_ENTITY;
    public static BlockEntityType<ColorPickerBlockEntity> COLOR_PICKER_ENTITY;

    public static void init() {
        TIMER_REPEATER_ENTITY = create(TimerRepeaterBlockEntity::new, AtbywBlocks.TIMER_REPEATER);
        COLOR_PICKER_ENTITY = create(ColorPickerBlockEntity::new, AtbywBlocks.COLOR_PICKER_BLOCK);

        LOGGER.info("ATBYW Block Entities Inintiliazed");
    }
}
