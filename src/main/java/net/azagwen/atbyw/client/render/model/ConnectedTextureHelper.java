package net.azagwen.atbyw.client.render.model;

import net.minecraft.block.Block;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;

public class ConnectedTextureHelper {

    public byte getByteIndex(boolean b0, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7) {
        byte index = booleanToByte(b0);
        index = this.appendToByte(index, b1);
        index = this.appendToByte(index, b2);
        index = this.appendToByte(index, b3);
        index = this.appendToByte(index, b4);
        index = this.appendToByte(index, b5);
        index = this.appendToByte(index, b6);
        index = this.appendToByte(index, b7);
        return index;
    }

    private byte booleanToByte(boolean b) {
        return (byte) (b ? 1 : 0);
    }

    private byte appendToByte(byte initialValue, boolean appendedValue) {
        return (byte) ((initialValue << 1) | this.booleanToByte(appendedValue));
    }

    public static ConnectedFace getFaceConnections(BlockRenderView blockView, BlockPos pos, Block expectedBlock, Direction faceDirection) {
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

        var upConnections = new ConnectedFace(northWest, north, northEast, east, southEast, south, southWest, west);
        var downConnections = new ConnectedFace(southWest, south, southEast, east, northEast, north, northWest, west);
        var northConnections = new ConnectedFace(upEast, up, upWest, west, downWest, down, downEast, east);
        var southConnections = new ConnectedFace(upWest, up, upEast, east, downEast, down, downWest, west);
        var eastConnections = new ConnectedFace(upSouth, up, upNorth, north, downNorth, down, downSouth, south);
        var westConnections = new ConnectedFace(upNorth, up, upSouth, south, downSouth, down, downNorth, north);

        return switch (faceDirection) {
            case UP -> upConnections;
            case DOWN -> downConnections;
            case NORTH -> northConnections;
            case SOUTH -> southConnections;
            case EAST -> eastConnections;
            case WEST -> westConnections;
        };
    }

    public static Face chooseFace(ConnectedFace connections, Sprite sprite, Direction cullFace, Direction faceDir, BlockPos pos, boolean isEmissive) {
        var index = ConnectionTypes.ALL_SIDES.getFaceIndex();

        System.out.println("Pos: "+pos.toString()+", Face: "+faceDir+", Connection Index: "+connections.getByteIndex()+", Binary Connections: "+Integer.toBinaryString((connections.getByteIndex() & 0xFF) + 0x100).substring(1));

        return makeConnectedTextureFace(index, sprite, cullFace, isEmissive);
    }

    public static Face makeConnectedTextureFace(int faceIndex, Sprite sprite, Direction cullFace, boolean isEmissive) {
        int xOffset = (faceIndex % 8) * 16;
        int yOffset = (faceIndex / 8) * 16;

//        System.out.println("[CullFace: "+cullFace+", Index: "+faceIndex+", xOffset: "+xOffset+", yOffset: "+yOffset+"]");

        return new Face(xOffset, yOffset, (xOffset + 16), (yOffset + 16), sprite, isEmissive, cullFace);
    }
}