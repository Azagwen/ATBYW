package net.azagwen.atbyw.client.render.model;

import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

public class ModelUtil {

    public static void setQuad(QuadEmitter emitter, Direction direction, float left, float bottom, float right, float top, float depth, @Nullable Direction cullFace) {
        var normalizedLeft = normalizeVoxelChannel(left);
        var normalizedRight = normalizeVoxelChannel(right);
        var normalizedBottom = normalizeVoxelChannel(bottom);
        var normalizedTop = normalizeVoxelChannel(top);
        var normalizedDepth = normalizeVoxelChannel(depth);
        emitter.square(direction, normalizedLeft, normalizedBottom, normalizedRight, normalizedTop, normalizedDepth);
        if (cullFace != null) {
            emitter.cullFace(cullFace);
        }
    }

    public static void setQuad(QuadEmitter emitter, Direction direction, float left, float bottom, float right, float top, float depth) {
        setQuad(emitter, direction, left, bottom, right, top, depth, null);
    }

    /**
     * Moves the current Quad
     *
     * @param emitter   The Quad Emitter being used.
     * @param xOffset   Offset applied ot the X coordinates.
     * @param yOffset   Offset applied ot the Y coordinates.
     * @param zOffset   Offset applied ot the Z coordinates.
     */
    public static void moveQuad(QuadEmitter emitter, float xOffset, float yOffset, float zOffset) {
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

    public static RenderContext.QuadTransform setRotation(Direction facing, Function<Direction, Float> angle) {
        var rotate = (facing.getAxis().isHorizontal() ? Vec3f.POSITIVE_Y : Vec3f.POSITIVE_X).getDegreesQuaternion(angle.apply(facing));
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

    public static float facingAngle(Direction direction) {
        return switch (direction.getOpposite()) {
            case DOWN -> 270;
            case UP -> 90;
            case NORTH -> 0;
            case EAST -> 270;
            case SOUTH -> 180;
            case WEST -> 90;
        };
    }

    /**
     * Same as {@code Sprite.getFrameU()}, adapted to allow for any sprite size.
     *
     * @param sprite    The sprite used.
     * @param frame     The coordinate of the point you want to get on U.
     * @return          A coordinate usable on the Atlas.
     */
    public static float getPointOnU(Sprite sprite, double frame) {
        float widthOnAtlas = sprite.getMaxU() - sprite.getMinU();
        return sprite.getMinU() + widthOnAtlas * (float)frame / sprite.getWidth();
    }

    /**
     * Same as {@code Sprite.getFrameV()}, adapted to allow for any sprite size.
     *
     * @param sprite    The sprite used.
     * @param frame     The coordinate of the point you want to get on V.
     * @return          A coordinate usable on the Atlas.
     */
    public static float getPointOnV(Sprite sprite, double frame) {
        float heightOnAtlas = sprite.getMaxV() - sprite.getMinV();
        return sprite.getMinV() + heightOnAtlas * (float)frame / sprite.getHeight();
    }

    /**
     * Sets the UV coordinates of the current quad on the specified sprite.
     *
     * @param emitter   The Quad Emitter being used.
     * @param sprite    The Sprite to set UVs on.
     * @param left      The left coordinate of the UV frame (u1).
     * @param top       The top coordinate of the UV frame (v1).
     * @param right     The right coordinate of the UV frame (u2).
     * @param bottom    The bottom coordinate of the UV frame (v2).
     */
    public static void setUvOnSprite(QuadEmitter emitter, Sprite sprite, int left, int top, int right, int bottom) {
        ModelUtil.setUv(emitter, 0,
                getPointOnU(sprite, left),
                getPointOnV(sprite, top),
                getPointOnU(sprite, right),
                getPointOnV(sprite, bottom)
        );
    }

    /**
     * Sets the current quad's UVs to the indicated coordinates on the atlas.
     *
     * @param emitter       The Quad Emitter being used.
     * @param spriteIndex   The Sprite index (most likely 0).
     * @param u1            The first U (width) coordinate.
     * @param v1            The first V (height) coordinate.
     * @param u2            The last U (width) coordinate.
     * @param v2            The last V (height) coordinate.
     */
    public static void setUv(QuadEmitter emitter, int spriteIndex, float u1, float v1, float u2, float v2) {
        emitter.sprite(0, spriteIndex, u1, v1);
        emitter.sprite(1, spriteIndex, u1, v2);
        emitter.sprite(2, spriteIndex, u2, v2);
        emitter.sprite(3, spriteIndex, u2, v1);
    }

    public static void emitTexturedData(QuadEmitter emitter, boolean emissive, boolean shaded) {
        var renderer = RendererAccess.INSTANCE.getRenderer();

        emitter.material(renderer.materialFinder().emissive(0, emissive).disableDiffuse(0, !shaded).find());
        emitter.spriteColor(0, -1, -1, -1, -1);
        emitter.emit();
    }

    /**
     * Creates a box using the same input that a JSON model would use.
     * @see Face
     *
     * @param emitter   The Quad Emitter being used.
     * @param from      3D Vector of the origin point.
     * @param to        3D Vector of the end point.
     * @param faceData  Map of a direction and a Face instance which stores the face data.
     */
    public static void emitBox(QuadEmitter emitter, Vec3f from, Vec3f to, Map<Direction, Face> faceData, boolean shaded, boolean useTint, int tintIndex) {
        var xFrom = from.getX();
        var yFrom = from.getY();
        var zFrom = from.getZ();
        var xTo = to.getX();
        var yTo = to.getY();
        var zTo = to.getZ();

        for (var direction : Direction.values()) {
            if (faceData.containsKey(direction)) {
                var sprite = faceData.get(direction).sprite();
                var isFaceEmissive = faceData.get(direction).emissive();
                var u1 = faceData.get(direction).u1();
                var v1 = faceData.get(direction).v1();
                var u2 = faceData.get(direction).u2();
                var v2 = faceData.get(direction).v2();

                var depth = 0.0F;
                switch (direction) {
                    case UP -> depth = (16 - yTo);
                    case DOWN -> depth = yFrom;
                    case NORTH -> depth = zFrom;
                    case SOUTH -> depth = (16 - zTo);
                    case EAST -> depth = (16 - xTo);
                    case WEST -> depth = xFrom;
                }
                switch (direction) {
                    case NORTH -> setQuad(emitter, direction, Math.abs(xTo - 16), yFrom, Math.abs(xFrom - 16), yTo, depth, direction);
                    case SOUTH -> setQuad(emitter, direction, xFrom, yFrom, xTo, yTo, depth, direction);
                    case EAST -> setQuad(emitter, direction, Math.abs(zTo - 16), yFrom, Math.abs(zFrom - 16), yTo, depth, direction);
                    case WEST -> setQuad(emitter, direction, zFrom, yFrom, zTo, yTo, depth, direction);
                    case UP, DOWN -> setQuad(emitter, direction, xFrom, Math.abs(zTo - 16), xTo, Math.abs(zFrom - 16), depth, direction);
                }
                emitter.spriteBake(0, sprite, MutableQuadView.BAKE_ROTATE_NONE);
                emitter.cullFace(faceData.get(direction).cullFace());
                if (useTint) {
                    emitter.colorIndex(tintIndex);
                }
                setUvOnSprite(emitter, sprite, u1, v1, u2, v2);
                emitTexturedData(emitter, isFaceEmissive, shaded);
            }
        }
        faceData.clear();
    }
}
