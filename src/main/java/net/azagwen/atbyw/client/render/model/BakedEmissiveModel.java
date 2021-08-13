package net.azagwen.atbyw.client.render.model;

import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;

import java.util.Random;
import java.util.function.Supplier;

public class BakedEmissiveModel extends ForwardingBakedModel {

    public BakedEmissiveModel(BakedModel baseModel) {
        this.wrapped = baseModel;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        var renderer = RendererAccess.INSTANCE.getRenderer();
        var emitter = context.getEmitter();
        var quads = Lists.<BakedQuad>newArrayList();

        for(var direction : Direction.values()) {
            quads.addAll(this.wrapped.getQuads(state, direction, randomSupplier.get()));
        }
        for (var quad : quads) {
            var sprite = quad.getSprite();
            var newSpriteId = new SpriteIdentifier(sprite.getAtlas().getId(), new Identifier(sprite.getId().getNamespace(), sprite.getId().getPath() + "_emissive"));
            var newQuad = new BakedQuad(quad.getVertexData(), quad.getColorIndex(), quad.getFace(), sprite, quad.hasShade());
            System.out.println(newSpriteId);
            emitter.fromVanilla(newQuad, renderer.materialFinder().emissive(0, true).disableDiffuse(0, true).find(), quad.getFace());
            emitter.cullFace(quad.getFace());
            emitter.emit();
        }
    }
}
