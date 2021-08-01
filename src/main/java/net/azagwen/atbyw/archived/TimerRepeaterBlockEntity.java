package net.azagwen.atbyw.archived;

import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachmentBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TimerRepeaterBlockEntity extends BlockEntity implements RenderAttachmentBlockEntity {

    public TimerRepeaterBlockEntity(BlockPos pos, BlockState state) {
        //Incorrect be-type for archive
        super(BlockEntityType.BED, pos, state);
    }

    @Override
    public @Nullable Object getRenderAttachmentData() {
        return null;
    }
}
