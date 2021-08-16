package net.azagwen.atbyw.client.render.model;

public enum ConnectionTypes {
    ALL_SIDES(0, new ByteIndex(false, false, false, false, false, false, false, false)),
    NO_RIGHT(1, new ByteIndex(false, false, false, true, false, false, false, false)),
    TOP_BOTTOM(2, new ByteIndex(false, false, false, true, false, false, false, true)),
    NO_LEFT(3, new ByteIndex(false, false, false, false, false, false, false, true)),
    LEFT_WITH_BOTTOM_DOT(4, new ByteIndex(false, true, true, true, false, true, false, false)),
    RIGHT_WITH_BOTTOM_DOT(5, new ByteIndex(true, true, false, false, false, true, false, true)),
    TOP_WITH_RIGHT_DOT(6, new ByteIndex(false, false, false, true, false, true, true, true)),
    TOP_WITH_LEFT_DOT(7, new ByteIndex(false, false, false, true, true, true, false, true)),
    NO_BOTTOM(8, new ByteIndex(false, false, false, false, false, true, false, false)),
    TOP_LEFT_CORNER(9, new ByteIndex(false, false, false, true, true, true, false, false)),
    TOP(10, new ByteIndex(false, false, false, true, true, true, true, true)),
    TOP_RIGHT_CORNER(11, new ByteIndex(false, false, false, false, false, true, true, true)),
    LEFT_WITH_TOP_DOT(12, new ByteIndex(false, true, false, true, true, true, false, false)),
    RIGHT_WITH_TOP_DOT(13, new ByteIndex(false, true, false, false, false, true, true, true)),
    BOTTOM_WITH_RIGHT_DOT(14, new ByteIndex(true, true, false, true, false, false, false, true)),
    BOTTOM_WITH_LEFT_DOT(15, new ByteIndex(false, true, true, true, false, false, false, true)),
    LEFT_RIGHT(16, new ByteIndex(false, true, false, false, false, true, false, false)),
    LEFT(17, new ByteIndex(false, true, true, true, true, true, false, false)),
    NO_SIDES(18, new ByteIndex(true, true, true, true, true, true, true, true)),
    RIGHT(19, new ByteIndex(true, true, false, false, false, true, true, true)),
    TOP_LEFT_DOTS(20, new ByteIndex(false, true, false, true, true, true, false, true)),
    TOP_RIGHT_DOTS(21, new ByteIndex(false, true, false, true, false, true, true, true)),
    BOTTOM_RIGHT_DOT(22, new ByteIndex(true, true, true, true, false, true, true, true)),
    BOTTOM_LEFT_DOT(23, new ByteIndex(true, true, true, true, true, true, false, true)),
    NO_TOP(24, new ByteIndex(false, true, false, false, false, false, false, false)),
    BOTTOM_LEFT_CORNER(25, new ByteIndex(false, true, true, true, false, false, false, false)),
    BOTTOM(26, new ByteIndex(true, true, true, true, false, false, false, true)),
    BOTTOM_RIGHT_CORNER(27, new ByteIndex(true, true, false, false, false, false, false, true)),
    BOTTOM_LEFT_DOTS(28, new ByteIndex(false, true, true, true, false, true, false, true)),
    BOTTOM_RIGHT_DOTS(29, new ByteIndex(true, true, false, true, false, true, false, true)),
    TOP_RIGHT_DOT(30, new ByteIndex(true, true, false, true, true, true, true, true)),
    TOP_LEFT_DOT(31, new ByteIndex(false, true, true, true, true, true, true, true)),
    TOP_LEFT_CORNER_WITH_DOT(32, new ByteIndex(false, false, false, true, false, true, false, false)),
    TOP_WITH_DOTS(33, new ByteIndex(false, false, false, true, false, true, false, true)),
    TOP_RIGHT_CORNER_WITH_DOT(34, new ByteIndex(false, false, false, false, false, true, false, true)),
    BOTTOM_DOTS(35, new ByteIndex(true, true, true, true, false, true, false, true)),
    LEFT_DOTS(36, new ByteIndex(false, true, true, true, true, true, false, true)),
    TOP_LEFT_BOTTOM_RIGHT_DOTS(37, new ByteIndex(false, true, true, true, false, true, true, true)),
    LEFT_WITH_DOTS(40, new ByteIndex(false, true, false, true, false, true, false, false)),
    ALL_DOTS(41, new ByteIndex(false, true, false, true, false, true, false, true)),
    RIGHT_WITH_DOTS(42, new ByteIndex(false, true, false, false, false, true, false, true)),
    RIGHT_DOTS(43, new ByteIndex(true, true, false, true, false, true, true, true)),
    TOP_DOTS(44, new ByteIndex(false, true, false, true, true, true, true, true)),
    TOP_RIGHT_BOTTOM_LEFT_DOTS(45, new ByteIndex(true, true, false, true, true, true, false, true)),
    BOTTOM_LEFT_CORNER_WITH_DOT(48, new ByteIndex(false, true, false, true, false, false, false, false)),
    BOTTOM_WITH_DOTS(49, new ByteIndex(false, true, false, true, false, false, false, true)),
    BOTTOM_RIGHT_CORNER_WITH_DOT(50, new ByteIndex(false, true, false, false, false, false, false, true));

    private final int faceIndex;
    private final byte byteIndex;
    private final String binaryString;
    ConnectionTypes(int faceIndex, ByteIndex byteIndex) {
        this.faceIndex = faceIndex;
        this.byteIndex = byteIndex.getByte();
        this.binaryString = byteIndex.getBinaryString();
    }

    public int getFaceIndex() {
        return this.faceIndex;
    }

    public byte getByteIndex() {
        return this.byteIndex;
    }

    @Override
    public String toString() {
        return "[ Name: "+this.name()+", FaceIndex: "+this.faceIndex+", ByteIndex: "+this.byteIndex+", Binary: "+this.binaryString+" ]";
    }
}
