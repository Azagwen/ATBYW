package net.azagwen.atbyw.client.render.model;

import net.minecraft.block.Block;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;

public class ConnectedTextureHelper {

    public static ByteIndex getFaceConnections(BlockRenderView blockView, BlockPos pos, Block expectedBlock, Direction faceDirection) {
        var up = blockView.getBlockState(pos.up()).isOf(expectedBlock);                     //1
        var down = blockView.getBlockState(pos.down()).isOf(expectedBlock);                 //2
        var north = blockView.getBlockState(pos.north()).isOf(expectedBlock);               //3
        var south = blockView.getBlockState(pos.south()).isOf(expectedBlock);               //4
        var east = blockView.getBlockState(pos.east()).isOf(expectedBlock);                 //5
        var west = blockView.getBlockState(pos.west()).isOf(expectedBlock);                 //6

        var upNorth = blockView.getBlockState(pos.up().north()).isOf(expectedBlock);        //7
        var upSouth = blockView.getBlockState(pos.up().south()).isOf(expectedBlock);        //8
        var upEast = blockView.getBlockState(pos.up().east()).isOf(expectedBlock);          //9
        var upWest = blockView.getBlockState(pos.up().west()).isOf(expectedBlock);          //10
        var downNorth = blockView.getBlockState(pos.down().north()).isOf(expectedBlock);    //11
        var downSouth = blockView.getBlockState(pos.down().south()).isOf(expectedBlock);    //12
        var downEast = blockView.getBlockState(pos.down().east()).isOf(expectedBlock);      //13
        var downWest = blockView.getBlockState(pos.down().west()).isOf(expectedBlock);      //14

        var northEast = blockView.getBlockState(pos.north().east()).isOf(expectedBlock);    //15
        var northWest = blockView.getBlockState(pos.north().west()).isOf(expectedBlock);    //16
        var southEast = blockView.getBlockState(pos.south().east()).isOf(expectedBlock);    //17
        var southWest = blockView.getBlockState(pos.south().west()).isOf(expectedBlock);    //18

        var upIndex = new ByteIndex(northWest, north, northEast, east, southEast, south, southWest, west);
        var downIndex = new ByteIndex(southWest, south, southEast, east, northEast, north, northWest, west);
        var northIndex = new ByteIndex(upEast, up, upWest, west, downWest, down, downEast, east);
        var southIndex = new ByteIndex(upWest, up, upEast, east, downEast, down, downWest, west);
        var eastIndex = new ByteIndex(upSouth, up, upNorth, north, downNorth, down, downSouth, south);
        var westIndex = new ByteIndex(upNorth, up, upSouth, south, downSouth, down, downNorth, north);

        return switch (faceDirection) {
            case UP -> upIndex;
            case DOWN -> downIndex;
            case NORTH -> northIndex;
            case SOUTH -> southIndex;
            case EAST -> eastIndex;
            case WEST -> westIndex;
        };
    }

    public static Face chooseFace(ByteIndex byteIndex, Sprite sprite, Direction cullFace, Direction faceDir, BlockPos pos, boolean isEmissive) {
        var index = ConnectionTypes.ALL_SIDES.getFaceIndex();

        for (var type : ConnectionTypes.values()) {
            for (var typeByteIndex : type.getByteIndexPowerSet()) {
                var invertedOdds = ((~typeByteIndex & 0xAA) | (typeByteIndex & 0x55));  //Unused, kept as example.
                if (byteIndex.getByte() == typeByteIndex) {
                    index = type.getFaceIndex();
                    break;
                }
            }
        }

//        System.out.println("Pos: "+pos.toString()+", Face: "+faceDir+", Connection Index: "+byteIndex.getByte()+", Binary Connections: "+byteIndex.getBinaryString());

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
