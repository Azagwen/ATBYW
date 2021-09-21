package net.azagwen.atbyw.archived;

import net.azagwen.atbyw.block.registry.AtbywBlocks;
import net.azagwen.atbyw.block.TimerRepeaterBlock;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.client.render.AtbywEntityModelLayers;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;

public class TimerRepeaterBlockEntityRenderer implements BlockEntityRenderer<TimerRepeaterBlockEntity> {
    private final ModelPart[] digitLeft = new ModelPart[10];
    private final ModelPart[] digitRight = new ModelPart[10];

    public TimerRepeaterBlockEntityRenderer(Context ctx) {
        ModelPart modelPart = ctx.getLayerModelPart(AtbywEntityModelLayers.TIMER_REPEATER);
        for (int i = 0; i < 10; i++) {
            this.digitLeft[i] = modelPart.getChild("digit_left_" + i);
            this.digitRight[i] = modelPart.getChild("digit_right_" + i);
        }
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        for (int i = 0; i < 10; i++) {
            modelPartData.addChild("digit_left_" + i, makeDigitLeftPart(i), ModelTransform.NONE);
            modelPartData.addChild("digit_right_" + i, makeDigitRightPart(i), ModelTransform.NONE);
        }
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(TimerRepeaterBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        var world = entity.getWorld();
        var isWorldNotNull = world != null;
        var blockState = isWorldNotNull ? entity.getCachedState() : RedstoneBlockRegistry.TIMER_REPEATER.getDefaultState().with(TimerRepeaterBlock.FACING, Direction.NORTH);
        var isPowered = blockState.get(TimerRepeaterBlock.POWERED);
        var timerDelay = blockState.get(TimerRepeaterBlock.TIMER_DELAY);

        matrices.push();

        //Apply facing state
        var facing = (blockState.get(TimerRepeaterBlock.FACING)).asRotation();
        matrices.translate(0.5D, 0.5D, 0.5D);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-facing));
        matrices.translate(-0.5D, -0.5D, -0.5D);

        //Apply texture based on power state & Model digits
        VertexConsumer vertexConsumer;
        if (isPowered) {
//            vertexConsumer = AtbywTextureRenderLayers.DIGIT_TEXTURE_ON.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
        } else {
//            vertexConsumer = AtbywTextureRenderLayers.DIGIT_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
        }

        var digitValueLeft = getDigits(timerDelay)[0];
        var digitValueRight = getDigits(timerDelay)[1];
//        this.digitLeft[digitValueLeft].render(matrices, vertexConsumer, light, overlay);
//        this.digitRight[digitValueRight].render(matrices, vertexConsumer, light, overlay);

        matrices.pop();
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
    private static int offSetDigitU(int digitValue) {
        return (digitValue <= 4 ? (digitValue * 4): ((digitValue - 5) * 4)) - 8;
    }

    /** Offset the V channel of a digit model UV based on the digit's value (0 to 9)
     *
     * @param digitValue the digit's value.
     * @return offset integer for the V channel of the model's UVs.
     */
    private static int offSetDigitV(int digitValue) {
        return digitValue <= 4 ? 0 : 6;
    }

    private static ModelPartBuilder makeDigitPart(int value) {
        return ModelPartBuilder.create().uv(offSetDigitU(value), offSetDigitV(value));
    }

    private static ModelPartBuilder makeDigitLeftPart(int value) {
        return makeDigitPart(value).cuboid(4.5F, 3.0F, 5.5F, 3.0F, 0.0F, 5.0F);
    }

    private static ModelPartBuilder makeDigitRightPart(int value) {
        return makeDigitPart(value).cuboid(8.5F, 3.0F, 5.5F, 3.0F, 0.0F, 5.0F);
    }
}
