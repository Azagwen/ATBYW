package net.azagwen.atbyw.block.state;

import net.minecraft.state.property.*;
import net.minecraft.util.math.Direction;

public class AtbywProperties extends Properties {
    public static final BooleanProperty SHEARED;
    public static final DirectionProperty VERTICAL_FACING;
    public static final DirectionProperty HORIZONTAL_FACING;
    public static final IntProperty APPEARANCE;
    public static final IntProperty TIMER;
    public static final IntProperty POWER_INTENSITY;
    public static final IntProperty MOSS_LEVEL;
    public static final EnumProperty<ColumnEnd> END;
    public static final BooleanProperty TOP;
    public static final BooleanProperty MIDDLE;
    public static final BooleanProperty BOTTOM;
    public static final BooleanProperty ACTIVE;
    public static final BooleanProperty CAN_BREAK;
    public static final IntProperty TIMER_DELAY;
    public static final BooleanProperty POWERED_X;
    public static final BooleanProperty POWERED_Z;
    public static final EnumProperty<LargeChainEnd> CONNECT_BOTTOM;
    public static final EnumProperty<LargeChainEnd> CONNECT_TOP;
    public static final BooleanProperty POST_SLAB;
    public static final EnumProperty<PillarSlabType> BOTTOM_TYPE;
    public static final EnumProperty<PillarSlabType> TOP_TYPE;
    public static final BooleanProperty CENTER;
    public static final IntProperty ROLL;

    static {
        SHEARED = BooleanProperty.of("sheared");
        VERTICAL_FACING = DirectionProperty.of("vertical_facing", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN);
        HORIZONTAL_FACING = DirectionProperty.of("horizontal_facing", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN);
        APPEARANCE = IntProperty.of("appearance", 1, 3);
        TIMER = IntProperty.of("timer", 0, 6);
        POWER_INTENSITY = IntProperty.of("power_intensity", 1, 15);
        MOSS_LEVEL = IntProperty.of("moss_accumulation", 0, 4);
        END = EnumProperty.of("end", ColumnEnd.class);
        TOP = BooleanProperty.of("top");
        MIDDLE = BooleanProperty.of("middle");
        BOTTOM = BooleanProperty.of("bottom");
        ACTIVE = BooleanProperty.of("active");
        CAN_BREAK = BooleanProperty.of("can_break");
        TIMER_DELAY = IntProperty.of("timer_delay", 1, 64);
        POWERED_X = BooleanProperty.of("powered_x");
        POWERED_Z = BooleanProperty.of("powered_z");
        CONNECT_BOTTOM = EnumProperty.of("connect_bottom", LargeChainEnd.class);
        CONNECT_TOP = EnumProperty.of("connect_top", LargeChainEnd.class);
        POST_SLAB = BooleanProperty.of("post_slab");
        BOTTOM_TYPE = EnumProperty.of("bottom_type", PillarSlabType.class);
        TOP_TYPE = EnumProperty.of("top_type", PillarSlabType.class);
        CENTER = BooleanProperty.of("center");
        ROLL = IntProperty.of("roll", 0, 3);
    }
}
