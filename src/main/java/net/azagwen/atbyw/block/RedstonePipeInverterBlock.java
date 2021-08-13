package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.shape.AxisShape;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.Map;

public class RedstonePipeInverterBlock extends AbstractRedstonePipeGate {
    public static final Map<Direction, VoxelShape> GATE_SHAPES;

    public RedstonePipeInverterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState().with(FACING, Direction.NORTH).with(POWERED, false));
    }

    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return 2;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(POWERED)) {
            return 0;
        } else {
            return state.get(FACING) == direction.getOpposite() ? this.getOutputLevel(world, pos, state) : 0;
        }
    }

    @Override
    public ComponentType getType() {
        return ComponentType.GATE;
    }

    @Override
    public boolean isInverted() {
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return GATE_SHAPES.get(state.get(FACING).getOpposite());
    }

    static {
        var shapes = new AxisShape(5.0D, 0.0D, 11.0D, 16.0D);
        GATE_SHAPES = shapes.getAsDirectionShapeMap();
    }
}
