package net.azagwen.atbyw.block;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.BitSetVoxelSet;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OutlineShape extends VoxelShape {
    private final VoxelShape fallback;
    private final List<Vec3d> edges;

    /**
     * @param fallback The {@link VoxelShape} to fall back to for voxel calculations
     * @param edges    The edges to use for {@link VoxelShape#forEachEdge(VoxelShapes.BoxConsumer)}
     */
    public OutlineShape(VoxelShape fallback, List<Vec3d> edges) {
        super(new BitSetVoxelSet(fallback.voxels));

        this.fallback = fallback;
        this.edges = edges;

        if (edges.size() % 2 != 0)
            throw new IllegalArgumentException("Edges must be in groups of2 points");
    }

    @Override
    public DoubleList getPointPositions(Direction.Axis axis) {
        return this.fallback.getPointPositions(axis);
    }

    /*
    *  Use the supplied edges instead of the voxels. This allows diagonal lines, etc.
    */
    @Override
    public void forEachEdge(VoxelShapes.BoxConsumer boxConsumer) {
        Iterator<Vec3d> iter = edges.iterator();
        while (iter.hasNext()) {
            Vec3d p1 = iter.next();
            Vec3d p2 = iter.next();
            boxConsumer.consume(
                    p1.x, p1.y, p1.z,
                    p2.x, p2.y, p2.z
            );
        }
    }

    public Vec3d voxelizeVec3d(Vec3d vec) {
        return new Vec3d(vec.x / 16, vec.y / 16, vec.z / 16);
    }

    public static OutlineShape createFourSidedShape(VoxelShape collisionBox, Vec3d pointBFL, Vec3d pointBFR, Vec3d pointBBR, Vec3d pointBBL, Vec3d pointTFL, Vec3d pointTFR, Vec3d pointTBR, Vec3d pointTBL) {
        return new OutlineShape(collisionBox, Arrays.asList(
                pointBFL, pointTFL, //Edge 1
                pointBFR, pointTFR, //Edge 2
                pointBBR, pointTBR, //Edge 3
                pointBBL, pointTBL, //Edge 4
                pointBFL, pointBFR, //Edge 5
                pointTFL, pointTFR, //Edge 6
                pointTBR, pointTBL, //Edge 7
                pointBBR, pointBBL, //Edge 8
                pointBFR, pointBBR, //Edge 9
                pointTFR, pointTBR, //Edge 10
                pointTFL, pointTBL, //Edge 11
                pointBFL, pointBBL  //Edge 12
        ));
    }
}
