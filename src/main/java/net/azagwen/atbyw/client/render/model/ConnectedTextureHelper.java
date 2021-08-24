package net.azagwen.atbyw.client.render.model;

import net.minecraft.block.Block;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;

public class ConnectedTextureHelper {

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
        var up = blockView.getBlockState(pos.up()).isOf(expectedBlock);                             //0
        var down = blockView.getBlockState(pos.down()).isOf(expectedBlock);                         //1
        var north = blockView.getBlockState(pos.north()).isOf(expectedBlock);                       //2
        var south = blockView.getBlockState(pos.south()).isOf(expectedBlock);                       //3
        var east = blockView.getBlockState(pos.east()).isOf(expectedBlock);                         //4
        var west = blockView.getBlockState(pos.west()).isOf(expectedBlock);                         //5

        var northEast = blockView.getBlockState(pos.north().east()).isOf(expectedBlock);            //6
        var northWest = blockView.getBlockState(pos.north().west()).isOf(expectedBlock);            //7
        var southEast = blockView.getBlockState(pos.south().east()).isOf(expectedBlock);            //8
        var southWest = blockView.getBlockState(pos.south().west()).isOf(expectedBlock);            //9

        var upNorth = blockView.getBlockState(pos.up().north()).isOf(expectedBlock);                //10
        var upSouth = blockView.getBlockState(pos.up().south()).isOf(expectedBlock);                //11
        var upEast = blockView.getBlockState(pos.up().east()).isOf(expectedBlock);                  //12
        var upWest = blockView.getBlockState(pos.up().west()).isOf(expectedBlock);                  //13
        var downNorth = blockView.getBlockState(pos.down().north()).isOf(expectedBlock);            //14
        var downSouth = blockView.getBlockState(pos.down().south()).isOf(expectedBlock);            //15
        var downEast = blockView.getBlockState(pos.down().east()).isOf(expectedBlock);              //16
        var downWest = blockView.getBlockState(pos.down().west()).isOf(expectedBlock);              //17

        var upNorthEast = blockView.getBlockState(pos.north().east().up()).isOf(expectedBlock);     //18
        var upNorthWest = blockView.getBlockState(pos.north().west().up()).isOf(expectedBlock);     //19
        var upSouthEast = blockView.getBlockState(pos.south().east().up()).isOf(expectedBlock);     //20
        var upSouthWest = blockView.getBlockState(pos.south().west().up()).isOf(expectedBlock);     //21
        var downNorthEast = blockView.getBlockState(pos.north().east().down()).isOf(expectedBlock); //22
        var downNorthWest = blockView.getBlockState(pos.north().west().down()).isOf(expectedBlock); //23
        var downSouthEast = blockView.getBlockState(pos.south().east().down()).isOf(expectedBlock); //24
        var downSouthWest = blockView.getBlockState(pos.south().west().down()).isOf(expectedBlock); //25

        var upIndex = new ByteIndex((northWest && !upNorthWest), (north && !upNorth), (northEast && !upNorthEast), (east && !upEast), (southEast && !upSouthEast), (south && !upSouth), (southWest && !upSouthWest), (west && !upWest));
        var downIndex = new ByteIndex((southWest && !downSouthWest), (south && !downSouth), (southEast && !downSouthEast), (east && !downEast), (northEast && !downNorthEast), (north && !downNorth), (northWest && !downNorthWest), (west && !downWest));
        var northIndex = new ByteIndex((upEast && !upNorthEast), (up && !upNorth), (upWest && !upNorthWest), (west && !northWest), (downWest && !downNorthWest), (down && !downNorth), (downEast && !downNorthEast), (east && !northEast));
        var southIndex = new ByteIndex((upWest && !upSouthWest), (up && !upSouth), (upEast && !upSouthEast), (east && !southEast), (downEast && !downSouthEast), (down && !downSouth), (downWest && !downSouthWest), (west && !southWest));
        var eastIndex = new ByteIndex((upSouth && !upSouthEast), (up && !upEast), (upNorth && !upNorthEast), (north && !northEast), (downNorth && !downNorthEast), (down && !downEast), (downSouth && !downSouthEast), (south && !southEast));
        var westIndex = new ByteIndex((upNorth && !upNorthWest), (up && !upWest), (upSouth && !upSouthWest), (south && !southWest), (downSouth && !downSouthWest), (down && !downWest), (downNorth && !downNorthWest), (north && !northWest));

        return switch (faceDirection) {
            case UP -> upIndex;
            case DOWN -> downIndex;
            case NORTH -> northIndex;
            case SOUTH -> southIndex;
            case EAST -> eastIndex;
            case WEST -> westIndex;
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
//        var invertedOdds = ((~typeByteIndex & 0xAA) | (typeByteIndex & 0x55));  //Unused, kept as example.
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
