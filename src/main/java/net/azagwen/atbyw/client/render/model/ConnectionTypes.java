package net.azagwen.atbyw.client.render.model;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.binary.BinaryCodec;

import java.util.Arrays;

public enum ConnectionTypes {
    ALL_SIDES(0, "00000000"),
    NO_RIGHT(1, "00010000"),
    TOP_BOTTOM(2, "00010001"),
    NO_LEFT(3, "00000001"),
    LEFT_WITH_BOTTOM_DOT(4, "01110100"),
    RIGHT_WITH_BOTTOM_DOT(5, "11000101"),
    TOP_WITH_RIGHT_DOT(6, "00010111"),
    TOP_WITH_LEFT_DOT(7, "00011101"),
    NO_BOTTOM(8, "00000100"),
    TOP_LEFT_CORNER(9, "00011100"),
    TOP(10, "00011111"),
    TOP_RIGHT_CORNER(11, "00000111"),
    LEFT_WITH_TOP_DOT(12, "01011100"),
    RIGHT_WITH_TOP_DOT(13, "01000111"),
    BOTTOM_WITH_RIGHT_DOT(14, "11010001"),
    BOTTOM_WITH_LEFT_DOT(15, "01110001"),
    LEFT_RIGHT(16, "01000100"),
    LEFT(17, "01111100"),
    NO_SIDES(18, "11111111"),
    RIGHT(19, "11000111"),
    TOP_LEFT_DOTS(20, "01011101"),
    TOP_RIGHT_DOTS(21, "01010111"),
    BOTTOM_RIGHT_DOT(22, "11110111"),
    BOTTOM_LEFT_DOT(23, "11111101"),
    NO_TOP(24, "01000000"),
    BOTTOM_LEFT_CORNER(25, "01110000"),
    BOTTOM(26, "11110001"),
    BOTTOM_RIGHT_CORNER(27, "11000001"),
    BOTTOM_LEFT_DOTS(28, "01110101"),
    BOTTOM_RIGHT_DOTS(29, "11010101"),
    TOP_RIGHT_DOT(30, "11011111"),
    TOP_LEFT_DOT(31, "01111111"),
    TOP_LEFT_CORNER_WITH_DOT(32, "00010100"),
    TOP_WITH_DOTS(33, "00010101"),
    TOP_RIGHT_CORNER_WITH_DOT(34, "00000101"),
    BOTTOM_DOTS(35, "11110101"),
    LEFT_DOTS(36, "01111101"),
    TOP_LEFT_BOTTOM_RIGHT_DOTS(37, "01110111"),
    LEFT_WITH_DOTS(40, "01010100"),
    ALL_DOTS(41 , "01010101"),
    RIGHT_WITH_DOTS(42, "01000101"),
    RIGHT_DOTS(43, "11010111"),
    TOP_DOTS(44, "01011111"),
    TOP_RIGHT_BOTTOM_LEFT_DOTS(45, "11011101"),
    BOTTOM_LEFT_CORNER_WITH_DOT(48, "01010000"),
    BOTTOM_WITH_DOTS(49, "01010001"),
    BOTTOM_RIGHT_CORNER_WITH_DOT(50, "01000001");

    private final int faceIndex;
    private final byte byteIndex;
    private final String binaryIndex;
    ConnectionTypes(int faceIndex, String binaryConnections) {
        this.faceIndex = faceIndex;
//        this.byteIndex = (byte) Integer.parseInt(binaryConnections);
        this.byteIndex = (byte) Integer.parseInt(binaryConnections, 2);
        this.binaryIndex = binaryConnections;
    }

    public int getFaceIndex() {
        return this.faceIndex;
    }

    public byte getByteIndex() {
        return this.byteIndex;
    }

    @Override
    public String toString() {
        return "[ Name: "+this.name()+", FaceIndex: "+this.faceIndex+", ByteIndex: "+this.byteIndex+", Binary: "+this.binaryIndex+" ]";
    }
}
