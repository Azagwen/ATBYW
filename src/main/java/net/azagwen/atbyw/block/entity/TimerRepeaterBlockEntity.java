package net.azagwen.atbyw.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class TimerRepeaterBlockEntity extends BlockEntity {

    public TimerRepeaterBlockEntity(BlockPos pos, BlockState state) {
        super(AtbywBlockEntityType.TIMER_REPEATER_ENTITY, pos, state);
    }
}
