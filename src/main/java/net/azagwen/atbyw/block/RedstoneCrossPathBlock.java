package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RedstoneCrossPathBlock extends Block {
    public static final BooleanProperty POWERED_X; //EAST & WEST
    public static final BooleanProperty POWERED_Z; //NORTH & SOUTH
    private static final VoxelShape SHAPE;
    private boolean isNorthInput;
    private boolean isSouthInput;
    private boolean isEastInput;
    private boolean isWestInput;
    private boolean enableDebug = false;

    public RedstoneCrossPathBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED_X, false).with(POWERED_Z, false));
    }

    public boolean isSideAnInput(World world, BlockPos pos, Direction dir) {
        BlockState stateOnDir = world.getBlockState(pos.offset(dir));
        BlockState stateOnDirOp = world.getBlockState(pos.offset(dir.getOpposite()));
        boolean isWireOnDir = stateOnDir.isOf(Blocks.REDSTONE_WIRE);
        boolean isWireOnDirOp = stateOnDirOp.isOf(Blocks.REDSTONE_WIRE);
        boolean hasPowerOnDir = world.isEmittingRedstonePower(pos.offset(dir), dir);

        if (isWireOnDir && isWireOnDirOp) {
            int getPowerFromDir = world.getBlockState(pos.offset(dir)).get(RedstoneWireBlock.POWER);
            int getPowerFromDirOp = world.getBlockState(pos.offset(dir.getOpposite())).get(RedstoneWireBlock.POWER);

            return getPowerFromDir > getPowerFromDirOp;
        } else {
            return hasPowerOnDir;
        }
    }

    public void assignInputs(BlockState state, World world, BlockPos pos) {
        this.isNorthInput = this.isSideAnInput(world, pos, Direction.NORTH);
        this.isSouthInput = this.isSideAnInput(world, pos, Direction.SOUTH);
        this.isEastInput = this.isSideAnInput(world, pos, Direction.EAST);
        this.isWestInput = this.isSideAnInput(world, pos, Direction.WEST);

        world.setBlockState(pos, state.with(POWERED_Z, (isNorthInput || isSouthInput)).with(POWERED_X, (isEastInput || isWestInput)));
        world.updateNeighborsAlways(pos, this);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        assignInputs(state, world, pos);

        if (enableDebug) {
            PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
            if (player != null) {
                player.sendMessage(new LiteralText("[ " + "§cN: " + isNorthInput + "§f, " + "§cS: " + isSouthInput + "§f, " + "§9E: " + isEastInput + "§f, " + "§9W: " + isWestInput + "§f ]"), true);
            }
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        assignInputs(state, world, pos);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        assignInputs(state, world, pos);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        boolean hasWiresOnX = world.getBlockState(pos.east()).isOf(Blocks.REDSTONE_WIRE) && world.getBlockState(pos.west()).isOf(Blocks.REDSTONE_WIRE);
        boolean hasWiresOnZ = world.getBlockState(pos.north()).isOf(Blocks.REDSTONE_WIRE) && world.getBlockState(pos.south()).isOf(Blocks.REDSTONE_WIRE);
        int returnValueX = 0;
        int returnValueZ = 0;

        switch (direction) {
            case NORTH:
                if (isNorthInput) {
                    if (hasWiresOnZ) {
                        returnValueZ = world.getBlockState(pos.north()).get(RedstoneWireBlock.POWER) - 2;
                        break;
                    } else if(world.getBlockState(pos.north()).getWeakRedstonePower(world, pos, direction) > 1) {
                        returnValueZ = world.getBlockState(pos.north()).getWeakRedstonePower(world, pos, direction) - 2;
                        break;
                    }
                }
            case SOUTH:
                if (isSouthInput) {
                    if (hasWiresOnZ) {
                        returnValueZ = world.getBlockState(pos.south()).get(RedstoneWireBlock.POWER) - 2;
                        break;
                    } else if(world.getBlockState(pos.south()).getWeakRedstonePower(world, pos, direction) > 1) {
                        returnValueZ = world.getBlockState(pos.south()).getWeakRedstonePower(world, pos, direction) - 2;
                        break;
                    }
                }
            case EAST:
                if (isEastInput) {
                    if (hasWiresOnX) {
                        returnValueX = world.getBlockState(pos.east()).get(RedstoneWireBlock.POWER) - 2;
                        break;
                    } else if(world.getBlockState(pos.east()).getWeakRedstonePower(world, pos, direction) > 1) {
                        returnValueX = world.getBlockState(pos.east()).getWeakRedstonePower(world, pos, direction) - 2;
                        break;
                    }
                }
            case WEST:
                if (isWestInput) {
                    if (hasWiresOnX) {
                        returnValueX = world.getBlockState(pos.west()).get(RedstoneWireBlock.POWER) - 2;
                        break;
                    } else if(world.getBlockState(pos.west()).getWeakRedstonePower(world, pos, direction) > 1) {
                        returnValueX = world.getBlockState(pos.west()).getWeakRedstonePower(world, pos, direction) - 2;
                        break;
                    }
                }
        }

        return (direction == Direction.NORTH || direction == Direction.SOUTH) ? returnValueZ : returnValueX;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED_X, POWERED_Z);
    }

    static {
        POWERED_X = AtbywProperties.POWERED_X;
        POWERED_Z = AtbywProperties.POWERED_Z;

        SHAPE = VoxelShapes.union(
                Block.createCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 3.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 5.0D, 16.0D, 3.0D, 11.0D)
        );
    }
}
