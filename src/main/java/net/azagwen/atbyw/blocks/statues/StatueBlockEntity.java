package net.azagwen.atbyw.blocks.statues;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;

public class StatueBlockEntity extends BlockEntity implements Tickable {
    private Direction facing;
    private int moss_level;

    public StatueBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    public StatueBlockEntity(BlockEntityType<?> type, Direction facing, int moss_level) {
        this(type);
        this.facing = facing;
        this.moss_level = moss_level;
    }

    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.facing = Direction.byId(tag.getInt("facing"));
        this.moss_level = tag.getInt("moss_accumulation");
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("facing", this.facing.getId());
        tag.putInt("moss_accumulation", this.moss_level);
        return tag;
    }

    public void tick() {
        BlockState blockState = this.getCachedState();
        this.facing = blockState.get(StatueBlock.FACING);
        this.moss_level = blockState.get(StatueBlock.MOSS_LEVEL);
    }
}
