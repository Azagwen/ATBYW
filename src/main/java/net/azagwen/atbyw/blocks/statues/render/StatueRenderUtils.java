package net.azagwen.atbyw.blocks.statues.render;

import net.azagwen.atbyw.blocks.statues.StatueBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

@Environment(EnvType.CLIENT)
public class StatueRenderUtils {

    public double getPixelOffset(double pixelAmount) {
        return pixelAmount / 16.0D;
    }

    public void setAngle(ModelPart modelPart, float pitch, float yaw, float roll) {
        modelPart.pitch = (float) Math.toRadians(pitch);
        modelPart.yaw = (float) Math.toRadians(yaw);
        modelPart.roll = (float) Math.toRadians(roll);
    }

    public RenderLayer getTextureFromMossLevel(BlockState blockState, Identifier[] textures) {
        Identifier TEXTURE_CLEAN = textures[0];
        Identifier TEXTURE_SLIGHTLY_MOSSY = textures[1];
        Identifier TEXTURE_MEDIUMLY_MOSSY = textures[2];
        Identifier TEXTURE_MOSTLY_MOSSY = textures[3];
        Identifier TEXTURE_FULLY_MOSSY = textures[4];

        switch (blockState.get(StatueBlock.MOSS_LEVEL)) {
            default:
            case 0:
                return RenderLayer.getEntityCutoutNoCullZOffset(TEXTURE_CLEAN);
            case 1:
                return RenderLayer.getEntityCutoutNoCullZOffset(TEXTURE_SLIGHTLY_MOSSY);
            case 2:
                return RenderLayer.getEntityCutoutNoCullZOffset(TEXTURE_MEDIUMLY_MOSSY);
            case 3:
                return RenderLayer.getEntityCutoutNoCullZOffset(TEXTURE_MOSTLY_MOSSY);
            case 4:
                return RenderLayer.getEntityCutoutNoCullZOffset(TEXTURE_FULLY_MOSSY);
        }
    }

    public void setTransformsFromDirection(MatrixStack matrices, Direction direction, double xOffset, double yOffset, double zOffset) {
        if (direction == Direction.SOUTH) {
            matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(180));
            matrices.translate(getPixelOffset(xOffset), getPixelOffset(yOffset), (getPixelOffset(zOffset - 16.0D)));
            matrices.scale(1.0F, 1.0F, 1.0F);
        }
        else if (direction == Direction.NORTH) {
            matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(180));
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180));
            matrices.translate(getPixelOffset(-xOffset), getPixelOffset(yOffset), (getPixelOffset(zOffset)));
            matrices.scale(1.0F, 1.0F, 1.0F);
        }
        else if (direction == Direction.EAST) {
            matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(180));
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90));
            matrices.translate(getPixelOffset(-xOffset), getPixelOffset(yOffset), (getPixelOffset(zOffset - 16.0D)));
            matrices.scale(1.0F, 1.0F, 1.0F);
        }
        else if (direction == Direction.WEST) {
            matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(180));
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90));
            matrices.translate(getPixelOffset(xOffset), getPixelOffset(yOffset), (getPixelOffset(zOffset)));
            matrices.scale(1.0F, 1.0F, 1.0F);
        }
    }
}
