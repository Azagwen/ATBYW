package net.azagwen.atbyw.client.render.model;

import net.azagwen.atbyw.block.TimerRepeaterBlock;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.math.*;
import net.minecraft.world.BlockRenderView;

import java.util.Random;
import java.util.function.Supplier;

public class BakedTimerRepeaterDigitModel extends ForwardingBakedModel {
    private static final SpriteIdentifier DIGITS_TEXTURE = SpriteRegistry.TIMER_REPEATER_DIGITS_TEXTURE;

    public BakedTimerRepeaterDigitModel(BakedModel baseModel) {
        this.wrapped = baseModel;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
        var emitter = context.getEmitter();
        if (state.getBlock() instanceof TimerRepeaterBlock) {
            var facing = state.get(TimerRepeaterBlock.FACING);
            context.pushTransform(ModelUtil.setRotation(facing, ModelUtil::facingAngle));
            this.emitDigitModel(emitter, state, false);
            this.emitDigitModel(emitter, state, true);
            context.popTransform();
        }
    }

    /**
     * Emits a digit model based on the blockstate
     *
     * @param emitter   Emitter that will be used to emit the quads.
     * @param state     State of the block the digit display will base itself on.
     * @param right     Determines if the digit is a u2 or u1 digit.
     */
    public void emitDigitModel(QuadEmitter emitter, BlockState state, boolean right) {
        var digits = this.getDigits(state.get(TimerRepeaterBlock.TIMER_DELAY));
        var powered = state.get(TimerRepeaterBlock.POWERED);
        var sprite = DIGITS_TEXTURE.getSprite();
        var digit = digits[(right ? 1 : 0)];

        ModelUtil.setQuad(emitter, Direction.UP, (6.5F + (right ? 2.0F : -2.0F)), 5.5F, (9.5F + (right ? 2.0F : -2.0F)), 10.5F, 13.0F);
        emitter.spriteBake(0, sprite, MutableQuadView.BAKE_ROTATE_NONE);

        var widthFactor = this.offSetDigitU(digit);
        var heightAddition = this.offSetDigitV(digit) + (powered ? 12 : 0);
        ModelUtil.setUvOnSprite(emitter, sprite, (widthFactor - 3), heightAddition, widthFactor, (5 + heightAddition));

        ModelUtil.emitTexturedData(emitter, powered, false);
    }

    /**
     * Generates a two integer array based on a value from 0 to 64 (intended use).
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

    /**
     * Offsets the U channel of a digit model UV based on the digit's value (0 to 9)
     *
     * @param digitValue the digit's value.
     * @return offset integer for the U channel of the model's UVs.
     */
    private int offSetDigitU(int digitValue) {
        var width = 3;
        var adjustedWidth = (width + 1);
        return ((digitValue <= 4 ? (adjustedWidth * digitValue) : (adjustedWidth * (digitValue - 5))) - 1) + 4;
    }

    /**
     * Offsets the V channel of a digit model UV based on the digit's value (0 to 9)
     *
     * @param digitValue the digit's value.
     * @return offset integer for the V channel of the model's UVs.
     */
    private int offSetDigitV(int digitValue) {
        return digitValue > 4 ? 6 : 0;
    }
}
