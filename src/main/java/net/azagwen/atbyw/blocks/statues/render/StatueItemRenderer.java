package net.azagwen.atbyw.blocks.statues.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class StatueItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private final Identifier TEXTURE;
    private final EntityModel<?> MODEL;

    public StatueItemRenderer(EntityModel<?> model, Identifier texture) {
        this.MODEL = model;
        this.TEXTURE = texture;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void render(ItemStack itemStack, ModelTransformation.Mode mode, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int i1) {
        StatueRenderUtils utils = new StatueRenderUtils();

        matrixStack.push();
        matrixStack.translate(utils.getPixelOffset(8), utils.getPixelOffset(24), (utils.getPixelOffset(8)));
        matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(180));
        matrixStack.scale(1.0F, 1.0F, 1.0F);
        MODEL.render(matrixStack, vertexConsumerProvider.getBuffer(RenderLayer.getArmorCutoutNoCull(TEXTURE)), i, i1, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }
}
