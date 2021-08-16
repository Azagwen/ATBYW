package net.azagwen.atbyw.client.render.model;

public class ByteIndex {
    private final byte index;

    public ByteIndex(boolean b0, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7) {
        this.index = this.getByteIndex(b0, b1, b2, b3, b4, b5, b6, b7);
    }

    public byte getByte() {
        return this.index;
    }

    public String getBinaryString() {
        return Integer.toBinaryString((this.index & 0xFF) + 0x100).substring(1);
    }

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

    private byte appendToByte(byte initialValue, boolean appendedValue) {
        return (byte) ((initialValue << 1) | this.booleanToByte(appendedValue));
    }

    private byte booleanToByte(boolean b) {
        return (byte) (b ? 1 : 0);
    }
}
