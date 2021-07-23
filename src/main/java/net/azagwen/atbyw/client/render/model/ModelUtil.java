package net.azagwen.atbyw.client.render.model;

import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;

public class ModelUtil {

    public static void emitQuad(QuadEmitter emitter, Direction direction, float left, float bottom, float right, float top, float depth) {
        var normalizedLeft = normalizeVoxelChannel(left);
        var normalizedRight = normalizeVoxelChannel(right);
        var normalizedBottom = normalizeVoxelChannel(bottom);
        var normalizedTop = normalizeVoxelChannel(top);
        var normalizedDepth = normalizeVoxelChannel(depth);
        emitter.square(direction, normalizedLeft, normalizedBottom, normalizedRight, normalizedTop, normalizedDepth);
    }

    public static void setUv(QuadEmitter emitter, int spriteIndex, float u1, float v1, float u2, float v2) {
        emitter.sprite(0, spriteIndex, u1, v1);
        emitter.sprite(1, spriteIndex, u1, v2);
        emitter.sprite(2, spriteIndex, u2, v2);
        emitter.sprite(3, spriteIndex, u2, v1);
    }

    public static void setPos(QuadEmitter emitter, float xOffset, float yOffset, float zOffset) {
        for (var i = 0; i < 4; i++) {
            var vector = new Vec3f();
            var xOffsetNormal = normalizeVoxelChannel(xOffset);
            var yOffsetNormal = normalizeVoxelChannel(yOffset);
            var zOffsetNormal = normalizeVoxelChannel(zOffset);
            emitter.copyPos(i, vector);
            emitter.pos(i, vector.getX() + xOffsetNormal, vector.getY() + yOffsetNormal, vector.getZ() + zOffsetNormal);
        }
    }

    public static float normalizeVoxelChannel(float channel) {
        return channel / 16.0F;
    }

    public static RenderContext.QuadTransform getFacingRotation(Direction facing) {
        var rotate = (facing.getAxis().isHorizontal() ? Vec3f.POSITIVE_Y : Vec3f.POSITIVE_X).getDegreesQuaternion(angle(facing));
        return transform -> {
            Vec3f vector = new Vec3f();

            for (int i = 0; i < 4; i++) {
                // Transform the position (center of rotation is 0.5, 0.5, 0.5)
                transform.copyPos(i, vector);
                vector.add(-0.5f, -0.5f, -0.5f);
                vector.rotate(rotate);
                vector.add(0.5f, 0.5f, 0.5f);
                transform.pos(i, vector);

                // Transform the normal
                if (transform.hasNormal(i)) {
                    transform.copyNormal(i, vector);
                    vector.rotate(rotate);
                    transform.normal(i, vector);
                }
            }

            transform.nominalFace(facing);
            return true;
        };
    }

    public static float angle(Direction direction) {
        return switch (direction.getOpposite()) {
            case DOWN -> 270;
            case UP -> 90;
            case NORTH -> 0;
            case EAST -> 270;
            case SOUTH -> 180;
            case WEST -> 90;
        };
    }
}
