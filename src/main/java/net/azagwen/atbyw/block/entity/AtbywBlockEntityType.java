package net.azagwen.atbyw.block.entity;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.main.AtbywMain.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;

@SuppressWarnings("unchecked")
public class AtbywBlockEntityType {

    @SuppressWarnings("rawtypes")
    private static BlockEntityType createBlockEntityType(Block block) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, getBlockID(block), BlockEntityType.Builder.create(TimerRepeaterBlockEntity::new, block).build(null));
    }

    public static final BlockEntityType<TimerRepeaterBlockEntity> TIMER_REPEATER_ENTITY;

    static {
        TIMER_REPEATER_ENTITY = createBlockEntityType(AtbywBlocks.TIMER_REPEATER);
    }

    public static void init() {

        LOGGER.info("ATBYW Block Entities Inintiliazed");
    }
}
