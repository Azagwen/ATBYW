package net.azagwen.atbyw.client.render.model;

import com.google.common.collect.Lists;
import com.mojang.datafixers.types.templates.List;
import net.azagwen.atbyw.block.TimerRepeaterBlock;
import net.azagwen.atbyw.util.Pair;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.fabricmc.loader.util.sat4j.core.Vec;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.math.*;
import net.minecraft.world.BlockRenderView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class BakedTimerRepeaterDigitModel extends ForwardingBakedModel {
    private static final SpriteIdentifier DIGITS_TEXTURE = SpriteRegistry.TIMER_REPEATER_DIGITS_TEXTURE;
    private static final SpriteIdentifier EMISSIVE_TEXTURE = SpriteRegistry.TIMER_REPEATER_EMISSIVE_TEXTURE;

    public BakedTimerRepeaterDigitModel(BakedModel baseModel) {
        this.wrapped = baseModel;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
        var emitter = context.getEmitter();
        if (state.getBlock() instanceof TimerRepeaterBlock) {
            var facing = state.get(TimerRepeaterBlock.FACING);
            context.pushTransform(ModelUtil.getFacingRotation(facing, ModelUtil::angle));
            this.emitDigitModel(emitter, state, false);
            this.emitDigitModel(emitter, state, true);
            this.emitPoweredModel(emitter, state);
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

        ModelUtil.emitQuad(emitter, Direction.UP, (6.5F + (right ? 2.0F : -2.0F)), 5.5F, (9.5F + (right ? 2.0F : -2.0F)), 10.5F, 13.0F);
        emitter.spriteBake(0, sprite, MutableQuadView.BAKE_ROTATE_NONE);

        var widthFactor = this.offSetDigitU(digit);
        var heightAddition = this.offSetDigitV(digit) + (powered ? 12 : 0);
        ModelUtil.setUvOnSprite(emitter, sprite, (widthFactor - 3), heightAddition, widthFactor, (5 + heightAddition), 2, 1);

        ModelUtil.emitTexturedData(emitter, powered);
    }

    public void emitPoweredModel(QuadEmitter emitter, BlockState state) {
        if (state.get(TimerRepeaterBlock.POWERED)){
            var sprite = EMISSIVE_TEXTURE.getSprite();

            ModelUtil.emitBox(emitter, new Vec3f(0.0F, 0.0F, -0.01F), new Vec3f(16.0F, 2.01F, 16.01F),
                    Map.ofEntries(
                            Map.entry(Direction.UP, new Face(0, 0, 16, 16, sprite, true, 8, 4)),
                            Map.entry(Direction.NORTH, new Face(16, 0, 0, 2, sprite, true, 8, 4)),
                            Map.entry(Direction.SOUTH, new Face(0, 16, 16, 14, sprite, true, 8, 4))
                    )
            );
        }
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
