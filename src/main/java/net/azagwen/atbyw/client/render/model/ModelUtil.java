package net.azagwen.atbyw.client.render.model;

import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.util.math.Direction;

public class ModelUtil {

    public static void emitQuad(QuadEmitter emitter, Direction direction, int left, int bottom, int right, int top, int depth) {
        var normalizedLeft = normalizeVoxelChannel(left);
        var normalizedRight = normalizeVoxelChannel(right);
        var normalizedBottom = normalizeVoxelChannel(bottom);
        var normalizedTop = normalizeVoxelChannel(top);
        var normalizedDepth = normalizeVoxelChannel(depth);
        emitter.square(direction, normalizedLeft, normalizedBottom, normalizedRight, normalizedTop, normalizedDepth);
    }

    public static void setUv(QuadEmitter emitter, int spriteIndex, int u1, int v1, int u2, int v2) {
        emitter.sprite(0, spriteIndex, u1, v1);
        emitter.sprite(1, spriteIndex, u1, v2);
        emitter.sprite(2, spriteIndex, u2, v2);
        emitter.sprite(2, spriteIndex, u2, v1);
    }

    public static float normalizeVoxelChannel(int channel) {
        return channel / 16.0F;
    }
}
