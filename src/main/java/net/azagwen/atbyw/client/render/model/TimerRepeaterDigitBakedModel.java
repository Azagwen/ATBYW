package net.azagwen.atbyw.client.render.model;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.block.TimerRepeaterBlock;
import net.azagwen.atbyw.main.AtbywMain;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;

import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

public class TimerRepeaterDigitBakedModel extends BakedModelImpl{
    protected static final SpriteIdentifier DIGITS_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, AtbywMain.id("entity/timer_repeater/timer_repeater_digits"));
    protected static final SpriteIdentifier DIGITS_TEXTURE_ON = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, AtbywMain.id("entity/timer_repeater/timer_repeater_digits_on"));

    public TimerRepeaterDigitBakedModel() {
    }

    public Collection<SpriteIdentifier> getTextureDependencies() {
        return Lists.newArrayList(DIGITS_TEXTURE, DIGITS_TEXTURE_ON);
    }


    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        var emitter = context.getEmitter();
        ModelUtil.emitQuad(emitter, Direction.UP, 0, 0, 16, 16, 0);
        switch (state.get(TimerRepeaterBlock.FACING)) {
            case NORTH -> emitter.spriteBake(0, DIGITS_TEXTURE.getSprite(), MutableQuadView.BAKE_ROTATE_180);
            case SOUTH -> emitter.spriteBake(0, DIGITS_TEXTURE.getSprite(), MutableQuadView.BAKE_ROTATE_NONE);
            case EAST -> emitter.spriteBake(0, DIGITS_TEXTURE.getSprite(), MutableQuadView.BAKE_ROTATE_270);
            case WEST -> emitter.spriteBake(0, DIGITS_TEXTURE.getSprite(), MutableQuadView.BAKE_ROTATE_90);
        }
        emitter.spriteUnitSquare(0);
//        ModelUtil.setUv(emitter, 0, 0, 0, 3, 5);
        emitter.spriteColor(0, -1, -1, -1, -1);
        emitter.emit();
    }

    @Override
    public Sprite getSprite() {
        return DIGITS_TEXTURE.getSprite();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    public static class Provider implements ModelResourceProvider {
        public static final Identifier TEST_ID = AtbywMain.id("block/timer_repeater_digits");

        @Override
        public UnbakedModel loadModelResource(Identifier identifier, ModelProviderContext modelProviderContext) throws ModelProviderException {
            if(identifier.equals(TEST_ID)) {
                return new TimerRepeaterDigitUnbakedModel();
            } else {
                return null;
            }
        }
    }
}
