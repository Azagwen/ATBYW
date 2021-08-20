package net.azagwen.atbyw.client.render.model;

import com.google.common.collect.Sets;

import java.util.Set;

//Reference pattern
//Also documented in debug CTM textures
// 0  1  2
// 7     3
// 6  5  4

public class ByteIndex {
    private final byte index;

    public ByteIndex(boolean b0, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7) {
        this.index = this.getByteIndex(b0, b1, b2, b3, b4, b5, b6, b7);
    }

    public ByteIndex(int... connections) {
        var set = Sets.<Byte>newHashSet();
        for (var connection :connections) {
            set.add((byte) connection);
        }
        this.index = this.byteIndexFromSet(set);
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

    public byte byteIndexFromSet(Set<Byte> connections) {
        var b0 = connections.contains((byte) 0);
        var b1 = connections.contains((byte) 1);
        var b2 = connections.contains((byte) 2);
        var b3 = connections.contains((byte) 3);
        var b4 = connections.contains((byte) 4);
        var b5 = connections.contains((byte) 5);
        var b6 = connections.contains((byte) 6);
        var b7 = connections.contains((byte) 7);
        return this.getByteIndex(b0, b1, b2, b3, b4, b5, b6, b7);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof ByteIndex that) {
                return this.index == that.index;
            }
        }
        return false;
    }

    private byte appendToByte(byte initialValue, boolean appendedValue) {
        return (byte) ((initialValue << 1) | this.booleanToByte(appendedValue));
    }

    private byte booleanToByte(boolean b) {
        return (byte) (b ? 1 : 0);
    }

    public void printByte() {
        System.out.println(this.index);
    }
}
