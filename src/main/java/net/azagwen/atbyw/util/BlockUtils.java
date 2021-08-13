package net.azagwen.atbyw.util;

import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public record BlockUtils() {
    public static int NORTH = 0;
    public static int SOUTH = 1;
    public static int EAST = 2;
    public static int WEST = 3;

    public static Direction getDirectionFromTwoPos(BlockPos start, BlockPos end) {
        var startX = start.getX();
        var startY = start.getY();
        var startZ = start.getZ();
        var endX = end.getX();
        var endY = end.getY();
        var endZ = end.getZ();
        var direction = Direction.NORTH;

        if (startX > endX) {
            direction = Direction.from(Direction.Axis.X, Direction.AxisDirection.POSITIVE);
        } else if (startX < endX) {
            direction = Direction.from(Direction.Axis.X, Direction.AxisDirection.NEGATIVE);
        } else if (startY > endY) {
            direction = Direction.from(Direction.Axis.Y, Direction.AxisDirection.POSITIVE);
        } else if (startY < endY) {
            direction = Direction.from(Direction.Axis.Y, Direction.AxisDirection.NEGATIVE);
        } else if (startZ > endZ) {
            direction = Direction.from(Direction.Axis.Z, Direction.AxisDirection.POSITIVE);
        } else if (startZ < endZ) {
            direction = Direction.from(Direction.Axis.Z, Direction.AxisDirection.NEGATIVE);
        }

        return direction;
    }

    public static boolean checkEmitsPower(Direction direction, BlockView world, BlockPos pos) {
        var offset = pos.offset(direction);
        return world.getBlockState(offset).emitsRedstonePower();
    }

    public static boolean checkFullSquare(Direction direction, BlockView world, BlockPos pos) {
        var offset = pos.offset(direction);
        return world.getBlockState(offset).isSideSolidFullSquare(world, offset, direction.getOpposite());
    }

    /** Inverts the input value for use in VoxelShapes.
     *
     *  @param i Value to invert.
     *  @return  Inverted Value.
     */
    public static double invert(double i) {
        return -(i - 16);
    }

    /** Creates an array of 4 shapes, corresponding
     *  to the 4 directions statues can be in.
     *
     *  @return        Array of all 4 directions combined from the input arrays.
     */
    public static VoxelShape[] makeDirectionalShapes(double xMin, double yMin, double zMin, double xMax, double yMax, double zMax) {
        double xMax2 = invert(zMax);
        double zMin2 = invert(zMin);

        VoxelShape NORTH = Block.createCuboidShape(xMin , yMin, zMin , xMax , yMax, zMax );
        VoxelShape SOUTH = Block.createCuboidShape(xMin , yMin, xMax2, xMax , yMax, zMin2);
        VoxelShape EAST  = Block.createCuboidShape(xMax2, yMin, xMin , zMin2, yMax, xMax );
        VoxelShape WEST  = Block.createCuboidShape(zMin , yMin, xMin , zMax , yMax, xMax );

        return new VoxelShape[] {NORTH, SOUTH, EAST, WEST};
    }

    //////////////////////////////////////////////////
    //              REGISTRATION UTILS              //
    //////////////////////////////////////////////////

    /** Registers a block without a block item.
     *
     *  @param name     Name of the block (path)
     *  @param block    The Block field
     */
    public static void registerBlockOnly(String name, Block block) {
        Registry.register(Registry.BLOCK, AtbywMain.id(name), block);
    }

    /** Registers a block and its block item.
     *
     *  @param fireproof    if the Block item should resist to fire & Lava.
     *  @param group        the ItemGroup this block should be in.
     *  @param name         Name of the block (Identifier path).
     *  @param block        The declared Block that will be registered.
     */
    public static void registerBlock(boolean fireproof, @Nullable ItemGroup group, String name, Block block) {
        Item.Settings normalSettings = group != null ? new Item.Settings().group(group) : new Item.Settings();
        Item.Settings fireproofSettings = group != null ? new Item.Settings().group(group).fireproof() : new Item.Settings().fireproof();

        Registry.register(Registry.BLOCK, AtbywMain.id(name), block);
        Registry.register(Registry.ITEM, AtbywMain.id(name), new BlockItem(block, (fireproof ? fireproofSettings : normalSettings)));
    }

    public static void registerBlock(boolean fireproof, String name, Block block) {
        registerBlock(fireproof, (ItemGroup) null, name, block);
    }

    /** Registers a block and its block item.
     *
     *  @param fireproof    if the Block item should resist to fire & Lava.
     *  @param itemTab      the ItemTab list this block should be in.
     *  @param name         Name of the block (Identifier path).
     *  @param block        The declared Block that will be registered.
     */
    public static void registerBlock(boolean fireproof, ArrayList<Item> itemTab, String name, Block block) {
        Item.Settings normalSettings = new Item.Settings();
        Item.Settings fireproofSettings = new Item.Settings().fireproof();

        Registry.register(Registry.BLOCK, AtbywMain.id(name), block);
        Registry.register(Registry.ITEM, AtbywMain.id(name), new BlockItem(block, (fireproof ? fireproofSettings : normalSettings)));

        itemTab.add(block.asItem());
    }

    /** Will only register blocks, without block items associated to them.
     *  Registers a given amount of blocks determined by "block" and "variant_type"'s length,
     *  those two arrays MUST match in order to register those blocks, if the lengths mismatch
     *  the game will crash on its own and notify you of that mistake.
     *
     *  @param block_name       The name of the block.
     *  @param variant_type     An array of Strings of which every index will be put between "prefix" and "block_name".
     *  @param block            An Array of Blocks that must match the length of "variant_type".
     */
    public static void registerBlocksOnly(String block_name, String[] variant_type, Block... block) {
        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                registerBlockOnly(String.join("_", variant_type[i], block_name), block[i]);
            }
        else
            throw new IllegalArgumentException("could not register " + block_name + " : mismatched lengths !");
    }

    /** Registers a given amount of blocks determined by "block" and "variant_type"'s length,
     *  those two arrays MUST match in order to register those blocks, if the lengths mismatch
     *  the game will crash on its own and notify you of that mistake.
     *
     *  @param fireproof    if the Block item should resist to fire & Lava.
     *  @param itemTab      the ItemTab list this block should be in.
     *  @param prefix           Optional, a prefix that will be added in front of the "block_name".
     *  @param block_name       The name of the block.
     *  @param variant_type     An array of Strings of which every index will be put between "prefix" and "block_name".
     *  @param block            An Array of Blocks that must match the length of "variant_type".
     */
    public static void registerBlocks(boolean fireproof, ArrayList<Item> itemTab, @Nullable String prefix, String block_name, String[] variant_type, Block... block) {
        if (block.length == variant_type.length)
            for (int i = 0; i < block.length; i++) {
                String name;
                if (prefix == null || prefix.isEmpty()) {
                    name = String.join("_", variant_type[i], block_name);
                } else {
                    name = String.join("_", prefix, variant_type[i], block_name);
                }

                registerBlock(fireproof, itemTab, name, block[i]);
            }
        else
            throw new IllegalArgumentException(String.join("could not register " + block_name + " : mismatched lengths !"));
    }

    public static void registerBlocks(boolean fireproof, ArrayList<Item> group, String block_name, List<String> variant_type, Block... block) {
        registerBlocks(fireproof, group, null, block_name, variant_type.toArray(String[]::new), block);
    }

    public static void registerBlocks(boolean fireproof, ArrayList<Item> group, String prefix, String block_name, List<String> variant_type, Block... block) {
        registerBlocks(fireproof, group, prefix, block_name, variant_type.toArray(String[]::new), block);
    }
}
