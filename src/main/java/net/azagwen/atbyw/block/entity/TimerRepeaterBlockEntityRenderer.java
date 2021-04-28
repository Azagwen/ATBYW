package net.azagwen.atbyw.block.entity;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.TimerRepeaterBlock;
import net.minecraft.block.*;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class TimerRepeaterBlockEntityRenderer extends BlockEntityRenderer<TimerRepeaterBlockEntity> {
    private final ModelPart digitLeft;
    private final ModelPart digitRight;


    public TimerRepeaterBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);

        this.digitLeft = new ModelPart(32, 32, 0, 0);
        this.digitLeft.addCuboid(8.5F, 3.0F, 5.5F, 3.F, 0.0F, 5.0F, 0.0F);
        this.digitRight = new ModelPart(32, 32, 0, 0);
        this.digitRight.addCuboid(4.5F, 3.0F, 5.5F, 3.F, 0.0F, 5.0F, 0.0F);
    }

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

    private int[] offSetDigit(int digitValue) {
        int U;
        int V;

        if (digitValue < 4) {
            U = digitValue * 4;
            V = 0;
        } else {
            U = (digitValue - 4) * 4;
            V = 6;
        }

        return new int[] {U, V};
    }

    @Override
    public void render(TimerRepeaterBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        boolean isWorldNotNull = world != null;
        BlockState blockState = isWorldNotNull ? entity.getCachedState() : AtbywBlocks.TIMER_REPEATER.getDefaultState().with(TimerRepeaterBlock.FACING, Direction.NORTH);

        boolean isPowered = blockState.get(TimerRepeaterBlock.POWERED);
        int timerDelay = blockState.get(TimerRepeaterBlock.TIMER_DELAY);
        int digitValueLeft = getDigits(timerDelay)[0];
        int digitValueRight = getDigits(timerDelay)[1];

        matrices.push();

        float facing = (blockState.get(TimerRepeaterBlock.FACING)).asRotation();
        matrices.translate(0.5D, 0.5D, 0.5D);
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-facing));
        matrices.translate(-0.5D, -0.5D, -0.5D);

        this.digitLeft.setTextureOffset(offSetDigit(digitValueLeft)[0], offSetDigit(digitValueLeft)[1]);
        this.digitRight.setTextureOffset(offSetDigit(digitValueRight)[0], offSetDigit(digitValueRight)[1]);

        VertexConsumer vertexConsumer = AtbywTextureRenderLayers.DIGIT_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
        VertexConsumer vertexConsumerOn = AtbywTextureRenderLayers.DIGIT_TEXTURE_ON.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);

        if (isPowered) {
            digitLeft.render(matrices, vertexConsumerOn, light, overlay);
            digitRight.render(matrices, vertexConsumerOn, light, overlay);
        } else {
            digitLeft.render(matrices, vertexConsumer, light, overlay);
            digitRight.render(matrices, vertexConsumer, light, overlay);
        }
        matrices.pop();
    }
}
