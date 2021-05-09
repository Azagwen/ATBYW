package net.azagwen.atbyw.mixin;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.StructureVoidBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(StructureVoidBlock.class)
public class StructureVoidBlockMixin {

    /**
     * @author Makes the structure void render a model.
     */
    @Overwrite
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
