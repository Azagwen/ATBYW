package net.azagwen.atbyw.block;

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
    public static final EnumProperty<Direction.Axis> AXIS;

    public PillarSlab(Settings settings) {
        super(settings);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        switch(rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch(state.get(AXIS)) {
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, TYPE, WATERLOGGED);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(AXIS, ctx.getSide().getAxis());
    }

    static {
        AXIS = Properties.AXIS;
    }
}
