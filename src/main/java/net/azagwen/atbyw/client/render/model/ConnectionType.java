package net.azagwen.atbyw.client.render.model;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.azagwen.atbyw.client.AtbywClient;

import java.util.Map;
import java.util.Set;

//Reference pattern
//Also documented in debug CTM textures
// 0  1  2
// 7     3
// 6  5  4

public enum ConnectionType {
    ALL_SIDES(0, new ByteIndex(), ConnectionTypeHelper.ALL),
    NO_RIGHT(1, new ByteIndex(false, false, false, true, false, false, false, false), ConnectionTypeHelper.ALL),
    TOP_BOTTOM(2, new ByteIndex(false, false, false, true, false, false, false, true), ConnectionTypeHelper.ALL),
    NO_LEFT(3, new ByteIndex(false, false, false, false, false, false, false, true), ConnectionTypeHelper.ALL),
    LEFT_WITH_BOTTOM_DOT(4, new ByteIndex(false, true, true, true, false, true, false, false), ConnectionTypeHelper.BOTTOM_RIGHT_EXCLUDED),
    RIGHT_WITH_BOTTOM_DOT(5, new ByteIndex(true, true, false, false, false, true, false, true), ConnectionTypeHelper.BOTTOM_LEFT_EXCLUDED),
    TOP_WITH_RIGHT_DOT(6, new ByteIndex(false, false, false, true, false, true, true, true), ConnectionTypeHelper.BOTTOM_RIGHT_EXCLUDED),
    TOP_WITH_LEFT_DOT(7, new ByteIndex(false, false, false, true, true, true, false, true), ConnectionTypeHelper.BOTTOM_LEFT_EXCLUDED),
    NO_BOTTOM(8, new ByteIndex(false, false, false, false, false, true, false, false), ConnectionTypeHelper.ALL),
    TOP_LEFT_CORNER(9, new ByteIndex(false, false, false, true, true, true, false, false), ConnectionTypeHelper.ALL),
    TOP(10, new ByteIndex(false, false, false, true, true, true, true, true), ConnectionTypeHelper.TOP_ONLY),
    TOP_RIGHT_CORNER(11, new ByteIndex(false, false, false, false, false, true, true, true), ConnectionTypeHelper.ALL),
    LEFT_WITH_TOP_DOT(12, new ByteIndex(false, true, false, true, true, true, false, false), ConnectionTypeHelper.TOP_RIGHT_EXCLUDED),
    RIGHT_WITH_TOP_DOT(13, new ByteIndex(false, true, false, false, false, true, true, true), ConnectionTypeHelper.TOP_LEFT_EXCLUDED),
    BOTTOM_WITH_RIGHT_DOT(14, new ByteIndex(true, true, false, true, false, false, false, true), ConnectionTypeHelper.TOP_RIGHT_EXCLUDED),
    BOTTOM_WITH_LEFT_DOT(15, new ByteIndex(false, true, true, true, false, false, false, true), ConnectionTypeHelper.TOP_LEFT_EXCLUDED),
    LEFT_RIGHT(16, new ByteIndex(false, true, false, false, false, true, false, false), ConnectionTypeHelper.ALL),
    LEFT(17, new ByteIndex(false, true, true, true, true, true, false, false), ConnectionTypeHelper.LEFT_ONLY),
    NO_SIDES(18, new ByteIndex(true, true, true, true, true, true, true, true)),
    RIGHT(19, new ByteIndex(true, true, false, false, false, true, true, true), ConnectionTypeHelper.RIGHT_ONLY),
    TOP_LEFT_DOTS(20, new ByteIndex(false, true, false, true, true, true, false, true)),
    TOP_RIGHT_DOTS(21, new ByteIndex(false, true, false, true, false, true, true, true)),
    BOTTOM_RIGHT_DOT(22, new ByteIndex(true, true, true, true, false, true, true, true)),
    BOTTOM_LEFT_DOT(23, new ByteIndex(true, true, true, true, true, true, false, true)),
    NO_TOP(24, new ByteIndex(false, true, false, false, false, false, false, false), ConnectionTypeHelper.ALL),
    BOTTOM_LEFT_CORNER(25, new ByteIndex(false, true, true, true, false, false, false, false), ConnectionTypeHelper.ALL),
    BOTTOM(26, new ByteIndex(true, true, true, true, false, false, false, true), ConnectionTypeHelper.BOTTOM_ONLY),
    BOTTOM_RIGHT_CORNER(27, new ByteIndex(true, true, false, false, false, false, false, true), ConnectionTypeHelper.ALL),
    BOTTOM_LEFT_DOTS(28, new ByteIndex(false, true, true, true, false, true, false, true)),
    BOTTOM_RIGHT_DOTS(29, new ByteIndex(true, true, false, true, false, true, false, true)),
    TOP_RIGHT_DOT(30, new ByteIndex(true, true, false, true, true, true, true, true)),
    TOP_LEFT_DOT(31, new ByteIndex(false, true, true, true, true, true, true, true)),
    TOP_LEFT_CORNER_WITH_DOT(32, new ByteIndex(false, false, false, true, false, true, false, false), ConnectionTypeHelper.BOTTOM_RIGHT_EXCLUDED),
    TOP_WITH_DOTS(33, new ByteIndex(false, false, false, true, false, true, false, true), ConnectionTypeHelper.TOP_ONLY),
    TOP_RIGHT_CORNER_WITH_DOT(34, new ByteIndex(false, false, false, false, false, true, false, true), ConnectionTypeHelper.BOTTOM_LEFT_EXCLUDED),
    BOTTOM_DOTS(35, new ByteIndex(true, true, true, true, false, true, false, true)),
    LEFT_DOTS(36, new ByteIndex(false, true, true, true, true, true, false, true)),
    TOP_LEFT_BOTTOM_RIGHT_DOTS(37, new ByteIndex(false, true, true, true, false, true, true, true)),
    LEFT_WITH_DOTS(40, new ByteIndex(false, true, false, true, false, true, false, false), ConnectionTypeHelper.LEFT_ONLY),
    ALL_DOTS(41, new ByteIndex(false, true, false, true, false, true, false, true)),
    RIGHT_WITH_DOTS(42, new ByteIndex(false, true, false, false, false, true, false, true), ConnectionTypeHelper.RIGHT_ONLY),
    RIGHT_DOTS(43, new ByteIndex(true, true, false, true, false, true, true, true)),
    TOP_DOTS(44, new ByteIndex(false, true, false, true, true, true, true, true)),
    TOP_RIGHT_BOTTOM_LEFT_DOTS(45, new ByteIndex(true, true, false, true, true, true, false, true)),
    BOTTOM_LEFT_CORNER_WITH_DOT(48, new ByteIndex(false, true, false, true, false, false, false, false), ConnectionTypeHelper.TOP_RIGHT_EXCLUDED),
    BOTTOM_WITH_DOTS(49, new ByteIndex(false, true, false, true, false, false, false, true), ConnectionTypeHelper.BOTTOM_ONLY),
    BOTTOM_RIGHT_CORNER_WITH_DOT(50, new ByteIndex(false, true, false, false, false, false, false, true), ConnectionTypeHelper.TOP_LEFT_EXCLUDED);

    public static class ConnectionTypeHelper {
        public static final Set<Byte> ALL = getOptionalsSet(0, 2, 4, 6);
        public static final Set<Byte> TOP_LEFT_EXCLUDED = getOptionalsSet(2, 4, 6);
        public static final Set<Byte> TOP_RIGHT_EXCLUDED = getOptionalsSet(0, 4, 6);
        public static final Set<Byte> BOTTOM_LEFT_EXCLUDED = getOptionalsSet(0, 2, 4);
        public static final Set<Byte> BOTTOM_RIGHT_EXCLUDED = getOptionalsSet(0, 2, 6);
        public static final Set<Byte> TOP_ONLY = getOptionalsSet(0, 2);
        public static final Set<Byte> BOTTOM_ONLY = getOptionalsSet(4, 6);
        public static final Set<Byte> LEFT_ONLY = getOptionalsSet(0, 6);
        public static final Set<Byte> RIGHT_ONLY = getOptionalsSet(2, 4);

        private static Set<Byte> getOptionalsSet(int... optionals) {
            var set = Sets.<Byte>newHashSet();
            for (var optional : optionals) {
                set.add((byte) optional);
            }
            return set;
        }

        public static Set<Byte> byteIndexPowerSet(Set<Byte> optionalConnectionsSet, byte requiredConnections) {
            var index = new ByteIndex();
            var byteIndices = Sets.<Byte>newHashSet();
            for (var optional : Sets.powerSet(optionalConnectionsSet)) {
                byteIndices.add((byte) (index.byteIndexFromSet(optional) | requiredConnections));
            }
            return byteIndices;
        }

        public static String getBinaryString(byte index) {
            return Integer.toBinaryString((index & 0xFF) + 0x100).substring(1);
        }
    }

    private final int faceIndex;
    private final byte requiredByteIndex;
    private final Set<Byte> byteIndexPowerSet;
    private final String binaryString;
    public static final Map<Byte, ConnectionType> CONNECTIONS = Maps.newHashMap();
    ConnectionType(int faceIndex, ByteIndex byteIndex, Set<Byte> optionalCorners) {
        this.faceIndex = faceIndex;
        this.requiredByteIndex = byteIndex.getByte();
        this.binaryString = byteIndex.getBinaryString();
        this.byteIndexPowerSet = ConnectionTypeHelper.byteIndexPowerSet(optionalCorners, this.requiredByteIndex);
    }

    ConnectionType(int faceIndex, ByteIndex byteIndex) {
        this(faceIndex, byteIndex, Sets.newHashSet());
    }

    public static void init() {
        for (var type : ConnectionType.values()) {
            for (var index : type.byteIndexPowerSet) {
                CONNECTIONS.put(index, type);
            }
        }

        AtbywClient.LOGGER.info("ATBYW Connected Texture Types added.");
    }

    public int getFaceIndex() {
        return this.faceIndex;
    }

    public byte getRequiredByteIndex() {
        return this.requiredByteIndex;
    }

    public Set<Byte> getByteIndexPowerSet() {
        return this.byteIndexPowerSet;
    }

    @Override
    public String toString() {
        return "[ Name: "+this.name()+", FaceIndex: "+this.faceIndex+", ByteIndex: "+this.requiredByteIndex +", Binary: "+this.binaryString+" ]";
    }

    public void printType() {
        for (var typeByteIndex : this.getByteIndexPowerSet()) {
            for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) {
                if (i == typeByteIndex) {
                    System.out.println("[ Name: " + this.name() + ", FaceIndex: " + this.faceIndex + ", ByteIndex: " + typeByteIndex + ", Binary: " + ConnectionTypeHelper.getBinaryString(typeByteIndex) + " ]");
                }
            }
        }
    }

    public void printAll() {
        for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++){
            for (var type : values()) {
                if (i == type.getRequiredByteIndex()) {
                    type.printType();
                }
            }
        }
    }
}
