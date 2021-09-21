package net.azagwen.atbyw.client.render.model;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.BlockRenderView;

import java.util.Random;
import java.util.function.Supplier;

public class BakedConnectedTextureModel extends ForwardingBakedModel {
    private final SpriteIdentifier texture;

    public BakedConnectedTextureModel(BakedModel baseModel, SpriteIdentifier texture) {
        this.wrapped = baseModel;
        this.texture = texture;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        var emitter = context.getEmitter();
        var sprite = texture.getSprite();
        var from = new Vec3f(0.0F, 0.0F, 0.0F);
        var to = new Vec3f(16.0F, 16.0F, 16.0F);
        var faceDataMap = Maps.<Direction, Face>newHashMap();

        for (var direction : Direction.values()) {
            var offsetPos = pos.offset(direction);
            var offsetState = blockView.getBlockState(offsetPos);

            if ((!offsetState.isSideSolidFullSquare(blockView, offsetPos, direction.getOpposite()) && !offsetState.isOpaque()) || !offsetState.isOpaque()) {
                var ownerBlock = blockView.getBlockState(pos).getBlock();
                var connections = ConnectedTextureHelper.getFaceConnections(blockView, pos, ownerBlock, direction);
                faceDataMap.put(direction, ConnectedTextureHelper.chooseFace(connections, sprite, direction, false));
            }
        }
        ModelUtil.emitBox(emitter, from, to, faceDataMap, true, false, 0);
    }
}
