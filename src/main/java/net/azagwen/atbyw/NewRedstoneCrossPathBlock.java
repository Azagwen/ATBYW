package net.azagwen.atbyw;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Map;
import java.util.Set;

public class NewRedstoneCrossPathBlock extends Block {
    public static final IntProperty POWER_X; //EAST & WEST
    public static final IntProperty POWER_Z; //NORTH & SOUTH
    private static final VoxelShape SHAPE;
    private static final Map<Direction, IntProperty> DIRECTION_POWER_MAP = Maps.newHashMap();
    private boolean givesPowerOnX = true;
    private boolean givesPowerOnZ = true;

    public NewRedstoneCrossPathBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWER_X, 0).with(POWER_Z, 0));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            if (state.canPlaceAt(world, pos)) {
                for (var direction : Direction.Type.HORIZONTAL) {
                    this.update(world, pos, state, direction);
                }
            } else {
                dropStacks(state, world, pos);
                world.removeBlock(pos, false);
            }

        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        var down = pos.down();
        return world.getBlockState(down).isSideSolidFullSquare(world, down, Direction.UP);
    }

    private void update(World world, BlockPos pos, BlockState state, Direction direction) {
        int i = this.getReceivedRedstonePower(world, pos, direction);
        System.out.println(direction + ", " + i + ", " + DIRECTION_POWER_MAP.get(direction));
        if (state.get(DIRECTION_POWER_MAP.get(direction)) != i) {
            if (world.getBlockState(pos) == state) {
                world.setBlockState(pos, state.with(DIRECTION_POWER_MAP.get(direction), i), 2);
            }

            Set<BlockPos> set = Sets.newHashSet();
            set.add(pos);
            set.add(pos.offset(direction));

            for (var blockPos : set) {
                world.updateNeighborsAlways(blockPos, this);
            }
        }
    }

    private int getPowerOnDirection(World world, BlockPos pos, Direction direction) {
        var emittedPower = world.getEmittedRedstonePower(pos.offset(direction), direction);
        var emittedPowerOp = world.getEmittedRedstonePower(pos.offset(direction.getOpposite()), direction.getOpposite());
        return Math.max(emittedPower, emittedPowerOp);
    }

    private int getReceivedRedstonePower(World world, BlockPos pos, Direction direction) {
        var receivedPower = 0;
        switch (direction.getAxis()) {
            case X -> {
                this.givesPowerOnX = false;
                receivedPower = this.getPowerOnDirection(world, pos, direction);
                this.givesPowerOnX = true;
            }
            case Z -> {
                this.givesPowerOnZ = false;
                receivedPower = this.getPowerOnDirection(world, pos, direction);
                this.givesPowerOnZ = true;
            }
        }
        int j;
        if (receivedPower < 15) {
            while(true) {
                j = this.increasePower(world.getBlockState(pos.offset(direction)), direction);
                return Math.max(receivedPower, j - 1);
            }
        } else {
            return receivedPower;
        }
    }

    private int increasePower(BlockState state, Direction direction) {
        return state.isOf(this) ? state.get(DIRECTION_POWER_MAP.get(direction)) : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return this.givesPowerOnX || this.givesPowerOnZ;
    }

    private boolean givesPower(Direction direction) {
        var givesPower = false;
        switch (direction.getAxis()) {
            case X -> givesPower = this.givesPowerOnX;
            case Z -> givesPower = this.givesPowerOnZ;
        }
        return givesPower;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return !this.givesPower(direction) ? 0 : state.getWeakRedstonePower(world, pos, direction);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (this.givesPower(direction)) {
            return state.get(DIRECTION_POWER_MAP.get(direction));
        } else {
            return 0;
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER_X, POWER_Z);
    }

    static {
        POWER_X = AtbywProperties.POWER_X;
        POWER_Z = AtbywProperties.POWER_Z;

        SHAPE = VoxelShapes.union(
                Block.createCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 2.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 5.0D, 16.0D, 3.0D, 11.0D)
        );

        for (var direction : Direction.values()) {
            switch (direction) {
                case EAST, WEST -> {
                    DIRECTION_POWER_MAP.put(direction, POWER_X);
                }
                case NORTH, SOUTH -> {
                    DIRECTION_POWER_MAP.put(direction, POWER_Z);
                }
            }
        }
    }
}
