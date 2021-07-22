package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.shape.AxisShape;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Map;

public class RedstonePipeRepeaterBlock extends AbstractRedstonePipeGate implements RedstonePipeComponent {
    public static final IntProperty DELAY;
    public static final Map<Direction, VoxelShape> GATE_SHAPES;

    public RedstonePipeRepeaterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState().with(FACING, Direction.NORTH).with(DELAY, 1).with(POWERED, false));
    }

    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return state.get(DELAY) * 2;
    }

    @Override
    public ComponentType getType() {
        return ComponentType.GATE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        } else {
            world.setBlockState(pos, state.cycle(DELAY), 3);
            return ActionResult.success(world.isClient);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(GATE_SHAPES.get(state.get(FACING).getOpposite()), RedstonePipeBlock.SHAPES.get(state.get(FACING))) ;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(DELAY);
    }

    static {
        DELAY = Properties.DELAY;

        var shapes = new AxisShape(4.0D, 0.0D, 12.0D, 12.0D);
        GATE_SHAPES = shapes.getAsDirectionShapeMap();
    }
}
