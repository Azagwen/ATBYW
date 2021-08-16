package net.azagwen.atbyw.client.render.model;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.BlockRenderView;

import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class BakedGlowingCanvasBlockModel extends ForwardingBakedModel {
    private static final SpriteIdentifier FACE_TEXTURE = SpriteRegistry.GLOWING_CANVAS_BLOCK_SURFACE;
    private static final SpriteIdentifier EDGE_TEXTURE = SpriteRegistry.GLOWING_CANVAS_BLOCK_EDGES;

    public BakedGlowingCanvasBlockModel(BakedModel baseModel) {
        this.wrapped = baseModel;
    }

    //░░░░░░░░░░
    //░░░░░░░░░░
    //░░░░░░░░░░
    //▓▓▓▓▓▓▓▓▓▓
    private Face createBottomEdgeFace(Sprite sprite, Direction cullFace) {
        return new Face(0, 0, 16, 16, sprite, true, cullFace);
    }

    //▓▓▓▓▓▓▓▓▓▓
    //░░░░░░░░░░
    //░░░░░░░░░░
    //░░░░░░░░░░
    private Face createTopEdgeFace(Sprite sprite, Direction cullFace) {
        return new Face(16, 16, 32, 32, sprite, true, cullFace);
    }

    //▓▓░░░░░░░░
    //▓▓░░░░░░░░
    //▓▓░░░░░░░░
    //▓▓░░░░░░░░
    private Face createLeftEdgeFace(Sprite sprite, Direction cullFace) {
        return new Face(16, 0, 32, 16, sprite, true, cullFace);
    }

    //░░░░░░░░▓▓
    //░░░░░░░░▓▓
    //░░░░░░░░▓▓
    //░░░░░░░░▓▓
    private Face createRightEdgeFace(Sprite sprite, Direction cullFace) {
        return new Face(0, 16, 16, 32, sprite, true, cullFace);
    }

    private void putFaceDataOnY(Map<Direction, Face> faceDataMap, Sprite edgeSprite, Direction cullFace) {
        if (cullFace.getAxis() == Direction.Axis.Y) {
            var topEdgeFace = this.createTopEdgeFace(edgeSprite, cullFace);
            var bottomEdgeFace = this.createBottomEdgeFace(edgeSprite, cullFace);
            var face = cullFace == Direction.DOWN ? bottomEdgeFace : topEdgeFace;

            for (var direction : Direction.values()) {
                if (direction != Direction.UP && direction != Direction.DOWN) {
                    faceDataMap.put(direction, face);
                }
            }
        }
    }

    private void putFaceDataOnZ(Map<Direction, Face> faceDataMap, Sprite edgeSprite, Direction cullFace) {
        if (cullFace.getAxis() == Direction.Axis.Z) {
            var downFace = cullFace == Direction.NORTH ? Direction.UP : Direction.DOWN;
            var upFace = downFace.getOpposite();
            var westFace = cullFace == Direction.NORTH ? Direction.EAST : Direction.WEST;
            var eastFace = westFace.getOpposite();

            faceDataMap.put(upFace, this.createBottomEdgeFace(edgeSprite, cullFace));
            faceDataMap.put(downFace, this.createTopEdgeFace(edgeSprite, cullFace));
            faceDataMap.put(eastFace, this.createLeftEdgeFace(edgeSprite, cullFace));
            faceDataMap.put(westFace, this.createRightEdgeFace(edgeSprite, cullFace));
        }
    }

    private void putFaceDataOnX(Map<Direction, Face> faceDataMap, Sprite edgeSprite, Direction cullFace) {
        if (cullFace.getAxis() == Direction.Axis.X) {
            var leftEdgeData = this.createLeftEdgeFace(edgeSprite, cullFace);
            var rightEdgeData = this.createRightEdgeFace(edgeSprite, cullFace);
            var allFaces = cullFace == Direction.WEST ? leftEdgeData : rightEdgeData;
            var northFace = cullFace == Direction.WEST ? rightEdgeData : leftEdgeData;

            faceDataMap.put(Direction.UP, allFaces);
            faceDataMap.put(Direction.DOWN, allFaces);
            faceDataMap.put(Direction.NORTH, northFace);
            faceDataMap.put(Direction.SOUTH, allFaces);
        }
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        var emitter = context.getEmitter();
        var faceSprite = FACE_TEXTURE.getSprite();
        var edgeSprite = EDGE_TEXTURE.getSprite();
        var from = new Vec3f(0.0F, 0.0F, 0.0F);
        var to = new Vec3f(16.0F, 16.0F, 16.0F);
        var faceDataMap = Maps.<Direction, Face>newHashMap();

        for (var direction : Direction.values()) {
            faceDataMap.put(direction, new Face(0, 0, 16, 16, faceSprite, true, direction));
        }
        ModelUtil.emitBox(emitter, from, to, faceDataMap, false, true, 0);

        for (var direction : Direction.values()) {
            switch (direction) {
                case UP, DOWN -> this.putFaceDataOnY(faceDataMap, edgeSprite, direction);
                case NORTH, SOUTH -> this.putFaceDataOnZ(faceDataMap, edgeSprite, direction);
                case EAST, WEST -> this.putFaceDataOnX(faceDataMap, edgeSprite, direction);
            }
            ModelUtil.emitBox(emitter, from, to, faceDataMap, false, true, 0);
        }
    }
}
