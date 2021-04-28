package net.azagwen.atbyw.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class TimerRepeaterBlockEntity extends BlockEntity {
    private int timer_delay;
    private boolean powered;

    public TimerRepeaterBlockEntity() {
        super(AtbywBlockEntityType.TIMER_REPEATER_ENTITY);
    }

    public boolean isPowered() {
        return this.powered;
    }

    public void setPowered(boolean powered) {
        this.powered = powered;
    }

    public int getTimerDelay() {
        return this.timer_delay;
    }

    public void setTimerDelay(int timer_delay) {
        this.timer_delay = timer_delay;
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putBoolean("powered", this.isPowered());
        tag.putInt("timer_delay", this.getTimerDelay());
        return tag;
    }

    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.powered = tag.getBoolean("powered");
        this.timer_delay = tag.getInt("timer_delay");
    }
}
