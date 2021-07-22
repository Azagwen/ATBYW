package net.azagwen.atbyw.block.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachmentBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TimerRepeaterBlockEntity extends BlockEntity implements RenderAttachmentBlockEntity {

    public TimerRepeaterBlockEntity(BlockPos pos, BlockState state) {
        super(AtbywBlockEntityTypes.TIMER_REPEATER_ENTITY, pos, state);
    }

    @Override
    public @Nullable Object getRenderAttachmentData() {
        return null;
    }
}
