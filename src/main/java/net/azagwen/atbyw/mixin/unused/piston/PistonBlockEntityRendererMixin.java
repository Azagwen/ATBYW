package net.azagwen.atbyw.mixin.unused.piston;

import net.azagwen.atbyw.block.piston.PistonWoodType;
import net.azagwen.atbyw.block.piston.PistonWoodTypes;
import net.azagwen.atbyw.block.piston.PistonDuck;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.block.enums.PistonType;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.entity.PistonBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(PistonBlockEntityRenderer.class)
public class PistonBlockEntityRendererMixin implements PistonDuck {
    private String type;

    @Inject(method = "render(Lnet/minecraft/block/entity/PistonBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at =
    @At(value = "HEAD"), cancellable = true)
    public void render(PistonBlockEntity pistonBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo cbi) {
        PistonWoodType[] values = PistonWoodTypes.values();

        for (PistonWoodType value : values) {
            Block piston_head = value.getPistonHead();
            Block sticky_piston = value.getStickyPiston();

            World world = pistonBlockEntity.getWorld();
            if (world != null) {
                BlockPos blockPos = pistonBlockEntity.getPos().offset(pistonBlockEntity.getMovementDirection().getOpposite());
                BlockState blockState = pistonBlockEntity.getPushedBlock();
                if (!blockState.isAir()) {
                    BlockModelRenderer.enableBrightnessCache();
                    matrixStack.push();
                    matrixStack.translate(pistonBlockEntity.getRenderOffsetX(f), pistonBlockEntity.getRenderOffsetY(f), pistonBlockEntity.getRenderOffsetZ(f));

                    if (blockState.isOf(piston_head) && pistonBlockEntity.getProgress(f) <= 4.0F) {
                        blockState = blockState.with(PistonHeadBlock.SHORT, pistonBlockEntity.getProgress(f) <= 0.5F);
                        this.method_3575(blockPos, blockState, matrixStack, vertexConsumerProvider, world, false, j);
                    } else if (pistonBlockEntity.isSource() && !pistonBlockEntity.isExtending()) {
                        PistonType pistonType = blockState.isOf(sticky_piston) ? PistonType.STICKY : PistonType.DEFAULT;
                        BlockState blockState2 = piston_head.getDefaultState().with(PistonHeadBlock.TYPE, pistonType).with(PistonHeadBlock.FACING, blockState.get(PistonBlock.FACING));
                        blockState2 = blockState2.with(PistonHeadBlock.SHORT, pistonBlockEntity.getProgress(f) >= 0.5F);
                        this.method_3575(blockPos, blockState2, matrixStack, vertexConsumerProvider, world, false, j);
                        BlockPos blockPos2 = blockPos.offset(pistonBlockEntity.getMovementDirection());
                        matrixStack.pop();
                        matrixStack.push();
                        blockState = blockState.with(PistonBlock.EXTENDED, true);
                        this.method_3575(blockPos2, blockState, matrixStack, vertexConsumerProvider, world, true, j);
                    } else {
                        this.method_3575(blockPos, blockState, matrixStack, vertexConsumerProvider, world, false, j);
                    }

                    matrixStack.pop();
                    BlockModelRenderer.disableBrightnessCache();
                }
            }
        }
    }

    @Shadow
    private void method_3575(BlockPos blockPos, BlockState blockState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, World world, boolean bl, int i) {}

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
