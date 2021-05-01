package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class RedstoneCrossPathBlock extends Block {
    public static final BooleanProperty POWERED_X; //EAST & WEST
    public static final BooleanProperty POWERED_Z; //NORTH & SOUTH
    private boolean isNorthInput;
    private boolean isSouthInput;
    private boolean isEastInput;
    private boolean isWestInput;

    public RedstoneCrossPathBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED_X, false).with(POWERED_Z, false));
    }

    public boolean isSideAnInput(World world, BlockPos pos, Direction dir) {
        boolean isWireOnDir = world.getBlockState(pos.offset(dir)).isOf(Blocks.REDSTONE_WIRE);
        boolean isWireOnDirOp = world.getBlockState(pos.offset(dir.getOpposite())).isOf(Blocks.REDSTONE_WIRE);

        if (isWireOnDir && isWireOnDirOp) {
            int getPowerFromDir = world.getBlockState(pos.offset(dir)).get(RedstoneWireBlock.POWER);
            int getPowerFromDirOp = world.getBlockState(pos.offset(dir.getOpposite())).get(RedstoneWireBlock.POWER);

            return getPowerFromDir > getPowerFromDirOp;
        }

        return false;
    }

    public void assignInputs(World world, BlockPos pos) {
        this.isNorthInput = this.isSideAnInput(world, pos, Direction.NORTH);
        this.isSouthInput = this.isSideAnInput(world, pos, Direction.SOUTH);
        this.isEastInput = this.isSideAnInput(world, pos, Direction.EAST);
        this.isWestInput = this.isSideAnInput(world, pos, Direction.WEST);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {

        assignInputs(world, pos);
        world.updateNeighborsAlways(pos, this);

        PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
        if (player != null) {
            player.sendMessage(new LiteralText("[ " + "§cN: " + isNorthInput + "§f, " + "§cS: " + isSouthInput + "§f, " + "§9E: " + isEastInput + "§f, " + "§9W: " + isWestInput + "§f ]"), true);
        }
    }


    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
//        return direction == Direction.SOUTH ? 15 : 0;

        boolean hasWiresOnX = world.getBlockState(pos.east()).isOf(Blocks.REDSTONE_WIRE) && world.getBlockState(pos.west()).isOf(Blocks.REDSTONE_WIRE);
        boolean hasWiresOnZ = world.getBlockState(pos.north()).isOf(Blocks.REDSTONE_WIRE) && world.getBlockState(pos.south()).isOf(Blocks.REDSTONE_WIRE);
        int returnValue = 0;

        if (isNorthInput) {
            if (direction == Direction.NORTH) {
                if (hasWiresOnZ) {
                    returnValue = world.getBlockState(pos.north()).get(RedstoneWireBlock.POWER) - 2;
                }
            }
        } else if (isSouthInput) {
            if (direction == Direction.SOUTH) {
                if (hasWiresOnZ) {
                    returnValue = world.getBlockState(pos.south()).get(RedstoneWireBlock.POWER) - 2;
                }
            }
        }

        if (isEastInput) {
            if (direction == Direction.EAST) {
                if (hasWiresOnX) {
                    returnValue = world.getBlockState(pos.east()).get(RedstoneWireBlock.POWER) - 2;
                }
            }
        } else if (isWestInput) {
            if (direction == Direction.WEST) {
                if (hasWiresOnX) {
                    returnValue = world.getBlockState(pos.west()).get(RedstoneWireBlock.POWER) - 2;
                }
            }
        }

        return returnValue;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED_X, POWERED_Z);
    }

    static {
        POWERED_X = AtbywProperties.POWERED_X;
        POWERED_Z = AtbywProperties.POWERED_Z;
    }
}
