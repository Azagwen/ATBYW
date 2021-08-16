package net.azagwen.atbyw.client.render.model;

public record ConnectedFace(boolean b0, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7) {
    //b0 b1 b2
    //b6    b3
    //b6 b5 b4

    public byte getByteIndex() {
        byte index = booleanToByte(this.b0());
        index = this.appendToByte(index, this.b1());
        index = this.appendToByte(index, this.b2());
        index = this.appendToByte(index, this.b3());
        index = this.appendToByte(index, this.b4());
        index = this.appendToByte(index, this.b5());
        index = this.appendToByte(index, this.b6());
        index = this.appendToByte(index, this.b7());
        return index;
    }

    private byte booleanToByte(boolean b) {
        return (byte) (b ? 1 : 0);
    }

    private byte appendToByte(byte initialValue, boolean appendedValue) {
        return (byte) ((initialValue << 1) | this.booleanToByte(appendedValue));
    }
}