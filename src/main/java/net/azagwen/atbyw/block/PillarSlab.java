package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.azagwen.atbyw.block.state.PillarSlabType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class PillarSlab extends SlabBlock {
    public static final EnumProperty<PillarSlabType> TOP_TYPE;
    public static final EnumProperty<PillarSlabType> BOTTOM_TYPE;

    public PillarSlab(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState().with(BOTTOM_TYPE, PillarSlabType.Y).with(TOP_TYPE, PillarSlabType.NONE));
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        var bottomRotation = switch (state.get(BOTTOM_TYPE)) {
            case X -> PillarSlabType.Z;
            case Z -> PillarSlabType.X;
            default -> PillarSlabType.Y;
        };
        var topRotation = switch (state.get(TOP_TYPE)) {
            case X -> PillarSlabType.Z;
            case Z -> PillarSlabType.X;
            default -> PillarSlabType.Y;
        };

        return switch(rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> state.with(BOTTOM_TYPE, bottomRotation).with(TOP_TYPE, topRotation);
            default -> state;
        };
    }

    public PillarSlabType getTypeFromAxis(Direction.Axis axis) {
        return switch (axis) {
            case X -> PillarSlabType.X;
            case Y -> PillarSlabType.Y;
            case Z -> PillarSlabType.Z;
        };
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var pos = ctx.getBlockPos();
        var world = ctx.getWorld();
        var superPlacementState = super.getPlacementState(ctx);
        assert superPlacementState != null;

        var hasBottom = false;
        var hasTop = false;
        switch (superPlacementState.get(SlabBlock.TYPE)) {
            case BOTTOM -> hasBottom = true;
            case TOP -> hasTop = true;
            case DOUBLE -> {
                hasBottom = true;
                hasTop = true;
            }
        }

        var bottomType = hasBottom ? this.getTypeFromAxis(ctx.getSide().getAxis()) : PillarSlabType.NONE;
        var topType = hasTop ? this.getTypeFromAxis(ctx.getSide().getAxis()) : PillarSlabType.NONE;
        if (world.getBlockState(pos).getBlock() instanceof PillarSlab) {
            if (superPlacementState.get(PillarSlab.BOTTOM_TYPE) != PillarSlabType.NONE) {
                bottomType = world.getBlockState(pos).get(PillarSlab.BOTTOM_TYPE);
            }

            if (superPlacementState.get(PillarSlab.TOP_TYPE) != PillarSlabType.NONE) {
                topType = world.getBlockState(pos).get(PillarSlab.TOP_TYPE);
            }
        }

        return superPlacementState.with(BOTTOM_TYPE, bottomType).with(TOP_TYPE, topType);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(TOP_TYPE, BOTTOM_TYPE);
    }

    static {
        TOP_TYPE = AtbywProperties.TOP_TYPE;
        BOTTOM_TYPE = AtbywProperties.BOTTOM_TYPE;
    }
}
