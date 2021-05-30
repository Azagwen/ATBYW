package net.azagwen.atbyw.mixin;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
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

    /**
     * @author Makes the block actually not a pain to work with
     */
    @Overwrite
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
//        return Block.createCuboidShape(0.1D, 0.1D, 0.1D, 15.9D, 15.9D, 15.9D);
        return VoxelShapes.fullCube();
    }
}
