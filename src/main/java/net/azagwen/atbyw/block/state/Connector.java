package net.azagwen.atbyw.block.state;

import com.google.common.collect.Lists;
import net.minecraft.util.math.Direction;

import java.util.List;

public record Connector(boolean up, boolean down, boolean north, boolean east, boolean south, boolean west) {

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
}
