package net.azagwen.atbyw.client.render.model;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

public class ConnectedTextureHelper {

    /**
     * Provides a blockstate for the given "direction" integer.
     *
     * @param pos           The Position of the model's "owner" block.
     * @param direction     int value of the chosen direction (see comments in switch for correspondence).
     *
     * @return              A {@link BlockState} corresponding to the given direction.
     */
    private static BlockPos getPosInDir(BlockPos pos, int direction) {
        return switch (direction) {
            default -> (pos.up());                      //up                0 / any missing value
            case 1 -> (pos.down());                     //down              1
            case 2 -> (pos.north());                    //north             2
            case 3 -> (pos.south());                    //south             3
            case 4 -> (pos.east());                     //east              4
            case 5 -> (pos.west());                     //west              5
            case 6 -> (pos.north().east());             //north-east        6
            case 7 -> (pos.north().west());             //north-west        7
            case 8 -> (pos.south().east());             //south-east        8
            case 9 -> (pos.south().west());             //south-west        9
            case 10 -> (pos.up().north());              //up-north          10
            case 11 -> (pos.up().south());              //up-south          11
            case 12 -> (pos.up().east());               //up-east           12
            case 13 -> (pos.up().west());               //up-west           13
            case 14 -> (pos.down().north());            //down-north        14
            case 15 -> (pos.down().south());            //down-south        15
            case 16 -> (pos.down().east());             //down-east         16
            case 17 -> (pos.down().west());             //down-west         17
            case 18 -> (pos.north().east().up());       //north-east-up     18
            case 19 -> (pos.north().west().up());       //north-west-up     19
            case 20 -> (pos.south().east().up());       //south-east-up     20
            case 21 -> (pos.south().west().up());       //south-west-up     21
            case 22 -> (pos.north().east().down());     //north-east-down   22
            case 23 -> (pos.north().west().down());     //north-west-down   23
            case 24 -> (pos.south().east().down());     //south-east-down   24
            case 25 -> (pos.south().west().down());     //south-west-down   25
        };
    }

    private static BlockState getStateInDir(BlockRenderView blockView, BlockPos pos, int direction) {
        return blockView.getBlockState(getPosInDir(pos, direction));
    }

    /**
     * Provides every possible adjacent state presence in the shape of boolean array.
     *
     * @param blockView     An access to the world.
     * @param pos           The Position of the model's "owner" block.
     *
     * @return              A boolean array of size 26 (0-25) containing data on weither a certain block is present in X direction.
     */
    private static boolean[] hasStateInDirArray(BlockRenderView blockView, BlockPos pos, @Nullable Block target) {
        var result = new boolean[26];
        for (var i = 0; i < result.length; i++) {
            var state = getStateInDir(blockView, pos, i);
            if (target != null) {
                result[i] = state.isOf(target);
            } else {
                var isFullCube = state.isFullCube(blockView, getPosInDir(pos, i));
                var isOpaque = state.isOpaque();
                result[i] = isFullCube && isOpaque;
            }
        }
        return result;
    }

    /**
     * Generates a {@link ByteIndex} that contains face connection data based on the input parameters.
     *
     * @param blockView         An access to the world.
     * @param pos               The Position of the model's "owner" block.
     * @param expectedBlock     The expected {@link Block} the face connection data will be generated from (aka, the block this model will connect to).
     * @param faceDirection     The direction of the face this {@link ByteIndex} will be used for.
     *
     * @return              A {@link ByteIndex} Object containing face connection data for the given direction.
     */
    public static ByteIndex getFaceConnections(BlockRenderView blockView, BlockPos pos, Block expectedBlock, Direction faceDirection) {
        var connectingStates = hasStateInDirArray(blockView, pos, expectedBlock);
        var otherStates = hasStateInDirArray(blockView, pos, null);

        var up = new ByteIndex((connectingStates[7] && !otherStates[19]), (connectingStates[2] && !otherStates[10]), (connectingStates[6] && !otherStates[18]), (connectingStates[4] && !otherStates[12]), (connectingStates[8] && !otherStates[20]), (connectingStates[3] && !otherStates[11]), (connectingStates[9] && !otherStates[21]), (connectingStates[5] && !otherStates[13]));
        var down = new ByteIndex((connectingStates[9] && !otherStates[25]), (connectingStates[3] && !otherStates[15]), (connectingStates[8] && !otherStates[24]), (connectingStates[4] && !otherStates[16]), (connectingStates[6] && !otherStates[22]), (connectingStates[2] && !otherStates[14]), (connectingStates[7] && !otherStates[23]), (connectingStates[5] && !otherStates[17]));
        var north = new ByteIndex((connectingStates[12] && !otherStates[18]), (connectingStates[0] && !otherStates[10]), (connectingStates[13] && !otherStates[19]), (connectingStates[5] && !otherStates[7]), (connectingStates[17] && !otherStates[23]), (connectingStates[1] && !otherStates[14]), (connectingStates[16] && !otherStates[22]), (connectingStates[4] && !otherStates[6]));
        var south = new ByteIndex((connectingStates[13] && !otherStates[21]), (connectingStates[0] && !otherStates[11]), (connectingStates[12] && !otherStates[20]), (connectingStates[4] && !otherStates[8]), (connectingStates[16] && !otherStates[24]), (connectingStates[1] && !otherStates[15]), (connectingStates[17] && !otherStates[25]), (connectingStates[5] && !otherStates[9]));
        var east = new ByteIndex((connectingStates[11] && !otherStates[20]), (connectingStates[0] && !otherStates[12]), (connectingStates[10] && !otherStates[18]), (connectingStates[2] && !otherStates[6]), (connectingStates[14] && !otherStates[22]), (connectingStates[1] && !otherStates[16]), (connectingStates[15] && !otherStates[24]), (connectingStates[3] && !otherStates[8]));
        var west = new ByteIndex((connectingStates[10] && !otherStates[19]), (connectingStates[0] && !otherStates[13]), (connectingStates[11] && !otherStates[21]), (connectingStates[3] && !otherStates[9]), (connectingStates[15] && !otherStates[25]), (connectingStates[1] && !otherStates[17]), (connectingStates[14] && !otherStates[23]), (connectingStates[2] && !otherStates[7]));

        return switch (faceDirection) {
            case UP -> up;
            case DOWN -> down;
            case NORTH -> north;
            case SOUTH -> south;
            case EAST -> east;
            case WEST -> west;
        };
    }

    /**
     * Resource-intensive, must be run as sparingly as possible in each Chunk.
     *
     * @param byteIndex     The "byte" index of the current face in-world, will be compared to every available indices from {@link ConnectionType}.
     * @param sprite        The Sprite to use for the face being choosen. MUST match the Connected texture format of the mod.
     * @param cullFace      The cullFace of this Face.
     * @param isEmissive    Weither this face should have emissive shading or not.
     *
     * @return              A {@link Face} Object containing the required data to create a baked model Quad using this mod's utils.
     */
    public static Face chooseFace(ByteIndex byteIndex, Sprite sprite, Direction cullFace, boolean isEmissive) {
        var index = ConnectionType.CONNECTIONS.get((byte) 0).getFaceIndex();
        var pickedIndex = ConnectionType.CONNECTIONS.get(byteIndex.getByte());
        if (pickedIndex != null) {
            index = pickedIndex.getFaceIndex();
        } else {
            System.out.println("Null value found! Index: " + byteIndex.getByte());
        }
        return makeConnectedTextureFace(index, sprite, cullFace, isEmissive);
    }

    public static Face makeConnectedTextureFace(int faceIndex, Sprite sprite, Direction cullFace, boolean isEmissive) {
        int faceWidth = (sprite.getWidth() / 8);
        int faceHeight = (sprite.getHeight() / 8);
        int xOffset = (faceIndex % 8) * faceWidth;
        int yOffset = (faceIndex / 8) * faceHeight;

        return new Face(xOffset, yOffset, (xOffset + faceWidth), (yOffset + faceHeight), sprite, isEmissive, cullFace);
    }
}
