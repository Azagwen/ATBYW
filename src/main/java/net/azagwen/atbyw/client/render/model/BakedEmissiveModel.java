package net.azagwen.atbyw.client.render.model;

import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;

import java.util.Random;
import java.util.function.Supplier;

public class BakedEmissiveModel extends ForwardingBakedModel {
    private final BakedModel emisiveModel;

    public BakedEmissiveModel(BakedModel baseModel, BakedModel emisiveModel) {
        this.wrapped = baseModel;
        this.emisiveModel = emisiveModel;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        super.emitBlockQuads(blockView, state, pos, randomSupplier, context);

        var renderer = RendererAccess.INSTANCE.getRenderer();
        var emitter = context.getEmitter();
        var quads = Lists.<BakedQuad>newArrayList();

        for(var direction : Direction.values()) {
            quads.addAll(this.emisiveModel.getQuads(state, direction, randomSupplier.get()));
        }
        for (var quad : quads) {
            emitter.fromVanilla(quad, renderer.materialFinder().emissive(0, true).find(), quad.getFace());
            emitter.emit();
        }
    }
}
