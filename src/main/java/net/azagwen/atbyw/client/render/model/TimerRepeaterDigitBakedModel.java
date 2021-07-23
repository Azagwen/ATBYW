package net.azagwen.atbyw.client.render.model;

import net.azagwen.atbyw.block.TimerRepeaterBlock;
import net.azagwen.atbyw.main.AtbywMain;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.*;
import net.minecraft.world.BlockRenderView;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TimerRepeaterDigitBakedModel extends ForwardingBakedModel {
    protected static final SpriteIdentifier DIGITS_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, AtbywMain.id("block/timer_repeater_digits"));

    public TimerRepeaterDigitBakedModel(BakedModel baseModel) {
        this.wrapped = baseModel;
    }



    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
        var emitter = context.getEmitter();
        if (state.getBlock() instanceof TimerRepeaterBlock) {
            var facing = state.get(TimerRepeaterBlock.FACING);
            context.pushTransform(ModelUtil.getFacingRotation(facing));
            this.emitDigitModel(emitter, state, false);
            this.emitDigitModel(emitter, state, true);
            context.popTransform();
        }
    }

    public void emitDigitModel(QuadEmitter emitter, BlockState state, boolean right) {
        var digits = this.getDigits(state.get(TimerRepeaterBlock.TIMER_DELAY));
        var sprite = DIGITS_TEXTURE.getSprite();
        var digit = digits[(right ? 1 : 0)];

        ModelUtil.emitQuad(emitter, Direction.UP, 6.5F, 5.5F, 9.5F, 10.5F, 0.0F);
        ModelUtil.setPos(emitter, (right ? 2.0F : -2.0F), -13.0F, 0.0F);
        emitter.spriteBake(0, sprite, MutableQuadView.BAKE_ROTATE_NONE);

        var widthFactor = this.offSetDigitU(digit);
        var lerpMinU = this.lerpU(sprite, this.findWidth(sprite, (widthFactor - 3), 2));
        var lerpMaxU = this.lerpU(sprite, this.findWidth(sprite, widthFactor, 2));

        var heightAddition = this.offSetDigitV(digit) + (state.get(TimerRepeaterBlock.POWERED) ? 12 : 0);
        var lerpMinV = this.lerpV(sprite, this.findHeight(sprite, heightAddition, 1));
        var lerpMaxV = this.lerpV(sprite, this.findHeight(sprite, (5 + heightAddition), 1));

        ModelUtil.setUv(emitter, 0, lerpMinU, lerpMinV, lerpMaxU, lerpMaxV);

        emitter.spriteColor(0, -1, -1, -1, -1);
        emitter.emit();
    }

    public float lerpU(Sprite sprite, float delta) {
        return MathHelper.lerp(delta, sprite.getMinU(), sprite.getMaxU());
    }

    public float lerpV(Sprite sprite, float delta) {
        return MathHelper.lerp(delta, sprite.getMinV(), sprite.getMaxV());
    }

    /**
     * Finds a 0-1 normalised value from a Sprite's coordinates and width
     *
     * @param sprite        Input sprite.
     * @param width         Width used to determine the "height" factor.
     * @param multiplier    Multiplier (used for simple scale corrections.
     * @return              a 0-1 factor usable in a Lerp operation.
     */

    public float findWidth(Sprite sprite, int width, int multiplier) {
        var floatWidth = (sprite.getMinU() - sprite.getMaxU());
        return Math.abs((floatWidth * width) * multiplier);
    }

    /**
     * Finds a 0-1 normalised value from a Sprite's coordinates and height
     *
     * @param sprite        Input sprite.
     * @param height        Height used to determine the "height" factor.
     * @param multiplier    Multiplier (used for simple scale corrections.
     * @return              a 0-1 factor usable in a Lerp operation.
     */
    public float findHeight(Sprite sprite, int height, int multiplier) {
        var floatHeight = (sprite.getMinV() - sprite.getMaxV());
        return Math.abs((floatHeight * height) * multiplier);
    }

    /** generates a two integer array based on a value from 0 to 64 (intended use).
     *
     * @param timerDelay the timer_delay state from the block entity's cached states.
     * @return returns an array of two integers, respectively digitLeft (0) and digitRight (1).
     */
    private int[] getDigits(int timerDelay) {
        int digitLeft = 0;
        int digitRight;

        if (timerDelay < 10) {
            digitRight = timerDelay;
        } else {
            String delayStr = Integer.toString(timerDelay);
            char[] digits = delayStr.toCharArray();

            digitLeft = Integer.parseInt(String.valueOf(digits[0]));
            digitRight = Integer.parseInt(String.valueOf(digits[1]));
        }

        return new int[] {digitLeft, digitRight};
    }

    /** Offset the U channel of a digit model UV based on the digit's value (0 to 9)
     *
     * @param digitValue the digit's value.
     * @return offset integer for the U channel of the model's UVs.
     */
    private int offSetDigitU(int digitValue) {
        var width = 3;
        return ((digitValue <= 4 ? ((width + 1) * digitValue) : ((width + 1) * (digitValue - 5))) - 1) + 4;
    }

    /** Offset the V channel of a digit model UV based on the digit's value (0 to 9)
     *
     * @param digitValue the digit's value.
     * @return offset integer for the V channel of the model's UVs.
     */
    private int offSetDigitV(int digitValue) {
        return digitValue > 4 ? 6 : 0;
    }
}
