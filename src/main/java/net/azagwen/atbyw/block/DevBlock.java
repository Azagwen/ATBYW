package net.azagwen.atbyw.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static net.azagwen.atbyw.block.state.AtbywProperties.*;

public class DevBlock extends FacingBlock {
    protected static final VoxelShape SHAPE_WEST_UP;
    protected static final VoxelShape SHAPE_X;
    protected static final VoxelShape SHAPE_Y;
    protected static final VoxelShape SHAPE_Z;
    protected static final VoxelShape SHAPE_X_FLAT_E;
    protected static final VoxelShape SHAPE_X_FLAT_W;
    protected static final VoxelShape SHAPE_Y_FLAT_B;
    protected static final VoxelShape SHAPE_Y_FLAT_T;
    protected static final VoxelShape SHAPE_Z_FLAT_N;
    protected static final VoxelShape SHAPE_Z_FLAT_S;
    protected static VoxelShape selectedShape;


    public DevBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH).with(VERTICAL_FACING, Direction.UP));

    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction up = Direction.UP;
        Direction down = Direction.DOWN;
        Direction north = Direction.NORTH;
        Direction south = Direction.SOUTH;
        Direction east = Direction.EAST;
        Direction west = Direction.WEST;

        //Up & Down
        if (state.get(VERTICAL_FACING).equals(up) && state.get(HORIZONTAL_FACING).equals(up))
            selectedShape = SHAPE_Y_FLAT_B;
        else if (state.get(VERTICAL_FACING).equals(down) && state.get(HORIZONTAL_FACING).equals(down))
            selectedShape = SHAPE_Y_FLAT_T;

        //East & West
        else if (state.get(VERTICAL_FACING).equals(east) && state.get(HORIZONTAL_FACING).equals(east))
            selectedShape = SHAPE_X_FLAT_E;
        else if (state.get(VERTICAL_FACING).equals(west) && state.get(HORIZONTAL_FACING).equals(west))
            selectedShape = SHAPE_X_FLAT_W;

        //North & South
        else if (state.get(VERTICAL_FACING).equals(north) && state.get(HORIZONTAL_FACING).equals(north))
            selectedShape = SHAPE_Z_FLAT_N;
        else if (state.get(VERTICAL_FACING).equals(south) && state.get(HORIZONTAL_FACING).equals(south))
            selectedShape = SHAPE_Z_FLAT_S;

        if ((state.get(VERTICAL_FACING).equals(up) || state.get(VERTICAL_FACING).equals(down)) && !((state.get(HORIZONTAL_FACING).equals(up) || state.get(HORIZONTAL_FACING).equals(down))))
            selectedShape = SHAPE_Y;
        else if ((state.get(VERTICAL_FACING).equals(north) || state.get(VERTICAL_FACING).equals(south)) && !((state.get(HORIZONTAL_FACING).equals(north) || state.get(HORIZONTAL_FACING).equals(south))))
            selectedShape = SHAPE_Z;
        else if ((state.get(VERTICAL_FACING).equals(east) || state.get(VERTICAL_FACING).equals(west)) && !((state.get(HORIZONTAL_FACING).equals(east) || state.get(HORIZONTAL_FACING).equals(west))))
            selectedShape = SHAPE_X;

        return selectedShape;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        } else {
            world.setBlockState(pos, state.cycle(APPEARANCE), 3);
            world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResult.success(world.isClient);
        }
    }

    @Environment(EnvType.CLIENT)
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        return this.getDefaultState().with(VERTICAL_FACING, direction).with(HORIZONTAL_FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VERTICAL_FACING, HORIZONTAL_FACING, APPEARANCE);
    }

    static {
        SHAPE_WEST_UP = VoxelShapes.union(
                Block.createCuboidShape(4.0D, 8.0D, 4.0D, 12.0D, 16.0D, 12.0D), //Head
                Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 2.0D, 12.0D), //Base
                Block.createCuboidShape(6.0D, 2.0D, 6.0D, 8.0D, 8.0D, 8.0D), //Neck
                Block.createCuboidShape(4.0D, 2.0D, 10.0D, 12.0D, 8.0D, 12.0D),
                Block.createCuboidShape(10.0D, 2.0D, 4.0D, 12.0D, 8.0D, 12.0D)
        );

        SHAPE_X = Block.createCuboidShape(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);
        SHAPE_Y = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        SHAPE_Z = Block.createCuboidShape(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D);

        SHAPE_X_FLAT_E = Block.createCuboidShape(0.0D, 4.0D, 0.0D, 2.0D, 12.0D, 16.0D);
        SHAPE_X_FLAT_W = Block.createCuboidShape(14.0D, 4.0D, 0.0D, 16.0D, 12.0D, 16.0D);

        SHAPE_Y_FLAT_B = Block.createCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D);
        SHAPE_Y_FLAT_T = Block.createCuboidShape(0.0D, 14.0D, 4.0D, 16.0D, 16.0D, 12.0D);

        SHAPE_Z_FLAT_S = Block.createCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 12.0D, 2.0D);
        SHAPE_Z_FLAT_N = Block.createCuboidShape(0.0D, 4.0D, 14.0D, 16.0D, 12.0D, 16.0D);
    }
}
