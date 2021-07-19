package net.azagwen.atbyw.block.state;

import com.google.common.collect.Lists;
import net.minecraft.util.math.Direction;

import java.util.List;
import java.util.Objects;

public class Connector {
    private boolean up;
    private boolean down;
    private boolean north;
    private boolean east;
    private boolean south;
    private boolean west;

    public Connector(boolean up, boolean down, boolean north, boolean east, boolean south, boolean west) {
        this.up = up;
        this.down = down;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    public Connector() {
        this(false, false, false, false, false, false);
    }

    public List<Direction> getDirections() {
        var list = Lists.<Direction>newArrayList();

        if (up) list.add(Direction.UP);
        if (down) list.add(Direction.DOWN);
        if (north) list.add(Direction.NORTH);
        if (east) list.add(Direction.EAST);
        if (south) list.add(Direction.SOUTH);
        if (west) list.add(Direction.WEST);

        return list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        var that = (Connector) obj;
        return this.up == that.up && this.down == that.down && this.north == that.north && this.east == that.east && this.south == that.south && this.west == that.west;
    }

    @Override
    public int hashCode() {
        return Objects.hash(up, down, north, east, south, west);
    }

    @Override
    public String toString() {
        return "Connector["+"up="+up+", "+"down="+down+", "+"north="+north+", "+"east="+east+", "+"south="+south+", "+"west="+west+']';
    }

    public void setFromDirection(Direction direction, boolean value) {
        switch (direction) {
            case UP -> this.up = value;
            case DOWN -> this.down = value;
            case NORTH -> this.north = value;
            case EAST -> this.east = value;
            case SOUTH -> this.south = value;
            case WEST -> this.west = value;
        }
    }

    public boolean getFromDirection(Direction direction) {
        return switch (direction) {
            case UP -> this.up;
            case DOWN -> this.down;
            case NORTH -> this.north;
            case EAST -> this.east;
            case SOUTH -> this.south;
            case WEST -> this.west;
        };
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public boolean up() {
        return up;
    }

    public boolean down() {
        return down;
    }

    public boolean north() {
        return north;
    }

    public boolean east() {
        return east;
    }

    public boolean south() {
        return south;
    }

    public boolean west() {
        return west;
    }
}
