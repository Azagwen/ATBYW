package net.azagwen.atbyw.blocks.statues.render;

import net.azagwen.atbyw.blocks.statues.StatueBlock;
import net.azagwen.atbyw.blocks.statues.StatueBlockEntity;
import net.azagwen.atbyw.blocks.statues.render.model.ChickenStatueModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.SilverfishEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import static net.azagwen.atbyw.main.AtbywMain.newID;

public class SilverfishStatueBlockEntityRenderer extends BlockEntityRenderer<StatueBlockEntity> {
    private static String TEXTURE_PATH = "textures/entity/silverfish_statue/";
    public static Identifier TEXTURE_CLEAN = newID(TEXTURE_PATH + "silverfish_statue_0.png");
    private static Identifier TEXTURE_SLIGHTLY_MOSSY = newID(TEXTURE_PATH + "silverfish_statue_1.png");
    private static Identifier TEXTURE_MEDIUMLY_MOSSY = newID(TEXTURE_PATH + "silverfish_statue_2.png");
    private static Identifier TEXTURE_MOSTLY_MOSSY = newID(TEXTURE_PATH + "silverfish_statue_3.png");
    private static Identifier TEXTURE_FULLY_MOSSY = newID(TEXTURE_PATH + "silverfish_statue_4.png");

    public SilverfishStatueBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    public static Identifier getItemTexture() {
        return TEXTURE_CLEAN;
    }

    @Override
    public void render(StatueBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        SilverfishEntityModel silverfishModel = new SilverfishEntityModel();
        StatueRenderUtils utils = new StatueRenderUtils();
        BlockState blockState = entity.getCachedState();
        Direction direction = blockState.get(StatueBlock.FACING);

        matrices.push();
        utils.setTransformsFromDirection(matrices, direction, 8, -24, 4);
        silverfishModel.render(matrices, vertexConsumers.getBuffer(utils.getTextureFromMossLevel(blockState, new Identifier[] {
                TEXTURE_CLEAN,
                TEXTURE_SLIGHTLY_MOSSY,
                TEXTURE_MEDIUMLY_MOSSY,
                TEXTURE_MOSTLY_MOSSY,
                TEXTURE_FULLY_MOSSY
        })), light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
    }
}
